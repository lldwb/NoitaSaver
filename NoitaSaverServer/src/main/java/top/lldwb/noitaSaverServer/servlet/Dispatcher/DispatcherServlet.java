package top.lldwb.noitaSaverServer.servlet.Dispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverServer.servlet.Controller;
import top.lldwb.noitaSaverServer.servlet.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 核心控制器
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
@MultipartConfig
public class DispatcherServlet extends HttpServlet {
    /**
     * 收集 Controller - key为请求URL，value为Controller的完成类路径
     */
    private static final Map<Object, Object> map = new HashMap<>();

    /**
     * 加载配置文件
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        Properties prop = new Properties();
        try {
            InputStream input = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config.properties");
            prop.load(input);
            prop.forEach(map::put);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        requestURI = requestURI.replace(this.getServletContext().getContextPath(), "");
        String controllerClass = (String) map.get(requestURI);
        if (controllerClass != null) {
            Object instance = newInstance(controllerClass);
            if (instance instanceof Controller) {
                try {
                    Controller controller = (Controller) instance;
                    controller.execute(req, resp);
                } catch (Exception e) {
                    // 全局异常处理
                    handleException(resp, e);
                }
            } else {
                throw new ClassCastException(controllerClass
                        + " 转换为 org.zing.shop.servlet.Controller 失败");
            }
        } else {
            req.getServletContext().getNamedDispatcher("default").forward(req, resp);
        }
    }

    /**
     * 实例化Controller
     *
     * @param className
     * @return
     * @throws ServletException
     */
    private Object newInstance(String className)
            throws ServletException {
        try {
            return Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {
            throw new ServletException(className + "：实例化失败", e);
        }
    }

    /**
     * 全局异常处理
     *
     * @param resp
     * @param e
     * @throws IOException
     */
    private void handleException(HttpServletResponse resp, Exception e)
            throws IOException {
        ResponseData responseData = new ResponseData();
        responseData.setCode(500);
        responseData.setMsg(e.getMessage());
        print(resp, responseData);
    }

    /**
     * 响应消息到客户端浏览器
     *
     * @param resp
     * @param responseData
     * @throws IOException
     */
    private void print(HttpServletResponse resp, ResponseData responseData)
            throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(responseData));
        out.flush();
        out.close();
    }
}
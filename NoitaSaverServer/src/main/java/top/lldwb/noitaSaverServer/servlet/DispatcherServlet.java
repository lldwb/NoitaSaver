package top.lldwb.noitaSaverServer.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reflections.Reflections;
import top.lldwb.noitaSaverServer.action.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 核心控制器
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
@MultipartConfig
public class DispatcherServlet extends HttpServlet {
    /**
     * 收集 Controller - key为请求URL，value为Controller的完成类路径
     */
    private static final Map<String, Class<? extends Controller>> map = new HashMap<>();

    /**
     * 加载配置文件
     * <p>
     * GenericServlet.init()的源码中文翻译
     * 这是一个便利的方法，可以被重写以避免调用super.init(config)。
     * 不需要重写init(ServletConfig)，只需要重写这个方法，GenericServlet.init(ServletConfig config)将会调用它。ServletConfig对象仍然可以通过getServletConfig方法获取。
     *
     */
    @Override
    public void init() {
        // 扫描IDataValidator所在的包 com.lm.validate
        Reflections reflections = new Reflections(Controller.class.getPackage().getName());
        // 获取包com.lm.validate下面所有IDataValidator实现类
        Set<Class<? extends Controller>> implClass = reflections.getSubTypesOf(Controller.class);
        for (Class<? extends Controller> clazz : implClass) {
            System.out.println(clazz.getName());
            if (clazz.isAnnotationPresent(WebController.class)) {
                map.put(clazz.getDeclaredAnnotation(WebController.class).value(), clazz);
            }
        }
    }

    /**
     * 重写 HttpServlet.service(HttpServletRequest req, HttpServletResponse resp)，
     * HttpServlet.service(HttpServletRequest req, HttpServletResponse resp) 又是重写
     * GenericServlet.service(ServletRequest req, ServletResponse res)。
     * 这是一个便利的方法，可以被重写以避免调用super.init(config)。
     * <p>
     * GenericServlet.service(ServletRequest req, ServletResponse res)的源码中文翻译
     * 不需要重写init(ServletConfig)，只需要重写这个方法，GenericServlet.init(ServletConfig config)将会调用它。ServletConfig对象仍然可以通过getServletConfig方法获取。
     * <p>
     * HttpServlet.service(ServletRequest req, ServletResponse res)的源码中文翻译
     * 从公共服务方法接收标准的HTTP请求，并将它们分派到该类中定义的doMethod方法。这个方法是javax.servlet.Servlet.service方法的HTTP特定版本。不需要重写此方法。
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /**
         * 返回此请求的URL中从协议名称到第一行HTTP请求中的查询字符串的部分。Web容器不会对此字符串进行解码。例如：
         * HTTP请求的第一行
         *
         * 返回
         * POST /some/path.html HTTP/1.1
         * GET http://foo.bar/a.html HTTP/1.0
         */
        String requestURI = req.getRequestURI();
        /**
         * 在使用 Servlet 开发 Web 应用程序时，`requestURI` 表示请求的 URI 路径，包括应用程序的上下文路径和请求路径。例如，如果应用程序的上下文路径为 `/myapp`，请求的 URI 路径为 `/myapp/products/show`，则 `requestURI` 为 `/myapp/products/show`。
         *
         * 有时候我们希望将请求的 URI 路径中的应用程序上下文路径去除，只保留请求路径，这时候可以使用 `replace()` 方法将应用程序上下文路径替换为空字符串，从而得到请求路径。
         *
         * 具体来说，`requestURI.replace(this.getServletContext().getContextPath(), "")` 的作用是将请求的 URI 路径中的应用程序上下文路径去除，返回的是请求的相对路径。例如，如果应用程序的上下文路径为 `/myapp`，请求的 URI 路径为 `/myapp/products/show`，则执行上述代码后得到的是 `/products/show`。
         *
         * 这个相对路径在某些场景下可能更加方便使用，比如在处理路由时。
         */
        requestURI = requestURI.replace(this.getServletContext().getContextPath(), "");
        Class<? extends Controller> controllerClass = map.get(requestURI);
        if (controllerClass != null) {
            try {
                Controller controller = controllerClass.newInstance();
                // 设置请求和响应为utf-8编码
                req.setCharacterEncoding("utf-8");
                resp.setCharacterEncoding("utf-8");
                controller.execute(req, resp);
            } catch (Exception e) {
                // 全局异常处理
                handleException(resp, e);
            }
        } else {
            // 作用是将请求转发给默认的 Servlet，这通常是用于处理静态资源的 Servlet。
            req.getServletContext().getNamedDispatcher("default").forward(req, resp);
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
     * 将响应消息返回客户端浏览器
     *
     * @param resp
     * @param responseData
     * @throws IOException
     */
    private void print(HttpServletResponse resp, ResponseData responseData)
            throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        // 文本输出流
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(responseData));
        out.flush();
        out.close();
    }
}
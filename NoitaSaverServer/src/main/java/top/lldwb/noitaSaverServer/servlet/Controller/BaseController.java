package top.lldwb.noitaSaverServer.servlet.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 它是所有的用户定义Servlet的父类 - 把一些公共操作的代码，抽象提取出来，在父类中定义，子类可以直接使用
 *
 * @author zqx
 * @date 2023-04-18
 */
public abstract class BaseController implements Controller {
    /**
     * 成功响应的封装 - 默认
     *
     * @param data
     * @return
     */
    public ResponseData successJson(Object data) {
        return new ResponseData(200, "", data);
    }

    /**
     * 重载方法 - 封装所有的成功响应信息
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData successJson(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    /**
     * 错误响应的封装 - 默认
     *
     * @param msg
     * @return
     */
    public ResponseData errorJson(String msg) {
        return new ResponseData(500, msg, null);
    }

    /**
     * 重载方法 - 封装所有的错误响应信息
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseData errorJson(Integer code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    /**
     * 响应 JSON 字符串到客户端浏览器中
     *
     * @param resp
     * @param responseData
     * @throws IOException
     */
    protected void print(HttpServletResponse resp, ResponseData responseData)
            throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(new ObjectMapper().writeValueAsString(responseData));
        out.flush();
        out.close();
    }
}
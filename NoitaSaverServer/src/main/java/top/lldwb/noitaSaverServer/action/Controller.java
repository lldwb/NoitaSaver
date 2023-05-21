package top.lldwb.noitaSaverServer.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller接口 - 处理前端请求
 */
public interface Controller {
    /**
     * 核心请求处理方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

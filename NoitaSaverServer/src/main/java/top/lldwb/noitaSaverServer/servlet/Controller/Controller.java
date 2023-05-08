package top.lldwb.noitaSaverServer.servlet.Controller;

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
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

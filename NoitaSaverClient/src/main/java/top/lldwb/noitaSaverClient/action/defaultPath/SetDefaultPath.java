package top.lldwb.noitaSaverClient.action.defaultPath;

import top.lldwb.noitaSaverClient.entity.DefaultPath;
import top.lldwb.noitaSaverClient.service.DefaultService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/setDefaultPath")
public class SetDefaultPath extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        DefaultService defaultService = new DefaultService();
        defaultService.setDefaultPath(new DefaultPath(req.getParameter("readPath"),req.getParameter("writePath")));
    }
}

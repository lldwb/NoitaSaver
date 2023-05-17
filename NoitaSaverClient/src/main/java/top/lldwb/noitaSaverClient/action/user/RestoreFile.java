package top.lldwb.noitaSaverClient.action.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverClient.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/restoreFile")
public class RestoreFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = new User();
        user.setUserKey(req.getParameter("key"));
        String path = req.getParameter("path");

        UserService userService = new UserService();
        resp.getWriter().print(new ObjectMapper().writeValueAsString(userService.restoreFile(path,user)));
    }
}

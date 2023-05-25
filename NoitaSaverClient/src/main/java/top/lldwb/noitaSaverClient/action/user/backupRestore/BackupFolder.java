package top.lldwb.noitaSaverClient.action.user.backupRestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverClient.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 云端备份
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/backupFolder")
public class BackupFolder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = new User();
        user.setUserKey(req.getParameter("key"));
        String path = req.getParameter("path");

        UserService userService = new UserService();
        resp.getWriter().print(new ObjectMapper().writeValueAsString(userService.backupFolder(path,user)));
    }
}

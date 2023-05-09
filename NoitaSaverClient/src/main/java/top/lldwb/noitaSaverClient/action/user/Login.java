package top.lldwb.noitaSaverClient.action.user;

import top.lldwb.noitaSaverClient.service.UserService;
import top.lldwb.noitaSaverClient.utils.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录接口
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService.login(new User());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

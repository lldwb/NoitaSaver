package top.lldwb.noitaSaverClient.action.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.service.UserService;
import top.lldwb.noitaSaverClient.entity.User;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = new User();
        user.setUserName(req.getParameter("name"));
        user.setUserPassword(req.getParameter("password"));

        // 获取并存放用户登录状态到 Session
        req.getSession().setAttribute("user", new ObjectMapper().writeValueAsString(UserService.login(user)));

    }
}

package top.lldwb.noitaSaverClient.action.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverClient.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册接口
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        User user = new User();
        user.setUserName(req.getParameter("name"));
        user.setUserPassword(req.getParameter("password"));
        user.setUserMail(req.getParameter("mail"));

        System.out.println(user);

        User user1 = new UserService().registration(user);
        if (user.getUserName().equals(user1.getUserName()) && user.getUserPassword().equals(user1.getUserPassword()) && user.getUserMail().equals(user1.getUserMail())) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(user1));
        } else {
            // 登录失败时，返回500状态码
            resp.setStatus(500);
            resp.getWriter().print("注册失败,可能是用户名或者邮箱被占用");
        }


    }
}

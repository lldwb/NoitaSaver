package top.lldwb.noitaSaverClient.action.user.emailVerificationCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverClient.service.EmailVerificationCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发送邮箱验证码接口
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/sendEmailVerificationCode")
public class SendEmailVerificationCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserMail(req.getParameter("mail"));
        if (!new EmailVerificationCodeService().sendEmailVerificationCode(user)) {
            // 登录失败时，返回500状态码
            resp.setStatus(500);
            resp.getWriter().print("发送失败");
        }
    }
}
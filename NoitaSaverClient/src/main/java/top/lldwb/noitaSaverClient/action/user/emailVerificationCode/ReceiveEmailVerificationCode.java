package top.lldwb.noitaSaverClient.action.user.emailVerificationCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.Entity.MailVerificationCode;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverClient.service.EmailVerificationCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收邮箱验证码接口
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/receiveEmailVerificationCode")
public class ReceiveEmailVerificationCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MailVerificationCode mailVerificationCode = new MailVerificationCode();
        mailVerificationCode.setMailVerificationCodeEmail(req.getParameter("mail"));
        mailVerificationCode.setMailVerificationCodeCode(req.getParameter("code"));

        User user = new EmailVerificationCodeService().receiveEmailVerificationCode(mailVerificationCode);
        if (mailVerificationCode.getMailVerificationCodeEmail().equals(user.getUserMail())) {
            resp.getWriter().println(new ObjectMapper().writeValueAsString(user));
        } else {
            // 登录失败时，返回500状态码
            resp.setStatus(500);
            resp.getWriter().print("邮箱或者验证码有误");
        }
    }
}

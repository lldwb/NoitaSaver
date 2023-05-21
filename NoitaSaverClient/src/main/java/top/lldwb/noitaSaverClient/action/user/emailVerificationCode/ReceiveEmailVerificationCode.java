package top.lldwb.noitaSaverClient.action.user.emailVerificationCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverClient.service.EmailVerificationCodeService;
import top.lldwb.noitaSaverClient.service.UserService;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MailVerificationCode mailVerificationCode = new MailVerificationCode();
        mailVerificationCode.setMailVerificationCodeEmail(req.getParameter("mail"));
        mailVerificationCode.setMailVerificationCodeCode(req.getParameter("code"));

        // 获取并存放用户登录状态到 Session
        req.getSession().setAttribute("user", new ObjectMapper().writeValueAsString(new EmailVerificationCodeService().receiveEmailVerificationCode(mailVerificationCode)));
    }
}

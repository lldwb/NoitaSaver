package top.lldwb.noitaSaverClient.action.emailVerificationCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverClient.service.EmailVerificationCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 发送邮箱验证码接口
 * 返回1(成功)或者0(失败)
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/sendEmailVerificationCode")
public class SendEmailVerificationCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (new EmailVerificationCodeService().sendEmailVerificationCode(req.getParameter("mail"))){
            resp.getWriter().print(1);
        }else {
            resp.getWriter().print(0);
        }
    }
}
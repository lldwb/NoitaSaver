package top.lldwb.noitaSaverClient.utils;

import org.junit.jupiter.api.Test;
import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverClient.service.EmailVerificationCodeService;

import java.io.*;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtilTest{
    @Test
    public void file() throws IOException {
        MailVerificationCode mailVerificationCode = new MailVerificationCode();
        mailVerificationCode.setMailVerificationCodeEmail("3247187440@qq.com");
        mailVerificationCode.setMailVerificationCodeCode("123456");
        new EmailVerificationCodeService().receiveEmailVerificationCode( mailVerificationCode);
    }
}

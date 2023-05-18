package top.lldwb.noitaSaverClient.utils;

import org.junit.jupiter.api.Test;
import top.lldwb.noitaSaverClient.entity.User;

import java.io.*;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtilTest{
    @Test
    public void file() throws IOException {
        User user  = new User();
        user.setUserMail("3247187440@qq.com");
        System.out.println(new ClientSocketUtil().sendEmailVerificationCode(user));
    }
}

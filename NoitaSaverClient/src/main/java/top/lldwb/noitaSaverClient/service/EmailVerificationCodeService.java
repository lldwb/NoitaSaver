package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;

import java.io.IOException;

/**
 * 邮箱验证码业务类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class EmailVerificationCodeService {
    /**
     * 发送邮箱验证码
     * @param mail 需要验证码的邮箱
     * @return 如果为假，邮箱对应的用户不存在
     * @throws IOException
     */
    public Boolean sendEmailVerificationCode(String mail) throws IOException {
        return new ClientSocketUtil().sendEmailVerificationCode(mail);
    }

    /**
     * 接收邮箱验证码
     * @param mailVerificationCode 邮箱验证码对象
     * @return 如果为验证码无误，返回用户对象
     * @throws IOException
     */
    public User receiveEmailVerificationCode(MailVerificationCode mailVerificationCode) throws IOException {
        return new ClientSocketUtil().receiveEmailVerificationCode(mailVerificationCode);
    }
}

package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaver.Entity.MailVerificationCode;

import java.sql.SQLException;

public interface MailVerificationCodeDao {
    /**
     * 根据 mail 和 验证码 在数据库中获取 MailVerificationCode 对象
     * 并且忽略超过时间的验证码
     * @param code 验证码
     * @param mail 用户邮箱
     * @return 有返回，无返回
     * @throws SQLException
     * @throws NoSuchFieldException
     */
    MailVerificationCode getMailVerificationCodeByCodeMail(String code, String mail);

    /**
     * 在数据库中添加验证码
     */
    void setMailVerificationCode(MailVerificationCode mailVerificationCode);
}

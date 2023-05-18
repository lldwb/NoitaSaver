package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.entity.MailVerificationCode;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class MailVerificationCodeDao {
    /**
     * 根据 mail 在数据库中获取 MailVerificationCode 对象
     *
     * @param mail 用户邮箱
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static MailVerificationCode getMailVerificationCodeMail(String mail) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new MailVerificationCode(), "select * from user where user_mail=?", mail);
    }

    /**
     * 在数据库中添加验证码
     *
     * @param mail 邮箱
     * @param code 验证码
     * @return
     * @throws SQLException
     */
    public static int setMailVerificationCode(String mail, String code) throws SQLException {
        return new MySQLUtil().update("insert into mailVerificationCodeMail(mailVerificationCode_email,mailVerificationCode_code) values(?,?)", mail, code);
    }
}

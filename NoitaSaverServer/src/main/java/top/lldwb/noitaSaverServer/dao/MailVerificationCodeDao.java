package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverClient.entity.MailVerificationCode;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class MailVerificationCodeDao {
    /**
     * 根据 mail 和 验证码 在数据库中获取 MailVerificationCode 对象
     * 并且忽略超过时间的验证码
     * @param code 验证码
     * @param mail 用户邮箱
     * @return 有返回，无返回
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static MailVerificationCode getMailVerificationCodeByCodeMail(String code, String mail) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new MailVerificationCode(), "select * from mailVerificationCode where now() < mailVerificationCode_expire_time and mailVerificationCode_code=? and mailVerificationCode_email = ?", code, mail);
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
        return new MySQLUtil().update("insert into mailVerificationCode(mailVerificationCode_email,mailVerificationCode_code) values(?,?)", mail, code);
    }
}

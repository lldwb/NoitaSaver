package top.lldwb.noitaSaver.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lldwb.noitaSaver.DbUtil.conf.AnotherName;

import java.sql.Timestamp;

/**
 * 邮箱验证码实体类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVerificationCode {
    /**
     * 验证码记录ID，自增1，主键，非空
     */
    @AnotherName("mailVerificationCode_id")
    private int mailVerificationCodeId;
    /**
     * 接收验证码的邮箱，最长255个字符，非空
     */
    @AnotherName("mailVerificationCode_email")
    private String mailVerificationCodeEmail;
    /**
     * 邮箱验证码，6位数字，非空
     */
    @AnotherName("mailVerificationCode_code")
    private String mailVerificationCodeCode;
    /**
     * 创建时间，非空，默认当前时间戳
     */
    @AnotherName("mailVerificationCode_create_time")
    private Timestamp mailVerificationCodeCreateTime;
    /**
     * 过期时间，非空，默认创建时间后的5分钟
     */
//    @AnotherName("mailVerificationCode_expire_time")
//    private Timestamp mailVerificationCodeExpireTime;
}

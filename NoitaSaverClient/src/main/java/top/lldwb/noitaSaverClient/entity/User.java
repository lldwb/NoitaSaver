package top.lldwb.noitaSaverClient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lldwb.noitaSaver.DbUtil.conf.AnotherName;

/**
 * 用户实体类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID，自增1，主键，非空
     */
    @AnotherName("user_id")
    private int userId;
    /**
     * 用户名，最长15个字符，非空
     */
    @AnotherName("user_name")
    private String userName;
    /**
     * 密码，最长15个字符，非空
     */
    @AnotherName("user_password")
    private String userPassword;
    /**
     * 邮箱，最长255个字符，非空
     */
    @AnotherName("user_mail")
    private String userMail;

    /**
     * 用户状态，0表示未邮箱认证，1表示通过认证，2表示注销，非空
     */
    @AnotherName("user_state")
    private int userState;
    /**
     * 访问秘钥，(id+时间戳+随机函数)使用MD5加密，最长32个字符
     */
    @AnotherName("user_key")
    private String userKey;
}

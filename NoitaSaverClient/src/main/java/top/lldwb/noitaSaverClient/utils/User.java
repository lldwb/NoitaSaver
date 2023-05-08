package top.lldwb.noitaSaverClient.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户ID，自增1，主键，非空
     */
    private int userId;
    /**
     * 用户名，最长15个字符，非空
     */
    private String userName;
    /**
     * 密码，最长15个字符，非空
     */
    private String userPassword;
    /**
     * 用户状态，0表示未邮箱认证，1表示通过认证，2表示注销，非空
     */
    private int userState;
    /**
     * 访问秘钥，(id+时间戳+随机函数)使用MD5加密，最长32个字符
     */
    private String userKey;
}

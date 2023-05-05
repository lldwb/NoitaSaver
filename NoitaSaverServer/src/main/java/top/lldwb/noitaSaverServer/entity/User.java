package top.lldwb.noitaSaverServer.entity;

import lombok.Data;

/**
 * 用户实体类
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
public class User {
    public int id;
    /**
     * 用户名
     */
    public String name;
    /**
     * 邮箱认证状态
     */
    public boolean emailAuthentication;
    /**
     * 访问秘钥，(id+时间戳+随机函数)使用MD5加密
     */
    public String accesskey;
}

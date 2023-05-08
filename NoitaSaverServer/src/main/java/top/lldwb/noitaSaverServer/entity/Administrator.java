package top.lldwb.noitaSaverServer.entity;

import lombok.Getter;

/**
 * 管理员配置
 * @author 安然的尾巴
 * @version 1.0
 */
@Getter
public class Administrator {

    /**
     * 用户名，最长15个字符，非空
     */
    private final String NAME="";
    /**
     * 密码，最长15个字符，非空
     */
    private final String PASSWORD="";
}

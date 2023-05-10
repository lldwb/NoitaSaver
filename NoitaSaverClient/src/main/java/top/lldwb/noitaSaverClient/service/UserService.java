package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaver.encrypt.EncryptTypes;
import top.lldwb.noitaSaver.encrypt.EncryptUtil;
import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;
import top.lldwb.noitaSaverClient.entity.User;

import java.io.IOException;

/**
 * 用户有关的业务类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserService {
    /**
     * 登录
     *
     * @param user 用户信息
     * @return 判断是否正确密码，如果正确返回User对象
     */
    public static User login(User user) throws IOException, ClassNotFoundException {
        // 对用户密码加密
        user.setUserPassword(EncryptUtil.encrypt(user.getUserPassword() + user.getUserName(), EncryptTypes.MD5));
        return new ClientSocketUtil().login(user);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 判断是否有用户，如果没有创建并返回User对象
     */
    public static User registration(User user) throws IOException, ClassNotFoundException {
        // 对用户密码加密
        user.setUserPassword(EncryptUtil.encrypt(user.getUserPassword() + user.getUserName(), EncryptTypes.MD5));
        return new ClientSocketUtil().registration(user);
    }
}
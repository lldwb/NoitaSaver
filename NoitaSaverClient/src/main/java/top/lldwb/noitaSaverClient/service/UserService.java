package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;
import top.lldwb.noitaSaverClient.utils.User;

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
        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        return clientSocketUtil.login(user);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 判断是否有用户，如果没有创建并返回User对象
     */
    public static User registration(User user) throws IOException, ClassNotFoundException {
        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        return clientSocketUtil.registration(user);
    }
}

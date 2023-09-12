package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaver.encrypt.EncryptTypes;
import top.lldwb.noitaSaver.encrypt.EncryptUtil;
import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;

import java.io.IOException;

/**
 * 用户有关的业务类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserService {
    public User userPassEncrypt(User user){
        // 对用户密码加密
        user.setUserPassword(EncryptUtil.encrypt(user.getUserPassword() + user.getUserName(), EncryptTypes.MD5));
        return user;
    }
    /**
     * 登录
     *
     * @param user 用户信息
     * @return 判断是否正确密码，如果正确返回User对象
     */
    public User login(User user) {
        // 对用户密码加密
        user = this.userPassEncrypt(user);
        try {
            ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
            user = clientSocketUtil.login(user);
            clientSocketUtil.close();
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 判断是否有用户，如果没有创建并返回User对象
     */
    public User registration(User user) {
        // 对用户密码加密
        user = this.userPassEncrypt(user);
        try {
            ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
            user = clientSocketUtil.registration(user);
            clientSocketUtil.close();
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 云备份
     * @param path 需要备份的地址
     * @param user 用户对象，用于验证权限
     * @return
     * @throws IOException
     */
    public Boolean backupFolder(String path, User user) throws IOException {
        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        Boolean aBoolean = clientSocketUtil.backupFolder(path,user);
        clientSocketUtil.close();
        return aBoolean;
    }


    /**
     * 向服务端发起云恢复请求
     * @param path 需要恢复的地址
     * @param user 用户对象，用于验证权限
     * @return 云恢复状态信息(0:恢复成功,1:验证失败,2:没有云端备份,3:接收云端备份失败)
     * @throws IOException
     */
    public int restoreFolder(String path, User user) throws IOException {
        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        int count = clientSocketUtil.restoreFolder(path,user);
        clientSocketUtil.close();
        return count;
    }
}

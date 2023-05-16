package top.lldwb.noitaSaverClient.utils;

import top.lldwb.noitaSaver.SocketUtil.SocketUtil;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverClient.service.UserService;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Socket工具类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtil extends SocketUtil {
    // 流套接字
    Socket socket;

    /**
     * 创建和服务器的通讯
     *
     * @return
     * @throws IOException
     */
    public ClientSocketUtil() throws IOException {
        // 创建一个流套接字并将其连接到指定主机上的指定端口号。
        // host:服务器地址 port:服务器端口
        super(new Socket("127.0.0.1", 8888));
    }

    /**
     * 登录
     *
     * @param user 用户信息
     * @return 判断是否正确密码，如果正确返回User对象
     */
    public User login(User user) throws IOException, ClassNotFoundException {
        if (user.getUserName() == null || user.getUserPassword() == null) {
            return null;
        }

        // 发送判断信息
        this.sendString("登录");

        if (this.checkUser(user)) {
            return this.receiveObject(User.class);
        } else {
            return null;
        }
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 判断是否有用户，如果没有创建并返回User对象
     */
    public User registration(User user) throws IOException, ClassNotFoundException {
        if (user.getUserName() == null || user.getUserPassword() == null || user.getUserMail() == null) {
            return null;
        }

        // 发送判断信息
        this.sendString("注册");

        // 发送实体类
        this.sendObject(user);

        // 获取
        if (this.receiveObject(Boolean.class)) {
            return this.receiveObject(User.class);
        } else {
//            System.out.println(1234);
            return null;
        }
    }

    /**
     * 向服务端发起云备份请求
     * @param path 需要备份的地址
     * @param user 用户对象，用于验证权限
     * @return
     * @throws IOException
     */
    public Boolean backupFolder(String path, User user) throws IOException {
        // 发送判断信息
        this.sendString("云备份");
        if (checkUser(user)) {
            // 对文件夹进行压缩,创建一个临时文件
            FileUtil.zipOutputFolder(path, "C:\\Users\\Public\\Documents\\NoitaSaverClient\\DefaultPath");
            // 发送临时文件
            super.sendFile(new File("C:\\Users\\Public\\Documents\\NoitaSaverClient\\DefaultPath.zip"));
            // 获取
            if (this.receiveObject(Boolean.class)) {
                System.out.println("成功");
                // 删除临时文件
                new File("C:\\Users\\Public\\Documents\\NoitaSaverClient\\DefaultPath.zip").delete();
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户
     *
     * @param user 用户对象
     * @return 返回服务端的判断结果
     * @throws IOException
     */
    private Boolean checkUser(User user) throws IOException {
        // 发送实体类
        this.sendObject(user);

        // 获取服务端发过来的判断结果
        return this.receiveObject(Boolean.class);
    }
}

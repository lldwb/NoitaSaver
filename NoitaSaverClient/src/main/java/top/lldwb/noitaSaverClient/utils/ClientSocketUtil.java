package top.lldwb.noitaSaverClient.utils;

import top.lldwb.noitaSaver.Entity.MailVerificationCode;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaver.SocketUtil.SocketUtil;
import top.lldwb.noitaSaver.fileUtil.FileUtil;

import java.io.*;
import java.net.Socket;

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
//        super(new Socket("127.0.0.1", 8888));
        super(new Socket("noitasaver.lldwb.top", 888866));
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
     *
     * @param path 需要备份的地址
     * @param user 用户对象，用于验证权限
     * @return
     * @throws IOException
     */
    public Boolean backupFolder(String path, User user) throws IOException {
        // 发送判断信息
        this.sendString("云备份");
        if (checkUser(user)) {
            // 临时文件文件
            String temporaryPath = "C:\\Users\\Public\\Documents\\NoitaSaver\\Client\\Tempfiles";
            // 对文件夹进行压缩,创建一个临时文件
            FileUtil.zipOutputFolder(path, temporaryPath);
            File file = new File(temporaryPath + ".zip");
            // 发送临时文件
            if (super.sendFile(file)) {
                System.out.println("成功");
                // 删除临时文件
                file.delete();
                return true;
            }
        }
        return false;
    }

    /**
     * 向服务端发起云恢复请求
     *
     * @param path 需要恢复的地址
     * @param user 用户对象，用于验证权限
     * @return 云恢复状态信息(0 : 恢复成功, 1 : 验证失败, 2 : 没有云端备份, 3 : 接收云端备份失败)
     * @throws IOException
     */
    public int restoreFolder(String path, User user) throws IOException {
        // 发送判断信息
        this.sendString("云恢复");
        if (checkUser(user)) {
            if (this.receiveObject(Boolean.class)) {
                // 临时文件文件地址
                String temporaryPath = "C:\\Users\\Public\\Documents\\NoitaSaver\\Client\\Tempfiles";
                // 接收临时文件
                if (super.receiveFile(temporaryPath + ".zip")) {
                    // 删除恢复地址的所有文件
                    FileUtil.deleteFileFolder(path);
                    // 对临时文件进行解压
                    FileUtil.zipInputFolder(path, temporaryPath);
                    // 删除临时文件
                    new File(temporaryPath + ".zip").delete();
                    return 0;
                } else {

                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    /**
     * 发送邮箱验证码
     *
     * @param user 需要验证码的用户
     * @return 如果为假，邮箱对应的用户不存在
     * @throws IOException
     */
    public Boolean sendEmailVerificationCode(User user) throws IOException {
        // 发送判断信息
        this.sendString("发送验证码");
        this.sendObject(user);
        return this.receiveObject(Boolean.class);
    }

    /**
     * 接收邮箱验证码
     *
     * @param mailVerificationCode 邮箱验证码对象
     * @return 如果为验证码无误，返回用户对象
     * @throws IOException
     */
    public User receiveEmailVerificationCode(MailVerificationCode mailVerificationCode) throws IOException {
        // 发送判断信息
        this.sendString("接收验证码");
        this.sendObject(mailVerificationCode);
        System.out.println(mailVerificationCode);
        if (this.receiveObject(Boolean.class)) {
            return this.receiveObject(User.class);
        }
        {
            return null;
        }
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

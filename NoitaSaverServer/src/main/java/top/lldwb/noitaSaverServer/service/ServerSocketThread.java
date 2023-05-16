package top.lldwb.noitaSaverServer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.SocketUtil.SocketUtil;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.utils.MailUtil;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketThread extends SocketUtil implements Runnable {

    public ServerSocketThread(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            // 接收客户端发出的类型识别字符串
            String types = this.receiveString();
            System.out.println(types);
            // 格式:“类型\n”
            switch (types) {
                case "登录":
                    login();
                    break;
                case "云备份":
                    backupFile();
                    break;
                case "云恢复":
                    break;
                case "邮箱":
                    types = this.receiveString();
                    System.out.println(types);
                    MailUtil.sendSession(types, "验证码", "2345");
                    break;
                case "注册":
                    registration();
                    break;
                default:
                    break;
            }
            socket.close();

        } catch (IOException | SQLException | NoSuchFieldException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录
     */
    private void login() throws IOException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);
        System.out.println(user);

        // 通过 UserDao 类的 getUser 方法从数据库中获取与输入用户名相符的用户信息（User）
        User userDao = UserDao.getUser(user.getUserName());
        System.out.println(userDao);

        // 如果密码一致，向客户端发送 true，并将从数据库中获取到的用户对象也发送过去
        if (user.getUserPassword().equals(userDao.getUserPassword())) {
            this.sendObject(true);
            this.sendObject(userDao);
        }
        // 如果密码不一致，向客户端发送 false
        else {
            this.sendObject(false);
        }
    }

    /**
     * 注册
     */
    private void registration() throws IOException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);

        System.out.println(user);

        // 判断是否有用户，如果没有执行如下代码创建用户，并判断是否创建成功，一切成功后向客户端发送 true
        if (!user.getUserName().equals(UserDao.getUser(user.getUserName()).getUserName())) {
            System.out.println(UserDao.setUser(user.getUserName(), user.getUserPassword(), user.getUserMail()));
            System.out.println(true);
            this.sendObject(true);
            this.sendObject(UserDao.getUser(user.getUserName()));
        }
        // 如果有，向客户端发送 false
        else {
            this.sendObject(false);
        }
    }

    /**
     * 备份文件
     * @throws IOException
     */
    private void backupFile() throws IOException {
        this.receiveFile("G:\\test\\1.zip");
        this.sendObject(true);
    }

    /**
     * 恢复文件
     */
    private void restoreFile(){

    }
}

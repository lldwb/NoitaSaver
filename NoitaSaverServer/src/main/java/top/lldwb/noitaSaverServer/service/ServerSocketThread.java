package top.lldwb.noitaSaverServer.service;

import top.lldwb.noitaSaver.Entity.MailVerificationCode;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaver.SocketUtil.SocketUtil;
import top.lldwb.noitaSaver.encrypt.EncryptTypes;
import top.lldwb.noitaSaver.encrypt.EncryptUtil;
import top.lldwb.noitaSaverServer.dao.*;
import top.lldwb.noitaSaverServer.utils.MailUtil;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;

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
                    backupFolder();
                    break;
                case "云恢复":
                    restoreFolder();
                    break;
                case "发送验证码":
                    sendEmailVerificationCode();
                    break;
                case "接收验证码":
                    receiveEmailVerificationCode();
                    break;
                case "注册":
                    registration();
                    break;
                default:
                    break;
            }
//            socket.close();

        } catch (IOException | SQLException | NoSuchFieldException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录
     */
    private void login() throws IOException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        // 验证用户
        User user = this.checkUser();
        if (user != null) {
            this.sendObject(user);
        }
    }

    /**
     * 注册
     */
    private void registration() throws IOException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);

//        System.out.println(user);
        UserDao dao = new UserDaoImpl();

        // 判断是否有用户或者邮箱，如果没有执行如下代码创建用户，并判断是否创建成功，一切成功后向客户端发送 true
        if (!(user.getUserName().equals(dao.getUserByName(user.getUserName()).getUserName()) || user.getUserMail().equals(dao.getUserByMail(user.getUserMail()).getUserMail()))) {
            // 创建远程秘钥
            user.setUserKey(EncryptUtil.encrypt(user.getUserName() + user.getUserMail(), EncryptTypes.MD5) + EncryptUtil.encrypt(System.currentTimeMillis() + user.getUserPassword(), EncryptTypes.MD5));

            // 在数据库创建用户
            System.out.println(dao.setUser(user.getUserName(), user.getUserPassword(), user.getUserMail(), user.getUserKey()));
            System.out.println(true);
            this.sendObject(true);
            this.sendObject(dao.getUserByName(user.getUserName()));
        }
        // 如果有，向客户端发送 false
        else {
            this.sendObject(false);
        }
    }

    /**
     * 备份用户所有文件
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void backupFolder() throws IOException, ClassNotFoundException {
        // 验证用户
        User user = this.checkUserKey();
        // 读取用户备份路径
        String path = new UserBackupPathService().getUserBackupPath().getName();
        if (user != null) {
            // 用户备份路径+用户id+后缀
            this.receiveFile(path + user.getUserId() + ".zip");
        }
    }

    /**
     * 恢复用户所有文件
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void restoreFolder() throws IOException, ClassNotFoundException {
        // 验证用户
        User user = this.checkUserKey();
        // 读取用户备份路径
        String path = new UserBackupPathService().getUserBackupPath().getName();
        // 是否有用户&&判断服务端存储地址是否有用户的备份文件(路径为:用户备份路径+用户id+后缀)
        if (user != null && new File(path + user.getUserId() + ".zip").isFile()) {
            this.sendObject(true);
            // 发送用户的备份文件
            this.sendFile(new File(path + user.getUserId() + ".zip"));
        } else {
            this.sendObject(false);
        }
    }

    /**
     * 发送邮箱验证码
     */
    private void sendEmailVerificationCode() throws IOException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);
        System.out.println(user);
        UserDao dao = new UserDaoImpl();
        user = dao.getUserByMail(user.getUserMail());
        System.out.println(user);
        // 判断用户邮箱是否存在
        if (user.getUserId() != 0) {
            // 发送成功
            this.sendObject(true);
            // 生成验证码
            String code = (new Random().nextInt(900000) + 100000) + "";
            // 发送验证码
            MailUtil.sendSession(user.getUserMail(), "noitaSaver验证码", code);
            // 在数据库中存储验证码
            MailVerificationCodeDao mailVerificationCodeDao = new MailVerificationCodeDaoImpl();
            mailVerificationCodeDao.setMailVerificationCode(user.getUserMail(), code);
        } else {
            // 发送失败
            this.sendObject(false);
        }
    }

    /**
     * 接收邮箱验证码
     */
    private void receiveEmailVerificationCode() throws IOException {
        // 接收客户端发过来的邮箱
        MailVerificationCode mailVerificationCode = this.receiveObject(MailVerificationCode.class);
        System.out.println(mailVerificationCode);
        MailVerificationCodeDao mailVerificationCodeDao = new MailVerificationCodeDaoImpl();
        if (mailVerificationCodeDao.getMailVerificationCodeByCodeMail(mailVerificationCode.getMailVerificationCodeCode(), mailVerificationCode.getMailVerificationCodeEmail()).getMailVerificationCodeId() != 0) {
            // 如果正确，向客户端发送 true 并返回 user 对象
            this.sendObject(true);
            System.out.println(true);
            // 修改用户状态为通过邮箱验证
            UserDao dao = new UserDaoImpl();
            dao.updateUserStatusByMail(mailVerificationCode.getMailVerificationCodeEmail(), 1);
            User user = dao.getUserByMail(mailVerificationCode.getMailVerificationCodeEmail());
            System.out.println(user);
            this.sendObject(user);
        } else {
            // 发送失败
            this.sendObject(false);
        }
    }

    /**
     * 验证用户
     *
     * @return 返回用户对象，如果没有就是错误
     * @throws IOException
     */
    private User checkUser() throws IOException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);
        System.out.println(user);

        // 通过 UserDaoNo 类的 getUserByName 方法从数据库中获取与输入用户名相符的用户信息（User）
        UserDao dao = new UserDaoImpl();
        User userDao = dao.getUserByName(user.getUserName());
        System.out.println(userDao);
        // 如果密码一致，向客户端发送 true 并返回 user 对象
        if (user.getUserPassword().equals(userDao.getUserPassword())) {
            this.sendObject(true);
            return userDao;
        }
        // 如果密码不一致，向客户端发送 false 并返回 null
        else {
            this.sendObject(false);
            return null;
        }
    }

    /**
     * 验证用户
     *
     * @return 返回用户对象，如果没有就是错误
     * @throws IOException
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private User checkUserKey() throws IOException {
        // 接收客户端发过来的JSON并转成Java对象
        User user = this.receiveObject(User.class);
        System.out.println(user);

        // 通过 UserDaoNo 类的 getUserByName 方法从数据库中获取与输入用户名相符的用户信息（User）
        UserDao dao = new UserDaoImpl();
        User userDao = dao.getUserByKey(user.getUserKey());
        System.out.println(userDao);
        // 如果密码一致，向客户端发送 true 并返回 user 对象
        if (userDao.getUserId() != 0) {
            this.sendObject(true);
            return userDao;
        }
        // 如果密码不一致，向客户端发送 false 并返回 null
        else {
            this.sendObject(false);
            return null;
        }
    }
}

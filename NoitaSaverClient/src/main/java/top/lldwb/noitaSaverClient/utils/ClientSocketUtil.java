package top.lldwb.noitaSaverClient.utils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Socket工具类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtil {

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
        this.socket = new Socket("127.0.0.1", 8888);
    }

    /**
     * 登录
     *
     * @param user 用户信息
     * @return 判断是否正确
     */
    public Boolean login(User user) throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        if (user.getUserName() == null || user.getUserPassword() == null) {
            return false;
        }

//        this.sendJudgment("登录");

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("登录");
        bufferedWriter.flush();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Boolean aBoolean = (Boolean) objectInputStream.readObject();
        return aBoolean;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public Boolean registration(User user) throws IOException, ClassNotFoundException {
//        this.sendJudgment("注册");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(user);
//        objectOutputStream.flush();
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        Boolean aBoolean = (Boolean) objectInputStream.readObject();
//        return aBoolean;
        return null;
    }

    /**
     * 发送判断信息
     */
    public void sendJudgment(String judgment) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
//        bufferedWriter.write(judgment);
//        bufferedWriter.flush();
    }


}

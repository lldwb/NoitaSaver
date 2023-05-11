package top.lldwb.noitaSaverClient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.User;

import java.io.*;
import java.net.Socket;

/**
 * Socket工具类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtil {

    // 流套接字
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

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
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        // 发送实体类
        this.sendObject(user);

        // 获取
        if (this.receiveObject(Boolean.class)) {
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
            System.out.println(1234);
            return null;
        }
    }

    /**
     * 发送java对象
     */
    private <T> void sendObject(T t) throws IOException {
        // 使用 ObjectMapper 类将其转换成 JSON 格式的数据
        this.sendString(new ObjectMapper().writeValueAsString(t));
    }

    /**
     * 发送字符串
     */
    private void sendString(String judgment) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
        writer.write(judgment + "\n");
        writer.flush();
    }

    /**
     * 接收对象
     */
    private <T> T receiveObject(Class<? extends T> clazz) throws IOException {
        return new ObjectMapper().readValue(this.receiveString(), clazz);
    }

    /**
     * 接收字符串
     *
     * @return
     */
    private String receiveString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        return reader.readLine();
    }
}

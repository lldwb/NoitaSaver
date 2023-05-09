package top.lldwb.noitaSaverServer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.utils.User;
import top.lldwb.noitaSaverServer.dao.GetUser;
import top.lldwb.noitaSaverServer.utils.MailUtil;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketThread implements Runnable {

    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
        try {
            // 返回客户端地址并打印出来
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            // 接收客户端发出的类型识别字符串
            String types = this.receiveString();
            // 格式:“类型\n”
            switch (types) {
                case "登录":
                    User user = this.receiveObject(User.class);

                    // 通过 GetUser 类的 getUser 方法从数据库中获取与输入用户名相符的用户信息（User）
                    User userDao = GetUser.getUser(user.getUserName());

                    // 如果密码一致，向客户端发送 true，并将从数据库中获取到的用户对象也发送过去
                    if (user.getUserPassword().equals(userDao.getUserPassword())) {
                        this.sendObject(true);
                        this.sendObject(userDao);
                    }
                    // 如果密码不一致，向客户端发送 false
                    else {
                        this.sendObject(false);
                    }
                    break;
                case "云备份":
                    break;
                case "云恢复":
                    break;
                case "邮箱":
                    types = this.receiveString();
                    System.out.println(types);
                    MailUtil.sendSession(types, "验证码", "2345");
                    break;
                case "注册":
                    break;
                default:
                    break;
            }

        } catch (IOException | SQLException | NoSuchFieldException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
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
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }
}

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
    /**
     * 创建和服务器的通讯
     *
     * @return
     * @throws IOException
     */
    private Socket getSocket() throws IOException {
        // 创建一个流套接字并将其连接到指定主机上的指定端口号。
        // host:服务器地址 port:服务器端口
        Socket socket = new Socket("127.0.0.1", 8888);
        return socket;
    }

    public User getUser(User user){
        return null;
    }
}

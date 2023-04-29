package top.lldwb.noitaSaverClient.utils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Socket工具类
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtil {

    /**
     * 创建和服务器的通讯
     * @return 返回Socket对象
     * @throws IOException
     */
    public static Socket clientSocket() throws IOException {
        // 创建一个流套接字并将其连接到指定主机上的指定端口号。
        // host:服务器地址 port:服务器端口
        Socket socket = new Socket("127.0.0.1", 8888);


//        InputStream inputStream = socket.getInputStream();
//        OutputStream outputStream = socket.getOutputStream();
//
//        //向服务器发送数据
//        Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//        // 文件/文件长度/文件名
//        writer.write("邮箱\n");
//        // 刷新缓冲流，保证立刻发送
//        writer.flush();
//
//        // 文件/文件长度/文件名
//        writer.write("3247187440@qq.com\n");
//        // 刷新缓冲流，保证立刻发送
//        writer.flush();


        return socket;
    }
}

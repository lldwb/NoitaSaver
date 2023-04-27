package top.lldwb.noitaSaverClient.utils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtil {

    /**
     * 与服务端通信的Socket对象的方法，
     * 根据需求自定义修改
     */
    public static byte[] clientSocket(byte[] bytes) {
        byte[] inputBytes = new byte[0];
        try {
            // 创建一个流套接字并将其连接到指定主机上的指定端口号。
            // host:服务器地址 port:服务器端口
            Socket socket = new Socket("127.0.0.1", 8888);

            //向服务器发送数据
            // 创建一个缓冲流，用于向服务器发送数据
            // OutputStreamWriter是字符流到字节流的桥梁:写入其中的字符会使用指定的字符集编码成字节
            BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 文件/文件长度/文件名
            outputStream.write("文件/1024/java.zip\n");
            // 刷新缓冲流，保证立刻发送
            outputStream.flush();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputBytes;
    }
}

package top.lldwb.noitaSaverClient.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.ByteListImpl;
import sun.security.util.ByteArrays;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtils {

    /**
     * 与服务端通信的Socket对象的方法，
     * 根据需求自定义修改
     */
    private byte[] clientSocket(byte[] bytes) {
        byte[] inputBytes = new byte[0];
        try {
            // 创建一个流套接字并将其连接到指定主机上的指定端口号。
            // host:服务器地址 port:服务器端口
            Socket socket = new Socket("127.0.0.1", 8888);

            //向服务器发送数据
            // 输出流对象用来向服务器发送数据
            OutputStream outputStream = socket.getOutputStream();

            //向服务器端发送一条消息
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            outputStream.write(bytes);
            // 通过flush()方法刷新缓存，确保数据被立即发送
            outputStream.flush();

            //读取服务器返回的消息
            // 输入流对象用来读取服务器发来的数据
            InputStream inputStream = socket.getInputStream();

            byte[] dateByte = new byte[1024*1024];
            inputStream.read(dateByte);

            int length = 0;
            for (int i = 0; i < 10; i++) {
                length = dateByte[i] * (10 * (10 - i));
            }

            int date = 0;
            inputBytes = new byte[length];
            if ((date = inputStream.read(inputBytes)) != -1) {
            }

            // 关闭连接
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputBytes;
    }
}

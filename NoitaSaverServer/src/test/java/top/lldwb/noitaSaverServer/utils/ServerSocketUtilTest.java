package top.lldwb.noitaSaverServer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketUtilTest {
    @Test
    public void file() throws IOException {

        // 设置服务器监听端口
        ServerSocket s = new ServerSocket(8888);
        Socket socket = s.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        FileOutputStream fileOutputStream = new FileOutputStream("G:\\test\\1.zip");

        int length = new ObjectMapper().readValue(reader.readLine(), Integer.class);
        System.out.println(length);

        while (length != -1) {
            byte[] bytes = new byte[length > 1024 ? 1024 : length];
            length = length + inputStream.read(bytes);
            System.out.println(length);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        }

        System.out.println(reader.readLine());
    }
}

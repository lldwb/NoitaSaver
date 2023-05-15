package top.lldwb.noitaSaverClient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtilTest {
    @Test
    public void file() throws IOException {
        File file = new File("G:\\test\\123.zip");
        System.out.println(file.length());

        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
        writer.write(new ObjectMapper().writeValueAsString(file.length()) + "\n");
        System.out.println(new ObjectMapper().writeValueAsString(file.length()));
        writer.flush();

        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int length;
        if ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes,0,length);
            outputStream.flush();
        }

        writer.write(file.length() + "成功\n");
        writer.flush();
    }
}

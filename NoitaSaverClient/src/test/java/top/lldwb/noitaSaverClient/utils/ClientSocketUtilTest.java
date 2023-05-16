package top.lldwb.noitaSaverClient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ClientSocketUtilTest{
    @Test
    public void file() throws IOException {
        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        clientSocketUtil.backupFile();
//        File file = new File("G:\\test\\123.zip");
//        System.out.println(file.length());
//
//        Socket socket = new Socket("127.0.0.1", 8888);
//        OutputStream outputStream = socket.getOutputStream();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
//
//        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//        dataOutputStream.writeInt((int) file.length());
////        writer.write(new ObjectMapper().writeValueAsString(file.length()) + "\n");
////        System.out.println(new ObjectMapper().writeValueAsString(file.length()));
////        writer.flush();
//
//        InputStream inputStream = new FileInputStream(file);
//
//        byte[] bytes = new byte[1024];
//        int length;
//        while ((length = inputStream.read(bytes,0,bytes.length)) != -1) {
//            outputStream.write(bytes,0,length);
//            outputStream.flush();
//        }
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
//        System.out.println(reader.readLine());
//
//        writer.write(file.length() + "成功\n");
//        writer.flush();
    }
}

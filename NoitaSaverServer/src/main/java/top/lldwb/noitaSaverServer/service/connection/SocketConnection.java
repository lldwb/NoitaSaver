package top.lldwb.noitaSaverServer.service.connection;

import java.io.*;
import java.net.Socket;

/**
 * Socket连接接口
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public interface SocketConnection {
    /**
     * 连接处理
     */

    String connectionHandling(InputStream inputStream, OutputStream outputStream) throws IOException;

    /**
     *
     * @param reader
     * @return
     * @throws IOException
     */
    static SocketConnection getSocketConnection(BufferedReader reader) throws IOException {
        // 读取一行文本。一行被视为以换行符('\n')、换行符('\r')或换行符后紧跟着的换行符中的任何一个结束。
        String types = reader.readLine();
        System.out.println(types);

//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
//        writer.write("接收成功\n");
//        writer.flush();

//        reader.close();

        // 格式:“类型\n”
        switch (types) {
            case "登录":
                return null;
            case "云备份":
                return null;
            case "云恢复":
                return null;
            case "邮箱":
                return new MailSocketConnection();
            case "注册":
                return null;
            default:
                return null;
        }
    }
}

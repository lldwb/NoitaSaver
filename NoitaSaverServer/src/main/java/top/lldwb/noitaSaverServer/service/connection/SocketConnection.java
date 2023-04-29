package top.lldwb.noitaSaverServer.service.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    String connectionHandling(Socket socket) throws IOException;

    /**
     * 返回
     * @param socket
     * @return
     * @throws IOException
     */
    static SocketConnection getSocketConnection(Socket socket) throws IOException {
        // 创建一个缓冲流，用于读取信息
        // InputStreamReader是字节流和字符流之间的桥梁:它读取字节，并使用指定的字符集将其解码为字符
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 读取一行文本。一行被视为以换行符('\n')、换行符('\r')或换行符后紧跟着的换行符中的任何一个结束。
//        String types = reader.readLine();
//        System.out.println(types);
        // 读取一行文本。一行被视为以换行符('\n')、换行符('\r')或换行符后紧跟着的换行符中的任何一个结束。
        // 格式:“类型\n”
        switch (reader.readLine()) {
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

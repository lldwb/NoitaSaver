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
     * @param types
     * @return
     */
    static SocketConnection getSocketConnection(String types) {
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

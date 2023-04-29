package top.lldwb.noitaSaverServer.service.connection;

import top.lldwb.noitaSaverServer.utils.MailUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class MailSocketConnection implements SocketConnection{

    /**
     * 读取Socket对象，邮箱发送验证码
     * @param socket 连接对象
     * @return 成功信息
     * @throws IOException
     */
    @Override
    public String connectionHandling(Socket socket) throws IOException {

        // 创建一个缓冲流，用于读取信息
        // InputStreamReader是字节流和字符流之间的桥梁:它读取字节，并使用指定的字符集将其解码为字符
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        MailUtil.sendSession(reader.readLine(),"验证码","2345");
        return "成功";
    }
}

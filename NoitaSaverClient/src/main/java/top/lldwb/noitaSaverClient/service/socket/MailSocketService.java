package top.lldwb.noitaSaverClient.service.socket;

import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;

import java.io.*;
import java.net.Socket;

/**
 * 邮箱验证码工具类
 */
public class MailSocketService {
    /**
     * 向服务器发送验证码请求
     *
     * @param mail 需要接收的邮箱
     * @return 返回验证码
     * @throws IOException
     */
    public String mailVerificationCode(String mail) throws IOException {
        Socket socket = ClientSocketUtil.clientSocket();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(mail);
        bufferedWriter.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return bufferedReader.readLine();
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        try {
            // 服务器监听端口
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            // 监听来自客户端的连接请求，当有客户端请求连接时，该方法会阻塞直到连接被接受
            // 当服务器端接受一个客户端的连接请求，并创建一个用于与该客户端通信的Socket对象
            Socket s = ss.accept();
            // s.getInetAddress().getLocalHost()
            // 返回客户端地址并打印出来
            System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");

            // 创建一个缓冲输入流，用于读取来自客户端的数据，并将其转换为字符串
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // 读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端：" + mess);

            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write(mess + "\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

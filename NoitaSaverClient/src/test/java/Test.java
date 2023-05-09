import top.lldwb.noitaSaverClient.utils.ClientSocketUtil;
import top.lldwb.noitaSaverClient.utils.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        try {
            // 创建一个流套接字并将其连接到指定主机上的指定端口号。
            // host:服务器地址 port:服务器端口
            Socket s = new Socket("127.0.0.1", 8888);

            //构建IO
            // 输入流对象用来读取服务器发来的数据
            InputStream is = s.getInputStream();
            // 输出流对象用来向服务器发送数据
            OutputStream os = s.getOutputStream();

            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
            Writer bw = new BufferedWriter(new OutputStreamWriter(os));
//            Writer bw = new OutputStreamWriter(os);
            //向服务器端发送一条消息
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();

            //读取服务器返回的消息
            // 创建一个缓冲输入流，用于读取来自服务器的输入流(InputStream)中的数据，并将其转换为字符串
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // br.readLine读取一行数据
            String mess = br.readLine();

            System.out.println("服务器：" + mess);
            // 关闭连接
//            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void s() {
        try {
            // 创建一个流套接字并将其连接到指定主机上的指定端口号。
            // host:服务器地址 port:服务器端口
            Socket s = new Socket("127.0.0.1", 8888);

            //构建IO
            // 输入流对象用来读取服务器发来的数据
            InputStream inputStream = s.getInputStream();
            // 输出流对象用来向服务器发送数据
            OutputStream outputStream = s.getOutputStream();

            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
            Writer bw = new BufferedWriter(new OutputStreamWriter(outputStream));
//            Writer bw = new OutputStreamWriter(os);
            //向服务器端发送一条消息
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();

//            //读取服务器返回的消息
//            // 创建一个缓冲输入流，用于读取来自服务器的输入流(InputStream)中的数据，并将其转换为字符串
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            // br.readLine读取一行数据
//            String mess = br.readLine();
//            System.out.println("服务器：" + mess);

            //向服务器端发送一条消息
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write("测试客户端和服务器通信，366436\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();
            // 关闭连接
//            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void testByte() throws IOException, ClassNotFoundException {
        User user = new User();
        user.setUserName("1");
        user.setUserPassword("1");

        ClientSocketUtil clientSocketUtil = new ClientSocketUtil();
        System.out.println(user);
        clientSocketUtil.login(user);
    }
}

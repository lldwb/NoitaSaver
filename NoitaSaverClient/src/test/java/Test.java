import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        try {
            // 创建一个Socket对象用于与服务器进行通信
            // host:服务器地址 port:服务器端口
            Socket s = new Socket("127.0.0.1",8888);

            //构建IO
            // 输入流对象用来读取服务器发来的数据
            InputStream is = s.getInputStream();
            // 输出流对象用来向服务器发送数据
            OutputStream os = s.getOutputStream();

            Writer bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();

            System.out.println("服务器："+mess);
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

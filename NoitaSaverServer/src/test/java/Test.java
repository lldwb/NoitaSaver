import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;
import top.lldwb.noitaSaverServer.utils.ServerSocketUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        try {
            // 设置服务器监听端口
            ServerSocket ss = new ServerSocket(8888);

            System.out.println("启动服务器....");
            // 侦听并接受到此套接字的连接。
            // 监听来自客户端的连接请求，当有客户端请求连接时，该方法会阻塞直到连接被接受
            // 当服务器端接受一个客户端的连接请求，并创建一个用于与该客户端通信的Socket对象
            Socket socket = ss.accept();

            // s.getInetAddress().getLocalHost()
            // 返回客户端地址并打印出来
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");

            // 创建一个缓冲输入流，用于读取来自客户端的数据，并将其转换为字符串
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端：" + mess);

            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 在缓冲输出流BufferedWriter bw 中写入字符串
            bw.write(mess + "\n");
            // 通过flush()方法刷新缓存，确保数据被立即发送
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void s() {
        try {
            // 设置服务器监听端口
            ServerSocket ss = new ServerSocket(8888);

            System.out.println("启动服务器....");
            // 侦听并接受到此套接字的连接。
            // 监听来自客户端的连接请求，当有客户端请求连接时，该方法会阻塞直到连接被接受
            // 当服务器端接受一个客户端的连接请求，并创建一个用于与该客户端通信的Socket对象
            Socket socket = ss.accept();

            // s.getInetAddress().getLocalHost()
            // 返回客户端地址并打印出来
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");

            InputStream inputStream = socket.getInputStream();
            // 创建一个缓冲输入流，用于读取来自客户端的数据，并将其转换为字符串
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            // 读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端：" + mess);

//            // 创建一个Writer对象bw，这个对象用于向Socket的输出流os中写入字符数据
//            // 数据会被先写入到BufferedWriter中，再通过OutputStreamWriter将数据转化为字节流
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            // 在缓冲输出流BufferedWriter bw 中写入字符串
//            bw.write(mess + "\n");
//            // 通过flush()方法刷新缓存，确保数据被立即发送
//            bw.flush();

            // 创建一个缓冲输入流，用于读取来自客户端的数据，并将其转换为字符串
//            br = new BufferedReader(new InputStreamReader(inputStream));
            // 读取客户端发送来的消息
            mess = br.readLine();
            System.out.println("客户端：" + mess);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void ss() {
        ServerSocketUtil.getServerSocketUtils();
    }

    @org.junit.jupiter.api.Test
    public void sf() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        User user = new User();
        user.setUserName("1");
        user.setUserPassword("1");
        user.setUserMail("lldwb@lldwb.top");

//        System.out.println(new MySQLUtil().pdsT(new User(), "select * from user where user_name=?", user.getUserName()));

        // 根据user的名字返回User对象
        if (!user.getUserName().equals(UserDao.getUser(user.getUserName()).getUserName()) && UserDao.setUser(user.getUserName(), user.getUserPassword(), user.getUserMail()) != 0) {
            System.out.println("1234");
        }
//        User userDao = UserDao.getUser(user.getUserName());
//        System.out.println(userDao);
//        System.out.println(user.getUserName().equals(userDao.getUserName()) && user.getUserPassword().equals(userDao.getUserPassword()));
    }
}

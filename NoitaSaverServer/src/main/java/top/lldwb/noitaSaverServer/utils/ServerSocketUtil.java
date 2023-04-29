package top.lldwb.noitaSaverServer.utils;

import top.lldwb.noitaSaverServer.service.connection.MailSocketConnection;
import top.lldwb.noitaSaverServer.service.connection.SocketConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Socket服务端工具类,使用单例模式-懒汉式
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class ServerSocketUtil {
    /**
     * 存储客户端连接对象集合
     */
    private List<Socket> socketList = new ArrayList<>();

    /**
     * Socket服务端工具类对象
     */
    private static ServerSocketUtil serverSocketUtil;

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public static ServerSocketUtil getServerSocketUtils() {
        return getServerSocketUtils(8888);
    }

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public static ServerSocketUtil getServerSocketUtils(int port) {
        return getServerSocketUtils(port, 50);
    }

    /**
     * 获取Socket服务端工具类对象
     *
     * @return Socket服务端工具类对象
     */
    public synchronized static ServerSocketUtil getServerSocketUtils(int port, int backlog) {
        if (serverSocketUtil == null) {
            serverSocketUtil = new ServerSocketUtil(port, backlog);
        }
        return serverSocketUtil;
    }

    /**
     * 私有化构造器保证只有一个对象
     *
     * @param port    端口
     * @param backlog 连接数量
     */
    private ServerSocketUtil(int port, int backlog) {
        try {
            // 设置服务器配置:监听端口,连接次数
            ServerSocket serverSocket = new ServerSocket(port, backlog);
            System.out.println("启动服务器....");

            while (true) {
                // 侦听并接受到此套接字的连接。
                // 监听来自客户端的连接请求，当有客户端请求连接时，该方法会阻塞直到连接被接受
                // 当服务器端接受一个客户端的连接请求，并创建一个用于与该客户端通信的Socket对象
                Socket socket = serverSocket.accept();

                // 将Socket存入集合
//                socketList.add(socket);

                // 创建一个新的线程处理与该客户端的通信
                new Thread(() -> ServerSocket(socket)).start();

//                ServerSocket(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 与客户端通信的Socket对象的方法，
     * 根据需求自定义修改
     *
     * @param socket 与客户端通信的Socket对象
     */
    private void ServerSocket(Socket socket) {
        try {
            // 返回客户端地址并打印出来
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String types = reader.readLine();
            System.out.println(types);
            // 格式:“类型\n”
            switch (types) {
                case "登录":
                    break;
                case "云备份":
                    break;
                case "云恢复":
                    break;
                case "邮箱":
                    String reception = reader.readLine();
                    System.out.println(reception);
                    MailUtil.sendSession(reception,"验证码","2345");
                    break;
                case "注册":
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

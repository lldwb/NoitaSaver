package top.lldwb.noitaSaverServer.utils;

import top.lldwb.noitaSaverServer.service.ServerSocketThread;

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
        return getServerSocketUtils(58866);
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
                new Thread(new ServerSocketThread(socket)).start();


//                new ServerSocketThread(socket).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package top.lldwb.noitaSaver.SocketUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public abstract class SocketUtil {
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;

    public SocketUtil(Socket socket, InputStream inputStream, OutputStream outputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public SocketUtil(Socket socket) {
        try {
            this.socket = socket;
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        this.inputStream.close();
        this.outputStream.close();
        this.socket.close();
    }

    /**
     * 发送文件
     * @param file 需要发送的文件对象
     * @return 返回是否发送成功
     * @throws IOException
     */
    protected Boolean sendFile(File file) throws IOException {
        if (file == null || file.length() <= 0) {
            throw new IOException("文件为null");
        }

        sendObject((int)file.length());

        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes,0,bytes.length)) != -1) {
            outputStream.write(bytes,0,length);
        }
        outputStream.flush();

        if (receiveObject(Boolean.class)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 接收文件
     * @param path 文件保存路径(需要文件名)
     * @return 返回是否接收成功
     * @throws IOException
     */
    protected Boolean receiveFile(String path) throws IOException {
        if (path == null) {
            throw new IOException("地址为空");
        }

        int length;
        int fileLength = receiveObject(Integer.class);
        int fileLengthBoolean = fileLength;
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        while (fileLength > 0) {
            byte[] bytes = new byte[fileLength < 1024 ? fileLength : 1024];
            length = inputStream.read(bytes);
            fileLength -= length;
            fileOutputStream.write(bytes, 0, length);
            fileOutputStream.flush();
        }
        fileOutputStream.close();

        if (fileLengthBoolean==new File(path).length()){
            sendObject(true);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 发送java对象
     *
     * @param t   需要发送的Java对象
     * @param <T>
     * @throws IOException
     */
    protected <T> void sendObject(T t) throws IOException {
        // 使用 ObjectMapper 类将其转换成 JSON 格式的数据
        this.sendString(new ObjectMapper().writeValueAsString(t));
    }

    /**
     * 接收对象
     *
     * @param clazz 接收对象的类型
     * @param <T>
     * @return
     * @throws IOException
     */
    protected <T> T receiveObject(Class<? extends T> clazz) throws IOException {
        return new ObjectMapper().readValue(this.receiveString(), clazz);
    }

    /**
     * 发送字符串
     *
     * @param judgment 需要发送的字符串
     * @throws IOException
     */
    protected void sendString(String judgment) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
        writer.write(judgment + "\n");
        writer.flush();
    }

    /**
     * 接收字符串
     *
     * @return
     * @throws IOException
     */
    protected String receiveString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        return reader.readLine();
    }
}

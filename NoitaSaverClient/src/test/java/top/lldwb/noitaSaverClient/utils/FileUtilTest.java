package top.lldwb.noitaSaverClient.utils;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class FileUtilTest {
    @Test
    public void ioFolder() {
        try {
            // 输入文件操作对象
            File read = new File("F:\\原神启动器.zip");
            // 输出文件操作对象
            File write = new File("G:\\原神启动器.zip");

            // 输入文件流
            FileInputStream inputStream = new FileInputStream(read);
            // 输出文件流
            FileOutputStream outputStream = new FileOutputStream(write);
            // 返回指定的文件长度
//            byte[] bytes = new byte[(int) read.length()];

//            // 读取输入文件的数据到字节数组中
//            while (inputStream.read(bytes) != -1) {
//                // 将字节数组中的数据写入输出文件
//                for (byte by:bytes){
//                    System.out.print(by);
//                }
//                System.out.println("1234===================");
//            }

            Map<Integer, byte[]> map = new HashMap<>();
            List<Thread> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Thread thread = new Thread(() -> {
                    byte[] bytes = new byte[1024];
                    synchronized (inputStream) {
                        try {
                            while (inputStream.read(bytes) != -1) {
                                String str = new String();
                                // 将字节数组中的数据写入输出文件
                                for (byte by : bytes) {
                                    str = str + by;
                                }
                                map.put(map.size() + 1, bytes);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread.start();
                list.add(thread);
            }


            for (Thread thread : list) {
                thread.join();
            }

            list = new ArrayList<>();

            //关闭流对象
            inputStream.close();
            for (int i = 0; i < 3; i++) {
                Thread thread = new Thread(() -> {
                    for (byte[] bytes : map.values()) {
                        try {
                            System.out.println(map);
                            outputStream.write(bytes);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread.start();
                list.add(thread);
            }

            for (Thread thread : list) {
                thread.join();
            }

            //关闭流对象
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void zipFolder() throws IOException {// 定义源文件和目标文件的路径
        FileUtil.zipOutputFolder("G:\\test\\20","G:\\test\\123");
        FileUtil.zipInputFolder("G:\\test\\20test","G:\\test\\123");
    }
}

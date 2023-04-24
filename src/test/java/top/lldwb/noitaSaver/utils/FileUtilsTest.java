package top.lldwb.noitaSaver.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class FileUtilsTest {
    @Test
    public void ioFolder() {
//        Thread thread = new Thread(() ->
//        {
//            try {
//                FileUtils.fileIO("E:\\我的世界\\测压工具\\EndMinecraftPlus-master.zip", "G:\\2023-04-23_10-10-53\\EndMinecraftPlus-master.zip");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


        try {
            // 输入文件操作对象
            File read = new File("E:\\000\\521个黄油链接.xls");
            // 输入文件流
            FileInputStream inputStream = new FileInputStream(read);
            // 返回指定的文件长度
//            byte[] bytes = new byte[(int) read.length()];
            byte[] bytes = new byte[1024];
            Map<Integer, byte[]> map = new HashMap<>();

//            // 读取输入文件的数据到字节数组中
//            while (inputStream.read(bytes) != -1) {
//                // 将字节数组中的数据写入输出文件
//                for (byte by:bytes){
//                    System.out.print(by);
//                }
//                System.out.println("1234===================");
//            }


            List<Thread> list = new ArrayList<>();
            while (inputStream.read(bytes) != -1) {
                Thread thread = new Thread(() -> {
                    synchronized (inputStream) {
                        try {
                            if (inputStream.read(bytes) != -1) {
                                String str = new String();
                                // 将字节数组中的数据写入输出文件
                                for (byte by : bytes) {
                                    str = str + by;
                                }
                                System.out.println(str + "===================");
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

            //关闭流对象
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

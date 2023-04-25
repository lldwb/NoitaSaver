package top.lldwb.noitaSaver.utils;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class FileUtilsTest {
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
    public void s() {// 定义源文件和目标文件的路径

        System.out.println("main thread start...");  //主线程开始
        //定义源文件路径和目的路径
        String srcpath = "F:\\QQ文件\\068 fetch的应用.mp4";
        String destpath = "G:\\CNTV\\068 fetch的应用.mp4";

        ThreadCopyDemo.MutilThreadCopyFile(srcpath,destpath,4);

        System.out.println("main thread end..."); //主线程结束
    }
}

class ThreadCopyDemo {


    public static void MutilThreadCopyFile(String srcPath,String destPath,Integer ThreadNum){
        //参数校验
        if(ThreadNum < 1){
            return;
        }

        //文件划分
        File file = new File(srcPath);
        //获取文件长度
        long length = file.length();
        //计算子线程处理的长度
        long len = length/ThreadNum;

        // 创建并划分每个子线程所读的长度
        for (int i = 0; i < ThreadNum-1; i++) {
            SubThread subThread = new SubThread(srcPath, destPath, i * len, (i + 1) * len);
            System.out.println("第"+i+"个子线程启动");
            subThread.start();
            System.out.println("第"+i+"个子线程结束");
        }
        //把剩余的分给最后一个线程
        SubThread subThread = new SubThread(srcPath, destPath, ThreadNum * len, file.length());
        System.out.println("lastThread start...");
        subThread.start();
        System.out.println("lastThread end...");

    }
}
//自定义子线程类   继承Thread
class SubThread extends Thread{
    private String srcPath;
    private String destPath;
    private long startIndex;
    private long endIndex;
    //构造函数：
    public SubThread(String srcPath,String destPath,long startIndex,long endIndex){
        this.srcPath = srcPath;//源文件路径
        this.destPath = destPath;//目的路径
        this.startIndex = startIndex;//开始位置
        this.endIndex = endIndex;//结束位置
    }


    @Override
    public void run() {  //子线程操作
        //随机访问类读取源文件，通过seek（）方法将指针移到固定位置，将读取的内容写入目的文件
        try {
            RandomAccessFile srcFile = new RandomAccessFile(srcPath,"r");  //源文件，只读
            RandomAccessFile destFile = new RandomAccessFile(destPath, "rw"); //目的文件，读写

            //指针移到指定位置
            srcFile.seek(startIndex);
            destFile.seek(startIndex);

            long index = startIndex; //标志读取的起始位置

            byte[] bytes = new byte[1000]; //读取内容到数组

            int  n;
            while ((n = srcFile.read(bytes)) != -1){//读到文件结尾

                index+=n; //更新读取的位置

                destFile.write(bytes,0,n); //将读的数组写入目的路径

                if(index >= endIndex){  //读到当前线程的范围结尾处
                    break;
                }
            }
            srcFile.close();//关闭流
            destFile.close();

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}


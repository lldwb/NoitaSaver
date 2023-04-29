package top.lldwb.noitaSaverClient.utils;

import java.io.*;
import java.util.*;

/**
 * IO工具类，提供文件和文件夹的读写操作
 */
public class FileUtil {

    // 源文件夹地址
    public static String readPath;

    /**
     * 读取指定文件夹中的所有文件并写入目标文件夹
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     */
    public static void copyDirectory(String readPath, String writePath) {
        // 设置源文件夹地址
        FileUtil.readPath = readPath;
        // 获取源文件夹中所有文件的路径列表
        List<String> list = getFilePathList(readPath, writePath);

        List<Thread> threadList = new ArrayList<>();

        // 逐一将源文件夹中的文件写入目标文件夹
        for (final String path : list) {
            // 构造目标文件路径
            final String write = writePath + path.substring(readPath.length());

            // 输出目标文件路径
//            System.out.println(write);

            Thread thread = new Thread(() -> {
                try {
                    FileUtil.fileIO(path, write);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            // 使用多线程将源文件夹中的文件写入目标文件夹中
//            if (new File(path).length() >= 1024 * 1024) {
            threadList.add(thread);
//            }
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取指定文件夹中所有文件的路径列表
     * 并创建文件夹
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     * @return 指定文件夹中所有文件的路径列表
     */
    public static List getFilePathList(String readPath, String writePath) {
        // 在目标文件夹中创建子文件夹
        new File(writePath + readPath.substring(FileUtil.readPath.length())).mkdir();
        // 初始化路径列表
        List<String> list = new ArrayList();
        // 遍历源文件夹中的所有文件
        for (String file : new File(readPath).list()) {
            // 构造当前文件的路径
            String paths = readPath + '\\' + file;
            // 如果当前文件是文件夹，则递归获取该文件夹中的所有文件
            if (new File(paths).isDirectory()) {
                list.addAll(getFilePathList(paths, writePath));
            } else {
                // 如果当前文件是普通文件，则将其路径添加到路径列表中
                list.add(paths);
            }
        }
        // 返回路径列表
        return list;
    }

    /**
     * 获取指定文件夹中所有文件的路径列表
     * 并创建文件夹
     *
     * @param path 源文件夹地址
     * @return 返回Map<String, Boolean>,String 路径|Boolean 判断是否是文件(true 文件|false 文件夹)
     */
    public static Map<String, Boolean> getFileFolderPathList(String path) {
        // 初始化路径列表
        Map<String, Boolean> map = new HashMap<>();
        // 遍历源文件夹中的所有文件
        for (String file : new File(path).list()) {
            // 构造当前文件的路径
            String paths = path + '\\' + file;
            // 如果当前文件是文件夹，则递归获取该文件夹中的所有文件
            // 并将其文件夹路径添加到路径列表中
            if (new File(paths).isDirectory()) {
                map.put(paths, false);
                map.putAll(getFileFolderPathList(paths));
            } else {
                // 如果当前文件是普通文件，则将其路径添加到路径列表中
                map.put(paths, true);
            }
        }
        // 返回路径列表
        return map;
    }

    /**
     * 读取指定文件夹下的文件夹名称列表
     *
     * @param path 源文件夹地址
     * @return 返回文件夹名称列表
     */
    public static List getFolderNameList(String path) {
        // 初始化路径列表
        List<String> list = new ArrayList();
        // 遍历源文件夹中的所有文件
        for (String file : new File(path).list()) {
            // 构造当前文件的路径
            String paths = path + '\\' + file;
            // 判断是否是文件夹
            if (new File(paths).isDirectory()) {
                list.add(file);
//                System.out.println(file);
            }
        }
        // 返回路径列表
        return list;
    }

    /**
     * 将指定文件从源地址复制到目标地址
     *
     * @param readPath  源地址
     * @param writePath 目标地址
     * @throws IOException
     */
    public static void fileIO(String readPath, String writePath) throws IOException {
        // 输入文件操作对象
        File read = new File(readPath);
        // 输出文件操作对象
        File write = new File(writePath);

        // 输入文件流
        InputStream inputStream = new FileInputStream(read);
        // 输出文件流
        OutputStream outputStream = new FileOutputStream(write);

        // 返回指定的文件长度
        byte[] bytes = new byte[(int) read.length()];
        // 读取输入文件的数据到字节数组中
        if (inputStream.read(bytes) != -1) {
            // 将字节数组中的数据写入输出文件
            outputStream.write(bytes);
        }

        //关闭流对象
        inputStream.close();
        outputStream.close();
    }

    /**
     * 删除文件夹和其中文件
     *
     * @param path 需要删除的路径
     */
    public static void deleteFileFolder(String path) {
        // 获取文件和文件夹的路径集合
        // Key:文件(文件夹)路径 Value:判断是否是文件(true 文件|false 文件夹)
        Map<String, Boolean> map = getFileFolderPathList(path);
        // 文件夹路径集合
        List<String> folderPath = new ArrayList<>();
        // 遍历文件和文件夹的路径集合，先删除文件并将文件夹路径存入文件夹路径集合
        for (String paths : map.keySet()) {
            if (map.get(paths)) {
                new File(paths).delete();
            } else {
                folderPath.add(paths);
            }
        }

        // 创建一个定制排序的规则，保证文件夹是降序排序
        // 越接近删除的路径越后面，保证可以完整删除全部文件
        Comparator<String> comparator = (o1, o2) -> o2.length() - o1.length();
        folderPath.sort(comparator);
        Collections.sort(folderPath, comparator);
        // 遍历文件夹集合来删除
        for (String paths : folderPath) {
            new File(paths).delete();
        }

        // 最后删除目标路径的根目录
        new File(path).delete();
    }

    // 设置缓存大小为10MB
    private static final int BUFFER_SIZE = 1024 * 1024 * 10;

    /**
     * 本地多线程IO复制大文件
     *
     * @param readPath  源文件路径
     * @param writePath 目标文件路径
     * @throws IOException
     */
    public static void largeFileIO(String readPath, String writePath) {
    }
}
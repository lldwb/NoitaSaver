package top.lldwb.noitaSaver.fileUtil;

import top.lldwb.noitaSaver.fileUtil.entity.Folder;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class FileUtil {
    // 源文件夹地址
    public static String readPath;

    /**
     * 将指定一个文件夹从源地址复制到目标地址
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     */
    public static void copyDirectory(String readPath, String writePath) {
        // 设置源文件夹地址
        FileUtil.readPath = readPath;
        // 获取源文件夹中所有文件的路径列表
        List<String> list = getFileAddFolderPathList(readPath, writePath);

        List<Thread> threadList = new ArrayList<>();

        // 逐一将源文件夹中的文件写入目标文件夹
        for (final String path : list) {
            // 构造目标文件路径
            final String write = writePath + path.substring(readPath.length());

            // 输出目标文件路径
//            System.out.println(write);

            Thread thread = new Thread(() -> {
                try {
                    fileIO(path, write);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            // 使用多线程将源文件夹中的文件写入目标文件夹中
            threadList.add(thread);
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
     * 将指定一个文件从源地址复制到目标地址
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
     * 序列化，序列化前记得实现 Serializable 接口
     * 如果没有父文件夹会自动创建
     *
     * @param t
     * @param <T>
     */
    public static <T> void serialization(T t, String path) throws IOException {
        // path 的父目录 File 对象
        File file = new File(path.replaceAll("\\\\[^\\\\]*$",""));
        // 文件或目录不存在时执行
        if (!file.isDirectory()) {
            // 创建一个新目录，包括必要的父目录
            file.mkdirs();
        }else{
            new File(path).delete();
        }

        OutputStream objectOutput = new BufferedOutputStream(new FileOutputStream(path));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(objectOutput);
        objectOutputStream.writeObject(t);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * 反序列化
     *
     * @param <T>
     * @return
     */
    public static <T> T deSerialization(String path) throws IOException, ClassNotFoundException {
        InputStream objectInput = new BufferedInputStream(new FileInputStream(path));
        ObjectInputStream objectInputStream = new ObjectInputStream(objectInput);
        T t = (T) objectInputStream.readObject();
        objectInputStream.close();
        return t;
    }

    /**
     * 获取指定文件夹中所有文件的路径列表
     * 并创建文件夹
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     * @return 指定文件夹中所有文件的路径列表
     */
    public static List getFileAddFolderPathList(String readPath, String writePath) {
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
                list.addAll(getFileAddFolderPathList(paths, writePath));
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
     *
     * @param path 源文件夹地址
     * @return 返回Map<String, Boolean>,String 路径|Boolean 判断是否是文件(true 文件|false 文件夹)
     */
    public static Map<String, Boolean> getFileFolderPathMap(String path) {
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
                map.putAll(getFileFolderPathMap(paths));
            } else {
                // 如果当前文件是普通文件，则将其路径添加到路径列表中
                map.put(paths, true);
            }
        }
        // 返回路径列表
        return map;
    }

    /**
     * 压缩文件夹
     *
     * @param folderPath 源路径
     * @param zipPath    压缩包存放路径/压缩包名称(不需要".zip"后缀)
     * @throws IOException
     */
    public static void zipOutputFolder(String folderPath, String zipPath) throws IOException {
        // 创建压缩流，传入文件输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath + ".zip"));
        // 获取指定文件夹中所有文件的路径列表
        Map<String, Boolean> map = getFileFolderPathMap(folderPath);

        // 遍历路径列表
        for (String paths : map.keySet()) {
            // 判断是否是文件
            if (map.get(paths)) {
                // 在zip流中创建一个ZIP条目，接受一个 ZipEntry 对象作为参数
                // 获取相对于 readPath 的相对路径，作为新的 ZipEntry 的名称
                zipOutputStream.putNextEntry(new ZipEntry(paths.replace(folderPath + "\\", "")));
                // 创建一个文件输入流，用于写入压缩流
                FileInputStream inputStream = new FileInputStream(paths);
                // 创建一个长度为文件长度的字节数组
                byte[] bytes = new byte[(int) new File(paths).length()];
                // 输入流中读取数据到字节数组
                if (inputStream.read(bytes) != -1) {
                    // 将文件数据写入压缩流
                    zipOutputStream.write(bytes);
                }
                inputStream.close();
            }
        }

        zipOutputStream.close();
    }

    /**
     * 解压文件夹
     *
     * @param folderPath 解压的目标路径(不需要"\\"后缀)
     * @param zipPath    压缩包存放路径/压缩包名称(不需要".zip"后缀)
     */
    public static void zipInputFolder(String folderPath, String zipPath) throws IOException {
        // 添加文件夹路径和ZIP文件扩展名
        folderPath = folderPath + "\\";
        zipPath = zipPath + ".zip";

        // 创建ZIP输入流
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
        ZipEntry zipEntry;

        // 逐个解压ZIP条目
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            // 构造ZIP条目对应的文件对象
            File file = new File(folderPath + zipEntry.getName());
            if (!file.getParentFile().exists()) {
                // 判断解压后文件的父目录是否存在，如果不存在，则使用 mkdirs() 方法创建该目录。这样确保解压后的文件可以被正确地存储在对应的目录中。
                file.getParentFile().mkdirs();
            }

            // 创建输出流和输入流
            OutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = new ZipFile(zipPath).getInputStream(zipEntry);

            // 逐个读取字节并写入文件
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
            // 逐个读取字节并写入文件
            outputStream.close();
            inputStream.close();
        }
        // 关闭ZIP输入流
        zipInputStream.close();
    }

    /**
     * 读取指定文件夹下的文件夹名称列表
     *
     * @param path 源文件夹地址
     * @return 返回文件夹名称列表
     */
    public static List getFolderNameList(String path) {
        // 初始化路径列表
        List<Folder> list = new ArrayList();
        // 遍历源文件夹中的所有文件
        for (String file : new File(path).list()) {
            // 构造当前文件的路径
            String paths = path + '\\' + file;
            // 判断是否是文件夹
            if (new File(paths).isDirectory()) {
                list.add(new Folder(file));
//                System.out.println(file);
            }
        }
        // 返回路径列表
        return list;
    }


    /**
     * 删除文件夹和其中文件
     *
     * @param path 需要删除的路径
     */
    public static void deleteFileFolder(String path) {
        // 获取文件和文件夹的路径集合
        // Key:文件(文件夹)路径 Value:判断是否是文件(true 文件|false 文件夹)
        Map<String, Boolean> map = getFileFolderPathMap(path);
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
}

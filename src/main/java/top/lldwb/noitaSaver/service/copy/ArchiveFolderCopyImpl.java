package top.lldwb.noitaSaver.service.copy;

import top.lldwb.noitaSaver.utils.FileUtils;
import top.lldwb.noitaSaver.utils.TimeUtils;

import java.io.File;

/**
 * 存档类，实现FolderCopy接口，用于将文件夹存档
 */
public class ArchiveFolderCopyImpl implements FolderCopy {

    /**
     * 将指定的文件夹存档
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标地址
     */
    @Override
    public void copy(String readPath, String writePath) {
        String localDateTime = TimeUtils.currentTime();
        // 在目标文件夹中创建子文件夹
//        new File(writePath + localDateTime).mkdir();
        FileUtils.copyDirectory(readPath, writePath + "\\" + localDateTime);
        System.out.println("存档成功！");
    }
}

package service.copy;

import top.lldwb.noitaSaver.utils.FileUtils;

import java.io.File;
import java.time.LocalDateTime;

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
        String localDateTime= LocalDateTime.now().toString();
        // 在目标文件夹中创建子文件夹
        new File(readPath+localDateTime).mkdir();
        FileUtils.copyDirectory(readPath, writePath + localDateTime + "\\save00");
        System.out.println("存档成功！");
    }
}

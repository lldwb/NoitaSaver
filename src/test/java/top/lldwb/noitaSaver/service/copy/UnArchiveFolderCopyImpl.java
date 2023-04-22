package top.lldwb.noitaSaver.service.copy;

import top.lldwb.noitaSaver.utils.FileUtils;

/**
 * 读档类，实现FolderCopy接口，用于将文件夹读档
 */
public class UnArchiveFolderCopyImpl implements FolderCopy {

    /**
     * 将指定的存档文件夹读档
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     */
    @Override
    public void copy(String readPath, String writePath) {
        FileUtils.copyDirectory(readPath, writePath);
        System.out.println("读档成功！");
    }
}

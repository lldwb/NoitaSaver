package top.lldwb.noitaSaver.service;

import top.lldwb.noitaSaver.service.copy.FolderCopy;
import top.lldwb.noitaSaver.utils.FileUtils;

/**
 * 删除的业务类
 */
public class DeleteFileFolderService {
    /**
     * 调用utils的删除方法
     * @param path 删除路径
     */
    public static void deleteFileFolder(String path){
        FileUtils.deleteFileFolder(path);
    }
}

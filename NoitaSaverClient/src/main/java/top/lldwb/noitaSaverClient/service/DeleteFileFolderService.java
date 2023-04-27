package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaverClient.utils.FileUtil;

/**
 * 删除的业务类
 */
public class DeleteFileFolderService {
    /**
     * 调用utils的删除方法
     * @param path 删除路径
     */
    public static void deleteFileFolder(String path){
        FileUtil.deleteFileFolder(path);
    }
}

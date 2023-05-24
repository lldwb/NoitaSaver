package top.lldwb.noitaSaverServer.service;

import top.lldwb.noitaSaver.fileUtil.FileUtil;
import top.lldwb.noitaSaver.fileUtil.entity.Folder;

import java.io.IOException;

/**
 * 用户备份路径业务类
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserBackupPathService {
    private final String PATH = "C:\\Users\\Public\\Documents\\NoitaSaver\\Server\\UserBackupPath.lldwb";

    /**
     * 修改用户备份路径
     */
    public void setUserBackupPath(Folder folder) throws IOException {
        FileUtil.serialization(folder,PATH);
    }

    /**
     * 读取用户备份路径
     */
    public Folder getUserBackupPath() throws IOException, ClassNotFoundException {
        return FileUtil.deSerialization(PATH);
    }
}

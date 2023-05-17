package top.lldwb.noitaSaverServer.service;

import top.lldwb.noitaSaver.fileUtil.FileUtil;
import top.lldwb.noitaSaver.fileUtil.entity.Folder;
import top.lldwb.noitaSaverClient.entity.DefaultPath;

import java.io.IOException;

/**
 * 用户备份路径业务类
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserBackupPathService {
    /**
     * 修改用户备份路径
     */
    public void setUserBackupPath(Folder folder) throws IOException {
        FileUtil.Serialization(folder,"C:\\Users\\Public\\Documents\\NoitaSaverClient\\UserBackupPath.lldwb");
    }

    /**
     * 读取用户备份路径
     */
    public Folder getUserBackupPath() throws IOException, ClassNotFoundException {
        return FileUtil.DeSerialization("C:\\Users\\Public\\Documents\\NoitaSaverClient\\UserBackupPath.lldwb");
    }
}

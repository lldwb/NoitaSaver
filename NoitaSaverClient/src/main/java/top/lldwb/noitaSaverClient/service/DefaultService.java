package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaver.fileUtil.FileUtil;
import top.lldwb.noitaSaverClient.entity.DefaultPath;

import java.io.File;
import java.io.IOException;

/**
 * 默认地址业务类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class DefaultService {
    /**
     * 修改默认地址
     */
    public void setDefaultPath(DefaultPath defaultPath) throws IOException {
        FileUtil.Serialization(defaultPath,"C:\\Users\\Public\\Documents\\NoitaSaverClient\\DefaultPath.lldwb");
    }

    /**
     * 读取默认地址
     */
    public DefaultPath getDefaultPath() throws IOException, ClassNotFoundException {
        return FileUtil.DeSerialization("C:\\Users\\Public\\Documents\\NoitaSaverClient\\DefaultPath.lldwb");
    }
}

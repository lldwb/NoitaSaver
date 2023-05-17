package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaver.fileUtil.FileUtil;
import top.lldwb.noitaSaverClient.entity.DefaultPath;

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
        FileUtil.serialization(defaultPath,"C:\\Users\\Public\\Documents\\NoitaSave\\rClient\\DefaultPath.lldwb");
    }

    /**
     * 读取默认地址
     */
    public DefaultPath getDefaultPath() throws IOException, ClassNotFoundException {
        return FileUtil.deSerialization("C:\\Users\\Public\\Documents\\NoitaSaver\\Client\\DefaultPath.lldwb");
    }
}

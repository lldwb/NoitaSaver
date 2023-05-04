package top.lldwb.noitaSaverClient.service;

import top.lldwb.noitaSaverClient.entity.DefaultPath;
import top.lldwb.noitaSaverClient.utils.FileUtil;

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
        FileUtil.setDefaultPath(defaultPath);
    }

    /**
     * 读取默认地址
     */
    public DefaultPath getDefaultPath() throws IOException, ClassNotFoundException {
        return FileUtil.getDefaultPath();
    }
}

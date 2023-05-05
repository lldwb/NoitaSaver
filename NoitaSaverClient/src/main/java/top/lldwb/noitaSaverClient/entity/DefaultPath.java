package top.lldwb.noitaSaverClient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 默认设置类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class DefaultPath implements Serializable {
    /**
     * 源地址
     */
    public String readPath;

    /**
     * 存档地址
     */
    public String writePath;
}

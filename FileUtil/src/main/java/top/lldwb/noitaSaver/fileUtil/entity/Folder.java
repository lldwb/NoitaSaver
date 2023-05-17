package top.lldwb.noitaSaver.fileUtil.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件夹实体类类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Folder implements Serializable {
    public String name;
}

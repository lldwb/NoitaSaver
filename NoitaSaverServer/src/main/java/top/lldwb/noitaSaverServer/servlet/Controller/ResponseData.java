package top.lldwb.noitaSaverServer.servlet.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应对象 - 封装相关的响应信息 - 实现前端与后端的交互（约定）
 *
 * @Date 2023-05-04
 * @Author zqx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;
}

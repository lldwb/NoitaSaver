package top.lldwb.noitaSaverServer.action.backupPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.service.UserBackupPathService;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回用户备份路径
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebController("/GetUserBackupPath")
public class GetUserBackupPath extends BaseController {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           resp.getWriter().print(new ObjectMapper().writeValueAsString(new UserBackupPathService().getUserBackupPath()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

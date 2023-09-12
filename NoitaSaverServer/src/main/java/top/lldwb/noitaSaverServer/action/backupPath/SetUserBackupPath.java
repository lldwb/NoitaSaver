package top.lldwb.noitaSaverServer.action.backupPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.fileUtil.entity.Folder;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.service.UserBackupPathService;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改用户备份路径，不需要添加"\\"为后缀
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebController("/SetUserBackupPath")
public class SetUserBackupPath extends BaseController {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        new UserBackupPathService().setUserBackupPath(new Folder(req.getParameter("path") + "\\"));

        try {
            resp.getWriter().print(new ObjectMapper().writeValueAsString(new UserBackupPathService().getUserBackupPath()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

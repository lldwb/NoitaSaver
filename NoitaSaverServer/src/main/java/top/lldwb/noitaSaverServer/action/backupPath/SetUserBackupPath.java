package top.lldwb.noitaSaverServer.action.backupPath;

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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new UserBackupPathService().setUserBackupPath(new Folder(request.getParameter("path") + "\\"));
    }
}

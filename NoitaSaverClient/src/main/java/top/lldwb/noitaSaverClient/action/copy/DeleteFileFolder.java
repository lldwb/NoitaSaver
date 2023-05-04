package top.lldwb.noitaSaverClient.action.copy;

import top.lldwb.noitaSaverClient.service.DeleteFileFolderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 删除API
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/deleteFileFolder")
public class DeleteFileFolder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 删除路径
        String path = req.getParameter("path");
        DeleteFileFolderService.deleteFileFolder(path);

        // 判断是否删除所有存档，如果是删除所有存档后创建一个新的文件夹
        if (req.getParameter("boolean")!=null){
            new File(path).mkdir();
        }
    }
}

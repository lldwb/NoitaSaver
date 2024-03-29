package top.lldwb.noitaSaverClient.action.copy;

import top.lldwb.noitaSaverClient.service.copy.Copy;
import top.lldwb.noitaSaverClient.service.copy.FolderCopy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 存档API
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/archiveFolderCopy")
public class ArchiveFolderCopy extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 源文件夹地址
        String writePath = req.getParameter("writePath");
        // 目标文件夹地址
        String readPath = req.getParameter("readPath");

        FolderCopy folderCopy = FolderCopy.getFolderCopyFactory(Copy.Archive);
        folderCopy.copy(writePath, readPath);
    }
}

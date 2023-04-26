package top.lldwb.noitaSaver.action.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaver.utils.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/getFolderNameList")
public class GetFolderNameList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        ObjectMapper om = new ObjectMapper();

        String json = om.writeValueAsString(FileUtils.getFolderNameList(req.getParameter("path")));
        resp.getWriter().print(json);
    }
}

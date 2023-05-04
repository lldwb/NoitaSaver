package top.lldwb.noitaSaverClient.action.defaultPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.service.DefaultService;
import top.lldwb.noitaSaverClient.utils.FileUtil;

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
@WebServlet("/getDefaultPath")
public class GetDefaultPath extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        ObjectMapper om = new ObjectMapper();

        DefaultService defaultService = new DefaultService();

        String json = null;
        try {
            json = om.writeValueAsString(defaultService.getDefaultPath());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().print(json);
    }
}

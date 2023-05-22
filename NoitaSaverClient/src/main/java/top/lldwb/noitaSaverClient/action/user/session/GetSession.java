package top.lldwb.noitaSaverClient.action.user.session;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回Session信息
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebServlet("/getSession")
public class GetSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Object object = req.getSession().getAttribute(req.getParameter("key"));

//        if (object == null) {
//            // 请求的资源不存在时，返回404状态码
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
        resp.getWriter().println(new ObjectMapper().writeValueAsString(object));
    }
}

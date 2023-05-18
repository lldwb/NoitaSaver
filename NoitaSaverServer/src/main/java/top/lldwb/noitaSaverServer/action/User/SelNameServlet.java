package top.lldwb.noitaSaverServer.action.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebController("/selNameServlet")
public class SelNameServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        UserDao userDao = new UserDao();
        try {
            User user = userDao.getUserByName(name);
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(user);
            response.getWriter().print(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

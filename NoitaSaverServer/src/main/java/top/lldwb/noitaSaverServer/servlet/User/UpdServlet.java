package top.lldwb.noitaSaverServer.servlet.User;

import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdServlet")
public class UpdServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");

        UserDao userDao = new UserDao();
        try {
            int i = userDao.updUser(name,password,mail);
            response.getWriter().print(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package top.lldwb.noitaSaverServer.servlet.User;

import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据id删除对应的用户
 */

@WebServlet("/DelServlet")
public class DelServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_userId = request.getParameter("userId");
        int userId = Integer.parseInt(str_userId);

        UserDao userDao = new UserDao();
        try {
            int i = userDao.delUser(userId);
            response.getWriter().print(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

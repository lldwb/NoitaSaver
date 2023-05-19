package top.lldwb.noitaSaverServer.action.user;

import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebController("/updServlet")
public class UpdServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");

        UserDao userDao = new UserDao();
        try {
            int row = userDao.updateUserByName(name,password,mail);
            // 打印响应的数据
            if (row == 1) {
                print(response, successJson(200, "修改成功", ""));
                return;
            }
            print(response, errorJson(500, "修改失败", ""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

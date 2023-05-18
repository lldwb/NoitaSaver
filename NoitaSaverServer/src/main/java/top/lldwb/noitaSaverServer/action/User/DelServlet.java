package top.lldwb.noitaSaverServer.action.User;

import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据id删除对应的用户
 */

@WebController("/delServlet")
public class DelServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str_userId = request.getParameter("userId");
        int userId = Integer.parseInt(str_userId);

        UserDao userDao = new UserDao();
        try {
            int row = userDao.deleteUserId(userId);
            // 打印响应的数据
            if (row == 1) {
                print(response, successJson(200, "删除成功", ""));
                return;
            }
            print(response, errorJson(500, "删除失败", ""));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

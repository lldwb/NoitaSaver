package top.lldwb.noitaSaverServer.action.user;

import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.dao.UserDaoImpl;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebController("/updServlet")
public class UpdServlet extends BaseController {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");

        UserDao dao = new UserDaoImpl();
        int row = dao.updateUserByName(name, password, mail);
        // 打印响应的数据
        if (row == 1) {
            print(resp, successJson(200, "修改成功", ""));
            return;
        }
        print(resp, errorJson(500, "修改失败", ""));
    }
}

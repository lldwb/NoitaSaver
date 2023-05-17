package top.lldwb.noitaSaverServer.servlet.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 查询全部用户
 */

@WebServlet( "/SelServlet")
public class SelServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        try {
            User user = userDao.selUser();
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(user);
            System.out.println(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

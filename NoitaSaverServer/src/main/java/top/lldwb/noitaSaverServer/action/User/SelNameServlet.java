package top.lldwb.noitaSaverServer.action.User;

import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.servlet.ResponseData;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查询用户
 * 支持全部查询 和 根据用户名称查询
 */
@WebController("/selNameServlet")
public class SelNameServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        try {
<<<<<<< Updated upstream
            User user = userDao.getUserByName(name);
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(user);
            response.getWriter().print(s);
=======
            ResponseData responseData = successJson(new UserDao().getUserList(name));
            print(response,responseData);
>>>>>>> Stashed changes
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

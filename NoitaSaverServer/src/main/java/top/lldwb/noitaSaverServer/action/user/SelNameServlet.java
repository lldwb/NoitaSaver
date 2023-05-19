package top.lldwb.noitaSaverServer.action.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.lldwb.noitaSaverServer.action.BaseController;
import top.lldwb.noitaSaverServer.service.UserService;
import top.lldwb.noitaSaverServer.servlet.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 查询用户
 * 支持全部查询 和 根据用户名称查询
 */
@WebController("/selNameServlet")
public class SelNameServlet extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            response.getWriter().print(new ObjectMapper().writeValueAsString(new UserService().getUserList(name)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

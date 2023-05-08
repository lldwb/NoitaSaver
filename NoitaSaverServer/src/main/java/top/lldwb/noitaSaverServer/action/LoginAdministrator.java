package top.lldwb.noitaSaverServer.action;

import top.lldwb.noitaSaverServer.servlet.Controller.WebController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
@WebController("/loginAdministrator")
public class LoginAdministrator extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("12345");
    }
}

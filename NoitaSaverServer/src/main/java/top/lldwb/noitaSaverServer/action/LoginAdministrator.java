package top.lldwb.noitaSaverServer.action;

import top.lldwb.noitaSaverServer.servlet.WebController;
import top.lldwb.noitaSaverServer.utils.ServerSocketUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 启动服务器
 *
 * @author 安然的尾巴
 * @version 1.0
 */
@WebController("/openServer")
public class LoginAdministrator extends BaseController {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServerSocketUtil.getServerSocketUtils();
        response.getWriter().print("启动服务器成功");
    }
}

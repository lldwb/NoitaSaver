//package top.lldwb.noitaSaverServer.servlet.User;
//
//import top.lldwb.noitaSaverServer.action.BaseController;
//import top.lldwb.noitaSaverServer.dao.UserDao;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//
///**
// * 添加用户
// * 写了注册此方法可不用，通过注册进行添加数据
// */
//
//@WebServlet("/InsServlet")
//public class InsServlet extends BaseController {
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        String mail = request.getParameter("mail");
//
//        UserDao userDao = new UserDao();
//        try {
//            int i = userDao.setUser(name,password,mail);
//            response.getWriter().print(i);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

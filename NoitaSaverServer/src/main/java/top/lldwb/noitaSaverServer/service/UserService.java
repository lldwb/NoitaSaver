package top.lldwb.noitaSaverServer.service;

import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverServer.dao.UserDao;
import top.lldwb.noitaSaverServer.dao.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserService {
    public List<User> getUserList(String name) {
        UserDao dao = new UserDaoImpl();
        if (name!=null && !"".equals(name.trim())){
            return dao.getUserListLikeName("%"+name+"%");
        }else {
            return dao.getUserList();
        }
    }
}

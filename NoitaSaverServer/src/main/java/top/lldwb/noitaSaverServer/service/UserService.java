package top.lldwb.noitaSaverServer.service;

import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverServer.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserService {
    public List<User> getUserList(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        if (name!=null && !"".equals(name.trim())){
            return UserDao.getUserListLikeName("%"+name+"%");
        }else {
            return UserDao.getUserList();
        }
    }
}

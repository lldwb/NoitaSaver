package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverServer.entity.User;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;

/**
 * Dao类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class Dao {
    public Boolean login(User user) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        MySQLUtil mySQLUtil = new MySQLUtil();
        return mySQLUtil.pdsT(new User(), "select user_password from user where user_name=?", user.getUserName()).getUserPassword().equals(user.getUserPassword());
    }
}

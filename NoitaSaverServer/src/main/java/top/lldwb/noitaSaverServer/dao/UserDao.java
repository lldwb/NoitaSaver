package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;

/**
 * 有关用户的数据库操作类
 *
 * @author 安然的尾巴
 * @version 1.0
 */
public class UserDao {
    /**
     * 根据名字在数据库中获取 User 对象
     *
     * @param name 用户名
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User getUserNameUser(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(), "select * from user where user_name=?", name);
    }

    /**
     * 根据key在数据库中获取 UserId
     *
     * @param key 远程秘钥
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User getUserKeyUser(String key) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(), "select * from user where user_key=?", key);
    }

    /**
     * 根据 mail 在数据库中获取 User
     *
     * @param mail 用户邮箱
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User getUserMailUser(String mail) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(), "select * from user where user_mail=?", mail);
    }

    /**
     * 在数据库中添加用户
     *
     * @param name     用户名
     * @param password 密码
     * @param mail     邮箱
     * @return
     * @throws SQLException
     */
    public static int setUser(String name, String password, String mail, String key) throws SQLException {
        return new MySQLUtil().update("insert into user(user_name,user_password,user_mail,user_key) values(?,?,?,?)", name, password, mail, key);
    }
}

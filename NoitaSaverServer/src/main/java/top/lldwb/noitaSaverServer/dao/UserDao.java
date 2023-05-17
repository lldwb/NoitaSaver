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

    /**
     *  根据 id 删除用户
     * @param id 编号
     * @return
     * @throws SQLException
     */
    public static int delUser(int id) throws SQLException {
        return new MySQLUtil().update("delete from user where user_id=?", id);
    }

    /**
     * 根据用户名 修改用户的密码以及邮箱
     *
     * @param name 用户名
     * @param password 密码
     * @param mail 邮箱
     * @return
     * @throws SQLException
     */
    public static int updUser(String name,String password, String mail) throws SQLException {
        return new MySQLUtil().update("update user set user_password=?,user_mail=? where user_name=?", password, mail,name);
    }

    /**
     * 查询全部数据
     *
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User selUser() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(),"select * from user");
    }

    /**
     *  根据 id 删除用户
     * @param id 编号
     * @return
     * @throws SQLException
     */
    public static int delUser(int id) throws SQLException {
        return new MySQLUtil().update("delete from user where user_id=?", id);
    }

    /**
     * 根据用户名 修改用户的密码以及邮箱
     *
     * @param name 用户名
     * @param password 密码
     * @param mail 邮箱
     * @return
     * @throws SQLException
     */
    public static int updUser(String name,String password, String mail) throws SQLException {
        return new MySQLUtil().update("update user set user_password=?,user_mail=? where user_name=?", password, mail,name);
    }

    /**
     * 查询全部数据
     *
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User selUser() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(),"select * from user");
    }

    /**
     *  根据 id 删除用户
     * @param id 编号
     * @return
     * @throws SQLException
     */
    public static int delUser(int id) throws SQLException {
        return new MySQLUtil().update("delete from user where user_id=?", id);
    }

    /**
     * 根据用户名 修改用户的密码以及邮箱
     *
     * @param name 用户名
     * @param password 密码
     * @param mail 邮箱
     * @return
     * @throws SQLException
     */
    public static int updUser(String name,String password, String mail) throws SQLException {
        return new MySQLUtil().update("update user set user_password=?,user_mail=? where user_name=?", password, mail,name);
    }

    /**
     * 查询全部数据
     *
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static User selUser() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsT(new User(),"select * from user");
    }
}

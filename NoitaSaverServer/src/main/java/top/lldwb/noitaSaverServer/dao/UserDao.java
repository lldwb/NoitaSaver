package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaverClient.entity.User;
import top.lldwb.noitaSaverServer.utils.MySQLUtil;

import java.sql.SQLException;
import java.util.List;

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
    public static User getUserByName(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
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
    public static User getUserByKey(String key) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
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
    public static User getUserByMail(String mail) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
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

    /**
     * 根据 id 删除用户
     *
     * @param id 编号
     * @return
     * @throws SQLException
     */
    public static int deleteUserId(int id) throws SQLException {
        return new MySQLUtil().update("delete from user where user_id=?", id);
    }

    /**
     * 根据用户名 修改用户的密码以及邮箱
     *
     * @param name     用户名
     * @param password 密码
     * @param mail     邮箱
     * @return
     * @throws SQLException
     */
    public static int updateUserByName(String name, String password, String mail) throws SQLException {
        return new MySQLUtil().update("update user set user_password=?,user_mail=? where user_name=?", password, mail, name);
    }

    /**
     * 通过用户id修改用户状态信息
     *
     * @param id    用户id
     * @param state 最终的状态信息
     */
    public static int updateUserStatusById(int id, int state) throws SQLException {
        return new MySQLUtil().update("update user set user_state=? where user_id=?", state, id);
    }

    /**
     * 通过用户邮箱修改用户状态信息
     *
     * @param mail  用户邮箱
     * @param state 最终的状态信息
     */
    public static int updateUserStatusByMail(String mail, int state) throws SQLException {
        return new MySQLUtil().update("update user set user_state=? where user_mail=?", state, mail);
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
    public static List<User> getUserList() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsList(new User(), "select * from user");
    }

    /**
     * 根据用户名模糊查询
     *
     * @param name
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static List<User> getUserListLikeName(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return new MySQLUtil().pdsList(new User(), "select * from user where user_name like ?", name);
    }
}

package top.lldwb.noitaSaverServer.dao;

import top.lldwb.noitaSaver.Entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * 根据名字在数据库中获取 User 对象
     *
     * @param name 用户名
     * @return
     */
    User getUserByName(String name);

    /**
     * 根据key在数据库中获取 UserId
     *
     * @param key 远程秘钥
     * @return
     */
    User getUserByKey(String key);

    /**
     * 根据 mail 在数据库中获取 User
     *
     * @param mail 用户邮箱
     * @return
     */
    User getUserByMail(String mail);

    /**
     * 查询全部数据
     *
     * @return
     */
    List<User> getUserList();

    /**
     * 根据用户名模糊查询
     *
     * @param name
     * @return
     */
    List<User> getUserListLikeName(String name);

    /**
     * 在数据库中添加用户
     *
     * @param name     用户名
     * @param password 密码
     * @param mail     邮箱
     * @param key      秘钥
     * @return
     */
    int setUser(String name, String password, String mail, String key);

    /**
     * 根据用户名 修改用户的密码以及邮箱
     *
     * @param name     用户名
     * @param password 密码
     * @param mail     邮箱
     * @return
     * @throws SQLException
     */
    int updateUserByName(String name, String password, String mail);

    /**
     * 通过用户id修改用户状态信息
     *
     * @param id    用户id
     * @param state 最终的状态信息
     */
    int updateUserStatusById(int id, int state);

    /**
     * 通过用户邮箱修改用户状态信息
     *
     * @param mail  用户邮箱
     * @param state 最终的状态信息
     */
    int updateUserStatusByMail(String mail, int state);

    /**
     * 根据 id 删除用户
     *
     * @param id 编号
     * @return
     */
    int deleteUserId(int id);
}

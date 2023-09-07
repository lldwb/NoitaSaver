package top.lldwb.noitaSaverServer.dao;

import org.apache.ibatis.session.SqlSession;
import top.lldwb.noitaSaver.Entity.User;
import top.lldwb.noitaSaverServer.utils.MybatisUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByName(String name) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).getUserByName(name);
    }

    @Override
    public User getUserByKey(String key) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).getUserByKey(key);
    }

    @Override
    public User getUserByMail(String mail) {
        SqlSession session = MybatisUtils.getSqlSession();
        try {
            session.getMapper(UserDao.class).getUserByMail(mail);
            session.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).getUserList();
    }

    @Override
    public List<User> getUserListLikeName(String name) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).getUserListLikeName(name);
    }

    @Override
    public int setUser(String name, String password, String mail, String key) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).setUser(name,password,mail,key);
    }

    @Override
    public int updateUserByName(String name, String password, String mail) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return session.getMapper(UserDao.class).updateUserByName(name,password,mail);
    }

    @Override
    public int updateUserStatusById(int id, int state) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return 0;
    }

    @Override
    public int updateUserStatusByMail(String mail, int state) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return 0;
    }

    @Override
    public int deleteUserId(int id) {
        SqlSession session = MybatisUtils.getSqlSession(true);
        return 0;
    }
}

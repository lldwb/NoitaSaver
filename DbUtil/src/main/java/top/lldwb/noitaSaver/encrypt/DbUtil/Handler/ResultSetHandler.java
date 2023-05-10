package top.lldwb.noitaSaver.encrypt.DbUtil.Handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 查询结果集处理接口
 */
public interface ResultSetHandler<T>{
    /**
     * 查询结果集处理的抽象方法
     * @param rs SQL查询结果集
     * @return 返回处理的结果
     * @throws SQLException 提供有关数据库访问错误或其他错误的信息的异常。
     */
    <T> T handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException;
}

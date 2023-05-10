package top.lldwb.noitaSaver.encrypt.DbUtil;

import top.lldwb.noitaSaver.encrypt.DbUtil.Handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryRunner {

    /**
     * 用于执行select并处理
     *
     * @param conn    连接对象
     * @param sql     SQL语句
     * @param handler 结果集处理的具体实现类
     * @param objects 条件语句
     * @param <T>     实体类
     * @return 返回处理过的查询结果
     * @throws SQLException           如果参数为空返回
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> handler, Object... objects) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        if (conn == null) {
            throw new SQLException("连接对象为null");
        }
        if (sql == null) {
            throw new SQLException("sql语句为null");
        }
        if (handler == null) {
            throw new SQLException("结果集处理对象为null");
        }
        return handler.handle(this.queryResultSet(conn, sql, objects));
    }

    //查询注意 记得关闭到ResultSet
    private ResultSet queryResultSet(Connection conn, String sql, Object... objects) throws SQLException {
        //创建预编译语句
        PreparedStatement pstm = conn.prepareStatement(sql);
        //添加参数
        if (objects != null) {
            for (int i = 1; i <= objects.length; i++) {
                pstm.setObject(i, objects[i - 1]);
            }
        }
        ResultSet rs = pstm.executeQuery();
        return rs;
    }

    /**
     * 用于执行insert update delete
     */
    public int update(Connection conn, String sql, Object... objects) throws SQLException {
        if (conn == null) {
            throw new SQLException("连接对象为null");
        }
        if (sql == null) {
            throw new SQLException("sql语句为null");
        }
        //创建预编译语句
        PreparedStatement pstm = conn.prepareStatement(sql);
        //添加参数
        if (objects != null) {
            for (int i = 1; i <= objects.length; i++) {
                pstm.setObject(i, objects[i - 1]);
            }
        }
        // 执行并返回执行的数据
        return pstm.executeUpdate();
    }

    /**
     * 批处理
     */
    public int batch() {
        return 1;
    }
}

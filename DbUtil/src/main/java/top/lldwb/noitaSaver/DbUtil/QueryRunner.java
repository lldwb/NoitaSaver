package top.lldwb.noitaSaver.DbUtil;

import top.lldwb.noitaSaver.DbUtil.Handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryRunner {

    /**
     * 用于执行select
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
    public int update() {
        return 1;
    }

    /**
     * 批处理
     */
    public int batch() {
        return 1;
    }
}

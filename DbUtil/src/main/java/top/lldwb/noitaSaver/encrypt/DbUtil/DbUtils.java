package top.lldwb.noitaSaver.encrypt.DbUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢世杰
 * @version 1.0
 */
public final class DbUtils {
    public static void close(Connection conn) throws SQLException {
        if (conn != null){
            conn.close();
        }
    }
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null){
            rs.close();
        }
    }
}
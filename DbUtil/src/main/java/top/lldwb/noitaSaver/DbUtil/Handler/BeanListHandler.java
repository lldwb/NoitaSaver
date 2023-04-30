package top.lldwb.noitaSaver.DbUtil.Handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements ResultSetHandler<List<T>> {

    Class<? extends T> type;

    public BeanListHandler(Class<? extends T> type) {
        this.type = type;
    }

    @Override
    public List<T> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<T> list = new ArrayList<>();

        //获取元数据
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()) {
            T t = type.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //getColumnLabel(i) 拿到别名  没有别名就是列名
                Field field = type.getDeclaredField(metaData.getColumnLabel(i));
                field.setAccessible(true);
                field.set(t, rs.getObject(i));
            }
            list.add(t);
        }

        return list;
    }
}

package top.lldwb.noitaSaver.DbUtil.Handler;

import top.lldwb.noitaSaver.DbUtil.conf.AnotherName;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回一个实体类集合
 * @param <T> 实体类
 */
public class BeanListHandler<T> implements ResultSetHandler<List<T>> {

    Class<? extends T> type;

    public BeanListHandler(Class<? extends T> type) {
        this.type = type;
    }

    @Override
    public List<T> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        //拿到有多少列
        int count = metaData.getColumnCount();
        while (rs.next()) {
            // 创建一个实体类的对象
            T t = type.newInstance();
            // 根据多少列遍历
            for (int i = 1; i <= count; i++) {
                // 遍历实体类的字段判断名字或者别名是否符合(但是会降低效率)
                for (Field field : type.getDeclaredFields()) {
                    String metaString = metaData.getColumnLabel(i);
                    // 检测是否有别名的字段
                    if (field.isAnnotationPresent(AnotherName.class) ? field.getDeclaredAnnotation(AnotherName.class).value().equals(metaString) : false || field.getName().equals(metaString)) {
                        // 如果有别名录入别名
                        field.setAccessible(true);
                        field.set(t, rs.getObject(i));
                    }
                }
            }
            list.add(t);
        }
        return list;
    }
}

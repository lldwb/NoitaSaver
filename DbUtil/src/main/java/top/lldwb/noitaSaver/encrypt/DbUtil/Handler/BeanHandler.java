package top.lldwb.noitaSaver.encrypt.DbUtil.Handler;

import top.lldwb.noitaSaver.encrypt.DbUtil.conf.AnotherName;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 返回一个实体类
 *
 * @param <T> 实体类
 */
public class BeanHandler<T> implements ResultSetHandler<T> {

    Class<? extends T> type;

    public BeanHandler(Class<? extends T> type) {
        this.type = type;
    }

    @Override
    public T handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 创建一个实体类的对象
        T t = type.newInstance();
        ResultSetMetaData metaData = rs.getMetaData();
        //拿到有多少列
        int count = metaData.getColumnCount();
        if (rs.next()) {
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
        }
        return t;
    }
}

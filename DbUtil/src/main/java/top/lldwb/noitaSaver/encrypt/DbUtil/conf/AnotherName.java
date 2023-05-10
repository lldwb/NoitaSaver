package top.lldwb.noitaSaver.encrypt.DbUtil.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段别名(当实体类属性名和对应的SQL表字段不一时使用)
 * @author 安然的尾巴
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AnotherName {
    String value();
}
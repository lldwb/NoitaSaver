/**
 * 邮箱传输协议类型
 * @author 安然的尾巴
 * @version 1.0
 */
public enum Protocl {
    SMTP("smtp");
    Protocl(String value){
        this.value = value;
    }
    String value;
}

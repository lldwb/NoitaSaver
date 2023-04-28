/**
 * 邮箱传输协议类型
 * @author 安然的尾巴
 * @version 1.0
 */
public enum Protocl {
    SMTP("SMTP"),
    POP3("POP3");
    Protocl(String value){
        this.value = value;
    }
    String value;
}

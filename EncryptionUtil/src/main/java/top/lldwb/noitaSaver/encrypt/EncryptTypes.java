package top.lldwb.noitaSaver.encrypt;

/**
 * 加密类型
 */
public enum EncryptTypes {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256");
    String value;
    EncryptTypes(String value){
        this.value = value;
    }
}

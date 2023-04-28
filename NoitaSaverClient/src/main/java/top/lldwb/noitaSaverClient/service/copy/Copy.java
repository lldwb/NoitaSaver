package top.lldwb.noitaSaverClient.service.copy;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public enum Copy {
    Archive("存档"),
    UnArchive("读档");
    String value;

    Copy(String value) {
        this.value = value;
    }
}

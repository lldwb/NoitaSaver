package top.lldwb.noitaSaver.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class FileUtilsTest {
    @Test
    public void ioFolder() {
        List<String> list = FileUtils.getFolderNameList("C:\\Users\\11\\Desktop\\安然的尾巴\\账号\\steam 账号");

        for (String str:list){
            System.out.println(str);
        }
    }
}

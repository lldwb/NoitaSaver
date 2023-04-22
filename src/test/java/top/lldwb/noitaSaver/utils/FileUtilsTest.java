package top.lldwb.noitaSaver.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FileUtilsTest {
    @Test
    public void ioFolder() {
//        FileUtils.copyDirectory("C:\\Users\\lldwb\\Desktop\\Noita\\1\\save00", "C:\\Users\\lldwb\\Desktop\\Noita\\test\\save00");

        LocalDateTime localDateTime = LocalDateTime.now();

        String writePath="C:\\Users\\lldwb\\Desktop\\Noita\\1\\save00";

        String readPath="C:\\Users\\lldwb\\Desktop\\Noita\\";

        System.out.println(readPath+localDateTime);
        // 在目标文件夹中创建子文件夹
        new File(readPath+localDateTime).mkdir();

//        FileUtils.copyDirectory(writePath, readPath+localDateTime+"\\save00");
    }
    @Test
    public void s(){

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}

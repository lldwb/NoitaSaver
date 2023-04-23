package top.lldwb.noitaSaver.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class FileUtilsTest {
    @Test
    public void ioFolder() {
        Thread thread = new Thread(() ->
        {
            try {
                FileUtils.fileIO("E:\\我的世界\\测压工具\\EndMinecraftPlus-master.zip", "G:\\2023-04-23_10-10-53\\EndMinecraftPlus-master.zip");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

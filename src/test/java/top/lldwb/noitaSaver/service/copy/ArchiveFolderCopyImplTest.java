package top.lldwb.noitaSaver.service.copy;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * 存档类，实现FolderCopy接口，用于将文件夹存档
 */
public class ArchiveFolderCopyImplTest {

    @Test
    public void tset() {
//        while (true) {
//            String localDateTime = LocalDateTime.now().toString();
//            System.out.println(localDateTime.substring(0, localDateTime.length() - 4));
//        }


        String writePath="C:\\Users\\lldwb\\AppData\\LocalLow\\Nolla_Games_Noita\\save00";

        String readPath="C:\\Users\\lldwb\\Desktop\\Noita\\";
        FolderCopy folderCopy = new ArchiveFolderCopyImpl();
        folderCopy.copy(writePath,readPath);
    }
}

package top.lldwb.noitaSaverClient.service.copy;

import org.junit.jupiter.api.Test;

/**
 * 存档类，实现FolderCopy接口，用于将文件夹存档
 */
public class ArchiveFolderCopyImplTest {

    @Test
    public void tset() {
        String writePath="C:\\Users\\lldwb\\AppData\\LocalLow\\Nolla_Games_Noita\\save00";

        String readPath="C:\\Users\\lldwb\\Desktop\\Noita";
        FolderCopy folderCopy = new ArchiveFolderCopyImpl();
        folderCopy.copy(writePath,readPath);
    }
}

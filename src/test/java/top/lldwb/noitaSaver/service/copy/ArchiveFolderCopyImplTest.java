package top.lldwb.noitaSaver.service.copy;

import org.junit.jupiter.api.Test;

/**
 * 存档类，实现FolderCopy接口，用于将文件夹存档
 */
public class ArchiveFolderCopyImplTest {

    @Test
    public void tset() {
        String writePath="C:\\Users\\lldwb\\Desktop\\Noita\\2023-04-22_18-23-33";

        String readPath="C:\\Users\\lldwb\\Desktop\\Noita\\";
        FolderCopy folderCopy = new ArchiveFolderCopyImpl();
        folderCopy.copy(writePath,readPath);
    }
}

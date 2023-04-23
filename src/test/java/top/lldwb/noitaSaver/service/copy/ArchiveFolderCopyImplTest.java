package top.lldwb.noitaSaver.service.copy;

import org.junit.jupiter.api.Test;

/**
 * 存档类，实现FolderCopy接口，用于将文件夹存档
 */
public class ArchiveFolderCopyImplTest {

    @Test
    public void tset() {
        String writePath="E:\\我的世界\\测压工具";

        String readPath="G:\\";
        FolderCopy folderCopy = new ArchiveFolderCopyImpl();
        folderCopy.copy(writePath,readPath);
    }
}

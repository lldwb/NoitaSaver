package top.lldwb.noitaSaver.service.copy;

import org.junit.jupiter.api.Test;

public class UnArchiveFolderCopyImplTest {
    @Test
    public void test(){

        String writePath="C:\\Users\\lldwb\\Desktop\\Noita\\2023-04-22_18-23-33";

        String readPath="C:\\Users\\lldwb\\AppData\\LocalLow\\Nolla_Games_Noita\\save00";
        FolderCopy folderCopy = new UnArchiveFolderCopyImpl();
        folderCopy.copy(writePath,readPath);
    }
}

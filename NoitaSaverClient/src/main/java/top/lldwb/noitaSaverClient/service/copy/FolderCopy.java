package top.lldwb.noitaSaverClient.service.copy;

/**
 * 文件夹复制接口
 *
 * @author 安然的尾巴
 * @version 1.0
 * <p>
 * 存档：ArchiveFolderCopyImpl，实现ArchiveSaver接口。
 * 读档：UnArchiveFolderCopyImpl，实现ArchiveReader接口。
 */
public interface FolderCopy {

    /**
     * 将一个文件夹复制到另一个文件夹中
     *
     * @param readPath  源文件夹地址
     * @param writePath 目标文件夹地址
     */
    void copy(String readPath, String writePath);

    /**
     * 静态工厂方法
     *
     * @param copy 枚举常量
     * @return
     */
    static FolderCopy getFolderCopyFactory(Copy copy) {
        if (Copy.Archive == copy) {
            return new ArchiveFolderCopyImpl();
        } else if (Copy.UnArchive == copy) {
            return new UnArchiveFolderCopyImpl();
        } else {
            return null;
        }
    }
}

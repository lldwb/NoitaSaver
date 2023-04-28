import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密解密工具类
 */
public class MD5Util {
    /**
     * MD5加密方法
     * @param input 待加密的字符串
     * @return 加密后的字符串，32位的十六进制字符串
     */
    public static String md5Encrypt(String input) {
        try {
            // 创建MessageDigest实例并指定加密算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将待加密的字符串转换为字节数组
            byte[] bytes = input.getBytes();
            // 执行加密操作
            byte[] encryptedBytes = md.digest(bytes);
            // 将加密后的字节数组转换为32位的十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : encryptedBytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5解密方法（由于MD5是不可逆的加密算法，所以不提供解密方法）
     * @param input 待解密的字符串
     * @return 解密后的字符串
     */
    public static String md5Decrypt(String input) {
        throw new UnsupportedOperationException("MD5是不可逆的加密算法，无法提供解密方法！");
    }
}
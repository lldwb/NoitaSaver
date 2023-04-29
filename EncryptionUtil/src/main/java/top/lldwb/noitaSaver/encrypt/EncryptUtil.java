package top.lldwb.noitaSaver.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 */
public class EncryptUtil {
    /**
     * 加密方法
     * @param input 待加密的字符串
     * @return 加密后的字符串，32位的十六进制字符串
     */
    public static String encrypt(String input,EncryptTypes types) {
        try {
            // 创建MessageDigest实例并指定加密算法
            MessageDigest md = MessageDigest.getInstance(types.value);
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
}
package top.lldwb.noitaSaver.jjwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class Jjwt {
    /**
     * 密钥
     */
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 签发人
     */
    private static final String ISS = "lldwb.top";

    /**
     * 主题
     */
    private static final String SUBJECT = "JWT_AUTH";

    /**
     * 对字符串进行加密
     * @param string 需要加密的字符串
     * @return 加密的结果
     */
    public static String encryptionString(String string) {
        Map<String, String> claims = new HashMap<String, String>();
        claims.put("data", string);

        return Jwts.builder()
                // 签名 - 通过KEY定义的HS256的算法进行加密
                .signWith(KEY)
                // 设置签发人
                .setIssuer(ISS)
                // 设置主题
                .setSubject(SUBJECT)
                // 设置数据内容
                .setClaims(claims)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 设置过期时间
                // new Date(毫秒):将毫秒转换为Date格式
                // System.currentTimeMillis():获取当前时间返回毫秒
                // 5 * 60 * 1000:5分钟
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                // 设置jwt唯一标识
                .setId(UUID.randomUUID().toString())
                // 创建 jwt 令牌字符串
                .compact();
    }

    /**
     * 对字符串进行解密
     * @param string 需要解密的字符串
     * @return 解密的结果
     */
    public static String decryptionString(String string) {
        return Jwts.parserBuilder()
                // 设置签名密钥 - 通过 KEY 定义的HS256的算法进行解密
                .setSigningKey(KEY)
                .build()
                // 解析 token 中的数据载体部分
                .parseClaimsJws(string)
                // 获取载体数据
                .getBody()
                // 根据 name 和 type 获取对应的 value
                .get("data", String.class);
    }
}

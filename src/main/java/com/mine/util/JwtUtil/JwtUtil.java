package com.mine.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description JWT的基本使用
 * @DATE 2019/5/10 16:05
 */
@Slf4j
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";
    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    /**
     * 创建时间
     */
    private static final String CREATED = "created";

    /**
     * 利用数据声明生成令牌
     *
     * @param claims 存储需要保存在令牌token中的数据
     * @return 返回加密的令牌字符串
     */
    private static String generateToken(Map<String, Object> claims) {
        //设置过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //SignatureAlgorithm设置加密方式
        //SECRET设置加密的密钥，在之后的解密时需要再次用到
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 从令牌中获取数据
     *
     * @param token 令牌
     * @return 用户名
     */
    public static Claims getDataFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//            String username1 = claims.getIssuer();
//            String username2 = claims.getSubject();
//            String username3 = claims.getAudience();
//            Date username4 = claims.getExpiration();
//            Date username5 = claims.getNotBefore();
//            Date username6 = claims.getIssuedAt();
//            String username7 = claims.getId();
//            String str1 = claims.get("1").toString();
//            String str2 = claims.get("2").toString();
//            String str3 = claims.get("3").toString();
//            String str5 = claims.get("exp").toString();
//            //使用下面这种方式可以预防空指针异常，同事指定返回类型
//            String str6 = claims.get("1", String.class);
//            String str7 = claims.get("4", String.class);
//            LinkedHashMap hashMap = claims.get("xxxx", LinkedHashMap.class);
        } catch (Exception e) {
            log.info("getDataFromToken is wrong");
            return null;
        }
        return claims;
    }

    /**
     * 从令牌中获取数据详情
     *
     * @param token 令牌
     * @return 用户名
     */
    public static void getDataDetailFromToken(Claims claims) {
        try {
            String username1 = claims.getIssuer();
            String username2 = claims.getSubject();
            String username3 = claims.getAudience();
            Date username4 = claims.getExpiration();
            Date username5 = claims.getNotBefore();
            Date username6 = claims.getIssuedAt();
            String username7 = claims.getId();
            String str1 = claims.get("1").toString();
            String str2 = claims.get("2").toString();
            String str3 = claims.get("3").toString();
            String str5 = claims.get("exp").toString();
            //使用下面这种方式可以预防空指针异常，同事指定返回类型
            String str6 = claims.get("1", String.class);
            String str7 = claims.get("4", String.class);
            LinkedHashMap hashMap = claims.get("xxxx", LinkedHashMap.class);
        } catch (Exception e) {
            log.info("getDataFromToken is wrong");
        }
    }

    /**
     * 获取请求头中的token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)) {
            token = null;
        }
        return token;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getDataFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getDataFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("1", "a");
        claims.put("2", "a");
        claims.put("3", "a");
        String token = generateToken(claims);
        getDataFromToken(token);
    }
}

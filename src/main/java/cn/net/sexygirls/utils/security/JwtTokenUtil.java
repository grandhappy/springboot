package cn.net.sexygirls.utils.security;

import cn.net.sexygirls.security.config.SecurityUserDetails;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: jwt工具类
 *               提供校验toeken 、生成token、根据token获取用户等方法
 * @Author: zule
 * @Date: 2019/5/7
 */
@Component
public class JwtTokenUtil {
    /**
     * JWT签名加密key
     */
    public static String secret;
    /**
     * token分割
     */
    public static String tokenPrefix;
    /**
     * token参数头
     */
    public static String tokenHeader;
    /**
     * 权限参数头
     */
    public static String authHeader;
    /**
     * token有效期
     */
    public static Integer tokenExpireTime;

    /**
     * 不需要认证的接口
     */
    public static String antMatchers;

    /**
     * @Description:利用Jwts生成token
     * @param: userDetails
     * @Return: java.lang.String
     * @Author: zule
     * @Date: 2019/5/7
     */
    public static String generateAccessToken(UserDetails userDetails){

        SecurityUserDetails securityUserDetails = (SecurityUserDetails)userDetails;
        //登陆成功生成JWT
        return Jwts.builder()
                //主题 放入用户名
                .setSubject(userDetails.getUsername())
                .setId(securityUserDetails.getId()+"")
                //自定义属性 放入用户拥有权限
                .claim(authHeader, JSON.toJSONString(securityUserDetails.getAuthorities()))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtTokenUtil.secret = secret;
    }
    @Value("${jwt.tokenPrefix}")
    public void setTokenPrefix(String tokenPrefix) {
        JwtTokenUtil.tokenPrefix = tokenPrefix;
    }
    @Value("${jwt.tokenHeader}")
    public void setTokenHeader(String tokenHeader) {
        JwtTokenUtil.tokenHeader = tokenHeader;
    }
    @Value("${jwt.authHeader}")
    public void setAuthHeader(String authHeader) {
        JwtTokenUtil.authHeader = authHeader;
    }
    @Value("${jwt.expiration}")
    public void setTokenExpireTime(Integer tokenExpireTime) {
        JwtTokenUtil.tokenExpireTime = tokenExpireTime;
    }
    @Value("${jwt.antMatchers}")
    public void setAntMatchers(String antMatchers) {
        JwtTokenUtil.antMatchers = antMatchers;
    }
}

package cn.net.sexygirls.security.config;

import cn.net.sexygirls.exception.BaseException;
import cn.net.sexygirls.utils.common.ResponseUtil;
import cn.net.sexygirls.utils.security.JwtTokenUtil;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * jwt认证token
 * 每次请求接口时，就会进入这里验证token是否合法token，
 * 如果用户一直在操作，则token 过期时间会叠加；如果超过设置的过期时间未操作，则token 失效，需要重新登录。
 * @Author: zule
 * @Date: 2019/5/6
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //过滤掉不需要token验证的url
        /*if(authenticationRequestMatcher != null && !authenticationRequestMatcher.matches(httpServletRequest)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }*/
        //获得accessToken
        String accessToken = request.getHeader(JwtTokenUtil.tokenHeader);
        if (null!=accessToken && accessToken.startsWith(JwtTokenUtil.tokenPrefix)) {
            try {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //包装请求设置uid
                /*if (authentication != null) {
                    Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
                    m.put("userId", new String[]{authentication.getCredentials() + ""});
                    request = new ParameterRequestWrapper(request, m);
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
        return;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(JwtTokenUtil.tokenHeader);
        if (!StringUtils.isEmpty(token)) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtTokenUtil.secret)
                        .parseClaimsJws(token.replace(JwtTokenUtil.tokenPrefix, ""))
                        .getBody();

                //获取用户名
                String username = claims.getSubject();
                String userId=claims.getId();
                //获取权限（角色）
                List<GrantedAuthority> authorities = new ArrayList<>();
                String authority = claims.get(JwtTokenUtil.authHeader).toString();
                if(!StringUtils.isEmpty(authority)){
                    //authority="[{"authority":"common"}]"
                    List<Map<String,String>> authrityMap =JSONObject.parseObject(authority, List.class);
                    for(Map<String,String> role : authrityMap){
                        if(!StringUtils.isEmpty(role)) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                if(!StringUtils.isEmpty(username)) {
                    //此处password不能为null
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, userId, authorities);
                }
            } catch (ExpiredJwtException e) {
                System.out.println("toekn超过有效期，请重新登");
                //throw new BaseException("401","toekn超过有效期，请重新登录");
                ResponseUtil.out(response, ResponseUtil.resultMap(401,"token has expired"));
            } catch (Exception e){
                ResponseUtil.out(response, ResponseUtil.resultMap(401,"token invalid"));
            }
        }
        return null;
    }

}


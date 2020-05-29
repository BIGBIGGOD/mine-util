package com.mine.util.securityutil.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.collect.Maps;
import com.mine.util.securityutil.JwtTokenUtil;
import com.mine.util.securityutil.config.SecurityParams;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * JWT登录授权过滤器
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
@Component
//@WebFilter(filterName = "jwtAuthenticationTokenFilter", urlPatterns = {"/*"})
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SecurityParams securityParams;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("进入jwt过滤器,请求路径={}", request.getRequestURI());
        //获取指定请求头的内容
        String authHeader = request.getHeader(securityParams.getTokenHeader());
        //检验指定请求头内容是否为null且以指定的前缀开头
        if (authHeader != null && authHeader.startsWith(securityParams.getTokenHead())) {
            // 截取除该前缀意外的内容,该内容为jwt生成的token
            String authToken = authHeader.substring(securityParams.getTokenHead().length());
            // jwt解析token获取username,当其为空时略过加载spring security context，当在其他步骤中需要登录验证时候会报未登录或者没有权限等异常
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking username:{}", username);
            // 判断用户名称和Security上下文是否为空，上下文为空时则需要判断是否重新授权
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //在security中根据用户名称
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // 根据jwt的token、security的用户信息验证token是否过期
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //构造上下文
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private void failResponse(ServletResponse response, int errorCode, String msg) {
        response.setContentType("application/json;charset=UTF-8");

        Map<String, String> map = Maps.newHashMap();
        map.put("retCode", errorCode + "");
        map.put("msg", msg);
        map.put("model", "xxxx");

        try {
            response.getWriter().write(JSONObject.fromObject(map).toString());
            response.flushBuffer();
        } catch (IOException e) {
            log.error("JwtAuthenticationTokenFilter fail response : ", e);
        }
    }
}

package com.mine.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.mine.security.entity.AdminUserDetails;
import com.mine.security.filter.JwtAuthenticationTokenFilter;
import com.mine.security.model.ManageUserDo;
import com.mine.security.model.UserPermissionDo;
import com.mine.security.response.RestAuthenticationEntryPoint;
import com.mine.security.response.RestfulAccessDeniedHandler;
import com.mine.security.service.SecurityUserService;
import lombok.extern.slf4j.Slf4j;


/**
 * SpringSecurity的配置
 *
 * @author macro
 * @date 2018/4/26
 */
@Slf4j
@Configuration
@EnableWebSecurity
/**
 * Spring Security默认是禁用注解的，要想开启注解，需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，
 * 并在该类中将AuthenticationManager定义为Bean
 * prePostEnabled设置为true之后可以通过在接口上增加注解@PreAuthorize("hasRole('权限1')")进行权限控制
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private SecurityUserService securityUserService;

    /**
     * 设置URL相关的权限
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()// 由于使用的是JWT，我们这里不需要csrf
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //开启跨域
                .and().cors()
                //authorizeRequests()配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
                .and().authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/999",
                        "/*.html",
                        "/*.jsp",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/webjars/springfox-swagger-ui/**"
                )
                .permitAll()
                // 对登录注册要允许匿名访问
                .antMatchers("/user/login", "/user/register")
                .permitAll();
                //跨域请求会先进行一次options请求
//                .antMatchers(HttpMethod.OPTIONS)
//                .permitAll()
                //测试时全部运行访问
//                .antMatchers("/**")
//                .permitAll()
                //当登录通过之后，指定特定地址需要对应角色权限访问,其中权限名称在数据库中存储时需要加前缀ROLE_
//                .antMatchers("/user/test1").hasRole("权限1")
                //指定特定地址需要鉴权认证
//                .antMatchers("/user/test1")
//                .authenticated();
                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest()
//                .authenticated();
        //formLogin()对应表单认证相关的配置
        //httpBasic()可以配置basic登录
        // 禁用缓存
//        httpSecurity.headers().cacheControl();
        // 添加自定义filter如jwtFilter，注意其中before表示添加在xxx过滤器之后，此外还有after和at表示怎么添加filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        // 退出登录处理器,logout()对应了注销相关的配置
        httpSecurity.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        log.info("httpSecurity设置完成");
    }

    /**
     * 设置userDetailService和加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //指定加密方法
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {

        //获取登录用户信息
        return username -> {
            log.info("获取登录用户信息");
            ManageUserDo manageUserDo = securityUserService.getUserByUsername(username);
            if (manageUserDo != null) {
                //获取当前用户对应的权限集合
                List<UserPermissionDo> permissionList = securityUserService.getUserPermissionList(manageUserDo.getId());
                return new AdminUserDetails(manageUserDo, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

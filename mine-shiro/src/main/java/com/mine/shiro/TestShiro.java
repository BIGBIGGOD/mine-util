package com.mine.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangqd on 2019/2/15.
 */
public class TestShiro {

    public static void main(String[] args) {
        //用户们
        ShiroUser zhang3 = new ShiroUser();
        zhang3.setName("zhang3");
        zhang3.setPassword("12345");

        ShiroUser li4 = new ShiroUser();
        li4.setName("li4");
        li4.setPassword("abcde");

        ShiroUser wang5 = new ShiroUser();
        wang5.setName("wang5");
        wang5.setPassword("wrongpassword");

        List<ShiroUser> shiroUsers = new ArrayList<>();

        shiroUsers.add(zhang3);
        shiroUsers.add(li4);
        shiroUsers.add(wang5);

        //角色们
        String roleAdmin = "admin";
        String roleProductManager = "productManager";

        List<String> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleProductManager);

        //权限们
        String permitAddProduct = "addProduct";
        String permitAddOrder = "addOrder";

        List<String> permits = new ArrayList<>();
        permits.add(permitAddProduct);
        permits.add(permitAddOrder);

        //登陆每个用户
        for (ShiroUser shiroUser : shiroUsers) {
            if (login(shiroUser)) {
                System.out.printf("%s \t登陆成功，用的密码是 %s\t %n", shiroUser.getName(), shiroUser.getPassword());
            } else {
                System.out.printf("%s \t登陆失败，用的密码是 %s\t %n", shiroUser.getName(), shiroUser.getPassword());
            }
        }

        System.out.println("-------how2j 分割线------");

        //判断能够登录的用户是否拥有某个角色
        for (ShiroUser shiroUser : shiroUsers) {
            for (String role : roles) {
                if (login(shiroUser)) {
                    if (hasRole(shiroUser, role)) {
                        System.out.printf("%s\t 拥有角色: %s\t%n", shiroUser.getName(), role);
                    } else {
                        System.out.printf("%s\t 不拥有角色: %s\t%n", shiroUser.getName(), role);
                    }
                }
            }
        }
        System.out.println("-------how2j 分割线------");

        //判断能够登录的用户，是否拥有某种权限
        for (ShiroUser shiroUser : shiroUsers) {
            for (String permit : permits) {
                if (login(shiroUser)) {
                    if (isPermitted(shiroUser, permit)) {
                        System.out.printf("%s\t 拥有权限: %s\t%n", shiroUser.getName(), permit);
                    } else {
                        System.out.printf("%s\t 不拥有权限: %s\t%n", shiroUser.getName(), permit);
                    }
                }
            }
        }
    }

    private static boolean hasRole(ShiroUser shiroUser, String role) {
        Subject subject = getSubject(shiroUser);
        return subject.hasRole(role);
    }

    private static boolean isPermitted(ShiroUser shiroUser, String permit) {
        Subject subject = getSubject(shiroUser);
        return subject.isPermitted(permit);
    }

    private static Subject getSubject(ShiroUser shiroUser) {
        //加载配置文件，并获取工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取安全管理者实例
        SecurityManager sm = factory.getInstance();
        //将安全管理者放入全局对象
        SecurityUtils.setSecurityManager(sm);
        //全局对象通过安全管理者生成Subject对象
        Subject subject = SecurityUtils.getSubject();

        return subject;
    }

    private static boolean login(ShiroUser shiroUser) {
        Subject subject = getSubject(shiroUser);
        //如果已经登录过了，退出
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(shiroUser.getName(), shiroUser.getPassword());
        try {
            //将用户的数据token 最终传递到Realm中进行对比
            subject.login(token);
        } catch (AuthenticationException e) {
            //验证错误
            return false;
        }

        return subject.isAuthenticated();
    }

}

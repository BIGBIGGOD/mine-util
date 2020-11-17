package com.mine.utils.cookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/27 15:17
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class CookieUtil {

    public static void setCookie(HttpServletResponse response, String key, String content) {
        Cookie cookie = new Cookie(key, content);
        //缓存30分钟
        cookie.setMaxAge(30 * 60);
        //设置了setHttpOnly属性之后前端将无法通过js读取cookie
        cookie.setHttpOnly(true);
        //设置cookie的路径为根目录
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] list = request.getCookies();
        if (list == null || list.length < 1) {
            log.info("cookie 列表为空");
            return null;
        }
        for (Cookie cookie : list) {
            log.info("cookie{},{}", cookie.getName(), cookie.getValue());
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        log.info("cookie 列表未找到匹配的cookie");
        return null;
    }


    public static void clearCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] list = request.getCookies();
        if (list == null || list.length < 1) {
            log.info("cookie 列表为空 不进行清理");
            return;
        }
        for (Cookie cookie : list) {
            //当某个cookie的name为test的时候把这个cookie的过期时间设置为0，即立刻失效
            if (cookie.getName().equals("test")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}

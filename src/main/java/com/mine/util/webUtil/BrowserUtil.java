package com.mine.util.webUtil;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
public class BrowserUtil {

    public static BrowserInfo getBrowserInfo(HttpServletRequest request){
        String ua = Optional.ofNullable(request.getHeader("User-Agent")).orElse("");
        String re = Optional.ofNullable(request.getHeader("Referer")).orElse("");
        String ip = Optional.ofNullable(WebUtil.getClientRealIp(request)).orElse("");
        return new BrowserInfo(ip,ua,re);
    }
}

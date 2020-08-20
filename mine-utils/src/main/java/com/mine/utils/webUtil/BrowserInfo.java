package com.mine.utils.webUtil;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class BrowserInfo {

    /**
     * ip地址
     */
    String ip;
    /**
     * 用户浏览器信息
     */
    String userAgent;
    /**
     * xxxx
     */
    String referer;

    public BrowserInfo(String ip, String userAgent , String referer) {

       this.ip = ip;
       this.userAgent = userAgent;
       this.referer = referer;
    }

}
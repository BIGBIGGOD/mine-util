package com.mine.utils.webUtil;

import java.net.HttpURLConnection;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 */
@Slf4j
public class URLUtil {


    /**
     * 检查是否可连接
     * @param urlStr
     * @return
     */
    public static synchronized boolean isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return false;
        }
        while (counts < 3) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                int state = con.getResponseCode();
                if (state == 200) {
                    return true;
                }
                break;
            } catch (Exception ex) {
                counts++;
                continue;
            }
        }
        return false;
    }

    public static String getDomainUrl(String urlStr) {
        String domainUrl = urlStr;
        try {
            URL url = new URL(urlStr);
            domainUrl = url.getProtocol() + "://" + url.getAuthority();
        } catch (Exception e) {
            //  handle exception
            log.error("getDomainUrl is erro,url :" + urlStr, e);
        }
        return domainUrl;
    }


    public static String getHost(String urlStr) {
        String host = urlStr;
        try {
            URL url = new URL(urlStr);
            host = url.getHost();
        } catch (Exception e) {
            //  handle exception
            log.error("getHost is erro,url :" + urlStr, e);
        }
        return host;
    }


    public static void main(String[] args) {
        System.out.println(getDomainUrl("http://common.cnblogs.com/favicon.ico"));
    }

}
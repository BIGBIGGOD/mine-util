package com.mine.config.properties;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**@Description druid过滤器
 * Created by jiangqd on 2019/1/12.
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {
        // 忽略资源
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
    }
)
public class DruidStatFilter extends WebStatFilter {

}

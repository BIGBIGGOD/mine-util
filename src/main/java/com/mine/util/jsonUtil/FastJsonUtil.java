package com.mine.util.jsonUtil;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/21.
 */
@Slf4j
public class FastJsonUtil {

    /**
     * json转Bean
     * @param json
     * @param clazz
     * @return
     */
    public static Object jsonToBean(String json, Class clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * Bean转json
     */
    public static String BeanToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

}

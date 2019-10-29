package com.mine.util.jsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mine.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jiangqd
 * @date 2019/3/21
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
    public static String beanToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * map转json
     */
    public static String mapToJson() {
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "mapxxx");
        map.put("pwd", "pwdxxx");
        //对象转json
        String json1 = JSON.toJSONString(map);
        //带格式转json
        String json2 = JSON.toJSONString(map);
        //json转对象
        User user1 = JSON.parseObject(json1, User.class);
        //json转List集合
        List<User> list = JSON.parseArray(json1, User.class);
        return json1;
    }

}

package com.mine.util.jsonUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2019/4/24.
 */
public class JacksonUtil {

    public static <T extends Object> T jsonToBean(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T t = mapper.readValue(json, clazz);
        return t;
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("dd", "ji");
        map.put("dd54", "545");
        String str = map.toString();
        String json = new Gson().toJson(map);
        HashMap<String, Object> jsonMap = JacksonUtil.jsonToBean(json, HashMap.class);
    }
}

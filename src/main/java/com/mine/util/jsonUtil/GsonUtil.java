package com.mine.util.jsonUtil;

import com.google.gson.Gson;
import com.mine.util.httpClientUtil.result.CommonResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/21.
 */
@Slf4j
public class GsonUtil {

    private static Gson gson = new Gson();

    /**
     * 将json转化成对应的Bean类
     * 参数中的T与返回类型中的T要一致，所以需要实现CommonResult类，可根据实际需求更改
     */
    public static <T extends CommonResult> T jsonToBean(String json, Class<T> clazz) {
        T t = null;
        t = gson.fromJson(json, clazz);
        return t;
    }

    /**
     * 将Bean类转化成json
     */
    public String beanToJson(Object obj) {
        return gson.toJson(obj);
    }

}

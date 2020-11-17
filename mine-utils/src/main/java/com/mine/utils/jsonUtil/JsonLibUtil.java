package com.mine.utils.jsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description jsonlib的基本使用
 * @DATE 2019/5/27 11:37
 */
public class JsonLibUtil {

    public static void main(String[] args) {
        String acceptjson = "{\"id\":\"A001\",\"name\":\"Jack\"}";
//        JSONObject json = JSONObject.fromObject(acceptjson);
//        System.out.println(json);
//        Object obj = json.get("ss");
//        System.out.println(obj);
//        JSONObject jsonObject = JSONObject.fromObject(acceptjson);
//        if (jsonObject.containsKey("msgType")) {
//            String xx = jsonObject.get("msgType").toString();
//            System.out.println(xx);
//        }
//        new User().getName().toString();
        Long.parseLong(null);
        System.out.println("");
    }

    public void  test(String json) {
        //json转化成JSONObject对象
        JSONObject jsonObject = JSONObject.fromObject(json);
        //从JSONObject对象中获取JSONArray数组
        JSONArray jsonArray = jsonObject.getJSONArray("key1");
        //从JSONObject对象中根据key值获取String字符串
        String str1 = jsonObject.getString("key2");
        //往JSONObject对象中添加键值对数据
        //put方式的值可以为null
        jsonObject.put("name", "ceshiname");
        //element方式的值不可以为null，需要使用空字符串替代
        jsonObject.element("xxname", "ceshiname");
        //往JSONArray数组中添加数据
        jsonArray.add("sss");
        //将JSONObject转化成String类型
        String str2 = jsonObject.toString();
        //判断是否存在对应key值的数据
        jsonObject.containsKey("key3");
    }
}

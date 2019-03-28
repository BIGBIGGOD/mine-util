package com.mine.util.jsonUtil;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.mine.BaseJunit4Test;
import com.mine.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by jiangqd on 2019/3/21.
 */
@Slf4j
public class FastJsonUtilTest extends BaseJunit4Test {

    @Test
    public void testJsonToBean() {
        String json = new Gson().toJson(new User("悟空"));
        System.out.println("json="+json);
        User user = (User)FastJsonUtil.jsonToBean(json, User.class);
        System.out.println(user);
    }

    @Test
    public void testBeanToJson() {
        Gson gson = new Gson();
        System.out.println(JSON.toJSONString(new User("悟空")));
        User user = new User();
        user.setName("八戒");
        user.setTime(System.currentTimeMillis());
        String json1 = gson.toJson(user);
        User user1 = gson.fromJson(json1, User.class);
        System.out.println(user1);
        String json2 = JSON.toJSONString(user);
        User user2 = JSON.parseObject(json2, User.class);
        System.out.println("nihaoa");
        String str = "{\"name\":\"tomcat\",\"time\":123456789012}";
        User user3 = gson.fromJson(str,User.class);
        System.out.println();
    }

}

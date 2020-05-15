package com.mine.util.jsonUtil;

import com.google.gson.Gson;
import com.mine.BaseJunit4Test;
import com.mine.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by jiangqd on 2019/3/21.
 */
@Slf4j
public class GsonUtilTest extends BaseJunit4Test{

    @Test
    public void testJsonToBean() {
        Gson gson = new Gson();
        User user = new User("joij");
        User user1 = GsonUtil.jsonToBean(gson.toJson(user), User.class);
        System.out.println(user1);
    }

}

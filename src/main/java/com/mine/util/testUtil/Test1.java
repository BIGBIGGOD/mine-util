package com.mine.util.testUtil;

import lombok.Data;

import java.util.List;

/**
 * Created by jiangqd on 2019/3/16.
 */
@Data
public class Test1 {

    private List<User> userList;

    @Data
    public class User {

        private int id;
        private String name;

    }

}

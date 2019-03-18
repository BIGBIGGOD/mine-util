package com.mine;

import com.mine.mapper.CeShiMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
public class UserTest extends BaseJunit4Test {

    @Autowired
    private CeShiMapper ceShiMapper;

    @Test
    public void test() {
        log.info("你好啊");
        List<String> list = ceShiMapper.getUsername();
        System.out.println(list.toString());
    }

}

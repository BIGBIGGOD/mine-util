package com.mine;

import com.mine.mapper.CeShiMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
public class UserTest extends BaseJunit4Test {

    @Autowired
    private CeShiMapper ceShiMapper;

    @Test
    public void test1() {
        log.info("你好啊");
        List<String> list = ceShiMapper.getUsername();
        System.out.println(list.toString());
    }

    @Test
    public void test2() {
        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        //Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println("**************");
        Integer i5 = 127;//java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6);//true
        Integer a= 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println(i2 == i3);
    }

}

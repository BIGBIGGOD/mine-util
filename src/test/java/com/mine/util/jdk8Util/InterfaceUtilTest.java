package com.mine.util.jdk8Util;

import com.mine.BaseJunit4Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by jiangqd on 2019/3/21.
 */
@Slf4j
public class InterfaceUtilTest extends BaseJunit4Test {

    @Test
    public void test1() {
        InterfaceUtil<String, Integer> interfaceUtil = new InterfaceUtil<String, Integer>() {
            @Override
        public void sayHello(Integer i) {
            System.out.println("Hello,你好啊");
        }
    };
        interfaceUtil.sayHi();
    }

    @Test
    public void test2() {
        InterfaceUtil interfaceUtil = i -> System.out.println("Hello,你好啊");
        interfaceUtil.sayHello(1);
        interfaceUtil.sayHi();
    }

}

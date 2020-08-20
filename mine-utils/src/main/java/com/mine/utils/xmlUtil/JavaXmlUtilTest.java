package com.mine.utils.xmlUtil;

import org.junit.Test;

import com.mine.common.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/11.
 */
@Slf4j
public class JavaXmlUtilTest {

    @Test
    public void testXml() {
        User user = new User("悟空", "123", 1);
        String str = JavaXmlUtil.toXml(user);
        System.out.println(str);
    }


}

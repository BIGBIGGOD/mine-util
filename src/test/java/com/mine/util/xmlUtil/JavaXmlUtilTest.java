package com.mine.util.xmlUtil;

import com.mine.BaseJunit4Test;
import com.mine.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by jiangqd on 2019/3/11.
 */
@Slf4j
public class JavaXmlUtilTest extends BaseJunit4Test{

    @Test
    public void testXml() {
        User user = new User("悟空","123", 1);
        String str = JavaXmlUtil.toXml(user);
        System.out.println(str);
    }


}

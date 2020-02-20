package com.mine.util.springUtil;

import org.junit.Test;

import com.mine.common.BaseJunit4Test;
import com.mine.service.TestService;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/2/20 14:39
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ApplicationContextAwareUtilTest extends BaseJunit4Test {

    @Test
    public void testGetBean() {
        System.out.println(ApplicationContextAwareUtil.getApplicationContext().getApplicationName() + "xxx");
        System.out.println(ApplicationContextAwareUtil.getBean(TestService.class));
        System.out.println("你好啊");
    }

}

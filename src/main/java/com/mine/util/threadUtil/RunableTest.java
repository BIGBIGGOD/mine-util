package com.mine.util.threadUtil;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/20 0020 21:31
 * @Copyright Copyright © 2019 深圳才华有限网络科技股份有限公司. All rights reserved.
 */
public class RunableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("我是RunableTest");
    }
}

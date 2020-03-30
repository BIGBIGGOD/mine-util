package com.mine.util.threadUtil;

import com.mine.util.threadUtil.RunableTest;
import com.mine.util.threadUtil.ThreadTest;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/20 21:28
 * @Copyright Copyright © 2019 深圳才华有限网络科技股份有限公司. All rights reserved.
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().start();
        new Thread(new RunableTest()).start();
        Thread thread = new ThreadTest();
        thread.start();
        thread.join();
    }
}

package com.mine.utils.threadUtil;

import com.mine.utils.concurrentutil.ThreadPoolUtil;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/11/11 11:46
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ThreadLocalTest {

        private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "你好啊");

    public static void main(String[] args) {
        String str = threadLocal.get();
        threadLocal.set("xxx");
        threadLocal.remove();

        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(() -> {
            System.out.println(threadLocal.get()+"...3");
            threadLocal.set( "你好啊1");
            System.out.println(threadLocal.get());
        });

        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(() -> {
            System.out.println(threadLocal.get()+"...2");
            threadLocal.set( "你好啊2");
            System.out.println(threadLocal.get());
        });

        ThreadPoolUtil.THREAD_POOL_EXECUTOR.shutdown();
    }
}

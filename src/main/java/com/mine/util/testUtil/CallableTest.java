package com.mine.util.testUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/20 0020 22:42
 * @Copyright Copyright © 2019 深圳才华有限网络科技股份有限公司. All rights reserved.
 */
public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "你好啊";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new CallableTest());
        new Thread(task).start();
        String res = task.get();
    }
}

package com.mine.utils.concurrentutil;

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
public class CallableUtil implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "你好啊";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //注意泛型的使用
        FutureTask<String> task = new FutureTask<>(new CallableUtil());
        new Thread(task).start();
        String res = task.get();
    }
}

package com.mine.util.threadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程池工具类
 * Created by zhongcy on 2016/9/22.
 */
public class ThreadPoolUtil {

    /**
     * CPU的核数
      */
    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService DATA_SYNC = Executors.newFixedThreadPool(CPU_NUM * 2 + 1, new
            CommonThreadFactory("DATA_SYNC"));

    private static class CommonThreadFactory implements ThreadFactory {

        private static int index = 0;

        final String prefix;

        CommonThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + (index++));
        }
    }

}
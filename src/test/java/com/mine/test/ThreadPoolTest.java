package com.mine.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import org.junit.Test;
import org.springframework.util.StopWatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mine.BaseJunit4Test;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/17.
 */
@Slf4j
public class ThreadPoolTest extends BaseJunit4Test {

    /**
     * 创建线程池
     */
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 32, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
                new ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat("ThreadPool-name-%d")
                        .setUncaughtExceptionHandler((t, e) -> log.info("There are Exception,t={},e={}", t, e)).build());

        int count = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.add("dddd");
                    log.info("增加一次，time={},threadName={}", System.currentTimeMillis(), Thread.currentThread().getName());
                }
            });
            if (i == 99999) {
                stopWatch.stop();
                log.info("总共耗时time={}", stopWatch.prettyPrint());
            }
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            System.out.println("你好啊");
        });

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "你好啊";
            }
        });

        String str = future.get();
    }
}

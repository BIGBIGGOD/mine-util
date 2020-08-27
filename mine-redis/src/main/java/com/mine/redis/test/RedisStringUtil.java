//package com.mine.redis.test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import net.sf.json.JSONArray;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Response;
//
///**
// * @author jiangqingdong
// * @version v1.0
// * @Description
// * @DATE 2019/8/27 16:25
// * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
// */
//@Component
//public class RedisStringUtil {
//
//    /**
//     * 自动注入关于操作redis的bean
//     * 在redis的xml配置文件中给bean配置的是JedisExecutor类，
//     * 需要注意JedisExecutor是RedisExecutor的实现类，
//     * 所以在这里我们声明的是RedisExecutor
//     * 此外因为类与其实现类的名称不一致，需要使用@Qualifier来引入对应的bean
//     */
//    @Autowired
//    @Qualifier("coreRedisExecutor")
//    private RedisExecutor redisExecutor;
//
//    /**
//     * 默认设为30分钟
//     */
//    private static final int EXPIRE = 60 * 30;
//    /**
//     * redis操作key
//     */
//    private static final String KEY_STRING = "redis:util:save:string:";
//
//    /**
//     * 总共有四种关于封装方法的调用方式
//     */
//
//    /**
//     * 第一种：普通调用无返回值
//     *
//     * @return
//     */
//    public void save1(String json) {
//
//        if (StringUtils.isBlank(json)) {
//            return;
//        }
//
//        redisExecutor.doInRedis(instance -> {
//            instance.set(KEY_STRING + json, json);
//            instance.expire(KEY_STRING, EXPIRE);
//        });
//    }
//
//    /**
//     * 第二种：有返回值
//     *
//     * @return
//     */
//    public boolean save2(String json) {
//
//        if (StringUtils.isBlank(json)) {
//            return false;
//        }
//
//        return redisExecutor.doInRedis(instance -> {
//            instance.set(KEY_STRING, json);
//            instance.expire(KEY_STRING, EXPIRE);
//            return true;
//        });
//    }
//
//    /**
//     * 第三种：redis管道pipeline的使用（无返回值）
//     *
//     * @return
//     */
//    public void save3(String json) {
//
//        if (StringUtils.isBlank(json)) {
//            return;
//        }
//
//        redisExecutor.doInPipeline(pipeline -> {
//            JSONArray jsonArray = JSONArray.fromObject(json);
//            for (Object obj : jsonArray) {
//                String key = KEY_STRING + obj.toString();
//                pipeline.set(key, obj.toString());
//                pipeline.expire(key, EXPIRE);
//            }
//            pipeline.sync();
//            return true;
//        });
//    }
//
//    /**
//     * 第四种：redis管道pipeline的使用（有返回值）
//     *
//     * @return
//     */
//    public List<Object> query1(String json) {
//
//        if (StringUtils.isBlank(json)) {
//            return new ArrayList<>();
//        }
//
//        return redisExecutor.doInPipeline(pipeline -> {
//            JSONArray jsonArray = JSONArray.fromObject(json);
//            for (Object obj : jsonArray) {
//                String key = KEY_STRING + obj.toString();
//                pipeline.get(key);
//            }
//            //当查询key所对应的value为空的时候，查询出来的list结果集合中同样会存在一个空的对象，这个时候list的size不为0，但是内部元素为空
//            List<Object> objectList = pipeline.syncAndReturnAll();
//            return objectList;
//        });
//    }
//
//    /**
//     * 第四种：redis管道pipeline的使用（有返回值）
//     *
//     * @return
//     */
//    public Map<String, Response<String>> query2(String json) {
//
//        if (StringUtils.isBlank(json)) {
//            return null;
//        }
//
//        return redisExecutor.doInPipeline(pipeline -> {
//            JSONArray jsonArray = JSONArray.fromObject(json);
//            //建立一个map集合接收查询结果，注意只有在执行了pipeline的同步方法syncAndReturnAll()之后，此处的Response才会被赋值
//            Map<String, Response<String>> responses = new HashMap<>(jsonArray.size());
//            for (Object obj : jsonArray) {
//                String key = KEY_STRING + obj.toString();
//                responses.put(key, pipeline.get(key));
//            }
//            pipeline.syncAndReturnAll();
//            return responses;
//        });
//    }
//
//    /**
//     * 普通查询
//     *
//     * @param json
//     * @return
//     */
//    public String query3(String json) {
//        if (StringUtils.isBlank(json)) {
//            return null;
//        }
//        return redisExecutor.doInRedis(instance -> {
//            String key = KEY_STRING + json;
//            return instance.get(key);
//        });
//    }
//
//    private JedisRunnable getJedisRunnable() {
//        // 创建一个JedisRunnable对象，其内部有一个run方法，需要一个jedis参数，
//        // 这也是为什么使用lambda表达式的时候会有一个instance对象的原因
//        JedisRunnable runnable = new JedisRunnable() {
//            @Override
//            public void run(Jedis instance) throws Exception {
//                System.out.println("你好啊");
//            }
//        };
//        return runnable;
//    }
//}

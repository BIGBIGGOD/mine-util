package com.mine.util.redisUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mine.redis.RedisExecutor;
import com.mine.util.dateUtil.DateUtil;
import net.sf.json.JSONObject;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/28 14:17
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
public class RedisHashUtil {
    @Autowired
    @Qualifier("coreRedisExecutor")
    private RedisExecutor redisExecutor;

    /**
     * 默认设为30分钟
     */
    private static final int EXPIRE = 60 * 30;
    /**
     * redis操作key
     */
    private static final String KEY_HASH = "redis:util:save:hash:";

    /**
     * 以hset和hmset方式存储
     * 此处数据直接写死测试
     *
     * @return
     */
    public boolean save1() {

        return redisExecutor.doInRedis(instance -> {

            //数字类型
            Integer num = 444;
            //时间类型
            Date date = new Date();
            //布尔类型
            Boolean del = false;
            //String类型
            String str = "测试";

            //以hset方式存储
            JSONObject jsonObject = new JSONObject();
            jsonObject.element("num", num == null ? "" : num.toString());
            jsonObject.element("date", date == null ? "" : DateUtil.getDateStr(date));
            jsonObject.element("del", del == null ? "" : del.toString());
            jsonObject.element("str", str == null ? "" : str.toString());
            instance.hset(KEY_HASH, "测试", jsonObject.toString());

            //以hmset方式存储
            Map<String, String> map = new HashMap<>();
            map.put("num", num == null ? "" : num.toString());
            map.put("date", date == null ? "" : DateUtil.getDateStr(date));
            map.put("del", del == null ? "" : del.toString());
            map.put("str", StringUtils.trimToEmpty(str));
            instance.hmset(KEY_HASH, map);
            return true;
        });
    }

    /**
     * 查询指定key的value
     *
     * @return
     */
    public String query1() {
        return redisExecutor.doInRedis(instance -> {
            if (!instance.exists(KEY_HASH)) {
                return null;
            }

            //查询以hset方式存储的value
            if (!instance.hexists(KEY_HASH, "测试")) {
                return null;
            }
            String json = instance.hget(KEY_HASH, "测试");
            JSONObject jsonObject = JSONObject.fromObject(json);

            //查询以hmset方式存储的value
            List<String> result = instance.hmget(KEY_HASH, "num", "date", "del", "str");
            Integer num = "".equals(result.get(0)) ? null : Integer.valueOf(result.get(0));
            Date date = "".equals(result.get(1)) ? null : DateUtil.getDate(result.get(1));
            Boolean del = "".equals(result.get(2)) ? null : Boolean.valueOf(result.get(2));
            String str = result.get(3);
            System.out.println("num = " + num + "date = " + date + "del = " + del + "str = " + str);
            return null;
        });
    }

    /**
     * 为哈希表中指定字段的整数值加上增量increment
     *
     * @return
     */
    public boolean update1(Integer increment) {

        if (increment == null) {
            return false;
        }

        return redisExecutor.doInRedis(instance -> {
            if (!instance.hexists(KEY_HASH, "num")) {
                return false;
            }

            instance.hincrBy(KEY_HASH, "num", increment);
            return true;
        });
    }

    /**
     * 查询队列长度
     *
     * @return
     */
    public Long query2() {
        return redisExecutor.doInRedis(instance -> {
            if (!instance.exists(KEY_HASH)) {
                return 0L;
            }
            return instance.hlen(KEY_HASH);
        });
    }


}

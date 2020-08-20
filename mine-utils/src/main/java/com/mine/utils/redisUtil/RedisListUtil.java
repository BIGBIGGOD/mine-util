package com.mine.utils.redisUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mine.redis.RedisExecutor;
import net.sf.json.JSONArray;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/28 14:17
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
public class RedisListUtil {
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
    private static final String KEY_LIST = "redis:util:save:list:";

    /**
     * 往队列中插入单个数据
     *
     * @param json
     * @return
     */
    public boolean save1(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        return redisExecutor.doInRedis(instance -> {
            //从尾部添加数据，相反如果为lpush则从头部往队列插入数据，如果为lpushx则只为已存在的队列添加value
            instance.rpush(KEY_LIST, json);
            instance.expire(KEY_LIST, EXPIRE);
            return true;
        });
    }

    /**
     * 往队列中插入多个数据
     *
     * @param json
     * @return
     */
    public boolean save2(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        return redisExecutor.doInRedis(instance -> {
            //将json转化成String类型的数组
            JSONArray jsonArray = JSONArray.fromObject(json);
            String[] arr = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                arr[i] = jsonArray.get(i).toString();
            }

            instance.rpush(KEY_LIST, arr);
            instance.expire(KEY_LIST, EXPIRE);
            return true;
        });
    }

    /**
     * 查询指定索引位置的value，索引从0开始
     *
     * @param index
     * @return
     */
    public String query1(Integer index) {
        if (index == null) {
            return null;
        }
        return redisExecutor.doInRedis(instance -> {
            if (!instance.exists(KEY_LIST)) {
                return null;
            }
            return instance.lindex(KEY_LIST, index);
        });
    }

    /**
     * 查询并从队列中移除
     *
     * @return
     */
    public String query2() {
        return redisExecutor.doInRedis(instance -> {
            if (!instance.exists(KEY_LIST)) {
                return null;
            }
            return instance.lpop(KEY_LIST);
        });
    }

    /**
     * 查询队列长度
     *
     * @return
     */
    public Long query3() {
        return redisExecutor.doInRedis(instance -> {
            if (!instance.exists(KEY_LIST)) {
                return 0L;
            }
            return instance.llen(KEY_LIST);
        });
    }
}

package com.mine.utils.shardingutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;


/**
 * 分表路由算法
 * 
 * @author IceLiang
 * @Description 
 * @version V1.0
 * @Date 2019年8月8日 下午4:50:29 
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ShardingKeyAlgorithm implements SingleKeyTableShardingAlgorithm<String>{

    /**
     * sql 中关键字 匹配符为 =的时候，表的路由函数
     */
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        int hashCode = HashUtil.getHash(shardingValue.getValue());
        int index = Math.abs(hashCode % availableTargetNames.size());
        ArrayList<String> candidateCollect = new ArrayList<>(availableTargetNames);
        return candidateCollect.get(index);

    }

    /**
     * sql 中关键字 匹配符为 in 的时候，表的路由函数
     */
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (String value : shardingValue.getValues()) {
            int hashCode = HashUtil.getHash(value);
            int index = Math.abs(hashCode % availableTargetNames.size());
            ArrayList<String> candidateCollect = new ArrayList<>(availableTargetNames);
            String item = candidateCollect.get(index);
            result.add(item);
        }
        return result;
    }

    /**
     * sql 中关键字 匹配符为 between的时候，表的路由函数
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        result.addAll(availableTargetNames);
        return result;
    }
    
}


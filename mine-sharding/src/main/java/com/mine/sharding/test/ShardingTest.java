package com.mine.sharding.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.sharding.common.BaseShardingTest;
import com.mine.sharding.mapper.ShardingTestDoMapper;
import com.mine.sharding.model.ShardingTestDo;
import com.mine.sharding.model.ShardingTestDoExample;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/26 0026 16:19
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class ShardingTest extends BaseShardingTest {

    @Autowired
    private ShardingTestDoMapper shardingTestDoMapper;

    @Test
    public void testInsert() {
        ShardingTestDo shardingTestDo = new ShardingTestDo();
        String businessId = "5555";
        shardingTestDo.setBusinessId(businessId);
        shardingTestDo.setUserId("aaa");
        shardingTestDo.setCreateTime(new Date());
        int res = shardingTestDoMapper.insert(shardingTestDo);
        log.info("插入结果res={}", res);
        ShardingTestDoExample example = new ShardingTestDoExample();
        example.createCriteria().andBusinessIdEqualTo(businessId);
        List<ShardingTestDo> shardingTestDoList = shardingTestDoMapper.selectByExample(example);
        log.info("查询结果shardingTestDoList={}", shardingTestDoList);
    }
}

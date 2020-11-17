package com.mine.sharding.mapper;

import com.mine.sharding.model.ShardingTestDo;
import com.mine.sharding.model.ShardingTestDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ShardingTestDoMapper {
    int countByExample(ShardingTestDoExample example);

    int deleteByExample(ShardingTestDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShardingTestDo record);

    int insertSelective(ShardingTestDo record);

    List<ShardingTestDo> selectByExampleWithRowbounds(ShardingTestDoExample example, RowBounds rowBounds);

    List<ShardingTestDo> selectByExample(ShardingTestDoExample example);

    ShardingTestDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShardingTestDo record, @Param("example") ShardingTestDoExample example);

    int updateByExample(@Param("record") ShardingTestDo record, @Param("example") ShardingTestDoExample example);

    int updateByPrimaryKeySelective(ShardingTestDo record);

    int updateByPrimaryKey(ShardingTestDo record);
}
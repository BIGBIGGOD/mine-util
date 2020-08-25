package com.mine.domain.mapper;

import com.mine.domain.model.UserDo;
import com.mine.domain.model.UserDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserDoMapper {
    int countByExample(UserDoExample example);

    int deleteByExample(UserDoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExampleWithRowbounds(UserDoExample example, RowBounds rowBounds);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}
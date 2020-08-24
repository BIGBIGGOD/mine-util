package com.mine.security.mapper;

import com.mine.security.model.UserLoginLogDo;
import com.mine.security.model.UserLoginLogDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserLoginLogDoMapper {
    int countByExample(UserLoginLogDoExample example);

    int deleteByExample(UserLoginLogDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLoginLogDo record);

    int insertSelective(UserLoginLogDo record);

    List<UserLoginLogDo> selectByExampleWithRowbounds(UserLoginLogDoExample example, RowBounds rowBounds);

    List<UserLoginLogDo> selectByExample(UserLoginLogDoExample example);

    UserLoginLogDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLoginLogDo record, @Param("example") UserLoginLogDoExample example);

    int updateByExample(@Param("record") UserLoginLogDo record, @Param("example") UserLoginLogDoExample example);

    int updateByPrimaryKeySelective(UserLoginLogDo record);

    int updateByPrimaryKey(UserLoginLogDo record);
}
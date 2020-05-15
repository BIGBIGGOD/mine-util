package com.mine.mapper;

import com.mine.model.UserPermissionDo;
import com.mine.model.UserPermissionDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserPermissionDoMapper {
    int countByExample(UserPermissionDoExample example);

    int deleteByExample(UserPermissionDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPermissionDo record);

    int insertSelective(UserPermissionDo record);

    List<UserPermissionDo> selectByExampleWithRowbounds(UserPermissionDoExample example, RowBounds rowBounds);

    List<UserPermissionDo> selectByExample(UserPermissionDoExample example);

    UserPermissionDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserPermissionDo record, @Param("example") UserPermissionDoExample example);

    int updateByExample(@Param("record") UserPermissionDo record, @Param("example") UserPermissionDoExample example);

    int updateByPrimaryKeySelective(UserPermissionDo record);

    int updateByPrimaryKey(UserPermissionDo record);
}
package com.mine.security.mapper;

import com.mine.security.model.UserPermissionRelationDo;
import com.mine.security.model.UserPermissionRelationDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserPermissionRelationDoMapper {
    int countByExample(UserPermissionRelationDoExample example);

    int deleteByExample(UserPermissionRelationDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPermissionRelationDo record);

    int insertSelective(UserPermissionRelationDo record);

    List<UserPermissionRelationDo> selectByExampleWithRowbounds(UserPermissionRelationDoExample example, RowBounds rowBounds);

    List<UserPermissionRelationDo> selectByExample(UserPermissionRelationDoExample example);

    UserPermissionRelationDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserPermissionRelationDo record, @Param("example") UserPermissionRelationDoExample example);

    int updateByExample(@Param("record") UserPermissionRelationDo record, @Param("example") UserPermissionRelationDoExample example);

    int updateByPrimaryKeySelective(UserPermissionRelationDo record);

    int updateByPrimaryKey(UserPermissionRelationDo record);
}
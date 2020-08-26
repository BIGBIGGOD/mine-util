package com.mine.security.mapper;

import com.mine.security.model.UserRoleRelationDo;
import com.mine.security.model.UserRoleRelationDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserRoleRelationDoMapper {
    int countByExample(UserRoleRelationDoExample example);

    int deleteByExample(UserRoleRelationDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRelationDo record);

    int insertSelective(UserRoleRelationDo record);

    List<UserRoleRelationDo> selectByExampleWithRowbounds(UserRoleRelationDoExample example, RowBounds rowBounds);

    List<UserRoleRelationDo> selectByExample(UserRoleRelationDoExample example);

    UserRoleRelationDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRoleRelationDo record, @Param("example") UserRoleRelationDoExample example);

    int updateByExample(@Param("record") UserRoleRelationDo record, @Param("example") UserRoleRelationDoExample example);

    int updateByPrimaryKeySelective(UserRoleRelationDo record);

    int updateByPrimaryKey(UserRoleRelationDo record);
}
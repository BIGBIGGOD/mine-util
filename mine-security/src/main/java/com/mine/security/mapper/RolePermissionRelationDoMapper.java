package com.mine.security.mapper;

import com.mine.domain.model.RolePermissionRelationDo;
import com.mine.domain.model.RolePermissionRelationDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RolePermissionRelationDoMapper {
    int countByExample(RolePermissionRelationDoExample example);

    int deleteByExample(RolePermissionRelationDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionRelationDo record);

    int insertSelective(RolePermissionRelationDo record);

    List<RolePermissionRelationDo> selectByExampleWithRowbounds(RolePermissionRelationDoExample example, RowBounds rowBounds);

    List<RolePermissionRelationDo> selectByExample(RolePermissionRelationDoExample example);

    RolePermissionRelationDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RolePermissionRelationDo record, @Param("example") RolePermissionRelationDoExample example);

    int updateByExample(@Param("record") RolePermissionRelationDo record, @Param("example") RolePermissionRelationDoExample example);

    int updateByPrimaryKeySelective(RolePermissionRelationDo record);

    int updateByPrimaryKey(RolePermissionRelationDo record);
}
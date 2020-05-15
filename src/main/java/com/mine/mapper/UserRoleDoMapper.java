package com.mine.mapper;

import com.mine.model.UserRoleDo;
import com.mine.model.UserRoleDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserRoleDoMapper {
    int countByExample(UserRoleDoExample example);

    int deleteByExample(UserRoleDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleDo record);

    int insertSelective(UserRoleDo record);

    List<UserRoleDo> selectByExampleWithRowbounds(UserRoleDoExample example, RowBounds rowBounds);

    List<UserRoleDo> selectByExample(UserRoleDoExample example);

    UserRoleDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRoleDo record, @Param("example") UserRoleDoExample example);

    int updateByExample(@Param("record") UserRoleDo record, @Param("example") UserRoleDoExample example);

    int updateByPrimaryKeySelective(UserRoleDo record);

    int updateByPrimaryKey(UserRoleDo record);
}
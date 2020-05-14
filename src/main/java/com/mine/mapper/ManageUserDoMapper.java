package com.mine.mapper;

import com.mine.model.ManageUserDo;
import com.mine.model.ManageUserDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ManageUserDoMapper {
    int countByExample(ManageUserDoExample example);

    int deleteByExample(ManageUserDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ManageUserDo record);

    int insertSelective(ManageUserDo record);

    List<ManageUserDo> selectByExampleWithRowbounds(ManageUserDoExample example, RowBounds rowBounds);

    List<ManageUserDo> selectByExample(ManageUserDoExample example);

    ManageUserDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ManageUserDo record, @Param("example") ManageUserDoExample example);

    int updateByExample(@Param("record") ManageUserDo record, @Param("example") ManageUserDoExample example);

    int updateByPrimaryKeySelective(ManageUserDo record);

    int updateByPrimaryKey(ManageUserDo record);
}
package com.mine.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Repository
public interface UserDoExtendMapper {

    @Select("select name from t_user")
    List<String> getName();

    @Insert("insert into t_user (name) value (#{name})")
    void addUser(@Param("name") String name);

}

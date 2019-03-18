package com.mine.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Repository
@Mapper
public interface CeShiMapper {

    @Select("select username from user")
    List<String> getUsername ();

}

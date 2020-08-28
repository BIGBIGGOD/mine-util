//package com.mine.mybatis.mapper;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
///**
// *
// * @author jiangqd
// * @date 2019/1/12
// */
//@Mapper
//public interface UserExtendMapper {
//
//    @Select("select name from t_user")
//    List<String> getName();
//
//    @Insert("insert into t_user (name) value (#{name})")
//    void addUser(@Param("name") String name);
//
//}

package com.mine.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mine.security.model.UserPermissionDo;
import com.mine.security.model.UserPermissionRelationDo;
import com.mine.security.model.UserRoleDo;
import com.mine.security.model.UserRoleRelationDo;


@Repository
public interface UserRoleRelationDoExtendMapper {

    /**
     * 批量插入用户角色关系
     *
     * @param userRoleRelationDoList 用户与角色对应集合
     * @return res
     */
    int insertUserRoleRelationDoList(@Param("list") List<UserRoleRelationDo> userRoleRelationDoList);

    /**
     * 根据用户获取所有角色
     */
    List<UserRoleDo> getRoleListById(@Param("userId") Long userId);

    /**
     * 获取用户所有角色权限
     */
    List<UserPermissionDo> getRolePermissionList(@Param("userId") Long userId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UserPermissionDo> getPermissionList(@Param("userId") Long userId);

    /**
     * 批量插入+-权限
     *
     * @param list
     * @return
     */
    @Insert(value =
            {"<script>",
                    "INSERT INTO t_user_permission_relation (user_id, permission_id, type) VALUES\n" +
                            "        <foreach collection=\"list\" separator=\",\" item=\"item\" index=\"index\">\n" +
                            "            (#{item.userId,jdbcType=BIGINT},\n" +
                            "            #{item.permissionId,jdbcType=BIGINT},\n" +
                            "            #{item.type,jdbcType=INTEGER})\n" +
                            "        </foreach>" +
                            "</script>"})
    int insertUserPermissionRelationDoList(@Param("list") List<UserPermissionRelationDo> list);
}
package com.mine.security.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mine.security.entity.SecurityUserRegisterParam;
import com.mine.security.entity.UpdateUserPasswordParam;
import com.mine.security.model.ManageUserDo;
import com.mine.security.model.UserPermissionDo;
import com.mine.security.model.UserRoleDo;

/**
 * 后台管理员Service
 *
 * @author macro
 * @date 2018/4/26
 */
public interface SecurityUserService {
    /**
     * 根据用户名获取用户信息
     */
    ManageUserDo getUserByUsername(String username);

    /**
     * 注册功能
     */
    ManageUserDo register(SecurityUserRegisterParam SecurityUserRegisterParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     * @return res
     */
    String refreshToken(String oldToken);


    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return res
     */
    ManageUserDo getManageUserDo(Long userId);

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param name   用户名
     * @param offset 初始
     * @param limit  限制
     * @return res
     */
    List<ManageUserDo> getManageUserDoByName(String name, Integer offset, Integer limit);

    /**
     * 修改指定用户信息
     *
     * @param userId       用户id
     * @param manageUserDo 用户实体类
     * @return res
     */
    int updateManageUserDoById(Long userId, ManageUserDo manageUserDo);

    /**
     * 删除指定用户
     *
     * @param userId 用户id
     * @return res
     */
    int deleteManageUser(Long userId);

    /**
     * 修改用户角色关系
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return res
     */
    @Transactional(rollbackFor = Exception.class)
    int updateUserRoleRelation(Long userId, List<Long> roleIds);

    /**
     * 获取用户对应角色集合
     *
     * @param userId 用户id
     * @return res
     */
    List<UserRoleDo> getRoleList(Long userId);

    /**
     * 修改用户的+-权限
     *
     * @param userId        用户id
     * @param permissionIds 权限id集合
     * @return res
     */
    @Transactional(rollbackFor = Exception.class)
    int updatePermission(Long userId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param userId
     * @return res
     */
    List<UserPermissionDo> getUserPermissionList(Long userId);


    /**
     * 修改密码
     *
     * @param updatePasswordParam 修改参数
     * @return res
     */
    int updatePassword(UpdateUserPasswordParam updatePasswordParam);
}

package com.mine.util.securityutil.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Maps;
import com.mine.controller.BaseController;
import com.mine.enums.CommonEnum;
import com.mine.model.ManageUserDo;
import com.mine.model.UserRoleDo;
import com.mine.util.securityutil.config.SecurityParams;
import com.mine.util.securityutil.entity.SecurityUserLoginParam;
import com.mine.util.securityutil.entity.SecurityUserRegisterParam;
import com.mine.util.securityutil.entity.UpdateUserPasswordParam;
import com.mine.util.securityutil.service.SecurityUserService;
import com.mine.util.shiroUtil.User;


/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/11/1 16:16
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@RestController
@RequestMapping(value = "/user")
public class SecurityController extends BaseController {

    @Autowired
    private SecurityUserService securityUserService;

    @Autowired
    private SecurityParams securityParams;

    @PreAuthorize("hasRole('权限1')")
    @RequestMapping(value = "test1")
    public User test1(@RequestParam(value = "str") String str) {
        User user = new User();
        user.setName("测试cshi");
        user.setPassword(str);
        return user;
    }

    /**
     * 用户注册
     *
     * @param securityAdminParam 注册参数
     * @param result             参数校验
     * @return res
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(SecurityUserRegisterParam securityAdminParam, BindingResult result) {
        // umsAdminParam是业务实体类，umsAdmin是db层实体类其中密码是加密之后的
        ManageUserDo manageUserDo = securityUserService.register(securityAdminParam);
        if (manageUserDo == null) {
            return failResponse(CommonEnum.FAILURE);
        }
        return successResponse(manageUserDo);
    }

    /**
     * 登录并返回token
     *
     * @param securityUserLoginParam 登录参数
     * @return res
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(SecurityUserLoginParam securityUserLoginParam) {
        String token = securityUserService.login(securityUserLoginParam.getUsername(), securityUserLoginParam.getPassword());
        if (token == null) {
            return failResponse(CommonEnum.FAILURE);
        }
        Map<String, String> tokenMap = Maps.newHashMap();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", securityParams.getTokenHead());
        return successResponse(tokenMap);
    }

    /**
     * 刷新token
     */
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public Result refreshToken(HttpServletRequest request) {
        // 1.从request的Header中获取token 2.截取head同等长度的字符形成新的一个token 3.检验是否可以刷新token 4.重新生成Claims 5.根据Claims由jwt重新生成token并返回
        String token = request.getHeader(securityParams.getTokenHeader());
        String refreshToken = securityUserService.refreshToken(token);
        if (refreshToken == null) {
            return failResponse(CommonEnum.FAILURE);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", securityParams.getTokenHead());
        return successResponse(tokenMap);
    }

    /**
     * 获取当前登录用户信息
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Result getUserInfo(Principal principal) {
        String username = principal.getName();
        ManageUserDo manageUserDo = securityUserService.getUserByUsername(username);
        Map<String, Object> data = Maps.newHashMap();
        data.put("username", manageUserDo.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", manageUserDo.getIcon());
        return successResponse(data);
    }

    /**
     *  登出功能
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        return successResponse();
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     */
    @RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
    public Result getUserByName(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer offset,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer limit) {
        return successResponse(securityUserService.getManageUserDoByName(name, offset, limit));
    }

    /**
     * 获取指定用户信息
     *
     * @param userId 用户id
     * @return res
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Result getManageUserDo(@PathVariable Long userId) {
        ManageUserDo manageUserDo = securityUserService.getManageUserDo(userId);
        return successResponse(manageUserDo);
    }

    /**
     * 修改指定用户信息
     */
    @RequestMapping(value = "/updateManageUserDoById/{userId}", method = RequestMethod.POST)
    public Result updateManageUserDoById(@PathVariable Long userId, @RequestBody ManageUserDo manageUserDo) {
        int count = securityUserService.updateManageUserDoById(userId, manageUserDo);
        if (count > 0) {
            return successResponse(count);
        }
        return failResponse(CommonEnum.FAILURE);
    }

    /**
     * 修改指定用户密码
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result updatePassword(@RequestBody UpdateUserPasswordParam updatePasswordParam) {
        int status = securityUserService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return successResponse(status);
        } else if (status == -1) {
            return failResponse(CommonEnum.PARAMS_ERROR);
        } else if (status == -2) {
            return failResponse(CommonEnum.DATA_NOT_EXISTS);
        } else if (status == -3) {
            return failResponse(CommonEnum.OLD_PARAMS_ERR);
        } else {
            return failResponse(CommonEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 删除指定用户信息
     */
    @RequestMapping(value = "/deleteManageUser/{userId}", method = RequestMethod.POST)
    public Result deleteManageUser(@PathVariable Long userId) {
        int count = securityUserService.deleteManageUser(userId);
        if (count > 0) {
            return successResponse(count);
        }
        return failResponse(CommonEnum.SYSTEM_ERROR);
    }

    /**
     * 给用户分配角色
     */
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public Result updateRole(@RequestParam("adminId") Long userId,
                             @RequestParam("roleIds") List<Long> roleIds) {
        int count = securityUserService.updateUserRoleRelation(userId, roleIds);
        if (count >= 0) {
            return successResponse(count);
        }
        return failResponse(CommonEnum.SYSTEM_ERROR);
    }

    /**
     * 获取指定用户的角色
     */
    @RequestMapping(value = "/role/{userId}", method = RequestMethod.GET)
    public Result getRoleList(@PathVariable Long userId) {
        List<UserRoleDo> roleList = securityUserService.getRoleList(userId);
        return successResponse(roleList);
    }

    /**
     * 给用户分配+-权限
     */
    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    public Result updatePermission(@RequestParam Long userId,
                                   @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = securityUserService.updatePermission(userId, permissionIds);
        if (count > 0) {
            return successResponse(count);
        }
        return failResponse(CommonEnum.SYSTEM_ERROR);
    }

    /**
     * 获取用户所有权限（包括+-权限）
     */
    @RequestMapping(value = "/permission/{userId}", method = RequestMethod.GET)
    public Result getPermissionList(@PathVariable Long userId) {
        return successResponse(securityUserService.getUserPermissionList(userId));
    }
}

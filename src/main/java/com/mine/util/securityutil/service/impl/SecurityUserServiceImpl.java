package com.mine.util.securityutil.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.mine.enums.CommonEnum;
import com.mine.mapper.*;
import com.mine.model.*;
import com.mine.util.securityutil.JwtTokenUtil;
import com.mine.util.securityutil.entity.SecurityUserRegisterParam;
import com.mine.util.securityutil.entity.UpdateUserPasswordParam;
import com.mine.util.securityutil.exception.SecurityHandleException;
import com.mine.util.securityutil.service.SecurityUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * SecurityUserService实现类
 * Created by macro on 2018/4/26.
 */
@Service
@Slf4j
public class SecurityUserServiceImpl implements SecurityUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private ManageUserDoMapper manageUserDoMapper;
    @Autowired
    private UserRoleRelationDoMapper userRoleRelationDoMapper;
    @Autowired
    private UserRoleRelationDoExtendMapper userRoleRelationDoExtendMapper;
    @Autowired
    private UserPermissionRelationDoMapper userPermissionRelationDoMapper;
    @Autowired
    private UserLoginLogDoMapper userLoginLogDoMapper;

    @Override
    public ManageUserDo getUserByUsername(String username) {
        ManageUserDoExample example = new ManageUserDoExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<ManageUserDo> manageUserDoList = manageUserDoMapper.selectByExample(example);
        if (manageUserDoList != null && manageUserDoList.size() > 0) {
            return manageUserDoList.get(0);
        }
        return null;
    }

    @Override
    public ManageUserDo register(SecurityUserRegisterParam securityUserRegisterParam) {
        ManageUserDo manageUserDo = new ManageUserDo();
        BeanUtils.copyProperties(securityUserRegisterParam, manageUserDo);
        manageUserDo.setCreateTime(new Date());
        manageUserDo.setStatus(1);
        //查询是否有相同用户名存在
        ManageUserDoExample example = new ManageUserDoExample();
        example.createCriteria().andUsernameEqualTo(manageUserDo.getUsername());
        List<ManageUserDo> manageUserDoList = manageUserDoMapper.selectByExample(example);
        if (manageUserDoList.size() > 0) {
            throw new SecurityHandleException(CommonEnum.EXIST_USERNAME);
        }
        //将密码进行加密存储
        String encodePassword = passwordEncoder.encode(manageUserDo.getPassword());
        manageUserDo.setPassword(encodePassword);
        manageUserDoMapper.insert(manageUserDo);
        return manageUserDo;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            //根据用户名查询用户信息
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //比较用户密码
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new SecurityHandleException(CommonEnum.ERROR_PWD);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        ManageUserDo manageUserDo = getUserByUsername(username);
        UserLoginLogDo loginLog = new UserLoginLogDo();
        loginLog.setUserId(manageUserDo.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        userLoginLogDoMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        ManageUserDo record = new ManageUserDo();
        record.setLoginTime(new Date());
        ManageUserDoExample example = new ManageUserDoExample();
        example.createCriteria().andUsernameEqualTo(username);
        manageUserDoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public ManageUserDo getManageUserDo(Long userId) {
        return manageUserDoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<ManageUserDo> getManageUserDoByName(String name, Integer offset, Integer limit) {
        PageHelper.startPage(offset, limit);
        ManageUserDoExample example = new ManageUserDoExample();
        ManageUserDoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andUsernameLike("%" + name + "%");
            example.or(example.createCriteria().andNickNameLike("%" + name + "%"));
        }
        return manageUserDoMapper.selectByExample(example);
    }

    @Override
    public int updateManageUserDoById(Long userId, ManageUserDo manageUserDo) {
        manageUserDo.setId(userId);
        //密码已经加密处理，需要单独修改
        manageUserDo.setPassword(null);
        return manageUserDoMapper.updateByPrimaryKeySelective(manageUserDo);
    }

    @Override
    public int deleteManageUser(Long userId) {
        return manageUserDoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int updateUserRoleRelation(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UserRoleRelationDoExample userRoleRelationDoExample = new UserRoleRelationDoExample();
        userRoleRelationDoExample.createCriteria().andUserIdEqualTo(userId);
        userRoleRelationDoMapper.deleteByExample(userRoleRelationDoExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UserRoleRelationDo> list = Lists.newArrayList();
            for (Long roleId : roleIds) {
                UserRoleRelationDo userRoleRelationDo = new UserRoleRelationDo();
                userRoleRelationDo.setUserId(userId);
                userRoleRelationDo.setRoleId(roleId);
                list.add(userRoleRelationDo);
            }
            userRoleRelationDoExtendMapper.insertUserRoleRelationDoList(list);
        }
        return count;
    }

    @Override
    public List<UserRoleDo> getRoleList(Long userId) {
        return userRoleRelationDoExtendMapper.getRoleListById(userId);
    }

    @Override
    public int updatePermission(Long userId, List<Long> permissionIds) {
        //删除原所有权限关系
        UserPermissionRelationDoExample relationExample = new UserPermissionRelationDoExample();
        relationExample.createCriteria().andUserIdEqualTo(userId);
        userPermissionRelationDoMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<UserPermissionDo> permissionList = userRoleRelationDoExtendMapper.getRolePermissionList(userId);
        List<Long> rolePermissionList = permissionList.stream().map(UserPermissionDo::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<UserPermissionRelationDo> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(userId, 1, addPermissionIdList));
            relationList.addAll(convert(userId, -1, subPermissionIdList));
            return userRoleRelationDoExtendMapper.insertUserPermissionRelationDoList(relationList);
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UserPermissionRelationDo> convert(Long userId, Integer type, List<Long> permissionIdList) {
        return permissionIdList.stream().map(permissionId -> {
            UserPermissionRelationDo relation = new UserPermissionRelationDo();
            relation.setUserId(userId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserPermissionDo> getUserPermissionList(Long userId) {
        return userRoleRelationDoExtendMapper.getPermissionList(userId);
    }

    @Override
    public int updatePassword(UpdateUserPasswordParam param) {
        ManageUserDoExample example = new ManageUserDoExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<ManageUserDo> userList = manageUserDoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return -2;
        }
        ManageUserDo manageUserDo = userList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), manageUserDo.getPassword())) {
            return -3;
        }
        manageUserDo.setPassword(passwordEncoder.encode(param.getNewPassword()));
        manageUserDoMapper.updateByPrimaryKey(manageUserDo);
        return 1;
    }
}

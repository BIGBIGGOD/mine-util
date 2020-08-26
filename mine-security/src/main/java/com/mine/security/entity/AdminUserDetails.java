package com.mine.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mine.security.model.ManageUserDo;
import com.mine.security.model.UserPermissionDo;

/**
 * Spring Security需要的用户详情
 *
 * @author macro
 * @date 2018/4/26
 */
public class AdminUserDetails implements UserDetails {
    private ManageUserDo manageUserDo;
    private List<UserPermissionDo> permissionList;

    public AdminUserDetails(ManageUserDo manageUserDo, List<UserPermissionDo> permissionList) {
        this.manageUserDo = manageUserDo;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return manageUserDo.getPassword();
    }

    @Override
    public String getUsername() {
        return manageUserDo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return manageUserDo.getStatus().equals(1);
    }
}

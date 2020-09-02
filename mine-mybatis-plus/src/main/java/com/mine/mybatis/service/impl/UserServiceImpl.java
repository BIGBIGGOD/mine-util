package com.mine.mybatis.service.impl;

import com.mine.mybatis.model.UserDo;
import com.mine.mybatis.mapper.UserDoMapper;
import com.mine.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jqd
 * @since 2020-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDoMapper, UserDo> implements UserService {

}

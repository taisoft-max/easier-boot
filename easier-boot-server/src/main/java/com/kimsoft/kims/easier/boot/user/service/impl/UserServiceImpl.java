package com.kimsoft.kims.easier.boot.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kimsoft.kims.easier.boot.user.entity.User;
import com.kimsoft.kims.easier.boot.user.mapper.UserMapper;
import com.kimsoft.kims.easier.boot.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author kimi
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

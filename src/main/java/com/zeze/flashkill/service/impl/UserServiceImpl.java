package com.zeze.flashkill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeze.flashkill.exception.GlobalException;
import com.zeze.flashkill.mapper.UserMapper;
import com.zeze.flashkill.pojo.User;
import com.zeze.flashkill.service.IUserService;
import com.zeze.flashkill.utils.CookieUtil;
import com.zeze.flashkill.utils.MD5Util;
import com.zeze.flashkill.utils.UUIDUtil;
import com.zeze.flashkill.utils.ValidatorUtil;
import com.zeze.flashkill.vo.LoginVo;
import com.zeze.flashkill.vo.RespBean;
import com.zeze.flashkill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //参数校验
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

        User user = UserMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        System.out.println(MD5Util.formPassToDBPass(password, user.getSalt()));
        //判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成Cookie
        String userTicket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:" + userTicket, user);

        //request.getSession().setAttribute(userTicket, user);
        CookieUtil.setCookie(request, response, "userTicket", userTicket);

        return RespBean.success(userTicket);
    }

    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
           return user;
    }
}

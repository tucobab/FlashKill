package com.zeze.flashkill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeze.flashkill.pojo.User;
import com.zeze.flashkill.vo.LoginVo;
import com.zeze.flashkill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2023-08-22
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}

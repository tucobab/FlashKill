package com.zeze.flashkill.controller;

import com.zeze.flashkill.service.IUserService;
import com.zeze.flashkill.vo.LoginVo;
import com.zeze.flashkill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        return userService.doLogin(loginVo,request,response);
    }

}

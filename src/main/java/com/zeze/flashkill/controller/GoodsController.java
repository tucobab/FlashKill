package com.zeze.flashkill.controller;


import com.zeze.flashkill.pojo.User;
import com.zeze.flashkill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 商品
 *
 * @author: LC
 * @date 2022/3/2 5:56 下午
 * @ClassName: GoodsController
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/toList")
    public String toList(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return "login";
        }
        //User user = (User) session.getAttribute(ticket);
        User user = userService.getUserByCookie(ticket, request, response);
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}

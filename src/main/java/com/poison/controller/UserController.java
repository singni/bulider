package com.poison.controller;

import com.poison.domain.User;
import com.poison.utils.BuliderResult;
import com.poison.utils.CookieUtils;
import com.poison.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.UnknownUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Rian on 2019/3/3.
 * Copyright © 2018 Rian. All rights reserved
 */
@Controller
public class UserController {
    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public BuliderResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(user.getUserName()) && StringUtils.isEmpty(user.getPassword())) {
            return BuliderResult.build(401, "请输入用户名和密码!");
        }
        String pass = MD5Utils.md5(user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUserName(), pass);
        try {

            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return BuliderResult.build(401, "用户名和密码错误!");
        } catch (ShiroException e) {
            e.printStackTrace();
            return BuliderResult.build(500, "系统错误");
        }
        User pu = (User) subject.getPrincipal();
        pu.setPassword(null);
        subject.getSession().setAttribute(TOKEN_KEY,pu);
        return BuliderResult.ok();
    }


}

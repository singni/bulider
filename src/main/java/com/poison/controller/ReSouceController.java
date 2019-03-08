package com.poison.controller;

import com.poison.domain.*;
import com.poison.mapper.ResourceMapper;
import com.poison.mapper.RoleMapper;
import com.poison.service.ResouceService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rian on 2019/3/4.
 * Copyright © 2018 Rian. All rights reserved
 */
@Controller
public class ReSouceController {
    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;
    @Autowired
    private ResouceService resouceService;
    
    @RequestMapping("/resource/findResouces")
    @ResponseBody
    public List<Resource> findResouce() {
        User attribute = (User) SecurityUtils.getSubject().getSession().getAttribute(TOKEN_KEY);
        if(attribute.getUserName().equals("admin"))
            return  resouceService.findALL();

        return resouceService.findResouces(attribute.getUserId());
    }

}

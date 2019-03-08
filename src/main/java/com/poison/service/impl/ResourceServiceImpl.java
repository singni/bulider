package com.poison.service.impl;

import com.poison.domain.Resource;
import com.poison.domain.ResourceExample;
import com.poison.mapper.ResourceMapper;
import com.poison.service.ResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Rian on 2019/3/4.
 * Copyright © 2018 Rian. All rights reserved
 */
@Service
public class ResourceServiceImpl implements ResouceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public List<Resource> findResouces(String userId) {


        return  resourceMapper.findResours(userId);
    }

    public List<Resource> findALL() {
        return resourceMapper.findALL();
    }


}

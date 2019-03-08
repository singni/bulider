package com.poison.service;

import com.poison.domain.Resource;

import java.util.List;


/**
 * Created by Rian on 2019/3/4.
 * Copyright © 2018 Rian. All rights reserved
 */
public interface ResouceService {


    public List<Resource> findResouces(String userId);

    List<Resource> findALL();
}

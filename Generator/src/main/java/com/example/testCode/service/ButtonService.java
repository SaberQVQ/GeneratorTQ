package com.example.testCode.service;

import com.example.testCode.entity.ButtonDO;
import com.example.testCode.ao.ButtonSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-13
 */
public interface ButtonService {

    public String listByQuery(ButtonSearchAO buttonSearchAO, int rows, int page);




}

package com.example.testCode.dao;

import com.example.testCode.entity.ButtonDO;
import com.example.testCode.ao.ButtonSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-13
 */
public interface ButtonDao {

    public List<ButtonDO> listByQuery(ButtonSearchAO buttonSearchAO);




}
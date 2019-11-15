package com.example.testCode.service;

import com.example.testCode.entity.ButtonDO;
import com.example.testCode.ao.ButtonSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
public interface ButtonService {

    public String listByQuery(ButtonSearchAO buttonSearchAO, int rows, int page);

    public String getByPrimaryKey(String primaryKey);

    public String saveSelective(ButtonDO saveDO);

    public String update(ButtonDO updateDO);

    public String delete(String ids);


}

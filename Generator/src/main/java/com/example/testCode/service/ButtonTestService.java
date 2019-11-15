package com.example.testCode.service;

import com.example.testCode.entity.ButtonTestDO;
import com.example.testCode.ao.ButtonTestSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
public interface ButtonTestService {

    public String listByQuery(ButtonTestSearchAO buttonTestSearchAO, int rows, int page);

    public String getByPrimaryKey(String primaryKey);

    public String saveSelective(ButtonTestDO saveDO);

    public String update(ButtonTestDO updateDO);

    public String delete(String ids);


}

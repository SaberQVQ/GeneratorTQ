package com.example.testCode.dao;

import com.example.testCode.entity.ButtonTestDO;
import com.example.testCode.ao.ButtonTestSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
public interface ButtonTestDao {

    public List<ButtonTestDO> listByQuery(ButtonTestSearchAO buttonTestSearchAO);

    public ButtonTestDO getByPrimaryKey(String primaryKey);

    public int saveSelective(ButtonTestDO saveDO);

    public int update(ButtonTestDO saveDO);

    public int delete(String primaryKey);

}
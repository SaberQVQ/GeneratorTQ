package com.example.testCode.dao;

import com.example.testCode.entity.ButtonDO;
import com.example.testCode.ao.ButtonSearchAO;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
public interface ButtonDao {

    public List<ButtonDO> listByQuery(ButtonSearchAO buttonSearchAO);

    public ButtonDO getByPrimaryKey(String primaryKey);

    public int saveSelective(ButtonDO saveDO);

    public int update(ButtonDO saveDO);

    public int delete(String primaryKey);

}
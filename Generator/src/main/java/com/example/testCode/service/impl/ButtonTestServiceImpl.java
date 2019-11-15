package com.example.testCode.service.impl;

import com.example.testCode.dao.ButtonTestDao;
import com.example.testCode.entity.ButtonTestDO;
import com.example.testCode.ao.ButtonTestSearchAO;
import com.example.testCode.dto.ButtonTestDTO;
import com.example.testCode.service.ButtonTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcd.common.utils.WebJsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.TqToolKit;
import com.pcd.common.utils.IdBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
@Service
public class ButtonTestServiceImpl implements ButtonTestService{

    @Autowired
    private ButtonTestDao buttonTestDao;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String listByQuery(ButtonTestSearchAO buttonTestSearchAO, int rows, int page){
        List <ButtonTestDTO> result = new ArrayList<>();

        PageHelper.startPage(page,rows);

        List <ButtonTestDO> businessDOList = buttonTestDao.listByQuery(buttonTestSearchAO);

        PageInfo<ButtonTestDO> pageInfo = new PageInfo<ButtonTestDO>(businessDOList);
        Integer total = Long.valueOf(pageInfo.getTotal()).intValue();

        if (businessDOList.size() > 0) {
            result = TqToolKit.copyList(businessDOList, ButtonTestDTO.class);
        }

        return WebJsonUtils.successReturn(result, total);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String getByPrimaryKey(String primaryKey){
        ButtonTestDO buttonTestDO = buttonTestDao.getByPrimaryKey(primaryKey);
        ButtonTestDTO result = new ButtonTestDTO();
        TqToolKit.copyAttribute(buttonTestDO, result);
        return WebJsonUtils.successReturnSingle(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveSelective(ButtonTestDO saveDO){
        // 非自动生成主键
        saveDO.setId(IdBuilder.newId());
        buttonTestDao.saveSelective(saveDO);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String update(ButtonTestDO updateDO){
        buttonTestDao.update(updateDO);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String delete(String ids){
        List<String> primaryKeys = Arrays.asList(ids.split(","));
        for (String id : primaryKeys) {
            if (!TqToolKit.isBlank(id)) {
                buttonTestDao.delete(id);
            }
        }
        return WebJsonUtils.successReturn();
    }
}

package com.example.testCode.service.impl;

import com.example.testCode.dao.ButtonDao;
import com.example.testCode.entity.ButtonDO;
import com.example.testCode.ao.ButtonSearchAO;
import com.example.testCode.dto.ButtonDTO;
import com.example.testCode.service.ButtonService;
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
public class ButtonServiceImpl implements ButtonService{

    @Autowired
    private ButtonDao buttonDao;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String listByQuery(ButtonSearchAO buttonSearchAO, int rows, int page){
        List <ButtonDTO> result = new ArrayList<>();

        PageHelper.startPage(page,rows);

        List <ButtonDO> businessDOList = buttonDao.listByQuery(buttonSearchAO);

        PageInfo<ButtonDO> pageInfo = new PageInfo<ButtonDO>(businessDOList);
        Integer total = Long.valueOf(pageInfo.getTotal()).intValue();

        if (businessDOList.size() > 0) {
            result = TqToolKit.copyList(businessDOList, ButtonDTO.class);
        }

        return WebJsonUtils.successReturn(result, total);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String getByPrimaryKey(String primaryKey){
        ButtonDO buttonDO = buttonDao.getByPrimaryKey(primaryKey);
        ButtonDTO result = new ButtonDTO();
        TqToolKit.copyAttribute(buttonDO, result);
        return WebJsonUtils.successReturnSingle(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveSelective(ButtonDO saveDO){
        // 非自动生成主键
        saveDO.setId(IdBuilder.newId());
        buttonDao.saveSelective(saveDO);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String update(ButtonDO updateDO){
        buttonDao.update(updateDO);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String delete(String ids){
        List<String> primaryKeys = Arrays.asList(ids.split(","));
        for (String id : primaryKeys) {
            if (!TqToolKit.isBlank(id)) {
                buttonDao.delete(id);
            }
        }
        return WebJsonUtils.successReturn();
    }
}

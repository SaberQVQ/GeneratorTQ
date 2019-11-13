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

import java.util.ArrayList;
import java.util.List;

/**
 * Author tq
 * Date  2019-11-13
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




}

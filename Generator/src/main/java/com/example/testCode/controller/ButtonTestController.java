package com.example.testCode.controller;

import com.example.testCode.entity.ButtonTestDO;
import com.example.testCode.service.ButtonTestService;
import com.example.testCode.ao.ButtonTestSearchAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSONObject;

import org.springframework.http.MediaType;
import com.pcd.common.utils.WebConversionUtils;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-14
 */
@RestController
@RequestMapping(value = "/buttonTest")
public class ButtonTestController {

    @Autowired
    private ButtonTestService buttonTestService;

    /**
     * 根据条件查询数据列表 带分页
     */
    @RequestMapping(value="/listByQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String list(@RequestParam(value = "content") String content, int rows, int page) {
        ButtonTestSearchAO searchAO = WebConversionUtils.getConditionJavaObject(content, ButtonTestSearchAO.class);
        return buttonTestService.listByQuery(searchAO, rows, page);
    }

    /**
     * 根据主键查询数据信息
     */
    @RequestMapping(value = "/getByPrimaryKey", method = RequestMethod.GET)
    public String getByPrimaryKey(@RequestParam("content") String content) {
        JSONObject conditionJson = WebConversionUtils.getConditionJson(content);
        String primaryKey = conditionJson.getString("id");
        return buttonTestService.getByPrimaryKey(primaryKey);
    }

    /**
     * 新增数据
     */
    @RequestMapping(value = "/saveSelective", method = RequestMethod.POST)
    public String saveSelective(@RequestParam("content") String content) {
        ButtonTestDO saveAO = WebConversionUtils.getValueJavaObject(content, ButtonTestDO.class);
        return buttonTestService.saveSelective(saveAO);
    }

    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("content") String content) {
        ButtonTestDO updateAO = WebConversionUtils.getValueJavaObject(content, ButtonTestDO.class);
        return buttonTestService.update(updateAO);
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("content") String content) {
        String ids = WebConversionUtils.getConditionJson(content).getString("ids");
        return buttonTestService.delete(ids);
    }


}

package com.example.testCode.controller;

import com.example.testCode.entity.ButtonDO;
import com.example.testCode.service.ButtonService;
import com.example.testCode.ao.ButtonSearchAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.MediaType;
import com.pcd.common.utils.WebConversionUtils;

import java.util.List;

/**
 * Author tq
 * Date  2019-11-13
 */
@RestController
@RequestMapping(value = "/button")
public class ButtonController {

    @Autowired
    private ButtonService buttonService;

    /**
     * 根据条件查询数据列表 带分页
     */
    @RequestMapping(value="/listByQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String list(@RequestParam(value = "content") String content, int rows, int page) {
        ButtonSearchAO searchAO = WebConversionUtils.getConditionJavaObject(content, ButtonSearchAO.class);
        return buttonService.listByQuery(searchAO, rows, page);
    }


}

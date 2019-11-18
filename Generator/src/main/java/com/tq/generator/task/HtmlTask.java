package com.tq.generator.task;

import com.tq.generator.entity.ColumnInfo;
import com.tq.generator.task.base.AbstractTask;
import com.tq.generator.util.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tian Qi
 * 2019/11/14
 **/
public class HtmlTask extends AbstractTask {

    public HtmlTask(String className) {
        super(className);
    }

    public HtmlTask(String className, List<ColumnInfo> tableInfos) {
        super(className, null, null, null, tableInfos);
    }

    public HtmlTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    public HtmlTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {

        // 生成Mapper填充数据
        System.out.println("Generating " + className + "List.html");
        System.out.println("Generating " + className + "Form.html");
        Map<String, Object> mapperData = new HashMap<>();
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));

        //组装属性列表
        mapperData.put("Columns", GeneratorUtil.generateHtmlColumns(tableInfos));
        mapperData.put("Events", GeneratorUtil.generateHtmlEvents(className));
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("FormPage", GeneratorUtil.generateHtmlFormPage(StringUtil.firstToLowerCase(className)));

        String filePath = FileUtil.getResourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getHtml());
        createFilePathIfNotExists(filePath);

        String fileName = "TP_" + StringUtil.firstToLowerCase(className) + "_list.html";
        // 生成HtmlList文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_HTML_LIST, mapperData, filePath + fileName);

        fileName = "TP_" + StringUtil.firstToLowerCase(className) + "_form.html";
        // 生成HtmlForm文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_HTML_FORM, mapperData, filePath + fileName);
    }

}

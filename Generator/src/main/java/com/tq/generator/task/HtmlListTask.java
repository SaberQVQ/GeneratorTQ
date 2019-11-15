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
public class HtmlListTask extends AbstractTask {

    public HtmlListTask(String className) {
        super(className);
    }

    public HtmlListTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    public HtmlListTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {

        // 生成Mapper填充数据
        System.out.println("Generating " + className + ".html");
        Map<String, Object> mapperData = new HashMap<>();
        mapperData.put("PackageName", ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        mapperData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        mapperData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("TableName", tableName);
        mapperData.put("BigBracket", "{");
        //组装属性列表
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));

        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapper());

        String fileName = className + "Mapper.xml";
        createFilePathIfNotExists(filePath);
        // 生成Mapper文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_MAPPER, mapperData, filePath + fileName);
    }
}

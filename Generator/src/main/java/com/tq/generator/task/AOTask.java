package com.tq.generator.task;

import com.tq.generator.entity.ColumnInfo;
import com.tq.generator.task.base.AbstractTask;
import com.tq.generator.util.ConfigUtil;
import com.tq.generator.util.FileUtil;
import com.tq.generator.util.FreemarketConfigUtils;
import com.tq.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tian Qi
 * 2019/11/13
 **/
public class AOTask extends AbstractTask {

    public AOTask(String className) {
        super(className);
    }

    public AOTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    public AOTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        System.out.println("Generating " + className + ".java");
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", className);
//        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
//            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos));
//            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos));
//        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
//            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
//            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos, foreignKey));
//        } else { // 单表关系
//            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
//            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
//        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getAo());

        createFilePathIfNotExists(filePath);

        String fileName = className + "SearchAO.java";
        // 生成Entity文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_AO, entityData, filePath + fileName);
    }
}

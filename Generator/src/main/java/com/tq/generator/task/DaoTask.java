package com.tq.generator.task;

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
import java.util.Map;

/**
 * Author tq
 * Date   2018/4/20
 */
public class DaoTask extends AbstractTask {

    public DaoTask(String className) {
        super(className);
    }

    public DaoTask(String className, String businessType) {
        super(className, businessType);
    }

    @Override
    public void run() throws IOException, TemplateException {
        //转换大小写
        String upClassName = StringUtil.firstToUpperCase(className);
        String lowClassName = StringUtil.firstToLowerCase(className);
        // 生成Dao填充数据
        System.out.println("Generating " + upClassName + "Dao.java");
        Map<String, String> daoData = new HashMap<>();
        daoData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        daoData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        daoData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        daoData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        daoData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        daoData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        daoData.put("ClassName", upClassName);
        daoData.put("EntityName", lowClassName);
        daoData.put("Bracket", "<");
        daoData.put("businessType", businessType);

        String filePath = FileUtil.getSourcePath() +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) +
                StringUtil.package2Path(businessType) +
                StringUtil.package2Path(lowClassName) +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDao());
        String fileName = upClassName + "Dao.java";
        createFilePathIfNotExists(filePath);
        // 生成dao文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_DAO, daoData, filePath + fileName);
    }
}

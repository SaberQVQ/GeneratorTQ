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
 * Date   2019/1/24
 */
public class InterfaceTask extends AbstractTask {

    public InterfaceTask(String className) {
        super(className);
    }

    public InterfaceTask(String className, String businessType) {
        super(className, businessType);
    }

    @Override
    public void run() throws IOException, TemplateException {
        //转换大小写
        String upClassName = StringUtil.firstToUpperCase(className);
        String lowClassName = StringUtil.firstToLowerCase(className);
        // 生成Service接口填充数据
        System.out.println("Generating " + upClassName + "Service.java");
        Map<String, String> interfaceData = new HashMap<>();
        interfaceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        interfaceData.put("InterfacePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
        interfaceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        interfaceData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        interfaceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        interfaceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        interfaceData.put("ClassName", upClassName);
        interfaceData.put("EntityName", lowClassName);
        interfaceData.put("businessType", businessType);
        String filePath = FileUtil.getSourcePath() +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) +
                StringUtil.package2Path(businessType) +
                StringUtil.package2Path(lowClassName) +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getInterf());
        String fileName = upClassName + "Service.java";
        createFilePathIfNotExists(filePath);
        // 生成Service接口文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_INTERFACE, interfaceData, filePath + fileName);
    }
}

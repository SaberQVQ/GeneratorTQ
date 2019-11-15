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

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Service接口填充数据
        System.out.println("Generating " + className + "Service.java");
        Map<String, String> interfaceData = new HashMap<>();
        interfaceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        interfaceData.put("InterfacePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
        interfaceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        interfaceData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        interfaceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        interfaceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        interfaceData.put("ClassName", className);
        interfaceData.put("EntityName", StringUtil.firstToLowerCase(className));
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getInterf());
        String fileName = className + "Service.java";
        createFilePathIfNotExists(filePath);
        // 生成Service接口文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_INTERFACE, interfaceData, filePath + fileName);
    }
}

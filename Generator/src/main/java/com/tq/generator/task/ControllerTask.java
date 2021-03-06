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
public class ControllerTask extends AbstractTask {

    public ControllerTask(String className) {
        super(className);
    }

    public ControllerTask(String className, String businessType) {
        super(className, businessType);
    }

    @Override
    public void run() throws IOException, TemplateException {
        //转换大小写
        String upClassName = StringUtil.firstToUpperCase(className);
        String lowClassName = StringUtil.firstToLowerCase(className);

        // 生成Controller填充数据
        System.out.println("Generating " + upClassName + "Controller.java");
        Map<String, String> controllerData = new HashMap<>();
        controllerData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        controllerData.put("ControllerPackageName", ConfigUtil.getConfiguration().getPath().getController());
        controllerData.put("businessType", businessType);

        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        } else {
            controllerData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getInterf());
        }
        controllerData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        controllerData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        controllerData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        controllerData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        controllerData.put("ClassName", upClassName);
        controllerData.put("EntityName", lowClassName);
        String filePath = FileUtil.getSourcePath() +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) +
                StringUtil.package2Path(businessType) +
                StringUtil.package2Path(lowClassName) +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getController());

        String fileName = upClassName + "Action.java";
        createFilePathIfNotExists(filePath);
        // 生成Controller文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_CONTROLLER, controllerData, filePath + fileName);
    }
}

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
public class ServiceTask extends AbstractTask {

    public ServiceTask(String className) {
        super(className);
    }

    public ServiceTask(String className, String businessType) {
        super(className, businessType);
    }

    @Override
    public void run() throws IOException, TemplateException {
        //转换大小写
        String upClassName = StringUtil.firstToUpperCase(className);
        String lowClassName = StringUtil.firstToLowerCase(className);
        // 生成Service填充数据
        Map<String, String> serviceData = new HashMap<>();
        serviceData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        serviceData.put("ServicePackageName", ConfigUtil.getConfiguration().getPath().getService());
        serviceData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        serviceData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getDao());
        serviceData.put("AOPackageName", ConfigUtil.getConfiguration().getPath().getAo());
        serviceData.put("DTOPackageName", ConfigUtil.getConfiguration().getPath().getDto());
        serviceData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        serviceData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        serviceData.put("ClassName", upClassName);
        serviceData.put("EntityName", lowClassName);
        serviceData.put("businessType", businessType);
        serviceData.put("InterfacePackageName", ConfigUtil.getConfiguration().getPath().getInterf());

        String filePath = FileUtil.getSourcePath() +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) +
                StringUtil.package2Path(businessType) +
                StringUtil.package2Path(lowClassName) +
                StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getService());

        String fileName;
        if (StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) { // 表示不生成Service接口文件
            serviceData.put("Impl", "");
//            serviceData.put("Override", "");
            serviceData.put("InterfaceImport", "");
            fileName = upClassName + "Service.java";
            System.out.println("Generating " + upClassName + "Service.java");
        } else {
            serviceData.put("Impl", "Impl implements " + upClassName + "Service");
//            serviceData.put("Override", "\n    @Override");
//            serviceData.put("InterfaceImport", "import " + ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getInterf() + "." + className + "Service;");
            serviceData.put("InterfaceImport", "");
            fileName = upClassName + "ServiceImpl.java";
            System.out.println("Generating " + upClassName + "ServiceImpl.java");
        }

        serviceData.put("Bracket", "<");
        createFilePathIfNotExists(filePath);
        // 生成Service文件
        FileUtil.generateToJava(FreemarketConfigUtils.TYPE_SERVICE, serviceData, filePath + fileName);
    }
}

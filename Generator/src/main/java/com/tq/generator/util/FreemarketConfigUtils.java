package com.tq.generator.util;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreemarketConfigUtils {
    private static String path = new File(FreemarketConfigUtils.class.getClassLoader().getResource("templatesStore").getFile() + ConfigUtil.getConfiguration().getFreemarkPath()).getPath();
    public final static int TYPE_ENTITY = 0;
    public final static int TYPE_DAO = 1;
    public final static int TYPE_SERVICE = 2;
    public final static int TYPE_CONTROLLER = 3;
    public final static int TYPE_MAPPER = 4;
    public final static int TYPE_INTERFACE = 5;
    public final static int TYPE_AO = 6;
    public final static int TYPE_DTO = 7;
    public final static int TYPE_HTML_LIST = 8;
    public final static int TYPE_HTML_FORM = 9;
    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration();
            try {
                if (path.contains("jar")){
                    configuration.setClassForTemplateLoading(FreemarketConfigUtils.class, "/ftls");
                } else {
                    configuration.setDirectoryForTemplateLoading(new File(path));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "utf-8");
        }
        return configuration;
    }
}

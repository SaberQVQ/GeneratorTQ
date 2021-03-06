package com.tq.generator.util;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Objects;

/**
 * Author tq
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath) throws IOException, TemplateException {
        File file = new File(filePath);
//        if (file.exists()) {
//            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
//            return;
//        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtils.TYPE_ENTITY:
                return FreemarketConfigUtils.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtils.TYPE_DAO:
                return FreemarketConfigUtils.getInstance().getTemplate("Dao.ftl");
            case FreemarketConfigUtils.TYPE_SERVICE:
                return FreemarketConfigUtils.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtils.TYPE_CONTROLLER:
                return FreemarketConfigUtils.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtils.TYPE_MAPPER:
                return FreemarketConfigUtils.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtils.TYPE_INTERFACE:
                return FreemarketConfigUtils.getInstance().getTemplate("Interface.ftl");
            case FreemarketConfigUtils.TYPE_AO:
                return FreemarketConfigUtils.getInstance().getTemplate("SearchAO.ftl");
            case FreemarketConfigUtils.TYPE_DTO:
                return FreemarketConfigUtils.getInstance().getTemplate("DTO.ftl");
            case FreemarketConfigUtils.TYPE_HTML_LIST:
                return FreemarketConfigUtils.getInstance().getTemplate("HtmlList.ftl");
            case FreemarketConfigUtils.TYPE_HTML_FORM:
                return FreemarketConfigUtils.getInstance().getTemplate("HtmlForm.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径 指定位置
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getCustomizePath())) {//自定义代码生成位置
            sb.append(ConfigUtil.getConfiguration().getCustomizePath());
            if (!Objects.equals('\\', sb.charAt(sb.length() - 1))) {
                sb.append("\\");
            }
        } else {
            sb.append(getBasicProjectPath()).append("java").append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     * todo 存在bug 需要优化
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        String resource;
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getCustomizeResourcesPath())) {
            resource = ConfigUtil.getConfiguration().getCustomizeResourcesPath();
        } else {
            resource = ConfigUtil.getConfiguration().getCustomizePath();
        }

        if (!StringUtil.isBlank(resource)) {//自定义代码生成位置
            //截取到main后面
            if (resource.indexOf("java") > -1) {
                resource = resource.substring(0, resource.indexOf("java"));
            }
            sb.append(resource);
            if (!Objects.equals('\\', sb.charAt(sb.length() - 1))) {
                sb.append("\\");
            }
        } else {
            sb.append(getBasicProjectPath());
        }
        sb.append("resources").append(File.separator);
        return sb.toString();
    }

//    //获取html文件位置
//    public static String getHtmlPath(){
//        StringBuilder sb = new StringBuilder();
//        String resource;
//        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getCustomizeHtmlPath())) {
//            resource = ConfigUtil.getConfiguration().getCustomizeHtmlPath();
//        } else {
//            sb.append(getBasicProjectPath());
//
//        }
//
//        return sb.toString();
//    }

}

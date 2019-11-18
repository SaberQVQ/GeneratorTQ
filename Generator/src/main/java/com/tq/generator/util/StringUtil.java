package com.tq.generator.util;

import java.io.File;

/**
 * @author Tian Qi
 * 2019/11/9
 **/
public class StringUtil {

    /**
     * 校验传入字符串
     */
    public static boolean isBlank(String string) {
        return null == string || ("").equals(string.trim());
    }

    /**
     * 首字母大写
     */
    public static String firstToUpperCase(String string) {
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * 首字母小写
     */
    public static String firstToLowerCase(String string) {
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * 数据库列名转换为实体的属性名
     *
     * @param columnName 列名
     * @return 转换后的实体属性名
     */
    public static String columnName2PropertyName(String columnName){
        if (isBlank(columnName)) {
            try {
                throw new ParameterNullException("传入参数不能为空");
            } catch (ParameterNullException e) {
                e.printStackTrace();
            }
        }
        if (!columnName.contains("_")) { // 列名中不包含 “_”
            if (isAllUpperCase(columnName)) {
                return columnName.toLowerCase();
            }
            return columnName;
        }
        StringBuilder sb = new StringBuilder();
        String[] str = columnName.toLowerCase().split("_");
        sb.append(str[0]);//属性名首字母小写
        for (int i = 1; i < str.length; i++) {
            sb.append(firstToUpperCase(str[i]));
        }
        return sb.toString();
    }


    /**
     * 给定字符串除特定符号外的字符是否全部大写
     *
     * @param string
     * @return
     */
    public static boolean isAllUpperCase(String string) {
        for (Character c : string.replace("_", "").toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    public static String package2Path(String packageName) {
        if (StringUtil.isBlank(packageName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }


}

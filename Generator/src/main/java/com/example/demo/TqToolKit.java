package com.example.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tian Qi
 * 2019/11/12
 **/
public class TqToolKit {

    /**
     * 校验传入字符串
     */
    public static boolean isBlank(String string) {
        return null == string || ("").equals(string.trim());
    }

    /**
     * 多个对象赋值
     */
    public static <T,M> List<M> copyList(List<T> sources, Class<M> targetClazz){
        if (!checkParams(sources, targetClazz)) {throw new IllegalArgumentException("参数不能为空");}
        return sources.stream().map(source -> copyAttribute(source, targetClazz)).collect(Collectors.toList());
    };

    public static <M> M copyAttribute(Object source, Class<M> targetClazz){
        if (!checkParams(source)) {throw new IllegalArgumentException("参数不能为空");}
        M target = null;
        try {
            target = targetClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        copyAttribute(source, target);
        return target;
    };

    public static void copyAttribute(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 校验传入参数中是否有空值
     *  true 没有空值
     *  false 有空值
     */
    public static boolean checkParams (Object ... params) {
        if (params == null) {
            return false;
        }
        List<Object> paramList = Arrays.asList(params);
        for (Object o : paramList) {
            if (Objects.isNull(o)) {
                return false;
            }
        }
        return true;
    }

//    public static String checkParamsHasInfo (Object ... params) {
//        if (params == null) {
//            return "传入参数不能为空";
//        }
//        List<Object> paramList = Arrays.asList(params);
//        for (int i = 0; i < paramList.size(); i++) {
//            if (Objects.isNull(paramList.get(i))) {
//                return "传入的第" + i + "个参数不能为空";
//            }
//        }
//        return null;
//    }

}

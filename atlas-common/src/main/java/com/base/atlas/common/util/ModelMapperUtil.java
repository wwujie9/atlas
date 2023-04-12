package com.base.atlas.common.util;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 伍接
 * @date 2020/08/23 22:57
 */
public class ModelMapperUtil {

    /**
     * 私有构造方法
     * @author     Mr.WuJie
     * @since      2020年11月12日 下午3:57:47
     */
    private ModelMapperUtil() {

    }
    /**
     * 类型转换
     *@author 1
     * @param sourceObject
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T convertTo(S sourceObject, Class<T> targetClass) {
        if (sourceObject == null) {
            return null;
        }
        try {
            return BeanUtil.copyProperties(sourceObject, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * batchConvertTo
     * @author     1
     * @since      2020年11月12日 下午3:57:47
     * @param sources
     * @param targetClass
     * @return
     */
    public static <S, T> List<T> batchConvertTo(Collection<S> sources, Class<T> targetClass) {
        if (sources == null) {
            return Lists.newArrayList();
        }
        return sources.stream().map(it -> convertTo(it, targetClass)).collect(Collectors.toList());
    }

}

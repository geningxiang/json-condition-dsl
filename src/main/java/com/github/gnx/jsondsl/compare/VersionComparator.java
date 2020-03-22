package com.github.gnx.jsondsl.compare;

import com.github.gnx.jsondsl.annotation.CompareByVersion;
import com.github.gnx.jsondsl.utils.VersionCompareUtil;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/21 13:49
 */
public class VersionComparator implements IComparater {

    private static volatile VersionComparator instance;

    private VersionComparator() {
    }

    public static VersionComparator getInstance() {
        if (instance == null) {
            synchronized (VersionComparator.class) {
                if (instance == null) {
                    instance = new VersionComparator();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean support(Class type, Map<Class, Annotation> annotationMap) {
        return String.class.isAssignableFrom(type) && annotationMap.containsKey(CompareByVersion.class);
    }

    @Override
    public int compare(Object dataValue, Object jsonValue) {
        return VersionCompareUtil.compare(String.valueOf(dataValue), String.valueOf(jsonValue));
    }
}

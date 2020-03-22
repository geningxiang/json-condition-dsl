package com.github.gnx.jsondsl.compare;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 默认的比较器 转String后比较
 * @author genx
 * @date 2020/3/20 19:43
 */
public class DefaultComparator implements IComparater {

    private static volatile DefaultComparator instance;

    private DefaultComparator() {
    }

    public static DefaultComparator getInstance() {
        if (instance == null) {
            synchronized (DefaultComparator.class) {
                if (instance == null) {
                    instance = new DefaultComparator();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean support(Class type, Map<Class, Annotation> annotationMap) {
        return true;
    }

    @Override
    public int compare(Object dataValue, Object jsonValue) {
        return String.valueOf(dataValue).compareTo(String.valueOf(jsonValue));
    }

}

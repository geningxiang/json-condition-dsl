package com.github.gnx.jsondsl.compare;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/20 19:49
 */
public interface IComparater {

    boolean support(Class type, Map<Class, Annotation> annotationMap);

    int compare(Object dataValue, Object jsonValue);
}

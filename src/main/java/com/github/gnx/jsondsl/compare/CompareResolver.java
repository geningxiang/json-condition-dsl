package com.github.gnx.jsondsl.compare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/21 10:53
 */
public class CompareResolver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private LinkedList<IComparater> comparaters = new LinkedList();

    public CompareResolver() {
        comparaters.addFirst(DefaultComparator.getInstance());
        comparaters.addFirst(NumberComparator.getInstance());
        comparaters.addFirst(DateComparator.getInstance());
        comparaters.addFirst(VersionComparator.getInstance());
    }

    public IComparater getMatchComparater(Class type, Map<Class, Annotation> annotationMap) {
        for (IComparater comparater : comparaters) {
            if (comparater.support(type, annotationMap)) {
                return comparater;
            }
        }
        return DefaultComparator.getInstance();
    }


    @Deprecated
    public int compare(Class type, Map<Class, Annotation> annotationMap, Object o1, Object o2) {
        for (IComparater comparater : comparaters) {
            logger.debug("{} | {}", comparater.getClass(), comparater.support(type, annotationMap));
            if (comparater.support(type, annotationMap)) {

                return comparater.compare(o1, o2);
            }
        }
        throw new RuntimeException("未找到相应的比较器");
    }

}

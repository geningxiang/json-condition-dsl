package com.github.gnx.jsondsl.compare;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 数值型比较器
 * @author genx
 * @date 2020/3/20 19:50
 */
public class NumberComparator implements IComparater {

    private static volatile NumberComparator instance;

    private NumberComparator() {
    }

    public static NumberComparator getInstance() {
        if (instance == null) {
            synchronized (NumberComparator.class) {
                if (instance == null) {
                    instance = new NumberComparator();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean support(Class type, Map<Class, Annotation> annotationMap) {
        return Number.class.isAssignableFrom(type);
    }

    @Override
    public int compare(Object dataValue, Object jsonValue) {
        //转 BigDecimal 后比较
        BigDecimal a = new BigDecimal(String.valueOf(dataValue));

        try {
            BigDecimal b = new BigDecimal(String.valueOf(jsonValue));
            return a.compareTo(b);
        } catch (Exception e) {
            //如果b 格式化出错 认为 a > b
            return 1;
        }


    }

}

package com.github.gnx.jsondsl.compare;

import org.apache.commons.lang3.time.FastDateFormat;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/20 19:55
 */
public class DateComparator implements IComparater {

    private static final String[] DATE_FORMAT = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy/MM/dd"
    };

    private static volatile DateComparator instance;

    private DateComparator() {
    }

    public static DateComparator getInstance() {
        if (instance == null) {
            synchronized (DateComparator.class) {
                if (instance == null) {
                    instance = new DateComparator();
                }
            }
        }
        return instance;
    }


    @Override
    public boolean support(Class type, Map<Class, Annotation> annotationMap) {
        return Date.class.isAssignableFrom(type);
    }

    @Override
    public int compare(Object dataValue, Object jsonValue) {
        Date fieldDate = (Date) dataValue;
        Date jsonDate = null;
        if (jsonValue instanceof Date) {
            jsonDate = (Date) jsonValue;
        } else if (jsonValue instanceof Number) {
            jsonDate = new Date(((Number) jsonValue).longValue());
        } else {
            jsonDate = parse(String.valueOf(jsonValue));
        }
        if (jsonDate == null) {
            //非空比 null 大  没毛病
            return 1;
        }
        return fieldDate.compareTo(jsonDate);
    }

    private Date parse(String timeStr) {
        Date result = null;
        for (String format : DATE_FORMAT) {
            if (timeStr.length() == format.length()) {
                try {
                    result = FastDateFormat.getInstance(format).parse(timeStr);
                } catch (Exception e) {

                }
                if (result != null) {
                    return result;
                }

            }
        }
        return null;
    }

}

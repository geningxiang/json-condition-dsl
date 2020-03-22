package com.github.gnx.jsondsl.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.gnx.jsondsl.DslResolver;
import com.github.gnx.jsondsl.compare.IComparater;
import com.github.gnx.jsondsl.handle.operator.OperatorResolver;
import com.github.gnx.jsondsl.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author genx
 * @date 2020/3/19 22:12
 */
public class FieldHandler implements IHandler {

    private static Logger logger = LoggerFactory.getLogger(FieldHandler.class);

    public static Map<String, FieldHandler> of(Class cls, DslResolver dslResolver) {
        Map<String, FieldHandler> map = new HashMap(16);
        FieldHandler fieldHandler;
        for (Method declaredMethod : cls.getDeclaredMethods()) {
            if (Modifier.isPublic(declaredMethod.getModifiers())
                    && !Modifier.isStatic(declaredMethod.getModifiers())
                    && declaredMethod.getParameterCount() == 0
                    && declaredMethod.getName().startsWith("get")
                    && declaredMethod.getReturnType() != Void.class
            ) {
                fieldHandler = new FieldHandler(declaredMethod, dslResolver);
                map.put(fieldHandler.name, fieldHandler);

            }
        }
        return map;
    }


    private final String name;
    private final Method method;
    private final DslResolver dslResolver;
    private final IComparater comparater;
    private final OperatorResolver operatorResolver;

    private FieldHandler(Method declaredMethod, DslResolver dslResolver) {
        this.dslResolver = dslResolver;
        this.name = StringUtils.lowCase(declaredMethod.getName().substring(3));
        this.method = declaredMethod;

        Map<Class, Annotation> annotationMap = new HashMap(8);
        for (Annotation annotation : declaredMethod.getAnnotations()) {
            annotationMap.put(annotation.annotationType(), annotation);
        }

        comparater = dslResolver.getCompareResolver().getMatchComparater(declaredMethod.getReturnType(), annotationMap);

        this.operatorResolver = new OperatorResolver(dslResolver.getDslConfig());
    }

    private Object getValue(Object data) {
        Object value = null;
        try {
            value = method.invoke(data);
        } catch (Exception e) {

        }
        return value;
    }

    @Override
    public boolean match(Map.Entry<String, Object> ruleEntry, Object data) {
        Object value = getValue(data);
        if (value == null) {
            if (ruleEntry.getValue() == null) {
                return true;
            } else {
                return false;
            }
        } else if (ruleEntry.getValue() == null) {
            return false;
        }

        boolean r;
        if (ruleEntry.getValue() instanceof JSONArray) {
            String key = this.dslResolver.getDslConfig().getOperatorIn()[0];

            r = this.operatorResolver.math(key, ruleEntry.getValue(), value, this.comparater);
            logger.debug("[比较 {}]{} {} {} = {}", ruleEntry.getKey(), value, key, ruleEntry.getValue(), r);

            return r;
        } else if (ruleEntry.getValue() instanceof JSONObject) {
            boolean result = true;
            for (Map.Entry<String, Object> entry : ((JSONObject) ruleEntry.getValue()).entrySet()) {
                r = this.operatorResolver.math(entry.getKey(), entry.getValue(), value, this.comparater);
                logger.debug("[比较 {}]{} {} {} = {}", ruleEntry.getKey(), value, entry.getKey(), entry.getValue(), r);

                result &= r;
                if (!result) {
                    break;
                }
            }
            return result;
        } else {
            r = this.comparater.compare(value, ruleEntry.getValue()) == 0;
            logger.debug("[比较 {}]{} == {} = {}", ruleEntry.getKey(), value, ruleEntry.getValue(), r);
            return r;
        }

    }

}

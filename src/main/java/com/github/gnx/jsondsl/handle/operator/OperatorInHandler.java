package com.github.gnx.jsondsl.handle.operator;

import com.github.gnx.jsondsl.compare.IComparater;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 17:39
 */
public class OperatorInHandler implements IOperatorHandler {

    @Override
    public boolean match(String key, Object ruleData, Object value, IComparater comparater) {
        boolean m = false;
        if (ruleData instanceof Iterable) {
            for (Object item : (Iterable) ruleData) {
                m = comparater.compare(value, item) == 0;
                if (m) {
                    break;
                }
            }
        } else {
            m = comparater.compare(value, ruleData) == 0;
        }
        return m;
    }
}

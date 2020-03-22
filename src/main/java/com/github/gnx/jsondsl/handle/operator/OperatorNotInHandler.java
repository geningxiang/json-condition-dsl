package com.github.gnx.jsondsl.handle.operator;

import com.github.gnx.jsondsl.compare.IComparater;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 22:28
 */
public class OperatorNotInHandler implements IOperatorHandler {
    @Override
    public boolean match(String key, Object ruleData, Object value, IComparater comparater) {
        if (ruleData instanceof Iterable) {
            for (Object item : (Iterable) ruleData) {
                if (comparater.compare(value, item) == 0) {
                    return false;
                }
            }
        } else {
            return comparater.compare(value, ruleData) != 0;
        }
        return true;
    }
}

package com.github.gnx.jsondsl.handle.operator;

import com.github.gnx.jsondsl.compare.IComparater;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/21 14:03
 */
public class OperatorHandler implements IOperatorHandler {


    private final OperatorType operatorType;

    public OperatorHandler(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    @Override
    public boolean match(String key, Object ruleData, Object value, IComparater comparater) {
        int c = comparater.compare(value, ruleData);
        return operatorType.math(c);
    }

}

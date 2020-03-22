package com.github.gnx.jsondsl.handle.operator;

import com.github.gnx.jsondsl.compare.IComparater;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 17:39
 */
public interface IOperatorHandler {

     boolean match(String key, Object ruleData, Object value, IComparater comparater);

}

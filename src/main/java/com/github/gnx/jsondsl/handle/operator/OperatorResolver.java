package com.github.gnx.jsondsl.handle.operator;

import com.github.gnx.jsondsl.DslConfig;
import com.github.gnx.jsondsl.compare.IComparater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 17:44
 */
public class OperatorResolver {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, IOperatorHandler> operatorHandlerMap = new HashMap(32);

    public OperatorResolver(DslConfig dslConfig) {
        OperatorHandler o1 = new OperatorHandler(OperatorType.GREATER_THAN);
        for (String s : dslConfig.getOperatorGreaterThan()) {
            operatorHandlerMap.put(s, o1);
        }

        OperatorHandler o2 =  new OperatorHandler(OperatorType.GREATER_THAN_OR_EQUAL);
        for (String s : dslConfig.getOperatorGreaterThanOrEqual()) {
            operatorHandlerMap.put(s,o2);
        }

        OperatorHandler o3 =  new OperatorHandler(OperatorType.LESS_THAN);
        for (String s : dslConfig.getOperatorLessThan()) {
            operatorHandlerMap.put(s, o3);
        }

        OperatorHandler o4 =  new OperatorHandler(OperatorType.LESS_THAN_OR_EQUAL);
        for (String s : dslConfig.getOperatorLessThanOrEqual()) {
            operatorHandlerMap.put(s,o4);
        }

        OperatorHandler o5 = new OperatorHandler(OperatorType.EQUAL);
        for (String s : dslConfig.getOperatorEqual()) {
            operatorHandlerMap.put(s, o5);
        }

        OperatorHandler o6 = new OperatorHandler(OperatorType.NOT_EQUAL);
        for (String s : dslConfig.getOperatorNotEqual()) {
            operatorHandlerMap.put(s, o6);
        }

        OperatorInHandler o7 = new OperatorInHandler();
        for (String s : dslConfig.getOperatorIn()) {
            operatorHandlerMap.put(s, o7);
        }

        OperatorNotInHandler o8 = new OperatorNotInHandler();
        for (String s : dslConfig.getOperatorNotIn()) {
            operatorHandlerMap.put(s, o8);
        }

    }


    public boolean math(String key, Object ruleData, Object value, IComparater comparater) {
        IOperatorHandler operatorHandler = operatorHandlerMap.get(key);
        if (operatorHandler != null) {
            return operatorHandler.match(key, ruleData, value, comparater);
        }
        throw new RuntimeException("未找到相应的比较器");
    }

}

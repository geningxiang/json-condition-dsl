package com.github.gnx.jsondsl.handle.relational;

import com.alibaba.fastjson.JSON;
import com.github.gnx.jsondsl.DslResolver;
import com.github.gnx.jsondsl.AbstractHandler;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 连接符处理器  and、or、not
 * @author genx
 * @date 2020/3/20 22:57
 */
public abstract class AbstractRelationalHandler extends AbstractHandler {

    public AbstractRelationalHandler(DslResolver dslResolver) {
        super(dslResolver);
    }

    @Override
    public final String[] keys() {
        String[] keys = getKeys();
        for (int i = 0; i < keys.length; i++) {
            keys[i] = getSymbolPrefix() + keys[i];
        }
        return keys;
    }


    public abstract String[] getKeys();

    @Override
    public boolean match(Map.Entry<String, Object> ruleEntry, Object data) {
        if(ruleEntry.getValue() == null){
            return false;
        }
        if(!(ruleEntry.getValue() instanceof JSON)){
            throw new IllegalArgumentException("连接符之后必须是JSONObject或JSONArray");
        }
        return doMatch(ruleEntry, data);
    }


}

package com.github.gnx.jsondsl.handle.relational;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.gnx.jsondsl.DslResolver;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/19 20:28
 */
public class AndHandler extends AbstractRelationalHandler {

    public AndHandler(DslResolver dslResolver) {
        super(dslResolver);
    }

    @Override
    public String[] getKeys() {
        return new String[]{"and"};
    }

    @Override
    public void check(Object ruleItem) {

    }

    @Override
    protected boolean doMatch(Map.Entry<String, Object> ruleEntry, Object data) {
        if (ruleEntry.getValue() instanceof JSONObject) {
            return doJudge((JSONObject) ruleEntry.getValue(), data);
        } else if (ruleEntry.getValue() instanceof JSONArray) {
            return doJudge((JSONArray) ruleEntry.getValue(), data, false);
        }
        return false;
    }

}

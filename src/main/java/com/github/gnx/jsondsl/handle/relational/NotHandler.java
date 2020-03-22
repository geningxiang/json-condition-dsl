package com.github.gnx.jsondsl.handle.relational;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.gnx.jsondsl.DslResolver;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/19 20:44
 */
public class NotHandler extends AbstractRelationalHandler {

    public NotHandler(DslResolver dslResolver) {
        super(dslResolver);
    }

    @Override
    public String[] getKeys() {
        return new String[]{"not"};
    }

    @Override
    public void check(Object obj) {

    }

    @Override
    protected boolean doMatch(Map.Entry<String, Object> ruleEntry, Object data) {
        if (ruleEntry.getValue() instanceof JSONObject) {
            return !doJudge((JSONObject) ruleEntry.getValue(), data);
        } else if (ruleEntry.getValue() instanceof JSONArray) {
            // not 之后   array应该用or判断
            return !doJudge((JSONArray) ruleEntry.getValue(), data, true);
        }
        return false;
    }
}

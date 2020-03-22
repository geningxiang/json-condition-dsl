package com.github.gnx.jsondsl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.gnx.jsondsl.handle.IHandler;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 连接符
 * @author genx
 * @date 2020/3/19 20:28
 */
public abstract class AbstractHandler implements IHandler {

    private final DslResolver dslResolver;

    public AbstractHandler(DslResolver dslResolver) {
        this.dslResolver = dslResolver;
    }

    protected String getSymbolPrefix(){
        return this.dslResolver.getSymbolPrefix();
    }

    public abstract String[] keys();

    public abstract void check(Object ruleItem);

    @Override
    public boolean match(Map.Entry<String, Object> ruleEntry, Object data) {
        return doMatch(ruleEntry, data);
    }

    protected abstract boolean doMatch(Map.Entry<String, Object> ruleEntry, Object data);


    protected boolean doJudge(JSONObject rule, Object data) {
        return dslResolver.doJudge(rule, data);
    }

    protected boolean doJudge(JSONArray rule, Object data, boolean or) {
        return dslResolver.doJudge(rule, data, or);
    }

}

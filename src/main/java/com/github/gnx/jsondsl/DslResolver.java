package com.github.gnx.jsondsl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.gnx.jsondsl.compare.CompareResolver;
import com.github.gnx.jsondsl.handle.FieldHandler;
import com.github.gnx.jsondsl.handle.IHandler;
import com.github.gnx.jsondsl.handle.relational.AndHandler;
import com.github.gnx.jsondsl.handle.relational.NotHandler;
import com.github.gnx.jsondsl.handle.relational.OrHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/19 20:38
 */
public class DslResolver {

    private final Map<String, IHandler> optionMap = new HashMap(64);
    private final Class sourceCls;

    /**
     * 关系符 前缀
     * 默认 $ 目前就3种
     * $and $or $not
     */
    private final String symbolPrefix;

    /**
     * 是否开启严格模式
     */
    private final boolean strict;

    private DslConfig dslConfig;

    private CompareResolver compareResolver = new CompareResolver();


    public DslResolver(Class cls) {
        this(cls, "", false);
    }

    public DslResolver(Class cls, String symbolPrefix, boolean strict) {
        this.sourceCls = cls;

//        if (StringUtils.isBlank(symbolPrefix)) {
//            throw new IllegalArgumentException("运算符前缀不能为空");
//        }

        this.symbolPrefix = symbolPrefix;
        this.strict = strict;
        this.dslConfig = new DslConfig();

        AbstractHandler[] handlers = {
                new AndHandler(this),
                new OrHandler(this),
                new NotHandler(this)
        };

        for (AbstractHandler handler : handlers) {
            for (String key : handler.keys()) {
                this.optionMap.put(key, handler);
            }
        }
        optionMap.putAll(FieldHandler.of(cls, this));


    }

    public boolean judge(JSON rule, Object data) {
        try {
            if (rule instanceof JSONObject) {
                return doJudge((JSONObject) rule, data);
            } else if (rule instanceof JSONArray) {
                return doJudge((JSONArray) rule, data, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    protected boolean doJudge(JSONObject rule, Object data) {
        if (!this.sourceCls.isAssignableFrom(data.getClass())) {
            throw new RuntimeException("解析器类型不匹配: " + data.getClass() + " --> " + this.sourceCls);
        }

        for (Map.Entry<String, Object> entry : rule.entrySet()) {
            IHandler handler = optionMap.get(entry.getKey());
            if (handler != null) {
                boolean r = handler.match(entry, data);
                if (!r) {
                    return false;
                }
            } else {
                throw new RuntimeException("未知关系符或字段:" + entry.getKey());
            }
            //warn
        }
        return true;
    }

    protected boolean doJudge(JSONArray rule, Object data, boolean or) {
        boolean result = true;
        boolean r ;
        for (Object datum : rule) {
            if (datum instanceof JSONObject) {
                r = doJudge((JSONObject) datum, data);
                if (r && or) {
                    return true;
                } else if (!or && !r) {
                    return false;
                }
                result &= r;
            }
        }
        return result;
    }

    public String getSymbolPrefix() {
        return symbolPrefix;
    }

    public boolean isStrict() {
        return strict;
    }

    public DslConfig getDslConfig() {
        return dslConfig;
    }

    public CompareResolver getCompareResolver() {
        return compareResolver;
    }
}

package com.github.gnx.jsondsl.handle;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author genx
 * @date 2020/3/19 22:22
 */
public interface IHandler {

    boolean match(Map.Entry<String, Object> ruleEntry, Object data);
}

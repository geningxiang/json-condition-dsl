package com.github.gnx.jsondsl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/20 23:20
 */
public class DslResolverTest {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private JSONObject rule;

    private DslResolver dslResolver = new DslResolver(RuleInfo.class);

    @Before
    public void setUp() throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:rule/rule1.json");

        String ruleStr = IOUtils.toString(resource.getURI(), StandardCharsets.UTF_8.name());
        this.rule = JSON.parseObject(ruleStr);
    }


    @Test
    public void test() {
        JSONObject rule = JSON.parseObject("-- 条件 json格式 --");
        RuleInfo ruleInfo = new RuleInfo();
        ruleInfo.setClientType(4);
        ruleInfo.setVip(2);
        ruleInfo.setVersion("3.1.0");
        ruleInfo.setBalance(new BigDecimal("1000"));
        boolean isMatch = dslResolver.judge(rule, ruleInfo);
        Assert.assertTrue(isMatch);

    }

}
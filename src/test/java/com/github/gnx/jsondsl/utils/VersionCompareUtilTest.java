package com.github.gnx.jsondsl.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/20 23:37
 */
public class VersionCompareUtilTest {

    @Test
    public void test() {
        String version1 = "5213.123.124-asdjjis";
        String version2 = "5213.123.123-asdjjid";
        Assert.assertTrue(com.github.gnx.jsondsl.utils.VersionCompareUtil.compare(version1, version2) > 0);


        version1 = "5213.123.124-asdjjis";
        version2 = "5213.123.124";
        Assert.assertTrue(com.github.gnx.jsondsl.utils.VersionCompareUtil.compare(version1, version2) > 0);


        version1 = "8.0.0";
        version2 = "4.5.0";

        Assert.assertTrue(com.github.gnx.jsondsl.utils.VersionCompareUtil.compare(version1, version2) > 0);
    }

}
package com.github.gnx.jsondsl.utils;

/**
 * Created with IntelliJ IDEA.
 * Description: 
 * @author genx
 * @date 2020/3/22 17:28
 */
public class StringUtils {

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String lowCase(String str) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            char[] ch = str.toCharArray();
            if (ch[0] >= 'A' && ch[0] <= 'Z') {
                ch[0] = (char) (ch[0] + 32);
            }
            return new String(ch);
        }
        return "";
    }

}

package com.github.gnx.jsondsl.utils;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * Description: 版本号 比较器
 * @author genx
 * @date 2020/3/20 23:37
 */
public class VersionCompareUtil {

    public static int compare(String version1, String version2) {
        if (version1 == null) {
            if (version2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (version2 == null) {
            return 1;
        }
        LinkedList<Object> list1 = parse(version1);
        LinkedList<Object> list2 = parse(version2);

        Object o1;
        Object o2;
        int result;
        while (true) {
            o1 = list1.pollFirst();
            o2 = list2.pollFirst();

            result = compareNumberOrString(o1, o2);

            if (result != 0) {
                return result;
            }

            if (o1 == null || o2 == null) {
                break;
            }
        }
        return result;
    }

    /**
     * 比较 数字或字符  这里认为 数字 > 字符
     * @param o1
     * @param o2
     * @return
     */
    private static int compareNumberOrString(Object o1, Object o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (o2 == null) {
            return 1;
        }

        if (o1 instanceof Long) {
            if (o2 instanceof Long) {
                // o1 is Long && o2 is Long
                return ((Long) o1).compareTo((Long) o2);
            } else {
                // o1 is Long && o2 is String
                return 1;
            }
        } else if (o2 instanceof Long) {
            // o1 is String  && o2 is Long
            return -1;
        }
        return String.valueOf(o1).compareTo(String.valueOf(o2));
    }

    /**
     * 解析版本号
     * 1.0-SNAPSHOT => [1, ".", 0, "-SNAPSHOT"]
     * @param version
     * @return
     */
    private static LinkedList<Object> parse(String version) {
        LinkedList<Object> list = new LinkedList();
        StringBuilder sb = new StringBuilder(16);
        Boolean lastIsDigit = null;
        boolean isDigit;
        char c;
        for (int i = 0; i < version.length(); i++) {
            c = version.charAt(i);
            isDigit = Character.isDigit(c);

            if (lastIsDigit != null && lastIsDigit != isDigit) {
                if (lastIsDigit) {
                    list.add(Long.parseLong(sb.toString()));
                } else {
                    list.add(sb.toString());
                }
                sb.delete(0, sb.length());
            }

            sb.append(c);
            lastIsDigit = isDigit;
        }

        if (lastIsDigit) {
            list.add(Long.parseLong(sb.toString()));
        } else {
            list.add(sb.toString());
        }
        return list;
    }
}

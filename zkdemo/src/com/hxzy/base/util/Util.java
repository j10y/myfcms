
package com.hxzy.base.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class Util {

    public static final String URL_ENCODING_SCHEMA = "GBK";

    /**
     * 描述: 对字符串进行url编码
     * 
     * @param url
     *            字符串
     * @return 编码后的字符串，如发生异常返回null
     */
    public static String urlEncoding(String url) {
        try {
            return URLEncoder.encode(url, URL_ENCODING_SCHEMA);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 描述: 对已编码的url进行解码
     * 
     * @param url
     *            字符串
     * @return 解码后的字符串，如发生异常返回null
     */
    public static String urlDecoding(String url) {
        try {
            return URLDecoder.decode(url, URL_ENCODING_SCHEMA);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 描述: 判断字符串是否为合法IP
     * 
     * @param ipStr
     *            字符串
     * @return 是返回true,否返回false
     */
    public static boolean isValidIp(String ipStr) {
        if (ipStr == null)
            return false;
        String[] ip = ipStr.split("(\\.)");
        int size = ip.length;
        if (size != 4)
            return false;
        Long j = null;
        for (int i = 0; i < size; i++) {
            j = StringUtil.stringToLong(ip[i]);
            if (j == null || j.longValue() > 255 || j.longValue() < 0)
                return false;
        }
        return true;
    }

    /**
     * 描述: 将IP地址格式化为12个字符的字符串
     * 
     * @param ipStr
     *            ip字符串
     * @return 如果IP合法返回格式化后的字符串，否则返回空值
     */
    public static String formatIp(String ipStr) {
        if (ipStr == null)
            return null;
        String[] ip = ipStr.split("(\\.)");
        int size = ip.length;
        if (size != 4)
            return null;
        Long j = null;
        String result = "";
        DecimalFormat decimalFormat = new DecimalFormat("000");
        for (int i = 0; i < size; i++) {
            j = StringUtil.stringToLong(ip[i]);
            if (j == null || j.longValue() > 255 || j.longValue() < 0)
                return null;
            result = result + decimalFormat.format(j.longValue());
        }
        return result;
    }

}

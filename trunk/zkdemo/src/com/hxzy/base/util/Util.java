
package com.hxzy.base.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class Util {

    public static final String URL_ENCODING_SCHEMA = "GBK";

    /**
     * ����: ���ַ�������url����
     * 
     * @param url
     *            �ַ���
     * @return �������ַ������緢���쳣����null
     */
    public static String urlEncoding(String url) {
        try {
            return URLEncoder.encode(url, URL_ENCODING_SCHEMA);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * ����: ���ѱ����url���н���
     * 
     * @param url
     *            �ַ���
     * @return �������ַ������緢���쳣����null
     */
    public static String urlDecoding(String url) {
        try {
            return URLDecoder.decode(url, URL_ENCODING_SCHEMA);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * ����: �ж��ַ����Ƿ�Ϊ�Ϸ�IP
     * 
     * @param ipStr
     *            �ַ���
     * @return �Ƿ���true,�񷵻�false
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
     * ����: ��IP��ַ��ʽ��Ϊ12���ַ����ַ���
     * 
     * @param ipStr
     *            ip�ַ���
     * @return ���IP�Ϸ����ظ�ʽ������ַ��������򷵻ؿ�ֵ
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

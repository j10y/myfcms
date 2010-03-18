/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2009 �����ڻ���Ϣ�Ƽ����޹�˾</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2009-12-16</p>
 * <p>���£�</p>
 */
package base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ������GenericsUtils
 * ������
 * @author xiacc
 */
public class GenericsUtils {
    /** *//**
     * ͨ�����䣬��ö���Classʱ�����ĸ���ĵ�һ�����Ͳ��������͡�
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }
    
    /** *//**
     * ͨ�����䣬��ö���Classʱ�����ĸ���ķ��Ͳ��������͡�
     * ��û���ҵ�����Ҫ��ķ��Ͳ�������ݹ�����ֱ��Object��
     *
     * @param clazz Ҫ���в�ѯ����
     * @param index ���ж������������������0��ʼ
     * @return ��indexλ�õķ��Ͳ��������ͣ�����޷��ж��򷵻�<code>Object.class</code>
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {
        boolean flag = true;
        Type genType = clazz.getGenericSuperclass();
        Type[] params = null;
        
        if (!(genType instanceof ParameterizedType))
            flag = false;
        else {
            params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index >= params.length || index < 0)
                flag = false;
            if (!(params[index] instanceof Class))
                flag = false;
        }
        if (!flag) {
            clazz = clazz.getSuperclass();
            if (clazz == Object.class)
                return Object.class;
            else
                return getSuperClassGenricType(clazz, index);
        }
        
        return (Class) params[index];
    }
}


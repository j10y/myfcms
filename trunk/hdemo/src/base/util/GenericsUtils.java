/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2009 湖北融汇信息科技有限公司</p>
 * <p>版本：1.0</p>
 * <p>日期：2009-12-16</p>
 * <p>更新：</p>
 */
package com.hxzy.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类名：GenericsUtils
 * 描述：
 * @author xiacc
 */
public class GenericsUtils {
    /** *//**
     * 通过反射，获得定义Class时声明的父类的第一个范型参数的类型。
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }
    
    /** *//**
     * 通过反射，获得定义Class时声明的父类的范型参数的类型。
     * 如没有找到符合要求的范型参数，则递归向上直到Object。
     *
     * @param clazz 要进行查询的类
     * @param index 如有多个范型声明该索引从0开始
     * @return 在index位置的范型参数的类型，如果无法判断则返回<code>Object.class</code>
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


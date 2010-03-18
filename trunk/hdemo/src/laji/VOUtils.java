package laji;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Value Object工具类<br>
 * 使用实例：<br>
 * 
 * <pre>
 * Person类：
 * package cn.org.jshuwei.j2ee.util.test;
 * 
 * public class Person {
 *         private String id;
 *         private User u;
 * 
 *         public Person(String id, User u) {
 *                 super();
 *                 this.id = id;
 *                 this.u = u;
 *         }
 * 
 *         public String getId() {
 *                 return id;
 *         }
 * 
 *         public void setId(String id) {
 *                 this.id = id;
 *         }
 * 
 *         public User getU() {
 *                 return u;
 *         }
 * 
 *         public void setU(User u) {
 *                 this.u = u;
 *         }
 * 
 * }
 * User类：
 * package cn.org.jshuwei.j2ee.util.test;
 * 
 * public class User {
 *         private String userName;
 *         private String userPass;
 *         private Integer userAge;
 *         
 *         public User(){
 *                 
 *         }
 *         
 *         public User(String userName, String userPass, int userAge) {
 *                 super();
 *                 this.userName = userName;
 *                 this.userPass = userPass;
 *                 this.userAge = userAge;
 *         }
 *         
 *         public String getUserName() {
 *                 return userName;
 *         }
 *         public void setUserName(String userName) {
 *                 this.userName = userName;
 *         }
 *         public String getUserPass() {
 *                 return userPass;
 *         }
 *         public void setUserPass(String userPass) {
 *                 this.userPass = userPass;
 *         }
 *         public Integer getUserAge() {
 *                 return userAge;
 *         }
 *         public void setUserAge(Integer userAge) {
 *                 this.userAge = userAge;
 *         }
 * }
 * 测试调用类：
 * package cn.org.jshuwei.j2ee.util.test;
 * 
 * import java.util.Map;
 * 
 * import cn.org.jshuwei.j2ee.util.VOUtils;
 * 
 * public class Test {
 * 
 *         public static void main(String[] args) {
 *                 User u = new User(&quot;huwei&quot;,&quot;123&quot;,23);
 *                 Person p = new Person(&quot;1&quot;,u);
 *                 Map&lt;String,Object&gt; map = VOUtils.beanToMap(p,&quot;cn.org.jshuwei.j2ee.util.test&quot;); 
 *                 for(Map.Entry&lt;String, Object&gt; entry : map.entrySet()){   
 *                     String value = entry.getValue().toString();   
 *                     String key = entry.getKey();
 *                     System.out.println(key+&quot;----&quot;+value);
 *                 }
 *                 u = new User();
 *                 u.setUserName(&quot;jshuwei&quot;);
 *                 System.out.println(VOUtils.beanToWhereSql(u));
 *         }        
 * }
 * </pre>
 * 
 * @author huwei(jshuwei.org.cn)
 * 
 */
public class VOUtils {
	/**
	 * 将实体类对象的属性值转换成属性名为key，属性值为value的Map<String,String>并返回
	 * 
	 * @param entity
	 *            需要转换的实体类对象
	 * @return 转换后的Map<String,String>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> beanToMap(Object entity) {
		Class c = entity.getClass();
		Object fieldValue = null;
		String fieldName = null;
		Field[] fields = c.getDeclaredFields();
		Map<String, String> fieldMap = new HashMap<String, String>();
		for (Field field : fields) {
			fieldName = field.getName();
			if (field.getModifiers() == Modifier.PUBLIC) {
				try {
					fieldValue = field.get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				fieldValue = invokeGet(entity, fieldName);
			}
			fieldMap.put(fieldName, fieldValue == null ? "" : fieldValue.toString());
		}
		return fieldMap;
	}

	/**
	 * 将实体类对象的属性值转换成属性名为key，属性值为value的Map<String,Object>并返回.
	 * 其中实体类中有其他自定义对象类型的属性。
	 * 
	 * @param entity
	 *            需要转换的实体类对象
	 * @param packageName
	 *            自定义对象类型属性的对象所在的包名
	 * @return 转换后的Map<String,Object>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object entity, String packageName) {
		Class c = entity.getClass();
		Object fieldValue = null;
		String fieldName = null;
		Field[] fields = c.getDeclaredFields();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		for (Field field : fields) {
			fieldName = field.getName();
			if (field.getModifiers() == Modifier.PUBLIC) {
				try {
					fieldValue = field.get(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				fieldValue = invokeGet(entity, fieldName);
			}
			if (fieldValue != null && fieldValue.getClass().getName().startsWith(packageName)) {
				fieldValue = beanToMap(fieldValue, packageName);
			}
			fieldMap.put(fieldName, fieldValue);
		}
		return fieldMap;
	}

	private static Object invokeGet(Object entity, String fieldName) {
		try {
			Method method = entity.getClass().getMethod("get" + StringUtil.firstToUpper(fieldName));
			return method.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将一个实体对象(已有部分属性赋值)转换成有值属性组成的条件查询SQL语句<br>
	 * <font color="red">注意：需要转换的对象的属性若是基本类型则得用包装类代替</font>
	 * 
	 * @param entity
	 *            需要转换的实体类对象
	 * @return 转换后的条件查询SQL语句字符串
	 */
	public static String beanToWhereSql(Object entity) {
		StringBuffer ret = new StringBuffer(" where 1=1");
		Map<String, Object> map = beanToMap(entity, "cn.org.jshuwei.j2ee.util");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			String key = entry.getKey();
			if (value != null) {
				ret.append(" and ").append(key).append("=").append(value);
			}
		}
		return ret.toString();
	}
}
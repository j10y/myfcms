package laji;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Value Object������<br>
 * ʹ��ʵ����<br>
 * 
 * <pre>
 * Person�ࣺ
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
 * User�ࣺ
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
 * ���Ե����ࣺ
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
	 * ��ʵ������������ֵת����������Ϊkey������ֵΪvalue��Map<String,String>������
	 * 
	 * @param entity
	 *            ��Ҫת����ʵ�������
	 * @return ת�����Map<String,String>
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
	 * ��ʵ������������ֵת����������Ϊkey������ֵΪvalue��Map<String,Object>������.
	 * ����ʵ�������������Զ���������͵����ԡ�
	 * 
	 * @param entity
	 *            ��Ҫת����ʵ�������
	 * @param packageName
	 *            �Զ�������������ԵĶ������ڵİ���
	 * @return ת�����Map<String,Object>
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
	 * ��һ��ʵ�����(���в������Ը�ֵ)ת������ֵ������ɵ�������ѯSQL���<br>
	 * <font color="red">ע�⣺��Ҫת���Ķ�����������ǻ�����������ð�װ�����</font>
	 * 
	 * @param entity
	 *            ��Ҫת����ʵ�������
	 * @return ת�����������ѯSQL����ַ���
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
package cn.org.rapid_framework.generator.provider.java;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.org.rapid_framework.generator.IGeneratorModelProvider;
import cn.org.rapid_framework.generator.provider.java.model.JavaClass;

public class JavaClassGeneratorModelProvider implements IGeneratorModelProvider {
	JavaClass clazz;

	public JavaClassGeneratorModelProvider(JavaClass clazz) {
		super();
		this.clazz = clazz;
	}

	public String getDisaplyText() {
		return "JavaClass:" + clazz.getClassName();
	}

	public void mergeFilePathModel(Map model) throws Exception {
		// for(JavaMethod m:clazz.getMethods()){
		// model.put(m.getMethodName(), m);
		// }
		//		
		// for(JavaField f:clazz.getFields()){
		// model.put(f.getFieldName(), f);
		// }
		//		 
		// for(JavaProperty p:clazz.getProperties()){
		// model.put(p.getName(), p);
		// }
		//		
		// model.put("className", clazz.getClassName());
		// model.put("classNameLower", clazz.getClassNameLower());
		// model.put("description", clazz.getDescription());
		// model.put("packageName", clazz.getPackageName());
		// model.put("packagePath", clazz.getPackagePath());
		// model.put("javaType", clazz.getJavaType());

		model.putAll(BeanUtils.describe(clazz));
	}

	public void mergeTemplateModel(Map model) throws Exception {
		model.put("clazz", clazz);
	}

}

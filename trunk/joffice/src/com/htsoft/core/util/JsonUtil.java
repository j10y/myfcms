package com.htsoft.core.util;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;

public class JsonUtil {
	public static JSONSerializer getJSONSerializer(String[] dateFields) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(new String[] { "class" });
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), dateFields);
		return serializer;
	}
}


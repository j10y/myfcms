package com.htsoft.core.util;

import java.util.UUID;

public class UUIDGenerator {
	public static String getUUID() {
		String s = UUID.randomUUID().toString();

		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
	}

	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; ++i) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static void main(String[] args) {
		String[] vars = UUID.randomUUID().toString().split("-");
		for (int i = 0; i < vars.length; ++i) {
			System.out.println("ok:" + vars[i]);
			long var = Long.valueOf(vars[i], 16).longValue();
			System.out.println("ok:===" + var);
		}
	}
}

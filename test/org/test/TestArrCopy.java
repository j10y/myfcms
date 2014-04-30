package org.test;

import java.util.Arrays;

public class TestArrCopy {

	private static String[] insert(String[] arr, String str) {
		int size = arr.length;

		String[] tmp = new String[size + 1];

		System.arraycopy(arr, 0, tmp, 1, size);

		tmp[0] = str;

		return tmp;
	}
	
	public static void main(String[] args) {
		String[] ids = { "1", "2", "3", "4", "5" };  
		String id = "0";
		
		System.out.println(Arrays.toString(insert(ids,id)));
		
	}
}

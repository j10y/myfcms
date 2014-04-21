package com.base;

public class Common {
	public static int getLetterNumber(char cha) {
		// 如果输入的不是字母，就抛出异常
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("输入的字符不属于英语26个字母");
		}
		// 大小写字母均可判断
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "是英文字母表中第" + num + "个字母");
		return num;
	}
	
	
	public static int getLetterNumber(String var) {
		
		if(var.length()>1){
			return getLetterNumber(var.charAt(0))*26+getLetterNumber(var.substring(1, var.length()));
		}
		char cha = var.charAt(0);
		// 如果输入的不是字母，就抛出异常
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("输入的字符不属于英语26个字母");
		}
		// 大小写字母均可判断
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "是英文字母表中第" + num + "个字母");
		return num;
	}
}

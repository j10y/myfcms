package com.base;

public class Common {
	public static int getLetterNumber(char cha) {
		// �������Ĳ�����ĸ�����׳��쳣
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("������ַ�������Ӣ��26����ĸ");
		}
		// ��Сд��ĸ�����ж�
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "��Ӣ����ĸ���е�" + num + "����ĸ");
		return num;
	}
	
	
	public static int getLetterNumber(String var) {
		
		if(var.length()>1){
			return getLetterNumber(var.charAt(0))*26+getLetterNumber(var.substring(1, var.length()));
		}
		char cha = var.charAt(0);
		// �������Ĳ�����ĸ�����׳��쳣
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("������ַ�������Ӣ��26����ĸ");
		}
		// ��Сд��ĸ�����ж�
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "��Ӣ����ĸ���е�" + num + "����ĸ");
		return num;
	}
}

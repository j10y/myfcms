package org.test;

public class TestString {

	public static void main(String[] args) {
		String title = "共产党员\"电视栏目_共产党员电视栏目";
		title = title.replace("\"","");
		System.out.println(title);
	}
}

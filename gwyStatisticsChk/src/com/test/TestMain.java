package com.test;

import java.text.ParseException;
import java.util.List;

import org.wltea.expression.ExpressionExecutor;
import org.wltea.expression.ExpressionToken;
import org.wltea.expression.IllegalExpressionException;

import com.base.Common;

public class TestMain {

	public static void main(String[] args) {
		ExpressionExecutor ee = new ExpressionExecutor();
		
		try {
			List<ExpressionToken> tokenList = ee.analyze("4875 <=  3022");
			
			tokenList = ee.compile(tokenList);
			
//			System.out.println(ee.execute(tokenList).toJavaObject());
			
			System.out.println(Common.getLetterNumber("AC"));
			
		} catch (IllegalExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

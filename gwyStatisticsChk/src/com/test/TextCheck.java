package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.wltea.expression.ExpressionExecutor;
import org.wltea.expression.ExpressionToken;
import org.wltea.expression.IllegalExpressionException;

import com.base.Common;
import com.sun.xml.internal.ws.util.StringUtils;

public class TextCheck {

	public static void main(String[] args) {

		HashSet<String> operator = new HashSet<String>();

		operator.add("+");
		operator.add("-");
		operator.add("*");
		operator.add("/");

		operator.add("==");
		operator.add(">");
		operator.add(">=");
		operator.add("<");
		operator.add("<=");
		operator.add("<>");

		operator.add("(");
		operator.add(")");

		operator.add("!");
		operator.add("%");
		operator.add("&&");
		operator.add("||");

		try {
			FileReader reader = new FileReader("source.txt");
			BufferedReader br = new BufferedReader(reader);

			InputStream is1 = new FileInputStream("test1.xls");
			InputStream is2 = new FileInputStream("test2.xls");
			Workbook wb1 = Workbook.getWorkbook(is1);
			Workbook wb2 = Workbook.getWorkbook(is2);

			Sheet[] sheets1 = wb1.getSheets();// 获取所有的sheet
			Sheet[] sheets2 = wb2.getSheets();// 获取所有的sheet

			String str = null;
			while ((str = br.readLine()) != null) {
				if (!str.isEmpty()) {
					str = str.replaceAll(" ", "");

					String str1[] = str.split("\\?");

					String expression = str1[0];
					String reason = str1[1];
					// System.out.println(str1[0]+","+str1[1]+"\n");

					String experssions[] = expression.split(";");

					for (int i = 0; i < experssions.length; i++) {
						// System.out.println(experssions[i]);
						// if(Operator.isLegalOperatorToken(experssions[i])){
						// System.out.println(experssions[i]);
						// }

						if (!operator.contains(experssions[i])) {
							// System.out.println(experssions[i]);

							String values[] = experssions[i].split(",");

							String value = "";

							if (values[0].equals("1")) {
								Sheet s1 = wb1.getSheet(Integer.parseInt(values[1]));// 获取sheet
								value = s1.getCell(Common.getLetterNumber(values[2]) - 1, Integer.parseInt(values[3]) - 1)
										.getContents();
								// System.out.println(value);
							}
							if (values[0].equals("2")) {
								Sheet s2 = wb2.getSheet(Integer.parseInt(values[1]));// 获取sheet
								value = s2.getCell(Common.getLetterNumber(values[2]) - 1, Integer.parseInt(values[3]) - 1)
										.getContents();
								// System.out.println(value);
							}

							if (value.equals("")) {
								value = "0";
							}
							experssions[i] = value;
						}
					}

					StringBuffer sb = new StringBuffer();

					for (int j = 0; j < experssions.length; j++) {
						sb.append(experssions[j]);
					}

					ExpressionExecutor ee = new ExpressionExecutor();

					List<ExpressionToken> tokenList = ee.analyze(sb.toString());

					tokenList = ee.compile(tokenList);

					if(Boolean.valueOf(ee.execute(tokenList).toJavaObject().toString())){
						System.out.println("错误;"+reason);
					}

				}
			}

			br.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IllegalExpressionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

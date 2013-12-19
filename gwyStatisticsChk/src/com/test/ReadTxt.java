package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadTxt {
	public static void main(String[] args) {
		try {
			// read file content from file
//			StringBuffer sb = new StringBuffer("");

			FileReader reader = new FileReader("source.txt");
			BufferedReader br = new BufferedReader(reader);

			String str = null;

			while ((str = br.readLine()) != null) {
//				sb.append(str + "/n");
//				System.out.println(str);
				
				if(!str.isEmpty()){
					String str1[] = str.split(";");
					
					String expression[] = str1[0].split(",");
					String reason = str1[1];
//					System.out.println(str1[0]+","+str1[1]+"\n");
				}
				
				
				
			}

			br.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

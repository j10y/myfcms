package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.base.DesEncrypter;

public class EncryptTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		 try {
//		 // Generate a temporary key. In practice, you would save this key.
//		 // See also Encrypting with DES Using a Pass Phrase.
//		 // SecretKey key = KeyGenerator.getInstance("DES").generateKey();
//		 // Create encrypter/decrypter class
//		 SecretKey key = DesEncrypter.open();
//		 DesEncrypter encrypter = new DesEncrypter(key);
//		
//		 // encrypter.saveKey(key);
//		
//		 System.out.println(key.toString());
//		
//		 // Encrypt
//		 String encrypted = encrypter.encrypt("WLHGWY");
//		 System.out.println("加密后数据：" + encrypted);
//		
//		 // Decrypt
//		 String decrypted = encrypter.decrypt("eqzHFgCohkxcQFbADhTR3lJRD6P0xvfzE+lcGfd/+Yor/uMQ+cXdOmEVwdJipK9t6XdsknxliT/m1z1OHJSKpA==");
//		 System.out.println("解密后数据：" + decrypted);
//		
//		 byte[] encoded = key.getEncoded();
//		
//		 SecretKey key2 = new SecretKeySpec(encoded, "DES");
//		 DesEncrypter encrypter2 = new DesEncrypter(key2);
//		 String de = encrypter2.decrypt(encrypted);
//		 System.out.println("新key解密测试" + de);
//		
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }

		EncryptTest et = new EncryptTest();
		et.encryptSource("sourceE0", "source0");
		et.encryptSource("sourceE1", "source1");
		et.encryptSource("sourceE1", "source2");
		et.encryptSource("sourceE3", "source3");
		
		
//		new EncryptTest().decryptSource();
	}

	public void decryptSource() {
		FileReader reader = null;
		BufferedReader br = null;
		SecretKey key = DesEncrypter.open();
		DesEncrypter encrypter = new DesEncrypter(key);

		try {
			reader = new FileReader("source1");

			br = new BufferedReader(reader);

			String str = null;

			while ((str = br.readLine()) != null) {
				String encrypted = encrypter.decrypt(str);
				System.out.println(encrypted+"\n");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (reader != null) {
					reader.close();
				}
				if (br != null) {
					br.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void encryptSource(String source,String taget) {
		FileReader reader = null;
		FileWriter writer = null;
		BufferedReader br = null;
		BufferedWriter bw = null;

		SecretKey key = DesEncrypter.open();
		DesEncrypter encrypter = new DesEncrypter(key);

		try {
			reader = new FileReader(source);
			writer = new FileWriter(taget);

			br = new BufferedReader(reader);
			bw = new BufferedWriter(writer);

			String str = null;

			while ((str = br.readLine()) != null) {
				String encrypted = encrypter.encrypt(str).replace("\r\n", "");
				bw.write(encrypted+"\n");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
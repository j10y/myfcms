package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

public class DesEncrypter {
	Cipher ecipher;
	Cipher dcipher;

	static String path = "key.dat";

	public DesEncrypter(SecretKey key) {
		try {
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);

		} catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (java.security.InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String str) {
		try {
			// Encode the string into bytes using utf-8
			byte[] GBK = str.getBytes("GBK");

			// Encrypt
			byte[] enc = ecipher.doFinal(GBK);

			// Encode bytes to base64 to get a string
			return new sun.misc.BASE64Encoder().encode(enc);
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			// Decode base64 to get bytes
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

			// Decrypt
			byte[] GBK = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(GBK, "GBK");
		} catch (javax.crypto.BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static void saveKey(SecretKey key, String path) {
//
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		File f = new File(path);
//		try {
//			fos = new FileOutputStream(f);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(key); // 括号内参数为要保存java对象
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//				fos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public static SecretKey open() {
		return open(path);
	}

//	public static void saveKey(SecretKey key) {
//		saveKey(key, path);
//	}

	public static SecretKey open(String path) {
		ObjectInputStream ois = null;
		try {
			InputStream in = DesEncrypter.class.getResourceAsStream("/" + path);
			ois = new ObjectInputStream(in);
			SecretKey key = (SecretKey) ois.readObject();// 强制类型转换

			return key;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

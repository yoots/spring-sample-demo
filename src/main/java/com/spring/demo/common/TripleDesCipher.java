/*
 * Copyright (c) 2012 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 모바일에서 넘어온 패스워드 암복호화....
 *
 * @author user
 * Created on 2012. 12. 28.
 */

public class TripleDesCipher {
	private static byte[] sharedkey = {
	    (byte)135, (byte)181, (byte)15, (byte)30, (byte)31, (byte)157, (byte)65, (byte)102, 
	    (byte)217, (byte)162, (byte)79, (byte)181, (byte)212, (byte)104, (byte)246, (byte)207, 
	    (byte)217, (byte)21, (byte)173, (byte)195, (byte)51, (byte)56, (byte)128, (byte)130
	};

	private static byte[] sharedvector = {
		(byte)64, (byte)249, (byte)131, (byte)251, (byte)161, (byte)102, (byte)215, (byte)78
	};

	public static String encrypt(String plaintext) throws Exception {
		setKey();
	    Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sharedkey, "DESede"), new IvParameterSpec(sharedvector));
		byte[] encrypted = c.doFinal(plaintext.getBytes("UTF-8"));
	    return Base64.encode(encrypted);
	}

	public static String decrypt(String ciphertext) throws Exception {
		setKey();
	    Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
	    c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(sharedkey, "DESede"), new IvParameterSpec(sharedvector));
		byte[] decrypted = c.doFinal(Base64.decode(ciphertext));
		return new String(decrypted, "UTF-8");
	}
	
	private static void setKey() {
		String[] key = Config.getProperty("tripledes.key").split(",");
		String[] vector = Config.getProperty("tripledes.vector").split(",");
		for (int i = 0; i < key.length; i++) {
			sharedkey[i] = (byte)Integer.parseInt(key[i]);
		}
		for (int i = 0; i < vector.length; i++) {
			sharedvector[i] = (byte)Integer.parseInt(vector[i]);
		}
	}
  
	/**
		 * @param args
		 * @throws Exception 
	*/
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String plaintext = "";
	    String ciphertext = encrypt(plaintext);
	    System.out.println(ciphertext);
	    System.out.println(decrypt(ciphertext));
	}
}


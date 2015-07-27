package com.mangosystem.rep.security.cryptography;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mangosystem.rep.util.ByteUtil;

public class Crypto {
	
	private Cipher cipher = null;
	
	private String algorithmName 	= "";
	private String keyGeneratorName = "";
	
	/**
	 * 
	 * @param algName (ex. AES/ECB/PKCS5Padding)
	 * @param keygenName (ex. AES)
	 */
	public Crypto(String algName, String keygenName) {
		this.algorithmName 		= algName.toUpperCase();
		this.keyGeneratorName 	= keygenName.toUpperCase();
	}
	
	
	/**
	 * <b>
	 * Java ™ Cryptography Architecture Standard Algorithm<br/>
	 * Auto Key Generator
	 * </b>
	 * @param algName Algorithm Name
	 * <br/> 
	 * AES, Blowfish, DES, DESede, DiffieHellman, DSA, OAEP, PBEWith(digest)And(encryption), PBE, RC2
	 * <br/>
	 * @return return key
	 * @throws NoSuchAlgorithmException
	 */
	public Key generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance( keyGeneratorName );
		keyGenerator.init(128);
	    SecretKey secretKey = keyGenerator.generateKey();
	    return secretKey;
	}
	
	
	/**
	 * DES Key Generator
	 * 
	 * @param cipherKey
	 * @return SecretKey
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public Key generateKey(String algName, byte[] cipherKey) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		if ("AES".equals(algName)) {
			SecretKeySpec keySpec = new SecretKeySpec(cipherKey, "AES");
			return keySpec;
		} else if ("DES".equals(algName)) {
			KeySpec keySpec = new DESKeySpec(cipherKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
			return secretKey;
		} else if ("DESede".equals(algName) || "TripleDES".equals(algName)) {
			KeySpec keySpec = new DESedeKeySpec(cipherKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algName);
			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
			return secretKey;
		} else {
			return generateKey();
		}
	}
	
	public static String generateHiddenKey16( int len ) {
		char[] charSet = new char[] {
			'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
		};

		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<len; i++){
			idx = (int)(charSet.length*Math.random());
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}
	
	public static String generateHiddenKey16() {
		return generateHiddenKey16(16);
	}
	
	public static String generateHiddenKey( int len ) {
		char[] charSet = new char[]{
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<len; i++){
			idx = (int)(charSet.length*Math.random());
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}
	
	
	/**
	 * @param encryptData
	 * @param cipherKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 */
	public byte[] encryptAES(String encryptData, String cipherKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		cipher = Cipher.getInstance( algorithmName );
		Key key = generateKey("AES", cipherKey.getBytes());
		
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] encrypt = cipher.doFinal( encryptData.getBytes() );
		//String encryptResult = CipherUtil.toHexStringFromBytes(encrypt);
		
		return encrypt;
	}
	

	/**
	 * @param encryptCode
	 * @param cipherKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 */
	public String decryptAES(byte[] encryptCode, String cipherKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		cipher = Cipher.getInstance( algorithmName );
		Key key = generateKey("AES", cipherKey.getBytes());
		
		cipher.init(Cipher.DECRYPT_MODE, key );
		byte[] decrypt = cipher.doFinal(encryptCode);
		//String decryptResult = new String(decrypt);
		
		return new String(decrypt);
	}
	
	
	/**
	 * Cipher Encrypt - AES128 (AES/ECB/PKCS5Padding)
	 * @param plainText
	 * @param cipherText
	 * @param base64
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String encryptAES128(String plainText, String cipherText, boolean base64) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec keySpec = new SecretKeySpec(cipherText.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			
			byte[] encrypt = cipher.doFinal( plainText.getBytes() );
			
			if (base64)
				return new BASE64Encoder().encode(encrypt);
			else 
				return ByteUtil.toHexStringFromBytes(encrypt);
		} catch(Exception e) {
			e.printStackTrace();
			return plainText;
		}
	}
	
	
	/**
	 * Cipher Decrypt - AES128 (AES/ECB/PKCS5Padding)
	 * @param encryptCode
	 * @param cipherText
	 * @param base64
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String decryptAES128(String encryptCode, String cipherText, boolean base64)  {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(cipherText.getBytes(), "AES");
			
			cipher.init(Cipher.DECRYPT_MODE, key );
			
			byte[] decrypt = null;
			
			if (base64)
				decrypt = cipher.doFinal( new BASE64Decoder().decodeBuffer(encryptCode) );
			else
				decrypt = cipher.doFinal( ByteUtil.toBytesFromHexString(encryptCode) );
			
			return new String(decrypt);
		} catch(Exception e) {
			e.printStackTrace();
			return encryptCode;
		}
	}
	
	
	/**
	 * Encrypt SHA-256 (Hash algorithm)
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSHA256(String value) throws NoSuchAlgorithmException {
		String encrypt = "";
		
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		sha256.update(value.getBytes());
		 
        byte[] digestByte = sha256.digest();
        for (int i=0; i<digestByte.length; i++) {
        	encrypt += Integer.toHexString(digestByte[i] & 0xF0);
        }
        return encrypt;
	}
	

	/**
	 * Encrypt MD5 (Hash algorithm)
	 * @param value
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptMD5(String value) throws NoSuchAlgorithmException {
		String encrypt = "";
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(value.getBytes());
		 
        byte[] digestByte = md5.digest();
        for (int i=0; i<digestByte.length; i++) {
        	encrypt += Integer.toHexString(digestByte[i] & 0xF0);
        }
        return encrypt;
	}
	
	/**
	 * Encrypt BASE64
	 * @param value
	 * @return
	 */
	public static String encryptBASE64(String value) {
		return new BASE64Encoder().encode(value.getBytes());
	}
	
}
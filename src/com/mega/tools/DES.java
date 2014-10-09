package com.mega.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES
{
	/**
	 * 
	 * 函数名称 : generateKey 功能描述 : 生成密钥 参数及返回值说明：
	 * 
	 * @return DES算法密钥
	 * 
	 *         修改记录： 日期：2013-5-19 下午1:05:38 修改人：kcx 描述 ：
	 * 
	 */
	public static byte[] generateKey()
	{
		try
		{

			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 生成一个DES算法的KeyGenerator对象
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);

			// 生成密钥
			SecretKey secretKey = kg.generateKey();

			// 获取密钥数据
			byte[] key = secretKey.getEncoded();

			return key;
		} catch (NoSuchAlgorithmException e)
		{
			System.err.println("DES算法，生成密钥出错!");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 函数名称 : encrypt 功能描述 : 加密函数 参数及返回值说明：
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return 返回加密后的数据
	 * 
	 *         修改记录： 日期：2013-5-19 下午1:05:10 修改人：kcx 描述 ：
	 * 
	 */
	public static byte[] encrypt(byte[] data, byte[] key)
	{

		try
		{

			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in ECB mode
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

			// 执行加密操作
			byte encryptedData[] = cipher.doFinal(data);

			return encryptedData;
		} catch (Exception e)
		{
			System.err.println("DES算法，加密数据出错!");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 函数名称 : decrypt 功能描述 : 解密函数 参数及返回值说明：
	 * 
	 * @param data
	 *            解密数据
	 * @param key
	 *            密钥
	 * @return 返回解密后的数据
	 * 
	 *         修改记录： 日期：2013-5-19 下午1:04:44 修改人：kcx 描述 ：
	 * 
	 */
	public static byte[] decrypt(byte[] data, byte[] key)
	{
		try
		{
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// byte rawKeyData[] = /* 用某种方法获取原始密匙数据 */;

			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in ECB mode
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

			// 正式执行解密操作
			byte decryptedData[] = cipher.doFinal(data);

			return decryptedData;
		} catch (Exception e)
		{
			System.err.println("DES算法，解密出错。");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 函数名称 : CBCEncrypt 功能描述 : 加密函数 参数及返回值说明：
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @param iv
	 * @return 返回加密后的数据
	 * 
	 *         修改记录： 日期：2013-5-19 下午1:03:52 修改人：kcx 描述 ：
	 * 
	 */
	public static byte[] CBCEncrypt(byte[] data, byte[] key, byte[] iv)
	{

		try
		{
			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// 若采用NoPadding模式，data长度必须是8的倍数
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

			// 用密匙初始化Cipher对象
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);

			// 执行加密操作
			byte encryptedData[] = cipher.doFinal(data);

			return encryptedData;
		} catch (Exception e)
		{
			System.err.println("DES算法，加密数据出错!");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 函数名称 : CBCDecrypt 功能描述 : 解密函数 参数及返回值说明：
	 * 
	 * @param data
	 *            解密数据
	 * @param key
	 *            密钥
	 * @param iv
	 * @return 返回解密后的数据
	 * 
	 *         修改记录： 日期：2013-5-19 下午1:03:08 修改人：kcx 描述 ：
	 * 
	 */
	public static byte[] CBCDecrypt(byte[] data, byte[] key, byte[] iv)
	{
		try
		{
			// 从原始密匙数据创建一个DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(key);

			// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
			// 一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in CBC mode
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// 若采用NoPadding模式，data长度必须是8的倍数
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

			// 用密匙初始化Cipher对象
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, param);

			// 正式执行解密操作
			byte decryptedData[] = cipher.doFinal(data);

			return decryptedData;
		} catch (Exception e)
		{
			System.err.println("DES算法，解密出错。");
			e.printStackTrace();
		}

		return null;
	}


	public static String getContent(String filePath, String key) throws IOException
	{
		byte[] decryptkey = key.getBytes();
		FileInputStream in = new FileInputStream(filePath);

		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		System.out.println("bytes available:" + in.available());

		byte[] temp = new byte[1024];

		int size = 0;

		while ((size = in.read(temp)) != -1)
		{
			out.write(temp, 0, size);
		}

		in.close();

		byte[] bytes = out.toByteArray();
		
//		System.out.println("bytes size got is:" + bytes.length);

		return new String(decrypt(bytes, decryptkey));
	}

	
	public static void createFile(String path, String content, String key) throws IOException
	{
		byte[] encryptkey = key.getBytes();
		byte[] bt = encrypt(content.getBytes(), encryptkey);
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(bt);
		fos.close();
	}

}
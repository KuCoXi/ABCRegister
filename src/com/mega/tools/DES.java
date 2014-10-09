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
	 * �������� : generateKey �������� : ������Կ ����������ֵ˵����
	 * 
	 * @return DES�㷨��Կ
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-5-19 ����1:05:38 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static byte[] generateKey()
	{
		try
		{

			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom sr = new SecureRandom();

			// ����һ��DES�㷨��KeyGenerator����
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);

			// ������Կ
			SecretKey secretKey = kg.generateKey();

			// ��ȡ��Կ����
			byte[] key = secretKey.getEncoded();

			return key;
		} catch (NoSuchAlgorithmException e)
		{
			System.err.println("DES�㷨��������Կ����!");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * �������� : encrypt �������� : ���ܺ��� ����������ֵ˵����
	 * 
	 * @param data
	 *            ��������
	 * @param key
	 *            ��Կ
	 * @return ���ؼ��ܺ������
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-5-19 ����1:05:10 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static byte[] encrypt(byte[] data, byte[] key)
	{

		try
		{

			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom sr = new SecureRandom();

			// ��ԭʼ��Կ���ݴ���DESKeySpec����
			DESKeySpec dks = new DESKeySpec(key);

			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			// һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in ECB mode
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");

			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

			// ִ�м��ܲ���
			byte encryptedData[] = cipher.doFinal(data);

			return encryptedData;
		} catch (Exception e)
		{
			System.err.println("DES�㷨���������ݳ���!");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * �������� : decrypt �������� : ���ܺ��� ����������ֵ˵����
	 * 
	 * @param data
	 *            ��������
	 * @param key
	 *            ��Կ
	 * @return ���ؽ��ܺ������
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-5-19 ����1:04:44 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static byte[] decrypt(byte[] data, byte[] key)
	{
		try
		{
			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom sr = new SecureRandom();

			// byte rawKeyData[] = /* ��ĳ�ַ�����ȡԭʼ�ܳ����� */;

			// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
			DESKeySpec dks = new DESKeySpec(key);

			// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
			// һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in ECB mode
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");

			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

			// ��ʽִ�н��ܲ���
			byte decryptedData[] = cipher.doFinal(data);

			return decryptedData;
		} catch (Exception e)
		{
			System.err.println("DES�㷨�����ܳ���");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * �������� : CBCEncrypt �������� : ���ܺ��� ����������ֵ˵����
	 * 
	 * @param data
	 *            ��������
	 * @param key
	 *            ��Կ
	 * @param iv
	 * @return ���ؼ��ܺ������
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-5-19 ����1:03:52 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static byte[] CBCEncrypt(byte[] data, byte[] key, byte[] iv)
	{

		try
		{
			// ��ԭʼ��Կ���ݴ���DESKeySpec����
			DESKeySpec dks = new DESKeySpec(key);

			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			// һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// ������NoPaddingģʽ��data���ȱ�����8�ı���
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

			// ���ܳ׳�ʼ��Cipher����
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);

			// ִ�м��ܲ���
			byte encryptedData[] = cipher.doFinal(data);

			return encryptedData;
		} catch (Exception e)
		{
			System.err.println("DES�㷨���������ݳ���!");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * �������� : CBCDecrypt �������� : ���ܺ��� ����������ֵ˵����
	 * 
	 * @param data
	 *            ��������
	 * @param key
	 *            ��Կ
	 * @param iv
	 * @return ���ؽ��ܺ������
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-5-19 ����1:03:08 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static byte[] CBCDecrypt(byte[] data, byte[] key, byte[] iv)
	{
		try
		{
			// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
			DESKeySpec dks = new DESKeySpec(key);

			// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
			// һ��SecretKey����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// using DES in CBC mode
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			// ������NoPaddingģʽ��data���ȱ�����8�ı���
			// Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

			// ���ܳ׳�ʼ��Cipher����
			IvParameterSpec param = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, param);

			// ��ʽִ�н��ܲ���
			byte decryptedData[] = cipher.doFinal(data);

			return decryptedData;
		} catch (Exception e)
		{
			System.err.println("DES�㷨�����ܳ���");
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
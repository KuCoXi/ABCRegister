package com.mega.pack;

import java.io.UnsupportedEncodingException;

public class ABCElement
{
	private String content;
	private String name;
	private int length;
	private int position;
	private boolean required;
	public static final int SPACE = 0x01;
	public static final int ZERO = 0x02;

	/**
	 * 
	 * ���캯����ABCElement ��������: ���Ԫ���࣬����Ҫ����򲹿ո� ����˵����
	 * 
	 * @param name
	 *            Ԫ������
	 * @param content
	 *            Ԫ������
	 * @param length
	 *            Ԫ�س���
	 * @param position
	 *            Ԫ���ڱ����е�λ��
	 * @param required
	 *            �Ƿ�Ϊ���� true���� false����
	 */
	public ABCElement(String name, String content, int length, int position, boolean required)
	{
		this.name = name;
		this.length = length;
		this.position = position;
		this.required = required;
		try
		{
			if (content.getBytes("GBK").length > length)
			{
				this.content = content.substring(content.getBytes("GBK").length - length);
			} else
			{
				this.content = content;
			}
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ���캯����ABCElement ��������: ���Ԫ���࣬��Ҫ����򲹿ո� ����˵����
	 * 
	 * @param name
	 *            Ԫ������
	 * @param content
	 *            Ԫ������
	 * @param length
	 *            Ԫ�س���
	 * @param position
	 *            Ԫ���ڱ����е�λ��
	 * @param required
	 *            �Ƿ�Ϊ���� true���� false����
	 * @param fillingtype
	 *            ������� ZERO:���� SPACE:�Ҳ��ո�
	 */
	public ABCElement(String name, String content, int length, int position, boolean required, int fillingtype)
	{
		this.name = name;
		this.length = length;
		this.position = position;
		this.required = required;
		try
		{
			if (content.length() < length)
			{
				switch (fillingtype)
				{
				case ZERO:
					StringBuffer zeroBuffer = new StringBuffer();

					for (int i = 0; i < length - content.getBytes("GBK").length; i++)
					{
						zeroBuffer.append('0');
					}

					this.content = zeroBuffer.toString() + content;
					break;
				case SPACE:
					StringBuffer spaceBuffer = new StringBuffer();
					for (int i = 0; i < length - content.getBytes("GBK").length; i++)
					{
						spaceBuffer.append(' ');
					}
					this.content = content + spaceBuffer.toString();
					break;
				default:
					break;
				}
			} else
			{
				this.content = content;
			}
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLength()
	{
		return length;
	}

	public boolean isRequired()
	{
		return required;
	}

	public String getName()
	{
		return name;
	}

	public int getPosition()
	{
		return position;
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return this.content;
	}

}

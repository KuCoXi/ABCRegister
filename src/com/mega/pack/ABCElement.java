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
	 * 构造函数：ABCElement 函数功能: 填充元素类，不需要补零或补空格 参数说明：
	 * 
	 * @param name
	 *            元素名称
	 * @param content
	 *            元素内容
	 * @param length
	 *            元素长度
	 * @param position
	 *            元素在报文中的位置
	 * @param required
	 *            是否为必须 true：是 false：否
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
	 * 构造函数：ABCElement 函数功能: 填充元素类，需要补零或补空格 参数说明：
	 * 
	 * @param name
	 *            元素名称
	 * @param content
	 *            元素内容
	 * @param length
	 *            元素长度
	 * @param position
	 *            元素在报文中的位置
	 * @param required
	 *            是否为必须 true：是 false：否
	 * @param fillingtype
	 *            填充类型 ZERO:左补零 SPACE:右补空格
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

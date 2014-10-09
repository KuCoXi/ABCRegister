package com.mega.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mega.pack.Card;
import com.mega.pack.Knowledge;
import com.mega.pack.Sale;

public class XmlTools
{

	/**
	 * 
	 * 函数名称 : readStringXmlOut 功能描述 : 参数及返回值说明：
	 * 
	 * @param xml
	 * @return
	 * 
	 *         修改记录： 日期：2013-4-28 下午3:37:04 修改人：kcx 描述 ：
	 * @throws IOException
	 * 
	 * 
	 */
	public static Map<String, String> readStringXmlOut(String path) throws IOException
	{
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		String xml = getContent(path, MyConstants.WORKKEY);
		try
		{
			// 将字符串转为XML
			doc = DocumentHelper.parseText(xml);
			// 获取根节点
			Element rootElt = doc.getRootElement();

			Iterator<?> iter = rootElt.elementIterator();
			// 遍历根节点
			while (iter.hasNext())
			{
				Element recordEle = (Element) iter.next();
				// 拿到根节点下的子节点title和value
				String title = recordEle.getName();
				String value = recordEle.getText();
				if (!title.startsWith("Card"))
				{
					System.out.println(title + "——" + value);
					map.put(title, value);
				}
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * 函数名称 : readCardTypeXmlOut 功能描述 : 参数及返回值说明：
	 * 
	 * @param xml
	 * @return
	 * 
	 *         修改记录： 日期：2013-4-28 下午3:36:40 修改人：kcx 描述 ：
	 * @throws IOException
	 * @throws DocumentException
	 * 
	 */
	public static List<Card> readCardTypeXmlOut(String path) throws IOException, DocumentException
	{
		List<Card> cards = new ArrayList<Card>();
		String xml = getContent(path, MyConstants.WORKKEY);
		Document doc = null;
		// try
		// {
		// 将字符串转为XML
		doc = DocumentHelper.parseText(xml);
		// 获取根节点
		Element rootElt = doc.getRootElement();
		Iterator<?> iter = rootElt.elementIterator();
		// 遍历根节点
		while (iter.hasNext())
		{
			Element recordEle = (Element) iter.next();
			Iterator<?> iter1 = recordEle.elementIterator();
			// 拿到根节点下的子节点title和value
			String title = recordEle.getName();
			Pattern p = Pattern.compile("Card\\d");
			Matcher m = p.matcher(title);
			int i = 0;
			while (m.find())
			{
				i++;
			}
			if (i > 0)
			{
				Card card = new Card();
				card.setCardDetails(recordEle.getText().trim());
				while (iter1.hasNext())
				{
					Element cardDetail = (Element) iter1.next();
					String t1 = cardDetail.getName();
					String v1 = cardDetail.getText();
					if (t1.equals("aName"))
					{
						card.setName(v1);
					} else if (t1.equals("aCode"))
					{
						card.setCode(v1);
					} else if (t1.equals("aMedia"))
					{
						card.setMedia(v1);
					} else if (t1.equals("aGroup"))
					{
						card.setGroup(v1);
					} else if (t1.equals("aCardGrade"))
					{
						card.setCardGrade(v1);
					} else if (t1.equals("aCardBranch"))
					{
						card.setCardBranch(v1);
					} else if (t1.equals("aCardBz"))
					{
						card.setCardBz(v1);
					} else if (t1.equals("aDouble_Cur"))
					{
						card.setDouble_Cur(v1);
					} else if (t1.equals("aPROD_KIND"))
					{
						card.setPROD_KIND(v1.toUpperCase());
					} else if (t1.equals("aColorCard"))
					{
						card.setColorCard(v1);
					} else if (t1.equals("aCardSingleCode"))
					{
						card.setCardSingleCode(v1);
					} else if (t1.equals("aCardKind"))
					{
						card.setStudentCard(v1);
					} else if (t1.equals("aCardMard"))
					{
						card.setLowLevel(v1);
					}
					else if (t1.equals("aLayout"))
					{
						List<String> list = new ArrayList<String>();
						String[] layout = v1.trim().split(",");
						for (int j = 0; j < layout.length; j++)
						{
							if (!layout[j].equals("") && !layout[j].trim().equals(""))
							{
								list.add(layout[j].trim());
							}
						}
						String[] strings = new String[list.size()];
						for (int a = 0; a < list.size(); a++)
						{
							strings[a] = list.get(a);
						}
						card.setCardLayout(strings);
					}
				}
				cards.add(card);
			}
		}
		return cards;
	}

	public static List<Sale> readSaleXmlOut(String path) throws IOException, DocumentException
	{
		List<Sale> sales = new ArrayList<Sale>();
		String xml = getContent(path, MyConstants.WORKKEY);
		Document doc = null;
		doc = DocumentHelper.parseText(xml);
		// 获取根节点
		Element rootElt = doc.getRootElement();
		Iterator<?> iter = rootElt.elementIterator();
		// 遍历根节点
		while (iter.hasNext())
		{
			Element recordEle = (Element) iter.next();
			Iterator<?> iter1 = recordEle.elementIterator();
			// 拿到根节点下的子节点title和value
			String title = recordEle.getName();
			Pattern p = Pattern.compile("Sale\\d");
			Matcher m = p.matcher(title);
			int i = 0;
			while (m.find())
			{
				i++;
			}
			if (i > 0)
			{
				Sale sale = new Sale();
				List<Card> cards = new ArrayList<Card>();
				while (iter1.hasNext())
				{
					Element saleDetail = (Element) iter1.next();
					String t1 = saleDetail.getName();
					String v1 = saleDetail.getText();
					if (t1.equals("aTitle"))
					{
						sale.setTitle(v1);
					}
					if (t1.equals("aData"))
					{
						sale.setData(v1);
					}
					Iterator<?> iter2 = saleDetail.elementIterator();
					Pattern p1 = Pattern.compile("SD\\d");
					Matcher m1 = p1.matcher(t1);
					int a = 0;
					while (m1.find())
					{
						a++;
					}
					if (a > 0)
					{
						Card card = new Card();
						while (iter2.hasNext())
						{
							Element cardDetail = (Element) iter2.next();
							String title1 = cardDetail.getName();
							String value1 = cardDetail.getText();
							if (title1.equals("aCode"))
							{
								card.setPROD_KIND(value1);
							}
						}
						cards.add(card);
					}
				}
				sale.setCards(cards);
				sales.add(sale);
			}
		}
		return sales;
	}

	public static List<Knowledge> readKnowledgeXmlOut(String path) throws IOException, DocumentException
	{
		List<Knowledge> knowledges = new ArrayList<Knowledge>();
		String xml = getContent(path, MyConstants.WORKKEY);
		Document doc = null;
		doc = DocumentHelper.parseText(xml);
		// 获取根节点
		Element rootElt = doc.getRootElement();
		Iterator<?> iter = rootElt.elementIterator();
		// 遍历根节点
		while (iter.hasNext())
		{
			Element recordEle = (Element) iter.next();
			Iterator<?> iter1 = recordEle.elementIterator();
			// 拿到根节点下的子节点title和value
			String title = recordEle.getName();
			Pattern p = Pattern.compile("Knowledge\\d");
			Matcher m = p.matcher(title);
			int i = 0;
			while (m.find())
			{
				i++;
			}
			if (i > 0)
			{
				Knowledge knowledge = new Knowledge();
				while (iter1.hasNext())
				{
					Element cardDetail = (Element) iter1.next();
					String t1 = cardDetail.getName();
					String v1 = cardDetail.getText();
					if (t1.equals("aTitle"))
					{
						knowledge.setTitle(v1);
					}
					if (t1.equals("aData"))
					{
						knowledge.setData(v1);
					}
				}
				knowledges.add(knowledge);
			}
		}
		return knowledges;
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

		// System.out.println("bytes size got is:" + bytes.length);

		return new String(DES.decrypt(bytes, decryptkey), "GBK");
		// System.out.println("哈哈哈哈"+new String(bytes,"GBK"));
		// return new String(bytes,"GBK");
	}

	public static Card getCard(String path, String name) throws IOException
	{
		Card card = new Card();
		String xml = getContent(path, MyConstants.WORKKEY);
		Document doc = null;
		try
		{
			// 将字符串转为XML
			doc = DocumentHelper.parseText(xml);
			// 获取根节点
			Element rootElt = doc.getRootElement();
			Iterator<?> iter = rootElt.elementIterator();
			// 遍历根节点
			while (iter.hasNext())
			{
				Element recordEle = (Element) iter.next();
				Iterator<?> iter1 = recordEle.elementIterator();
				// 拿到根节点下的子节点title和value
				String title = recordEle.getName();
				Pattern p = Pattern.compile("Card\\d");
				Matcher m = p.matcher(title);
				int i = 0;
				while (m.find())
				{
					i++;
				}
				if (i > 0)
				{
					boolean flag = false;
					while (iter1.hasNext())
					{
						Element cardDetail = (Element) iter1.next();
						String t1 = cardDetail.getName();
						String v1 = cardDetail.getText();
						if (t1.equals("aName") && v1.equals(name))
						{
							flag = true;
							card.setName(v1);
						}
						if (t1.equals("aCode") && flag)
						{
							card.setCode(v1);
						}
						if (t1.equals("aMedia") && flag)
						{
							card.setMedia(v1);
						}
						if (t1.equals("aGroup") && flag)
						{
							card.setGroup(v1);
						}
						if (t1.equals("aCardGrade") && flag)
						{
							card.setCardGrade(v1);
						}
						if (t1.equals("aCardBranch") && flag)
						{
							card.setCardBranch(v1);
						}
						if (t1.equals("aCardBz") && flag)
						{
							card.setCardBz(v1);
						}
						if (t1.equals("aDouble_Cur") && flag)
						{
							card.setDouble_Cur(v1);
						}
						if (t1.equals("aPROD_KIND") && flag)
						{
							card.setPROD_KIND(v1.toUpperCase());
						}
						if (t1.equals("aColorCard") && flag)
						{
							card.setColorCard(v1);
						}
						if (t1.equals("aCardSingleCode") && flag)
						{
							card.setCardSingleCode(v1);
						}
						if (t1.equals("aCardKind") && flag)
						{
							card.setStudentCard(v1);
						}
						if (t1.equals("aCardMard") && flag)
						{
							card.setLowLevel(v1);
						}
						if (t1.equals("aLayout") && flag)
						{
							List<String> list = new ArrayList<String>();
							String[] layout = v1.trim().split(",");
							for (int j = 0; j < layout.length; j++)
							{
								if (!layout[j].equals("") && !layout[j].trim().equals(""))
								{
									list.add(layout[j].trim());
								}
							}
							String[] strings = new String[list.size()];
							for (int a = 0; a < list.size(); a++)
							{
								strings[a] = list.get(a);
							}
							card.setCardLayout(strings);
						}
						if (flag)
						{
							card.setCardDetails(recordEle.getText().trim());
						}
					}
				}
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return card;
	}

}

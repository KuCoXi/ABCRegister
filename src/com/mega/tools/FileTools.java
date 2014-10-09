package com.mega.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import android.os.Environment;

import com.mega.pack.CardMsg;
import com.mega.pack.MyElement;

public class FileTools
{

	/**
	 * 
	 *  函数名称 : RecursionDeleteFile
	 *  功能描述 : 递归删除文件/文件夹 
	 *  参数及返回值说明：
	 *  	@param file 要删除的文件/文件夹
	 *
	 *  修改记录：
	 *  	日期：2013-3-18 下午3:16:55	修改人：kcx
	 *  	描述	：
	 *
	 */
	public static void RecursionDeleteFile(File file)
	{
		if (!file.exists())
		{
			return;
		}
		if (file.isFile())
		{
			file.delete();
			return;
		}
		if (file.isDirectory())
		{
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0)
			{
				file.delete();
				return;
			}
			for (File f : childFile)
			{
				RecursionDeleteFile(f);
			}
		}
	}
	
	/**
	 * 
	 *  函数名称 : isSDCardReady
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-3-21 上午9:17:28	修改人：kcx
	 *  	描述	：
	 *
	 */
	public static final boolean isSDCardReady()
	{
        String STATE = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(STATE) ;
	}
	
	/**
	 * 
	 *  函数名称 : hasImg
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param path
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-3-21 上午10:51:15	修改人：kcx
	 *  	描述	：
	 *
	 */
	public static final boolean hasImg(String path)
	{
		boolean result = false;
		if (!new File(path).exists())
		{
			return result;
		}
		String[] list = new File(path).list();
		System.out.println(list.length);
		for (int i = 0; i < list.length; i++)
		{
			if (list[i].endsWith(".jpg"))
			{
				result = true;
			}
		}
		
		return result;
		
	}
	
	
	public static void encryptToFile(String path,String content,String key) throws IOException
	{
		RandomAccessFile raf;
		byte[] encryptkey;
			encryptkey = key.getBytes("GBK");
			raf = new RandomAccessFile(path, "rw");
			raf.seek(raf.length());
			byte[] contents = DES.encrypt(autofix8(content).getBytes("GBK"), encryptkey);
			byte[] length = new byte[8];
			String dlength = String.valueOf(contents.length);
			length = autofix8(dlength).getBytes("GBK");
			raf.write(length);
			raf.write(contents);
			raf.close();
	}
	
	public static String autofix8(String source) throws UnsupportedEncodingException
	{
		StringBuffer str = new StringBuffer();
		int a = source.getBytes("GBK").length % 8;
		if (8 - a > 0)
		{
			str.append(source);
			for (int i = 0; i < 8 - a; i++)
			{
				str.append(" ");
			}
		}
		return str.toString();
	}
	
	public static boolean makeXmlFile(String path)
	{
		if (new File(path).exists())
		{
			return true;
		}
		Document document = DocumentHelper.createDocument();
		Element works = document.addElement("works");
		works.addComment("申请办卡信息集");
		try
		{
			OutputFormat oFormat = new OutputFormat().createPrettyPrint();
			oFormat.createCompactFormat();
			oFormat.setEncoding("UTF-8");
			XMLWriter output = new XMLWriter(new FileOutputStream(path), oFormat);
			output.write(document);
			output.close();
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static boolean deleteMsg(String path, String idnum, String appnum)
	{
		boolean res = false;
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try
		{
			document = saxReader.read(new File(path));
		} catch (DocumentException e)
		{
			System.out.println(e);
			return false;
		}
		
		Element works = document.getRootElement();
		List l = works.elements();
		List<Element> de = new ArrayList<Element>();
		for (int i = 0; i < l.size(); i++)
		{
			Element e = (Element) l.get(i);
			String app_num = e.attributeValue("APP_NUM").trim();
			String id_num = e.elementTextTrim("CERT_NO");
			if (app_num.equals(appnum)&&id_num.equals(idnum))
			{
				de.add(e);
			}
		}
		
		for (Element element : de)
		{
			res = works.remove(element);
		}
		
		try
		{
			OutputFormat oFormat = new OutputFormat().createPrettyPrint();
			oFormat.createCompactFormat();
			oFormat.setEncoding("UTF-8");
			XMLWriter output = new XMLWriter(new FileOutputStream(path), oFormat);
			output.write(document);
			output.close();
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
		return res;
	}
	
	public static boolean addElements(List<MyElement> list,String path)
	{
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try
		{
			document = saxReader.read(new File(path));
		} catch (DocumentException e)
		{
			System.out.println(e);
			return false;
		}
		Element works = document.getRootElement();
		Element work = works.addElement("work");
		for (MyElement myElement : list)
		{
			if (myElement.getName().equals("APP_NUM"))
			{
				work.addAttribute(myElement.getName(), myElement.getValue());
			}
			else {
				Element element = work.addElement(myElement.getName());
				element.setText(myElement.getValue());
			}
		}
		try
		{
			OutputFormat oFormat = new OutputFormat().createPrettyPrint();
			oFormat.createCompactFormat();
			oFormat.setEncoding("UTF-8");
			XMLWriter output = new XMLWriter(new FileOutputStream(path), oFormat);
			output.write(document);
			output.close();
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static List<CardMsg> getCardMsgList(String path)
	{
		List<CardMsg> cList = new ArrayList<CardMsg>();
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try
		{
			document = saxReader.read(new File(path));
		} catch (DocumentException e)
		{
			System.out.println(e);
			return null;
		}
		
		Element works = document.getRootElement();
		Iterator<?> iter = works.elementIterator("work");
		while (iter.hasNext())
		{
			CardMsg cMsg = new CardMsg();
			Element work = (Element) iter.next();
			Iterator<?> attrList = work.attributeIterator();
			while (attrList.hasNext()) {
                Attribute attr = (Attribute) attrList.next();
                cMsg.setAPP_NUM(attr.getValue().trim());
            }
			
			Iterator<?> eleIte = work.elementIterator();
            while (eleIte.hasNext()) {
                Element ele = (Element) eleIte.next();
                if (ele.getName().equals("CARD_NAME"))
				{
					cMsg.setCARD_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("ISSTUDENT")) {
					if (ele.getTextTrim().equals("1"))
					{
						cMsg.setISSTUDENT(false);
					}else {
						cMsg.setISSTUDENT(true);
					}
				}else if (ele.getName().equals("APPFORM_TYPE")) {
					cMsg.setAPPFORM_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("APPFORM_CON")) {
					cMsg.setAPPFORM_CON(ele.getTextTrim());
				}else if (ele.getName().equals("APPINFO_TYPE")) {
					cMsg.setAPPINFO_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("NET_NODE_NO")) {
					cMsg.setNET_NODE_NO(ele.getTextTrim());
				}else if (ele.getName().equals("PROD_KIND")) {
					cMsg.setPROD_KIND(ele.getTextTrim());
				}else if (ele.getName().equals("CARD_MARK")) {
					cMsg.setCARD_MARK(ele.getTextTrim());
				}else if (ele.getName().equals("CARD_TYPE")) {
					cMsg.setCARD_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("CARD_KIND")) {
					cMsg.setCARD_KIND(ele.getTextTrim());
				}else if (ele.getName().equals("NORMAL_CARD")) {
					cMsg.setNORMAL_CARD(ele.getTextTrim());
				}else if (ele.getName().equals("DOUBLE_CUR")) {
					cMsg.setDOUBLE_CUR(ele.getTextTrim());
				}else if (ele.getName().equals("APP_LMT")) {
					cMsg.setAPP_LMT(ele.getTextTrim());
				}else if (ele.getName().equals("COLOR_CARD")) {
					cMsg.setCOLOR_CARD(ele.getTextTrim());
				}else if (ele.getName().equals("EMPLOYEE")) {
					cMsg.setEMPLOYEE(ele.getTextTrim());
				}else if (ele.getName().equals("APP_CUR")) {
					cMsg.setAPP_CUR(ele.getTextTrim());
				}else if (ele.getName().equals("CUST_NAME")) {
					cMsg.setCUST_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("LAST_NAME")) {
					cMsg.setLAST_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("FIRST_NAME")) {
					cMsg.setFIRST_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("CUST_SEX")) {
					cMsg.setCUST_SEX(ele.getTextTrim());
				}else if (ele.getName().equals("BIRTHDAY")) {
					cMsg.setBIRTHDAY(ele.getTextTrim());
				}else if (ele.getName().equals("MARR_STAT")) {
					cMsg.setMARR_STAT(ele.getTextTrim());
				}else if (ele.getName().equals("NATIONALITY")) {
					cMsg.setNATIONALITY(ele.getTextTrim());
				}else if (ele.getName().equals("FAMI_MEMBER")) {
					cMsg.setFAMI_MEMBER(ele.getTextTrim());
				}else if (ele.getName().equals("CERT_TYPE")) {
					cMsg.setCERT_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("CERT_NO")) {
					cMsg.setCERT_NO(ele.getTextTrim());
				}else if (ele.getName().equals("CERT_NAME")) {
					cMsg.setCERT_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("EDU_DEGR")) {
					cMsg.setEDU_DEGR(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_ADDR")) {
					cMsg.setPRE_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_CITY_CODE")) {
					cMsg.setPRE_CITY_CODE(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_POST")) {
					cMsg.setPRE_POST(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_YEARS")) {
					cMsg.setPRE_YEARS(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_ZONE_NO")) {
					cMsg.setPRE_ZONE_NO(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_PHONE")) {
					cMsg.setPRE_PHONE(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_MOBILE")) {
					cMsg.setPRE_MOBILE(ele.getTextTrim());
				}else if (ele.getName().equals("EMAIL")) {
					cMsg.setEMAIL(ele.getTextTrim());
				}else if (ele.getName().equals("PRE_REG_ADDR")) {
					cMsg.setPRE_REG_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("REG_ADDR")) {
					cMsg.setREG_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("REG_POST")) {
					cMsg.setREG_POST(ele.getTextTrim());
				}else if (ele.getName().equals("PER_INCOME")) {
					cMsg.setPER_INCOME(ele.getTextTrim());
				}else if (ele.getName().equals("AVE_INCOME")) {
					cMsg.setAVE_INCOME(ele.getTextTrim());
				}else if (ele.getName().equals("HOU_STAT")) {
					cMsg.setHOU_STAT(ele.getTextTrim());
				}else if (ele.getName().equals("HOU_MON_LOAN")) {
					cMsg.setHOU_MON_LOAN(ele.getTextTrim());
				}else if (ele.getName().equals("CAR_NO")) {
					cMsg.setCAR_NO(ele.getTextTrim());
				}else if (ele.getName().equals("CAR_TYPE")) {
					cMsg.setCAR_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("CAR_AGE")) {
					cMsg.setCAR_AGE(ele.getTextTrim());
				}else if (ele.getName().equals("CAR_MON_LOAN")) {
					cMsg.setCAR_MON_LOAN(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_NAME")) {
					cMsg.setCOMP_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_ADDR")) {
					cMsg.setCOMP_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_CITY_CODE")) {
					cMsg.setCOMP_CITY_CODE(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_POST")) {
					cMsg.setCOMP_POST(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_ZONE_NO")) {
					cMsg.setCOMP_ZONE_NO(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_PHONE")) {
					cMsg.setCOMP_PHONE(ele.getTextTrim());
				}else if (ele.getName().equals("COMP_CHAR")) {
					cMsg.setCOMP_CHAR(ele.getTextTrim());
				}else if (ele.getName().equals("TRADE_KIND")) {
					cMsg.setTRADE_KIND(ele.getTextTrim());
				}else if (ele.getName().equals("TECH_GRADE")) {
					cMsg.setTECH_GRADE(ele.getTextTrim());
				}else if (ele.getName().equals("TECH_POSI")) {
					cMsg.setTECH_POSI(ele.getTextTrim());
				}else if (ele.getName().equals("WORK_YEAR")) {
					cMsg.setWORK_YEAR(ele.getTextTrim());
				}else if (ele.getName().equals("REMAIN_QUES")) {
					cMsg.setREMAIN_QUES(ele.getTextTrim());
				}else if (ele.getName().equals("REMAIN_ANS")) {
					cMsg.setREMAIN_ANS(ele.getTextTrim());
				}else if (ele.getName().equals("GET_CARD_MODE")) {
					cMsg.setGET_CARD_MODE(ele.getTextTrim());
				}else if (ele.getName().equals("POST_ADDR")) {
					cMsg.setPOST_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("LM_NAME")) {
					cMsg.setLM_NAME(ele.getTextTrim());
				}else if (ele.getName().equals("LM_SEX")) {
					cMsg.setLM_SEX(ele.getTextTrim());
				}else if (ele.getName().equals("LM_COMP")) {
					cMsg.setLM_COMP(ele.getTextTrim());
				}else if (ele.getName().equals("LM_COMP_ZONENO")) {
					cMsg.setLM_COMP_ZONENO(ele.getTextTrim());
				}else if (ele.getName().equals("LM_COMP_PHONE")) {
					cMsg.setLM_COMP_PHONE(ele.getTextTrim());
				}else if (ele.getName().equals("LM_PRE_ADDR")) {
					cMsg.setLM_PRE_ADDR(ele.getTextTrim());
				}else if (ele.getName().equals("LM_PRE_ZONENO")) {
					cMsg.setLM_PRE_ZONENO(ele.getTextTrim());
				}else if (ele.getName().equals("LM_PRE_PHONE")) {
					cMsg.setLM_PRE_PHONE(ele.getTextTrim());
				}else if (ele.getName().equals("LM_MOBILE")) {
					cMsg.setLM_MOBILE(ele.getTextTrim());
				}else if (ele.getName().equals("RELATION_BET")) {
					cMsg.setRELATION_BET(ele.getTextTrim());
				}else if (ele.getName().equals("CARD_SINGLE_CODE")) {
					cMsg.setCARD_SINGLE_CODE(ele.getTextTrim());
				}else if (ele.getName().equals("APP_SOURCE")) {
					cMsg.setAPP_SOURCE(ele.getTextTrim());
				}else if (ele.getName().equals("EXAM_NODE")) {
					cMsg.setEXAM_NODE(ele.getTextTrim());
				}else if (ele.getName().equals("EXAM_NODE_NO")) {
					cMsg.setEXAM_NODE_NO(ele.getTextTrim());
				}else if (ele.getName().equals("CUST_MANAGER")) {
					cMsg.setCUST_MANAGER(ele.getTextTrim());
				}else if (ele.getName().equals("SALES_CODE")) {
					cMsg.setSALES_CODE(ele.getTextTrim());
				}else if (ele.getName().equals("REPAY_CARD")) {
					cMsg.setREPAY_CARD(ele.getTextTrim());
				}else if (ele.getName().equals("REPAY_MARK")) {
					cMsg.setREPAY_MARK(ele.getTextTrim());
				}else if (ele.getName().equals("CURRENCY")) {
					cMsg.setCURRENCY(ele.getTextTrim());
				}else if (ele.getName().equals("START_DATE")) {
					cMsg.setSTART_DATE(ele.getTextTrim());
				}else if (ele.getName().equals("EXPIRE_DATE")) {
					cMsg.setEXPIRE_DATE(ele.getTextTrim());
				}else if (ele.getName().equals("INPUT_OPERATOR")) {
					cMsg.setINPUT_OPERATOR(ele.getTextTrim());
				}else if (ele.getName().equals("INPUT_DATE")) {
					cMsg.setINPUT_DATE(ele.getTextTrim());
				}else if (ele.getName().equals("PREP2")) {
					cMsg.setPREP2(ele.getTextTrim());
				}else if (ele.getName().equals("LAYOUT_FLAG")) {
					cMsg.setLAYOUT_FLAG(ele.getTextTrim());
				}else if (ele.getName().equals("MEMBER_ID")) {
					cMsg.setMEMBER_ID(ele.getTextTrim());
				}else if (ele.getName().equals("PRE2")) {
					cMsg.setPRE2(ele.getTextTrim());
				}else if (ele.getName().equals("PRE4")) {
					cMsg.setPRE4(ele.getTextTrim());
				}else if (ele.getName().equals("ORGA")) {
					cMsg.setORGA(ele.getTextTrim());
				}else if (ele.getName().equals("REF_INFO")) {
					cMsg.setREF_INFO(ele.getTextTrim());
				}else if (ele.getName().equals("CAREER")) {
					cMsg.setCAREER(ele.getTextTrim());
				}else if (ele.getName().equals("ABCCARD")) {
					cMsg.setABCCARD(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELNAME")) {
					cMsg.setHUKOU_RELNAME(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELSEX")) {
					cMsg.setHUKOU_RELSEX(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELATION")) {
					cMsg.setHUKOU_RELATION(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELZONENO")) {
					cMsg.setHUKOU_RELZONENO(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELPHONE")) {
					cMsg.setHUKOU_RELPHONE(ele.getTextTrim());
				}else if (ele.getName().equals("HUKOU_RELMOBILE")) {
					cMsg.setHUKOU_RELMOBILE(ele.getTextTrim());
				}else if (ele.getName().equals("MANAGER_MOBILE")) {
					cMsg.setMANAGER_MOBILE(ele.getTextTrim());
				}else if (ele.getName().equals("agentPerson")) {
					cMsg.setAgentPerson(ele.getTextTrim());
				}else if (ele.getName().equals("PROCESSER_MOBILE")) {
					cMsg.setPROCESSER_MOBILE(ele.getTextTrim());
				}else if (ele.getName().equals("PROCESSER_ZONENO")) {
					cMsg.setPROCESSER_ZONENO(ele.getTextTrim());
				}else if (ele.getName().equals("PROCESSER_PHONE")) {
					cMsg.setPROCESSER_PHONE(ele.getTextTrim());
				}else if (ele.getName().equals("CHANNEL_ID")) {
					cMsg.setCHANNEL_ID(ele.getTextTrim());
				}else if (ele.getName().equals("ACT_ID")) {
					cMsg.setACT_ID(ele.getTextTrim());
				}else if (ele.getName().equals("ASSURE_TYPE")) {
					cMsg.setASSURE_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("ASSURE_LMT")) {
					cMsg.setASSURE_LMT(ele.getTextTrim());
				}else if (ele.getName().equals("BUDGET_PROD")) {
					cMsg.setBUDGET_PROD(ele.getTextTrim());
				}else if (ele.getName().equals("BUDGET_LMT")) {
					cMsg.setBUDGET_LMT(ele.getTextTrim());
				}else if (ele.getName().equals("BUDGET_ASSURE_TYPE")) {
					cMsg.setBUDGET_ASSURE_TYPE(ele.getTextTrim());
				}else if (ele.getName().equals("FILLER")) {
					cMsg.setFILLER(ele.getTextTrim());
				}
//                System.out.println("<" + ele.getName() + ">" + ele.getTextTrim());
            }
            cList.add(cMsg);
		}
		return cList;
	}
	
}

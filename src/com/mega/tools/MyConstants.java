package com.mega.tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

import com.mega.abcregister.CustAttachActivity;
import com.mega.abcregister.CustMsgActivity;
import com.mega.pack.ABCElement;
import com.mega.pack.Card;
import com.mega.pack.CardMsg;
import com.mega.pack.MyElement;

public class MyConstants
{
	/** ѹ����� */
	public static final int COMPRESS_DONE = 0x01;
	/** ����ʱ�䵽 */
	public static final int TIME_END = 0x02;
	/***/
	public static final int START_TIME_LEFT = 0x03;
	/***/
	public static final int END_TIME_LEFT = 0x04;

	public static final int EXIT = 0x05;
	public static final int LOGOUT = 0x06;
	public static final int CANCEL = 0x07;
	public static final int ADD_DOWN = 0x08;

	/** �����ļ� */
	public static SharedPreferences spf;
	/** �����ļ��༭�� */
	public static Editor editor;

	/** �汾��Ϣ */
	public static final String[] VERSION_LIST = { "ȫ����", "�ӱ���", "���ϰ�" };
	/***/
	public static final String MEGA_PATH = Environment.getExternalStorageDirectory() + "/MEGA/";
	public static final String IMAGE_PATH = Environment.getExternalStorageDirectory() + "/MEGA/IMAGE/";
	public static final String CONFIG_PATH = Environment.getExternalStorageDirectory() + "/MEGA/CONFIG/";
	public static final String WORK_PATH = Environment.getExternalStorageDirectory() + "/MEGA/WORK/";
	public static final String CARD_IMG_PATH = Environment.getExternalStorageDirectory()+"/MEGA/CARD_IMG/";
	public static String WORKFILENAME;
	public static final String CONFIGFILENAME = getIMEI()/*"111111111111111"*/ + ".dat";
	public static final String DBFILENAME = "auto_data.db";
	public static String WORKKEY = "11111111";
	public static SQLiteDatabase SDB = null;// ���ݿ����

	/** spf��ǩ */
	// public static final String HAS_CARD = "HAS_CARD";//�Ƿ��ѳ��б��д��ǿ�
	public static final String APP_NUM = "APP_NUM";// �ƶ��������/������
	public static final String APP_NUM_FLAG = "APP_NUM_FLAG";// �ƶ����������ҵ����ŵ�����ʶ
	public static final String OTHER_CARD = "OTHER_CARD";// �Ƿ���Ҫ������
	public static final String CARD_NAME = "CARD_NAME";// ��Ƭ����
	public static final String APPFORM_TYPE = "1";
	public static final String APPFORM_CON = "2";
	public static final String APPINFO_TYPE = "3";
	public static final String NET_NODE_NO = "4";
	public static final String PROD_KIND = "5";
	public static final String CARD_MARK = "6";
	public static final String CARD_TYPE = "7";
	public static final String CARD_KIND = "8";
	public static final String NORMAL_CARD = "9";
	public static final String DOUBLE_CUR = "10";
	public static final String APP_LMT = "11";
	public static final String COLOR_CARD = "12";
	public static final String EMPLOYEE = "13";
	public static final String APP_CUR = "14";
	public static final String CUST_NAME = "15";
	public static final String LAST_NAME = "16";
	public static final String FIRST_NAME = "17";
	public static final String CUST_SEX = "18";
	public static final String BIRTHDAY = "19";
	public static final String MARR_STAT = "20";
	public static final String NATIONALITY = "21";
	public static final String FAMI_MEMBER = "22";
	public static final String CERT_TYPE = "23";
	public static final String CERT_NO = "24";
	public static final String CERT_NAME = "25";
	public static final String EDU_DEGR = "26";
	public static final String PRE_ADDR = "27";
	public static final String PRE_CITY_CODE = "28";
	public static final String PRE_POST = "29";
	public static final String PRE_YEARS = "30";
	public static final String PRE_ZONE_NO = "31";
	public static final String PRE_PHONE = "32";
	public static final String PRE_MOBILE = "33";
	public static final String EMAIL = "34";
	public static final String PRE_REG_ADDR = "35";
	public static final String REG_ADDR = "36";
	public static final String REG_POST = "37";
	public static final String PER_INCOME = "38";
	public static final String AVE_INCOME = "39";
	public static final String HOU_STAT = "40";
	public static final String HOU_MON_LOAN = "41";
	public static final String CAR_NO = "42";
	public static final String CAR_TYPE = "43";
	public static final String CAR_AGE = "44";
	public static final String CAR_MON_LOAN = "45";
	public static final String COMP_NAME = "46";
	public static final String COMP_ADDR = "47";
	public static final String COMP_CITY_CODE = "48";
	public static final String COMP_POST = "49";
	public static final String COMP_ZONE_NO = "50";
	public static final String COMP_PHONE = "51";
	public static final String COMP_CHAR = "52";
	public static final String TRADE_KIND = "53";
	public static final String TECH_GRADE = "54";
	public static final String TECH_POSI = "55";
	public static final String WORK_YEAR = "56";
	public static final String REMAIN_QUES = "57";
	public static final String REMAIN_ANS = "58";
	public static final String GET_CARD_MODE = "59";
	public static final String POST_ADDR = "60";
	public static final String LM_NAME = "61";
	public static final String LM_SEX = "62";
	public static final String LM_COMP = "63";
	public static final String LM_COMP_ZONENO = "64";
	public static final String LM_COMP_PHONE = "65";
	public static final String LM_PRE_ADDR = "66";
	public static final String LM_PRE_ZONENO = "67";
	public static final String LM_PRE_PHONE = "68";
	public static final String LM_MOBILE = "69";
	public static final String RELATION_BET = "70";
	public static final String CARD_SINGLE_CODE = "71";
	public static final String APP_SOURCE = "72";
	public static final String EXAM_NODE = "73";
	public static final String EXAM_NODE_NO = "74";
	public static final String CUST_MANAGER = "75";
	public static final String SALES_CODE = "76";
	public static final String REPAY_CARD = "77";
	public static final String REPAY_MARK = "78";
	public static final String CURRENCY = "79";
	public static final String START_DATE = "80";
	public static final String EXPIRE_DATE = "81";
	public static final String INPUT_OPERATOR = "82";
	public static final String INPUT_DATE = "83";
	public static final String PREP2 = "84";
	public static final String LAYOUT_FLAG = "85";
	public static final String MEMBER_ID = "86";
	public static final String PRE2 = "87";
	public static final String PRE4 = "88";
	public static final String ORGA = "89";
	public static final String REF_INFO = "90";
	public static final String CAREER = "91";
	public static final String ABCCARD = "92";
	public static final String HUKOU_RELNAME = "93";
	public static final String HUKOU_RELSEX = "94";
	public static final String HUKOU_RELATION = "95";
	public static final String HUKOU_RELZONENO = "96";
	public static final String HUKOU_RELPHONE = "97";
	public static final String HUKOU_RELMOBILE = "98";
	public static final String MANAGER_MOBILE = "99";
	public static final String agentPerson = "100";
	public static final String PROCESSER_MOBILE = "101";
	public static final String PROCESSER_ZONENO = "102";
	public static final String PROCESSER_PHONE = "103";
	public static final String CHANNEL_ID = "104";
	public static final String ACT_ID = "105";
	public static final String ASSURE_TYPE = "106";
	public static final String ASSURE_LMT = "107";
	public static final String BUDGET_PROD = "108";
	public static final String BUDGET_LMT = "109";
	public static final String BUDGET_ASSURE_TYPE = "110";
	public static final String FILLER = "111";

	static
	{
		spf = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getApplicationContext());
		editor = spf.edit();
	}

	/**
	 * 
	 * �������� : clearPreference �������� : ���sharepreference ����������ֵ˵����
	 * 
	 * �޸ļ�¼�� ���ڣ�2013-1-16 ����2:25:28 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static final void clearPreference()
	{
		for (int i = 1; i <= 111; i++)
		{
			String id = String.valueOf(i);
			editor.putString(id, "");
		}
		editor.putBoolean(OTHER_CARD, false);
		editor.putString(CARD_NAME, "");
		editor.commit();
	}

	public static boolean makeFile(boolean attach)
	{
		if (!new File(WORK_PATH).exists())
		{
			boolean res = new File(WORK_PATH).mkdirs();
			if (!res)
			{
				return false;
			}
		}
		if (attach)
		{
			editor.putString(APP_NUM, getAppNum());
			editor.commit();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDate = sdf.format(new Date());
		List<MyElement> list = new ArrayList<MyElement>();
		try
		{
			WORKFILENAME = XmlTools.readStringXmlOut(CONFIG_PATH + CONFIGFILENAME).get("aFileName");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		if (WORKFILENAME == null)
		{
			WORKFILENAME = "default.xml";
		}
//		WORKFILENAME = "111111111111111.xml";
		if (!FileTools.makeXmlFile(WORK_PATH + WORKFILENAME))
		{
			return false;
		}
		
		if (attach)
		{
			list.add(new MyElement("APP_NUM", spf.getString(MyConstants.APP_NUM, "00000000000000000000").trim() ));
			list.add(new MyElement("APPFORM_TYPE", "06"));
			list.add(new MyElement("APPFORM_CON", "01"));
			list.add(new MyElement("APPINFO_TYPE", "01"));
			list.add(new MyElement("CUST_NAME", CustAttachActivity.chsname));// ������
			list.add(new MyElement("LAST_NAME", CustAttachActivity.plname));// ������
			list.add(new MyElement("FIRST_NAME", CustAttachActivity.pfname));// ������
			list.add(new MyElement("CUST_SEX", CustAttachActivity.sex));// ������
			list.add(new MyElement("NATIONALITY", CustAttachActivity.nation));
			list.add(new MyElement("BIRTHDAY", CustAttachActivity.birthday));// ������
			list.add(new MyElement("CERT_TYPE", CustAttachActivity.cardtype));// ������
			list.add(new MyElement("CERT_NO", CustAttachActivity.id));// ������
			list.add(new MyElement("CERT_NAME", CustAttachActivity.cardname));// ������
			list.add(new MyElement("PRE_ADDR", CustAttachActivity.addr));
			list.add(new MyElement("PRE_POST", CustAttachActivity.post));
			list.add(new MyElement("EMAIL", CustAttachActivity.email));// ////////û��
			list.add(new MyElement("PRE_ZONE_NO", CustAttachActivity.zone_num));
			list.add(new MyElement("PRE_PHONE", CustAttachActivity.tel));
			list.add(new MyElement("PRE_MOBILE", CustAttachActivity.mobilephone));
			list.add(new MyElement("RELATION_BET", CustAttachActivity.relation));
			list.add(new MyElement("GET_CARD_MODE", CustAttachActivity.getway));
			list.add(new MyElement("REMAIN_QUES", ""));
			list.add(new MyElement("REMAIN_ANS", ""));
			list.add(new MyElement("INPUT_OPERATOR", "00000000"));
			list.add(new MyElement("INPUT_DATE", currentDate));
			list.add(new MyElement("LAYOUT_FLAG", spf.getString(LAYOUT_FLAG, "")));
			list.add(new MyElement("REF_INFO", ""));
			list.add(new MyElement("MEMBER_ID", ""));
			list.add(new MyElement("FILLER", ""));
			list.add(new MyElement("CARD_NAME", spf.getString(CARD_NAME, "") + "������"));
		} else
		{
			list.add(new MyElement("APP_NUM", spf.getString(MyConstants.APP_NUM, "00000000000000000000").trim()));
			list.add(new MyElement("APPFORM_TYPE", "05"));
			list.add(new MyElement("APPFORM_CON", "01"));
			list.add(new MyElement("APPINFO_TYPE", "01"));
			list.add(new MyElement("NET_NODE_NO", spf.getString(NET_NODE_NO, "")));
			list.add(new MyElement("PROD_KIND", spf.getString(PROD_KIND, "")));// �����ļ�
			list.add(new MyElement("CARD_MARK", spf.getString(CARD_MARK, "")));// �����ļ�
			list.add(new MyElement("CARD_TYPE", "1"));
			list.add(new MyElement("CARD_KIND", spf.getString(CARD_KIND, "")));// �����ļ�
			list.add(new MyElement("NORMAL_CARD", spf.getString(NORMAL_CARD, "")));
			list.add(new MyElement("DOUBLE_CUR", spf.getString(DOUBLE_CUR, "")));// �����ļ�
			list.add(new MyElement("APP_LMT", ""));
			list.add(new MyElement("COLOR_CARD", spf.getString(COLOR_CARD, "")));// �����ļ�
			list.add(new MyElement("EMPLOYEE", spf.getString(EMPLOYEE, "")));
			list.add(new MyElement("APP_CUR", spf.getString(APP_CUR, "")));// �����ļ�
			list.add(new MyElement("CUST_NAME", spf.getString(CUST_NAME, "")));// ������
			list.add(new MyElement("LAST_NAME", spf.getString(LAST_NAME, "")));// ������
			list.add(new MyElement("FIRST_NAME", spf.getString(FIRST_NAME, "")));// ������
			list.add(new MyElement("CUST_SEX", spf.getString(CUST_SEX, "")));// ������
			list.add(new MyElement("BIRTHDAY", spf.getString(BIRTHDAY, "")));// ������
			list.add(new MyElement("MARR_STAT", spf.getString(MARR_STAT, "")));
			list.add(new MyElement("NATIONALITY", spf.getString(NATIONALITY, "")));
			list.add(new MyElement("FAMI_MEMBER", ""));
			list.add(new MyElement("CERT_TYPE", spf.getString(CERT_TYPE, "")));// ������
			list.add(new MyElement("CERT_NO", spf.getString(CERT_NO, "")));// ������
			list.add(new MyElement("CERT_NAME", spf.getString(CERT_NAME, "")));// ������
			list.add(new MyElement("EDU_DEGR", spf.getString(EDU_DEGR, "")));
			list.add(new MyElement("PRE_ADDR", spf.getString(PRE_ADDR, "")));
			list.add(new MyElement("PRE_CITY_CODE", spf.getString(PRE_CITY_CODE, "")));
			list.add(new MyElement("PRE_POST", spf.getString(PRE_POST, "")));
			list.add(new MyElement("PRE_YEARS", spf.getString(PRE_YEARS, "")));
			list.add(new MyElement("PRE_ZONE_NO", spf.getString(PRE_ZONE_NO, "")));
			list.add(new MyElement("PRE_PHONE", spf.getString(PRE_PHONE, "")));
			list.add(new MyElement("PRE_MOBILE", spf.getString(PRE_MOBILE, "")));
			list.add(new MyElement("EMAIL", spf.getString(EMAIL, "")));
			list.add(new MyElement("PRE_REG_ADDR", spf.getString(PRE_REG_ADDR, "")));// ѧ����--ѧУ��ϵ���������˹�ϵ
			list.add(new MyElement("REG_ADDR", spf.getString(REG_ADDR, "")));
			list.add(new MyElement("REG_POST", spf.getString(REG_POST, "")));
			list.add(new MyElement("PER_INCOME", spf.getString(PER_INCOME, "")));
			list.add(new MyElement("AVE_INCOME", spf.getString(AVE_INCOME, "")));
			list.add(new MyElement("HOU_STAT", spf.getString(HOU_STAT, "")));
			list.add(new MyElement("HOU_MON_LOAN", spf.getString(HOU_MON_LOAN, "")));
			list.add(new MyElement("CAR_NO", spf.getString(CAR_NO, "")));// ѧ����--ѧУ��ϵ���ֻ�
			list.add(new MyElement("CAR_TYPE", spf.getString(CAR_TYPE, "")));// ѧ����--��ͥ��ϵ����ϵ��ַ�Ƿ�ͬ��סַ
			list.add(new MyElement("CAR_AGE", ""));
			list.add(new MyElement("CAR_MON_LOAN", ""));
			list.add(new MyElement("COMP_NAME", spf.getString(COMP_NAME, "")));
			list.add(new MyElement("COMP_ADDR", spf.getString(COMP_ADDR, "")));
			list.add(new MyElement("COMP_CITY_CODE", spf.getString(COMP_CITY_CODE, "")));
			list.add(new MyElement("COMP_POST", spf.getString(COMP_POST, "")));
			list.add(new MyElement("COMP_ZONE_NO", spf.getString(COMP_ZONE_NO, "")));
			list.add(new MyElement("COMP_PHONE", spf.getString(COMP_PHONE, "")));
			list.add(new MyElement("COMP_CHAR", ""));
			list.add(new MyElement("TRADE_KIND", spf.getString(TRADE_KIND, "")));
			list.add(new MyElement("TECH_GRADE", spf.getString(TECH_GRADE, "")));
			list.add(new MyElement("TECH_POSI", spf.getString(TECH_POSI, "")));
			list.add(new MyElement("WORK_YEAR", spf.getString(WORK_YEAR, "")));
			list.add(new MyElement("REMAIN_QUES", ""));
			list.add(new MyElement("REMAIN_ANS", ""));
			list.add(new MyElement("GET_CARD_MODE", spf.getString(GET_CARD_MODE, "")));
			list.add(new MyElement("POST_ADDR", spf.getString(POST_ADDR, "")));
			list.add(new MyElement("LM_NAME", spf.getString(LM_NAME, "")));
			list.add(new MyElement("LM_SEX", spf.getString(LM_SEX, "")));
			list.add(new MyElement("LM_COMP", spf.getString(LM_COMP, "")));
			list.add(new MyElement("LM_COMP_ZONENO", spf.getString(LM_COMP_ZONENO, "")));
			list.add(new MyElement("LM_COMP_PHONE", spf.getString(LM_COMP_PHONE, "")));
			list.add(new MyElement("LM_PRE_ADDR", spf.getString(LM_PRE_ADDR, "")));
			list.add(new MyElement("LM_PRE_ZONENO", spf.getString(LM_PRE_ZONENO, "")));
			list.add(new MyElement("LM_PRE_PHONE", spf.getString(LM_PRE_PHONE, "")));
			list.add(new MyElement("LM_MOBILE", spf.getString(LM_MOBILE, "")));
			list.add(new MyElement("RELATION_BET", spf.getString(RELATION_BET, "")));
			list.add(new MyElement("CARD_SINGLE_CODE", spf.getString(CARD_SINGLE_CODE, "")));// �����ļ�
			list.add(new MyElement("APP_SOURCE", "01"));
			list.add(new MyElement("EXAM_NODE", ""));
			list.add(new MyElement("EXAM_NODE_NO", spf.getString(EXAM_NODE_NO, "")));// �����ļ�
			list.add(new MyElement("CUST_MANAGER", ""));
			list.add(new MyElement("SALES_CODE", ""));
			list.add(new MyElement("REPAY_CARD", spf.getString(REPAY_CARD, "")));
			list.add(new MyElement("REPAY_MARK", spf.getString(REPAY_MARK, "")));
			list.add(new MyElement("CURRENCY", ""));
			list.add(new MyElement("START_DATE", ""));
			list.add(new MyElement("EXPIRE_DATE", ""));
			list.add(new MyElement("INPUT_OPERATOR", "00000000"));
			list.add(new MyElement("INPUT_DATE", currentDate));
			list.add(new MyElement("PREP2", spf.getString(PREP2, "")));
			list.add(new MyElement("LAYOUT_FLAG", spf.getString(LAYOUT_FLAG, "")));
			list.add(new MyElement("MEMBER_ID", ""));
			list.add(new MyElement("PRE2", spf.getString(PRE2, "")));
			list.add(new MyElement("PRE4", spf.getString(PRE4, "")));
			list.add(new MyElement("ORGA", ""));
			list.add(new MyElement("REF_INFO", ""));
			list.add(new MyElement("CAREER", spf.getString(CAREER, "")));
			list.add(new MyElement("ABCCARD", spf.getString(ABCCARD, "")));
			list.add(new MyElement("HUKOU_RELNAME", spf.getString(HUKOU_RELNAME, "")));
			list.add(new MyElement("HUKOU_RELSEX", spf.getString(HUKOU_RELSEX, "")));
			list.add(new MyElement("HUKOU_RELATION", spf.getString(HUKOU_RELATION, "")));
			list.add(new MyElement("HUKOU_RELZONENO", spf.getString(HUKOU_RELZONENO, "")));
			list.add(new MyElement("HUKOU_RELPHONE", spf.getString(HUKOU_RELPHONE, "")));
			list.add(new MyElement("HUKOU_RELMOBILE", spf.getString(HUKOU_RELMOBILE, "")));
			list.add(new MyElement("MANAGER_MOBILE", ""));
			list.add(new MyElement("agentPerson", ""));
			list.add(new MyElement("PROCESSER_MOBILE", ""));
			list.add(new MyElement("PROCESSER_ZONENO", ""));
			list.add(new MyElement("PROCESSER_PHONE", ""));
			list.add(new MyElement("CHANNEL_ID", ""));
			list.add(new MyElement("ACT_ID", ""));
			String uid = "";
			try
			{
				uid = XmlTools.readStringXmlOut(CONFIG_PATH + CONFIGFILENAME).get("aOpenId");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (CustMsgActivity.card.getStudentCard().equals("1"))
			{
				list.add(new MyElement("ASSURE_TYPE", ""));
				list.add(new MyElement("ASSURE_LMT", ""));
				list.add(new MyElement("BUDGET_PROD", ""));
				list.add(new MyElement("BUDGET_LMT", ""));
				list.add(new MyElement("BUDGET", ""));
				list.add(new MyElement("FILLER", ""));
				list.add(new MyElement("CARD_NAME", spf.getString(CARD_NAME, "")));
				list.add(new MyElement("ISSTUDENT", CustMsgActivity.card.getStudentCard()));
				list.add(new MyElement("OPEN_ID", uid));
			} else
			{
				list.add(new MyElement("FILLER", ""));
				list.add(new MyElement("CARD_NAME", spf.getString(CARD_NAME, "")));
				list.add(new MyElement("ISSTUDENT", CustMsgActivity.card.getStudentCard()));
				list.add(new MyElement("OPEN_ID", uid));
			}

		}

		for (MyElement abcElement : list)
		{
			System.out.println(abcElement.getName()+":"+abcElement.getValue());
		}
		if(!FileTools.addElements(list, WORK_PATH + WORKFILENAME))
		{
			return false;
		}
		// �ı������ű�ʶ
		String app_flag = MyConstants.spf.getString(MyConstants.APP_NUM_FLAG, "");
		app_flag = app_flag.substring(0, app_flag.length() - 1) + "1";
		editor.putString(MyConstants.APP_NUM_FLAG, app_flag);
		editor.commit();
		return true;
	}

	/**
	 * 
	 * �������� : showPswd �������� : ��������ת�� ����������ֵ˵����
	 * 
	 * @param pswd
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-1-21 ����3:49:56 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static final String showPswd(String pswd)
	{
		char[] ch = pswd.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < ch.length; i++)
		{
			stringBuffer.append("*");
		}
		return stringBuffer.toString();
	}

	/**
	 * 
	 * �������� : getCurrentVer �������� : ��ȡ��ǰ����汾 ����������ֵ˵����
	 * 
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-3-14 ����10:37:48 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static String getCurrentVer()
	{
		Context context = MyApplication.getInstance();
		PackageManager pm = context.getPackageManager();// contextΪ��ǰActivity������
		PackageInfo pi = null;
		try
		{
			pi = pm.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String lastVer = pi.versionName;
		return lastVer;
	}

	/**
	 * 
	 * �������� : checkPhoneNum �������� : ����������ֵ˵����
	 * 
	 * @param phonenum
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:50 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean checkPhoneNum(String phonenum)
	{
		boolean flag = false;  
	    try
	    {  
	    	Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
	    	Matcher m = p.matcher(phonenum);  
	    	flag = m.matches();  
	    }catch(Exception e)
	    {  
	    	flag = false;  
	    }  
	    return flag;  
	}

	/**
	 * 
	 * �������� : checkTelNum �������� : У��绰�����ʽ ����������ֵ˵����
	 * 
	 * @param telnum
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-6-20 ����5:06:41 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean checkTelNum(String telnum)
	{
		if (telnum.equals(""))
		{
			return false;
		}
		if (telnum.length() < 7 || telnum.length() > 8)
		{
			return false;
		}
		if (telnum.length() == 8)
		{
			String last7 = telnum.substring(1);
			char[] c = last7.toCharArray();
			char element = c[0];
			int count = 0;
			for (int i = 1; i < c.length; i++)
			{
				if (c[i] == element)
				{
					count++;
				}
			}
			if (count == 6)
			{
				return false;
			}
		} else
		{
			char[] c = telnum.toCharArray();
			char element = c[0];
			int count = 0;
			for (int i = 1; i < c.length; i++)
			{
				if (c[i] == element)
				{
					count++;
				}
			}
			if (count == 6)
			{
				return false;
			}
		}
		char[] c = telnum.toCharArray();
		int space = 0;
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == ' ')
			{
				space++;
			}
		}
		if (space > 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * �������� : checkPostCode �������� : ����������ֵ˵����
	 * 
	 * @param postcode
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:43 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean checkPostCode(String postcode)
	{
		if (postcode.length() != 6)
		{
			return false;
		}
		char[] c = postcode.toCharArray();
		int count = 0;
		int space = 0;
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == '0')
			{
				count++;
			}
			if (c[i] == ' ')
			{
				space++;
			}
		}
		if (count == 6)
		{
			return false;
		}
		if (space > 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * �������� : baseCheck �������� : ����������ֵ˵����
	 * 
	 * @param element
	 * @param size
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:39 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean baseCheck(String element, int size)
	{
		if (element.equals(""))
		{
			return false;
		}
		try
		{
			if (element.getBytes("GBK").length > size)
			{
				return false;
			}
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		char[] c = element.toCharArray();
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == ' ')
			{
				count++;
			}
		}
		if (count > 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * �������� : numberCheck �������� : ����������ֵ˵����
	 * 
	 * @param number
	 * @param size
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:34 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean numberCheck(String number, int size)
	{
		if (number.equals(""))
		{
			return false;
		}
		if (number.startsWith("0"))
		{
			return false;
		}
		try
		{
			if (number.getBytes("GBK").length > size)
			{
				return false;
			}
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char[] c = number.toCharArray();
		int space = 0;
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == ' ')
			{
				space++;
			}
		}
		if (space > 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * �������� : checkEmailAddr �������� : ����������ֵ˵����
	 * 
	 * @param emailaddr
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:29 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static boolean checkEmailAddr(String emailaddr)
	{
		// TODO Auto-generated method stub
		if (emailaddr.equals(""))
		{
			return false;
		}
		char[] c = emailaddr.toCharArray();
		int space = 0;
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == ' ')
			{
				space++;
			}
		}
		if (space > 0)
		{
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(emailaddr);
		return matcher.matches();
	}

	/**
	 * 
	 * �������� : searchCard �������� : ��ѯ��Ƭ ����������ֵ˵����
	 * 
	 * @param cards
	 * @param bz
	 * @param jg
	 * @param dj
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-6-25 ����11:13:28 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static List<Card> searchCard(List<Card> cards, String bz, String jg, String dj, String keyword)
	{
		if (bz.equals("�����"))
		{
			bz = "000";
		} else if (bz.equals("˫����(��Ԫ)"))
		{
			bz = "840";
		} else if (bz.equals("˫����(ŷԪ)"))
		{
			bz = "978";
		}
		if (jg.equals("����"))
		{
			jg = "4";
		} else if (jg.equals("VISA"))
		{
			jg = "0";
		} else if (jg.equals("MasterCard"))
		{
			jg = "1";
		}
		if (dj.equals("�տ�"))
		{
			dj = "1";
		} else if (dj.equals("��"))
		{
			dj = "0";
		} else if (dj.equals("�׽�"))
		{
			dj = "2";
		} else if (dj.equals("��ʯ��"))
		{
			dj = "3";
		} else if (dj.equals("���������"))
		{
			dj = "4";
		}
		List<Card> list = new ArrayList<Card>();
		for (Card card : cards)
		{
			String group = card.getGroup();
			String cardbz = card.getCardBz();
			String grade = card.getCardGrade();
			String name = card.getName().toUpperCase();
			boolean cond1 = bz.equals("ȫ��") ? true : cardbz.equals(bz);
			boolean cond2 = jg.equals("ȫ��") ? true : group.equals(jg);
			boolean cond3 = dj.equals("ȫ��") ? true : grade.equals(dj);
			boolean cond4 = keyword.equals("") ? true : name.contains(keyword.toUpperCase());
			if (cond1 && cond2 && cond3 && cond4)
			{
				list.add(card);
			}
		}
		return list;
	}

	/**
	 * 
	 * �������� : getMsgList �������� : ����������ֵ˵����
	 * 
	 * @param path
	 * @param key
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:11 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static List<HashMap<String, Object>> getMsgList(String path)
	{
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		List<CardMsg> msgList = FileTools.getCardMsgList(path);
		if (msgList!=null)
		{
			for (CardMsg s : msgList)
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				String kind = s.getAPPFORM_TYPE();
					String app_num = s.getAPP_NUM();// ������
					String card_name = s.getCARD_NAME();// ��Ƭ����
					String name = s.getCUST_NAME();
					String idnum = s.getCERT_NO();
					map.put("appnum", app_num);
					map.put("name", name);
					map.put("cardname", card_name);
					map.put("idnum", idnum);
					list.add(map);

			}
		}
		else {
			return null;
		}
		
		return list;
	}

	/**
	 * 
	 * �������� : getCardDetail �������� : ����������ֵ˵����
	 * 
	 * @param path
	 * @param key
	 * @param appnum
	 * @param idnum
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:30:07 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static CardMsg getCardDetail(String path, String appnum, String idnum)
	{
		CardMsg cMsg = new CardMsg();
		List<CardMsg> msgList = FileTools.getCardMsgList(path);
		for (CardMsg s : msgList)
		{
			String kind = s.getAPPFORM_TYPE();
			if (kind.equals("05"))
			{
				String APP_NUM = s.getAPP_NUM();// ������
				System.out.println("ѧ������"+s.isISSTUDENT());
				String CERT_NO = s.getCERT_NO();
				if (APP_NUM.equals(appnum) && CERT_NO.equals(idnum))
				{
					cMsg = s;
					cMsg.setNORMAL_CARD(s.getNORMAL_CARD().equals("1") ? "��" : "��");// �����뿨��δ����׼���Ƿ�ͬ����������ȼ����ǿ�
					cMsg.setCUST_SEX(s.getCUST_SEX().equals("M") ? "��" : "Ů");// �Ա�
					String birth = s.getBIRTHDAY();
					cMsg.setBIRTHDAY(birth.substring(0, 4)+"��"+birth.substring(4, 6)+"��"+birth.substring(6, 8)+"��");// ��������
					String marr = s.getMARR_STAT();
					if (marr.equals("0"))
					{
						marr = "δ��";
					} else if (marr.equals("1"))
					{
						marr = "�ѻ�����Ů";
					} else if (marr.equals("2"))
					{
						marr = "�ѻ�����Ů";
					} else
					{
						marr = "����";
					}
					cMsg.setMARR_STAT(marr);// ����״��
					String itype = s.getCERT_TYPE();
					if (itype.equals("I"))
					{
						itype = "���֤";
					} else if (itype.equals("P"))
					{
						itype = "����";
					} 
					cMsg.setCERT_TYPE(itype);// ֤������
					String edu = s.getEDU_DEGR();
					if (!cMsg.isISSTUDENT())
					{
						if (edu.equals("0"))
						{
							edu = "�о���������";
						} else if (edu.equals("2"))
						{
							edu = "��ѧ����";
						} else if (edu.equals("3"))
						{
							edu = "��ѧר��";
						} else
						{
							edu = "����/��ר������";
						}
					} else
					{
						if (edu.equals("0"))
						{
							edu = "��ʿ";
						} else if (edu.equals("2"))
						{
							edu = "�о���";
						} else if (edu.equals("3"))
						{
							edu = "����";
						} else
						{
							edu = "ר��";
						}
					}
					cMsg.setEDU_DEGR(edu);// �����̶�
					String hquhao = s.getPRE_ZONE_NO();
					if (hquhao.length() == 3 && !hquhao.startsWith("0"))
					{
						hquhao = "0" + hquhao;
					}
					cMsg.setPRE_ZONE_NO(hquhao);// ��סַ�绰�����ţ�
					cMsg.setPER_INCOME(Integer.valueOf(s.getPER_INCOME()) / 100 + "");// ����������
					String house = s.getHOU_STAT();
					if (house.equals("0"))
					{
						house = "�Թ��޴��";
					} else if (house.equals("1"))
					{
						house = "�Թ��д��";
					} else if (house.equals("2"))
					{
						house = "�븸ĸͬס";
					} else if (house.equals("3"))
					{
						house = "����";
					} else
					{
						house = "����";
					}
					cMsg.setHOU_STAT(house);// סլ״��
					String dquhao = s.getCOMP_ZONE_NO();
					if (dquhao.length()==3&&!dquhao.startsWith("0"))
					{
						dquhao = "0"+dquhao;
					}
					cMsg.setCOMP_ZONE_NO(dquhao);// ��λ�绰����
					String jobType = s.getTRADE_KIND();
					if (jobType.equals("0"))
					{
						jobType = "���������������֯";
					} else if (jobType.equals("1"))
					{
						jobType = "�����Ļ���������";
					} else if (jobType.equals("2"))
					{
						jobType = "���ڵ�������";
					} else if (jobType.equals("3"))
					{
						jobType = "������ͨ���乫��";
					} else if (jobType.equals("4"))
					{
						jobType = "��������������ҵ";
					} else if (jobType.equals("5"))
					{
						jobType = "��������";
					} else if (jobType.equals("6"))
					{
						jobType = "��ҵ��ҵ����ҵó��";
					} else
					{
						jobType = "����";
					}
					cMsg.setTRADE_KIND(jobType);// ��ҵ���
					String title = s.getTECH_GRADE();
					if (title.equals("0"))
					{
						title = "�߼�";
					}
					else if (title.equals("1")) {
						title = "�м�";
					}
					else if (title.equals("2")) {
						title = "����";
					}
					else {
						title = "��";
					}
					cMsg.setTECH_GRADE(title);// ְ��
					String job = s.getTECH_POSI();
					if (s.getCARD_NAME().contains("���ӵ�λ"))
					{
						if (job.equals("1"))
						{
							job = "�ɲ�";
						}
						else if (job.equals("2")) {
							job = "ʿ��";
						}
						else {
							job = "ְ��";
						}
					}
					else {
						if (job.equals("0"))
						{
							job = "���ּ����ϣ�����";
						}
						else if (job.equals("1")) {
							job = "����";
						}
						else if (job.equals("2")) {
							job = "�Ƽ�";
						}
						else if (job.equals("3")) {
							job = "һ��ɲ�";
						}
						else if (job.equals("A")) {
							job = "�ܾ������ϣ�����";
						}
						else if (job.equals("B")) {
							job = "���ž���";
						}
						else if (job.equals("C")) {
							job = "ְԱ";
						}
						else {
							job = "����";
						}
					}
					cMsg.setTECH_POSI(job);// ְ��
					cMsg.setWORK_YEAR(Integer.valueOf(s.getWORK_YEAR())+"");// �ֵ�λ��������
					String getcard = s.getGET_CARD_MODE();
					if (getcard.equals("0"))
					{
						getcard = "����������ȡ";
					}
					else {
						getcard = "�ʼĵ��˵���ַ";
					}
					cMsg.setGET_CARD_MODE(getcard);// �쿨��ʽ
					String sentAddr = s.getPOST_ADDR();
					if (sentAddr.equals("0"))
					{
						sentAddr = "��סַ";
					}
					else {
						sentAddr = "��λ��ַ";
					}
					cMsg.setPOST_ADDR(sentAddr);// �ʼĵ�ַ
					cMsg.setLM_SEX(s.getLM_SEX().equals("M")?"��":"Ů");// ��ϵ���Ա�
					String lquhao = s.getLM_PRE_ZONENO();
					if (lquhao.length()==3&&!lquhao.startsWith("0"))
					{
						lquhao = "0"+lquhao;
					}
					cMsg.setLM_PRE_ZONENO(lquhao);// ��ϵ����סַ�绰����
					String rela1 = s.getRELATION_BET();
					if (rela1.equals("0"))
					{
						rela1 = "��ż";
					}
					else if (rela1.equals("1")) {
						if (cMsg.isISSTUDENT())
						{
							rela1 = "��ĸ";
						}
						else {
							rela1 = "����";
						}
					}
					else if (rela1.equals("2")) {
						rela1 = "����";
					}else {
						rela1 = "����";
					}
					cMsg.setRELATION_BET(rela1);// �������˹�ϵ
					String repay = s.getREPAY_MARK();
					if (repay.equals("1")) {
						repay = "��ͻ����";
					}
					else if (repay.equals("2"))
					{
						repay = "ȫ���";
					}
					else {
						repay = "��";
					}
					cMsg.setREPAY_MARK(repay);// ���ʽ
					String zhang = s.getPRE4();
					if (zhang.equals("0"))
					{
						zhang = "ֽ���ʵ�";
					}
					else if (zhang.equals("1")) {
						zhang = "�����ʵ�";
					}
					else if (zhang.equals("2")) {
						zhang = "ֽ�Ƽ������ʵ�";
					}
					else if (zhang.equals("3")) {
						zhang = "�����˵�";
					}
					else {
						zhang = "ֽ�ʼ������˵�";
					}
					cMsg.setPRE4(zhang);// �˵���ʽ
					String career = s.getCAREER();
					if (career.equals("1"))
					{
						career = "����Ա";
					}
					else if (career.equals("2")) {
						career = "ҽ��";
					}
					else if (career.equals("3")) {
						career = "��ʦ";
					}
					else if (career.equals("4")) {
						career = "��ʦ/���/���ʦ";
					}
					else if (career.equals("A")) {
						career = "������ҵ��λԱ��";
					}
					else if (career.equals("B")) {
						career = "������ҵԱ��";
					}
					else if (career.equals("C")) {
						career = "������ҵԱ��";
					}
					else if (career.equals("D")) {
						career = "��Ӫ��ҵԱ��";
					}
					else if (career.equals("6")) {
						career = "����";
					}
					else if (career.equals("7")) {
						career = "ѧ��";
					}
					else if (career.equals("8")) {
						career = "����/����ְҵ��";
					}
					else {
						career = "����";
					}
					cMsg.setCAREER(career);// ְҵ
					cMsg.setHUKOU_RELSEX(s.getHUKOU_RELSEX().equals("M")?"��":"Ů");// ������ϵ���Ա�
					String hjrela = s.getHUKOU_RELATION();
					if (hjrela.equals("0"))
					{
						hjrela = "��ż";
					}
					else if (hjrela.equals("1")) {
						hjrela = "����";
					}
					else if (hjrela.equals("2")) {
						hjrela = "����";
					}else {
						hjrela = "����";
					}
					cMsg.setHUKOU_RELATION(hjrela);// ������ϵ���������˹�ϵ
					String hjquhao = s.getHUKOU_RELZONENO();
					if (hjquhao.length()==3&&!hjquhao.startsWith("0"))
					{
						hjquhao = "0"+hjquhao;
					}
					cMsg.setHUKOU_RELZONENO(hjquhao);// ������ϵ�˵绰����
				}
			} else
			{
				String APP_NUM = s.getAPP_NUM();// ������
				String CERT_NO = s.getCERT_NO();
				if (APP_NUM.equals(appnum) && CERT_NO.equals(idnum))
				{
					cMsg = s;
					cMsg.setCUST_SEX(s.getCUST_SEX().equals("M")?"��":"Ů");// �Ա�
					String birth = s.getBIRTHDAY();
					cMsg.setBIRTHDAY(birth.substring(0, 4)+"��"+birth.substring(4, 6)+"��"+birth.substring(6, 8)+"��");// ��������
					String itype = s.getCERT_TYPE();
					if (itype.equals("I"))
					{
						itype = "���֤";
					} else if (itype.equals("P"))
					{
						itype = "����";
					} 
					cMsg.setCERT_TYPE(itype);// ֤������
					String fquhao = s.getPRE_ZONE_NO();
					if (fquhao.length()==3&&!fquhao.startsWith("0"))
					{
						fquhao= "0"+fquhao;
					}
					cMsg.setPRE_ZONE_NO(fquhao);// ��סַ�绰�����ţ�
					String rela1 = s.getRELATION_BET();
					if (rela1.equals("0"))
					{
						rela1 = "��ż";
					}
					else if (rela1.equals("1")) {
						rela1 = "����";
					}
					else if (rela1.equals("2")) {
						rela1 = "����";
					}else {
						rela1 = "����";
					}
					cMsg.setRELATION_BET(rela1);// �������˹�ϵ
					String getcard = s.getGET_CARD_MODE();
					if (getcard.equals("0"))
					{
						getcard = "����������ȡ";
					}
					else {
						getcard = "�ʼĵ��˵���ַ";
					}
					cMsg.setGET_CARD_MODE(getcard);// �쿨��ʽ
				}
			}

		}
		return cMsg;
	}

	/**
	 * 
	 * �������� : getElement �������� : ����������ֵ˵����
	 * 
	 * @param str
	 * @param position
	 * @param length
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:29:47 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static String getElement(String str, int position, int length)
	{
		String string = null;
		try
		{
			byte[] b = str.getBytes("GBK");
			byte[] s = new byte[length];
			int a = 0;
			for (int i = position; i < b.length; i++)
			{
				if (a == length)
				{
					break;
				}
				s[a++] = b[i];
			}
			string = new String(s, "GBK").trim();
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 
	 * �������� : searchMsg �������� : ����������ֵ˵����
	 * 
	 * @param path
	 * @param key
	 * @param idnum
	 * @param appnum
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:29:21 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static CardMsg searchMsg(String path, String idnum, String appnum)
	{
		List<CardMsg> msgList = FileTools.getCardMsgList(path);
		for (CardMsg s : msgList)
		{
			String app_num = s.getAPP_NUM();// ������
			String card_name = s.getCARD_NAME();// ��Ƭ����
			String id_num = s.getCERT_NO();
			if (idnum.equals(id_num) && appnum.equals(app_num))
			{
				return s;
			}
		}
		return null;
	}
	
	/**
	 * 
	 *  �������� : deleteMsg
	 *  �������� :  
	 *  ����������ֵ˵����
	 *  	@param path
	 *  	@param key
	 *  	@param idnum
	 *  	@param appnum
	 *  	@return
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-8-26 ����9:56:40	�޸��ˣ�kcx
	 *  	����	��
	 *
	 */
	public static boolean deleteMsg(String path, String key, String idnum, String appnum)
	{
		List<String> msgList = Jie(path, key);
		List<String> deleteList = new ArrayList<String>();
		boolean d1 = false;
		boolean d2 = false;
		for (String s : msgList)
		{
			String temp = s;
			String app_num = s.substring(1, 31).trim();// ������
			String card_name = getElement(s, 2033, 100);// ��Ƭ����
			s = getElement(s, 31, 2002);// ҵ���ַ���
			String id_num = getElement(s, 153, 30);
			if (idnum.equals(id_num) && appnum.equals(app_num))
			{
				d1 = true;
			} else
			{
				deleteList.add(temp);
			}
		}
		File file = new File(path);
		if (file.delete())
		{
			if (deleteList.size()!=0)
			{
				for (String string : deleteList)
				{
					try
					{
						FileTools.encryptToFile(path, string, key);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						System.err.println(e);
					}
				}
			}
			d2 = true;
		}
		return d1&&d2;
	}

	/**
	 * 
	 * �������� : Jie �������� : ����������ֵ˵����
	 * 
	 * @param path
	 * @param key
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:29:16 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static List<String> Jie(String path, String key)
	{
		byte[] decryptkey;// ������Կ
		List<String> list = new ArrayList<String>();// ���ܺ󼯺�
		RandomAccessFile raf;
		try
		{
			decryptkey = key.getBytes("GBK");
			raf = new RandomAccessFile(path, "r");
			long flength = raf.length();
			int length = 0;
			int count = 0;
			System.out.println("flength="+flength);
			while (count != flength)
			{
				byte[] blength = new byte[8];
				raf.read(blength);
				count += 8;
				length = Integer.valueOf(new String(blength, "GBK").trim());
				System.out.println("length="+length);
				blength = new byte[length];
				count += length;
				raf.read(blength);
				list.add(new String(DES.decrypt(blength, decryptkey), "GBK"));
			}
			raf.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
			return null;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
			return null;
		}
		return list;
	}

	/**
	 * 
	 * �������� : getHtml �������� : ����������ֵ˵����
	 * 
	 * @param cardMsg
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:29:06 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static String getHtml(CardMsg cardMsg)
	{
		StringBuffer sBuffer = new StringBuffer();
		System.out.println("���ͣ�"+cardMsg.getAPPFORM_TYPE());
		if (cardMsg.getAPPFORM_TYPE().equals("05"))
		{
			System.out.println("�Ƿ�ѧ������"+cardMsg.isISSTUDENT());
			if (!cardMsg.isISSTUDENT())
			{
				sBuffer.append("<html><body><p>������Ŀ��֣�" + cardMsg.getCARD_NAME().trim() + "</p>")
				.append("<p>�ƶ�������ţ�" + cardMsg.getAPP_NUM().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">  ������Ϣ</font></b></p>")
				.append("<p>����������" + cardMsg.getCUST_NAME().trim() + "</p>")
				.append("<p>ƴ��������" + cardMsg.getLAST_NAME().trim() + " " + cardMsg.getFIRST_NAME().trim() + "</p>")
				.append("<p>�Ա�" + cardMsg.getCUST_SEX().trim() + "</p>")
				.append("<p>��סַ��" + cardMsg.getPRE_ADDR().trim() + "</p>")
				.append("<p>�������ڣ�" + cardMsg.getBIRTHDAY().trim() + "</p>")
				.append("<p>֤�����ͣ�" + cardMsg.getCERT_TYPE().trim() + "</p>")
				.append("<p>֤�����룺" + cardMsg.getCERT_NO().trim() + "</p>")
				.append("<p>������" + cardMsg.getNATIONALITY().trim() + "</p>")
				.append("<p>�����̶ȣ�" + cardMsg.getEDU_DEGR().trim() + "</p>")
				.append("<p>����״����" + cardMsg.getMARR_STAT().trim() + "</p>")
				.append("<p>���������룺" + cardMsg.getPER_INCOME().trim() + "Ԫ" + "</p>")
				.append("<p>סլ״����" + cardMsg.getHOU_STAT().trim() + "</p>")
				.append("<p>�������룺" + cardMsg.getPRE_POST().trim() + "</p>")
				.append("<p>סլ�绰��" + cardMsg.getPRE_ZONE_NO().trim() + "-" + cardMsg.getPRE_PHONE().trim() + "</p>")
				.append("<p>�ֻ����룺" + cardMsg.getPRE_MOBILE().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">ְҵ��Ϣ</font></b></p>")
				.append("<p>ְҵ��" + cardMsg.getCAREER().trim() + "</p>")
				.append("<p>��ҵ���ࣺ" + cardMsg.getTRADE_KIND().trim() + "</p>")
				.append("<p>ְ�ƣ�" + cardMsg.getTECH_GRADE().trim() + "</p>")
				.append("<p>�ֵ�λ�������ޣ�" + cardMsg.getWORK_YEAR().trim() +"��"+ "</p>")
				.append("<p>��λȫ�ƣ�" + cardMsg.getCOMP_NAME().trim() + "</p>")
				.append("<p>ְ��" + cardMsg.getTECH_POSI().trim() + "</p>")
				.append("<p>��λ��ַ��" + cardMsg.getCOMP_ADDR().trim() + "</p>")
				.append("<p>�������룺" + cardMsg.getCOMP_POST().trim() + "</p>")
				.append("<p>��λ�绰��" + cardMsg.getCOMP_ZONE_NO().trim() + "-" + cardMsg.getCOMP_PHONE().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">��ϵ����Ϣ</font></b></p>")
				.append("<p>��ϵ��������" + cardMsg.getLM_NAME().trim() + "</p>")
				.append("<p>�Ա�" + cardMsg.getLM_SEX().trim() + "</p>")
				.append("<p>�������˹�ϵ��" + cardMsg.getRELATION_BET().trim() + "</p>")
				.append("<p>��ϵ�绰��" + cardMsg.getLM_PRE_ZONENO().trim() + "-" + cardMsg.getLM_PRE_PHONE().trim() + "</p>");
				if (cardMsg.getHUKOU_RELNAME().length() != 0)
				{
					sBuffer.append("<p><b><font color=\"#00FFFF\">��������ϵ����Ϣ</font></b></p>")
					.append("<p>��ϵ��������" + cardMsg.getHUKOU_RELNAME().trim() + "</p>")
					.append("<p>�Ա�" + cardMsg.getHUKOU_RELSEX().trim() + "</p>")
					.append("<p>�������˹�ϵ��" + cardMsg.getHUKOU_RELATION().trim() + "</p>")
					.append("<p>��ϵ�绰��" + cardMsg.getHUKOU_RELZONENO().trim() + "-" + cardMsg.getHUKOU_RELPHONE().trim() + "</p>")
					.append("<p>�ֻ���" + cardMsg.getHUKOU_RELMOBILE().trim() + "</p>")
					.append("<p>���˻�����ַ��" + cardMsg.getREG_ADDR().trim() + "</p>");
				}
				sBuffer.append("<p><b><font color=\"#00FFFF\">������Ϣ</font></b></p>")
				.append("<p>�Ƿ�����Ƽ���" + cardMsg.getNORMAL_CARD().trim() + "</p>")
				.append("<p>�쿨��ʽ��" + cardMsg.getGET_CARD_MODE().trim() + "</p>")
				.append("<p>������Ƭ�����뺯�ʼĵ�ַ��" + cardMsg.getPOST_ADDR().trim() + "</p>")
				.append("<p>���˵����ͷ�ʽ��" + cardMsg.getPRE4().trim() + "</p>")
				.append("<p>�������䣺" + cardMsg.getEMAIL().trim() + "</p>")
				.append("<p>Լ�����ʽ��" + cardMsg.getREPAY_MARK().trim() + "</p>")
				.append("<p>Լ������ţ�" + cardMsg.getREPAY_CARD().trim() + "</p>");

			} else
			{
				sBuffer.append("<html><body><p>������Ŀ��֣�" + cardMsg.getCARD_NAME().trim() + "</p>")
				.append("<p>�ƶ�������ţ�" + cardMsg.getAPP_NUM().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">������Ϣ</font></b></p>")
				.append("<p>����������" + cardMsg.getCUST_NAME().trim() + "</p>")
				.append("<p>ƴ��������" + cardMsg.getLAST_NAME().trim() + " " + cardMsg.getFIRST_NAME().trim() + "</p>")
				.append("<p>�Ա�" + cardMsg.getCUST_SEX().trim() + "</p>")
				.append("<p>��סַ��" + cardMsg.getPRE_ADDR().trim() + "</p>")
				.append("<p>�������ڣ�" + cardMsg.getBIRTHDAY().trim() + "</p>")
				.append("<p>֤�����ͣ�" + cardMsg.getCERT_TYPE().trim() + "</p>")
				.append("<p>֤�����룺" + cardMsg.getCERT_NO().trim() + "</p>")
				.append("<p>������" + cardMsg.getNATIONALITY().trim() + "</p>")
				.append("<p>�ڶ�ѧ����" + cardMsg.getEDU_DEGR().trim() + "</p>")
				.append("<p>סլ״����" + cardMsg.getHOU_STAT().trim() + "</p>")
				.append("<p>�������룺" + cardMsg.getPRE_POST().trim() + "</p>")
				.append("<p>סլ�绰��" + cardMsg.getPRE_ZONE_NO().trim() + "-" + cardMsg.getPRE_PHONE().trim() + "</p>")
				.append("<p>�ֻ����룺" + cardMsg.getPRE_MOBILE().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">ѧУ��Ϣ</font></b></p>")
				.append("<p>ѧУȫ�ƣ�" + cardMsg.getCOMP_NAME().trim() + "</p>")
				.append("<p>�ڶ��꼶��" + cardMsg.getWORK_YEAR().trim() +"�꼶"+ "</p>")
				.append("<p>ѧУ��ַ��" + cardMsg.getCOMP_ADDR().trim() + "</p>")
				.append("<p>�������룺" + cardMsg.getCOMP_POST().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">ѧУ��ϵ����Ϣ</font></b></p>")
				.append("<p>��ϵ��������" + cardMsg.getREG_ADDR().trim() + "</p>")
				.append("<p>�Ա�" + cardMsg.getHUKOU_RELSEX().trim() + "</p>")
				.append("<p>�������˹�ϵ��")
				.append(cardMsg.getPRE_REG_ADDR().equals("1")?"��ʦ":"ͬѧ" + "</p>")
				.append("<p>��ϵ�绰��" + cardMsg.getCOMP_ZONE_NO().trim() + "-" + cardMsg.getCOMP_PHONE().trim() + "</p>")
				.append("<p>�ֻ���" + cardMsg.getCAR_NO().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">��ͥ��ϵ����Ϣ</font></b></p>")
				.append("<p>��ϵ��������" + cardMsg.getLM_NAME().trim() + "</p>")
				.append("<p>�Ա�" + cardMsg.getLM_SEX().trim() + "</p>")
				.append("<p>�������˹�ϵ��" + cardMsg.getRELATION_BET().trim() + "</p>")
				.append("<p>��ϵ�绰��" + cardMsg.getLM_PRE_ZONENO().trim() + "-" + cardMsg.getLM_PRE_PHONE().trim() + "</p>")
				.append("<p>�ֻ���" + cardMsg.getLM_MOBILE().trim() + "</p>")
				.append("<p><b><font color=\"#00FFFF\">������Ϣ</font></b></p>")
				.append("<p>�Ƿ�����Ƽ���" + cardMsg.getNORMAL_CARD().trim() + "</p>")
				.append("<p>�쿨��ʽ��" + cardMsg.getGET_CARD_MODE().trim() + "</p>")
				.append("<p>������Ƭ�����뺯�ʼĵ�ַ��" + cardMsg.getPOST_ADDR().trim() + "</p>")
				.append("<p>���˵����ͷ�ʽ��" + cardMsg.getPRE4().trim() + "</p>")
				.append("<p>�������䣺" + cardMsg.getEMAIL().trim() + "</p>")
				.append("<p>Լ�����ʽ��" + cardMsg.getREPAY_MARK().trim() + "</p>")
				.append("<p>Լ������ţ�" + cardMsg.getREPAY_CARD().trim() + "</p>");
				
			}

		} else
		{
			sBuffer.append("<html><body><p>������Ŀ��֣�" + cardMsg.getCARD_NAME().trim() + "</p>")
			.append("<p>�ƶ�������ţ�" + cardMsg.getAPP_NUM().trim() + "</p>")
			.append("<p><b><font color=\"#00FFFF\">������Ϣ</font></b></p>")
			.append("<p>����������" + cardMsg.getCUST_NAME().trim() + "</p>")
			.append("<p>ƴ��������" + cardMsg.getLAST_NAME().trim() + " " + cardMsg.getFIRST_NAME().trim() + "</p>")
			.append("<p>�Ա�" + cardMsg.getCUST_SEX().trim() + "</p>")
			.append("<p>��סַ��" + cardMsg.getPRE_ADDR().trim() + "</p>")
			.append("<p>�������ڣ�" + cardMsg.getBIRTHDAY().trim() + "</p>")
			.append("<p>֤�����ͣ�" + cardMsg.getCERT_TYPE().trim() + "</p>")
			.append("<p>֤�����룺" + cardMsg.getCERT_NO().trim() + "</p>")
			.append("<p>������" + cardMsg.getNATIONALITY().trim() + "</p>")
			.append("<p>�������룺" + cardMsg.getPRE_POST().trim() + "</p>")
			.append("<p>�������䣺" + cardMsg.getEMAIL().trim() + "</p>")
			.append("<p>��ϵ�绰��" + cardMsg.getPRE_ZONE_NO().trim() + "-" + cardMsg.getPRE_PHONE().trim() + "</p>")
			.append("<p>�ֻ����룺" + cardMsg.getPRE_MOBILE().trim() + "</p>")
			.append("<p>�������˹�ϵ��" + cardMsg.getRELATION_BET().trim() + "</p>")
			.append("<p>�쿨��ʽ��" + cardMsg.getGET_CARD_MODE().trim() + "</p>");
		}

		return sBuffer.toString();
	}

	/**
	 * 
	 * �������� : getAppNum �������� : ����������ֵ˵����
	 * 
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:29:02 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static String getAppNum()
	{
		// APP_NUM_FLAG �ṹ��8λ����+2λ�������+1λ�Ƿ�ʹ�ù��ı�ʶ(0/1)
		SimpleDateFormat app_sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String appnum = null;
		String app_flag = MyConstants.spf.getString(MyConstants.APP_NUM_FLAG, "");
		String current = app_sdf.format(new Date());
		if (!app_flag.equals(""))
		{
			String isused = app_flag.substring(app_flag.length() - 1, app_flag.length());
			String last = app_flag.substring(0, 8);
			int num = Integer.valueOf(app_flag.substring(8, 10));
			if (!current.equals(last))
			{
				appnum = CustMsgActivity.map.get("aBranch").substring(5, 9) + sdf.format(new Date()) + "01";
				MyConstants.editor.putString(MyConstants.APP_NUM_FLAG, current + "010");
			} else
			{
				if (isused.equals("0"))
				{
					appnum = CustMsgActivity.map.get("aBranch").substring(5, 9) + sdf.format(new Date()) + (num > 9 ? num : "0" + num);
				} else
				{
					num++;
					if (num == 100)
					{
						num = 1;
					}
					appnum = CustMsgActivity.map.get("aBranch").substring(5, 9) + sdf.format(new Date()) + (num > 9 ? num : "0" + num);
					MyConstants.editor.putString(MyConstants.APP_NUM_FLAG, current + (num > 9 ? num : "0" + num) + "0");
				}
			}
		} else
		{
			appnum = CustMsgActivity.map.get("aBranch").substring(5, 9) + sdf.format(new Date()) + "01";
			MyConstants.editor.putString(MyConstants.APP_NUM_FLAG, current + "010");
		}
		MyConstants.editor.commit();
//		try
//		{
//			appnum = appnum + XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aOperatorId");
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		appnum = appnum+"          ";
		return appnum;
	}

	/**
	 * 
	 * �������� : getPosition �������� : ����������ֵ˵����
	 * 
	 * @param data
	 * @param item
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:28:57 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static int getPosition(String[] data, String item)
	{
		int p = 0;
		for (int i = 0; i < data.length; i++)
		{
			if (data[i].equals(item))
			{
				p = i;
			}
		}
		return p;
	}

	/**
	 * 
	 * �������� : getIMEI �������� : ����������ֵ˵����
	 * 
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-7-19 ����2:28:52 �޸��ˣ�kcx ���� ��
	 * 
	 */
	public static String getIMEI()
	{
		TelephonyManager tm = (TelephonyManager) MyApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getDeviceId() != null)
		{
			return tm.getDeviceId();
		}
		return "";
	}
	
	public static int getVersionCode()
	{
		String pName = "com.mega.abcregister";  
		int versionCode = 0;
		try {  
		        PackageInfo pinfo = MyApplication.getInstance().getPackageManager().getPackageInfo(pName, PackageManager.GET_CONFIGURATIONS);  
		        versionCode = pinfo.versionCode;  
		} catch (NameNotFoundException e) {  
			return versionCode;
		}  
		return versionCode;
	}
	
	public static String getVersionName()
	{
		String pName = "com.mega.abcregister";  
		String versionName = null;
		try {  
		        PackageInfo pinfo = MyApplication.getInstance().getPackageManager().getPackageInfo(pName, PackageManager.GET_CONFIGURATIONS);  
		        versionName = pinfo.versionName;  
		} catch (NameNotFoundException e) {  
			return versionName;
		}  
		return versionName;
	}
	
	public static String execRootCmd(String cmd) {
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;

        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());
            System.out.println(cmd);

            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();

            String line = null;
            while ((line = dis.readLine()) != null) {
                result += line;
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }
	
	public static void setPowerOnSFZ()
	{
		File awakeTimeFile = new File("/proc/s706_power/s706_power_sfz");
        FileWriter fr;
        try
       {
             fr = new FileWriter(awakeTimeFile);
             fr.write("1"); 
             fr.close();
        } 
        catch (IOException e) 
        {
                    e.printStackTrace();
         }
	}

}

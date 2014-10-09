package com.mega.abcregister;

import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mega.dao.DataDao;
import com.mega.myview.CustomSinnper;
import com.mega.myview.HintEdittext;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.tools.IdCardUtils;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class CustAttachActivity extends Activity
{
	private Button nextButton;
	private Button lastButton;
	private CustomSinnper spNation;
	private CustomSinnper spSex;
	private CustomSinnper spRelation;
	private CustomSinnper spProvince;
	private CustomSinnper spCity;
	private CustomSinnper spCounty;
	private CustomSinnper spCardType;
	private CustomSinnper spGetCardWay;//�쿨��ʽ
	private Calendar cd;
	private HintEdittext name;
	private HintEdittext pyfname;
	private HintEdittext pylname;
	private HintEdittext idnum;
	private EditText otherType;
	private HintEdittext birth;
	private HintEdittext postCode;
	private EditText tel1;
	private EditText tel2;
	private HintEdittext mobile;
	private EditText addrdetail;
	private HintEdittext etemail;
	public static String chsname = "";
	public static String plname = "";
	public static String pfname = "";
	public static String sex = "";
	public static String birthday = "";
	public static String cardtype = "";
	public static String cardname="";
	public static String id = "";
	public static String relation = "";
	public static String post = "";
	public static String zone_num = "";
	public static String tel = "";
	public static String mobilephone = "";
	public static String addr = "";
	public static String getway = "";
	public static String email = "";
	public static String nation = "";
	private ArrayAdapter<CharSequence> cardAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_attach);
		initComponent();
		addListener();
	}
	
	private void initComponent()
	{
		etemail = (HintEdittext) findViewById(R.id.et_email);
		etemail.init("��������", Color.GRAY, 25, 5, 11);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		spGetCardWay = (CustomSinnper) findViewById(R.id.sp_getcard_way);
		spNation = (CustomSinnper)findViewById(R.id.sp_attach_nation);
		spSex = (CustomSinnper) findViewById(R.id.sp_attach_sex);
		spRelation = (CustomSinnper) findViewById(R.id.sp_attach_rela);
		spProvince = (CustomSinnper) findViewById(R.id.sp_attach_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_attach_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_attach_county);
		otherType = (EditText) findViewById(R.id.et_attach_othertype);
		spCardType = (CustomSinnper) findViewById(R.id.sp_attach_cardtype);
		name = (HintEdittext) findViewById(R.id.et_attach_chsname);
		name.init("*��������", Color.GRAY, 25, 5, 11);
		pylname = (HintEdittext) findViewById(R.id.et_py_lname);
		pylname.init("*ƴ����", Color.GRAY, 25, 5, 11);
		pyfname = (HintEdittext) findViewById(R.id.et_py_fname);
		pyfname.init("*��", Color.GRAY, 25, 5, 11);
		idnum = (HintEdittext) findViewById(R.id.et_attach_idnum);
		idnum.init("*���֤������", Color.GRAY, 25, 5, 11);
		birth = (HintEdittext) findViewById(R.id.et_attach_birth);
		birth.init("*��������", Color.GRAY, 25, 5, 11);
		postCode = (HintEdittext) findViewById(R.id.et_attach_postcode);
		postCode.init("*�ʱ�", Color.GRAY, 25, 5, 11);
		tel1 = (EditText) findViewById(R.id.et_house_tel1);
		tel2 = (EditText) findViewById(R.id.et_house_tel2);
		mobile = (HintEdittext) findViewById(R.id.et_attach_phonenum);
		mobile.init("�ֻ�", Color.GRAY, 25, 5, 11);
		addrdetail = (EditText) findViewById(R.id.et_attach_add_detail);
		cd = Calendar.getInstance();
		cd.setTime(new Date());
		String[] nationData = DataDao.getSingleData("sp_countries", "title", "title");
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
		cardAdapter = ArrayAdapter.createFromResource(this, R.array.chs_cardtype_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> nationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nationData);
		ArrayAdapter<CharSequence> relationAdapter = ArrayAdapter.createFromResource(this, R.array.relation_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> getCardAdapter = ArrayAdapter.createFromResource(this, R.array.getcard_list_entries, android.R.layout.simple_list_item_1);
//		getCardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		nationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		relationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spCardType.setAdapter(cardAdapter);
		spSex.setAdapter(sexAdapter);
		spNation.setAdapter(nationAdapter);
		spRelation.setAdapter(relationAdapter);
		spProvince.setAdapter(provinceAdapter);
//		spProvince.setSelection(MyConstants.getPosition(provinceData, "��ѡ��"));
		spGetCardWay.setAdapter(getCardAdapter);
		spNation.setText("�й�");
//		spNation.setEnabled(false);
	}
	
	private void addListener()
	{
		spSex.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spRelation.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spGetCardWay.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spNation.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object obj = parent.getItemAtPosition(position);
				String nationValue = obj.toString();
				if (nationValue.equals("�й�"))
				{
					cardAdapter = ArrayAdapter.createFromResource(CustAttachActivity.this, R.array.chs_cardtype_list_entries, android.R.layout.simple_list_item_1);
//					cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCardType.setAdapter(cardAdapter);
				}
				else {
					cardAdapter = ArrayAdapter.createFromResource(CustAttachActivity.this, R.array.other_cardtype_list_entries, android.R.layout.simple_list_item_1);
//					cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCardType.setAdapter(cardAdapter);
				}
			}
		});
		nextButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				nation = DataDao.getSingleString("sp_countries", "code", "title", spNation.getText().toString());
				email = etemail.getText().toString().trim();
				chsname = name.getText().toString().trim();
				plname = pylname.getText().toString().trim().toUpperCase();
				pfname = pyfname.getText().toString().trim().toUpperCase();
				sex = spSex.getText().toString().trim();
				if (sex.equals("��"))
				{
					sex = "M";
				}
				else if(sex.equals("Ů"))
				{
					sex = "F";
				}
				birthday = birth.getText().toString().trim();
				cardtype = spCardType.getText().toString().trim();
				if (cardtype.equals("���֤"))
				{
					cardtype = "I";
				}
				if (cardtype.equals("����"))
				{
					cardtype = "P";
				}
				if (cardtype.equals("����"))
				{
					cardtype = "O";
					cardname = otherType.getText().toString().trim();
				}
				id = idnum.getText().toString().trim().toUpperCase();
				relation = spRelation.getText().toString().trim();
				if (relation.equals("��ż"))
				{
					relation = "0";
				}
				else if (relation.equals("����")) {
					relation = "1";
				}
				else if (relation.equals("����")) {
					relation = "2";
				}
				else if (relation.equals("����")) {
					relation = "3";
				}
				post = postCode.getText().toString().trim();
				zone_num = tel1.getText().toString().trim();
				tel = tel2.getText().toString().trim();
				mobilephone = mobile.getText().toString().trim();
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					System.out.println("�ؼ�ѡ���Ѽ���");
					county = spCounty.getText().toString().trim();
				}
				String addrdetl = addrdetail.getText().toString().trim();
				addr = province+city+county+addrdetl;
				System.out.println(addr);
				getway = spGetCardWay.getText().toString().trim();
				if (getway.equals("����������ȡ"))
				{
					getway = "0";
				}
				else if (getway.equals("�ʼĵ��˵���ַ")) {
					getway = "3";
				}
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (cardtype.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t"+count+"�����֤������δѡ��\n\n");
				}
				if (sex.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t"+count+"���Ա�δѡ��\n\n");
				}
				if (relation.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t"+count+"���������������������˹�ϵδѡ��\n\n");
				}
				if (getway.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t"+count+"���쿨��ʽδѡ��\n\n");
				}
				if (!MyConstants.baseCheck(chsname, 40))
				{
					count++;
					sBuffer.append("\t"+count+"������������ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(plname, 10))
				{
					count++;
					sBuffer.append("\t"+count+"��ƴ���ո�ʽ�����δ��д\n\n");
				} 
				if (!MyConstants.baseCheck(pfname, 16))
				{
					count++;
					sBuffer.append("\t"+count+"��ƴ������ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(birthday, 8))
				{
					count++;
					sBuffer.append("\t"+count+"����������δ��д\n\n");
				}
				if (cardtype.equals("O")&&!MyConstants.baseCheck(cardname, 20))
				{
					count++;
					sBuffer.append("\t"+count+"������֤�����͸�ʽ�����δ��д\n\n");
				}
				if(cardtype.equals("I")&&!IdCardUtils.validateCard(id))
				{
					count++;
					sBuffer.append("\t"+count+"�����֤���벻���ڻ�δ��д\n\n");
				}
				if (!cardtype.equals("I")&&!MyConstants.baseCheck(id, 30))
				{
					count++;
					sBuffer.append("\t"+count+"�����ջ�����֤�������ʽ�����δ��д\n\n");
				}
				if (!id.equals(""))
				{
					int sexflag = Integer.valueOf(id.substring(16, 17));
					boolean sexmatch = false;
					if (sex.equals("F"))
					{
						if (sexflag%2==0)
						{
							sexmatch = true;
						}
					}
					else if (sex.equals("M")) {
						if (sexflag%2!=0)
						{
							sexmatch = true;
						}
					}
					if (cardtype.equals("I")&&IdCardUtils.validateCard(id)&&(!birthday.equals(id.substring(6, 14))||!sexmatch))
					{
						count++;
						sBuffer.append("\t"+count+"�����֤������������ڻ��Ա�ƥ��\n\n");
					}
				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("������")||sp.equals("�����")||sp.equals("�Ϻ���")||sp.equals("������"))
				{
					if (spCity.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"����ϵ��ַ��ʽ�����δ��д\n\n");
					}
				}else {
					if (sp.equals("��ѡ��")||spCity.getText().toString().trim().equals("��ѡ��")||spCounty.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"����ϵ��ַ��ʽ�����δ��д\n\n");
					}
				}
				if (!MyConstants.checkPostCode(post))
				{
					count++;
					sBuffer.append("\t"+count+"�����������ʽ�����δ��д\n\n");
				}
				if (!email.equals("")&&!MyConstants.checkEmailAddr(email))
				{
					count++;
					sBuffer.append("\t"+count+"�����������ʽ����\n\n");
				}
				if ((zone_num.length()!=3 && zone_num.length()!=4)||!MyConstants.checkTelNum(tel))
				{
					count++;
					sBuffer.append("\t"+count+"����ϵ�绰��ʽ�����δ��д\n\n");
				}
				if ((!mobile.equals(""))&&!MyConstants.checkPhoneNum(mobilephone))
				{
					count++;
					sBuffer.append("\t"+count+"���ֻ������ʽ����\n\n");
				}
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
					CustMsgActivity.mTabHost.setCurrentTab(5);
				}
			}
		});
		lastButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				CustMsgActivity.mTabHost.setCurrentTab(3);
			}
		});
		birth.setOnTouchListener(new OnTouchListener()
		{
			
			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO Auto-generated method stub
				if (event.getAction()==MotionEvent.ACTION_DOWN) 
				{
					new DatePickerDialog(CustAttachActivity.this, new OnDateSetListener()
					{
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
						{
							int month = monthOfYear+1;
							birth.setText(year + "" + (month>9?month:("0"+month)) + "" + (dayOfMonth>9?dayOfMonth:("0"+dayOfMonth)));
						}
					}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
				}
				return false;
			}
		});
		spProvince.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object obj = parent.getItemAtPosition(position);
				String provinceValue = obj.toString();
				String provinceID = DataDao.getSingleString("province", "provinceID", "provinceNAME", provinceValue);
				String[] cityData = DataDao.getMultiString("city", "cityNAME", "provinceID", provinceID);
				if (cityData.length==0)
				{
					spCity.setVisibility(View.INVISIBLE);
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCity.setVisibility(View.VISIBLE);
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CustAttachActivity.this, android.R.layout.simple_list_item_1, cityData);
//					cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCity.setAdapter(cityAdapter);
				}
			}
		});
		spCity.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object obj = parent.getItemAtPosition(position);
				String cityValue = obj.toString();
				String cityID = DataDao.getSingleString("city", "cityID", "cityNAME", cityValue);
				String[] countyData = DataDao.getMultiString("county", "countyNAME", "cityID", cityID);
				if (countyData.length==0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(CustAttachActivity.this, android.R.layout.simple_list_item_1, countyData);
//					countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCounty.setAdapter(countyAdapter);
				}
				
			}
		});
		spCardType.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object obj = parent.getItemAtPosition(position);
				String typeValue = obj.toString();
				if (typeValue.equals("����"))
				{
					otherType.setVisibility(View.VISIBLE);
				}
				else {
					otherType.setVisibility(View.INVISIBLE);
				}
			}
		});
		spCounty.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}

	private int getPosition(String[] data, String item)
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
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
	    {     
//			CustMsgActivity.mTabHost.setCurrentTab(3);
			return true;
	    }
		return super.dispatchKeyEvent(event);
	}
}

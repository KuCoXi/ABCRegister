package com.mega.abcregister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mega.myview.CustomSinnper;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.myview.HintEdittext;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

import com.mega.dao.DataDao;
import com.mega.tools.MyConstants;

public class CustRelaActivity extends Activity
{

	private Button nextButton;
	private Button lastButton;
	private CustomSinnper spRelaSex; //��ϵ���Ա�
	private CustomSinnper spRelation;//��ϵ���������˹�ϵ
	private CustomSinnper spHomeRelaSex;//��������ϵ���Ա�
	private CustomSinnper spHomeRelation;//��������ϵ���������˹�ϵ
	private CustomSinnper spProvince;
	private CustomSinnper spCity;
	private CustomSinnper spCounty;
	private CustomSinnper spBorn;//�Ƿ񱾵��л���
	private HintEdittext chsname;//��ϵ����������
	private EditText tel1;//��ϵ�˵绰����
	private EditText tel2;//��ϵ�˵绰����
	private HintEdittext phone;//��ϵ���ֻ�
	private HintEdittext relachsname;//��������ϵ����������
	private EditText relatel1;//��������ϵ�˵绰����
	private EditText relatel2;//��������ϵ�˵绰����
	private HintEdittext relaphone;//��������ϵ���ֻ�
	private EditText relaaddr;//��������ϵ����ϸ��ַ
	private HintEdittext relapostcode;//�������ʱ�
	private String card_name = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//��Ʒ����
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_rela);
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		chsname = (HintEdittext) findViewById(R.id.et_rela_name);
		chsname.init("*��ϵ������", Color.GRAY, 25, 5, 11);
		tel1 = (EditText) findViewById(R.id.et_rela_tel1);
		tel2 = (EditText) findViewById(R.id.et_rela_tel2);
		phone = (HintEdittext) findViewById(R.id.et_rela_phonenum);
		phone.init("�ֻ�", Color.GRAY, 25, 5, 11);
		relachsname = (HintEdittext) findViewById(R.id.et_homerela_name);
		relachsname.init("*��������ϵ������", Color.GRAY, 25, 5, 11);
		relatel1 = (EditText) findViewById(R.id.et_homerela_tel1);
		relatel2 = (EditText) findViewById(R.id.et_homerela_tel2);
		relaphone = (HintEdittext) findViewById(R.id.et_homerela_phonenum);
		relaphone.init("*�ֻ�", Color.GRAY, 25, 5, 11);
		relaaddr = (EditText) findViewById(R.id.et_homeadd_detail);
		relapostcode = (HintEdittext) findViewById(R.id.et_homerela_postcode);
		relapostcode.init("*�ʱ�", Color.GRAY, 25, 5, 11);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		spRelaSex = (CustomSinnper) findViewById(R.id.sp_rela_sex);
		spRelation = (CustomSinnper) findViewById(R.id.sp_relation);
		spHomeRelaSex = (CustomSinnper) findViewById(R.id.sp_homerela_sex);
		spHomeRelation = (CustomSinnper) findViewById(R.id.sp_home_relation);
		spProvince = (CustomSinnper) findViewById(R.id.sp_home_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_home_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_home_county);
		spBorn = (CustomSinnper) findViewById(R.id.sp_born_place);
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> relationAdapter1 = ArrayAdapter.createFromResource(this, R.array.relation_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> relationAdapter2 = ArrayAdapter.createFromResource(this, R.array.relation_list_entries, android.R.layout.simple_list_item_1);
		if (MyConstants.spf.getString(MyConstants.MARR_STAT, "").equals("0"))
		{
			relationAdapter1 = ArrayAdapter.createFromResource(this, R.array.nomarr_list_entries, android.R.layout.simple_list_item_1);
			relationAdapter2 = ArrayAdapter.createFromResource(this, R.array.nomarr_list_entries, android.R.layout.simple_list_item_1);
		}
		ArrayAdapter<CharSequence> bornAdapter = ArrayAdapter.createFromResource(this, R.array.born_list_entries, android.R.layout.simple_list_item_1);
		spBorn.setAdapter(bornAdapter);
		spBorn.setText("������");
		spRelaSex.setAdapter(sexAdapter);
		spRelation.setAdapter(relationAdapter1);
		spHomeRelaSex.setAdapter(sexAdapter);
		spHomeRelation.setAdapter(relationAdapter2);
		spProvince.setAdapter(provinceAdapter);
		if (CustMsgActivity.card.getCardGrade().equals("4"))
		{
			spBorn.setText("������");
			spBorn.setEnabled(false);
		}
		if (spBorn.getText().equals("������"))
		{
			spHomeRelaSex.setEnabled(false);
			spHomeRelation.setEnabled(false);
			relachsname.setEnabled(false);
			relachsname.setClickable(false);
			relaphone.setEnabled(false);
			relaphone.setClickable(false);
			relatel1.setEnabled(false);
			relatel1.setEnabled(false);
			relatel2.setEnabled(false);
			relatel2.setEnabled(false);
			spProvince.setEnabled(false);
			spCity.setEnabled(false);
			spCounty.setEnabled(false);
			relaaddr.setEnabled(false);
			relaaddr.setClickable(false);
			relapostcode.setEnabled(false);
			relapostcode.setClickable(false);
		}
		else {
			spHomeRelaSex.setEnabled(true);
			spHomeRelation.setEnabled(true);
			relachsname.setEnabled(true);
			relachsname.setClickable(true);
			relaphone.setEnabled(true);
			relaphone.setClickable(true);
			relatel1.setEnabled(true);
			relatel1.setEnabled(true);
			relatel2.setEnabled(true);
			relatel2.setEnabled(true);
			spProvince.setEnabled(true);
			spCity.setEnabled(true);
			spCounty.setEnabled(true);
			relaaddr.setEnabled(true);
			relaaddr.setClickable(true);
			relapostcode.setEnabled(true);
			relapostcode.setClickable(true);
		}
//		spProvince.setSelection(MyConstants.getPosition(provinceData, "��ѡ��"));
	}
	
	private void addListener()
	{
		spRelaSex.setOnItemSeletedListener(new OnItemSeletedListener()
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
		spProvince.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String provinceValue = object.toString();
				String provinceID = DataDao.getSingleString("province", "provinceID", "provinceNAME", provinceValue);
				String[] cityData = DataDao.getMultiString("city", "cityNAME", "provinceID", provinceID);
				if (cityData.length==0)
				{
					spCity.setVisibility(View.INVISIBLE);
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCity.setVisibility(View.VISIBLE);
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CustRelaActivity.this, android.R.layout.simple_list_item_1, cityData);
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
				Object object = parent.getItemAtPosition(position);
				String cityValue = object.toString();
				String cityID = DataDao.getSingleString("city", "cityID", "cityNAME", cityValue);
				String[] countyData = DataDao.getMultiString("county", "countyNAME", "cityID", cityID);
				if (countyData.length==0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(CustRelaActivity.this, android.R.layout.simple_list_item_1, countyData);
//					countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCounty.setAdapter(countyAdapter);
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
		spHomeRelaSex.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spHomeRelation.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spBorn.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String value = object.toString();
				if (value.equals("�Ǳ�����"))
				{
					spHomeRelaSex.setEnabled(true);
					spHomeRelation.setEnabled(true);
					relachsname.setEnabled(true);
					relachsname.setClickable(true);
					relaphone.setEnabled(true);
					relaphone.setClickable(true);
					relatel1.setEnabled(true);
					relatel1.setEnabled(true);
					relatel2.setEnabled(true);
					relatel2.setEnabled(true);
					spProvince.setEnabled(true);
					spCity.setEnabled(true);
					spCounty.setEnabled(true);
					relaaddr.setEnabled(true);
					relaaddr.setClickable(true);
					relapostcode.setEnabled(true);
					relapostcode.setClickable(true);
				}
				else {
					spHomeRelaSex.setEnabled(false);
					spHomeRelation.setEnabled(false);
					relachsname.setEnabled(false);
					relachsname.setClickable(false);
					relaphone.setEnabled(false);
					relaphone.setClickable(false);
					relatel1.setEnabled(false);
					relatel1.setEnabled(false);
					relatel2.setEnabled(false);
					relatel2.setEnabled(false);
					spProvince.setEnabled(false);
					spCity.setEnabled(false);
					spCounty.setEnabled(false);
					relaaddr.setEnabled(false);
					relaaddr.setClickable(false);
					relapostcode.setEnabled(false);
					relapostcode.setClickable(false);
				}
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String cname = chsname.getText().toString().trim();
				String sex = spRelaSex.getText().toString().trim();
				if (sex.equals("��"))
				{
					sex = "M";
				}
				else if(sex.equals("Ů"))
				{
					sex = "F";
				}
				String rela = spRelation.getText().toString().trim();
				if (rela.equals("��ż"))
				{
					rela = "0";
				}
				else if (rela.equals("����")) {
					rela = "1";
				}
				else if (rela.equals("����")) {
					rela = "2";
				}
				else if (rela.equals("����")) {
					rela = "3";
				}
				String telnum1 = tel1.getText().toString().trim();
				String telnum2 = tel2.getText().toString().trim();
				String mobile = phone.getText().toString().trim();
				String homerelaname = "";
				String homerelasex = "";
				String homerela = "";
				String homerelatel1 = "";
				String homerelatel2 = "";
				String homerelamobile = "";
				String homerelapostcode = "";
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = "";
				String addr = "";//��ַ
				if (spBorn.getText().equals("�Ǳ�����"))
				{
					homerelaname = relachsname.getText().toString().trim();
					homerelasex = spHomeRelaSex.getText().toString().trim();
					if (homerelasex.equals("��"))
					{
						homerelasex = "M";
					}
					else if(homerelasex.equals("Ů"))
					{
						homerelasex = "F";
					}
					homerela = spHomeRelation.getText().toString().trim();
					if (homerela.equals("��ż"))
					{
						homerela = "0";
					}
					else if (homerela.equals("����")) {
						homerela = "1";
					}
					else if (homerela.equals("����")) {
						homerela = "2";
					}
					else if (homerela.equals("����")) {
						homerela = "3";
					}
					homerelatel1 = relatel1.getText().toString().trim();
					if (!homerelatel1.equals(""))
					{
						homerelatel1 = homerelatel1.length()==3?homerelatel1:homerelatel1.substring(1);
					}
					homerelatel2 = relatel2.getText().toString().trim();
					homerelamobile = relaphone.getText().toString().trim();
					homerelapostcode = relapostcode.getText().toString().trim();
					addrdetail = relaaddr.getText().toString().trim();
					addr = province+city+county+addrdetail;
				}
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (sex.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"����ϵ���Ա�δѡ��\n\n");
				}
				if (rela.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"����ϵ���������˹�ϵδѡ��\n\n");
				}
				if (!MyConstants.baseCheck(cname, 40))
				{
					count++;
					sBuffer.append(count+"����ϵ������������ʽ�����δ��д\n\n");
				}else if (cname.equals(MyConstants.spf.getString(MyConstants.CUST_NAME,""))) {
					count++;
					sBuffer.append(count+"����ϵ�������������˲���һ��\n\n");
				}
				if ((telnum1.length()!=3&&telnum1.length()!=4)||!MyConstants.checkTelNum(telnum2))
				{
					count++;
					sBuffer.append(count+"����ϵ�˵绰�����ʽ�����δ��д\n\n");
				}else if (telnum2.equals(MyConstants.spf.getString(MyConstants.COMP_PHONE,""))){
					count++;
					sBuffer.append(count+"����ϵ�˵绰�����뵥λ�绰һ��\n\n");
				}else if (telnum2.equals(MyConstants.spf.getString(MyConstants.PRE_PHONE,""))) {
					count++;
					sBuffer.append(count+"����ϵ�˵绰��������סַ�绰һ��\n\n");
				}
				if (!mobile.equals("")&&!MyConstants.checkPhoneNum(mobile))
				{
					count++;
					sBuffer.append(count+"����ϵ���ֻ������ʽ����\n\n");
				}
				if (spBorn.getText().equals("�Ǳ�����"))
				{
					if (homerelasex.equals("��ѡ��"))
					{
						count++;
						sBuffer.append(count+"����������ϵ���Ա�δѡ��\n\n");
					}
					if (homerela.equals("��ѡ��"))
					{
						count++;
						sBuffer.append(count+"����������ϵ���������˹�ϵδѡ��\n\n");
					}
					if (!MyConstants.baseCheck(homerelaname, 40))
					{
						count++;
						sBuffer.append(count+"����������ϵ������������ʽ�����δ��д\n\n");
					}
					if ((homerelatel1.length()!=3&&homerelatel1.length()!=4)||!MyConstants.checkTelNum(homerelatel2))
					{
						count++;
						sBuffer.append(count+"����������ϵ�˵绰�����ʽ�����δ��д\n\n");
					}
					if (!MyConstants.checkPhoneNum(homerelamobile))
					{
						count++;
						sBuffer.append(count+"����������ϵ���ֻ������ʽ�����δ��д\n\n");
					}
					if (!MyConstants.checkPostCode(homerelapostcode))
					{
						count++;
						sBuffer.append(count+"���������ʱ��ʽ�����δ��д\n\n");
					}
					String sp = spProvince.getText().toString().trim();
					if (sp.equals("������")||sp.equals("�����")||sp.equals("�Ϻ���")||sp.equals("������"))
					{
						if (spCity.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count+"�����˻�������ϵ��ַ��ʽ�����δ��д\n\n");
						}
					}else {
						if (sp.equals("��ѡ��")||spCity.getText().toString().trim().equals("��ѡ��")||spCounty.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count+"�����˻�������ϵ��ַ��ʽ�����δ��д\n\n");
						}
					}
				}
				if (sex.equals(MyConstants.spf.getString(MyConstants.CUST_SEX,""))&&rela.equals("0"))
				{
					count++;
					sBuffer.append(count+"������������ϵ�˹�ϵ��������ż\n\n");
				}
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
					MyConstants.editor.putString(MyConstants.LM_NAME, cname);
					MyConstants.editor.putString(MyConstants.LM_SEX, sex);
					MyConstants.editor.putString(MyConstants.RELATION_BET, rela);
					MyConstants.editor.putString(MyConstants.LM_PRE_ZONENO, telnum1.length()==3?telnum1:telnum1.substring(1));
					MyConstants.editor.putString(MyConstants.LM_PRE_PHONE, telnum2);
					MyConstants.editor.putString(MyConstants.HUKOU_RELNAME, homerelaname);
					MyConstants.editor.putString(MyConstants.HUKOU_RELSEX, homerelasex);
					MyConstants.editor.putString(MyConstants.HUKOU_RELATION, homerela);
					MyConstants.editor.putString(MyConstants.HUKOU_RELZONENO, homerelatel1);
					MyConstants.editor.putString(MyConstants.HUKOU_RELPHONE, homerelatel2);
					MyConstants.editor.putString(MyConstants.HUKOU_RELMOBILE, homerelamobile);
					MyConstants.editor.putString(MyConstants.REG_POST, homerelapostcode);
					MyConstants.editor.putString(MyConstants.REG_ADDR, addr);
					MyConstants.editor.putString(MyConstants.LM_MOBILE, mobile);
					MyConstants.editor.putString(MyConstants.LM_COMP, "");
					MyConstants.editor.putString(MyConstants.LM_COMP_ZONENO, "");
					MyConstants.editor.putString(MyConstants.LM_COMP_PHONE, "");
					MyConstants.editor.putString(MyConstants.LM_PRE_ADDR, "");
					if (spBorn.getText().equals("������"))
					{
						MyConstants.editor.putString(MyConstants.PRE_REG_ADDR, "4");//������ַͬ��סַ
					}
					else {
						MyConstants.editor.putString(MyConstants.PRE_REG_ADDR, "4");//������ַ��ͬ��סַ
					}
					if (MyConstants.spf.getBoolean(MyConstants.OTHER_CARD, false))
					{
						CustMsgActivity.mTabHost.setCurrentTab(4);
					}
					else {
						CustMsgActivity.mTabHost.setCurrentTab(5);
					}
				}
				
			}
		});
		lastButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				CustMsgActivity.mTabHost.setCurrentTab(2);
			}
		});
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
	    {     
//			CustMsgActivity.mTabHost.setCurrentTab(2);
			return true;
	    }
		return super.dispatchKeyEvent(event);
	}
}

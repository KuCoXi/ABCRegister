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
	private CustomSinnper spRelaSex; //联系人性别
	private CustomSinnper spRelation;//联系人与申请人关系
	private CustomSinnper spHomeRelaSex;//户籍地联系人性别
	private CustomSinnper spHomeRelation;//户籍地联系人与申请人关系
	private CustomSinnper spProvince;
	private CustomSinnper spCity;
	private CustomSinnper spCounty;
	private CustomSinnper spBorn;//是否本地市户籍
	private HintEdittext chsname;//联系人中文姓名
	private EditText tel1;//联系人电话区号
	private EditText tel2;//联系人电话号码
	private HintEdittext phone;//联系人手机
	private HintEdittext relachsname;//户籍地联系人中文姓名
	private EditText relatel1;//户籍地联系人电话区号
	private EditText relatel2;//户籍地联系人电话号码
	private HintEdittext relaphone;//户籍地联系人手机
	private EditText relaaddr;//户籍地联系人详细地址
	private HintEdittext relapostcode;//户籍地邮编
	private String card_name = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//产品名称
	
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
		chsname.init("*联系人姓名", Color.GRAY, 25, 5, 11);
		tel1 = (EditText) findViewById(R.id.et_rela_tel1);
		tel2 = (EditText) findViewById(R.id.et_rela_tel2);
		phone = (HintEdittext) findViewById(R.id.et_rela_phonenum);
		phone.init("手机", Color.GRAY, 25, 5, 11);
		relachsname = (HintEdittext) findViewById(R.id.et_homerela_name);
		relachsname.init("*户籍地联系人姓名", Color.GRAY, 25, 5, 11);
		relatel1 = (EditText) findViewById(R.id.et_homerela_tel1);
		relatel2 = (EditText) findViewById(R.id.et_homerela_tel2);
		relaphone = (HintEdittext) findViewById(R.id.et_homerela_phonenum);
		relaphone.init("*手机", Color.GRAY, 25, 5, 11);
		relaaddr = (EditText) findViewById(R.id.et_homeadd_detail);
		relapostcode = (HintEdittext) findViewById(R.id.et_homerela_postcode);
		relapostcode.init("*邮编", Color.GRAY, 25, 5, 11);
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
		spBorn.setText("本地市");
		spRelaSex.setAdapter(sexAdapter);
		spRelation.setAdapter(relationAdapter1);
		spHomeRelaSex.setAdapter(sexAdapter);
		spHomeRelation.setAdapter(relationAdapter2);
		spProvince.setAdapter(provinceAdapter);
		if (CustMsgActivity.card.getCardGrade().equals("4"))
		{
			spBorn.setText("本地市");
			spBorn.setEnabled(false);
		}
		if (spBorn.getText().equals("本地市"))
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
//		spProvince.setSelection(MyConstants.getPosition(provinceData, "请选择"));
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
				if (value.equals("非本地市"))
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
				if (sex.equals("男"))
				{
					sex = "M";
				}
				else if(sex.equals("女"))
				{
					sex = "F";
				}
				String rela = spRelation.getText().toString().trim();
				if (rela.equals("配偶"))
				{
					rela = "0";
				}
				else if (rela.equals("亲属")) {
					rela = "1";
				}
				else if (rela.equals("朋友")) {
					rela = "2";
				}
				else if (rela.equals("其他")) {
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
				String addr = "";//地址
				if (spBorn.getText().equals("非本地市"))
				{
					homerelaname = relachsname.getText().toString().trim();
					homerelasex = spHomeRelaSex.getText().toString().trim();
					if (homerelasex.equals("男"))
					{
						homerelasex = "M";
					}
					else if(homerelasex.equals("女"))
					{
						homerelasex = "F";
					}
					homerela = spHomeRelation.getText().toString().trim();
					if (homerela.equals("配偶"))
					{
						homerela = "0";
					}
					else if (homerela.equals("亲属")) {
						homerela = "1";
					}
					else if (homerela.equals("朋友")) {
						homerela = "2";
					}
					else if (homerela.equals("其他")) {
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
				if (sex.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、联系人性别未选择\n\n");
				}
				if (rela.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、联系人与申请人关系未选择\n\n");
				}
				if (!MyConstants.baseCheck(cname, 40))
				{
					count++;
					sBuffer.append(count+"、联系人中文姓名格式错误或未填写\n\n");
				}else if (cname.equals(MyConstants.spf.getString(MyConstants.CUST_NAME,""))) {
					count++;
					sBuffer.append(count+"、联系人姓名与申请人不能一致\n\n");
				}
				if ((telnum1.length()!=3&&telnum1.length()!=4)||!MyConstants.checkTelNum(telnum2))
				{
					count++;
					sBuffer.append(count+"、联系人电话号码格式错误或未填写\n\n");
				}else if (telnum2.equals(MyConstants.spf.getString(MyConstants.COMP_PHONE,""))){
					count++;
					sBuffer.append(count+"、联系人电话不能与单位电话一致\n\n");
				}else if (telnum2.equals(MyConstants.spf.getString(MyConstants.PRE_PHONE,""))) {
					count++;
					sBuffer.append(count+"、联系人电话不能与现住址电话一致\n\n");
				}
				if (!mobile.equals("")&&!MyConstants.checkPhoneNum(mobile))
				{
					count++;
					sBuffer.append(count+"、联系人手机号码格式错误\n\n");
				}
				if (spBorn.getText().equals("非本地市"))
				{
					if (homerelasex.equals("请选择"))
					{
						count++;
						sBuffer.append(count+"、户籍地联系人性别未选择\n\n");
					}
					if (homerela.equals("请选择"))
					{
						count++;
						sBuffer.append(count+"、户籍地联系人与申请人关系未选择\n\n");
					}
					if (!MyConstants.baseCheck(homerelaname, 40))
					{
						count++;
						sBuffer.append(count+"、户籍地联系人中文姓名格式错误或未填写\n\n");
					}
					if ((homerelatel1.length()!=3&&homerelatel1.length()!=4)||!MyConstants.checkTelNum(homerelatel2))
					{
						count++;
						sBuffer.append(count+"、户籍地联系人电话号码格式错误或未填写\n\n");
					}
					if (!MyConstants.checkPhoneNum(homerelamobile))
					{
						count++;
						sBuffer.append(count+"、户籍地联系人手机号码格式错误或未填写\n\n");
					}
					if (!MyConstants.checkPostCode(homerelapostcode))
					{
						count++;
						sBuffer.append(count+"、户籍地邮编格式错误或未填写\n\n");
					}
					String sp = spProvince.getText().toString().trim();
					if (sp.equals("北京市")||sp.equals("天津市")||sp.equals("上海市")||sp.equals("重庆市"))
					{
						if (spCity.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count+"、本人户籍地联系地址格式错误或未填写\n\n");
						}
					}else {
						if (sp.equals("请选择")||spCity.getText().toString().trim().equals("请选择")||spCounty.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count+"、本人户籍地联系地址格式错误或未填写\n\n");
						}
					}
				}
				if (sex.equals(MyConstants.spf.getString(MyConstants.CUST_SEX,""))&&rela.equals("0"))
				{
					count++;
					sBuffer.append(count+"、申请人与联系人关系不能是配偶\n\n");
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
					if (spBorn.getText().equals("本地市"))
					{
						MyConstants.editor.putString(MyConstants.PRE_REG_ADDR, "4");//户籍地址同现住址
					}
					else {
						MyConstants.editor.putString(MyConstants.PRE_REG_ADDR, "4");//户籍地址不同现住址
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

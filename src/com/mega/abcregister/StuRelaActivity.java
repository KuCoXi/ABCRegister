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

public class StuRelaActivity extends Activity
{

	private Button nextButton;
	private Button lastButton;
	private CustomSinnper spRelaSex; // 家庭联系人性别
	private CustomSinnper spRelation;// 与联系人与申请人关系
	private CustomSinnper spHomeRelaSex;// 学校联系人性别
	private CustomSinnper spHomeRelation;// 学校联系人与申请人关系
	private CustomSinnper spHomeRelaAddr;
	private CustomSinnper spProvince;
	private CustomSinnper spCity;
	private CustomSinnper spCounty;
	private HintEdittext chsname;// 家庭联系人中文姓名
	private EditText tel1;// 家庭联系人电话区号
	private EditText tel2;// 家庭联系人电话号码
	private HintEdittext dept;//家庭联系人单位
	private EditText dept_tel1;//家庭联系人单位区号
	private EditText dept_tel2;//家庭联系人单位号码
	private HintEdittext phone;// 联系人手机
	private HintEdittext relachsname;// 学校联系人中文姓名
	private EditText relatel1;// 学校联系人电话区号
	private EditText relatel2;// 学校联系人电话号码
	private HintEdittext relaphone;// 学校联系人手机
	private EditText relaaddr;// 家庭联系人详细地址

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stu_card3);
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		dept = (HintEdittext) findViewById(R.id.et_depart_name);
		dept.init("家庭联系人单位", Color.GRAY, 25, 5, 11);
		dept_tel1 = (EditText) findViewById(R.id.et_dept_tel1);
		dept_tel2 = (EditText) findViewById(R.id.et_dept_tel2);
		chsname = (HintEdittext) findViewById(R.id.et_rela_name);
		chsname.init("*家庭联系人姓名", Color.GRAY, 25, 5, 11);
		tel1 = (EditText) findViewById(R.id.et_rela_tel1);
		tel2 = (EditText) findViewById(R.id.et_rela_tel2);
		phone = (HintEdittext) findViewById(R.id.et_rela_phonenum);
		phone.init("手机", Color.GRAY, 25, 5, 11);
		relachsname = (HintEdittext) findViewById(R.id.et_homerela_name);
		relachsname.init("学校联系人姓名", Color.GRAY, 25, 5, 11);
		relatel1 = (EditText) findViewById(R.id.et_homerela_tel1);
		relatel2 = (EditText) findViewById(R.id.et_homerela_tel2);
		relaphone = (HintEdittext) findViewById(R.id.et_homerela_phonenum);
		relaphone.init("手机", Color.GRAY, 25, 5, 11);
		relaaddr = (EditText) findViewById(R.id.et_homeadd_detail);
		// relapostcode = (EditText) findViewById(R.id.et_homerela_postcode);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		spRelaSex = (CustomSinnper) findViewById(R.id.sp_rela_sex);
		spRelation = (CustomSinnper) findViewById(R.id.sp_relation);
		spHomeRelaSex = (CustomSinnper) findViewById(R.id.sp_homerela_sex);
		spHomeRelation = (CustomSinnper) findViewById(R.id.sp_home_relation);
		spHomeRelaAddr = (CustomSinnper) findViewById(R.id.sp_as_homeaddr);
		spProvince = (CustomSinnper) findViewById(R.id.sp_home_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_home_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_home_county);
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
		ArrayAdapter<CharSequence> homeaddrAdapter = ArrayAdapter.createFromResource(this, R.array.homeaddr_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> relationAdapter1 = ArrayAdapter.createFromResource(this, R.array.stu_relation_list_entries1, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> relationAdapter2 = ArrayAdapter.createFromResource(this, R.array.stu_relation_list_entries2, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> bornAdapter = ArrayAdapter.createFromResource(this, R.array.born_list_entries, android.R.layout.simple_list_item_1);
		spHomeRelaAddr.setAdapter(homeaddrAdapter);
		spHomeRelaAddr.setText("同现住址");
		spRelaSex.setAdapter(sexAdapter);
		spRelation.setAdapter(relationAdapter1);
		spHomeRelaSex.setAdapter(sexAdapter);
		spHomeRelation.setAdapter(relationAdapter2);
		spProvince.setAdapter(provinceAdapter);
//		spProvince.setSelection(MyConstants.getPosition(provinceData, "请选择"));
		if (spHomeRelaAddr.getText().equals("同现住址"))
		{
			spProvince.setEnabled(false);
			spCity.setEnabled(false);
			spCounty.setEnabled(false);
			relaaddr.setEnabled(false);
		} else
		{
			spProvince.setEnabled(true);
			spCity.setEnabled(true);
			spCounty.setEnabled(true);
			relaaddr.setEnabled(true);
		}
	}

	private void addListener()
	{
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
		spHomeRelaAddr.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String value = object.toString();
				if (value.equals("同现住址"))
				{
					spProvince.setEnabled(false);
					spCity.setEnabled(false);
					spCounty.setEnabled(false);
					relaaddr.setEnabled(false);
				} else
				{
					spProvince.setEnabled(true);
					spCity.setEnabled(true);
					spCounty.setEnabled(true);
					relaaddr.setEnabled(true);
				}
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
				if (cityData.length == 0)
				{
					spCity.setVisibility(View.INVISIBLE);
					spCounty.setVisibility(View.INVISIBLE);
				} else
				{
					spCity.setVisibility(View.VISIBLE);
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(StuRelaActivity.this, android.R.layout.simple_list_item_1, cityData);
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
				if (countyData.length == 0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				} else
				{
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(StuRelaActivity.this, android.R.layout.simple_list_item_1, countyData);
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

		nextButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String homerelaaddr = spHomeRelaAddr.getText().toString().trim();
				if (homerelaaddr.equals("同现住址"))
				{
					homerelaaddr = "4";
				}
				else if (homerelaaddr.equals("另注如下")) {
					homerelaaddr = "5";
				}
				String deptname = dept.getText().toString().trim();
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
				if (rela.equals("父母"))
				{
					rela = "1";
				}
				else if (rela.equals("其他")) {
					rela = "3";
				}
				String telnum1 = tel1.getText().toString().trim();
				String telnum2 = tel2.getText().toString().trim();
				String mobile = phone.getText().toString().trim();
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = "";
				String addr = "";
				String homerelaname = relachsname.getText().toString().trim();
				String homerelasex = spHomeRelaSex.getText().toString().trim();
				if (homerelasex.equals("男"))
				{
					homerelasex = "M";
				}
				else if(homerelasex.equals("女"))
				{
					homerelasex = "F";
				}
				String homerela = spHomeRelation.getText().toString().trim();
				if (homerela.equals("老师"))
				{
					homerela = "1";
				}
				else if (homerela.equals("同学")) {
					homerela = "2";
				}
				String homerelatel1 = relatel1.getText().toString().trim();
				if (!homerelatel1.equals(""))
				{
					homerelatel1 = (homerelatel1.length() == 3) ? homerelatel1 : homerelatel1.substring(1);
				}
				String homerelatel2 = relatel2.getText().toString().trim();
				String depttel1 = dept_tel1.getText().toString().trim();
				String depttel2 = dept_tel2.getText().toString().trim();
				String homerelamobile = relaphone.getText().toString().trim();
				addrdetail = relaaddr.getText().toString().trim();
				addr = province + city + county + addrdetail;
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (sex.equals("请选择"))
				{
					count++;
					sBuffer.append(count + "、家庭联系人性别未选择\n\n");
				}
				if (rela.equals("请选择"))
				{
					count++;
					sBuffer.append(count + "、家庭联系人与申请人关系未选择\n\n");
				}
				if (homerelaaddr.equals("请选择"))
				{
					count++;
					sBuffer.append(count + "、家庭联系人联系地址未选择\n\n");
				}
				if (homerelasex.equals("请选择"))
				{
					count++;
					sBuffer.append(count + "、学校联系人性别未选择\n\n");
				}
				if (homerela.equals("请选择"))
				{
					count++;
					sBuffer.append(count + "、学校联系人与申请人关系未选择\n\n");
				}
				if (!MyConstants.baseCheck(cname, 40))
				{
					count++;
					sBuffer.append(count + "、家庭联系人中文姓名格式错误或未填写\n\n");
				}else if (cname.equals(MyConstants.spf.getString(MyConstants.CUST_NAME,""))) {
					count++;
					sBuffer.append(count+"、家庭联系人姓名与申请人不能一致\n\n");
				}
				
				if ((telnum1.length() != 3 && telnum1.length() != 4) || !MyConstants.checkTelNum(telnum2))
				{
					count++;
					sBuffer.append(count + "、家庭联系人电话号码格式错误或未填写\n\n");
				}
				
				if (!mobile.equals("")&&!MyConstants.checkPhoneNum(mobile))
				{
					count++;
					sBuffer.append(count + "、家庭联系人手机号码格式错误\n\n");
				}else if (!mobile.equals("")&&mobile.equals(MyConstants.spf.getString(MyConstants.PRE_MOBILE,""))) {
					count++;
					sBuffer.append(count + "、家庭联系人手机与申请人不能一致\n\n");
				}
				
				if (spHomeRelaAddr.getText().toString().trim().equals("另注如下"))
				{
					String sp = spProvince.getText().toString().trim();
					if (sp.equals("北京市")||sp.equals("天津市")||sp.equals("上海市")||sp.equals("重庆市"))
					{
						if (spCity.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count + "、家庭联系人地址格式错误或未填写\n\n");
						}
					}else {
						if (spProvince.getText().toString().trim().equals("请选择")||spCity.getText().toString().trim().equals("请选择")||spCounty.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
						{
							count++;
							sBuffer.append(count + "、家庭联系人地址格式错误或未填写\n\n");
						}
					}
				}
				
				if (!deptname.equals("")&&!MyConstants.baseCheck(deptname, 80))
				{
					count++;
					sBuffer.append(count + "、家庭联系人单位格式错误\n\n");
				}
				
				if ((!depttel1.equals("")&&(depttel1.length() != 3 && depttel1.length() != 4)) || (!depttel2.equals("")&&!MyConstants.checkTelNum(depttel2)))
				{
					count++;
					System.out.println("depttel1:"+depttel1);
					sBuffer.append(count + "、家庭联系人单位电话号码格式错误\n\n");
				}
				
				if (!homerelaname.equals("")&&!MyConstants.baseCheck(homerelaname, 40))
				{
					count++;
					sBuffer.append(count + "、学校联系人中文姓名格式错误\n\n");
				}else if (!homerelaname.equals("")&&homerelaname.equals(MyConstants.spf.getString(MyConstants.CUST_NAME,""))) {
					count++;
					sBuffer.append(count + "、学校联系人姓名与申请人不能一致\n\n");
				}else if (!homerelaname.equals("")&&homerelaname.equals(cname)) {
					count++;
					sBuffer.append(count + "、学校联系人姓名与家庭联系人不能一致\n\n");
				}
				
				if ((homerelatel1.length() != 3 && homerelatel1.length() != 4) || !MyConstants.checkTelNum(homerelatel2))
				{
					count++;
					sBuffer.append(count + "、学校联系人电话号码格式错误或未填写\n\n");
				}else if(homerelatel2.equals(telnum2)){
					count++;
					sBuffer.append(count + "、学校联系人联系电话与家庭联系人联系电话不能一致\n\n");
				}
				
				if (!homerelamobile.equals("")&&!MyConstants.checkPhoneNum(homerelamobile))
				{
					count++;
					sBuffer.append(count + "、学校联系人手机号码格式错误\n\n");
				}else if (!homerelamobile.equals("")&&homerelamobile.equals(mobile)) {
					count++;
					sBuffer.append(count + "、学校联系人手机与家庭联系人手机不能一致\n\n");
				}else if (!homerelamobile.equals("")&&homerelamobile.equals(MyConstants.spf.getString(MyConstants.PRE_MOBILE,""))) {
					count++;
					sBuffer.append(count + "、学校联系人手机与申请人不能一致\n\n");
				}
//				if (addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
//				{
//					count++;
//					sBuffer.append(count + "、家庭联系人详细地址\n");
//				}
				if (count > 0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				} else
				{
					MyConstants.editor.putString(MyConstants.LM_NAME, cname);
					MyConstants.editor.putString(MyConstants.LM_SEX, sex);
					MyConstants.editor.putString(MyConstants.LM_COMP, deptname);
					if (!depttel1.equals(""))
					{
						depttel1 = (depttel1.length() == 3) ? depttel1 : depttel1.substring(1);
					}
					MyConstants.editor.putString(MyConstants.LM_COMP_ZONENO, depttel1);
					MyConstants.editor.putString(MyConstants.LM_COMP_PHONE, depttel2);
					MyConstants.editor.putString(MyConstants.LM_PRE_ADDR, spHomeRelaAddr.getText().equals("另注如下")?addr:"");
					MyConstants.editor.putString(MyConstants.RELATION_BET, rela);
					MyConstants.editor.putString(MyConstants.LM_PRE_ZONENO, telnum1.length() == 3 ? telnum1 : telnum1.substring(1));
					MyConstants.editor.putString(MyConstants.LM_PRE_PHONE, telnum2);
					MyConstants.editor.putString(MyConstants.HUKOU_RELNAME, "");
					MyConstants.editor.putString(MyConstants.HUKOU_RELSEX, homerelaname.equals("")?"":homerelasex);
					MyConstants.editor.putString(MyConstants.HUKOU_RELATION, "");
					MyConstants.editor.putString(MyConstants.COMP_ZONE_NO, homerelatel1.length() == 3 ? homerelatel1 : homerelatel1.substring(1));
					MyConstants.editor.putString(MyConstants.COMP_PHONE, homerelatel2);
					MyConstants.editor.putString(MyConstants.HUKOU_RELZONENO, "");
					MyConstants.editor.putString(MyConstants.HUKOU_RELPHONE, "");
					MyConstants.editor.putString(MyConstants.HUKOU_RELMOBILE, "");
					MyConstants.editor.putString(MyConstants.CAR_NO, homerelamobile);// 学校联系人手机
					MyConstants.editor.putString(MyConstants.CAR_TYPE, homerelaaddr);// 家庭联系人联系地址是否同现住址
					// MyConstants.editor.putString(MyConstants.REG_POST,
					// homerelapostcode);
					MyConstants.editor.putString(MyConstants.PRE_REG_ADDR, homerela);// 学校联系人与申请人关系
					MyConstants.editor.putString(MyConstants.REG_ADDR, homerelaname);// 学校联系人姓名
					MyConstants.editor.putString(MyConstants.LM_MOBILE, mobile);
//					if (MyConstants.spf.getBoolean(MyConstants.OTHER_CARD, false))
//					{
						CustMsgActivity.mTabHost.setCurrentTab(4);
//					} else
//					{
//						CardActivity.mTabHost.setCurrentTab(5);
//					}
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
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}

package com.mega.abcregister;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
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
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class StuSchoolActivity extends Activity
{
	private Calendar cd;
	private Button nextButton;
	private Button lastButton;
	private EditText etAddrDetail;//详细地址
	private HintEdittext etPostcode;//邮编
	private HintEdittext etStuYear;//工作年限
	private HintEdittext etSchool;//学校全称
	private CustomSinnper spProvince;//省
	private CustomSinnper spCity;//市
	private CustomSinnper spCounty;//县
	private HintEdittext etGraDate;//预计毕业时间
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stu_card2);
		initComponent();
		addListener();
	}
	
	private void initComponent()
	{
		cd = Calendar.getInstance();
		cd.setTime(new Date());
		etGraDate = (HintEdittext) findViewById(R.id.et_gradate);
		etGraDate.init("*预计毕业时间", Color.GRAY, 25, 5, 11);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		etAddrDetail = (EditText) findViewById(R.id.et_deptadd_detail);
		etPostcode = (HintEdittext) findViewById(R.id.et_dept_postcode);
		etPostcode.init("*邮政编码", Color.GRAY, 25, 5, 11);
		etStuYear = (HintEdittext) findViewById(R.id.et_workyear);
		etStuYear.init("*在读年级", Color.GRAY, 25, 5, 11);
		etSchool = (HintEdittext) findViewById(R.id.et_depart_name);
		etSchool.init("*学校全称", Color.GRAY, 25, 5, 11);
		spProvince = (CustomSinnper) findViewById(R.id.sp_dept_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_dept_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_dept_county);
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
//		ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobmsg_list_entries, android.R.layout.simple_list_item_1);
//		ArrayAdapter<CharSequence> jobtypeAdapter = ArrayAdapter.createFromResource(this, R.array.jobtype_list_entries, android.R.layout.simple_list_item_1);
//		ArrayAdapter<CharSequence> titlesAdapter = ArrayAdapter.createFromResource(this, R.array.jobtitle_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
//		ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(this, R.array.jobposition_list_entries, android.R.layout.simple_list_item_1);
//		jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		jobtypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		titlesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spProvince.setAdapter(provinceAdapter);
//		spProvince.setSelection(MyConstants.getPosition(provinceData, "请选择"));
	}
	
	private void addListener()
	{
		etGraDate.setOnTouchListener(new OnTouchListener()
		{
			
			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO Auto-generated method stub
				if (event.getAction()==MotionEvent.ACTION_DOWN) 
				{
					new DatePickerDialog(StuSchoolActivity.this, new OnDateSetListener()
					{
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
						{
							int month = monthOfYear+1;
							etGraDate.setText(year + "" + (month>9?month:("0"+month)));
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
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(StuSchoolActivity.this, android.R.layout.simple_list_item_1, cityData);
					cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
				String cityID = DataDao.getSingleString("city", "cityID", "cityNAME", cityValue).trim();
				String[] countyData = DataDao.getMultiString("county", "countyNAME", "cityID", cityID);
				if (countyData.length==0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(StuSchoolActivity.this, android.R.layout.simple_list_item_1, countyData);
					countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		nextButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String school = etSchool.getText().toString().trim();//学校全称
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					System.out.println("县级选项已激活");
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = etAddrDetail.getText().toString().trim();
				String addr = province+city+county+addrdetail;//地址
				String postcode = etPostcode.getText().toString().trim();//邮编
				String citycode = DataDao.getSingleString("city", "cityCODE", "cityNAME", spCity.getText().toString().trim());
				String stuyear = etStuYear.getText().toString().trim();//在读年级
				String gradate = etGraDate.getText().toString().trim();
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (!MyConstants.baseCheck(school, 80))
				{
					count++;
					sBuffer.append(count+"、学校全称格式错误或未填写\n\n");
				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("北京市")||sp.equals("天津市")||sp.equals("上海市")||sp.equals("重庆市"))
				{
					if (spCity.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"、学校地址格式错误或未填写\n\n");
					}
				}else {
					if (spProvince.getText().toString().trim().equals("请选择")||spCity.getText().toString().trim().equals("请选择")||spCounty.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"、学校地址格式错误或未填写\n\n");
					}
				}
				if (!MyConstants.checkPostCode(postcode))
				{
					count++;
					sBuffer.append(count+"、邮政编码格式错误或未填写\n\n");
				}
				if (!MyConstants.numberCheck(stuyear, 1))
				{
					count++;
					sBuffer.append(count+"、在读年级格式错误或未填写\n\n");
				}
				if (!MyConstants.numberCheck(gradate, 6))
				{
					count++;
					sBuffer.append(count+"、预计毕业时间未填写\n\n");
				}
				
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
					MyConstants.editor.putString(MyConstants.CAREER, "7");
					MyConstants.editor.putString(MyConstants.TRADE_KIND, "7");
					MyConstants.editor.putString(MyConstants.TECH_GRADE, "3");
					MyConstants.editor.putString(MyConstants.COMP_NAME, school);
					MyConstants.editor.putString(MyConstants.TECH_POSI, "D");
					MyConstants.editor.putString(MyConstants.COMP_ADDR, addr);
					MyConstants.editor.putString(MyConstants.COMP_CITY_CODE, citycode);
					MyConstants.editor.putString(MyConstants.PRE_CITY_CODE, citycode);
					MyConstants.editor.putString(MyConstants.COMP_POST, postcode);
//					MyConstants.editor.putString(MyConstants.COMP_ZONE_NO, tel1.length()==3?tel1:tel1.substring(1));
//					MyConstants.editor.putString(MyConstants.COMP_PHONE, tel2);
					MyConstants.editor.putString(MyConstants.WORK_YEAR, stuyear);
					MyConstants.editor.putString(MyConstants.REG_POST, gradate);
					MyConstants.editor.commit();
					CustMsgActivity.mTabHost.setCurrentTab(3);
				}
				
//				System.out.println("职称："+title+"\n职业："+job+"\n职务："+position+"\n行业种类："+jobtype+"\n单位："+company+"\n城市代码："+citycode+"\n地址："+addr);
				
			}
		});
		
		lastButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				CustMsgActivity.mTabHost.setCurrentTab(1);
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

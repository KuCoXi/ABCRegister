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

import com.mega.dao.DataDao;
import com.mega.myview.CustomSinnper;
import com.mega.myview.HintEdittext;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class CustJobActivity extends Activity
{
	private Button nextButton;
	private Button lastButton;
	private CustomSinnper spJob;//职业
	private EditText etOtherJob;//其他职业(手动输入)
	private EditText etAddrDetail;//详细地址
	private HintEdittext etPostcode;//邮编
	private EditText etDepTel1;//单位电话区号
	private EditText etDepTel2;//单位电话号码
	private HintEdittext etWorkYear;//工作年限
	private HintEdittext etCompany;//单位全称及部门
	private CustomSinnper spJobType;//行业种类
	private CustomSinnper spTitles;//职称
	private CustomSinnper spPosition;//职务
	private CustomSinnper spProvince;//省
	private CustomSinnper spCity;//市
	private CustomSinnper spCounty;//县
	private String card_name = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//产品名称
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_job);
		initComponent();
		addListener();
	}
	
	private void initComponent()
	{
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		spJob = (CustomSinnper) findViewById(R.id.sp_job);
		etOtherJob = (EditText) findViewById(R.id.et_other_job);
		etOtherJob.setVisibility(View.INVISIBLE);
		etAddrDetail = (EditText) findViewById(R.id.et_deptadd_detail);
		etPostcode = (HintEdittext) findViewById(R.id.et_dept_postcode);
		etPostcode.init("*邮政编码", Color.GRAY, 25, 5, 11);
		etDepTel1 = (EditText) findViewById(R.id.et_dept_tel1);
		etDepTel2 = (EditText) findViewById(R.id.et_dept_tel2);
		etWorkYear = (HintEdittext) findViewById(R.id.et_workyear);
		etWorkYear.init("*现单位工作年限", Color.GRAY, 25, 5, 11);
		etCompany = (HintEdittext) findViewById(R.id.et_depart_name);
		etCompany.init("*单位全称及部门", Color.GRAY, 25, 5, 11);
		spJobType = (CustomSinnper) findViewById(R.id.sp_job_type);
		spTitles = (CustomSinnper) findViewById(R.id.sp_titles);
		spPosition = (CustomSinnper) findViewById(R.id.sp_position);
		spProvince = (CustomSinnper) findViewById(R.id.sp_dept_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_dept_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_dept_county);
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
		ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobmsg_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> jobtypeAdapter = ArrayAdapter.createFromResource(this, R.array.jobtype_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> titlesAdapter = ArrayAdapter.createFromResource(this, R.array.jobtitle_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter.createFromResource(this, R.array.jobposition_list_entries, android.R.layout.simple_list_item_1);
		if(CustMsgActivity.card.getCardGrade().equals("4"))
		{
			positionAdapter = ArrayAdapter.createFromResource(this, R.array.jobposition2_list_entries, android.R.layout.simple_list_item_1);
		}
		if (card_name.contains("军队单位公务"))
		{
			etWorkYear.setText("1");
			etWorkYear.setEnabled(false);
			positionAdapter = ArrayAdapter.createFromResource(this, R.array.jobposition1_list_entries, android.R.layout.simple_list_item_1);
		}
		if (card_name.contains("中央预算单位公务"))
		{
			jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobmsg1_list_entries, android.R.layout.simple_list_item_1);
		}
		if (card_name.contains("军队单位公务")||card_name.contains("武警部队公务"))
		{
			jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobmsg2_list_entries, android.R.layout.simple_list_item_1);
		}
		
		spJob.setAdapter(jobAdapter);
		spJobType.setAdapter(jobtypeAdapter);
		spTitles.setAdapter(titlesAdapter);
		spProvince.setAdapter(provinceAdapter);
		spPosition.setAdapter(positionAdapter);
	}
	
	private void addListener()
	{
		spTitles.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				
			}
		});
		spPosition.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				
			}
		});
		spJob.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String jobValue = object.toString();
				if (jobValue.equals("其他"))
				{
					etOtherJob.setVisibility(View.VISIBLE);
				}
				else {
					etOtherJob.setVisibility(View.INVISIBLE);
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
				if (cityData.length==0)
				{
					spCity.setVisibility(View.INVISIBLE);
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCity.setVisibility(View.VISIBLE);
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CustJobActivity.this, android.R.layout.simple_list_item_1, cityData);
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
				String cityID = DataDao.getSingleString("city", "cityID", "cityNAME", cityValue).trim();
				String[] countyData = DataDao.getMultiString("county", "countyNAME", "cityID", cityID);
				if (countyData.length==0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				}
				else {
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(CustJobActivity.this, android.R.layout.simple_list_item_1, countyData);
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
				
			}
		});
		spJobType.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				
			}
		});
		nextButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String title = spTitles.getText().toString().trim();//职称
				if (title.equals("高级"))
				{
					title = "0";
				}
				else if (title.equals("中级")) {
					title = "1";
				}
				else if (title.equals("初级")) {
					title = "2";
				}
				else if (title.equals("无")) {
					title = "3";
				}
				String jobtype = spJobType.getText().toString().trim();//行业类型
				if (jobtype.equals("公共管理与社会组织"))
				{
					jobtype = "0";
				}
				else if (jobtype.equals("科研文化卫生教育")) {
					jobtype = "1";
				}
				else if (jobtype.equals("金融电力电信")) {
					jobtype = "2";
				}
				else if (jobtype.equals("邮政交通运输公用")) {
					jobtype = "3";
				}
				else if (jobtype.equals("计算机服务与软件业")) {
					jobtype = "4";
				}
				else if (jobtype.equals("体育娱乐")) {
					jobtype = "5";
				}
				else if (jobtype.equals("工业商业服务业贸易")) {
					jobtype = "6";
				}
				else if (jobtype.equals("其他")) {
					jobtype = "7";
				}
				String company = etCompany.getText().toString().trim();//单位全称
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
				String tel1 = etDepTel1.getText().toString().trim();//区号
				String tel2 = etDepTel2.getText().toString().trim();//电话号码
				String citycode = DataDao.getSingleString("city", "cityCODE", "cityNAME", spCity.getText().toString().trim());
				String workyear = etWorkYear.getText().toString().trim();//工作年限
				String position = spPosition.getText().toString().trim();//职务
				if (position.equals("厅局级以上(含)"))
				{
					position = "0";
				}
				else if (position.equals("处级")||position.equals("干部")) {
					position = "1";
				}
				else if (position.equals("科级")||position.equals("士官")) {
					position = "2";
				}
				else if (position.equals("一般干部")||position.equals("职工")) {
					position = "3";
				}
				else if (position.equals("总经理以上(含)")) {
					position = "A";
				}
				else if (position.equals("部门经理")) {
					position = "B";
				}
				else if (position.equals("职员")) {
					position = "C";
				}
				else if (position.equals("其他")) {
					position = "D";
				}
				String job = spJob.getText().toString().trim();//职业
				if (job.equals("公务员"))
				{
					job = "1";
				}
				else if (job.equals("医生")) {
					job = "2";
				}
				else if (job.equals("教师")) {
					job = "3";
				}
				else if (job.equals("律师/会计师/审计师")) {
					job = "4";
				}
				else if (job.equals("其他事业单位员工")) {
					job = "A";
				}
				else if (job.equals("国有企业员工")) {
					job = "B";
				}
				else if (job.equals("三资企业员工")) {
					job = "C";
				}
				else if (job.equals("民营企业员工")) {
					job = "D";
				}
				else if (job.equals("军人")) {
					job = "6";
				}
				else if (job.equals("学生")) {
					job = "7";
				}
				else if (job.equals("个体/自由职业者")) {
					job = "8";
				}
				else if (job.equals("其他")) {
					job = "9";
				}
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (job.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、职业未选择\n\n");
				}
				if (jobtype.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、行业类别未选择\n\n");
				}
				if (position.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、职务未选择\n\n");
				}
				if (title.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、职称未选择\n\n");
				}
				if (!MyConstants.baseCheck(company, 80))
				{
					count++;
					sBuffer.append(count+"、单位全称及部门格式错误或未填写\n\n");
				}
				if (card_name.contains("员工卡"))
				{
					if (!company.contains("农业银行")||!company.contains("农行"))
					{
						count++;
						sBuffer.append(count+"、申请员工卡的单位全称必须包含“农业银行”或“农行”\n\n");
					}
				}
//				if (CustMsgActivity.card.getCardGrade().equals("4"))
//				{
//					if (position.equals("A")||position.equals("B")||position.equals("C"))
//					{
//						count++;
//						sBuffer.append(count+"、公务卡职务选择错误\n\n");
//					}
//				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("北京市")||sp.equals("天津市")||sp.equals("上海市")||sp.equals("重庆市"))
				{
					if (spCity.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"、现住址格式错误或未填写\n\n");
					}
				}else {
					if (sp.equals("请选择")||spCity.getText().toString().trim().equals("请选择")||spCounty.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"、现住址格式错误或未填写\n\n");
					}
				}
				if (CustMsgActivity.card.getCardGrade().equals("4")) {
					if (!addr.equals(MyConstants.spf.getString(MyConstants.PRE_ADDR,"")))
					{
						count++;
						sBuffer.append(count+"、单位地址与现住址必须相同\n\n");
					}
				}
				if (!MyConstants.checkPostCode(postcode))
				{
					count++;
					sBuffer.append(count+"、邮政编码格式错误或未填写\n\n");
				}
				else if (CustMsgActivity.card.getCardGrade().equals("4")) {
					if (!postcode.equals(MyConstants.spf.getString(MyConstants.PRE_POST,"")))
					{
						count++;
						sBuffer.append(count+"、单位地址邮编与现住址邮编必须相同\n\n");
					}
				}
				if ((tel1.length()!=3&&tel1.length()!=4)||!MyConstants.checkTelNum(tel2))
				{
					count++;
					sBuffer.append(count+"、电话号码格式错误或未填写\n\n");
				}
				else {
					if (tel2.equals(MyConstants.spf.getString(MyConstants.PRE_PHONE,"")))
					{
						count++;
						sBuffer.append(count+"、单位电话不能与现住址电话一致\n\n");
					}
				}
				if (!MyConstants.numberCheck(workyear, 2))
				{
					count++;
					sBuffer.append(count+"、工作年限格式错误或未填写\n\n");
				}
				
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
					MyConstants.editor.putString(MyConstants.CAREER, job);
					MyConstants.editor.putString(MyConstants.TRADE_KIND, jobtype);
					MyConstants.editor.putString(MyConstants.TECH_GRADE, title);
					MyConstants.editor.putString(MyConstants.COMP_NAME, company);
					MyConstants.editor.putString(MyConstants.TECH_POSI, position);
					MyConstants.editor.putString(MyConstants.COMP_ADDR, addr);
					MyConstants.editor.putString(MyConstants.COMP_CITY_CODE, citycode);
					MyConstants.editor.putString(MyConstants.PRE_CITY_CODE, citycode);
					MyConstants.editor.putString(MyConstants.COMP_POST, postcode);
					MyConstants.editor.putString(MyConstants.COMP_ZONE_NO, tel1.length()==3?tel1:tel1.substring(1));
					MyConstants.editor.putString(MyConstants.COMP_PHONE, tel2);
					MyConstants.editor.putString(MyConstants.WORK_YEAR, workyear);
					MyConstants.editor.commit();
					CustMsgActivity.mTabHost.setCurrentTab(3);
				}
				
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
//			CustMsgActivity.mTabHost.setCurrentTab(1);
			return true;
	    }
		return super.dispatchKeyEvent(event);
	}
	
}

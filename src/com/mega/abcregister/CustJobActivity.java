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
	private CustomSinnper spJob;//ְҵ
	private EditText etOtherJob;//����ְҵ(�ֶ�����)
	private EditText etAddrDetail;//��ϸ��ַ
	private HintEdittext etPostcode;//�ʱ�
	private EditText etDepTel1;//��λ�绰����
	private EditText etDepTel2;//��λ�绰����
	private HintEdittext etWorkYear;//��������
	private HintEdittext etCompany;//��λȫ�Ƽ�����
	private CustomSinnper spJobType;//��ҵ����
	private CustomSinnper spTitles;//ְ��
	private CustomSinnper spPosition;//ְ��
	private CustomSinnper spProvince;//ʡ
	private CustomSinnper spCity;//��
	private CustomSinnper spCounty;//��
	private String card_name = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//��Ʒ����
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
		etPostcode.init("*��������", Color.GRAY, 25, 5, 11);
		etDepTel1 = (EditText) findViewById(R.id.et_dept_tel1);
		etDepTel2 = (EditText) findViewById(R.id.et_dept_tel2);
		etWorkYear = (HintEdittext) findViewById(R.id.et_workyear);
		etWorkYear.init("*�ֵ�λ��������", Color.GRAY, 25, 5, 11);
		etCompany = (HintEdittext) findViewById(R.id.et_depart_name);
		etCompany.init("*��λȫ�Ƽ�����", Color.GRAY, 25, 5, 11);
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
		if (card_name.contains("���ӵ�λ����"))
		{
			etWorkYear.setText("1");
			etWorkYear.setEnabled(false);
			positionAdapter = ArrayAdapter.createFromResource(this, R.array.jobposition1_list_entries, android.R.layout.simple_list_item_1);
		}
		if (card_name.contains("����Ԥ�㵥λ����"))
		{
			jobAdapter = ArrayAdapter.createFromResource(this, R.array.jobmsg1_list_entries, android.R.layout.simple_list_item_1);
		}
		if (card_name.contains("���ӵ�λ����")||card_name.contains("�侯���ӹ���"))
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
				if (jobValue.equals("����"))
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
				String title = spTitles.getText().toString().trim();//ְ��
				if (title.equals("�߼�"))
				{
					title = "0";
				}
				else if (title.equals("�м�")) {
					title = "1";
				}
				else if (title.equals("����")) {
					title = "2";
				}
				else if (title.equals("��")) {
					title = "3";
				}
				String jobtype = spJobType.getText().toString().trim();//��ҵ����
				if (jobtype.equals("���������������֯"))
				{
					jobtype = "0";
				}
				else if (jobtype.equals("�����Ļ���������")) {
					jobtype = "1";
				}
				else if (jobtype.equals("���ڵ�������")) {
					jobtype = "2";
				}
				else if (jobtype.equals("������ͨ���乫��")) {
					jobtype = "3";
				}
				else if (jobtype.equals("��������������ҵ")) {
					jobtype = "4";
				}
				else if (jobtype.equals("��������")) {
					jobtype = "5";
				}
				else if (jobtype.equals("��ҵ��ҵ����ҵó��")) {
					jobtype = "6";
				}
				else if (jobtype.equals("����")) {
					jobtype = "7";
				}
				String company = etCompany.getText().toString().trim();//��λȫ��
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					System.out.println("�ؼ�ѡ���Ѽ���");
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = etAddrDetail.getText().toString().trim();
				String addr = province+city+county+addrdetail;//��ַ
				String postcode = etPostcode.getText().toString().trim();//�ʱ�
				String tel1 = etDepTel1.getText().toString().trim();//����
				String tel2 = etDepTel2.getText().toString().trim();//�绰����
				String citycode = DataDao.getSingleString("city", "cityCODE", "cityNAME", spCity.getText().toString().trim());
				String workyear = etWorkYear.getText().toString().trim();//��������
				String position = spPosition.getText().toString().trim();//ְ��
				if (position.equals("���ּ�����(��)"))
				{
					position = "0";
				}
				else if (position.equals("����")||position.equals("�ɲ�")) {
					position = "1";
				}
				else if (position.equals("�Ƽ�")||position.equals("ʿ��")) {
					position = "2";
				}
				else if (position.equals("һ��ɲ�")||position.equals("ְ��")) {
					position = "3";
				}
				else if (position.equals("�ܾ�������(��)")) {
					position = "A";
				}
				else if (position.equals("���ž���")) {
					position = "B";
				}
				else if (position.equals("ְԱ")) {
					position = "C";
				}
				else if (position.equals("����")) {
					position = "D";
				}
				String job = spJob.getText().toString().trim();//ְҵ
				if (job.equals("����Ա"))
				{
					job = "1";
				}
				else if (job.equals("ҽ��")) {
					job = "2";
				}
				else if (job.equals("��ʦ")) {
					job = "3";
				}
				else if (job.equals("��ʦ/���ʦ/���ʦ")) {
					job = "4";
				}
				else if (job.equals("������ҵ��λԱ��")) {
					job = "A";
				}
				else if (job.equals("������ҵԱ��")) {
					job = "B";
				}
				else if (job.equals("������ҵԱ��")) {
					job = "C";
				}
				else if (job.equals("��Ӫ��ҵԱ��")) {
					job = "D";
				}
				else if (job.equals("����")) {
					job = "6";
				}
				else if (job.equals("ѧ��")) {
					job = "7";
				}
				else if (job.equals("����/����ְҵ��")) {
					job = "8";
				}
				else if (job.equals("����")) {
					job = "9";
				}
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (job.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"��ְҵδѡ��\n\n");
				}
				if (jobtype.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"����ҵ���δѡ��\n\n");
				}
				if (position.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"��ְ��δѡ��\n\n");
				}
				if (title.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"��ְ��δѡ��\n\n");
				}
				if (!MyConstants.baseCheck(company, 80))
				{
					count++;
					sBuffer.append(count+"����λȫ�Ƽ����Ÿ�ʽ�����δ��д\n\n");
				}
				if (card_name.contains("Ա����"))
				{
					if (!company.contains("ũҵ����")||!company.contains("ũ��"))
					{
						count++;
						sBuffer.append(count+"������Ա�����ĵ�λȫ�Ʊ��������ũҵ���С���ũ�С�\n\n");
					}
				}
//				if (CustMsgActivity.card.getCardGrade().equals("4"))
//				{
//					if (position.equals("A")||position.equals("B")||position.equals("C"))
//					{
//						count++;
//						sBuffer.append(count+"������ְ��ѡ�����\n\n");
//					}
//				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("������")||sp.equals("�����")||sp.equals("�Ϻ���")||sp.equals("������"))
				{
					if (spCity.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"����סַ��ʽ�����δ��д\n\n");
					}
				}else {
					if (sp.equals("��ѡ��")||spCity.getText().toString().trim().equals("��ѡ��")||spCounty.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append(count+"����סַ��ʽ�����δ��д\n\n");
					}
				}
				if (CustMsgActivity.card.getCardGrade().equals("4")) {
					if (!addr.equals(MyConstants.spf.getString(MyConstants.PRE_ADDR,"")))
					{
						count++;
						sBuffer.append(count+"����λ��ַ����סַ������ͬ\n\n");
					}
				}
				if (!MyConstants.checkPostCode(postcode))
				{
					count++;
					sBuffer.append(count+"�����������ʽ�����δ��д\n\n");
				}
				else if (CustMsgActivity.card.getCardGrade().equals("4")) {
					if (!postcode.equals(MyConstants.spf.getString(MyConstants.PRE_POST,"")))
					{
						count++;
						sBuffer.append(count+"����λ��ַ�ʱ�����סַ�ʱ������ͬ\n\n");
					}
				}
				if ((tel1.length()!=3&&tel1.length()!=4)||!MyConstants.checkTelNum(tel2))
				{
					count++;
					sBuffer.append(count+"���绰�����ʽ�����δ��д\n\n");
				}
				else {
					if (tel2.equals(MyConstants.spf.getString(MyConstants.PRE_PHONE,"")))
					{
						count++;
						sBuffer.append(count+"����λ�绰��������סַ�绰һ��\n\n");
					}
				}
				if (!MyConstants.numberCheck(workyear, 2))
				{
					count++;
					sBuffer.append(count+"���������޸�ʽ�����δ��д\n\n");
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

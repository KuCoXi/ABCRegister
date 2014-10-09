package com.mega.abcregister;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android_serialport_api.ParseSFZAPI;
import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortManager;
import android_serialport_api.ParseSFZAPI.People;

import com.android.rfid.CardInfoParser;
import com.android.rfid.CardReader;
import com.android.rfid.RfidDevice;
import com.authentication.utils.DataUtils;
import com.mega.dao.DataDao;
import com.mega.model.AsyncParseSFZ;
import com.mega.model.ReadCardThread;
import com.mega.model.AsyncParseSFZ.OnReadSFZListener;
import com.mega.myview.CustomSinnper;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.myview.HintEdittext;
import com.mega.pack.Person;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.SpellTools;
import com.mega.tools.UITools;
import com.xhw.rfid.activity.RunawayBean;

public class CustBasicActivity extends Activity
{
	private Calendar cd;
	private Button nextButton;//下一步按钮
	private Button lastButton;//返回按钮
	private Button readCardButton;//重启读卡器按钮
	private HintEdittext etBirth;//出生日期
	private EditText etOtherType;//其他证件类型
	private HintEdittext etMonthBack;//月还款
	private HintEdittext etChsName;//中文姓名
	private HintEdittext etPyLName;//拼音姓
	private HintEdittext etPyFName;//拼音名
	private HintEdittext etIDNum;//身份证/护照号码
	private EditText etAddress;//现住址
	private HintEdittext etLiveYear;//现住址居住年限
	private HintEdittext etIncome;//年收入
	private HintEdittext etPostcode;//邮编
	private EditText etHouseTel1;//住宅电话(区号)
	private EditText etHouseTel2;//住宅电话(号码)
	private HintEdittext etPhoneNum;//手机号码
	private TextView tv2;
	private CustomSinnper spSex;//性别
	private CustomSinnper spNation;//国籍
	private CustomSinnper spEducation;//教育程度
	private CustomSinnper spCardType;//身份证件类型
	private CustomSinnper spMarriage;//婚姻状况
	private CustomSinnper spHouse;//住宅状况
	private CustomSinnper spProvince;//省
	private CustomSinnper spCity;//市
	private CustomSinnper spCounty;//县
	private static int count = 0;
	private String card_name = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//产品名称
	private ArrayAdapter<CharSequence> cardAdapter;
	private ProgressDialog progressDialog;
	private SerialPort mserialPort;
	private InputStream mmInStream;
	private OutputStream mmOutStream;
	private Thread thread;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_basic);
		initComponent();
		addListener();
		System.out.println("oncreate");
		count = 0;
	}

	private void initComponent()
	{
		MyConstants.setPowerOnSFZ();
		cd = Calendar.getInstance();
		cd.setTime(new Date());
		spProvince = (CustomSinnper) findViewById(R.id.sp_province);
		spCity = (CustomSinnper) findViewById(R.id.sp_city);
		spCounty = (CustomSinnper) findViewById(R.id.sp_county);
		spSex = (CustomSinnper) findViewById(R.id.sp_sex);
		spNation = (CustomSinnper) findViewById(R.id.sp_nation);
		spEducation = (CustomSinnper) findViewById(R.id.sp_education);
		spCardType = (CustomSinnper) findViewById(R.id.sp_cardtype);
		spMarriage = (CustomSinnper) findViewById(R.id.sp_marriage);
		spHouse = (CustomSinnper) findViewById(R.id.sp_house);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		readCardButton = (Button) findViewById(R.id.bt_readcard);
//		if (type)
//		{
			readCardButton.setText("读二代证");
//		}
		etBirth = (HintEdittext) findViewById(R.id.et_birth);
		etBirth.init("*出生日期",Color.GRAY, 25, 5, 11);
		etOtherType = (EditText) findViewById(R.id.et_othertype);
		etOtherType.setVisibility(View.INVISIBLE);
		etMonthBack = (HintEdittext) findViewById(R.id.et_month_back);
		etMonthBack.init("月还款",Color.GRAY, 25, 5, 11);
		etMonthBack.setVisibility(View.INVISIBLE);
		etChsName = (HintEdittext) findViewById(R.id.et_chsname);
		etChsName.init("*中文姓名", Color.GRAY, 25, 5, 11);
		etPyLName = (HintEdittext) findViewById(R.id.et_py_lname);
		etPyLName.init("*拼音姓", Color.GRAY, 25, 5, 11);
		etPyFName = (HintEdittext) findViewById(R.id.et_py_fname);
		etPyFName.init("*名", Color.GRAY, 25, 5, 11);
		etIDNum = (HintEdittext) findViewById(R.id.et_idnum);
		etIDNum.init("*身份证件号码", Color.GRAY, 25, 5, 11);
		etAddress = (EditText) findViewById(R.id.et_add_detail);
		etBirth.setEnabled(false);
		etChsName.setEnabled(false);
		etIDNum.setEnabled(false);
		etLiveYear = (HintEdittext) findViewById(R.id.et_house_time);
		etLiveYear.init("*现住址居住年限", Color.GRAY, 25, 5, 11);
		etIncome = (HintEdittext) findViewById(R.id.et_income);
		etIncome.init("*个人年收入", Color.GRAY, 25, 5, 11);
		etPostcode = (HintEdittext) findViewById(R.id.et_postcode);
		etPostcode.init("*邮政编码", Color.GRAY, 25, 5, 11);
		etHouseTel1 = (EditText) findViewById(R.id.et_house_tel1);
		etHouseTel2 = (EditText) findViewById(R.id.et_house_tel2);
		etPhoneNum = (HintEdittext) findViewById(R.id.et_phonenum);
		etPhoneNum.init("手机", Color.GRAY, 25, 5, 11);
		tv2 = (TextView) findViewById(R.id.tv_month_back2);
		tv2.setVisibility(View.INVISIBLE);
		String[] nationData = DataDao.getSingleData("sp_countries", "title","title");
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME","provinceID");
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_list_entries, android.R.layout.simple_list_item_1);
		cardAdapter = ArrayAdapter.createFromResource(this, R.array.chs_cardtype_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> marriageAdapter = ArrayAdapter.createFromResource(this, R.array.marry_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> educationAdapter = ArrayAdapter.createFromResource(this, R.array.education_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> houseAdapter = ArrayAdapter.createFromResource(this, R.array.house_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> nationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nationData);
		spProvince.setAdapter(provinceAdapter);
		spSex.setAdapter(sexAdapter);
		spCardType.setAdapter(cardAdapter);
		spCardType.setText("身份证");
		spMarriage.setAdapter(marriageAdapter);
		spEducation.setAdapter(educationAdapter);
		spHouse.setAdapter(houseAdapter);
		spNation.setAdapter(nationAdapter);
		spNation.setText("中国");
		if (card_name.contains("军队单位公务卡"))
		{
			etIncome.setText("2");
			etIncome.setEnabled(false);
		}
		if (CustMsgActivity.card.getCardGrade().equals("4"))
		{
			spNation.setEnabled(false);
		}
	}
	
	private Handler hd = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what)
			{
			case 1:
				UITools.getTools().showToast("串口打开失败！", true, UITools.BAD);
				progressDialog.dismiss();
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				break;
			case 13:
				progressDialog.dismiss();
				UITools.getTools().showToast("读二代证成功！", true, UITools.NORMAL);
				Bundle bundle = msg.getData();
				Person person = (Person) bundle.getSerializable("person");
				try
				{
					etChsName.setText(person.getPeopleName().trim());
					spSex.setText(person.getPeopleSex());
					String year = person.getPeopleBirthday().substring(0, 4);
					int month = Integer.parseInt(person.getPeopleBirthday().substring(4, 6));
					int day = Integer.parseInt(person.getPeopleBirthday().substring(6, 8));
					etBirth.setText((year + (month>9?month:"0"+month) + (day>9?day:"0"+day)).trim());
//					etAddress.setText(bean.getAddress().trim());
					if (spCardType.getText().toString().trim().equals("身份证"))
					{
						etIDNum.setText(person.getPeopleIDCode().trim());
					}
					try
					{
						etPyLName.setText(SpellTools.getEname(etChsName.getText().toString().trim().substring(0, 1)).trim());
						etPyFName.setText(SpellTools.getEname(etChsName.getText().toString().trim().substring(1)).trim());
					} catch (BadHanyuPinyinOutputFormatCombination e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NumberFormatException e)
				{
					e.printStackTrace();
				}
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				break;
			case 11:
				progressDialog.dismiss();
				Bundle error_msg = msg.getData();
				UITools.getTools().showToast(error_msg.getString("error"), true, UITools.SAD);
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				break;
			default:
				break;
			}
		}
		
	};
	private void addListener()
	{
		readCardButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				progressDialog = UITools.getTools().getSpinnerProgress(CustBasicActivity.this, "读二代证", "正在读取二代证信息...");
				progressDialog.setCancelable(true);
				progressDialog.show();
				try
				{

					if (mserialPort == null)
					{
						mserialPort = new SerialPort(new File("/dev/ttySAC3"), 115200, 0);
					}
					if (mmInStream == null)
					{
						mmInStream = mserialPort.getInputStream();
					}
					if (mmOutStream == null)
					{
						mmOutStream = mserialPort.getOutputStream();
					}
					if (mmInStream != null && mmOutStream != null)
					{
						thread = new ReadCardThread(mmInStream, mmOutStream, hd);
						thread.start();
					} else
					{
						Message message = new Message();
						message.what = 1;
						hd.sendMessage(message);
					}

				} catch (SecurityException e)
				{
					// TODO Auto-generated catch block
					Message message = new Message();
					message.what = 1;
					hd.sendMessage(message);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					Message message = new Message();
					message.what = 1;
					hd.sendMessage(message);
				}
			}
		});
		
		etBirth.setOnTouchListener(new OnTouchListener()
		{
			
			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO Auto-generated method stub
				if (event.getAction()==MotionEvent.ACTION_DOWN) 
				{
					new DatePickerDialog(CustBasicActivity.this, new OnDateSetListener()
					{
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
						{
							int month = monthOfYear+1;
							etBirth.setText(year + "" + (month>9?month:("0"+month)) + "" + (dayOfMonth>9?dayOfMonth:("0"+dayOfMonth)));
						}
					}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
				}
				return false;
			}
		});
		
		spNation.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object obj = parent.getItemAtPosition(position);
				String countryValue = obj.toString();
				if (countryValue.equals("中国"))
				{
					etChsName.setEnabled(false);
					etChsName.setHint("请读二代证");
					etChsName.setText("");
					etBirth.setEnabled(false);
					etBirth.setHint("请读二代证");
					etBirth.setText("");
					cardAdapter = ArrayAdapter.createFromResource(CustBasicActivity.this, R.array.chs_cardtype_list_entries, android.R.layout.simple_list_item_1);
//					cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCardType.setAdapter(cardAdapter);
				}
				else {
					etChsName.setEnabled(true);
					etChsName.setHint("请输入");
					etChsName.setText("");
					etBirth.setEnabled(true);
					etBirth.setHint("请输入");
					etBirth.setText("");
					cardAdapter = ArrayAdapter.createFromResource(CustBasicActivity.this, R.array.other_cardtype_list_entries, android.R.layout.simple_list_item_1);
//					cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCardType.setAdapter(cardAdapter);
				}
			}

		});
		
		spCardType.setOnItemSeletedListener(new OnItemSeletedListener()
		{

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String typeValue = object.toString();
				if (typeValue.equals("其他"))
				{
					etOtherType.setVisibility(View.VISIBLE);
				}
				else {
					etOtherType.setVisibility(View.INVISIBLE);
				}
				
				if (!typeValue.equals("身份证"))
				{
					etIDNum.setEnabled(true);
					etIDNum.setHint("请输入");
					etIDNum.setText("");
				}
				else {
					etIDNum.setEnabled(false);
					etIDNum.setHint("请读二代证");
					etIDNum.setText("");
				}
			}
		});
		spHouse.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String typeValue = object.toString();
				if (typeValue.equals("自购有贷款房"))
				{
					tv2.setVisibility(View.VISIBLE);
					etMonthBack.setVisibility(View.VISIBLE);
				}
				else {
					tv2.setVisibility(View.INVISIBLE);
					etMonthBack.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		lastButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				UITools.getTools().getDialog(MyConstants.CANCEL, "确定退出，并清空已填写信息？").show();
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
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CustBasicActivity.this, android.R.layout.simple_list_item_1, cityData);
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
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(CustBasicActivity.this, android.R.layout.simple_list_item_1, countyData);
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
		spMarriage.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
			}
		});
		
		spSex.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
			}
		});
		
		spEducation.setOnItemSeletedListener(new OnItemSeletedListener()
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
				String province = spProvince.getText().toString().trim();
				String city = spCity.getText().toString().trim();
				String county = "";
				if (spCounty.isShown())
				{
					System.out.println("县级选项已激活");
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = etAddress.getText().toString().trim();
				String addr = province+city+county+addrdetail;
				String plname = etPyLName.getText().toString().trim();
				String pfname = etPyFName.getText().toString().trim();
				String cname = etChsName.getText().toString().trim();
				String sex = spSex.getText().toString().trim();
				if (sex.equals("男"))
				{
					sex = "M";
				}
				else if(sex.equals("女"))
				{
					sex = "F";
				}
				String country = DataDao.getSingleString("sp_countries", "code", "title", spNation.getText().toString());
				String birth = etBirth.getText().toString().trim();
				String eduString = spEducation.getText().toString().trim();
				if (eduString.equals("研究生及以上"))
				{
					eduString = "0";
				}
				else if (eduString.equals("大学本科")) {
					eduString = "2";
				}
				else if (eduString.equals("大学专科")) {
					eduString = "3";
				}
				else if (eduString.equals("高中/中专及以下")) {
					eduString = "4";
				}
				String cardtype = spCardType.getText().toString().trim();
				String cardname="";
				if (cardtype.equals("身份证"))
				{
					cardtype = "I";
				}
				if (cardtype.equals("护照"))
				{
					cardtype = "P";
				}
				if (cardtype.equals("其他"))
				{
					cardtype = "O";
					cardname = etOtherType.getText().toString().trim();
				}
				String idnum = etIDNum.getText().toString().trim().toUpperCase();
				String marriage = spMarriage.getText().toString().trim();
				if (marriage.equals("未婚"))
				{
					marriage = "0";
				}
				else if (marriage.equals("已婚有子女")) {
					marriage = "1";
				}
				else if (marriage.equals("已婚无子女")) {
					marriage = "2";
				}
				else if (marriage.equals("其他")) {
					marriage = "3";
				}
				String house = spHouse.getText().toString().trim();
				if (house.equals("自购无贷款房"))
				{
					house = "0";
				}
				else if (house.equals("自购有贷款房")) {
					house = "1";
				}
				else if (house.equals("与父母同住")) {
					house = "2";
				}
				else if (house.equals("租用")) {
					house = "3";
				}
				else if (house.equals("其他")) {
					house = "4";
				}
				String monthback="";
				if (house.equals("1"))
				{
					monthback = etMonthBack.getText().toString().trim();
				}		
				String liveyear = etLiveYear.getText().toString().trim(); 
				String income = etIncome.getText().toString().trim();
				String postcode = etPostcode.getText().toString().trim();
				String tel1 = etHouseTel1.getText().toString().trim();
				String tel2 = etHouseTel2.getText().toString().trim();
				String mobile = etPhoneNum.getText().toString().trim();
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (sex.equals("请选择"))
				{
					count++;
					sBuffer.append("\t"+count+"、性别未选择\n\n");
				}
				if (eduString.equals("请选择"))
				{
					count++;
					sBuffer.append("\t"+count+"、教育程度未选择\n\n");
				}
				if (!idnum.equals(""))
				{
					int sexflag = Integer.valueOf(idnum.substring(16, 17));
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
					if (!sex.equals("请选择") && cardtype.equals("I") && idnum.length()==18 && !sexmatch)
					{
						count++;
						sBuffer.append("\t"+count+"、性别与身份证号码不符\n\n");
					}
				}
				
//				if (cardtype.equals("请选择"))
//				{
//					count++;
//					sBuffer.append("\t"+count+"、身份证件类型未选择\n\n");
//				}
				if (marriage.equals("请选择"))
				{
					count++;
					sBuffer.append("\t"+count+"、婚姻状况未选择\n\n");
				}
				if (house.equals("请选择"))
				{
					count++;
					sBuffer.append("\t"+count+"、住宅状况未选择\n\n");
				}
				if (!MyConstants.baseCheck(cname, 40))
				{
					count++;
					sBuffer.append("\t"+count+"、中文姓名未填写\n\n");
				}
				if (!MyConstants.baseCheck(plname, 10))
				{
					count++;
					sBuffer.append("\t"+count+"、拼音姓格式错误或未填写\n\n");
				}
				if (!MyConstants.baseCheck(pfname, 16))
				{
					count++;
					sBuffer.append("\t"+count+"、拼音名格式错误或未填写\n\n");
				}
				if (!MyConstants.baseCheck(birth, 8))
				{
					count++;
					sBuffer.append("\t"+count+"、出生日期未填写\n\n");
				}
				if (cardtype.equals("O")&&!MyConstants.baseCheck(cardname, 20))
				{
					count++;
					sBuffer.append("\t"+count+"、其他证件类型格式错误或未填写\n\n");
				}
				if (!MyConstants.baseCheck(idnum, 30))
				{
					count++;
					sBuffer.append("\t"+count+"、身份证件号码未填写\n\n");
				}
				if (house.equals("1")&&!MyConstants.numberCheck(monthback, 12))
				{
					count++;
					sBuffer.append("\t"+count+"、月还款金额未填写\n\n");
				}
				if (!MyConstants.numberCheck(liveyear, 2))
				{
					count++;
					sBuffer.append("\t"+count+"、现住址居住年限未填写\n\n");
				}
				if (!MyConstants.numberCheck(income, 12))
				{
					count++;
					sBuffer.append("\t"+count+"、个人年收入格式错误或未填写\n\n");
				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("北京市")||sp.equals("天津市")||sp.equals("上海市")||sp.equals("重庆市"))
				{
					if (spCity.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"、现住址格式错误或未填写\n\n");
					}
				}else {
					if (spProvince.getText().toString().trim().equals("请选择")||spCity.getText().toString().trim().equals("请选择")||spCounty.getText().toString().trim().equals("请选择")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"、现住址格式错误或未填写\n\n");
					}
				}
				
				if (!MyConstants.checkPostCode(postcode))
				{
					count++;
					sBuffer.append("\t"+count+"、邮政编码格式错误或未填写\n\n");
				}
				if ((tel1.length()!=3 && tel1.length()!=4)||!MyConstants.checkTelNum(tel2))
				{
					count++;
					sBuffer.append("\t"+count+"、住宅电话格式错误或未填写\n\n");
				}
				if ((!mobile.equals("")||card_name.contains("易卡"))&&!MyConstants.checkPhoneNum(mobile))
				{
					count++;
					sBuffer.append("\t"+count+"、手机号码格式错误或未填写\n\n");
				}
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
//					rfidDeviceStop();
					MyConstants.editor.putString(MyConstants.CUST_NAME, cname);
					MyConstants.editor.putString(MyConstants.CUST_SEX, sex);
					MyConstants.editor.putString(MyConstants.NATIONALITY, country);
					MyConstants.editor.putString(MyConstants.LAST_NAME, plname.toUpperCase());
					MyConstants.editor.putString(MyConstants.FIRST_NAME, pfname.toUpperCase());
					MyConstants.editor.putString(MyConstants.BIRTHDAY, birth);
					MyConstants.editor.putString(MyConstants.EDU_DEGR, eduString);
					MyConstants.editor.putString(MyConstants.CERT_TYPE, cardtype);
					MyConstants.editor.putString(MyConstants.CERT_NO, idnum);
					MyConstants.editor.putString(MyConstants.CERT_NAME, cardname);
					MyConstants.editor.putString(MyConstants.MARR_STAT, marriage);
					MyConstants.editor.putString(MyConstants.HOU_STAT, house);
					MyConstants.editor.putString(MyConstants.HOU_MON_LOAN, monthback+"00");
					MyConstants.editor.putString(MyConstants.PER_INCOME, income+"000000");
					MyConstants.editor.putString(MyConstants.AVE_INCOME, "");
					MyConstants.editor.putString(MyConstants.PRE_YEARS, liveyear);
					MyConstants.editor.putString(MyConstants.PRE_ADDR, addr);
					MyConstants.editor.putString(MyConstants.PRE_POST, postcode);
					MyConstants.editor.putString(MyConstants.PRE_ZONE_NO, tel1.length()==3?tel1:tel1.substring(1));
					MyConstants.editor.putString(MyConstants.PRE_PHONE, tel2);
					MyConstants.editor.putString(MyConstants.PRE_MOBILE, mobile);
					MyConstants.editor.putString(MyConstants.CAR_NO, "");//车牌号
					MyConstants.editor.putString(MyConstants.CAR_TYPE, "");//品牌
					MyConstants.editor.commit();
					CustMsgActivity.mTabHost.setCurrentTab(2);
				}
			}
		});

	}

	
	@Override
	protected void onDestroy()
	{
		System.out.println("ondestroy");
		super.onDestroy();
	}

	@Override
	protected void onResume()
	{
		System.out.println("onresume");
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		System.out.println("onpause");
		super.onPause();
	}
	
	@Override
	protected void onStop()
	{
		System.out.println("onstop");
		super.onStop();
	}
	
	@Override
	protected void onStart()
	{
		System.out.println("onstart");
		super.onStart();
	}
	
	@Override
	protected void onRestart()
	{
		System.out.println("onrestart");
		super.onRestart();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)//返回键监听
	{
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			UITools.getTools().getDialog(MyConstants.CANCEL, "确定退出，并清空已填写信息？").show();
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	
}

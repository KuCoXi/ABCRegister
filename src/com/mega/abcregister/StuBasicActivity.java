package com.mega.abcregister;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
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
import android_serialport_api.SerialPort;

import com.authentication.utils.DataUtils;
import com.mega.dao.DataDao;
import com.mega.model.ReadCardThread;
import com.mega.myview.CustomSinnper;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.myview.HintEdittext;
import com.mega.pack.Person;
import com.mega.tools.MyConstants;
import com.mega.tools.SpellTools;
import com.mega.tools.UITools;

public class StuBasicActivity extends Activity
{
	private Calendar cd;
	private Button nextButton;// ��һ����ť
	private Button lastButton;// ���ذ�ť
	private Button readCardButton;// ������������ť
	private HintEdittext etBirth;// ��������
	private EditText etOtherType;// ����֤������
	private HintEdittext etMonthBack;// �»���
	private HintEdittext etChsName;// ��������
	private HintEdittext etPyLName;// ƴ����
	private HintEdittext etPyFName;// ƴ����
	private HintEdittext etIDNum;// ���֤/���պ���
	private EditText etAddress;// ��סַ
	private HintEdittext etLiveYear;// ��סַ��ס����
	private HintEdittext etPostcode;// �ʱ�
	private EditText etHouseTel1;// סլ�绰(����)
	private EditText etHouseTel2;// סլ�绰(����)
	private HintEdittext etPhoneNum;// �ֻ�����
	private TextView tv2;
	private CustomSinnper spProvince;// ʡ
	private CustomSinnper spCity;// ��
	private CustomSinnper spCounty;// ��
	private CustomSinnper spSex;// �Ա�
	private CustomSinnper spNation;// ����
	private CustomSinnper spEducation;// �����̶�
	private CustomSinnper spCardType;// ���֤������
	private CustomSinnper spMarriage;// ����״��
	private CustomSinnper spHouse;// סլ״��
	private static int count = 0;
	private ProgressDialog progressDialog;
	private SerialPort mserialPort;
	private InputStream mmInStream;
	private OutputStream mmOutStream;
	private Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stu_card1);
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
			readCardButton.setText("������֤");
//		}
		etBirth = (HintEdittext) findViewById(R.id.et_birth);
		etBirth.init("*��������", Color.GRAY, 25, 5, 11);
		etOtherType = (EditText) findViewById(R.id.et_othertype);
		etOtherType.setVisibility(View.INVISIBLE);
		etMonthBack = (HintEdittext) findViewById(R.id.et_month_back);
		etMonthBack.init("�»���", Color.GRAY, 25, 5, 11);
		etMonthBack.setVisibility(View.INVISIBLE);
		etChsName = (HintEdittext) findViewById(R.id.et_chsname);
		etChsName.init("*��������", Color.GRAY, 25, 5, 11);
		etPyLName = (HintEdittext) findViewById(R.id.et_py_lname);
		etPyLName.init("*ƴ����", Color.GRAY, 25, 5, 11);
		etPyFName = (HintEdittext) findViewById(R.id.et_py_fname);
		etPyFName.init("*��", Color.GRAY, 25, 5, 11);
		etIDNum = (HintEdittext) findViewById(R.id.et_idnum);
		etIDNum.init("*���֤������", Color.GRAY, 25, 5, 11);
		etAddress = (EditText) findViewById(R.id.et_add_detail);
		etBirth.setEnabled(false);
		etChsName.setEnabled(false);
		etIDNum.setEnabled(false);
		etLiveYear = (HintEdittext) findViewById(R.id.et_house_time);
		etLiveYear.init("*��סַ��ס����", Color.GRAY, 25, 5, 11);
		etPostcode = (HintEdittext) findViewById(R.id.et_postcode);
		etPostcode.init("*��������", Color.GRAY, 25, 5, 11);
		etHouseTel1 = (EditText) findViewById(R.id.et_house_tel1);
		etHouseTel2 = (EditText) findViewById(R.id.et_house_tel2);
		etPhoneNum = (HintEdittext) findViewById(R.id.et_phonenum);
		etPhoneNum.init("�ֻ�", Color.GRAY, 25, 5, 11);
		tv2 = (TextView) findViewById(R.id.tv_month_back2);
		tv2.setVisibility(View.INVISIBLE);
		String[] nationData = DataDao.getSingleData("sp_countries", "title", "title");
		String[] provinceData = DataDao.getSingleData("province", "provinceNAME", "provinceID");
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, provinceData);
		ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this, R.array.sex_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> cardAdapter = ArrayAdapter.createFromResource(this, R.array.chs_cardtype_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> marriageAdapter = ArrayAdapter.createFromResource(this, R.array.marry_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> educationAdapter = ArrayAdapter.createFromResource(this, R.array.stuedu_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> houseAdapter = ArrayAdapter.createFromResource(this, R.array.house_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<String> nationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nationData);
		spProvince.setAdapter(provinceAdapter);
		// spProvince.setSelection(MyConstants.getPosition(provinceData,
		// "��ѡ��"));
		spSex.setAdapter(sexAdapter);
		spCardType.setAdapter(cardAdapter);
		spCardType.setText("���֤");
		spMarriage.setAdapter(marriageAdapter);
		spMarriage.setText("δ��");
		spMarriage.setEnabled(false);
		spEducation.setAdapter(educationAdapter);
		spHouse.setAdapter(houseAdapter);
		spNation.setAdapter(nationAdapter);
		spNation.setText("�й�");
		spNation.setEnabled(false);
		// spSex.setEnabled(false);

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
		spNation.setOnItemSeletedListener(new OnItemSeletedListener()
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
		spMarriage.setOnItemSeletedListener(new OnItemSeletedListener()
		{

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub

			}
		});
		spHouse.setOnItemSeletedListener(new OnItemSeletedListener()
		{

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub

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
		etBirth.setOnTouchListener(new OnTouchListener()
		{

			public boolean onTouch(View v, MotionEvent event)
			{
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					new DatePickerDialog(StuBasicActivity.this, new OnDateSetListener()
					{
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
						{
							int month = monthOfYear + 1;
							etBirth.setText(year + "" + (month > 9 ? month : ("0" + month)) + "" + (dayOfMonth > 9 ? dayOfMonth : ("0" + dayOfMonth)));
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
				if (cityData.length == 0)
				{
					spCity.setVisibility(View.INVISIBLE);
					spCounty.setVisibility(View.INVISIBLE);
				} else
				{
					spCity.setVisibility(View.VISIBLE);
					ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(StuBasicActivity.this, android.R.layout.simple_list_item_1, cityData);
					// cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
				if (countyData.length == 0)
				{
					spCounty.setVisibility(View.INVISIBLE);
				} else
				{
					spCounty.setVisibility(View.VISIBLE);
					ArrayAdapter<String> countyAdapter = new ArrayAdapter<String>(StuBasicActivity.this, android.R.layout.simple_list_item_1, countyData);
					// countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spCounty.setAdapter(countyAdapter);
				}

			}
		});
		readCardButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				progressDialog = UITools.getTools().getSpinnerProgress(StuBasicActivity.this, "������֤", "���ڶ�ȡ����֤��Ϣ...");
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

		spCardType.setOnItemSeletedListener(new OnItemSeletedListener()
		{

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String typeValue = object.toString();
				if (typeValue.equals("����"))
				{
					etOtherType.setVisibility(View.VISIBLE);
				} else
				{
					etOtherType.setVisibility(View.INVISIBLE);
				}

				if (!typeValue.equals("���֤"))
				{
					etIDNum.setEnabled(true);
					etIDNum.setHint("������");
					etIDNum.setText("");
				} else
				{
					etIDNum.setEnabled(false);
					etIDNum.setHint("�������֤");
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
				Object obj = parent.getItemAtPosition(position);
				String typeValue = obj.toString();
				if (typeValue.equals("�Թ��д��"))
				{
					tv2.setVisibility(View.VISIBLE);
					etMonthBack.setVisibility(View.VISIBLE);
				} else
				{
					tv2.setVisibility(View.INVISIBLE);
					etMonthBack.setVisibility(View.INVISIBLE);
				}
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
					System.out.println("�ؼ�ѡ���Ѽ���");
					county = spCounty.getText().toString().trim();
				}
				String addrdetail = etAddress.getText().toString().trim();
				String addr = province + city + county + addrdetail;
				String plname = etPyLName.getText().toString().trim();
				String pfname = etPyFName.getText().toString().trim();
				String cname = etChsName.getText().toString().trim();
				String sex = spSex.getText().toString().trim();
				if (sex.equals("��"))
				{
					sex = "M";
				} else if (sex.equals("Ů"))
				{
					sex = "F";
				}
				String country = "CHN";
				String birth = etBirth.getText().toString().trim();
				String eduString = spEducation.getText().toString().trim();
				if (eduString.equals("��ʿ"))
				{
					eduString = "0";
				} else if (eduString.equals("�о���"))
				{
					eduString = "2";
				} else if (eduString.equals("����"))
				{
					eduString = "3";
				} else if (eduString.equals("ר��"))
				{
					eduString = "4";
				}
				String cardtype = spCardType.getText().toString().trim();
				String cardname = "";
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
					cardname = etOtherType.getText().toString().trim();
				}
				String idnum = etIDNum.getText().toString().trim().toUpperCase();
				String marriage = "0";
				String house = spHouse.getText().toString().trim();
				if (house.equals("�����޴��"))
				{
					house = "0";
				} else if (house.equals("�Թ��д��"))
				{
					house = "1";
				} else if (house.equals("�븸ĸͬס"))
				{
					house = "2";
				} else if (house.equals("����"))
				{
					house = "3";
				} else if (house.equals("����"))
				{
					house = "4";
				}
				String monthback = "";
				if (house.equals("1"))
				{
					monthback = etMonthBack.getText().toString().trim();
				}
				String liveyear = etLiveYear.getText().toString().trim();
				String postcode = etPostcode.getText().toString().trim();
				String tel1 = etHouseTel1.getText().toString().trim();
				String tel2 = etHouseTel2.getText().toString().trim();
				String mobile = etPhoneNum.getText().toString().trim();
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (sex.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t" + count + "���Ա�δѡ��\n\n");
				}
				if (eduString.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t" + count + "���ڶ�ѧ��δѡ��\n\n");
				}
				if (!idnum.equals(""))
				{
					int sexflag = Integer.valueOf(idnum.substring(16, 17));
					boolean sexmatch = false;
					if (sex.equals("F"))
					{
						if (sexflag % 2 == 0)
						{
							sexmatch = true;
						}
					} else if (sex.equals("M"))
					{
						if (sexflag % 2 != 0)
						{
							sexmatch = true;
						}
					}
					if (!sex.equals("��ѡ��") && cardtype.equals("I") && idnum.length() == 18 && !sexmatch)
					{
						count++;
						sBuffer.append("\t" + count + "���Ա������֤���벻��\n\n");
					}
				}
				if (cardtype.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t" + count + "�����֤������δѡ��\n\n");
				}
				if (house.equals("��ѡ��"))
				{
					count++;
					sBuffer.append("\t" + count + "��סլ״��δѡ��\n\n");
				}
				if (!MyConstants.baseCheck(cname, 40))
				{
					count++;
					sBuffer.append("\t" + count + "������������ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(plname, 10))
				{
					count++;
					sBuffer.append("\t" + count + "��ƴ���ո�ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(pfname, 16))
				{
					count++;
					sBuffer.append("\t" + count + "��ƴ������ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(birth, 8))
				{
					count++;
					sBuffer.append("\t" + count + "����������δ��д\n\n");
				}
				if (cardtype.equals("O") && !MyConstants.baseCheck(cardname, 20))
				{
					count++;
					sBuffer.append("\t" + count + "������֤�����͸�ʽ�����δ��д\n\n");
				}
				if (!MyConstants.baseCheck(idnum, 30))
				{
					count++;
					sBuffer.append("\t" + count + "�����֤�������ʽ�����δ��д\n\n");
				}
				if (house.equals("1") && !MyConstants.numberCheck(monthback, 12))
				{
					count++;
					sBuffer.append("\t" + count + "���»������ʽ�����δ��д\n\n");
				}
				if (!MyConstants.numberCheck(liveyear, 2))
				{
					count++;
					sBuffer.append("\t" + count + "����סַ��ס���޸�ʽ�����δ��д\n\n");
				}
				String sp = spProvince.getText().toString().trim();
				if (sp.equals("������")||sp.equals("�����")||sp.equals("�Ϻ���")||sp.equals("������"))
				{
					if (spCity.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"����סַ��ʽ�����δ��д\n\n");
					}
				}else {
					if (spProvince.getText().toString().trim().equals("��ѡ��")||spCity.getText().toString().trim().equals("��ѡ��")||spCounty.getText().toString().trim().equals("��ѡ��")||addrdetail.equals("")||!MyConstants.baseCheck(addr, 80))
					{
						count++;
						sBuffer.append("\t"+count+"����סַ��ʽ�����δ��д\n\n");
					}
				}
				if (!MyConstants.checkPostCode(postcode))
				{
					count++;
					sBuffer.append("\t" + count + "�����������ʽ�����δ��д\n\n");
				}
				if ((tel1.length() != 3 && tel1.length() != 4) || !MyConstants.checkTelNum(tel2))
				{
					count++;
					sBuffer.append("\t" + count + "��סլ�绰��ʽ�����δ��д\n\n");
				}
				if (!mobile.equals("") && !MyConstants.checkPhoneNum(mobile))
				{
					count++;
					sBuffer.append("\t" + count + "���ֻ������ʽ����\n\n");
				}
				if (count > 0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				} else
				{
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
					MyConstants.editor.putString(MyConstants.HOU_MON_LOAN, monthback + "00");
					MyConstants.editor.putString(MyConstants.PER_INCOME, "2000000");
					MyConstants.editor.putString(MyConstants.AVE_INCOME, "2000000");
					MyConstants.editor.putString(MyConstants.PRE_YEARS, liveyear);
					MyConstants.editor.putString(MyConstants.PRE_ADDR, addr);
					MyConstants.editor.putString(MyConstants.PRE_POST, postcode);
					MyConstants.editor.putString(MyConstants.PRE_ZONE_NO, tel1.length() == 3 ? tel1 : tel1.substring(1));
					MyConstants.editor.putString(MyConstants.PRE_PHONE, tel2);
					MyConstants.editor.putString(MyConstants.PRE_MOBILE, mobile);
					MyConstants.editor.commit();
					CustMsgActivity.mTabHost.setCurrentTab(2);
				}
			}
		});
		lastButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				UITools.getTools().getDialog(MyConstants.CANCEL, "ȷ���˳������������д��Ϣ��").show();
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
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	public Handler hd = new Handler()
	{
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what)
			{
			case 1:
				UITools.getTools().showToast("���ڴ�ʧ�ܣ�", true, UITools.BAD);
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
				UITools.getTools().showToast("������֤�ɹ���", true, UITools.NORMAL);
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
					if (spCardType.getText().toString().trim().equals("���֤"))
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

	@Override
	protected void onDestroy()
	{
		// rfidDeviceStop();
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
}

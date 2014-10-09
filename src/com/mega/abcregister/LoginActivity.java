package com.mega.abcregister;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android_serialport_api.SerialPort;

import com.mega.handle.MyHandle;
import com.mega.model.ReadCardThread;
import com.mega.myview.HintEdittext;
import com.mega.pack.Person;
import com.mega.tools.Alarm;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class LoginActivity extends Activity
{
	private Thread thread;
	private Button loginButton;
	private Button loginBackButton;
	private ImageView settingButton;
	private Button reboot;
	private Intent intent;
	private HintEdittext etUserID;
	private HintEdittext etUserPswd;
	private TextView timeTip;
	private MyHandle handle;
	private static int count = 0;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private Alarm alarm;
	private ProgressDialog progressDialog;
	private SerialPort mserialPort;
	private InputStream mmInStream;
	private OutputStream mmOutStream;

	// private boolean type = MyConstants.spf.getString("readcardtype_set",
	// "").equals("肯麦思");

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
		count = 0;
	}

	private void initComponent()
	{
		MyConstants.setPowerOnSFZ();
		reboot = (Button) findViewById(R.id.btReboot);
		reboot.setText("读二代证");
		loginButton = (Button) findViewById(R.id.btLogin);
		loginBackButton = (Button) findViewById(R.id.btLoginBack);
		settingButton = (ImageView) findViewById(R.id.ivLoginSetting);
		etUserID = (HintEdittext) findViewById(R.id.etLoginUserName);
		etUserPswd = (HintEdittext) findViewById(R.id.etLoginPswd);
		etUserID.init("身份证号", Color.DKGRAY, 35, 10, 15);
		etUserPswd.init("密码", Color.DKGRAY, 35, 10, 15);
		timeTip = (TextView) findViewById(R.id.tvTimeTip);
		handle = new MyHandle(timeTip);
		String enddate = null;
		String startdate = null;
		try
		{
			enddate = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aEndDate");
			startdate = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aStartDate");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (enddate == null || startdate == null)
		{
			System.out.println("配置文件出错，请重新获取");
			UITools.getTools().getWarnDialog("配置文件出错，请重新获取").show();
			this.finish();
		} else
		{
			// System.out.println(enddate);
			alarm = new Alarm(startdate, enddate, handle);
			alarm.start();
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
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				progressDialog.dismiss();
				break;
			case 11:
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				progressDialog.dismiss();
				Bundle error_msg = msg.getData();
				UITools.getTools().showToast(error_msg.getString("error"), true, UITools.SAD);
				break;
			case 13:
				progressDialog.dismiss();
				if (mserialPort!=null)
				{
					mserialPort.close();
					mserialPort = null;
				}
				thread = null;
				Bundle bundle = msg.getData();
				Person person = (Person) bundle.getSerializable("person");
				etUserID.setText(person.getPeopleIDCode());
				UITools.getTools().showToast("读二代证成功！", true, UITools.NORMAL);
				break;
			default:
				break;
			}
		}

	};

	private void addListener()
	{
		reboot.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				progressDialog = UITools.getTools().getSpinnerProgress(LoginActivity.this, "读二代证", "正在读取二代证信息...");
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
		settingButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		loginBackButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				alarm.stopRun();
				finish();
			}
		});
		loginButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				intent = new Intent();
				intent.setClass(LoginActivity.this, TaskHomeActivity.class);
				String idText = etUserID.getText().toString().trim().toUpperCase();
				String pswdText = etUserPswd.getText().toString().trim();
				String id = null;
				String pswd = null;
				String starttime = null;
				try
				{
					id = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aOpenId");
					pswd = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aOpenPwd");
					starttime = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.CONFIGFILENAME).get("aStartDate");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (id != null && pswd != null && starttime != null)
				{
					if (Long.parseLong(starttime) > Long.parseLong(sFormat.format(new Date())))
					{
						UITools.getTools().showToast("工作时间未到！", true, UITools.SAD);
					} 
					else if (idText.equals(id)&&pswdText.equals(pswd))
					{
						startActivity(intent);
						LoginActivity.this.finish();
						UITools.getTools().showToast("签到成功！", true, UITools.GOOD);
					}else {
						UITools.getTools().showToast("用户名或密码错误！", true, UITools.SAD);
					}
				} else
				{
					UITools.getTools().getWarnDialog("配置文件出错，请重新获取").show();
				}
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}

	@Override
	protected void onResume()
	{
		System.out.println("onresume");
		super.onResume();
	}
	
}
package com.mega.abcregister;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mega.handle.MyHandle;
import com.mega.tools.MyApplication;
import com.mega.tools.UITools;

public class LogoutActivity extends Activity
{

	private Button logoutButton;
	private Button logoutBackButton;
	private Button settingButton;
	private ProgressDialog progressDialog;
	private Button readCardButton;
	private ProgressDialog readProgress;
	private MyHandle myHandle;
	private EditText etUserID;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logout);
		MyApplication.getInstance().addActivity(this);
		initComponent();
		addListener();
	}
	
	private void initComponent()
	{
		logoutButton = (Button) findViewById(R.id.btLogout);
		logoutBackButton = (Button) findViewById(R.id.btLogoutBack);
		readCardButton = (Button) findViewById(R.id.btLogoutRead);
		etUserID = (EditText) findViewById(R.id.etLogoutUserName);
		settingButton = (Button) findViewById(R.id.btSetting);
		progressDialog = UITools.getTools().getSpinnerProgress(this, "签退中", "正在签退...");
		progressDialog.setCancelable(false);
		readProgress = UITools.getTools().getSpinnerProgress(this, "读取身份证", "正在读取身份证...");
		readProgress.setCancelable(false);
	}
	private void addListener()
	{
		settingButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(LogoutActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		logoutBackButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		logoutButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				progressDialog.show();
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						try
						{
							Thread.sleep(2000);
							progressDialog.dismiss();
							Intent intent = new Intent();
							intent.setClass(LogoutActivity.this, TaskHomeActivity.class);
							startActivity(intent);
						} catch (Exception e)
						{
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		readCardButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
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
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}


}

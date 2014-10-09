package com.mega.abcregister;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.mega.handle.MyHandle;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class RePhotoActivity extends Activity
{

//	private Spinner searchWay;
	private Button searchButton;
	private Button backButton;
	private ImageView settingButton;
	private EditText searchText;
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rephoto);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	
	private void initComponent()
	{
		settingButton = (ImageView) findViewById(R.id.ivSetting);
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("¿ÏÂóË¼"))
//		{
//			LayoutParams lp = new LayoutParams(54, 54);
//			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//			settingButton.setLayoutParams(lp);
//		}
		searchButton = (Button) findViewById(R.id.btSearch);
		backButton = (Button) findViewById(R.id.btSearchBack);
		searchText = (EditText) findViewById(R.id.etProSearchWay);
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
				Intent intent = new Intent(RePhotoActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		searchButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("appnum", searchText.getText().toString().trim());
				intent.putExtras(bundle);
				intent.setClass(RePhotoActivity.this, AddActivity.class);
				if (searchText.getText().toString().trim().length()!=20)
				{
					UITools.getTools().showToast("ÉêÇë±àºÅ±ØÐëÎª20Î»Êý×Ö", true, UITools.SAD);
				}
				else {
					startActivity(intent);
					finish();
				}
			}
		});
		
		backButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) 
//	{
//		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
//	    {     
//			return true;
//	    }
//		return super.dispatchKeyEvent(event);
//	}

}

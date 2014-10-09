package com.mega.abcregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class EditionActivity extends Activity
{

	private Spinner spVersion;
	private TextView tvEdition;
//	private Button btUpdate;
//	private ImageView backButton;
	private ImageView settingButton;
	private ArrayAdapter<String> adapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edition);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	
	private void initComponent()
	{
		tvEdition = (TextView) findViewById(R.id.tvEdition);
		adapter = new ArrayAdapter<String>(this,    
                R.layout.spinner_item, MyConstants.VERSION_LIST);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spVersion = (Spinner) findViewById(R.id.spVersion);
		spVersion.setAdapter(adapter);
//		btUpdate = (Button) findViewById(R.id.btUpdate);
//		backButton = (ImageView) findViewById(R.id.ivBack);
		settingButton = (ImageView) findViewById(R.id.ivSetting);
		spVersion.setSelection(1);
		spVersion.setEnabled(false);
		tvEdition.setText("版本号："+MyConstants.getVersionName());
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("肯麦思"))
//		{
//			LayoutParams lp = new LayoutParams(54, 54);
//			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//			settingButton.setLayoutParams(lp);
//		}
	}
	
	private void addListener()
	{
//		backButton.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				EditionActivity.this.finish();
//			}
//		});
		settingButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(EditionActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
//		btUpdate.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
////				UITools.getTools().showToast("检查更新中...", true);
////				if (MyConstants.spf.getBoolean("ONLINE", false))
////				{
////					new CheckUpdateThread().start();//检查更新
////				}
//			}
//		});
		
		spVersion.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub
				
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


}

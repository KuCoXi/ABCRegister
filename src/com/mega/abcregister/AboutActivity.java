package com.mega.abcregister;

import android.app.Activity;
import android.os.Bundle;

import com.mega.tools.MyApplication;

public class AboutActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		MyApplication.getInstance().addActivity(this);
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}


}

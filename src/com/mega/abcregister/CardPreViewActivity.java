package com.mega.abcregister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CardPreViewActivity extends Activity
{

	private Button ok;
	private TextView detailText;
	private String previewMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_card_detail);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}

	private void initComponent()
	{
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		previewMsg = bundle.getString("premsg");
		ok = (Button) findViewById(R.id.bt_next);
		detailText = (TextView) findViewById(R.id.tv_detail);
		detailText.setText(Html.fromHtml(previewMsg));
		detailText.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	private void addListener()
	{
		ok.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}

	private String readFromFile(String file)
	{
		StringBuffer sBuffer = new StringBuffer();
		try 
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) 
            {
                  sBuffer.append(str);
            }
            in.close();
        } 
        catch (IOException e) 
        {
            e.getStackTrace();
        }
		return sBuffer.toString();
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
}

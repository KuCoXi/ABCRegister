package com.mega.abcregister;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;

public class ZCActivity extends Activity
{

	private WebView webView;
	private String type;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zc);
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		webView = (WebView) findViewById(R.id.wvZC);
		webView.getSettings().setDefaultTextEncodingName("GBK");
		Intent intent = getIntent();
		type = intent.getExtras().getString("type");
	}
	
	private void addListener()
	{
		if (type.equals("zc"))
		{
			webView.loadUrl("file:///android_asset/zc.html");  
		}
		else if (type.equals("hy")) {
			webView.loadUrl("file:///android_asset/hy.html"); 
		}
		else if (type.equals("bz")) {
			webView.loadUrl("file:///android_asset/fee2013.jpg");
		}
		else if (type.equals("zx")) {
			webView.loadUrl("file:///android_asset/zx.html");
		}
		

	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.zc, menu);
//		return true;
//	}

}

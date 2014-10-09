package com.mega.abcregister;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;

public class DownloadActivity extends Activity
{

	private ProgressBar progressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		progressBar = (ProgressBar) findViewById(R.id.pbDownload);
	}
	
	private void addListener()
	{
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.download, menu);
		return true;
	}

}

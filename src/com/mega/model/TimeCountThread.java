package com.mega.model;

import android.app.ProgressDialog;

public class TimeCountThread extends Thread
{
	private int time;
	private ProgressDialog progressDialog;
	public TimeCountThread(int time,ProgressDialog progressDialog)
	{
		this.time = time;
		this.progressDialog = progressDialog;
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		super.run();
		while (time>0)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(time);
			time--;
		}
		progressDialog.dismiss();
	}
}

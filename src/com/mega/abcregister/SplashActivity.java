package com.mega.abcregister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.Manifest.permission;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.mega.handle.MyHandle;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class SplashActivity extends Activity
{

	private File phoneDBpath;// �ֻ������ݿ��ļ���
	private File phoneDBfile;// �ֻ������ݿ��ļ�·����
	private File cardPath;// ����ϢXML�ļ�Ŀ¼
	private File imagePath;// ��ƬĿ¼
	private File megaPath;// �����Ŀ¼
	private File workPath;// ���°�����Ŀ¼
	private File configPath;// ����ϢXML�ļ�
	private File cardImgPath;//��ƬͼƬ�ļ�
//	private File configFile;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		setContentView(R.layout.activity_splash);
		MyApplication.getInstance().addActivity(this);
		phoneDBpath = new File(getDatabasePath(MyConstants.DBFILENAME).getParent());
		phoneDBfile = getDatabasePath(MyConstants.DBFILENAME);
		cardPath = new File(MyConstants.CONFIG_PATH);
		imagePath = new File(MyConstants.IMAGE_PATH);
		megaPath = new File(MyConstants.MEGA_PATH);
		workPath = new File(MyConstants.WORK_PATH);
		configPath = new File(MyConstants.CONFIG_PATH);
//		configFile = new File(MyConstants.CONFIG_PATH+MyConstants.CONFIGFILENAME);
		cardImgPath = new File(MyConstants.CARD_IMG_PATH);
		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				try
				{
					Thread.sleep(3000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		if (!isPad())
		{
			UITools.getTools().getWarnDialog("�������ڸóߴ���豸������").show();
		}
		else {
			if (!phoneDBpath.exists())
			{
				phoneDBpath.mkdir();
			}
			if (!megaPath.exists())
			{
				megaPath.mkdir();
			}
			if (!cardPath.exists())
			{
				cardPath.mkdir();
			}
			if (!imagePath.exists())
			{
				imagePath.mkdir();
			}
			if (!workPath.exists())
			{
				workPath.mkdir();
			}
			if (!configPath.exists())
			{
				configPath.mkdir();
			}
			if (!cardImgPath.exists())
			{
				cardImgPath.mkdir();
			}
			thread.start();
			if (!phoneDBfile.exists())
			{
				try
				{
					InputStream is = getResources().openRawResource(R.raw.auto_data);
					FileOutputStream fos;
					fos = new FileOutputStream(phoneDBfile);
					byte[] buffer = new byte[2048];
					int count = 0;
					while ((count = is.read(buffer)) > 0)
					{
						fos.write(buffer, 0, count);
					}
					fos.close();
					is.close();
					
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			if (!configFile.exists())
//			{
//				try
//				{
//					InputStream is = getResources().openRawResource(R.raw.config);
//					FileOutputStream fos;
//					fos = new FileOutputStream(configFile);
//					byte[] buffer = new byte[2048];
//					int count = 0;
//					while ((count = is.read(buffer)) > 0)
//					{
//						fos.write(buffer, 0, count);
//					}
//					fos.close();
//					is.close();
//					
//				} catch (FileNotFoundException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		}
		
	}

	/**
	 * 
	 * �������� : onKeyDown �������� : ���η��ؼ� ����˵����
	 * 
	 * @param keyCode
	 * @param event
	 * @return ����ֵ��
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-1-5 ����9:59:18 �޸��ˣ�kcx ���� ��
	 * 
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK:
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}

	private String getDisplayX()
	{
		DisplayMetrics dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		return dMetrics.widthPixels + "x" + dMetrics.heightPixels;
	}

	private boolean isPad()
	{
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
//		// ��Ļ���
//		float screenWidth = display.getWidth();
//		// ��Ļ�߶�
//		float screenHeight = display.getHeight();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		// ��Ļ�ߴ�
		double screenInches = Math.sqrt(x + y);
		// ����6�ߴ���ΪPad
		if (screenInches >= 6.0)
		{
			return true;
		}
		return false;
	}

}

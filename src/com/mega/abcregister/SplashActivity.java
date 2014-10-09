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

	private File phoneDBpath;// 手机上数据库文件夹
	private File phoneDBfile;// 手机上数据库文件路径名
	private File cardPath;// 卡信息XML文件目录
	private File imagePath;// 照片目录
	private File megaPath;// 程序根目录
	private File workPath;// 更新包下载目录
	private File configPath;// 卡信息XML文件
	private File cardImgPath;//卡片图片文件
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
			UITools.getTools().getWarnDialog("程序不能在该尺寸的设备上运行").show();
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
	 * 函数名称 : onKeyDown 功能描述 : 屏蔽返回键 参数说明：
	 * 
	 * @param keyCode
	 * @param event
	 * @return 返回值：
	 * 
	 *         修改记录： 日期：2013-1-5 上午9:59:18 修改人：kcx 描述 ：
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
//		// 屏幕宽度
//		float screenWidth = display.getWidth();
//		// 屏幕高度
//		float screenHeight = display.getHeight();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		// 屏幕尺寸
		double screenInches = Math.sqrt(x + y);
		// 大于6尺寸则为Pad
		if (screenInches >= 6.0)
		{
			return true;
		}
		return false;
	}

}

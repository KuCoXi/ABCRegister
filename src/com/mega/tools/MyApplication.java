package com.mega.tools;

import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：kcx
 *  创建时间	：2013-1-28 下午3:53:07 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2013-1-28 下午3:53:07 	修改人：kcx
 *  	描述	:
 ***********************************************************
 */
public class MyApplication extends Application
{
	private List<Activity> actList = new LinkedList<Activity>();//activity集合
	private static MyApplication instance;
	private HandlerThread handlerThread;
	private String rootPath;
	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		instance=this;
		handlerThread = new HandlerThread("handlerThread");
		handlerThread.start();
		setRootPath();
	}
	public static MyApplication getInstance()
	{
		return instance;
	}
	/**
	 * 
	 *  函数名称 : addActivity
	 *  功能描述 : 添加activity到集合中 
	 *  参数及返回值说明：
	 *  	@param activity
	 *
	 *  修改记录：
	 *  	日期：2012-8-26 下午3:35:30	修改人：torres
	 *  	描述	：
	 *
	 */
	public void addActivity(Activity activity)
	{
		actList.add(activity);
	}
	public String getRootPath() {
		return rootPath;
	}
	private void setRootPath() {
		PackageManager manager = this.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			rootPath = info.applicationInfo.dataDir;
			Log.i("rootPath", "################rootPath=" + rootPath);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HandlerThread getHandlerThread() {
		return handlerThread;
	}
	/**
	 * 
	 *  函数名称 : removeActivity
	 *  功能描述 : 从集合中删除activity 
	 *  参数及返回值说明：
	 *  	@param activity
	 *
	 *  修改记录：
	 *  	日期：2012-8-26 下午3:35:49	修改人：torres
	 *  	描述	：
	 *
	 */
	public void removeActivity(Activity activity)
	{
		actList.remove(activity);
	}
	
	/**
	 * 
	 *  函数名称 : getList
	 *  功能描述 : 获取activity集合 
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-1-28 下午3:45:38	修改人：torres
	 *  	描述	：
	 *
	 */
	public List<Activity> getList()
	{
		return actList;
	}
	
	/**
	 * 
	 *  函数名称 : getPresentActivity
	 *  功能描述 : 获取当前activity 
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-1-28 下午3:45:58	修改人：torres
	 *  	描述	：
	 *
	 */
	public Activity getPresentActivity()
	{
		int length = actList.size();
		return actList.get(length-1);
	}
	
	/**
	 * 
	 *  函数名称 : FinishTwoActivity
	 *  功能描述 : 关闭activity栈最上方的两个activity 
	 *  参数及返回值说明：
	 *
	 *  修改记录：
	 *  	日期：2013-1-28 下午3:46:17	修改人：torres
	 *  	描述	：
	 *
	 */
	public void FinishTwoActivity()
	{
		int length = actList.size();
		actList.get(length-1).finish();
		actList.get(length-2).finish();
	}
	
	/**
	 * 
	 *  函数名称 : exit
	 *  功能描述 : 退出程序 
	 *  参数及返回值说明：
	 *
	 *  修改记录：
	 *  	日期：2012-8-26 下午3:36:10	修改人：torres
	 *  	描述	：
	 *
	 */
	public void exit()
	{
		if (MyConstants.SDB != null)
		{
			MyConstants.SDB.close();
		}
		for (Activity activity : actList)
		{
			activity.finish();
		}
	}
	
	public int getWidth()
	{
		DisplayMetrics dm = new DisplayMetrics();   
        getPresentActivity().getWindowManager().getDefaultDisplay().getMetrics(dm); 
        return dm.widthPixels;
	}
	
	public int getHeight()
	{
		DisplayMetrics dm = new DisplayMetrics();   
        getPresentActivity().getWindowManager().getDefaultDisplay().getMetrics(dm); 
        return dm.heightPixels;
	}
}

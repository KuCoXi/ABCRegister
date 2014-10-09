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
 *  ����ժҪ	��<p>
 *
 *  ����	��kcx
 *  ����ʱ��	��2013-1-28 ����3:53:07 
 *  ��ǰ�汾�ţ�v1.0
 *  ��ʷ��¼	:
 *  	����	: 2013-1-28 ����3:53:07 	�޸��ˣ�kcx
 *  	����	:
 ***********************************************************
 */
public class MyApplication extends Application
{
	private List<Activity> actList = new LinkedList<Activity>();//activity����
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
	 *  �������� : addActivity
	 *  �������� : ���activity�������� 
	 *  ����������ֵ˵����
	 *  	@param activity
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2012-8-26 ����3:35:30	�޸��ˣ�torres
	 *  	����	��
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
	 *  �������� : removeActivity
	 *  �������� : �Ӽ�����ɾ��activity 
	 *  ����������ֵ˵����
	 *  	@param activity
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2012-8-26 ����3:35:49	�޸��ˣ�torres
	 *  	����	��
	 *
	 */
	public void removeActivity(Activity activity)
	{
		actList.remove(activity);
	}
	
	/**
	 * 
	 *  �������� : getList
	 *  �������� : ��ȡactivity���� 
	 *  ����������ֵ˵����
	 *  	@return
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-1-28 ����3:45:38	�޸��ˣ�torres
	 *  	����	��
	 *
	 */
	public List<Activity> getList()
	{
		return actList;
	}
	
	/**
	 * 
	 *  �������� : getPresentActivity
	 *  �������� : ��ȡ��ǰactivity 
	 *  ����������ֵ˵����
	 *  	@return
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-1-28 ����3:45:58	�޸��ˣ�torres
	 *  	����	��
	 *
	 */
	public Activity getPresentActivity()
	{
		int length = actList.size();
		return actList.get(length-1);
	}
	
	/**
	 * 
	 *  �������� : FinishTwoActivity
	 *  �������� : �ر�activityջ���Ϸ�������activity 
	 *  ����������ֵ˵����
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-1-28 ����3:46:17	�޸��ˣ�torres
	 *  	����	��
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
	 *  �������� : exit
	 *  �������� : �˳����� 
	 *  ����������ֵ˵����
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2012-8-26 ����3:36:10	�޸��ˣ�torres
	 *  	����	��
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

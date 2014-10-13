package com.mega.fragment;

import java.io.IOException;

import com.mega.abcregister.R;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.XmlTools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.telephony.TelephonyManager;

public class TernSettingFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private EditTextPreference tern_id;
	private EditTextPreference flowid_set;
	private ListPreference overtime_set;
	private ListPreference recalltime_set;
//	private ListPreference readcardtype_set;
	private Preference imei;
	private Preference time;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.tern_setting);
		initPreference();
	}

	private void initPreference()
	{
		tern_id = (EditTextPreference) findPreference("tern_id");
		tern_id.setLayoutResource(R.layout.preference);
		overtime_set = (ListPreference) findPreference("overtime_set");
		overtime_set.setLayoutResource(R.layout.preference);
		flowid_set = (EditTextPreference) findPreference("flowid_set");
		flowid_set.setLayoutResource(R.layout.preference);
		recalltime_set = (ListPreference) findPreference("recalltime_set");
		recalltime_set.setLayoutResource(R.layout.preference);
//		readcardtype_set = (ListPreference) findPreference("readcardtype_set");
//		readcardtype_set.setLayoutResource(R.layout.preference);
		imei = findPreference("imei_num");
		imei.setLayoutResource(R.layout.preference);
		time = findPreference("work_time");
		time.setLayoutResource(R.layout.preference);
	}
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("tern_id"))
		{
			tern_id.setSummary(sharedPreferences.getString("tern_id", ""));
		}
		else if (key.equals("overtime_set")) 
		{
			overtime_set.setSummary(sharedPreferences.getString("overtime_set", "")+"秒");
		}
		else if (key.equals("flowid_set")) 
		{
			flowid_set.setSummary(sharedPreferences.getString("flowid_set", ""));
		}
		else if (key.equals("recalltime_set")) 
		{
			recalltime_set.setSummary(sharedPreferences.getString("recalltime_set", "")+"次");
		}
//		else if (key.equals("readcardtype_set")) 
//		{
//			readcardtype_set.setSummary(sharedPreferences.getString("readcardtype_set", ""));
//			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//			builder.setTitle("提示").setMessage("应用程序将重启以应用新的配置").setCancelable(false).setPositiveButton("确定",new DialogInterface.OnClickListener()
//			{
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which)
//				{
//					// TODO Auto-generated method stub
//					Intent i = getActivity().getBaseContext().getPackageManager()
//						    .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
//						  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						  startActivity(i);
//				}
//			}).create().show();
//		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		tern_id.setSummary(MyConstants.spf.getString("tern_id", ""));
		overtime_set.setSummary(MyConstants.spf.getString("overtime_set", "")+"秒");
		flowid_set.setSummary(MyConstants.spf.getString("flowid_set", ""));
		recalltime_set.setSummary(MyConstants.spf.getString("recalltime_set", "")+"次");
//		readcardtype_set.setSummary(MyConstants.spf.getString("readcardtype_set", ""));
		imei.setSummary(MyConstants.getIMEI().equals("")?"未发现IMEI":MyConstants.getIMEI());
		String startTime = null;
		String endTime = null;
		try
		{
			startTime = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName()).get("aStartDate");
			endTime = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName()).get("aEndDate");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			
		}
		if (startTime!=null && endTime!=null)
		{
			time.setSummary("开始时间："+getDate(startTime)+"\n结束时间："+getDate(endTime));
		}
		else {
			time.setSummary("时间解析错误");
		}
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);// 注册配置变动监听
	}
	
	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);// 解除配置变动监听
	}
	
	private String getDate(String str)
	{
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		String day = str.substring(6, 8);
		String hour = str.substring(8, 10);
		String minute = str.substring(10, 12);
		String second = str.substring(12, 14);
		return year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分"+second+"秒";
	}
}

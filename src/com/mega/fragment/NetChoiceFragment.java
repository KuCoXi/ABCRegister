package com.mega.fragment;

import com.mega.abcregister.R;
import com.mega.tools.MyConstants;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;

public class NetChoiceFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private CheckBoxPreference open_gprs;
	private CheckBoxPreference open_tcp;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.net_choice);
		initPreference();
	}
	
	private void initPreference()
	{
		open_gprs = (CheckBoxPreference) findPreference("open_gprs");
		open_gprs.setLayoutResource(R.layout.preference);
		open_tcp = (CheckBoxPreference) findPreference("open_tcp");
		open_tcp.setLayoutResource(R.layout.preference);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("open_gprs")) 
		{
			if (open_gprs.isChecked()==true)
			{
				open_tcp.setChecked(false);
			}
			else {
				open_tcp.setChecked(true);
			}
		}
		else if (key.equals("open_tcp")) 
		{
			if (open_tcp.isChecked()==true)
			{
				open_gprs.setChecked(false);
			}
			else {
				open_gprs.setChecked(true);
			}
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		if (MyConstants.spf.getBoolean("FIRST_CHOOSE", true))
		{
			open_tcp.setChecked(true);
			MyConstants.editor.putBoolean("FIRST_CHOOSE", false);
			MyConstants.editor.commit();
		}
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);// ◊¢≤·≈‰÷√±‰∂Øº‡Ã˝
	}
	
	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);// Ω‚≥˝≈‰÷√±‰∂Øº‡Ã˝
	}

}

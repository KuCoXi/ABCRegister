package com.mega.fragment;

import com.mega.abcregister.R;
import com.mega.tools.MyConstants;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

public class TcpFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private EditTextPreference tcp_ip_set;
	private EditTextPreference tcp_port_set;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.tcpip);
		initPreference();
	}
	
	private void initPreference()
	{
		tcp_ip_set = (EditTextPreference) findPreference("tcp_ip_set");
		tcp_ip_set.setLayoutResource(R.layout.preference);
		tcp_port_set = (EditTextPreference) findPreference("tcp_port_set");
		tcp_port_set.setLayoutResource(R.layout.preference);
	}
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("tcp_ip_set")) 
		{
			tcp_ip_set.setSummary(sharedPreferences.getString("tcp_ip_set", ""));
		}
		else if (key.equals("tcp_port_set")) 
		{
			tcp_port_set.setSummary(sharedPreferences.getString("tcp_port_set", ""));
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		tcp_ip_set.setSummary(MyConstants.spf.getString("tcp_ip_set", ""));
		tcp_port_set.setSummary(MyConstants.spf.getString("tcp_port_set", ""));
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

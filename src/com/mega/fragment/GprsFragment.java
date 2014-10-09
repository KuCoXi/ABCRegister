package com.mega.fragment;

import com.mega.abcregister.R;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.Preference.OnPreferenceClickListener;
import android.text.InputType;
import android.widget.Toast;

public class GprsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private EditTextPreference gprs_ip_set;
	private EditTextPreference gprs_port_set;
	private EditTextPreference gprs_apn_set;
	private EditTextPreference gprs_dialnum_set;
	private EditTextPreference gprs_username_set;
	private EditTextPreference gprs_pswd_set;
	private Preference gprs_conn;
	private Preference check_gprs;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.gprs);
		initPreference();
		addListener();
	}
	
	private void initPreference()
	{
		gprs_ip_set = (EditTextPreference) findPreference("gprs_ip_set");
		gprs_ip_set.setLayoutResource(R.layout.preference);
		gprs_port_set = (EditTextPreference) findPreference("gprs_port_set");
		gprs_port_set.setLayoutResource(R.layout.preference);
		gprs_apn_set = (EditTextPreference) findPreference("gprs_apn_set");
		gprs_apn_set.setLayoutResource(R.layout.preference);
		gprs_dialnum_set = (EditTextPreference) findPreference("gprs_dialnum_set");
		gprs_dialnum_set.setLayoutResource(R.layout.preference);
		gprs_username_set = (EditTextPreference) findPreference("gprs_username_set");
		gprs_username_set.setLayoutResource(R.layout.preference);
		gprs_pswd_set = (EditTextPreference) findPreference("gprs_pswd_set");
		gprs_pswd_set.getEditText().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
		gprs_pswd_set.setLayoutResource(R.layout.preference);
		gprs_conn = findPreference("gprs_conn");
		gprs_conn.setLayoutResource(R.layout.preference);
		check_gprs = findPreference("check_gprs");
		check_gprs.setLayoutResource(R.layout.preference);
	}
	
	private void addListener()
	{
		gprs_conn.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				// TODO Auto-generated method stub
//				Toast.makeText(getActivity(), "GPRS已连接！", Toast.LENGTH_SHORT).show();
				UITools.getTools().showToast("GPRS已连接！", true, UITools.GOOD);
				return false;
			}
		});
		
		check_gprs.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				// TODO Auto-generated method stub
//				Toast.makeText(getActivity(), "GPRS信号正常！", Toast.LENGTH_SHORT).show();
				UITools.getTools().showToast("GPRS信号正常！", true, UITools.GOOD);
				return false;
			}
		});
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("gprs_ip_set")) 
		{
			gprs_ip_set.setSummary(sharedPreferences.getString("gprs_ip_set", ""));
		}
		else if (key.equals("gprs_port_set")) 
		{
			gprs_port_set.setSummary(sharedPreferences.getString("gprs_port_set", ""));
		}
		else if (key.equals("gprs_apn_set")) 
		{
			gprs_apn_set.setSummary(sharedPreferences.getString("gprs_apn_set", ""));
		}
		else if (key.equals("gprs_dialnum_set")) 
		{
			gprs_dialnum_set.setSummary(sharedPreferences.getString("gprs_dialnum_set", ""));
		}
		else if (key.equals("gprs_username_set")) 
		{
			gprs_username_set.setSummary(sharedPreferences.getString("gprs_username_set", ""));
		}
		else if (key.equals("gprs_pswd_set")) 
		{
			gprs_pswd_set.setSummary(MyConstants.showPswd(sharedPreferences.getString("gprs_pswd_set", "")));
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		if (MyConstants.spf.getBoolean("open_gprs", true))
		{
			gprs_conn.setEnabled(true);
			check_gprs.setEnabled(true);
		}
		else {
			gprs_conn.setEnabled(false);
			check_gprs.setEnabled(false);
		}
		gprs_apn_set.setSummary(MyConstants.spf.getString("gprs_apn_set", ""));
		gprs_dialnum_set.setSummary(MyConstants.spf.getString("gprs_dialnum_set", ""));
		gprs_ip_set.setSummary(MyConstants.spf.getString("gprs_ip_set", ""));
		gprs_port_set.setSummary(MyConstants.spf.getString("gprs_port_set", ""));
		gprs_pswd_set.setSummary(MyConstants.showPswd(MyConstants.spf.getString("gprs_pswd_set", "")));
		gprs_username_set.setSummary(MyConstants.spf.getString("gprs_username_set", ""));
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);// 注册配置变动监听
	}
	
	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);// 解除配置变动监听
	}

}

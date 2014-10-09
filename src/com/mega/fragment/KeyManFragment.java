package com.mega.fragment;

import com.mega.abcregister.R;
import com.mega.tools.MyConstants;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.text.InputType;

public class KeyManFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private EditTextPreference manual_set;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.key);
		manual_set = (EditTextPreference) findPreference("manual_set");
		manual_set.setLayoutResource(R.layout.preference);
		manual_set.getEditText().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("manual_set")) 
		{
			manual_set.setSummary(MyConstants.showPswd(sharedPreferences.getString("manual_set", "")));
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		manual_set.setSummary(MyConstants.showPswd(MyConstants.spf.getString("manual_set", "")));
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

package com.mega.fragment;

import java.util.Set;

import com.mega.abcregister.R;
import com.mega.tools.MyConstants;

import android.R.integer;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class BluetoothFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener
{

	private CheckBoxPreference open_bluetooth;
	private Preference bluetooth_setting;
	private ListPreference default_printer;
	private BluetoothAdapter mBluetoothAdapter = null;
	private Set<BluetoothDevice> pairedDevices;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.bluetooth);
		initPreference();
		addListener();
		getActivity().registerReceiver(mStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
	}
	
	private void initPreference()
	{
		open_bluetooth = (CheckBoxPreference) findPreference("open_bluetooth");
		open_bluetooth.setLayoutResource(R.layout.preference);
		bluetooth_setting = findPreference("bluetooth_setting");
		bluetooth_setting.setLayoutResource(R.layout.preference);
		default_printer = (ListPreference) findPreference("default_printer");
		default_printer.setLayoutResource(R.layout.preference);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		pairedDevices = mBluetoothAdapter.getBondedDevices();
		String[] entries = new String[pairedDevices.size()];
		int i = 0;
		if (pairedDevices.size() > 0)
		{
			// findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
			for (BluetoothDevice device : pairedDevices)
			{
				entries[i] = device.getName() + " (已配对)" + "\n" + device.getAddress();
				i++;
			}
		}
		default_printer.setEntries(entries);
		default_printer.setEntryValues(entries);
	}
	
	private void addListener()
	{
		bluetooth_setting.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);  
				startActivity(intent);
				return false;
			}
		});
	}
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		// TODO Auto-generated method stub
		if (key.equals("open_bluetooth"))
		{
			if (open_bluetooth.isChecked())
			{
				mBluetoothAdapter.enable();
			}
			else {
				mBluetoothAdapter.disable();
			}
		}
		else if(key.equals("default_printer"))
		{
			String str = sharedPreferences.getString("default_printer", "");
			str = str.substring(0, str.length()-17);
			default_printer.setSummary(str);
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		mBluetoothAdapter.enable();
//		if (mBluetoothAdapter.isEnabled())
//		{
//			open_bluetooth.setChecked(true);
//		}
//		else {
//			open_bluetooth.setChecked(false);
//		}
		pairedDevices = mBluetoothAdapter.getBondedDevices();
		String[] entries = new String[pairedDevices.size()];
		int i = 0;
		if (pairedDevices.size() > 0)
		{
			// findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
			for (BluetoothDevice device : pairedDevices)
			{
				entries[i] = device.getName() + " (已配对)" + "\n" + device.getAddress();
				i++;
			}
		}
		default_printer.setEntries(entries);
		default_printer.setEntryValues(entries);
		default_printer.setSummary(MyConstants.spf.getString("default_printer", ""));
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);// 注册配置变动监听
	}
	
	private BroadcastReceiver mStateReceiver = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			switch (mBluetoothAdapter.getState())
			{
			case BluetoothAdapter.STATE_ON:
				open_bluetooth.setChecked(true);
				break;
			case BluetoothAdapter.STATE_OFF:
				open_bluetooth.setChecked(false);
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);// 解除配置变动监听
	}
	
	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().unregisterReceiver(mStateReceiver);
	}

}

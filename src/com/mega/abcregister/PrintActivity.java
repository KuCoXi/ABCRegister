package com.mega.abcregister;

import java.lang.reflect.Method;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.mega.handle.MyHandle;
import com.mega.model.TimeCountThread;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.sprt.bluetooth.android.BluetoothPrinter;
import com.sprt.bluetooth.android.PrinterType;

public class PrintActivity extends Activity
{
	private BluetoothAdapter mBluetoothAdapter = null;
	public static BluetoothPrinter mPrinter;
	private BluetoothDevice currentDevice;
	private boolean hasRegisteredBoundReceiver;
	private IntentFilter boundFilter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
	private Button btOpen;
	private Button btPrint;
	private Button btSetting;
	private MyHandle handle;
	private ProgressDialog progressDialog;
	// Intent request codes

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_print);
		initComponent();
		addListener();
		registerReceiver(mStateReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
		MyApplication.getInstance().addActivity(this);
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		if (!mBluetoothAdapter.isEnabled())
		{
			btOpen.setEnabled(true);
			btOpen.setText("打开蓝牙");
			btPrint.setEnabled(false);
		} else
		{
			btOpen.setText("蓝牙已开启");
			btOpen.setEnabled(false);
			btPrint.setEnabled(true);
		}
		
		if(MyConstants.spf.getString("default_printer", "").equals(""))
		{
			btPrint.setEnabled(false);
//			Toast.makeText(getApplicationContext(), "未设置蓝牙打印机！", Toast.LENGTH_SHORT).show();
			UITools.getTools().showToast("未设置蓝牙打印机！", true, UITools.SAD);
		} 
	}

	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mStateReceiver);
		if (mPrinter!=null)
		{
			mPrinter.closeConnection();
		}
		MyApplication.getInstance().removeActivity(this);
	}

	private void initComponent()
	{
		btOpen = (Button) findViewById(R.id.btOpenBluetooth);
		btPrint = (Button) findViewById(R.id.btSubmit);
		btSetting = (Button) findViewById(R.id.btBluetoothSetting);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		progressDialog = UITools.getTools().getSpinnerProgress(this, "打印", "正在连接并打印...");
//		progressDialog.setCancelable(false);
		if (mBluetoothAdapter == null)
		{
			btOpen.setText("该设备无蓝牙！");
			btOpen.setEnabled(false);
			btPrint.setEnabled(false);
		}
	}

	private void addListener()
	{
		btOpen.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mBluetoothAdapter.enable();
			}
		});
		
		btPrint.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				progressDialog.show();
				new TimeCountThread(30, progressDialog).start();
				handle = new MyHandle(progressDialog);
				String address = MyConstants.spf.getString("default_printer", "");
				address = address.substring(address.length()-17);
				BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

				if (device.getBondState() == BluetoothDevice.BOND_NONE)
				{
					currentDevice = device;
					registerReceiver(boundDeviceReceiver, boundFilter);
					hasRegisteredBoundReceiver = true;
					// use invoke method to call
					// BluetoothDevice.createBond(BluetoothDevice remoteDevice);
					try
					{
						// Method setPinMethod =
						// BluetoothDevice.class.getMethod("setPin");
						// setPinMethod.invoke(device, 1234);
						Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
						createBondMethod.invoke(device);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (device.getBondState() == BluetoothDevice.BOND_BONDED)
				{
					// Attempt to connect to the device
					initPrinter(device);
				}
			}
		});
		
		btSetting.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(PrintActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
	}

	private BroadcastReceiver mStateReceiver = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			switch (mBluetoothAdapter.getState())
			{
			case BluetoothAdapter.STATE_ON:
				btOpen.setText("蓝牙已开启");
				btOpen.setEnabled(false);
				btPrint.setEnabled(true);
				break;
			case BluetoothAdapter.STATE_OFF:
				btOpen.setEnabled(true);
				btOpen.setText("打开蓝牙");
				btPrint.setEnabled(false);
				break;
			default:
				break;
			}
		}
	};

	private BroadcastReceiver boundDeviceReceiver = new BroadcastReceiver()
	{
		public void onReceive(Context context, Intent intent)
		{
			String action = intent.getAction();
			BluetoothDevice device = null;
			if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action))
			{
				device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (!currentDevice.equals(device))
				{
					return;
				}
				switch (device.getBondState())
				{
				case BluetoothDevice.BOND_BONDING:
					break;
				case BluetoothDevice.BOND_BONDED:
					// if bound success, auto init BluetoothPrinter. open
					// connect.
					if (hasRegisteredBoundReceiver)
					{
						unregisterReceiver(boundDeviceReceiver);
						hasRegisteredBoundReceiver = false;
					}
					initPrinter(device);
					break;
				case BluetoothDevice.BOND_NONE:
					if (hasRegisteredBoundReceiver)
					{
						unregisterReceiver(boundDeviceReceiver);
						hasRegisteredBoundReceiver = false;
					}
				default:
					break;
				}
			}
		}
	};

	// use device to init printer.
	private void initPrinter(BluetoothDevice device)
	{
		mPrinter = new BluetoothPrinter(device);
		mPrinter.setCurrentPrintType(PrinterType.TIII);
		// set handler for receive message of connect state from sdk.
		 mPrinter.setHandler(handle);
		mPrinter.openConnection();
	}

}

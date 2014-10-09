package com.mega.fragment;

import com.mega.abcregister.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingTitlesFragment extends ListFragment
{
	boolean mDualPane;
	int mCurCheckPosition = 0;
	public static String[] array = { "终端设置","联网方式","GPRS设置","TCP/IP设置","密钥管理","蓝牙打印机" };
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, array));

		View detailsFrame = getActivity().findViewById(R.id.details);

		mDualPane = (detailsFrame != null) && (detailsFrame.getVisibility() == View.VISIBLE);

		if (savedInstanceState != null)
		{
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0); 
		}

		if (mDualPane)
		{
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

			showDetails(mCurCheckPosition);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showDetails(position);
	}

	void showDetails(int index)
	{
		mCurCheckPosition = index;
		if (mDualPane)
		{
			getListView().setItemChecked(index, true);
			Fragment details = (Fragment) getFragmentManager().findFragmentById(R.id.details);
			switch (index)
			{
			case 0:
				details = new TernSettingFragment();
				break;
			case 1:
				details = new NetChoiceFragment();
				break;
			case 2:
				details = new GprsFragment();
				break;
			case 3:
				details = new TcpFragment();
				break;
			case 4:
				details = new KeyManFragment();
				break;
			case 5:
				details = new BluetoothFragment();
				break;
			default:
				break;
			}
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.details, details);
			ft.commit();
	
		} else
		{
			new AlertDialog.Builder(getActivity()).setTitle(android.R.string.dialog_alert_title).setMessage(array[index]).setPositiveButton(android.R.string.ok, null).show();
		}
	}
}

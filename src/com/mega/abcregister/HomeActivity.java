package com.mega.abcregister;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleAdapter;

import com.mega.handle.MyHandle;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

/**
 * **********************************************************
 *  ����ժҪ	��<p>
 *
 *  ����	��torres
 *  ����ʱ��	��2012-12-25 ����10:51:08 
 *  ��ǰ�汾�ţ�v1.0
 *  ��ʷ��¼	:
 *  	����	: 2012-12-25 ����10:51:08 	�޸��ˣ�kcx
 *  	����	:
 ***********************************************************
 */
public class HomeActivity extends Activity
{

	private GridView gvHome;//�Ź���
	private ImageView btExit;//�˳���ť
	private ImageView btAbout;//���ڰ�ť
	private ProgressDialog progressDialog;
	private MyHandle loginHandle;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
		if (MyConstants.spf.getBoolean("FIRST_USE", true))
		{
			MyConstants.editor.putString("tern_id", getString(R.string.tern_id_value));
			MyConstants.editor.putString("overtime_set", getString(R.string.overtime_value));
			MyConstants.editor.putString("flowid_set", getString(R.string.flowid_value));
			MyConstants.editor.putString("recalltime_set", getString(R.string.recalltime_value));
//			MyConstants.editor.putString("readcardtype_set", getString(R.string.readcardtype_value));
			MyConstants.editor.putString("tcp_ip_set", getString(R.string.tcp_ip_value));
			MyConstants.editor.putString("tcp_port_set", getString(R.string.tcp_port_value));
			MyConstants.editor.putBoolean("FIRST_USE", false);
			MyConstants.editor.commit();
		}
		
	}
	
	/**
	 * 
	 *  �������� : initComponent
	 *  �������� : ��ʼ���������
	 *  ����������ֵ˵����
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2012-12-25 ����10:52:08	�޸��ˣ�kcx
	 *  	����	��
	 *
	 */
	private void initComponent()
	{
		gvHome = (GridView) findViewById(R.id.gvHome);//�Ź������
		ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
		String[] iconname = new String[] { "ǩ��", "ǩ��", "����", "�汾" };
		for (int i = 0; i < 4; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", R.drawable.home_icon1+i);
			map.put("itemText", iconname[i]);
			lst.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, lst, R.layout.home_item, new String[] { "itemImage","itemText"}, new int[] { R.id.ivIcon/*,R.id.tvIconName*/});
		gvHome.setAdapter(adapter);
		btAbout = (ImageView) findViewById(R.id.ivAbout);
		btExit = (ImageView) findViewById(R.id.ivExit);
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("����˼"))
//		{
//			LayoutParams lp1 = new LayoutParams(56, 56);
//			LayoutParams lp2 = new LayoutParams(56, 56);
//			lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//			lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//			btAbout.setLayoutParams(lp1);
//			btExit.setLayoutParams(lp2);
//		}
		progressDialog = UITools.getTools().getSpinnerProgress(this, "�ն�ǩ��", "����ǩ��...");
	}
	
	/**
	 * 
	 *  �������� : addListener
	 *  �������� : ��Ӽ��� 
	 *  ����������ֵ˵����
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2012-12-26 ����3:43:52	�޸��ˣ�kcx
	 *  	����	��
	 *
	 */
	public void addListener()
	{
		gvHome.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				Object obj = gvHome.getAdapter().getItem(arg2);
				HashMap<String, Object> map = (HashMap<String, Object>) obj;
				String str = (String) map.get("itemText");
				Intent intent = new Intent();
				if (str.equals("ǩ��"))
				{
					File file = new File(MyConstants.CONFIG_PATH+MyConstants.CONFIGFILENAME);
					System.out.println("�����ļ�����"+MyConstants.CONFIG_PATH+MyConstants.CONFIGFILENAME);
					if (!file.exists()||file.length()==0)
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
						builder.setTitle("����").setMessage("δ���������ļ�").setCancelable(false).setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int which)
							{}
						}).create().show();
					}
					else {
						intent.setClass(HomeActivity.this, LoginActivity.class);
//						intent.setClass(HomeActivity.this, TaskHomeActivity.class);
						startActivity(intent);
					}
				}
				if (str.equals("ǩ��"))
				{
//					intent.setClass(HomeActivity.this, LogoutActivity.class);
//					HomeActivity.this.startActivity(intent);
					UITools.getTools().showToast("�ù�����ȡ��", true, UITools.SAD);
				}
				if (str.equals("����"))
				{
//					UITools.getTools().getSettingDialog().show();
					intent.setClass(HomeActivity.this, SettingActivity.class);
					startActivity(intent);
				}
				if (str.equals("�汾"))
				{
					intent.setClass(HomeActivity.this, EditionActivity.class);
					startActivity(intent);
				}
				
			}
		});
		
		btAbout.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, AboutActivity.class);
				HomeActivity.this.startActivity(intent);
			}
		});
		
		btExit.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				UITools.getTools().getDialog(MyConstants.EXIT,"ȷ���˳���").show();
			}
		});
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) 
//	{
//		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
//	    {     
//			return true;
//	    }
//		return super.dispatchKeyEvent(event);
//	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)//���ؼ�����
	{
		// ���¼����Ϸ��ذ�ť
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			UITools.getTools().getDialog(MyConstants.EXIT,"ȷ���˳���").show();
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

}

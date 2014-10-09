package com.mega.abcregister;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.DocumentException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.mega.handle.MyHandle;
import com.mega.model.TimeCountThread;
import com.mega.pack.Card;
import com.mega.tools.FileTools;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class TaskHomeActivity extends Activity
{

	private GridView gvTaskHome;
//	private ImageView logoutButton;
	private ImageView settingButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_home);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}

	private void initComponent()
	{
//		logoutButton = (ImageView) findViewById(R.id.ivLogout);
		settingButton = (ImageView) findViewById(R.id.ivSetting);
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("����˼"))
//		{
//			LayoutParams lp = new LayoutParams(54, 54);
//			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//			settingButton.setLayoutParams(lp);
//		}
		gvTaskHome = (GridView) findViewById(R.id.gvTaskHome);//�Ź������
		ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
		String[] iconname = new String[] { "�쿨����", "����Ԥ��", "�ֶ�����", "����ص�","������","�ʾ����" };
		for (int i = 0; i < 6; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", R.drawable.task_icon1+i);
			map.put("itemText", iconname[i]);
			lst.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, lst, R.layout.home_item, new String[] { "itemImage","itemText"}, new int[] { R.id.ivIcon/*,R.id.tvIconName*/});
		gvTaskHome.setAdapter(adapter);
	}
	
	private void addListener()
	{
//		logoutButton.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				UITools.getTools().getDialog(MyConstants.LOGOUT,"ȷ��ע����").show();
//			}
//		});
		settingButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(TaskHomeActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		gvTaskHome.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				Object obj = gvTaskHome.getAdapter().getItem(arg2);
				HashMap<String, Object> map = (HashMap<String, Object>) obj;
				String str = (String) map.get("itemText");
				Intent intent = new Intent();
				if (str.equals("�쿨����"))
				{
					if (FileTools.isSDCardReady())
					{
						Bundle bundle = new Bundle();
						List<Card> list = new ArrayList<Card>();
						try
						{
							list = XmlTools.readCardTypeXmlOut(MyConstants.CONFIG_PATH+MyConstants.CONFIGFILENAME);
							bundle.putSerializable("card", (Serializable)list);
							intent.putExtras(bundle);
							intent.setClass(TaskHomeActivity.this, CardChooseActivity.class);
							startActivity(intent);
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							System.out.println(e);
							UITools.getTools().showToast("�����ļ�����", true, UITools.BAD);
						} catch (DocumentException e)
						{
							// TODO Auto-generated catch block
							System.out.println(e);
							UITools.getTools().showToast("�����ļ�����", true, UITools.BAD);
						}
//						list.addAll(list);
						
						
					}
					else {
						UITools.getTools().showToast("δ���ִ洢�������ô洢", true, UITools.SAD);
					}
					
				}
				if (str.equals("����Ԥ��"))
				{
					intent.setClass(TaskHomeActivity.this, MsgPreViewActivity.class);
					startActivity(intent);
				}
				if (str.equals("�ֶ�����"))
				{
					intent.setClass(TaskHomeActivity.this, RePhotoActivity.class);
					startActivity(intent);
				}
				if (str.equals("����ص�"))
				{
//					UITools.getTools().showToast("�˹�����δ��ͨ", true);
					intent.setClass(TaskHomeActivity.this, MsgPreViewActivity.class);
					startActivity(intent);
				}
				if (str.equals("������"))
				{
//					UITools.getTools().showToast("�˹�����δ��ͨ", true);
					intent.setClass(TaskHomeActivity.this, KnowledgeActivity.class);
					startActivity(intent);
				}
				if (str.equals("�ʾ����"))
				{
//					String macAddress = null;  
//					WifiManager wifiMgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);  
//					WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());  
//					if (null != info) {  
//					    macAddress = info.getMacAddress();  
//					}  
//						  String[] strArr = macAddress.split(":");
//						  StringBuffer sBuffer = new StringBuffer();
//						  for(int i = 0;i < strArr.length; i++){
//						   sBuffer.append(strArr[i]);
//						  }
//						System.out.println("mac:" + sBuffer);
//					UITools.getTools().showToast("mac:" + sBuffer, true);
//					UITools.getTools().showToast("�˹�����δ��ͨ", true);
					intent.setClass(TaskHomeActivity.this, SaleActivity.class);
					startActivity(intent);
				}
			}
		});
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
			UITools.getTools().getDialog(MyConstants.LOGOUT,"ȷ��ע����").show();
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}

}

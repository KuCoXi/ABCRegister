package com.mega.abcregister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mega.adapter.MsgListAdapter;
import com.mega.handle.MyHandle;
import com.mega.myview.ScrollTextView;
import com.mega.pack.CardMsg;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class MsgPreViewActivity extends Activity
{

//	private ImageView backButton;
	private ImageView settingButton;
	private ListView listView;
	private TextView tvTip;
	private MsgListAdapter msgListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_preview);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	
	private void initComponent()
	{
		tvTip = (TextView) findViewById(R.id.tvTip);
		try
		{
			MyConstants.WORKFILENAME = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH + MyConstants.getConfigFileName()).get("aFileName");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			UITools.getTools().showToast("配置文件出错，请检查", true, UITools.BAD);
		}
		System.out.println(MyConstants.WORKFILENAME);
		settingButton = (ImageView) findViewById(R.id.ivSetting);
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("肯麦思"))
//		{
//			LayoutParams lp = new LayoutParams(54, 54);
//			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//			settingButton.setLayoutParams(lp);
//		}
		listView = (ListView) findViewById(R.id.lvMsg);
	}
	
	private void addListener()
	{
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				View contentView = LayoutInflater.from(MsgPreViewActivity.this).inflate(R.layout.card_msg_detail, null);
				TextView tvMsg = (TextView) contentView.findViewById(R.id.tvMsgDetail);
				TextView appNum = (TextView) view.findViewById(R.id.tvAppNum);
				TextView idnum = (TextView) view.findViewById(R.id.tvIdNum);
				String[] app_num = appNum.getText().toString().trim().split("：");
				String[] id_num = idnum.getText().toString().trim().split("：");
				CardMsg cardMsg = MyConstants.getCardDetail(MyConstants.WORK_PATH+MyConstants.WORKFILENAME,app_num[1].trim(), id_num[1]);
				tvMsg.setText(Html.fromHtml(MyConstants.getHtml(cardMsg)));
				AlertDialog.Builder builder = new AlertDialog.Builder(MsgPreViewActivity.this);
				builder.setTitle("业务信息详情")
				.setView(contentView)
				.setCancelable(false)
				.setPositiveButton("返回", null).create().show();
			}
		});
		settingButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(MsgPreViewActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("onResume()");
		List<HashMap<String, Object>> msgList = MyConstants.getMsgList(MyConstants.WORK_PATH+MyConstants.WORKFILENAME);
		if (msgList!=null&&msgList.size()>0)
		{
			listView.setVisibility(View.VISIBLE);
			msgListAdapter = new MsgListAdapter(MsgPreViewActivity.this, msgList, R.layout.msg_list_item, 
					new String[]{"name","idnum","cardname","appnum"}, 
					new int[]{R.id.tvName,R.id.tvIdNum,R.id.tvCardName,R.id.tvAppNum});
			System.out.println("信息列表数："+msgListAdapter.getCount());
			listView.setAdapter(msgListAdapter);
			tvTip.setText("");
		}
		else {
			listView.setVisibility(View.INVISIBLE);
			tvTip.setText("无办卡申请信息");
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
		MyApplication.getInstance().removeActivity(this);
	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onPause()");
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
}

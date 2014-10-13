package com.mega.abcregister;


import java.io.IOException;
import java.util.Map;

import android.app.ActivityGroup;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TabHost;
import android.widget.TextView;

import com.mega.pack.Card;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.XmlTools;

@SuppressWarnings("deprecation")
public class CustMsgActivity extends ActivityGroup
{

	public static TabHost mTabHost;
	public static Card card;
	public static Map<String, String> map;
	public static StringBuffer preView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_msg);
		initComponent();
		MyApplication.getInstance().addActivity(this);
//		MyConstants.editor.putString(MyConstants.CARD_CODE, "中央预算单位公务卡");
		MyConstants.editor.commit();
	}

	private void initComponent()
	{
		try
		{
			map = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle bundle = this.getIntent().getExtras();
		card = (Card) bundle.get("detail");
		MyConstants.SDB = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(MyConstants.DBFILENAME), null);
		mTabHost = (TabHost) findViewById(R.id.mytabhost);
		mTabHost.setup(this.getLocalActivityManager());
		if (card.getStudentCard().equals("0"))
		{
			mTabHost.addTab(mTabHost.newTabSpec("介绍").setIndicator("介绍").setContent(new Intent(this, CustPreviewActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("基本").setIndicator("基本").setContent(new Intent(this, StuBasicActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("学校").setIndicator("学校").setContent(new Intent(this, StuSchoolActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("联系人").setIndicator("联系人").setContent(new Intent(this, StuRelaActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("其他").setIndicator("其他").setContent(new Intent(this, CustOtherActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("影像").setIndicator("影像").setContent(new Intent(this, CustPhotoActivity.class)));
		}
		else {
			mTabHost.addTab(mTabHost.newTabSpec("介绍").setIndicator("介绍").setContent(new Intent(this, CustPreviewActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("基本").setIndicator("基本").setContent(new Intent(this, CustBasicActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("职业").setIndicator("职业").setContent(new Intent(this, CustJobActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("联系人").setIndicator("联系人").setContent(new Intent(this, CustRelaActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("附属卡").setIndicator("附属卡").setContent(new Intent(this, CustAttachActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("其他").setIndicator("其他").setContent(new Intent(this, CustOtherActivity.class)));
			mTabHost.addTab(mTabHost.newTabSpec("影像").setIndicator("影像").setContent(new Intent(this, CustPhotoActivity.class)));
			mTabHost.getTabWidget().getChildAt(6).setClickable(false);
			mTabHost.getTabWidget().getChildAt(6).setFocusable(false);
		}
		mTabHost.setCurrentTab(0);
		mTabHost.getTabWidget().getChildAt(0).setClickable(false);
		mTabHost.getTabWidget().getChildAt(0).setFocusable(false);
		mTabHost.getTabWidget().getChildAt(1).setClickable(false);
		mTabHost.getTabWidget().getChildAt(1).setFocusable(false);
		mTabHost.getTabWidget().getChildAt(2).setClickable(false);
		mTabHost.getTabWidget().getChildAt(2).setFocusable(false);
		mTabHost.getTabWidget().getChildAt(3).setClickable(false);
		mTabHost.getTabWidget().getChildAt(3).setFocusable(false);
		mTabHost.getTabWidget().getChildAt(4).setClickable(false);
		mTabHost.getTabWidget().getChildAt(4).setFocusable(false);
		mTabHost.getTabWidget().getChildAt(5).setClickable(false);
		mTabHost.getTabWidget().getChildAt(5).setFocusable(false);
		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++)
		{
			TextView textView = (TextView)mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			textView.setTextSize(30);
			textView.setGravity(Gravity.CENTER);
			textView.getLayoutParams().height = LayoutParams.MATCH_PARENT;
			textView.getLayoutParams().width = LayoutParams.MATCH_PARENT;
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

package com.mega.abcregister;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mega.myview.HintEdittext;
import com.mega.myview.MyNote;
import com.mega.tools.FileTools;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class CustPreviewActivity extends Activity
{

	private Button back;
	private Button next;
	private MyNote detail;
	private RadioButton rbYes1;
	private RadioButton rbNo1;
	private RadioButton rbYes2;
	private RadioButton rbNo2;
	private RadioButton rbYes3;
	private RadioButton rbNo3;
	private Spinner spLayout;
	private HintEdittext etAppNum;//申请编号输入框
	private HintEdittext etCardName;//卡种简称输入框
	private HintEdittext etCardCode;//卡种代码输入框
	private HintEdittext etHYName;//会员卡名称
	private HintEdittext etHYNum;//会员卡编号
	private String appnum;//申请编号
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_preview);
		initComponent();
		addListener();
	}
	
	private void initComponent()
	{
//		System.out.println(MyConstants.spf.getString(MyConstants.APP_NUM_FLAG, ""));
		CustMsgActivity.preView = new StringBuffer();
		MyConstants.clearPreference();
		etAppNum = (HintEdittext) findViewById(R.id.et1);
		etAppNum.init("*申请编号",Color.DKGRAY,25,5,11);
		etCardCode = (HintEdittext) findViewById(R.id.et3);
		etCardCode.init("*品牌类型", Color.DKGRAY,25, 5, 11);
		etCardName = (HintEdittext) findViewById(R.id.et2);
		etCardName.init("*卡种简称", Color.DKGRAY,25, 5, 11);
		etHYName = (HintEdittext) findViewById(R.id.et4);
		etHYName.init("会员卡名称", Color.DKGRAY,25, 5, 11);
		etHYNum = (HintEdittext) findViewById(R.id.et5);
		etHYNum.init("会员卡编号", Color.DKGRAY,25, 5, 11);
		back = (Button) findViewById(R.id.bt_back);
		next = (Button) findViewById(R.id.bt_next);
		detail = (MyNote) findViewById(R.id.tv_card_intro);
		rbYes1 = (RadioButton) findViewById(R.id.rbYes);
		rbNo1 = (RadioButton) findViewById(R.id.rbNo);
		rbYes2 = (RadioButton) findViewById(R.id.rbSQ);
		rbNo2 = (RadioButton) findViewById(R.id.rbBSQ);
		rbYes3 = (RadioButton) findViewById(R.id.rbIs);
		rbNo3 = (RadioButton) findViewById(R.id.rbIsNot);
		spLayout = (Spinner) findViewById(R.id.sp_layout);
		detail.setText(Html.fromHtml(CustMsgActivity.card.getCardDetails()));
		String jg = CustMsgActivity.card.getGroup();
		String bz = CustMsgActivity.card.getCardBz(); 
		String dj = CustMsgActivity.card.getCardGrade();
		if (jg.equals("0"))
		{
			jg = "VISA";
			if (bz.equals("840"))
			{
				bz="双币种(美元)";
			}
			else if(bz.equals("978")){
				bz="双币种(欧元)";
			}
			else {
				bz="人民币";
			}
		}
		else if(jg.equals("1")){
			jg = "万事达";
			if (bz.equals("840"))
			{
				bz="双币种(美元)";
			}
			else if(bz.equals("978")){
				bz="双币种(欧元)";
			}
			else {
				bz="人民币";
			}
		}
		else {
			jg="银联";
			bz="人民币";
		}
		if (dj.equals("0"))
		{
			dj="金卡";
		}
		else if (dj.equals("1")) {
			dj="普卡";
		}
		else if (dj.equals("2")) {
			dj="白金卡";
		}
		else if (dj.equals("3")) {
			dj="钻石卡";
		}
		else {
			jg="";
			bz="";
			dj="银联公务金卡";
		}
		etCardCode.setText(jg+bz+dj);
		etCardName.setText(CustMsgActivity.card.getName());
		appnum = MyConstants.getAppNum();
		etAppNum.setText(appnum.trim());
		MyConstants.editor.putString(MyConstants.APP_NUM, appnum);
		MyConstants.editor.putString(MyConstants.CARD_NAME, CustMsgActivity.card.getName());
		MyConstants.editor.commit();
//		if (!MyConstants.spf.getString(MyConstants.CARD_NAME, "").contains("员工"))
//		{
//			rbYes3.setEnabled(false);
//			rbNo3.setEnabled(false);
//		}
		ArrayAdapter<String> layoutAdapter;
		if (CustMsgActivity.card.getCardLayout().length==0)
		{
			layoutAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, new String[]{"无版面信息"});
		}
		else {
			layoutAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, CustMsgActivity.card.getCardLayout());
		}
		layoutAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spLayout.setAdapter(layoutAdapter);
		if (CustMsgActivity.card.getCardGrade().equals("4")||CustMsgActivity.card.getStudentCard().equals("0"))
		{
			rbYes2.setEnabled(false);
			rbNo2.setEnabled(false);
		}
		if (CustMsgActivity.card.getStudentCard().equals("0"))
		{
			rbYes1.setEnabled(false);
			rbNo1.setEnabled(false);
		}
//		rbYes2.setEnabled(false);
//		rbNo2.setEnabled(false);
	}
	
	
	private void getConfig()
	{
		MyConstants.editor.putString(MyConstants.NET_NODE_NO, CustMsgActivity.map.get("aBranch"));
		MyConstants.editor.putString(MyConstants.PROD_KIND, CustMsgActivity.card.getPROD_KIND());
		MyConstants.editor.putString(MyConstants.CARD_MARK, CustMsgActivity.card.getGroup());
		MyConstants.editor.putString(MyConstants.CARD_KIND, CustMsgActivity.card.getCardGrade());
		MyConstants.editor.putString(MyConstants.DOUBLE_CUR, CustMsgActivity.card.getDouble_Cur());
		MyConstants.editor.putString(MyConstants.COLOR_CARD, CustMsgActivity.card.getColorCard());
		MyConstants.editor.putString(MyConstants.APP_CUR, CustMsgActivity.card.getCardBz());
		MyConstants.editor.putString(MyConstants.CARD_SINGLE_CODE, CustMsgActivity.card.getCardSingleCode());
		MyConstants.editor.putString(MyConstants.EXAM_NODE_NO, CustMsgActivity.map.get("aBranch"));
		MyConstants.editor.putString(MyConstants.PREP2, CustMsgActivity.map.get("aOperatorId"));
		MyConstants.editor.commit();
//		MyConstants.editor.putString(MyConstants.INPUT_OPERATOR, CardActivity.map.get("aOperatorId"));
	}
	
	
	private void addListener()
	{
		back.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				File file = new File(MyConstants.IMAGE_PATH+MyConstants.spf.getString(MyConstants.APP_NUM, "")+"/");
				FileTools.RecursionDeleteFile(file);
				MyApplication.getInstance().getPresentActivity().finish();
			}
		});
		
		next.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (rbYes1.isChecked()==true)
				{
					MyConstants.editor.putString(MyConstants.ABCCARD, "1");
				}
				else {
					MyConstants.editor.putString(MyConstants.ABCCARD, "0");
				}
				if (rbYes2.isChecked()==true)
				{
					MyConstants.editor.putBoolean(MyConstants.OTHER_CARD, true);
				}
				else {
					MyConstants.editor.putBoolean(MyConstants.OTHER_CARD, false);
				}
				if (rbYes3.isSelected())
				{
					MyConstants.editor.putString(MyConstants.EMPLOYEE, "1");
				}
				else {
					MyConstants.editor.putString(MyConstants.EMPLOYEE, "0");
				}
				if (!spLayout.getSelectedItem().toString().trim().equals("无版面信息"))
				{
					MyConstants.editor.putString(MyConstants.LAYOUT_FLAG, String.valueOf(spLayout.getSelectedItemId()+1));
				}
				getConfig();
				MyConstants.editor.commit();
				CustMsgActivity.mTabHost.setCurrentTab(1);
			}
		});
	}
	
}

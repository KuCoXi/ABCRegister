package com.mega.abcregister;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mega.myview.CustomSinnper;
import com.mega.myview.CustomSinnper.OnItemSeletedListener;
import com.mega.myview.HintEdittext;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class CustOtherActivity extends Activity
{
	private TextView tvMoney;
	private Button nextButton;//下一步
	private Button lastButton;//上一步
	private CustomSinnper spAcceptRec;//是否接受推荐
	private CustomSinnper spGetCardWay;//领卡方式
	private CustomSinnper spCardMailAddr;//邮寄地址
	private CustomSinnper spPaperMailAddr;//纸质账单邮寄地址
	private CustomSinnper spCheckSentWay;//对账单发送方式
	private CustomSinnper spRepayWay;//约定还款方式
	private CustomSinnper spSetMoney;//固定转存金额
	private HintEdittext email;//电子邮箱
	private HintEdittext cardnum;//还款卡号
	private HintEdittext appcode;//申请代码
	private HintEdittext opcode;//营销人员代码
	private HintEdittext deptcode;
	private HintEdittext deptname;
	private String cardgrade = MyConstants.spf.getString(MyConstants.CARD_KIND, "");//产品等级
	private String cardname = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//产品名称
	private String lowlevel = CustMsgActivity.card.getLowLevel();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_other);
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		tvMoney = (TextView) findViewById(R.id.tvMoney);
		spSetMoney = (CustomSinnper) findViewById(R.id.sp_set_money);
		nextButton = (Button) findViewById(R.id.bt_next);
		lastButton = (Button) findViewById(R.id.bt_last);
		spAcceptRec = (CustomSinnper) findViewById(R.id.sp_accept_recommend);
		spCardMailAddr = (CustomSinnper) findViewById(R.id.sp_cardpswd_addr);
		spCheckSentWay = (CustomSinnper) findViewById(R.id.sp_check_sentway);
		spGetCardWay = (CustomSinnper) findViewById(R.id.sp_getcard_way);
		spPaperMailAddr = (CustomSinnper) findViewById(R.id.sp_paper_sentaddr);
		spRepayWay = (CustomSinnper) findViewById(R.id.sp_repay_way);
		email = (HintEdittext) findViewById(R.id.et_email);
		email.init("电子邮箱", Color.GRAY, 25, 5, 11);
		cardnum = (HintEdittext) findViewById(R.id.et_card_num);
		cardnum.init("约定还款卡号", Color.GRAY, 25, 5, 11);
		cardnum.setEnabled(false);
		cardnum.setHint("16或19位卡号");
		appcode = (HintEdittext) findViewById(R.id.et_app_code);
		appcode.init("*申请代码", Color.GRAY, 25, 5, 11);
		opcode = (HintEdittext) findViewById(R.id.et_op_code);
		opcode.init("营销人员代码", Color.GRAY, 25, 5, 11);
		deptcode = (HintEdittext) findViewById(R.id.et_dept_code);
		deptcode.init("*受理机构代码", Color.GRAY, 25, 5, 11);
		deptname = (HintEdittext) findViewById(R.id.et_dept_name);
		deptname.init("*受理机构名称", Color.GRAY, 25, 5, 11);
		deptcode.setText(CustMsgActivity.map.get("aBranch"));
		appcode.setText(MyConstants.spf.getString(MyConstants.APP_NUM, "").trim());
//		opcode.setText(CustMsgActivity.map.get("aOperatorId"));
		deptname.setText(CustMsgActivity.map.get("aBranchName"));
		ArrayAdapter<CharSequence> moneyAdapter = ArrayAdapter.createFromResource(this, R.array.money_list, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> acceptAdapter = ArrayAdapter.createFromResource(this, R.array.tof_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> mailAdapter = ArrayAdapter.createFromResource(this, R.array.addr_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> sentWayAdapter = ArrayAdapter.createFromResource(this, R.array.sentway_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> repayAdapter = ArrayAdapter.createFromResource(this, R.array.repay_list_entries, android.R.layout.simple_list_item_1);
		ArrayAdapter<CharSequence> getCardAdapter = ArrayAdapter.createFromResource(this, R.array.getcard_list_entries, android.R.layout.simple_list_item_1);
		spAcceptRec.setAdapter(acceptAdapter);
		spCardMailAddr.setAdapter(mailAdapter);
		spCheckSentWay.setAdapter(sentWayAdapter);
		spGetCardWay.setAdapter(getCardAdapter);
		spRepayWay.setAdapter(repayAdapter);
		spPaperMailAddr.setAdapter(mailAdapter);
		spSetMoney.setAdapter(moneyAdapter);
		if (cardname.contains("易卡"))
		{
			spRepayWay.setText("全额还款");
			spRepayWay.setEnabled(false);
			tvMoney.setVisibility(View.VISIBLE);
			spSetMoney.setVisibility(View.VISIBLE);
			cardnum.setEnabled(true);
		}
		else {
			tvMoney.setVisibility(View.INVISIBLE);
			spSetMoney.setVisibility(View.INVISIBLE);
		}
		if (!cardgrade.equals("0")||lowlevel.equals("0"))
		{
			spAcceptRec.setText("否");
			spAcceptRec.setEnabled(false);
		}
		
		if (cardname.contains("西藏中央预算"))
		{
			spRepayWay.setText("无");
			spRepayWay.setEnabled(false);
			cardnum.setClickable(false);
			cardnum.setEnabled(false);
		}
	}
	
	private void addListener()
	{
		spAcceptRec.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spCardMailAddr.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spCheckSentWay.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spGetCardWay.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spPaperMailAddr.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		spRepayWay.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Object object = parent.getItemAtPosition(position);
				String repayway = object.toString();
				if (!repayway.equals("请选择")&&!repayway.equals("无"))
				{
					cardnum.setEnabled(true);
				}
				else {
					cardnum.setEnabled(false);
				}
			}
		});
		spSetMoney.setOnItemSeletedListener(new OnItemSeletedListener()
		{
			
			@Override
			public void onItemSeleted(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				
			}
		});
		nextButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String operacode = opcode.getText().toString().trim();
				String acceptrec = spAcceptRec.getText().toString().trim();
				if (acceptrec.equals("是"))
				{
					acceptrec = "1";
				}
				else if (acceptrec.equals("否")) {
					acceptrec = "0";
				}
				String getway = spGetCardWay.getText().toString().trim();
				if (getway.equals("网点自行领取"))
				{
					getway = "0";
				}
				else if (getway.equals("邮寄到账单地址")) {
					getway = "3";
				}
				String cardmailaddr = spCardMailAddr.getText().toString().trim();
				if (cardmailaddr.equals("现住址"))
				{
					cardmailaddr = "0";
				}
				else if (cardmailaddr.equals("单位地址")) {
					cardmailaddr = "1";
				}
				String checkway = spCheckSentWay.getText().toString().trim();
				if (checkway.equals("纸质帐单"))
				{
					checkway = "0";
				}
				else if (checkway.equals("电子帐单"))
				{
					checkway = "1";
				}
				else if (checkway.equals("纸质及电子帐单"))
				{
					checkway = "2";
				}
				else if (checkway.equals("彩信账单"))
				{
					checkway = "3";
				}
				else if (checkway.equals("纸质及彩信账单"))
				{
					checkway = "4";
				}
				String backway = spRepayWay.getText().toString().trim();
				if (backway.equals("最低还款额")) {
					backway = "1";
				}
				else if (backway.equals("全额还款")) {
					backway = "2";
				}
				String backcardnum = "";
				if (backway.equals("无"))
				{
					backway = " ";
					backcardnum = " ";
				}
				else if(backway.equals("1")||backway.equals("2")){
					backcardnum = cardnum.getText().toString().trim();
				}
				String emailaddr = email.getText().toString().trim();
				String money = "";
				if (cardname.contains("易卡"))
				{
					money = spSetMoney.getText().toString().trim();
					if (money.equals("0"))
					{
						money = "A";
					}
					else if (money.equals("500")) {
						money = "B";
					}
					else if (money.equals("1000")) {
						money = "C";
					}
					else {
						money = "D";
					}
				}
				
				StringBuffer sBuffer = new StringBuffer();
				int count = 0;
				if (acceptrec.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、是否接受推荐未选择\n\n");
				}
				if (getway.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、领卡方式未选择\n\n");
				}
				if (cardmailaddr.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、主卡卡片及密码函邮寄地址未选择\n\n");
				}
				if (checkway.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、对账单发送方式未选择\n\n");
				}
				if (backway.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、约定还款方式未选择\n\n");
				}
				if (cardname.contains("易卡")&&money.equals("请选择"))
				{
					count++;
					sBuffer.append(count+"、固定转存金额未选择\n\n");
				}
				if (!operacode.equals("")&&!MyConstants.numberCheck(operacode, 10))
				{
					count++;
					sBuffer.append(count+"、营销人员编号格式错误或未填写\n\n");
				}
				if ((checkway.equals("1")||checkway.equals("2")||!emailaddr.equals(""))&&!MyConstants.checkEmailAddr(emailaddr))
				{
					count++;
					sBuffer.append(count+"、电子邮箱格式错误或未填写\n\n");
				}
				if ((backway.equals("1")||backway.equals("2"))&&((backcardnum.length()!=16&&backcardnum.length()!=19)||!MyConstants.baseCheck(backcardnum, 19)))
				{
					count++;
					sBuffer.append(count+"、还款卡号格式错误或未填写\n\n");
				}
				if (count>0)
				{
					UITools.getTools().getErrDialog(count, sBuffer.toString()).show();
				}
				else {
					MyConstants.editor.putString(MyConstants.NORMAL_CARD, acceptrec);
					MyConstants.editor.putString(MyConstants.GET_CARD_MODE, getway);
					MyConstants.editor.putString(MyConstants.POST_ADDR, cardmailaddr);
					MyConstants.editor.putString(MyConstants.PRE4, checkway);
					MyConstants.editor.putString(MyConstants.PRE2, money);
					MyConstants.editor.putString(MyConstants.PREP2, operacode);
					MyConstants.editor.putString(MyConstants.REPAY_MARK, backway);
					MyConstants.editor.putString(MyConstants.REPAY_CARD, backcardnum);
					MyConstants.editor.putString(MyConstants.EMAIL, emailaddr);
					MyConstants.editor.commit();
					if (CustMsgActivity.card.getStudentCard().equals("1"))
					{
						CustMsgActivity.mTabHost.setCurrentTab(6);
					}
					else {
						CustMsgActivity.mTabHost.setCurrentTab(5);
					}
					
				}
			}
		});
		lastButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (MyConstants.spf.getBoolean(MyConstants.OTHER_CARD, false))
				{
					CustMsgActivity.mTabHost.setCurrentTab(4);
				}
				else {
					CustMsgActivity.mTabHost.setCurrentTab(3);
				}
				
			}

		});
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
	    {     
//			if (MyConstants.spf.getBoolean(MyConstants.OTHER_CARD, false))
//			{
//				CustMsgActivity.mTabHost.setCurrentTab(4);
//			}
//			else {
//				CustMsgActivity.mTabHost.setCurrentTab(3);
//			}
			return true;
	    }
		return super.dispatchKeyEvent(event);
	}

}

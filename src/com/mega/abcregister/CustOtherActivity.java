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
	private Button nextButton;//��һ��
	private Button lastButton;//��һ��
	private CustomSinnper spAcceptRec;//�Ƿ�����Ƽ�
	private CustomSinnper spGetCardWay;//�쿨��ʽ
	private CustomSinnper spCardMailAddr;//�ʼĵ�ַ
	private CustomSinnper spPaperMailAddr;//ֽ���˵��ʼĵ�ַ
	private CustomSinnper spCheckSentWay;//���˵����ͷ�ʽ
	private CustomSinnper spRepayWay;//Լ�����ʽ
	private CustomSinnper spSetMoney;//�̶�ת����
	private HintEdittext email;//��������
	private HintEdittext cardnum;//�����
	private HintEdittext appcode;//�������
	private HintEdittext opcode;//Ӫ����Ա����
	private HintEdittext deptcode;
	private HintEdittext deptname;
	private String cardgrade = MyConstants.spf.getString(MyConstants.CARD_KIND, "");//��Ʒ�ȼ�
	private String cardname = MyConstants.spf.getString(MyConstants.CARD_NAME, "");//��Ʒ����
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
		email.init("��������", Color.GRAY, 25, 5, 11);
		cardnum = (HintEdittext) findViewById(R.id.et_card_num);
		cardnum.init("Լ�������", Color.GRAY, 25, 5, 11);
		cardnum.setEnabled(false);
		cardnum.setHint("16��19λ����");
		appcode = (HintEdittext) findViewById(R.id.et_app_code);
		appcode.init("*�������", Color.GRAY, 25, 5, 11);
		opcode = (HintEdittext) findViewById(R.id.et_op_code);
		opcode.init("Ӫ����Ա����", Color.GRAY, 25, 5, 11);
		deptcode = (HintEdittext) findViewById(R.id.et_dept_code);
		deptcode.init("*�����������", Color.GRAY, 25, 5, 11);
		deptname = (HintEdittext) findViewById(R.id.et_dept_name);
		deptname.init("*�����������", Color.GRAY, 25, 5, 11);
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
		if (cardname.contains("�׿�"))
		{
			spRepayWay.setText("ȫ���");
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
			spAcceptRec.setText("��");
			spAcceptRec.setEnabled(false);
		}
		
		if (cardname.contains("��������Ԥ��"))
		{
			spRepayWay.setText("��");
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
				if (!repayway.equals("��ѡ��")&&!repayway.equals("��"))
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
				if (acceptrec.equals("��"))
				{
					acceptrec = "1";
				}
				else if (acceptrec.equals("��")) {
					acceptrec = "0";
				}
				String getway = spGetCardWay.getText().toString().trim();
				if (getway.equals("����������ȡ"))
				{
					getway = "0";
				}
				else if (getway.equals("�ʼĵ��˵���ַ")) {
					getway = "3";
				}
				String cardmailaddr = spCardMailAddr.getText().toString().trim();
				if (cardmailaddr.equals("��סַ"))
				{
					cardmailaddr = "0";
				}
				else if (cardmailaddr.equals("��λ��ַ")) {
					cardmailaddr = "1";
				}
				String checkway = spCheckSentWay.getText().toString().trim();
				if (checkway.equals("ֽ���ʵ�"))
				{
					checkway = "0";
				}
				else if (checkway.equals("�����ʵ�"))
				{
					checkway = "1";
				}
				else if (checkway.equals("ֽ�ʼ������ʵ�"))
				{
					checkway = "2";
				}
				else if (checkway.equals("�����˵�"))
				{
					checkway = "3";
				}
				else if (checkway.equals("ֽ�ʼ������˵�"))
				{
					checkway = "4";
				}
				String backway = spRepayWay.getText().toString().trim();
				if (backway.equals("��ͻ����")) {
					backway = "1";
				}
				else if (backway.equals("ȫ���")) {
					backway = "2";
				}
				String backcardnum = "";
				if (backway.equals("��"))
				{
					backway = " ";
					backcardnum = " ";
				}
				else if(backway.equals("1")||backway.equals("2")){
					backcardnum = cardnum.getText().toString().trim();
				}
				String emailaddr = email.getText().toString().trim();
				String money = "";
				if (cardname.contains("�׿�"))
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
				if (acceptrec.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"���Ƿ�����Ƽ�δѡ��\n\n");
				}
				if (getway.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"���쿨��ʽδѡ��\n\n");
				}
				if (cardmailaddr.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"��������Ƭ�����뺯�ʼĵ�ַδѡ��\n\n");
				}
				if (checkway.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"�����˵����ͷ�ʽδѡ��\n\n");
				}
				if (backway.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"��Լ�����ʽδѡ��\n\n");
				}
				if (cardname.contains("�׿�")&&money.equals("��ѡ��"))
				{
					count++;
					sBuffer.append(count+"���̶�ת����δѡ��\n\n");
				}
				if (!operacode.equals("")&&!MyConstants.numberCheck(operacode, 10))
				{
					count++;
					sBuffer.append(count+"��Ӫ����Ա��Ÿ�ʽ�����δ��д\n\n");
				}
				if ((checkway.equals("1")||checkway.equals("2")||!emailaddr.equals(""))&&!MyConstants.checkEmailAddr(emailaddr))
				{
					count++;
					sBuffer.append(count+"�����������ʽ�����δ��д\n\n");
				}
				if ((backway.equals("1")||backway.equals("2"))&&((backcardnum.length()!=16&&backcardnum.length()!=19)||!MyConstants.baseCheck(backcardnum, 19)))
				{
					count++;
					sBuffer.append(count+"������Ÿ�ʽ�����δ��д\n\n");
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

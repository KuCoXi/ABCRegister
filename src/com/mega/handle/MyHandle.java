package com.mega.handle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.mega.abcregister.PrintActivity;
import com.mega.print.PrintUtils;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.sprt.bluetooth.android.BluetoothPrinter;
import com.sprt.bluetooth.android.PrinterType;

/**
 * **********************************************************
 *  ����ժҪ	��<p>
 *
 *  ����	��kcx
 *  ����ʱ��	��2013-1-28 ����3:54:29 
 *  ��ǰ�汾�ţ�v1.0
 *  ��ʷ��¼	:
 *  	����	: 2013-1-28 ����3:54:29 	�޸��ˣ�kcx
 *  	����	:
 ***********************************************************
 */
public class MyHandle extends Handler
{
	private View view;//��ͼ
	private ProgressDialog progressDialog;//���ȶԻ���
//	private Bitmap bm;// ͼƬ��ԴBitmap
	private Intent intent;

	public MyHandle(View view)
	{
		this.view = view;
	}
	public MyHandle(ProgressDialog progressDialog, View view)
	{
		this.progressDialog = progressDialog;
		this.view = view;
	}
	
	public MyHandle(ProgressDialog progressDialog)
	{
		this.progressDialog = progressDialog;
	}
	
	public MyHandle(ProgressDialog progressDialog,Intent intent)
	{
		this.progressDialog = progressDialog;
		this.intent = intent;
	}
	
	@Override
	public void handleMessage(Message msg)
	{
		super.handleMessage(msg);
		switch (msg.what)
		{
		case BluetoothPrinter.Handler_Connect_Connecting:
			break;
		case BluetoothPrinter.Handler_Connect_Success:
			progressDialog.dismiss();
			StringBuffer sBuffer = new StringBuffer();
			String name = MyConstants.spf.getString(MyConstants.CUST_NAME, "").trim();
			String cardtype = MyConstants.spf.getString(MyConstants.CERT_TYPE, "").trim();
			String app_num = MyConstants.spf.getString(MyConstants.APP_NUM, "").trim();
			String cardname = MyConstants.spf.getString(MyConstants.CARD_NAME, "").trim();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��");
			String cardnum = MyConstants.spf.getString(MyConstants.CERT_NO, "").trim();
			String deptaddr = MyConstants.spf.getString(MyConstants.COMP_ADDR, "").trim();
			String depttel1 = MyConstants.spf.getString(MyConstants.COMP_ZONE_NO, "").trim();
			String depttel2 = MyConstants.spf.getString(MyConstants.COMP_PHONE, "").trim();
			String homeaddr = MyConstants.spf.getString(MyConstants.PRE_ADDR, "").trim();
			String hometel1 = MyConstants.spf.getString(MyConstants.PRE_ZONE_NO, "").trim();
			String hometel2 = MyConstants.spf.getString(MyConstants.PRE_PHONE, "").trim();
			String mobile = MyConstants.spf.getString(MyConstants.PRE_MOBILE, "").trim();
			if (depttel1.length()==3&&!depttel1.startsWith("0"))
			{
				depttel1="0"+depttel1;
			}
			if (hometel1.length()==3&&!hometel1.startsWith("0"))
			{
				hometel1="0"+hometel1;
			}
			if (cardtype.equals("I"))
			{
				cardtype = "���֤";
			}
			else if (cardtype.equals("P")) {
				cardtype = "����";
			}
			else {
				cardtype = MyConstants.spf.getString(MyConstants.CERT_NAME, "default").trim();
			}
			sBuffer.append("               ����ȷ�ϵ�\n")
			.append("������������"+name+"\n")
			.append("֤�����ͣ�"+cardtype+"\n")
			.append("֤�����룺"+cardnum+"\n")
			.append("�����ţ�"+app_num+"\n")
			.append("���뿨�֣�"+cardname+"\n")
			.append("�ֻ���"+mobile+"\n")
			.append("��ӡ���ڼ�ʱ�䣺"+sdf.format(new Date())+"\n")
			.append("�ͻ�ǩ���� \n")
			.append("                         ��    ��    ��\n")
			.append("��ճ��ֽ�����ÿ��³̲������������ǩ����\n")
			.append("----------------------------------------\n\n")
			.append("                �ͻ���ִ\n")
			.append("�����ţ�"+app_num+"\n")
			.append("������������"+name+"\n")
			.append("���뿨�֣�"+cardname+"\n")
			.append("��λ��ַ��"+deptaddr+"\n")
			.append("��λ�绰��"+depttel1+"-"+depttel2+"\n")
			.append("��סַ��"+homeaddr+"\n")
			.append("סլ�绰��"+hometel1+"-"+hometel2+"\n")
			.append("�ֻ���"+mobile+"\n")
			.append("��ӡ���ڼ�ʱ�䣺"+sdf.format(new Date())+"\n\n");
			
			if (PrintActivity.mPrinter.getCurrentPrintType() == PrinterType.T9)
			{
				PrintUtils.printTextToT9(PrintActivity.mPrinter, sBuffer.toString());
			} else
			{
				PrintUtils.printTextToOther(PrintActivity.mPrinter, sBuffer.toString());
			}
			MyApplication.getInstance().getPresentActivity().finish();
			UITools.getTools().showToast("�ύ�ɹ���", true, UITools.GOOD);
			break;
		case BluetoothPrinter.Handler_Connect_Failed:
			progressDialog.dismiss();
			UITools.getTools().showToast("����ʧ��", true, UITools.BAD);
			break;
		case BluetoothPrinter.Handler_Connect_Closed:
			progressDialog.dismiss();
			UITools.getTools().showToast("���ӶϿ�", true, UITools.SAD);
			break;
		case MyConstants.COMPRESS_DONE:
			progressDialog.dismiss();
			MyApplication.getInstance().getPresentActivity().startActivity(intent);
			break;
		case MyConstants.ADD_DOWN:
			progressDialog.dismiss();
			MyApplication.getInstance().getPresentActivity().finish();
			break;
		case MyConstants.TIME_END:
			((TextView)view).setText("����ʱ���ѽ���");
			UITools.getTools().getWarnDialog("����ʱ���ѽ�����������������������»�ȡ��Ȩ").show();
			break;
		case MyConstants.END_TIME_LEFT:
			Bundle bundle = msg.getData();
			String str = bundle.getString("etime");
			((TextView)view).setText("���빤��ʱ���������\n"+str);
			break;
		case MyConstants.START_TIME_LEFT:
			Bundle bundle1 = msg.getData();
			String str1 = bundle1.getString("stime");
			((TextView)view).setText("���빤��ʱ�俪ʼ����\n"+str1);
			UITools.getTools().getWarnDialog("����ʱ��δ�������Ժ�����").show();
			break;
		default:
			break;
		}
		

	}

}

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
 *  内容摘要	：<p>
 *
 *  作者	：kcx
 *  创建时间	：2013-1-28 下午3:54:29 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2013-1-28 下午3:54:29 	修改人：kcx
 *  	描述	:
 ***********************************************************
 */
public class MyHandle extends Handler
{
	private View view;//视图
	private ProgressDialog progressDialog;//进度对话框
//	private Bitmap bm;// 图片资源Bitmap
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
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
				cardtype = "身份证";
			}
			else if (cardtype.equals("P")) {
				cardtype = "护照";
			}
			else {
				cardtype = MyConstants.spf.getString(MyConstants.CERT_NAME, "default").trim();
			}
			sBuffer.append("               申请确认单\n")
			.append("申请人姓名："+name+"\n")
			.append("证件类型："+cardtype+"\n")
			.append("证件号码："+cardnum+"\n")
			.append("申请编号："+app_num+"\n")
			.append("申请卡种："+cardname+"\n")
			.append("手机："+mobile+"\n")
			.append("打印日期及时间："+sdf.format(new Date())+"\n")
			.append("客户签名： \n")
			.append("                         年    月    日\n")
			.append("请粘于纸质信用卡章程并请申请人骑缝签名！\n")
			.append("----------------------------------------\n\n")
			.append("                客户回执\n")
			.append("申请编号："+app_num+"\n")
			.append("申请人姓名："+name+"\n")
			.append("申请卡种："+cardname+"\n")
			.append("单位地址："+deptaddr+"\n")
			.append("单位电话："+depttel1+"-"+depttel2+"\n")
			.append("现住址："+homeaddr+"\n")
			.append("住宅电话："+hometel1+"-"+hometel2+"\n")
			.append("手机："+mobile+"\n")
			.append("打印日期及时间："+sdf.format(new Date())+"\n\n");
			
			if (PrintActivity.mPrinter.getCurrentPrintType() == PrinterType.T9)
			{
				PrintUtils.printTextToT9(PrintActivity.mPrinter, sBuffer.toString());
			} else
			{
				PrintUtils.printTextToOther(PrintActivity.mPrinter, sBuffer.toString());
			}
			MyApplication.getInstance().getPresentActivity().finish();
			UITools.getTools().showToast("提交成功！", true, UITools.GOOD);
			break;
		case BluetoothPrinter.Handler_Connect_Failed:
			progressDialog.dismiss();
			UITools.getTools().showToast("连接失败", true, UITools.BAD);
			break;
		case BluetoothPrinter.Handler_Connect_Closed:
			progressDialog.dismiss();
			UITools.getTools().showToast("连接断开", true, UITools.SAD);
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
			((TextView)view).setText("工作时间已结束");
			UITools.getTools().getWarnDialog("工作时间已结束，如需继续工作，请重新获取授权").show();
			break;
		case MyConstants.END_TIME_LEFT:
			Bundle bundle = msg.getData();
			String str = bundle.getString("etime");
			((TextView)view).setText("距离工作时间结束还有\n"+str);
			break;
		case MyConstants.START_TIME_LEFT:
			Bundle bundle1 = msg.getData();
			String str1 = bundle1.getString("stime");
			((TextView)view).setText("距离工作时间开始还有\n"+str1);
			UITools.getTools().getWarnDialog("工作时间未到，请稍后再试").show();
			break;
		default:
			break;
		}
		

	}

}

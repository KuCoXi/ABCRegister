package com.mega.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mega.abcregister.AddActivity;
import com.mega.abcregister.DeleteActivity;
import com.mega.abcregister.MsgPreViewActivity;
import com.mega.abcregister.PrintActivity;
import com.mega.abcregister.R;
import com.mega.pack.CardMsg;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MsgListAdapter extends SimpleAdapter
{

	private int resource;
	private Context context;
	private ListView list;
	public MsgListAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	{
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resource = resource;
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		final TextView tvName;
		final TextView tvIdNum;
		final TextView tvCardName;
		final TextView tvAppNum;
		ImageView btBuJian,btBuDa,btDelete;
		ViewHolder vh;
		if (convertView==null)
		{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, null);
			tvName = (TextView) convertView.findViewById(R.id.tvName);
			tvIdNum = (TextView) convertView.findViewById(R.id.tvIdNum);
			tvCardName = (TextView) convertView.findViewById(R.id.tvCardName);
			tvAppNum = (TextView) convertView.findViewById(R.id.tvAppNum);
			btBuJian = (ImageView) convertView.findViewById(R.id.ivBuJian);
			btBuDa = (ImageView) convertView.findViewById(R.id.ivBuDa);
			btDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
			vh = new ViewHolder();
			vh.tvAppNum = tvAppNum;
			vh.tvCardName = tvCardName;
			vh.tvIdNum = tvIdNum;
			vh.tvName = tvName;
			vh.btBuDa = btBuDa;
			vh.btBuJian = btBuJian;
			vh.btDelete = btDelete;
			convertView.setTag(vh);
		}
		else {
			vh = (ViewHolder)convertView.getTag();
			tvAppNum = vh.tvAppNum;
			tvCardName = vh.tvCardName;
			tvIdNum = vh.tvIdNum;
			tvName = vh.tvName;
			btBuDa = vh.btBuDa;
			btBuJian = vh.btBuJian;
			btDelete = vh.btDelete;
		}
		Map<String, Object> map = (Map<String, Object>)getItem(position);
		final String name = (String) map.get("name");
		final String idnum = (String) map.get("idnum");
		final String cardname = (String) map.get("cardname");
		final String appnum = ((String) map.get("appnum")).trim();
		tvAppNum.setText("�����ţ�"+appnum);
		tvCardName.setText("��Ƭ���ƣ�"+cardname);
		tvIdNum.setText("֤�����룺"+idnum);
		tvName.setText("�ͻ�������"+name);
		btDelete.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				AlertDialog.Builder builder = new AlertDialog.Builder(MyApplication.getInstance().getPresentActivity());
//				builder.setTitle("ѯ��").setMessage("ȷ��ɾ��������¼��").setPositiveButton("ȡ��", new DialogInterface.OnClickListener()
//				{
//					@Override
//					public void onClick(DialogInterface dialog, int which)
//					{
//						// TODO Auto-generated method stub
//
//					}
//				}).setNeutralButton("ȷ��", new DialogInterface.OnClickListener()
//				{
//
//					@Override
//					public void onClick(DialogInterface dialog, int which)
//					{
//						// TODO Auto-generated method stub
//						boolean res = MyConstants.deleteMsg(MyConstants.WORK_PATH+MyConstants.WORKFILENAME, "11111111", idnum, appnum);
//						if (res)
//						{
//							UITools.getTools().showToast("ɾ���ɹ���", true, UITools.GOOD);
//						}
//						else {
//							UITools.getTools().showToast("ɾ��ʧ�ܣ������ڸ�����¼��ɾ������", true, UITools.BAD);
//						}
//					}
//				}).create().show();
				Context context = MyApplication.getInstance().getPresentActivity();
				Bundle bundle = new Bundle();
				bundle.putString("idnum", idnum);
				bundle.putString("appnum", appnum);
				Intent intent = new Intent(context, DeleteActivity.class);
				intent.putExtras(bundle);
				context.startActivity(intent);
				
			}
		});
		btBuJian.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				CardMsg msg = MyConstants.searchMsg(MyConstants.WORK_PATH+MyConstants.WORKFILENAME, idnum, appnum);
				System.out.println(msg);
				System.out.println("��Ƭ�������ͣ�"+msg.getAPPFORM_TYPE());
				if (msg.getAPPFORM_TYPE().equals("05"))
				{
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("appnum", appnum);
					intent.setClass(context, AddActivity.class);
					intent.putExtras(bundle);
					context.startActivity(intent);
				}
				else {
					UITools.getTools().showToast("��������Ϣ�޷����в�������", true, UITools.SAD);
				}
				
			}
		});
		btBuDa.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				CardMsg msg = MyConstants.searchMsg(MyConstants.WORK_PATH+MyConstants.WORKFILENAME, idnum, appnum);
//				System.out.println("��Ƭ�������ͣ�"+msg.getAPPFORM_TYPE());
//				if (msg.getAPPFORM_TYPE().equals("05"))
//				{
//					MyConstants.editor.putString(MyConstants.CUST_NAME, name);
//					MyConstants.editor.putString(MyConstants.CERT_TYPE, msg.getCERT_TYPE());
//					MyConstants.editor.putString(MyConstants.APP_NUM, appnum);
//					MyConstants.editor.putString(MyConstants.CARD_NAME, cardname);
//					MyConstants.editor.putString(MyConstants.CERT_NO, msg.getCERT_NO());
//					MyConstants.editor.putString(MyConstants.COMP_ADDR, msg.getCOMP_ADDR());
//					MyConstants.editor.putString(MyConstants.COMP_ZONE_NO, msg.getCOMP_ZONE_NO());
//					MyConstants.editor.putString(MyConstants.COMP_PHONE, msg.getCOMP_PHONE());
//					MyConstants.editor.putString(MyConstants.PRE_ADDR, msg.getPRE_ADDR());
//					MyConstants.editor.putString(MyConstants.PRE_ZONE_NO, msg.getPRE_ZONE_NO());
//					MyConstants.editor.putString(MyConstants.PRE_PHONE, msg.getPRE_PHONE());
//					MyConstants.editor.putString(MyConstants.PRE_MOBILE, msg.getPRE_MOBILE());
//					MyConstants.editor.commit();
//					Intent intent = new Intent(context,PrintActivity.class);
//					context.startActivity(intent);
//				}
//				else {
//					UITools.getTools().showToast("��������Ϣ�޷����в������", true, UITools.SAD);
//				}
				UITools.getTools().showToast("�ù�����ȡ����", true, UITools.SAD);
				
			}
		});
		return convertView;
	}
	
	private class ViewHolder
	{
		TextView tvName;
		TextView tvIdNum;
		TextView tvCardName;
		TextView tvAppNum;
		ImageView btBuJian;
		ImageView btBuDa;
		ImageView btDelete;
	}

}

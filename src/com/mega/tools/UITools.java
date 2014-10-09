package com.mega.tools;

import java.lang.reflect.Field;

import android.R.integer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mega.abcregister.R;
import com.mega.abcregister.SettingActivity;

public class UITools
{
	private static UITools tools = null;
	public static final int GOOD = 1;
	public static final int BAD = 2;
	public static final int SAD = 3;
	public static final int NORMAL = 4;
	private UITools()
	{
	}

	public static UITools getTools()
	{
		if (tools == null)
		{
			tools = new UITools();
		}
		return tools;
	}

	/**
	 * 
	 * 函数名称 : getProgressDialog 功能描述 : 参数及返回值说明：
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @return
	 * 
	 *         修改记录： 日期：2013-2-4 下午4:14:21 修改人：kcx 描述 ：
	 * 
	 */
	public ProgressDialog getSpinnerProgress(Context context, String title, String message)
	{
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);
		return progressDialog;
	}

	public ProgressDialog getLineProgress(Context context, String title)
	{
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(title);
		progressDialog.setProgress(0);
		progressDialog.setMax(100);
		progressDialog.setCancelable(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		return progressDialog;
	}

	/**
	 * 
	 *  函数名称 : showToast
	 *  功能描述 :  显示自定义toast
	 *  参数及返回值说明：
	 *  	@param msg   显示的文本信息
	 *  	@param isShort  显示时间是否为短
	 *  	@param type  显示表情类型
	 *
	 *  修改记录：
	 *  	日期：2013-8-5 下午10:50:22	修改人：kcx
	 *  	描述	：
	 *
	 */
	public final void showToast(String msg, boolean isShort, int type)
	{
		Toast toast;
		View toastRoot = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.my_toast, null);
		TextView message = (TextView) toastRoot.findViewById(R.id.message);
		ImageView image = (ImageView) toastRoot.findViewById(R.id.image);
		message.setText(msg);
		switch (type)
		{
		case GOOD:
			image.setBackgroundResource(R.drawable.good);
			break;
		case BAD:
			image.setBackgroundResource(R.drawable.bad);
			break;
		case NORMAL:
			image.setBackgroundResource(R.drawable.normal);
			break;
		case SAD:
			image.setBackgroundResource(R.drawable.sad);
			break;
		default:
			break;
		}
		if (isShort)
		{
//			toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
			toast = new Toast(MyApplication.getInstance());
			toast.setView(toastRoot);
			toast.setDuration(Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.BOTTOM, 0, 0);
			toast.show();
		} else
		{
//			toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_LONG);
			toast = new Toast(MyApplication.getInstance());
			toast.setView(toastRoot);
			toast.setDuration(Toast.LENGTH_LONG);
//			toast.setGravity(Gravity.BOTTOM, 0, 0);
			toast.show();
		}
	}

	public Builder getJumpDialog(Intent intent, String title, String msg)
	{
		final Context context = MyApplication.getInstance().getPresentActivity();
		final Intent myIntent = intent;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);// 对话框
		builder.setTitle(title).setMessage(msg).setPositiveButton("取消", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub

			}
		}).setNeutralButton("确定", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				context.startActivity(myIntent);
			}
		}).create();

		return builder;
	}


	public Builder getErrDialog(int count, String msg)
	{
		Context context = MyApplication.getInstance().getPresentActivity();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("下列" + count + "处输入项未填或填写错误，请检查").setMessage(msg).setCancelable(false).setNegativeButton("确定", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
			}
		}).create();
		return builder;
	}

	public Builder getWarnDialog(String msg)
	{
		Context context = MyApplication.getInstance().getPresentActivity();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("警告").setMessage(msg).setCancelable(false).setNegativeButton("退出", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				MyApplication.getInstance().getPresentActivity().finish();
			}
		}).create();
		return builder;
	}

	public Builder getDialog(final int actionKey, String msg)
	{
		Context context = MyApplication.getInstance().getPresentActivity();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("询问").setMessage(msg).setCancelable(false).setNegativeButton("取消", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				switch (actionKey)
				{
				case MyConstants.EXIT:
					MyApplication.getInstance().exit();
					break;
				case MyConstants.LOGOUT:
					MyApplication.getInstance().getPresentActivity().finish();
					break;
				case MyConstants.CANCEL:
					MyApplication.getInstance().getPresentActivity().finish();
					break;
				default:
					break;
				}
			}
		}).create();
		return builder;
	}
}

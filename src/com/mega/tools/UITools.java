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
	 * �������� : getProgressDialog �������� : ����������ֵ˵����
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @return
	 * 
	 *         �޸ļ�¼�� ���ڣ�2013-2-4 ����4:14:21 �޸��ˣ�kcx ���� ��
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
	 *  �������� : showToast
	 *  �������� :  ��ʾ�Զ���toast
	 *  ����������ֵ˵����
	 *  	@param msg   ��ʾ���ı���Ϣ
	 *  	@param isShort  ��ʾʱ���Ƿ�Ϊ��
	 *  	@param type  ��ʾ��������
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-8-5 ����10:50:22	�޸��ˣ�kcx
	 *  	����	��
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
		AlertDialog.Builder builder = new AlertDialog.Builder(context);// �Ի���
		builder.setTitle(title).setMessage(msg).setPositiveButton("ȡ��", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub

			}
		}).setNeutralButton("ȷ��", new DialogInterface.OnClickListener()
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
		builder.setTitle("����" + count + "��������δ�����д��������").setMessage(msg).setCancelable(false).setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
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
		builder.setTitle("����").setMessage(msg).setCancelable(false).setNegativeButton("�˳�", new DialogInterface.OnClickListener()
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
		builder.setTitle("ѯ��").setMessage(msg).setCancelable(false).setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
			}
		}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
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

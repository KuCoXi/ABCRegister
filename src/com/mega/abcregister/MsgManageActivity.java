package com.mega.abcregister;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.mega.tools.FileTools;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;

public class MsgManageActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_manage);
		MyApplication.getInstance().addActivity(this);
		File imagePath = new File(MyConstants.IMAGE_PATH);
		if (!imagePath.exists())
		{
			imagePath.mkdir();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_msg_manage, menu);
//		return true;
//	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)// 返回键监听
	{
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{

			new AlertDialog.Builder(MsgManageActivity.this).setTitle("警告").setMessage("放弃修改并返回?")
			.setCancelable(false).setNegativeButton("取消", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub
				}
			})
			.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{
					File file = new File(MyConstants.IMAGE_PATH);
					FileTools.RecursionDeleteFile(file);
					if (!file.exists())
					{
						file.mkdir();
					}
					MsgManageActivity.this.finish();
				}
			}).show();
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}

}

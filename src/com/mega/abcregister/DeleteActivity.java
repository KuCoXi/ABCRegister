package com.mega.abcregister;

import java.io.IOException;

import com.mega.tools.FileTools;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DeleteActivity extends Activity
{
	private Button btYes;
	private Button btNo;
	private EditText etPswd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_delete);
		btYes = (Button) findViewById(R.id.btDelYes);
		btNo = (Button) findViewById(R.id.btDelNo);
		etPswd = (EditText) findViewById(R.id.etPswd);
		Intent intent = getIntent();
		final Bundle bundle = intent.getExtras();
		btNo.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		btYes.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String pswd = null;
				String pswdcheck = etPswd.getText().toString();
				try
				{
					pswd = XmlTools.readStringXmlOut(MyConstants.CONFIG_PATH+MyConstants.CONFIGFILENAME).get("aOpenPwd");
					if (pswd.equals(pswdcheck))
					{
						boolean res = FileTools.deleteMsg(MyConstants.WORK_PATH+MyConstants.WORKFILENAME, bundle.getString("idnum"), bundle.getString("appnum"));
						if (res)
						{
							UITools.getTools().showToast("删除成功！", true, UITools.GOOD);
						}
						else {
							UITools.getTools().showToast("删除失败！不存在该条记录或删除出错！", true, UITools.BAD);
						}
						finish();
					}
					else {
						UITools.getTools().showToast("密码错误！", true, UITools.BAD);
					}
					
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					System.out.println(e);
					UITools.getTools().showToast("配置文件出错！", true, UITools.BAD);
					finish();
				}
				
			}
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.delete, menu);
//		return true;
//	}

}

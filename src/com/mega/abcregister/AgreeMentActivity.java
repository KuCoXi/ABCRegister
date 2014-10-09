package com.mega.abcregister;

import java.io.File;
import java.io.FileOutputStream;

import android.R.anim;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

import com.mega.myview.ElectronWriteName;
import com.mega.tools.FileTools;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class AgreeMentActivity extends Activity
{

	private CheckBox agree;
	private Button accept;
	private ProgressDialog progressDialog;
	private Button btZC;
	private Button btHY;
	private Button btBZ;
	private Button btZX;
	private Button save;
	private Button clear;
	private Button quit;
	private LinearLayout draw;
	private ElectronWriteName drawname;
	private Intent intent;
	private Button last;
	private TextView cntitle;
	private TextView main;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_agreement);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
		intent = new Intent(AgreeMentActivity.this, ZCActivity.class);
	}

	private void addListener()
	{
		// TODO Auto-generated method stub
		btZC.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("type", "zc");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		btHY.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("type", "hy");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		btZX.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("type", "zx");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		btBZ.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("type", "bz");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		last.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				last.setVisibility(View.INVISIBLE);
				agree.setVisibility(View.INVISIBLE);
				btZX.setVisibility(View.INVISIBLE);
				btBZ.setVisibility(View.INVISIBLE);
				btHY.setVisibility(View.INVISIBLE);
				btZC.setVisibility(View.INVISIBLE);
				accept.setText("下一页");
				accept.setEnabled(true);
				cntitle.setVisibility(View.VISIBLE);
				main.setVisibility(View.VISIBLE);
			}
		});
		accept.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (accept.getText().equals("下一页"))
				{
					accept.setText("下一步");
					cntitle.setVisibility(View.INVISIBLE);
					main.setVisibility(View.INVISIBLE);
					last.setVisibility(View.VISIBLE);
					agree.setVisibility(View.VISIBLE);
					btZX.setVisibility(View.VISIBLE);
					btBZ.setVisibility(View.VISIBLE);
					btHY.setVisibility(View.VISIBLE);
					btZC.setVisibility(View.VISIBLE);
					if (!agree.isChecked())
					{
						accept.setEnabled(false);
					}
				}
				else {
					setContentView(R.layout.handsign);
					save = (Button) findViewById(R.id.btSave);
					clear = (Button) findViewById(R.id.btClear);
					quit = (Button) findViewById(R.id.btQuit);
					draw = (LinearLayout) findViewById(R.id.WriteUserNameSpace);
					drawname = new ElectronWriteName(AgreeMentActivity.this);
					draw.addView(drawname);
					quit.setOnClickListener(new OnClickListener()
					{
						
						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							AgreeMentActivity.this.finish();
						}
					});
					clear.setOnClickListener(new OnClickListener()
					{
						
						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							invalidateLayoout();
						}
					});
					save.setOnClickListener(new OnClickListener()
					{
						
						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							MyConstants.SDB.close();
							progressDialog.show();
							new Thread(new Runnable()
							{
								@Override
								public void run()
								{
									// TODO Auto-generated method stub
									try
									{
										boolean flag = false;
										if (MyConstants.spf.getBoolean(MyConstants.OTHER_CARD, false))
										{
											flag = MyConstants.makeFile(false) && MyConstants.makeFile(true);
										}
										else {
											flag = MyConstants.makeFile(false);
										}
										takeScreenShot(drawname);
										Thread.sleep(3000);
										if (flag)
										{
											progressDialog.dismiss();
											Looper.prepare();
											UITools.getTools().showToast("保存成功！", true, UITools.GOOD);
//											String app_num = MyConstants.spf.getString(MyConstants.APP_NUM,"");
//											int oldnum = MyConstants.spf.getInt(app_num, 0);
//											MyConstants.editor.putInt(app_num, oldnum+1);
											MyApplication.getInstance().FinishTwoActivity();
											Looper.loop();
										}
										else {
											System.err.println("写入文件错误");
											progressDialog.dismiss();
											Looper.prepare();
											UITools.getTools().showToast("保存失败！", true, UITools.BAD);
											Looper.loop();
										}
										
									} catch (Exception e)
									{
										// TODO: handle exception
										System.err.println("文件保存失败，错误信息：\n"+e);
										e.printStackTrace();
										progressDialog.dismiss();
										Looper.prepare();
										UITools.getTools().showToast("保存失败！", true, UITools.BAD);
										Looper.loop();
									}
								}
							}).start();
						}
					});
				}
				
			}
		});
		agree.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// TODO Auto-generated method stub
				if (isChecked)
				{
					accept.setEnabled(true);
				}
				else {
					accept.setEnabled(false);
				}
			}
		});
	}

	private void initComponent()
	{
		cntitle = (TextView) findViewById(R.id.tv_cntitle);
		main = (TextView) findViewById(R.id.tv_main);
		String maintext = "\t\t本人系使用中国农业银行移动申请终端(下称“申请终端”)，通过电子申请方式提交办理信用卡的申请。本人完全认可该申请方式的效力等同于书面申请的效力，愿意承担相关的全部风险。"
				+"\n\t\t本人同意中国农业银行使用影像方式现场获取办理银行卡的相关个人信息及证明文件，包括但不限于本人免冠肖像、身份证明文件、工作证明文件和财力证明文件等。"
				+"\n\t\t本人在移动终端上输入的信息以及事后补充、更正的信息、本人以任何方式(包括但不限于书面或影像等方式)提供的证明文件等，共同构成本人提供的完整的信用卡申请资料（下称“申请资料”）。本人确认指定申请编码("+MyConstants.spf.getString(MyConstants.APP_NUM, "").trim()
				+")项下的所有申请资料均由本人提供且真实、有效、准确。";
		SpannableStringBuilder style=new SpannableStringBuilder(maintext);
		style.setSpan(new ForegroundColorSpan(Color.GREEN),264,284,Spannable.SPAN_EXCLUSIVE_INCLUSIVE); 
		main.setText(style);
		btZX = (Button) findViewById(R.id.btZhengXin);
		btBZ = (Button) findViewById(R.id.btBiaoZhun);
		btHY = (Button) findViewById(R.id.btHeYue);
		btZC = (Button) findViewById(R.id.btZhangCheng);
		progressDialog = UITools.getTools().getSpinnerProgress(this, "保存", "正在保存到文件...");
		agree = (CheckBox) findViewById(R.id.cb_agree);
		accept = (Button) findViewById(R.id.bt_accept);
		last = (Button) findViewById(R.id.bt_last);
		last.setVisibility(View.INVISIBLE);
		agree.setVisibility(View.INVISIBLE);
		btZX.setVisibility(View.INVISIBLE);
		btBZ.setVisibility(View.INVISIBLE);
		btHY.setVisibility(View.INVISIBLE);
		btZC.setVisibility(View.INVISIBLE);
		accept.setEnabled(true);
		accept.setText("下一页");
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
	
	public void takeScreenShot(View view) throws Exception
	{
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b = view.getDrawingCache();
		FileOutputStream fos = null;
		try
		{
			String PIC_DIR = MyConstants.IMAGE_PATH+MyConstants.spf.getString(MyConstants.APP_NUM, "").trim()+"/";
			String fileName = PIC_DIR+MyConstants.spf.getString(MyConstants.APP_NUM, "app_num").trim()+"-sign.jpg";
			if (!new File(PIC_DIR).exists())
			{
				new File(PIC_DIR).mkdir();
			}
			fos = new FileOutputStream(fileName);
			if (fos != null)
			{
				b.compress(Bitmap.CompressFormat.PNG, 90, fos);
				fos.flush();
				fos.close();
			}
		} catch (Exception e)
		{
			// e.printStackTrace();
			System.out.println(e);
		}

//		invalidateLayoout();
	}

	/* save username picture update the layout */
	private void invalidateLayoout()
	{
		if (drawname != null)
		{
			drawname = null;
			draw.removeAllViews();
			drawname = new ElectronWriteName(AgreeMentActivity.this);
			draw.addView(drawname);
			draw.invalidate();
		}
	}

}

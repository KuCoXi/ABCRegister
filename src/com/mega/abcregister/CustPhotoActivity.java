package com.mega.abcregister;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mega.adapter.ImageAdapter;
import com.mega.handle.MyHandle;
import com.mega.model.CompressThread;
import com.mega.pack.PreObject;
import com.mega.tools.FileTools;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;

public class CustPhotoActivity extends Activity
{
	private Button acceptButton;
	private Button lastButton;
	private Button cameraButton;
	private Button deleteButton;
	private Button preViewButton;
//	private ImageView ivLast;
//	private ImageView ivNext;
	private Button print;
	private Gallery gallery;
	private ImageAdapter adapter;
	private ProgressDialog progressDialog;
	private MyHandle handle;
	private static int picFolderLength;
	private static File picFile;
	private String picFolder = MyConstants.IMAGE_PATH+MyConstants.spf.getString(MyConstants.APP_NUM, "").trim()+"/";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cust_photo);
		addPreViewMsg();
		initComponent();
		addListener();
	}

	private void initComponent()
	{
		print = (Button) findViewById(R.id.btPrint);
		acceptButton = (Button) findViewById(R.id.bt_accept);
		lastButton = (Button) findViewById(R.id.bt_last);
		gallery = (Gallery) findViewById(R.id.gallery_flow);
		cameraButton = (Button) findViewById(R.id.btCamera);
		deleteButton = (Button) findViewById(R.id.btDelete);
		preViewButton = (Button) findViewById(R.id.btPreView);
//		ivLast = (ImageView) findViewById(R.id.ivPicLast);
//		ivNext = (ImageView) findViewById(R.id.ivPicNext);
		adapter = getAdapter();
		gallery.setAdapter(adapter);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示").setMessage("您的预评分为："+getPre()).setCancelable(false)
		.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				UITools.getTools().showToast("左右滑动切换图片", false, UITools.NORMAL);
			}
		}).create().show();
	}
	
	private String getPre()
	{
		PreObject object = new PreObject();
		object.setAge(MyConstants.spf.getString(MyConstants.BIRTHDAY, ""));
		object.setSex(MyConstants.spf.getString(MyConstants.CUST_SEX, ""));
		object.setEducation(MyConstants.spf.getString(MyConstants.EDU_DEGR, ""));
		object.setMarriage(MyConstants.spf.getString(MyConstants.MARR_STAT, ""));
		object.setOffernum("");
		object.setGrouptype("");
		object.setJobtype(MyConstants.spf.getString(MyConstants.TRADE_KIND, ""));
		object.setPosition(MyConstants.spf.getString(MyConstants.TECH_POSI, ""));
		object.setTitle(MyConstants.spf.getString(MyConstants.TECH_GRADE, ""));
		object.setWorkyear(MyConstants.spf.getString(MyConstants.WORK_YEAR, ""));
		object.setIncome(MyConstants.spf.getString(MyConstants.PER_INCOME, ""));
		object.setEvincome("");
		object.setHouse(MyConstants.spf.getString(MyConstants.HOU_STAT, ""));
		return object.toString();
//		return "100";
	}

	private void addListener()
	{
		print.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(CustPhotoActivity.this, PrintActivity.class);
				startActivity(intent);
			}
		});
		acceptButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(CustPhotoActivity.this, AgreeMentActivity.class);
				if (!FileTools.hasImg(picFolder))
				{
//					UITools.getTools().showToast("无照片，请先拍照！", false);
					UITools.getTools().getJumpDialog(intent, "询问", "此申请还未拍照，确定提交？").show();
				} else
				{
//					progressDialog = UITools.getTools().getSpinnerProgress(CustPhotoActivity.this, "压缩照片", "压缩照片并保存...");
//					progressDialog.show();
//					handle = new MyHandle(progressDialog, intent);
//					new CompressThread(picFolder, 250, handle,false).start();
					 startActivity(intent);
				}
			}
		});

		lastButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (CustMsgActivity.card.getStudentCard().equals("1"))
				{
					CustMsgActivity.mTabHost.setCurrentTab(5);
				}
				else {
					CustMsgActivity.mTabHost.setCurrentTab(4);
				}
				
			}
		});

		deleteButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (adapter.getPaths() != null)
				{
					final int position = (int) gallery.getSelectedItemId();
					final File file = new File(adapter.getPaths()[position]);
					if (file.exists())
					{
						AlertDialog.Builder builder = new AlertDialog.Builder(CustPhotoActivity.this);
						builder.setTitle("确认删除？").setPositiveButton("取消", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
							}
						}).setNeutralButton("确定", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								// TODO Auto-generated method stub
								String[] list = new File(picFolder).list();
								for (int i = 0; i < list.length; i++)
								{
									if (list[i].endsWith(file.getName()))
									{
										new File(picFolder + list[i]).delete();
										System.out.println(file.getName());
									}
								}
								adapter = getAdapter();
								gallery.setAdapter(adapter);

							}

						}).create().show();
					}
				}
			}
		});
		preViewButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				Intent intent = new Intent(CustPhotoActivity.this, CardPreViewActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString("premsg", CustMsgActivity.preView.toString());
//				intent.putExtras(bundle);
//				startActivity(intent);
				
			
					CustMsgActivity.mTabHost.setCurrentTab(1);
				
				
			}
		});
		cameraButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (!new File(picFolder).exists())
				{
					new File(picFolder).mkdir();
				}
				picFolderLength = new File(picFolder).listFiles().length;
				try
				{
					if (FileTools.isSDCardReady())
					{
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						picFile = new File(picFolder + getDateFormat() + ".jpg");
						intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picFile));
						startActivityForResult(intent, 1);
					} else
					{
						UITools.getTools().showToast("未发现存储卡或内置存储", true, UITools.SAD);
					}

				} catch (ActivityNotFoundException e)
				{
					// Do nothing for now
				}
			}
		});
	}

	private void addPreViewMsg()
	{
		CustMsgActivity.preView.append("<html><body><p>您申请的卡种："+CustMsgActivity.card.getName()+"</p>");
		CustMsgActivity.preView.append("<p>移动进件编号："+MyConstants.spf.getString(MyConstants.APP_NUM,"")+"</p>");
		CustMsgActivity.preView.append("<p><b><font color=\"#00FFFF\">基本信息</font></b></p>");
		CustMsgActivity.preView.append("<p>中文姓名："+MyConstants.spf.getString(MyConstants.CUST_NAME, "")+"</p>");
		CustMsgActivity.preView.append("<p>拼音姓名："+MyConstants.spf.getString(MyConstants.LAST_NAME, "")+MyConstants.spf.getString(MyConstants.FIRST_NAME, "")+"</p>");
		CustMsgActivity.preView.append("<p>性别："+(MyConstants.spf.getString(MyConstants.CUST_SEX, "").equals("F")?"女":"男")+"</p>");
		CustMsgActivity.preView.append("<p>出生日期："+MyConstants.spf.getString(MyConstants.BIRTHDAY, "")+"</p>");
		if (MyConstants.spf.getString(MyConstants.CERT_TYPE, "").equals("I"))
		{
			CustMsgActivity.preView.append("<p>身份证件类型：身份证</p>");
		}
		else if (MyConstants.spf.getString(MyConstants.CERT_TYPE, "").equals("P")) {
			CustMsgActivity.preView.append("<p>身份证件类型：护照</p>");
		}
		else {
			CustMsgActivity.preView.append("<p>身份证件类型："+MyConstants.spf.getString(MyConstants.CERT_NAME, "")+"</p>");
		}
		CustMsgActivity.preView.append("<p>身份证件号码："+MyConstants.spf.getString(MyConstants.CERT_NO, "")+"</p>");
		CustMsgActivity.preView.append("<p>国籍：中国</p>");
//		CardActivity.preView.append("<p>教育程度：</p>");
		CustMsgActivity.preView.append("</body></html>");
//		CardActivity.preView.append("");
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(CustPhotoActivity.this);
		View askView = inflater.inflate(R.layout.pic_ask_view, null);
		final Spinner kind = (Spinner) askView.findViewById(R.id.spPicKind);
		ArrayAdapter<CharSequence> askAdapter = ArrayAdapter.createFromResource(this, R.array.pic_kind_entries, android.R.layout.simple_spinner_item);
		askAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		kind.setAdapter(askAdapter);
		final EditText other = (EditText) askView.findViewById(R.id.etOtherPic);
		kind.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				String typeValue = (String) kind.getItemAtPosition(arg2);
				if (typeValue.equals("其他"))
				{
					other.setVisibility(View.VISIBLE);
				} else
				{
					other.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub

			}
		});
		AlertDialog.Builder builder = new AlertDialog.Builder(CustPhotoActivity.this);
		builder.setTitle("询问").setMessage("您刚才拍摄的照片属于：").setView(askView).setCancelable(false).setNegativeButton("确定", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				
				try
				{
					Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
					field.setAccessible(true);
					File picNewFile = new File(picFolder + MyConstants.spf.getString(MyConstants.APP_NUM, "app_num").trim() + "-" + kind.getSelectedItemId() + ".jpg");
					if (kind.getSelectedItem().toString().trim().equals("请选择"))
					{
						field.set(dialog, false);//点击dialog不消失
						UITools.getTools().showToast("请选择照片类型", true, UITools.SAD);
					}
					else if (kind.getSelectedItem().toString().trim().equals("其他") && other.getText().toString().trim().equals(""))
					{
						field.set(dialog, false);//点击dialog不消失
						UITools.getTools().showToast("请注明照片类型", true, UITools.SAD);
					} else
					{
						field.set(dialog, true);//点击dialog消失
						if (kind.getSelectedItem().toString().trim().equals("其他"))
						{
							picNewFile = new File(picFolder + MyConstants.spf.getString(MyConstants.APP_NUM, "app_num").trim() + "-" + other.getText().toString().trim() + ".jpg");
						}
						if (picNewFile.exists())
						{
							picNewFile.delete();
						}
						picFile.renameTo(picNewFile);
						adapter = getAdapter();
						if (adapter.getPaths() != null)
						{
							gallery.setAdapter(adapter);
							gallery.setSelection(adapter.getPaths().length - 1);
						} else
						{
							gallery.setAdapter(adapter);
						}
					}
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).create();

		switch (requestCode)
		{
		case 1:
			if (picFolderLength < new File(picFolder).listFiles().length)//有新增照片
			{
				builder.show();//显示文件命名对话框
			} else
			{
				adapter = getAdapter();
				if (adapter.getPaths() != null)
				{
					gallery.setAdapter(adapter);
					gallery.setSelection(adapter.getPaths().length - 1);
				} else
				{
					gallery.setAdapter(adapter);
				}
			}

			break;

		default:
			break;
		}
	}

	/**
	 * 
	 *  函数名称 : getAdapter
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-6-8 下午4:57:07	修改人：kcx
	 *  	描述	：
	 *
	 */
	private ImageAdapter getAdapter()
	{
		ImageAdapter adapter;
		if (FileTools.hasImg(picFolder))
		{
			String[] filelist = new File(picFolder).list();
			List<String> temp = new ArrayList<String>();
			for (int i = 0; i < filelist.length; i++)
			{
				if (filelist[i].endsWith(".jpg"))
				{
					temp.add(picFolder + filelist[i]);
				}
			}
			String[] templist = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++)
			{
				templist[i] = temp.get(i);
			}
			adapter = new ImageAdapter(CustPhotoActivity.this, templist);
//			if (templist.length > 1)
//			{
//				UITools.getTools().showToast("左右滑动切换照片", false);
//			}
		} else
		{
			int[] imgids = { R.drawable.no_img };
			adapter = new ImageAdapter(CustPhotoActivity.this, imgids);
		}
		return adapter;
	}

	/**
	 * 
	 *  函数名称 : getDateFormat
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期：2013-6-8 下午4:57:13	修改人：kcx
	 *  	描述	：
	 *
	 */
	public static String getDateFormat()
	{
		String rel = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());
		rel = formatter.format(curDate);
		return rel;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
//			if (CustMsgActivity.card.getStudentCard().equals("1"))
//			{
//				CustMsgActivity.mTabHost.setCurrentTab(5);
//			}
//			else {
//				CustMsgActivity.mTabHost.setCurrentTab(4);
//			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		 adapter = getAdapter();
		 gallery.setAdapter(adapter);
	}
}

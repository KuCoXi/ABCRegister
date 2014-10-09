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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddActivity extends Activity
{

	private Button acceptButton;
	private Button lastButton;
	private Button cameraButton;
	private Button deleteButton;
//	private ImageView ivLast;
//	private ImageView ivNext;
	private Gallery gallery;
	private ImageAdapter adapter;
	private ProgressDialog progressDialog;
	private MyHandle handle;
	private static int picFolderLength;
	private static File picFile;
	private String picFolder ;
	private String fileName;
	private List<String> picList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		Intent intent = getIntent();
		fileName = intent.getExtras().getString("appnum");
		picFolder = MyConstants.IMAGE_PATH+intent.getExtras().getString("appnum")+"/";
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	
	private void initComponent()
	{
		acceptButton = (Button) findViewById(R.id.bt_accept);
		lastButton = (Button) findViewById(R.id.bt_last);
		gallery = (Gallery) findViewById(R.id.gallery_flow);
		cameraButton = (Button) findViewById(R.id.btCamera);
		deleteButton = (Button) findViewById(R.id.btDelete);
//		ivLast = (ImageView) findViewById(R.id.ivPicLast);
//		ivNext = (ImageView) findViewById(R.id.ivPicNext);
		adapter = getAdapter();
		gallery.setAdapter(adapter);
		UITools.getTools().showToast("���һ����л�ͼƬ", false, UITools.NORMAL);
	}

	private void addListener()
	{
		acceptButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if (!FileTools.hasImg(picFolder))
				{
					UITools.getTools().showToast("����Ƭ���������գ�", false, UITools.SAD);
				} else
				{
//					progressDialog = UITools.getTools().getSpinnerProgress(AddActivity.this, "ѹ����Ƭ", "ѹ����Ƭ������...");
//					progressDialog.show();
//					handle = new MyHandle(progressDialog);
//					new CompressThread(picFolder, 250, handle,true).start();
					AddActivity.this.finish();
				}
			}
		});

		lastButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
				builder.setTitle("ѯ��").setMessage("�˲�����ɾ�����β����������Ƭ��ȷ����").setCancelable(false).setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{}
				}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						File file = new File(picFolder);
						String[] path = file.list();
						if (path!=null)
						{
							for (int i = 0; i < path.length; i++)
							{
								for (String string : picList)
								{
									if (path[i].endsWith(string))
									{
										new File(picFolder+path[i]).delete();
									}
								}
							}
						}
						finish();
					}
				}).create().show();
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
						AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
						builder.setTitle("ȷ��ɾ����").setPositiveButton("ȡ��", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
							}
						}).setNeutralButton("ȷ��", new DialogInterface.OnClickListener()
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
						UITools.getTools().showToast("δ���ִ洢�������ô洢", true, UITools.SAD);
					}

				} catch (ActivityNotFoundException e)
				{
					// Do nothing for now
				}
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(AddActivity.this);
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
				if (typeValue.equals("����"))
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
		AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
		builder.setTitle("ѯ��").setMessage("���ղ��������Ƭ���ڣ�").setView(askView).setCancelable(false).setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				
				try
				{
					Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
					field.setAccessible(true);
					File picNewFile = new File(picFolder + fileName + "-" + kind.getSelectedItemId() + ".jpg");
					picList.add("-" + kind.getSelectedItemId() + ".jpg");
					if (kind.getSelectedItem().toString().trim().equals("��ѡ��"))
					{
						field.set(dialog, false);//���dialog����ʧ
						UITools.getTools().showToast("��ѡ����Ƭ����", true, UITools.SAD);
					}
					else if (kind.getSelectedItem().toString().trim().equals("����") && other.getText().toString().trim().equals(""))
					{
						field.set(dialog, false);//���dialog����ʧ
						UITools.getTools().showToast("��ע����Ƭ����", true, UITools.SAD);
					} else
					{
						field.set(dialog, true);//���dialog��ʧ
						if (kind.getSelectedItem().toString().trim().equals("����"))
						{
							picNewFile = new File(picFolder + fileName + "-" + other.getText().toString().trim() + ".jpg");
							picList.add("-" + other.getText().toString().trim() + ".jpg");
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
			if (picFolderLength < new File(picFolder).listFiles().length)//��������Ƭ
			{
				builder.show();//��ʾ�ļ������Ի���
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
	 *  �������� : getAdapter
	 *  �������� :  
	 *  ����������ֵ˵����
	 *  	@return
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-6-8 ����4:57:07	�޸��ˣ�kcx
	 *  	����	��
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
			adapter = new ImageAdapter(AddActivity.this, templist);
//			if (templist.length > 1)
//			{
//				UITools.getTools().showToast("���һ����л���Ƭ", false);
//			}
		} else
		{
			int[] imgids = { R.drawable.no_img };
			adapter = new ImageAdapter(AddActivity.this, imgids);
		}
		return adapter;
	}

	/**
	 * 
	 *  �������� : getDateFormat
	 *  �������� :  
	 *  ����������ֵ˵����
	 *  	@return
	 *
	 *  �޸ļ�¼��
	 *  	���ڣ�2013-6-8 ����4:57:13	�޸��ˣ�kcx
	 *  	����	��
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

//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event)
//	{
//		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
//		{
//			return true;
//		}
//		return super.dispatchKeyEvent(event);
//	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)//���ؼ�����
	{
		// ���¼����Ϸ��ذ�ť
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
			builder.setTitle("ѯ��").setMessage("�˲�����ɾ�����β����������Ƭ��ȷ����").setCancelable(false).setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which)
				{}
			}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{
					File file = new File(picFolder);
					String[] path = file.list();
					if (path!=null)
					{
						for (int i = 0; i < path.length; i++)
						{
							for (String string : picList)
							{
								if (path[i].endsWith(string))
								{
									new File(picFolder+path[i]).delete();
								}
							}
						}
					}
					finish();
				}
			}).create().show();
			return true;
		} else
		{
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		 adapter = getAdapter();
		 gallery.setAdapter(adapter);
	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}

}

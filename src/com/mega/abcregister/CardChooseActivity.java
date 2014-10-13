package com.mega.abcregister;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.dom4j.DocumentException;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.mega.pack.Card;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.SortByTimes;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class CardChooseActivity extends Activity
{
	private GridView gvCard;// 九宫格
	private ImageView settingButton;
//	private ImageView backButton;
	private EditText etSearchText;
	private Spinner spJg;
	private Spinner spBz;
	private Spinner spDj;
	private ArrayAdapter<CharSequence> bzAdapter;
	private ArrayAdapter<CharSequence> jgAdapter;
	private ArrayAdapter<CharSequence> djAdapter;
	private SimpleAdapter adapter;
	private List<Card> myCard = new ArrayList<Card>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_choose);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}

	private void addListener()
	{
		// TODO Auto-generated method stub
		etSearchText.addTextChangedListener(tWatcher);
		settingButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
//				UITools.getTools().getSettingDialog().show();
				Intent intent = new Intent(CardChooseActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});

		gvCard.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				Object obj = gvCard.getAdapter().getItem(arg2);
				HashMap<String, Object> map = (HashMap<String, Object>) obj;
				String str = (String) map.get("itemText");
				Card card = new Card();
				try
				{
					card = XmlTools.getCard(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName(), str);
					int t = MyConstants.spf.getInt(card.getPROD_KIND(), 0);
					MyConstants.editor.putInt(card.getPROD_KIND(), t+1);
					MyConstants.editor.commit();
					System.out.println(str);
					System.out.println(card.getCardDetails());
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					System.err.println(e);
					UITools.getTools().showToast("配置文件出错！", true, UITools.BAD);
				}
				Bundle bundle = new Bundle();
				bundle.putSerializable("detail", (Serializable)card);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(CardChooseActivity.this, CustMsgActivity.class);
				startActivity(intent);
			}
		});
		spBz.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				search();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0){}
		});
		spJg.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				String jgString = (String) spJg.getItemAtPosition(arg2);
				if (jgString.equals("银联"))
				{
					spBz.setSelection(1);
					spBz.setEnabled(false);
				}
				else {
					spBz.setEnabled(true);
				}
				search();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0){}
		});
		spDj.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				String djString = (String) spDj.getItemAtPosition(arg2);
				if (djString.equals("银联公务金卡"))
				{
					spBz.setSelection(1);
					spJg.setSelection(1);
					spBz.setEnabled(false);
					spJg.setEnabled(false);
				}
				else {
					spBz.setEnabled(true);
					spJg.setEnabled(true);
				}
				search();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0){}
		});
	}

	private void initComponent()
	{
		// TODO Auto-generated method stub
		String[] iconname;
		/**
		 * 排序
		 */
//		MyConstants.editor.putInt("AMUG00CC", 10);
//		MyConstants.editor.putInt("AYRGH1IC", 10);
//		MyConstants.editor.putInt("LYRG30CC", 10);
//		MyConstants.editor.putInt("LVUPBGCC", 10);
//		MyConstants.editor.putInt("LVUPBFCC", 10);
//		MyConstants.editor.putInt("AYRGH2IC", 10);
//		MyConstants.editor.commit();
		Bundle bundle = new Bundle();
		bundle = this.getIntent().getExtras();
		myCard = (List<Card>) bundle.get("card");
		Collections.sort(myCard,new SortByTimes());
		iconname = new String[myCard.size()];
		int count = 0;
		for (Card card1 : myCard)
		{
			iconname[count++] = card1.getName();
		}
		spBz = (Spinner) findViewById(R.id.spBz);
		spDj = (Spinner) findViewById(R.id.spDj);
		spJg = (Spinner) findViewById(R.id.spJg);
		etSearchText = (EditText) findViewById(R.id.etSearchName);
		etSearchText.setVisibility(View.INVISIBLE);
		bzAdapter = ArrayAdapter.createFromResource(this, R.array.bz_list, R.layout.my_spinner_item);
		jgAdapter = ArrayAdapter.createFromResource(this, R.array.jg_list, R.layout.my_spinner_item);
		djAdapter = ArrayAdapter.createFromResource(this, R.array.dj_list, R.layout.my_spinner_item);
		bzAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		djAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spBz.setAdapter(bzAdapter);
		spDj.setAdapter(djAdapter);
		spJg.setAdapter(jgAdapter);
		settingButton = (ImageView) findViewById(R.id.ivSetting);
//		if (MyConstants.spf.getString("readcardtype_set", "").equals("肯麦思"))
//		{
//			LayoutParams lp = new LayoutParams(54, 54);
//			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//			settingButton.setLayoutParams(lp);
//		}
		gvCard = (GridView) findViewById(R.id.gvCard);
		ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < iconname.length; i++)
		{
			String picName = "k"+myCard.get(i).getPROD_KIND().substring(4, 6);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemText", iconname[i]);
			int picid = getResources().getIdentifier(picName.toLowerCase(), "drawable", getPackageName());
			if (picid!=0)
			{
				map.put("itemImage", picid);
			}
			else {
				map.put("itemImage", R.drawable.noimg);
			}
			
			lst.add(map);
		}

		adapter = new SimpleAdapter(this, lst, R.layout.card_item, new String[] { "itemImage", "itemText" }, new int[] { R.id.ivIcon, R.id.tvIconName });
		gvCard.setAdapter(adapter);
	}

	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
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
	
	public Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:
				String[] iconname;
				iconname = new String[myCard.size()];
				int count = 0;
				for (Card card1 : myCard)
				{
					iconname[count++] = card1.getName();
				}
				ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < iconname.length; i++)
				{
					String picName = "k"+myCard.get(i).getPROD_KIND().substring(4, 6);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("itemText", iconname[i]);
					int picid = getResources().getIdentifier(picName.toLowerCase(), "drawable", getPackageName());
					if (picName.toLowerCase().equals("k00"))
					{
						String apicname = myCard.get(i).getPROD_KIND();
						System.out.println("非标准系列："+apicname);
						picid = getResources().getIdentifier(apicname.toLowerCase(), "drawable", getPackageName());
					}
					if (picid!=0)
					{
						map.put("itemImage", picid);
					}
					else {
						map.put("itemImage", R.drawable.noimg);
					}
					
					lst.add(map);
				}
				adapter = new SimpleAdapter(CardChooseActivity.this, lst, R.layout.card_item, new String[] { "itemImage", "itemText" }, new int[] { R.id.ivIcon, R.id.tvIconName });
				gvCard.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};
	
	private void search()
	{
		String bz = spBz.getSelectedItem().toString().trim();
		String dj = spDj.getSelectedItem().toString().trim();
		String jg = spJg.getSelectedItem().toString().trim();
		String keyword = etSearchText.getText().toString().trim();
		try
		{
			myCard = MyConstants.searchCard(XmlTools.readCardTypeXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName()), bz, jg, dj,keyword);
			Collections.sort(myCard,new SortByTimes());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
			UITools.getTools().showToast("配置文件出错！", true, UITools.BAD);
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
			UITools.getTools().showToast("配置文件出错！", true, UITools.BAD);
		}
		Message message = new Message();
		message.what = 1;
		myHandler.sendMessage(message);
	}
	
	private TextWatcher tWatcher = new TextWatcher()
	{
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			// TODO Auto-generated method stub
			search();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			// TODO Auto-generated method stub
			search();
		}
		
		@Override
		public void afterTextChanged(Editable s)
		{
			// TODO Auto-generated method stub
			search();
		}
	};

}

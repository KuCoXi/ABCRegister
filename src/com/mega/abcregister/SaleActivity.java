package com.mega.abcregister;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mega.adapter.MyExpandableListAdapter;
import com.mega.pack.Card;
import com.mega.pack.Sale;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class SaleActivity extends Activity
{

	private ExpandableListView sale;
	private MyExpandableListAdapter adapter;
//	private Map<String, String> map;
	private TextView tvTip;
//	private List<Card> cards;
	private List<Sale> sales;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	private void initComponent()
	{
		tvTip = (TextView) findViewById(R.id.tvTip);
		try
		{
			sales = XmlTools.readSaleXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName());
			if (sales.size()!=0)
			{
				String[] group = new String[sales.size()];
				String[][] child = new String[sales.size()][1];
				int[] layout = new int[sales.size()];
				sale = (ExpandableListView) findViewById(R.id.elvSale);
				sale.setGroupIndicator(null);
				for (int i = 0; i < sales.size(); i++)
				{
					layout[i] = R.layout.saleinfo;
					group[i] = sales.get(i).getTitle();
				}
				adapter = new MyExpandableListAdapter(this, group, child, R.layout.list_group, layout);
				for (int i = 0; i < sales.size(); i++)
				{
					View view = adapter.getMyView(i);
					TextView tView = (TextView) view.findViewById(R.id.tvSale);
					SimpleAdapter adapter;
					final GridView gView = (GridView) view.findViewById(R.id.gvSaleCard);
					tView.setText(sales.get(i).getData());
					ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();
//					Field[] fields = R.drawable.class.getDeclaredFields();
					List<Card> cards = sales.get(i).getCards();
					System.out.println("卡片张数："+cards.size());
					if (cards.size()!=0)
					{
						for (int j = 0; j < cards.size(); j++)
						{
							String picName = cards.get(j).getPROD_KIND();
							System.out.println("卡片代码："+picName);
							HashMap<String, Object> map = new HashMap<String, Object>();
							List<Card> cardData = XmlTools.readCardTypeXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName());
							Card card = null;
							for (Card card2 : cardData)
							{
								if (card2.getPROD_KIND().equals(picName))
								{
									card = card2;
									break;
								}
							}
							if (card!=null)
							{
								System.out.println("卡片名称："+card.getName());
								map.put("itemText", card.getName());
								int picid = getResources().getIdentifier(("k"+picName.substring(4, 6)).toLowerCase(), "drawable", getPackageName());
								if (picid!=0)
								{
									map.put("itemImage", picid);
								}
								else {
									map.put("itemImage", R.drawable.noimg);
								}
								lst.add(map);
							}
							
						}
						adapter = new SimpleAdapter(this, lst, R.layout.card_item, new String[] { "itemImage", "itemText" }, new int[] { R.id.ivIcon, R.id.tvIconName });
						gView.setAdapter(adapter);
						gView.setOnItemClickListener(new OnItemClickListener()
						{

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
							{
								// TODO Auto-generated method stub
								Object obj = gView.getAdapter().getItem(arg2);
								HashMap<String, Object> map = (HashMap<String, Object>) obj;
								String str = (String) map.get("itemText");
								Card card = new Card();
								try
								{
									card = XmlTools.getCard(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName(), str);
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
								intent.setClass(SaleActivity.this, CustMsgActivity.class);
								startActivity(intent);
							}
						});
					}
					
					
				}
				sale.setOnGroupExpandListener(new OnGroupExpandListener()
				{
					
					@Override
					public void onGroupExpand(int groupPosition)
					{
						// TODO Auto-generated method stub
						for (int i = 0; i < sales.size(); i++)
						{
							if (groupPosition!=i)
							{
								sale.collapseGroup(i);
							}
						}
					}
				});
				sale.setAdapter(adapter);
			}
			else {
				tvTip.setText("无营销活动");
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
			UITools.getTools().getWarnDialog("配置文件出错！");
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			System.out.println(e);
			UITools.getTools().getWarnDialog("配置文件出错！");
		}
		
	}
	private void addListener()
	{
	}

//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) 
//	{
//		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) 
//	    {     
//			return true;
//	    }
//		return super.dispatchKeyEvent(event);
//	}
	
	@Override
	protected void onDestroy()
	{
		// TODO Auto-generated method stub
		super.onDestroy();
		MyApplication.getInstance().removeActivity(this);
	}
	
}

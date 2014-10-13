package com.mega.abcregister;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import com.hp.hpl.sparta.Text;
import com.mega.adapter.MyExpandableListAdapter;
import com.mega.handle.MyHandle;
import com.mega.pack.Knowledge;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;
import com.mega.tools.XmlTools;

public class KnowledgeActivity extends Activity
{
	private ExpandableListView knowledge;
	private MyExpandableListAdapter adapter;
	private Map<String, String> map;
	private List<Knowledge> knowledges;
	private TextView tvTip;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knowledge);
		initComponent();
		addListener();
		MyApplication.getInstance().addActivity(this);
	}
	
	private void initComponent()
	{
		tvTip = (TextView) findViewById(R.id.tvKTip);
		try
		{
			knowledges = XmlTools.readKnowledgeXmlOut(MyConstants.CONFIG_PATH+MyConstants.getConfigFileName());
			if (knowledges.size()!=0)
			{
				String[] group = new String[knowledges.size()];
				String[][] child = new String[knowledges.size()][1];
				int[] layout = new int[knowledges.size()];
				knowledge = (ExpandableListView) findViewById(R.id.elvKnowledge);
				knowledge.setGroupIndicator(null);
				for (int i = 0; i < knowledges.size(); i++)
				{
					layout[i] = R.layout.knowledgeinfo;
					group[i] = knowledges.get(i).getTitle();
				}
				
				adapter = new MyExpandableListAdapter(this, group, child, R.layout.list_group, layout);
				for (int i = 0; i < knowledges.size(); i++)
				{
					View view = adapter.getMyView(i);
					TextView tView = (TextView) view.findViewById(R.id.tvKnowledge);
					tView.setText(knowledges.get(i).getData());
				}
				knowledge.setOnGroupExpandListener(new OnGroupExpandListener()
				{
					
					@Override
					public void onGroupExpand(int groupPosition)
					{
						// TODO Auto-generated method stub
						for (int i = 0; i < knowledges.size(); i++)
						{
							if (groupPosition!=i)
							{
								knowledge.collapseGroup(i);
							}
						}
					}
				});
				knowledge.setAdapter(adapter);
			}
			else {
				tvTip.setText("无知识库信息");
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
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
	
	private Map<String, String> getKnowledgeList(String data)
	{
		if (data.equals(""))
		{
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		String[] str1 = data.split("\\|\\|");
		for (int i = 0; i < str1.length; i++)
		{
			if (!str1[i].equals(""))
			{
				String[] str2 = str1[i].split("\\|");
				map.put(str2[0], str2[1]);
				System.out.println(str2[0]+":"+str2[1]);
			}
		}
		return map;
	}

}

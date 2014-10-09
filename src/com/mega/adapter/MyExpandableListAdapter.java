package com.mega.adapter;

import java.util.HashMap;

import com.mega.abcregister.R;
import com.mega.myview.MyTextView;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter
{

	private Context context;
	//分组数据
    private String[] group;
    //子项数据
    private String[][] child;
    //分组、子项的布局
    private int groupLayout;
    private int[] childLayout;
    private LayoutInflater inflater;
    private HashMap<Integer, View> childView = new HashMap<Integer, View>();//子项的view
    
    public MyExpandableListAdapter(Context context, String[] group, String[][] child, int groupLayout, int[] childLayout)
    {
    	super();
    	this.context = context;
    	this.group = group;
    	this.child = child;
    	this.groupLayout = groupLayout;
    	this.childLayout = childLayout;
    	this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	for (int i = 0; i < childLayout.length; i++)
		{
			childView.put(i, inflater.inflate(childLayout[i], null));
		}
    }
    
    public View getMyView(int position)
	{
		return childView.get(position);
	}
	public int getGroupCount()
	{
		return group.length;
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer)
	{
		// TODO Auto-generated method stub
		if (observer!=null)
		{
			super.unregisterDataSetObserver(observer);
		}
	}
	public int getChildrenCount(int groupPosition)
	{
		return child[groupPosition].length; 
	}

	
	public Object getGroup(int groupPosition)
	{
		return group[groupPosition];
	}

	
	public Object getChild(int groupPosition, int childPosition)
	{
		return child[groupPosition][childPosition]; 
	}

	
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	
	public boolean hasStableIds()
	{
		return true;
	}

	
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		View rLayout = inflater.inflate(R.layout.list_group, null);
		ImageView ivGroup1 = (ImageView) rLayout.findViewById(R.id.ivGroup1); 
		ImageView ivGroup2 = (ImageView) rLayout.findViewById(R.id.ivGroup2); 
        //判断分组是否展开，分别传入不同的图片资源
        if(isExpanded) 
        { 
        	ivGroup1.setImageResource(R.drawable.up); 
        	ivGroup2.setImageResource(R.drawable.up2); 
        } 
        else 
        { 
        	ivGroup1.setImageResource(R.drawable.down); 
        	ivGroup2.setImageResource(R.drawable.down2); 
        } 
        MyTextView tvGroup = (MyTextView) rLayout.findViewById(R.id.tvGroup); 
        tvGroup.setText(this.getGroup(groupPosition).toString()); 
        return rLayout; 
	}

	
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		convertView = childView.get(groupPosition);
        return convertView; 
	}

	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return false;
	}

}

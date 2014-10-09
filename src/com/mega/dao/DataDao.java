package com.mega.dao;

import java.util.ArrayList;
import java.util.List;

import com.mega.tools.MyConstants;

import android.database.Cursor;


public class DataDao
{
	public static String[] getSingleData(String table,String field,String order)
	{
		List<String> list = new ArrayList<String>();
		String sql = "select " +field +" from "+table+" order by "+order;
		Cursor cursor = MyConstants.SDB.rawQuery(sql, null);
		if (cursor!=null)
		{
			if (cursor.moveToFirst())
			{
				do
				{
					String str = cursor.getString(cursor.getColumnIndex(field));
					list.add(str);
				} while (cursor.moveToNext());
			}
		}
		String[] strings = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			strings[i] = list.get(i);
		}
		cursor.close();
		return strings;
	}
	
	public static String getSingleString(String table,String field,String condition,String value)
	{
		String str = null;
		String sql = "select " +field +" from "+table+" where "+condition+"='"+value+"'";
		Cursor cursor = MyConstants.SDB.rawQuery(sql, null);
		if (cursor!=null)
		{
			if (cursor.moveToFirst())
			{
				do
				{
					str = cursor.getString(cursor.getColumnIndex(field));
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		return str;
	}
	
	public static String[] getMultiString(String table,String field,String condition,String value)
	{
		List<String> list = new ArrayList<String>();
		String sql = "select " +field +" from "+table+" where "+condition+"='"+value+"'";
		Cursor cursor = MyConstants.SDB.rawQuery(sql, null);
		if (cursor!=null)
		{
			if (cursor.moveToFirst())
			{
				do
				{
					String str = cursor.getString(cursor.getColumnIndex(field));
					list.add(str);
				} while (cursor.moveToNext());
			}
		}
		String[] strings = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			strings[i] = list.get(i);
		}
		cursor.close();
		return strings;
	}
	
}

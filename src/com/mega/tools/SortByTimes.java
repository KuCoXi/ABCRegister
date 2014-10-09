package com.mega.tools;

import java.util.Comparator;

import android.R.integer;

import com.mega.pack.Card;

public class SortByTimes implements Comparator<Card>
{

	@Override
	public int compare(Card card1, Card card2)
	{
		// TODO Auto-generated method stub
		int t1 = MyConstants.spf.getInt(card1.getPROD_KIND(), 0);
		int t2 = MyConstants.spf.getInt(card2.getPROD_KIND(), 0);
		if (t1<t2)
		{
			return 1;
		}
		return -1;
	}

}

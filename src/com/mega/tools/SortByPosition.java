package com.mega.tools;

import java.util.Comparator;

import com.mega.pack.ABCElement;

public class SortByPosition implements Comparator<ABCElement>
{

	@Override
	public int compare(ABCElement o1, ABCElement o2)
	{
		// TODO Auto-generated method stub
		if (o1.getPosition()>o2.getPosition())
		{
			return 1;
		}
		return 0;
	}

}

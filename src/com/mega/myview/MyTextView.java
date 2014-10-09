package com.mega.myview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView
{
	public MyTextView(Context con)
	{
		super(con);
	}

	public MyTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused()
	{
		return true;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect)
	{
	}
}

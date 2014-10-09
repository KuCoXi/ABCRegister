package com.mega.myview;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class HintEdittext extends EditText
{
	private String hint = "default";
	private float size = 20;
	private float x = 0;
	private float y = 0;
	private int color;
	public HintEdittext(Context context) {  
        super(context);  
    }  
	public void init(String title,int color,float size,float x,float y)
	{
		this.color = color;
		this.hint = title;
		this.size = size;
		this.x = x;
		this.y = y;
	}
    
    public HintEdittext(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public HintEdittext(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {  
        Paint paint = new Paint();  
        paint.setTextSize(size);  
        paint.setColor(color);  
        canvas.drawText(hint+":", x, getHeight() / 2 + y, paint);  
        super.onDraw(canvas);  
    }  
}

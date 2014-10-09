package com.mega.myview;


import com.mega.abcregister.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

//����һ���̳���EditText�����

public class MyNote extends EditText{
   private static final String TAG ="MyNote";
   private final String PACKAGE_NAME ="com.mega.myview";
   private int color;

   public MyNote(Context context, AttributeSet attrs) {
        super(context, attrs);

        //����Զ���������Դid

        //��һ��������spaceName

        //�ڶ���������������

        //������������������Բ�������Ҫʹ�õ�Ĭ��ֵ
        int resourceId = attrs.getAttributeResourceValue(PACKAGE_NAME, "backgroud", R.color.red);

        //�õ�id��Ӧ����ɫֵ
        color = getResources().getColor(resourceId);
   }

   @Override
   protected void onDraw(Canvas canvas) {

        int lineHeight =  this.getLineHeight(); 
        Paint mPaint = getPaint();
        mPaint.setColor(color);
        int topPadding =this.getPaddingTop();
        int leftPadding = this.getPaddingLeft();
        float textSize = getTextSize();
        setGravity(Gravity.LEFT|Gravity.TOP);
        int y = (int) (topPadding + textSize);
        for(int i=0; i<getLineCount(); i++) {
            canvas.drawLine(leftPadding, y+2, getRight()-leftPadding, y+2, mPaint);
            y+=lineHeight;
       }
      canvas.translate(0, 0);
      super.onDraw(canvas);
  }

 /**
  * ���ü��±��ı༭�򱳾�������ɫ
  * @param color int type��������ɫ��������
  */
  public void setBGColor(int color) {
      this.color = color;
      invalidate();
  }

  /**
   * ���ü��±��ı༭�򱳾�������ɫ
   * @param colorId int type��������ɫ����Դid��
   */
  public void setBGColorId(int colorId) {
      this.color = getResources().getColor(colorId);
      invalidate();
  }

}


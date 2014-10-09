package com.mega.adapter;

import java.io.File;

import com.mega.tools.MyApplication;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	DisplayMetrics dm = new DisplayMetrics(); 
	
	// ����Context  
    private Context mContext;  
    // ������������ ��ͼƬԴ  
    private String[] paths;
    
    private int[] imgids;
    
    private int position;
    // ����ImageAdapter  
    public ImageAdapter(Context c,String[] paths) 
    {  
        mContext = c;  
        this.paths = paths;
    }  
    
    public int getPosition()
    {
    	return position;
    }
    public ImageAdapter(Context c,int[] imgids)
    {
    	mContext = c;
    	this.imgids = imgids;
    }
  
    // ��ȡͼƬ�ĸ���  
    public int getCount() {  
        return paths==null?imgids.length:paths.length;  
    }  
  
    // ��ȡͼƬ�ڿ��е�λ��  
    public Object getItem(int position) {  
        return position;  
    }  
  
    // ��ȡͼƬID  
    public long getItemId(int position) {  
        return position;  
    }  
  
    public String[] getPaths()
	{
		return paths;
	}

	public View getView(int position, View convertView, ViewGroup parent) {  
        ImageView imageview = new ImageView(mContext);  
        int width = MyApplication.getInstance().getWidth()/3*2;
        // ��ImageView������Դ  
        if (imgids!=null)
		{
			imageview.setImageResource(imgids[position]);
		}
        else {
        	this.position = position;
        	Bitmap image = getBitmapFromFile(new File(paths[position]), width, width);
            imageview.setImageBitmap(image);
		}
        // ���ò���  
        imageview.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));  
        // ������ʾ��������  
        imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);  
        return imageview;  
    }  

	public Bitmap getBitmapFromFile(File dst, int width, int height)
	{
		if (null != dst && dst.exists())
		{
			BitmapFactory.Options opts = null;
			if (width > 0 && height > 0)
			{
				opts = new BitmapFactory.Options();
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(dst.getPath(), opts);
				// ����ͼƬ���ű���
				final int minSideLength = Math.min(width, height);
				opts.inSampleSize = computeSampleSize(opts, minSideLength, width * height);
				opts.inJustDecodeBounds = false;
				opts.inInputShareable = true;
				opts.inPurgeable = true;
			}
			try
			{
				return BitmapFactory.decodeFile(dst.getPath(), opts);
			} catch (OutOfMemoryError e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	private static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels)
	{
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8)
		{
			roundedSize = 1;
			while (roundedSize < initialSize)
			{
				roundedSize <<= 1;
			}
		} else
		{
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels)
	{
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound)
		{
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1))
		{
			return 1;
		} else if (minSideLength == -1)
		{
			return lowerBound;
		} else
		{
			return upperBound;
		}
	}

}

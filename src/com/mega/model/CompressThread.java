package com.mega.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.R.integer;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

import com.mega.handle.MyHandle;
import com.mega.tools.MyApplication;
import com.mega.tools.MyConstants;
import com.mega.tools.UITools;

public class CompressThread extends Thread
{
	private String[] path;
	private String pathname;
	private int size;
	private MyHandle handle;
	private boolean isAdd;
	Message message = new Message();
	public CompressThread(String pathname, int size,MyHandle handle,Boolean isAdd)
	{
		this.pathname = pathname;
		this.path = new File(pathname).list();
		this.size = size;
		this.handle = handle;
		this.isAdd = isAdd;
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
		//判断是否有无源图片的压缩图片，并删除
//		for (int i = 0; i < path.length; i++)
//		{
//			String compressed;
//			if (path[i].startsWith("cps")&&(path[i].endsWith(".jpg")))
//			{
//				compressed = path[i];
//				int count = 0;
//				for (int j = 0; j < path.length; j++)
//				{
//					if (!path[j].equals(compressed) && path[j].endsWith(compressed.substring(3)))
//					{
//						count++;
//					}
//				}
//				if (count==0)
//				{
//					new File(MyConstants.IMAGE_PATH+compressed).delete();
//				}
//			}
//		}
		
		//遍历图片目录将源图片压缩保存
		for (int i = 0; i < path.length; i++)
		{
			if (!path[i].startsWith("cps")&&(path[i].endsWith(".jpg")))
			{
				String source = path[i];
				int count = 0;
				//判断源图片是否已被压缩
				for (int j = 0; j < path.length; j++)
				{
					if(!path[j].equals(source) && path[j].endsWith(source) && new File(path[j]).length()!=0)
					{
						count++;
					}
				}
				if (count==0)//源图片未被压缩
				{
					System.out.println(path[i]);
					FileOutputStream out = null;
					try
					{
						out = new FileOutputStream(pathname+"cps"+path[i]);
					} catch (FileNotFoundException e)
					{
						// TODO Auto-generated catch block
//						e.printStackTrace();
						System.out.println("压缩图片错误1："+e);
					}
					
					Uri imageUri = Uri.fromFile(new File(pathname+path[i]));
					MyApplication.getInstance().getContentResolver().notifyChange(imageUri, null);
					ContentResolver cr = MyApplication.getInstance().getContentResolver();
					Bitmap bitmap = null;
					try
					{
						bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, imageUri);
					} catch (FileNotFoundException e1)
					{
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						System.out.println("压缩图片错误2："+e1);
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						System.out.println("压缩图片错误3："+e1);
					}
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
					int options = 100;
					if(baos.toByteArray().length / 1024 <= size)
					{
						size = baos.toByteArray().length/1024;
					}
					while (baos.toByteArray().length / 1024 > size)
					{
						// 循环判断如果压缩后图片是否大于size,大于继续压缩
						baos.reset();// 重置baos即清空baos
						bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
						options -= 10;// 每次都减少10%
//						if (options!=100)
//						{
//							System.out.println("大小："+baos.toByteArray().length / 1024);
//						}
					}
					System.out.println("压缩后大小："+baos.toByteArray().length/1024+"KB");
					
					try
					{
						out.write(baos.toByteArray());
						out.flush();
						out.close();
//						imageView = (ImageView) view.findViewById(R.id.ivPhoto);
//						ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
//						Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//						imageView.setImageBitmap(bitmap);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
//						e.printStackTrace();
						System.out.println("压缩图片错误4："+e);
					}
					new File(pathname+source).delete();
				}
				
			}
			
		}
		Message message = new Message();
		message.what = MyConstants.COMPRESS_DONE;
		if (isAdd)
		{
			message.what = MyConstants.ADD_DOWN;
		}
		handle.sendMessage(message);
		Looper.prepare();
		UITools.getTools().showToast("压缩完毕!", true, UITools.GOOD);
		Looper.loop();
	}
}

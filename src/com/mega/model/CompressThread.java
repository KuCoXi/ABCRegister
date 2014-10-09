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
		
		//�ж��Ƿ�����ԴͼƬ��ѹ��ͼƬ����ɾ��
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
		
		//����ͼƬĿ¼��ԴͼƬѹ������
		for (int i = 0; i < path.length; i++)
		{
			if (!path[i].startsWith("cps")&&(path[i].endsWith(".jpg")))
			{
				String source = path[i];
				int count = 0;
				//�ж�ԴͼƬ�Ƿ��ѱ�ѹ��
				for (int j = 0; j < path.length; j++)
				{
					if(!path[j].equals(source) && path[j].endsWith(source) && new File(path[j]).length()!=0)
					{
						count++;
					}
				}
				if (count==0)//ԴͼƬδ��ѹ��
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
						System.out.println("ѹ��ͼƬ����1��"+e);
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
						System.out.println("ѹ��ͼƬ����2��"+e1);
					} catch (IOException e1)
					{
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						System.out.println("ѹ��ͼƬ����3��"+e1);
					}
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
					int options = 100;
					if(baos.toByteArray().length / 1024 <= size)
					{
						size = baos.toByteArray().length/1024;
					}
					while (baos.toByteArray().length / 1024 > size)
					{
						// ѭ���ж����ѹ����ͼƬ�Ƿ����size,���ڼ���ѹ��
						baos.reset();// ����baos�����baos
						bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// ����ѹ��options%����ѹ��������ݴ�ŵ�baos��
						options -= 10;// ÿ�ζ�����10%
//						if (options!=100)
//						{
//							System.out.println("��С��"+baos.toByteArray().length / 1024);
//						}
					}
					System.out.println("ѹ�����С��"+baos.toByteArray().length/1024+"KB");
					
					try
					{
						out.write(baos.toByteArray());
						out.flush();
						out.close();
//						imageView = (ImageView) view.findViewById(R.id.ivPhoto);
//						ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ��ѹ���������baos��ŵ�ByteArrayInputStream��
//						Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ��ByteArrayInputStream��������ͼƬ
//						imageView.setImageBitmap(bitmap);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
//						e.printStackTrace();
						System.out.println("ѹ��ͼƬ����4��"+e);
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
		UITools.getTools().showToast("ѹ�����!", true, UITools.GOOD);
		Looper.loop();
	}
}

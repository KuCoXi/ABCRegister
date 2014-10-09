package com.mega.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Message;

import com.mega.handle.MyHandle;

public class Alarm extends Thread
{
	private String enddate;
	private String startdate;
	private SimpleDateFormat sFormat;
	private MyHandle handle;

	public Alarm(String startdate, String enddate, MyHandle handle)
	{
		this.enddate = enddate;
		this.startdate = startdate;
		this.handle = handle;
		this.sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		super.run();
		try
		{
			boolean flag = true;
			while (flag)
			{
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					flag = false;
				}
				Date time = new Date();
				Date start = sFormat.parse(startdate);
				Date end = sFormat.parse(enddate);
				if (end.getTime() <= time.getTime())
				{
					flag = false;
					System.out.println("时间到");
					Message msg = new Message();
					msg.what = MyConstants.TIME_END;
					handle.sendMessage(msg);
				}
				else if(time.getTime()>=start.getTime()&&time.getTime()<end.getTime())
				{
					Bundle bundle = new Bundle();
					bundle.putString("etime", getTime(end, time));
					Message msg = new Message();
					msg.setData(bundle);
					msg.what = MyConstants.END_TIME_LEFT;
					handle.sendMessage(msg);
				}
				else {
					flag = false;
					Bundle bundle = new Bundle();
					bundle.putString("stime", getTime(start, time));
					Message msg = new Message();
					msg.setData(bundle);
					msg.what = MyConstants.START_TIME_LEFT;
					handle.sendMessage(msg);
				}
			}
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopRun()
	{
		this.interrupt();
	}
	
	private String getTime(Date d1,Date d2)
	{
		long l = d1.getTime() - d2.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long sec = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String dayStr = String.valueOf(day > 9 ? day : "0" + day);
		String hourStr = String.valueOf(hour > 9 ? hour : "0" + hour);
		String minStr = String.valueOf(min > 9 ? min : "0" + min);
		String secStr = String.valueOf(sec > 9 ? sec : "0" + sec);
		return dayStr + "天" + hourStr + "小时" + minStr + "分" + secStr + "秒";
	}
}

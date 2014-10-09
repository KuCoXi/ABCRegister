package com.mega.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import com.mega.handle.MyHandle;
import com.mega.pack.Person;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ReadCardThread extends Thread
{
	private InputStream mmInStream = null;
	private OutputStream mmOutStream = null;
	int Readflage = -99;
	byte[] cmd_SAM = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x12, (byte) 0xFF, (byte) 0xEE };
	byte[] cmd_find = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x01, 0x22 };
	byte[] cmd_selt = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x20, 0x02, 0x21 };
	byte[] cmd_read = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x03, 0x30, 0x01, 0x32 };
	byte[] cmd_sleep = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x02, 0x00, 0x02 };
	byte[] cmd_weak = { (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0x96, 0x69, 0x00, 0x02, 0x01, 0x03 };
	byte[] recData = new byte[1500];
	private Handler handler;
	private static Person person;
	private Thread thread = Thread.currentThread();
	private Timer timer;

	public ReadCardThread(InputStream mmInStream, OutputStream mmOutStream,Handler handler)
	{
		this.mmInStream = mmInStream;
		this.mmOutStream = mmOutStream;
		this.handler = handler;
	}

	private TimerTask readtask = new TimerTask()
	{

		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			Message message6 = new Message();
			message6.what = 11;
			Bundle bundle = new Bundle();
			bundle.putString("error", "¶ÁÈ¡¶þ´úÖ¤³¬Ê±Òì³££¡");
			message6.setData(bundle);
			handler.sendMessage(message6);
			thread.interrupt();
		}
	};

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		super.run();
		try
		{
			timer = new Timer();
			Thread.sleep(100);
			timer.schedule(readtask, 10000);
			ReadCard();
			if (Readflage > 0)
			{
				timer.cancel();
				Message message1 = new Message();
				message1.what = 13;
				Bundle bundle = new Bundle();
				bundle.putSerializable("person", person);
				message1.setData(bundle);
				handler.sendMessage(message1);
				thread.interrupt();
			} else
			{
				if (Readflage == -2)
				{
					timer.cancel();
					Message message2 = new Message();
					message2.what = 11;
					Bundle bundle = new Bundle();
					bundle.putString("error", "´íÎóÂë£º" + Readflage + "£¬Éè±¸Á¬½ÓÒì³££¡");
					message2.setData(bundle);
					handler.sendMessage(message2);
					thread.interrupt();
				}
				if (Readflage == -3 || Readflage == -4)
				{
					timer.cancel();
					Message message3 = new Message();
					message3.what = 11;
					Bundle bundle = new Bundle();
					bundle.putString("error", "´íÎóÂë£º" + Readflage + "£¬ÎÞ¿¨»ò¿¨Æ¬ÒÑ¶Á¹ý£¡");
					message3.setData(bundle);
					handler.sendMessage(message3);
					thread.interrupt();
				}
				if (Readflage == -5)
				{
					timer.cancel();
					Message message4 = new Message();
					message4.what = 11;
					Bundle bundle = new Bundle();
					bundle.putString("error", "´íÎóÂë£º" + Readflage + "£¬¶Á¿¨Ê§°Ü£¡");
					message4.setData(bundle);
					handler.sendMessage(message4);
					thread.interrupt();
				}
				if (Readflage == -99)
				{
					timer.cancel();
					Message message5 = new Message();
					message5.what = 11;
					Bundle bundle = new Bundle();
					bundle.putString("error", "´íÎóÂë£º" + Readflage + "£¬²Ù×÷Òì³££¡");
					message5.setData(bundle);
					handler.sendMessage(message5);
					thread.interrupt();
				}
			}
		} catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			timer.cancel();
			Message message8 = new Message();
			message8.what = 11;
			Bundle bundle = new Bundle();
			bundle.putString("error", "´íÎóÂë£ºNumberFormatException£¬Éè±¸Á¬½ÓÒì³££¡");
			message8.setData(bundle);
			handler.sendMessage(message8);
			thread.interrupt();
			// e.printStackTrace();
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			timer.cancel();
			Thread.currentThread().interrupt();
			Message message8 = new Message();
			message8.what = 11;
			Bundle bundle = new Bundle();
			bundle.putString("error", "´íÎóÂë£ºInterruptedException£¬Éè±¸Á¬½ÓÒì³££¡");
			message8.setData(bundle);
			handler.sendMessage(message8);
		}
	}

	private void ReadCard()
	{
		try
		{
			if ((mmInStream == null) || (mmOutStream == null))
			{
				Readflage = -2;// Á¬½ÓÒì³£
				return;
			}
			mmOutStream.write(cmd_find);
			Thread.sleep(200);
			int datalen = mmInStream.read(recData);
			if (recData[9] == -97)
			{
				mmOutStream.write(cmd_selt);
				Thread.sleep(200);
				datalen = mmInStream.read(recData);
				if (recData[9] == -112)
				{
					mmOutStream.write(cmd_read);
					Thread.sleep(1000);
					byte[] tempData = new byte[1500];
					if (mmInStream.available() > 0)
					{
						datalen = mmInStream.read(tempData);
					} else
					{
						Thread.sleep(500);
						if (mmInStream.available() > 0)
						{
							datalen = mmInStream.read(tempData);
						}
					}
					int flag = 0;
					if (datalen < 1294)
					{
						for (int i = 0; i < datalen; i++, flag++)
						{
							recData[flag] = tempData[i];
						}
						Thread.sleep(1000);
						if (mmInStream.available() > 0)
						{
							datalen = mmInStream.read(tempData);
						} else
						{
							Thread.sleep(500);
							if (mmInStream.available() > 0)
							{
								datalen = mmInStream.read(tempData);
							}
						}
						for (int i = 0; i < datalen; i++, flag++)
						{
							recData[flag] = tempData[i];
						}

					} else
					{
						for (int i = 0; i < datalen; i++, flag++)
						{
							recData[flag] = tempData[i];
						}
					}
					tempData = null;
					if (flag == 1295)
					{
						if (recData[9] == -112)
						{

							byte[] dataBuf = new byte[256];
							for (int i = 0; i < 256; i++)
							{
								dataBuf[i] = recData[14 + i];
							}
							String TmpStr = new String(dataBuf, "UTF16-LE");
							TmpStr = new String(TmpStr.getBytes("UTF-8"));
							person = new Person();
							person.setPeopleName(TmpStr.substring(0, 15));
							person.setPeopleSex(TmpStr.substring(15, 16).equals("1") ? "ÄÐ" : "Å®");
							person.setPeopleNation(decodeNation(Integer.parseInt(TmpStr.substring(16, 18))));
							person.setPeopleBirthday(TmpStr.substring(18, 26));
							person.setPeopleAddress(TmpStr.substring(26, 61));
							person.setPeopleIDCode(TmpStr.substring(61, 79));
							person.setDepartment(TmpStr.substring(79, 94));
							person.setStartDate(TmpStr.substring(94, 102));
							person.setEndDate(TmpStr.substring(102, 110));

							// ÕÕÆ¬½âÂë
							try
							{
								byte[] datawlt = new byte[1024];
								for (int i = 0; i < 1024; i++)
								{
									datawlt[i] = recData[i + 270];
								}
								person.setPhoto(datawlt);
								Readflage = 1;// ¶Á¿¨³É¹¦
							} catch (Exception e)
							{
								Readflage = 6;// ÕÕÆ¬½âÂëÒì³£
							}

						} else
						{
							Readflage = -5;// ¶Á¿¨Ê§°Ü£¡
						}
					} else
					{
						Readflage = -5;// ¶Á¿¨Ê§°Ü
					}
				} else
				{
					Readflage = -4;// Ñ¡¿¨Ê§°Ü
				}
			} else
			{
				Readflage = -3;// Ñ°¿¨Ê§°Ü
			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			Readflage = -99;// ¶ÁÈ¡Êý¾ÝÒì³£
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
//			Readflage = -99;// ¶ÁÈ¡Êý¾ÝÒì³£
		}
	}
	
	private String decodeNation(int code)
	{
		String nation;
		switch (code)
		{
		case 1:
			nation = "ºº";
			break;
		case 2:
			nation = "ÃÉ¹Å";
			break;
		case 3:
			nation = "»Ø";
			break;
		case 4:
			nation = "²Ø";
			break;
		case 5:
			nation = "Î¬Îá¶û";
			break;
		case 6:
			nation = "Ãç";
			break;
		case 7:
			nation = "ÒÍ";
			break;
		case 8:
			nation = "×³";
			break;
		case 9:
			nation = "²¼ÒÀ";
			break;
		case 10:
			nation = "³¯ÏÊ";
			break;
		case 11:
			nation = "Âú";
			break;
		case 12:
			nation = "¶±";
			break;
		case 13:
			nation = "Ñþ";
			break;
		case 14:
			nation = "°×";
			break;
		case 15:
			nation = "ÍÁ¼Ò";
			break;
		case 16:
			nation = "¹þÄá";
			break;
		case 17:
			nation = "¹þÈø¿Ë";
			break;
		case 18:
			nation = "´ö";
			break;
		case 19:
			nation = "Àè";
			break;
		case 20:
			nation = "ÀüËÛ";
			break;
		case 21:
			nation = "Øô";
			break;
		case 22:
			nation = "î´";
			break;
		case 23:
			nation = "¸ßÉ½";
			break;
		case 24:
			nation = "À­ìï";
			break;
		case 25:
			nation = "Ë®";
			break;
		case 26:
			nation = "¶«Ïç";
			break;
		case 27:
			nation = "ÄÉÎ÷";
			break;
		case 28:
			nation = "¾°ÆÄ";
			break;
		case 29:
			nation = "¿Â¶û¿Ë×Î";
			break;
		case 30:
			nation = "ÍÁ";
			break;
		case 31:
			nation = "´ïÎÓ¶û";
			break;
		case 32:
			nation = "ØïÀÐ";
			break;
		case 33:
			nation = "Ç¼";
			break;
		case 34:
			nation = "²¼ÀÊ";
			break;
		case 35:
			nation = "ÈöÀ­";
			break;
		case 36:
			nation = "Ã«ÄÏ";
			break;
		case 37:
			nation = "ØîÀÐ";
			break;
		case 38:
			nation = "Îý²®";
			break;
		case 39:
			nation = "°¢²ý";
			break;
		case 40:
			nation = "ÆÕÃ×";
			break;
		case 41:
			nation = "Ëþ¼ª¿Ë";
			break;
		case 42:
			nation = "Å­";
			break;
		case 43:
			nation = "ÎÚ×Î±ð¿Ë";
			break;
		case 44:
			nation = "¶íÂÞË¹";
			break;
		case 45:
			nation = "¶õÎÂ¿Ë";
			break;
		case 46:
			nation = "µÂ°º";
			break;
		case 47:
			nation = "±£°²";
			break;
		case 48:
			nation = "Ô£¹Ì";
			break;
		case 49:
			nation = "¾©";
			break;
		case 50:
			nation = "ËþËþ¶û";
			break;
		case 51:
			nation = "¶ÀÁú";
			break;
		case 52:
			nation = "¶õÂ×´º";
			break;
		case 53:
			nation = "ºÕÕÜ";
			break;
		case 54:
			nation = "ÃÅ°Í";
			break;
		case 55:
			nation = "çó°Í";
			break;
		case 56:
			nation = "»ùÅµ";
			break;
		case 97:
			nation = "ÆäËû";
			break;
		case 98:
			nation = "Íâ¹úÑªÍ³ÖÐ¹ú¼®ÈËÊ¿";
			break;
		default:
			nation = "";
			break;
		}
		return nation;
	}

}

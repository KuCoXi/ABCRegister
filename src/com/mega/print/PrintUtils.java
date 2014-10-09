package com.mega.print;

import android.util.Log;

import com.sprt.bluetooth.android.BluetoothPrinter;
import com.sprt.bluetooth.android.FontProperty;
import com.sprt.bluetooth.android.PrintGraphics;
import com.sprt.bluetooth.android.PrinterType;
import com.sprt.bluetooth.android.Table;

public class PrintUtils
{
	public static void printNote(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		StringBuffer sb = new StringBuffer();
		mPrinter.setPrinter(BluetoothPrinter.COMM_LINE_HEIGHT, 80);

		mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN, BluetoothPrinter.COMM_ALIGN_CENTER);
		// �ֺź�����������һ��
		mPrinter.setCharacterMultiple(1, 1);
		mPrinter.printText("�����º���\n");

		mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN, BluetoothPrinter.COMM_ALIGN_LEFT);
		// �ֺ�ʹ��Ĭ��
		mPrinter.setCharacterMultiple(0, 0);
		sb.append("�ŵ�ţ�574001\n");
		sb.append("���ݺţ�S00003169\n");
		sb.append("����Ա��s004_s004\n");

		sb.append("����ʱ�䣺2012-06-17\n");
		sb.append("��ӡʱ�䣺2012-06-17 13:37:24\n");
		mPrinter.printText(sb.toString()); // ��ӡ

		printTable1(mPrinter); // ��ӡ���

		sb = new StringBuffer();
		sb.append("����: 6.00       	        �ϼ�: 35.00\n");
		sb.append("����: �ֽ� 			    100.00\n");
		sb.append("����: �ֽ�			     65.00\n");

		sb.append("��˾���ƣ�����һ��һʮ�Ҿ���Ʒ���޹�˾\n");
		sb.append("��˾��ַ��www.jiangsu1510.com\n");
		sb.append("��ַ������ʡ�����н���������·12��\n");
		sb.append("�绰��0574-88222999\n");
		sb.append("����ר�ߣ�4008-567-567 \n");
		sb.append("==============================================\n");
		mPrinter.printText(sb.toString());

		mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN, BluetoothPrinter.COMM_ALIGN_CENTER);
		mPrinter.setCharacterMultiple(0, 1);
		mPrinter.printText("лл�ݹ�,��ӭ�ٴι���!\n");
		mPrinter.printText("Demo��!\n\n\n");
	}

	public static void printDrawToPOS76(BluetoothPrinter mPrinter)
	{
		mPrinter.init();

		// ͼƬ��������200������
		// mPrinter.setCurrentPrintType(PrinterType.T5);
		PrintGraphics pg = new PrintGraphics();
		pg.initCanvas(210);
		/*
		 * ��ʼ�����ʣ�Ĭ�������У� 1��������� 2�����û�����ɫΪ��ɫ 3������ͼ��Ϊ����ģʽ
		 */
		pg.initPaint();

		/*
		 * ����ͼƬ����: drawImage(float x, float y, String path)
		 * ����(x,y)��ָ����ͼƬ�����϶������ꡣ
		 */
		pg.drawImage(0, 0, "/sdcard/tiii.bmp");// SD���ϵ�ͼƬ
		mPrinter.printImage(pg.getCanvasImage()); // ��ӡ�����ϵ�ͼ��
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 3);// ����3��
	}

	public static void printDrawToT9(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		PrintGraphics pg = new PrintGraphics();

		// pg.initCanvas(550);
		/*
		 * ��ʼ�����ʣ�Ĭ�������У� 1��������� 2�����û�����ɫΪ��ɫ 3������ͼ��Ϊ����ģʽ
		 */
		// pg.initPaint();
		// init ��������pg.initCanvas(550)��pg.initPaint(), T9��ӡ���Ϊ72mm,����Ϊ47mm.
		pg.init(PrinterType.T9);

		/*
		 * ����ͼƬ����: drawImage(float x, float y, String path)
		 * ����(x,y)��ָ����ͼƬ�����϶������ꡣ
		 */
		pg.drawImage(0, 0, "/sdcard/tiii.bmp");

		// ʵ����������
		FontProperty fp = new FontProperty();

		fp.setFont(false, false, false, false, 28, null);
		pg.setFontProperty(fp); // ͨ����ʼ���������������û���

		/*
		 * ���ı�������drawTaxt(float x, float y, String nStr)
		 * ����(x,y)��ָ�����ı����������꣬����y���ܵ���0
		 */
		pg.drawText(160, 80, "����˼�����ؿƼ���չ���޹�˾");

		// ����������Ⱥ�����setLinewidth(float w)
		pg.setLineWidth(8);
		/*
		 * ������������drawLine(float x1, float y1, float x2, float y2)
		 * ����(x1,y1)��ʾ������꣬(x2,y2)��ʾ�յ�����
		 */
		pg.drawLine(0, 180, 200, 180);

		// ע�⣺���ı���ʱ��һ��Ҫ��������Ȼָ���0
		pg.setLineWidth(0);

		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);

		pg.drawText(20, 100, "����һ����ͨ�ı���18������");
		pg.drawText(20, 120, "����һ����ͨ�ı���18������");
		pg.drawText(20, 140, "����һ����ͨ�ı���18������");
		pg.drawText(20, 160, "����һ����ͨ�ı���18������");

		fp.setFont(false, false, true, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 210, "����һ���»����ı���18������");

		fp.setFont(false, false, false, true, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 240, "����һ��ɾ�����ı���18������");

		fp.setFont(true, true, true, true, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 290, "����һ�л�ϸ�ʽ�ı���24������");

		pg.setLineWidth(2);
		pg.drawLine(0, 0, 200, 300);
		/*
		 * ������Բ����Բ������drawEllips(float x1, float y1, float x2, float y2)
		 * ����(x1,y1)��Բ��Ӿ��λ��͵����ϵ����� (x2,y2)��Բ��Ӿ��λ��͵����µ�����
		 */

		pg.setLineWidth(0);
		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(50, 350, "����˼�����ؿƼ���չ���޹�˾");
		fp.setFont(false, false, false, false, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(160, 380, "����˼�����ؿƼ���չ���޹�˾");

		// pg.printPng();

		// getCanvasImage������û�����������ͼ��,printImage������ӡͼ��.
		mPrinter.printImage(pg.getCanvasImage());
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 3); // ����3��
	}

//	public static void printImage(Resources resources, BluetoothPrinter mPrinter)
//	{
//		mPrinter.init();
//		PrintGraphics pg = new PrintGraphics();
//
//		// ʹ��init���������ӡ������,�ڲ�����initCanvas(576) �� initPaint();
//		pg.init(PrinterType.T9);
//
//		Bitmap logo = BitmapFactory.decodeResource(resources, R.drawable.sprt_printer);
//		pg.drawImage(50, 0, logo);
//		pg.drawImage(50, logo.getHeight() + 10, "/sdcard/logo.jpg");
//
//		// getCanvasImage������û�����������ͼ��,printImage������ӡͼ��.
//		mPrinter.printImage(pg.getCanvasImage());
//		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2); // ����2��
//	}

	public static void printCustomImage(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub

		PrintGraphics pg = new PrintGraphics();
		/*
		 * ��ʼ�������������Ŀ��Ϊ������һ��������ѡ�� 1��58mm�ͺŴ�ӡ��ʵ�ʿ�����48mm��48*8=384px
		 * 2��80mm�ͺŴ�ӡ��ʵ�ʿ�����72mm��72*8=576px ��Ϊ�����ĸ߶��������Ƶģ������ڴ���䷽�濼��ҪС��4M�ȽϺ��ʣ�
		 * ����Ԥ��Ϊ��ȵ�10����
		 */
		// pg.initCanvas(576);
		/*
		 * ��ʼ�����ʣ�Ĭ�������У� 1��������� 2�����û�����ɫΪ��ɫ 3������ͼ��Ϊ����ģʽ
		 */
		// pg.initPaint();

		// init ��������pg.initCanvas(550)��pg.initPaint(), T9��ӡ���Ϊ72mm,����Ϊ47mm.
		pg.init(PrinterType.T9);

		/*
		 * ����ͼƬ����: drawImage(float x, float y, String path)
		 * ����(x,y)��ָ����ͼƬ�����϶������ꡣ
		 */
		pg.drawImage(0, 0, "/sdcard/sp1.jpg");

		// ʵ����������
		FontProperty fp = new FontProperty();

		/*
		 * ��ʼ�����庯���� setFont(boolean bBold, boolean bItalic, boolean bUnderLine,
		 * boolean bStrikeout, int iSize, Typeface sFace)
		 * ��1���������Ƿ���壨ȡֵΪtrue/false�� ������,��������������Ϊ����,��ӡ������.
		 * ��2���������Ƿ�б�壨ȡֵΪtrue/false�� ��3���������Ƿ��»��ߣ�ȡֵΪtrue/false��
		 * ��4���������Ƿ�ɾ���ߣ�ȡֵΪtrue/false�� ��5�������������С��ȡֵΪһ������
		 * ��6���������������ͣ�һ������Ϊnull����ʾʹ��ϵͳĬ�����壩
		 */
		fp.setFont(false, false, false, false, 28, null);
		// ͨ����ʼ���������������û���
		pg.setFontProperty(fp);
		/*
		 * ���ı�������drawTaxt(float x, float y, String nStr)
		 * ����(x,y)��ָ�����ı����������꣬����y���ܵ���0
		 */
		// pg.drawText(160, 80, "����˼�����ؿƼ���չ���޹�˾");

		// ����������Ⱥ�����setLinewidth(float w)
		pg.setLineWidth(8);
		/*
		 * ������������drawLine(float x1, float y1, float x2, float y2)
		 * ����(x1,y1)��ʾ������꣬(x2,y2)��ʾ�յ�����
		 */
		pg.drawLine(0, 180, 576, 180);
		// ע�⣺���ı���ʱ��һ��Ҫ��������Ȼָ���0

		pg.setLineWidth(0);
		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 200, "����һ����ͨ�ı���18������");

		fp.setFont(false, false, true, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 220, "����һ���»����ı���18������");

		fp.setFont(false, false, false, true, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 240, "����һ��ɾ�����ı���18������");

		fp.setFont(true, true, true, true, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 270, "����һ�л�ϸ�ʽ�ı���24������");

		pg.setLineWidth(2);
		pg.drawLine(0, 300, 576, 300);
		/*
		 * ������Բ����Բ������drawEllips(float x1, float y1, float x2, float y2)
		 * ����(x1,y1)��Բ��Ӿ��λ��͵����ϵ����� (x2,y2)��Բ��Ӿ��λ��͵����µ�����
		 */
		pg.drawEllips(20, 320, 340, 480);
		pg.drawEllips(360, 320, 520, 480);
		/*
		 * ���ƾ��λ��ͺ�����drawRectangle(float x1, float y1, float x2, float y2)
		 * ����(x1,y1)�Ǿ��λ��͵����ϵ����� (x2,y2)�Ǿ��λ��͵����µ�����
		 */
		pg.drawRectangle(20, 500, 340, 660);
		pg.drawRectangle(360, 500, 520, 660);
		pg.drawLine(0, 680, 576, 680);
		pg.setLineWidth(5);
		pg.drawEllips(20, 700, 340, 860);
		pg.drawEllips(360, 700, 520, 860);
		pg.drawRectangle(20, 880, 340, 1040);
		pg.drawRectangle(360, 880, 520, 1040);

		pg.setLineWidth(2);
		pg.drawLine(0, 1050, 576, 1050);

		pg.setLineWidth(0);
		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(50, 400, "����˼�����ؿƼ���չ���޹�˾");
		fp.setFont(false, false, false, false, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(160, 580, "����˼�����ؿƼ���չ���޹�˾");

		// pg.printPng();

		mPrinter.printImage(pg.getCanvasImage());
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2);
	}

	public static void printTable(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		// getTable����:����1,���ض����ŷָ�������; 2,�����ָ���;
		// 3,������ռ�ַ����,����2��,Ӣ��1��. Ĭ�������ܹ���Ҫ����48
		// ��񳬳����ֻ�����һ�д�ӡ.�����ֶ�����,�ɼ�\n.
		mPrinter.setCharacterMultiple(0, 0);
		String column = "Ʒ��;����;����;���";
		Table table = new Table(column, ";", new int[] { 18, 8, 8, 8 });
		table.add("1,���ϼ�;	    2.00;    5.00;   10.00");
		table.add("2,�����;�;   2.00;   5.00;    10.00");
		table.add("3,�ɹ��Ｆ(����Ȼ�л�ũ������);   1.00;   68.00;   68.00");
		table.add("4,����ƹ�;   1.00;   4.00;    4.00");
		table.add("5,��ը������\n\n(����������100Ԫ���������); 1234567890123456.00;   5.00;    5.00");
		table.add("6,֥�齴;	    1.00;   2.00;    2.00");
		table.add("7,�׷�;      1.00;   2.00;     2.00");
		mPrinter.printTable(table);
	}

	public static void printTable1(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		String column = "����/Ʒ��,����,����,С��";
		Table table = new Table(column, ",", new int[] { 20, 10, 8, 10 });
		table.add("0009480\n�Զ�EVAֱ��ɡ,10.00,1,10.00");
		table.add("0007316\n1.5kg������˿�ҹ�,5.00,2,10.00");
		table.add("0007392\n3��һ���ʴ�,5.00,3,15.00");
		table.setHasSeparator(true);
		mPrinter.printTable(table);
	}

//	public static void printTitle(Context context, BluetoothPrinter mPrinter)
//	{
//		// TODO Auto-generated method stub
//		mPrinter.init();
//
//		// setTitle����:����1,������; 2,������; 3,logo
//		// ��ӡЧ��Ϊlogo��������һ��,��Ϊlogo,��Ϊ������; ����������һ��. ��������ʾ.
//		mPrinter.setTitle("����˼�����ؿƼ���չ���޹�˾", "רע���ɿ����й����ִ�ӡ������Ʒ��", BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n1.��logo,������͸�����:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle(null, "רע���ɿ����й����ִ�ӡ������Ʒ��", BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n2.��logo�͸�����:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle("����˼�����ؿƼ���չ���޹�˾", null, BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n3.��logo��������:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle("����˼�����ؿƼ���չ���޹�˾", "רע���ɿ����й����ִ�ӡ������Ʒ��", null);
//		mPrinter.printText("\n4.��������͸�����:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2);
//	}

	public static void printTextToOther(BluetoothPrinter mPrinter, String content)
	{
		mPrinter.init();
		// �����и�
		mPrinter.setPrinter(BluetoothPrinter.COMM_LINE_HEIGHT, 10);

		// ��ӡ�ı�
		mPrinter.printText(content);
		// ��2��
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_NEWLINE);
	}

	public static void printTextToT9(BluetoothPrinter mPrinter, String content)
	{
		mPrinter.init();
		// �����и�
		mPrinter.setPrinter(BluetoothPrinter.COMM_LINE_HEIGHT, 80);

		// ��ӡ�ı�
		mPrinter.printText(content);
		// ����
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_NEWLINE);
	}

	public static void printBarCode(BluetoothPrinter mPrinter, String codeNum)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		Log.i("12345", "xxxxxxxxxxxxxxxxxxx");
		mPrinter.setCharacterMultiple(0, 0);
		/**
		 * ������߾�,nL,nH ���ÿ��Ϊ(nL+nH*256)* �����ƶ���λ. ������߾�Դ�ӡ�����ע��λ����Ӱ��.
		 */
		mPrinter.setLeftMargin(15, 0);
		// mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN,BluetoothPrinter.COMM_ALIGN_LEFT);

		/**
		 * ����1: ������������� 2<=n<=6,Ĭ��Ϊ2 ����2: ��������߶� 1<=n<=255,Ĭ��162 ����3:
		 * ��������ע�ʹ�ӡλ��.0����ӡ,1�Ϸ�,2�·�,3���·�����,Ĭ��Ϊ0 ����4:
		 * ������������.BluetoothPrinter.BAR_CODE_TYPE_ ��ͷ�ĳ���,Ĭ��ΪCODE128
		 */
		mPrinter.setBarCode(3, 150, 2, BluetoothPrinter.BAR_CODE_TYPE_CODE128);
		mPrinter.printBarCode(codeNum);
	}
}

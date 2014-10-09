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
		// 字号横向纵向扩大一倍
		mPrinter.setCharacterMultiple(1, 1);
		mPrinter.printText("宁波月湖店\n");

		mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN, BluetoothPrinter.COMM_ALIGN_LEFT);
		// 字号使用默认
		mPrinter.setCharacterMultiple(0, 0);
		sb.append("门店号：574001\n");
		sb.append("单据号：S00003169\n");
		sb.append("收银员：s004_s004\n");

		sb.append("单据时间：2012-06-17\n");
		sb.append("打印时间：2012-06-17 13:37:24\n");
		mPrinter.printText(sb.toString()); // 打印

		printTable1(mPrinter); // 打印表格

		sb = new StringBuffer();
		sb.append("数量: 6.00       	        合计: 35.00\n");
		sb.append("付款: 现金 			    100.00\n");
		sb.append("找零: 现金			     65.00\n");

		sb.append("公司名称：江苏一五一十家居用品有限公司\n");
		sb.append("公司网址：www.jiangsu1510.com\n");
		sb.append("地址：江苏省宁波市江北区东昌路12号\n");
		sb.append("电话：0574-88222999\n");
		sb.append("服务专线：4008-567-567 \n");
		sb.append("==============================================\n");
		mPrinter.printText(sb.toString());

		mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN, BluetoothPrinter.COMM_ALIGN_CENTER);
		mPrinter.setCharacterMultiple(0, 1);
		mPrinter.printText("谢谢惠顾,欢迎再次光临!\n");
		mPrinter.printText("Demo版!\n\n\n");
	}

	public static void printDrawToPOS76(BluetoothPrinter mPrinter)
	{
		mPrinter.init();

		// 图片宽不过超过200个像素
		// mPrinter.setCurrentPrintType(PrinterType.T5);
		PrintGraphics pg = new PrintGraphics();
		pg.initCanvas(210);
		/*
		 * 初始化画笔，默认属性有： 1、消除锯齿 2、设置画笔颜色为黑色 3、设置图形为空心模式
		 */
		pg.initPaint();

		/*
		 * 插入图片函数: drawImage(float x, float y, String path)
		 * 其中(x,y)是指插入图片的左上顶点坐标。
		 */
		pg.drawImage(0, 0, "/sdcard/tiii.bmp");// SD卡上的图片
		mPrinter.printImage(pg.getCanvasImage()); // 打印画布上的图像
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 3);// 换行3行
	}

	public static void printDrawToT9(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		PrintGraphics pg = new PrintGraphics();

		// pg.initCanvas(550);
		/*
		 * 初始化画笔，默认属性有： 1、消除锯齿 2、设置画笔颜色为黑色 3、设置图形为空心模式
		 */
		// pg.initPaint();
		// init 方法包含pg.initCanvas(550)和pg.initPaint(), T9打印宽度为72mm,其他为47mm.
		pg.init(PrinterType.T9);

		/*
		 * 插入图片函数: drawImage(float x, float y, String path)
		 * 其中(x,y)是指插入图片的左上顶点坐标。
		 */
		pg.drawImage(0, 0, "/sdcard/tiii.bmp");

		// 实例化字体类
		FontProperty fp = new FontProperty();

		fp.setFont(false, false, false, false, 28, null);
		pg.setFontProperty(fp); // 通过初始化的字体属性设置画笔

		/*
		 * 绘文本函数：drawTaxt(float x, float y, String nStr)
		 * 其中(x,y)是指插入文本的左下坐标，所以y不能等于0
		 */
		pg.drawText(160, 80, "北京思普瑞特科技发展有限公司");

		// 设置线条宽度函数：setLinewidth(float w)
		pg.setLineWidth(8);
		/*
		 * 绘线条函数：drawLine(float x1, float y1, float x2, float y2)
		 * 其中(x1,y1)表示起点坐标，(x2,y2)表示终点坐标
		 */
		pg.drawLine(0, 180, 200, 180);

		// 注意：绘文本的时候，一定要将线条宽度恢复到0
		pg.setLineWidth(0);

		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);

		pg.drawText(20, 100, "这是一行普通文本，18号字体");
		pg.drawText(20, 120, "这是一行普通文本，18号字体");
		pg.drawText(20, 140, "这是一行普通文本，18号字体");
		pg.drawText(20, 160, "这是一行普通文本，18号字体");

		fp.setFont(false, false, true, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 210, "这是一行下划线文本，18号字体");

		fp.setFont(false, false, false, true, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 240, "这是一行删除线文本，18号字体");

		fp.setFont(true, true, true, true, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 290, "这是一行混合格式文本，24号字体");

		pg.setLineWidth(2);
		pg.drawLine(0, 0, 200, 300);
		/*
		 * 绘制椭圆或正圆函数：drawEllips(float x1, float y1, float x2, float y2)
		 * 其中(x1,y1)是圆外接矩形或方型的左上点坐标 (x2,y2)是圆外接矩形或方型的右下点坐标
		 */

		pg.setLineWidth(0);
		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(50, 350, "北京思普瑞特科技发展有限公司");
		fp.setFont(false, false, false, false, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(160, 380, "北京思普瑞特科技发展有限公司");

		// pg.printPng();

		// getCanvasImage方法获得画布上所画的图像,printImage方法打印图像.
		mPrinter.printImage(pg.getCanvasImage());
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 3); // 换行3行
	}

//	public static void printImage(Resources resources, BluetoothPrinter mPrinter)
//	{
//		mPrinter.init();
//		PrintGraphics pg = new PrintGraphics();
//
//		// 使用init方法传入打印机类型,内部包括initCanvas(576) 和 initPaint();
//		pg.init(PrinterType.T9);
//
//		Bitmap logo = BitmapFactory.decodeResource(resources, R.drawable.sprt_printer);
//		pg.drawImage(50, 0, logo);
//		pg.drawImage(50, logo.getHeight() + 10, "/sdcard/logo.jpg");
//
//		// getCanvasImage方法获得画布上所画的图像,printImage方法打印图像.
//		mPrinter.printImage(pg.getCanvasImage());
//		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2); // 换行2行
//	}

	public static void printCustomImage(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub

		PrintGraphics pg = new PrintGraphics();
		/*
		 * 初始化画布，画布的宽度为变量，一般有两个选择： 1、58mm型号打印机实际可用是48mm，48*8=384px
		 * 2、80mm型号打印机实际可用是72mm，72*8=576px 因为画布的高度是无限制的，但从内存分配方面考虑要小于4M比较合适，
		 * 所以预置为宽度的10倍。
		 */
		// pg.initCanvas(576);
		/*
		 * 初始化画笔，默认属性有： 1、消除锯齿 2、设置画笔颜色为黑色 3、设置图形为空心模式
		 */
		// pg.initPaint();

		// init 方法包含pg.initCanvas(550)和pg.initPaint(), T9打印宽度为72mm,其他为47mm.
		pg.init(PrinterType.T9);

		/*
		 * 插入图片函数: drawImage(float x, float y, String path)
		 * 其中(x,y)是指插入图片的左上顶点坐标。
		 */
		pg.drawImage(0, 0, "/sdcard/sp1.jpg");

		// 实例化字体类
		FontProperty fp = new FontProperty();

		/*
		 * 初始化字体函数： setFont(boolean bBold, boolean bItalic, boolean bUnderLine,
		 * boolean bStrikeout, int iSize, Typeface sFace)
		 * 第1个参数：是否粗体（取值为true/false） 经测试,若单独设置中文为粗体,打印不出来.
		 * 第2个参数：是否斜体（取值为true/false） 第3个参数：是否下划线（取值为true/false）
		 * 第4个参数：是否删除线（取值为true/false） 第5个参数：字体大小（取值为一整数）
		 * 第6个参数：字体类型（一般设置为null，表示使用系统默认字体）
		 */
		fp.setFont(false, false, false, false, 28, null);
		// 通过初始化的字体属性设置画笔
		pg.setFontProperty(fp);
		/*
		 * 绘文本函数：drawTaxt(float x, float y, String nStr)
		 * 其中(x,y)是指插入文本的左下坐标，所以y不能等于0
		 */
		// pg.drawText(160, 80, "北京思普瑞特科技发展有限公司");

		// 设置线条宽度函数：setLinewidth(float w)
		pg.setLineWidth(8);
		/*
		 * 绘线条函数：drawLine(float x1, float y1, float x2, float y2)
		 * 其中(x1,y1)表示起点坐标，(x2,y2)表示终点坐标
		 */
		pg.drawLine(0, 180, 576, 180);
		// 注意：绘文本的时候，一定要将线条宽度恢复到0

		pg.setLineWidth(0);
		fp.setFont(false, false, false, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 200, "这是一行普通文本，18号字体");

		fp.setFont(false, false, true, false, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 220, "这是一行下划线文本，18号字体");

		fp.setFont(false, false, false, true, 18, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 240, "这是一行删除线文本，18号字体");

		fp.setFont(true, true, true, true, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(20, 270, "这是一行混合格式文本，24号字体");

		pg.setLineWidth(2);
		pg.drawLine(0, 300, 576, 300);
		/*
		 * 绘制椭圆或正圆函数：drawEllips(float x1, float y1, float x2, float y2)
		 * 其中(x1,y1)是圆外接矩形或方型的左上点坐标 (x2,y2)是圆外接矩形或方型的右下点坐标
		 */
		pg.drawEllips(20, 320, 340, 480);
		pg.drawEllips(360, 320, 520, 480);
		/*
		 * 绘制矩形或方型函数：drawRectangle(float x1, float y1, float x2, float y2)
		 * 其中(x1,y1)是矩形或方型的左上点坐标 (x2,y2)是矩形或方型的右下点坐标
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
		pg.drawText(50, 400, "北京思普瑞特科技发展有限公司");
		fp.setFont(false, false, false, false, 24, null);
		pg.setFontProperty(fp);
		pg.drawText(160, 580, "北京思普瑞特科技发展有限公司");

		// pg.printPng();

		mPrinter.printImage(pg.getCanvasImage());
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2);
	}

	public static void printTable(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		// getTable方法:参数1,以特定符号分隔的列名; 2,列名分隔符;
		// 3,各列所占字符宽度,中文2个,英文1个. 默认字体总共不要超过48
		// 表格超出部分会另起一行打印.若想手动换行,可加\n.
		mPrinter.setCharacterMultiple(0, 0);
		String column = "品名;数量;单价;金额";
		Table table = new Table(column, ";", new int[] { 18, 8, 8, 8 });
		table.add("1,王老吉;	    2.00;    5.00;   10.00");
		table.add("2,消毒餐具;   2.00;   5.00;    10.00");
		table.add("3,干锅田鸡(纯天然有机农场饲养);   1.00;   68.00;   68.00");
		table.add("4,蒜泥黄瓜;   1.00;   4.00;    4.00");
		table.add("5,油炸花生米\n\n(当日消费满100元花生米免费); 1234567890123456.00;   5.00;    5.00");
		table.add("6,芝麻酱;	    1.00;   2.00;    2.00");
		table.add("7,米饭;      1.00;   2.00;     2.00");
		mPrinter.printTable(table);
	}

	public static void printTable1(BluetoothPrinter mPrinter)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		String column = "货号/品名,单价,数量,小计";
		Table table = new Table(column, ",", new int[] { 20, 10, 8, 10 });
		table.add("0009480\n自动EVA直柄伞,10.00,1,10.00");
		table.add("0007316\n1.5kg弧形铁丝挂钩,5.00,2,10.00");
		table.add("0007392\n3合一保鲜袋,5.00,3,15.00");
		table.setHasSeparator(true);
		mPrinter.printTable(table);
	}

//	public static void printTitle(Context context, BluetoothPrinter mPrinter)
//	{
//		// TODO Auto-generated method stub
//		mPrinter.init();
//
//		// setTitle方法:参数1,主标题; 2,副标题; 3,logo
//		// 打印效果为logo与主标题一行,左为logo,右为主标题; 副标题另起一行. 均居中显示.
//		mPrinter.setTitle("北京思普瑞特科技发展有限公司", "专注，可靠，中国特种打印机优秀品牌", BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n1.有logo,主标题和副标题:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle(null, "专注，可靠，中国特种打印机优秀品牌", BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n2.有logo和副标题:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle("北京思普瑞特科技发展有限公司", null, BitmapFactory.decodeResource(context.getResources(), R.drawable.sprt));
//		mPrinter.printText("\n3.有logo和主标题:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setTitle("北京思普瑞特科技发展有限公司", "专注，可靠，中国特种打印机优秀品牌", null);
//		mPrinter.printText("\n4.有主标题和副标题:\n");
//		mPrinter.printTitle();
//
//		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_WAKE_PAPER_BY_LINE, 2);
//	}

	public static void printTextToOther(BluetoothPrinter mPrinter, String content)
	{
		mPrinter.init();
		// 设置行高
		mPrinter.setPrinter(BluetoothPrinter.COMM_LINE_HEIGHT, 10);

		// 打印文本
		mPrinter.printText(content);
		// 换2行
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_NEWLINE);
	}

	public static void printTextToT9(BluetoothPrinter mPrinter, String content)
	{
		mPrinter.init();
		// 设置行高
		mPrinter.setPrinter(BluetoothPrinter.COMM_LINE_HEIGHT, 80);

		// 打印文本
		mPrinter.printText(content);
		// 换行
		mPrinter.setPrinter(BluetoothPrinter.COMM_PRINT_AND_NEWLINE);
	}

	public static void printBarCode(BluetoothPrinter mPrinter, String codeNum)
	{
		mPrinter.init();
		// TODO Auto-generated method stub
		Log.i("12345", "xxxxxxxxxxxxxxxxxxx");
		mPrinter.setCharacterMultiple(0, 0);
		/**
		 * 设置左边距,nL,nH 设置宽度为(nL+nH*256)* 横向移动单位. 设置左边距对打印条码的注释位置有影响.
		 */
		mPrinter.setLeftMargin(15, 0);
		// mPrinter.setPrinter(BluetoothPrinter.COMM_ALIGN,BluetoothPrinter.COMM_ALIGN_LEFT);

		/**
		 * 参数1: 设置条码横向宽度 2<=n<=6,默认为2 参数2: 设置条码高度 1<=n<=255,默认162 参数3:
		 * 设置条码注释打印位置.0不打印,1上方,2下方,3上下方均有,默认为0 参数4:
		 * 设置条码类型.BluetoothPrinter.BAR_CODE_TYPE_ 开头的常量,默认为CODE128
		 */
		mPrinter.setBarCode(3, 150, 2, BluetoothPrinter.BAR_CODE_TYPE_CODE128);
		mPrinter.printBarCode(codeNum);
	}
}

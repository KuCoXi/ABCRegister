package com.xhw.rfid.activity;

import java.io.File;

import android.os.Environment;

public class SystemUtil {
	private static String sdDir = null;
	private static String ePoliceDir = null;

	private SystemUtil() {

	}

	public static String getSDPath() {
		if (sdDir == null) {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED);
			File sdDirFile = Environment.getExternalStorageDirectory();
			if (sdCardExist)
				sdDir = sdDirFile.toString();
		}
		return sdDir;
	}
	
	public static String getePoliceDir() {
		if(ePoliceDir == null) {
			ePoliceDir = getSDPath() + "/ePolice/";
			File ePoliceDirFile = new File(ePoliceDir);
		}
		return ePoliceDir;
	}
}

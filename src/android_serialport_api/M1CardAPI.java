package android_serialport_api;

import android.os.SystemClock;
import android.util.Log;

import com.authentication.utils.DataUtils;

public class M1CardAPI {
	public static final int KEY_A = 1;
	public static final int KEY_B = 2;

	private static final byte[] SWITCH_COMMAND = "D&C00040104".getBytes();
	// 发送数据包的前缀
	private static final String DATA_PREFIX = "c050605";
	private static final String FIND_CARD_ORDER = "01";// 寻卡指令
	private static final String PASSWORD_SEND_ORDER = "02";// 密码下发指令
	private static final String PASSWORD_VALIDATE_ORDER = "03";// 密码认证命令
	private static final String READ_DATA_ORDER = "04";// 读指令
	private static final String WRITE_DATA_ORDER = "05";// 写指令
	private static final String ENTER = "\r\n";// 换行符

	private static final String TURN_OFF = "c050602\r\n";// 关闭天线厂

	// 寻卡的指令包
	private static final String FIND_CARD = DATA_PREFIX + FIND_CARD_ORDER
			+ ENTER;

	// 下发密码指令包(A，B段密码各12个’f‘)
	private static final String SEND_PASSWORD = DATA_PREFIX
			+ PASSWORD_SEND_ORDER + "ffffffffffffffffffffffff" + ENTER;

	// //密码认证指令包
	// private static final static PASSWORD_VALIDATE =

	// private static final String FIND_SUCCESS = "c05060501" + ENTER + "0x00,";
	private static final String FIND_SUCCESS = "0x00,";

	private static final String WRITE_SUCCESS = " Write Success!" + ENTER;

	public byte[] buffer = new byte[100];



	/**
	 * 切换成读取RFID
	 * @return
	 */
	private boolean switchStatus() {
		sendCommand(SWITCH_COMMAND);
		Log.i("whw", "SWITCH_COMMAND hex=" + new String(SWITCH_COMMAND));
		SystemClock.sleep(1500);
		SerialPortManager.switchRFID = true;
		return true;
	}

	private int receive(byte[] command, byte[] buffer) {
		int length = -1;
		if (!SerialPortManager.switchRFID) {
			switchStatus();
		}
		sendCommand(command);

		length = SerialPortManager.getInstance().read(buffer, 300, 100);
		return length;
	}

	private void sendCommand(byte[] command) {
		SerialPortManager.getInstance().write(command);
	}

	private static final String DEFAULT_PASSWORD = "ffffffffffff";

	private String getCompletePassword(int keyType, String passwordHexStr) {
		// A.B端密码长度各为6字节，换算成16进制字符串，密码长度就为12个字符长度
		StringBuffer passwordBuffer = new StringBuffer();
		passwordBuffer.append(passwordHexStr);
		if (passwordHexStr != null && passwordHexStr.length() < 12) {
			int length = 12 - passwordHexStr.length();
			for (int i = 0; i < length; i++) {
				passwordBuffer.append('0');
			}
		}
		passwordHexStr = passwordBuffer.toString();
		String completePasswordHexStr = "";
		switch (keyType) {
		case KEY_A:
			completePasswordHexStr = passwordHexStr + DEFAULT_PASSWORD;
			break;
		case KEY_B:
			completePasswordHexStr = DEFAULT_PASSWORD + passwordHexStr;
			break;

		default:
			break;
		}
		return completePasswordHexStr;
	}

	private String getKeyTypeStr(int keyType) {
		String keyTypeStr = null;
		switch (keyType) {
		case KEY_A:
			keyTypeStr = "60";
			break;
		case KEY_B:
			keyTypeStr = "61";
			break;
		default:
			keyTypeStr = "60";
			break;
		}
		return keyTypeStr;
	}

	// 转换扇区里块的地址为两位
	private String getZoneId(int position) {
		return DataUtils.byte2Hexstr((byte) position);
	}

	
	/**
	 * 读取M1卡卡号
	 * Read the M1 card number
	 * @return
	 */
	public Result readCardNum() {
		Log.i("whw", "!!!!!!!!!!!!readCard");
		Result result = new Result();
		byte[] command = FIND_CARD.getBytes();
		int length = receive(command, buffer);
		if (length == 0) {
			result.confirmationCode = Result.TIME_OUT;
			return result;
		}
		String msg = "";
		msg = new String(buffer, 0, length);
		Log.i("whw", "msg hex=" + msg);
		turnOff();
		if (msg.startsWith(FIND_SUCCESS)) {
			result.confirmationCode = Result.SUCCESS;
			result.num = msg.substring(FIND_SUCCESS.length());
		} else {
			result.confirmationCode = Result.FIND_FAIL;
		}
		return result;
	}
	
	/**
	 * 验证密码
	 * Verify password
	 * @param position  block number
	 * @param keyType   Password type
	 * @param password
	 * @return
	 */
	public boolean validatePassword(int position, int keyType, byte[] password) {
		Log.i("whw", "!!!!!!!!!!!!!!keyType=" + keyType);
		byte[] command1 = null;
		if (password == null) {
			// 下发认证命令
			command1 = SEND_PASSWORD.getBytes();
		} else {
			String passwordHexStr = DataUtils.toHexString(password);
			String completePassword = getCompletePassword(keyType,
					passwordHexStr);
			command1 = (DATA_PREFIX + PASSWORD_SEND_ORDER + completePassword + ENTER)
					.getBytes();
		}

		int tempLength = receive(command1, buffer);
		String verifyStr = new String(buffer, 0, tempLength);
		Log.i("whw", "validatePassword verifyStr=" + verifyStr);
		// 验证密码
		byte[] command2 = (DATA_PREFIX + PASSWORD_VALIDATE_ORDER
				+ getKeyTypeStr(keyType) + getZoneId(position) + ENTER)
				.getBytes();

		int length = receive(command2, buffer);
		String msg = new String(buffer, 0, length);
		Log.i("whw", "validatePassword msg=" + msg);
		String prefix = "0x00,\r\n";
		if (msg.startsWith(prefix)) {
			return true;
		}
		return false;
	}


	/**
	 * 读取指定块号存储的数据，长度一般为16字节
	 * Reads the specified number stored data, length of 16 bytes
	 * @param position  block number
	 * @return
	 */
	public byte[] read(int position) {
		byte[] command = { 'c', '0', '5', '0', '6', '0', '5', '0', '4', '0',
				'0', '\r', '\n' };
		char[] c = getZoneId(position).toCharArray();
		command[9] = (byte) c[0];
		command[10] = (byte) c[1];
		int length = receive(command, buffer);
		String data = new String(buffer, 0, length);
		Log.i("whw", "read data=" + data);
		String[] split = data.split(";");
		String msg = "";
		if (split.length == 2) {
			int index = split[1].indexOf("\r\n");
			if (index != -1) {
				msg = split[1].substring(0, index);
			}

			Log.i("whw", "split msg=" + msg + "  msg length=" + msg.length());
		}
		byte[] readData = DataUtils.hexStringTobyte(msg);
		return readData;
	}


	/**
	 * 向指定的块号写入数据，长度为16字节
	 * Write data to the specified block, length is 16 bytes
	 * @param data
	 * @param position
	 * @return
	 */
	public boolean write(byte[] data, int position) {
		String hexStr = DataUtils.toHexString(data);
		byte[] command = (DATA_PREFIX + WRITE_DATA_ORDER + getZoneId(position)
				+ hexStr + ENTER).getBytes();
		Log.i("whw", "***write hexStr=" + hexStr);

		int length = receive(command, buffer);
		if (length > 0) {
			String writeResult = new String(buffer, 0, length);
			Log.i("whw", "write result=" + writeResult);
			return M1CardAPI.WRITE_SUCCESS.equals(writeResult);
		}
		return false;
	}
	
	// 关闭天线厂
	public String turnOff() {
		// byte[] command = TURN_OFF.getBytes();
		// int length = receive(command, buffer);
		// String str = "";
		// if (length > 0) {
		// str = new String(buffer, 0, length);
		// }
		// return str;
		return "";
	}


	public static class Result {
		/**
		 * 成功
		 * successful
		 */
		public static final int SUCCESS = 1;
		/**
		 * 寻卡失败
		 * Find card failure
		 */
		public static final int FIND_FAIL = 2;
		/**
		 * 验证失败
		 * Validation fails
		 */
		public static final int VALIDATE_FAIL = 3;
		/**
		 * 写卡失败
		 * Write card failure
		 */
		public static final int WRITE_FAIL = 4;
		/**
		 * 超时
		 * timeout
		 */
		public static final int TIME_OUT = 5;
		/**
		 * 其它异常
		 * other exception
		 */
		public static final int OTHER_EXCEPTION = 6;

		/**
		 * 确认码 1: 成功 2：寻卡失败 3：验证失败 4:写卡失败 5：超时 6：其它异常
		 */
		public int confirmationCode;

		/**
		 * 结果集:当确认码为1时，再判断是否有结果
		 * Results: when the code is 1, then determine whether to have the result
		 */
		public Object resultInfo;
		
		/**
		 * 卡号
		 * The card number
		 */
		public String num;
	}

}

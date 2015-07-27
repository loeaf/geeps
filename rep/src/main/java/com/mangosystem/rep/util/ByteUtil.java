package com.mangosystem.rep.util;

public class ByteUtil {

	public ByteUtil(){}

	public static final char[] HEX_DIGITS = {
		'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
	};

	public static byte[] toBytesFromString(String str) {
		byte[] b = new byte[str.length()];
		for (int i=0; i<str.length(); i++)
			b[i]= (byte)str.charAt(i);

		return b;
	}

	public static byte[] toBytesFromHexString(String digits) {
		int dLength = digits.length();
		if (dLength % 2 == 1)
			return null;

		dLength = dLength / 2;
		byte[] bytes = new byte[dLength];
		for (int i=0; i<dLength; i++) {
			int idx = i * 2;
			bytes[i] = (byte)(Short.parseShort(digits.substring(idx, idx+2), 16));
		}
		return bytes;
	}

	public static String toHexStringFromByte(byte b) {
		StringBuffer sb = new StringBuffer(3);
		sb.append(Integer.toString((b & 0xF0) >> 4, 16));
		sb.append(Integer.toString(b & 0x0F, 16));
		return sb.toString();
	}

	public static String toHexStringFromBytes(byte[] b) {
		int length = b.length;
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<length; i++) {
			char[] buf = {
					HEX_DIGITS[(b[i] >>> 4) & 0x0F],
					HEX_DIGITS[ b[i]        & 0x0F]
			};
			sb.append( new String(buf) );
		}
		return sb.toString();
	}
	public static String toHexStringFromBytes(byte[] b, int offset, int length) {
		if (b == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for (int i=offset; i<offset+length; i++) {
			char[] buf = {
					HEX_DIGITS[(b[i] >>> 4) & 0x0F],
					HEX_DIGITS[ b[i]        & 0x0F]
			};
			sb.append( new String(buf) );
		}
		return sb.toString();
	}

	public static String toStringFromHexString(String hexStr) {
		byte [] txtInByte = new byte [hexStr.length() / 2];
		int j = 0;
		for (int i = 0; i < hexStr.length(); i += 2) {
			txtInByte[j++] = Byte.parseByte(hexStr.substring(i, i + 2));
		}
		return new String(txtInByte);
	}

	public static String toStringFromBytes(byte[] b) {
		return new String(b);
	}

}
package com.mangosystem.rep.security.cryptography;


public class Base62 {

//	private final static char[] digitsBase62Origin = {
//		'A','B','C','D','E','F','G','H','I','J',
//		'K','L','M','N','O','P','Q','R','S','T',
//		'U','V','W','X','Y','Z',
//		'a','b','c','d','e','f','g','h','i','j',
//		'k','l','m','n','o','p','q','r','s','t',
//		'u','v','w','x','y','z',
//		'0','1','2','3','4','5','6','7','8','9'
//	};
	
	private final static char[] digitsBase62 = {
		'A','B','C','D','E',
		'U','V','W','X','Y','Z',
		'5','6','7','8','9',
		'p','q','r','s','t',
		'u','v','w','x','y','z',
		'a','b','c','d','e',
		'0','1','2','3','4',
		'K','L','M','N','O',
		'f','g','h','i','j',
		'F','G','H','I','J',
		'P','Q','R','S','T',
		'k','l','m','n','o'
	};
	
	private final static char[] digitsBase36 = {
		'a','b','c','d','e',
		'f','g','h','i','j',
		'k','l','m','n','o',
		'p','q','r','s','t',
		'u','v','w','x','y','z',
		'0','1','2','3','4',
		'5','6','7','8','9'
	};
	
	private final static char[] digitsBase52 = {
		'a','b','c','d','e',
		'f','g','h','i','j',
		'k','l','m','n','o',
		'p','q','r','s','t',
		'u','v','w','x','y','z',
		'0','1','2','3','4',
		'5','6','7','8','9',
		'~','`','!','@','#',
		'$','%','^','&','*',
		'(',')','_','+','-','='
	};
	
	public static String encode(long value) {
		try {
			char encodeByte[] = new char[62];
			int ebPos = encodeByte.length - 1;
			int radix = digitsBase62.length;
	
			while (value > -1) {
				encodeByte[ebPos--] = digitsBase62[(int)(value % radix)];
				value = value / radix;
				if(value == 0) break;
			}
			return new String(encodeByte, ebPos+1, (encodeByte.length-1-ebPos));
		} catch (Exception e) {
			return "";
		}
	}
	
	public static long decode(String value) {
		long num = 0;
		for (int i=0, n=value.length(); i<n; i++) {
			for (int j=0, n2=digitsBase62.length; j<n2; j++) {
				if ( value.charAt(i) == digitsBase62[j] ) {
					num = num * digitsBase62.length + j;
					break;
				}
			}
		}
		return num;
	}
	

	public static String encode36(long value) {
		try {
			char encodeByte[] = new char[36];
			int ebPos = encodeByte.length - 1;
			int radix = digitsBase36.length;
	
			while (value > -1) {
				encodeByte[ebPos--] = digitsBase36[(int)(value % radix)];
				value = value / radix;
				if(value == 0) break;
			}
			return new String(encodeByte, ebPos+1, (encodeByte.length-1-ebPos));
		} catch (Exception e) {
			return "";
		}
	}
	
	public static long decode36(String value) {
		long num = 0;
		for (int i=0, n=value.length(); i<n; i++) {
			for (int j=0, n2=digitsBase36.length; j<n2; j++) {
				if ( value.charAt(i) == digitsBase36[j] ) {
					num = num * digitsBase36.length + j;
					break;
				}
			}
		}
		return num;
	}
	
	public static String encode52(long value) {
		try {
			char encodeByte[] = new char[52];
			int ebPos = encodeByte.length - 1;
			int radix = digitsBase52.length;
	
			while (value > -1) {
				encodeByte[ebPos--] = digitsBase52[(int)(value % radix)];
				value = value / radix;
				if(value == 0) break;
			}
			return new String(encodeByte, ebPos+1, (encodeByte.length-1-ebPos));
		} catch (Exception e) {
			return "";
		}
	}
	
	public static long decode52(String value) {
		long num = 0;
		for (int i=0, n=value.length(); i<n; i++) {
			for (int j=0, n2=digitsBase52.length; j<n2; j++) {
				if ( value.charAt(i) == digitsBase52[j] ) {
					num = num * digitsBase52.length + j;
					break;
				}
			}
		}
		return num;
	}
	
	
//	public static String encode2(long value) {
//		String digits62_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//		StringBuilder sb = new StringBuilder();
//		while ( value > -1 ) {
//			sb.append( digits62_str.charAt( (int)value % digits62_str.length()) );
//			value /= digits62_str.length();
//			if(value == 0) break;
//		}
//		return sb.reverse().toString();
//	}
	
//	public static long decode2(String value) {
//		String digits62_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//		long num = 0;
//		for (int i=0, n=value.length(); i<n; i++) {
//			num = num * digits62_str.length() + digits62_str.indexOf( value.charAt(i) );
//		}
//		return num;
//	}
	
}
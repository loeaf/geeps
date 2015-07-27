package com.mangosystem.rep.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class StringUtil {
	private StringUtil() {
	}

	/**
	 * If the value argument value is <code>null</code> or trimmed empty value,
	 * returns defaultValue argument value; if the value argument value is not
	 * <code>null</code> and not trimmed empty value, returns value argument
	 * value.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the string argument or default value argument.
	 * @see #nvl(String value, String defaultValue)
	 */
	public static String evl(Object evlObj, String defaultValue) {
		String value = "";
		try {
			if (evlObj!=null) {
				value = evlObj.toString();
				return ( value == null || value.trim().equals("") || value.equals("null") ) ? defaultValue : value;
			} else {
				return defaultValue;
			}
		} catch(Exception e) {
			return defaultValue;
		}
	}

	/**
	 * If the value argument value is <code>null</code>, returns defaultValue
	 * argument value; if the value argument value is not <code>null</code>,
	 * returns value argument value.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the string argument or default value argument.
	 * @see #evl(String value, String defaultValue)
	 */
	public static String nvl(String value, String defaultValue) {
		return (value == null || value.equals("null")) ? defaultValue : value;
	}

	public static String nvl(String value) {
		return nvl(value, "");
	}

	// ----------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param String
	 *            str
	 * @return Integer
	 */
	public static int stoi(String str) {
		if (str == null)
			return 0;
		return (Integer.valueOf(str).intValue());
	}

	/**
	 * 
	 * @param String
	 *            value
	 * @param int i
	 * @return Integer
	 */
	public static int stoi(String value, int i) {
		if (value == null || value.equals(""))
			return i;
		return (Integer.valueOf(value).intValue());
	}

	/**
	 * 
	 * @param int i
	 * @return String
	 */
	public static String itos(int i) {
		return (new Integer(i).toString());
	}
	
	/**
	 * 
	 * @param double i
	 * @return String
	 */
	public static String dtos(double i) {
		return (new Double(i).toString());
	}
	
	/**
	 * 
	 * @param long i
	 * @return String
	 */
	public static String ltos(long i) {
		return (new Long(i).toString());
	}

	/**
	 * 
	 * @param String
	 *            value
	 * @param boolean defaultValue
	 * @return
	 */
	public static boolean stob(String value, boolean defaultValue) {
		return value == null ? defaultValue : Boolean.valueOf(value)
				.booleanValue();
	}

	/**
	 * 
	 * @param value
	 * @return convertStringToBollean(value, false)
	 */
	public static boolean stob(String value) {
		return stob(value, false);
	}

	// ----------------------------------------------------------------------------------------------------

	/**
	 * Parses the string argument as a signed decimal integer.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return 0.
	 * 
	 * @param value
	 *            a string.
	 * @return the integer represented by the argument in decimal.
	 */
	public static int parseInt(String value) {
		return parseInt(value, 0);
	}

	/**
	 * Parses the string argument as a signed decimal integer.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return default value argument.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the integer represented by the argument in decimal.
	 */
	public static int parseInt(String value, int defaultValue) {
		try {
			return (value == null || value.trim().equals("")) ? defaultValue
					: Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns a <code>boolean</code> with a value represented by the specified
	 * String.
	 * 
	 * The <code>boolean</code> returned represents the value true if the string
	 * argument is equal, ignoring case, to the string "true". If the string
	 * argument is <code>null</code>, returns the default value argument.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the <code>boolean</code> value represented by the string.
	 */
	public static boolean parseBoolean(String value, boolean defaultValue) {
		return value == null ? defaultValue : Boolean.valueOf(value)
				.booleanValue();
	}

	/**
	 * Returns a <code>boolean</code> with a value represented by the specified
	 * String.
	 * 
	 * The <code>boolean</code> returned represents the value true if the string
	 * argument is equal, ignoring case, to the string "true". If the string
	 * argument is <code>null</code>, return false.
	 * 
	 * @param value
	 *            a string.
	 * @return the <code>boolean</code> value represented by the string.
	 */
	public static boolean parseBoolean(String value) {
		return parseBoolean(value, false);
	}

	/**
	 * Parses the string argument as a signed decimal <code>long</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return default value argument.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the <code>long</code> represented by the argument in decimal.
	 */
	public static long parseLong(String value, long defaultValue) {
		try {
			return (value == null || value.trim().equals("")) ? defaultValue
					: Long.parseLong(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Parses the string argument as a signed decimal <code>long</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return -0L.
	 * 
	 * @param value
	 *            a string.
	 * @return the <code>long</code> represented by the argument in decimal.
	 */
	public static long parseLong(String value) {
		return parseLong(value, 0L);
	}

	/**
	 * Returns a new <code>float</code> initialized to the value represented by
	 * the specified <code>String</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return default value argument.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the <code>float</code> represented by the argument.
	 */
	public static float parseFloat(String value, float defaultValue) {
		try {
			return (value == null || value.trim().equals("")) ? defaultValue
					: Float.parseFloat(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns a new <code>float</code> initialized to the value represented by
	 * the specified <code>String</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return 0.0F.
	 * 
	 * @param value
	 *            a string.
	 * @return the <code>float</code> represented by the argument.
	 */
	public static float parseFloat(String value) {
		return parseFloat(value, 0.0F);
	}

	/**
	 * Returns a new <code>double</code> initialized to the value represented by
	 * the specified <code>String</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return default value argument.
	 * 
	 * @param value
	 *            a string.
	 * @param defaultValue
	 *            default value.
	 * @return the <code>double</code> represented by the argument.
	 */
	public static double parseDouble(String value, double defaultValue) {
		try {
			return (value == null || value.trim().equals("")) ? defaultValue
					: Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns a new <code>double</code> initialized to the value represented by
	 * the specified <code>String</code>.
	 * 
	 * If the string argument is <code>null</code> or empty or not a number(
	 * <code>NumberFormatException<code> occur),
	 * return 0.0.
	 * 
	 * @param value
	 *            a string.
	 * @return the <code>double</code> represented by the argument.
	 */
	public static double parseDouble(String value) {
		return parseDouble(value, 0.0);
	}

	// ----------------------------------------------------------------------------------------------------

	/**
	 * String left right trim
	 * 
	 * @param String
	 *            str
	 * @return String
	 */
	public static String stringTrim(String str) {
		if (str == null)
			return "";
		return str.trim();
	}

	/**
	 * String left trim
	 * 
	 * @param String
	 *            str
	 * @return String
	 */
	public static String stringLTrim(String str) {
		if (str == null)
			return "";

		while (str.startsWith(" ")) {
			str = str.substring(1, str.length());
		}
		return str;
	}

	/**
	 * String right trim
	 * 
	 * @param String
	 *            str
	 * @return String
	 */
	public static String stringRTrim(String str) {
		if (str == null)
			return "";

		while (str.endsWith(" ")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	// ---------------------------------------------------------

	/**
	 * String left padding
	 * 
	 * @param String
	 *            str
	 * @param char padChar : padded character
	 * @param int padLen : padded length
	 * @return String
	 */
	public static String stringLPad(String str, char padChar, int padLen) {
		while (str.length() < padLen) {
			str = padChar + str;
		}

		return str;
	}

	/**
	 * String right padding
	 * 
	 * @param String
	 *            str
	 * @param char padChar : padded character
	 * @param int padLen : padded length
	 * @return String
	 */
	public static String stringRPad(String str, char padChar, int padLen) {
		while (str.length() < padLen) {
			str = str + padChar;
		}

		return str;
	}

	// ----------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param String
	 *            no
	 * @return String
	 */
	public static String makeMoneyType(String no) {
		if (no == null || "".equals(no))
			return "0";
		int index = no.indexOf(".");
		if (index == -1) {
			return makeMoneyType(Long.parseLong(no));
		} else {
			return (makeMoneyType(Long.parseLong(no.substring(0, index))) + no
					.substring(index, no.length()));
		}
	}

	/**
	 * 
	 * @param int no
	 * @return String
	 */
	public static String makeMoneyType(int no) {
		return (makeMoneyType((new Integer(no)).longValue()));
	}

	/**
	 * 
	 * @param long no
	 * @return String
	 */
	public static String makeMoneyType(long no) {
		return NumberFormat.getInstance().format(no);
	}

	/**
	 * 
	 * @param double no
	 * @return String
	 */
	public static String makeMoneyType(double no) {
		return NumberFormat.getInstance().format(no);
	}

	/**
	 * 
	 * @param float no
	 * @return String
	 */
	public static String makeMoneyType(float no) {
		return (makeMoneyType((new Float(no)).doubleValue()));
	}
	
	
	/**
	 * Star Rating
	 * 
	 * @param ratePoint 점수
	 * @param fullPoint 최고점
	 * @param starLength 별총개수
	 * @return
	 */
	public static String makeStarRating(String ratePoint, int fullPoint, int starLength) {
		
		BigDecimal bd = new BigDecimal( ratePoint );
		long point  = bd.longValue();
		
		int grade = Math.round(fullPoint / starLength); //Grade
		int fullStar = Math.round(point / grade); //Full Star
		int halfStar = Math.round(point % grade); //Half Star
		
		String str = "";
		
		//"●◐○"
		for (int i=0; i<fullStar; i++)
			str += "<i class=\"fa fa-star\"></i>";
		
		for (int i=0; i<halfStar; i++)
			str += "<i class=\"fa fa-star-half-o\"></i>";
		
		for (int i=0; i<(starLength-(fullStar+halfStar)); i++)
			str += "<i class=\"fa fa-star-o\"></i>";
		
		return str;
	}
	
	/**
	 * Star Rating
	 * 
	 * @param ratePoint 점수 (최고점 : 10)
	 * @return
	 */
	public static String makeStarRating(String ratePoint) {
		return makeStarRating(ratePoint, 10, 5);
	}
	
	
	
	/**
	 * 
	 * @ param Exponential Notation Number 
	 * @ return
	 */
	public static String convertExponentialValue(String bd) {
		
		try {
			if (bd.length() < 3) {
				return bd;
			}
			String returnVal = new BigDecimal(bd).toPlainString();	
			return String.format("%,.0f", Double.parseDouble(returnVal));	
		} catch (Exception e) {
			return "0";
		}
	}
	
	
	/**
	 * 
	 * @ param Exponential Notation Number 
	 * @ return
	 */
	public static String convertExponentialValueRound(String bd) {
		
		try {
			if (bd.length() < 3) {
				return bd;
			}
			String returnVal = new BigDecimal(bd).toPlainString();	
			return String.format("%,.2f", Double.parseDouble(returnVal));	
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 
	 * @ param Exponential Notation Number 
	 * @ return
	 */
	public static String convertExponentialValueRound(String bd, int divid) {
		
		try {
			if (bd.length() < 3) {
				return bd;
			}
			BigDecimal bn1 = new BigDecimal(bd);
			BigDecimal bn2 = new BigDecimal(divid);
			String returnVal = bn1.divide(bn2, 2, BigDecimal.ROUND_HALF_UP).toPlainString();
			return String.format("%,.2f", Double.parseDouble(returnVal));	
		} catch (Exception e) {
			return "0";
		}
	}
	
}
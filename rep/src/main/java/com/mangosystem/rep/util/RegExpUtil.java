package com.mangosystem.rep.util;

import java.util.regex.Pattern;

public class RegExpUtil {
	private RegExpUtil() {
	}

	/**
	 * Pattern
	 * @param value
	 * @return
	 */
	public static boolean matchesInt(String value) {
		return Pattern.matches("^[0-9]*$", value);
	}
	
	public static boolean matchesDobule(String value) {
		return Pattern.matches("^[.0-9E]*$", value);
	}

	public static boolean matchesEng(String value) {
		return Pattern.matches("^[a-zA-Z]*$", value);
	}
	
	public static boolean matchesKor(String value) {
		return Pattern.matches("^[가-힣]*$", value);
	}
	
	public static boolean matchesNumOrEng(String value) {
		return Pattern.matches("^[0-9a-zA-Z]*$", value);
	}
	
	public static boolean matchesNumOrKor(String value) {
		return Pattern.matches("^[0-9가-힣]*$", value);
	}
	
	public static boolean matchesEmail(String value) {
		return Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$", value);
	}
	
}
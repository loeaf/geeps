package com.mangosystem.rep.web.common;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.support.MessageSourceAccessor;


@Resource(name="messageSource")
public class MessageSources {

	private static MessageSourceAccessor message = null;
	
	public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		MessageSources.message = msAcc;
	}

	public static String getMessage(String key) {
		return message.getMessage(key, Locale.getDefault());
	}

	public static String getMessage(String key, Object[] args) {
		return message.getMessage(key, args, Locale.getDefault());
	}
}
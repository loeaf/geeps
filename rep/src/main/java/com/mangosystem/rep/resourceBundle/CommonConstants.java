package com.mangosystem.rep.resourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommonConstants {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("system", Locale.getDefault());

	
	//Site main title
	public static final String  WEB_MAIN_TITLE				= resource.getString("WEB_MAIN_TITLE");
	
	//Web context path
	public static final String  WEB_CONTEXT_PATH 			= resource.getString("WEB_CONTEXT_PATH");
	
	//list paging size
	public static final int 	WEB_PAGING_DEFAULT_LIST_SIZE = Integer.parseInt(resource.getString("WEB_PAGING_DEFAULT_LIST_SIZE"));
	public static final int 	WEB_PAGING_DEFAULT_PAGE_SIZE = Integer.parseInt(resource.getString("WEB_PAGING_DEFAULT_PAGE_SIZE"));
	
	//data upload save directory
	public static final String 	WEB_DATA_UPLOAD_FILE_PATH	= resource.getString("WEB_DATA_UPLOAD_FILE_PATH");
	
	//session
	public static final String 	SESSION_IDENTIFY_INFO		= resource.getString("SESSION_IDENTIFY_INFO");
	
}
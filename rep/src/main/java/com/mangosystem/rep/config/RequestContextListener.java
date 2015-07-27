package com.mangosystem.rep.config;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

public class RequestContextListener extends org.springframework.web.context.request.RequestContextListener {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public RequestContextListener() { }
	
	
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		super.requestInitialized(requestEvent);
		
		try {
			//Mandatory
			requestEvent.getServletRequest().setCharacterEncoding("UTF-8");
			
			String lang = requestEvent.getServletRequest().getParameter("lang");
			
			if (lang!=null && !"".equals(lang)) {
				
				logger.debug("lang=" + lang);
				
				//requestEvent.getServletRequest().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver",  StringUtils.parseLocaleString(lang));
				WebUtils.setSessionAttribute(
						(HttpServletRequest) requestEvent.getServletRequest(), 
						SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, 
						StringUtils.parseLocaleString(lang) );
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
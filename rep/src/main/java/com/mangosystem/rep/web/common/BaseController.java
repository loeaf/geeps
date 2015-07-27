package com.mangosystem.rep.web.common;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


public class BaseController {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired 
	protected MessageSourceAccessor messageSourceAccessor;
	
	protected ModelAndView modelView = null;
	protected RedirectView redirectView = null;
	
	protected void download( HttpServletRequest request, HttpServletResponse response, 
			String fileName, String mimeType) throws UnsupportedEncodingException {
		
		if (mimeType == null || mimeType.length() == 0) {
			mimeType = "application/octet-stream;";
		}
	    response.setContentType(mimeType + "; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
	}
	
}
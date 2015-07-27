package com.mangosystem.rep.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mangosystem.rep.web.auth.UserDetailsDTO;
import com.mangosystem.rep.web.auth.service.AuthUserService;
import com.mangosystem.rep.web.common.BaseController;

@Controller
public class MainController extends BaseController {
	
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ModelAndView viewIndex(Principal principal, HttpServletRequest request, HttpSession session) {
		modelView = new ModelAndView("/main");
		return modelView;
	}
	
	@RequestMapping(value={"proxy"})
	public void proxyServer(@RequestParam(required = true, value = "url") String urlParam,
            HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			if (urlParam == null || urlParam.trim().length() == 0) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			boolean doPost = request.getMethod().equalsIgnoreCase("POST");
			
			if (!doPost) {
				
				Enumeration en = request.getParameterNames();
				
				while (en.hasMoreElements()) {
					String obj = (String) en.nextElement();
					urlParam = urlParam + "&" + obj + "=" + request.getParameter(obj);
					
					
				}
				
			}
			
			URL url = new URL(urlParam);
			
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				if (!key.equalsIgnoreCase("Host")) {
					http.setRequestProperty(key, request.getHeader(key));
				}
			}
			http.setDoInput(true);
			http.setDoOutput(doPost);

			byte[] buffer = new byte[8192];
			int read = -1;

			if (doPost) {
				OutputStream os = http.getOutputStream();
				ServletInputStream sis = request.getInputStream();
				while ((read = sis.read(buffer)) != -1) {
					os.write(buffer, 0, read);
				}
				os.close();
			}
			

			InputStream is = http.getInputStream();
			response.setStatus(http.getResponseCode());
			Map<String, List<String>> headerKeys = http.getHeaderFields();
			Set<String>	keySet = headerKeys.keySet();
			Iterator<String> iter = keySet.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = http.getHeaderField(key);
				if (key != null && value != null) {
					response.setHeader(key, value);
				}
			}
			ServletOutputStream sos = response.getOutputStream();
			response.resetBuffer();
			while ((read = is.read(buffer)) != -1) {
				sos.write(buffer, 0, read);
			}
			response.flushBuffer();
			sos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value={"proxy1"}, method=RequestMethod.GET)
	public void proxyServer1(@RequestParam(required = true, value = "url") String urlParam,
            HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			if (urlParam == null || urlParam.trim().length() == 0) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			boolean doPost = request.getMethod().equalsIgnoreCase("POST");
			URL url = new URL(urlParam);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				if (!key.equalsIgnoreCase("Host")) {
					http.setRequestProperty(key, request.getHeader(key));
				}
			}
			http.setDoInput(true);
			http.setDoOutput(doPost);
			byte[] buffer = new byte[8192];
			int read = -1;

			if (doPost) {
				OutputStream os = http.getOutputStream();
				ServletInputStream sis = request.getInputStream();
				while ((read = sis.read(buffer)) != -1) {
					os.write(buffer, 0, read);
				}
				os.close();
			}

			InputStream is = http.getInputStream();
			response.setStatus(http.getResponseCode());
			Map<String, List<String>> headerKeys = http.getHeaderFields();
			Set<String>	keySet = headerKeys.keySet();
			Iterator<String> iter = keySet.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = http.getHeaderField(key);
				if (key != null && value != null) {
					response.setHeader(key, value);
				}
			}
			ServletOutputStream sos = response.getOutputStream();
			response.resetBuffer();
			while ((read = is.read(buffer)) != -1) {
				sos.write(buffer, 0, read);
			}
			response.flushBuffer();
			sos.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
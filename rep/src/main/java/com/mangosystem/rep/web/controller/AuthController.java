package com.mangosystem.rep.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mangosystem.rep.web.common.BaseController;

@Controller
@RequestMapping("")
public class AuthController extends BaseController {
	
	/**
	 * Login Page
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sign", method=RequestMethod.POST)
	public @ResponseBody
	ModelAndView login(HttpServletRequest request) {
		System.out.println("AuthController login()");
		logger.info("AuthController login()");
		// @TODO login 구현해야힘
		ModelAndView modelView = new ModelAndView("redirect:/");
		request.getSession().invalidate();
		try {
			Map paramMap = request.getParameterMap();
			if (paramMap.get("userId").toString().equals("nkgis") && paramMap.get("userPw").toString().equals("nkgis")) {
				
			}
			request.getSession().setAttribute("userId", "nkgis");
			modelView.addObject("session", request.getSession());
			modelView.addObject("redirectUrl", "/");
			modelView.addObject("result", "1");
			return modelView;
		} catch (NullPointerException e) {
			return modelView;
		} catch (Exception e) {
			e.printStackTrace();
			return modelView;
		}
//		return "/auth/login_page";
	}
	
	/**
	 * Logout Page
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AuthController logout()");
		logger.info("AuthController logout()");
//		return "/auth/logout_page";
		return "/login";
	}
	
	/**
	 * Error Page
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/access_denied", method=RequestMethod.GET)
	public String accessDenied(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AuthController accessDenied()");
		return "/auth/denied_page";
	}
	
}
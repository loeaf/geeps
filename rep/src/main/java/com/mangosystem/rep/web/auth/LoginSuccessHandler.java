package com.mangosystem.rep.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mangosystem.rep.resourceBundle.CommonConstants;
import com.mangosystem.rep.web.auth.mapper.AuthUserMapper;
import com.mangosystem.rep.web.auth.service.UserDetailsVO;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private AuthUserMapper authUserMapper;
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		UserDetailsVO vo = (UserDetailsVO)authentication.getPrincipal();
		if (vo.getUsername() !=null) {
			UserDetailsDTO myInfo = authUserMapper.selectIdentify(vo.getUsername());
			request.getSession().setAttribute(CommonConstants.SESSION_IDENTIFY_INFO, myInfo);
			response.sendRedirect(request.getContextPath() + "/");
		}
		
	}
}
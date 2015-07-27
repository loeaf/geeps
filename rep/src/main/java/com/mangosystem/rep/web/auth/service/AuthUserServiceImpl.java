package com.mangosystem.rep.web.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangosystem.rep.web.auth.UserDetailsDTO;
import com.mangosystem.rep.web.auth.mapper.AuthUserMapper;
import com.mangosystem.rep.web.common.BaseService;


@Service
public class AuthUserServiceImpl extends BaseService implements AuthUserService, UserDetailsService {

	@Autowired
	private AuthUserMapper authUserMapper;
	
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		String password = authUserMapper.selectPassword(username);
		List<Map<String,Object>> roles = authUserMapper.selectAuthority(username);
		
		UserDetailsVO userVO = new UserDetailsVO(username, password);
		
		Collection<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
		for (Map<String,Object> r : roles) {
			if (r.get("authority")!=null && !"".equals(r.get("authority").toString()))
				authority.add(new SimpleGrantedAuthority( String.valueOf(r.get("authority"))));
		}
		userVO.setAuthorities(authority);
		
		return userVO;
	}

	@Override
	public int selectCode(String userId) {
		return authUserMapper.selectCode(userId);
	}

	@Override
	public String selectId(int userCode) {
		return authUserMapper.selectId(userCode);
	}

	@Override
	public String selectPassword(String username) {
		return authUserMapper.selectPassword(username);
	}

	@Override
	public UserDetailsDTO selectIdentify(String username) {
		return authUserMapper.selectIdentify(username);
	}

	@Override
	public List<Map<String, Object>> selectAuthority(String username) {
		return authUserMapper.selectAuthority(username);
	}
	
	@Override
	public List<UserDetailsDTO> selectUserList() {
		return authUserMapper.selectUserList();
	}
	
}
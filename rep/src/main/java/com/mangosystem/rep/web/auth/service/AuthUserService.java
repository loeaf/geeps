package com.mangosystem.rep.web.auth.service;

import java.util.List;
import java.util.Map;

import com.mangosystem.rep.web.auth.UserDetailsDTO;

public interface AuthUserService {

	public int selectCode(String userId);
	public String selectId(int userCode);
	public String selectPassword(String username);
	
	public UserDetailsDTO selectIdentify(String username);
	public List<Map<String, Object>> selectAuthority(String username);
	
	public List<UserDetailsDTO> selectUserList();
	
}
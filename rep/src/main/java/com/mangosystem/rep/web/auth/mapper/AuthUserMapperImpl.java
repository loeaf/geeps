package com.mangosystem.rep.web.auth.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mangosystem.rep.web.auth.UserDetailsDTO;
import com.mangosystem.rep.web.common.BaseMapper;

@Repository
public class AuthUserMapperImpl extends BaseMapper implements AuthUserMapper {

	@Override
	public String selectPassword(String username) {
		String resultStr = null;
		try {
			resultStr = (String)sqlSession.selectOne("web.mapper.auth.authMapper.selectPassword", username);
		} catch (Exception e) {
			logger.debug(e);
		}
		return resultStr;
	}
	
	@Override
	public int selectCode(String userId) {
		int resultInt = 0;
		try {
			resultInt = sqlSession.selectOne("web.mapper.auth.authMapper.selectCode", userId);
		} catch (Exception e) {
			logger.debug(e);
		}
		return resultInt;
	}
	
	@Override
	public String selectId(int userCode) {
		String resultStr = null;
		try {
			resultStr = sqlSession.selectOne("web.mapper.auth.authMapper.selectId", userCode);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return resultStr;
	}
	
	@Override
	public UserDetailsDTO selectIdentify(String username) {
		UserDetailsDTO vo = new UserDetailsDTO();
		try {
			vo = sqlSession.selectOne("web.mapper.auth.authMapper.selectIdentify", username);
		} catch(Exception e) {
			logger.debug(e);
			vo = null;
		}
		return vo;
	}
	
	@Override
	public List<Map<String, Object>> selectAuthority(String username) {
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
		try {
			resultMapList = sqlSession.selectList("web.mapper.auth.authMapper.selectAuthority", username);
		} catch (Exception e) {
			logger.debug(e);
		}
		return resultMapList;
	}

	@Override
	public List<UserDetailsDTO> selectUserList() {
		
		List<UserDetailsDTO> resultMapList = new ArrayList<UserDetailsDTO>();
		try {
			resultMapList = sqlSession.selectList("web.mapper.auth.authMapper.selectUserList");
		} catch(Exception e) {
			logger.debug(e);
		}
		return resultMapList;
	}
	
}
package com.mangosystem.rep.web.common;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseMapper {
	
	public final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected SqlSession sqlSession;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	protected boolean validateReturnValue(int value) {
		return value > 0 ? true : false;
	}
	
}
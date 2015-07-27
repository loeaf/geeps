package com.mangosystem.rep.web.layers.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mangosystem.rep.web.common.BaseMapper;

@Repository
public class ScenarioMapperImpl extends BaseMapper implements ScenarioMapper {

	@Override
	public List<HashMap<String, String>> getScenarioList(String condition) {
		List<HashMap<String, String>> sidoList = new ArrayList<HashMap<String,String>>();
		try {
			sidoList = sqlSession.selectList("web.mapper.scenario.scenarioMapper.selectScenarioList");
		} catch (Exception e) {
			logger.debug(e);
			sidoList = null;
		}
		return sidoList;
	}
}

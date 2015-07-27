package com.mangosystem.rep.web.layers.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

import com.mangosystem.rep.web.common.BaseService;
import com.mangosystem.rep.web.layers.mapper.ScenarioMapper;

@Service
public class ScenarioServiceImpl extends BaseService implements ScenarioService {

	@Autowired
	private ScenarioMapper scenarioMapper;
	
	@Override
	public String getScenarioList(String condition) {
		String returnString = "";
		try {
			List<HashMap<String, String>> sidoList = scenarioMapper.getScenarioList(condition);
			JSONArray ja = JSONArray.fromObject(sidoList);
			returnString = ja.toString();
			//System.out.println(ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}

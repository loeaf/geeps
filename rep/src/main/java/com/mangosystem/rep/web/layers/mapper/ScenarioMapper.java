package com.mangosystem.rep.web.layers.mapper;

import java.util.HashMap;
import java.util.List;


public interface ScenarioMapper {
	
	public List<HashMap<String, String>> getScenarioList(String condition);
}

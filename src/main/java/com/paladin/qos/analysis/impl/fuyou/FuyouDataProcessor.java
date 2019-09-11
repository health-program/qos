package com.paladin.qos.analysis.impl.fuyou;

import java.util.HashMap;
import java.util.Map;

import com.paladin.qos.analysis.DataProcessor;

public abstract class FuyouDataProcessor extends DataProcessor{

	private final static Map<String, String> unitMap;
	
	static {
		unitMap = new HashMap<>();
		unitMap.put("320583467170249", "018302");
		unitMap.put("320583467170257", "018303");
		unitMap.put("320583467170265", "018305");
		unitMap.put("320583467170513", "018304");
		unitMap.put("320583102100", "018334");
	}
	
	public String getMappingUnitId(String unitId) {
		return unitMap.get(unitId);
	} 

	
	
}

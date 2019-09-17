package com.paladin.qos.analysis.impl.fuyou;

import java.util.Date;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.analysis.DataProcessor;
import com.paladin.qos.analysis.RateMetadata;

public abstract class FuyouDataProcessor extends DataProcessor{

	public String getMappingUnitId(String unitId) {		
		Unit unit = DataConstantContainer.getUnit(unitId);
		return unit == null ? null : unit.getSource().getFuyouCode();	
	} 

	public RateMetadata processByDay(Date startTime, Date endTime, String unitId) {
		unitId = getMappingUnitId(unitId);
		return super.processByDay(startTime, endTime, unitId);
	}
	
}

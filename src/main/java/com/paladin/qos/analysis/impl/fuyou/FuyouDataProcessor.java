package com.paladin.qos.analysis.impl.fuyou;

import com.paladin.qos.analysis.DataConstantContainer;
import com.paladin.qos.analysis.DataConstantContainer.Unit;
import com.paladin.qos.analysis.DataProcessor;

public abstract class FuyouDataProcessor extends DataProcessor{

	public String getMappingUnitId(String unitId) {		
		Unit unit = DataConstantContainer.getUnit(unitId);
		return unit == null ? null : unit.getSource().getFuyouCode();	
	} 

}

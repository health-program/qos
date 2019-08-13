package com.paladin.qos.analysis.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.paladin.qos.analysis.DataProcessor;

@Component
public class MortalityOfHospitalizedDataPreprocessor extends DataProcessor {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String getProcessorId() {
		return "mortality_of_hospitalized";
	}

	@Override
	public long getTotalNum(Date startTime, Date endTime, String unitId) {
		System.out.println(format.format(startTime) + "到" + format.format(endTime));
		
		return 1000;
	}

	@Override
	public long getEventNum(Date startTime, Date endTime, String unitId) {
		return new Random().nextInt(1000);
	}

}

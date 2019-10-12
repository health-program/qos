package com.paladin.qos.service.analysis;

import java.util.ArrayList;
import java.util.List;

public class AnalysisConstant {
	/** 特殊单位妇幼 */
	public final static String SPECIAL_UNIT_FUYOU = "320583810343";

	public final static List<String> SPECIAL_UNITS_FUYOU;

	static {
		SPECIAL_UNITS_FUYOU = new ArrayList<>();
		SPECIAL_UNITS_FUYOU.add(SPECIAL_UNIT_FUYOU);
	}
}

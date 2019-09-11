package com.paladin.qos.dynamic.mapper;

import java.util.List;

import com.paladin.qos.dynamic.model.CountEntity;

public interface DisplayMapper {

	List<CountEntity> getOutpatientNumber();

	List<CountEntity> getEmergencyNumber();

	List<CountEntity> getPatientsNumber();

	long getPrescriptionNumber();

	double getPrescriptionMoney();

	double getMedicalMoney();

	double getDrugMoney();

	double getOtherMoney();

	double getTotalMoney();

	long getInhospitalNumber();

	String getBedUseingRate();

	long getBedInUseNumber();

	long getRatedBedNumber();

	long getOnhospitalNumber();

	long getOuthospitalNumber();

	double getAverageNumber();

	long getVisitDoctorNumber();

	double getMaxPrescriptionMoney();

	double getMinPrescriptionMoney();

	double getAvgPrescriptionMoney();

}

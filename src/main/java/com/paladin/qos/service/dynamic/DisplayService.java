package com.paladin.qos.service.dynamic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.dynamic.mapper.DisplayMapper;
import com.paladin.qos.dynamic.model.CountEntity;

@Service
public class DisplayService {
	@Autowired
	private SqlSessionContainer sqlSessionContainer;
	
	/**
	 * 门诊人次数
	 */
	public List<CountEntity> getOutpatientNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getOutpatientNumber();
	}
	
	/**
	 * 急诊人次数
	 */
	public List<CountEntity> getEmergencyNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getEmergencyNumber();
	}
	
	/**
	 * 门急诊总人次数
	 */
	public List<CountEntity> getPatientsNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getPatientsNumber();
	}
	
	
	/**
	 * 处方数量
	 */
	public long getPrescriptionNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getPrescriptionNumber();
	}
	
	/**
	 * 处方总额
	 */
	public double getPrescriptionMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getPrescriptionMoney();
	}
	
	/**
	 * 最大处方金额
	 */
	public double getMaxPrescriptionMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getMaxPrescriptionMoney();
	}
	/**
	 * 最小处方金额
	 * @return
	 */
	public double getMinPrescriptionMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getMinPrescriptionMoney();	
	}
	/**
	 * 平均处方金额
	 * @return
	 */
	public double getAvgPrescriptionMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getAvgPrescriptionMoney();
	}
	
	/**
	 * 医疗收入
	 */
	public double getMedicalMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getMedicalMoney();
	}
	
	/**
	 * 药品收入
	 */
	public double getDrugMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getDrugMoney();
	}
	
	/**
	 * 其他收入
	 */
	public double getOtherMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getOtherMoney();
	}
	
	/**
	 * 合计总收入
	 */
	public double getTotalMoney(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getTotalMoney();
	}
	
	/**
	 * 出诊医生数
	 */
	public long getVisitDoctorNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getVisitDoctorNumber();
	}
	
	/**
	 * 医生平均门急诊量
	 */
	public double getAverageNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getAverageNumber();
	}
	
	/**
	 * 住院人次数
	 */
	public long getInhospitalNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getInhospitalNumber();
	}
	
	/**
	 * 出院人次数
	 */
	public long getOuthospitalNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getOuthospitalNumber();
	}
	
	/**
	 * 在院人数
	 */
	public long getOnhospitalNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getOnhospitalNumber();
	}
	/**
	 * 额定床位
	 */
	public long getRatedBedNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getRatedBedNumber();
	}
	
	/**
	 * 使用床位
	 */
	public long getBedInUseNumber(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getBedInUseNumber();
	}
	
	/**
	 * 病床使用率
	 */
	public String getBedUseingRate(){
		DisplayMapper mapper = sqlSessionContainer.getMapper(DisplayMapper.class);
		sqlSessionContainer.setCurrentDataSource("oracle");
		return mapper.getBedUseingRate();
	}
	
}












































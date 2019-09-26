package com.paladin.qos.service.exhibition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paladin.qos.mapper.report.ReportDataMapper;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;

/**
 * <孕产妇管理>
 * 
 * @author Huangguochen
 * @create 2019/8/28 9:18
 */
@Service
public class MaternalManagementService extends BaseExhibitionDataAcquire {

	@Autowired
	private ReportDataMapper reportDataMapper;


	public List<ReportDataVO> getWcqData() {
		return reportDataMapper.findAll(new ReportDataQueryDTO());
	}
}

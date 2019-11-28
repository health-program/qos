package com.paladin.qos.service.exhibition;

import com.paladin.data.dynamic.SqlSessionContainer;
import com.paladin.qos.controller.analysis.AnalysisRequest;
import com.paladin.qos.dynamic.mapper.MaternalManagementMapper;
import com.paladin.qos.mapper.report.ReportDataMapper;
import com.paladin.qos.service.report.dto.ReportDataQueryDTO;
import com.paladin.qos.service.report.vo.ReportDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Autowired
	private SqlSessionContainer sqlSessionContainer;


	public List<ReportDataVO> getWcqData() {
		return reportDataMapper.findAll(new ReportDataQueryDTO());
	}

	public long getHighriskMaternalData(AnalysisRequest request) {
		long i = 0;
		try {
			sqlSessionContainer.setCurrentDataSource("fuyou");
			MaternalManagementMapper mapper = sqlSessionContainer.getSqlSessionTemplate().getMapper(MaternalManagementMapper.class);
			i = mapper.getHighriskMaternalNumber(request.getStartTime(),request.getEndTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}

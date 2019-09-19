package com.paladin.qos.service.familydoctor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.paladin.qos.mapper.familydoctor.FamilyDoctorPersonnelMapper;
import com.paladin.qos.mapper.familydoctor.FamilyDoctorTeamMapper;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.model.familydoctor.FamilyDoctorTeam;
import com.paladin.qos.service.familydoctor.dto.ExcelFamilyDoctorTeam;
import com.paladin.qos.service.familydoctor.vo.DataFamilyDoctorTeamVo;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorTeamVO;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.DefaultSheet;
import com.paladin.framework.excel.read.DefaultReadColumn;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ExcelReader;
import com.paladin.framework.excel.read.ReadColumn;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class FamilyDoctorTeamService extends ServiceSupport<FamilyDoctorTeam> {

    @Autowired
    private FamilyDoctorPersonnelService familyDoctorPersonnelService;
    
    @Autowired
    private FamilyDoctorPersonnelMapper familyDoctorPersonnelMapper;
    
    @Autowired
    private FamilyDoctorTeamMapper familyDoctorTeamMapper;
    
    public FamilyDoctorTeamVO countTaem(String teamName){
	return familyDoctorTeamMapper.getTeam(teamName);
    }
    
    public int removeTeam(String id) {
	if (StringUtil.isEmpty(id)) {
	    throw new BusinessException("该团队不存在");
	}
	List<FamilyDoctorPersonnel> list = familyDoctorPersonnelService.findPersonnel(id);
	if (list.size() > 0) {
	    throw new BusinessException("请先删除团队下属的人员");
	}
	return removeByPrimaryKey(id);
    }
    
    public List<DataFamilyDoctorTeamVo> teamNum(){
	List<DataFamilyDoctorTeamVo> vo = new ArrayList<DataFamilyDoctorTeamVo>();
	vo.addAll(familyDoctorTeamMapper.teamNum());
	vo.addAll(familyDoctorPersonnelMapper.personnelNum());
	return vo;
    };
    
    
    private static final List<ReadColumn> teamImportColumns = DefaultReadColumn.createReadColumn(ExcelFamilyDoctorTeam.class, null);
    
    /**
     * 导入团队信息
     * @param excelInputStream
     * @param unitId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("resource")
    @Transactional
    public ExcelImportResult importTeam(InputStream excelInputStream,String unitId) {
	XSSFWorkbook workbook;
	try {
	    workbook = new XSSFWorkbook(excelInputStream);
	} catch (IOException e1) {
	    throw new BusinessException("导入异常");
	}

	ExcelReader<ExcelFamilyDoctorTeam> reader = new ExcelReader<>(ExcelFamilyDoctorTeam.class, teamImportColumns,new DefaultSheet(workbook.getSheetAt(0)), 1);
	List<ExcelImportError> errors = new ArrayList<>();

	int i = 0;

	while (reader.hasNext()) {
	    i++;
	    if (i > 500) {
		break;
	    }
	    ExcelFamilyDoctorTeam excelTeam = null;
	    try {
		excelTeam = reader.readRow();
	    } catch (ExcelReadException e) {
		errors.add(new ExcelImportError(i, e));
		continue;
	    }
	    FamilyDoctorTeam team = new FamilyDoctorTeam();
	    SimpleBeanCopyUtil.simpleCopy(excelTeam, team);
	    
	    String teamName = team.getTeamName();
	    
	    if(StringUtil.isEmpty(teamName)){
		errors.add(new ExcelImportError(i, "团队名称不能为空"));
		continue;
	    }
	    
	    if(countTaem(teamName) != null){
		errors.add(new ExcelImportError(i, ""+teamName+":团队名称不能重复"));
		continue;
	    }
	    
	    String id = UUIDUtil.createUUID();
	    team.setId(id);
	    team.setUnitId(unitId);
	    try {
		save(team);
        	} catch (BusinessException e) {
        		errors.add(new ExcelImportError(i, e.getMessage()));
        		continue;
        	} catch (Exception e) {
        		errors.add(new ExcelImportError(i, "保存失败"));
        		continue;
        	}
	    
	}
	return new ExcelImportResult(i, errors);
    }

}
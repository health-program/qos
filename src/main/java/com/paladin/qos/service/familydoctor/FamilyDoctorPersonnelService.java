package com.paladin.qos.service.familydoctor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.qos.mapper.familydoctor.FamilyDoctorPersonnelMapper;
import com.paladin.qos.mapper.familydoctor.FamilyDoctorTeamMapper;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.service.familydoctor.dto.ExcelFamilyDoctorPersonnel;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelQuery;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorPersonnelVO;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorTeamVO;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier.SimpleBeanCopyUtil;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.excel.DefaultSheet;
import com.paladin.framework.excel.EnumContainer;
import com.paladin.framework.excel.read.DefaultReadColumn;
import com.paladin.framework.excel.read.ExcelReadException;
import com.paladin.framework.excel.read.ExcelReader;
import com.paladin.framework.excel.read.ReadColumn;
import com.paladin.framework.utils.StringUtil;
import com.paladin.framework.utils.uuid.UUIDUtil;

@Service
public class FamilyDoctorPersonnelService extends ServiceSupport<FamilyDoctorPersonnel> {
    
    @Autowired
    private FamilyDoctorPersonnelMapper familyDoctorPersonnelMapper;
    
    @Autowired
    private FamilyDoctorTeamMapper familyDoctorTeamMapper;
    

    public List<FamilyDoctorPersonnel> findPersonnel(String teamId) {
	return searchAll(new Condition(FamilyDoctorPersonnel.COLUMN_TEAM_ID,QueryType.EQUAL, teamId));
    }

    public PageResult<FamilyDoctorPersonnelVO> personnelQuery(FamilyDoctorPersonnelQuery query) {
	Page<FamilyDoctorPersonnelVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
	searchPage(query);
	return new PageResult<>(page);
    }
    
    public int countPersonnel(String name){
	return familyDoctorPersonnelMapper.countPersonnel(name);
    } 
    
    private static final List<ReadColumn> personnelImportColumns = DefaultReadColumn.createReadColumn(ExcelFamilyDoctorPersonnel.class, new EnumContainer(){

	@Override
	public String getEnumName(String type, String key) {
	    return ConstantsContainer.getTypeValue(type, key);
	}

	@Override
	public String getEnumKey(String type, String name) {
	    return ConstantsContainer.getTypeKey(type, name);
	}
	
    });
    
    /**
     * 导入团队人员信息
     * @param excelInputStream
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("resource")
    @Transactional
    public ExcelImportResult importPersonnel(InputStream excelInputStream) {
	
	XSSFWorkbook workbook;
	try {
	    workbook = new XSSFWorkbook(excelInputStream);
	} catch (IOException e1) {
	    throw new BusinessException("导入异常");
	}

	ExcelReader<ExcelFamilyDoctorPersonnel> reader = new ExcelReader<>(ExcelFamilyDoctorPersonnel.class, personnelImportColumns,new DefaultSheet(workbook.getSheetAt(0)), 1);
	List<ExcelImportError> errors = new ArrayList<>();

	int i = 0;

	while (reader.hasNext()) {
	    i++;
	    if (i > 500) {
		break;
	    }
	    ExcelFamilyDoctorPersonnel excelPersonnel = null;
	    try {
		excelPersonnel = reader.readRow();
	    } catch (ExcelReadException e) {
		errors.add(new ExcelImportError(i, e));
		continue;
	    }
	    FamilyDoctorPersonnel personnel = new FamilyDoctorPersonnel();
	    SimpleBeanCopyUtil.simpleCopy(excelPersonnel, personnel);
	    
	    if(StringUtil.isEmpty(excelPersonnel.getTeamName())){
		errors.add(new ExcelImportError(i, "团队名称不能为空"));
		continue;
	    }
	    
	    FamilyDoctorTeamVO teamVO = familyDoctorTeamMapper.getTeam(excelPersonnel.getTeamName());
	    
	    if(teamVO == null){
		errors.add(new ExcelImportError(i, "("+excelPersonnel.getTeamName()+")团队名称不存在"));
		continue;
	    }
	    
	    if(countPersonnel(personnel.getName()) > 0){
		errors.add(new ExcelImportError(i, ""+personnel.getName()+":人员名字不能重复"));
		continue;
	    }
	    
	    String id = UUIDUtil.createUUID();
	    personnel.setId(id);
	    personnel.setTeamId(teamVO.getId());
	    personnel.setUnitId(teamVO.getUnitId());
	    try {
		save(personnel);
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
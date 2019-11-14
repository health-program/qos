package com.paladin.qos.service.school;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.paladin.qos.model.school.CdcTeam;
import com.paladin.qos.service.school.dto.ExcelCdcTeam;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.ExcelImportResult;
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
public class CdcTeamService extends ServiceSupport<CdcTeam> {
    
    private static final List<ReadColumn> schoolImportColumns = DefaultReadColumn.createReadColumn(ExcelCdcTeam.class, new EnumContainer(){
 	
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
     * 导入疾控队伍信息 
     * @param excelInputStream
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("resource")
    @Transactional
    public ExcelImportResult importCdcTeam(InputStream excelInputStream) {
    XSSFWorkbook workbook;
    try {
        workbook = new XSSFWorkbook(excelInputStream);
    } catch (IOException e1) {
        throw new BusinessException("导入异常");
    }
    
    ExcelReader<ExcelCdcTeam> reader = new ExcelReader<>(ExcelCdcTeam.class, schoolImportColumns,new DefaultSheet(workbook.getSheetAt(0)), 1);
    List<ExcelImportError> errors = new ArrayList<>();
    
    int i = 0;
    
	while (reader.hasNext()) {
	    i++;
	    if (i > 500) {
		break;
	    }
	    ExcelCdcTeam excelCdcTeam = null;
	    try {
		excelCdcTeam = reader.readRow();
	    } catch (ExcelReadException e) {
		errors.add(new ExcelImportError(i, e));
		continue;
	    }

	    if (StringUtil.isEmpty(excelCdcTeam.getName())) {
		errors.add(new ExcelImportError(i, "疾控队伍名称不能为空"));
		continue;
	    }
	    
	    if (StringUtil.isEmpty(excelCdcTeam.getName())) {
		errors.add(new ExcelImportError(i, "区域不能为空"));
		continue;
	    }
	    
	    String name = excelCdcTeam.getName();	   
	    CdcTeam cdcTeam = searchOne(new Condition(CdcTeam.CDC_TEAM_NAME, QueryType.EQUAL, name));
	    
	    if (cdcTeam != null) {
		errors.add(new ExcelImportError(i, "" + name + "已存在"));
		continue;
	    }

	    try {
		CdcTeam team = new CdcTeam();
		SimpleBeanCopyUtil.simpleCopy(excelCdcTeam, team);
		String id = UUIDUtil.createUUID();
		team.setId(id);
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
package com.paladin.qos.service.familydoctor;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.framework.common.Condition;
import com.paladin.framework.common.ExcelImportResult;
import com.paladin.framework.common.ExcelImportResult.ExcelImportError;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.common.QueryType;
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
import com.paladin.qos.mapper.familydoctor.FamilyDoctorPersonnelMapper;
import com.paladin.qos.mapper.familydoctor.FamilyDoctorTeamMapper;
import com.paladin.qos.model.familydoctor.FamilyDoctorPersonnel;
import com.paladin.qos.model.familydoctor.FamilySign;
import com.paladin.qos.service.familydoctor.dto.ExcelFamilyDoctorPersonnel;
import com.paladin.qos.service.familydoctor.dto.FamilyDoctorPersonnelQuery;
import com.paladin.qos.service.familydoctor.vo.DataFamilyDoctorPersonnelVO;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorPersonnelVO;
import com.paladin.qos.service.familydoctor.vo.FamilyDoctorTeamVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignService extends ServiceSupport<FamilySign> {
    

}
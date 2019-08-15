package com.paladin.qos.service.infectIndication;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;

import com.paladin.qos.mapper.infectIndication.InfectIndicationMapper;
import com.paladin.qos.model.infectIndication.InfectIndication;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationDTO;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationQuery;
import com.paladin.qos.service.infectIndication.vo.InfectIndicationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class InfectIndicationService extends ServiceSupport<InfectIndication> {

    @Autowired
    private InfectIndicationMapper infectIndicationMapper;

    //分页列表
    public PageResult<InfectIndicationVO> searchFindPage(InfectIndicationQuery query) {
        Page<InfectIndicationVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        infectIndicationMapper.findInfectRecord(query);
        return new PageResult<>(page);
    }


    @Transactional
    public int saveInfectIndication(InfectIndicationDTO dto) {
        String infectRecordId = dto.getId();

        if (infectRecordId == null || infectRecordId.length() == 0) {
            infectRecordId = UUIDUtil.createUUID();
            dto.setId(infectRecordId);
        }
        InfectIndication infectIndication = new InfectIndication();
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(dto, infectIndication);

        //todo 取当前登录账号对应的医院
        infectIndication.setHospitalId("2");
        return save(infectIndication);
    }

    @Transactional
    public int updateInfectIndication(InfectIndicationDTO dto) {
        String infectRecordId = dto.getId();
        InfectIndication infectIndication = get(infectRecordId);
        if (null == infectIndication) {
            throw new BusinessException("没有对应需要更新的感染统计记录");
        }
        String createBy = infectIndication.getCreateUserId();
        Date createTime = infectIndication.getCreateTime();
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(dto, infectIndication);
        infectIndication.setCreateUserId(createBy);
        infectIndication.setCreateTime(createTime);
        updateModelWrap(infectIndication);
        return infectIndicationMapper.update(infectIndication);
    }

    //根据id找记录
    public InfectIndication get(String id) {
        return infectIndicationMapper.selectById(id);
    }

    //根据id删除记录
    public int delete(String id) {
        return infectIndicationMapper.deleteById(id);
    }

    //判断半年后新增
    public Boolean canAdd() throws ParseException {
        InfectIndication infectIndication=infectIndicationMapper.findRecentlyRecord();
        //todo test
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date testDate=sdf.parse("2019-2-12");
        if (null!=infectIndication){
            Date recordDate=infectIndication.getCreateTime();
            Calendar c=Calendar.getInstance();
            c.setTime(testDate);
            c.add(Calendar.MONTH,6);
            Date currentTime=new Date();
            if (c.getTime().after(currentTime)){
                return false;
            }
        }
        return true;
    }
}
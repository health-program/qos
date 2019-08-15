package com.paladin.qos.service.infectionAndComplication;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.mapper.infectionAndComplication.InfectionMapper;
import com.paladin.qos.model.infectionAndComplication.Infection;
import com.paladin.qos.service.infectionAndComplication.dto.InfectionDTO;
import com.paladin.qos.service.infectionAndComplication.dto.InfectionQuery;
import com.paladin.qos.service.infectionAndComplication.vo.InfectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class InfectionService extends ServiceSupport<Infection> {

    @Autowired
    private InfectionMapper infectionMapper;

    //分页列表
    public PageResult<InfectionVO> searchFindPage(InfectionQuery query) {
        Page<InfectionVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
        infectionMapper.findInfectRecord(query);
        return new PageResult<>(page);
    }


    @Transactional
    public int saveInfectIndication(InfectionDTO dto) {
        String infectRecordId = dto.getId();

        if (infectRecordId == null || infectRecordId.length() == 0) {
            infectRecordId = UUIDUtil.createUUID();
            dto.setId(infectRecordId);
        }
        Infection infection = new Infection();
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(dto, infection);

        //todo 取当前登录账号对应的医院
        infection.setHospitalId("2");
        return save(infection);
    }

    @Transactional
    public int updateInfectIndication(InfectionDTO dto) {
        String infectRecordId = dto.getId();
        Infection infection = get(infectRecordId);
        if (null == infection) {
            throw new BusinessException("没有对应需要更新的感染统计记录");
        }
        String createBy = infection.getCreateUserId();
        Date createTime = infection.getCreateTime();
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(dto, infection);
        infection.setCreateUserId(createBy);
        infection.setCreateTime(createTime);
        updateModelWrap(infection);
        return infectionMapper.update(infection);
    }

    //根据id找记录
    public Infection get(String id) {
        return infectionMapper.selectById(id);
    }

    //根据id删除记录
    public int delete(String id) {
        return infectionMapper.deleteById(id);
    }

    //判断半年后新增
    public Boolean canAdd() {
        Infection infection = infectionMapper.findRecentlyRecord();
        //todo test
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date testDate=sdf.parse("2019-2-13");
        if (null != infection) {
            Date recordDate = infection.getCreateTime();
            Calendar c = Calendar.getInstance();
            c.setTime(recordDate);
            c.add(Calendar.MONTH, 6);
            Date currentTime = new Date();
            if (c.getTime().after(currentTime)) {
                return false;
            }
        }
        return true;
    }
}
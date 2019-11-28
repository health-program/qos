package com.paladin.qos.service.goal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.specific.CommonUserSession;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.core.session.UserSession;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.goal.HospitalAnnualGoalMapper;
import com.paladin.qos.model.goal.HospitalAnnualGoal;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalDTO;
import com.paladin.qos.service.goal.dto.HospitalAnnualGoalQuery;
import com.paladin.qos.service.goal.vo.HospitalAnnualGoalVO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HospitalAnnualGoalService extends ServiceSupport<HospitalAnnualGoal> {

    @Autowired
    private HospitalAnnualGoalMapper hospitalAnnualGoalMapper;

    public PageResult<HospitalAnnualGoalVO> searchFindPage(HospitalAnnualGoalQuery query) {
        Page<HospitalAnnualGoalVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
        hospitalAnnualGoalMapper.findList(query);
        return new PageResult<>(page);
    }

    @Transactional
    public int deleteById(String id) {
        Map<String,Object> params=new HashMap<>();
        params.put("id", id);
        params.put("updateTime", new Date());
        params.put("updateUserId", CommonUserSession.getCurrentUserSession().getUserName());
        params.put("state", HospitalAnnualGoal.BOOLEAN_NO);
        return hospitalAnnualGoalMapper.deleteById(params);
    }

    @Transactional
    public int saveGoal(HospitalAnnualGoal model) {
        Date now=new Date();
        String user= QosUserSession.getCurrentUserSession().getUserName();
        model.setCreateTime(now);
        model.setCreateUserId(user);
        model.setUpdateTime(now);
        model.setUpdateUserId(user);
        model.setState(HospitalAnnualGoal.BOOLEAN_YES);
        return hospitalAnnualGoalMapper.insert(model);
    }

    @Transactional
    public int updateGoal(HospitalAnnualGoalDTO model, HospitalAnnualGoal oldRecord) {
        Date now=new Date();
        String updateUserId= QosUserSession.getCurrentUserSession().getUserId();
        // 先删除,为有记录可查，将原有数据更新id后，备份一下
        oldRecord.setId(UUIDUtil.createUUID());
        oldRecord.setState(HospitalAnnualGoal.BOOLEAN_NO);
        oldRecord.setUpdateTime(now);
        oldRecord.setUpdateUserId(updateUserId);
        hospitalAnnualGoalMapper.insert(oldRecord);
        // 保存
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(model, oldRecord);//保持原有ID
        oldRecord.setState(HospitalAnnualGoal.BOOLEAN_YES);
        oldRecord.setCreateTime(now);
        oldRecord.setCreateUserId(updateUserId);
        return hospitalAnnualGoalMapper.updateByPrimaryKey(oldRecord);
    }
}

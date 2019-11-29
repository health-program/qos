package com.paladin.qos.service.goal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.common.specific.CommonUserSession;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.copy.SimpleBeanCopier;
import com.paladin.framework.utils.uuid.UUIDUtil;
import com.paladin.qos.core.QosUserSession;
import com.paladin.qos.mapper.goal.HospitalMonthGoalMapper;
import com.paladin.qos.model.goal.HospitalAnnualGoal;
import com.paladin.qos.model.goal.HospitalMonthGoal;
import com.paladin.qos.service.goal.dto.HospitalGoalAnalysisQuery;
import com.paladin.qos.service.goal.dto.HospitalMonthGoalDTO;
import com.paladin.qos.service.goal.dto.HospitalMonthGoalQuery;
import com.paladin.qos.service.goal.vo.HospitalMonthGoalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HospitalMonthGoalService extends ServiceSupport<HospitalMonthGoal> {

    @Autowired
    private HospitalMonthGoalMapper hospitalMonthGoalMapper;

    public PageResult<HospitalMonthGoalVO> searchFindPage(HospitalMonthGoalQuery query) {
        Page<HospitalMonthGoalVO> page = PageHelper.offsetPage(query.getOffset(),query.getLimit());
        hospitalMonthGoalMapper.findList(query);
        return new PageResult<>(page);
    }

    @Transactional
    public int deleteById(String id) {
        Map<String,Object> params=new HashMap<>();
        params.put("id", id);
        params.put("updateTime", new Date());
        params.put("updateUserId", CommonUserSession.getCurrentUserSession().getUserName());
        params.put("state", HospitalMonthGoal.BOOLEAN_NO);
        return hospitalMonthGoalMapper.deleteById(params);
    }

    @Transactional
    public int saveGoal(HospitalMonthGoal model) {
        Date now=new Date();
        String user= QosUserSession.getCurrentUserSession().getUserName();
        model.setCreateTime(now);
        model.setCreateUserId(user);
        model.setUpdateTime(now);
        model.setUpdateUserId(user);
        model.setState(HospitalMonthGoal.BOOLEAN_YES);
        return hospitalMonthGoalMapper.insert(model);
    }

    @Transactional
    public int updateGoal(HospitalMonthGoalDTO model, HospitalMonthGoal oldRecord) {
        Date now=new Date();
        String updateUserId= QosUserSession.getCurrentUserSession().getUserId();
        // 先删除,为有记录可查，将原有数据更新id后，备份一下
        oldRecord.setId(UUIDUtil.createUUID());
        oldRecord.setState(HospitalMonthGoal.BOOLEAN_NO);
        oldRecord.setUpdateTime(now);
        oldRecord.setUpdateUserId(updateUserId);
        hospitalMonthGoalMapper.insert(oldRecord);
        // 保存
        SimpleBeanCopier.SimpleBeanCopyUtil.simpleCopy(model, oldRecord);//保持原有ID
        oldRecord.setState(HospitalAnnualGoal.BOOLEAN_YES);
        oldRecord.setCreateTime(now);
        oldRecord.setCreateUserId(updateUserId);
        return hospitalMonthGoalMapper.updateByPrimaryKey(oldRecord);
    }

    public List<HospitalMonthGoalVO> findGoalListByCondition(HospitalGoalAnalysisQuery query) {
        return hospitalMonthGoalMapper.findGoalListByCondition(query);
    }
}

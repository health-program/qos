package com.paladin.qos.mapper.infectIndication;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.infectIndication.InfectIndication;
import com.paladin.qos.service.infectIndication.dto.InfectIndicationQuery;
import com.paladin.qos.service.infectIndication.vo.InfectIndicationVO;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InfectIndicationMapper extends CustomMapper<InfectIndication>{

        //新增感染统计记录
        int insert(InfectIndication infectIndication);

        //根据查询条件搜索记录列表
        List<InfectIndicationVO> findInfectRecord(InfectIndicationQuery query);

        //根据主键查询
        InfectIndication selectById(String id);

        //更新
        int update(InfectIndication infectIndication);

        //删除
        int deleteById (String id);

        //查找所有记录
        InfectIndication findRecentlyRecord();
}
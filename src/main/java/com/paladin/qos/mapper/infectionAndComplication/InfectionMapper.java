package com.paladin.qos.mapper.infectionAndComplication;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.infectionAndComplication.Infection;
import com.paladin.qos.service.infectionAndComplication.dto.InfectionQuery;
import com.paladin.qos.service.infectionAndComplication.vo.InfectionVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InfectionMapper extends CustomMapper<Infection>{

        //新增感染统计记录
        int insert(Infection infection);

        //根据查询条件搜索记录列表
        List<InfectionVO> findInfectRecord(InfectionQuery query);

        //根据主键查询
        Infection selectById(String id);

        //更新
        int update(Infection infection);

        //删除
        int deleteById (String id);

        //查找最近一条记录
        Infection findRecentlyRecord();
}
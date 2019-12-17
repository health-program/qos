package com.paladin.qos.mapper.infectioncomplication;

import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.infectioncomplication.Infection;
import com.paladin.qos.service.infectioncomplication.dto.InfectionQuery;
import com.paladin.qos.service.infectioncomplication.vo.InfectionVO;
import io.lettuce.core.dynamic.annotation.Param;
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
        Infection findRecentlyRecord(@Param("unitId") String unitId);
        
        List<InfectionVO> infectionCount(@Param("query")InfectionQuery query);
        
        List<InfectionVO> infectionCountYaer(@Param("query")InfectionQuery query);

        public int judgeDate(@org.apache.ibatis.annotations.Param("unitId") String unitId, @org.apache.ibatis.annotations.Param("inputDate") String inputDate);
}
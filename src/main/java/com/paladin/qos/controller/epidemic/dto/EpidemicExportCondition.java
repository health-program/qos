package com.paladin.qos.controller.epidemic.dto;

import com.paladin.common.core.export.ExportCondition;
import com.paladin.qos.service.epidemic.dto.EpidemicSituationQueryDTO;

public class EpidemicExportCondition extends ExportCondition{

    private EpidemicSituationQueryDTO query;

    public EpidemicSituationQueryDTO getQuery() {
        return query;
    }

    public void setQuery(EpidemicSituationQueryDTO query) {
        this.query = query;
    }
    
}

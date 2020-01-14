package com.paladin.qos.model.school;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;

public class DiseaseManage extends BaseModel {

    public static final String DISEASE_MANAGE_CODE ="code";

    @Id
    private String id;

    private Integer code;

    private String name;

    private Integer warningValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(Integer warningValue) {
        this.warningValue = warningValue;
    }
}

package com.paladin.qos.model.goal;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public class HospitalMonthGoal extends BaseModel implements Serializable {

    private static final long serialVersionUID = 3862334613769520096L;

    @Id
    private String id;

    private String hospital;//医院

    private String eventId;//事件ID,如门诊挂号量、门诊收入。。。

    private String month;//月度

    private String basicGoal;//基本目标

    private String raiseGoal;//提升目标

    private String zoomGoal;//跃升目标

    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getBasicGoal() {
        return basicGoal;
    }

    public void setBasicGoal(String basicGoal) {
        this.basicGoal = basicGoal == null ? null : basicGoal.trim();
    }

    public String getRaiseGoal() {
        return raiseGoal;
    }

    public void setRaiseGoal(String raiseGoal) {
        this.raiseGoal = raiseGoal == null ? null : raiseGoal.trim();
    }

    public String getZoomGoal() {
        return zoomGoal;
    }

    public void setZoomGoal(String zoomGoal) {
        this.zoomGoal = zoomGoal == null ? null : zoomGoal.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}

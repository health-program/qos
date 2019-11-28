package com.paladin.qos.model.goal;

import com.paladin.framework.common.BaseModel;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_hospital_annual_goal")
public class HospitalAnnualGoal extends BaseModel implements Serializable {

    private static final long serialVersionUID = -5058162526152643177L;

    @Id
    private String id;

    private String hospital;//医院

    private String eventId;//事件ID,如门诊挂号量、门诊收入。。。

    private String annual;//年度

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

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual == null ? null : annual.trim();
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

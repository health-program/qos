package com.paladin.qos.dynamic.model.exhibition;

/**
 * <孕妇建卡>
 *
 * @author Huangguochen
 * @create 2019/9/4 8:31
 */
public class MaternalCheckup {

    private  String agencyName;

    private  String agencyNo;

    private  Long firstExaminationTimes;

    private  Long cycleExaminationTimes;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public Long getFirstExaminationTimes() {
        return firstExaminationTimes;
    }

    public void setFirstExaminationTimes(Long firstExaminationTimes) {
        this.firstExaminationTimes = firstExaminationTimes;
    }

    public Long getCycleExaminationTimes() {
        return cycleExaminationTimes;
    }

    public void setCycleExaminationTimes(Long cycleExaminationTimes) {
        this.cycleExaminationTimes = cycleExaminationTimes;
    }
}

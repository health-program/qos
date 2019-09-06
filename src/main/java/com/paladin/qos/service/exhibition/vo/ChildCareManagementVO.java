package com.paladin.qos.service.exhibition.vo;

import java.util.List;

/**
 * <儿童保健管理前台展示>
 *
 * @author Huangguochen
 * @create 2019/8/30 9:00
 */
public class ChildCareManagementVO {

    /**男性新生儿分娩数*/
    private List<DataDemonstrationVO> maleNewbornChildbirth;

    /**女性新生儿分娩数*/
    private List<DataDemonstrationVO> femaleNewbornChildbirth;

    /**儿童建卡人数*/
    private List<DataDemonstrationVO> childCard;

    /**新生儿疾病筛查人数*/
    private List<DataDemonstrationVO> neonatalDiseaseScreening;

    /**新生儿出生缺陷人数*/
    private List<DataDemonstrationVO> neonatalBirthDefects;

    /**婴幼儿死亡人数*/
    private List<DataDemonstrationVO> infantDeath;

    /**儿童入园健康检查数*/
    private List<DataDemonstrationVO> childHealthCheck;

    /**新生儿听力人数*/
    private List<DataDemonstrationVO> newbornHearing;

    public List<DataDemonstrationVO> getMaleNewbornChildbirth() {
        return maleNewbornChildbirth;
    }

    public void setMaleNewbornChildbirth(List<DataDemonstrationVO> maleNewbornChildbirth) {
        this.maleNewbornChildbirth = maleNewbornChildbirth;
    }

    public List<DataDemonstrationVO> getFemaleNewbornChildbirth() {
        return femaleNewbornChildbirth;
    }

    public void setFemaleNewbornChildbirth(List<DataDemonstrationVO> femaleNewbornChildbirth) {
        this.femaleNewbornChildbirth = femaleNewbornChildbirth;
    }

    public List<DataDemonstrationVO> getChildCard() {
        return childCard;
    }

    public void setChildCard(List<DataDemonstrationVO> childCard) {
        this.childCard = childCard;
    }

    public List<DataDemonstrationVO> getNeonatalDiseaseScreening() {
        return neonatalDiseaseScreening;
    }

    public void setNeonatalDiseaseScreening(List<DataDemonstrationVO> neonatalDiseaseScreening) {
        this.neonatalDiseaseScreening = neonatalDiseaseScreening;
    }

    public List<DataDemonstrationVO> getNeonatalBirthDefects() {
        return neonatalBirthDefects;
    }

    public void setNeonatalBirthDefects(List<DataDemonstrationVO> neonatalBirthDefects) {
        this.neonatalBirthDefects = neonatalBirthDefects;
    }

    public List<DataDemonstrationVO> getInfantDeath() {
        return infantDeath;
    }

    public void setInfantDeath(List<DataDemonstrationVO> infantDeath) {
        this.infantDeath = infantDeath;
    }

    public List<DataDemonstrationVO> getChildHealthCheck() {
        return childHealthCheck;
    }

    public void setChildHealthCheck(List<DataDemonstrationVO> childHealthCheck) {
        this.childHealthCheck = childHealthCheck;
    }

    public List<DataDemonstrationVO> getNewbornHearing() {
        return newbornHearing;
    }

    public void setNewbornHearing(List<DataDemonstrationVO> newbornHearing) {
        this.newbornHearing = newbornHearing;
    }
}

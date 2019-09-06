package com.paladin.qos.service.exhibition.vo;

import java.util.List;

/**
 * <孕产妇管理前台展示类>
 *
 * @author Huangguochen
 * @create 2019/8/29 10:11
 */
public class MaternalManagementVO {

    /**男性婚前检查*/
    private Long malePremaritalCheck;

    /**女性婚前检查*/
    private Long femalePremaritalCheck;

    /**孕前检查人次数*/
    private Long prepregnancyCheck;

    /**产妇筛查数*/
    private List<DataDemonstrationVO> maternalScreening;

    /**高危孕产妇人数*/
    private List<DataDemonstrationVO> highriskMaternal;

    /**孕产妇死亡人数*/
    private List<DataDemonstrationVO> maternalDeath;

    /**两癌筛查-宫颈癌筛查*/
    private List<DataDemonstrationVO> cervicalCancerScreening;

    /**两癌筛查-乳腺癌筛查*/
    private List<DataDemonstrationVO> breastCancerScreening;

    /**母婴阻断-梅毒检测人数*/
    private List<DataDemonstrationVO> syphilisTest;

    /**母婴阻断-梅毒感染人数*/
    private List<DataDemonstrationVO> syphilisInfection;

    /**母婴阻断-乙肝检测人数*/
    private List<DataDemonstrationVO> hepatitisBtest;

    /**母婴阻断-乙肝确定感染人次数*/
    private List<DataDemonstrationVO> hepatitisBdeterminesInfection;

    /**妇女病筛查*/
    private List<DataDemonstrationVO> womenDiseaseScreening;

    /**产后访视*/
    private List<DataDemonstrationVO> postpartumVisit;

    /**叶酸发放人次数*/
    private Long numberOfFolates;

    /**叶酸发放瓶数*/
    private Long folicAcidDispensingBottle;

    public Long getMalePremaritalCheck() {
        return malePremaritalCheck;
    }

    public void setMalePremaritalCheck(Long malePremaritalCheck) {
        this.malePremaritalCheck = malePremaritalCheck;
    }

    public Long getFemalePremaritalCheck() {
        return femalePremaritalCheck;
    }

    public void setFemalePremaritalCheck(Long femalePremaritalCheck) {
        this.femalePremaritalCheck = femalePremaritalCheck;
    }

    public Long getPrepregnancyCheck() {
        return prepregnancyCheck;
    }

    public void setPrepregnancyCheck(Long prepregnancyCheck) {
        this.prepregnancyCheck = prepregnancyCheck;
    }

    public List<DataDemonstrationVO> getMaternalScreening() {
        return maternalScreening;
    }

    public void setMaternalScreening(List<DataDemonstrationVO> maternalScreening) {
        this.maternalScreening = maternalScreening;
    }

    public List<DataDemonstrationVO> getHighriskMaternal() {
        return highriskMaternal;
    }

    public void setHighriskMaternal(List<DataDemonstrationVO> highriskMaternal) {
        this.highriskMaternal = highriskMaternal;
    }

    public List<DataDemonstrationVO> getMaternalDeath() {
        return maternalDeath;
    }

    public void setMaternalDeath(List<DataDemonstrationVO> maternalDeath) {
        this.maternalDeath = maternalDeath;
    }

    public List<DataDemonstrationVO> getCervicalCancerScreening() {
        return cervicalCancerScreening;
    }

    public void setCervicalCancerScreening(List<DataDemonstrationVO> cervicalCancerScreening) {
        this.cervicalCancerScreening = cervicalCancerScreening;
    }

    public List<DataDemonstrationVO> getBreastCancerScreening() {
        return breastCancerScreening;
    }

    public void setBreastCancerScreening(List<DataDemonstrationVO> breastCancerScreening) {
        this.breastCancerScreening = breastCancerScreening;
    }

    public List<DataDemonstrationVO> getSyphilisTest() {
        return syphilisTest;
    }

    public void setSyphilisTest(List<DataDemonstrationVO> syphilisTest) {
        this.syphilisTest = syphilisTest;
    }

    public List<DataDemonstrationVO> getSyphilisInfection() {
        return syphilisInfection;
    }

    public void setSyphilisInfection(List<DataDemonstrationVO> syphilisInfection) {
        this.syphilisInfection = syphilisInfection;
    }

    public List<DataDemonstrationVO> getHepatitisBtest() {
        return hepatitisBtest;
    }

    public void setHepatitisBtest(List<DataDemonstrationVO> hepatitisBtest) {
        this.hepatitisBtest = hepatitisBtest;
    }

    public List<DataDemonstrationVO> getHepatitisBdeterminesInfection() {
        return hepatitisBdeterminesInfection;
    }

    public void setHepatitisBdeterminesInfection(List<DataDemonstrationVO> hepatitisBdeterminesInfection) {
        this.hepatitisBdeterminesInfection = hepatitisBdeterminesInfection;
    }

    public List<DataDemonstrationVO> getWomenDiseaseScreening() {
        return womenDiseaseScreening;
    }

    public void setWomenDiseaseScreening(List<DataDemonstrationVO> womenDiseaseScreening) {
        this.womenDiseaseScreening = womenDiseaseScreening;
    }

    public List<DataDemonstrationVO> getPostpartumVisit() {
        return postpartumVisit;
    }

    public void setPostpartumVisit(List<DataDemonstrationVO> postpartumVisit) {
        this.postpartumVisit = postpartumVisit;
    }

    public Long getNumberOfFolates() {
        return numberOfFolates;
    }

    public void setNumberOfFolates(Long numberOfFolates) {
        this.numberOfFolates = numberOfFolates;
    }

    public Long getFolicAcidDispensingBottle() {
        return folicAcidDispensingBottle;
    }

    public void setFolicAcidDispensingBottle(Long folicAcidDispensingBottle) {
        this.folicAcidDispensingBottle = folicAcidDispensingBottle;
    }
}

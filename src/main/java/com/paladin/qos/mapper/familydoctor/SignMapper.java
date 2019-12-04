package com.paladin.qos.mapper.familydoctor;


import com.paladin.framework.core.configuration.mybatis.CustomMapper;
import com.paladin.qos.model.familydoctor.FamilySign;

import java.util.Date;
import java.util.List;

public interface SignMapper extends CustomMapper<FamilySign>{

    /**
     * 综合健康管理服务包签约率（免费）
     */
    public long signingNotMoneyNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);


    public long singingResidentNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);// 应签约居民数

    /**
     * 个性化服务签约率（收费）
     */
    public long singingServeNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);// 签约个性化服务数

    public long singingTotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);// 签约总数

    /** 签约机构门诊就诊率 */
    // 签约居民中心就诊人次数
    public List<String> singingAgencyOPDpersonNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    public long registerOPD(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId,
                            @org.apache.ibatis.annotations.Param("list") List<String> idcards);

    // 签约居民就诊总次数
    public List<String> singingAgencyOPDTotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    public List<String> registerOPDtotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId,
                                         @org.apache.ibatis.annotations.Param("idCard") List<String> idCard);

    public long hospitalOPDTotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId,
                                 @org.apache.ibatis.annotations.Param("idCard") List<String> idCard);

    /** 签约医生门诊就诊率 */
    public List<String> singingDoctorOPDtotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);// 签约居民家庭医生就诊总数

    public long docnameOPDnum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId,
                              @org.apache.ibatis.annotations.Param("list") List<String> idcards);

    /** 慢病签约人员管理数 */
    public long singingPersonManageNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 慢病签约人员随访数 */
    public long singingPersonFollowNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 上门服务人次数 */
    public long doorServicePersonNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 营养指导人次数 */
    public long nutritionGuidePersonNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 康复指导人次数 */
    public long recoveryGuidePersonNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 用药指导人次数 */
    public long medicationGuidePersonNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 慢病长处方服务数 */
    public long prescriptionServicNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 签约居民体检人数 */
    public long singingInspectNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 心脑血管高危人群筛查人数 */
    public long cardiovascularSiftNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 肿瘤高危人群筛查数 */
    public long tumourSiftNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 骨质疏松高危人群筛查数 */
    public long osteoporosisSiftNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** COPD高危人群筛查数 */
    public long copdSiftNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 儿童哮喘筛查数 */
    public long childhoodSsthmaSiftNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 健康自理检测评估数 */
    public long healthyselfcareSssessNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** CDSS心脑血管风险评估数 */
    public long cdssSssessNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 个性化健康信息精准推送数 */
    public long personalizedHealthPushNum(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 家庭医生签约服务协议表总数 */
    public long singingServiceTotal(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 残疾人签约率 */
    public long disabledSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 高血压患者签约率 */
    public long hypertensionSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 糖尿病患者签约率 */
    public long diabetesSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 孕产妇签约率 */
    public long maternalSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 0-6周岁儿童签约率 */
    public long childrenSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 65周岁及以上老年人签约率 */
    public long agedSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);

    /** 严重肺结核患者签约率 */
    public long tuberculosisSigningRate(@org.apache.ibatis.annotations.Param("startTime") Date startTime, @org.apache.ibatis.annotations.Param("endTime") Date endTime, @org.apache.ibatis.annotations.Param("unitId") String unitId);


}
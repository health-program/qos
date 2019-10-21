package com.paladin.qos.service.org.vo;

import com.paladin.qos.analysis.DataConstantContainer;

public class OrgPersonVO {

	private String id;

	// 机构编号
	private String unitId;

	//机构名称
	private String unitName;

	// 年度
	private Integer year;

	// 在岗人员统计
	private Integer workPersonTotal;

	// 编制人员
	private Integer formatPerson;

	// 备案制人员
	private Integer beianzhiPerson;

	// 人事代理
	private Integer renshiPerson;

	// 合同制人员
	private Integer hetongzhiPerson;
	// 退休返聘
	private Integer tuixiuPerson;
	// 卫技人员
	private Integer healthPerson;
	// 非卫技人员
	private Integer nonHealthPerson;
	// 人口数
	private Double personNumber;
	// 需配置数
	private Integer peizhiPerson;
	// 现有数
	private Integer xianyouPerson;
	// 需求
	private Integer xuqiuPerson;
	// 高级
	private Integer highPerson;
	// 中级
	private Integer middlePerson;
	// 低级
	private Integer lowPerson;
	// 员级
	private Integer yuanjiPerson;
	// 未定级
	private Integer weidingjiPerson;
	// 硕士
	private Integer masterPerson;
	// 本科
	private Integer benkePerson;
	// 大专
	private Integer dazhuanPerson;
	// 中专
	private Integer zhongzhuanPerson;
	// 30岁及以下(男)
	private Integer underThirtyM;
	// 31-40岁(男)
	private Integer thirtyFortyM;
	// 41-50岁(男)
	private Integer fortyFiftyM;
	// 51-60岁(男)
	private Integer fiftySixtyM;
	// 60岁以上(男)
	private Integer beyondSixtyM;

	// 30岁及以下(女)
	private Integer underThirtyF;
	// 31-40岁(女)
	private Integer thirtyFortyF;
	// 41-50岁(女)
	private Integer fortyFiftyF;
	// 51-60岁(女)
	private Integer fiftySixtyF;
	// 60岁以上(女)
	private Integer beyondSixtyF;
	// 站点非卫技人员
	private Integer zFeiweijiPerson;
	// 全科
	private Integer zQuankePerson;
	// 临床
	private Integer zLinchuangPerson;
	// 中医
	private Integer zZhongyiPerson;
	// 乡村医生
	private Integer zXiangcunPerson;
	// 乡镇助理
	private Integer zZhuliPerson;
	// 护理
	private Integer zHuliPerson;
	// 其他
	private Integer zQitaPerson;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getWorkPersonTotal() {
		return workPersonTotal;
	}

	public void setWorkPersonTotal(Integer workPersonTotal) {
		this.workPersonTotal = workPersonTotal;
	}

	public Integer getFormatPerson() {
		return formatPerson;
	}

	public void setFormatPerson(Integer formatPerson) {
		this.formatPerson = formatPerson;
	}

	public Integer getBeianzhiPerson() {
		return beianzhiPerson;
	}

	public void setBeianzhiPerson(Integer beianzhiPerson) {
		this.beianzhiPerson = beianzhiPerson;
	}

	public Integer getRenshiPerson() {
		return renshiPerson;
	}

	public void setRenshiPerson(Integer renshiPerson) {
		this.renshiPerson = renshiPerson;
	}

	public Integer getTuixiuPerson() {
		return tuixiuPerson;
	}

	public void setTuixiuPerson(Integer tuixiuPerson) {
		this.tuixiuPerson = tuixiuPerson;
	}

	public Integer getHealthPerson() {
		return healthPerson;
	}

	public void setHealthPerson(Integer healthPerson) {
		this.healthPerson = healthPerson;
	}

	public Integer getNonHealthPerson() {
		return nonHealthPerson;
	}

	public void setNonHealthPerson(Integer nonHealthPerson) {
		this.nonHealthPerson = nonHealthPerson;
	}

	public Double getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Double personNumber) {
		this.personNumber = personNumber;
	}

	public Integer getPeizhiPerson() {
		return peizhiPerson;
	}

	public void setPeizhiPerson(Integer peizhiPerson) {
		this.peizhiPerson = peizhiPerson;
	}

	public Integer getXianyouPerson() {
		return xianyouPerson;
	}

	public void setXianyouPerson(Integer xianyouPerson) {
		this.xianyouPerson = xianyouPerson;
	}

	public Integer getXuqiuPerson() {
		return xuqiuPerson;
	}

	public void setXuqiuPerson(Integer xuqiuPerson) {
		this.xuqiuPerson = xuqiuPerson;
	}

	public Integer getHighPerson() {
		return highPerson;
	}

	public void setHighPerson(Integer highPerson) {
		this.highPerson = highPerson;
	}

	public Integer getMiddlePerson() {
		return middlePerson;
	}

	public void setMiddlePerson(Integer middlePerson) {
		this.middlePerson = middlePerson;
	}

	public Integer getLowPerson() {
		return lowPerson;
	}

	public void setLowPerson(Integer lowPerson) {
		this.lowPerson = lowPerson;
	}

	public Integer getYuanjiPerson() {
		return yuanjiPerson;
	}

	public void setYuanjiPerson(Integer yuanjiPerson) {
		this.yuanjiPerson = yuanjiPerson;
	}

	public Integer getWeidingjiPerson() {
		return weidingjiPerson;
	}

	public void setWeidingjiPerson(Integer weidingjiPerson) {
		this.weidingjiPerson = weidingjiPerson;
	}

	public Integer getMasterPerson() {
		return masterPerson;
	}

	public void setMasterPerson(Integer masterPerson) {
		this.masterPerson = masterPerson;
	}

	public Integer getBenkePerson() {
		return benkePerson;
	}

	public void setBenkePerson(Integer benkePerson) {
		this.benkePerson = benkePerson;
	}

	public Integer getDazhuanPerson() {
		return dazhuanPerson;
	}

	public void setDazhuanPerson(Integer dazhuanPerson) {
		this.dazhuanPerson = dazhuanPerson;
	}

	public Integer getZhongzhuanPerson() {
		return zhongzhuanPerson;
	}

	public void setZhongzhuanPerson(Integer zhongzhuanPerson) {
		this.zhongzhuanPerson = zhongzhuanPerson;
	}

	public Integer getHetongzhiPerson() {
		return hetongzhiPerson;
	}

	public void setHetongzhiPerson(Integer hetongzhiPerson) {
		this.hetongzhiPerson = hetongzhiPerson;
	}

	public Integer getUnderThirtyM() {
		return underThirtyM;
	}

	public void setUnderThirtyM(Integer underThirtyM) {
		this.underThirtyM = underThirtyM;
	}

	public Integer getThirtyFortyM() {
		return thirtyFortyM;
	}

	public void setThirtyFortyM(Integer thirtyFortyM) {
		this.thirtyFortyM = thirtyFortyM;
	}

	public Integer getFortyFiftyM() {
		return fortyFiftyM;
	}

	public void setFortyFiftyM(Integer fortyFiftyM) {
		this.fortyFiftyM = fortyFiftyM;
	}

	public Integer getFiftySixtyM() {
		return fiftySixtyM;
	}

	public void setFiftySixtyM(Integer fiftySixtyM) {
		this.fiftySixtyM = fiftySixtyM;
	}

	public Integer getBeyondSixtyM() {
		return beyondSixtyM;
	}

	public void setBeyondSixtyM(Integer beyondSixtyM) {
		this.beyondSixtyM = beyondSixtyM;
	}

	public Integer getUnderThirtyF() {
		return underThirtyF;
	}

	public void setUnderThirtyF(Integer underThirtyF) {
		this.underThirtyF = underThirtyF;
	}

	public Integer getThirtyFortyF() {
		return thirtyFortyF;
	}

	public void setThirtyFortyF(Integer thirtyFortyF) {
		this.thirtyFortyF = thirtyFortyF;
	}

	public Integer getFortyFiftyF() {
		return fortyFiftyF;
	}

	public void setFortyFiftyF(Integer fortyFiftyF) {
		this.fortyFiftyF = fortyFiftyF;
	}

	public Integer getFiftySixtyF() {
		return fiftySixtyF;
	}

	public void setFiftySixtyF(Integer fiftySixtyF) {
		this.fiftySixtyF = fiftySixtyF;
	}

	public Integer getBeyondSixtyF() {
		return beyondSixtyF;
	}

	public void setBeyondSixtyF(Integer beyondSixtyF) {
		this.beyondSixtyF = beyondSixtyF;
	}

	public Integer getzFeiweijiPerson() {
		return zFeiweijiPerson;
	}

	public void setzFeiweijiPerson(Integer zFeiweijiPerson) {
		this.zFeiweijiPerson = zFeiweijiPerson;
	}

	public Integer getzQuankePerson() {
		return zQuankePerson;
	}

	public void setzQuankePerson(Integer zQuankePerson) {
		this.zQuankePerson = zQuankePerson;
	}

	public Integer getzLinchuangPerson() {
		return zLinchuangPerson;
	}

	public void setzLinchuangPerson(Integer zLinchuangPerson) {
		this.zLinchuangPerson = zLinchuangPerson;
	}

	public Integer getzZhongyiPerson() {
		return zZhongyiPerson;
	}

	public void setzZhongyiPerson(Integer zZhongyiPerson) {
		this.zZhongyiPerson = zZhongyiPerson;
	}

	public Integer getzXiangcunPerson() {
		return zXiangcunPerson;
	}

	public void setzXiangcunPerson(Integer zXiangcunPerson) {
		this.zXiangcunPerson = zXiangcunPerson;
	}

	public Integer getzZhuliPerson() {
		return zZhuliPerson;
	}

	public void setzZhuliPerson(Integer zZhuliPerson) {
		this.zZhuliPerson = zZhuliPerson;
	}

	public Integer getzHuliPerson() {
		return zHuliPerson;
	}

	public void setzHuliPerson(Integer zHuliPerson) {
		this.zHuliPerson = zHuliPerson;
	}

	public Integer getzQitaPerson() {
		return zQitaPerson;
	}

	public void setzQitaPerson(Integer zQitaPerson) {
		this.zQitaPerson = zQitaPerson;
	}

}
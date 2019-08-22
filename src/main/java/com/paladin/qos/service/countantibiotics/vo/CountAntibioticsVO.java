package com.paladin.qos.service.countantibiotics.vo;

import java.util.Date;


public class CountAntibioticsVO {
	    public String id;
	    public Integer varieties;
	    public  Double averageCost;
	    public  Double userRate;
	    public  Double rateOfTotal;
	    public  Date createTime;
	    public  String createUserId;
	    public  Date updateTime;
	    public  String updateUserId;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Integer getVarieties() {
			return varieties;
		}
		public void setVarieties(Integer varieties) {
			this.varieties = varieties;
		}
		public Double getAverageCost() {
			return averageCost;
		}
		public void setAverageCost(Double averageCost) {
			this.averageCost = averageCost;
		}
		public Double getUserRate() {
			return userRate;
		}
		public void setUserRate(Double userRate) {
			this.userRate = userRate;
		}
		public Double getRateOfTotal() {
			return rateOfTotal;
		}
		public void setRateOfTotal(Double rateOfTotal) {
			this.rateOfTotal = rateOfTotal;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getCreateUserId() {
			return createUserId;
		}
		public void setCreateUserId(String createUserId) {
			this.createUserId = createUserId;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public String getUpdateUserId() {
			return updateUserId;
		}
		public void setUpdateUserId(String updateUserId) {
			this.updateUserId = updateUserId;
		}
	    
	    
}

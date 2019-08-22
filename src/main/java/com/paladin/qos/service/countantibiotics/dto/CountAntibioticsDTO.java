package com.paladin.qos.service.countantibiotics.dto;

import com.paladin.framework.common.OffsetPage;

 
public class CountAntibioticsDTO  extends  OffsetPage{
		public String id;
	    public Integer varieties;
	    public  Double averageCost;
	    public  Double userRate;
	    public  Double rateOfTotal;
	    
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
		 
	    
}

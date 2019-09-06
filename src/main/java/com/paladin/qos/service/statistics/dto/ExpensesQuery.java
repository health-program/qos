package com.paladin.qos.service.statistics.dto;
/**   
 * @author MyKite
 * @version 2019年9月2日 上午10:16:35 
 */
public class ExpensesQuery {

    private String unitId;
    
    private String date;
    
    private String year;
    
    private String month;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    
}

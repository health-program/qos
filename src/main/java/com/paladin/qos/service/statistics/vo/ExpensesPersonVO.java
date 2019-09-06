package com.paladin.qos.service.statistics.vo;
/**   
 * @author MyKite
 * @version 2019年9月2日 上午11:01:44 
 */
public class ExpensesPersonVO {

    private String typeMonth;
    
    private int oPDCount;//门急诊人数
    
    private int inPatientCount;//住院人数

    public int getoPDCount() {
        return oPDCount;
    }

    public void setoPDCount(int oPDCount) {
        this.oPDCount = oPDCount;
    }

    public int getInPatientCount() {
        return inPatientCount;
    }

    public void setInPatientCount(int inPatientCount) {
        this.inPatientCount = inPatientCount;
    }

    public String getTypeMonth() {
        return typeMonth;
    }

    public void setTypeMonth(String typeMonth) {
        this.typeMonth = typeMonth;
    }
    
}

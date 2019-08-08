package com.paladin.qos.service.school.vo;
/**   
 * @author MyKite
 * @version 2019年7月23日 下午2:56:40 
 */
public class OrgSchoolPersonnelCountVO {

    private String id;
    
    private String parentSchoolId;//学校ID
    
    private String schoolFullName;//学校名称
    
    private int total;//学生总数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentSchoolId() {
        return parentSchoolId;
    }

    public void setParentSchoolId(String parentSchoolId) {
        this.parentSchoolId = parentSchoolId;
    }

    public String getSchoolFullName() {
        return schoolFullName;
    }

    public void setSchoolFullName(String schoolFullName) {
        this.schoolFullName = schoolFullName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}

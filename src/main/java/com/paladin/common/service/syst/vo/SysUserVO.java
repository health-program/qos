package com.paladin.common.service.syst.vo;
/**   
 * @author MyKite
 * @version 2019年7月29日 上午10:24:02 
 */
public class SysUserVO {
    private String id;
    private String name;
    private String account;
    private String roleId;
    private String createTime;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
}

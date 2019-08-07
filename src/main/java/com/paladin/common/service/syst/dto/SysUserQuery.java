package com.paladin.common.service.syst.dto;

import com.paladin.framework.common.OffsetPage;

/**   
 * @author MyKite
 * @version 2019年7月29日 上午10:18:51 
 */
public class SysUserQuery extends OffsetPage{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

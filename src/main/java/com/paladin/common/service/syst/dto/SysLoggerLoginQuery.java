package com.paladin.common.service.syst.dto;

import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.common.QueryCondition;
import com.paladin.framework.common.QueryType;

public class SysLoggerLoginQuery extends OffsetPage {

    // 登录账号
    private String account;

    @QueryCondition(type = QueryType.EQUAL)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
package com.deng.crm.commons.domain;

public class ReturnObject {


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return Message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    private String code; //处理成功失败

    private String Message;//报错信息

    private Object retData;//返回数据


}

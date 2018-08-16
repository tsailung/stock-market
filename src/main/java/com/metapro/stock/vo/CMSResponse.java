package com.metapro.stock.vo;

import java.io.Serializable;
import java.util.List;

public class CMSResponse implements Serializable {

    private boolean                     success;
    private String                      message;
    private Integer                     code;
    private List<CMSClearingPrice>      data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<CMSClearingPrice> getData() {
        return data;
    }

    public void setData(List<CMSClearingPrice> data) {
        this.data = data;
    }
}

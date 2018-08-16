package com.metapro.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockClearingPrice implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private Date clrDate;

    private BigDecimal clrPrice;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getClrDate() {
        return clrDate;
    }

    public void setClrDate(Date clrDate) {
        this.clrDate = clrDate;
    }

    public BigDecimal getClrPrice() {
        return clrPrice;
    }

    public void setClrPrice(BigDecimal clrPrice) {
        this.clrPrice = clrPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", clrDate=").append(clrDate);
        sb.append(", clrPrice=").append(clrPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
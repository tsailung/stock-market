package com.metapro.stock.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockQuote implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private BigDecimal increPer;

    private BigDecimal increase;

    private BigDecimal todayStartPri;

    private BigDecimal yestodEndPri;

    private BigDecimal nowPri;

    private BigDecimal todayMax;

    private BigDecimal todayMin;

    private BigDecimal competitivePri;

    private Integer reservePri;

    private BigDecimal traNumber;

    private BigDecimal traAmount;

    private Date reqDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getIncrePer() {
        return increPer;
    }

    public void setIncrePer(BigDecimal increPer) {
        this.increPer = increPer;
    }

    public BigDecimal getIncrease() {
        return increase;
    }

    public void setIncrease(BigDecimal increase) {
        this.increase = increase;
    }

    public BigDecimal getTodayStartPri() {
        return todayStartPri;
    }

    public void setTodayStartPri(BigDecimal todayStartPri) {
        this.todayStartPri = todayStartPri;
    }

    public BigDecimal getYestodEndPri() {
        return yestodEndPri;
    }

    public void setYestodEndPri(BigDecimal yestodEndPri) {
        this.yestodEndPri = yestodEndPri;
    }

    public BigDecimal getNowPri() {
        return nowPri;
    }

    public void setNowPri(BigDecimal nowPri) {
        this.nowPri = nowPri;
    }

    public BigDecimal getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(BigDecimal todayMax) {
        this.todayMax = todayMax;
    }

    public BigDecimal getTodayMin() {
        return todayMin;
    }

    public void setTodayMin(BigDecimal todayMin) {
        this.todayMin = todayMin;
    }

    public BigDecimal getCompetitivePri() {
        return competitivePri;
    }

    public void setCompetitivePri(BigDecimal competitivePri) {
        this.competitivePri = competitivePri;
    }

    public Integer getReservePri() {
        return reservePri;
    }

    public void setReservePri(Integer reservePri) {
        this.reservePri = reservePri;
    }

    public BigDecimal getTraNumber() {
        return traNumber;
    }

    public void setTraNumber(BigDecimal traNumber) {
        this.traNumber = traNumber;
    }

    public BigDecimal getTraAmount() {
        return traAmount;
    }

    public void setTraAmount(BigDecimal traAmount) {
        this.traAmount = traAmount;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", increPer=").append(increPer);
        sb.append(", increase=").append(increase);
        sb.append(", todayStartPri=").append(todayStartPri);
        sb.append(", yestodEndPri=").append(yestodEndPri);
        sb.append(", nowPri=").append(nowPri);
        sb.append(", todayMax=").append(todayMax);
        sb.append(", todayMin=").append(todayMin);
        sb.append(", competitivePri=").append(competitivePri);
        sb.append(", reservePri=").append(reservePri);
        sb.append(", traNumber=").append(traNumber);
        sb.append(", traAmount=").append(traAmount);
        sb.append(", reqDate=").append(reqDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
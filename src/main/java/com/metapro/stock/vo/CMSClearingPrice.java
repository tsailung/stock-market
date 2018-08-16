package com.metapro.stock.vo;

import java.io.Serializable;

public class CMSClearingPrice implements Serializable {

    private String              clsPrc;
    private String              finBuyVal;
    private String              finRpayVal;
    private String              finVal;
    private String              secuSellVol;
    private String              secuVal;
    private String              secuVol;
    private String              trdDt;
    private String              ttlVal;

    public String getClsPrc() {
        return clsPrc;
    }

    public void setClsPrc(String clsPrc) {
        this.clsPrc = clsPrc;
    }

    public String getFinBuyVal() {
        return finBuyVal;
    }

    public void setFinBuyVal(String finBuyVal) {
        this.finBuyVal = finBuyVal;
    }

    public String getFinRpayVal() {
        return finRpayVal;
    }

    public void setFinRpayVal(String finRpayVal) {
        this.finRpayVal = finRpayVal;
    }

    public String getFinVal() {
        return finVal;
    }

    public void setFinVal(String finVal) {
        this.finVal = finVal;
    }

    public String getSecuSellVol() {
        return secuSellVol;
    }

    public void setSecuSellVol(String secuSellVol) {
        this.secuSellVol = secuSellVol;
    }

    public String getSecuVal() {
        return secuVal;
    }

    public void setSecuVal(String secuVal) {
        this.secuVal = secuVal;
    }

    public String getSecuVol() {
        return secuVol;
    }

    public void setSecuVol(String secuVol) {
        this.secuVol = secuVol;
    }

    public String getTrdDt() {
        return trdDt;
    }

    public void setTrdDt(String trdDt) {
        this.trdDt = trdDt;
    }

    public String getTtlVal() {
        return ttlVal;
    }

    public void setTtlVal(String ttlVal) {
        this.ttlVal = ttlVal;
    }
}

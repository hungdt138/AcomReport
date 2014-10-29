/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.subscriber.bean;

/**
 *
 * @author hungdt
 */
public class SubBean {

    private String serviceAddr = "";
    private int mo = 0;
    private int mt = 0;

    private String dateTime = "";

    private int telcoId = 0;

    private String cmdCode = "";

    private int subActive = 0;

    private int compareSubActive = 0;

    private int increaseSub = 0;

    private int reg = 0;
    private int unreg = 0;

    private int total = 0;

    private long price = 0;

    private int otpChannel = 0;

    private int smsChannel = 0;

    public int getOtpChannel() {
        return otpChannel;
    }

    public void setOtpChannel(int otpChannel) {
        this.otpChannel = otpChannel;
    }

    public int getSmsChannel() {
        return smsChannel;
    }

    public void setSmsChannel(int smsChannel) {
        this.smsChannel = smsChannel;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getUnreg() {
        return unreg;
    }

    public void setUnreg(int unreg) {
        this.unreg = unreg;
    }

    public int getIncreaseSub() {
        return increaseSub;
    }

    public void setIncreaseSub(int increaseSub) {
        this.increaseSub = increaseSub;
    }

    public int getCompareSubActive() {
        return compareSubActive;
    }

    public void setCompareSubActive(int compareSubActive) {
        this.compareSubActive = compareSubActive;
    }

    public int getSubActive() {
        return subActive;
    }

    public void setSubActive(int subActive) {
        this.subActive = subActive;
    }

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr;
    }

    public int getMo() {
        return mo;
    }

    public void setMo(int mo) {
        this.mo = mo;
    }

    public int getMt() {
        return mt;
    }

    public void setMt(int mt) {
        this.mt = mt;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getTelcoId() {
        return telcoId;
    }

    public void setTelcoId(int telcoId) {
        this.telcoId = telcoId;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

}

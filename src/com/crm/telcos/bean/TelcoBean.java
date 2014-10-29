/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.telcos.bean;

import java.util.Date;

/**
 *
 * @author hungdt
 */
public class TelcoBean {

    private long Id = 0;
    private String isdn = "";
    private Date orderDate;
    private int chargeResult = 0;
    private String spId = "";
    private String serviceID = "";
    private String productId = "";
    private int chargeMode = 0;
    private long price = 0;
    private int telcoId = 0;
    private String thirdparty = "";

    private int totalPage = 0;
    private int totalRecord = 0;
    private int count = 0;
    private String dateCharts = "";

    public String getDateCharts() {
        return dateCharts;
    }

    public void setDateCharts(String dateCharts) {
        this.dateCharts = dateCharts;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getChargeResult() {
        return chargeResult;
    }

    public void setChargeResult(int chargeResult) {
        this.chargeResult = chargeResult;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(int chargeMode) {
        this.chargeMode = chargeMode;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getTelcoId() {
        return telcoId;
    }

    public void setTelcoId(int telcoId) {
        this.telcoId = telcoId;
    }

    public String getThirdparty() {
        return thirdparty;
    }

    public void setThirdparty(String thirdparty) {
        this.thirdparty = thirdparty;
    }

}

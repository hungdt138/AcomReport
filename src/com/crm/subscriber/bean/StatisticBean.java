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
public class StatisticBean {

    private int subActive = 0;
    private int regInDay = 0;
    private int regInMonth = 0;
    private int subInDay = 0;
    private int subInMonth = 0;

    public int getSubActive() {
        return subActive;
    }

    public void setSubActive(int subActive) {
        this.subActive = subActive;
    }

    public int getRegInDay() {
        return regInDay;
    }

    public void setRegInDay(int regInDay) {
        this.regInDay = regInDay;
    }

    public int getRegInMonth() {
        return regInMonth;
    }

    public void setRegInMonth(int regInMonth) {
        this.regInMonth = regInMonth;
    }

    public int getSubInDay() {
        return subInDay;
    }

    public void setSubInDay(int subInDay) {
        this.subInDay = subInDay;
    }

    public int getSubInMonth() {
        return subInMonth;
    }

    public void setSubInMonth(int subInMonth) {
        this.subInMonth = subInMonth;
    }

}

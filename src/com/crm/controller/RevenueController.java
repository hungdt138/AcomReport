/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.telcos.bean.TelcoBean;
import com.crm.telcos.impl.TelcosImpl;
import com.crm.user.bean.UserEntry;
import com.crm.util.StringUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author hungdt
 */
public class RevenueController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("sanluongtelco");
        if (request.getSession().getAttribute("logined") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        UserEntry u = (UserEntry) request.getSession().getAttribute("user");

        String fromDate = StringUtil.getRightString(request.getParameter("f"));
        String toDate = StringUtil.getRightString(request.getParameter("t"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        Calendar cal = Calendar.getInstance();
        if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
            fromDate = sdf1.format(cal.getTime()) + "/01/" + sdf.format(cal.getTime());
            toDate = sdf2.format(cal.getTime());
        }

        //Get list date
        List<String> lstDate = new ArrayList<String>();

        //Revenue mobifone
        List<TelcoBean> lstMobi = TelcosImpl.getChartCost(1, fromDate, toDate);
        List<String> lstMobifone = new ArrayList<String>();

        for (TelcoBean b : lstMobi) {
            lstDate.add("'" + b.getDateCharts() + "'");
            lstMobifone.add(String.valueOf(b.getPrice()));
        }

        //Revenue Vinaphone
        List<TelcoBean> lstVina = TelcosImpl.getChartCost(2, fromDate, toDate);
        List<String> lstVinaphone = new ArrayList<String>();

        for (TelcoBean b : lstVina) {

            lstVinaphone.add(String.valueOf(b.getPrice()));
        }

        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("lstMobifone", lstMobifone);
        mv.addObject("lstVinaphone", lstVinaphone);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("lstDate", lstDate);
        return mv;
    }

}

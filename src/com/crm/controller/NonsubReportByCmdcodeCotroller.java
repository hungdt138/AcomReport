/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.partner.bean.MerchantEntry;
import com.crm.partner.impl.PartnerImpl;
import com.crm.subscriber.bean.NonsubBean;
import com.crm.subscriber.imp.SubscriberOrderImpl;
import com.crm.user.bean.UserEntry;
import com.crm.util.StringUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author hungdt
 */
public class NonsubReportByCmdcodeCotroller implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("nonsubreportbycmdcode");
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
        String merchantId = StringUtil.getRightString(request.getParameter("mId"));
        String s = StringUtil.getRightString(request.getParameter("s"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));
        if (s.equalsIgnoreCase("")) {
            s = "8626";
        }
        long mId = !"".equals(merchantId) ? Long.parseLong(merchantId) : 0;

        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 0;

        if (u.getMerchantId() != 0) {
            mId = u.getMerchantId();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
            Calendar cal = Calendar.getInstance();

            fromDate = sdf.format(cal.getTime());
            toDate = sdf.format(cal.getTime());

        } else if (fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
            Calendar cal = Calendar.getInstance();
            fromDate = sdf.format(cal.getTime());
        }

        //start bieu do
        //end bieu do
        //Get list merchant
        List<MerchantEntry> lstMerchant = PartnerImpl.getMerchantCat();
        //Get list service addr
        List<String> lstSC = SubscriberOrderImpl.getListSC(tId);

        List<NonsubBean> lst = null;
        int reportFlag = 0;
        List<String> categories = new ArrayList<String>();
        List<String> mo = new ArrayList<String>();
        List<String> mt = new ArrayList<String>();

        lst = SubscriberOrderImpl.getReportNonsubByCmdCode(mId, tId, fromDate, toDate, s);

        for (NonsubBean n : lst) {
            categories.add("'"+ n.getCmdCode()+"'");
            mo.add(String.valueOf(n.getMo()));
            mt.add(String.valueOf(n.getMt()));
        }

        System.out.println(categories);
        System.out.println(mo);
        System.out.println(mt);
        mv.addObject("categories", categories);
        mv.addObject("mo", mo);
        mv.addObject("mt", mt);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("lstmerchant", lstMerchant);
        mv.addObject("lst", lst);
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("merchantId", mId);
        mv.addObject("sc", lstSC);
        mv.addObject("s", s);
        mv.addObject("tId", tId);

        return mv;
    }

}

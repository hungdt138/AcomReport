/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.partner.bean.MerchantEntry;
import com.crm.partner.impl.PartnerImpl;
import com.crm.subscriber.bean.NonsubBean;
import com.crm.subscriber.bean.SubBean;
import com.crm.subscriber.imp.SubscriberOrderImpl;
import com.crm.user.bean.UserEntry;
import com.crm.util.LogUtils;
import com.crm.util.StringUtil;
import java.text.SimpleDateFormat;
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
public class DoiSoatController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("doisoat");

        if (request.getSession().getAttribute("logined") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.action");
            return null;
        }
        

        UserEntry u = (UserEntry) request.getSession().getAttribute("user");
        LogUtils.printLog("Doi soat", "User " + u.getScreenName()+ " view doi soat.");
        String fromDate = StringUtil.getRightString(request.getParameter("f"));
        String toDate = StringUtil.getRightString(request.getParameter("t"));
        String merchantId = StringUtil.getRightString(request.getParameter("mId"));
        String type = StringUtil.getRightString(request.getParameter("type"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));

        long mId = !"".equals(merchantId) ? Long.parseLong(merchantId) : 0;
        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 1;
        
        

        //Get list merchant
        List<MerchantEntry> lstMerchant = PartnerImpl.getMerchantCat();

        List<NonsubBean> lstNonSub = null;
        List<SubBean> lstSub = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        Calendar cal = Calendar.getInstance();
        if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
            fromDate = sdf1.format(cal.getTime()) + "/01/" + sdf.format(cal.getTime());
            toDate = sdf2.format(cal.getTime());
        }

        if (type.equals("")) {
            type = "sub";
        }

        if (type.equals("sub")) {
            lstSub = SubscriberOrderImpl.getDuLieuDoiSoatSub(tId, mId, fromDate, toDate);
        } else if (type.equals("nonsub")) {
            lstNonSub = SubscriberOrderImpl.getDuLieuDoiSoatNonSub(tId, mId, fromDate, toDate);
        }

        mv.addObject("lstSub", lstSub);
        mv.addObject("lstNonsub", lstNonSub);

        mv.addObject("lstSubSize", lstSub != null ? lstSub.size() : 0);
        mv.addObject("lstNonSubSize", lstNonSub != null ? lstNonSub.size() : 0);
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("merchantId", merchantId);
        mv.addObject("tId", tId);
        mv.addObject("partner", lstMerchant);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("type", type);
        return mv;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.partner.bean.MerchantEntry;
import com.crm.partner.impl.PartnerImpl;
import com.crm.product.bean.ProductEntry;
import com.crm.product.impl.ProductImpl;
import com.crm.subscriber.bean.SubBean;
import com.crm.subscriber.imp.SubscriberOrderImpl;
import com.crm.user.bean.UserEntry;
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
public class SubStatisticByCmdCodeController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("subreportbycmdcode");
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
        String productId = StringUtil.getRightString(request.getParameter("pId"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));
        long mId = !"".equals(merchantId) ? Long.parseLong(merchantId) : 0;
        long pId = !"".equals(productId) ? Long.parseLong(productId) : 0;
        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 1;
        
        if (u.getMerchantId() != 0) {
            mId = u.getMerchantId();
        }

        List<ProductEntry> lstProductCat = ProductImpl.getProductForCategory(mId, 0, "sub");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        Calendar cal = Calendar.getInstance();
        if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
            fromDate = sdf1.format(cal.getTime()) + "/01/" + sdf.format(cal.getTime());
            toDate = sdf2.format(cal.getTime());
        }

        //Get list merchant
        List<MerchantEntry> lstMerchant = PartnerImpl.getMerchantCat();

        List<SubBean> lst = SubscriberOrderImpl.getAllSubActiveByCmd(mId, tId, fromDate, toDate, "", pId);

        mv.addObject("lst", lst);
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("lstmerchant", lstMerchant);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("merchantId", mId);
        mv.addObject("lstProductCat", lstProductCat);
        mv.addObject("productId", productId);
        mv.addObject("tId", tId);
        return mv;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.kernel.message.Constants;
import com.crm.partner.bean.MerchantEntry;
import com.crm.partner.impl.PartnerImpl;
import com.crm.product.bean.ProductEntry;
import com.crm.product.impl.ProductImpl;
import com.crm.subscriber.bean.SubscriberOrder;
import com.crm.subscriber.imp.SubscriberOrderImpl;
import com.crm.user.bean.UserEntry;
import com.crm.util.StringUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author hungdt
 */
public class CSKHController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("cskh");

        if (request.getSession().getAttribute("logined") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        UserEntry u = (UserEntry) request.getSession().getAttribute("user");

        int page = StringUtil.getRightPage(request.getParameter("p"));
        String rowNumDisplay = StringUtil.getRightString(request.getParameter("r"));
        String isdn = StringUtil.getRightString(request.getParameter("isdn"));
        String productId = StringUtil.getRightString(request.getParameter("pId"));
        String telcosServiceId = StringUtil.getRightString(request.getParameter("tsId"));
        String merchantId = StringUtil.getRightString(request.getParameter("mId"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));
        String fromDate = StringUtil.getRightString(request.getParameter("f"));
        String toDate = StringUtil.getRightString(request.getParameter("t"));
        String action = StringUtil.getRightString(request.getParameter("action"));
        
        if(action.equals("0"))
        {
            action = "";
        }
        int rowNumDisp = !"".equals(rowNumDisplay) ? Integer.parseInt(rowNumDisplay) : Constants.PAGE_SIZE;
        long pId = !"".equals(productId) ? Long.parseLong(productId) : 0;
        long mId = !"".equals(merchantId) ? Long.parseLong(merchantId) : 0;
        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 0;
        if (u.getTelcoId() != 0) {
            tId = u.getTelcoId();
        }

        if (u.getMerchantId() != 0) {
            mId = u.getMerchantId();
        }

        //Get product fill to commobox
        List<ProductEntry> lstProductCat = ProductImpl.getProductForCategory(mId, tId, "");

        //Get list merchant
        List<MerchantEntry> lstMerchant = PartnerImpl.getMerchantCat();

        //Get list log system
        List<SubscriberOrder> lstOrder = SubscriberOrderImpl.getAllLog(page, rowNumDisp, isdn, fromDate, toDate, pId, telcosServiceId, mId, tId, action);
        int totalPage = 0;
        long totalRc = 0;
        if (lstOrder.size() > 0) {
            totalPage = (int) Math.ceil(lstOrder.get(0).getTotalRecord() * 1.0 / Constants.PAGE_SIZE);
            totalRc = lstOrder.get(0).getTotalRecord();
        }

        mv.addObject("page", page);
        mv.addObject("lst", lstOrder);
        mv.addObject("lstSize", lstOrder.size());
        mv.addObject("totalPage", totalPage);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("rowDisplay", rowNumDisp);
        mv.addObject("productId", productId);
        mv.addObject("telcoServiceId", telcosServiceId);
        mv.addObject("merchantId", merchantId);
        mv.addObject("tId", telcoId);
        mv.addObject("lstProductCat", lstProductCat);
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("isdn", isdn);
        mv.addObject("partner", lstMerchant);
        mv.addObject("totalRc", totalRc);
        mv.addObject("action", action);
        return mv;
    }

}

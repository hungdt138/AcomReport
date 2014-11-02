/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.kernel.message.Constants;
import com.crm.product.bean.ProductEntry;
import com.crm.product.impl.ProductImpl;
import com.crm.telcos.bean.TelcoBean;
import com.crm.telcos.impl.TelcosImpl;
import com.crm.user.bean.UserEntry;
import com.crm.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author hungdt
 */
public class TelcoDataController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("telcodata");

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
        String isdn = StringUtil.getRightString(request.getParameter("isdn"));
        String productId = StringUtil.getRightString(request.getParameter("pId"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));
        String fromDate = StringUtil.getRightString(request.getParameter("f"));
        String toDate = StringUtil.getRightString(request.getParameter("t"));
        String rowNumDisplay = StringUtil.getRightString(request.getParameter("r"));
        String price = StringUtil.getRightString(request.getParameter("price"));
        String status = StringUtil.getRightString(request.getParameter("status"));
        
        if("".equals(price))
        {
            price = "1";
        }
        
        if ("".equals(status))
        {
            status = "5";
        }

        int rowNumDisp = !"".equals(rowNumDisplay) ? Integer.parseInt(rowNumDisplay) : Constants.PAGE_SIZE;

        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 0;
        
        List<String> lstPrice = new ArrayList<String>();
        lstPrice.add("0");
        lstPrice.add("500");
        lstPrice.add("1000");
        lstPrice.add("2000");
        lstPrice.add("3000");
        lstPrice.add("4000");
        lstPrice.add("5000");
        lstPrice.add("10000");
        lstPrice.add("15000");
        lstPrice.add("20000");
        
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add("0");
        lstStatus.add("1");

        //Get product fill to commobox
        List<ProductEntry> lstProductCat = ProductImpl.getProductForCategory(0, tId, "");

        List<TelcoBean> lst = TelcosImpl.getAllCdr(page, rowNumDisp, isdn, productId, tId, fromDate, toDate, Long.parseLong(price), Integer.parseInt(status));

        int totalPage = 0;
        long totalRc = 0;
        if (lst.size() > 0) {
            totalPage = (int) Math.ceil(lst.get(0).getTotalRecord() * 1.0 / Constants.PAGE_SIZE);
            totalRc = lst.get(0).getTotalRecord();
        }

        //user
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());

        //current
        mv.addObject("page", page);
        mv.addObject("lst", lst);
        mv.addObject("lstSize", lst.size());
        mv.addObject("totalPage", totalPage);
        mv.addObject("fromDate", fromDate);
        mv.addObject("toDate", toDate);
        mv.addObject("rowDisplay", rowNumDisp);
        mv.addObject("productId", productId);
        mv.addObject("isdn", isdn);
        mv.addObject("totalRc", totalRc);
        mv.addObject("tId", telcoId);
        mv.addObject("lstProductCat", lstProductCat);
        mv.addObject("lstPrice", lstPrice);
        mv.addObject("lstStatus", lstStatus);
        mv.addObject("price", price);
        mv.addObject("status", status);

        return mv;
    }

}

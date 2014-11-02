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
public class SubStatisticController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("substatistic");
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
		String merchantId = StringUtil.getRightString(request
				.getParameter("mId"));
		String productId = StringUtil.getRightString(request
				.getParameter("pId"));

		long mId = !"".equals(merchantId) ? Long.parseLong(merchantId) : 0;
		long pId = !"".equals(productId) ? Long.parseLong(productId) : 0;

		if (u.getMerchantId() != 0) {
			mId = u.getMerchantId();
		}

		List<ProductEntry> lstProductCat = ProductImpl.getProductForCategory(
				mId, 0, "sub");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance();
		if (fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
			fromDate = sdf1.format(cal.getTime()) + "/01/"
					+ sdf.format(cal.getTime());
			toDate = sdf2.format(cal.getTime());
		}

		// Get list merchant
		List<MerchantEntry> lstMerchant = PartnerImpl.getMerchantCat();

		// Get list date
		List<String> lstDate = new ArrayList<String>();

		// get CCU Mobifone
		List<SubBean> lst1 = SubscriberOrderImpl.getAllSubActive(mId, 1,
				fromDate, toDate, "", pId, 1);

		List<String> lstMobifone = new ArrayList<String>();

		for (SubBean s : lst1) {
			lstDate.add("'" + s.getDateTime() + "'");
			lstMobifone.add(String.valueOf(s.getTotal()));
		}

		// vina
		List<SubBean> lst2 = SubscriberOrderImpl.getAllSubActive(mId, 2,
				fromDate, toDate, "", pId, 1);
		List<String> lstVinaphone = new ArrayList<String>();
		for (SubBean s : lst2) {
			lstVinaphone.add(String.valueOf(s.getTotal()));
		}

		// Viettel
		List<SubBean> lst3 = SubscriberOrderImpl.getAllSubActive(mId, 3,
				fromDate, toDate, "", pId, 1);

		List<String> lstViettel = new ArrayList<String>();
		for (SubBean s : lst3) {
			lstViettel.add(String.valueOf(s.getTotal()));
		}
		// Vietnamobile
		List<SubBean> lst4 = SubscriberOrderImpl.getAllSubActive(mId, 4,
				fromDate, toDate, "", pId, 1);
		List<String> lstVNM = new ArrayList<String>();
		for (SubBean s : lst4) {
			lstVNM.add(String.valueOf(s.getTotal()));
		}

		mv.addObject("mobi", lst1);
		mv.addObject("vina", lst2);
		mv.addObject("viettel", lst3);
		mv.addObject("vnm", lst4);

		mv.addObject("username", u.getScreenName());
		mv.addObject("role", u.getRoleName());
		mv.addObject("lstMobifone", lstMobifone);
		mv.addObject("lstVinaphone", lstVinaphone);
		mv.addObject("lstViettel", lstViettel);
		mv.addObject("lstVNM", lstVNM);
		mv.addObject("lstDate", lstDate);
		mv.addObject("lstmerchant", lstMerchant);
		mv.addObject("fromDate", fromDate);
		mv.addObject("toDate", toDate);
		mv.addObject("merchantId", mId);
		mv.addObject("lstProductCat", lstProductCat);
		mv.addObject("productId", productId);
		return mv;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.kernel.message.Constants;
import com.crm.user.bean.UserEntry;
import com.crm.user.impl.UserImpl;
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
public class UserController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("user");
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
        String act = StringUtil.getRightString(request.getParameter("act"));
        String uid = StringUtil.getRightString(request.getParameter("uid"));
        int rowNumDisp = !"".equals(rowNumDisplay) ? Integer.parseInt(rowNumDisplay) : Constants.PAGE_SIZE;
        int userId = !"".equals(uid) ? Integer.parseInt(uid) : 0;

        if (act.equals("1")) {
            UserImpl.deActiveUser(1, userId);
        } else if (act.equals("2")) {
            UserImpl.deActiveUser(0, userId);
        }

        List<UserEntry> lstUser = UserImpl.getAllUser(page, rowNumDisp);

        int totalPage = 0;
        long totalRc = 0;
        if (lstUser.size() > 0) {
            totalPage = (int) Math.ceil(lstUser.get(0).getTotalRecord() * 1.0 / Constants.PAGE_SIZE);
            totalRc = lstUser.get(0).getTotalRecord();
        }

        mv.addObject("page", page);
        mv.addObject("lstUser", lstUser);
        mv.addObject("lstSize", lstUser.size());
        mv.addObject("rowDisplay", rowNumDisp);
        mv.addObject("totalRc", totalRc);
        mv.addObject("totalPage", totalPage);
        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());

        return mv;
    }

}

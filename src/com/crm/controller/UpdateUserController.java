/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.controller;

import com.crm.partner.bean.MerchantEntry;
import com.crm.partner.impl.PartnerImpl;
import com.crm.user.bean.RoleBean;
import com.crm.user.bean.UserEntry;
import com.crm.user.impl.UserImpl;
import com.crm.util.Encrypt;
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
public class UpdateUserController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("updateuser");

        if (request.getSession().getAttribute("logined") == null) {
            response.sendRedirect("login.action");
            return null;
        }

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.action");
            return null;
        }
        UserEntry u = (UserEntry) request.getSession().getAttribute("user");

        String act = StringUtil.getRightString(request.getParameter("act"));
        String a = StringUtil.getRightString(request.getParameter("a"));
        String username = StringUtil.getRightString(request.getParameter("u"));
        String pass = StringUtil.getRightString(request.getParameter("p"));
        String rpass = StringUtil.getRightString(request.getParameter("p1"));
        String email = StringUtil.getRightString(request.getParameter("e"));
        String partnerId = StringUtil.getRightString(request.getParameter("mId"));
        String telcoId = StringUtil.getRightString(request.getParameter("tId"));
        String roleId = StringUtil.getRightString(request.getParameter("pId"));
        String uid = StringUtil.getRightString(request.getParameter("uid"));

        int mId = !"".equals(partnerId) ? Integer.parseInt(partnerId) : 0;
        int tId = !"".equals(telcoId) ? Integer.parseInt(telcoId) : 0;
        int pId = !"".equals(roleId) ? Integer.parseInt(roleId) : 0;
        int userId = !"".equals(uid) ? Integer.parseInt(uid) : 0;
        String error = "";
        String note = "";
        UserEntry user = null;
        if (userId != 0) {
            user = UserImpl.getUserById(userId);
            mv.addObject("u", user);
        }
        if (act.equals("1") && a.equals("2")) {
            if (!pass.equals(rpass)) {
                error = "Mật khẩu không trùng nhau, vui lòng nhập lại!";
            } else {
                String passEncryp = "";
                if (!pass.equals("")) {
                    passEncryp = Encrypt.encrypt(pass);
                } else {
                    passEncryp = user.getPassword();
                }
                UserImpl.updateUser(passEncryp, email, mId, tId, pId, userId);
                note = "Tài khoản " + username + " cập nhật thành công.";
            }

            mv.addObject("tId", user.getTelcoId());
            mv.addObject("merchantId", user.getMerchantId());
            mv.addObject("roleId", user.getRoleId());

        } else if (act.equals("2") && a.equals("1")) {
            String passEncryp = Encrypt.encrypt(pass);

            if (!"".equals(username) && !"".equals(passEncryp) && pId != 0) {
                UserImpl.insertUser(username, passEncryp, email, mId, tId, pId);
                note = "Tài khoản " + username + " đã được thêm.";
            } else {
                error = "Vui lòng nhập đầy đủ thông tin.";
            }
            mv.addObject("roleId", roleId);
            mv.addObject("merchantId", partnerId);
            mv.addObject("tId", telcoId);
        }

        List<RoleBean> role1 = UserImpl.getAllRole();
        List<MerchantEntry> merchant = PartnerImpl.getMerchantCat();

        mv.addObject("username", u.getScreenName());
        mv.addObject("role", u.getRoleName());
        mv.addObject("act", act);
        mv.addObject("error", error);
        mv.addObject("partner", merchant);
        mv.addObject("role1", role1);
        mv.addObject("note", note);
        return mv;
    }

}

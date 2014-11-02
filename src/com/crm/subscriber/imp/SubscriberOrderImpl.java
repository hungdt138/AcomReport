/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.subscriber.imp;

import com.crm.kernel.sql.Database;
import com.crm.subscriber.bean.NonsubBean;
import com.crm.subscriber.bean.StatisticBean;
import com.crm.subscriber.bean.SubBean;
import com.crm.subscriber.bean.SubscriberOrder;
import com.crm.util.DateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class SubscriberOrderImpl {

    public static List<SubscriberOrder> getAllLog(int currentPage, int rowNumDisplay, String isdn, String fromDate, String toDate,
            long productId, String telcoServiceId, long merchantId, int telcoId, String action) throws Exception {
        List<SubscriberOrder> lst = new ArrayList<SubscriberOrder>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String DATE_PATTERN = "MM/dd/yyyy";
        int rowIdFirst = 0;
        if (currentPage > 1) {
            rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        }
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        SubscriberOrder order = null;

        String search = "WHERE 1 = 1 ";

        if (isdn != null && !"".equalsIgnoreCase(isdn)) {
            search += "AND o.isdn = " + isdn + " ";
        }

        if (productId != 0) {
            search += "AND o.productId = " + productId + " ";
        }

        if (telcoServiceId != null && !"".equalsIgnoreCase(telcoServiceId)) {
            search += "AND o.telcosserviceid = " + telcoServiceId + " ";
        }

        if (telcoId != 0) {
            search += "AND o.telcoId = " + telcoId + " ";
        }
        if (merchantId != 0) {
            search += "AND o.merchantId = " + merchantId + " ";
        }

        if (!"".equals(action)) {
            search += "AND o.orderType = '" + action + "' ";
        }

        if (!"".equalsIgnoreCase(fromDate) && !"".equalsIgnoreCase(toDate)) {
            search += "AND o.createDate >=  to_date('" + fromDate + " 00:00:00','MM/DD/YYYY HH24:MI:SS') AND o.createDate <= to_date('" + toDate + " 23:59:59','MM/DD/YYYY HH24:MI:SS') ";

        }

        try {
            String sqlCount = "select count(*) from subscriberOrder o inner join productEntry p"
                    + " on o.productId = p.productId "
                    + " inner join merchantEntry e"
                    + " on o.merchantId = e.merchantId " + search;
            String sql = "select * from (select o.*, p.title, p.alias_,  e.alias_ merchantTitle, row_number() over (order by o.createDate desc) r "
                    + "from subscriberOrder o inner join productEntry p\n"
                    + "       on o.productId = p.productid "
                    + "inner join merchantEntry e"
                    + " on o.merchantId = e.merchantId " + search + " ) where r > ? and r <= ?";

            connection = Database.getConnection();
            stmt = connection.prepareStatement(sqlCount);
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalRc = rs.getInt(1);
                totalPage = totalRc / rowNumDisplay;
                if (totalRc > (totalPage * rowNumDisplay)) {
                    totalPage = totalPage + 1;
                }
            }

            if (totalPage > 0) {
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, rowIdFirst);
                stmt.setInt(2, rowIdLast);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    order = new SubscriberOrder();
                    order.setOrderId(rs.getLong("orderId"));
                    order.setUserName(rs.getString("username"));
                    order.setMerchant(rs.getString("merchantTitle"));
                    order.setCreateDate(rs.getTimestamp("createDate"));
                    order.setChannel(rs.getString("channel"));
                    order.setOrderType(rs.getString("orderType"));
                    order.setIsdn(rs.getString("isdn"));
                    order.setShipTo(rs.getString("shippingto"));
                    order.setProductAlias(rs.getString("alias_"));
                    order.setProductTitle(rs.getString("title"));
                    order.setStatus(rs.getInt("status"));
                    order.setServiceAddr(rs.getString("serviceaddr"));
                    order.setDelivery_status(rs.getString("delivery_status"));
                    order.setTelcoId(rs.getInt("telcoId"));
                    order.setCause(rs.getString("cause"));
                    order.setMotype(rs.getInt("motype"));
                    order.setTotalRecord(totalRc);
                    lst.add(order);

                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }

        return lst;
    }

    public static List<NonsubBean> getReportNonsubByDate(long merchantId, int telcoId, String fromDate, String toDate, String sc) throws Exception {
        List<NonsubBean> lst = new ArrayList<NonsubBean>();

        List<Date> dates = new ArrayList<Date>();
        DateFormat formatter;

        formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = (Date) formatter.parse(fromDate);
        Date endDate = (Date) formatter.parse(toDate);
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        Collections.reverse(dates);
        NonsubBean bean = null;
        DateFormat formatter1 = new SimpleDateFormat("dd-MM");
        for (int i = 0; i < dates.size(); i++) {
            bean = new NonsubBean();
            Date lDate = (Date) dates.get(i);
            String ds = formatter1.format(lDate);
            bean.setDateTime(ds);
            bean.setMo(0);
            bean.setMt(0);
            lst.add(bean);
        }

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String search = "";
        if (merchantId != 0) {
            search += "and o.merchantId = " + merchantId;
        }
        if (telcoId != 0) {
            search += "and o.telcoid = " + telcoId;
        }

//        if (merchantId != 0 && telcoId != 0) {
//            search += "and o.merchantId = " + merchantId + " and o.telcoId = " + telcoId;
//        }
        try {
            String sql = "select TO_CHAR(o.createDate, 'DD-MM') as datecreate, "
                    + " count(CASE WHEN o.channel != 'core' and o.orderType = 'register' THEN 0 END) mo_reg,\n "
                    + "        count(CASE WHEN o.channel != 'core' and o.orderType = 'unregister'  THEN 0 END) mo_unreg, "
                    + "       count(CASE WHEN o.channel != 'core' and o.orderType = 'confirm' THEN 0 END) mo_answer, "
                    + " sum(o.deliveryCounter) as mt  "
                    + " from subscriberOrder o inner join productEntry p on o.productid = p.productId \n"
                    + "       where o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "       and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "       and p.productType = 'nonsub'"
                    + "       and o.serviceAddr = ?\n"
                    + search
                    + "      group by TO_CHAR(o.createDate, 'DD-MM')\n"
                    + "       order by max(o.createDate) desc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(3, sc);
            stmt.setString(1, fromDate + " 00:00:00");
            stmt.setString(2, toDate + " 23:59:59");

            rs = stmt.executeQuery();

            while (rs.next()) {
                for (NonsubBean nonsub : lst) {
                    if (nonsub.getDateTime().equals(rs.getString("datecreate"))) {
                        nonsub.setMo(rs.getInt("mo_reg") + rs.getInt("mo_answer"));
                        nonsub.setMo_req(rs.getInt("mo_reg"));
                        nonsub.setMo_confirm(rs.getInt("mo_answer"));
                        nonsub.setMo_unreg(rs.getInt("mo_unreg"));
                        nonsub.setMt(rs.getInt("mt"));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return lst;
    }

    public static List<NonsubBean> getReportNonsubByHour(long merchantId, int telcoId, String fromDate, String toDate, String sc) throws Exception {
        List<NonsubBean> lst = new ArrayList<NonsubBean>();
        NonsubBean bean = null;
        for (int i = 0; i < 24; i++) {
            bean = new NonsubBean();
            bean.setDateTime(String.valueOf(i));
            bean.setMo(0);
            bean.setMt(0);
            lst.add(bean);
        }
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String search = "";
        if (merchantId != 0) {
            search += "and o.merchantId = " + merchantId;
        }
        if (telcoId != 0) {
            search += "and o.telcoid = " + telcoId;
        }

//        if (merchantId != 0 && telcoId != 0) {
//            search += "and o.merchantId = " + merchantId + " and o.telcoId = " + telcoId;
//        }
        try {
            String sql = "select TO_CHAR(o.createDate, 'HH24') datecreate, "
                    + " count(CASE WHEN o.channel != 'core' and o.orderType = 'register' THEN 0 END) mo_reg, \n "
                    + "        count(CASE WHEN o.channel != 'core' and o.orderType = 'unregister'  THEN 0 END) mo_unreg, "
                    + "       count(CASE WHEN o.channel != 'core' and o.orderType = 'confirm' THEN 0 END) mo_answer, "
                    + "sum(o.deliveryCounter) as mt "
                    + " from subscriberOrder o inner join productEntry p on o.productId = p.productId \n"
                    + "       where  o.serviceAddr = ?\n"
                    + "       and o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "       and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "       and p.productType = 'nonsub'"
                    + search
                    + "      group by TO_CHAR(o.createDate, 'HH24')\n"
                    + "       order by max(o.createDate) asc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");
            stmt.setString(1, sc);

            rs = stmt.executeQuery();

            while (rs.next()) {
                for (NonsubBean nonsub : lst) {
                    if (Integer.parseInt(nonsub.getDateTime()) == Integer.parseInt(rs.getString("datecreate"))) {
                        nonsub.setDateTime(nonsub.getDateTime());
                        nonsub.setMo(rs.getInt("mo_reg") + rs.getInt("mo_answer"));
                        nonsub.setMo_req(rs.getInt("mo_reg"));
                        nonsub.setMo_confirm(rs.getInt("mo_answer"));
                        nonsub.setMo_unreg(rs.getInt("mo_unreg"));
                        nonsub.setMt(rs.getInt("mt"));
                    }
                }

            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return lst;
    }

    public static List<NonsubBean> getReportNonsubByCmdCode(long merchantId, int telcoId, String fromDate, String toDate, String sc) throws Exception {
        List<NonsubBean> lst = new ArrayList<NonsubBean>();
        NonsubBean bean = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String search = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        if (telcoId != 0) {
            search += "and p.telcoid = " + telcoId;
        }

//        if (merchantId != 0 && telcoId != 0) {
//            search += "and p.merchantId = " + merchantId + " and p.telcoId = " + telcoId;
//        }
        try {
            String sql = "select p.cmdCode from productEntry p where p.productType = 'nonsub' "
                    + search;
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bean = new NonsubBean();
                bean.setCmdCode(rs.getString("cmdCode"));
                bean.setMo(0);
                bean.setMt(0);
                lst.add(bean);
            }

            String sql1 = "select p.cmdCode, "
                    + "count(CASE WHEN o.channel != 'core' and o.orderType = 'register' THEN 0 END) mo_reg,\n"
                    + "        count(CASE WHEN o.channel != 'core' and o.orderType = 'unregister'  THEN 0 END) mo_unreg, "
                    + "       count(CASE WHEN o.channel != 'core' and o.orderType = 'confirm' THEN 0 END) mo_answer,"
                    + "sum(o.deliveryCounter) as mt "
                    + "from subscriberOrder o inner join productEntry p\n"
                    + "    on o.productId = p.productid\n"
                    + "    where o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and p.productType = 'nonsub'\n"
                    + search
                    + "    and p.serviceaddr = ?\n"
                    + "    group by p.cmdcode\n"
                    + "    order by p.cmdcode desc";
            stmt = connection.prepareStatement(sql1);
            stmt.setString(1, fromDate + " 00:00:00");
            stmt.setString(2, toDate + " 23:59:59");
            stmt.setString(3, sc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                for (NonsubBean n : lst) {
                    if (n.getCmdCode().equalsIgnoreCase(rs.getString("cmdCode"))) {
                        n.setMo(rs.getInt("mo_reg") + rs.getInt("mo_answer"));
                        n.setMo_req(rs.getInt("mo_reg"));
                        n.setMo_unreg(rs.getInt("mo_unreg"));
                        n.setMo_confirm(rs.getInt("mo_answer"));
                        n.setMt(rs.getInt("mt"));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }

        return lst;
    }

    public static List<String> getListSC(int telcoId) throws Exception {
        List<String> lst = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (telcoId != 0) {
            search = "and telcoId = " + telcoId;
        }
        try {
            String sql = "select serviceAddr from productEntry "
                    + "where productType = 'nonsub' "
                    + search
                    + "group by serviceAddr order by serviceAddr desc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                lst.add(rs.getString("serviceAddr"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return lst;
    }

    public static List<SubBean> reportByTime(long merchantId, int telcoId, String fromDate, String toDate, String sc) {
        List<SubBean> lst = new ArrayList<SubBean>();
        SubBean bean = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select  count(*) from subscriberProduct s inner join productEntry p\n"
                    + "    on s.productId = p.productId\n"
                    + "    where  s.registerDate <= to_date('03/09/2014 23:59:59','DD/MM/YYYY HH24:MI:SS')\n"
                    + "    and p.telcoId = 1\n"
                    + "    and s.supplierstatus = 1";
        } catch (Exception e) {
        }
        return lst;
    }

    public static List<SubBean> getAllSubActive(long merchantId, int telcoId, String fromDate, String toDate, String sc, long productId, int type) throws Exception {
        List<SubBean> lst = new ArrayList<SubBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Date> dates = new ArrayList<Date>();
        DateFormat formatter;
        if (type == 1) {
            formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = (Date) formatter.parse(fromDate);
            Date endDate = (Date) formatter.parse(toDate);
            long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
            long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            //Collections.reverse(dates);
            SubBean sub = null;
            DateFormat formatter1 = new SimpleDateFormat("dd-MM");
            for (int i = 0; i < dates.size(); i++) {
                sub = new SubBean();
                Date lDate = (Date) dates.get(i);
                String ds = formatter1.format(lDate);
                sub.setDateTime(ds);
                sub.setSubActive(0);
                sub.setReg(0);
                sub.setUnreg(0);
                lst.add(sub);
            }
        } else if (type == 2) {
            SubBean sub = null;
            for (int i = 0; i < 24; i++) {
                sub = new SubBean();
                sub.setDateTime(String.valueOf(i));
                sub.setSubActive(0);
                sub.setReg(0);
                sub.setUnreg(0);
                lst.add(sub);
            }
        }

        String search = "";
        String search2 = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        if (!sc.equalsIgnoreCase("")) {
            search += " and p.serviceaddr = " + sc;
        }

        if (productId != 0) {
            search += "and p.productId = " + productId;
        }
        String sql = "";
        try {
            if (type == 1) {
                sql = "SELECT to_char(o.createDate, 'DD-MM') regday,\n"
                        + "          count(CASE WHEN o.ordertype = 'unregister' THEN 0 END) unreg,\n"
                        + "          count(CASE WHEN o.ordertype = 'register' THEN 0 END) reg,\n"
                        + "          (count(CASE WHEN o.ordertype = 'register' THEN 0 END) -  count(CASE WHEN o.ordertype = 'unregister' THEN 0 END)) total,\n"
                        + "   count(CASE WHEN o.orderType = 'sub-req' THEN 0 END) otp,\n"
                        + "   (count(CASE WHEN o.ordertype = 'register' THEN 0 END) - count(CASE WHEN o.orderType = 'sub-req' THEN 0 END)) sms"
                        + "   FROM subscriberOrder o\n"
                        + "   INNER JOIN productEntry p ON o.productId = p.productId\n"
                        + "   WHERE o.telcoId = ?\n"
                        + "   AND p.productType = 'sub'\n"
                        + "     AND o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                        + "     AND o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS') \n"
                        + "     AND o.cause = 'success'\n"
                        + search
                        + "   GROUP BY TO_CHAR(o.createDate, 'DD-MM')\n"
                        + "   ORDER BY max(o.createDate) ASC";
            } else if (type == 2) {
                sql = "SELECT to_char(o.createDate, 'HH24') regday,\n"
                        + "          count(CASE WHEN o.ordertype = 'unregister' THEN 0 END) unreg,\n"
                        + "          count(CASE WHEN o.ordertype = 'register' THEN 0 END) reg,\n"
                        + "          (count(CASE WHEN o.ordertype = 'register' THEN 0 END) -  count(CASE WHEN o.ordertype = 'unregister' THEN 0 END)) total, \n"
                        + "   count(CASE WHEN o.orderType = 'sub-req' THEN 0 END) otp,\n"
                        + "   (count(CASE WHEN o.ordertype = 'register' THEN 0 END) - count(CASE WHEN o.orderType = 'sub-req' THEN 0 END)) sms"
                        + "   FROM subscriberOrder o\n"
                        + "   INNER JOIN productEntry p ON o.productId = p.productId\n"
                        + "   WHERE o.telcoId = ?\n"
                        + "   AND p.productType = 'sub'\n"
                        + "     AND o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                        + "     AND o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS') \n"
                        + "     AND o.cause = 'success'\n"
                        + search
                        + "   GROUP BY TO_CHAR(o.createDate, 'HH24')\n"
                        + "   ORDER BY max(o.createDate) ASC";

            }

            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");

            rs = stmt.executeQuery();

            while (rs.next()) {
                for (SubBean s : lst) {
                    if (type == 2) {
                        if (Integer.parseInt(s.getDateTime()) == Integer.parseInt(rs.getString("regday"))) {
                            s.setReg(rs.getInt("reg"));
                            s.setUnreg(rs.getInt("unreg"));
                            s.setTotal(rs.getInt("total"));
                            s.setOtpChannel(rs.getInt("otp"));
                            s.setSmsChannel(rs.getInt("sms"));
                        }
                    } else {
                        if (s.getDateTime().equalsIgnoreCase(rs.getString("regday"))) {
                            s.setReg(rs.getInt("reg"));
                            s.setUnreg(rs.getInt("unreg"));
                            s.setTotal(rs.getInt("total"));
                            s.setOtpChannel(rs.getInt("otp"));
                            s.setSmsChannel(rs.getInt("sms"));
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return lst;
    }

    public static List<SubBean> getAllSubActiveByCmd(long merchantId, int telcoId, String fromDate, String toDate, String sc, long productId) throws Exception {
        List<SubBean> lst = new ArrayList<SubBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String search = "";
        String search2 = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        if (!sc.equalsIgnoreCase("")) {
            search += " and p.serviceaddr = " + sc;
        }

        if (productId != 0) {
            search += "and p.productId = " + productId;
        }
        String sql = "";

        try {
            sql = "SELECT p.cmdcode,\n"
                    + "          count(CASE WHEN o.ordertype = 'unregister' THEN 0 END) unreg,\n"
                    + "          count(CASE WHEN o.ordertype = 'register' THEN 0 END) reg,\n"
                    + "          (count(CASE WHEN o.ordertype = 'register' THEN 0 END) -  count(CASE WHEN o.ordertype = 'unregister' THEN 0 END)) total,\n"
                    + "   count(CASE WHEN o.orderType = 'sub-req' THEN 0 END) otp,\n"
                    + "   (count(CASE WHEN o.ordertype = 'register' THEN 0 END) - count(CASE WHEN o.orderType = 'sub-req' THEN 0 END)) sms"
                    + "   FROM subscriberOrder o\n"
                    + "   INNER JOIN productEntry p ON o.productId = p.productId\n"
                    + "   WHERE o.telcoId = ?\n"
                    + "   AND p.productType = 'sub'\n"
                    + "     AND o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "     AND o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS') \n"
                    + "     AND o.cause = 'success'\n"
                    + search
                    + "   GROUP BY p.cmdcode \n"
                    + "   ORDER BY max(o.createDate) ASC";

            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");

            rs = stmt.executeQuery();
            SubBean sub = null;
            while (rs.next()) {
                sub = new SubBean();
                sub.setCmdCode(rs.getString("cmdcode"));
                sub.setReg(rs.getInt("reg"));
                sub.setUnreg(rs.getInt("unreg"));
                sub.setTotal(rs.getInt("total"));
                sub.setOtpChannel(rs.getInt("otp"));
                sub.setSmsChannel(rs.getInt("sms"));
                lst.add(sub);
            }
        }  catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }

        return lst;
    }

    public static int countAllRegisterByTime(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        try {
            String sql = "select count(*) from subscriberOrder o inner join productEntry p\n"
                    + "    on o.productId = p.productId\n"
                    + "    where p.productType = 'sub'\n"
                    + "    and p.telcoId = ?\n"
                    + search
                    + "    and o.orderType = 'register'\n"
                    + "    and o.cause= 'success'\n"
                    + "    and o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')";

            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(stmt);
            Database.closeObject(rs);
        }

        return count;
    }

    public static StatisticBean subStatistic(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        StatisticBean st = new StatisticBean();
        int subActive = SubscriberProductImpl.countAllSub(telcoId, merchantId);

        int regInDay = SubscriberOrderImpl.countAllRegisterByTime(telcoId, merchantId, toDate, toDate);

        int regInMonth = SubscriberOrderImpl.countAllRegisterByTime(telcoId, merchantId, fromDate, toDate);

        List<SubBean> lst = getAllSubActive(merchantId, telcoId, fromDate, toDate, "", 0, 1);
        int total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        for (SubBean sub : lst) {
            total = total + sub.getTotal();
            if (sub.getDateTime().equalsIgnoreCase(sdf.format(Calendar.getInstance().getTime()))) {
                st.setSubInDay(sub.getTotal());
            }
        }

        st.setSubActive(subActive);
        st.setRegInDay(regInDay);
        st.setRegInMonth(regInMonth);

        st.setSubInMonth(total);

        return st;
    }

    public static int countMONonsubByTime(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        try {
            String sql = "select count(*) from subscriberOrder o inner join productEntry p\n"
                    + "    on o.productId = p.productId\n"
                    + "    where p.productType = 'nonsub'\n"
                    + "    and p.telcoId = ?\n"
                    + search
                    + "    and (o.orderType = 'register' or o.orderType = 'confirm')\n"
                    + "    and (o.cause = 'success' or o.cause = 'registered' or o.cause = 'correct' or o.cause = 'wrong')  \n"
                    + "    and o.orderDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and o.orderDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')";

            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(stmt);
            Database.closeObject(rs);
        }

        return count;
    }

    public static StatisticBean nonSubStatistic(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        StatisticBean st = new StatisticBean();

        int nonSubActive = SubscriberProductImpl.countAllSub(telcoId, merchantId);

        int regInDay = SubscriberOrderImpl.countMONonsubByTime(telcoId, merchantId, toDate, toDate);

        int regInMonth = SubscriberOrderImpl.countMONonsubByTime(telcoId, merchantId, fromDate, toDate);

        st.setRegInDay(regInDay);
        st.setRegInMonth(regInMonth);
        st.setSubActive(nonSubActive);

        return st;
    }

    public static List<SubBean> getDuLieuDoiSoatSub(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        List<SubBean> lst = new ArrayList<SubBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        String sql = "";
        try {
            sql = "select p.price, count(CASE WHEN o.ordertype = 'register' THEN 0 END) MO,\n"
                    + "count(CASE WHEN o.ordertype = 'send-mt' THEN 0 END) MT,"
                    + "   count(CASE WHEN o.orderType = 'sub-req' THEN 0 END) otp,\n"
                    + "   (count(CASE WHEN o.ordertype = 'register' THEN 0 END) - count(CASE WHEN o.orderType = 'sub-req' THEN 0 END)) sms"
                    + " from subscriberOrder o inner join productEntry p\n"
                    + "    on o.productId = p.productId\n"
                    + "    where p.telcoId = ?\n"
                    + "    and p.productType = 'sub'\n"
                    + search
                    + "   -- and o.orderType = 'register'\n"
                    + "    and o.cause = 'success'\n"
                    + "    and o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    group by p.price";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");
            rs = stmt.executeQuery();
            SubBean bean;

            while (rs.next()) {
                bean = new SubBean();
                bean.setMo(rs.getInt("MO"));
                bean.setMt(rs.getInt("MT"));
                bean.setPrice(rs.getLong("price"));
                bean.setOtpChannel(rs.getInt("otp"));
                bean.setSmsChannel(rs.getInt("sms"));
                lst.add(bean);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(stmt);
            Database.closeObject(rs);
        }

        return lst;
    }

    public static List<NonsubBean> getDuLieuDoiSoatNonSub(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        List<NonsubBean> lst = new ArrayList<NonsubBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and p.merchantId = " + merchantId;
        }
        String sql = "";
        try {
            sql = "select p.price, count(CASE WHEN o.ordertype = 'register' THEN 0 END) MO,\n"
                    + "count(CASE WHEN o.ordertype = 'confirm' THEN 0 END) moconfirm,\n"
                    + "count(CASE WHEN o.ordertype = 'smsdaily' THEN 0 END) mtdaily from subscriberOrder o inner join productEntry p\n"
                    + "    on o.productId = p.productId\n"
                    + "    where p.telcoId = ?\n"
                    + "    and p.productType = 'nonsub'\n"
                    + search
                    + "   -- and o.orderType = 'register'\n"
                    + "    and o.cause = 'success'\n"
                    + "    and o.createDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and o.createDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    group by p.price";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");
            rs = stmt.executeQuery();
            NonsubBean bean;

            while (rs.next()) {
                bean = new NonsubBean();
                bean.setMo(rs.getInt("MO"));
                int mtconfirm = rs.getInt("moconfirm");
                int mtdaily = rs.getInt("mtdaily");
                int mt = bean.getMo() * 3 + mtconfirm + mtdaily;
                bean.setMt(mt);
                bean.setPrice(rs.getLong("price"));
                lst.add(bean);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(stmt);
            Database.closeObject(rs);
        }

        return lst;
    }

    public static List<SubBean> statisticSubByPrice(long merchantId, int telcoId, String fromDate, String toDate, String sc) {
        List<SubBean> lst = new ArrayList<SubBean>();

        return lst;
    }

}

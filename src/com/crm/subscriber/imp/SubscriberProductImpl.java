/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.subscriber.imp;

import com.crm.kernel.sql.Database;
import com.crm.subscriber.bean.SubscriberCountBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class SubscriberProductImpl {

    public static List<String> getTotal(String fromDate, String toDate, int telcoId, long merchantId) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
        List<String> lst = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SubscriberCountBean sub = null;
        String search = "";
        if (merchantId != 0) {
            search = " and p.merchantId = " + merchantId + " ";
        }
        try {
            String sql = "select trunc(p.registerDate) registerDate, count(*) as total from subscriberProduct p inner join Productentry e\n"
                    + "       on p.productId = e.productId\n"
                    + "       where p.registerDate >= to_date(?,'DD/MM/YYYY') \n"
                    + "       and p.registerDate < to_date(?,'DD/MM/YYYY')\n"
                    + "       and e.telcoId = ?\n"
                    + "       and p.supplierstatus = 1\n"
                    + search
                    + "       group by trunc(p.registerDate)\n"
                    + "       order by trunc(p.registerDate) asc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);
            stmt.setInt(3, telcoId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("total"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(rs);
            Database.closeObject(stmt);
        }
        return lst;
    }

    public static List<String> getTotalDate(String fromDate, String toDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        List<String> lst = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SubscriberCountBean sub = null;
        try {
            String sql = "select trunc(registerDate) as registerDate from subscriberProduct\n"
                    + "       where registerDate >= to_date(?,'DD/MM/YYYY') \n"
                    + "       and registerDate < to_date(?,'DD/MM/YYYY')\n"
                    + "       group by trunc(registerDate)\n"
                    + "       order by trunc(registerDate) asc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fromDate);
            stmt.setString(2, toDate);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lst.add("'" + sdf.format(rs.getTimestamp("registerDate")) + "'");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(connection);
            Database.closeObject(rs);
            Database.closeObject(stmt);
        }
        return lst;
    }

    public static int countAllSub(int telcoId, long merchantId) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and e.merchantId = " + merchantId;
        }
        try {
            String sql = "select count(*) as total from subscriberProduct p inner join Productentry e\n"
                    + "       on p.productId = e.productId\n"
                    + "       where e.telcoid = ?\n"
                    + search
                    + "       and p.supplierstatus = 1";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, telcoId);
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

    public static int countAllSubActiveByTime(int telcoId, long merchantId, String fromDate, String toDate) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String search = "";
        if (merchantId != 0) {
            search += "and e.merchantId = " + merchantId;
        }
        try {
            String sql = "select count(*) from subscriberProduct p inner join productEntry e\n"
                    + "    on p.productId = e.productId\n"
                    + "    where e.productType = 'sub'\n"
                    + "    and e.telcoId = ?\n"
                    + search
                    + "    and p.supplierStatus = 1\n"
                    + "    and p.registerDate >= to_date(?,'MM/DD/YYYY HH24:MI:SS')\n"
                    + "    and p.registerDate <= to_date(?,'MM/DD/YYYY HH24:MI:SS')";

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
}

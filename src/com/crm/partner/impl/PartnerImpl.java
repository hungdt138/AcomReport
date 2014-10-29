/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.partner.impl;

import com.crm.kernel.sql.Database;
import com.crm.partner.bean.MerchantEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class PartnerImpl {

    public static List<MerchantEntry> getMerchantCat() throws Exception {
        List<MerchantEntry> lst = new ArrayList<MerchantEntry>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from merchantEntry order by createDate desc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            MerchantEntry merchant = null;
            while (rs.next()) {
                merchant = new MerchantEntry();
                merchant.setMerchantId(rs.getLong("merchantId"));
                merchant.setAlias(rs.getString("alias_"));
                lst.add(merchant);
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.product.impl;

import com.crm.kernel.sql.Database;
import com.crm.product.bean.ProductEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungdt
 */
public class ProductImpl {

    public static List<ProductEntry> getProductForCategory(long merchantId, int telcoId, String type) throws Exception {
        List<ProductEntry> lst = new ArrayList<ProductEntry>();
        ProductEntry product = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String search = "where 1=1 ";

        if (merchantId != 0) {
            search += "and merchantid =" + merchantId + " ";
        }
        if (telcoId != 0) {
            search += "and telcoId =" + telcoId + " ";
        }
        if (!"".equals(type)) {
            search += "and productType ='" + type + "' ";
        }

        try {
            String sql = "select * from productEntry " + search + " order by telcoId asc";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                product = new ProductEntry();
                product.setProductId(rs.getLong("productId"));
                product.setTitle(rs.getString("title"));
                product.setAlias(rs.getString("alias_"));
                lst.add(product);
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
}

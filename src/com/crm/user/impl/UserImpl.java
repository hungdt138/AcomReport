/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.user.impl;

import com.crm.kernel.sql.Database;
import com.crm.user.bean.RoleBean;
import com.crm.user.bean.UserEntry;
import com.crm.util.Encrypt;
import com.fss.util.AppException;
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
public class UserImpl {

    private final static String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static UserEntry checkLogin(String username, String password) throws Exception {
        UserEntry u = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String hashPass = Encrypt.encrypt(password);

        try {
            String sql = "select u.*, r.* from crm_user u inner join crm_role r"
                    + " on u.roleId =r.roleId where u.username = ? and u.password = ? and u.status = 0";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashPass);

            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new UserEntry();
                u.setUserId(rs.getLong("userId"));
                u.setMerchantId(rs.getLong("merchantId"));
                u.setTelcoId(rs.getInt("telcoId"));
                u.setRoleId(rs.getInt("roleId"));
                u.setRoleName(rs.getString("name"));
                u.setScreenName(rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("error");
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return u;
    }

    public static UserEntry getUserById(int Id) throws Exception {
        UserEntry u = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select u.*, r.* from crm_user u inner join crm_role r"
                    + " on u.roleId =r.roleId where u.userid = ?";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new UserEntry();
                u.setUserId(rs.getLong("userId"));
                u.setMerchantId(rs.getLong("merchantId"));
                u.setTelcoId(rs.getInt("telcoId"));
                u.setRoleId(rs.getInt("roleId"));
                u.setRoleName(rs.getString("name"));
                u.setScreenName(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("error");
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
            Database.closeObject(rs);
        }
        return u;
    }

    public static void insertUser(String username, String pass, String email, int parnerId, int telcoId, int roleId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            String sql = "insert into crm_user(userId, createDate, password, username, email, roleId, status, merchantId, telcoId)\n"
                    + "       values (crm_common_seq.nextval,sysdate,?,?,?,?,?,?,?)";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pass);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setInt(4, roleId);
            stmt.setInt(5, 0);
            stmt.setLong(6, parnerId);
            stmt.setInt(7, telcoId);
            stmt.executeQuery();
            System.out.println("Insert user " + username + " success!");
        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
        }
    }

    public static void updateUser(String pass, String email, int parnerId, int telcoId, int roleId, long userId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            String sql = "update crm_user set email = ?, "
                    + "password = ?, "
                    + "roleId = ?, merchantId = ?, telcoId = ? where userId = ?";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            stmt.setInt(3, roleId);
            stmt.setLong(4, parnerId);
            stmt.setInt(5, telcoId);
            stmt.setLong(6, userId);
            stmt.executeQuery();
            System.out.println("Update userID " + userId + " success!");

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
        }
    }

    public static void deActiveUser(int status, long userId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            String sql = "update crm_user set status = ? where userId = ?";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, status);
            stmt.setLong(2, userId);
            stmt.executeQuery();
            System.out.println("Update status userID " + userId + " to " + status + " success!");

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
        }
    }

    public static void updateLogin(String loginIP, long userId) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            String sql = "update crm_user set logindate = sysdate, loginip = ? where userId = ?";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, loginIP);
            stmt.setLong(2, userId);
            stmt.executeQuery();
            System.out.println("userID " + userId + ": login with IP " + loginIP);

        } catch (Exception e) {
            throw e;
        } finally {
            Database.closeObject(stmt);
            Database.closeObject(connection);
        }
    }

    public static List<UserEntry> getAllUser(int currentPage, int rowNumDisplay) throws Exception {
        List<UserEntry> lst = new ArrayList<UserEntry>();
        int rowIdFirst = 0;
        if (currentPage > 1) {
            rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
        }
        int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
        int totalRc = 0;
        int totalPage = 0;

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            String sqlCount = "select count(*) from crm_user";
            String sql = "select * from (select u.*,r.name, row_number() over (order by u.createDate desc) r from crm_user u inner join crm_role r"
                    + " on u.roleId = r.roleId)"
                    + " where r > ? and r <= ? ";

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
                UserEntry u = null;
                while (rs.next()) {
                    u = new UserEntry();
                    u.setScreenName(rs.getString("username"));
                    u.setCreateDate(sdf.format(rs.getTimestamp("createdate")));
                    u.setEmail(rs.getString("email"));
                    if (rs.getTimestamp("logindate") != null) {
                        u.setLoginDate(sdf.format(rs.getTimestamp("logindate")));
                    }

                    u.setLoginIp(rs.getString("loginip"));
                    u.setUserId(rs.getInt("userId"));
                    u.setStatus(rs.getInt("status"));
                    u.setMerchantId(rs.getLong("merchantId"));
                    u.setTelcoId(rs.getInt("telcoId"));
                    u.setRoleName(rs.getString("name"));
                    u.setTotalRecord(totalRc);
                    lst.add(u);
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

    public static List<RoleBean> getAllRole() throws Exception {
        List<RoleBean> lst = new ArrayList<RoleBean>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from crm_role where status = 0";
            connection = Database.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            RoleBean role = null;
            while (rs.next()) {
                role = new RoleBean();
                role.setRoleId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("name"));
                lst.add(role);
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

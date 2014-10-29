/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.telcos.impl;

import com.crm.kernel.sql.Database;
import com.crm.telcos.bean.TelcoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author hungdt
 */
public class TelcosImpl {

	/**
	 * Lấy tất cả dữ liệu data từ nhà mạng
	 * 
	 * @param currentPage
	 * @param rowNumDisplay
	 * @param isdn
	 * @param productId
	 * @param telcoId
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception
	 */
	public static List<TelcoBean> getAllCdr(int currentPage, int rowNumDisplay,
			String isdn, String productId, int telcoId, String fromDate,
			String toDate, long price, int chargeResult) throws Exception {
		List<TelcoBean> lst = new ArrayList<TelcoBean>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int rowIdFirst = 0;
		if (currentPage > 1) {
			rowIdFirst = ((currentPage - 1) * rowNumDisplay) + 1;
		}
		int rowIdLast = rowIdFirst + (rowNumDisplay - 1);
		int totalRc = 0;
		int totalPage = 0;

		TelcoBean telco = null;
		String search = "WHERE 1 = 1 ";

		if (isdn != null && !"".equalsIgnoreCase(isdn)) {
			search += " and t.isdn = " + isdn;
		}

		if (!"".equalsIgnoreCase(productId) && !"0".equalsIgnoreCase(productId)) {
			search += " and t.productId ='" + productId + "' ";
		}

		if (telcoId != 0) {
			search += " and t.telcoId = " + telcoId;
		}

		if (price != 0) {
			search += " and t.cost = " + price;
		}
		if (chargeResult != 5) {
			if (telcoId == 1 && chargeResult == 0) {
				chargeResult = 1;
			} else if (telcoId == 1 && chargeResult == 1) {
				chargeResult = 0;
			} else if (telcoId == 2 && chargeResult == 0) {
				chargeResult = 0;
			} else if (telcoId == 2 && chargeResult == 1) {
				chargeResult = 1;
			}

			search += " and t.chargeResult = " + chargeResult;
		}

		if (!"".equalsIgnoreCase(fromDate) && !"".equalsIgnoreCase(toDate)) {
			search += " AND t.createDate >= to_date('" + fromDate
					+ " 00:00:00','MM/DD/YYYY HH24:MI:SS') "
					+ "AND t.createDate <= to_date('" + toDate
					+ " 23:59:59','MM/DD/YYYY HH24:MI:SS') ";
		}

		try {
			String sqlCount = "select count(*) from telcoCdr t " + search;
			String sql = "select * from (select row_number() over (order by t.createDate desc) r, t.isdn, "
					+ "t.timestamp, t.createDate, t.chargeResult, t.spId, t.serviceId, \n"
					+ "    t.productId, t.chargeMode, t.cost, t.telcoId, t.thirdparty   from telcoCdr t  "
					+ search + ") where r> ? and r<=? ";

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
					telco = new TelcoBean();
					telco.setChargeMode(rs.getInt("chargeMode"));
					telco.setChargeResult(rs.getInt("chargeResult"));
					telco.setIsdn(rs.getString("isdn"));
					telco.setOrderDate(rs.getTimestamp("createDate"));
					telco.setPrice(rs.getLong("cost"));
					telco.setProductId(rs.getString("productId"));
					telco.setServiceID(rs.getString("serviceId"));
					telco.setSpId(rs.getString("spid"));
					telco.setTelcoId(rs.getInt("telcoId"));
					telco.setThirdparty(rs.getString("thirdparty"));
					telco.setTotalRecord(totalRc);
					lst.add(telco);
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

	/*
	 * Biểu đồ sản lượng
	 */

	public static List<TelcoBean> getChartCost(int telcoId, String fromDate,
			String toDate) throws Exception {
		List<TelcoBean> lst = new ArrayList<TelcoBean>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Date> dates = new ArrayList<Date>();
		DateFormat formatter;

		formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = (Date) formatter.parse(fromDate);
		Date endDate = (Date) formatter.parse(toDate);
		long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
		long endTime = endDate.getTime(); // create your endtime here, possibly
											// using Calendar or Date
		long curTime = startDate.getTime();

		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}

		TelcoBean bean = null;

		DateFormat formatter1 = new SimpleDateFormat("dd-MM");

		for (int i = 0; i < dates.size(); i++) {
			bean = new TelcoBean();
			Date lDate = (Date) dates.get(i);
			String ds = formatter1.format(lDate);
			bean.setDateCharts(ds);
			lst.add(bean);
		}

		String search = "";

		if (telcoId == 1) {
			search += " Where chargeResult = 1 ";
			search += " AND telcoId = 1 and thirdparty IS NULL ";
		} else if (telcoId == 2) {
			search += " Where chargeResult = 0 ";
			search += " AND telcoId = 2 ";
		}

		if (!"".equalsIgnoreCase(fromDate) && !"".equalsIgnoreCase(toDate)) {
			search += " AND createDate >= to_date('" + fromDate
					+ " 00:00:00','MM/DD/YYYY HH24:MI:SS') "
					+ "AND createDate <= to_date('" + toDate
					+ " 23:59:59','MM/DD/YYYY HH24:MI:SS') ";
		}
		try {
			String sql = "SELECT TO_CHAR(createDate, 'DD-MM') orderDate1, SUM(cost) total \n"
					+ "FROM telcoCdr "
					+ search
					+ "GROUP BY TO_CHAR(createDate, 'DD-MM')\n"
					+ "ORDER BY max(createDate) ASC";

			connection = Database.getConnection();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				for (TelcoBean b : lst) {
					if (b.getDateCharts().equalsIgnoreCase(
							rs.getString("orderDate1"))) {
						b.setPrice(rs.getLong("total"));
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
}

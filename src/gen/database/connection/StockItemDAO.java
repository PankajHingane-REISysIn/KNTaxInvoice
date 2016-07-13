/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import gen.dto.StockItemDTO;
import gen.dto.StockItemTransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pc5
 */
public class StockItemDAO {

//    public static Map<String, String> getStockItemsFromGroupName(List<String> stockGroupNames, Boolean isIn) throws SQLException, Exception {
//	Map<String, String> mapToReturn = new HashMap<String, String>();
//	String mainQuery = "";
//	String condition = "";
//	Connection connection = null;
//	try {
//	    mainQuery = "SELECT   tblstockgroup.sg_name AS tblstockgroup_sg_name,     tblstockitem.si_id AS tblstockitem_si_id,     tblstockitem.si_name AS tblstockitem_si_name FROM     tblstockgroup tblstockgroup INNER JOIN tblstockitem tblstockitem ON tblstockgroup.sg_id = tblstockitem.si_under";
//	    if (stockGroupNames != null && stockGroupNames.size() > 0) {
//		String stkGroupList = "";
//		for (String stkGroup : stockGroupNames) {
//		    stkGroupList = "'" + stkGroup + "' ,";
//		}
//		if (stkGroupList.length() > 0) {
//		    stkGroupList = stkGroupList.substring(0, stkGroupList.length() - 2);
//		}
//
//		System.out.println("NOw you are at DAO class for sttkGrouplist" + stkGroupList);
//
//		if (isIn == null || isIn) {
//		    condition = "where tblstockgroup.sg_name in (" + stkGroupList + ")";
//		} else {
//		    condition = "where tblstockgroup.sg_name not in (" + stkGroupList + ")";
//		}
//
//	    }
//	    String query = mainQuery + condition;
//
//	    System.out.println("----------------->> query: " + query);
//	    connection = DatabaseConnection1.GetConnection();
//	    connection.setAutoCommit(false);
//	    PreparedStatement psmt = connection.prepareStatement(query);
//	    ResultSet rsSet = psmt.executeQuery();
//	    while (rsSet.next()) {
//		mapToReturn.put(rsSet.getString("tblstockitem_si_name"), rsSet.getLong("tblstockitem_si_id") + "");
//	    }
//	    rsSet.close();
//	    psmt.close();
//	    connection.commit();
//	    connection.close();
//	} catch (Exception e) {
//	    if (connection != null && !connection.isClosed()) {
//		connection.close();
//	    }
//	    e.printStackTrace();
//	    throw e;
//	}
//	return mapToReturn;
//    }
//
//    public static StockItemDTO getStockItem(String stockItemName) {
//	return null;
//    }
//
//    public static StockItemTransactionDTO getStockItemValues(String name) throws SQLException, Exception {
//
//	StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
//
//	Connection conn = null;
//	try {
//	    String str = "SELECT tblunitofmeasure.uom_symbol AS tblunitofmeasure_uom_symbol,tblstockitem.si_width AS tblstockitem_si_width,tblstockitem.si_thickness AS tblstockitem_si_thickness,tblstockitem.si_id AS tblstockitem_si_id,tblstockitem.si_alias AS tblstockitem_si_alias,tblstockitem.si_under AS tblstockitem_si_under,tblstockitem.si_length AS tblstockitem_si_length,tblstockitem.si_rate AS tblstockitem_si_rate,tblstockitem.si_openingBalance AS tblstockitem_si_openingBalance,tblstockitem.si_unitOfMeasure AS tblstockitem_si_unitOfMeasure,tblstockitem.si_unit AS tblstockitem_si_unit,tblstockitem.si_type AS tblstockitem_si_type,tblstockitem.si_name AS tblstockitem_si_name FROM tblunitofmeasure tblunitofmeasure INNER JOIN tblstockitem tblstockitem ON tblunitofmeasure.uom_id = tblstockitem.si_unitOfMeasure where tblstockitem.si_name in('" + name + "')";
//	    conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    PreparedStatement psmt = conn.prepareStatement(str);
//	    ResultSet rs = psmt.executeQuery();
//	    while (rs.next()) {
//		stockItemTransactionDTO.setLength(rs.getDouble("tblstockitem_si_length"));
//		stockItemTransactionDTO.setWidth(rs.getDouble("tblstockitem_si_width"));
//		stockItemTransactionDTO.setThkness(rs.getDouble("tblstockitem_si_thickness"));
//		stockItemTransactionDTO.setUnit_of_symbol(rs.getString("tblunitofmeasure_uom_symbol"));
//	    }
//	    rs.close();
//	    psmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (Exception e) {
//	    if (conn != null && !conn.isClosed()) {
//		conn.close();
//	    }
//	    e.printStackTrace();
//	    throw e;
//	}
//	return stockItemTransactionDTO;
//
//    }
//
//    public static Map<String, StockItemTransactionDTO> getStockItemValues(Set<String> nameSet) throws SQLException, Exception {
//	System.out.println("Query");
//
//
//	if (nameSet != null && nameSet.size() > 0) {
//	    Map<String, StockItemTransactionDTO> mapToReturn = new HashMap<String, StockItemTransactionDTO>();
//	    String inQuery = "";
//	    for (String str : nameSet) {
//		inQuery += "'" + str + "',";
//	    }
//
//	    inQuery = inQuery.substring(0, inQuery.length() - 1);
//	    Connection conn = DatabaseConnection1.GetConnection();
//
//	    String str = "select * from tblstockitem where si_name in(" + inQuery + ")";
//
//	    PreparedStatement psmt = conn.prepareStatement(str);
//	    ResultSet rs = psmt.executeQuery();
//	    while (rs.next()) {
//		StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
//		stockItemTransactionDTO.setLength(rs.getDouble("si_length"));
//		stockItemTransactionDTO.setWidth(rs.getDouble("si_width"));
//		stockItemTransactionDTO.setThkness(rs.getDouble("si_thickness"));
//
//		mapToReturn.put(rs.getString("si_name"), stockItemTransactionDTO);
//	    }
//	    rs.close();
//	    psmt.close();
//	    conn.close();
//	    return mapToReturn;
//	}
//	return null;
//
//    }
}

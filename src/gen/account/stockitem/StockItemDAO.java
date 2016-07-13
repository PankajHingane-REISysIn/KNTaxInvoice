/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.stockitem;

import gen.ImpExp.TagsHelper1;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class StockItemDAO {

    public static Set<String> stockGroupIDSet;
    public static Set<String> unitOfMeasureIDSet;

    public static void upsertStockItem(List<StockItemDTO> stockItemDTOs) throws SQLException, Exception {
        if (stockItemDTOs != null && stockItemDTOs.size() > 0) {
            List<StockItemDTO> stockItemDTOstoInsert = new ArrayList<StockItemDTO>();
            List<StockItemDTO> stockItemDTOstoUpdate = new ArrayList<StockItemDTO>();

            for (StockItemDTO stockItemDTO : stockItemDTOs) {
                if (stockItemDTO.getID() == 0) {
                    stockItemDTOstoInsert.add(stockItemDTO);
                } else {
                    stockItemDTOstoUpdate.add(stockItemDTO);
                }
            }

            if (stockItemDTOstoInsert.size() > 0) {
                insertStockItem(stockItemDTOstoInsert);
            }

            if (stockItemDTOstoUpdate.size() > 0) {
                updateStockItem(stockItemDTOstoUpdate);
            }

        }

    }

    public static List<StockItemDTO> getStockItemList(Set<String> stockItemMap, String type) throws SQLException {
        List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
        String query = "";
        String condition = "";
        System.out.println("Before stockItemMap is empty");
        String stockItemNameList = "";
        Connection conn = null;
        try {
            if (!Util.isEmpty(stockItemMap)) {

                if (type.equalsIgnoreCase(Constants.STOCK_ITEM_NAME)) {
                    for (String group : stockItemMap) {
                        stockItemNameList = "'" + group + "'" + "," + stockItemNameList;
                    }
                    String groupNameList1 = stockItemNameList.substring(0, stockItemNameList.length() - 1);
                    condition = " where si_name in(" + groupNameList1 + ")";
                } else if (type.equalsIgnoreCase(Constants.STOCK_ITEM_ID)) {
                    for (String group : stockItemMap) {
                        stockItemNameList = group + "," + stockItemNameList;
                    }
                    String groupNameList1 = stockItemNameList.substring(0, stockItemNameList.length() - 1);
                    condition = " where si_id in(" + groupNameList1 + ")";
                }
            }



            query = "select * from tblstockitem " + condition;
            System.out.println("Query--->>" + query);
            conn = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                StockItemDTO stockItemDTO = new StockItemDTO();

                stockItemDTO.setID(resultSet.getLong("si_id"));
                stockItemDTO.setAlias(resultSet.getString("si_alias"));
                stockItemDTO.setLength(resultSet.getDouble("si_length"));
                stockItemDTO.setWidth(resultSet.getDouble("si_width"));
                stockItemDTO.setThkness(resultSet.getDouble("si_thickness"));
                stockItemDTO.setName(resultSet.getString("si_name"));
                stockItemDTO.setOpeningBal(resultSet.getDouble("si_openingBalance"));
                stockItemDTO.setRate(resultSet.getDouble("si_rate"));
                stockItemDTO.setUnder(resultSet.getString("si_under"));
                stockItemDTO.setUnit(resultSet.getString("si_unit"));
                stockItemDTO.setUnit(resultSet.getString("si_unitOfMeasure"));

                stockItemDTOList.add(stockItemDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return stockItemDTOList;
    }

    public static Map<String, String> loadStockGroupIDToNameMap() throws SQLException {
        Connection con = null;
        Map<String, String> stockGroupIDToNameMapping = new HashMap<String, String>();
        String query = "select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')";
        try {
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                stockGroupIDToNameMapping.put(String.valueOf(resultSet.getInt("sg_id")), resultSet.getString("sg_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return stockGroupIDToNameMapping;
    }

    public static Map<String, String> loadStockGroupNameToIDMap() throws SQLException {
        Connection con = null;
        Map<String, String> stockGroupNameToIDMapping = new HashMap<String, String>();
        String query = "select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')";
        try {
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                stockGroupNameToIDMapping.put(resultSet.getString("sg_name"), String.valueOf(resultSet.getInt("sg_id")));
            }
        } catch (SQLException ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return stockGroupNameToIDMapping;
    }

    public static Map<String, String> loadUnitOfMeasureNameToIDMap() throws SQLException {
        Connection con = null;
        Map<String, String> unitOfMeasureNameToIDMapping = new HashMap<String, String>();
        try {

            String query = "select uom_formalName,uom_id from tblunitofmeasure";
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                unitOfMeasureNameToIDMapping.put(String.valueOf(resultSet.getString("uom_formalName")), String.valueOf(resultSet.getInt("uom_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);

            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return unitOfMeasureNameToIDMapping;
    }

    public static Map<String, String> loadUnitOfMeasureIDToNameMap() throws SQLException {
        Connection con = null;
        Map<String, String> unitOfMeasureIDToNameMapping = new HashMap<String, String>();
        try {
            String query = "select uom_formalName,uom_id from tblunitofmeasure";
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                unitOfMeasureIDToNameMapping.put(String.valueOf(resultSet.getInt("uom_id")), resultSet.getString("uom_formalName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);

            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return unitOfMeasureIDToNameMapping;
    }

    public static void insertStockItem(List<StockItemDTO> stockItemDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            System.out.println("Insert function for Stock Item called");
            conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();

            String q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance,si_length,si_width,si_thickness,created_date,created_user,applicable_From_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            for (StockItemDTO stockItemDTO : stockItemDTOList) {

                ps.setString(1, stockItemDTO.getName().toString().trim());
                ps.setString(2, stockItemDTO.getAlias().toString().trim());
                ps.setInt(3, Integer.parseInt(stockItemDTO.getUnder().toString()));
                ps.setDouble(7, Double.parseDouble(stockItemDTO.getLength().toString()));
                ps.setDouble(8, Double.parseDouble(stockItemDTO.getWidth().toString()));
                ps.setDouble(9, Double.parseDouble(stockItemDTO.getThkness().toString()));
//		java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(stockItemDTO.getStockitem_Date().trim());
                // set comany Creation Date
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
		java.sql.Date sqlStartDate = new Date(date.getTime());
		ps.setDate(10, sqlStartDate);
		ps.setString(11, "1");
                ps.setInt(5, Integer.parseInt(stockItemDTO.getUnit().toString()));
                ps.setDouble(4, Double.parseDouble(stockItemDTO.getRate().toString()));
                ps.setDouble(6, Double.parseDouble(stockItemDTO.getOpeningBal().toString()));

                ps.setDate(12, sqlStartDate);


                System.out.println("UNDER" + stockItemDTO.getUnder());
                ps.addBatch();

                //ps.executeUpdate();
            }
            ps.executeBatch();
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            int i = 0;
            for (StockItemDTO stockItemDTO : stockItemDTOList) {
                Integer fk = idList.get(i);

                String query1 = "";
                query1 = "insert into tblStockItemCurrentBalance(si_id,si_currentBalance,si_DebitOrCredit) values(" + fk + "," + stockItemDTO.getOpeningBal() + ",2)";  //2 for credit and 1 for debit
                st1.executeUpdate(query1);

                i++;
            }
            Constants.STOCK_ITEM_TIME_STAMP = Constants.STOCK_ITEM_TIME_STAMP + 1;
        } catch (SQLException ex) {
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }

    }

    public static void updateStockItem(List<StockItemDTO> stockItemDTOList) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "update tblstockitem set si_name = ?,si_alias = ?, si_under = ?, si_rate = ?, si_unitOfMeasure = ?, si_openingBalance = ?, si_length = ?, si_width = ?, si_thickness = ?,modified_date=?,modified_user=?,applicable_From_date=? where si_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            for (StockItemDTO stockItemDTO : stockItemDTOList) {
                ps.setString(1, stockItemDTO.getName().trim());
                ps.setString(2, stockItemDTO.getAlias().trim());
                ps.setString(3, stockItemDTO.getUnder().trim());
                ps.setDouble(4, Double.parseDouble("" + stockItemDTO.getRate()));
                ps.setString(5, (stockItemDTO.getUnit().trim()));
                ps.setDouble(6, stockItemDTO.getOpeningBal());
                ps.setDouble(7, stockItemDTO.getLength());
                ps.setDouble(8, stockItemDTO.getWidth());
                ps.setDouble(9, stockItemDTO.getThkness());
		java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(stockItemDTO.getStockitem_Date().trim());
		java.sql.Date sqlStartDate = new Date(date.getTime());
		ps.setDate(10, sqlStartDate);
		ps.setString(11, "1");
                java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
                java.sql.Date sqlStartDate1 = new Date(date1.getTime());
                ps.setDate(12, sqlStartDate1);
                System.out.println("IIIIII     " + stockItemDTO.getID());
                ps.setLong(13, stockItemDTO.getID());


                ps.addBatch();
            }
            ps.executeBatch();

            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    // update only validate date
    public static void updateStockItemValidate_Date(List<StockItemDTO> stockItemDTOList) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "update tblstockitem set applicable_From_date=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
            java.sql.Date sqlStartDate1 = new Date(date1.getTime());
            ps.setDate(1, sqlStartDate1);
            ps.addBatch();
            ps.executeBatch();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static boolean deleteStockItem(List<StockItemDTO> stockItemDTOList) throws SQLException {
        Connection conn = null;
        try {
            Long select = 0L;
            for (StockItemDTO stockItemDTO : stockItemDTOList) {
                select = (stockItemDTO.getID());
            }
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select invtrans_id from tblinventorytransactionitems where invtrans_itemId = " + select + "");
            if (rs.next()) {
                return true;
            } else {
                st.executeUpdate("delete from tblstockitemcurrentbalance where si_id=" + select + "");

                st.executeUpdate("delete from tblstockitemopblupdaterecord where si_id=" + select + "");
        		st.executeUpdate("delete from tblstockitemrate where stkid=" + select + "");
                st.executeUpdate("delete from tblstockitem where si_id=" + select + "");
                conn.commit();
            }
            conn.close();
        } catch (SQLException ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return false;
    }

    public static Map<String, String> getStockItemWithIDList() throws SQLException, Exception {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        Connection conn = null;
        try {

            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String str = "Select * from tblstockitem";
            PreparedStatement psmt = conn.prepareStatement(str);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                mapToReturn.put(rs.getString("si_name"), rs.getString("si_id"));
            }
            rs.close();
            psmt.close();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return mapToReturn;
    }
    
    public static Map<String, String> getStockItemsFromGroupName(List<String> stockGroupNames, Boolean isIn) throws SQLException, Exception {
	Map<String, String> mapToReturn = new HashMap<String, String>();
	String mainQuery = "";
	String condition = "";
	Connection connection = null;
	try {
	    mainQuery = "SELECT   tblstockgroup.sg_name AS tblstockgroup_sg_name,     tblstockitem.si_id AS tblstockitem_si_id,     tblstockitem.si_name AS tblstockitem_si_name FROM     tblstockgroup tblstockgroup INNER JOIN tblstockitem tblstockitem ON tblstockgroup.sg_id = tblstockitem.si_under";
	    if (stockGroupNames != null && stockGroupNames.size() > 0) {
		String stkGroupList = "";
		for (String stkGroup : stockGroupNames) {
		    stkGroupList = "'" + stkGroup + "' ,";
		}
		if (stkGroupList.length() > 0) {
		    stkGroupList = stkGroupList.substring(0, stkGroupList.length() - 2);
		}

		System.out.println("NOw you are at DAO class for sttkGrouplist" + stkGroupList);

		if (isIn == null || isIn) {
		    condition = " where tblstockgroup.sg_name in (" + stkGroupList + ")";
		} else {
		    condition = " where tblstockgroup.sg_name not in (" + stkGroupList + ")";
		}

	    }
	    String query = mainQuery + condition;

	    System.out.println("----------------->> query: " + query);
	    connection = DatabaseConnection1.GetConnection();
	    connection.setAutoCommit(false);
	    PreparedStatement psmt = connection.prepareStatement(query);
	    ResultSet rsSet = psmt.executeQuery();
	    while (rsSet.next()) {
		mapToReturn.put(rsSet.getString("tblstockitem_si_name"), rsSet.getLong("tblstockitem_si_id") + "");
	    }
	    rsSet.close();
	    psmt.close();
	    connection.commit();
	    connection.close();
	} catch (Exception e) {
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return mapToReturn;
    }

    public static StockItemDTO getStockItem(String stockItemName) {
	return null;
    }

    public static StockItemTransactionDTO getStockItemValues(String name) throws SQLException, Exception {

	StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();

	Connection conn = null;
	try {
	    String str = "SELECT tblunitofmeasure.uom_symbol AS tblunitofmeasure_uom_symbol,tblstockitem.si_width AS tblstockitem_si_width,tblstockitem.si_thickness AS tblstockitem_si_thickness,tblstockitem.si_id AS tblstockitem_si_id,tblstockitem.si_alias AS tblstockitem_si_alias,tblstockitem.si_under AS tblstockitem_si_under,tblstockitem.si_length AS tblstockitem_si_length,tblstockitem.si_rate AS tblstockitem_si_rate,tblstockitem.si_openingBalance AS tblstockitem_si_openingBalance,tblstockitem.si_unitOfMeasure AS tblstockitem_si_unitOfMeasure,tblstockitem.si_unit AS tblstockitem_si_unit,tblstockitem.si_type AS tblstockitem_si_type,tblstockitem.si_name AS tblstockitem_si_name FROM tblunitofmeasure tblunitofmeasure INNER JOIN tblstockitem tblstockitem ON tblunitofmeasure.uom_id = tblstockitem.si_unitOfMeasure where tblstockitem.si_name in('" + name + "')";
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    PreparedStatement psmt = conn.prepareStatement(str);
	    ResultSet rs = psmt.executeQuery();
	    while (rs.next()) {
		stockItemTransactionDTO.setLength(rs.getDouble("tblstockitem_si_length"));
		stockItemTransactionDTO.setWidth(rs.getDouble("tblstockitem_si_width"));
		stockItemTransactionDTO.setThkness(rs.getDouble("tblstockitem_si_thickness"));
		stockItemTransactionDTO.setUnit_of_symbol(rs.getString("tblunitofmeasure_uom_symbol"));
	    }
	    rs.close();
	    psmt.close();
	    conn.commit();
	    conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return stockItemTransactionDTO;

    }

    public static Map<String, StockItemTransactionDTO> getStockItemValues(Set<String> nameSet) throws SQLException, Exception {
	System.out.println("Query");


	if (nameSet != null && nameSet.size() > 0) {
	    Map<String, StockItemTransactionDTO> mapToReturn = new HashMap<String, StockItemTransactionDTO>();
	    String inQuery = "";
	    for (String str : nameSet) {
		inQuery += "'" + str + "',";
	    }

	    inQuery = inQuery.substring(0, inQuery.length() - 1);
	    Connection conn = DatabaseConnection1.GetConnection();

	    String str = "select * from tblstockitem where si_name in(" + inQuery + ")";

	    PreparedStatement psmt = conn.prepareStatement(str);
	    ResultSet rs = psmt.executeQuery();
	    while (rs.next()) {
		StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
		stockItemTransactionDTO.setLength(rs.getDouble("si_length"));
		stockItemTransactionDTO.setWidth(rs.getDouble("si_width"));
		stockItemTransactionDTO.setThkness(rs.getDouble("si_thickness"));

		mapToReturn.put(rs.getString("si_name"), stockItemTransactionDTO);
	    }
	    rs.close();
	    psmt.close();
	    conn.close();
	    return mapToReturn;
	}
	return null;

    }

    public static List<StockItemDTO> exportAllStockItems() {
        Connection conn = null;
        List<StockItemDTO> stockItemTransactionDTOList = new ArrayList<StockItemDTO>();
        try {

            String query = "select * from tblstockitem";
            System.out.println("Query--->>" + query);
            conn = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                StockItemDTO stockItemDTO = new StockItemDTO();

                stockItemDTO.setID(resultSet.getLong("si_id"));
                stockItemDTO.setAlias(resultSet.getString("si_alias"));
                stockItemDTO.setLength(resultSet.getDouble("si_length"));
                stockItemDTO.setWidth(resultSet.getDouble("si_width"));
                stockItemDTO.setThkness(resultSet.getDouble("si_thickness"));
                stockItemDTO.setName(resultSet.getString("si_name"));
                stockItemDTO.setOpeningBal(resultSet.getDouble("si_openingBalance"));
                stockItemDTO.setRate(resultSet.getDouble("si_rate"));
                stockItemDTO.setUnder(resultSet.getString("si_under"));
                stockItemDTO.setUnit(resultSet.getString("si_unit"));
                stockItemDTO.setUnit(resultSet.getString("si_unitOfMeasure"));

                stockItemTransactionDTOList.add(stockItemDTO);
            }

        } catch (Exception ex) {
            Logger.getLogger(StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stockItemTransactionDTOList;
    }
    
       public static Map<String, String> getStockItemsNameID(List<String> stockGroupNames, Boolean isIn) throws SQLException, Exception {
	Map<String, String> mapToReturn = new HashMap<String, String>();
	String mainQuery = "";
	String condition = "";
	Connection connection = null;
	try {
	    mainQuery = "SELECT   tblstockitem.si_id AS tblstockitem_si_id,     tblstockitem.si_name AS tblstockitem_si_name FROM   tblstockitem ";
            if (stockGroupNames != null && stockGroupNames.size() > 0) {
		String stkGroupList = "";
		for (String stkGroup : stockGroupNames) {
		    stkGroupList = "'" + stkGroup + "' ,";
		}
		if (stkGroupList.length() > 0) {
		    stkGroupList = stkGroupList.substring(0, stkGroupList.length() - 2);
		}

		System.out.println("NOw you are at DAO class for sttkGrouplist" + stkGroupList);

//		if (isIn == null || isIn) {
//		    condition = "where tblstockgroup.sg_name in (" + stkGroupList + ")";
//		} else {
//		    condition = "where tblstockgroup.sg_name not in (" + stkGroupList + ")";
//		}

	    }
	    String query = mainQuery + condition;

	    System.out.println("----------------->> query: " + query);
	    connection = DatabaseConnection1.GetConnection();
	    connection.setAutoCommit(false);
	    PreparedStatement psmt = connection.prepareStatement(query);
	    ResultSet rsSet = psmt.executeQuery();
	    while (rsSet.next()) {
		mapToReturn.put(rsSet.getString("tblstockitem_si_name"), rsSet.getString("tblstockitem_si_id"));
	    }
	    rsSet.close();
	    psmt.close();
	    connection.commit();
	    connection.close();
	} catch (Exception e) {
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return mapToReturn;
    }
       

////    public static Set<String> stockGroupIDSet;
////    public static Set<String> unitOfMeasureIDSet;
//    //public static Map<String, String> stockGroupIDToNameMapping = new HashMap<String, String>();
//    //public static Map<String, String> stockGroupNameToIDMapping = new HashMap<String, String>();
//    //public static Map<String, String> unitOfMeasureIDToNameMapping = new HashMap<String, String>();
//    //public static Map<String, String> unitOfMeasureNameToIDMapping = new HashMap<String, String>();
//
//    public static void upsertStockItem(List<StockItemDTO> stockItemDTOs) throws SQLException, Exception {
//        if (stockItemDTOs != null && stockItemDTOs.size() > 0) {
//            List<StockItemDTO> stockItemDTOstoInsert = new ArrayList<StockItemDTO>();
//            List<StockItemDTO> stockItemDTOstoUpdate = new ArrayList<StockItemDTO>();
//
//            for (StockItemDTO stockItemDTO : stockItemDTOs) {
//                if (stockItemDTO.getID() == 0) {
//                    stockItemDTOstoInsert.add(stockItemDTO);
//                } else {
//                    stockItemDTOstoUpdate.add(stockItemDTO);
//                }
//            }
//
//            if (stockItemDTOstoInsert.size() > 0) {
//                insertStockItem(stockItemDTOstoInsert);
//            }
//
//            if (stockItemDTOstoUpdate.size() > 0) {
//                updateStockItem(stockItemDTOstoUpdate);
//            }
//
//        }
//
//    }
//
//    public static List<StockItemDTO> getStockItemList(Set<String> stockItemMap, String type) throws SQLException {
//        List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
//        Map<String, List<ColorAndOBDTO>> receivedMap = getColorAndOB();
//        String query = "";
//        String condition = "";
//        System.out.println("Before stockItemMap is empty");
//        String stockItemNameList = "";
//        Connection conn = null;
//        try {
//            if (!Util.isEmpty(stockItemMap)) {
//
//                if (type.equalsIgnoreCase(Constants.STOCK_ITEM_NAME)) {
//                    for (String group : stockItemMap) {
//                        stockItemNameList = "'" + group + "'" + "," + stockItemNameList;
//                    }
//                    String groupNameList1 = stockItemNameList.substring(0, stockItemNameList.length() - 1);
//                    condition = " where si_name in(" + groupNameList1 + ")";
//                } else if (type.equalsIgnoreCase(Constants.STOCK_ITEM_ID)) {
//                    for (String group : stockItemMap) {
//                        stockItemNameList = group + "," + stockItemNameList;
//                    }
//                    String groupNameList1 = stockItemNameList.substring(0, stockItemNameList.length() - 1);
//                    condition = " where si_id in(" + groupNameList1 + ")";
//                }
//            }
//
//            query = "select * from tblstockitem " + condition;
//            System.out.println("Query--->>" + query);
//            conn = DatabaseConnection1.GetConnection();
//
//            PreparedStatement preparedStmt = conn.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                StockItemDTO stockItemDTO = new StockItemDTO();
//
//                stockItemDTO.setID(resultSet.getLong("si_id"));
//                stockItemDTO.setAlias(resultSet.getString("si_alias"));
//                stockItemDTO.setLength(resultSet.getDouble("si_length"));
//                stockItemDTO.setName(resultSet.getString("si_name"));
//                //stockItemDTO.setOpeningBal(resultSet.getDouble("si_openingBalance"));
//                stockItemDTO.setRate(resultSet.getDouble("si_rate"));
//                stockItemDTO.setThkness(resultSet.getDouble("si_thickness"));
//                stockItemDTO.setUnder(resultSet.getString("si_under"));
//                stockItemDTO.setUnit(resultSet.getString("si_unit"));
//                stockItemDTO.setUnit(resultSet.getString("si_unitOfMeasure"));
//
//                for (Map.Entry<String, List<ColorAndOBDTO>> e : receivedMap.entrySet()) {
//
//                    if (resultSet.getLong("si_id") == Long.parseLong(e.getKey())) {
//                        stockItemDTO.setColorAndOBDTOList(e.getValue());
//                    }
//
//                }
//
//                stockItemDTOList.add(stockItemDTO);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//        return stockItemDTOList;
//    }
//
//    public static Map<String, String> loadStockGroupIDToNameMap() throws SQLException {
//        Map<String, String> stockGroupIDToNameMapping = new HashMap<String, String>();
//        Connection con = null;
//        String query = "select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')";
//        try {
//            con = DatabaseConnection1.GetConnection();
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                stockGroupIDToNameMapping.put(String.valueOf(resultSet.getInt("sg_id")), resultSet.getString("sg_name"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            throw ex;
//        }
//        return stockGroupIDToNameMapping;
//    }
//
//    public static Map<String, String> loadStockGroupNameToIDMap() throws SQLException {
//        Map<String, String> stockGroupNameToIDMapping = new HashMap<String, String>();
//        Connection con = null;
//        String query = "select sg_id,sg_name from tblstockgroup where sg_name not in ('Primary')";
//        try {
//            con = DatabaseConnection1.GetConnection();
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                stockGroupNameToIDMapping.put(resultSet.getString("sg_name"), String.valueOf(resultSet.getInt("sg_id")));
//            }
//        } catch (SQLException ex) {
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            throw ex;
//        }
//        return stockGroupNameToIDMapping;
//    }
//
//    public static Map<String, String> loadUnitOfMeasureNameToIDMap() throws SQLException {
//        Map<String, String> unitOfMeasureNameToIDMapping = new HashMap<String, String>();
//        Connection con = null;
//        try {
//
//            String query = "select uom_formalName,uom_id from tblunitofmeasure";
//            con = DatabaseConnection1.GetConnection();
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                unitOfMeasureNameToIDMapping.put(String.valueOf(resultSet.getString("uom_formalName")), String.valueOf(resultSet.getInt("uom_id")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            throw ex;
//        }
//        return unitOfMeasureNameToIDMapping;
//    }
//
//    public static Map<String, String> loadUnitOfMeasureIDToNameMap() throws SQLException {
//        Map<String, String> unitOfMeasureIDToNameMapping = new HashMap<String, String>();
//        Connection con = null;
//        try {
//            String query = "select uom_formalName,uom_id from tblunitofmeasure";
//            con = DatabaseConnection1.GetConnection();
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                unitOfMeasureIDToNameMapping.put(String.valueOf(resultSet.getInt("uom_id")), resultSet.getString("uom_formalName"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            throw ex;
//        }
//        return unitOfMeasureIDToNameMapping;
//    }

    public static Map<String, String> loadColorsNameToIDMap() throws SQLException {
        Map<String, String> colorsNameToIDMapping = new HashMap<String, String>();
        Connection con = null;
        try {

            String query = "select color_name,color_id from tblcolor";
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                colorsNameToIDMapping.put(String.valueOf(resultSet.getString("color_name")), String.valueOf(resultSet.getInt("color_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);

            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return colorsNameToIDMapping;
    }

    public static Map<String, String> loadColorsIDToNameMap() throws SQLException {
        Map<String, String> colorsIDToNameMapping = new HashMap<String, String>();
        Connection con = null;
        try {
            String query = "select color_id,color_name from tblcolor";
            con = DatabaseConnection1.GetConnection();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                colorsIDToNameMapping.put(String.valueOf(resultSet.getInt("color_id")), resultSet.getString("color_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);

            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return colorsIDToNameMapping;
    }

//    public static void insertStockItem(List<StockItemDTO> stockItemDTOList) throws SQLException, Exception {
//        Connection conn = null;
//        try {
//            System.out.println("Insert function for Stock Item called");
//            conn = DatabaseConnection1.GetConnection();
//            Statement st1 = conn.createStatement();
////
//            String query = "insert into tblcatalogstockitem(si_name,si_alias,si_under,si_rate,si_unitofmeasure) values(?,?,?,?,?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//
//            for (StockItemDTO stockItemDTO : stockItemDTOList) {
//
//                preparedStatement.setString(1, stockItemDTO.getName());
//                preparedStatement.setString(2, stockItemDTO.getAlias());
//                preparedStatement.setInt(3, Integer.parseInt(stockItemDTO.getUnder().toString()));
//                preparedStatement.setDouble(4, Double.parseDouble(stockItemDTO.getRate().toString()));
//                preparedStatement.setInt(5, Integer.parseInt(stockItemDTO.getUnit().toString()));
//
//                preparedStatement.addBatch();
//
//            }
//            preparedStatement.executeBatch();
//
//            ResultSet rs = preparedStatement.getGeneratedKeys();
//            List<Integer> idList = new ArrayList<Integer>();
//            while (rs.next()) {
//                idList.add(rs.getInt(1));
//            }
//            int i = 0;
////            String q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance,si_length,si_width,si_thickness,created_date,created_user) values(?,?,?,?,?,?,?,?,?,?,?)";
////            String q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitofmeasure,si_openingBalance,si_length,si_width,si_thickness,si_color,created_date,created_user) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//            String q = "insert into tblstockitem(si_id,si_openingBalance,si_length,si_width,si_thickness,si_color,created_date,created_user) values(?,?,?,?,?,?,?,?)";
////            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
//            PreparedStatement ps = conn.prepareStatement(q);
//            for (StockItemDTO stockItemDTO : stockItemDTOList) {
//
//                Integer fk = idList.get(i);
//                ps.setInt(1, fk);
//
//                ps.setDouble(3, Double.parseDouble(stockItemDTO.getLength().toString()));
//                ps.setDouble(4, Double.parseDouble(stockItemDTO.getWidth().toString()));
//                ps.setDouble(5, Double.parseDouble(stockItemDTO.getThkness().toString()));
//                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(stockItemDTO.getStockitem_Date().trim());
//                java.sql.Date sqlStartDate = new Date(date.getTime());
//                ps.setDate(7, sqlStartDate);
//                ps.setString(8, "1");
//                for (ColorAndOBDTO colorAndOBDTO : stockItemDTO.getColorAndOBDTOList()) {
//                    System.out.println("colorAndOBDTO.getColorName()--->>>>>" + colorAndOBDTO.getColorName());
//                    System.out.println("colorAndOBDTO.getOpeningBalance--->>>>>" + colorAndOBDTO.getOpeningBalance());
//                    ps.setDouble(6, Integer.parseInt(colorAndOBDTO.getColorName()));
//                    ps.setDouble(2, Double.parseDouble(colorAndOBDTO.getOpeningBalance().toString()));
//                }
//                System.out.println("UNDER" + stockItemDTO.getUnder());
//                ps.addBatch();
//
//                //ps.executeUpdate();
//            }
//            ps.executeBatch();
//            ResultSet rs1 = ps.getGeneratedKeys();
//            List<Integer> idList1 = new ArrayList<Integer>();
//            while (rs1.next()) {
//                idList1.add(rs1.getInt(1));
//            }
//            int i1 = 0;
//            for (StockItemDTO stockItemDTO : stockItemDTOList) {
//                Integer fk = idList.get(i1);
//
//                String query1 = "";
//                query1 = "insert into tblStockItemCurrentBalance(si_id,si_currentBalance,si_DebitOrCredit) values(" + fk + "," + stockItemDTO.getOpeningBal() + ",2)";  //2 for credit and 1 for debit
//                st1.executeUpdate(query1);
//
//                i++;
//            }
//            Constants.STOCK_ITEM_TIME_STAMP = Constants.STOCK_ITEM_TIME_STAMP + 1;
//        } catch (SQLException ex) {
//            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//
//    }
//
//    public static void updateStockItem(List<StockItemDTO> stockItemDTOList) throws Exception {
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            String query = "update tblstockitem set si_name = ?,si_alias = ?, si_under = ?, si_rate = ?, si_unitOfMeasure = ?, si_openingBalance = ?, si_length = ?, si_width = ?, si_thickness = ?,modified_date=?,modified_user=? where si_id = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            for (StockItemDTO stockItemDTO : stockItemDTOList) {
//                ps.setString(1, stockItemDTO.getName().trim());
//                ps.setString(2, stockItemDTO.getAlias().trim());
//                ps.setString(3, stockItemDTO.getUnder().trim());
//                ps.setDouble(4, Double.parseDouble("" + stockItemDTO.getRate()));
//                ps.setString(5, (stockItemDTO.getUnit().trim()));
//                ps.setDouble(6, stockItemDTO.getOpeningBal());
//                ps.setDouble(7, stockItemDTO.getLength());
//                ps.setDouble(8, stockItemDTO.getWidth());
//                ps.setDouble(9, stockItemDTO.getThkness());
//                System.out.println("IIIIII     " + stockItemDTO.getID());
//                ps.setLong(12, stockItemDTO.getID());
//                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(stockItemDTO.getStockitem_Date().trim());
//                java.sql.Date sqlStartDate = new Date(date.getTime());
//                ps.setDate(10, sqlStartDate);
//                ps.setString(11, "1");
//
//                ps.addBatch();
//            }
//            ps.executeBatch();
//
//            ps.close();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//    }
//
//    public static boolean deleteStockItem(List<StockItemDTO> stockItemDTOList) throws SQLException {
//        Connection conn = null;
//        try {
//            Long select = 0L;
//            for (StockItemDTO stockItemDTO : stockItemDTOList) {
//                select = (stockItemDTO.getID());
//            }
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery("select invtrans_id from tblinventorytransactionitems where invtrans_itemId = " + select + "");
//            if (rs.next()) {
//                return true;
//            } else {
//                st.executeUpdate("delete from tblstockitemcurrentbalance where si_id=" + select + "");
//
//                st.executeUpdate("delete from tblstockitemopblupdaterecord where si_id=" + select + "");
//                st.executeUpdate("delete from tblstockitemrate where stkid=" + select + "");
//                st.executeUpdate("delete from tblstockitem where si_id=" + select + "");
//                conn.commit();
//            }
//            conn.close();
//        } catch (SQLException ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//        return false;
//    }
//
//    public static Map<String, String> getStockItemWithIDList() throws SQLException, Exception {
//        Map<String, String> mapToReturn = new HashMap<String, String>();
//        Connection conn = null;
//        try {
//
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//
//            String str = "Select * from tblstockitem";
//            PreparedStatement psmt = conn.prepareStatement(str);
//            ResultSet rs = psmt.executeQuery();
//            while (rs.next()) {
//                mapToReturn.put(rs.getString("si_name"), rs.getString("si_id"));
//            }
//            rs.close();
//            psmt.close();
//            conn.commit();
//            conn.close();
//        } catch (Exception ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//        return mapToReturn;
//    }

    private static Map<String, List<ColorAndOBDTO>> getColorAndOB() {
//        Map<String, Map<String, String>> returnColorAndOBMap = new HashMap<String, Map<String, String>>();
        Map<String, List<ColorAndOBDTO>> returnColorAndOBMap = new HashMap<String, List<ColorAndOBDTO>>();
//        Map<String, String> ColorAndOBMap = null;
        List<ColorAndOBDTO> ColorAndOBMap = new ArrayList<ColorAndOBDTO>();
        try {
            Connection conn = DatabaseConnection1.GetConnection();
//            String query1 = "select si_id,si_color,si_openingBalance from tblstockitem group by si_id";
            String query1 = "select si_id,si_color,si_openingBalance from tblstockitem";
            PreparedStatement preparedStatement = conn.prepareStatement(query1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ColorAndOBDTO colorAndOBDTO = new ColorAndOBDTO();
                colorAndOBDTO.setColorName(resultSet.getString("si_color"));
                colorAndOBDTO.setOpeningBalance(resultSet.getDouble("si_openingBalance"));
                System.out.println("ColorGET--->>>" + colorAndOBDTO.getColorName());
                System.out.println("OPGET--->>>>" + colorAndOBDTO.getOpeningBalance());
                System.out.println("resultSet.getString(\"si_id\")--->>>>" + resultSet.getString("si_id"));
                ColorAndOBMap.add(colorAndOBDTO);
                returnColorAndOBMap.put(resultSet.getString("si_id"), ColorAndOBMap);
            }
            for (Map.Entry<String, List<ColorAndOBDTO>> e : returnColorAndOBMap.entrySet()) {

//                System.out.println("IDeaaaa--->>>" + e.getKey());

                for (ColorAndOBDTO colorAndOBDTO : e.getValue()) {
//                    System.out.println("Color--->>>" + colorAndOBDTO.getColorName());
//                    System.out.println("OP--->>>>" + colorAndOBDTO.getOpeningBalance());
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(gen.account.stockitem.StockItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnColorAndOBMap;
    }
}
   
       


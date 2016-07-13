/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.ledger;

import gen.account.groupDTODAO.GroupDAO;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class LedgerDAO {

//    public static void upsertGroup(List<gen.account.ledger.LedgerDTO> ledgerDTOs) throws SQLException, ParseException, Exception {
//        if (ledgerDTOs != null && ledgerDTOs.size() > 0) {
//            List<gen.account.ledger.LedgerDTO> ledgerDTOstoInsert = new ArrayList<gen.account.ledger.LedgerDTO>();
//            List<gen.account.ledger.LedgerDTO> ledgerDTOstoUpdate = new ArrayList<gen.account.ledger.LedgerDTO>();
//
//            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerDTOs) {
//                if (ledgerDTO.getLedgerID().trim().equalsIgnoreCase("")) {
//                    ledgerDTOstoInsert.add(ledgerDTO);
//                } else {
//                    ledgerDTOstoUpdate.add(ledgerDTO);
//                }
//            }
//
//            if (ledgerDTOstoInsert.size() > 0) {
//                insertLedger(ledgerDTOstoInsert);
//            }
//
//            if (ledgerDTOstoUpdate.size() > 0) {
//                UpdateLedger(ledgerDTOstoUpdate);
//            }
//
//        }
//
//    }
//
//    public static void insertLedger(List<gen.account.ledger.LedgerDTO> ledgerFormDTOList) throws SQLException, Exception {
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            Statement st = conn.createStatement();
//            Statement st1 = conn.createStatement();
//            ResultSet rs1;
//
//            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerFormDTOList) {
//                String sql = "";
//                PreparedStatement ps;
//                sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,created_date,created_user,applicable_From_date,isdeletable) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setString(1, ledgerDTO.getLedger_Name().trim());
//                ps.setString(2, ledgerDTO.getLedger_Under().trim());
//                ps.setString(3, ledgerDTO.getLedger_Address().trim());
//                ps.setString(4, ledgerDTO.getLedger_ContactNo().trim());
//                ps.setString(5, ledgerDTO.getLedger_EmailID().trim());
//                ps.setDouble(6, ledgerDTO.getLedger_OpeningBalence());
//                ps.setString(7, ledgerDTO.getLedger_Alias().trim());
//                ps.setString(8, ledgerDTO.getLedger_IncomeTaxNo().trim());
//                ps.setString(9, ledgerDTO.getLedger_SaleTaxNo().trim());
//                if (ledgerDTO.getDebitOrCredit() == 1) {
//                    ps.setInt(10, 1);
//                } else {
//                    ps.setInt(10, 2);
//                }
//
////                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(ledgerDTO.getLedger_Date().trim());
//                // set Company Creation date
//                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
//                java.sql.Date sqlStartDate = new Date(date.getTime());
//                ps.setDate(11, sqlStartDate);
//                ps.setString(12, "1");
//
//                ps.setDate(13, sqlStartDate);
//                ps.setString(14, ledgerDTO.getLedger_isdeletable());
//                ps.executeUpdate();
//                
//                String q = "";
//                int temp_id = 0;
//                ResultSet rs = ps.getGeneratedKeys();
//                List<Integer> idList = new ArrayList<Integer>();
//                while (rs.next()) {
//                    temp_id = rs.getInt(1);
//                }
//                ps.close();
//
//                q = "insert into tblledgercreditlimit(ledger_id,ledger_limit) values(" + temp_id + "," + Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString()) + ")";
//                st1.executeUpdate(q);
//
//                q = "insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + "," + Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()) + "," + ledgerDTO.getDebitOrCredit().toString() + ")";  //2 for credit and 1 for debit
//                st1.executeUpdate(q);
//                conn.commit();
//
//                Constants.LEDGER_TIME_STAMP = Constants.LEDGER_TIME_STAMP + 1;
//
//
//            }
//
//        } catch (Exception ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//    }
//
//    public static void UpdateLedger(List<gen.account.ledger.LedgerDTO> ledgerFormDTOList) throws SQLException, Exception {
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            Statement st = conn.createStatement();
//            Statement st1 = conn.createStatement();
//            Statement st2 = conn.createStatement();
//            Statement st3 = conn.createStatement();
//            Statement st4 = conn.createStatement();
//            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerFormDTOList) {
//                double blnc = 0;
//                int DebCred = 0;
//                String q = "";
//                int ot = 0;//opening type
//                if (ledgerDTO.getDebitOrCredit() == 2) {
//                    ot = 2;
//                } else {
//                    ot = 1;
//                }
//
//                String sql = "UPDATE tblledger SET ledger_name=?,ledger_under=?,ledger_alias=?,ledger_address=?,ledger_contactno=?,ledger_emailid=?,ledger_openingbalance=?,ledger_intaxo=?,ledger_saletaxno=?,opening_type=?,modified_date=?,modified_user=?,applicable_From_date=?,isdeletable=? where ledger_id=?";
//                PreparedStatement ps = null;
//                ps = conn.prepareStatement(sql);
//                ps.setString(1, ledgerDTO.getLedger_Name().trim());
//                ps.setString(2, ledgerDTO.getLedger_Under().trim());
//                ps.setString(3, ledgerDTO.getLedger_Alias().trim());
//                ps.setString(4, ledgerDTO.getLedger_Address().trim());
//                ps.setString(5, ledgerDTO.getLedger_ContactNo().trim());
//                ps.setString(6, ledgerDTO.getLedger_EmailID().trim());
//                ps.setDouble(7, ledgerDTO.getLedger_OpeningBalence());
//                ps.setString(8, ledgerDTO.getLedger_IncomeTaxNo().trim());
//                ps.setString(9, ledgerDTO.getLedger_SaleTaxNo().trim());
//                ps.setInt(10, ot);
//                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(ledgerDTO.getLedger_Date().trim());
//                java.sql.Date sqlStartDate = new Date(date.getTime());
//                ps.setDate(11, sqlStartDate);
//                ps.setString(12, "1");
//                java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
//                java.sql.Date sqlStartDate1 = new Date(date1.getTime());
//                ps.setDate(13, sqlStartDate1);
//                ps.setString(15, ledgerDTO.getLedgerID());
//                ps.setString(14, ledgerDTO.getLedger_isdeletable());
//                ps.executeUpdate();
//                double c = 0;
//                if (ledgerDTO.getLedger_CreditLimit().equals("") == false) {
//                    c = Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString());
//                }
//
//
//                String query = "UPDATE tblledgercreditlimit SET ledger_limit = ? where ledger_id = ?";
//                PreparedStatement ps1 = conn.prepareStatement(query);
//                ps1.setDouble(1, Math.abs(ledgerDTO.getLedger_CreditLimit()));
//                ps1.setString(2, ledgerDTO.getLedgerID());
//                ps1.executeUpdate();
//                ps1.close();
//
//                String query1 = "UPDATE tblledgercurrentbalance SET ledger_currentBalance = ?,ledger_DebitOrCredit = ? where ledger_id = ? ";
//                PreparedStatement ps2 = conn.prepareStatement(query1);
//                ps2.setDouble(1, Math.abs(ledgerDTO.getLedger_OpeningBalence()));
//                ps2.setInt(2, ledgerDTO.getDebitOrCredit());
//                ps2.setString(3, ledgerDTO.getLedgerID());
//                ps2.executeUpdate();
//                ps2.close();
//
//            }
//            conn.commit();
//            Constants.LEDGER_TIME_STAMP++;
//
//
//        } catch (Exception ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//
//    }
//
//    // update only validate date
    public static void updateLedgerValidate_Date(List<LedgerDTO> ledgerDTOList) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "update tblledger set applicable_From_date=? ";
            PreparedStatement ps = conn.prepareStatement(query);

            java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
            java.sql.Date sqlStartDate1 = new Date(date1.getTime());
            ps.setDate(1, sqlStartDate1);
            ps.addBatch();

            ps.executeBatch();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LedgerDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }
//
//    public static List<LedgerDTO> getLedgerList(Set<String> ledgerNameMap, String type) throws SQLException {
//        List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
//        String query = "";
//        String condition = "";
//        Connection con = null;
//        try {
//        if (!Util.isEmpty(ledgerNameMap)) {
//            String ledgerNameList = "";
//            if (type.equalsIgnoreCase(Constants.LEDGER_NAME)) {
//                for (String ledgerName : ledgerNameMap) {
//                    ledgerNameList = ledgerNameList + "'" + ledgerName + "',";
//                }
//                String ledgerNameList1 = ledgerNameList.substring(0, ledgerNameList.length() - 1);
//                condition = " where ledger_name in(" + ledgerNameList1 + ")";
//            } else if (type.equalsIgnoreCase(Constants.LEDGER_ID)) {
//                for (String ledgerName : ledgerNameMap) {
//                    ledgerNameList = ledgerNameList + "" + ledgerName + ",";
//                }
//                String ledgerNameList1 = ledgerNameList.substring(0, ledgerNameList.length() - 1);
//                condition = " where ledger_id in(" + ledgerNameList1 + ")";
//            }
//        }
//
//        query = "select * from tblledger " + condition;
//        con = DatabaseConnection1.GetConnection();
//
//        
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                LedgerDTO ledgerDTO = new LedgerDTO();
//
//                ledgerDTO.setLedgerID(resultSet.getString("ledger_id"));
//                ledgerDTO.setLedger_Alias(resultSet.getString("ledger_alias"));
//                ledgerDTO.setLedger_Address(resultSet.getString("ledger_address"));
//                ledgerDTO.setLedger_ContactNo(resultSet.getString("ledger_contactno"));
//                ledgerDTO.setLedger_EmailID(resultSet.getString("ledger_emailId"));
//                ledgerDTO.setLedger_IncomeTaxNo(resultSet.getString("ledger_inTaxo"));
//                ledgerDTO.setLedger_Name(resultSet.getString("ledger_name"));
//                ledgerDTO.setLedger_OpeningBalence(resultSet.getDouble("ledger_openingBalance"));
//                ledgerDTO.setLedger_SaleTaxNo(resultSet.getString("ledger_saleTaxNo"));
//                ledgerDTO.setLedger_Under(resultSet.getString("ledger_under") + "");
//                ledgerDTO.setDebitOrCredit(resultSet.getInt("opening_type"));
//                ledgerDTO.setLedger_isdeletable(resultSet.getString("isdeletable"));
//                ledgerDTO.setLedger_CreditLimit(0D);////////////////////////////need to review.... Pankaj
//                String query_ledger_limit = "select ledger_limit from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
//                PreparedStatement preparedStmt_ledger_limit = con.prepareStatement(query_ledger_limit);
//                ResultSet resultSet_ledger_limit = preparedStmt_ledger_limit.executeQuery();
//                if (resultSet_ledger_limit.next()) {
//                    ledgerDTO.setLedger_CreditLimit(resultSet_ledger_limit.getDouble("ledger_limit"));
//                } else {
//                    ledgerDTO.setLedger_CreditLimit(0D);
//                }
//                resultSet_ledger_limit.close();
//                preparedStmt_ledger_limit.close();
//
//
//                String query_ledger_OPBL = "select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id=" + ledgerDTO.getLedgerID() + "";
//                PreparedStatement preparedStmt_ledger_OPBL = con.prepareStatement(query_ledger_OPBL);
//                ResultSet resultSet_ledger_OPBL = preparedStmt_ledger_OPBL.executeQuery();
//                if (resultSet_ledger_OPBL.next()) {
//                    ledgerDTO.setLedger_OpeningBalence(resultSet_ledger_OPBL.getDouble("ledger_currentBalance"));
//                    ledgerDTO.setDebitOrCredit(resultSet_ledger_OPBL.getInt("ledger_DebitOrCredit"));
//                } else {
//                    ledgerDTO.setLedger_OpeningBalence(0.0);
//                    ledgerDTO.setDebitOrCredit(1);
//                }
//                resultSet_ledger_OPBL.close();
//                preparedStmt_ledger_OPBL.close();
//
//
//                ledgerDTO = gen.ImpExp.ImpExpUtil.valiDateAndRepairLedgerDTO(ledgerDTO);
//                ledgerDTOList.add(ledgerDTO);
//            }
//        } catch (SQLException ex) {
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            throw ex;
//        }
//
//        return ledgerDTOList;
//    }
//
//    public static boolean deleteLedger(gen.account.ledger.LedgerDTO ledgerDTO) throws SQLException {
//        Boolean flag = false;
//        Connection conn = null;
//        try {
//             conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            Statement st = conn.createStatement();
//
//            System.out.println("Ledgetr DTO -------------- IN DAO>>>>>>>>>>>>>>>" + ledgerDTO.getLedgerID());
//
//	    // if LedgerTransaction ARE PRESENT then Dont delete
//            ResultSet rs = st.executeQuery("select trans_ledgerId from tbltransactionledger where trans_ledgerId=" + ledgerDTO.getLedgerID() + "");
//            while (rs.next()) {
//		System.out.println("tbltransactionledger-----------------------");
//                flag = true;
//            }
//            rs.close();
//
////	    // if tblstockitemrate ARE PRESENT
////            ResultSet rs1 = st.executeQuery("select   custid from tblstockitemrate where custid=" + ledgerDTO.getLedgerID() + "");
////            while (rs1.next()) {
////		System.out.println("tblstockitemrate-----------------------");
////                flag = true;
////            }
////            rs1.close();
//
//
//            if (flag) {
//                flag = true;
//            } else {
//
//                String str = "delete from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
//                st.executeUpdate("delete from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "");
//                st.executeUpdate("delete from tblledgercurrentbalance where ledger_id=" + ledgerDTO.getLedgerID() + "");
//		        st.executeUpdate("delete from tblstockitemrate where custid=" + ledgerDTO.getLedgerID() + "");
//                st.executeUpdate("delete from tblledger where ledger_id=" + ledgerDTO.getLedgerID() + "");
//
//            }
//            st.close();
//            conn.commit();
//            conn.close();
//
//        } catch (SQLException ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//        return flag;
//    }
//
//    public static Map<String, String> getLedgerNameMap(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
//        Map<String, String> mapToReturn = new HashMap<String, String>();
//        String mainQuery = "";
//        String condition = "";
//        mainQuery = " select ledger_id,ledger_Name from tblledger";
//        Connection connection = null;
//        try {
//        if (groupNames != null && groupNames.size() > 0) {
//            String groupList = "";
//            Set<String> groupSet = new HashSet<String>();
//            for (String stkGroup : groupNames) {
//                groupSet.add(stkGroup);
//            }
//
//            groupSet = GroupDAO.getUnderGroups(groupSet);
//            groupNames = new ArrayList<String>();
//            for (String stkGroup : groupSet) {
//                groupNames.add(stkGroup);
//            }
//
//            for (String stkGroup : groupNames) {
//                groupList += "'" + stkGroup + "' ,";
//            }
//            if (groupList.length() > 0) {
//                groupList = groupList.substring(0, groupList.length() - 2);
//            }
//
//        }
//        String query = mainQuery + condition;
//
//        System.out.println("----------------->> query: " + query);
//        connection = DatabaseConnection1.GetConnection();
//        PreparedStatement psmt = connection.prepareStatement(query);
//        ResultSet rsSet = psmt.executeQuery();
//        while (rsSet.next()) {
//            mapToReturn.put(rsSet.getString("ledger_name"), rsSet.getLong("ledger_id") + "");
//        }
//        rsSet.close();
//        psmt.close();
//        connection.close();
//        } catch (Exception ex) {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//            throw ex;
//        }
//        return mapToReturn;
//    }
//    
//        public static Map<String, String> getLedgerNameToIDMap() throws SQLException, Exception {
//	Map<String, String> ledgerNameMap = new HashMap<String, String>();
//	Connection con = null;
//	try {
//	    String query = "select ledger_name, ledger_id from tblledger";
//	    con = DatabaseConnection1.GetConnection();
//	    con.setAutoCommit(false);
//	    PreparedStatement preparedStmt = con.prepareStatement(query);
//	    ResultSet resultSet = preparedStmt.executeQuery();
//	    while (resultSet.next()) {
//		ledgerNameMap.put(resultSet.getString("ledger_name"), String.valueOf(resultSet.getInt("ledger_id")));
//	    }
//	    con.commit();
//	    con.close();
//	} catch (Exception e) {
//	    if (con != null && !con.isClosed()) {
//		con.close();
//	    }
//	    e.printStackTrace();
//	    throw e;
//	}
//
//	return ledgerNameMap;
//    }
//

    public static Map<String, String> getLedgerIDToNameMap() throws SQLException, Exception {
        System.out.println("Step--->>>23");
        Map<String, String> ledgerNameMap = new HashMap<String, String>();
        Connection con = null;
        try {
            String query = "select  ledger_id,ledger_name from tblledger";
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                ledgerNameMap.put(String.valueOf(resultSet.getInt("ledger_id")), resultSet.getString("ledger_name"));
            }
            con.commit();
            con.close();
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
            throw e;
        }

        return ledgerNameMap;
    }
//

    public static Map<String, String> getLedgerFromGroupName(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";
        Connection connection = null;
        try {
            mainQuery = "SELECT     tblgroup.group_name AS tblgroup_group_name,  tblledger.ledger_name AS tblledger_ledger_name,     tblledger.ledger_under AS tblledger_ledger_under,     tblledger.ledger_id AS tblledger_ledger_id FROM      tblgroup tblgroup INNER JOIN tblledger tblledger ON tblgroup.group_id = tblledger.ledger_under";
            if (groupNames != null && groupNames.size() > 0) {
                String groupList = "";
                Set<String> groupSet = new HashSet<String>();
                for (String stkGroup : groupNames) {
                    groupSet.add(stkGroup);
                }

                groupSet = GroupDAO.getUnderGroups(groupSet);
                groupNames = new ArrayList<String>();
                for (String stkGroup : groupSet) {
                    groupNames.add(stkGroup);
                }

                for (String stkGroup : groupNames) {
                    groupList += "'" + stkGroup + "' ,";
                }
                if (groupList.length() > 0) {
                    groupList = groupList.substring(0, groupList.length() - 2);
                }

                if (isIn == null || isIn) {
                    condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
                } else {
                    condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
                }

            }
            String query = mainQuery + condition;

            System.out.println("----------------->> query: " + query);
            connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);
            PreparedStatement psmt = connection.prepareStatement(query);
            ResultSet rsSet = psmt.executeQuery();
            while (rsSet.next()) {
                mapToReturn.put(rsSet.getString("tblledger_ledger_name"), rsSet.getLong("tblledger_ledger_id") + "");
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
//
//    public static Map<String, String> getLedgerContactDetails() throws SQLException, Exception {
//        System.out.println("Step--->>>18");
//        Map<String, String> ledgerContactDetails = new HashMap<String, String>();
//        Connection con = null;
//        try {
//            String query = "select ledger_id,ledger_emailId from tblledger";
//            con = DatabaseConnection1.GetConnection();
//            con.setAutoCommit(false);
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                ledgerContactDetails.put(String.valueOf(resultSet.getString("ledger_id")), resultSet.getString("ledger_emailId"));
//            }
//            con.commit();
//            con.close();
//        } catch (Exception e) {
//            if (con != null && !con.isClosed()) {
//                con.close();
//            }
//            e.printStackTrace();
//            throw e;
//        }
//
//        return ledgerContactDetails;
//    }
//

    public static List<LedgerDTO> exportAllLedgers() {

        List<LedgerDTO> ledgersDTOList = new ArrayList<LedgerDTO>();
        String mainQuery = "";
        Connection connection = null;
        try {

            connection = DatabaseConnection1.GetConnection();
            mainQuery = "select * from tblledger";
            PreparedStatement preparedStmt = connection.prepareStatement(mainQuery);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                LedgerDTO ledgerDTO = new LedgerDTO();

                ledgerDTO.setLedgerID(resultSet.getString("ledger_id"));
                ledgerDTO.setLedger_Alias(resultSet.getString("ledger_alias"));
                ledgerDTO.setLedger_Address(resultSet.getString("ledger_address"));
                ledgerDTO.setLedger_ContactNo(resultSet.getString("ledger_contactno"));
                ledgerDTO.setLedger_EmailID(resultSet.getString("ledger_emailId"));
                ledgerDTO.setLedger_IncomeTaxNo(resultSet.getString("ledger_inTaxo"));
                ledgerDTO.setLedger_Name(resultSet.getString("ledger_name"));
                ledgerDTO.setLedger_OpeningBalence(resultSet.getDouble("ledger_openingBalance"));
                ledgerDTO.setLedger_SaleTaxNo(resultSet.getString("ledger_saleTaxNo"));
                ledgerDTO.setLedger_Under(resultSet.getString("ledger_under") + "");
                ledgerDTO.setDebitOrCredit(resultSet.getInt("opening_type"));
                ledgerDTO.setLedger_isdeletable(resultSet.getString("isdeletable"));
                ledgerDTO.setLedger_CreditLimit(0D);////////////////////////////need to review.... Pankaj
                String query_ledger_limit = "select ledger_limit from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
                PreparedStatement preparedStmt_ledger_limit = connection.prepareStatement(query_ledger_limit);
                ResultSet resultSet_ledger_limit = preparedStmt_ledger_limit.executeQuery();
                if (resultSet_ledger_limit.next()) {
                    ledgerDTO.setLedger_CreditLimit(resultSet_ledger_limit.getDouble("ledger_limit"));
                } else {
                    ledgerDTO.setLedger_CreditLimit(0D);
                }
                ledgerDTO = gen.ImpExp.ImpExpUtil.valiDateAndRepairLedgerDTO(ledgerDTO);
                ledgersDTOList.add(ledgerDTO);
                resultSet_ledger_limit.close();
                preparedStmt_ledger_limit.close();
            }
            System.out.println("ledgersDTOList---DAO---" + ledgersDTOList.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return ledgersDTOList;

    }

    public static void upsertGroup(List<gen.account.ledger.LedgerDTO> ledgerDTOs) throws SQLException, ParseException, Exception {
        if (ledgerDTOs != null && ledgerDTOs.size() > 0) {
            List<gen.account.ledger.LedgerDTO> ledgerDTOstoInsert = new ArrayList<gen.account.ledger.LedgerDTO>();
            List<gen.account.ledger.LedgerDTO> ledgerDTOstoUpdate = new ArrayList<gen.account.ledger.LedgerDTO>();

            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerDTOs) {
                if (ledgerDTO.getLedgerID().trim().equalsIgnoreCase("")) {
                    ledgerDTOstoInsert.add(ledgerDTO);
                } else {
                    ledgerDTOstoUpdate.add(ledgerDTO);
                }
            }

            if (ledgerDTOstoInsert.size() > 0) {
                insertLedger(ledgerDTOstoInsert);
            }

            if (ledgerDTOstoUpdate.size() > 0) {
                UpdateLedger(ledgerDTOstoUpdate);
            }

        }

    }

    public static void insertLedger(List<gen.account.ledger.LedgerDTO> ledgerFormDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            ResultSet rs1;

            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerFormDTOList) {
                String sql = "";
                PreparedStatement ps;
                sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,ledger_ECSNumber,ledger_CVATNumber,ledger_CSTNumber,opening_type,created_date,created_user,isdeletable) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ledgerDTO.getLedger_Name().trim());
                ps.setString(2, ledgerDTO.getLedger_Under().trim());
                ps.setString(3, ledgerDTO.getLedger_Address().trim());
                ps.setString(4, ledgerDTO.getLedger_ContactNo().trim());
                ps.setString(5, ledgerDTO.getLedger_EmailID().trim());
                ps.setDouble(6, ledgerDTO.getLedger_OpeningBalence());
                ps.setString(7, ledgerDTO.getLedger_Alias().trim());
                ps.setString(8, ledgerDTO.getLedger_IncomeTaxNo().trim());
                ps.setString(9, ledgerDTO.getLedger_SaleTaxNo().trim());
                ps.setString(10, ledgerDTO.getLedger_ECSNumber().trim());
                ps.setString(11, ledgerDTO.getLedger_CVATNumber().trim());
                ps.setString(12, ledgerDTO.getLedger_CSTNumber().trim());
                if (ledgerDTO.getDebitOrCredit() == 1) {
                    ps.setInt(13, 1);
                } else {
                    ps.setInt(13, 2);
                }

                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(ledgerDTO.getLedger_Date().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(14, sqlStartDate);
                ps.setString(15, "1");
                ps.setString(16, ledgerDTO.getLedger_isdeletable());
                ps.executeUpdate();

                String q = "";
                int temp_id = 0;
                ResultSet rs = ps.getGeneratedKeys();
                List<Integer> idList = new ArrayList<Integer>();
                while (rs.next()) {
                    temp_id = rs.getInt(1);
                }
                ps.close();

                q = "insert into tblledgercreditlimit(ledger_id,ledger_limit) values(" + temp_id + "," + Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString()) + ")";
                st1.executeUpdate(q);

                q = "insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + "," + Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()) + "," + ledgerDTO.getDebitOrCredit().toString() + ")";  //2 for credit and 1 for debit
                st1.executeUpdate(q);
                conn.commit();

                Constants.LEDGER_TIME_STAMP = Constants.LEDGER_TIME_STAMP + 1;


            }

        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static void UpdateLedger(List<gen.account.ledger.LedgerDTO> ledgerFormDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();
            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerFormDTOList) {
                double blnc = 0;
                int DebCred = 0;
                String q = "";
                int ot = 0;//opening type
                if (ledgerDTO.getDebitOrCredit() == 2) {
                    ot = 2;
                } else {
                    ot = 1;
                }

                String sql = "UPDATE tblledger SET ledger_name=?,ledger_under=?,ledger_alias=?,ledger_address=?,ledger_contactno=?,ledger_emailid=?,ledger_openingbalance=?,ledger_intaxo=?,ledger_saletaxno=?,ledger_ECSNumber=?,ledger_CVATNumber=?, ledger_CSTNumber = ?,opening_type=?,modified_date=?,modified_user=?,isdeletable = ? where ledger_id=?";
                PreparedStatement ps = null;
                ps = conn.prepareStatement(sql);
                ps.setString(1, ledgerDTO.getLedger_Name().trim());
                ps.setString(2, ledgerDTO.getLedger_Under().trim());
                ps.setString(3, ledgerDTO.getLedger_Alias().trim());
                ps.setString(4, ledgerDTO.getLedger_Address().trim());
                ps.setString(5, ledgerDTO.getLedger_ContactNo().trim());
                ps.setString(6, ledgerDTO.getLedger_EmailID().trim());
                ps.setDouble(7, ledgerDTO.getLedger_OpeningBalence());
                ps.setString(8, ledgerDTO.getLedger_IncomeTaxNo().trim());
                ps.setString(9, ledgerDTO.getLedger_SaleTaxNo().trim());
                ps.setString(10, ledgerDTO.getLedger_ECSNumber().trim());
                ps.setString(11, ledgerDTO.getLedger_CVATNumber().trim());
                ps.setString(12, ledgerDTO.getLedger_CSTNumber().trim());
                ps.setInt(13, ot);
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(ledgerDTO.getLedger_Date().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(14, sqlStartDate);
                ps.setString(15, "1");
                ps.setString(16, ledgerDTO.getLedger_isdeletable());
                ps.setString(17, ledgerDTO.getLedgerID());
                ps.executeUpdate();
                double c = 0;
                if (ledgerDTO.getLedger_CreditLimit().equals("") == false) {
                    c = Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString());
                }


                String query = "UPDATE tblledgercreditlimit SET ledger_limit = ? where ledger_id = ?";
                PreparedStatement ps1 = conn.prepareStatement(query);
                ps1.setDouble(1, Math.abs(ledgerDTO.getLedger_CreditLimit()));
                ps1.setString(2, ledgerDTO.getLedgerID());
                ps1.executeUpdate();
                ps1.close();

                String query1 = "UPDATE tblledgercurrentbalance SET ledger_currentBalance = ?,ledger_DebitOrCredit = ? where ledger_id = ? ";
                PreparedStatement ps2 = conn.prepareStatement(query1);
                ps2.setDouble(1, Math.abs(ledgerDTO.getLedger_OpeningBalence()));
                ps2.setInt(2, ledgerDTO.getDebitOrCredit());
                ps2.setString(3, ledgerDTO.getLedgerID());
                ps2.executeUpdate();
                ps2.close();

            }
            conn.commit();
            Constants.LEDGER_TIME_STAMP++;


        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }

    }

    public static List<LedgerDTO> getLedgerList(Set<String> ledgerNameMap, String type) throws SQLException {
        List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
        String query = "";
        String condition = "";
        Connection con = null;
        try {
            if (!Util.isEmpty(ledgerNameMap)) {
                String ledgerNameList = "";
                if (type.equalsIgnoreCase(Constants.LEDGER_NAME)) {
                    for (String ledgerName : ledgerNameMap) {
                        ledgerNameList = ledgerNameList + "'" + ledgerName + "',";
                    }
                    String ledgerNameList1 = ledgerNameList.substring(0, ledgerNameList.length() - 1);
                    condition = " where ledger_name in(" + ledgerNameList1 + ")";
                } else if (type.equalsIgnoreCase(Constants.LEDGER_ID)) {
                    for (String ledgerName : ledgerNameMap) {
                        ledgerNameList = ledgerNameList + "" + ledgerName + ",";
                    }
                    String ledgerNameList1 = ledgerNameList.substring(0, ledgerNameList.length() - 1);
                    condition = " where ledger_id in(" + ledgerNameList1 + ")";
                }
            }

            query = "select * from tblledger " + condition;
            con = DatabaseConnection1.GetConnection();


            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                LedgerDTO ledgerDTO = new LedgerDTO();
                System.out.println("is Ledger Name --------------- " + resultSet.getString("ledger_name"));
                System.out.println("is delatable --------------- " + resultSet.getString("isdeletable"));
                ledgerDTO.setLedgerID(resultSet.getString("ledger_id"));
                ledgerDTO.setLedger_Alias(resultSet.getString("ledger_alias"));
                ledgerDTO.setLedger_Address(resultSet.getString("ledger_address"));
                ledgerDTO.setLedger_ContactNo(resultSet.getString("ledger_contactno"));
                ledgerDTO.setLedger_EmailID(resultSet.getString("ledger_emailId"));
                ledgerDTO.setLedger_IncomeTaxNo(resultSet.getString("ledger_inTaxo"));
                ledgerDTO.setLedger_Name(resultSet.getString("ledger_name"));
                ledgerDTO.setLedger_OpeningBalence(resultSet.getDouble("ledger_openingBalance"));
                ledgerDTO.setLedger_SaleTaxNo(resultSet.getString("ledger_saleTaxNo"));
                ledgerDTO.setLedger_ECSNumber(resultSet.getString("ledger_ECSNumber"));
                ledgerDTO.setLedger_CVATNumber(resultSet.getString("ledger_CVATNumber"));
                ledgerDTO.setLedger_CSTNumber(resultSet.getString("ledger_CSTNumber"));
                ledgerDTO.setLedger_Under(resultSet.getString("ledger_under") + "");
                ledgerDTO.setDebitOrCredit(resultSet.getInt("opening_type"));
                ledgerDTO.setLedger_isdeletable(resultSet.getString("isdeletable"));
                ledgerDTO.setLedger_CreditLimit(0D);////////////////////////////need to review.... Pankaj
                String query_ledger_limit = "select ledger_limit from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
                PreparedStatement preparedStmt_ledger_limit = con.prepareStatement(query_ledger_limit);
                ResultSet resultSet_ledger_limit = preparedStmt_ledger_limit.executeQuery();
                if (resultSet_ledger_limit.next()) {
                    ledgerDTO.setLedger_CreditLimit(resultSet_ledger_limit.getDouble("ledger_limit"));
                } else {
                    ledgerDTO.setLedger_CreditLimit(0D);
                }
                resultSet_ledger_limit.close();
                preparedStmt_ledger_limit.close();


                String query_ledger_OPBL = "select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id=" + ledgerDTO.getLedgerID() + "";
                PreparedStatement preparedStmt_ledger_OPBL = con.prepareStatement(query_ledger_OPBL);
                ResultSet resultSet_ledger_OPBL = preparedStmt_ledger_OPBL.executeQuery();
                if (resultSet_ledger_OPBL.next()) {
                    ledgerDTO.setLedger_OpeningBalence(resultSet_ledger_OPBL.getDouble("ledger_currentBalance"));
                    ledgerDTO.setDebitOrCredit(resultSet_ledger_OPBL.getInt("ledger_DebitOrCredit"));
                } else {
                    ledgerDTO.setLedger_OpeningBalence(0.0);
                    ledgerDTO.setDebitOrCredit(1);
                }
                resultSet_ledger_OPBL.close();
                preparedStmt_ledger_OPBL.close();


                ledgerDTO = gen.ImpExp.ImpExpUtil.valiDateAndRepairLedgerDTO(ledgerDTO);
                ledgerDTOList.add(ledgerDTO);
            }
        } catch (SQLException ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }

        return ledgerDTOList;
    }

    public static boolean deleteLedger(gen.account.ledger.LedgerDTO ledgerDTO) throws SQLException {
        Boolean flag = false;
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();

            System.out.println("Ledgetr DTO -------------- IN DAO>>>>>>>>>>>>>>>" + ledgerDTO.getLedgerID());

            // if LedgerTransaction ARE PRESENT then Dont delete
            ResultSet rs = st.executeQuery("select trans_ledgerId from tbltransactionledger where trans_ledgerId=" + ledgerDTO.getLedgerID() + "");
            while (rs.next()) {
                System.out.println("tbltransactionledger-----------------------");
                flag = true;
            }
            rs.close();

//	    // if tblstockitemrate ARE PRESENT
//            ResultSet rs1 = st.executeQuery("select   custid from tblstockitemrate where custid=" + ledgerDTO.getLedgerID() + "");
//            while (rs1.next()) {
//		System.out.println("tblstockitemrate-----------------------");
//                flag = true;
//            }
//            rs1.close();


            if (flag) {
                flag = true;
            } else {

                String str = "delete from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
                st.executeUpdate("delete from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "");
                st.executeUpdate("delete from tblledgercurrentbalance where ledger_id=" + ledgerDTO.getLedgerID() + "");
                st.executeUpdate("delete from tblstockitemrate where custid=" + ledgerDTO.getLedgerID() + "");
                st.executeUpdate("delete from tblledger where ledger_id=" + ledgerDTO.getLedgerID() + "");

            }
            st.close();
            conn.commit();
            conn.close();

        } catch (SQLException ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return flag;
    }

    public static Map<String, String> getLedgerNameMap(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";
        mainQuery = " select ledger_id,ledger_Name from tblledger";
        Connection connection = null;
        try {
            if (groupNames != null && groupNames.size() > 0) {
                String groupList = "";
                Set<String> groupSet = new HashSet<String>();
                for (String stkGroup : groupNames) {
                    groupSet.add(stkGroup);
                }

                groupSet = gen.account.groupDTODAO.GroupDAO.getUnderGroups(groupSet);
                groupNames = new ArrayList<String>();
                for (String stkGroup : groupSet) {
                    groupNames.add(stkGroup);
                }

                for (String stkGroup : groupNames) {
                    groupList += "'" + stkGroup + "' ,";
                }
                if (groupList.length() > 0) {
                    groupList = groupList.substring(0, groupList.length() - 2);
                }

            }
            String query = mainQuery + condition;

            System.out.println("----------------->> query: " + query);
            connection = DatabaseConnection1.GetConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            ResultSet rsSet = psmt.executeQuery();
            while (rsSet.next()) {
                mapToReturn.put(rsSet.getString("ledger_name"), rsSet.getLong("ledger_id") + "");
            }
            rsSet.close();
            psmt.close();
            connection.close();
        } catch (Exception ex) {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            throw ex;
        }
        return mapToReturn;
    }

    public static List<gen.account.ledger.LedgerDTO> getLedgerInfoForSetOPBL(String ledgerNameStart) throws SQLException, Exception {
        List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
        String query = "";
        String where = " where ";
        String condition_1 = "";
        String condition_2 = "";
        Connection con = null;
        try {
            if (!ledgerNameStart.trim().isEmpty()) {
                condition_1 = " where UPPER(ledger_name) like UPPER('%" + ledgerNameStart + "%')";
            }

            if (!condition_1.isEmpty()) {
                condition_2 = " and isdeletable != 1";
            } else {
                condition_2 = " where isdeletable != 1";
            }
            query = "select * from tblledger " + condition_1 + condition_2;
            System.out.println("query ---------- " + query);
//            query = "select * from tblledger " + condition_1;
            con = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                LedgerDTO ledgerDTO = new LedgerDTO();
                System.out.println("isdelatable ------- " + resultSet.getString("isdeletable"));
                ledgerDTO.setLedgerID(resultSet.getString("ledger_id"));
                ledgerDTO.setLedger_Alias(resultSet.getString("ledger_alias"));
                ledgerDTO.setLedger_Address(resultSet.getString("ledger_address"));
                ledgerDTO.setLedger_ContactNo(resultSet.getString("ledger_contactno"));
                ledgerDTO.setLedger_EmailID(resultSet.getString("ledger_emailId"));
                ledgerDTO.setLedger_IncomeTaxNo(resultSet.getString("ledger_inTaxo"));
                ledgerDTO.setLedger_Name(resultSet.getString("ledger_name"));
                ledgerDTO.setLedger_OpeningBalence(resultSet.getDouble("ledger_openingBalance"));
                ledgerDTO.setLedger_SaleTaxNo(resultSet.getString("ledger_saleTaxNo"));
                ledgerDTO.setLedger_ECSNumber(resultSet.getString("ledger_ECSNumber"));
                ledgerDTO.setLedger_CVATNumber(resultSet.getString("ledger_CVATNumber"));
                ledgerDTO.setLedger_CSTNumber(resultSet.getString("ledger_CSTNumber"));
                ledgerDTO.setLedger_Under(resultSet.getString("ledger_under") + "");
                ledgerDTO.setDebitOrCredit(resultSet.getInt("opening_type"));
                ledgerDTO.setLedger_isdeletable(resultSet.getString("isdeletable"));
                ledgerDTO.setLedger_CreditLimit(0D);////////////////////////////need to review.... Pankaj
                String query_ledger_limit = "select ledger_limit from tblledgercreditlimit where ledger_id=" + ledgerDTO.getLedgerID() + "";
                PreparedStatement preparedStmt_ledger_limit = con.prepareStatement(query_ledger_limit);
                ResultSet resultSet_ledger_limit = preparedStmt_ledger_limit.executeQuery();
                if (resultSet_ledger_limit.next()) {
                    ledgerDTO.setLedger_CreditLimit(resultSet_ledger_limit.getDouble("ledger_limit"));
                } else {
                    ledgerDTO.setLedger_CreditLimit(0D);
                }
                resultSet_ledger_limit.close();
                preparedStmt_ledger_limit.close();


                String query_ledger_OPBL = "select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id=" + ledgerDTO.getLedgerID() + "";
                PreparedStatement preparedStmt_ledger_OPBL = con.prepareStatement(query_ledger_OPBL);
                ResultSet resultSet_ledger_OPBL = preparedStmt_ledger_OPBL.executeQuery();
                if (resultSet_ledger_OPBL.next()) {
                    ledgerDTO.setLedger_OpeningBalence(resultSet_ledger_OPBL.getDouble("ledger_currentBalance"));
                    ledgerDTO.setDebitOrCredit(resultSet_ledger_OPBL.getInt("ledger_DebitOrCredit"));
                } else {
                    ledgerDTO.setLedger_OpeningBalence(0.0);
                    ledgerDTO.setDebitOrCredit(1);
                }
                resultSet_ledger_OPBL.close();
                preparedStmt_ledger_OPBL.close();


                ledgerDTO = gen.ImpExp.ImpExpUtil.valiDateAndRepairLedgerDTO(ledgerDTO);
                ledgerDTOList.add(ledgerDTO);
            }
        } catch (Exception ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }

        return ledgerDTOList;
    }
}

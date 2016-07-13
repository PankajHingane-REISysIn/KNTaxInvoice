/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.DatabaseBackupRestore;

import gen.database.connection.DatabaseConnection1;
import gen.dto.LedgerDTO;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DatabaseBackUpRestoreDAO {
//
//    public static List<String> tableListFor_Insert = new ArrayList<String>();
//    public static List<String> tableListFor_Delete_Ledger = new ArrayList<String>();
//    public static List<String> tableListFor_Ledger_id = new ArrayList<String>();
//    public static List<String> tableListFor_Delete_StockItem = new ArrayList<String>();
//    public static List<String> tableListFor_StockItem_id = new ArrayList<String>();
//    public static List<String> tableListFor_Delete_Transaction = new ArrayList<String>();
//    public static List<String> tableListFor_Transaction_id = new ArrayList<String>();
//    public static List<String> tableListFor_InvTransaction_id = new ArrayList<String>();
//    public static String FromtableString = "atul_demo";
//    public static String TotableString = "1p";
//    public static List<String> tableListFor_Transaction_Ledgerid = new ArrayList<String>();
//    //  public static List<String> tableListFor_InvTransaction_id = new ArrayList<String>();
//    public static Map<String, String> map_ledger_id = new HashMap<String, String>();
//    public static Map<String, String> map_Stockitem_id = new HashMap<String, String>();
//    public static String databaseClassName = "com.mysql.jdbc.Driver";
//    public static String instantURL = "jdbc:mysql://localhost";
//    public static String portNo = "3308";
//
//    public static List<String> getCompanyList() {
//	List<String> list = new ArrayList<String>();
//	try {
//	    Class.forName(databaseClassName);
//
////	    Connection con = DriverManager.getConnection(instantURL, "root", " ");
////	    Connection con = DriverManager.getConnection(instantURL + ":" + portNo + "/" + "root", " ");
//	    Connection con = DriverManager.getConnection(instantURL + ":" + portNo, "root", "adm");
//
//	    con.setAutoCommit(false);
//	    ResultSet rs = con.getMetaData().getCatalogs();
//	    while (rs.next()) {
//		System.out.println("TABLE_CAT = " + rs.getString("TABLE_CAT"));
//		list.add(rs.getString("TABLE_CAT"));
//	    }
//
//	    rs.close();
//	    con.commit();
//	    con.close();
//	} catch (ClassNotFoundException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return list;
//    }
//
//    public static void delete_Records_By_Date(String date, String CallFrom) {
//	callTableList_For_Delete_Ledger();
//	callTableList_For_Delete_StockItem();
//	callTableList_For_Delete_Transaction();
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
////            String FromtableString = "aj";
////            String TotableString = "1p";
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    for (int i = 0; i < tableListFor_Delete_Transaction.size(); i++) {
//		System.out.println("Call from the date Value :::::::::::::::::::::::TTTTTTTT" + date);
//		if (date == null || CallFrom.equals("callfromInsert")) {
//		    query = "delete from " + TotableString + "." + tableListFor_Delete_Transaction.get(i);
//		} else if (date != null && CallFrom.equals("DirectlyCall")) {
//		    if (tableListFor_Delete_Transaction.get(i).equals("tbltransactionmain")) {
//			query = "delete from " + TotableString + "." + tableListFor_Delete_Transaction.get(i) + " where  trans_date > '" + date + "'";
//		    }
//		}
//		if (!query.isEmpty()) {
//		    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + query);
//		    prmt = conn.prepareStatement(query);
//		    prmt.executeUpdate();
//		}
//	    }
//
//
//	    tableListFor_Transaction_Ledgerid = ledger_iDs();
//
//	    String ledger_id = "";
//	    for (int i = 1; i <= tableListFor_Transaction_Ledgerid.size(); i++) {
//		System.out.println(tableListFor_Transaction_Ledgerid.size() - 1);
//		if (i == (tableListFor_Transaction_Ledgerid.size() - (tableListFor_Transaction_Ledgerid.size() - 1))) {
//		    ledger_id = tableListFor_Transaction_Ledgerid.get(i - 1);
//		} else {
//		    ledger_id = ledger_id + "," + tableListFor_Transaction_Ledgerid.get(i - 1);
//		}
//	    }
//	    System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQ" + ledger_id);
//
//	    for (int i = 0; i < tableListFor_Delete_Ledger.size(); i++) {
//		System.out.println("Call from the date Value :::::::::::::::::::::::" + date);
//
//		if (date == null || CallFrom.equals("callfromInsert")) {
//		    query = "delete from " + TotableString + "." + tableListFor_Delete_Ledger.get(i);
//		} else if (date != null && CallFrom.equals("DirectlyCall")) {
//		    if (tableListFor_Delete_Ledger.get(i).equals("tblledger")) {
//			query = "delete from " + TotableString + "." + tableListFor_Delete_Ledger.get(i) + " where dat > '" + date + "'";
//			//query = "delete from " + TotableString + "."+tableListFor_Delete_Ledger.get(i) + " where dat > '" + date + "' and ledger_id not in ("+ledger_id+")";
//			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + query);
//		    }
//		}
//
//
//		if (!query.isEmpty()) {
//		    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + query);
//		    prmt = conn.prepareStatement(query);
//		    prmt.executeUpdate();
//		}
//	    }
//
//
//	    for (int i = 0; i < tableListFor_Delete_StockItem.size(); i++) {
//		System.out.println("Call from the date Value :::::::::::::::::::::::" + date);
//
//		if (date == null || CallFrom.equals("callfromInsert")) {
//		    query = "delete from " + TotableString + "." + tableListFor_Delete_StockItem.get(i);
//		} else if (date != null && CallFrom.equals("DirectlyCall")) {
//		    // if (!tableListFor_Delete_Ledger.get(i).equals("tblgroup") || !tableListFor_Delete_Ledger.get(i).equals("tblledgercurrentbalance") || !tableListFor_Delete_Ledger.get(i).equals("tblledgercreditlimit")) {
//		    if (tableListFor_Delete_StockItem.get(i).equals("tblstockitem")) {
//			query = "delete from " + TotableString + "." + tableListFor_Delete_StockItem.get(i) + " where created_date > '" + date + "'";
//		    }
//		}
//
//		if (!query.isEmpty()) {
//		    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + query);
//		    prmt = conn.prepareStatement(query);
//		    prmt.executeUpdate();
//		}
//	    }
//
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void InsertDataofSecondaryData() {
////            String FromtableString = "aj";
////            String TotableString = "1p";
//
//	call_secondaryData_Ledger(TotableString, FromtableString);
//	call_secondaryData_Stockitem(TotableString, FromtableString);
//	call_secondaryData_Transaction_Main(TotableString, FromtableString);
//    }
//
//    private static void callTableList_For_Insert() {
//	tableListFor_Insert.clear();
//	tableListFor_Insert.add("tblgroup");
//	tableListFor_Insert.add("tblledger");
//	// tableListFor_Insert.add("tblledgercreditlimit");
//	// tableListFor_Insert.add("tblledgercurrentbalance");
//
//	tableListFor_Insert.add("tbluomtype");
//	tableListFor_Insert.add("tblunitofmeasure");
//	tableListFor_Insert.add("tblstockgroup");
//	tableListFor_Insert.add("tblstockitem");
//
////        tableListFor_Insert.add("tblstockitemcurrentbalance");
////
////       
////	tableListFor_Insert.add("tblaccountvouchersmaxid");
////	tableListFor_Insert.add("tbltransactionmain");
////
////	tableListFor_Insert.add("tbltransactionvat");
////	tableListFor_Insert.add("tbltransactionotherdetails");
////	tableListFor_Insert.add("tbltransactionledger");
////	tableListFor_Insert.add("tblinventorytransaction");
////	tableListFor_Insert.add("tblinventorytransactionitems");
////
////
////        tableListFor_Insert.add("tblstockitemopblupdaterecord");
////        tableListFor_Insert.add("tblstockitemrate");
////        tableListFor_Insert.add("tblstockitemtype");
////        tableListFor_Insert.add("tbltempopenclosebalance");
////        tableListFor_Insert.add("tbltransactiontype");
////
////
////        tableListFor_Insert.add("tblfinishitem");
////        tableListFor_Insert.add("tblfinishtype");
////        tableListFor_Insert.add("tbllogin");
////        tableListFor_Insert.add("tblothersetting");
////        tableListFor_Insert.add("tblproductiondetails");
////        tableListFor_Insert.add("tblproductionitems");
////        tableListFor_Insert.add("tblproductionrawmaterial");
////        tableListFor_Insert.add("tblaccountvouchersmaxid");
////        tableListFor_Insert.add("tbladsumudisettings");
////        
////        
////        tableList.add("tblavailable_voucher");
////        
////        
////        tableListFor_Insert.add("tblbackup");
////        tableListFor_Insert.add("tblbackupsetting");
////        tableListFor_Insert.add("tblcreatecompany");
////        tableListFor_Insert.add("tblcurrentlogin");
//    }
//
//    private static void callTableList_For_Delete_Ledger() {
//	tableListFor_Delete_Ledger.clear();
//	tableListFor_Delete_Ledger.add("tblledgercreditlimit");
//	tableListFor_Delete_Ledger.add("tblledgercurrentbalance");
//	tableListFor_Delete_Ledger.add("tblledger");
//	tableListFor_Delete_Ledger.add("tblgroup");
//
//    }
//
//    private static void callTableList_For_Delete_StockItem() {
//	tableListFor_Delete_StockItem.clear();
//	tableListFor_Delete_StockItem.add("tblstockitemcurrentbalance");
//	tableListFor_Delete_StockItem.add("tblstockitem");
//	tableListFor_Delete_StockItem.add("tblstockgroup");
//	tableListFor_Delete_StockItem.add("tblunitofmeasure");
//	tableListFor_Delete_StockItem.add("tbluomtype");
//    }
//
//    private static void callTableList_For_Delete_Transaction() {
//	tableListFor_Delete_Transaction.clear();
//	tableListFor_Delete_Transaction.add("tbltransactionvat");
//	tableListFor_Delete_Transaction.add("tbltransactionotherdetails");
//	tableListFor_Delete_Transaction.add("tbltransactionledger");
//	tableListFor_Delete_Transaction.add("tblinventorytransactionitems");
//	tableListFor_Delete_Transaction.add("tblinventorytransaction");
//	tableListFor_Delete_Transaction.add("tbltransactionmain");
//    }
//
//    public static void call_secondaryData_Stockitem(String TotableString, String FromtableString) {
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    tableListFor_StockItem_id.clear();
//
//	    query = "select * from " + TotableString + ".tblstockitem";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs1 = prmt.executeQuery();
//	    while (rs1.next()) {
//		tableListFor_StockItem_id.add(rs1.getString("si_id"));
//	    }
//
//	    query = "insert into " + TotableString + "." + " tblstockitemcurrentbalance" + " select * from " + FromtableString + "." + " tblstockitemcurrentbalance" + " where si_id = ?";
//	    PreparedStatement ps6 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_StockItem_id.size(); i++) {
//		ps6.setString(1, tableListFor_StockItem_id.get(i));
//		ps6.addBatch();
//	    }
//	    ps6.executeBatch();
//
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void call_secondaryData_Ledger(String TotableString, String FromtableString) {
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    tableListFor_Ledger_id.clear();
//
//	    query = "select * from " + TotableString + ".tblledger";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		tableListFor_Ledger_id.add(rs.getString("ledger_id"));
//	    }
//
//	    query = "insert into " + TotableString + "." + " tblledgercurrentbalance" + " select * from " + FromtableString + "." + " tblledgercurrentbalance" + " where ledger_id = ?";
//	    PreparedStatement ps4 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Ledger_id.size(); i++) {
//		ps4.setString(1, tableListFor_Ledger_id.get(i));
//		ps4.addBatch();
//	    }
//	    ps4.executeBatch();
//
//
//	    query = "insert into " + TotableString + "." + " tblledgercreditlimit" + " select * from " + FromtableString + "." + " tblledgercreditlimit" + " where ledger_id = ?";
//	    PreparedStatement ps5 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Ledger_id.size(); i++) {
//		ps5.setString(1, tableListFor_Ledger_id.get(i));
//		ps5.addBatch();
//	    }
//	    ps5.executeBatch();
//
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void call_secondaryData_Transaction_Main(String TotableString, String FromtableString) {
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    tableListFor_Transaction_id.clear();
//	    query = "select * from " + TotableString + ".tbltransactionmain";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		tableListFor_Transaction_id.add(rs.getString("trans_id"));
//	    }
//
//
//
//	    query = "insert into " + TotableString + "." + " tbltransactionvat" + " select * from " + FromtableString + "." + " tbltransactionvat" + " where trans_id = ?";
//	    PreparedStatement ps4 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Transaction_id.size(); i++) {
//		ps4.setString(1, tableListFor_Transaction_id.get(i));
//		ps4.addBatch();
//	    }
//	    ps4.executeBatch();
//
//
//	    query = "insert into " + TotableString + "." + " tbltransactionotherdetails" + " select * from " + FromtableString + "." + " tbltransactionotherdetails" + " where trans_id = ?";
//	    PreparedStatement ps5 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Transaction_id.size(); i++) {
//		ps5.setString(1, tableListFor_Transaction_id.get(i));
//		ps5.addBatch();
//	    }
//	    ps5.executeBatch();
//
//	    query = "insert into " + TotableString + "." + " tbltransactionledger" + " select * from " + FromtableString + "." + " tbltransactionledger" + " where trans_id = ?";
//	    PreparedStatement ps3 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Transaction_id.size(); i++) {
//		ps3.setString(1, tableListFor_Transaction_id.get(i));
//		ps3.addBatch();
//	    }
//	    ps3.executeBatch();
//
//
//	    query = "insert into " + TotableString + "." + "tblinventorytransaction" + " select * from " + FromtableString + "." + "tblinventorytransaction" + " where trans_id = ?";
//	    PreparedStatement ps1 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_Transaction_id.size(); i++) {
//		ps1.setString(1, tableListFor_Transaction_id.get(i));
//		ps1.addBatch();
//	    }
//	    ps1.executeBatch();
//
//	    tableListFor_InvTransaction_id.clear();
//	    query = "select * from " + TotableString + ".tblinventorytransaction";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs1 = prmt.executeQuery();
//	    while (rs1.next()) {
//		tableListFor_InvTransaction_id.add(rs1.getString("trans_id"));
//	    }
//
//	    System.out.println("INV trans_id >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + tableListFor_InvTransaction_id);
//	    query = "insert into " + TotableString + "." + "tblinventorytransactionitems" + " select * from " + FromtableString + "." + "tblinventorytransactionitems" + " where invtrans_id = ?";
//	    System.out.println("OOOOOOOOOOOOOOOOOOOOO" + query);
//	    PreparedStatement ps2 = conn.prepareStatement(query);
//	    for (int i = 0; i < tableListFor_InvTransaction_id.size(); i++) {
//		ps2.setString(1, tableListFor_InvTransaction_id.get(i));
//		ps2.addBatch();
//	    }
//	    ps2.executeBatch();
////            
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static List<String> ledger_iDs() {
//	List<String> returnList = new ArrayList<String>();
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//	    query = "select Distinct(trans_ledgerId) from " + TotableString + ".tbltransactionledger";
//	    prmt = conn.prepareStatement(query);
//	    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		returnList.add(rs.getString("trans_ledgerId"));
//	    }
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return returnList;
//    }
//
//    public static List<LedgerDTO> ledger_closing_balance(String date1, String date2) {
//	List<LedgerDTO> LedgerDTOList = new ArrayList<LedgerDTO>();
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    Statement st = conn.createStatement();
//	    Statement st1 = conn.createStatement();
//	    Statement st2 = conn.createStatement();
//
//	    String q = "";
//	    ResultSet rs1, rs2, rs3;
//	    double o = 00f;
//	    double cr = 00f;
//	    double d = 00f;
//	    int i = 0;
//
//	    map_ledger_id = returnLedger_Map();
//
//
//	    DecimalFormat df = new DecimalFormat("#.##");
//
//	    for (Map.Entry<String, String> e : map_ledger_id.entrySet()) {
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + map_ledger_id);
//		LedgerDTO ledgerDTO = new LedgerDTO();
//		q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + e.getValue() + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=6)";
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + q);
//		rs1 = st.executeQuery(q);
//
//		while (rs1.next()) {
//		    cr = cr + rs1.getDouble("totalCred");
//		}
//		rs1.close();
//
//		//---------------new query------------------
//		q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + e.getValue() + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=1)";
//		rs1 = st.executeQuery(q);
//		while (rs1.next()) {
//		    cr = cr + rs1.getDouble("totalCred");
//		}
//		rs1.close();
//		//------------------------------------------
//
//		q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_ledgerId=" + e.getValue() + " and trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex in (3,2))";
//		rs1 = st.executeQuery(q);
//		while (rs1.next()) {
//		    d = d + rs1.getDouble("totalDeb");
//		}
//		rs1.close();
//
//		//--------------find if ledger is under Cash In Hand-----------
//		long g_id = 0;
//		q = "select group_id from tblgroup where group_name='Cash In Hand'";
//		rs1 = st.executeQuery(q);
//		if (rs1.next()) {
//		    g_id = rs1.getLong("group_id");
//		}
//
//		ArrayList<Long> cash_ids = new ArrayList<Long>();
//		int ind = 0;
//		cash_ids.add(g_id);
//		int flg = 0;
//		do {
//		    q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
//		    ResultSet rs = st.executeQuery(q);
//		    while (rs.next()) {
//			cash_ids.add(rs.getLong("group_id"));
//		    }
//		    if (ind == cash_ids.size() - 1) {
//			flg = 1;
//		    }
//		    ind++;
//		} while (flg == 0);
//
//		//-------------------------------------------------------------
//
////	    q = "select ledger_openingBalance,opening_type,ledger_under from tblledger where ledger_id=" + e.getKey() + "";
////	    rs1 = st.executeQuery(q);
////	    if (rs1.next()) {
////		i = 0;
////		while (i < cash_ids.size()) {
////		    if (rs1.getLong("ledger_under") == cash_ids.get(i)) {
////			flag_cash_in_hand = 1;
////			break;
////		    }
////		    i++;
////		}
////		
////		o = rs1.getDouble("ledger_openingBalance");
////		if (rs1.getInt("opening_type") == 2) {
////		    o = o + cr - d;
////		    
////		    labelOpening.setText("" + df.format(Math.abs(o)));
////		    if (o < 0) {
////			labelOType.setText("Debit");
////		    } else {
////			labelOType.setText("Credit");
////		    }
////		} else {
////		    o = o + d - cr;
////		    labelOpening.setText("" + df.format(Math.abs(o)));
////		    if (o < 0) {
////			labelOType.setText("Debit");
////		    } else {
////			labelOType.setText("Credit");
////		    }
////		}
////	    }
//		Label labelOType = new Label();
//		double op = 0D;
//		//----------opening balance---------
//		q = "select ledger_openingBalance,opening_type from tblledger where ledger_id=" + e.getValue() + "";
//		rs3 = st2.executeQuery(q);
//		if (rs3.next()) {
//		    op = rs3.getDouble("ledger_openingBalance");
////		labelOpening.setText("" + df.format(op));
//		    if (rs3.getInt("opening_type") == 1) {
//			labelOType.setText("Debit");
//		    } else {
//			labelOType.setText("Credit");
//		    }
//		}
//		rs3.close();
//		//----------------------------------
//
//
//		double deb_qty = 00f;
//		double cred_qty = 00f;
//
//		//    q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex<>7 order by trans_date";
//		q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date < '" + date1 + "' and trans_typeIndex<>7 order by trans_date";
//		rs1 = st.executeQuery(q);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + q);
//		while (rs1.next()) {
//		    q = "select distinct(trans_type),trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_ledgerId=" + e.getValue() + "";
//		    rs2 = st1.executeQuery(q);
//		    System.out.println("-------------------------------------" + q);
//		    while (rs2.next()) {
//			if (rs1.getInt("trans_typeIndex") == 1 || rs1.getInt("trans_typeIndex") == 2) {
//			    //--------select ledger of opposite transaction type i.e.debit X Credit----------
//			    q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + "";
//			    ResultSet rs4 = st2.executeQuery(q);
//			    while (rs4.next()) {
//
//				//Creditted-Debitted Amount
//				if (rs4.getInt("trans_type") == 1) {
//				    deb_qty = rs4.getDouble("trans_amt") + deb_qty;
//				} else {
//				    cred_qty = rs4.getDouble("trans_amt") + cred_qty;
//				}
//			    }
//			    rs4.close();
//			} else {
//			    //--------select ledger of opposite transaction type i.e.debit X Credit----------
//			    q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + " and trans_index=" + rs2.getLong("trans_index") + "";
//			    ResultSet rs4 = st2.executeQuery(q);
//			    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + q);
//			    while (rs4.next()) {
//
//				//Creditted-Debitted Amount
//				if (rs4.getInt("trans_type") == 1) {
//				    deb_qty = rs4.getDouble("trans_amt") + deb_qty;
//				} else {
//				    cred_qty = rs4.getDouble("trans_amt") + cred_qty;
//				}
//			    }
//			    rs4.close();
//			}
//
//		    }
//		    rs2.close();
//		}
//		rs1.close();
//
////	    //----------Count Total Debitted and creditted Amount---------
////	    if (tableLedger.getRowCount() > 0) {
////		i = 0;
////		double deb_qty = 00f;
////		double cred_qty = 00f;
////		System.out.println("TABLE COUNT::::::;" + tableLedger.getRowCount());
////		while (i < tableLedger.getRowCount()) {
////		    System.out.println("TABLE VALUE AT i,3::::::;" + tableLedger.getValueAt(i, 3) != null);
////		    if (tableLedger.getValueAt(i, 3) != null) {
////			if (tableLedger.getValueAt(i, 3).toString().equals("Receipt") || tableLedger.getValueAt(i, 3).toString().equals("Payment")) {
////			    if (tableLedger.getValueAt(i, 5).toString().equals("-") == false && tableLedger.getValueAt(i, 5).toString().equals("") == false) {
////				deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(i, 5).toString());
////				// System.out.println("DEBIT QUANTITY::::::"+deb_qty);
////			    }
////			    if (tableLedger.getValueAt(i, 6).toString().equals("-") == false && tableLedger.getValueAt(i, 6).toString().equals("") == false) {
////				cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(i, 6).toString());
////			    }
////			    
////			} else if ((tableLedger.getValueAt(i, 3).toString().equals("Sales") || tableLedger.getValueAt(i, 3).toString().equals("Purchase"))) {
////			    if (tableLedger.getValueAt(i, 5).toString().equals("-") == false && tableLedger.getValueAt(i, 5).toString().equals("") == false) {
////				deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(i, 5).toString());
////				System.out.println("DEBIT QUANTITY::::::" + deb_qty);
////			    }
////			    if (tableLedger.getValueAt(i, 6).toString().equals("-") == false && tableLedger.getValueAt(i, 6).toString().equals("") == false) {
////				cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(i, 6).toString());
////				System.out.println("CREDIT QUANTITY::::::" + cred_qty);
////			    }
////			}
////		    }
////		    i++;
////		}
////		
////		labelTotalDebit.setText("" + decformat.format(deb_qty));
////		labelTotalCredit.setText("" + decformat.format(cred_qty));
////	    } else {
////		labelTotalDebit.setText("0");
////		labelTotalCredit.setText("0");
////	    }
//		//-----------------------------------------------------------
//
//		//----------------calculate closing balance------------------
//		//o = Double.parseDouble(labelOpening.getText());
//		//cr = Double.parseDouble(labelTotalCredit.getText());
//		//d = Double.parseDouble(labelTotalDebit.getText());
//		double cl = 00f;
//		int cl_type = 5;
//		if (labelOType.getText().equalsIgnoreCase("Credit")) {
//		    cl = op + cred_qty - deb_qty;
//		    //labelClosing.setText("" + Math.abs(cl));
//		    if (cl < 0) {
//			//labelCType.setText("Debit");
//			cl_type = 1;
//		    } else {
//			//labelCType.setText("Credit");
//			cl_type = 2;
//		    }
//		} else {
//		    cl = op + deb_qty - cred_qty;
////		    labelClosing.setText("" + decformat.format(Math.abs(cl)));
//		    if (cl > 0) {
//			//labelCType.setText("Debit");
//			cl_type = 1;
//		    } else {
//			//labelCType.setText("Credit");
//			cl_type = 2;
//		    }
//		}
//
//
//		ledgerDTO.setLedger_Name(e.getKey());
//		ledgerDTO.setDebitOrCredit(cl_type);
//		ledgerDTO.setLedger_OpeningBalence(cl);
//
//
//
//		LedgerDTOList.add(ledgerDTO);
//		System.out.println("DEEEEEEEEEEEEEEEEEEE" + cl);
//		System.out.println("Ledeger NAme..........." + e.getKey());
//		System.out.println("Credit or Debit....." + cl_type);
//	    }
//	    //-------------------------------------------------------------
//	} catch (SQLException ex) {
//	    //  Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
//	}
//	return LedgerDTOList;
//    }
//
//    public static Map<String, String> returnLedger_Map() {
//
//	map_ledger_id.clear();
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    query = "select * from " + FromtableString + ".tblledger";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		map_ledger_id.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
//	    }
//
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return map_ledger_id;
//    }
//
//    public static Map<String, String> returnStockItem_Map() {
//
//	map_Stockitem_id.clear();
//	try {
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    PreparedStatement prmt = null;
//	    String query = "";
//
//	    query = "select * from " + FromtableString + ".tblstockitem";
//	    prmt = conn.prepareStatement(query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		map_Stockitem_id.put(rs.getString("si_name"), rs.getString("si_id"));
//	    }
//
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	} catch (SQLException ex) {
//	    Logger.getLogger(DatabaseBackUpRestoreDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return map_Stockitem_id;
//    }
}

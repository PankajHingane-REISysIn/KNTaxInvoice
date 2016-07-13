/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.profitandloss;

import gen.database.connection.DatabaseConnection1;
import gen.display.report.TrialBalanceDTO;
import gen.dto.Constants;
import gen.dto.GroupDTO;
import gen.dto.LedgerDTO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class ProfitAndLossDAO {

    static double saleItems = 0D;
    static double purchaseItems = 0D;
    static double salepurchaseItemAmountTotal = 0D;
    static double totalAmountThroughSale = 0D;
    static double totalAmountThroughPurchase = 0D;
    static double profitorlossSALE = 0D;
    static double profitorlossPURCHASE = 0D;
    static double netprofitorlossSALE = 0D;
    static double netprofitorlossPURCHASE = 0D;
    static String date1_for_closing = "";
    static String date2_for_closing = "";
    static int call_For_Closing = 1;
    static int call_For_Opening = 1;
    // static   List<TrialBalanceDTO> trialBalanceDTOList = new ArrayList<TrialBalanceDTO>(); 

    public static List<TrialBalanceDTO> getGroupTransactions(List<String> groups, String fromDateStr, String toDateStr) throws SQLException, IOException {
	//   Double finalTotal = 0D;
	// return GroupDTOList
	List<TrialBalanceDTO> groupDTOList = new ArrayList<TrialBalanceDTO>();
	// map of PrimaryGroup and their SubGroup
	Map<String, List<String>> mapMainGroupIdWithSubGroupList = getGroupsHierarchy();

	for (Map.Entry<String, List<String>> e : mapMainGroupIdWithSubGroupList.entrySet()) {
	    // get trialBalanceDto for PrimaryGroup and theirSubGroup 
	    TrialBalanceDTO trialBalanceDTO = getTransaction(e.getValue(), fromDateStr, toDateStr);
	    // get trialBalanceDTOList and add it to groupDTOList 
	    groupDTOList.add(trialBalanceDTO);
	}
	return groupDTOList;
    }

    public static Map<String, Double> getOpeningBalence(List<String> listOfStockItems, List<String> listOfStockGroups, String fromDateStr, String toDateStr) throws SQLException {
	Map<String, Double> mapToReturn = new HashMap<String, Double>();
	Map<String, Double> saleMap = new HashMap<String, Double>();
	Map<String, Double> purchaseMap = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();
	try {
	    String dateQuery = "";
	    String groupByQuery = " group by tblstockitem_si_name";
	    if (fromDateStr != null && toDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && !toDateStr.trim().equalsIgnoreCase("")) {
		dateQuery = " AND trans_date  BETWEEN '" + fromDateStr + "' AND '" + toDateStr + "' ";
	    } else if (fromDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && (toDateStr == null || toDateStr.trim().equalsIgnoreCase(""))) {
		dateQuery = " AND trans_date  < '" + fromDateStr + "' ";
	    }

	    String query1 = "SELECT     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     sum(tblinventorytransactionitems.`invtrans_qty`) AS tblinventorytransactionitems_invtrans_qty,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_rate` AS tblstockitem_si_rate,     tblstockitem.`si_openingBalance` AS tblstockitem_si_openingBalance,tblstockitem.`si_name` AS tblstockitem_si_name,tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM      `tblinventorytransaction` tblinventorytransaction INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblinventorytransaction.`invtrans_id` = tblinventorytransactionitems.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id`     INNER JOIN `tblstockitem` tblstockitem ON tblinventorytransactionitems.`invtrans_itemId` = tblstockitem.`si_id` WHERE  trans_typeIndex = 1";
	    query1 += dateQuery + groupByQuery;
	    PreparedStatement ps1 = conn.prepareStatement(query1);
	    ResultSet rs1 = ps1.executeQuery();
	    while (rs1.next()) {
		saleMap.put(rs1.getString("tblstockitem_si_name"), rs1.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    }


	    String query2 = "SELECT     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     sum(tblinventorytransactionitems.`invtrans_qty`) AS tblinventorytransactionitems_invtrans_qty,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_rate` AS tblstockitem_si_rate,     tblstockitem.`si_openingBalance` AS tblstockitem_si_openingBalance,tblstockitem.`si_name` AS tblstockitem_si_name ,tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM      `tblinventorytransaction` tblinventorytransaction INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblinventorytransaction.`invtrans_id` = tblinventorytransactionitems.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id`     INNER JOIN `tblstockitem` tblstockitem ON tblinventorytransactionitems.`invtrans_itemId` = tblstockitem.`si_id` WHERE  trans_typeIndex = 2 ";
	    query2 += dateQuery + groupByQuery;
	    PreparedStatement ps2 = conn.prepareStatement(query2);
	    ResultSet rs2 = ps2.executeQuery();
	    while (rs2.next()) {
		purchaseMap.put(rs2.getString("tblstockitem_si_name"), rs2.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    }

	    Map<String, Double> stkItemInitialOpenBalMap = getStockItemOpeningBalence(null, null);
	    if (stkItemInitialOpenBalMap != null) {
		for (String openBalKey : stkItemInitialOpenBalMap.keySet()) {
		    mapToReturn.put(openBalKey, stkItemInitialOpenBalMap.get(openBalKey));
		}
	    }

	    if (purchaseMap != null) {
		for (String purchaseKey : purchaseMap.keySet()) {
		    if (mapToReturn.containsKey(purchaseKey)) {
			mapToReturn.put(purchaseKey, mapToReturn.get(purchaseKey) + purchaseMap.get(purchaseKey));
		    }
		}
	    }

	    if (saleMap != null) {
		for (String saleKey : saleMap.keySet()) {
		    if (mapToReturn.containsKey(saleKey)) {
			mapToReturn.put(saleKey, mapToReturn.get(saleKey) - saleMap.get(saleKey));
		    }
		}
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}
	return mapToReturn;
    }

    public static Map<String, Double> getStockItemRate(List<String> listOfStockItems, List<String> listOfStockGroups) throws SQLException {

	Map<String, Double> stockItemRate = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();

	try {

	    String query = "SELECT     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_rate` AS tblstockitem_si_rate,     tblstockitem.`si_name` AS tblstockitem_si_name,     tblstockitem.`si_openingBalance` AS tblstockitem_si_openingBalance FROM      `tblstockitem` tblstockitem";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
		stockItemRate.put(rs.getString("tblstockitem_si_name"), rs.getDouble("tblstockitem_si_rate"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}

	return stockItemRate;

    }

    public static Map<String, Double> getStockItemOpeningBalence(List<String> listOfStockItems, List<String> listOfStockGroups) throws SQLException {
	Map<String, Double> stockItemOpeningBalence = new HashMap<String, Double>();
	Connection conn = DatabaseConnection1.GetConnection();
	try {
	    String query = "SELECT     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_rate` AS tblstockitem_si_rate,     tblstockitem.`si_name` AS tblstockitem_si_name,     tblstockitem.`si_openingBalance` AS tblstockitem_si_openingBalance FROM      `tblstockitem` tblstockitem";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		stockItemOpeningBalence.put(rs.getString("tblstockitem_si_name"), rs.getDouble("tblstockitem_si_openingBalance"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}
	return stockItemOpeningBalence;
    }

    public static Double getProfitAndLossOpeningBal(String date1, String date2) throws SQLException {
        Double profitAndLossOpenBal = 0D;
	try {
	    //// Dont use this Code (:request by Atul) 
	    date1_for_closing = date1;
	    date2_for_closing = date2;
	    //Double profitAndLossOpenBal = 0D;
	    Double stockItemSaleAmount = 0D;
	    Double stockItemPurchaseAmount = 0D;
	    Double saleProfitOrLossAmount = 0D;
	    Double purchaseProfitOrLossAmount = 0D;
	    Double stockItem_Balance = 0D;
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);

	    System.out.println("stockItem_Balance DAte^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + date1 + "      " + date2 + " call_Ofr_Opeinig" + call_For_Opening);
	    // calculate opening balnce of StockItem present in tblstockitem
	    String query = "";
	    if (call_For_Opening != 1) {
		query = "Select * from tblstockitem";
	    } else if (call_For_Opening == 1) {
                query = "Select * from tblstockitem where applicable_From_date <= '" + date2 + "'";
	    }
	    System.out.println("#############################################" + query);
	    PreparedStatement prmt = conn.prepareStatement(query);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		stockItem_Balance = (Double.parseDouble(rs.getString("si_openingBalance")) * Double.parseDouble(rs.getString("si_rate"))) + stockItem_Balance;
                System.out.println("*******     "+stockItem_Balance);
	    }

	    System.out.println("stockItem_Balance ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + stockItem_Balance);

            System.out.println("date1        " + date1);
            // if date is not greater than compnay creation then dont add sale and Purchase in opening balance
            java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

            java.util.Date date10 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(date1);
            java.sql.Date transaction_date = new java.sql.Date(date10.getTime());

            if (transaction_date.compareTo(sqlStartDate) > 0) {
	    // calculate opening balnce of StockItem present in sale transaction FromDate to ToDate
	    stockItemSaleAmount = getvalue(Constants.SALE_TYPE_INDEX.toString(), date1, date2);
	    // calculate opening balnce of StockItem present in Purchse transaction FromDate to ToDate
	    stockItemPurchaseAmount = getvalue(Constants.PURCHASE_TYPE_INDEX.toString(), date1, date2);
            }
	    System.out.println("stockItemSaleAmount ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + stockItemSaleAmount);
	    System.out.println("stockItemPurchaseAmount ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + stockItemPurchaseAmount);

	    // calculate Total openingbalance of StockItem which is (opening Balance + purchase(StockItem) - sale(StockItem) transaction)
	    profitAndLossOpenBal = (stockItem_Balance + stockItemPurchaseAmount) - stockItemSaleAmount;

	    conn.setAutoCommit(true);
	    conn.close();
	    ///////////////////////////////////////////////////////////////////////////////

        } catch (ParseException ex) {
            Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}
        return profitAndLossOpenBal;
    }

    public static Double getProfitAndLossClosingBal(String date1, String date2) throws SQLException {

	Double profitAndLossCloseBal = 0D;
	Double profitAndLossOpenBal = 0D;


	Double opening_Balance = 0D;
	Double closing_Sale = 0D;
	Double closing_Purchase = 0D;
	Double total_Closing_Balance = 0D;

	// calculate total Opening Balance 
	call_For_Opening = 5;
	opening_Balance = getProfitAndLossOpeningBal(date1_for_closing, date2_for_closing);
	call_For_Opening = 1;

	// set call_for_closing = 5 so that we get transaction of SAle and purchase between FromDate to ToDate
	call_For_Closing = 5;

	System.out.println("SALEEEEEEEEEEEEEEE 000000  From Closing" + getvalue(Constants.SALE_TYPE_INDEX.toString(), date1_for_closing, date2_for_closing));
	System.out.println("PURCHASEEEEEEEEEEEE ))))))))0 From Closing" + getvalue(Constants.PURCHASE_TYPE_INDEX.toString(), date1_for_closing, date2_for_closing));
	System.out.println("CXALLLLLLL FROM     total closing >>>>>>>>>>>>>>>>>>>");

	// calculate total Opening Balance 
	total_Closing_Balance = opening_Balance + getvalue(Constants.PURCHASE_TYPE_INDEX.toString(), date1_for_closing, date2_for_closing) - getvalue(Constants.SALE_TYPE_INDEX.toString(), date1_for_closing, date2_for_closing);
	System.out.println("total Closing........." + total_Closing_Balance);

	// set call_for_closing = 1 so that we get transaction of SAle and purchase less than FromDate for opening balance
	call_For_Closing = 1;
	return total_Closing_Balance;
    }

    public static Map<String, Double> getStockItemSaleAmount(List<String> listOfStockItems, List<String> listOfStockGroups, String fromDateStr, String toDateStr) throws SQLException {

	Map<String, Double> mapToReturn = new HashMap<String, Double>();

	Map<String, Double> saleMap = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();
	String dateQuery = "";
	String groupByQuery = " group by tblstockitem_si_name";
	if (fromDateStr != null && toDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && !toDateStr.trim().equalsIgnoreCase("")) {
	    dateQuery = " AND trans_date  BETWEEN '" + fromDateStr + "' AND '" + toDateStr + "' ";
	} else if (fromDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && (toDateStr == null || toDateStr.trim().equalsIgnoreCase(""))) {
	    dateQuery = " AND trans_date  < '" + fromDateStr + "' ";
	}

	String query1 = "SELECT     sum(tblinventorytransactionitems.`invtrans_amount`) AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.`invtrans_qty` AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.`invtrans_rate` AS tblinventorytransactionitems_invtrans_rate,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_name` AS tblstockitem_si_name,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.`trans_id` AS tblinventorytransaction_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex FROM      `tblstockitem` tblstockitem INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblstockitem.`si_id` = tblinventorytransactionitems.`invtrans_itemId`     INNER JOIN `tblinventorytransaction` tblinventorytransaction ON tblinventorytransactionitems.`invtrans_id` = tblinventorytransaction.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_typeIndex=1";
	query1 += dateQuery + groupByQuery;
	PreparedStatement ps1 = conn.prepareStatement(query1);
	ResultSet rs1 = ps1.executeQuery();

	while (rs1.next()) {
	    saleMap.put(rs1.getString("tblstockitem_si_name"), rs1.getDouble("tblinventorytransactionitems_invtrans_amount"));
	    //mapToReturn.put(rs1.getString("tblstockitem_si_name"), rs1.getDouble("tblinventorytransactionitems_invtrans_amount"));
	}

	if (saleMap != null) {
	    for (String saleKey : saleMap.keySet()) {
		mapToReturn.put(saleKey, saleMap.get(saleKey));
	    }
	}
	return mapToReturn;
    }

    public static Map<String, Double> getStockItemPurchaseAmount(List<String> listOfStockItems, List<String> listOfStockGroups, String fromDateStr, String toDateStr) throws SQLException {

	Map<String, Double> mapToReturn = new HashMap<String, Double>();

	Map<String, Double> purchaseMap = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();
	String dateQuery = "";
	String groupByQuery = " group by tblstockitem_si_name";
	if (fromDateStr != null && toDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && !toDateStr.trim().equalsIgnoreCase("")) {
	    dateQuery = " AND trans_date  BETWEEN '" + fromDateStr + "' AND '" + toDateStr + "' ";
	} else if (fromDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && (toDateStr == null || toDateStr.trim().equalsIgnoreCase(""))) {
	    dateQuery = " AND trans_date  < '" + fromDateStr + "' ";
	}

	String query2 = "SELECT     sum(tblinventorytransactionitems.`invtrans_amount`) AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.`invtrans_qty` AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.`invtrans_rate` AS tblinventorytransactionitems_invtrans_rate,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_name` AS tblstockitem_si_name,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.`trans_id` AS tblinventorytransaction_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex FROM      `tblstockitem` tblstockitem INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblstockitem.`si_id` = tblinventorytransactionitems.`invtrans_itemId`     INNER JOIN `tblinventorytransaction` tblinventorytransaction ON tblinventorytransactionitems.`invtrans_id` = tblinventorytransaction.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_typeIndex=2";
	query2 += dateQuery + groupByQuery;
	System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP" + query2);
	PreparedStatement ps2 = conn.prepareStatement(query2);
	ResultSet rs2 = ps2.executeQuery();

	while (rs2.next()) {
	    purchaseMap.put(rs2.getString("tblstockitem_si_name"), rs2.getDouble("tblinventorytransactionitems_invtrans_amount"));
	    //mapToReturn.put(rs2.getString("tblstockitem_si_name"), rs2.getDouble("tblinventorytransactionitems_invtrans_amount"));
	}

	if (purchaseMap != null) {
	    for (String purchaseKey : purchaseMap.keySet()) {
		mapToReturn.put(purchaseKey, purchaseMap.get(purchaseKey));
	    }
	}

	return mapToReturn;

    }

    public static Map<String, Double> getStockItemSaleQuantity(List<String> listOfStockItems, List<String> listOfStockGroups, String fromDateStr, String toDateStr) throws SQLException {
	Map<String, Double> mapToReturn = new HashMap<String, Double>();
	Map<String, Double> saleMap = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();
	String dateQuery = "";
	String groupByQuery = " group by tblstockitem_si_name";
	if (fromDateStr != null && toDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && !toDateStr.trim().equalsIgnoreCase("")) {
	    dateQuery = " AND trans_date  BETWEEN '" + fromDateStr + "' AND '" + toDateStr + "' ";
	} else if (fromDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && (toDateStr == null || toDateStr.trim().equalsIgnoreCase(""))) {
	    dateQuery = " AND trans_date  < '" + fromDateStr + "' ";
	}

	String query1 = "SELECT     sum(tblinventorytransactionitems.`invtrans_amount`) AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.`invtrans_qty` AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.`invtrans_rate` AS tblinventorytransactionitems_invtrans_rate,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_name` AS tblstockitem_si_name,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.`trans_id` AS tblinventorytransaction_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex FROM      `tblstockitem` tblstockitem INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblstockitem.`si_id` = tblinventorytransactionitems.`invtrans_itemId`     INNER JOIN `tblinventorytransaction` tblinventorytransaction ON tblinventorytransactionitems.`invtrans_id` = tblinventorytransaction.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_typeIndex=1";
	query1 += dateQuery + groupByQuery;
	PreparedStatement ps1 = conn.prepareStatement(query1);
	ResultSet rs1 = ps1.executeQuery();

	while (rs1.next()) {
	    saleMap.put(rs1.getString("tblstockitem_si_name"), rs1.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    //mapToReturn.put(rs1.getString("tblstockitem_si_name"), rs1.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    saleItems = saleItems + rs1.getDouble("tblinventorytransactionitems_invtrans_qty");

	}

	if (saleMap != null) {
	    for (String saleKey : saleMap.keySet()) {
		mapToReturn.put(saleKey, saleMap.get(saleKey));
	    }
	}

	return mapToReturn;

    }

    public static Map<String, Double> getStockItemPurchaseQuantity(List<String> listOfStockItems, List<String> listOfStockGroups, String fromDateStr, String toDateStr) throws SQLException {

	Map<String, Double> mapToReturn = new HashMap<String, Double>();

	Map<String, Double> purchaseMap = new HashMap<String, Double>();

	Connection conn = DatabaseConnection1.GetConnection();
	String dateQuery = "";
	String groupByQuery = " group by tblstockitem_si_name";
	if (fromDateStr != null && toDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && !toDateStr.trim().equalsIgnoreCase("")) {
	    dateQuery = " AND trans_date  BETWEEN '" + fromDateStr + "' AND '" + toDateStr + "' ";
	} else if (fromDateStr != null && !fromDateStr.trim().equalsIgnoreCase("") && (toDateStr == null || toDateStr.trim().equalsIgnoreCase(""))) {
	    dateQuery = " AND trans_date  < '" + fromDateStr + "' ";
	}

	String query2 = "SELECT     sum(tblinventorytransactionitems.`invtrans_amount`) AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.`invtrans_id` AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.`invtrans_itemId` AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.`invtrans_qty` AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.`invtrans_rate` AS tblinventorytransactionitems_invtrans_rate,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_name` AS tblstockitem_si_name,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tblinventorytransaction.`invtrans_id` AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.`trans_id` AS tblinventorytransaction_trans_id,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex FROM      `tblstockitem` tblstockitem INNER JOIN `tblinventorytransactionitems` tblinventorytransactionitems ON tblstockitem.`si_id` = tblinventorytransactionitems.`invtrans_itemId`     INNER JOIN `tblinventorytransaction` tblinventorytransaction ON tblinventorytransactionitems.`invtrans_id` = tblinventorytransaction.`invtrans_id`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tblinventorytransaction.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_typeIndex=2";
	query2 += dateQuery + groupByQuery;

	PreparedStatement ps2 = conn.prepareStatement(query2);
	ResultSet rs2 = ps2.executeQuery();

	while (rs2.next()) {
	    purchaseMap.put(rs2.getString("tblstockitem_si_name"), rs2.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    //mapToReturn.put(rs2.getString("tblstockitem_si_name"), rs2.getDouble("tblinventorytransactionitems_invtrans_qty"));
	    purchaseItems = purchaseItems + rs2.getDouble("tblinventorytransactionitems_invtrans_qty");
	}


	if (purchaseMap != null) {
	    for (String purchaseKey : purchaseMap.keySet()) {
		mapToReturn.put(purchaseKey, purchaseMap.get(purchaseKey));
	    }
	}

	return mapToReturn;

    }

    public static List<ProfitAndLossDTO> getAllDataTemp() {

	List<ProfitAndLossDTO> showProfitAndLoss = new ArrayList<ProfitAndLossDTO>();

	ProfitAndLossDTO prolossDTO = new ProfitAndLossDTO();

	prolossDTO.setSaleItems(saleItems);
	prolossDTO.setTotalAmountThroughSale(totalAmountThroughSale);
	prolossDTO.setProfitSALE(profitorlossSALE);
	prolossDTO.setNetProfitSALE(netprofitorlossSALE);

	prolossDTO.setPurchaseItems(purchaseItems);
	prolossDTO.setTotalAmountThroughPurchase(totalAmountThroughPurchase);
	prolossDTO.setProfitPURCHASE(profitorlossPURCHASE);
	prolossDTO.setNetProfitPURCHASE(netprofitorlossPURCHASE);

	showProfitAndLoss.add(prolossDTO);

	return showProfitAndLoss;

    }

    public static Double getvalue(String trans_type, String date1, String date2) {
	Double returnValue = 0D;
	try {
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    if (call_For_Closing == 1) {
		System.out.println("");
		query = "select trans_id from tbltransactionmain where trans_date <= '" + date1 + "'";
	    } else if (call_For_Closing == 5) {
		System.out.println("");
		query = "select trans_id from tbltransactionmain where trans_date <= '" + date2 + "' and trans_date >= '" + date1 + "'";
	    }
	    System.out.println("query!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + query);
	    PreparedStatement prmt5 = conn.prepareStatement(query);
	    ResultSet rs5 = prmt5.executeQuery();
	    while (rs5.next()) {
		query = "select invtrans_id from  tblinventorytransaction where trans_id = " + rs5.getString("trans_id") + " and  invtrans_type =" + trans_type + " ";
		PreparedStatement prmt = conn.prepareStatement(query);
		ResultSet rs = prmt.executeQuery();
		while (rs.next()) {
		    query = "select * from  tblinventorytransactionitems where invtrans_id = " + rs.getString("invtrans_id");
		    PreparedStatement prmt1 = conn.prepareStatement(query);
		    ResultSet rs1 = prmt1.executeQuery();
		    while (rs1.next()) {
			returnValue = Double.parseDouble(rs1.getString("invtrans_amount")) + returnValue;
		    }
		}
	    }
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return returnValue;
    }

    //////////////// Function From Trial Balance  (By aTUL)      //////////////////////////////////////////////
    public static Map<String, List<String>> getGroupsHierarchy() {

	Map<String, List<String>> mapToReturn = new HashMap<String, List<String>>();
	Map<String, GroupDTO> groupNameMap = new HashMap<String, GroupDTO>();
	Map<String, List<GroupDTO>> groupUnderMap = new HashMap<String, List<GroupDTO>>();
	Map<String, String> groupUnderIDsMap = new HashMap<String, String>();
	Map<String, String> allGroupWithIDsMap = new HashMap<String, String>();
	try {

	    Connection conn = DatabaseConnection1.GetConnection();
	    allGroupWithIDsMap = allGroupWithIDs();
	    // String query1 = "select group_id,group_name,group_under from tblgroup";
	    String query1 = "select group_id,group_name,group_under from tblgroup where group_name in " + "('" + Constants.SALES_ACCOUNT + "','" + Constants.PURCHASE_ACCOUNT + "','" + Constants.INCOME_DIRECT + "','" + Constants.INCOME_INDIRECT + "','" + Constants.EXPENSE_DIRECT + "','" + Constants.EXPENSE_INDIRECT + "')";
	    PreparedStatement ps1 = conn.prepareStatement(query1);
	    ResultSet rs1 = ps1.executeQuery();
	    Integer groupSize = 0;
	    while (rs1.next()) {
		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setGroup_id(rs1.getString("group_id"));
		groupDTO.setGroup_Name(rs1.getString("group_name"));
		groupDTO.setGroup_Under(rs1.getString("group_under"));
		groupNameMap.put(groupDTO.getGroup_Name(), groupDTO);
		groupUnderIDsMap.put(rs1.getString("group_id"), rs1.getString("group_under"));

		if (!groupUnderMap.containsKey(groupDTO.getGroup_Under())) {
		    groupUnderMap.put(groupDTO.getGroup_Under(), new ArrayList<GroupDTO>());
		}
		groupUnderMap.get(groupDTO.getGroup_Under()).add(groupDTO);
		groupSize++;
	    }

            // getting parent group and its subGroup.
	    for (Map.Entry<String, String> e1 : groupUnderIDsMap.entrySet()) {
		//  System.out.println("groupUnderIDsMap  KEYYYYYYYYYYY" + e1.getKey());
		//  System.out.println("groupUnderIDsMap VALUeeeeeeeeee" + e1.getValue());

		String id = e1.getKey();
		ArrayList<String> cash_ids = new ArrayList<String>();
		cash_ids.add(id);

		int index = 0;
		int flg = 0;
		do {
		    for (Map.Entry<String, String> e2 : allGroupWithIDsMap.entrySet()) {
			if (cash_ids.get(index).equalsIgnoreCase(e2.getValue())) {
                            System.out.println("Under of Group -------------  "+e2.getKey());
			    cash_ids.add(e2.getKey());
			}
		    }
		    if (index == cash_ids.size() - 1) {
			flg = 1;
		    }
		    index++;
		} while (flg == 0);
		mapToReturn.put(id, cash_ids);
                System.out.println("ID of Group -------------     "+id);

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return mapToReturn;
    }

    public static Map<String, String> allGroupWithIDs() {
	Map<String, String> mapToreturn = new HashMap<String, String>();
	try {
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    query = "Select * from tblgroup";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		mapToreturn.put(rs.getString("group_id"), rs.getString("group_under"));
	    }
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return mapToreturn;
    }

    public static List<LedgerDTO> allLedgerDetails() {
	List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
	try {
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    query = "Select * from tblledger";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		LedgerDTO ledgerDTO = new LedgerDTO();
		ledgerDTO.setLedger_Under(rs.getString("ledger_under"));
		ledgerDTO.setDebitOrCredit(rs.getInt("opening_type"));
		ledgerDTO.setLedger_Name(rs.getString("ledger_name"));
		ledgerDTO.setLedger_OpeningBalence(rs.getDouble("ledger_openingBalance"));
	    }
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return ledgerDTOList;
    }

    public static TrialBalanceDTO getTransaction(List<String> GroupNames, String fromDateStr, String toDateStr) throws SQLException {
	TrialBalanceDTO trialBalanceDTO = new TrialBalanceDTO();
	List<TrialBalanceDTO> trialBalanceDTOList = new ArrayList<TrialBalanceDTO>();

	Double creditLedger_Balance = 0D;
	Double debitLedger_Balance = 0D;
	Double credit_Debit_Difference = 0D;
	String parent_GroupName = "";

	/// set Primary_Group and their Subgroup to Str1
	String str1 = "";
	for (int i = 0; i < GroupNames.size(); i++) {
	    parent_GroupName = GroupNames.get(0);
	    if (i != GroupNames.size() - 1) {
		str1 = str1 + GroupNames.get(i) + ",";
	    } else {
		str1 = str1 + GroupNames.get(i);
	    }
	}

	double debit = 00f;
	double credit = 00f;
	int i = 0;
	int flag = 0;
	Connection conn = DatabaseConnection1.GetConnection();

	////// Calculate total credit or debit of ledgers
        String query = "Select * from tblledger where ledger_under in (" + str1 + ") and applicable_From_date between '" + fromDateStr + "' and '" + toDateStr + "'";

        //  String query = "Select * from tblledger where ledger_under in (" + str1 + ") and ledger_applicable_From_date >= '" + fromDateStr + "'";
	//     String query = "Select * from tblledger where ledger_under in (" + str1 + ")";
	PreparedStatement prmt = conn.prepareStatement(query);
	// System.out.println("asdasdasdasdsadsadsadsdasd                                               "+query);
	ResultSet rs = prmt.executeQuery();
	System.out.println("Query For Ledger " + query);
	while (rs.next()) {
	    if (rs.getInt("opening_type") == 2) {
		creditLedger_Balance = creditLedger_Balance + rs.getDouble("ledger_openingBalance");
		//  credit_String = "credit";
	    } else if (rs.getInt("opening_type") == 1) {
		debitLedger_Balance = debitLedger_Balance + rs.getDouble("ledger_openingBalance");
		// debit_String = "Debit";
	    }
	}

        System.out.println("Geeting Credit opeinig Blance of ledger---"+creditLedger_Balance);
        System.out.println("Geeting Debit  opeinig Blance of ledger---"+debitLedger_Balance);
        
	String query2 = "select sum(trans_amt) as totalDeb, trans_type from tbltransactionledger ";
	String whereCondition = "";
	String whereCondition1 = "where trans_id in";
	String subQuery1 = "(select trans_id from tbltransactionmain ";
	String dateQuery = "where trans_date>='" + fromDateStr + "' and trans_date<='" + toDateStr + "')";
	String whereCondition2 = "and trans_ledgerid in";
	String subQuery2 = "(select ledger_id from tblledger ";

	ArrayList<String> group_IDs = new ArrayList<String>();

	if (GroupNames != null && GroupNames.size() > 0) {
	    String groupID = "";
	    for (String str : GroupNames) {
		groupID = str;
		group_IDs.add(groupID);
	    }
	    String str = "";
	    int ind = 0;
	    while (ind < group_IDs.size()) {
		if (ind != group_IDs.size() - 1) {
		    str = str + group_IDs.get(ind) + ",";
		} else {
		    str = str + group_IDs.get(ind);
		}
		ind++;
	    }
	    whereCondition = "WHERE      ledger_under IN  (" + str + ")) group by trans_type";
	}

	//query2 += whereCondition1 + subQuery1 + dateQuery + whereCondition2 + subQuery2 + whereCondition3 + subQuery3 + whereCondition;
	Integer debCred = 500;

	////// Calculate total credit or debit of Groups
	query2 += whereCondition1 + subQuery1 + dateQuery + whereCondition2 + subQuery2 + whereCondition;
	System.out.println("Query For Transaction " + query2);
	PreparedStatement ps2 = conn.prepareStatement(query2);
	ResultSet rs2 = ps2.executeQuery();
	while (rs2.next()) {
	    debCred = rs2.getInt("trans_type");
	    if (debCred == Constants.DEBIT) {
		credit = rs2.getDouble("totalDeb");
		//trialBalanceDTO.setCreditAmount(credit);
	    } else if (debCred == Constants.CREDIT) {
		debit = rs2.getDouble("totalDeb");
		//trialBalanceDTO.setDebitAmount(debit);
	    }
	}

	////// ADD total credit or debit of Groups and Ledger
	credit = creditLedger_Balance + credit;
	debit = debitLedger_Balance + debit;
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	////// Calculate Difference between credit or debit of Groups and Ledger
	if (credit > debit) {
	    credit_Debit_Difference = credit - debit;
	    trialBalanceDTO.setTrans_Type(Constants.CREDIT);
	} else {
	    credit_Debit_Difference = debit - credit;
	    trialBalanceDTO.setTrans_Type(Constants.DEBIT);
	}
	trialBalanceDTO.setCredit_Debit_Difference(credit_Debit_Difference);
	trialBalanceDTO.setCoreGroup_id(Long.parseLong(parent_GroupName));
	//////////////////////////////////////////////////////////////////////////////////////////////////////////     

	if (i == GroupNames.size() - 1) {
	    flag = 1;
	}
	i++;
	// trialBalanceDTOList.add(trialBalanceDTO);
	DecimalFormat decformat = new DecimalFormat("#.##");
	System.out.println("Total Debit--->>" + decformat.format(debit));
	System.out.println("Total Credit--->>" + decformat.format(credit));
	System.out.println("Total Diffrence--->>" + credit_Debit_Difference);
	System.out.println("************************************************************************");
	return trialBalanceDTO;
    }

    public static Map<String, String> allGroupNameWithIDs() {
	Map<String, String> mapToreturn = new HashMap<String, String>();
	try {
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    query = "Select * from tblgroup";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		mapToreturn.put(rs.getString("group_id"), rs.getString("group_name"));
	    }
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return mapToreturn;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    public static void returntype() {
	try {
	    Connection conn = DatabaseConnection1.GetConnection();
	    String check = "";
	    String query = "Select * from demo";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		check = rs.getString("address");
	    }
	    if (check == null) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++" + check);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ProfitAndLossDAO.class.getName()).log(Level.SEVERE, null, ex);
	}

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.GroupDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc5
 */
public class GroupSummaryDAO {

    public static Map<String, String> getGroupDetails() throws SQLException, Exception {
	Connection conn = null;
	Map<String, String> mapToReturn = new HashMap<String, String>();
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String queryOne = "select group_id,group_name from tblgroup";
	    PreparedStatement ps1 = conn.prepareStatement(queryOne);
	    ResultSet rs1 = ps1.executeQuery();
	    while (rs1.next()) {
		if (!rs1.getString("group_name").equalsIgnoreCase("Primary")) {
		    mapToReturn.put(rs1.getString("group_id"), rs1.getString("group_name"));
		}
	    }
	    ps1.close();
	    rs1.close();
	    conn.commit();
	    conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return mapToReturn;
    }

    public static Map<String, List<GroupSummaryDTO>> getLedgerDetails() throws SQLException, Exception {
	Connection conn = null;
	Map<String, List<GroupSummaryDTO>> mapOfLedgerToReturn = new HashMap<String, List<GroupSummaryDTO>>();
	try {
	    Map<String, String> mapOfGroupsToReceive = getGroupDetails();
	    Map<String, List<GroupSummaryDTO>> mapOfLedgerToReturn1 = new HashMap<String, List<GroupSummaryDTO>>();
	    List<GroupSummaryDTO> listOfLedger = new ArrayList<GroupSummaryDTO>();
	    List<Integer> ledger_underList = new ArrayList<Integer>();

	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String queryToGetLedger = "select ledger_id,ledger_name,ledger_under,ledger_OpeningBalance,opening_type from tblledger";
	    PreparedStatement psLedger = conn.prepareStatement(queryToGetLedger);
	    ResultSet rsLedger = psLedger.executeQuery();
	    while (rsLedger.next()) {

		GroupSummaryDTO groupSummaryDTO = new GroupSummaryDTO();

		groupSummaryDTO.setLedger_id(rsLedger.getInt("ledger_id"));
		groupSummaryDTO.setLedger_name(rsLedger.getString("ledger_name"));
		groupSummaryDTO.setLedger_under(rsLedger.getInt("ledger_under"));
		groupSummaryDTO.setOpeningBalance(rsLedger.getDouble("ledger_OpeningBalance"));
		groupSummaryDTO.setTrans_Type(rsLedger.getInt("opening_type"));

		if (!ledger_underList.contains(rsLedger.getInt("ledger_under"))) {
		    ledger_underList.add(Integer.parseInt(rsLedger.getString("ledger_under")));
		}
		listOfLedger.add(groupSummaryDTO);
	    }

	    for (int i = 0; i < ledger_underList.size(); i++) {
		List<GroupSummaryDTO> listOfLedger_final = new ArrayList<GroupSummaryDTO>();
		for (GroupSummaryDTO groupSummaryDTO : listOfLedger) {
		    if (groupSummaryDTO.getLedger_under() == ledger_underList.get(i)) {
			listOfLedger_final.add(groupSummaryDTO);
		    }
		}
		mapOfLedgerToReturn1.put(ledger_underList.get(i).toString(), listOfLedger_final);
	    }

	    for (Map.Entry<String, String> e1 : mapOfGroupsToReceive.entrySet()) {

		for (Map.Entry<String, List<GroupSummaryDTO>> e2 : mapOfLedgerToReturn1.entrySet()) {

		    if (Integer.parseInt(e1.getKey()) == Integer.parseInt(e2.getKey())) {

			mapOfLedgerToReturn.put(e1.getValue(), e2.getValue());

		    }
		}

	    }
	    conn.commit();
	    conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return mapOfLedgerToReturn;
    }

    public static Map<String, List<GroupSummaryDTO>> getLedgerClosingBalance(String fromDateStr, String toDateStr) throws SQLException, Exception {

	double ledgerDebit = 0D;
	double ledgerCredit = 0D;

	Map<String, List<GroupSummaryDTO>> mapToReceive = getLedgerDetails();
	Connection conn = null;
	Map<String, List<GroupSummaryDTO>> groupLedgerNames = new HashMap<String, List<GroupSummaryDTO>>();
	try {

	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    for (Map.Entry<String, List<GroupSummaryDTO>> e : mapToReceive.entrySet()) {

		List<GroupSummaryDTO> listOfLedger = new ArrayList<GroupSummaryDTO>();
		for (GroupSummaryDTO e1 : e.getValue()) {

		    String queryOne = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + fromDateStr + "' and trans_date<='" + toDateStr + "' ) and trans_ledgerid =" + e1.getLedger_id() + "";
		    PreparedStatement ps3 = conn.prepareStatement(queryOne);
		    ResultSet rs3 = ps3.executeQuery();

		    while (rs3.next()) {
			ledgerDebit = rs3.getDouble("totalDeb");
		    }

		    String queryTwo = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + fromDateStr + "' and trans_date<='" + toDateStr + "' ) and trans_ledgerid =" + e1.getLedger_id() + "";
		    PreparedStatement ps4 = conn.prepareStatement(queryTwo);
		    ResultSet rs4 = ps4.executeQuery();

		    while (rs4.next()) {
			ledgerCredit = rs4.getDouble("totalCred");
		    }

		    //----------------calculate closing balance------------------
		    double closing_Balance = 00f;
		    if (e1.getTrans_Type() == 2) {
			closing_Balance = e1.getOpeningBalance() + ledgerCredit - ledgerDebit;
			e1.setClosingBalance(Math.abs(closing_Balance));
			if (closing_Balance < 0) {

			    e1.setClosing_trans_Type(1);

			} else {

			    e1.setClosing_trans_Type(2);
			}
		    } else {
			closing_Balance = e1.getOpeningBalance() + ledgerDebit - ledgerCredit;
			e1.setClosingBalance(Math.abs(closing_Balance));
			if (closing_Balance > 0) {

			    e1.setClosing_trans_Type(1);

			} else {

			    e1.setClosing_trans_Type(2);

			}
		    }

		    listOfLedger.add(e1);
		}
		if (listOfLedger.size() > 0) {
		    groupLedgerNames.put(e.getKey(), listOfLedger);
		}
	    }
	    conn.commit();
	    conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}

	return groupLedgerNames;
    }
}

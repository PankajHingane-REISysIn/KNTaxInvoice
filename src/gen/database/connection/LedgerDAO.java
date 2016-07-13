/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import gen.ImpExp.TagsHelper1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

//    public static Map<String, String> getLedgerNameToIDMap() throws SQLException, Exception {
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
//    public static Map<String, String> getLedgerFromGroupName(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
//	Map<String, String> mapToReturn = new HashMap<String, String>();
//	String mainQuery = "";
//	String condition = "";
//	Connection connection = null;
//	try {
//	    mainQuery = "SELECT     tblgroup.group_name AS tblgroup_group_name,  tblledger.ledger_name AS tblledger_ledger_name,     tblledger.ledger_under AS tblledger_ledger_under,     tblledger.ledger_id AS tblledger_ledger_id FROM      tblgroup tblgroup INNER JOIN tblledger tblledger ON tblgroup.group_id = tblledger.ledger_under";
//	    if (groupNames != null && groupNames.size() > 0) {
//		String groupList = "";
//		Set<String> groupSet = new HashSet<String>();
//		for (String stkGroup : groupNames) {
//		    groupSet.add(stkGroup);
//		}
//
//		groupSet = GroupDAO.getUnderGroups(groupSet);
//		groupNames = new ArrayList<String>();
//		for (String stkGroup : groupSet) {
//		    groupNames.add(stkGroup);
//		}
//
//		for (String stkGroup : groupNames) {
//		    groupList += "'" + stkGroup + "' ,";
//		}
//		if (groupList.length() > 0) {
//		    groupList = groupList.substring(0, groupList.length() - 2);
//		}
//
//		if (isIn == null || isIn) {
//		    condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
//		} else {
//		    condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
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
//		mapToReturn.put(rsSet.getString("tblledger_ledger_name"), rsSet.getLong("tblledger_ledger_id") + "");
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
}

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
public class TrialBalanceDAO {

    public static Map<String, List<String>> getGroupsHierarchy() throws SQLException, Exception {

	Map<String, List<String>> mapToReturn = new HashMap<String, List<String>>();
	Map<String, GroupDTO> groupNameMap = new HashMap<String, GroupDTO>();
	Map<String, List<GroupDTO>> groupUnderMap = new HashMap<String, List<GroupDTO>>();
	Map<String, String> groupUnderIDsMap = new HashMap<String, String>();
	Connection conn = null;
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String queryOne = "select group_id,group_name,group_under from tblgroup";
	    PreparedStatement ps1 = conn.prepareStatement(queryOne);
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

	    List<GroupDTO> primaryGroupDTOList = groupUnderMap.get(null);
	    for (GroupDTO groupDTO : primaryGroupDTOList) {
		if (mapToReturn.get(groupDTO.getGroup_Name()) == null) {
		    mapToReturn.put(groupDTO.getGroup_Name(), new ArrayList<String>());
		}

		mapToReturn.get(groupDTO.getGroup_Name()).add(groupDTO.getGroup_Name());


		//ArrayList<Long> groupUnderValues = new ArrayList<Long>();
		int loopCount = 0;
		String id = "";
		for (GroupDTO groupDTOEntity : groupUnderMap.get(groupDTO.getGroup_id())) {
		    if (mapToReturn.get(groupDTOEntity.getGroup_Name()) == null) {
			mapToReturn.put(groupDTOEntity.getGroup_Name(), new ArrayList<String>());
			//cash_ids.add((groupDTOEntity.getGroup_id()));
			id = groupDTOEntity.getGroup_id();
		    }
		    mapToReturn.get(groupDTOEntity.getGroup_Name()).add(groupDTOEntity.getGroup_Name());
		    //System.out.println("groupDTOEntity.getGroup_Name()--->>>" + groupDTOEntity.getGroup_Name());

		    ArrayList<String> cash_ids = new ArrayList<String>();

		    cash_ids.add(id);
		    // System.out.println("CashID----" + id);

		    int index = 0;
		    int flg = 0;
		    do {

			for (Map.Entry<String, String> e2 : groupUnderIDsMap.entrySet()) {

			    if (cash_ids.get(index).equalsIgnoreCase(e2.getValue())) {

				//System.out.println("UnderMap Ids------------------------------------->>>>>>>>" + e2.getValue());
				// System.out.println("HierarchyOLD--->>>" + e2.getKey());
				cash_ids.add(e2.getKey());
				// newGroupIDUnderMap.put(e2.getKey(), e2.getValue());

			    }
			}
			if (index == cash_ids.size() - 1) {
			    flg = 1;
			}
			index++;
		    } while (flg == 0);

		    loopCount++;
		    mapToReturn.put(groupDTOEntity.getGroup_Name(), cash_ids);
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
	return mapToReturn;
    }

    public static List<TrialBalanceDTO> getTrialBalance(String fromDateStr, String toDateStr) throws SQLException, Exception {
	
	List<TrialBalanceDTO> trialBalanbceDTOList = new ArrayList<TrialBalanceDTO>();
	Map<String, List<String>> mapToGetData = TrialBalanceDAO.getGroupsHierarchy();

	for (Map.Entry<String, List<String>> e : mapToGetData.entrySet()) {

	    if (!e.getKey().equalsIgnoreCase("Primary")) {
		System.out.println("GroupsNames======>>>>" + e.getKey());
		System.out.println("GroupUNder==>>>" + e.getValue());
	    }
	}

	for (String str : mapToGetData.keySet()) {
	    if (!str.equalsIgnoreCase("Primary")) {
		TrialBalanceDTO trialBalanceDTO = getTransaction(mapToGetData.get(str), fromDateStr, toDateStr);
		trialBalanceDTO.setGroup_name(str);
		trialBalanbceDTOList.add(trialBalanceDTO);
	    }
	}
	return trialBalanbceDTOList;
    }

    public static TrialBalanceDTO getTransaction(List<String> GroupNames, String fromDateStr, String toDateStr) throws Exception {

	TrialBalanceDTO trialBalanceDTO = new TrialBalanceDTO();
	Connection conn = null;
	try{
	double debit = 00f;
	double credit = 00f;
	int i = 0;
	int flag = 0;
	conn = DatabaseConnection1.GetConnection();
	conn.setAutoCommit(false);
	String query2 = "select sum(trans_amt) as totalDeb, trans_type from tbltransactionledger ";

	String whereCondition = "";
	String whereCondition1 = "where trans_id in";
	String subQuery1 = "(select trans_id from tbltransactionmain ";
	String dateQuery = "where trans_date>='" + fromDateStr + "' and trans_date<='" + toDateStr + "' and tbltransactionmain.trans_typeIndex Not in(" + gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX + " , " + gen.dto.Constants.GRNOTE_TYPE_INDEX + "))";
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
	query2 += whereCondition1 + subQuery1 + dateQuery + whereCondition2 + subQuery2 + whereCondition;
	System.out.println("QUERY2---->>>" + query2);
	PreparedStatement ps2 = conn.prepareStatement(query2);
	ResultSet rs2 = ps2.executeQuery();
	while (rs2.next()) {
	    Integer debCred = rs2.getInt("trans_type");

	    if (debCred == Constants.CREDIT) {
		trialBalanceDTO.setCreditAmount(rs2.getDouble("totalDeb"));
		credit += rs2.getDouble("totalDeb");
	    } else {
		trialBalanceDTO.setDebitAmount(rs2.getDouble("totalDeb"));
		debit += rs2.getDouble("totalDeb");
	    }
	}
	if (i == GroupNames.size() - 1) {
	    flag = 1;
	}
	i++;

	DecimalFormat decformat = new DecimalFormat("#.##");
	System.out.println("Total Debit--->>" + decformat.format(debit));
	System.out.println("Total Credit--->>" + decformat.format(credit));

	conn.commit();
	conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return trialBalanceDTO;
    }
}

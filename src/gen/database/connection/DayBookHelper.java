/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import ledgerreport.DayBookDTO;

/**
 *
 * @author pc5
 */
public class DayBookHelper {

    public static List<DayBookDTO> getDaybookLedgerList(Date fromDate, Date toDate, Integer transIndex, Integer transTypeIndex, Set<String> groupList, Boolean isUnder) throws SQLException {

        List<DayBookDTO> listToReturn = new ArrayList<DayBookDTO>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        String mainQuery = "";
        String condition = "";
        String orderBy = "";
        List<String> conditions = new ArrayList<String>();
        if (fromDate != null) {
            String condStr = "trans_date >= '" + dateFormatter.format(fromDate) + "'";
            conditions.add(condStr);
        }
        if (toDate != null) {
            String condStr = "trans_date <= '" + dateFormatter.format(toDate) + "'";
            conditions.add(condStr);
        }
        if (transIndex != null) {
            String condStr = "trans_typeIndex = " + transIndex + "";
            conditions.add(condStr);
        }
        if (transTypeIndex != null) {
            String condStr = "trans_type = " + transTypeIndex + "";
            conditions.add(condStr);
        }
        if (groupList != null && groupList.size() > 0) {
            String groupnames = "";
            for (String groupName : groupList) {
                groupnames = groupnames + "'" + groupName + "',";
            }
            groupnames = groupnames.substring(0, groupnames.length() - 2);
            String condStr = "group_name IN (" + groupnames + ")";
            conditions.add(condStr);
        }
        if (conditions.size() > 0) {
            condition = " where ";
            for (String condStr : conditions) {
                condition = condition + condStr + " and ";
            }
        }

        if (!condition.equalsIgnoreCase("")) {
            condition = condition.substring(0, condition.length() - 5);
        }
        mainQuery = "SELECT     tbltransactionmain.`trans_receiptNo` AS tbltransactionmain_trans_receiptNo,     tbltransactionmain.`trans_id` AS tbltransactionmain_trans_id,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date,     tbltransactionmain.`trans_typeIndex` AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.`trans_grandtotal` AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tblgroup.`group_name` AS tblgroup_group_name,     tblledger.`ledger_name` AS tblledger_ledger_name FROM     `tbltransactionmain` tbltransactionmain INNER JOIN `tbltransactionledger` tbltransactionledger ON tbltransactionmain.`trans_id` = tbltransactionledger.`trans_id`     INNER JOIN `tblledger` tblledger ON tbltransactionledger.`trans_ledgerId` = tblledger.`ledger_id`     INNER JOIN `tblgroup` tblgroup ON tblledger.`ledger_under` = tblgroup.`group_id` ";
        orderBy = " order by trans_receiptNo ASC";
        String query = mainQuery + condition + orderBy;
        System.out.println("----------------->> query: " + query);
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();

        while (rsSet.next()) {
            DayBookDTO dayBookDTO = new DayBookDTO();
            dayBookDTO.setReceiptNo(rsSet.getLong("tbltransactionmain_trans_receiptNo"));
            dayBookDTO.setTransID(rsSet.getLong("tbltransactionmain_trans_id"));
            dayBookDTO.setLedgerName(rsSet.getString("tblledger_ledger_name"));
            dayBookDTO.setAmount(rsSet.getDouble("tbltransactionmain_trans_grandtotal"));
            listToReturn.add(dayBookDTO);
        }
        rsSet.close();
        psmt.close();
        connection.close();
        return listToReturn;
    }
}

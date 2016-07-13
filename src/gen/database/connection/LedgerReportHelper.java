/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.LedgerReportDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasturi NovaSoft
 */
public class LedgerReportHelper {

    public static Map<String, List<LedgerReportDTO>> getLedger(Date fromDate, Date toDate) throws SQLException {

        Map<String, List<LedgerReportDTO>> mapToReturn = new TreeMap<String, List<LedgerReportDTO>>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        String query = "SELECT     tblledger.`ledger_name` AS tblledger_ledger_name,     tblgroup.`group_name` AS tblgroup_group_name,     tbltransactionledger.`trans_amt` AS tbltransactionledger_trans_amt,     sum(tbltransactionmain.`trans_grandtotal`)AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM     `tblgroup` tblgroup INNER JOIN `tblledger` tblledger ON tblgroup.`group_id` = tblledger.`ledger_under`     INNER JOIN `tbltransactionledger` tbltransactionledger ON tblledger.`ledger_id` = tbltransactionledger.`trans_ledgerId`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tbltransactionledger.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_date >= '" + dateFormatter.format(fromDate) + "' and trans_date <= '" + dateFormatter.format(toDate) + "' GROUP BY      tblgroup_group_name,      tblledger_ledger_name,      tbltransactionledger_trans_type";
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();

        while (rsSet.next()) {
            String groupName;
            Integer transactionIndex;
            LedgerReportDTO ledgerReportDTO = new LedgerReportDTO();

            groupName = rsSet.getString("tblgroup_group_name");
            ledgerReportDTO.setLedgerName(rsSet.getString("tblledger_ledger_name"));
            transactionIndex = rsSet.getInt("tbltransactionledger_trans_type");
            if (transactionIndex == Constants.CREDIT) {
                ledgerReportDTO.setTotalCredit(rsSet.getDouble("tbltransactionmain_trans_grandtotal"));
            } else {
                ledgerReportDTO.setTotalDebit(rsSet.getDouble("tbltransactionmain_trans_grandtotal"));
            }
            if (mapToReturn.get(groupName) == null) {
                mapToReturn.put(groupName, new ArrayList<LedgerReportDTO>());
            }
            mapToReturn.get(groupName).add(ledgerReportDTO);
        }

        mapToReturn = removeDublicates(mapToReturn);


        Map<String, Double> openingBalMap = getAllLedgerOpeningBal(null, fromDate);
        for (String groupName : mapToReturn.keySet()) {
            for (LedgerReportDTO ledgerReportDTOEntity : mapToReturn.get(groupName)) {
                if (openingBalMap.get(ledgerReportDTOEntity.getLedgerName()) != null) {
                    ledgerReportDTOEntity.setOpeningBal(openingBalMap.get(ledgerReportDTOEntity.getLedgerName()));
                }
                ledgerReportDTOEntity.setClosingBal(ledgerReportDTOEntity.getOpeningBal() + ledgerReportDTOEntity.getTotalCredit() - ledgerReportDTOEntity.getTotalDebit());
            }
        }
        return mapToReturn;
    }

    private static Map<String, List<LedgerReportDTO>> removeDublicates(Map<String, List<LedgerReportDTO>> paramMap) {
        Map<String, List<LedgerReportDTO>> mapTOReturn = new TreeMap<String, List<LedgerReportDTO>>();
        if (paramMap != null) {

            for (String groupName : paramMap.keySet()) {
                List<LedgerReportDTO> ledgerReportDTOList = new ArrayList<LedgerReportDTO>();
                for (LedgerReportDTO ledgerReportDTO : paramMap.get(groupName)) {
                    Boolean isExists = false;
                    for (LedgerReportDTO ledgerEntityDTO : ledgerReportDTOList) {
                        if (ledgerEntityDTO.getLedgerName().trim().equalsIgnoreCase(ledgerReportDTO.getLedgerName().trim())) {
                            //copy over
                            if (ledgerReportDTO.getTotalCredit() != null && ledgerReportDTO.getTotalCredit() > 0D) {
                                ledgerEntityDTO.setTotalCredit(ledgerReportDTO.getTotalCredit());
                            }

                            if (ledgerReportDTO.getTotalDebit() != null && ledgerReportDTO.getTotalDebit() > 0D) {
                                ledgerEntityDTO.setTotalDebit(ledgerReportDTO.getTotalDebit());
                            }
                            isExists = true;
                        }

                    }
                    if (isExists == false) {
                        ledgerReportDTOList.add(ledgerReportDTO);
                    }
                }
                mapTOReturn.put(groupName, ledgerReportDTOList);
            }
        }
        return mapTOReturn;
    }

    public static Map<String, Double> getAllLedgerOpeningBal(Date fromDate, Date toDate) throws SQLException {

        Map<String, Double> mapToReturn = new TreeMap<String, Double>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        String query;
        if (fromDate != null) {
            query = "SELECT     tblledger.`ledger_name` AS tblledger_ledger_name,tblledger.`ledger_openingBalance` AS tblledger_ledger_openingBalance,     tblgroup.`group_name` AS tblgroup_group_name,     sum(tbltransactionledger.`trans_amt`) AS tbltransactionledger_trans_amt,     tbltransactionmain.`trans_grandtotal`AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM     `tblgroup` tblgroup INNER JOIN `tblledger` tblledger ON tblgroup.`group_id` = tblledger.`ledger_under`     INNER JOIN `tbltransactionledger` tbltransactionledger ON tblledger.`ledger_id` = tbltransactionledger.`trans_ledgerId`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tbltransactionledger.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_date > '" + dateFormatter.format(fromDate) + "' and trans_date < '" + dateFormatter.format(toDate) + "' GROUP BY      tblgroup_group_name,      tblledger_ledger_name,      tbltransactionledger_trans_type";
        } else {
            query = "SELECT     tblledger.`ledger_name` AS tblledger_ledger_name,tblledger.`ledger_openingBalance` AS tblledger_ledger_openingBalance,     tblgroup.`group_name` AS tblgroup_group_name,     sum(tbltransactionledger.`trans_amt`) AS tbltransactionledger_trans_amt,     tbltransactionmain.`trans_grandtotal` AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM     `tblgroup` tblgroup INNER JOIN `tblledger` tblledger ON tblgroup.`group_id` = tblledger.`ledger_under`     INNER JOIN `tbltransactionledger` tbltransactionledger ON tblledger.`ledger_id` = tbltransactionledger.`trans_ledgerId`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tbltransactionledger.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_date < '" + dateFormatter.format(toDate) + "' GROUP BY      tblgroup_group_name,      tblledger_ledger_name,      tbltransactionledger_trans_type";
        }
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();

        while (rsSet.next()) {
            String ledgerName;
            Integer transactionIndex;
            Double ledgerOpenBal;
            Double totalCredit = null;
            Double totalDebit = null;


            ledgerName = rsSet.getString("tblledger_ledger_name");
            transactionIndex = rsSet.getInt("tbltransactionledger_trans_type");
            ledgerOpenBal = rsSet.getDouble("tblledger_ledger_openingBalance");
            if (transactionIndex == Constants.CREDIT) {
                totalCredit = rsSet.getDouble("tbltransactionmain_trans_grandtotal");
            } else {
                totalDebit = rsSet.getDouble("tbltransactionmain_trans_grandtotal");
            }
            if (ledgerOpenBal == null) {
                ledgerOpenBal = 0D;
            }
            if (totalCredit == null) {
                totalCredit = 0D;
            }
            if (totalDebit == null) {
                totalDebit = 0D;
            }
            ledgerOpenBal = ledgerOpenBal + totalCredit - totalDebit;
            mapToReturn.put(ledgerName, ledgerOpenBal);
        }
        rsSet.close();
        psmt.close();
        connection.close();

        return mapToReturn;
    }

    public static Map<String, Double> getLedgerOpeningBal(Date fromDate, Date toDate, Set<String> ledgerNameSet) throws SQLException {

        Map<String, Double> mapToReturn = new TreeMap<String, Double>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        String mainQuery = "";
        String condtion = "";
        if (fromDate != null) {
            mainQuery = "SELECT     tblledger.`ledger_name` AS tblledger_ledger_name,tblledger.`ledger_openingBalance` AS tblledger_ledger_openingBalance,     tblgroup.`group_name` AS tblgroup_group_name,     sum(tbltransactionledger.`trans_amt`) AS tbltransactionledger_trans_amt,     tbltransactionmain.`trans_grandtotal` AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM     `tblgroup` tblgroup INNER JOIN `tblledger` tblledger ON tblgroup.`group_id` = tblledger.`ledger_under`     INNER JOIN `tbltransactionledger` tbltransactionledger ON tblledger.`ledger_id` = tbltransactionledger.`trans_ledgerId`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tbltransactionledger.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_date > '" + dateFormatter.format(fromDate) + "' and trans_date < '" + dateFormatter.format(toDate) + "' ";
        } else {
            mainQuery = "SELECT     tblledger.`ledger_name` AS tblledger_ledger_name,tblledger.`ledger_openingBalance` AS tblledger_ledger_openingBalance,     tblgroup.`group_name` AS tblgroup_group_name,     sum(tbltransactionledger.`trans_amt`) AS tbltransactionledger_trans_amt,     tbltransactionmain.`trans_grandtotal` AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.`trans_type` AS tbltransactionledger_trans_type,     tbltransactionmain.`trans_date` AS tbltransactionmain_trans_date FROM     `tblgroup` tblgroup INNER JOIN `tblledger` tblledger ON tblgroup.`group_id` = tblledger.`ledger_under`     INNER JOIN `tbltransactionledger` tbltransactionledger ON tblledger.`ledger_id` = tbltransactionledger.`trans_ledgerId`     INNER JOIN `tbltransactionmain` tbltransactionmain ON tbltransactionledger.`trans_id` = tbltransactionmain.`trans_id` WHERE      trans_date < '" + dateFormatter.format(toDate) + "' ";
        }

        if (ledgerNameSet != null && ledgerNameSet.size() > 0) {
            condtion = " and ledger_name in(";
            for (String ledgerName : ledgerNameSet) {
                condtion = condtion + "'" + ledgerName + "' ,";
            }
            condtion = condtion.substring(0, condtion.length() - 2);
            condtion = condtion + ")";
        }
        String orderBy = " GROUP BY      tblgroup_group_name,      tblledger_ledger_name,      tbltransactionledger_trans_type ";
        String query = mainQuery + condtion + orderBy;
        System.out.println("getLedgerOpeningBal -->>query-->>" + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        Double ledgerOpenBal = null;
        Double totalCredit = null;
        Double totalDebit = null;
        String ledgerName = "";

        while (rsSet.next()) {

            Integer transactionIndex;



            ledgerName = rsSet.getString("tblledger_ledger_name");
            transactionIndex = rsSet.getInt("tbltransactionledger_trans_type");
            ledgerOpenBal = rsSet.getDouble("tblledger_ledger_openingBalance");
            if (transactionIndex == Constants.CREDIT) {
                totalCredit = rsSet.getDouble("tbltransactionmain_trans_grandtotal");
            } else {
                totalDebit = rsSet.getDouble("tbltransactionmain_trans_grandtotal");
            }


        }
        rsSet.close();
        psmt.close();

        query = "select ledger_openingBalance from tblledger where ledger_name in ('Cash')";
        psmt = connection.prepareStatement(query);
        rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            ledgerOpenBal = rsSet.getDouble("ledger_openingBalance");
        }

        if (ledgerOpenBal == null) {
            ledgerOpenBal = 0D;
        }
        if (totalCredit == null) {
            totalCredit = 0D;
        }
        if (totalDebit == null) {
            totalDebit = 0D;
        }
        ledgerOpenBal = ledgerOpenBal + totalCredit - totalDebit;
        System.out.println("LedgerReportHel[er-------------->>ledgerOpenBal : " + ledgerOpenBal);
        mapToReturn.put("cash", ledgerOpenBal);

        rsSet.close();
        psmt.close();
        connection.close();

        return mapToReturn;
    }
}

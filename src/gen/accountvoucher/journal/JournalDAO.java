/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.journal;

import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pc5
 */
public class JournalDAO {

    public static void insertReceiptVoucher(List<JournalDTO> journalFormDTOList) throws SQLException, ParseException {

        Connection conn = DatabaseConnection1.GetConnection();
        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
        for (JournalDTO journalDTO : journalFormDTOList) {
            ps.setInt(1, (journalDTO.getReceiptNo()));
            java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(journalDTO.getDate().trim());
            java.sql.Date sqlStartDate = new Date(date.getTime());
            ps.setDate(2, sqlStartDate);
//            ps.setString(2, journalDTO.getDate().trim());
            ps.setInt(3, Constants.JOURNAL_TYPE_INDEX);
            ps.setDouble(4, Double.parseDouble(journalDTO.getFinalAmount().toString()));
            ps.addBatch();
        }
        ps.executeBatch();
        ResultSet rs = ps.getGeneratedKeys();
        List<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
        ps.close();

        int i = 0;
        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
        PreparedStatement ps2 = conn.prepareStatement(query2);
        for (JournalDTO journalDTO : journalFormDTOList) {
            Integer fk = idList.get(i);
            ps2.setInt(1, fk);
            ps2.setDouble(2, journalDTO.getFinalAmount());
            ps2.addBatch();
            i++;
        }
        ps2.executeBatch();

        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
        PreparedStatement ps6 = conn.prepareStatement(query33);
        i = 0;
        for (JournalDTO journalDTO : journalFormDTOList) {

            Integer fk = idList.get(i);

            for (LedgerTransactionDTO ledgerTrsDTO : journalDTO.getLedgerTransactionDTOList()) {
                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(journalDTO.getAccountName().toString()));
                ps6.setInt(3, Constants.CREDIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, ledgerTrsDTO.getAmount());
                ps6.setString(6, ledgerTrsDTO.getNote());
                ps6.setString(7, ledgerTrsDTO.getCheckNo());
                ps6.addBatch();

                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                ps6.setInt(3, Constants.DEBIT);
                ps6.setInt(4, i);
                ps6.setDouble(5, ledgerTrsDTO.getAmount());
                ps6.setString(6, ledgerTrsDTO.getNote());
                ps6.setString(7, ledgerTrsDTO.getCheckNo());
                ps6.addBatch();
                i++;
            }
            i++;
        }
        ps6.executeBatch();
        ps6.close();
        conn.close();
    }

    public static List<JournalDTO> getReceipts(Set<String> journalIDSet) throws SQLException {
        if (journalIDSet == null || journalIDSet.isEmpty()) {
            return null;
        }
        List<JournalDTO> journalDTOList = new ArrayList<JournalDTO>();
        Connection con = DatabaseConnection1.GetConnection();
        for (String receiptID : journalIDSet) {
            String str = "SELECT   tblledger.ledger_name AS tblledger_ledger_name,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,   tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,  tbltransactionmain.trans_id AS tbltransactionmain_trans_id,    tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_narration AS tbltransactionledger_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id      INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_typeIndex = " + Constants.JOURNAL_TYPE_INDEX + " and trans_receiptNo = " + receiptID;
            PreparedStatement psmt = con.prepareStatement(str);
            ResultSet rs = psmt.executeQuery();
            JournalDTO journalDTO = new JournalDTO();
            List<LedgerTransactionDTO> ledgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            Boolean isAdded = false;
            while (rs.next()) {
                if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT && isAdded == false) {
                    journalDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                    journalDTO.setAccountName(rs.getString("tblledger_ledger_name"));
                    journalDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                    journalDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                    journalDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                    isAdded = true;
                }
                if (rs.getInt("tbltransactionledger_trans_type") == Constants.DEBIT) {
                    LedgerTransactionDTO ledgerTransactionDTO = new LedgerTransactionDTO();

                    ledgerTransactionDTO.setName(rs.getString("tblledger_ledger_name"));
                    ledgerTransactionDTO.setCheckNo(rs.getString("tbltransactionledger_trans_checkNo"));
                    ledgerTransactionDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                    ledgerTransactionDTO.setAmount(rs.getDouble("tbltransactionledger_trans_amt"));
                    ledgerTransactionDTOList.add(ledgerTransactionDTO);
                }
            }
            journalDTO = ImpExpUtil.valiDateAndRepairJournalDTO(journalDTO);
            journalDTO.setLedgerTransactionDTOList(ledgerTransactionDTOList);
            journalDTOList.add(journalDTO);
        }
        con.close();

        return journalDTOList;
    }

    public static List<JournalDTO> getTransactionList() throws SQLException {
        List<JournalDTO> journalDTOTransactionList = new ArrayList<JournalDTO>();
        Connection conn = DatabaseConnection1.GetConnection();

        String str = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.DEBIT + " and trans_typeIndex=" + Constants.JOURNAL_TYPE_INDEX;
        PreparedStatement psmt = conn.prepareStatement(str);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            JournalDTO journalDTO = new JournalDTO();
            journalDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
            journalDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
            journalDTO.setAccountName(rs.getString("tblledger_ledger_name"));

            journalDTOTransactionList.add(journalDTO);
        }
        conn.close();
        return journalDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.JOURNAL_TYPE_INDEX);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void deleteReords(long transID) {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st2 = conn.createStatement();
            String query = "";

            query = "delete from tbltransactionledger where trans_id=" + transID + "";
            st2.executeUpdate(query);

            query = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(query);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateReceiptVoucher(List<JournalDTO> journalFormDTOList) throws SQLException, ParseException {

        for (JournalDTO journalDTO : journalFormDTOList) {
            deleteReords(Long.parseLong(journalDTO.getTrans_ID()));
        }

        Connection conn = DatabaseConnection1.GetConnection();
        String q = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_grandtotal = ? where trans_id = ?";
        PreparedStatement ps = conn.prepareStatement(q);
        for (JournalDTO journalDTO : journalFormDTOList) {
            ps.setInt(1, (journalDTO.getReceiptNo()));
            java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(journalDTO.getDate().trim());
            java.sql.Date sqlStartDate = new Date(date.getTime());
            ps.setDate(2, sqlStartDate);
//            ps.setString(2, journalDTO.getDate().trim());
            ps.setInt(3, Constants.JOURNAL_TYPE_INDEX);
            ps.setDouble(4, Double.parseDouble(journalDTO.getFinalAmount().toString()));
            ps.setDouble(5, Long.parseLong(journalDTO.getTrans_ID().toString()));
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();

        int i = 0;
        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
        PreparedStatement ps2 = conn.prepareStatement(query2);
        for (JournalDTO journalDTO : journalFormDTOList) {
            ps2.setLong(1, Long.parseLong(journalDTO.getTrans_ID()));
            ps2.setString(2, "" + journalDTO.getFinalAmount());
            ps2.addBatch();
            i++;
        }
        ps2.executeBatch();

        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
        PreparedStatement ps6 = conn.prepareStatement(query33);
        i = 0;
        for (JournalDTO journalDTO : journalFormDTOList) {
            i = 0;
            for (LedgerTransactionDTO ledgerTrsDTO : journalDTO.getLedgerTransactionDTOList()) {
                ps6.setInt(1, Integer.parseInt(journalDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(journalDTO.getAccountName()));
                ps6.setInt(3, Constants.CREDIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, ledgerTrsDTO.getAmount());
                ps6.setString(6, ledgerTrsDTO.getNote());
                ps6.setString(7, ledgerTrsDTO.getCheckNo());
                ps6.addBatch();

                ps6.setInt(1, Integer.parseInt(journalDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                ps6.setInt(3, Constants.DEBIT);
                ps6.setInt(4, i);
                ps6.setDouble(5, ledgerTrsDTO.getAmount());
                ps6.setString(6, ledgerTrsDTO.getNote());
                ps6.setString(7, ledgerTrsDTO.getCheckNo());
                ps6.addBatch();

                i++;
            }
            i++;
        }
        ps6.executeBatch();
        ps6.close();
        conn.close();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.receipt;

import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
import gen.dto.StockItemTransactionDTO;
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
public class ReceiptDAO {

    public static void insertReceiptVoucher(List<ReceiptDTO> receiptFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                ps.setInt(1, (receiptDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(receiptDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.RECEIPT_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(receiptDTO.getFinalAmount().toString()));
                ps.addBatch();
            }
            if (!receiptFormDTOList.isEmpty()) {
            ps.executeBatch();
            }
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            int i = 0;
            String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                Integer fk = idList.get(i);
                ps2.setInt(1, fk);
                ps2.setDouble(2, receiptDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            if (!receiptFormDTOList.isEmpty()) {
            ps2.executeBatch();
            }

            String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo,IsAvaibleToUser) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(query33);
            i = 0;
            int j = 0;
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                Integer fk = idList.get(j);

                for (LedgerTransactionDTO ledgerTrsDTO : receiptDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(receiptDTO.getAccountName().toString()));
                    ps6.setInt(3, Constants.CREDIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                    // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();

                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                    ps6.setInt(3, Constants.DEBIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                    // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();
                    i++;
                }
                j++;
            }
            if (!receiptFormDTOList.isEmpty()) {
            ps6.executeBatch();
            }
            ps6.close();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static List<ReceiptDTO> getReceipts(Set<String> receiptIDSet , String calling_Class) throws SQLException, Exception {
        if (receiptIDSet == null || receiptIDSet.isEmpty()) {
            return null;
        }
        List<ReceiptDTO> receiptDTOList = new ArrayList<ReceiptDTO>();
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT   tblledger.ledger_name AS tblledger_ledger_name,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,   tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,  tbltransactionmain.trans_id AS tbltransactionmain_trans_id,    tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_narration AS tbltransactionledger_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id      INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_typeIndex = " + Constants.RECEIPT_TYPE_INDEX + " and trans_receiptNo = " + receiptID;
                PreparedStatement psmt = con.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                ReceiptDTO receiptDTO = new ReceiptDTO();
                List<LedgerTransactionDTO> ledgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT && isAdded == false) {
                        receiptDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        receiptDTO.setAccountName(rs.getString("tblledger_ledger_name"));
                        receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        receiptDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                        receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
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
                receiptDTO = ImpExpUtil.valiDateAndRepairReceiptDTO(receiptDTO);
                receiptDTO.setLedgerTransactionDTOList(ledgerTransactionDTOList);
		
		if (!calling_Class.isEmpty()) {
		    gen.ImpExp.TagsHelper1.ledgerNameSet.add(receiptDTO.getAccountName());
		    for (LedgerTransactionDTO ledgertransactionDTO : receiptDTO.getLedgerTransactionDTOList()) {
//			gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
			gen.ImpExp.TagsHelper1.ledgerNameSet.add(ledgertransactionDTO.getName());
		    }
		}
		
                receiptDTOList.add(receiptDTO);
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return receiptDTOList;
    }

    public static List<ReceiptDTO> getTransactionList() throws SQLException, Exception {
        List<ReceiptDTO> receiptDTOTransactionList = new ArrayList<ReceiptDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.RECEIPT_TYPE_INDEX, Constants.DEBIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.DEBIT + "  and trans_typeIndex=" + Constants.RECEIPT_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ReceiptDTO receiptDTO = new ReceiptDTO();
                receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                receiptDTO.setAccountName(rs.getString("tblledger_ledger_name"));

                receiptDTOTransactionList.add(receiptDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return receiptDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.RECEIPT_TYPE_INDEX);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void deleteReords(long transID , Connection conn) throws Exception {
//        Connection conn = null;
        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
            Statement st2 = conn.createStatement();

            String queryOne = "delete from tbltransactionledger where trans_id=" + transID + "";
            st2.executeUpdate(queryOne);

            String queryTwo = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(queryTwo);

//            conn.commit();
//            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
//                conn.close();
            }
            throw ex;
        }
    }

    public static void updateReceiptVoucher(List<ReceiptDTO> receiptFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                deleteReords(Long.parseLong(receiptDTO.getTrans_ID()) , conn);
            }

            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_grandtotal = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                ps.setInt(1, (receiptDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(receiptDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.RECEIPT_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(receiptDTO.getFinalAmount().toString()));
                ps.setDouble(5, Long.parseLong(receiptDTO.getTrans_ID().toString()));
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryTwo);
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                ps2.setLong(1, Long.parseLong(receiptDTO.getTrans_ID()));
                ps2.setString(2, "" + receiptDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            ps2.executeBatch();

            String queryThree = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo,IsAvaibleToUser) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryThree);
            i = 0;
            for (ReceiptDTO receiptDTO : receiptFormDTOList) {
                i = 0;
                for (LedgerTransactionDTO ledgerTrsDTO : receiptDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, Integer.parseInt(receiptDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(receiptDTO.getAccountName()));
                    ps6.setInt(3, Constants.CREDIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                      // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();

                    ps6.setInt(1, Integer.parseInt(receiptDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                    ps6.setInt(3, Constants.DEBIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                      // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();

                    i++;
                }
                i++;
            }
            ps6.executeBatch();
            ps6.close();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.payment;

import gen.ImpExp.ImpExpUtil;
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
public class PaymentDAO {

    public static void insertPaymentVoucher(List<PaymentDTO> paymentFormDTOList) throws SQLException, ParseException, Exception {

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                ps.setInt(1, (paymentDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(paymentDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.PAYMENT_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(paymentDTO.getFinalAmount().toString()));
                ps.addBatch();
            }
            if (!paymentFormDTOList.isEmpty()) {
            ps.executeBatch();
            }
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryTwo);
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                Integer fk = idList.get(i);
                ps2.setInt(1, fk);
                ps2.setDouble(2, paymentDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            if (!paymentFormDTOList.isEmpty()) {
            ps2.executeBatch();
            }

            String queryThree = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo,IsAvaibleToUser) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryThree);
            i = 0;
            int j = 0;
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                Integer fk = 0;
                fk = idList.get(j);
                i = 1;
                for (LedgerTransactionDTO ledgerTrsDTO : paymentDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(paymentDTO.getAccountName()));
                    ps6.setInt(3, Constants.DEBIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                    // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();

                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                    ps6.setInt(3, Constants.CREDIT);
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
            if (!paymentFormDTOList.isEmpty()) {
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

    public static List<PaymentDTO> getPayment(Set<String> paymentIDSet , String calling_Class) throws SQLException, Exception {
        if (paymentIDSet == null || paymentIDSet.isEmpty()) {
            return null;
        }
        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            for (String paymentID : paymentIDSet) {
                String queryOne = "SELECT   tblledger.ledger_name AS tblledger_ledger_name, tbltransactionledger.trans_type AS tbltransactionledger_trans_type,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,  tbltransactionmain.trans_id AS tbltransactionmain_trans_id,    tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_narration AS tbltransactionledger_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id      INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_typeIndex = " + Constants.PAYMENT_TYPE_INDEX + " and trans_receiptNo = " + paymentID;
                PreparedStatement psmt = conn.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                PaymentDTO paymentDTO = new PaymentDTO();
                List<LedgerTransactionDTO> ledgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.DEBIT && isAdded == false) {
                        paymentDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        paymentDTO.setAccountName(rs.getString("tblledger_ledger_name"));
                        paymentDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        paymentDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                        paymentDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                        isAdded = true;
                    }
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT) {
                        LedgerTransactionDTO ledgerTransactionDTO = new LedgerTransactionDTO();
                        ledgerTransactionDTO.setName(rs.getString("tblledger_ledger_name"));
                        ledgerTransactionDTO.setCheckNo(rs.getString("tbltransactionledger_trans_checkNo"));
                        ledgerTransactionDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                        ledgerTransactionDTO.setAmount(rs.getDouble("tbltransactionledger_trans_amt"));
                        ledgerTransactionDTOList.add(ledgerTransactionDTO);
                    }
                }
                paymentDTO = ImpExpUtil.valiDateAndRepairPaymemntDTO(paymentDTO);
                paymentDTO.setLedgerTransactionDTOList(ledgerTransactionDTOList);
		
		if (!calling_Class.isEmpty()) {
		    gen.ImpExp.TagsHelper1.ledgerNameSet.add(paymentDTO.getAccountName());
		    for (LedgerTransactionDTO ledgertransactionDTO : paymentDTO.getLedgerTransactionDTOList()) {
//			gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
			gen.ImpExp.TagsHelper1.ledgerNameSet.add(ledgertransactionDTO.getName());
		    }
		}
		
                paymentDTOList.add(paymentDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return paymentDTOList;
    }

    public static List<PaymentDTO> getTransactionList() throws SQLException, Exception {
        List<PaymentDTO> paymentDTOTransactionList = new ArrayList<PaymentDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PAYMENT_TYPE_INDEX, Constants.CREDIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            String str = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PAYMENT_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            PreparedStatement psmt = conn.prepareStatement(str);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                paymentDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                paymentDTO.setAccountName(rs.getString("tblledger_ledger_name"));

                paymentDTOTransactionList.add(paymentDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return paymentDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.PAYMENT_TYPE_INDEX);
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

    public static void updatePaymentVoucher(List<PaymentDTO> paymentFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                deleteReords(Long.parseLong(paymentDTO.getTrans_ID()), conn);
            }

            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_grandtotal = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                ps.setInt(1, (paymentDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(paymentDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.PAYMENT_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(paymentDTO.getFinalAmount().toString()));
                ps.setDouble(5, Long.parseLong(paymentDTO.getTrans_ID().toString()));
                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryTwo);
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                ps2.setLong(1, Long.parseLong(paymentDTO.getTrans_ID()));
                ps2.setString(2, "" + paymentDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            ps2.executeBatch();

            String queryThree = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo,IsAvaibleToUser) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryThree);
            for (PaymentDTO paymentDTO : paymentFormDTOList) {
                i = 1;
                for (LedgerTransactionDTO ledgerTrsDTO : paymentDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, Integer.parseInt(paymentDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(paymentDTO.getAccountName()));
                    ps6.setInt(3, Constants.DEBIT);
                    ps6.setInt(4, i);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                     // this is for ledger report
                    ps6.setInt(8, 1); //IsAvailable to User
                    ps6.addBatch();

                    ps6.setInt(1, Integer.parseInt(paymentDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(ledgerTrsDTO.getName().toString()));
                    ps6.setInt(3, Constants.CREDIT);
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

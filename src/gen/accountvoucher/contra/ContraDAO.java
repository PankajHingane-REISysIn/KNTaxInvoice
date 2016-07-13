/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.contra;

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
public class ContraDAO {

    public static void insertContraVoucher(List<ContraDTO> contraFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (ContraDTO contraDTO : contraFormDTOList) {
                ps.setInt(1, (contraDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(contraDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.CONTRA_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(contraDTO.getFinalAmount().toString()));
                ps.addBatch();
            }
            if (!contraFormDTOList.isEmpty()) {
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
            for (ContraDTO contraDTO : contraFormDTOList) {
                Integer fk = idList.get(i);
                ps2.setInt(1, fk);
                ps2.setDouble(2, contraDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            if (!contraFormDTOList.isEmpty()) {
            ps2.executeBatch();
            }

            String queryThree = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryThree);
            i = 0;
            for (ContraDTO contraDTO : contraFormDTOList) {

                Integer fk = idList.get(i);

                for (LedgerTransactionDTO ledgerTrsDTO : contraDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(contraDTO.getAccountName().toString()));
                    ps6.setInt(3, Constants.CREDIT);
                    ps6.setInt(4, i);
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
            if (!contraFormDTOList.isEmpty()) {
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

    public static List<ContraDTO> getContra(Set<String> receiptIDSet , String calling_Class) throws SQLException, Exception {
        if (receiptIDSet == null || receiptIDSet.isEmpty()) {
            return null;
        }
        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT   tblledger.ledger_name AS tblledger_ledger_name,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,   tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,  tbltransactionmain.trans_id AS tbltransactionmain_trans_id,    tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_narration AS tbltransactionledger_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id      INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_typeIndex = " + Constants.CONTRA_TYPE_INDEX + " and trans_receiptNo = " + receiptID;
                PreparedStatement psmt = conn.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                ContraDTO contraDTO = new ContraDTO();
                List<LedgerTransactionDTO> ledgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT && isAdded == false) {
                        contraDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        contraDTO.setAccountName(rs.getString("tblledger_ledger_name"));
                        contraDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        contraDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                        contraDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
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
                contraDTO = ImpExpUtil.valiDateAndRepairContraDTO(contraDTO);
                contraDTO.setLedgerTransactionDTOList(ledgerTransactionDTOList);
		
		if (!calling_Class.isEmpty()) {
		    gen.ImpExp.TagsHelper1.ledgerNameSet.add(contraDTO.getAccountName());
		    for (LedgerTransactionDTO ledgertransactionDTO : contraDTO.getLedgerTransactionDTOList()) {
//			gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
			gen.ImpExp.TagsHelper1.ledgerNameSet.add(ledgertransactionDTO.getName());
		    }
		}
		
                contraDTOList.add(contraDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return contraDTOList;
    }

    public static List<ContraDTO> getTransactionList() throws SQLException, Exception {
        List<ContraDTO> contraDTOTransactionList = new ArrayList<ContraDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.CONTRA_TYPE_INDEX, Constants.DEBIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.DEBIT + "  and trans_typeIndex=" + Constants.CONTRA_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";

            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ContraDTO contraDTO = new ContraDTO();
                contraDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                contraDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                contraDTO.setAccountName(rs.getString("tblledger_ledger_name"));

                contraDTOTransactionList.add(contraDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return contraDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.CONTRA_TYPE_INDEX);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void deleteReords(long transID, Connection conn) throws Exception {
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

    public static void updateContraVoucher(List<ContraDTO> contraFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            
            for (ContraDTO contraDTO : contraFormDTOList) {
                deleteReords(Long.parseLong(contraDTO.getTrans_ID()) , conn);
            }


            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_grandtotal = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (ContraDTO contraDTO : contraFormDTOList) {
                ps.setInt(1, (contraDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(contraDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.CONTRA_TYPE_INDEX);
                ps.setDouble(4, Double.parseDouble(contraDTO.getFinalAmount().toString()));
                ps.setDouble(5, Long.parseLong(contraDTO.getTrans_ID().toString()));
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionOtherDetails(trans_id,trans_amt) values(?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryTwo);
            for (ContraDTO contraDTO : contraFormDTOList) {
                ps2.setLong(1, Long.parseLong(contraDTO.getTrans_ID()));
                ps2.setString(2, "" + contraDTO.getFinalAmount());
                ps2.addBatch();
                i++;
            }
            ps2.executeBatch();

            String queryThree = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryThree);
            i = 0;
            for (ContraDTO contraDTO : contraFormDTOList) {
                i = 0;
                for (LedgerTransactionDTO ledgerTrsDTO : contraDTO.getLedgerTransactionDTOList()) {
                    ps6.setInt(1, Integer.parseInt(contraDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(contraDTO.getAccountName()));
                    ps6.setInt(3, Constants.CREDIT);
                    ps6.setInt(4, 0);
                    ps6.setDouble(5, ledgerTrsDTO.getAmount());
                    ps6.setString(6, ledgerTrsDTO.getNote());
                    ps6.setString(7, ledgerTrsDTO.getCheckNo());
                    ps6.addBatch();

                    ps6.setInt(1, Integer.parseInt(contraDTO.getTrans_ID()));
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

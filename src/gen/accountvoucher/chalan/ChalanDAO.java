/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.chalan;

import gen.ImpExp.ImpExpUtil;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class ChalanDAO {

    public static void insertChalanVoucher(List<ChalanDTO> chalanFormDTOList) throws SQLException, ParseException, Exception {

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_transport,trans_lessBillAmt) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                ps.setInt(1, (chalanDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(chalanDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.CHALAN_TYPE_INDEX);
                ps.setString(4, chalanDTO.getNote().toString());
                ps.setDouble(5, 0d);
                ps.setDouble(6, chalanDTO.getTransport());
                ps.setDouble(7, chalanDTO.getLessBillAmount());

                ps.addBatch();

            }
            if (!chalanFormDTOList.isEmpty()) {
                ps.executeBatch();
            }
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment) values(?,?,?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                Integer fk = idList.get(i);
                ps1.setInt(1, fk);
                ps1.setDouble(2, chalanDTO.getVatRate());
                ps1.setDouble(3, chalanDTO.getVatAmount());

                ps1.addBatch();

                ps2.setInt(1, fk);
                ps2.setString(2, chalanDTO.getDispatchDocThrough());
                ps2.setString(3, chalanDTO.getDispatchDocNo());
                ps2.setDouble(4, 0d);
                ps2.setString(5, "" + 0);
                ps2.setString(6, "" + 0);

                ps2.addBatch();
                i++;
            }
            if (!chalanFormDTOList.isEmpty()) {
                ps1.executeBatch();
                ps2.executeBatch();
            }

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,IsAvaibleToUser) values(?,?,?,?,? , ?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                Integer fk = idList.get(i);
                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(chalanDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, 0d);
                ps6.setDouble(6, 1);
                ps6.addBatch();

                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(chalanDTO.getSaleLedger().toString()));
                ps6.setInt(3, Constants.DEBIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, 0d);
                ps6.setDouble(6, 1);
                ps6.addBatch();

                i++;

            }
            if (!chalanFormDTOList.isEmpty()) {
                ps6.executeBatch();
            }
            ps6.close();

            i = 0;
            String queryFive = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
            PreparedStatement ps3 = conn.prepareStatement(queryFive, Statement.RETURN_GENERATED_KEYS);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                Integer fk = idList.get(i);
                ps3.setInt(1, fk);
                ps3.setString(2, Constants.CHALAN_TYPE_INDEX.toString());
                ps3.addBatch();
                i++;
            }
            if (!chalanFormDTOList.isEmpty()) {
                ps3.executeBatch();
            }


            ResultSet rs1 = ps3.getGeneratedKeys();
            List<Integer> idList1 = new ArrayList<Integer>();
            while (rs1.next()) {
                idList1.add(rs1.getInt(1));
            }
            ps3.close();
            i = 0;
            String querySix = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySix);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                Integer fk = idList1.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, 0d);
                    ps4.setDouble(5, 0d);
                    ps4.setDouble(6, 0d);
                    ps4.setInt(7, k);
                    ps4.setDouble(8, 0.0);
                    ps4.setDouble(9, 0d);
                    ps4.setDouble(10, 0d);
                    ps4.setDouble(11, 0d);
                    ps4.addBatch();

                    k++;
                }
                i++;

            }
            if (!chalanFormDTOList.isEmpty()) {
                ps4.executeBatch();
            }
            ps4.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public static List<ChalanDTO> getChalan(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        Connection con = null;
        List<ChalanDTO> chalanDTOList = new ArrayList<ChalanDTO>();
        try {
            if (receiptIDSet == null || receiptIDSet.isEmpty()) {
                return null;
            }

            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {

                //String queryOne = "SELECT     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,   tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,tbltransactionmain.trans_id AS tbltransactionmain_trans_ID,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tblstockitem.si_name AS tblstockitem_si_name,     tblledger.ledger_name AS tblledger_ledger_name,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tblinventorytransaction tblinventorytransaction ON tbltransactionmain.trans_id = tblinventorytransaction.trans_id     INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE trans_typeIndex = " + Constants.CHALAN_TYPE_INDEX + " and     trans_receiptNo =  " + receiptID;



                String queryOne = "SELECT      tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.trans_id AS tblinventorytransaction_trans_id,     tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type,     tblinventorytransaction.dat AS tblinventorytransaction_dat,     tblinventorytransactionitems.invtrans_id AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_finishType AS tblinventorytransactionitems_invtrans_finishType,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tblinventorytransactionitems.invtrans_making AS tblinventorytransactionitems_invtrans_making,     tblledger.ledger_id AS tblledger_ledger_id,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitem.si_id AS tblstockitem_si_id,     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionmain.trans_transport AS tbltransactionmain_trans_transport,     tbltransactionmain.trans_lessBillAmt AS tbltransactionmain_trans_lessBillAmt,     tbltransactionmain.trans_payment AS tbltransactionmain_trans_payment,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionmain.trans_narration AS tbltransactionledger_trans_narration,     tbltransactionledger.trans_index AS tbltransactionledger_trans_index,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,     tbltransactionledger.trans_id AS tbltransactionledger_trans_id,     tbltransactionvat.trans_id AS tbltransactionvat_trans_id,     tbltransactionvat.vat_rate AS tbltransactionvat_vat_rate,     tbltransactionvat.vat_amt AS tbltransactionvat_vat_amt,     tbltransactionvat.discount_rate AS tbltransactionvat_discount_rate,    tbltransactionvat.shipping AS tbltransactionvat_shipping,     tbltransactionvat.discount_amt AS tbltransactionvat_discount_amt,     tbltransactionotherdetails.trans_id AS tbltransactionotherdetails_trans_id,     tbltransactionotherdetails.trans_reference AS tbltransactionotherdetails_trans_reference,     tbltransactionotherdetails.trans_buyerOrderNo AS tbltransactionotherdetails_trans_buyerOrderNo,     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionotherdetails.trans_amt AS tbltransactionotherdetails_trans_amt,     tbltransactionotherdetails.trans_payment AS tbltransactionotherdetails_trans_payment,     tbltransactionotherdetails.trans_vehicleNo AS tbltransactionotherdetails_trans_vehicleNo,     tbltransactionotherdetails.trans_challanNo AS tbltransactionotherdetails_trans_challanNo,     tbltransactionotherdetails.trans_poNo AS tbltransactionotherdetails_trans_poNo,     tbltransactionotherdetails.trans_challanDate AS tbltransactionotherdetails_trans_challanDate,     tbltransactionotherdetails.trans_poDate AS tbltransactionotherdetails_trans_poDate,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo FROM      tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionvat tbltransactionvat ON tbltransactionmain.trans_id = tbltransactionvat.trans_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE trans_typeIndex = " + Constants.CHALAN_TYPE_INDEX + " and     trans_receiptNo = " + receiptID + "  order by  tblinventorytransactionitems.invtrans_thickness ASC";
                PreparedStatement psmt = con.prepareStatement(queryOne);
                System.out.println("RRRRRRRRRR       " + receiptID);
                System.out.println("RRRRRRRRRR       " + receiptID);
                ResultSet rs = psmt.executeQuery();
                ChalanDTO chalanDTO = new ChalanDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    System.out.println("Rs True---------------------");
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT && isAdded == false) {
                        System.out.println("TTTTTTTTT ");
                        chalanDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        chalanDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                        chalanDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        chalanDTO.setDispatchDocNo(rs.getString("tbltransactionotherdetails_trans_dispatchDocNo"));
                        chalanDTO.setDispatchDocThrough(rs.getString("tbltransactionotherdetails_trans_dispatchDocThrough"));
                        chalanDTO.setNote(rs.getString("tbltransactionmain_trans_narration")); //to do
                        chalanDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));

                        chalanDTO.setVatRate(rs.getDouble("tbltransactionvat_vat_rate"));
                        chalanDTO.setVatAmount(rs.getDouble("tbltransactionvat_vat_amt"));
                        chalanDTO.setTransport(rs.getDouble("tbltransactionmain_trans_transport"));
                        chalanDTO.setLessBillAmount(rs.getDouble("tbltransactionmain_trans_lessBillAmt"));
                        isAdded = true;
                    }
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.DEBIT) {
                        System.out.println("TTTTTTTTT DDDDDDDDDDDDDDDDDD");
                        chalanDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));
                        StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                        stockItemTransactionDTO.setAmount(rs.getDouble("tblinventorytransactionitems_invtrans_amount"));
                        stockItemTransactionDTO.setLength(rs.getDouble("tblinventorytransactionitems_invtrans_length"));
                        stockItemTransactionDTO.setName(rs.getString("tblstockitem_si_name"));
                        stockItemTransactionDTO.setRate(rs.getDouble("tblinventorytransactionitems_invtrans_rate"));
                        stockItemTransactionDTO.setQuantity(rs.getDouble("tblinventorytransactionitems_invtrans_qty"));
                        stockItemTransactionDTO.setSqFeet(rs.getDouble("tblinventorytransactionitems_invtrans_totalSize"));
                        stockItemTransactionDTO.setThkness(rs.getDouble("tblinventorytransactionitems_invtrans_thickness"));
                        stockItemTransactionDTO.setWidth(rs.getDouble("tblinventorytransactionitems_invtrans_width"));
                        stockItemTransactionDTOList.add(stockItemTransactionDTO);
                    }
                }

                chalanDTO = ImpExpUtil.valiDateAndRepairChalanDTO(chalanDTO);
                chalanDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

                if (!calling_Class.isEmpty()) {
                    gen.ImpExp.TagsHelper1.ledgerNameSet.add(chalanDTO.getCashLedger());
                    gen.ImpExp.TagsHelper1.ledgerNameSet.add(chalanDTO.getSaleLedger());
                    for (StockItemTransactionDTO stktransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {
                        gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
                    }
                }

                chalanDTOList.add(chalanDTO);
            }
            con.commit();
            con.close();
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
            throw e;
        }
        return chalanDTOList;
    }

    public static List<ChalanDTO> getTransactionList() throws SQLException, Exception {
        List<ChalanDTO> ChalanDTOTransactionList = new ArrayList<ChalanDTO>();
        Connection conn = null;

        try {

            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (getCount_transaction(Constants.CHALAN_TYPE_INDEX, Constants.CREDIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.CHALAN_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            System.out.println("getTransactionList ====----------" + queryOne);
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ChalanDTO chalanDTO = new ChalanDTO();
                chalanDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                chalanDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                chalanDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));
                chalanDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                ChalanDTOTransactionList.add(chalanDTO);
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
        return ChalanDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        AccountingVoucherHelper.deleteTransaction(transID, Constants.CHALAN_TYPE_INDEX);
    }

    public static void deleteReords(long transID, Connection conn) throws SQLException, Exception {
//	Connection conn = null;
        try {
//	    conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            String query;

            query = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + transID + ")";
            st1.executeUpdate(query);

            query = "delete from tbltransactionledger where trans_id=" + transID + "";
            st2.executeUpdate(query);

            query = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(query);

            query = "delete from tbltransactionvat where trans_id=" + transID + "";
            st3.executeUpdate(query);
//	    conn.commit();
//	    conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
//		conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateChalanVoucher(List<ChalanDTO> chalanFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                deleteReords(Long.parseLong(chalanDTO.getTrans_ID()), conn);
            }


            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_narration = ?,trans_grandtotal = ?,trans_transport = ?,trans_lessBillAmt = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                ps.setInt(1, (chalanDTO.getReceiptNo()));

                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(chalanDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.CHALAN_TYPE_INDEX);
                ps.setString(4, chalanDTO.getNote().toString());
                ps.setDouble(5, 0d);
                ps.setDouble(6, chalanDTO.getTransport());
                ps.setDouble(7, chalanDTO.getLessBillAmount());
                ps.setDouble(8, Long.parseLong(chalanDTO.getTrans_ID().toString()));

                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment) values(?,?,?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                ps1.setLong(1, Long.parseLong(chalanDTO.getTrans_ID()));
                ps1.setDouble(2, chalanDTO.getVatRate());
                ps1.setDouble(3, chalanDTO.getVatAmount());

                ps1.addBatch();

                ps2.setLong(1, Long.parseLong(chalanDTO.getTrans_ID()));
                ps2.setString(2, chalanDTO.getDispatchDocThrough());
                ps2.setString(3, chalanDTO.getDispatchDocNo());
                ps2.setDouble(4, 0d);
                ps2.setString(5, "" + 0);
                ps2.setString(6, "" + 0);

                ps2.addBatch();
                i++;
            }
            ps1.executeBatch();
            ps2.executeBatch();

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,IsAvaibleToUser) values(?,?,?,?,? , ?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                ps6.setLong(1, Long.parseLong(chalanDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(chalanDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, 0d);
                ps6.setDouble(6, 1);
                ps6.addBatch();

                ps6.setLong(1, Long.parseLong(chalanDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(chalanDTO.getSaleLedger().toString()));
                ps6.setInt(3, Constants.DEBIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, 0d);
                ps6.setDouble(6, 1);
                ps6.addBatch();

                i++;
            }
            ps6.executeBatch();
            ps6.close();


            //todo
            i = 0;
            List<Integer> idList = new ArrayList<Integer>();
            String queryFive = "update tblInventoryTransaction set trans_id = ?,invtrans_type = ? where invtrans_id =?";
            PreparedStatement ps3 = conn.prepareStatement(queryFive);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                ps = conn.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + Long.parseLong(chalanDTO.getTrans_ID()));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idList.add(rs.getInt(1));
                }
                ps.close();
                i = 0;
                Integer fk = idList.get(i);
                ps3.setLong(1, Long.parseLong(chalanDTO.getTrans_ID()));
                ps3.setString(2, Constants.CHALAN_TYPE_INDEX.toString());
                ps3.setLong(3, fk);
                ps3.addBatch();
                i++;
            }
            ps3.executeBatch();
            ps3.close();

            i = 0;
            String querySix = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySix);
            for (ChalanDTO chalanDTO : chalanFormDTOList) {
                Integer fk = idList.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, 0d);
                    ps4.setDouble(5, 0d);
                    ps4.setDouble(6, 0d);
                    ps4.setInt(7, k);
                    ps4.setDouble(8, 0.0);
                    ps4.setDouble(9, 0d);
                    ps4.setDouble(10, 0d);
                    ps4.setDouble(11, 0d);

                    ps4.addBatch();
                    k++;
                }
                i++;

            }
            ps4.executeBatch();
            ps4.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // get chalan Trans_id for chalan receipt no
    public static String getChalan_trans_ID(String chalan_ReceiptNo) throws SQLException, Exception {
        String chalan_Trans_ID = "";
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "select trans_id from tbltransactionmain where trans_receiptNo = ? and trans_typeIndex=?";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            psmt.setString(1, chalan_ReceiptNo);
            psmt.setInt(2, Constants.CHALAN_TYPE_INDEX);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                chalan_Trans_ID = rs.getString("trans_id");
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
        return chalan_Trans_ID;
    }

    // check whether sale has done for thw chalan
    public static boolean check_Chalan_For_Generate_Invoice(String chalan_Receipt_No) throws Exception {
        Boolean isExist = false;
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            String trans_id = "";
            trans_id = getChalan_trans_ID(chalan_Receipt_No);
//            String queryOne = "select  * from tbltranscactionchange where chalan_trans_id = " + trans_id + "";
//            PreparedStatement prpdStmt1 = con.prepareStatement("select  * from tbltranscactionchange where chalan_trans_id = ? ");
//            System.out.println("check the number ------------>>>>>>>>>>>>>>" + queryOne);
//            prpdStmt1.setString(1, trans_id);
            String queryOne = "select  * from tbltranscactionchange where chalan_trans_id = " + trans_id + "  and voucher_type = " + gen.dto.Constants.CHALAN_TYPE_INDEX.toString() + "";
            PreparedStatement prpdStmt1 = con.prepareStatement("select  * from tbltranscactionchange where chalan_trans_id = ? and voucher_type = ?");
            System.out.println("check the number ------------>>>>>>>>>>>>>>" + queryOne);
            prpdStmt1.setString(1, trans_id);
            prpdStmt1.setString(2, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
            ResultSet rs1 = prpdStmt1.executeQuery();
            if (rs1.next()) {
                isExist = true;
            }
            rs1.close();
            prpdStmt1.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isExist;
    }

    // get trans_id of chalan for Invoice Generated... 
    public static List<String> getInvoice_Generated_Chalan_trans_ID() throws SQLException, Exception {
        List<String> chalan_Trans_ID_List = new ArrayList<String>();
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
//            String queryOne = "select chalan_trans_id from tbltranscactionchange";
//            PreparedStatement psmt = conn.prepareStatement(queryOne);
//            ResultSet rs = psmt.executeQuery();
//            while (rs.next()) {
//                chalan_Trans_ID_List.add(rs.getString("chalan_trans_id"));
//            }
            String queryOne = "select chalan_trans_id from tbltranscactionchange where voucher_type = ?";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            psmt.setString(1, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                chalan_Trans_ID_List.add(rs.getString("chalan_trans_id"));
            }
            rs.close();
            psmt.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return chalan_Trans_ID_List;
    }

    public static Long getCount_transaction(Integer TYPE_INDEX, Integer Trans_Type) throws SQLException, Exception {
        Long return_count = 0L;
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "";
            if (Constants.CREDIT == Trans_Type) {
                queryOne = "SELECT     count(tbltransactionmain.trans_id) as tbltransactionmain_trans_id FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT + " and trans_typeIndex=" + TYPE_INDEX;
            } else if (Constants.DEBIT == Trans_Type) {
                queryOne = "SELECT     count(tbltransactionmain.trans_id) as tbltransactionmain_trans_id FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.DEBIT + " and trans_typeIndex=" + TYPE_INDEX;
            }

            System.out.println("Query -------------- " + queryOne);
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                return_count = rs.getLong("tbltransactionmain_trans_id");
            }
            rs.close();
            psmt.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return return_count;
    }
}

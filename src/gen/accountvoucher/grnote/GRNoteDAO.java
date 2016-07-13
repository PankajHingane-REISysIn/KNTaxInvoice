/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.grnote;

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
 * @author admin
 */
public class GRNoteDAO {

    public static void insertGRNoteVoucher(List<GRNoteDTO> grnoteFormDTOList) throws SQLException, ParseException, Exception {

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_transport,trans_lessBillAmt) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                ps.setInt(1, (GRNoteDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(GRNoteDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.GRNOTE_TYPE_INDEX);
                ps.setString(4, GRNoteDTO.getNote().toString());
                ps.setDouble(5, 0d);
                ps.setDouble(6, 0d);
                ps.setDouble(7, 0d);

                ps.addBatch();

            }
            if (!grnoteFormDTOList.isEmpty()) {
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
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);

            int check_for_Chalan_to_Sale = 0;
            String querySeven = "insert into tbltranscactionchange(sale_trans_id,chalan_trans_id,voucher_type) values(?,?,?)";
            PreparedStatement ps8 = conn.prepareStatement(querySeven);

            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                Integer fk = idList.get(i);
                ps1.setInt(1, fk);
                ps1.setDouble(2, 0d);
                ps1.setDouble(3, 0d);

                ps1.addBatch();

                ps2.setInt(1, fk);
//                ps2.setString(2, GRNoteDTO.getDispatchDocThrough());
//                ps2.setString(3, GRNoteDTO.getDispatchDocNo());
                ps2.setDouble(2, 0d);
                ps2.setString(3, "" + 0);
                ps2.setString(4, "" + 0);

                ps2.addBatch();

                if (GRNoteDTO.getPurchaseOrderNo_Transid() != null && !GRNoteDTO.getPurchaseOrderNo_Transid().trim().equalsIgnoreCase("") && !GRNoteDTO.getPurchaseOrderNo_Transid().isEmpty() ) {
                    ps8.setInt(1, fk);
                    ps8.setString(2, GRNoteDTO.getPurchaseOrderNo_Transid());
                    ps8.setInt(3, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX);
                    ps8.addBatch();
                    check_for_Chalan_to_Sale = 1;
                }

                i++;
            }
            if (!grnoteFormDTOList.isEmpty()) {
                ps1.executeBatch();
                ps2.executeBatch();
            }
            if (check_for_Chalan_to_Sale == 1) {
                ps8.executeBatch();
            }

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt, IsAvaibleToUser) values(?,?,?,?,? , ?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                Integer fk = idList.get(i);
                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(GRNoteDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT_DEBIT_NIL);
                ps6.setInt(4, 0);
                ps6.setDouble(5, 0d);
                ps6.setDouble(6, 1);
                ps6.addBatch();

                i++;

            }
            if (!grnoteFormDTOList.isEmpty()) {
                ps6.executeBatch();
            }
            ps6.close();

            i = 0;
            String queryFive = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
            PreparedStatement ps3 = conn.prepareStatement(queryFive, Statement.RETURN_GENERATED_KEYS);
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                Integer fk = idList.get(i);
                ps3.setInt(1, fk);
                ps3.setString(2, Constants.GRNOTE_TYPE_INDEX.toString());
                ps3.addBatch();
                i++;
            }
            if (!grnoteFormDTOList.isEmpty()) {
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
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                Integer fk = idList1.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : GRNoteDTO.getStockItemTreansactioDTOList()) {

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
            if (!grnoteFormDTOList.isEmpty()) {
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

    public static List<GRNoteDTO> getGRNote(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        Connection con = null;
        List<GRNoteDTO> grnoteDTOList = new ArrayList<GRNoteDTO>();
        try {
            if (receiptIDSet == null || receiptIDSet.isEmpty()) {
                return null;
            }

            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {

                String queryOne = "SELECT     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,   tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,tbltransactionmain.trans_id AS tbltransactionmain_trans_ID,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tblstockitem.si_name AS tblstockitem_si_name,     tblledger.ledger_name AS tblledger_ledger_name,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration FROM      tbltransactionmain tbltransactionmain INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tblinventorytransaction tblinventorytransaction ON tbltransactionmain.trans_id = tblinventorytransaction.trans_id     INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE trans_typeIndex = " + Constants.GRNOTE_TYPE_INDEX + " and     trans_receiptNo =  " + receiptID;
                PreparedStatement psmt = con.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                GRNoteDTO GRNoteDTO = new GRNoteDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT_DEBIT_NIL && isAdded == false) {
                        GRNoteDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        GRNoteDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                        GRNoteDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
//                        GRNoteDTO.setDispatchDocNo(rs.getString("tbltransactionotherdetails_trans_dispatchDocNo"));
//                        GRNoteDTO.setDispatchDocThrough(rs.getString("tbltransactionotherdetails_trans_dispatchDocThrough"));
                        GRNoteDTO.setNote(rs.getString("tbltransactionmain_trans_narration")); //to do
                        GRNoteDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                        isAdded = true;
                    }
//                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.DEBIT) {
//                        GRNoteDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));
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
//                    }
                }
                //  GRNoteDTO = ImpExpUtil.valiDateAndRepairChalanDTO(GRNoteDTO);
                GRNoteDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);


                // getting PO_transid for respective Grnoteid present in tbltransactonchange
                getInvoice_Generated_Chalan_trans_ID(gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString(), GRNoteDTO.getTrans_ID(), gen.dto.Constants.SALE_TYPE_INDEX.toString());

                for (VoucherReferenceDTO voucherReferenceDTO : getInvoice_Generated_Chalan_trans_ID(gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString(), GRNoteDTO.getTrans_ID(), gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
                    {
                        GRNoteDTO.setPurchaseOrderNo_Transid(voucherReferenceDTO.getChalan_trans_id());
                    }
                }

//                if (!calling_Class.isEmpty()) {
//                    gen.ImpExp.TagsHelper1.ledgerNameSet.add(GRNoteDTO.getCashLedger());
//                    // gen.ImpExp.TagsHelper1.ledgerNameSet.add(GRNoteDTO.getSaleLedger());
//                    for (StockItemTransactionDTO stktransactionDTO : GRNoteDTO.getStockItemTreansactioDTOList()) {
//                        gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
//                    }
//                }



                grnoteDTOList.add(GRNoteDTO);
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
        return grnoteDTOList;
    }

    public static List<GRNoteDTO> getTransactionList() throws SQLException, Exception {
        List<GRNoteDTO> ChalanDTOTransactionList = new ArrayList<GRNoteDTO>();
        Connection conn = null;

        try {

            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.GRNOTE_TYPE_INDEX, Constants.DEBIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT_DEBIT_NIL + "  and trans_typeIndex=" + Constants.GRNOTE_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            System.out.println("getTransactionList ====----------" + queryOne);
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                GRNoteDTO GRNoteDTO = new GRNoteDTO();
                GRNoteDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                GRNoteDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                GRNoteDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                GRNoteDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                ChalanDTOTransactionList.add(GRNoteDTO);
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
        AccountingVoucherHelper.deleteTransaction(transID, Constants.GRNOTE_TYPE_INDEX);
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

    public static void updateGRNoteVoucher(List<GRNoteDTO> grnoteFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                deleteReords(Long.parseLong(GRNoteDTO.getTrans_ID()), conn);
            }


            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_narration = ?,trans_grandtotal = ?,trans_transport = ?,trans_lessBillAmt = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                ps.setInt(1, (GRNoteDTO.getReceiptNo()));

                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(GRNoteDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.GRNOTE_TYPE_INDEX);
                ps.setString(4, GRNoteDTO.getNote().toString());
                ps.setDouble(5, 0d);
                ps.setDouble(6, 0d);
                ps.setDouble(7, 0d);
                ps.setDouble(8, Long.parseLong(GRNoteDTO.getTrans_ID().toString()));

                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);

            int check_for_Chalan_to_Sale = 0;
            String querySeven = "update tbltranscactionchange set chalan_trans_id = ?,voucher_type=? where sale_trans_id = ? ";
            PreparedStatement ps8 = conn.prepareStatement(querySeven);

            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                ps1.setLong(1, Long.parseLong(GRNoteDTO.getTrans_ID()));
                ps1.setDouble(2, 0d);
                ps1.setDouble(3, 0d);

                ps1.addBatch();

                ps2.setLong(1, Long.parseLong(GRNoteDTO.getTrans_ID()));
                ps2.setDouble(2, 0d);
                ps2.setString(3, "" + 0);
                ps2.setString(4, "" + 0);

                ps2.addBatch();

                if (GRNoteDTO.getPurchaseOrderNo_Transid() != null && !GRNoteDTO.getPurchaseOrderNo_Transid().trim().equalsIgnoreCase("") && !GRNoteDTO.getPurchaseOrderNo_Transid().isEmpty()) {
                    ps8.setInt(3, Integer.parseInt(GRNoteDTO.getTrans_ID()));
                    ps8.setString(1, GRNoteDTO.getPurchaseOrderNo_Transid());
                    ps8.setInt(2, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX);
                    ps8.addBatch();
                    check_for_Chalan_to_Sale = 1;
                }

                i++;
            }
            ps1.executeBatch();
            ps2.executeBatch();

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,IsAvaibleToUser) values(?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                ps6.setLong(1, Long.parseLong(GRNoteDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(GRNoteDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT_DEBIT_NIL);
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
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                ps = conn.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + Long.parseLong(GRNoteDTO.getTrans_ID()));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idList.add(rs.getInt(1));
                }
                ps.close();
                i = 0;
                Integer fk = idList.get(i);
                ps3.setLong(1, Long.parseLong(GRNoteDTO.getTrans_ID()));
                ps3.setString(2, Constants.GRNOTE_TYPE_INDEX.toString());
                ps3.setLong(3, fk);
                ps3.addBatch();
                i++;
            }
            ps3.executeBatch();
            ps3.close();

            i = 0;
            String querySix = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySix);
            for (GRNoteDTO GRNoteDTO : grnoteFormDTOList) {
                Integer fk = idList.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : GRNoteDTO.getStockItemTreansactioDTOList()) {

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
    public static String getGRNote_trans_ID(String chalan_ReceiptNo) throws SQLException, Exception {
        String chalan_Trans_ID = "";
        Connection conn = null;
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "select trans_id from tbltransactionmain where trans_receiptNo = ? and trans_typeIndex=?";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            psmt.setString(1, chalan_ReceiptNo);
            psmt.setInt(2, Constants.GRNOTE_TYPE_INDEX);
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
    public static boolean check_PO_For_Generate_Invoice(String chalan_Receipt_No) throws Exception {
        Boolean isExist = false;
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            String trans_id = "";
            trans_id = getGRNote_trans_ID(chalan_Receipt_No);
            if (!trans_id.isEmpty()) {
                String queryOne = "select  * from tbltranscactionchange where chalan_trans_id = " + trans_id + " and voucher_type =" + gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX + "";
                System.out.println("dsasdsdsa                "+queryOne);
                PreparedStatement prpdStmt1 = con.prepareStatement("select  * from tbltranscactionchange where chalan_trans_id = " + trans_id + " and voucher_type =" + gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX + "");
                ResultSet rs1 = prpdStmt1.executeQuery();
                if (rs1.next()) {
                    isExist = true;
                }
                rs1.close();
                prpdStmt1.close();
            }
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
    public static List<VoucherReferenceDTO> getInvoice_Generated_Chalan_trans_ID(String voucher_type, String trans_id, String trans_id_type) throws SQLException, Exception {
        Connection conn = null;
        List<VoucherReferenceDTO> voucherReferenceDTOList = new ArrayList<VoucherReferenceDTO>();
        try {

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "";
            if (trans_id != null && !trans_id.isEmpty() && !trans_id_type.isEmpty() && trans_id_type != null) {
                if (trans_id_type.equalsIgnoreCase(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
                    queryOne = "select * from tbltranscactionchange where voucher_type = ? and  sale_trans_id = ?";
                } else {
                    queryOne = "select * from tbltranscactionchange where voucher_type = ? and  chalan_trans_id = ?";
                }
            } else {
                queryOne = "select * from tbltranscactionchange where voucher_type = ? ";
            }
            System.out.println("were " + queryOne);
//            queryOne = "select * from tbltranscactionchange";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            if (trans_id != null && !trans_id.isEmpty()) {
                psmt.setString(1, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString());
                psmt.setString(2, trans_id);
            } else {
                psmt.setString(1, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString());
            }

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                VoucherReferenceDTO voucherReferenceDTO = new VoucherReferenceDTO();
//		chalan_Trans_ID_List.add(rs.getString("chalan_trans_id"));
                voucherReferenceDTO.setChalan_trans_id(rs.getString("chalan_trans_id"));
                voucherReferenceDTO.setSale_trans_id(rs.getString("sale_trans_id"));
                voucherReferenceDTO.setVoucher_type(rs.getInt("voucher_type"));
                System.out.println("!!!  " + rs.getString("chalan_trans_id"));
                System.out.println("!!!  -------- " + rs.getString("sale_trans_id"));
                System.out.println("!!!  ---------  " + rs.getString("voucher_type"));
                voucherReferenceDTOList.add(voucherReferenceDTO);
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
        return voucherReferenceDTOList;
    }

    public static List<StockItemTransactionDTO> getsi_Qty_of_PO_GRNote(List<StockItemTransactionDTO> stockItemTransactionDTOList, List<String> grNote_trans_id) throws SQLException, Exception {
        Connection conn = null;
        List<StockItemTransactionDTO> returnstockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "";
            String condition = "";

            String str = "";
            for (String grNote_transid : grNote_trans_id) {
                str = str + grNote_transid + ",";
            }
            if (!str.isEmpty()) {
                str = str.trim().substring(0, str.length() - 1);
            }


            queryOne = "select sum(itm.invtrans_qty) as total_qty from tblinventorytransaction as i inner join  tblinventorytransactionitems as itm on itm.invtrans_id = i.invtrans_id ";
            condition = "where itm.invtrans_itemId = ? and i.invtrans_id = (select invtrans_id  from tblinventorytransaction where trans_id in(?)) group by itm.invtrans_itemId ";
            queryOne = queryOne + condition;
            System.out.println("Query ----------- " + queryOne);
            System.out.println("                                 " + grNote_trans_id.size());
            for (String grNote_transid : grNote_trans_id) {
                System.out.println("(((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))" + stockItemTransactionDTOList.size());
                for (StockItemTransactionDTO stockItemTransactionDTO1 : stockItemTransactionDTOList) {
                    PreparedStatement prmt = conn.prepareStatement(queryOne);
                    prmt.setString(1, stockItemTransactionDTO1.getName());
                    System.out.println("Str ------------ " + str);
                    prmt.setString(2, grNote_transid);
                    ResultSet rs = prmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("rrrrrr");
                        StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                        stockItemTransactionDTO.setName(stockItemTransactionDTO1.getName());
                        stockItemTransactionDTO.setQuantity(Double.parseDouble(rs.getString("total_qty")));
                        returnstockItemTransactionDTOList.add(stockItemTransactionDTO);
                    }
                }
            }
//            for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
//                prmt.setString(1, stockItemTransactionDTO.getName().toString());
//                prmt.addBatch();
//                prmt.setString(2, str);
//                prmt.addBatch();
//            }
//            prmt.executeBatch();
//            while (prmt.getMoreResults()) {
//                ResultSet rs = prmt.getResultSet();
//                while (rs.next()) {
//                    StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
//                    stockItemTransactionDTO.setName(rs.getString("itm.invtrans_itemId"));
//                    stockItemTransactionDTO.setQuantity(Double.parseDouble(rs.getString("itm.invtrans_qty")));
//                    returnstockItemTransactionDTOList.add(stockItemTransactionDTO);
//                }
//            }
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
        return returnstockItemTransactionDTOList;
    }
}

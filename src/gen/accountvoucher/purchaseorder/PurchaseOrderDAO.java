/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.purchaseorder;

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

/**
 *
 * @author admin
 */
public class PurchaseOrderDAO {

    public static void insertPurchaseOrderVoucher(List<PurchaseOrderDTO> PurchaseOrderDTOList) throws SQLException, ParseException, Exception {

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);


            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_lessBillAmt) values(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                System.out.println("Insert into ----------- queryOne");
                ps.setInt(1, (PurchaseOrderDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(PurchaseOrderDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                System.out.println("SQL DATE ------- " + sqlStartDate);
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.PURCHASE_ORDER_TYPE_INDEX);
                System.out.println(" Purchase DTO Get Note ---     " + PurchaseOrderDTO.getNote().toString());
                ps.setString(4, PurchaseOrderDTO.getNote().toString());
                ps.setDouble(5, Double.parseDouble(PurchaseOrderDTO.getFinalAmount().toString()));
                ps.setDouble(6, 0d);
                ps.addBatch();
            }
            if (!PurchaseOrderDTOList.isEmpty()) {
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
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_round_Off_amt) values(?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);

            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                System.out.println("Insert into ----------- queryTwo");

                Integer fk = idList.get(i);
                ps1.setInt(1, fk);
                ps1.setDouble(2, PurchaseOrderDTO.getVatRate());
                ps1.setDouble(3, PurchaseOrderDTO.getVatAmount());

                ps1.addBatch();

                ps2.setInt(1, fk);
                ps2.setDouble(2, PurchaseOrderDTO.getAmount());
                ps2.setString(3, "" + 0);
                ps2.setDouble(4, PurchaseOrderDTO.getRoundOffAmount());

                ps2.addBatch();


                i++;
            }
            if (!PurchaseOrderDTOList.isEmpty()) {
                ps1.executeBatch();
                ps2.executeBatch();
            }

            String queryFive = "insert into tblTransactionLedger(trans_id, trans_ledgerId, trans_type, trans_index, trans_amt, IsAvaibleToUser) values(?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFive);
            i = 0;
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                System.out.println("Insert into ----------- queryFive");
                Integer fk = idList.get(i);
                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(PurchaseOrderDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT_DEBIT_NIL);
                ps6.setInt(4, 0);      //index of ledger on form
                ps6.setDouble(5, PurchaseOrderDTO.getFinalAmount());
                ps6.setInt(6, 1); //IsAvailable to User
                ps6.addBatch();

                i++;

            }
            if (!PurchaseOrderDTOList.isEmpty()) {
                ps6.executeBatch();
            }
            ps6.close();

            i = 0;
            String querySix = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
            PreparedStatement ps3 = conn.prepareStatement(querySix, Statement.RETURN_GENERATED_KEYS);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                System.out.println("Insert into ----------- seven");

                Integer fk = idList.get(i);
                ps3.setInt(1, fk);
                ps3.setString(2, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString());
                ps3.addBatch();
                i++;
            }
            if (!PurchaseOrderDTOList.isEmpty()) {
                ps3.executeBatch();
            }


            ResultSet rs1 = ps3.getGeneratedKeys();
            List<Integer> idList1 = new ArrayList<Integer>();
            while (rs1.next()) {
                idList1.add(rs1.getInt(1));
            }
            ps3.close();
            i = 0;
            String querySeven = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySeven);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                System.out.println("Insert into ----------- querySeven");
                Integer fk = idList1.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : PurchaseOrderDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
                    ps4.setDouble(5, Double.parseDouble(stockItemTransactionDTO.getAmount().toString()));
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
            if (!PurchaseOrderDTOList.isEmpty()) {
                ps4.executeBatch();
            }
            ps4.close();
            conn.commit();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static List<PurchaseOrderDTO> getPurchaseOrder(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        if (receiptIDSet == null || receiptIDSet.isEmpty()) {
            return null;
        }
        List<PurchaseOrderDTO> PurchaseOrderDTOList = new ArrayList<PurchaseOrderDTO>();
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT      tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id, tbltransactionotherdetails.trans_round_Off_amt AS tbltransactionotherdetails_trans_round_Off_amt,     tblinventorytransaction.trans_id AS tblinventorytransaction_trans_id,     tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type,     tblinventorytransaction.dat AS tblinventorytransaction_dat,     tblinventorytransactionitems.invtrans_id AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_finishType AS tblinventorytransactionitems_invtrans_finishType,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tblinventorytransactionitems.invtrans_making AS tblinventorytransactionitems_invtrans_making,     tblledger.ledger_id AS tblledger_ledger_id,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitem.si_id AS tblstockitem_si_id,     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionmain.trans_transport AS tbltransactionmain_trans_transport,     tbltransactionmain.trans_lessBillAmt AS tbltransactionmain_trans_lessBillAmt,     tbltransactionmain.trans_payment AS tbltransactionmain_trans_payment,     tbltransactionledger.IsAvaibleToUser AS tbltransactionledger_IsAvaibleToUser,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionmain.trans_narration AS tbltransactionledger_trans_narration,     tbltransactionledger.trans_index AS tbltransactionledger_trans_index,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,     tbltransactionledger.trans_id AS tbltransactionledger_trans_id,     tbltransactionvat.trans_id AS tbltransactionvat_trans_id,     tbltransactionvat.vat_rate AS tbltransactionvat_vat_rate,     tbltransactionvat.vat_amt AS tbltransactionvat_vat_amt,     tbltransactionvat.discount_rate AS tbltransactionvat_discount_rate,    tbltransactionvat.shipping AS tbltransactionvat_shipping,     tbltransactionvat.discount_amt AS tbltransactionvat_discount_amt,     tbltransactionotherdetails.trans_id AS tbltransactionotherdetails_trans_id,     tbltransactionotherdetails.trans_reference AS tbltransactionotherdetails_trans_reference,     tbltransactionotherdetails.trans_buyerOrderNo AS tbltransactionotherdetails_trans_buyerOrderNo,     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionotherdetails.trans_amt AS tbltransactionotherdetails_trans_amt,     tbltransactionotherdetails.trans_payment AS tbltransactionotherdetails_trans_payment,     tbltransactionotherdetails.trans_vehicleNo AS tbltransactionotherdetails_trans_vehicleNo,     tbltransactionotherdetails.trans_challanNo AS tbltransactionotherdetails_trans_challanNo,     tbltransactionotherdetails.trans_poNo AS tbltransactionotherdetails_trans_poNo,     tbltransactionotherdetails.trans_challanDate AS tbltransactionotherdetails_trans_challanDate,     tbltransactionotherdetails.trans_poDate AS tbltransactionotherdetails_trans_poDate,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo FROM      tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionvat tbltransactionvat ON tbltransactionmain.trans_id = tbltransactionvat.trans_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE trans_typeIndex = " + Constants.PURCHASE_ORDER_TYPE_INDEX + " and     trans_receiptNo = " + receiptID + " and IsAvaibleToUser != 2 order by tbltransactionmain.trans_date asc";
                System.out.println("ee      " + queryOne);
                PreparedStatement psmt = con.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                PurchaseOrderDTO PurchaseOrderDTO = new PurchaseOrderDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT_DEBIT_NIL && isAdded == false) {
                        PurchaseOrderDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                        PurchaseOrderDTO.setAmount(rs.getDouble("tbltransactionotherdetails_trans_amt"));
                        PurchaseOrderDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        PurchaseOrderDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                        System.out.println("setFinalAmount-->>>" + PurchaseOrderDTO.getFinalAmount());
                        System.out.println("Get Note DTO ---------------    " + rs.getString("tbltransactionledger_trans_narration"));
                        PurchaseOrderDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                        PurchaseOrderDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                        PurchaseOrderDTO.setpurchaseOrder_Trans_ID(rs.getString("tbltransactionmain_trans_id"));
                        PurchaseOrderDTO.setVatAmount(rs.getDouble("tbltransactionvat_vat_amt"));
                        PurchaseOrderDTO.setVatRate(rs.getDouble("tbltransactionvat_vat_rate"));
                        isAdded = true;
                    }

//                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT) {
//                        PurchaseOrderDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));
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


//                    PurchaseOrderDTO = ImpExpUtil.valiDateAndRepairPurchaseOrderDTO(PurchaseOrderDTO);


                }
                System.out.println("Size of stockItemTransactionDTOList---->>>" + stockItemTransactionDTOList.size());
                PurchaseOrderDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

                PurchaseOrderDTOList.add(PurchaseOrderDTO);
            }
            con.commit();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return PurchaseOrderDTOList;
    }

    public static List<PurchaseOrderDTO> getTransactionList(List<String> receiptNo_List, String condition_Type) throws SQLException, Exception {
        List<PurchaseOrderDTO> PurchaseOrderDTOTransactionList = new ArrayList<PurchaseOrderDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            if (receiptNo_List != null && receiptNo_List.isEmpty()) {
                if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                    gen.dto.Constants.OFFSET_VALUE = 0L;
                }
                if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PURCHASE_ORDER_TYPE_INDEX, Constants.DEBIT) < gen.dto.Constants.OFFSET_VALUE) {
                    gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
                }
            }

            String queryOne = "";
            String receipt_Id_List = "";
            for (String str : receiptNo_List) {
                System.out.println("No ------------ " + str);
                receipt_Id_List = str + "," + receipt_Id_List;
            }
            if (receipt_Id_List != null && !receipt_Id_List.isEmpty()) {
                receipt_Id_List = receipt_Id_List.substring(0, receipt_Id_List.length() - 1);
            }

            System.out.println("receiptNo_List       " + receiptNo_List != null);
            System.out.println("receiptNo_List                 " + receiptNo_List.size());
            System.out.println("condition type                 " + condition_Type);
            String condition = "";
            if (receiptNo_List != null && receiptNo_List.isEmpty()) {
                queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT_DEBIT_NIL + "  and trans_typeIndex=" + Constants.PURCHASE_ORDER_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            } else if (condition_Type.equals("trans_id")) {
                queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT_DEBIT_NIL + "  and trans_typeIndex=" + Constants.PURCHASE_ORDER_TYPE_INDEX + " and trans_id in (?)  order by  trans_receiptNo Asc";
            } else if (condition_Type.equals("receipt_No")) {
                 queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT_DEBIT_NIL + "  and trans_typeIndex=" + Constants.PURCHASE_ORDER_TYPE_INDEX + " and trans_receiptNo in (?)  order by  trans_receiptNo Asc";
            }

            System.out.println("Query  for --------- " + receipt_Id_List);
            System.out.println("Query  for --------- " + queryOne);
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            if (receipt_Id_List != null && !receipt_Id_List.isEmpty()) {
//                if (condition_Type.equals("trans_id")) {
                    psmt.setString(1, receipt_Id_List);
//                } else if(condition_Type.equals("receipt_No")){
//                    psmt.setString(1, receipt_Id_List);
//                }
            }
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                PurchaseOrderDTO PurchaseOrderDTO = new PurchaseOrderDTO();
                PurchaseOrderDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                PurchaseOrderDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                PurchaseOrderDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                PurchaseOrderDTO.setpurchaseOrder_Trans_ID(rs.getString("tbltransactionmain_trans_id"));
                System.out.println("Purchase order transctionLIst Receipt No----- " + rs.getInt("tbltransactionmain_trans_receiptNo"));
                PurchaseOrderDTOTransactionList.add(PurchaseOrderDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return PurchaseOrderDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.PURCHASE_ORDER_TYPE_INDEX);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static void deleteReords(long transID, Connection conn) throws Exception {
//        Connection conn = null;
        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();

            String queryOne = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + transID + ")";
            st1.executeUpdate(queryOne);

            String queryTwo = "delete from tbltransactionledger where trans_id=" + transID + "";
            st2.executeUpdate(queryTwo);

            String queryThree = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(queryThree);

            String queryFour = "delete from tbltransactionvat where trans_id=" + transID + "";
            st3.executeUpdate(queryFour);

//            conn.commit();
//            conn.close();
        } catch (Exception ex) {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
            throw ex;
        }
    }

    public static void updatePurchaseOrderVoucher(List<PurchaseOrderDTO> PurchaseOrderDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                deleteReords(Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()), conn);
            }

            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_narration = ?,trans_grandtotal = ?,trans_lessBillAmt = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                ps.setInt(1, (PurchaseOrderDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(PurchaseOrderDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.PURCHASE_ORDER_TYPE_INDEX);
                System.out.println("GET NOTE ------------   " + PurchaseOrderDTO.getNote().toString());
                ps.setString(4, PurchaseOrderDTO.getNote().toString());
                ps.setDouble(5, Double.parseDouble(PurchaseOrderDTO.getFinalAmount().toString()));
                ps.setDouble(6, 0d);
                ps.setDouble(7, Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID().toString()));

                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                ps1.setLong(1, Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()));
                ps1.setDouble(2, PurchaseOrderDTO.getVatRate());
                ps1.setDouble(3, PurchaseOrderDTO.getVatAmount());
                ps1.addBatch();

                ps2.setLong(1, Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()));
                ps2.setDouble(2, PurchaseOrderDTO.getAmount());
                ps2.setString(3, "" + 0);
                ps2.setString(4, "" + 0);
                ps2.addBatch();

                i++;
            }
            ps1.executeBatch();
            ps2.executeBatch();

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,IsAvaibleToUser) values(?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                ps6.setLong(1, Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()));
                ps6.setInt(2, Integer.parseInt(PurchaseOrderDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT_DEBIT_NIL);
                ps6.setInt(4, 0);
                ps6.setDouble(5, PurchaseOrderDTO.getFinalAmount());
                ps6.setInt(6, 1); //IsAvailable to User
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
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                ps = conn.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idList.add(rs.getInt(1));
                }
                ps.close();
                i = 0;
                Integer fk = idList.get(i);
                ps3.setLong(1, Long.parseLong(PurchaseOrderDTO.getpurchaseOrder_Trans_ID()));
                ps3.setString(2, "" + 1);
                ps3.setLong(3, fk);
                ps3.addBatch();
                i++;
            }
            ps3.executeBatch();
            ps3.close();

            i = 0;
            String querySix = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySix);
            for (PurchaseOrderDTO PurchaseOrderDTO : PurchaseOrderDTOList) {
                Integer fk = idList.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : PurchaseOrderDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
                    ps4.setDouble(5, Double.parseDouble(stockItemTransactionDTO.getAmount().toString()));
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

        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }
//    public static void insertStockItemRate(List<CustomerRateDTO> customerRateDTOList) throws SQLException {
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            String queryOne = "insert into tblstockitemrate(custid,stkid,rate)values(?,?,?)";
//            PreparedStatement ps = conn.prepareStatement(queryOne);
//            for (CustomerRateDTO customerRateDTO : customerRateDTOList) {
//                ps.setInt(1, customerRateDTO.getCustomerID());
//                ps.setInt(2, customerRateDTO.getStockItemID());
//                ps.setDouble(3, customerRateDTO.getRate());
//
//                ps.addBatch();
//
//            }
//            ps.executeBatch();
//            ps.close();
//            conn.commit();
//            conn.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//
//    }
//
//    public static Map<String, String> getRateByCustomerID(Long customerID) throws SQLException, Exception {
//        Connection conn = null;
//        Map<String, String> mapTOReturn = new HashMap<String, String>();
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            conn.setAutoCommit(false);
//            String queryOne = "SELECT     tblstockitemrate.rate AS tblstockitemrate_rate,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitemrate.custid AS tblstockitemrate_custid,     tblstockitemrate.stkid AS tblstockitemrate_stkid FROM     tblledger tblledger INNER JOIN tblstockitemrate tblstockitemrate ON tblledger.ledger_id = tblstockitemrate.custid      INNER JOIN tblstockitem tblstockitem ON tblstockitemrate.stkid = tblstockitem.si_id WHERE      tblstockitemrate.custid = " + customerID;
//            PreparedStatement psmt = conn.prepareStatement(queryOne);
//            ResultSet rs = psmt.executeQuery();
//            while (rs.next()) {
//                mapTOReturn.put(rs.getString("tblstockitem_si_name").toLowerCase(), rs.getString("tblstockitemrate_rate"));
//            }
//            rs.close();
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//        return mapTOReturn;
//    }
//
//    public static void DeleteStockItemRate(List<CustomerRateDTO> customerRateDTOList) throws SQLException {
//        Connection conn = null;
//        try {
//            if (customerRateDTOList != null && customerRateDTOList.size() > 0) {
//                String inStmt = "";
//                for (CustomerRateDTO custDTO : customerRateDTOList) {
//                    inStmt += custDTO.getStockItemID() + ",";
//                }
//                inStmt = inStmt.substring(0, inStmt.length() - 1);
//
//                conn = DatabaseConnection1.GetConnection();
//                conn.setAutoCommit(false);
//                Statement st1 = conn.createStatement();
//
//                String queryOne = "delete from tblstockitemrate where custid=" + customerRateDTOList.get(0).getCustomerID() + " and stkid in(" + inStmt + ")";
//                st1.executeUpdate(queryOne);
//                conn.commit();
//                conn.close();
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//            throw ex;
//        }
//    }
}

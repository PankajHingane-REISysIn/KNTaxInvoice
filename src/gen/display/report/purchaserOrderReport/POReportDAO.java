/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.purchaserOrderReport;

import gen.accountvoucher.purchaseorder.PurchaseOrderDTO;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author admin
 */
public class POReportDAO {

    public static List<PurchaseOrderDTO> getPurchaseOrderList(String fromdate, String todate) throws SQLException, Exception {

        List<PurchaseOrderDTO> PurchaseOrderDTOList = new ArrayList<PurchaseOrderDTO>();
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            String queryOne = "SELECT      tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id, tbltransactionotherdetails.trans_round_Off_amt AS tbltransactionotherdetails_trans_round_Off_amt,     tblinventorytransaction.trans_id AS tblinventorytransaction_trans_id,     tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type,     tblinventorytransaction.dat AS tblinventorytransaction_dat,     tblinventorytransactionitems.invtrans_id AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_finishType AS tblinventorytransactionitems_invtrans_finishType,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tblinventorytransactionitems.invtrans_making AS tblinventorytransactionitems_invtrans_making,     tblledger.ledger_id AS tblledger_ledger_id,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitem.si_id AS tblstockitem_si_id,     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionmain.trans_transport AS tbltransactionmain_trans_transport,     tbltransactionmain.trans_lessBillAmt AS tbltransactionmain_trans_lessBillAmt,     tbltransactionmain.trans_payment AS tbltransactionmain_trans_payment,     tbltransactionledger.IsAvaibleToUser AS tbltransactionledger_IsAvaibleToUser,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionmain.trans_narration AS tbltransactionledger_trans_narration,     tbltransactionledger.trans_index AS tbltransactionledger_trans_index,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,     tbltransactionledger.trans_id AS tbltransactionledger_trans_id,     tbltransactionvat.trans_id AS tbltransactionvat_trans_id,     tbltransactionvat.vat_rate AS tbltransactionvat_vat_rate,     tbltransactionvat.vat_amt AS tbltransactionvat_vat_amt,     tbltransactionvat.discount_rate AS tbltransactionvat_discount_rate,    tbltransactionvat.shipping AS tbltransactionvat_shipping,     tbltransactionvat.discount_amt AS tbltransactionvat_discount_amt,     tbltransactionotherdetails.trans_id AS tbltransactionotherdetails_trans_id,     tbltransactionotherdetails.trans_reference AS tbltransactionotherdetails_trans_reference,     tbltransactionotherdetails.trans_buyerOrderNo AS tbltransactionotherdetails_trans_buyerOrderNo,     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionotherdetails.trans_amt AS tbltransactionotherdetails_trans_amt,     tbltransactionotherdetails.trans_payment AS tbltransactionotherdetails_trans_payment,     tbltransactionotherdetails.trans_vehicleNo AS tbltransactionotherdetails_trans_vehicleNo,     tbltransactionotherdetails.trans_challanNo AS tbltransactionotherdetails_trans_challanNo,     tbltransactionotherdetails.trans_poNo AS tbltransactionotherdetails_trans_poNo,     tbltransactionotherdetails.trans_challanDate AS tbltransactionotherdetails_trans_challanDate,     tbltransactionotherdetails.trans_poDate AS tbltransactionotherdetails_trans_poDate,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo FROM      tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionvat tbltransactionvat ON tbltransactionmain.trans_id = tbltransactionvat.trans_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE  trans_typeIndex = " + Constants.PURCHASE_ORDER_TYPE_INDEX + "  and IsAvaibleToUser != 2 and tbltransactionmain.trans_date between '" + fromdate + "' and '" + todate + "' order by tbltransactionmain.trans_date asc";
            System.out.println("ee      " + queryOne);
            PreparedStatement psmt = con.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();

            Boolean isAdded = false;
            while (rs.next()) {
                PurchaseOrderDTO PurchaseOrderDTO = new PurchaseOrderDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
//                if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT_DEBIT_NIL && isAdded == false) {
                PurchaseOrderDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                PurchaseOrderDTO.setAmount(rs.getDouble("tbltransactionotherdetails_trans_amt"));
                PurchaseOrderDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                PurchaseOrderDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                PurchaseOrderDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                PurchaseOrderDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                PurchaseOrderDTO.setpurchaseOrder_Trans_ID(rs.getString("tbltransactionmain_trans_id"));
                PurchaseOrderDTO.setVatAmount(rs.getDouble("tbltransactionvat_vat_amt"));
                PurchaseOrderDTO.setVatRate(rs.getDouble("tbltransactionvat_vat_rate"));
                isAdded = true;
//                }

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

    public static List<PurchaseOrderDTO> getPurchaseOrderListByLedger(List<String> ledger_List, String fromdate, String todate) throws SQLException, Exception {
        String ledger_id = "";
        for (String ledger_id1 : ledger_List) {
            ledger_id = ledger_id1;
        }
        List<PurchaseOrderDTO> PurchaseOrderDTOList = new ArrayList<PurchaseOrderDTO>();
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            String queryOne = "SELECT      tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id, tbltransactionotherdetails.trans_round_Off_amt AS tbltransactionotherdetails_trans_round_Off_amt,     tblinventorytransaction.trans_id AS tblinventorytransaction_trans_id,     tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type,     tblinventorytransaction.dat AS tblinventorytransaction_dat,     tblinventorytransactionitems.invtrans_id AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_finishType AS tblinventorytransactionitems_invtrans_finishType,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tblinventorytransactionitems.invtrans_making AS tblinventorytransactionitems_invtrans_making,     tblledger.ledger_id AS tblledger_ledger_id,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitem.si_id AS tblstockitem_si_id,     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionmain.trans_transport AS tbltransactionmain_trans_transport,     tbltransactionmain.trans_lessBillAmt AS tbltransactionmain_trans_lessBillAmt,     tbltransactionmain.trans_payment AS tbltransactionmain_trans_payment,     tbltransactionledger.IsAvaibleToUser AS tbltransactionledger_IsAvaibleToUser,     tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionmain.trans_narration AS tbltransactionledger_trans_narration,     tbltransactionledger.trans_index AS tbltransactionledger_trans_index,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,     tbltransactionledger.trans_id AS tbltransactionledger_trans_id,     tbltransactionvat.trans_id AS tbltransactionvat_trans_id,     tbltransactionvat.vat_rate AS tbltransactionvat_vat_rate,     tbltransactionvat.vat_amt AS tbltransactionvat_vat_amt,     tbltransactionvat.discount_rate AS tbltransactionvat_discount_rate,    tbltransactionvat.shipping AS tbltransactionvat_shipping,     tbltransactionvat.discount_amt AS tbltransactionvat_discount_amt,     tbltransactionotherdetails.trans_id AS tbltransactionotherdetails_trans_id,     tbltransactionotherdetails.trans_reference AS tbltransactionotherdetails_trans_reference,     tbltransactionotherdetails.trans_buyerOrderNo AS tbltransactionotherdetails_trans_buyerOrderNo,     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,     tbltransactionotherdetails.trans_amt AS tbltransactionotherdetails_trans_amt,     tbltransactionotherdetails.trans_payment AS tbltransactionotherdetails_trans_payment,     tbltransactionotherdetails.trans_vehicleNo AS tbltransactionotherdetails_trans_vehicleNo,     tbltransactionotherdetails.trans_challanNo AS tbltransactionotherdetails_trans_challanNo,     tbltransactionotherdetails.trans_poNo AS tbltransactionotherdetails_trans_poNo,     tbltransactionotherdetails.trans_challanDate AS tbltransactionotherdetails_trans_challanDate,     tbltransactionotherdetails.trans_poDate AS tbltransactionotherdetails_trans_poDate,     tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo FROM      tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id     INNER JOIN tbltransactionvat tbltransactionvat ON tbltransactionmain.trans_id = tbltransactionvat.trans_id     INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE  tbltransactionledger.trans_ledgerId = " + ledger_id + " and trans_typeIndex = " + Constants.PURCHASE_ORDER_TYPE_INDEX + "  and IsAvaibleToUser != 2 and tbltransactionmain.trans_date between '" + fromdate + "' and '" + todate + "' order by tbltransactionmain.trans_date asc";
            System.out.println("ee      " + queryOne);
            PreparedStatement psmt = con.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();

            Boolean isAdded = false;
            while (rs.next()) {
                PurchaseOrderDTO PurchaseOrderDTO = new PurchaseOrderDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
//                if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT_DEBIT_NIL && isAdded == false) {
                PurchaseOrderDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                PurchaseOrderDTO.setAmount(rs.getDouble("tbltransactionotherdetails_trans_amt"));
                PurchaseOrderDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                PurchaseOrderDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                PurchaseOrderDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                PurchaseOrderDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                PurchaseOrderDTO.setpurchaseOrder_Trans_ID(rs.getString("tbltransactionmain_trans_id"));
                PurchaseOrderDTO.setVatAmount(rs.getDouble("tbltransactionvat_vat_amt"));
                PurchaseOrderDTO.setVatRate(rs.getDouble("tbltransactionvat_vat_rate"));
                isAdded = true;
//                }

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
}

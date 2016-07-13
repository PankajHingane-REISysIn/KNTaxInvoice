/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.StockItem;

import gen.database.connection.DatabaseConnection1;
import gen.display.report.Ledger.TransactionsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc5
 */
public class StockItemReportDAO {

    public static List<StockItemReportDTO> getStockItemReport(String fromDateStr, String toDateStr, String stockItem_id) throws Exception {

        StockItemReportDTO stockItemReportFormDTO = new StockItemReportDTO();
        List<TransactionsDTO> transactionsDTOList = new ArrayList<TransactionsDTO>();
        List<StockItemReportDTO> ledgerReportFormDTOList = new ArrayList<StockItemReportDTO>();

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String query = "";
            String cr = "";
            Double d = 0D;

            query = "select sum(invtrans_qty) as totalCred from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex = " + gen.dto.Constants.PURCHASE_TYPE_INDEX + "))";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, stockItem_id);
            ResultSet rs1 = prmt.executeQuery();
            while (rs1.next()) {
                cr = cr + rs1.getDouble("totalCred");
            }
            rs1.close();
            prmt.close();


            // code to avoid stockitem which are present in challan and genarated Invoice from same challan
            // in this we want only sale transaction in stock_item report not challan.
//	    query = "select * from tbltranscactionchange";
//	    PreparedStatement prmt5 = conn.prepareStatement(query);
//	    ResultSet rs5 = prmt5.executeQuery();
            List<String> chalan_Trans_idList = new ArrayList<String>();
            query = "select * from tbltranscactionchange where voucher_type = ?";
            PreparedStatement prmt5 = conn.prepareStatement(query);
            prmt5.setString(1, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
            ResultSet rs5 = prmt5.executeQuery();
            while (rs5.next()) {
                System.out.println("Chalan ID ---------- " + rs5.getString("chalan_trans_id"));
                chalan_Trans_idList.add(rs5.getString("chalan_trans_id"));
            }
            rs5.close();
            prmt5.close();

            String chalan_Trans_idListPass = "";
            for (String stkGroup : chalan_Trans_idList) {
                chalan_Trans_idListPass = stkGroup + "," + chalan_Trans_idListPass;
            }

            if (!chalan_Trans_idListPass.isEmpty()) {
                chalan_Trans_idListPass = chalan_Trans_idListPass.substring(0, chalan_Trans_idListPass.length() - 1);
            }
//	    // remove Above chalan_ID
//	    query = "select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.CHALAN_TYPE_INDEX + ")  and trans_id  in (select trans_id from tbltransactionmain where trans_id Not in (32))";
//	    prmt5 = conn.prepareStatement(query);
//	    rs5 = prmt5.executeQuery();
//	    while (rs5.next()) {
//		System.out.println("Chalan ID 5555555555555555 ---------- " + rs5.getString("trans_id"));
//		chalan_Trans_idList.add(rs5.getString("trans_id"));
//	    }
//	    rs5.close();
//	    prmt5.close();

//	    query = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.SALE_TYPE_INDEX + "," + gen.dto.Constants.CHALAN_TYPE_INDEX + ")))";
            query = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.SALE_TYPE_INDEX + ")))";
            PreparedStatement prmt1 = conn.prepareStatement(query);
            prmt1.setString(1, stockItem_id);
            ResultSet rs2 = prmt1.executeQuery();
            while (rs2.next()) {
                d = d + rs2.getDouble("totalDeb");
            }
            rs2.close();
            prmt1.close();


//	    query = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.CHALAN_TYPE_INDEX + ")))";
            if (chalan_Trans_idListPass.isEmpty()) {
                query = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.CHALAN_TYPE_INDEX + ")))";
            } else {
                query = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId = ? and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < '" + fromDateStr + "' and trans_typeIndex in (" + gen.dto.Constants.CHALAN_TYPE_INDEX + ")  and trans_id  in (select trans_id from tbltransactionmain where trans_id Not in (" + chalan_Trans_idListPass + "))))";
            }
            System.out.println("query ---------- " + query);
            PreparedStatement prmt6 = conn.prepareStatement(query);
            prmt6.setString(1, stockItem_id);
            ResultSet rs6 = prmt6.executeQuery();
            while (rs6.next()) {
                d = d + rs6.getDouble("totalDeb");
            }
            System.out.println("DDDDDDDd        " + d);
            rs6.close();
            prmt6.close();

            // Calculate Opening Balance 
            Double opening_Balance = 0D;
            query = "select si_openingBalance from tblstockitem where si_id = ?";
            PreparedStatement prmt3 = conn.prepareStatement(query);
            prmt3.setString(1, stockItem_id);
            ResultSet rs3 = prmt3.executeQuery();
            while (rs3.next()) {
                opening_Balance = rs3.getDouble("si_openingBalance");
                opening_Balance = opening_Balance + Double.parseDouble(cr) - d;
                if (opening_Balance > 0) {
                    stockItemReportFormDTO.setOpening_balance_type("Credit");
                } else {
                    stockItemReportFormDTO.setOpening_balance_type("Debit");
                }
                stockItemReportFormDTO.setOpening_balance(Math.abs(opening_Balance));
            }
            rs3.close();
            prmt3.close();

            if (!chalan_Trans_idListPass.isEmpty()) {
                query = "SELECT tbltransactionmain.trans_id AS tbltransactionmain_trans_id ,tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo, tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex, tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id,tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type, tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId FROM tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id Where tbltransactionmain.trans_date Between ? and ? and tblinventorytransactionitems.invtrans_itemId = ? and tbltransactionmain.trans_id Not in (" + chalan_Trans_idListPass + ")  and tblinventorytransactionitems.invtrans_itemId = " + stockItem_id + " and tbltransactionmain.trans_typeIndex Not in( " + gen.dto.Constants.CONTRA_TYPE_INDEX + " , " + gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX + " ) order by tbltransactionmain.trans_date ";
            } else {
                query = "SELECT tbltransactionmain.trans_id AS tbltransactionmain_trans_id ,tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo, tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex, tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id,tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type, tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId FROM tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id Where tbltransactionmain.trans_date Between ? and ? and tblinventorytransactionitems.invtrans_itemId = ? and tbltransactionmain.trans_typeIndex Not in( " + gen.dto.Constants.CONTRA_TYPE_INDEX + " , " + gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX + " ) order by tbltransactionmain.trans_date";
            }
            System.out.println("*************        " + query);
            System.out.println("*************        " + fromDateStr);
            System.out.println("*************        " + toDateStr);
            System.out.println("*************        " + stockItem_id);
            PreparedStatement prmt4 = conn.prepareStatement(query);
            prmt4.setString(1, fromDateStr);
            prmt4.setString(2, toDateStr);
            prmt4.setString(3, stockItem_id);
            ResultSet rs4 = prmt4.executeQuery();
            while (rs4.next()) {
                TransactionsDTO transactionsDTO = new TransactionsDTO();
                transactionsDTO.setParticulars("" + rs4.getLong("tblinventorytransactionitems_invtrans_itemId"));

                transactionsDTO.setDate(gen.dto.Constants.DATE_FORMATER.format(rs4.getDate("tbltransactionmain_trans_date")));

                //VCH Type
                if (rs4.getInt("tbltransactionmain_trans_typeIndex") == 1) {
                    transactionsDTO.setVchtype("Sales");
                } else if (rs4.getInt("tbltransactionmain_trans_typeIndex") == 2) {
                    transactionsDTO.setVchtype("Purchase");
                } else if (rs4.getInt("tbltransactionmain_trans_typeIndex") == 7) {
                    transactionsDTO.setVchtype("Chalan");
                }else if (rs4.getInt("tbltransactionmain_trans_typeIndex") == 12) {
                    transactionsDTO.setVchtype("GRNote");
                }

                //VCH No
                transactionsDTO.setVchno("" + rs4.getLong("tbltransactionmain_trans_receiptNo"));

                //Creditted-Debitted Amount
                if (rs4.getInt("tblinventorytransaction_invtrans_type") == gen.dto.Constants.PURCHASE_TYPE_INDEX || rs4.getInt("tblinventorytransaction_invtrans_type") == gen.dto.Constants.GRNOTE_TYPE_INDEX) {
                    transactionsDTO.setCredit("" + rs4.getDouble("tblinventorytransactionitems_invtrans_qty"));
                } else {
                    transactionsDTO.setDebit("" + rs4.getDouble("tblinventorytransactionitems_invtrans_qty"));
                }

                transactionsDTOList.add(transactionsDTO);
            }
            rs4.close();
            prmt4.close();
            Double total_credit = 0D;
            Double total_debit = 0D;

            for (TransactionsDTO transactionsDTO : transactionsDTOList) {
                if (!transactionsDTO.getCredit().equals("")) {
                    total_credit = total_credit + Double.parseDouble(transactionsDTO.getCredit());
                }
                if (!transactionsDTO.getDebit().equals("")) {
                    total_debit = total_debit + Double.parseDouble(transactionsDTO.getDebit());
                }

            }
            stockItemReportFormDTO.setTotal_credit(total_credit);
            stockItemReportFormDTO.setTotal_debit(total_debit);

            Double cl = 0D;
            if (stockItemReportFormDTO.getOpening_balance_type().equalsIgnoreCase("Credit")) {
                cl = stockItemReportFormDTO.getOpening_balance() + stockItemReportFormDTO.getTotal_credit() - stockItemReportFormDTO.getTotal_debit();
                if (cl < 0.0) {
                    stockItemReportFormDTO.setClosing_balance_type("Debit");
                } else {
                    stockItemReportFormDTO.setClosing_balance_type("Credit");
                }
            } else {
                cl = stockItemReportFormDTO.getOpening_balance() + stockItemReportFormDTO.getTotal_debit() - stockItemReportFormDTO.getTotal_credit();
                if (cl > 0.0) {
                    stockItemReportFormDTO.setClosing_balance_type("Debit");
                } else {
                    stockItemReportFormDTO.setClosing_balance_type("Credit");
                }
            }
            stockItemReportFormDTO.setClosing_balance(cl);
            stockItemReportFormDTO.setTransactionsDTOList(transactionsDTOList);
            ledgerReportFormDTOList.add(stockItemReportFormDTO);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
        return ledgerReportFormDTOList;
    }
}

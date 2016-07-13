/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.sale;

import gen.ImpExp.ImpExpUtil;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.CustomerRateDTO;
import gen.dto.StockItemTransactionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class SaleDAO {

    public static void insertSaleVoucher(List<SaleDTO> saleFormDTOList) throws SQLException, ParseException, Exception {

        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);


            //create vat ledger if not present and insert into database
            Map<String, String> LedgerNameToIDMapping = new HashMap<String, String>();
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }
            ////////////////////////////////////////////////////////////////////////////////////     
            //create excise duty ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create ed_Cess ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create vat ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create cst ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     

            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.ROUND_OFF_LEDGER);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.ROUND_OFF_LEDGER);
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "64"); // Indirect expense gouop Under
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.ROUND_OFF_LEDGER, rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }


            String queryOne = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_transport,trans_lessBillAmt) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (SaleDTO saleDTO : saleFormDTOList) {
                ps.setInt(1, (saleDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.SALE_TYPE_INDEX);
                ps.setString(4, saleDTO.getNote().toString());
                ps.setDouble(5, Double.parseDouble(saleDTO.getFinalAmount().toString()));
                ps.setDouble(6, Double.parseDouble(saleDTO.getShipping().toString()));
                ps.setDouble(7, 0d);
                ps.addBatch();
            }
            if (!saleFormDTOList.isEmpty()) {
                ps.executeBatch();
            }
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt,tax_type) values(?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment,trans_round_Off_amt,trans_poNo,trans_poDate,trans_oc_No,trans_oc_Date,trans_date_Iss,trans_Time_Iss,trans_date_Rem,trans_Time_Rem) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);

            int check_for_Chalan_to_Sale = 0;
//            String queryFour = "insert into tbltranscactionchange(sale_trans_id,chalan_trans_id) values(?,?)";
            String queryFour = "insert into tbltranscactionchange(sale_trans_id,chalan_trans_id,voucher_type) values(?,?,?)";
            PreparedStatement ps8 = conn.prepareStatement(queryFour);

            for (SaleDTO saleDTO : saleFormDTOList) {
                Integer fk = idList.get(i);
                ps1.setInt(1, fk);
                ps1.setDouble(2, saleDTO.getVatRate());
                ps1.setDouble(3, saleDTO.getVatAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_VAT_TYPE);

                ps1.addBatch();

                // added Excise Duty TAX
                ps1.setInt(1, fk);
                System.out.println("getExciseDutyRate  " + saleDTO.getExciseDutyRate());
                System.out.println("getExciseDutyAmount  " + saleDTO.getExciseDutyAmount());
                ps1.setDouble(2, saleDTO.getExciseDutyRate());
                ps1.setDouble(3, saleDTO.getExciseDutyAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_EXCISE_TYPE);
                ps1.addBatch();

                // added ED_CESS TAX Duty 
                ps1.setInt(1, fk);
                System.out.println("getEdCessRate  " + saleDTO.getEdCessRate());
                System.out.println("getEdCessAmount  " + saleDTO.getEdCessAmount());
                ps1.setDouble(2, saleDTO.getEdCessRate());
                ps1.setDouble(3, saleDTO.getEdCessAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_EDCESS_TYPE);
                ps1.addBatch();

                // added H_ED_CESS TAX Duty 
                ps1.setInt(1, fk);
                System.out.println("gethEdCessRate  " + saleDTO.gethEdCessRate());
                System.out.println("getHedCessAmount  " + saleDTO.getHedCessAmount());
                ps1.setDouble(2, saleDTO.gethEdCessRate());
                ps1.setDouble(3, saleDTO.getHedCessAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_H_EDCESS_TYPE);
                ps1.addBatch();

                ps1.setInt(1, fk);
                System.out.println("gethEdCessRate  " + saleDTO.gethEdCessRate());
                System.out.println("getHedCessAmount  " + saleDTO.getHedCessAmount());
                ps1.setDouble(2, saleDTO.getCstRate());
                ps1.setDouble(3, saleDTO.getCstAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_CST_TYPE);
                ps1.addBatch();


                ps2.setInt(1, fk);
                ps2.setString(2, saleDTO.getDispatchDocThrough());
                ps2.setString(3, saleDTO.getDispatchDocNo());
                ps2.setDouble(4, saleDTO.getAmount());
                ps2.setString(5, "" + 0);
                ps2.setString(6, "" + 0);
                ps2.setDouble(7, saleDTO.getRoundOffAmount());
                ps2.setString(8, saleDTO.getPoNo());
                if (!saleDTO.getPoDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(9, dateFormat(saleDTO.getPoDate()));
                } else {
                    ps2.setString(9, null);

                }
                System.out.println("getOcNo  " + saleDTO.getOcNo());
                System.out.println("getOcDate  " + saleDTO.getOcDate());
                ps2.setString(10, saleDTO.getOcNo());
                if (!saleDTO.getOcDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(11, dateFormat(saleDTO.getOcDate()));
                } else {
                    ps2.setString(11, "");

                }

                System.out.println("getDateIssDate  " + saleDTO.getDateIssDate());
                System.out.println("getTimeIss  " + saleDTO.getTimeIss());
                if (!saleDTO.getDateIssDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(12, dateFormat(saleDTO.getDateIssDate()));
                } else {
                    ps2.setString(12, "");

                }
                ps2.setString(13, saleDTO.getTimeIss());

                System.out.println("getDateRemDate  " + saleDTO.getDateRemDate());
                System.out.println("getTimeRem  " + saleDTO.getTimeRem());
                if (!saleDTO.getDateRemDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(14, dateFormat(saleDTO.getDateRemDate()));
                } else {
                    ps2.setString(14, "");

                }
                ps2.setString(15, saleDTO.getTimeRem());
                ps2.addBatch();

                if (saleDTO.getChallan_trans_ID() != null && !saleDTO.getChallan_trans_ID().trim().equalsIgnoreCase("") && !saleDTO.getChallan_trans_ID().isEmpty() && Double.parseDouble(saleDTO.getChallan_trans_ID()) != 0D) {
                    ps8.setInt(1, fk);
                    ps8.setString(2, saleDTO.getChallan_trans_ID());
                    ps8.setInt(3, gen.dto.Constants.CHALAN_TYPE_INDEX);
                    ps8.addBatch();
                    check_for_Chalan_to_Sale = 1;
                }

                i++;
            }
            if (!saleFormDTOList.isEmpty()) {
                ps1.executeBatch();
                ps2.executeBatch();
            }
            if (check_for_Chalan_to_Sale == 1) {
                ps8.executeBatch();
            }
            check_for_Chalan_to_Sale = 0;

            String queryFive = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration, IsAvaibleToUser) values(?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFive);
            i = 0;
            for (SaleDTO saleDTO : saleFormDTOList) {
                Integer fk = idList.get(i);
                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(saleDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT);      //1 for credit
                ps6.setInt(4, 0);      //index of ledger on form
                ps6.setDouble(5, saleDTO.getFinalAmount());
                ps6.setString(6, saleDTO.getNote().trim());
                ps6.setInt(7, 1); //IsAvailable to User
                ps6.addBatch();

                ps6.setInt(1, fk);
                ps6.setInt(2, Integer.parseInt(saleDTO.getSaleLedger().toString()));
                ps6.setInt(3, Constants.DEBIT);      //1 for credit
                ps6.setInt(4, 0);      //index of ledger on form
                ps6.setDouble(5, saleDTO.getFinalAmount());
                System.out.println("Insert Note ----------- " + saleDTO.getNote());
                System.out.println("Insert Note ----------- Sale Ledger -------" + saleDTO.getSaleLedger().toString());
                ps6.setString(6, saleDTO.getNote().trim());
                ps6.setInt(7, 1); //IsAvailable to User
                ps6.addBatch();

                //if vat is non zero then insert into tbltransaction ledger
                if (saleDTO.getVatRate() != 0D && saleDTO.getVatAmount() != 0D) {
                    System.out.println("Insert into ----------- Six");
                    ps6.setInt(1, fk);
                    System.out.println("===>>" + LedgerNameToIDMapping.keySet());
                    System.out.println("===>>" + gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                    ps6.setInt(2, Integer.parseInt(LedgerNameToIDMapping.get(gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%")));
                    ps6.setInt(3, Constants.DEBIT);      //1 for credit
                    ps6.setInt(4, 0);      //index of ledger on form
                    ps6.setDouble(5, saleDTO.getVatAmount());
                    ps6.setString(6, saleDTO.getNote().trim());
                    ps6.setInt(7, 2); //not IsAvailable to User
                    ps6.addBatch();
                }

                //if round off is non zero then insert into tbltransaction ledger
                if (saleDTO.getRoundOffAmount() != null && saleDTO.getRoundOffAmount() != 0D) {
                    ps6.setInt(1, fk);
                    ps6.setInt(2, Integer.parseInt(LedgerNameToIDMapping.get(gen.dto.Constants.ROUND_OFF_LEDGER)));
                    ps6.setInt(3, Constants.DEBIT);      //1 for credit
                    ps6.setInt(4, 0);      //index of ledger on form
                    ps6.setDouble(5, saleDTO.getRoundOffAmount());
                    ps6.setString(6, saleDTO.getNote().trim());
                    ps6.setInt(7, 2); //not IsAvailable to User
                    ps6.addBatch();
                }
                i++;

            }
            if (!saleFormDTOList.isEmpty()) {
                ps6.executeBatch();
            }
            ps6.close();

            i = 0;
            String querySix = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
            PreparedStatement ps3 = conn.prepareStatement(querySix, Statement.RETURN_GENERATED_KEYS);
            for (SaleDTO saleDTO : saleFormDTOList) {
                Integer fk = idList.get(i);
                ps3.setInt(1, fk);
                ps3.setString(2, "" + 1);
                ps3.addBatch();
                i++;
            }
            if (!saleFormDTOList.isEmpty()) {
                ps3.executeBatch();
            }


            ResultSet rs1 = ps3.getGeneratedKeys();
            List<Integer> idList1 = new ArrayList<Integer>();
            while (rs1.next()) {
                idList1.add(rs1.getInt(1));
            }
            ps3.close();
            i = 0;
            String querySeven = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness,invtrans_stk_under,invtrans_nos,invtrans_package,invtrans_color_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySeven);
            for (SaleDTO saleDTO : saleFormDTOList) {
                Integer fk = idList1.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
                    ps4.setDouble(5, Double.parseDouble(stockItemTransactionDTO.getAmount().toString()));
                    ps4.setDouble(6, 0d);
                    ps4.setInt(7, k);
                    ps4.setDouble(8, 0.0);
//                    ps4.setDouble(9, 0d);
//                    ps4.setDouble(10, 0d);
//                    ps4.setDouble(11, 0d);
                    ps4.setDouble(9, Double.parseDouble(stockItemTransactionDTO.getLength().toString()));
                    ps4.setDouble(10, Double.parseDouble(stockItemTransactionDTO.getWidth().toString()));
                    ps4.setDouble(11, Double.parseDouble(stockItemTransactionDTO.getThkness().toString()));
                    System.out.println("   UOM         ----------" + stockItemTransactionDTO.getUser_unit_of_symbol());
                    System.out.println("  No     ----------" + stockItemTransactionDTO.getNo());
                    System.out.println("  Package     ----------" + stockItemTransactionDTO.getStkpackage());
                    System.out.println(" Color       ----------" + stockItemTransactionDTO.getColor());
                    ps4.setString(12, (stockItemTransactionDTO.getUser_unit_of_symbol() == null || stockItemTransactionDTO.getUser_unit_of_symbol().isEmpty() ) ? null : stockItemTransactionDTO.getUser_unit_of_symbol());
                    ps4.setDouble(13, stockItemTransactionDTO.getNo());
                    ps4.setString(14, stockItemTransactionDTO.getStkpackage());
                    ps4.setString(15, stockItemTransactionDTO.getColor());

                    ps4.addBatch();
                    k++;
                }
                i++;
            }
            if (!saleFormDTOList.isEmpty()) {
                ps4.executeBatch();
            }
            ps4.close();
            conn.commit();
            conn.close();

            List<CustomerRateDTO> custRateDTOList = new ArrayList<CustomerRateDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : saleFormDTOList.get(0).getStockItemTreansactioDTOList()) {
                CustomerRateDTO custRateDTO = new CustomerRateDTO();
                custRateDTO.setCustomerID(Integer.parseInt(saleFormDTOList.get(0).getCashLedger()));
                custRateDTO.setStockItemID(Integer.parseInt(stockItemTransactionDTO.getName()));
                custRateDTO.setRate(stockItemTransactionDTO.getRate());
                custRateDTOList.add(custRateDTO);
            }
            if (custRateDTOList.size() > 0) {
                DeleteStockItemRate(custRateDTOList);
                insertStockItemRate(custRateDTOList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static List<SaleDTO> getSales(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        return getSales(receiptIDSet, calling_Class, "VoucherId");
    }
    public static List<SaleDTO> getSales(Set<String> receiptIDSet, String calling_Class, String type) throws SQLException, Exception {
        return getSales(receiptIDSet, calling_Class, null, type);
    }
    public static List<SaleDTO> getSales(Set<String> receiptIDSet, String calling_Class, String date, String type) throws SQLException, Exception {
        if (receiptIDSet == null || receiptIDSet.isEmpty()) {
            return null;
        }
        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        Connection con = null;
        try {
            con = DatabaseConnection1.GetConnection();
            con.setAutoCommit(false);
            String dateStrForMain = "";
            if( date!= null && !date.isEmpty() && type.equalsIgnoreCase("VoucherId") ) {
                dateStrForMain = " and trans_date = '"+ date + "'";
            }
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT      tblinventorytransaction.invtrans_id AS tblinventorytransaction_invtrans_id,     tblinventorytransaction.trans_id AS tblinventorytransaction_trans_id,     tblinventorytransaction.invtrans_type AS tblinventorytransaction_invtrans_type,     tblinventorytransaction.dat AS tblinventorytransaction_dat,     tblinventorytransactionitems.invtrans_id AS tblinventorytransactionitems_invtrans_id,     tblinventorytransactionitems.invtrans_qty AS tblinventorytransactionitems_invtrans_qty,     tblinventorytransactionitems.invtrans_rate AS tblinventorytransactionitems_invtrans_rate,     tblinventorytransactionitems.invtrans_amount AS tblinventorytransactionitems_invtrans_amount,     tblinventorytransactionitems.invtrans_totalSize AS tblinventorytransactionitems_invtrans_totalSize,     tblinventorytransactionitems.invtrans_index AS tblinventorytransactionitems_invtrans_index,     tblinventorytransactionitems.invtrans_itemId AS tblinventorytransactionitems_invtrans_itemId,     tblinventorytransactionitems.invtrans_finishType AS tblinventorytransactionitems_invtrans_finishType,     tblinventorytransactionitems.invtrans_length AS tblinventorytransactionitems_invtrans_length,     tblinventorytransactionitems.invtrans_width AS tblinventorytransactionitems_invtrans_width , tblinventorytransactionitems.invtrans_package AS tblinventorytransactionitems_invtrans_package, tblinventorytransactionitems.invtrans_color_id AS tblinventorytransactionitems_invtrans_color, tblinventorytransactionitems.invtrans_nos AS tblinventorytransactionitems_invtrans_nos, tblinventorytransactionitems.invtrans_stk_under AS tblinventorytransactionitems_invtrans_stk_under,     tblinventorytransactionitems.invtrans_thickness AS tblinventorytransactionitems_invtrans_thickness,     tblinventorytransactionitems.invtrans_making AS tblinventorytransactionitems_invtrans_making,     tblledger.ledger_id AS tblledger_ledger_id,     tblledger.ledger_name AS tblledger_ledger_name,  tblledger.ledger_address As tblledger_ledger_address, tblledger.ledger_contactNo As tblledger_ledger_contactNo,  tblledger.ledger_ECSNumber As tblledger_ledger_ECSNumber, tblledger.ledger_CVATNumber As tblledger_ledger_CVATNumber,  tblledger.ledger_CSTNumber As tblledger_ledger_CSTNumber ,  tblstockitem.si_name AS tblstockitem_si_name,     tblstockitem.si_id AS tblstockitem_si_id,     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,     tbltransactionmain.trans_date AS tbltransactionmain_trans_date,     tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex,     tbltransactionmain.trans_narration AS tbltransactionmain_trans_narration,     tbltransactionmain.trans_grandtotal AS tbltransactionmain_trans_grandtotal,     tbltransactionmain.trans_transport AS tbltransactionmain_trans_transport,     tbltransactionmain.trans_lessBillAmt AS tbltransactionmain_trans_lessBillAmt,     tbltransactionmain.trans_payment AS tbltransactionmain_trans_payment,    tbltransactionledger.trans_checkNo AS tbltransactionledger_trans_checkNo,     tbltransactionledger.trans_amt AS tbltransactionledger_trans_amt,     tbltransactionmain.trans_narration AS tbltransactionledger_trans_narration,     tbltransactionledger.trans_index AS tbltransactionledger_trans_index,     tbltransactionledger.trans_type AS tbltransactionledger_trans_type,     tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,     tbltransactionledger.trans_id AS tbltransactionledger_trans_id,    tbltransactionotherdetails.trans_id AS tbltransactionotherdetails_trans_id,     tbltransactionotherdetails.trans_reference AS tbltransactionotherdetails_trans_reference,     tbltransactionotherdetails.trans_buyerOrderNo AS tbltransactionotherdetails_trans_buyerOrderNo,     tbltransactionotherdetails.trans_dispatchDocThrough AS tbltransactionotherdetails_trans_dispatchDocThrough,     tbltransactionotherdetails.trans_dispatchDocNo AS tbltransactionotherdetails_trans_dispatchDocNo,  tbltransactionotherdetails.trans_amt AS tbltransactionotherdetails_trans_amt,     tbltransactionotherdetails.trans_payment AS tbltransactionotherdetails_trans_payment,     tbltransactionotherdetails.trans_vehicleNo AS tbltransactionotherdetails_trans_vehicleNo,     tbltransactionotherdetails.trans_challanNo AS tbltransactionotherdetails_trans_challanNo,     tbltransactionotherdetails.trans_poNo AS tbltransactionotherdetails_trans_poNo,     tbltransactionotherdetails.trans_challanDate AS tbltransactionotherdetails_trans_challanDate,     tbltransactionotherdetails.trans_poDate AS tbltransactionotherdetails_trans_poDate,  tbltransactionotherdetails.trans_oc_No AS tbltransactionotherdetails_trans_oc_No , tbltransactionotherdetails.trans_oc_Date AS tbltransactionotherdetails_trans_oc_Date, tbltransactionotherdetails.trans_date_Iss AS tbltransactionotherdetails_trans_date_Iss , tbltransactionotherdetails.trans_Time_Iss AS tbltransactionotherdetails_trans_Time_Iss,tbltransactionotherdetails.trans_date_Rem AS tbltransactionotherdetails_trans_date_Rem , tbltransactionotherdetails.trans_Time_Rem AS tbltransactionotherdetails_trans_Time_Rem, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo FROM      tblinventorytransaction tblinventorytransaction INNER JOIN tblinventorytransactionitems tblinventorytransactionitems ON tblinventorytransaction.invtrans_id = tblinventorytransactionitems.invtrans_id     INNER JOIN tblstockitem tblstockitem ON tblinventorytransactionitems.invtrans_itemId = tblstockitem.si_id     INNER JOIN tbltransactionmain tbltransactionmain ON tblinventorytransaction.trans_id = tbltransactionmain.trans_id     INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id     INNER JOIN tbltransactionotherdetails tbltransactionotherdetails ON tbltransactionmain.trans_id = tbltransactionotherdetails.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id WHERE trans_typeIndex = " + Constants.SALE_TYPE_INDEX + " and "+ (type.equalsIgnoreCase("VoucherId") ? ("     trans_receiptNo = " + receiptID) : ("    trans_id = " + receiptID)) +  " and IsAvaibleToUser != 2 "+ dateStrForMain + " order by tbltransactionmain.trans_date asc";
                PreparedStatement psmt = con.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                SaleDTO saleDTO = new SaleDTO();
                List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                Boolean isAdded = false;
                while (rs.next()) {
                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.CREDIT && isAdded == false) {
                        gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
                        ledgerDTO.setLedger_Address(rs.getString("tblledger_ledger_address"));
                        ledgerDTO.setLedger_ContactNo(rs.getString("tblledger_ledger_contactNo"));
                        ledgerDTO.setLedger_ECSNumber(rs.getString("tblledger_ledger_ECSNumber"));
                        ledgerDTO.setLedger_CVATNumber(rs.getString("tblledger_ledger_CVATNumber"));
                        ledgerDTO.setLedger_SaleTaxNo(rs.getString("tblledger_ledger_CSTNumber"));
                        saleDTO.setLedgerDTO(ledgerDTO);
                        saleDTO.setCashLedger(rs.getString("tblledger_ledger_name"));
                        saleDTO.setAmount(rs.getDouble("tbltransactionotherdetails_trans_amt"));
                        saleDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                        saleDTO.setDispatchDocNo(rs.getString("tbltransactionotherdetails_trans_dispatchDocNo"));
                        saleDTO.setDispatchDocThrough(rs.getString("tbltransactionotherdetails_trans_dispatchDocThrough"));
                        saleDTO.setFinalAmount(rs.getDouble("tbltransactionmain_trans_grandtotal"));
                        System.out.println("setFinalAmount-->>>" + saleDTO.getFinalAmount());
                        saleDTO.setLessBillAmount(rs.getDouble("tbltransactionmain_trans_lessBillAmt"));
                        saleDTO.setNote(rs.getString("tbltransactionledger_trans_narration"));
                        saleDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                        saleDTO.setTrans_ID(rs.getString("tbltransactionmain_trans_id"));
                        saleDTO.setShipping(rs.getDouble("tbltransactionmain_trans_transport"));

                        // newly added
                        saleDTO.setPoNo(rs.getString("tbltransactionotherdetails_trans_poNo"));
                        saleDTO.setPoDate(rs.getString("tbltransactionotherdetails_trans_poDate"));

                        saleDTO.setOcNo(rs.getString("tbltransactionotherdetails_trans_oc_No"));
                        saleDTO.setOcDate(rs.getString("tbltransactionotherdetails_trans_oc_Date"));

                        saleDTO.setDateIssDate(rs.getString("tbltransactionotherdetails_trans_date_Iss"));
                        saleDTO.setTimeIss(rs.getString("tbltransactionotherdetails_trans_Time_Iss"));

                        saleDTO.setDateRemDate(rs.getString("tbltransactionotherdetails_trans_date_Rem"));
                        saleDTO.setTimeRem(rs.getString("tbltransactionotherdetails_trans_Time_Rem"));

                        isAdded = true;
                    }



                    if (rs.getInt("tbltransactionledger_trans_type") == Constants.DEBIT) {
                        saleDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));
                        System.out.println("----------------------------- " + rs.getString("tblledger_ledger_name"));
                        StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                        stockItemTransactionDTO.setAmount(rs.getDouble("tblinventorytransactionitems_invtrans_amount"));
                        stockItemTransactionDTO.setLength(rs.getDouble("tblinventorytransactionitems_invtrans_length"));
                        stockItemTransactionDTO.setName(rs.getString("tblstockitem_si_name"));
                        stockItemTransactionDTO.setRate(rs.getDouble("tblinventorytransactionitems_invtrans_rate"));
                        stockItemTransactionDTO.setQuantity(rs.getDouble("tblinventorytransactionitems_invtrans_qty"));
                        stockItemTransactionDTO.setSqFeet(rs.getDouble("tblinventorytransactionitems_invtrans_totalSize"));
                        stockItemTransactionDTO.setThkness(rs.getDouble("tblinventorytransactionitems_invtrans_thickness"));
                        stockItemTransactionDTO.setWidth(rs.getDouble("tblinventorytransactionitems_invtrans_width"));

                        // update on 15-04-2014
                        System.out.println("Geeting tblinventorytransactionitems_invtrans_rate ---------------------" + rs.getDouble("tblinventorytransactionitems_invtrans_rate"));
                        System.out.println("Geeting SizeA ---------------------" + rs.getDouble("tblinventorytransactionitems_invtrans_width"));
                        System.out.println("Geeting thickness ---------------------" + rs.getString("tblinventorytransactionitems_invtrans_length"));
                        System.out.println("Geeting SizeB ---------------------" + rs.getString("tblinventorytransactionitems_invtrans_color"));
                        stockItemTransactionDTO.setUser_unit_of_symbol(rs.getString("tblinventorytransactionitems_invtrans_stk_under"));
                        stockItemTransactionDTO.setNo(rs.getDouble("tblinventorytransactionitems_invtrans_nos"));
                        stockItemTransactionDTO.setStkpackage(rs.getString("tblinventorytransactionitems_invtrans_package"));
                        stockItemTransactionDTO.setColor(rs.getString("tblinventorytransactionitems_invtrans_color"));

                        stockItemTransactionDTOList.add(stockItemTransactionDTO);
                    }
                    saleDTO = ImpExpUtil.valiDateAndRepairSaleDTO(saleDTO);
                }
                System.out.println("Size of stockItemTransactionDTOList---->>>" + stockItemTransactionDTOList.size());

                String queryTwo = "select tblvat.vat_rate as vat_rate,  tblvat.vat_amt as vat_amt, tblvat.tax_type as  tax_type from  tbltransactionvat as tblvat Inner join tbltransactionmain as tbltransactionmain on tbltransactionmain.trans_id = tblvat.trans_id where trans_typeIndex = " + Constants.SALE_TYPE_INDEX +  " and " + (type.equalsIgnoreCase("VoucherId") ? ("     trans_receiptNo = " + receiptID) : ("    trans_id = " + receiptID) ) + dateStrForMain ;
                PreparedStatement psmt1 = con.prepareStatement(queryTwo);
                ResultSet rs1 = psmt1.executeQuery();
                while (rs1.next()) {
                    if (rs1.getString("tax_type").equals("1")) {
                        saleDTO.setVatRate(rs1.getDouble("vat_rate"));
                        saleDTO.setVatAmount(rs1.getDouble("vat_amt"));
                    }
                    if (rs1.getString("tax_type").equals("2")) {
                        saleDTO.setExciseDutyRate(rs1.getDouble("vat_rate"));
                        saleDTO.setExciseDutyAmount(rs1.getDouble("vat_amt"));
                    }
                    if (rs1.getString("tax_type").equals("3")) {
                        saleDTO.setEdCessRate(rs1.getDouble("vat_rate"));
                        saleDTO.setEdCessAmount(rs1.getDouble("vat_amt"));
                    }
                    if (rs1.getString("tax_type").equals("4")) {
                        saleDTO.sethEdCessRate(rs1.getDouble("vat_rate"));
                        saleDTO.setHedCessAmount(rs1.getDouble("vat_amt"));
                    }
                    if (rs1.getString("tax_type").equals("5")) {
                        saleDTO.setCstRate(rs1.getDouble("vat_rate"));
                        saleDTO.setCstAmount(rs1.getDouble("vat_amt"));
                    }
                  
                }
                saleDTO = ImpExpUtil.valiDateAndRepairSaleDTO(saleDTO);
                saleDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

                if (!calling_Class.isEmpty()) {
                    gen.ImpExp.TagsHelper1.ledgerNameSet.add(saleDTO.getCashLedger());
                    gen.ImpExp.TagsHelper1.ledgerNameSet.add(saleDTO.getSaleLedger());
                    for (StockItemTransactionDTO stktransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {
                        gen.ImpExp.TagsHelper1.stockItemNameSet.add(stktransactionDTO.getName());
                    }
                }

                saleDTOList.add(saleDTO);
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
        return saleDTOList;
    }

    public static List<SaleDTO> getTransactionList(String ledger_id) throws SQLException, Exception {
        List<SaleDTO> saleDTOTransactionList = new ArrayList<SaleDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
                gen.dto.Constants.OFFSET_VALUE = 0L;
            }
            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.SALE_TYPE_INDEX, Constants.CREDIT) < gen.dto.Constants.OFFSET_VALUE) {
                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            }

            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where ";
            String condition = " trans_type=" + Constants.CREDIT + "  and  trans_typeIndex=" + Constants.SALE_TYPE_INDEX + " order by  trans_date desc,trans_receiptNo desc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + " ";
            if (ledger_id.isEmpty()) {
                condition = condition;
            } else {
                condition = " trans_ledgerId = " + ledger_id + " and " + condition;
            }
            queryOne = queryOne + condition;
            System.out.println("query ----- " + queryOne);
//            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.SALE_TYPE_INDEX + " order by  trans_receiptNo desc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + " ";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                SaleDTO saleDTO = new SaleDTO();
                saleDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                saleDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                saleDTO.setSaleLedger(rs.getString("tblledger_ledger_name"));

                saleDTOTransactionList.add(saleDTO);
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
        return saleDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.SALE_TYPE_INDEX);
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

    public static void updateSaleVoucher(List<SaleDTO> saleFormDTOList) throws SQLException, ParseException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            for (SaleDTO saleDTO : saleFormDTOList) {
                deleteReords(Long.parseLong(saleDTO.getTrans_ID()), conn);
            }

            //create vat ledger if not present and insert into database
            Map<String, String> LedgerNameToIDMapping = new HashMap<String, String>();
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72");
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            ////////////////////////////////////////////////////////////////////////////////////     
            //create excise duty ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.excise_DutyTransactionLedgerString + saleDTO.getExciseDutyRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create ed_Cess ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.ed_Cess_TransactionLedgerString + saleDTO.getEdCessRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create vat ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.hed_Cess_TransactionLedgerString + saleDTO.gethEdCessRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            //create cst ledger if not present and insert into database
            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under,isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%");
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "72"); // tax gound under value
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.cst_TransactionLedgerString + saleDTO.getCstRate() + "%", rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     


            //@todo - Need to remove query from for loop
            for (SaleDTO saleDTO : saleFormDTOList) {
                String queryStr = "select ledger_Id, ledger_name from tblLedger where ledger_name in (?)";
                System.out.println("queryStr==>>" + queryStr);
                PreparedStatement ps = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, gen.dto.Constants.ROUND_OFF_LEDGER);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    LedgerNameToIDMapping.put(rs.getString("ledger_name"), rs.getString("ledger_id"));
                } else {
                    String queryOne = "insert into tblLedger(ledger_name,ledger_under , isdeletable) values(?,?,?)";
                    PreparedStatement ps1 = conn.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
                    ps1.setString(1, gen.dto.Constants.ROUND_OFF_LEDGER);
                    // This is the id of Duties and Taxex.
                    ps1.setString(2, "64"); // Indirect expense gouop Under
                    ps1.setInt(3, 1);       // ledger can not be deleted
                    ps1.executeUpdate();
                    ResultSet rs1 = ps1.getGeneratedKeys();
                    while (rs1.next()) {
                        LedgerNameToIDMapping.put(gen.dto.Constants.ROUND_OFF_LEDGER, rs1.getInt(1) + "");
                    }

                    ps1.close();
                }
                ps.close();
            }

            String queryOne = "update tblTransactionMain set trans_receiptNo = ?,trans_date = ?, trans_typeIndex = ?, trans_narration = ?,trans_grandtotal = ?,trans_transport = ?,trans_lessBillAmt = ? where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (SaleDTO saleDTO : saleFormDTOList) {
                ps.setInt(1, (saleDTO.getReceiptNo()));
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getDate().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(2, sqlStartDate);
                ps.setInt(3, Constants.SALE_TYPE_INDEX);
                ps.setString(4, saleDTO.getNote().toString());
                ps.setDouble(5, Double.parseDouble(saleDTO.getFinalAmount().toString()));
                ps.setDouble(6, Double.parseDouble(saleDTO.getShipping().toString()));
                ps.setDouble(7, 0d);
                ps.setDouble(8, Long.parseLong(saleDTO.getTrans_ID().toString()));

                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();

            int i = 0;
            String queryTwo = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt,tax_type) values(?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(queryTwo);
            String queryThree = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment,trans_round_Off_amt,trans_poNo,trans_poDate,trans_oc_No,trans_oc_Date,trans_date_Iss,trans_Time_Iss,trans_date_Rem,trans_Time_Rem) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(queryThree);
            for (SaleDTO saleDTO : saleFormDTOList) {
                ps1.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                ps1.setDouble(2, saleDTO.getVatRate());
                ps1.setDouble(3, saleDTO.getVatAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_VAT_TYPE);
                ps1.addBatch();

                // added Excise Duty TAX
                ps1.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                System.out.println("getExciseDutyRate  " + saleDTO.getExciseDutyRate());
                System.out.println("getExciseDutyAmount  " + saleDTO.getExciseDutyAmount());
                ps1.setDouble(2, saleDTO.getExciseDutyRate());
                ps1.setDouble(3, saleDTO.getExciseDutyAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_EXCISE_TYPE);
                ps1.addBatch();

                // added ED_CESS TAX Duty 
                ps1.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                System.out.println("getEdCessRate  " + saleDTO.getEdCessRate());
                System.out.println("getEdCessAmount  " + saleDTO.getEdCessAmount());
                ps1.setDouble(2, saleDTO.getEdCessRate());
                ps1.setDouble(3, saleDTO.getEdCessAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_EDCESS_TYPE);
                ps1.addBatch();

                // added H_ED_CESS TAX Duty 
                ps1.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                System.out.println("gethEdCessRate  " + saleDTO.gethEdCessRate());
                System.out.println("getHedCessAmount  " + saleDTO.getHedCessAmount());
                ps1.setDouble(2, saleDTO.gethEdCessRate());
                ps1.setDouble(3, saleDTO.getHedCessAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_H_EDCESS_TYPE);
                ps1.addBatch();

                ps1.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                System.out.println("gethEdCessRate  " + saleDTO.gethEdCessRate());
                System.out.println("getHedCessAmount  " + saleDTO.getHedCessAmount());
                ps1.setDouble(2, saleDTO.getCstRate());
                ps1.setDouble(3, saleDTO.getCstAmount());
                ps1.setInt(4, gen.dto.Constants.TAX_CST_TYPE);
                ps1.addBatch();

                ps2.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                ps2.setString(2, saleDTO.getDispatchDocThrough());
                ps2.setString(3, saleDTO.getDispatchDocNo());
                ps2.setDouble(4, saleDTO.getAmount());
                ps2.setString(5, "" + 0);
                ps2.setString(6, "" + 0);
                System.out.println("getPoNo  " + saleDTO.getPoNo());
                System.out.println("getDate  " + saleDTO.getPoDate());
                ps2.setDouble(7, saleDTO.getRoundOffAmount());
                ps2.setString(8, saleDTO.getPoNo());
                if (!saleDTO.getPoDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(9, dateFormat(saleDTO.getPoDate()));
                } else {
                    ps2.setString(9, null);

                }
                System.out.println("getOcNo  " + saleDTO.getOcNo());
                System.out.println("getOcDate  " + saleDTO.getOcDate());
                ps2.setString(10, saleDTO.getOcNo());
                if (!saleDTO.getOcDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(11, dateFormat(saleDTO.getOcDate()));
                } else {
                    ps2.setString(11, "");

                }

                System.out.println("getDateIssDate  " + saleDTO.getDateIssDate());
                System.out.println("getTimeIss  " + saleDTO.getTimeIss());
                if (!saleDTO.getDateIssDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(12, dateFormat(saleDTO.getDateIssDate()));
                } else {
                    ps2.setString(12, "");
                }
                ps2.setString(13, saleDTO.getTimeIss());

                System.out.println("getDateRemDate  " + saleDTO.getDateRemDate());
                System.out.println("getTimeRem  " + saleDTO.getTimeRem());
                if (!saleDTO.getDateRemDate().trim().equalsIgnoreCase("")) {
                    ps2.setDate(14, dateFormat(saleDTO.getDateRemDate()));
                } else {
                    ps2.setString(14, "");
                }
                ps2.setString(15, saleDTO.getTimeRem());
                ps2.addBatch();
                i++;
            }
            ps1.executeBatch();
            ps2.executeBatch();

            String queryFour = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,IsAvaibleToUser) values(?,?,?,?,?,?,?)";
            PreparedStatement ps6 = conn.prepareStatement(queryFour);
            i = 0;
            for (SaleDTO saleDTO : saleFormDTOList) {
                ps6.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(saleDTO.getCashLedger()));
                ps6.setInt(3, Constants.CREDIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, saleDTO.getFinalAmount());
                ps6.setString(6, saleDTO.getNote().trim());
                ps6.setInt(7, 1); //IsAvailable to User
                ps6.addBatch();

                ps6.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                ps6.setInt(2, Integer.parseInt(saleDTO.getSaleLedger().toString()));
                ps6.setInt(3, Constants.DEBIT);
                ps6.setInt(4, 0);
                ps6.setDouble(5, saleDTO.getFinalAmount());
                ps6.setString(6, saleDTO.getNote().trim());
                ps6.setInt(7, 1); //IsAvailable to User
                ps6.addBatch();

                //if vat is non zero then insert into tbltransaction ledger
                if (saleDTO.getVatRate() != 0D && saleDTO.getVatAmount() != 0D) {
                    ps6.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                    System.out.println("===>>" + LedgerNameToIDMapping.keySet());
                    System.out.println("===>>" + gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%");
                    ps6.setInt(2, Integer.parseInt(LedgerNameToIDMapping.get(gen.dto.Constants.vatTransactionLedgerString_Sale + saleDTO.getVatRate() + "%")));
                    ps6.setInt(3, Constants.DEBIT);      //1 for credit
                    ps6.setInt(4, 0);      //index of ledger on form
                    ps6.setDouble(5, saleDTO.getVatAmount());
                    ps6.setString(6, saleDTO.getNote().trim());
                    ps6.setInt(7, 2); //not IsAvailable to User
                    ps6.addBatch();
                }


                //if round off is non zero then insert into tbltransaction ledger
                if (saleDTO.getRoundOffAmount() != null && saleDTO.getRoundOffAmount() != 0D) {
                    ps6.setInt(1, Integer.valueOf(saleDTO.getTrans_ID()));
                    ps6.setInt(2, Integer.parseInt(LedgerNameToIDMapping.get(gen.dto.Constants.ROUND_OFF_LEDGER)));
                    ps6.setInt(3, Constants.DEBIT);      //1 for credit
                    ps6.setInt(4, 0);      //index of ledger on form
                    ps6.setDouble(5, saleDTO.getRoundOffAmount());
                    ps6.setString(6, saleDTO.getNote().trim());
                    ps6.setInt(7, 2); //not IsAvailable to User
                    ps6.addBatch();
                }
                i++;
            }
            ps6.executeBatch();
            ps6.close();

            //todo
            i = 0;
            List<Integer> idList = new ArrayList<Integer>();
            String queryFive = "update tblInventoryTransaction set trans_id = ?,invtrans_type = ? where invtrans_id =?";
            PreparedStatement ps3 = conn.prepareStatement(queryFive);
            for (SaleDTO saleDTO : saleFormDTOList) {
                ps = conn.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + Long.parseLong(saleDTO.getTrans_ID()));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    idList.add(rs.getInt(1));
                }
                ps.close();
                i = 0;
                Integer fk = idList.get(i);
                ps3.setLong(1, Long.parseLong(saleDTO.getTrans_ID()));
                ps3.setString(2, "" + 1);
                ps3.setLong(3, fk);
                ps3.addBatch();
                i++;
            }
            ps3.executeBatch();
            ps3.close();

            i = 0;
            String querySix = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness,invtrans_stk_under,invtrans_nos,invtrans_package,invtrans_color_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps4 = conn.prepareStatement(querySix);
            for (SaleDTO saleDTO : saleFormDTOList) {
                Integer fk = idList.get(i);
                int k = 0;
                for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {

                    ps4.setInt(1, fk);
                    ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
                    ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
                    ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
                    ps4.setDouble(5, Double.parseDouble(stockItemTransactionDTO.getAmount().toString()));
                    ps4.setDouble(6, 0d);
                    ps4.setInt(7, k);
                    ps4.setDouble(8, 0.0);
                    ps4.setDouble(9, Double.parseDouble(stockItemTransactionDTO.getLength().toString()));
                    ps4.setDouble(10, Double.parseDouble(stockItemTransactionDTO.getWidth().toString()));
                    ps4.setDouble(11, Double.parseDouble(stockItemTransactionDTO.getThkness().toString()));
                    System.out.println("            ----------" + stockItemTransactionDTO.getUser_unit_of_symbol());
                    System.out.println("       ----------" + stockItemTransactionDTO.getNo());
                    System.out.println("       ----------" + stockItemTransactionDTO.getStkpackage());
                    System.out.println(" Color       ----------" + stockItemTransactionDTO.getColor());
                    ps4.setString(12, stockItemTransactionDTO.getUser_unit_of_symbol());
                    ps4.setDouble(13, stockItemTransactionDTO.getNo());
                    ps4.setString(14, stockItemTransactionDTO.getStkpackage());
                    ps4.setString(15, stockItemTransactionDTO.getColor());
                    ps4.addBatch();

                    k++;
                }
                i++;

            }
            ps4.executeBatch();
            ps4.close();
            conn.commit();
            conn.close();

            List<CustomerRateDTO> custRateDTOList = new ArrayList<CustomerRateDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : saleFormDTOList.get(0).getStockItemTreansactioDTOList()) {
                CustomerRateDTO custRateDTO = new CustomerRateDTO();
                custRateDTO.setCustomerID(Integer.parseInt(saleFormDTOList.get(0).getCashLedger()));
                custRateDTO.setStockItemID(Integer.parseInt(stockItemTransactionDTO.getName()));
                custRateDTO.setRate(stockItemTransactionDTO.getRate());
                custRateDTOList.add(custRateDTO);
            }
            if (custRateDTOList.size() > 0) {
                DeleteStockItemRate(custRateDTOList);
                insertStockItemRate(custRateDTOList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static void insertStockItemRate(List<CustomerRateDTO> customerRateDTOList) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "insert into tblstockitemrate(custid,stkid,rate)values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(queryOne);
            for (CustomerRateDTO customerRateDTO : customerRateDTOList) {
                ps.setInt(1, customerRateDTO.getCustomerID());
                ps.setInt(2, customerRateDTO.getStockItemID());
                ps.setDouble(3, customerRateDTO.getRate());

                ps.addBatch();

            }
            ps.executeBatch();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }

    }

    public static Map<String, String> getRateByCustomerID(Long customerID) throws SQLException, Exception {
        Connection conn = null;
        Map<String, String> mapTOReturn = new HashMap<String, String>();
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String queryOne = "SELECT     tblstockitemrate.rate AS tblstockitemrate_rate,     tblledger.ledger_name AS tblledger_ledger_name,     tblstockitem.si_name AS tblstockitem_si_name,     tblstockitemrate.custid AS tblstockitemrate_custid,     tblstockitemrate.stkid AS tblstockitemrate_stkid FROM     tblledger tblledger INNER JOIN tblstockitemrate tblstockitemrate ON tblledger.ledger_id = tblstockitemrate.custid      INNER JOIN tblstockitem tblstockitem ON tblstockitemrate.stkid = tblstockitem.si_id WHERE      tblstockitemrate.custid = " + customerID;
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                mapTOReturn.put(rs.getString("tblstockitem_si_name").toLowerCase(), rs.getString("tblstockitemrate_rate"));
            }
            rs.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return mapTOReturn;
    }

    public static void DeleteStockItemRate(List<CustomerRateDTO> customerRateDTOList) throws SQLException {
        Connection conn = null;
        try {
            if (customerRateDTOList != null && customerRateDTOList.size() > 0) {
                String inStmt = "";
                for (CustomerRateDTO custDTO : customerRateDTOList) {
                    inStmt += custDTO.getStockItemID() + ",";
                }
                inStmt = inStmt.substring(0, inStmt.length() - 1);

                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st1 = conn.createStatement();

                String queryOne = "delete from tblstockitemrate where custid=" + customerRateDTOList.get(0).getCustomerID() + " and stkid in(" + inStmt + ")";
                st1.executeUpdate(queryOne);
                conn.commit();
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    private static java.sql.Date dateFormat(String parsingDate) throws Exception {
        java.sql.Date sqlStartDate = null;
        try {
            java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(parsingDate.trim());
            sqlStartDate = new Date(date.getTime());
        } catch (ParseException ex) {
            try {
                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(parsingDate.trim());
                sqlStartDate = new Date(date.getTime());
            } catch (ParseException ex1) {
                ex1.printStackTrace();
                Logger.getLogger(SaleDAO.class.getName()).log(Level.SEVERE, null, ex1);
                throw ex1;
            }
            
            
            
        }
        return sqlStartDate;

    }
}

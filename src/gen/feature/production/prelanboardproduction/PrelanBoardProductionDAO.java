/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.prelanboardproduction;

import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kasturi
 */
public class PrelanBoardProductionDAO {

    public String getMaxreceiptno() throws SQLException {
        int receiptno = 0;
        String mainQuery = "";
        String condition = "";
        String max_receiptno = "";

        mainQuery = "select max(trans_receiptNo) As max_id from tblTransactionMain where trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX;
        String query = mainQuery + condition;

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            receiptno = rsSet.getInt("max_id");
        }
        receiptno++;
        max_receiptno = "" + receiptno;
        return max_receiptno;
    }

    public static void insert(List<PrelanBoardProductionDTO> prelanBoardProductionDTOList) throws SQLException {
        if (prelanBoardProductionDTOList != null && !prelanBoardProductionDTOList.isEmpty()) {
            String query = "";
            int checkQtyZero = 1;
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);
//
//            for (PrelanBoardProductionDTO newProductionProcessFinishItemQtyDTO : productionProcessDTO.getPaperproductionProcessDTOList()) {
//                if (newProductionProcessFinishItemQtyDTO.getListStockItemDTO() != null && newProductionProcessFinishItemQtyDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(newProductionProcessFinishItemQtyDTO.getFinishItemQty().toString()) != 0D) {
//                    checkQtyZero = 1;
//                }
//            }
            Long autoID1 = null;

            String receiptNo = "";
            String date = "";
            String sg_id = "2";
            for (PrelanBoardProductionDTO prelanBoardProductionDTO : prelanBoardProductionDTOList) {
                receiptNo = prelanBoardProductionDTO.getReceiptNo().toString();
                date = prelanBoardProductionDTO.getDate();
                sg_id = prelanBoardProductionDTO.getPrelanBoard_id();



                if (checkQtyZero == 1) {
                    /*
                     *Insert Data into table tblTransactionMain
                     */
                    query = "insert into tblTransactionMain(trans_receiptNo, trans_date , trans_typeIndex , sg_id) values(?, ?,?,?)";
                    PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    psmt.setString(1, receiptNo);
                    psmt.setString(2, date);
                    psmt.setDouble(3, Constants.PRODUCTION_TYPE_INDEX);
                    psmt.setString(4, sg_id);
                    psmt.execute();
                    ResultSet rs = psmt.getGeneratedKeys();
                    while (rs.next()) {
                        autoID1 = rs.getLong(1);
                    }
                    psmt.close();
                }
//                for (PrelanBoardProductionDTO newProductionProcessFinishItemQtyDTO : impregnatedPaperProductionDTOList) {
                if (prelanBoardProductionDTO.getListStockItemDTO() != null && prelanBoardProductionDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(prelanBoardProductionDTO.getFinishItemQty().toString()) != 0D) {
                    /*
                     *Insert Data into table tblInventoryTransaction
                     */
                    query = "insert into tblInventoryTransaction(trans_id, invtrans_type , dat) values(?,?,?)";
                    PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    psmt.setLong(1, autoID1);
                    psmt.setString(3, date);
                    psmt.setDouble(2, Constants.PRODUCTION_TYPE_INDEX);
                    psmt.execute();
                    ResultSet rs = psmt.getGeneratedKeys();
                    Long autoID = null;
                    while (rs.next()) {
                        autoID = rs.getLong(1);
                    }
                    psmt.close();
                    /*
                     *Insert Data into table tblInventoryTransactionItems
                     */
                    query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type) values(?,?,?,?)";
                    psmt = connection.prepareStatement(query);
                    for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : prelanBoardProductionDTO.getListStockItemDTO()) {
                        psmt.setLong(1, autoID);
                        psmt.setLong(2, rawStockItemDTO.getID());
                        psmt.setDouble(3, rawStockItemDTO.getQuantity());
                        psmt.setDouble(4, Constants.DEBIT);
                        psmt.addBatch();
                    }
                    {
                        //add finish Item
//                            System.out.println("Item IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd" + autoID + "AAAAAAAAAAAAA222222222222222222" + newProductionProcessFinishItemQtyDTO.getFinishItemId().toString());
                        psmt.setLong(1, autoID);
                        psmt.setString(2, prelanBoardProductionDTO.getFinishItemId().toString());
                        psmt.setDouble(3, prelanBoardProductionDTO.getFinishItemQty());
                        psmt.setDouble(4, Constants.CREDIT);
                        psmt.addBatch();
                    }
                    psmt.executeBatch();
                    psmt.close();
                }
//                }
            }
            connection.commit();
            connection.close();
        }
    }

    public static void update(List<PrelanBoardProductionDTO> impregnatedPaperProductionDTOList) throws Exception {
        Connection connection = DatabaseConnection1.GetConnection();
        try {
            if (impregnatedPaperProductionDTOList != null && !impregnatedPaperProductionDTOList.isEmpty()) {
                String query = "";
                int checkQtyZero = 1;
//                Connection connection = DatabaseConnection1.GetConnection();
                connection.setAutoCommit(false);


                Long autoID1 = null;

                String trans_id = "";
                String receiptNo = "";
                String date = "";
                String sg_id = "";
                for (PrelanBoardProductionDTO prelanBoardProductionDTO : impregnatedPaperProductionDTOList) {
                    receiptNo = prelanBoardProductionDTO.getReceiptNo().toString();
                    date = prelanBoardProductionDTO.getDate();
                    trans_id = prelanBoardProductionDTO.getTrans_id();
                    sg_id = prelanBoardProductionDTO.getPrelanBoard_id();


//                for (PrelanBoardProductionDTO PrelanBoardProductionDTO : impregnatedPaperProductionDTOList) {
                    System.out.println("Update Receipt  No ------------- " + prelanBoardProductionDTO.getReceiptNo());
                    System.out.println("------------- " + prelanBoardProductionDTO.getTrans_id());
                    deleteReords(Long.parseLong(prelanBoardProductionDTO.getTrans_id()), connection);
//                }

                    if (checkQtyZero == 1) {
                        /*
                         *Insert Data into table tblTransactionMain
                         */
//                query = "insert into tblTransactionMain(trans_receiptNo, trans_date , trans_typeIndex) values(?,?,?)";
                        query = "update  tblTransactionMain set trans_receiptNo = ?, trans_date =?, trans_typeIndex =? , sg_id = ? where trans_id =?";
                        PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        psmt.setString(1, receiptNo);
                        psmt.setString(2, date);
                        psmt.setDouble(3, Constants.PRODUCTION_TYPE_INDEX);
                        psmt.setString(4, sg_id);
                        psmt.setString(5, trans_id);
                        psmt.execute();
                        System.out.println("222222222222222");
                        ResultSet rs = psmt.getGeneratedKeys();
                        while (rs.next()) {
                            autoID1 = rs.getLong(1);
                        }
                        psmt.close();
                    }
//                for (PrelanBoardProductionDTO newProductionProcessFinishItemQtyDTO : impregnatedPaperProductionDTOList) {
                    if (prelanBoardProductionDTO.getListStockItemDTO() != null && prelanBoardProductionDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(prelanBoardProductionDTO.getFinishItemQty().toString()) != 0D) {
//                    /*
//                     *Insert Data into table tblInventoryTransaction
//                     */
//                    query = "insert into tblInventoryTransaction(trans_id, invtrans_type , dat) values(?,?,?)";
//                    PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                    psmt.setLong(1, autoID1);
//                    psmt.setString(3, date);
//                    psmt.setDouble(2, Constants.PRODUCTION_TYPE_INDEX);
//                    psmt.execute();
//                    ResultSet rs = psmt.getGeneratedKeys();
//                    Long autoID = null;
//                    while (rs.next()) {
//                        autoID = rs.getLong(1);
//                    }
//                    psmt.close();

                        query = "update tblInventoryTransaction set  invtrans_type = ?, dat = ? where trans_id = ?";
                        PreparedStatement psmt = connection.prepareStatement(query);
                        psmt.setDouble(1, Constants.PRODUCTION_TYPE_INDEX);
                        psmt.setString(2, date);
                        psmt.setString(3, trans_id);
                        System.out.println("333333333333333");
                        psmt.execute();
                        psmt.close();

                        System.out.println("");

                        List<Integer> idList = new ArrayList<Integer>();
                        PreparedStatement ps = connection.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + trans_id);
//                    PreparedStatement ps = connection.prepareStatement("select invtrans_id from tblInventoryTransaction ");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("44444444444444");
                        while (rs.next()) {
                            idList.add(rs.getInt(1));
                        }
                        ps.close();

                        Integer fk = idList.get(0);

                        /*
                         *Insert Data into table tblInventoryTransactionItems
                         */
                        query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type) values(?,?,?,?)";
                        psmt = connection.prepareStatement(query);
                        for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : prelanBoardProductionDTO.getListStockItemDTO()) {
                            System.out.println("555555555555555555555");
                            //                        System.out.println("Item IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd" + fk + "AAAAAAAAAAAAA" + rawStockItemDTO.getID());
                            psmt.setLong(1, fk);
                            psmt.setLong(2, rawStockItemDTO.getID());
                            psmt.setDouble(3, rawStockItemDTO.getQuantity());
                            psmt.setDouble(4, Constants.DEBIT);
                            psmt.addBatch();
                        }
                        {
                            //add finish Item
                            System.out.println("666666666666666666666");
                            System.out.println("Item IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd" + fk + "AAAAAAAAAAAAA222222222222222222" + prelanBoardProductionDTO.getFinishItemId().toString());
                            psmt.setLong(1, fk);
                            psmt.setString(2, prelanBoardProductionDTO.getFinishItemId().toString());
                            psmt.setDouble(3, prelanBoardProductionDTO.getFinishItemQty());
                            psmt.setDouble(4, Constants.CREDIT);
                            psmt.addBatch();
                        }
                        psmt.executeBatch();
                        psmt.close();
                    }
//                }
                }
            }
            connection.commit();
            connection.close();
        } catch (Exception e) {
            connection.commit();
            connection.close();
            throw e;
        }

    }

    public static void deleteReords(long transID, Connection conn) throws Exception {
        try {
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            System.out.println("111111111111111111111");
            String queryOne = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + transID + ")";
            st1.executeUpdate(queryOne);

            String queryThree = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(queryThree);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<PrelanBoardProductionDTO> getImprePaper(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        List<PrelanBoardProductionDTO> transDTOTransactionList = new ArrayList<PrelanBoardProductionDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT   tblInventoryTransactionItems.invtrans_qty as tblInventoryTransactionItems_invtrans_qty , tblInventoryTransactionItems.trans_type as  tblInventoryTransactionItems_trans_type , tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_receiptNo =" + receiptID
                        + " and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
                PrelanBoardProductionDTO receiptDTO = new PrelanBoardProductionDTO();
                List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
                PreparedStatement psmt = conn.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                int check_trans_no = 1;
                while (rs.next()) {

                    receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                    receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));


                    System.out.println("Count trans_id----------" + rs.getString("tbltransactionmain_trans_id"));

                    if (rs.getInt("tblInventoryTransactionItems_trans_type") == Constants.CREDIT) {
                        check_trans_no = 2;
                        receiptDTO.setTrans_id(rs.getString("tbltransactionmain_trans_id"));
                        receiptDTO.setFinishStockItemName(rs.getString("tblsi_si_name"));
                        System.out.println("Database Finish Item Qty --------- " + Double.parseDouble(rs.getString("tblInventoryTransactionItems_invtrans_qty")));
                        receiptDTO.setFinishItemQty(Double.parseDouble(rs.getString("tblInventoryTransactionItems_invtrans_qty")));
                    }

                    if (rs.getInt("tblInventoryTransactionItems_trans_type") == Constants.DEBIT) {
                        gen.account.StockItemFormation.StockItemDTO stockItemTransactionDTO = new gen.account.StockItemFormation.StockItemDTO();
                        stockItemTransactionDTO.setName(rs.getString("tblsi_si_name"));
                        stockItemTransactionDTO.setQuantity(Double.parseDouble(rs.getString("tblInventoryTransactionItems_invtrans_qty")));
                        stockItemTransactionDTOList.add(stockItemTransactionDTO);
                        check_trans_no = 1;
                    }

                    if (check_trans_no == 2) {
                        receiptDTO.setListStockItemDTO(stockItemTransactionDTOList);
                        System.out.println("GET  Trans_id ------ " + receiptDTO.getTrans_id());
                        transDTOTransactionList.add(receiptDTO);
                        receiptDTO = new PrelanBoardProductionDTO();
                    }

                }
//                receiptDTO.setListStockItemDTO(stockItemTransactionDTOList);
//                System.out.println("GET  Trans_id ------ " + receiptDTO.getTrans_id());
//                transDTOTransactionList.add(receiptDTO);
            }

            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return transDTOTransactionList;
    }

    public static List<PrelanBoardProductionDTO> getTransactionList() throws SQLException, Exception {
        List<PrelanBoardProductionDTO> transDTOTransactionList = new ArrayList<PrelanBoardProductionDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
//            if (gen.dto.Constants.OFFSET_VALUE < 0L) {
//                gen.dto.Constants.OFFSET_VALUE = 0L;
//            }
//            if (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.RECEIPT_TYPE_INDEX, Constants.DEBIT) < gen.dto.Constants.OFFSET_VALUE) {
//                gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
//            }

//            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id, tbltransactionledger.trans_id AS tbltransactionledger_trans_id,   tbltransactionledger.trans_ledgerId AS tbltransactionledger_trans_ledgerId,  tblledger.ledger_name AS tblledger_ledger_name,    tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date,   tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain INNER JOIN tbltransactionledger tbltransactionledger ON tbltransactionmain.trans_id = tbltransactionledger.trans_id    INNER JOIN tblledger tblledger ON tbltransactionledger.trans_ledgerId = tblledger.ledger_id where trans_type=" + Constants.DEBIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  LIMIT " + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination") + " OFFSET " + gen.dto.Constants.OFFSET_VALUE + "";
            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " and sg_id = " + 3 + " order by  trans_receiptNo Asc  ";
//            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                PrelanBoardProductionDTO receiptDTO = new PrelanBoardProductionDTO();
                receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                receiptDTO.setFinishStockItemName(rs.getString("tblsi_si_name"));

                transDTOTransactionList.add(receiptDTO);
            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return transDTOTransactionList;
    }

    public static void deleteTransaction(long transID) throws SQLException, Exception {
        try {
            AccountingVoucherHelper.deleteTransaction(transID, Constants.PRODUCTION_TYPE_INDEX);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
//    public static ProductionReportDTO getChemicalProduct() throws SQLException {
//        Long lastinsertedid = 0L;
//        ProductionReportDTO productionReportDTO = new ProductionReportDTO();
//        List<PrelanBoardProductionDTO> newChemicalProductionProcessList = new ArrayList<PrelanBoardProductionDTO>();
//
//        Connection connection = DatabaseConnection1.GetConnection();
//        String mainquery = "SELECT max(invtrans_id) As invtrans_id from tblinventorytransactionitems";
//        PreparedStatement Stat = connection.prepareStatement(mainquery);
//        ResultSet rs = Stat.executeQuery();
//        while (rs.next()) {
//            lastinsertedid = rs.getLong("invtrans_id");
//        }
//        rs.close();
//
//        mainquery = "SELECT * from tblinventorytransactionitems where   trans_type =1 and invtrans_id = " + lastinsertedid;
//        PreparedStatement Stat1 = connection.prepareStatement(mainquery);
//        ResultSet rs1 = Stat1.executeQuery();
//
//        PrelanBoardProductionDTO newChemicalProductionProcessDTO = new PrelanBoardProductionDTO();
//
//        while (rs1.next()) {
//            newChemicalProductionProcessDTO.setFinishItemId(rs1.getLong("invtrans_itemId"));
//            newChemicalProductionProcessDTO.setFinishItemQty(rs1.getDouble("invtrans_qty"));
//            newChemicalProductionProcessList.add(newChemicalProductionProcessDTO);
//        }
//        rs1.close();
//
//        productionReportDTO.setPaperproductionProcessDTOList(newChemicalProductionProcessList);
//        return productionReportDTO;
//    }
}

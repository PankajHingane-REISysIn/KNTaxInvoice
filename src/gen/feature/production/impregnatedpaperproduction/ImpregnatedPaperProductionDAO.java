/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.impregnatedpaperproduction;

import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.feature.production.resinproduction.ResignProductionProcessDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kasturi
 */
public class ImpregnatedPaperProductionDAO {

    /*
     *  1 ) Function For Getting  All StockItem ID and Names 
     *    
     */
    public static Map<String, String> getAllStockItems() throws SQLException {
        String mainQuery = "";
        String condition = "";
        Map<String, String> mapToReturn = new HashMap<String, String>();

        mainQuery = "Select * from tblstockitem";

        String query = mainQuery + condition;
        System.out.println("Query value of ALL getStockItem" + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("si_name"), rsSet.getLong("si_id") + "");
        }
        return mapToReturn;
    }

    /*
     *  1 ) Function For Getting  All FinishStockgGroup for FinishGroupCombo 
     *    
     */
    public static Map<String, String> getAllFinishStockGroup() throws SQLException {
        String mainQuery = "";
        String condition = "";
        Map<String, String> mapToReturn = new HashMap<String, String>();

        mainQuery = "select sg.sg_id,sg.sg_name from tblstockgroup As sg INNER JOIN tblfinishitemgroupandsubgroup As fgsg ON sg.sg_id = fgsg.finishgroupid ";
        condition = "Where sg.sg_name in ('Impregnated Paper')";
        String query = mainQuery + condition;
        System.out.println("Query value of getStockItemByFinishItem by FinishItem" + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("sg.sg_name"), rsSet.getLong("sg.sg_id") + "");
        }
        return mapToReturn;
    }

    /*
     *  1.1 ) Function For Getting  FinishStockgGroup for Raw Material aDdeddd for FinishGroupCombo 
     *    
     */
    public static Map<String, String> getOnlyFinishStockGroupofRawMaterialAdded() throws SQLException {
        String mainQuery = "";
        String condition = "";
        Map<String, String> mapToReturn = new HashMap<String, String>();

        mainQuery = "SELECT Distinct tblstockgroup.`sg_name` AS tblstockgroup_sg_name,tblstockgroup.`sg_id` AS tblstockgroup_sg_id,tblproductionrawmaterial.`FinishItemUnder` AS tblproductionrawmaterial_FinishItemUnder FROM  `tblstockgroup` tblstockgroup INNER JOIN `tblproductionrawmaterial` tblproductionrawmaterial ON tblstockgroup.`sg_id` = tblproductionrawmaterial.`FinishItemUnder`,`tblstockitem` tblstockitem";

        String query = mainQuery + condition;
        System.out.println("IN getOnlyFinishStockGroupofRawMaterialAdded........." + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("tblstockgroup_sg_name"), rsSet.getLong("tblstockgroup_sg_id") + "");
        }
        return mapToReturn;
    }

    /*
     *  2 ) Function For Getting  All FinishStockItem for FinishGroupCombo 
     *    
     */
    public static Map<String, String> getStockItemByFinishGroupCombo(String stockGroupid) throws SQLException {
        String mainQuery = "";
        String condition = "";
        Map<String, String> ListToReturn = new HashMap<String, String>();
        mainQuery = "select distinct stk.si_name,stk.si_id from tblproductionrawmaterial as prm INNER JOIN  tblstockitem As stk ON prm.FinishedItem = stk.si_id";

        if (!stockGroupid.equalsIgnoreCase("")) {
            condition = " where prm.FinishItemUnder ='" + stockGroupid + "'";
        }


        String query = mainQuery + condition;
        System.out.println(" in         getStockItemByFinishGroupCombo" + query);

        Connection connection;
        connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            ListToReturn.put(rsSet.getString("stk.si_name"), rsSet.getLong("stk.si_id") + "");
        }

        return ListToReturn;
    }

    /*
     *  Function For Getting StockItems Present For FinishItems 
     *  depending upon finishItems StockGroup    
     */
    public static Map<String, String> getStockItemsByFinishItems(List<String> stockGroupNames, Boolean isIn) throws SQLException {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";

        mainQuery = "SELECT   tblstockgroup.`sg_name` AS tblstockgroup_sg_name,     tblstockitem.`si_id` AS tblstockitem_si_id,     tblstockitem.`si_name` AS tblstockitem_si_name FROM     `tblstockgroup` tblstockgroup INNER JOIN `tblstockitem` tblstockitem ON tblstockgroup.`sg_id` = tblstockitem.`si_under`";
        condition = " where tblstockgroup.`sg_name` = 'Impregnated Paper' ";
        String query = mainQuery + condition;
        System.out.println("getStockItemsByFinishItems..........." + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("tblstockitem_si_name"), rsSet.getLong("tblstockitem_si_id") + "");
        }
        rsSet.close();
        psmt.close();
        connection.close();
        return mapToReturn;
    }

//    public static List<gen.account.StockItemFormation.StockItemDTO> getStockItemByFinishItem(Date date, String FinishItem) throws SQLException {
//        List<gen.account.StockItemFormation.StockItemDTO> listToReturn = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
//        String mainQuery = "";
//        String condition = "";
//        /*
//         * main query comment on 3-06-2013
//         */
//        mainQuery = "SELECT  tblstockitem.`si_id`,tblproductionrawmaterial.`FinishedItem` AS tblproductionrawmaterial_FinishedItem,tblproductionrawmaterial.`RawItem` AS tblproductionrawmaterial_RawItem,tblproductionrawmaterial.`qty` AS tblproductionrawmaterial_qty,      tblproductionrawmaterial.`ind` AS tblproductionrawmaterial_ind, tblstockitem.`si_name` AS tblstockitem_si_name FROM `tblstockitem` tblstockitem INNER JOIN `tblproductionrawmaterial` tblproductionrawmaterial ON tblstockitem.`si_id` = tblproductionrawmaterial.`RawItem`";
//
//        //  New Code 3-06-2013
//        //   mainQuery = "SELECT tblstockitem.`si_id`,tblproductionrawmaterial.`FinishedItem` AS tblproductionrawmaterial_FinishedItem,tblproductionrawmaterial.`RawItem` AS tblproductionrawmaterial_RawItem,tblproductionrawmaterial.`qty` AS tblproductionrawmaterial_qty, tblproductionrawmaterial.`ind` AS tblproductionrawmaterial_ind,tblstockitem.`si_name` AS tblstockitem_si_name,tblunitofmeasure.`uom_symbol` AS tblunitofmeasure_uom_symbol FROM `tblstockitem` tblstockitem INNER JOIN `tblproductionrawmaterial` tblproductionrawmaterial ON tblstockitem.`si_id` = tblproductionrawmaterial.`RawItem` INNER JOIN `tblunitofmeasure` tblunitofmeasure ON tblstockitem.`si_unitOfMeasure` = tblunitofmeasure.`uom_id`";       
//        if (FinishItem != null) {
//            if (FinishItem.equalsIgnoreCase("")) {
//                condition = "" + "Group by tblproductionrawmaterial.`RawItem`";
//            } else {
//                condition = "where tblproductionrawmaterial.`FinishedItem`=" + FinishItem + " ";
//            }
//            String query = mainQuery + condition;
//
//            System.out.println("Stock Item By Finish Item" + query);
//            Connection connection = DatabaseConnection1.GetConnection();
//            PreparedStatement psmt = connection.prepareStatement(query);
//            ResultSet rsSet = psmt.executeQuery();
//            while (rsSet.next()) {
//                gen.account.StockItemFormation.StockItemDTO rawStockitemDTO = new gen.account.StockItemFormation.StockItemDTO();
//                rawStockitemDTO.setIndex(rsSet.getInt("tblproductionrawmaterial_ind"));
//                rawStockitemDTO.setFinishItem(rsSet.getLong("tblproductionrawmaterial_FinishedItem"));
//                rawStockitemDTO.setQuantity(rsSet.getDouble("tblproductionrawmaterial_qty"));
//                rawStockitemDTO.setStockitemName(rsSet.getString("tblstockitem_si_name"));
//                rawStockitemDTO.setStockItemId(rsSet.getLong("tblproductionrawmaterial_RawItem"));
//
//                query = "SELECT tblunitofmeasure.`uom_symbol` AS tblunitofmeasure_uom_symbol, tblstockitem.`si_id` AS tblstockitem_si_id  FROM `tblunitofmeasure` tblunitofmeasure INNER JOIN `tblstockitem` tblstockitem ON tblunitofmeasure.`uom_id` = tblstockitem.`si_unitOfMeasure` where tblstockitem.`si_id` =" + rsSet.getLong("tblproductionrawmaterial_RawItem") + "";
//                PreparedStatement psmt1 = connection.prepareStatement(query);
//                ResultSet rsSet1 = psmt1.executeQuery();
//                while (rsSet1.next()) {
//                    rawStockitemDTO.setUnitOfSymbol(rsSet1.getString("tblunitofmeasure_uom_symbol"));
//                }
//                listToReturn.add(rawStockitemDTO);
//            }
//        }
//        return listToReturn;
//    }
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

    public static void insert(List<ImpregnatedPaperProductionDTO> impregnatedPaperProductionDTOList) throws SQLException {
        if (impregnatedPaperProductionDTOList != null && !impregnatedPaperProductionDTOList.isEmpty()) {
            String query = "";
            int checkQtyZero = 1;
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);
//
//            for (ImpregnatedPaperProductionDTO newProductionProcessFinishItemQtyDTO : productionProcessDTO.getPaperproductionProcessDTOList()) {
//                if (newProductionProcessFinishItemQtyDTO.getListStockItemDTO() != null && newProductionProcessFinishItemQtyDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(newProductionProcessFinishItemQtyDTO.getFinishItemQty().toString()) != 0D) {
//                    checkQtyZero = 1;
//                }
//            }
            Long autoID1 = null;

            String receiptNo = "";
            String date = "";
            String sg_id = "2";
            for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO : impregnatedPaperProductionDTOList) {
                receiptNo = impregnatedPaperProductionDTO.getReceiptNo().toString();
                date = impregnatedPaperProductionDTO.getDate();
                sg_id = impregnatedPaperProductionDTO.getImpreganated_group_id();



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
//                for (ImpregnatedPaperProductionDTO newProductionProcessFinishItemQtyDTO : impregnatedPaperProductionDTOList) {
                if (impregnatedPaperProductionDTO.getListStockItemDTO() != null && impregnatedPaperProductionDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(impregnatedPaperProductionDTO.getFinishItemQty().toString()) != 0D) {
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
                    for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : impregnatedPaperProductionDTO.getListStockItemDTO()) {
                        psmt.setLong(1, autoID);
                        psmt.setLong(2, rawStockItemDTO.getID());
                        System.out.println("Raw Item Qty ---- "+rawStockItemDTO.getQuantity());
                        psmt.setDouble(3, rawStockItemDTO.getQuantity());
                        psmt.setDouble(4, Constants.DEBIT);
                        psmt.addBatch();
                    }
                    {
                        //add finish Item
//                            System.out.println("Item IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd" + autoID + "AAAAAAAAAAAAA222222222222222222" + newProductionProcessFinishItemQtyDTO.getFinishItemId().toString());
                        psmt.setLong(1, autoID);
                        psmt.setString(2, impregnatedPaperProductionDTO.getFinishItemId().toString());
                        System.out.println("getFinishItemQty Qty ---- "+impregnatedPaperProductionDTO.getFinishItemQty());
                        psmt.setDouble(3, impregnatedPaperProductionDTO.getFinishItemQty());
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

    public static void update(List<ImpregnatedPaperProductionDTO> impregnatedPaperProductionDTOList) throws Exception {
        Connection connection = DatabaseConnection1.GetConnection();
        try {
            if (impregnatedPaperProductionDTOList != null && !impregnatedPaperProductionDTOList.isEmpty()) {
                String query = "";
                int checkQtyZero = 1;
//            Connection connection = DatabaseConnection1.GetConnection();
                connection.setAutoCommit(false);


                Long autoID1 = null;

                String trans_id = "";
                String receiptNo = "";
                String date = "";
                String sg_id = "";
                for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO : impregnatedPaperProductionDTOList) {
                    receiptNo = impregnatedPaperProductionDTO.getReceiptNo().toString();
                    date = impregnatedPaperProductionDTO.getDate();
                    trans_id = impregnatedPaperProductionDTO.getTrans_id();
                    sg_id = impregnatedPaperProductionDTO.getImpreganated_group_id();


//                for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO : impregnatedPaperProductionDTOList) {
                    System.out.println("Update Receipt  No ------------- " + impregnatedPaperProductionDTO.getReceiptNo());
                    System.out.println("------------- " + impregnatedPaperProductionDTO.getTrans_id());
                    deleteReords(Long.parseLong(impregnatedPaperProductionDTO.getTrans_id()), connection);
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
//                for (ImpregnatedPaperProductionDTO newProductionProcessFinishItemQtyDTO : impregnatedPaperProductionDTOList) {
                    if (impregnatedPaperProductionDTO.getListStockItemDTO() != null && impregnatedPaperProductionDTO.getListStockItemDTO().size() > 0 && Double.parseDouble(impregnatedPaperProductionDTO.getFinishItemQty().toString()) != 0D) {
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
                        for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : impregnatedPaperProductionDTO.getListStockItemDTO()) {
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
                            System.out.println("Item IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd" + fk + "AAAAAAAAAAAAA222222222222222222" + impregnatedPaperProductionDTO.getFinishItemId().toString());
                            psmt.setLong(1, fk);
                            psmt.setString(2, impregnatedPaperProductionDTO.getFinishItemId().toString());
                            psmt.setDouble(3, impregnatedPaperProductionDTO.getFinishItemQty());
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

    public static List<ImpregnatedPaperProductionDTO> getImprePaper(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        List<ImpregnatedPaperProductionDTO> transDTOTransactionList = new ArrayList<ImpregnatedPaperProductionDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT   tblInventoryTransactionItems.invtrans_qty as tblInventoryTransactionItems_invtrans_qty , tblInventoryTransactionItems.trans_type as  tblInventoryTransactionItems_trans_type , tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_receiptNo =" + receiptID
                        + " and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
                ImpregnatedPaperProductionDTO receiptDTO = new ImpregnatedPaperProductionDTO();
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
                        receiptDTO = new ImpregnatedPaperProductionDTO();
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

    public static List<ImpregnatedPaperProductionDTO> getTransactionList() throws SQLException, Exception {
        List<ImpregnatedPaperProductionDTO> transDTOTransactionList = new ArrayList<ImpregnatedPaperProductionDTO>();
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
            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " and sg_id = " + 2 + " order by  trans_receiptNo Asc  ";
//            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ImpregnatedPaperProductionDTO receiptDTO = new ImpregnatedPaperProductionDTO();
                receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                receiptDTO.setFinishStockItemName(rs.getString("tblsi_si_name"));
                receiptDTO.setTrans_id(rs.getString("tbltransactionmain_trans_id"));

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
//        List<ImpregnatedPaperProductionDTO> newChemicalProductionProcessList = new ArrayList<ImpregnatedPaperProductionDTO>();
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
//        ImpregnatedPaperProductionDTO newChemicalProductionProcessDTO = new ImpregnatedPaperProductionDTO();
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

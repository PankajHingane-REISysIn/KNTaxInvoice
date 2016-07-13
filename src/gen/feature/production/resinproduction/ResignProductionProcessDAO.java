/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.resinproduction;

import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
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
public class ResignProductionProcessDAO {

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
        condition = "Where sg.sg_name in ('Chemical')";

        String query = mainQuery + condition;
        System.out.println("Query value of getStockItemByChemicalItem by FinishItem" + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("sg.sg_name"), rsSet.getLong("sg.sg_id") + "");
        }
        return mapToReturn;
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
//        if (stockGroupNames != null && stockGroupNames.size() > 0) {
//            String stkGroupList = "";
//            for (String stkGroup : stockGroupNames) {
//                stkGroupList = "'" + stkGroup + "' ,";
//            }
//            if (stkGroupList.length() > 0) {
//                stkGroupList = stkGroupList.substring(0, stkGroupList.length() - 2);
//            }
//
//            if (isIn == null || isIn) {
//                condition = "where tblstockgroup.`sg_name` in (" + stkGroupList + ")";
//            } else {
//                condition = "where tblstockgroup.`sg_name` not in (" + stkGroupList + ")";
//            }
//
//        }
        condition = " where tblstockgroup.`sg_name` in ('Chemical','') ";
        String query = mainQuery + condition;
        System.out.println("Query For Get Stock Item by nAme" + query);

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

    public static void insert(ResignProductionProcessDTO resignProductionProcessDTO) throws SQLException {
        if (resignProductionProcessDTO != null) {
            String query = "";
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);
            Long autoID1 = null;
            /*
             *Insert Data into table tblTransactionMain
             */
            query = "insert into tblTransactionMain(trans_receiptNo, trans_date , trans_typeIndex , sg_id) values(?,?,?,?)";
            PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, resignProductionProcessDTO.getReceiptNo());
            System.out.println("date ------- " + resignProductionProcessDTO.getDate());
            psmt.setString(2, resignProductionProcessDTO.getDate());
            psmt.setDouble(3, Constants.PRODUCTION_TYPE_INDEX);
            psmt.setString(4, resignProductionProcessDTO.getResin_group_id());
            psmt.execute();
            ResultSet rs = psmt.getGeneratedKeys();
            while (rs.next()) {
                autoID1 = rs.getLong(1);
            }
            psmt.close();
            /*
             *Insert Data into table tblInventoryTransaction
             */
            query = "insert into tblInventoryTransaction(trans_id, invtrans_type , dat) values(?,?,?)";
            psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.setLong(1, autoID1);
            psmt.setDouble(2, Constants.PRODUCTION_TYPE_INDEX);
            psmt.setString(3, resignProductionProcessDTO.getDate());

            psmt.execute();
            rs = psmt.getGeneratedKeys();
            Long autoID = null;
            while (rs.next()) {
                autoID = rs.getLong(1);
            }
            psmt.close();

//            List<ResignProductionProcessDTO> newChemicalProductionProcessList = productionProcessDTO.getChemicalProductionProcessDTOList();
//            System.out.println("newChemicalProductionProcessListnewChemicalProductionProcessList>>>>>"+newChemicalProductionProcessList.size());
//            
//           for (ResignProductionProcessDTO newChemicalProductionProcessDTO : productionProcessDTO.getChemicalProductionProcessDTOList()) {
//                System.out.println("newChemicalProductionProcessDTO++++++newChemicalProductionProcessDTO"+newChemicalProductionProcessDTO.getFinishStockItem());

            if (resignProductionProcessDTO != null) {
                /*
                 *Insert Data into table tblInventoryTransactionItems
                 */
//                    query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type,trans_receiptNo) values(?,?,?,?,?)";
                query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type) values(?,?,?,?)";
                psmt = connection.prepareStatement(query);
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : resignProductionProcessDTO.getListStockItemDTO()) {
                    psmt.setLong(1, autoID);
                    psmt.setLong(2, rawStockItemDTO.getID());
                    psmt.setDouble(3, rawStockItemDTO.getQuantity());
                    psmt.setDouble(4, Constants.DEBIT);
//                        psmt.setInt(5, resignProductionProcessDTO.getReceiptNo());
                    psmt.addBatch();
                }
                {
                    //add finish Item
                    psmt.setLong(1, autoID);
                    psmt.setString(2, "" + resignProductionProcessDTO.getFinishItemId().toString());
                    psmt.setDouble(3, resignProductionProcessDTO.getFinishItemQty());
                    psmt.setDouble(4, Constants.CREDIT);
//                        psmt.setInt(5, resignProductionProcessDTO.getReceiptNo());
                    psmt.addBatch();
                }
                psmt.executeBatch();
                psmt.close();
            }
            connection.commit();
            connection.close();
//            }
        }
    }

    public static void update(ResignProductionProcessDTO resignProductionProcessDTO) throws Exception {
        if (resignProductionProcessDTO != null) {
            String query = "";
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);
            Long autoID1 = null;

            System.out.println("update Resin -------------");
            
            if (resignProductionProcessDTO != null) {
                deleteReords(Long.parseLong(resignProductionProcessDTO.getTrans_id()), connection);
            }


            /*
             *Insert Data into table tblTransactionMain
             */
            query = "update tblTransactionMain set trans_receiptNo = ?, trans_date = ? , trans_typeIndex = ? , sg_id = ? where trans_id = ?";
            PreparedStatement psmt = connection.prepareStatement(query);
//            PreparedStatement psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, resignProductionProcessDTO.getReceiptNo());
            System.out.println("date ------- " + resignProductionProcessDTO.getDate());
            psmt.setString(2, resignProductionProcessDTO.getDate());
            psmt.setDouble(3, Constants.PRODUCTION_TYPE_INDEX);
            psmt.setString(4, resignProductionProcessDTO.getResin_group_id());
            psmt.setString(5, resignProductionProcessDTO.getTrans_id());
            psmt.execute();
            
            System.out.println("Date ----------- "+resignProductionProcessDTO.getDate());
            System.out.println("Trasn_id ----------- "+resignProductionProcessDTO.getTrans_id());
            System.out.println("Receipt No ----------- "+resignProductionProcessDTO.getReceiptNo());
            
//            ResultSet rs = psmt.getGeneratedKeys();
//            while (rs.next()) {
//                autoID1 = rs.getLong(1);
//            }
//            psmt.close();
            /*
             *Insert Data into table tblInventoryTransaction
             */
            query = "update tblInventoryTransaction set  invtrans_type = ?, dat = ? where trans_id = ?";
            psmt = connection.prepareStatement(query);
//            psmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            psmt.setDouble(1, Constants.PRODUCTION_TYPE_INDEX);
            psmt.setString(2, resignProductionProcessDTO.getDate());
            psmt.setString(3, resignProductionProcessDTO.getTrans_id());
            psmt.execute();
            psmt.close();

            List<Integer> idList = new ArrayList<Integer>();
            PreparedStatement ps = connection.prepareStatement("select invtrans_id from tblInventoryTransaction where trans_id = " + Long.parseLong(resignProductionProcessDTO.getTrans_id()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            Integer fk = idList.get(0);
            
//            List<ResignProductionProcessDTO> newChemicalProductionProcessList = productionProcessDTO.getChemicalProductionProcessDTOList();
//            System.out.println("newChemicalProductionProcessListnewChemicalProductionProcessList>>>>>"+newChemicalProductionProcessList.size());
//            
//           for (ResignProductionProcessDTO newChemicalProductionProcessDTO : productionProcessDTO.getChemicalProductionProcessDTOList()) {
//                System.out.println("newChemicalProductionProcessDTO++++++newChemicalProductionProcessDTO"+newChemicalProductionProcessDTO.getFinishStockItem());

            if (resignProductionProcessDTO != null) {
                /*
                 *Insert Data into table tblInventoryTransactionItems
                 */
//                    query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type,trans_receiptNo) values(?,?,?,?,?)";
                query = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,trans_type) values(?,?,?,?)";
                psmt = connection.prepareStatement(query);
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : resignProductionProcessDTO.getListStockItemDTO()) {
                    psmt.setLong(1, fk);
                    psmt.setLong(2, rawStockItemDTO.getID());
                    psmt.setDouble(3, rawStockItemDTO.getQuantity());
                    psmt.setDouble(4, Constants.DEBIT);
                    
                    System.out.println("");
                    
//                        psmt.setInt(5, resignProductionProcessDTO.getReceiptNo());
                    psmt.addBatch();
                }
                {
                    //add finish Item
                    psmt.setLong(1, fk);
                    psmt.setString(2, "" + resignProductionProcessDTO.getFinishItemId().toString());
                    psmt.setDouble(3, resignProductionProcessDTO.getFinishItemQty());
                    psmt.setDouble(4, Constants.CREDIT);
//                        psmt.setInt(5, resignProductionProcessDTO.getReceiptNo());
                    psmt.addBatch();
                }
                psmt.executeBatch();
                psmt.close();
            }
            connection.commit();
            connection.close();
//            }
        }
    }

    public static void deleteReords(long transID, Connection conn) throws Exception {
        try {
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();

            String queryOne = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + transID + ")";
            st1.executeUpdate(queryOne);

            String queryThree = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
            st2.executeUpdate(queryThree);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<gen.account.StockItemFormation.StockItemDTO> getStockItemByFinishItem(Date date, String FinishItem) throws SQLException {
        List<gen.account.StockItemFormation.StockItemDTO> listToReturn = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
        String mainQuery = "";
        String condition = "";
        /*
         * main query comment on 3-06-2013
         */
        mainQuery = "SELECT  tblstockitem.`si_id`,tblproductionrawmaterial.`FinishedItem` AS tblproductionrawmaterial_FinishedItem,tblproductionrawmaterial.`RawItem` AS tblproductionrawmaterial_RawItem,tblproductionrawmaterial.`qty` AS tblproductionrawmaterial_qty,      tblproductionrawmaterial.`ind` AS tblproductionrawmaterial_ind, tblstockitem.`si_name` AS tblstockitem_si_name FROM `tblstockitem` tblstockitem INNER JOIN `tblproductionrawmaterial` tblproductionrawmaterial ON tblstockitem.`si_id` = tblproductionrawmaterial.`RawItem`";

        //  New Code 3-06-2013
        //   mainQuery = "SELECT tblstockitem.`si_id`,tblproductionrawmaterial.`FinishedItem` AS tblproductionrawmaterial_FinishedItem,tblproductionrawmaterial.`RawItem` AS tblproductionrawmaterial_RawItem,tblproductionrawmaterial.`qty` AS tblproductionrawmaterial_qty, tblproductionrawmaterial.`ind` AS tblproductionrawmaterial_ind,tblstockitem.`si_name` AS tblstockitem_si_name,tblunitofmeasure.`uom_symbol` AS tblunitofmeasure_uom_symbol FROM `tblstockitem` tblstockitem INNER JOIN `tblproductionrawmaterial` tblproductionrawmaterial ON tblstockitem.`si_id` = tblproductionrawmaterial.`RawItem` INNER JOIN `tblunitofmeasure` tblunitofmeasure ON tblstockitem.`si_unitOfMeasure` = tblunitofmeasure.`uom_id`";       
        if (FinishItem != null) {
            if (FinishItem.equalsIgnoreCase("")) {
                condition = "" + "Group by tblproductionrawmaterial.`RawItem`";
            } else {
                condition = "where tblproductionrawmaterial.`FinishedItem`=" + FinishItem + " ";
            }
            String query = mainQuery + condition;

            System.out.println("Stock Item By Finish Item" + query);
            Connection connection = DatabaseConnection1.GetConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            ResultSet rsSet = psmt.executeQuery();
            while (rsSet.next()) {
                gen.account.StockItemFormation.StockItemDTO rawStockitemDTO = new gen.account.StockItemFormation.StockItemDTO();
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
                listToReturn.add(rawStockitemDTO);
            }
        }
        return listToReturn;
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
        System.out.println("Query value of getStockItemByFinishItem by FinishItem" + query);

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("tblstockgroup_sg_name"), rsSet.getLong("tblstockgroup_sg_id") + "");
        }
        return mapToReturn;
    }

    public static List<ResignProductionProcessDTO> getTransactionList() throws SQLException, Exception {
        List<ResignProductionProcessDTO> transDTOTransactionList = new ArrayList<ResignProductionProcessDTO>();
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
            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " and sg_id ="+1+" order by  trans_receiptNo Asc  ";
//            String queryOne = "SELECT     tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_type=" + Constants.CREDIT + "  and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
            PreparedStatement psmt = conn.prepareStatement(queryOne);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ResignProductionProcessDTO receiptDTO = new ResignProductionProcessDTO();
                receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));
                receiptDTO.setFinishStockItem(rs.getString("tblsi_si_name"));
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

    public static List<ResignProductionProcessDTO> getResin(Set<String> receiptIDSet, String calling_Class) throws SQLException, Exception {
        List<ResignProductionProcessDTO> transDTOTransactionList = new ArrayList<ResignProductionProcessDTO>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            for (String receiptID : receiptIDSet) {
                String queryOne = "SELECT   tblInventoryTransactionItems.invtrans_qty as tblInventoryTransactionItems_invtrans_qty , tblInventoryTransactionItems.trans_type as  tblInventoryTransactionItems_trans_type , tbltransactionmain.trans_id AS tbltransactionmain_trans_id,tblstockitem.si_name as  tblsi_si_name, tbltransactionmain.trans_receiptNo AS tbltransactionmain_trans_receiptNo,   tbltransactionmain.trans_date AS tbltransactionmain_trans_date, tbltransactionmain.trans_typeIndex AS tbltransactionmain_trans_typeIndex FROM    tbltransactionmain tbltransactionmain  INNER JOIN  tblInventoryTransaction tblInventoryTransaction ON tbltransactionmain.trans_id = tblInventoryTransaction.trans_id    INNER JOIN tblInventoryTransactionItems tblInventoryTransactionItems ON tblInventoryTransaction.invtrans_id = tblInventoryTransactionItems.invtrans_id Inner join tblstockitem  tblstockitem On tblInventoryTransactionItems.invtrans_itemId  = tblstockitem.si_id where trans_receiptNo =" + receiptID
                        + " and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + " order by  trans_receiptNo Asc  ";
                ResignProductionProcessDTO receiptDTO = new ResignProductionProcessDTO();
                List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
                PreparedStatement psmt = conn.prepareStatement(queryOne);
                ResultSet rs = psmt.executeQuery();
                while (rs.next()) {

                    receiptDTO.setTrans_id(rs.getString("tbltransactionmain_trans_id"));
                    receiptDTO.setReceiptNo(rs.getInt("tbltransactionmain_trans_receiptNo"));
                    receiptDTO.setDate(rs.getString("tbltransactionmain_trans_date"));

                    if (rs.getInt("tblInventoryTransactionItems_trans_type") == Constants.CREDIT) {
                        receiptDTO.setFinishStockItem(rs.getString("tblsi_si_name"));
                        receiptDTO.setFinishItemQty(Double.parseDouble(rs.getString("tblInventoryTransactionItems_invtrans_qty")));
                    }

                    if (rs.getInt("tblInventoryTransactionItems_trans_type") == Constants.DEBIT) {
                        gen.account.StockItemFormation.StockItemDTO stockItemTransactionDTO = new gen.account.StockItemFormation.StockItemDTO();
                        stockItemTransactionDTO.setName(rs.getString("tblsi_si_name"));
                        stockItemTransactionDTO.setQuantity(Double.parseDouble(rs.getString("tblInventoryTransactionItems_invtrans_qty")));
                        stockItemTransactionDTOList.add(stockItemTransactionDTO);
                        System.out.println("iddddddddddddddddd------------------------");
                    }
                }
                receiptDTO.setListStockItemDTO(stockItemTransactionDTOList);
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
//    /*
//     *  2 ) Function For Getting  All FinishStockItem for FinishGroupCombo 
//     *    
//     */
//    public static Map<String, String> getStockItemByFinishGroupCombo(String stockGroupid) throws SQLException {
//        String mainQuery = "";
//        String condition = "";
//        Map<String, String> ListToReturn = new HashMap<String, String>();
//        mainQuery = "select distinct stk.si_name,stk.si_id from tblproductionrawmaterial as prm INNER JOIN  tblstockitem As stk ON prm.FinishedItem = stk.si_id";
//
//        if (!stockGroupid.equalsIgnoreCase("")) {
//            condition = " where prm.FinishItemUnder ='" + stockGroupid + "'";
//        }
//
//
//        String query = mainQuery + condition;
//        System.out.println("Query value of getStockItemByFinishItem by FinishItem" + query);
//
//        Connection connection = DatabaseConnection1.GetConnection();
//        PreparedStatement psmt = connection.prepareStatement(query);
//        ResultSet rsSet = psmt.executeQuery();
//        while (rsSet.next()) {
//            ListToReturn.put(rsSet.getString("stk.si_name"), rsSet.getLong("stk.si_id") + "");
//        }
//
//        return ListToReturn;
//    }
//    public static List<StockItemDTO> getStockItemByFinishItem(Date date, String FinishItem) throws SQLException {
//        List<StockItemDTO> listToReturn = new ArrayList<StockItemDTO>();
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
//                condition = "where tblproductionrawmaterial.`FinishedItem`='" + FinishItem + "'";
//            }
//            String query = mainQuery + condition;
//
//            System.out.println("Stock Item By Finish Item" + query);
//            Connection connection = DatabaseConnection1.GetConnection();
//            PreparedStatement psmt = connection.prepareStatement(query);
//            ResultSet rsSet = psmt.executeQuery();
//            while (rsSet.next()) {
//                StockItemDTO rawStockitemDTO = new StockItemDTO();
//                rawStockitemDTO.setIndex(rsSet.getInt("tblproductionrawmaterial_ind"));
//                rawStockitemDTO.setFinishItem(rsSet.getLong("tblproductionrawmaterial_FinishedItem"));
//                rawStockitemDTO.setQuantity(rsSet.getDouble("tblproductionrawmaterial_qty"));
//                rawStockitemDTO.setStockitemName(rsSet.getString("tblstockitem_si_name"));
//                rawStockitemDTO.setStockItemId(rsSet.getLong("tblproductionrawmaterial_RawItem"));
//
//                query = "SELECT tblunitofmeasure.`uom_symbol` AS tblunitofmeasure_uom_symbol, tblstockitem.`si_id` AS tblstockitem_si_id  FROM `tblunitofmeasure` tblunitofmeasure INNER JOIN `tblstockitem` tblstockitem ON tblunitofmeasure.`uom_id` = tblstockitem.`si_unitOfMeasure` where tblstockitem.`si_id` =" + rsSet.getLong("tblproductionrawmaterial_RawItem") + "";
//                PreparedStatement psmt1 = connection.prepareStatement(query);
//                ResultSet rsSet1 = psmt1.executeQuery();
//                while(rsSet1.next()){
//                  rawStockitemDTO.setUnitOfSymbol(rsSet1.getString("tblunitofmeasure_uom_symbol"));  
//                }
//                listToReturn.add(rawStockitemDTO);
//            }
//        }
//        return listToReturn;
//    }
}

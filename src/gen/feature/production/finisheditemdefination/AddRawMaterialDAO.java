/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.finisheditemdefination;

import gen.account.StockItemFormation.StockItemDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.database.connection.DatabaseConnection1;
import gen.dto.StockItemTransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kasturi
 */
public class AddRawMaterialDAO {
    /*
     *  Function For Getting All StockItems 
     *  For ComboFinishItems  
     */

    public static Map<String, String> getAllFinishItems() throws SQLException {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";

        mainQuery = "select FinishedItem,si_name,si_id from tblstockitem Inner join tblproductdefinition On si_id = FinishedItem ";
        String query = mainQuery + condition;
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            System.out.println("---------------------------");
            mapToReturn.put(rsSet.getString("si_name"), rsSet.getLong("FinishedItem") + "");
        }
        rsSet.close();
        psmt.close();
        connection.close();
        return mapToReturn;
    }

    /*
     *  1 ) Function For Getting  StockgGroup Of FinishItems 
     *  by ComboFinishItem Value  
     */
    public static Integer getStockGroupOfFinishItem(Date date, String FinishItem) throws SQLException {
        String mainQuery = "";
        String condition = "";
        int parentGroup = 0;

        mainQuery = "SELECT tblstockitem.`si_id` AS tblstockitem_si_id,tblstockitem.`si_name` AS tblstockitem_si_name,tblstockitem.`si_under` AS tblstockitem_si_under, tblstockgroup.`sg_id` AS tblstockgroup_sg_id,tblstockgroup.`sg_name` AS tblstockgroup_sg_name FROM `tblstockgroup` tblstockgroup INNER JOIN `tblstockitem` tblstockitem ON tblstockgroup.`sg_id` = tblstockitem.`si_under`";
        condition = "where tblstockitem.`si_name` = '" + FinishItem + "'";

        String query = mainQuery + condition;

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            parentGroup = Integer.parseInt(rsSet.getString("tblstockgroup_sg_id"));
        }
        return parentGroup;
    }

    /*
     *  2 ) Function For Getting  SubStockgGroup Of FinishItems 
     *  by ComboFinishItem Value  
     */
    public static List<String> getFinishItemSubGroup(Date date, String FinishItem) throws SQLException {
        List<String> listToReturn = new ArrayList<String>();
        String mainQuery = "";
        String condition = "";

        mainQuery = "SELECT  rawitemgroupid from tblfinishitemgroupandsubgroup";
        condition = " where finishgroupid = ?";

        String query = mainQuery + condition;

        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        psmt.setString(1, FinishItem);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            listToReturn.add(rsSet.getString("rawitemgroupid"));
        }
        return listToReturn;
    }

    /*
     *   3 ) Function For Getting  StockgItems according to  
     *  list of SubGroup 
     */
    public static Map<String, String> getRawStockItemByFinishItems(Date date, List<String> FinishItem) throws SQLException {
        Map<String, String> listToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";
        for (int i = 0; i < FinishItem.size(); i++) {
            mainQuery = "SELECT tblstockitem.`si_id` AS tblstockitem_si_id,tblstockitem.`si_name` AS tblstockitem_si_name,tblstockitem.`si_under` AS tblstockitem_si_under, tblstockgroup.`sg_id` AS tblstockgroup_sg_id,tblstockgroup.`sg_name` AS tblstockgroup_sg_name FROM `tblstockgroup` tblstockgroup INNER JOIN `tblstockitem` tblstockitem ON tblstockgroup.`sg_id` = tblstockitem.`si_under`";
            condition = "where tblstockitem.`si_under` = '" + FinishItem.get(i) + "'";

            String query = mainQuery + condition;

            Connection connection = DatabaseConnection1.GetConnection();
            PreparedStatement psmt = connection.prepareStatement(query);
            ResultSet rsSet = psmt.executeQuery();
            while (rsSet.next()) {
                listToReturn.put(rsSet.getString("tblstockitem_si_name").toString(), rsSet.getString("tblstockitem_si_id").toString());
            }
        }
        return listToReturn;
    }

    /*
     *  Function For First Deleting value from tblProductionRawMaterial according to FinishedItem
     *  and then Inserting FinishedItem and RawMaterialStockItem 
     *  into the table tblProductionRawMaterial  
     */
    public static void upsert(List<AddRawMaterialDTO> rawMaterialDTOList, Boolean flag) throws Exception {
        if (flag) {
            update(rawMaterialDTOList);
        } else {
            insert(rawMaterialDTOList);
        }
    }

    public static void insert(List<AddRawMaterialDTO> rawMaterialDTOList) throws Exception {
        Long stk_under = 0l;
        System.out.println("Insert ---------------- ");
        if (rawMaterialDTOList != null) {
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);

            //get StockGroupUnder for finishitemdata
            String query = "Select si_under from tblstockitem ";
            String where = "";
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                where = "  where si_id=" + Long.parseLong(rawMaterialDTO.getFinishStockItem()) + "";
            }
            String mainquery = query + where;
            PreparedStatement psmt = connection.prepareStatement(mainquery);
            ResultSet rsSet1 = psmt.executeQuery();
            while (rsSet1.next()) {
                stk_under = rsSet1.getLong("si_under");
            }

            // insert into tblproductproduction
            String queryOne = "insert into tblproductdefinition(FinishedItem,date) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(queryOne, Statement.RETURN_GENERATED_KEYS);
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                ps.setString(1, rawMaterialDTO.getFinishStockItem());
                ps.setString(2, rawMaterialDTO.getDate());
                ps.addBatch();
            }
            if (rawMaterialDTOList != null && !rawMaterialDTOList.isEmpty()) {
                ps.executeBatch();
            }
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> idList = new ArrayList<Integer>();
            while (rs.next()) {
                idList.add(rs.getInt(1));
            }
            ps.close();

            //insert new data
            int i = 0;
            query = "insert into tblProductionRawMaterial(prod_def_id, RawItem , qty, FinishItemUnder ,date) values(?,?,?,?,?)";
            PreparedStatement psmt1 = connection.prepareStatement(query);
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                Integer fk = idList.get(i);
                int k = 0;
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : rawMaterialDTO.getListStockItemDTO()) {
                    psmt1.setLong(1, fk);
                    psmt1.setLong(2, rawStockItemDTO.getID());
                    psmt1.setDouble(3, rawStockItemDTO.getQuantity());
                    psmt1.setLong(4, stk_under);
                    psmt1.setString(5, rawMaterialDTO.getDate());
                    psmt1.addBatch();
                }
                psmt1.executeBatch();
                i++;
            }

            connection.commit();
            connection.close();
        }
    }

    public static void update(List<AddRawMaterialDTO> rawMaterialDTOList) throws Exception {
        System.out.println("update ---------------- ");
        Long stk_under = 0l;
        if (rawMaterialDTOList != null) {
            Connection connection = DatabaseConnection1.GetConnection();
            connection.setAutoCommit(false);

            //get StockGroupUnder for finishitemdata
            String query = "Select si_under from tblstockitem ";
            String where = "";
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                where = "  where si_id=" + Long.parseLong(rawMaterialDTO.getFinishStockItem()) + "";
            }
            String mainquery = query + where;
            PreparedStatement psmt = connection.prepareStatement(mainquery);
            ResultSet rsSet1 = psmt.executeQuery();
            while (rsSet1.next()) {
                stk_under = rsSet1.getLong("si_under");
            }

            String query_delete = "delete from tblProductionRawMaterial where prod_def_id = ?";
            PreparedStatement psmt_delete = connection.prepareStatement(query_delete);
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : rawMaterialDTO.getListStockItemDTO()) {
                    psmt_delete.setLong(1, rawStockItemDTO.getProduction_def_id());
                    System.out.println("Delete ID ------ " + rawStockItemDTO.getProduction_def_id());
                }
            }
            psmt_delete.executeUpdate();

            //insert new data
            int i = 0;
            query = "insert into tblProductionRawMaterial(prod_def_id, RawItem , qty, FinishItemUnder ,date) values(?,?,?,?,?)";
            PreparedStatement psmt1 = connection.prepareStatement(query);
            for (AddRawMaterialDTO rawMaterialDTO : rawMaterialDTOList) {
                int k = 0;
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : rawMaterialDTO.getListStockItemDTO()) {
                    psmt1.setLong(1, rawStockItemDTO.getProduction_def_id());
                    psmt1.setLong(2, rawStockItemDTO.getID());
                    psmt1.setDouble(3, rawStockItemDTO.getQuantity());
                    psmt1.setLong(4, stk_under);
                    psmt1.setString(5, rawMaterialDTO.getDate());
                    psmt1.addBatch();
                }
                psmt1.executeBatch();
                i++;
            }

            connection.commit();
            connection.close();
        }
    }

    public static List<gen.account.StockItemFormation.StockItemDTO> getAllRawStockItemsInfo(String finishItem, String date, Boolean isIn) throws Exception {
//        gen.account.StockItemFormation.StockItemDTO rawStockItemDTO = new gen.account.StockItemFormation.StockItemDTO();
        List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
        String mainQuery = "";
        String condition = "";

        mainQuery = "select tblproddef.FinishedItem,tblproddef.prod_def_id,tblprodraw.rawitem, tblsi.si_name,tblsi.si_id,tblprodraw.qty,tblproddef.date from  tblproductdefinition  as tblproddef Inner join  tblproductionrawmaterial as tblprodraw On  tblproddef.prod_def_id = tblprodraw.prod_def_id Inner join tblstockitem as tblsi On  tblsi.si_id = tblprodraw.rawitem ";
        condition = " where tblproddef.FinishedItem = ? and tblproddef.date = ? order by tblproddef.date desc";


        String query = mainQuery + condition;
        Connection connection = DatabaseConnection1.GetConnection();
        // if we got no date then go with entered date
        if(!getPreviousDateFinishItem(finishItem, date , connection ).isEmpty()){
             date = getPreviousDateFinishItem(finishItem, date , connection );
        }
        PreparedStatement psmt = connection.prepareStatement(query);
        psmt.setString(1, finishItem);
        System.out.println("Finsih Item -------- " + finishItem);
        System.out.println("Date -------- " + date);
        System.out.println("Query ----- " + query);
        
        System.out.println("date -------- "+date);
        
        psmt.setString(2, date);
        ResultSet rsSet = psmt.executeQuery();

        while (rsSet.next()) {
            gen.account.StockItemFormation.StockItemDTO stockitemdto = new StockItemDTO();
            stockitemdto.setName(rsSet.getString("si_name"));
            stockitemdto.setID(rsSet.getLong("si_id"));
            stockitemdto.setQuantity(rsSet.getDouble("qty"));
            stockitemdto.setProduction_def_id(rsSet.getLong("prod_def_id"));
            System.out.println("Getting Date -------- " + rsSet.getString("date"));
            stockitemdto.setStockitem_Date(rsSet.getString("date"));
            stockItemDTOList.add(stockitemdto);
        }

//        addRawMaterialDTO.setListStockItemDTO(stockItemDTOList);

        rsSet.close();
        psmt.close();
        connection.close();
        return stockItemDTOList;
    }

    //    public static Map<String, String> getAllStockItems(List<String> stockGroupNames, Boolean isIn) throws SQLException {
    public static Map<String, String> getAllStockItems(String finishItem, Boolean isIn) throws SQLException {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";

        if (!finishItem.isEmpty() && isIn == true) {
            mainQuery = "select FinishedItem,rawitem,si_name,si_id from tblstockitem Inner join tblproductionrawmaterial On si_id = rawitem Inner join tblproductdefinition on prod_def_id = prod_def_id";
            condition = " where FinishedItem = ?";
        } else {
            mainQuery = "select si_name,si_id from tblstockitem";
            condition = " where si_id != ?";
        }
        String query = mainQuery + condition;
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
//        if (!finishItem.isEmpty() && isIn == true) {
        psmt.setString(1, finishItem);
//        } else {
//            psmt.setString(1, finishItem);
//        }
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("si_name"), rsSet.getString("si_id"));
        }
        rsSet.close();
        psmt.close();
        connection.close();
        return mapToReturn;
    }

    public static String getPreviousDateFinishItem(String finishItem, String date,Connection connection) throws Exception {

        String returnDate = "";
        
        String mainQuery = "";
        String condition = "";

        mainQuery = "select tblproddef.date as proddefDate from  tblproductdefinition  as tblproddef Inner join  tblproductionrawmaterial as tblprodraw On  tblproddef.prod_def_id = tblprodraw.prod_def_id Inner join tblstockitem as tblsi On  tblsi.si_id = tblprodraw.rawitem ";
        condition = " where tblproddef.FinishedItem = ? and tblproddef.date <= ? order by tblproddef.date desc limit 1";

        String query = mainQuery + condition;
        PreparedStatement psmt = connection.prepareStatement(query);
        psmt.setString(1, finishItem);
        psmt.setString(2, date);
        System.out.println(" getPreviousDateFinishItem Query ----- " + query);
        ResultSet rsSet = psmt.executeQuery();

        while (rsSet.next()) {
           returnDate = rsSet.getString("proddefDate");
        }

        return returnDate;
    }
    public static void  getInfo() {
        
        try{
        List<String> listToReturn = new ArrayList<String>();
        String mainQuery = "";
        String condition = "";

        mainQuery = "SELECT  *  from tblproductdefinition";

        String query = mainQuery + condition;
            
        System.out.println("----------------           ");
        
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            System.out.println("----------------           "+rsSet.getString("prod_def_id"));
            System.out.println("---------------- "+rsSet.getString("FinishedItem"));
            System.out.println("---------------- "+rsSet.getString("date"));
//            listToReturn.add(rsSet.getString("prod_def_id"));
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

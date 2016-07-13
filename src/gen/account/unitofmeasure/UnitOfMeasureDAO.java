/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.unitofmeasure;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.Util;
import java.sql.Connection;
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
public class UnitOfMeasureDAO {

    public static void upsertUOM(List<UnitOfMeasureDTO> UOMDTOs) throws SQLException, ParseException, Exception {
        if (UOMDTOs != null && UOMDTOs.size() > 0) {
            List<UnitOfMeasureDTO> UOMDTOstoInsert = new ArrayList<UnitOfMeasureDTO>();
            List<UnitOfMeasureDTO> UOMDTOstoUpdate = new ArrayList<UnitOfMeasureDTO>();

            for (UnitOfMeasureDTO UOMDTO : UOMDTOs) {
                if (UOMDTO.getUnitOfMeasure_ID().trim().equalsIgnoreCase("")) {
                    UOMDTOstoInsert.add(UOMDTO);
                } else {
                    UOMDTOstoUpdate.add(UOMDTO);
                }
            }

            if (UOMDTOstoInsert.size() > 0) {
                insertUnitOfMeasure(UOMDTOstoInsert);
            }

            if (UOMDTOstoUpdate.size() > 0) {
                updateUOM(UOMDTOstoUpdate);
            }

        }

    }

    public static List<UnitOfMeasureDTO> getUnitOfMeasureList(Set<String> unitOfMeasure, String type) throws SQLException, Exception {

        List<UnitOfMeasureDTO> unitOfMeasureDTOList = new ArrayList<UnitOfMeasureDTO>();
        String query = "";
        String condition = "";
        Connection con = null;
        try {
            System.out.println("ssdfdfsdfsdfsdfdsfds--->>>" + unitOfMeasure);
            String UOMNameList = "";
            if (!Util.isEmpty(unitOfMeasure)) {

                if (type.equalsIgnoreCase(Constants.UNIT_MEASURE_NAME)) {
                    for (String group : unitOfMeasure) {
                        UOMNameList = "'" + group + "'" + "," + UOMNameList;
                    }
                    String groupNameList1 = UOMNameList.substring(0, UOMNameList.length() - 1);
                    condition = " where uom_formalName in(" + groupNameList1 + ")";
                } else if (type.equalsIgnoreCase(Constants.UNIT_MEASURE_ID)) {
                    for (String group : unitOfMeasure) {
                        UOMNameList = group + "," + UOMNameList;
                    }
                    String groupNameList1 = UOMNameList.substring(0, UOMNameList.length() - 1);
                    condition = " where uom_id in(" + groupNameList1 + ")";
                }
            }

            query = "select * from tblunitofmeasure " + condition;
            System.out.println("Query of UOM--->>>>" + query);
            con = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {

                UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();

                unitOfMeasureDTO.setUnitOfMeasure_FormalName(resultSet.getString("uom_formalName"));
                unitOfMeasureDTO.setUnitOfMeasure_NoDecimalPlaces(String.valueOf(resultSet.getInt("uom_noOfDecimalPts")));
                unitOfMeasureDTO.setUnitOfMeasure_Symbol(resultSet.getString("uom_symbol"));
                unitOfMeasureDTO.setUnitOfMeasure_Type(resultSet.getString("uomType_id"));
                unitOfMeasureDTO.setUnitOfMeasure_TypeName(resultSet.getString("uom_type"));
                unitOfMeasureDTO.setUnitOfMeasure_ID("" + resultSet.getInt("uom_id"));

                unitOfMeasureDTOList.add(unitOfMeasureDTO);
            }
        } catch (Exception ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            throw ex;
        }
        return unitOfMeasureDTOList;
    }

    public static Map<String, String> getUOMTypes() throws SQLException {
        Map<String, String> UOMTypeList = new HashMap<String, String>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "select uomType_id,uomType_name from tbluomtype";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                UOMTypeList.put(rs.getString("uomType_name"), rs.getString("uomType_id"));

            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return UOMTypeList;
    }

    public static void insertUnitOfMeasure(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String q = "insert into tblunitofmeasure(uomType_id,uom_type,uom_symbol,uom_formalName,uom_noOfDecimalPts)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q);
            for (UnitOfMeasureDTO unitofmeasureDTO : UnitOfMeasureDTOList) {
                ps.setString(1, unitofmeasureDTO.getUnitOfMeasure_Type().trim());
                ps.setString(2, unitofmeasureDTO.getUnitOfMeasure_TypeName().toString().trim());
                ps.setString(3, unitofmeasureDTO.getUnitOfMeasure_Symbol().toString().trim());
                ps.setString(4, unitofmeasureDTO.getUnitOfMeasure_FormalName().toString().trim());
                ps.setInt(5, Integer.parseInt(unitofmeasureDTO.getUnitOfMeasure_NoDecimalPlaces()));
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Insert function END");
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }

    }

    public static void updateUOM(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "update tblunitofmeasure set uom_type = ?,uom_symbol = ?, uom_formalName = ?, uom_noOfDecimalPts = ? where uom_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            for (UnitOfMeasureDTO unitofmeasureDTO : UnitOfMeasureDTOList) {
                ps.setString(1, unitofmeasureDTO.getUnitOfMeasure_TypeName().trim());
                ps.setString(2, unitofmeasureDTO.getUnitOfMeasure_Symbol().trim());
                ps.setString(3, unitofmeasureDTO.getUnitOfMeasure_FormalName().trim());
                ps.setInt(4, Integer.parseInt(unitofmeasureDTO.getUnitOfMeasure_NoDecimalPlaces()));
                ps.setInt(5, Integer.parseInt(unitofmeasureDTO.getUnitOfMeasure_ID()));
                ps.addBatch();
            }
            ps.executeBatch();

            ps.close();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public static boolean deleteUOM(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) throws SQLException, Exception {
        Connection conn = null;
        try {
            Integer select = 0;
            for (UnitOfMeasureDTO unitofmeasureDTO : UnitOfMeasureDTOList) {
                select = Integer.parseInt(unitofmeasureDTO.getUnitOfMeasure_ID());
            }
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select si_under from tblstockitem where si_unitOfMeasure=" + select + "");
            if (rs.next()) {
                return true;
            } else {
                st.executeUpdate("delete from tblunitofmeasure where uom_id = " + select + "");

            }
            conn.commit();
            conn.close();
        } catch (Exception ex) {

            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return false;
    }

    public static List<UnitOfMeasureDTO> exportAllUnitOfMeasures() {
        List<UnitOfMeasureDTO> unitOfMeasureDTOList = new ArrayList<UnitOfMeasureDTO>();
        Connection conn = null;
        try {

            String query = "select * from tblunitofmeasure";
            System.out.println("Query of UOM--->>>>" + query);
            conn = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {

                UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();

                unitOfMeasureDTO.setUnitOfMeasure_FormalName(resultSet.getString("uom_formalName"));
                unitOfMeasureDTO.setUnitOfMeasure_NoDecimalPlaces(String.valueOf(resultSet.getInt("uom_noOfDecimalPts")));
                unitOfMeasureDTO.setUnitOfMeasure_Symbol(resultSet.getString("uom_symbol"));
                unitOfMeasureDTO.setUnitOfMeasure_Type(resultSet.getString("uomType_id"));
                unitOfMeasureDTO.setUnitOfMeasure_TypeName(resultSet.getString("uom_type"));
                unitOfMeasureDTO.setUnitOfMeasure_ID("" + resultSet.getInt("uom_id"));

                unitOfMeasureDTOList.add(unitOfMeasureDTO);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UnitOfMeasureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unitOfMeasureDTOList;
    }

     public static Map<String, String> getUOMNameIDMap() throws SQLException {
        Map<String, String> UOMTypeList = new HashMap<String, String>();
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            String query = "select uom_symbol,uom_id from tblunitofmeasure";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                System.out.println("Get Unit of Measure -------------- "+rs.getString("uom_symbol"));
                System.out.println("Get Unit of Measure -------------- "+rs.getString("uom_id"));
                UOMTypeList.put(rs.getString("uom_symbol"), rs.getString("uom_id"));

            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
        return UOMTypeList;
    }

    public static String getUOMTypeID(Connection conn) throws Exception {
        String UOMTypeID = "";
        try {
//            Connection conn = null;
            String query = "select uomType_id from tbluomtype where uomType_name = 'Simple'";
            conn = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                UOMTypeID = "" + resultSet.getInt("uomType_id");
            }
            resultSet.close();
            preparedStmt.close();
//            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(UnitOfMeasureDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return UOMTypeID;
    }

    public static void insertSquareFeetUnitAsDefault(String UOMTypeID , Connection conn) throws Exception {

        try {
//            Connection conn = null;
            String query = "insert into tblunitofmeasure(uomType_id,uom_type,uom_symbol,uom_formalName,uom_noOfDecimalPts)values(?,?,?,?,?)";
            conn = DatabaseConnection1.GetConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, UOMTypeID);
            preparedStatement.setString(2, "Simple");
            preparedStatement.setString(3, "Sq.Ft");
            preparedStatement.setString(4, "Sq.Ft");
            preparedStatement.setInt(5, 0);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (Exception ex) {
            conn.close();
            ex.printStackTrace();
            Logger.getLogger(UnitOfMeasureDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}

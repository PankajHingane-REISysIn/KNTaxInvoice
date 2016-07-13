/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.AdSuMuDiSettingsOLD;

import gen.database.connection.DatabaseConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class AdSuMuDiSettingsDAO {

    public static void insert_Receipt_Settings(Map<String, String> passedMap) {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            PreparedStatement prpt;
            conn.setAutoCommit(false);
            String query = "";

            // query = "delete from tbladsumudisettings where name = 'receipt_Settings_Editable'";
            query = "delete from tbladsumudisettings";
            prpt = conn.prepareStatement(query);
            prpt.executeUpdate();

//            query = "update tbladsumudisettings set name=?,value=?";
	    query = "insert into tbladsumudisettings values(?,?)";
            prpt = conn.prepareStatement(query);
            for (Map.Entry<String, String> e : passedMap.entrySet()) {
                prpt.setString(1, e.getKey());
                prpt.setString(2, e.getValue());
                prpt.addBatch();
            }
            prpt.executeBatch();

            conn.commit();
            conn.close();
	} catch (Exception ex) {
	    ex.printStackTrace();
            Logger.getLogger(AdSuMuDiSettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Map<String, String> getting_CheckBoxValue() {
        Map<String, String> return_Map = new HashMap<String, String>();
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            PreparedStatement prpt;
            conn.setAutoCommit(false);
            String query = "";
            query = "Select * from tbladsumudisettings";
            PreparedStatement prmt = conn.prepareStatement(query);
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
                return_Map.put(rs.getString("name"), rs.getString("value"));
            }
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdSuMuDiSettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_Map;
    }
}

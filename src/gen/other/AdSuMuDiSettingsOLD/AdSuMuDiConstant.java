/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.AdSuMuDiSettingsOLD;


import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author admin
 */
public class AdSuMuDiConstant {

    public static String RECEIPT_SETTINGS = "receipt_Settings_Editable";
    public static String VOUCHER_NUMBER_SETTING = "Voucher_Number_Setting";
    public static String PAGINATION = "pagination";
    public static Map<String, Integer> map_checkBoxValue = new HashMap<String, Integer>();

    public static void getValuesFromTable() {
        map_checkBoxValue.clear();
        try {
            
            Connection conn = DatabaseConnection1.GetConnection();
            //String vouchersetting = "vouchersetting";
            //String query = "select value from tbladsumudisettings where name = '"+vouchersetting+"'";
            //conn.setAutoCommit(false);
            String query = "select * from tbladsumudisettings";
            PreparedStatement prmt = conn.prepareStatement(query);
            System.out.println("4444444444444444444444444444444444" + query + map_checkBoxValue.entrySet());
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
                map_checkBoxValue.put(rs.getString("name"), rs.getInt("value"));
		System.out.println("ttttttttttttt"+rs.getString("name"));
		System.out.println("ttttttttttttt"+rs.getInt("value"));
            }
            prmt.close();
            //conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.AdSuMuDiSettings;


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
    public static Map<String, Integer> map_Company_and_ID = new HashMap<String, Integer>();

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
	    }
	    prmt.close();
	    //conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
    }

//    public static void getCurrentCompany_Details() {
//	map_Company_and_ID.clear();
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection_old.GetConnection();
//	    conn.setAutoCommit(false);
//	    String query = "select * from tblcurrentcompany";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		map_Company_and_ID.put(rs.getString("company_name"), rs.getInt("company_id"));
//	    }
//	    prmt.close();
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	    conn.setAutoCommit(true);
//	    conn.close();
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	}
//    }
    public static gen.other.CompanySettings.CompanySettingsDTO getCurrentCompany_Details(String database_Name) {
	map_Company_and_ID.clear();
	gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO = new gen.other.CompanySettings.CompanySettingsDTO();
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "select * from tblcurrentcompany";
	    String where = " where company_database = ?"; 
	  
	    if(!database_Name.equals("")){
		   query = query + where;
	    }
	    
	    
	 
	    
	    System.out.println("Database ---------------- "+database_Name);
	    System.out.println("Query  ---------------- "+query);
	    
	    PreparedStatement prmt = conn.prepareStatement(query);
	    if(!database_Name.equals("")){
	    prmt.setString(1, database_Name);
	    }
	    ResultSet rs = prmt.executeQuery();
	    
	    while (rs.next()) {
		
		companySettingsDTO.setCompany_id(Long.parseLong(rs.getString("company_id")));
		companySettingsDTO.setCompany_name(rs.getString("company_name"));
		companySettingsDTO.setCompany_DatabaseName(rs.getString("company_Database"));
		//map_Company_and_ID.put(rs.getString("company_name"), rs.getInt("company_id"));
	    }
	    prmt.close();
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	    conn.setAutoCommit(true);
	    conn.close();
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return companySettingsDTO;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.login;

import gen.mainclass.UserSettingDTO;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class LoginDAO {

    public static List<UserSettingDTO> validete_User_For_Company(String company_id, String user_Name, String user_Password, String user_ID) {
	List<UserSettingDTO> UserSettingDTOList = new ArrayList<UserSettingDTO>();
	try {

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    ResultSet rs1;
            
	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    String subQuery = "select * from tbluserdetails";
	    String where = " where ";
	    String conditions_Username = " username = ?  ";
	    String conditions_Password = " and password=? ";
	    String conditions_Company_id = "   and company_id = ? ";
	    String conditions_User_id = " and id = ?";

	    String mainQuery = subQuery;
	    
	    if(!company_id.equals("") || !user_Name.equals("") || !user_Password.equals("") || !user_ID.equals("")){
		mainQuery = mainQuery + where;
	    }
	    if (!company_id.equals("")) {
		mainQuery = mainQuery + conditions_Company_id;
	    }
	    if (!user_Name.equals("")) {
		mainQuery = mainQuery + conditions_Username;
	    }
	    if (!user_Password.equals("")) {
		mainQuery = mainQuery + conditions_Password;
	    }
	    if (!user_ID.equals("")) {
		mainQuery = mainQuery + conditions_User_id;
	    }
     
//	    Statement s = conn.createStatement();
////	    ResultSet rsAllStuff = s.executeQuery("SELECT FirstName, LastName FROM Stuff ORDER BY LastName, FirstName");
//	    ResultSet rsAllStuff = s.executeQuery("select * from tbluserdetails");
//	    while (rsAllStuff.next()) {
//		System.out.println(rsAllStuff.getString("LastName") + " " + rsAllStuff.getString("FirstName"));
//	    }
	    
	    System.out.println("Query --------- "+mainQuery);
	    PreparedStatement prStat = conn.prepareStatement(mainQuery);
	
	    int i = 1;
	    if(!company_id.equals("")){
		 prStat.setString(i, company_id);
		 i++;
	    }
	    if (!user_Name.equals("")) {
		 prStat.setString(i, user_Name);
		 i++;
	    }
	    if (!user_Password.equals("")) {
		 prStat.setString(i, user_Password);
		 i++;
	    }
	    if (!user_ID.equals("")) {
		 prStat.setString(i, user_ID);
		 i++;
	    }
	    
	    rs1 = prStat.executeQuery();

	    while (rs1.next()) {

		UserSettingDTO usersettingDTO = new UserSettingDTO();
		usersettingDTO.setUser_id(rs1.getString("id"));
		usersettingDTO.setUser_Name(rs1.getString("username"));
		usersettingDTO.setUser_Password(rs1.getString("password"));
		usersettingDTO.setUser_Email_ID(rs1.getString("emailid"));
		usersettingDTO.setUser_Email_Password(rs1.getString("emaild_password"));
		usersettingDTO.setUser_Role(rs1.getInt("role_id"));
		usersettingDTO.setUser_Type(rs1.getInt("type"));
		UserSettingDTOList.add(usersettingDTO);
	    }
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return UserSettingDTOList;
    }
}

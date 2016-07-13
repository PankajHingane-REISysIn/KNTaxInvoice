///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package gen.mainclass;
//
//import gen.database.connection.DatabaseConnection1;
//import gen.other.CompanySettings.CompanySettingsDTO;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author admin
// */
//public class UserSettingDAO {
//
//    public static void insertUserSetting(List<UserSettingDTO> userSettingList) {
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//
//	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String query = "insert into tbluserdetails( username, password, emailid, emaild_password, role_id, type) values(?,?,?,?,?,?)";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    for (UserSettingDTO userSettingDTO : userSettingList) {
//		prmt.setString(1, userSettingDTO.getUser_Name());
//		prmt.setString(2, userSettingDTO.getUser_Password());
//		prmt.setString(3, userSettingDTO.getUser_Email_ID());
//		prmt.setString(4, userSettingDTO.getUser_Email_Password());
//		prmt.setInt(5, userSettingDTO.getUser_Role());
//		prmt.setInt(6, userSettingDTO.getUser_Type());
//		prmt.addBatch();
//	    }
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void insertUsertoCompany(List<CompanySettingsDTO> CompanySettingsDTOList, String user_ID) {
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//
//	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    String query = "";
//
//	    System.out.println("iNsert value -------------------------------");
//
//	    Long max_User_id = 0L;
//	    if (user_ID.equals("")) {
//		query = "SELECT max(id) as new_id from tbluserdetails ";
//		PreparedStatement prmt1 = conn.prepareStatement(query);
//		ResultSet rs = prmt1.executeQuery();
//		while (rs.next()) {
//		    max_User_id = rs.getLong("new_id");
//		}
//		rs.close();
//		prmt1.close();
//	    } else {
//
//		query = "delete from tblusertocompany where user_id = ?";
//		PreparedStatement prmt1 = conn.prepareStatement(query);
//		prmt1.setString(1, user_ID);
//		prmt1.executeUpdate();
//
//		max_User_id = Long.parseLong(user_ID);
//	    }
//
//	    System.out.println("rewrwe                                 " + max_User_id);
//
//	    query = "insert into tblusertocompany( user_id, company_id, company_name) values(?,? ,?)";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
//		prmt.setLong(1, max_User_id);
//		prmt.setString(3, companySettingsDTO.getCompany_name());
//		prmt.setString(2, companySettingsDTO.getCompany_id().toString());
//		prmt.addBatch();
//	    }
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void updateUserSetting_By_Admin(List<UserSettingDTO> userSettingList) {
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//
//	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String query = "update tbluserdetails set role_id = ?, type = ? where id = ?";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    for (UserSettingDTO userSettingDTO : userSettingList) {
//		prmt.setInt(1, userSettingDTO.getUser_Role());
//		prmt.setInt(2, userSettingDTO.getUser_Type());
//		prmt.setString(3, userSettingDTO.getUser_id());
//		System.out.println("Update the value ------------------      " + userSettingDTO.getUser_id());
//		System.out.println("userSettingDTO.getUser_Role() ------------------      " + userSettingDTO.getUser_Role());
//		prmt.addBatch();
//	    }
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void updateUserSetting_By_User(List<UserSettingDTO> userSettingList) {
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//
//	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String query = "update tbluserdetails set username = ?, password = ?, emailid = ?, emaild_password = ? where id = ?";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    for (UserSettingDTO userSettingDTO : userSettingList) {
//		prmt.setString(1, userSettingDTO.getUser_Name());
//		prmt.setString(2, userSettingDTO.getUser_Password());
//		prmt.setString(3, userSettingDTO.getUser_Email_ID());
//		prmt.setString(4, userSettingDTO.getUser_Email_Password());
//		prmt.setString(5, gen.dto.Constants.CURRENT_USER_ID);
//		System.out.println("Update the value ------------------      " + userSettingDTO.getUser_Password());
//		System.out.println("userSettingDTO.getUser_Role() ------------------      " + userSettingDTO.getUser_Name());
//		System.out.println("userSettingDTO.gen.dto.Constants.CURRENT_USER_ID  ------------------      " + gen.dto.Constants.CURRENT_USER_ID);
//		prmt.addBatch();
//	    }
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
////    public static List<CompanySettingsDTO> get_Company_List_By_User(List<String> user_IDList) {
////	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
////	try {
////	    Connection conn = DatabaseConnection_old.GetConnection();
////
////	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
////	    conn.setAutoCommit(false);
////	    String subQuery = "Select * from tblusertocompany";
////	    String condition = " where user_id = ";
////	    String query = subQuery + condition;
////
////	    PreparedStatement prmt = conn.prepareStatement(query);
////	    String user_id = "";
////	    for (String user_ID : user_IDList) {
////		user_id = user_ID;
////	    }
////	    ResultSet rs = prmt.executeQuery();
////	    while (rs.next()) {
////		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
////		companySettingsDTO.setCompany_id(rs.getLong("company_id"));
////		companySettingsDTOList.add(companySettingsDTO);
////	    }
////	    rs.close();
////	    prmt.executeBatch();
////	    prmt.close();
////	    conn.commit();
////	    conn.close();
////	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
////
////	} catch (SQLException ex) {
////	    ex.printStackTrace();
////	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
////	}
////	return companySettingsDTOList;
////    }
//    public static Map get_Role_ID(String role, String role_ID) {
//	Map<String, String> map_Role_ID = new HashMap<String, String>();
//	try {
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String subQuery = "Select * from tblrole  ";
//	    String where = " where ";
//	    String and = "  and ";
//	    String condition_For_Role_ID = "  role_id = ?";
//	    String condition_For_Role = " role = ?";
//
//	    String mainQuery = subQuery;
//
//	    if (!role.equals("") && !role_ID.equals("")) {
//		mainQuery = mainQuery + where + condition_For_Role + and + condition_For_Role_ID;
//	    } else if (!role_ID.equals("") || !role.equals("")) {
//		if (!role.equals("")) {
//		    mainQuery = mainQuery + where + condition_For_Role;
//		} else if (!role_ID.equals("")) {
//		    mainQuery = mainQuery + where + condition_For_Role_ID;
//		}
//	    }
//
//	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
//
//	    int check_condition = 1;
//	    if (!role.equals("")) {
//		prmt.setString(check_condition, role);
//		check_condition++;
//	    }
//	    if (!role_ID.equals("")) {
//		prmt.setString(check_condition, role_ID);
//	    }
//
//	    System.out.println("Query for Role ID ::::::::::::::         " + mainQuery);
//
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		map_Role_ID.put(rs.getString("role"), rs.getString("id"));
//	    }
//	    rs.close();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return map_Role_ID;
//    }
//
////    public static void updateUsertoCompany(List<CompanySettingsDTO> CompanySettingsDTOList, String user_ID) {
////	try {
////	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
////
////	    Connection conn = gen.database.connection.DatabaseConnection_old.GetConnection();
////	    conn.setAutoCommit(false);
////
////	    String query = "delete from tblusertocompany where user_id = ?";
////	    PreparedStatement prmt1 = conn.prepareStatement(query);
////	    prmt1.setString(1, user_ID);
////	    prmt1.executeUpdate();
////
////	    query = "update tblusertocompany set company_id = ?, company_name = ? where user_id = ?";
////	    PreparedStatement prmt = conn.prepareStatement(query);
////	    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
////		prmt.setString(3, user_ID);
////		prmt.setString(2, companySettingsDTO.getCompany_name());
////		prmt.setString(1, companySettingsDTO.getCompany_id().toString());
////		prmt.addBatch();
////	    }
////	    prmt.executeBatch();
////	    prmt.close();
////	    conn.commit();
////	    conn.close();
////
////	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
////	} catch (SQLException ex) {
////	    ex.printStackTrace();
////	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
////	}
////    }
//    public static List<UserSettingDTO> get_User_Details(String user_ID, String user_Name) {
//	List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String subQuery = "Select * from tbluserdetails  ";
//	    String where = " where ";
//	    String condition_For_Role_ID = "  id = ?";
//	    String and = "  and ";
//	    String condition_For_Role = " username = ?";
//
//	    String mainQuery = subQuery;
//
//	    if (!user_Name.equals("") && !user_ID.equals("")) {
//		mainQuery = mainQuery + where + condition_For_Role + and + condition_For_Role_ID;
//	    } else if (!user_ID.equals("") || !user_Name.equals("")) {
//		if (!user_Name.equals("")) {
//		    mainQuery = mainQuery + where + condition_For_Role;
//		} else if (!user_ID.equals("")) {
//		    mainQuery = mainQuery + where + condition_For_Role_ID;
//		}
//	    }
//
//	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
//
//	    int check_condition = 1;
//	    if (!user_Name.equals("")) {
//		prmt.setString(check_condition, user_Name);
//		check_condition++;
//	    }
//	    if (!user_ID.equals("")) {
//		prmt.setString(check_condition, user_ID);
//	    }
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		UserSettingDTO usersettingDTO = new UserSettingDTO();
//		usersettingDTO.setUser_id(rs.getString("id"));
//		usersettingDTO.setUser_Name(rs.getString("username"));
//		usersettingDTO.setUser_Password(rs.getString("password"));
//		usersettingDTO.setUser_Email_ID(rs.getString("emailid"));
//		usersettingDTO.setUser_Email_Password(rs.getString("emaild_password"));
//		usersettingDTO.setUser_Role(rs.getInt("role_id"));
//		usersettingDTO.setUser_Type(rs.getInt("type"));
//		userSettingDTOList.add(usersettingDTO);
//	    }
//	    rs.close();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return userSettingDTOList;
//    }
//
//    public static List<CompanySettingsDTO> getCurrent_Company_Details_By_User(UserSettingDTO userSettingDTO) {
//	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String subQuery = "Select * from tblusertocompany  ";
//	    String where = " where user_id = ?";
//	    String mainQuery = subQuery + where;
//	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
//	    prmt.setString(1, userSettingDTO.getUser_id());
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
//		companySettingsDTO.setCompany_name(rs.getString("company_name"));
//		companySettingsDTO.setCompany_id(Long.parseLong(rs.getString("company_id")));
//		companySettingsDTOList.add(companySettingsDTO);
//	    }
//	    rs.close();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return companySettingsDTOList;
//    }
//
//    public static List<CompanySettingsDTO> getCurrentCompany_UserID(String user_ID) {
//	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
//
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//	    String subQuery = "Select * from tblcurrentcompany";
//	    String where = " where user_id = ?";
//	    String mainQuery = subQuery + where;
//	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
//	    prmt.setString(1, user_ID);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
//		companySettingsDTO.setCompany_name(rs.getString("company_name"));
//		companySettingsDTO.setCompany_id(Long.parseLong(rs.getString("company_id")));
//		companySettingsDTO.setCompany_DatabaseName(rs.getString("company_Database"));
//		companySettingsDTOList.add(companySettingsDTO);
//	    }
//	    rs.close();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//
//	return companySettingsDTOList;
//    }
//
//    public static FeaturesDTO get_Active_Inactive_By_User(String user_ID) {
//	gen.mainclass.FeaturesDTO featuresDTO = new FeaturesDTO();
//	List<String> featuresList = new ArrayList<String>();
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    Connection conn = DatabaseConnection1.GetConnection();
//	    String query = "select f.feature,r.id As role_id from tblrole as r inner join tbluserdetails as ud  on r.id = ud.role_id inner join tblrolefeatures as rf on r.id = rf.role_id inner join tblfeature as f on rf.feature_id = f.id  where ud.id = ? and isactive = 1 order by feature Asc";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    conn.setAutoCommit(false);
//	    prmt.setString(1, user_ID);
//	    
//	    ResultSet rs = prmt.executeQuery();
//	    
////	    int i = 0;
//	    while (rs.next()) {
//		System.out.println("Features -----------------------------" + rs.getString("feature"));
//		featuresDTO.setRoleID(rs.getString("role_id"));
//		
//		featuresList.add(rs.getString("feature"));
//		
////		if (i == 0) {
////		    featuresDTO.setCHALLAN(rs.getString("feature"));
////		}
////		if (i == 1) {
////		    featuresDTO.setCompanySettings(query);
////		}
////		if (i == 2) {
////		    featuresDTO.setContra(query);
////		}
////		if (i == 3) {
////		    featuresDTO.setGroup(query);
////		}
////		if (i == 4) {
////		    featuresDTO.setLedger(query);
////		}
////		if (i == 5) {
////		     featuresDTO.setNetworkSettings(query);
////		}
////		if (i == 6) {
////		     featuresDTO.setPayment(query);
////		}
////		if (i == 7) {
////		     featuresDTO.setPurchase(query);
////		}
////		if (i == 8) {
////		     featuresDTO.setReceipt(query);
////		}
////		if (i == 9) {
////		     featuresDTO.setReport(query);
////		}
////		if (i == 10) {
////		     featuresDTO.setSale(query);
////		}
////		if (i == 11) {
////		     featuresDTO.setStockGroup(query);
////		}
////		if (i == 12) {
////		     featuresDTO.setStockItem(query);
////		}
////		if (i == 13) {
////		     featuresDTO.setUnitOfmeasure(query);
////		}
//	    }
//	    featuresDTO.setFeatureList(featuresList);
//	    rs.close();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return featuresDTO;
//    }
//}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

import gen.database.connection.DatabaseConnection1;
import gen.other.CompanySettings.CompanySettingsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserSettingDAO {

    public static void insertUserSetting(List<UserSettingDTO> userSettingList) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "insert into tbluserdetails( username, password, emailid, emaild_password, role_id, type) values(?,?,?,?,?,?)";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    for (UserSettingDTO userSettingDTO : userSettingList) {
		prmt.setString(1, userSettingDTO.getUser_Name());
		prmt.setString(2, userSettingDTO.getUser_Password());
		prmt.setString(3, userSettingDTO.getUser_Email_ID());
		prmt.setString(4, userSettingDTO.getUser_Email_Password());
		prmt.setInt(5, 1);
		prmt.setInt(6, userSettingDTO.getUser_Type());
		prmt.addBatch();
	    }
	    prmt.executeBatch();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static void insertUserRoles(List<UserSettingDTO> userSettingList) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    Long max_User_id = 0L;
	    String user_ID = "";
	    
	    for (UserSettingDTO userSettingDTO : userSettingList) {
		user_ID = userSettingDTO.getUser_id();
	    }

	    if (user_ID.equals("")) {
		System.out.println("In IF ----------------");
		query = "SELECT max(id) as new_id from tbluserdetails ";
		PreparedStatement prmt1 = conn.prepareStatement(query);
		ResultSet rs = prmt1.executeQuery();
		while (rs.next()) {
		    max_User_id = rs.getLong("new_id");
		    System.out.println("Max-id - = " + max_User_id);
		}
		rs.close();
		prmt1.close();
	    } else {
		System.out.println("In else ----------------");
		query = "delete from tblusertorole where user_id = ?";
		PreparedStatement prmt1 = conn.prepareStatement(query);
		for (UserSettingDTO userSettingDTO : userSettingList) {
		    prmt1.setString(1, userSettingDTO.getUser_id());
		    max_User_id = Long.parseLong(userSettingDTO.getUser_id());
		}
		prmt1.executeUpdate();
		prmt1.close();

	    }
	    System.out.println("--------------------------" + userSettingList.size());
	    System.out.println("Max - id ----------------=" + max_User_id);
	    query = "insert into tblusertorole(user_id,role_id) values(?,?)";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    for (UserSettingDTO userSettingDTO : userSettingList) {
		System.out.println("dfgfgfgdfgfdgfg" + userSettingDTO.getUser_id());
		for (FeaturesDTO featuresDTO : userSettingDTO.getFeaturesDTOList()) {
		    prmt.setLong(1, max_User_id);
		    System.out.println("user id ======== " + userSettingDTO.getUser_id());
		    System.out.println("Feature id =====" + featuresDTO.getRoleID());
		    prmt.setString(2, featuresDTO.getRoleID());
		    prmt.addBatch();
		}
	    }
	    prmt.executeBatch();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static void insertUsertoCompany(List<CompanySettingsDTO> CompanySettingsDTOList, String user_ID) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "";
	    System.out.println("iNsert value -------------------------------");
	    Long max_User_id = 0L;
	    if (user_ID.equals("")) {
		query = "SELECT max(id) as new_id from tbluserdetails ";
		PreparedStatement prmt1 = conn.prepareStatement(query);
		ResultSet rs = prmt1.executeQuery();
		while (rs.next()) {
		    max_User_id = rs.getLong("new_id");
		}
		rs.close();
		prmt1.close();
	    } else {
		query = "delete from tblusertocompany where user_id = ?";
		PreparedStatement prmt1 = conn.prepareStatement(query);
		prmt1.setString(1, user_ID);
		prmt1.executeUpdate();
		max_User_id = Long.parseLong(user_ID);
	    }

	    query = "insert into tblusertocompany( user_id, company_id, company_name) values(?,? ,?)";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
		prmt.setLong(1, max_User_id);
		prmt.setString(3, companySettingsDTO.getCompany_name());
		prmt.setString(2, companySettingsDTO.getCompany_id().toString());
		prmt.addBatch();
	    }
	    prmt.executeBatch();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static void updateUserSetting_By_Admin(List<UserSettingDTO> userSettingList) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "update tbluserdetails set role_id = ?, type = ? where id = ?";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    for (UserSettingDTO userSettingDTO : userSettingList) {
		prmt.setInt(1, 1);
		prmt.setInt(2, userSettingDTO.getUser_Type());
		prmt.setString(3, userSettingDTO.getUser_id());
		System.out.println("Update the value ------------------      " + userSettingDTO.getUser_id());
		System.out.println("userSettingDTO.getUser_Role() ------------------      " + userSettingDTO.getUser_Role());
		prmt.addBatch();
	    }
	    prmt.executeBatch();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static void updateUserSetting_By_User(List<UserSettingDTO> userSettingList) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String query = "update tbluserdetails set username = ?, password = ?, emailid = ?, emaild_password = ? where id = ?";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    for (UserSettingDTO userSettingDTO : userSettingList) {
		prmt.setString(1, userSettingDTO.getUser_Name());
		prmt.setString(2, userSettingDTO.getUser_Password());
		prmt.setString(3, userSettingDTO.getUser_Email_ID());
		prmt.setString(4, userSettingDTO.getUser_Email_Password());
		prmt.setString(5, gen.dto.Constants.CURRENT_USER_ID);
		System.out.println("Update the value ------------------      " + userSettingDTO.getUser_Password());
		System.out.println("userSettingDTO.getUser_Role() ------------------      " + userSettingDTO.getUser_Name());
		System.out.println("userSettingDTO.gen.dto.Constants.CURRENT_USER_ID  ------------------      " + gen.dto.Constants.CURRENT_USER_ID);
		prmt.addBatch();
	    }
	    prmt.executeBatch();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

//    public static List<CompanySettingsDTO> get_Company_List_By_User(List<String> user_IDList) {
//	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
//	try {
//	    Connection conn = DatabaseConnection1.GetConnection();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//	    conn.setAutoCommit(false);
//	    String subQuery = "Select * from tblusertocompany";
//	    String condition = " where user_id = ";
//	    String query = subQuery + condition;
//
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    String user_id = "";
//	    for (String user_ID : user_IDList) {
//		user_id = user_ID;
//	    }
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
//		companySettingsDTO.setCompany_id(rs.getLong("company_id"));
//		companySettingsDTOList.add(companySettingsDTO);
//	    }
//	    rs.close();
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	return companySettingsDTOList;
//    }
    public static Map get_Role_ID(String role, String role_ID) {
	Map<String, String> map_Role_ID = new HashMap<String, String>();
	try {

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "Select * from tblrole  ";
	    String where = " where ";
	    String and = "  and ";
	    String condition_For_Role_ID = "  role_id = ?";
	    String condition_For_Role = " role = ?";

	    String mainQuery = subQuery;

	    if (!role.equals("") && !role_ID.equals("")) {
		mainQuery = mainQuery + where + condition_For_Role + and + condition_For_Role_ID;
	    } else if (!role_ID.equals("") || !role.equals("")) {
		if (!role.equals("")) {
		    mainQuery = mainQuery + where + condition_For_Role;
		} else if (!role_ID.equals("")) {
		    mainQuery = mainQuery + where + condition_For_Role_ID;
		}
	    }

	    PreparedStatement prmt = conn.prepareStatement(mainQuery);

	    int check_condition = 1;
	    if (!role.equals("")) {
		prmt.setString(check_condition, role);
		check_condition++;
	    }
	    if (!role_ID.equals("")) {
		prmt.setString(check_condition, role_ID);
	    }

	    System.out.println("Query for Role ID ::::::::::::::         " + mainQuery);

	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		map_Role_ID.put(rs.getString("role"), rs.getString("id"));
	    }
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return map_Role_ID;
    }

//    public static void updateUsertoCompany(List<CompanySettingsDTO> CompanySettingsDTOList, String user_ID) {
//	try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
//
//	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
//	    conn.setAutoCommit(false);
//
//	    String query = "delete from tblusertocompany where user_id = ?";
//	    PreparedStatement prmt1 = conn.prepareStatement(query);
//	    prmt1.setString(1, user_ID);
//	    prmt1.executeUpdate();
//
//	    query = "update tblusertocompany set company_id = ?, company_name = ? where user_id = ?";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOList) {
//		prmt.setString(3, user_ID);
//		prmt.setString(2, companySettingsDTO.getCompany_name());
//		prmt.setString(1, companySettingsDTO.getCompany_id().toString());
//		prmt.addBatch();
//	    }
//	    prmt.executeBatch();
//	    prmt.close();
//	    conn.commit();
//	    conn.close();
//
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
    public static List<UserSettingDTO> get_User_Details(String user_ID, String user_Name) {
	List<UserSettingDTO> userSettingDTOList = new ArrayList<UserSettingDTO>();
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "Select * from tbluserdetails  ";
	    String where = " where ";
	    String condition_For_Role_ID = "  id = ?";
	    String and = "  and ";
	    String condition_For_Role = " username = ?";

	    String mainQuery = subQuery;

	    if (!user_Name.equals("") && !user_ID.equals("")) {
		mainQuery = mainQuery + where + condition_For_Role + and + condition_For_Role_ID;
	    } else if (!user_ID.equals("") || !user_Name.equals("")) {
		if (!user_Name.equals("")) {
		    mainQuery = mainQuery + where + condition_For_Role;
		} else if (!user_ID.equals("")) {
		    mainQuery = mainQuery + where + condition_For_Role_ID;
		}
	    }

	    System.out.println("Main Query in User Setting DAO ------------    -----------   " + mainQuery);
	    System.out.println("User Name  ------------    ----------- " + user_Name);
	    // find feature List for this user_id
	    
	    
	    PreparedStatement prmt = conn.prepareStatement(mainQuery);

	    int check_condition = 1;
	    if (!user_Name.equals("")) {
		prmt.setString(check_condition, user_Name);
		check_condition++;
	    }
	    if (!user_ID.equals("")) {
		prmt.setString(check_condition, user_ID);
	    }
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		UserSettingDTO usersettingDTO = new UserSettingDTO();
		usersettingDTO.setUser_id(rs.getString("id"));
		usersettingDTO.setUser_Name(rs.getString("username"));
		usersettingDTO.setUser_Password(rs.getString("password"));
		usersettingDTO.setUser_Email_ID(rs.getString("emailid"));
		usersettingDTO.setUser_Email_Password(rs.getString("emaild_password"));
		usersettingDTO.setUser_Role(rs.getInt("role_id"));
		usersettingDTO.setUser_Type(rs.getInt("type"));
		userSettingDTOList.add(usersettingDTO);
	    }
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return userSettingDTOList;
    }

    public static List<CompanySettingsDTO> getCurrent_Company_Details_By_User(UserSettingDTO userSettingDTO) {
	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "Select * from tblusertocompany  ";
	    String where = " where user_id = ?";
	    String mainQuery = subQuery + where;
	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
	    prmt.setString(1, userSettingDTO.getUser_id());
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
		companySettingsDTO.setCompany_name(rs.getString("company_name"));
		companySettingsDTO.setCompany_id(Long.parseLong(rs.getString("company_id")));
		companySettingsDTOList.add(companySettingsDTO);
	    }
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return companySettingsDTOList;
    }

    public static List<CompanySettingsDTO> getCurrentCompany_UserID(String user_ID) {
	List<CompanySettingsDTO> companySettingsDTOList = new ArrayList<CompanySettingsDTO>();

	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "Select * from tblcurrentcompany";
	    String where = " where user_id = ?";
	    String mainQuery = subQuery + where;
	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
	    prmt.setString(1, user_ID);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		CompanySettingsDTO companySettingsDTO = new CompanySettingsDTO();
		companySettingsDTO.setCompany_name(rs.getString("company_name"));
		companySettingsDTO.setCompany_id(Long.parseLong(rs.getString("company_id")));
		companySettingsDTO.setCompany_DatabaseName(rs.getString("company_Database"));
		companySettingsDTOList.add(companySettingsDTO);
	    }
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}

	return companySettingsDTOList;
    }

    public static FeaturesDTO get_Active_Inactive_By_User(String user_ID) {
	gen.mainclass.FeaturesDTO featuresDTO = new FeaturesDTO();
	List<String> featuresList = new ArrayList<String>();

	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    String query = "select f.feature,r.id As role_id , r.role as role_Name from tblrole as r inner join tblusertorole as ud  on r.id = ud.role_id inner join tblrolefeatures as rf on r.id = rf.role_id inner join tblfeature as f on rf.feature_id = f.id  where ud.user_id = ? and isactive = 1 order by feature Asc";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    conn.setAutoCommit(false);
	    prmt.setString(1, user_ID);
	    System.out.println("Query in get Active Inactive Companonnent" + query);

	    ResultSet rs = prmt.executeQuery();

	    while (rs.next()) {
		System.out.println("Features -----------------------------" + rs.getString("feature"));
		featuresDTO.setRoleID(rs.getString("role_id"));
		featuresDTO.setRole_Name(rs.getString("role_Name"));
		featuresList.add(rs.getString("feature"));
	    }
	    featuresDTO.setFeatureList(featuresList);
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();
           
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
             System.out.println("11111111111111111111111111111                   "+gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING);
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return featuresDTO;
    }
    
    public static List<FeaturesDTO> getRoleByUsers(String user_ID ){
	List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "select r.id as role_id,r.role as role_Name from tblrole as r Inner join tblusertorole as ur ON r.id = ur.role_id ";
	    String where = " where ur.user_id = ?";
	    String mainQuery = subQuery + where;
	    PreparedStatement prmt = conn.prepareStatement(mainQuery);
	    prmt.setString(1, user_ID);
	    ResultSet rs = prmt.executeQuery();
	    while (rs.next()) {
		FeaturesDTO featuresDTO = new FeaturesDTO();
		featuresDTO.setRoleID(rs.getString("role_id"));
		featuresDTO.setRole_Name(rs.getString("role_Name"));
		System.out.println("Role -----------   "+rs.getString("role_Name"));
		featuresDTOList.add(featuresDTO);
	    }
	    rs.close();
	    prmt.close();
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	
 	return featuresDTOList;
    }
    
    public static void Delete_Users(List<UserSettingDTO> userSettingList) {
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

	    Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);

	  
	    
	    for (UserSettingDTO userSettingDTO : userSettingList) {

		System.out.println("$$$$$$$$$$$$$$$$        $$$$$$$$$        ");
		
		String query = "delete from tblcurrentcompany where user_id = ? ";
		PreparedStatement prmt = conn.prepareStatement(query);
		prmt.setString(1, userSettingDTO.getUser_id());
		prmt.executeUpdate();

		query = "delete from tblusertorole where user_id = ? ";
		prmt = conn.prepareStatement(query);
		prmt.setString(1, userSettingDTO.getUser_id());
		prmt.executeUpdate();

		query = "delete from tblusertocompany where user_id = ? ";
		prmt = conn.prepareStatement(query);
		prmt.setString(1, userSettingDTO.getUser_id());
		prmt.executeUpdate();

		query = "delete from tbluserdetails where  id = ? ";
		prmt = conn.prepareStatement(query);
		prmt.setString(1, userSettingDTO.getUser_id());
		prmt.executeUpdate();

		 prmt.close();
	    }
	   
	    conn.commit();
	    conn.close();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(UserSettingDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}

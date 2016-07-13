/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.CompanySettings;

import gen.database.connection.DatabaseConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CompanySettingsDAO {

    //Get all company_List
    /**
     *
     * @param company_Name
     * @param company_Name
     * @return
     */
    public static List<gen.other.CompanySettings.CompanySettingsDTO> getComapany_List(String company_Name, String company_ID) {
        List<gen.other.CompanySettings.CompanySettingsDTO> CompanySettingsDTOList = new ArrayList<gen.other.CompanySettings.CompanySettingsDTO>();
        try {
            //set COMPANYDETAILS_DATABASE_CALLING = 1 for selection of Database comany_profile
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String primary_query = "select * from tblcompanyinfo";
            String condition = "";
            if (!company_Name.equals("") && company_Name != null) {
                condition = "  where company_name = '" + company_Name + "' and company_id = " + company_ID + "";
            }
            String query = primary_query + condition;

            System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIII       " + query);
            PreparedStatement prmt = conn.prepareStatement(query);
            ResultSet rs = prmt.executeQuery();

            while (rs.next()) {
                gen.other.CompanySettings.CompanySettingsDTO companySettingsDTO = new gen.other.CompanySettings.CompanySettingsDTO();
                companySettingsDTO.setNameOfGod(rs.getString("NameOfGod"));
                System.out.println("From List company_id------------"+rs.getString("company_id"));
                companySettingsDTO.setCompany_id(rs.getLong("company_id"));
                System.out.println("From List Company Nmae------------"+rs.getString("company_name"));
                companySettingsDTO.setCompany_name(rs.getString("company_name"));
                System.out.println("From List Database Nmae------------"+rs.getString("company_database"));
                companySettingsDTO.setCompany_DatabaseName(rs.getString("company_database"));
                companySettingsDTO.setcompany_Created_by(rs.getString("created_by"));
                companySettingsDTO.setcompany_Modified_by(rs.getString("modified_by"));
                companySettingsDTO.setcompany_Created_date(rs.getString("created_date"));
                companySettingsDTO.setcompany_Modified_date(rs.getString("modified_date"));
                companySettingsDTO.setcompany_Alias(rs.getString("Alias"));
                companySettingsDTO.setcompany_Address(rs.getString("Address"));
                companySettingsDTO.setcompany_ContactNo(rs.getString("ContactNo"));
                companySettingsDTO.setcompany_EmailId(rs.getString("EmailId"));
                companySettingsDTO.setcompany_IncomeTaxNo(rs.getString("IncomeTaxNo"));
                companySettingsDTO.setcompany_SaleTaxNo(rs.getString("SaleTaxNo"));
                companySettingsDTO.setcompany_Declaration(rs.getString("Declaration"));
                companySettingsDTO.setcompany_TagLine(rs.getString("TagLine"));
                companySettingsDTO.setcompany_TermCnditions(rs.getString("TermCnditions"));
                companySettingsDTO.setcompany_SignAuthority(rs.getString("SignAuthority"));
                companySettingsDTO.setcompany_VatTinNo(rs.getString("VatTinNo"));
                companySettingsDTO.setcompany_Note(rs.getString("Note"));
                System.out.println("LBTNo chkout-->>>" + rs.getString("Note"));
                companySettingsDTO.setcompany_LBTNo(rs.getString("LBTNo"));
                companySettingsDTO.setcompany_Field1(rs.getString("Field1"));
                companySettingsDTO.setcompany_Field2(rs.getString("Field2"));
                companySettingsDTO.setCompany_ApplicableFrom_date(rs.getString("applicable_From_date"));
                CompanySettingsDTOList.add(companySettingsDTO);
            }

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            prmt.close();
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(gen.other.CompanySettings.CompanySettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CompanySettingsDTOList;
    }

    //Get MAx Company ID to ADD in New Database of Company
    public static String get_Max_Company_ID() {
        //Long max_Company_ID = 0L;
        String return_Max_Company_ID = "";
        System.out.println("Get max Company _ id ------------------ ");
        try {
            //set COMPANYDETAILS_DATABASE_CALLING = 1 for selection of Database comany_profile
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";

            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String primary_query = "";
            if (gen.dto.Constants.DATABASE_SERVER.equals("com.mysql.jdbc.Driver")) {
                primary_query = "SHOW TABLE STATUS WHERE `Name` = 'tblcompanyinfo'";
            } else {
                primary_query = "select max(company_id) as max_id from tblcompanyinfo";
            }
            PreparedStatement prmt = conn.prepareStatement(primary_query);
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
                if (gen.dto.Constants.DATABASE_SERVER.equals("com.mysql.jdbc.Driver")) {
                    return_Max_Company_ID = rs.getString("Auto_increment");
                } else {
                    Long max_id = rs.getLong("max_id");
//		    max_id = max_id + 1;
                    return_Max_Company_ID = max_id.toString();
                }
            }
            // max_Company_ID = max_Company_ID + 1;
            //return_Max_Company_ID = return_Max_Company_ID.toString().trim();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(gen.other.CompanySettings.CompanySettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return return_Max_Company_ID;
    }

    // into insert_Current_Company
    public static void insert_Current_Company(String company_Name, String company_id) {
        try {
            //set COMPANYDETAILS_DATABASE_CALLING = 1 for selection of Database comany_profile
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();

            conn.setAutoCommit(false);
//	    String query = "delete from  tblcurrentcompany where user_id = ?";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    prmt.setString(1, gen.dto.Constants.CURRENT_USER_ID);
//	    prmt.executeUpdate();


//	    Boolean flag_Insert_or_Delete = true;
//	    String query = "select * from tblcurrentcompany where user_id = ?";
//	    PreparedStatement prmt = conn.prepareStatement(query);
//	    prmt.setString(1, gen.dto.Constants.CURRENT_USER_ID);
//	    ResultSet rs = prmt.executeQuery();
//	    while (rs.next()) {
//		flag_Insert_or_Delete = false;
//	    }

            String database_Name = "";
            String query = "select company_database from  tblcompanyinfo where company_name = ? and company_id = ?";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, company_Name);
            prmt.setInt(2, Integer.parseInt(company_id));
            ResultSet rs1 = prmt.executeQuery();
            while (rs1.next()) {
                database_Name = rs1.getString("company_database");
            }
            rs1.close();

//	    if (flag_Insert_or_Delete) {
            System.out.println("Current User ID -------------   " + gen.dto.Constants.CURRENT_USER_ID);
            query = "insert into tblcurrentcompany(company_name,company_id,company_database,user_id) values (?,?,?,?)";
            prmt = conn.prepareStatement(query);
            prmt.setString(1, company_Name);
            prmt.setString(2, company_id);
            prmt.setString(3, database_Name);
            prmt.setString(4, gen.dto.Constants.CURRENT_USER_ID);
            prmt.executeUpdate();
//	    } else {
//		query = "update tblcurrentcompany set company_name = ? ,company_id = ? ,company_database = ? where user_id = ? ";
//		prmt = conn.prepareStatement(query);
//		prmt.setString(1, company_Name);
//		prmt.setString(2, company_id);
//		prmt.setString(3, database_Name);
//		prmt.setString(4, gen.dto.Constants.CURRENT_USER_ID);
//		prmt.executeUpdate();
//	    }
            prmt.close();
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(gen.other.CompanySettings.CompanySettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void delete_Current_Company_By_User(String user_id) {
        try {
            //set COMPANYDETAILS_DATABASE_CALLING = 1 for selection of Database comany_profile
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();

            conn.setAutoCommit(false);
            String query = "delete from  tblcurrentcompany where user_id = ?";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, gen.dto.Constants.CURRENT_USER_ID);
            prmt.executeUpdate();

            prmt.close();
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(gen.other.CompanySettings.CompanySettingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

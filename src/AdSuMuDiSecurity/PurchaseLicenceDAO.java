/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import AdSuMuDiSecurity.PurchaseLicenceDTO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.io.FileWriter;
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
public class PurchaseLicenceDAO {

    public static PurchaseLicenceDTO get_Licence_Information() {
//	Map<String, String> map_Role_ID = new HashMap<String, String>();
	PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    String subQuery = "Select * from tblLicence_Version_Details";
	    PreparedStatement prmt = conn.prepareStatement(subQuery);
	    ResultSet rs = prmt.executeQuery();

	    while(rs.next()){
		purchaseLicenceDTO.setApplication_ID(rs.getString("application_ID"));
		purchaseLicenceDTO.setMobile_key(rs.getString("mobile_Key"));
		purchaseLicenceDTO.setMobile_User_1(rs.getString("mobile_User_1"));
		purchaseLicenceDTO.setMobile_User_2(rs.getString("mobile_User_2"));
	    }
	    
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(PurchaseLicenceDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return purchaseLicenceDTO;
    }
    
      public static void writingMobile_Information_ID_TextToFile(String splittingFinal) {
	FileWriter fileWritter = null;
//	    fileWritter = new FileWriter(dir + "\\others\\MobileInformation.properties", true);
//	    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//
//	    String splittingFinal1[] = splittingFinal.split("-");
//
//	    for (int i = 0; i < splittingFinal1.length; i++) {
//		System.out.println("splittingFinal[i+1].toString() ----------  " + splittingFinal1[i].toString());
//		bufferWritter.write("\n" + splittingFinal1[i].toString());
//	    }

	String splittingFinal1[] = splittingFinal.split("-");

	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
	    Connection conn = DatabaseConnection1.GetConnection();
	    System.out.println("Conn --------- " + conn);

	    String query = "delete from tblLicence_Version_Details";
	    PreparedStatement prmt = conn.prepareStatement(query);
	    prmt.executeUpdate();

	    int i = 0;
	    query = "insert into tblLicence_Version_Details values(?,?,?,?)";
	    prmt = conn.prepareStatement(query);
	    prmt.setString(1, splittingFinal1[i].toString());
	    prmt.setString(2, splittingFinal1[i + 1].toString());
	    prmt.setString(3, splittingFinal1[i + 2].toString());
	    prmt.setString(4, splittingFinal1[i + 3].toString());
	    prmt.executeUpdate();

	    // set mobile_key
	    Schedular.Constants.mobile_key = splittingFinal1[i + 1].toString();

	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	} catch (SQLException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
	}


	// bufferWritter.write("\n" + "Registered User For Licenced Version");
//	    bufferWritter.close();
    }
}

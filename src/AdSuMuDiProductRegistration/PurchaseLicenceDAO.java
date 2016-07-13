/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiProductRegistration;

import AdSuMuDiProductRegistration.PurchaseLicenceDTO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.io.FileWriter;
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
public class PurchaseLicenceDAO {

    public static List<PurchaseLicenceDTO> get_Licence_Information() {
//	Map<String, String> map_Role_ID = new HashMap<String, String>();
        List<PurchaseLicenceDTO> purchaseLicenceDTOList = new ArrayList<PurchaseLicenceDTO>();
        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String subQuery = "Select * from tblLicence_Version_User_Details";
            PreparedStatement prmt = conn.prepareStatement(subQuery);
            ResultSet rs = prmt.executeQuery();

            while (rs.next()) {
                PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
                purchaseLicenceDTO.setMobile_key(rs.getString("mobile_Key"));
                purchaseLicenceDTO.setMobile_User_1(rs.getString("mobile_User_1"));
                purchaseLicenceDTOList.add(purchaseLicenceDTO);
            }
            System.out.println("purchaseLicenceDTOList--->>>" + purchaseLicenceDTOList.size());
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(PurchaseLicenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purchaseLicenceDTOList;
    }

    public static void writingMobile_Information_ID_TextToDatabase(String[] splittingFinal) throws Exception {
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

        //String splittingFinal1[] = splittingFinal.split("-");

        try {
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            System.out.println("Conn --------- " + conn);

            String query = "delete from tblLicence_Version_Details";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.executeUpdate();

            int i = 0;
            query = "insert into tblLicence_Version_Details values(?,?)";
            prmt = conn.prepareStatement(query);
            prmt.setString(1, splittingFinal[i].toString());
            prmt.setString(2, splittingFinal[i + 1].toString());
            //prmt.setString(3, splittingFinal1[i + 2].toString());
            //prmt.setString(4, splittingFinal1[i + 3].toString());
            prmt.executeUpdate();

            query = "delete from tblLicence_Version_User_Details";
            prmt = conn.prepareStatement(query);
            prmt.executeUpdate();

            query = "insert into tblLicence_Version_User_Details values(?,?)";
            prmt = conn.prepareStatement(query);
            for (int j = 2; j < splittingFinal.length - 1; j++) {
                prmt.setString(1, splittingFinal[splittingFinal.length - 1].toString());
                prmt.setString(2, splittingFinal[j].toString());
                prmt.addBatch();
            }
            prmt.executeBatch();
            // set mobile_key
            List<PurchaseLicenceDTO> purchaseLicenceDTOList = new ArrayList<PurchaseLicenceDTO>();
            purchaseLicenceDTOList = AdSuMuDiProductRegistration.PurchaseLicenceDAO.get_Licence_Information();
            for (PurchaseLicenceDTO purchaseLicenceDTO : purchaseLicenceDTOList) {
                Schedular.Constants.mobile_key = purchaseLicenceDTO.getMobile_key();
            }

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }


        // bufferWritter.write("\n" + "Registered User For Licenced Version");
//	    bufferWritter.close();
    }
}

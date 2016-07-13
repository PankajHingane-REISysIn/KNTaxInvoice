/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class SetReceiptNoDAO {

    public static void insertSetReceiptNo(SetReceiptNoDTO receiptNoDTO) throws Exception {
        try {

            Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
            String query = "insert into tblreceiptperiod(fromdate) values (?)";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, receiptNoDTO.getDate());
            prmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void selectReceiptNoYear(String year) throws Exception {
        try {

            Connection conn = gen.database.connection.DatabaseConnection1.GetConnection();
            String query = "Select  into tblreceiptperiod(fromdate) values (?)";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, year);
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

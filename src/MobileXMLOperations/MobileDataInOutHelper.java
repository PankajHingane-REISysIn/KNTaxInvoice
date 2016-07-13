/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileXMLOperations;

import gen.database.connection.DatabaseConnection1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class MobileDataInOutHelper {
    
    public static Set<String> retriveTransactionIDsFromDatabase(String fromDate, String toDate, String voucherType, String name) {
        voucherType = MobileDataInOutHelper.voucherNameToIDMap(voucherType);
        Set<String> IdSet = new HashSet<String>();
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            String query = "select trans_receiptNo from tbltransactionmain";
            String condition0 = " where trans_date = '" + fromDate + "' ";
            String condition1 = " where trans_date BETWEEN '" + fromDate + "' AND '" + toDate + "' ";
            String condition2 = "AND trans_typeIndex IN (" + voucherType + ")";
            String condition3 = "AND  trans_ledgerId = " + name;
            if (!fromDate.equalsIgnoreCase("") && toDate.equalsIgnoreCase("")) {
                query = query + condition0 + condition2;
                System.out.println("Into The Sale Fire--QueryIf1->>>" + query);
            } else if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("") && name.equalsIgnoreCase("")) {
                query = query + condition1 + condition2;
                System.out.println("Into The Sale Fire--QueryIf2->>>" + query);
            } else if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("") && !name.equalsIgnoreCase("")) {
                query = query + condition1 + condition2 + condition3;
                System.out.println("Into The Sale Fire--QueryIf3->>>" + query);
            } else {
                query = query + condition1 + condition2 + condition3;
                System.out.println("Into The Sale Fire--QueryIf4->>>" + query);
            }
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                IdSet.add(rs.getString("trans_receiptNo"));
            }
            
            System.out.println("IdSet Size in MDIOHelper---->>" + IdSet.size());
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MobileDataInOutHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IdSet;
    }
    
    private static String voucherNameToIDMap(String voucherType) {
        
        if (voucherType.equalsIgnoreCase("Sale")) {
            voucherType = "" + 1;
        } else if (voucherType.equalsIgnoreCase("Purchase")) {
            voucherType = "" + 2;
        } else if (voucherType.equalsIgnoreCase("Receipt")) {
            voucherType = "" + 6;
        } else if (voucherType.equalsIgnoreCase("Payment")) {
            voucherType = "" + 3;
        } else if (voucherType.equalsIgnoreCase("Chalan")) {
            voucherType = "" + 7;
        } else if (voucherType.equalsIgnoreCase("Contra")) {
            voucherType = "" + 6;
        } else if (voucherType.equalsIgnoreCase("DaybookReceiptPayment")) {
            voucherType = "" + 6 + "," + "" + 3;
        } else if (voucherType.equalsIgnoreCase("DaybookSalePurchase")) {
            voucherType = "" + 1 + "," + "" + 2;
        } else if (voucherType.equalsIgnoreCase("DaybookChalan")) {
            voucherType = "" + 7;
        }
        return voucherType;
        
    }

    public static void main(String args[]) {
        
        MobileDataInOutHelper.retriveTransactionIDsFromDatabase("2005-04-01", "", "DaybookReceipt/Payment", "");
        
    }
}

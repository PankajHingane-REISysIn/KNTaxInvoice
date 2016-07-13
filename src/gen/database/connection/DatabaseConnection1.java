package gen.database.connection;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection1 {

    public static String databaseClassName;
    public static String instantURL;
    public static String portNo = "3308";
    public static String databaseName;
    public static String userName;
    public static String password;

    public DatabaseConnection1() {
        System.out.println("");
    }

    public static Connection GetConnection() {
        Connection conn = null;
        try {
            databaseClassName = "org.hsqldb.jdbcDriver";
            instantURL = "jdbc:hsqldb:data/";
//            instantURL = "jdbc:hsqldb:hsql://localhost/";
//            databaseName = "udb";
            userName = "sa";
            password = "";
            String key = "604a6105889da65326bf35790a923932";
            String getColumnName = "false";

            if ("1".equalsIgnoreCase(gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING)) {
                databaseName = "companies_details";
                System.out.println("COMPANYDETAILS_DATABASE_CALLING   11111111  -------- ++++++++++ " + databaseName);
//                databaseName = "udb";
            } else if (!gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings.isEmpty()) {
                databaseName = gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings;
                System.out.println("Check in the gertFuncton -------- ++++++++++ " + databaseName);
//                databaseName = "xdb";
//                conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/ndb;file:D:/HQSLDB Projects/TAX INVOICE 29-04-2014/29-04-2014/N SVN-AdvancedPlywoodProject Tax Invoice New 27-04-2014 3.30PM/data/" + gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings + "; crypt_key=" + key + ";crypt_type=AES", "SA", "");
            }

            Class.forName(databaseClassName).newInstance();
            Properties props = new Properties();
            System.out.println("Database NMae ----------" + databaseName);

            props.put("user", userName);
            props.put("password", password);
            props.put("jdbc.strict_md", "false");
            props.put("jdbc.get_column_name", getColumnName);
            conn = DriverManager.getConnection(instantURL + databaseName + "; crypt_key=" + key + ";crypt_type=AES", props);
        } catch (InstantiationException ex) {
            Logger.getLogger(DatabaseConnection1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DatabaseConnection1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
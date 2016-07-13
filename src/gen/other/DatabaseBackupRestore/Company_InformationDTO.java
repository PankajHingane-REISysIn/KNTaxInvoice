/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.DatabaseBackupRestore;

/**
 *
 * @author admin
 */
public class Company_InformationDTO {
    
    private String company_Name = "";
    private String comapny_Database_Name = "";
    private String company_SQL_File_Name = "";
    private String company_Unique_ID = "";

    /**
     * @return the company_Name
     */
    public String getCompany_Name() {
	return company_Name;
    }

    /**
     * @param company_Name the company_Name to set
     */
    public void setCompany_Name(String company_Name) {
	this.company_Name = company_Name;
    }

    /**
     * @return the database_Name
     */
    public String getCompany_Database_Name() {
	return comapny_Database_Name;
    }

    /**
     * @param database_Name the database_Name to set
     */
    public void setCompany_Database_Name(String database_Name) {
	this.comapny_Database_Name = database_Name;
    }

    /**
     * @return the sQL_File_Name
     */
    public String getCompany_SQL_File_Name() {
	return company_SQL_File_Name;
    }

    /**
     * @param sQL_File_Name the sQL_File_Name to set
     */
    public void setCompany_SQL_File_Name(String sQL_File_Name) {
	this.company_SQL_File_Name = sQL_File_Name;
    }

    /**
     * @return the company_Unique_ID
     */
    public String getCompany_Unique_ID() {
	return company_Unique_ID;
    }

    /**
     * @param company_Unique_ID the company_Unique_ID to set
     */
    public void setCompany_Unique_ID(String company_Unique_ID) {
	this.company_Unique_ID = company_Unique_ID;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserSettingDTO {
    
    private String user_Name = "";
    private String user_Password = "";
    private String user_Email_ID = "";
    private String user_Email_Password = "";
    private int user_Role;
    private int user_Type;
    private String company_id = "";
    private String user_id = "";
    private List<FeaturesDTO> featuresDTOList = new ArrayList<FeaturesDTO>();

    
    /**
     * @return the user_Name
     */
    public String getUser_Name() {
	return user_Name;
    }

    /**
     * @param user_Name the user_Name to set
     */
    public void setUser_Name(String user_Name) {
	this.user_Name = user_Name;
    }

    /**
     * @return the user_Password
     */
    public String getUser_Password() {
	return user_Password;
    }

    /**
     * @param user_Password the user_Password to set
     */
    public void setUser_Password(String user_Password) {
	this.user_Password = user_Password;
    }

    /**
     * @return the user_Email_ID
     */
    public String getUser_Email_ID() {
	return user_Email_ID;
    }

    /**
     * @param user_Email_ID the user_Email_ID to set
     */
    public void setUser_Email_ID(String user_Email_ID) {
	this.user_Email_ID = user_Email_ID;
    }

    /**
     * @return the user_Email_Password
     */
    public String getUser_Email_Password() {
	return user_Email_Password;
    }

    /**
     * @param user_Email_Password the user_Email_Password to set
     */
    public void setUser_Email_Password(String user_Email_Password) {
	this.user_Email_Password = user_Email_Password;
    }

    
    /**
     * @return the user_Type
     */
    public int getUser_Type() {
	return user_Type;
    }

    /**
     * @param user_Type the user_Type to set
     */
    public void setUser_Type(int user_Type) {
	this.user_Type = user_Type;
    }

    /**
     * @return the user_Role
     */
    public int getUser_Role() {
	return user_Role;
    }

    /**
     * @param user_Role the user_Role to set
     */
    public void setUser_Role(int user_Role) {
	this.user_Role = user_Role;
    }

    /**
     * @return the company_id
     */
    public String getCompany_id() {
	return company_id;
    }

    /**
     * @param company_id the company_id to set
     */
    public void setCompany_id(String company_id) {
	this.company_id = company_id;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
	return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
	this.user_id = user_id;
    }
    
    /**
     * @return the featuresDTOList
     */
    public List<FeaturesDTO> getFeaturesDTOList() {
	return featuresDTOList;
    }

    /**
     * @param featuresDTOList the featuresDTOList to set
     */
    public void setFeaturesDTOList(List<FeaturesDTO> featuresDTOList) {
	this.featuresDTOList = featuresDTOList;
    }
    
}

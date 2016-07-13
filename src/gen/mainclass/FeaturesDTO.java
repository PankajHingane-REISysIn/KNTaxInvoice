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
public class FeaturesDTO {

     private String RoleID = "";
    private String Role_Name = "";
    private List<String> featureList = new ArrayList<String>();
    
    
    /**
     * @return the RoleID
     */
    public String getRoleID() {
	return RoleID;
    }

    /**
     * @param RoleID the RoleID to set
     */
    public void setRoleID(String RoleID) {
	this.RoleID = RoleID;
    }

    /**
     * @return the featureList
     */
    public List<String> getFeatureList() {
	return featureList;
    }

    /**
     * @param featureList the featureList to set
     */
    public void setFeatureList(List<String> featureList) {
	this.featureList = featureList;
    }

    /**
     * @return the Role_Name
     */
    public String getRole_Name() {
	return Role_Name;
    }

    /**
     * @param Role_Name the Role_Name to set
     */
    public void setRole_Name(String Role_Name) {
	this.Role_Name = Role_Name;
    }
}

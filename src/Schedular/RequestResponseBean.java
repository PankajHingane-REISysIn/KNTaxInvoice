/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedular;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kasturi NovaSoft
 */
@XmlRootElement
public class RequestResponseBean {
    
    private String unique_id;
    private String username;
    private String password;
    private String request;
    private String requested_date;
    private String body;
    private String mobile_id;
    
    public RequestResponseBean(){
        
    }
    
    public RequestResponseBean(String unique_id,String username,String password, String request,String body){
        this.unique_id = unique_id;
        this.username = username;
        this.password = password;
        this.request = request;
        this.body = body;
        
    }
    
    //For Mobile Apps
    
     public RequestResponseBean(String unique_id,String mobileId, String username,String password, String request,String requested_date,String body){
        this.unique_id = unique_id;
        this.username = username;
        this.password = password;
        this.request = request;
        this.requested_date = requested_date;
        this.body = body;
        this.mobile_id = mobileId;
        
    }


    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the unique_id
     */
    public String getUnique_id() {
        return unique_id;
    }

    /**
     * @param unique_id the unique_id to set
     */
    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    /**
     * @return the requested_date
     */
    public String getRequested_date() {
        return requested_date;
    }

    /**
     * @param requested_date the requested_date to set
     */
    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    /**
     * @return the mobile_id
     */
    public String getMobile_id() {
        return mobile_id;
    }

    /**
     * @param mobile_id the mobile_id to set
     */
    public void setMobile_id(String mobile_id) {
        this.mobile_id = mobile_id;
    }
    
    
}

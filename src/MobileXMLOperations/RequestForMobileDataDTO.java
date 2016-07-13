/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileXMLOperations;

/**
 *
 * @author pc5
 */
public class RequestForMobileDataDTO {
    private String voucherType = "";
    private String fromDate = "";
    private String toDate = "";
    private String Name = "";

    /**
     * @return the voucherType
     */
    public String getVoucherType() {
        return voucherType;
    }

    /**
     * @param voucherType the voucherType to set
     */
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    
}

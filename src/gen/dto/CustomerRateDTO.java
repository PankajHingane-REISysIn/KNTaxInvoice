/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

/**
 *
 * @author pc5
 */
public class CustomerRateDTO {

    private Integer customerID = 0;
    private Integer stockItemID = 0;
    private Double rate = 0d;

    /**
     * @return the customerID
     */
    public Integer getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the stockItemID
     */
    public Integer getStockItemID() {
        return stockItemID;
    }

    /**
     * @param stockItemID the stockItemID to set
     */
    public void setStockItemID(Integer stockItemID) {
        this.stockItemID = stockItemID;
    }

    /**
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }
}

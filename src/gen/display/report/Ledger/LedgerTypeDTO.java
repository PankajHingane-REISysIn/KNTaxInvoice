/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gen.display.report.Ledger;

/**
 *
 * @author Owner
 */
public class LedgerTypeDTO {
    
    private String ledgerId;
    private Integer IsAvailabtToUser;
    private Double amount;
    private String index;
    /**
     * @return the ledgerId
     */
    public String getLedgerId() {
        return ledgerId;
    }

    /**
     * @param ledgerId the ledgerId to set
     */
    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the IsAvailabtToUser
     */
    public Integer getIsAvailabtToUser() {
        return IsAvailabtToUser;
    }

    /**
     * @param IsAvailabtToUser the IsAvailabtToUser to set
     */
    public void setIsAvailabtToUser(Integer IsAvailabtToUser) {
        this.IsAvailabtToUser = IsAvailabtToUser;
    }

    /**
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }

}

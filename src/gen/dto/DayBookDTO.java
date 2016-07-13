/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ledgerreport;

/**
 *
 * @author Kasturi NovaSoft
 */
public class DayBookDTO {

    private Long transID;
    private Long receiptNo;
    private String ledgerName;
    private Double Amount;

    /**
     * @return the transID
     */
    public Long getTransID() {
        return transID;
    }

    /**
     * @param transID the transID to set
     */
    public void setTransID(Long transID) {
        this.transID = transID;
    }

    /**
     * @return the receiptNo
     */
    public Long getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(Long receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the ledgerName
     */
    public String getLedgerName() {
        return ledgerName;
    }

    /**
     * @param ledgerName the ledgerName to set
     */
    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    /**
     * @return the Amount
     */
    public Double getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }
}

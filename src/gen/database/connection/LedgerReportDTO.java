/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

/**
 *
 * @author Kasturi NovaSoft
 */
public class LedgerReportDTO {

    private String ledgerName;
    private Double openingBal = 0D;
    private Double totalCredit = 0D;
    private Double totalDebit = 0D;
    private Double closingBal = 0D;

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
     * @return the totalCredit
     */
    public Double getTotalCredit() {
        return totalCredit;
    }

    /**
     * @param totalCredit the totalCredit to set
     */
    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * @return the totalDebit
     */
    public Double getTotalDebit() {
        return totalDebit;
    }

    /**
     * @param totalDebit the totalDebit to set
     */
    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    /**
     * @return the closingBal
     */
    public Double getClosingBal() {
        return closingBal;
    }

    /**
     * @param closingBal the closingBal to set
     */
    public void setClosingBal(Double closingBal) {
        this.closingBal = closingBal;
    }

    /**
     * @return the openingBal
     */
    public Double getOpeningBal() {
        return openingBal;
    }

    /**
     * @param openingBal the openingBal to set
     */
    public void setOpeningBal(Double openingBal) {
        this.openingBal = openingBal;
    }
}

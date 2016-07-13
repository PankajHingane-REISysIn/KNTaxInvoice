/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

/**
 *
 * @author pc5
 */
public class LedgerDTO {

    private String ledgerID;
    private String ledger_Name = "";
    private String ledger_Alias = "";
    private String ledger_Under = "";
    private Integer debitOrCredit = 0;
    private Double ledger_OpeningBalence = 0D;
    private String ledger_Address = "";
    private String ledger_ContactNo = "";
    private String ledger_EmailID = "";
    private String ledger_IncomeTaxNo = "";
    private String ledger_SaleTaxNo = "";
    private Double ledger_CreditLimit = 0D;

    /**
     * @return the ledger_Name
     */
    public String getLedger_Name() {
        return ledger_Name;
    }

    /**
     * @param ledger_Name the ledger_Name to set
     */
    public void setLedger_Name(String ledger_Name) {
        this.ledger_Name = ledger_Name;
    }

    /**
     * @return the ledger_Alias
     */
    public String getLedger_Alias() {
        return ledger_Alias;
    }

    /**
     * @param ledger_Alias the ledger_Alias to set
     */
    public void setLedger_Alias(String ledger_Alias) {
        this.ledger_Alias = ledger_Alias;
    }

    /**
     * @return the ledger_Under
     */
    public String getLedger_Under() {
        return ledger_Under;
    }

    /**
     * @param ledger_Under the ledger_Under to set
     */
    public void setLedger_Under(String ledger_Under) {
        this.ledger_Under = ledger_Under;
    }

    /**
     * @return the ledger_OpeningBalence
     */
    public Double getLedger_OpeningBalence() {
        return ledger_OpeningBalence;
    }

    /**
     * @param ledger_OpeningBalence the ledger_OpeningBalence to set
     */
    public void setLedger_OpeningBalence(Double ledger_OpeningBalence) {
        this.ledger_OpeningBalence = ledger_OpeningBalence;
    }

    /**
     * @return the ledger_Address
     */
    public String getLedger_Address() {
        return ledger_Address;
    }

    /**
     * @param ledger_Address the ledger_Address to set
     */
    public void setLedger_Address(String ledger_Address) {
        this.ledger_Address = ledger_Address;
    }

    /**
     * @return the ledger_ContactNo
     */
    public String getLedger_ContactNo() {
        return ledger_ContactNo;
    }

    /**
     * @param ledger_ContactNo the ledger_ContactNo to set
     */
    public void setLedger_ContactNo(String ledger_ContactNo) {
        this.ledger_ContactNo = ledger_ContactNo;
    }

    /**
     * @return the ledger_EmailID
     */
    public String getLedger_EmailID() {
        return ledger_EmailID;
    }

    /**
     * @param ledger_EmailID the ledger_EmailID to set
     */
    public void setLedger_EmailID(String ledger_EmailID) {
        this.ledger_EmailID = ledger_EmailID;
    }

    /**
     * @return the ledger_IncomeTaxNo
     */
    public String getLedger_IncomeTaxNo() {
        return ledger_IncomeTaxNo;
    }

    /**
     * @param ledger_IncomeTaxNo the ledger_IncomeTaxNo to set
     */
    public void setLedger_IncomeTaxNo(String ledger_IncomeTaxNo) {
        this.ledger_IncomeTaxNo = ledger_IncomeTaxNo;
    }

    /**
     * @return the ledger_SaleTaxNo
     */
    public String getLedger_SaleTaxNo() {
        return ledger_SaleTaxNo;
    }

    /**
     * @param ledger_SaleTaxNo the ledger_SaleTaxNo to set
     */
    public void setLedger_SaleTaxNo(String ledger_SaleTaxNo) {
        this.ledger_SaleTaxNo = ledger_SaleTaxNo;
    }

    /**
     * @return the ledger_CreditLimit
     */
    public Double getLedger_CreditLimit() {
        return ledger_CreditLimit;
    }

    /**
     * @param ledger_CreditLimit the ledger_CreditLimit to set
     */
    public void setLedger_CreditLimit(Double ledger_CreditLimit) {
        this.ledger_CreditLimit = ledger_CreditLimit;
    }

    /**
     * @return the debitOrCredit
     */
    public Integer getDebitOrCredit() {
        return debitOrCredit;
    }

    /**
     * @param debitOrCredit the debitOrCredit to set
     */
    public void setDebitOrCredit(Integer debitOrCredit) {
        this.debitOrCredit = debitOrCredit;
    }

    /**
     * @return the ledgerID
     */
    public String getLedgerID() {
        return ledgerID;
    }

    /**
     * @param ledgerID the ledgerID to set
     */
    public void setLedgerID(String ledgerID) {
        this.ledgerID = ledgerID;
    }
}

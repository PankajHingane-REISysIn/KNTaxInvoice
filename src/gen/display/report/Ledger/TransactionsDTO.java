/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.Ledger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class TransactionsDTO implements Cloneable{
    private String particulars = "";
    private String date = "";
    private String vchtype = "";
    private String transId = "";
    private String vchno = "";
    private String debit = "";
    private String credit = "";
    private Boolean IsDebit;
    private Map<String, List<LedgerTypeDTO>> ledgerTypeDTOMap;
    private Integer IsAvailableTouser; 

    public TransactionsDTO(){
            ledgerTypeDTOMap = new HashMap<String, List<LedgerTypeDTO>>();
    }
    /**
     * @return the particulars
     */
    public String getParticulars() {
	return particulars;
    }

    /**
     * @param particulars the particulars to set
     */
    public void setParticulars(String particulars) {
	this.particulars = particulars;
    }

    /**
     * @return the date
     */
    public String getDate() {
	return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
	this.date = date;
    }

    /**
     * @return the vchtype
     */
    public String getVchtype() {
	return vchtype;
    }

    /**
     * @param vchtype the vchtype to set
     */
    public void setVchtype(String vchtype) {
	this.vchtype = vchtype;
    }

    /**
     * @return the vchno
     */
    public String getVchno() {
	return vchno;
    }

    /**
     * @param vchno the vchno to set
     */
    public void setVchno(String vchno) {
	this.vchno = vchno;
    }

    /**
     * @return the debit
     */
    public String getDebit() {
	return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(String debit) {
	this.debit = debit;
    }

    /**
     * @return the credit
     */
    public String getCredit() {
	return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(String credit) {
	this.credit = credit;
    }
   
    /**
     * @return the ledgerTypeDTOMap
     */
    public Map<String, List<LedgerTypeDTO>> getLedgerTypeDTOMap() {
        return ledgerTypeDTOMap;
    }
    
    /**
     * @param ledgerTypeDTOMap the ledgerTypeDTOMap to set
     */
    public void setLedgerTypeDTOMap(Map<String, List<LedgerTypeDTO>> ledgerTypeDTOMap) {
        this.ledgerTypeDTOMap = ledgerTypeDTOMap;
    }
    
    /**
     * @return the IsDebit
     */
    public Boolean getIsDebit() {
        return IsDebit;
    }

    /**
     * @param IsDebit the IsDebit to set
     */
    public void setIsDebit(Boolean IsDebit) {
        this.IsDebit = IsDebit;
    }
    
    /**
     * @return the IsAvailableTouser
     */
    public Integer getIsAvailableTouser() {
        return IsAvailableTouser;
    }

    /**
     * @param IsAvailableTouser the IsAvailableTouser to set
     */
    public void setIsAvailableTouser(Integer IsAvailableTouser) {
        this.IsAvailableTouser = IsAvailableTouser;
    }
    
    /**
     * @return the transId
     */
    public String getTransId() {
        return transId;
    }

    /**
     * @param transId the transId to set
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}

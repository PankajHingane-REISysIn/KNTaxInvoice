/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.dto.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author pc5
 */
public class TrialBalanceDTO {

    private Long coreGroup_id = 0l;
    private Long underCoreGroup_id = 0l;
    private Long underMainGroup_id = 0l;
    private Integer ledger_id = 0;
    private String group_name = "";
    private Integer trans_Type = 0;
    private Double debitAmount = 0D;
    private Double creditAmount = 0D;
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    ////////// Created by Atul For Profit and Loss //////////////////////////
    private Double Credit_Debit_Difference = 0D;
    private Long parent_GroupID = 0L;

    /**
     * @return the group_name
     */
    public String getGroup_name() {
        return group_name;
    }

    /**
     * @param group_name the group_name to set
     */
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    /**
     * @return the trans_Type
     */
    public Integer getTrans_Type() {
        return trans_Type;
    }

    /**
     * @param trans_Type the trans_Type to set
     */
    public void setTrans_Type(Integer trans_Type) {
        this.trans_Type = trans_Type;
    }

    /**
     * @return the debitAmount
     */
    public Double getDebitAmount() {
        return debitAmount;
    }

    /**
     * @param debitAmount the debitAmount to set
     */
    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    /**
     * @return the creditAmount
     */
    public Double getCreditAmount() {
        return creditAmount;
    }

    /**
     * @param creditAmount the creditAmount to set
     */
    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * @return the ledger_id
     */
    public Integer getLedger_id() {
        return ledger_id;
    }

    /**
     * @param ledger_id the ledger_id to set
     */
    public void setLedger_id(Integer ledger_id) {
        this.ledger_id = ledger_id;
    }

    /**
     * @return the coreGroup_id
     */
    public Long getCoreGroup_id() {
        return coreGroup_id;
    }

    /**
     * @param coreGroup_id the coreGroup_id to set
     */
    public void setCoreGroup_id(Long coreGroup_id) {
        this.coreGroup_id = coreGroup_id;
    }

    /**
     * @return the underCoreGroup_id
     */
    public Long getUnderCoreGroup_id() {
        return underCoreGroup_id;
    }

    /**
     * @param underCoreGroup_id the underCoreGroup_id to set
     */
    public void setUnderCoreGroup_id(Long underCoreGroup_id) {
        this.underCoreGroup_id = underCoreGroup_id;
    }

    /**
     * @return the underMainGroup_id
     */
    public Long getUnderMainGroup_id() {
        return underMainGroup_id;
    }

    /**
     * @param underMainGroup_id the underMainGroup_id to set
     */
    public void setUnderMainGroup_id(Long underMainGroup_id) {
        this.underMainGroup_id = underMainGroup_id;
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
     * @return the Credit_Debit_Difference
     */
    public Double getCredit_Debit_Difference() {
	return Credit_Debit_Difference;
    }

    /**
     * @param Credit_Debit_Difference the Credit_Debit_Difference to set
     */
    public void setCredit_Debit_Difference(Double Credit_Debit_Difference) {
	this.Credit_Debit_Difference = Credit_Debit_Difference;
    }

    /**
     * @return the parent_GroupID
     */
    public Long getParent_GroupID() {
	return parent_GroupID;
    }

    /**
     * @param parent_GroupID the parent_GroupID to set
     */
    public void setParent_GroupID(Long parent_GroupID) {
	this.parent_GroupID = parent_GroupID;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.dto.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pc5
 */
public class GroupSummaryDTO {

    private Long coreGroup_id = 0l;
    private Long underCoreGroup_id = 0l;
    private Long underMainGroup_id = 0l;
    private Integer ledger_id = 0;
    private Integer ledger_under = 0;
    private String group_name = "";
    private String ledger_name = "";
    private Double closingBalance = 0D;
    private Double openingBalance = 0D;
    private Integer trans_Type = 0;
    private Integer closing_trans_Type = 0;
    private Double debitAmount = 0D;
    private Double creditAmount = 0D;
    //private Map<String,Map<String,String>> ledgersCollection = new HashMap<String, Map<String, String>>();
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());

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
     * @return the ledger_name
     */
    public String getLedger_name() {
        return ledger_name;
    }

    /**
     * @param ledger_name the ledger_name to set
     */
    public void setLedger_name(String ledger_name) {
        this.ledger_name = ledger_name;
    }

    /**
     * @return the closingBalance
     */
    public Double getClosingBalance() {
        return closingBalance;
    }

    /**
     * @param closingBalance the closingBalance to set
     */
    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    /**
     * @return the openingBalance
     */
    public Double getOpeningBalance() {
        return openingBalance;
    }

    /**
     * @param openingBalance the openingBalance to set
     */
    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    /**
     * @return the closing_trans_Type
     */
    public Integer getClosing_trans_Type() {
        return closing_trans_Type;
    }

    /**
     * @param closing_trans_Type the closing_trans_Type to set
     */
    public void setClosing_trans_Type(Integer closing_trans_Type) {
        this.closing_trans_Type = closing_trans_Type;
    }

    /**
     * @return the ledger_under
     */
    public Integer getLedger_under() {
        return ledger_under;
    }

    /**
     * @param ledger_under the ledger_under to set
     */
    public void setLedger_under(Integer ledger_under) {
        this.ledger_under = ledger_under;
    }
}

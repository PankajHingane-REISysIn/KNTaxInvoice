/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.receipt;

import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author pc5
 */
public class ReceiptDTO {

    private String trans_ID = "";
    private Integer receiptNo = 0;
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String accountName = "";
    private List<LedgerTransactionDTO> ledgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
    private Double finalAmount = 0D;
    private String note = "";
    private String checkNo = "";
    private String narration = "";

    /**
     * @return the receiptNo
     */
    public Integer getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(Integer receiptNo) {
        this.receiptNo = receiptNo;
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
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the ledgerTransactionDTOList
     */
    public List<LedgerTransactionDTO> getLedgerTransactionDTOList() {
        return ledgerTransactionDTOList;
    }

    /**
     * @param ledgerTransactionDTOList the ledgerTransactionDTOList to set
     */
    public void setLedgerTransactionDTOList(List<LedgerTransactionDTO> ledgerTransactionDTOList) {
        this.ledgerTransactionDTOList = ledgerTransactionDTOList;
    }

    /**
     * @return the finalAmount
     */
    public Double getFinalAmount() {
        return finalAmount;
    }

    /**
     * @param finalAmount the finalAmount to set
     */
    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    /**
     * @return the trans_ID
     */
    public String getTrans_ID() {
        return trans_ID;
    }

    /**
     * @param trans_ID the trans_ID to set
     */
    public void setTrans_ID(String trans_ID) {
        this.trans_ID = trans_ID;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the checkNo
     */
    public String getCheckNo() {
        return checkNo;
    }

    /**
     * @param checkNo the checkNo to set
     */
    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    /**
     * @return the narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     * @param narration the narration to set
     */
    public void setNarration(String narration) {
        this.narration = narration;
    }
}

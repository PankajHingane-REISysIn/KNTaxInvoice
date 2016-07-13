/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.grnote;
import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author admin
 */
public class GRNoteDTO {

    private String trans_ID = "";
    private String quotationNo = "";
    private String cashLedger = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private String termsAndCondition = "";
    private Integer receiptNo = 0;
    private String note = "";
    private String purchaseOrderNo_transid = "";
    private String purchaseOrderNo = "";

    /**
     * @return the quotationNo
     */
    public String getQuotationNo() {
        return quotationNo;
    }

    /**
     * @param quotationNo the quotationNo to set
     */
    public void setQuotationNo(String quotationNo) {
        this.quotationNo = quotationNo;
    }

    /**
     * @return the cashLedger
     */
    public String getCashLedger() {
        return cashLedger;
    }

    /**
     * @param cashLedger the cashLedger to set
     */
    public void setCashLedger(String cashLedger) {
        this.cashLedger = cashLedger;
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
     * @return the stockItemTreansactioDTOList
     */
    public List<StockItemTransactionDTO> getStockItemTreansactioDTOList() {
        return stockItemTreansactioDTOList;
    }

    /**
     * @param stockItemTreansactioDTOList the stockItemTreansactioDTOList to set
     */
    public void setStockItemTreansactioDTOList(List<StockItemTransactionDTO> stockItemTreansactioDTOList) {
        this.stockItemTreansactioDTOList = stockItemTreansactioDTOList;
    }

    /**
     * @return the termsAndCondition
     */
    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    /**
     * @param termsAndCondition the termsAndCondition to set
     */
    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
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
     * @return the purchaseOrderNo
     */
    public String getPurchaseOrderNo_Transid() {
        return purchaseOrderNo_transid;
    }

    /**
     * @param purchaseOrderNo the purchaseOrderNo to set
     */
    public void setPurchaseOrderNo_Transid(String purchaseOrderNo) {
        this.purchaseOrderNo_transid = purchaseOrderNo;
    }

    /**
     * @return the purchaseOrderNo
     */
    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    /**
     * @param purchaseOrderNo the purchaseOrderNo to set
     */
    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.purchaseorder;

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
public class PurchaseOrderDTO {

    private String purchaseOrder_trans_ID = "";
    private String quotationNo = "";
    private String cashLedger = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private String note = " 1.PLEASE RAISE THE DELIVERY CHALLANS AND INVOICES WITH OUR FACTORY ADDRESS AND ANY REFERENCES WITH OFFICE ADDRESS MENTIONED HERERIN.\n 2.FOOLOWING DETAILS MUST APPEAR ON YOUR INVOICE IS: YOUR NOTE NO. & DATE,P.O. NO. IF ANY.\n 3. RATES:INCLUSIVE OF ALL TAXES. "
            +"\n 4.DELIVERY AT:KESNAND FACTORY SITE AT SUPPLIERS COST AND RISK. \n 5.FOR ALL DISCREPANCIES,THE SUPPLIERS WILL ALONE BE RESPONSIBLE FOR CONSEQUENCES THEREOF.\n 6.DISPUTES IF ANY,ARISING WITH RESPECT TO THIS P.O. SHALL BE SUBJECT TO JURISDICTION OF PUNE COURTS ONLY."
            +"\n 7.PLEASE SEND EXCIABLE INVOICE TO CLAIM CENVAT/MODVAT BENEFIT. PLEASE FURNISH EXCISE GATE PASS IF IT IS BEING CHARGED,OTHERWISE AMOUNT CHARGED TOWARD THE SAME WILL BE NOT PAYABLE."
            +"\n 8.DELEVERY ACCEPTANCE HOURS: 2.00 TO 4.00 P.M. EXCEPT HOLIDAYS.PURCHASERS NOT RESPONSIBLE FOR DELAYS DUE TO HOLDING UP OF TRUCKS IN EMERGENCIES. ";
    private Double amount = 0d;
    private Double vatRate = 0d;
    private Double vatAmount = 0d;
    private Double finalAmount = 0d;
    private Integer receiptNo = 0;
    private Double roundOffAmount;

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
     * @return the Ledger
     */
    public String getCashLedger() {
        return cashLedger;
    }

    /**
     * @param Ledger the Ledger to set
     */
    public void setCashLedger(String Ledger) {
        this.cashLedger = Ledger;
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
     * @return the vatRate
     */
    public Double getVatRate() {
        return vatRate;
    }

    /**
     * @param vatRate the vatRate to set
     */
    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }

    /**
     * @return the vatAmount
     */
    public Double getVatAmount() {
        return vatAmount;
    }

    /**
     * @param vatAmount the vatAmount to set
     */
    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
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
    public String getpurchaseOrder_Trans_ID() {
        return purchaseOrder_trans_ID;
    }

    /**
     * @param trans_ID the trans_ID to set
     */
    public void setpurchaseOrder_Trans_ID(String trans_ID) {
        this.purchaseOrder_trans_ID = trans_ID;
    }

    /**
     * @return the roundOfAmount
     */
    public Double getRoundOffAmount() {
        return roundOffAmount;
    }

    /**
     * @param roundOfAmount the roundOfAmount to set
     */
    public void setRoundOffAmount(Double roundOfAmount) {
        this.roundOffAmount = roundOfAmount;
    }
}

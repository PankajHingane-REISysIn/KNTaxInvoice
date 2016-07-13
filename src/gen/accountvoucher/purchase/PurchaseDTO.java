/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.purchase;

import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author pc5
 */
public class PurchaseDTO {

    private String trans_ID = "";
    private Integer receiptNo = 0;
    private String cashLedger = "";
    private String purchaseLedger = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private Double amount = 0d;
    private String note = "";
    private String paymentMode = "";
    private Double vatRate = 0d;
    private Double vatAmount = 0d;
    private Double finalAmount = 0d;
    private String purchaseReference = "";
    private Double roundOfAmount;
    private Double exciseDutyRate = 0d;
    private Double exciseDutyAmount = 0d;
    private Double edCessRate = 0d;
    private Double edCessAmount = 0d;
    private Double hEdCessRate = 0d;
    private Double hedCessAmount = 0d;
    private Double total_Without_Vat = 0d;
    private gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
    private Double cstRate = 0d;
    private Double cstAmount = 0d;
    private String poNo = gen.dto.Constants.PO_NUMBER;
    private String poDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String ocNo = "";
    private String ocDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());

    /**
     * @return the receiptNo
     */
    
    public PurchaseDTO(){
        roundOfAmount = 0D;
    }
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
     * @return the purchaseLedger
     */
    public String getPurchaseLedger() {
        return purchaseLedger;
    }

    /**
     * @param purchaseLedger the purchaseLedger to set
     */
    public void setPurchaseLedger(String purchaseLedger) {
        this.purchaseLedger = purchaseLedger;
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
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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
     * @return the purchaseReference
     */
    public String getPurchaseReference() {
        return purchaseReference;
    }

    /**
     * @param purchaseReference the purchaseReference to set
     */
    public void setPurchaseReference(String purchaseReference) {
        this.purchaseReference = purchaseReference;
    }

    /**
     * @return the roundOfAmount
     */
    public Double getRoundOfAmount() {
        return roundOfAmount;
    }

    /**
     * @param roundOfAmount the roundOfAmount to set
     */
    public void setRoundOfAmount(Double roundOfAmount) {
        this.roundOfAmount = roundOfAmount;
    }

    /**
     * @return the exciseDutyRate
     */
    public Double getExciseDutyRate() {
        return exciseDutyRate;
    }

    /**
     * @param exciseDutyRate the exciseDutyRate to set
     */
    public void setExciseDutyRate(Double exciseDutyRate) {
        this.exciseDutyRate = exciseDutyRate;
    }

    /**
     * @return the exciseDutyAmount
     */
    public Double getExciseDutyAmount() {
        return exciseDutyAmount;
    }

    /**
     * @param exciseDutyAmount the exciseDutyAmount to set
     */
    public void setExciseDutyAmount(Double exciseDutyAmount) {
        this.exciseDutyAmount = exciseDutyAmount;
    }

    /**
     * @return the edCessRate
     */
    public Double getEdCessRate() {
        return edCessRate;
    }

    /**
     * @param edCessRate the edCessRate to set
     */
    public void setEdCessRate(Double edCessRate) {
        this.edCessRate = edCessRate;
    }

    /**
     * @return the edCessAmount
     */
    public Double getEdCessAmount() {
        return edCessAmount;
    }

    /**
     * @param edCessAmount the edCessAmount to set
     */
    public void setEdCessAmount(Double edCessAmount) {
        this.edCessAmount = edCessAmount;
    }

    /**
     * @return the hEdCessRate
     */
    public Double gethEdCessRate() {
        return hEdCessRate;
    }

    /**
     * @param hEdCessRate the hEdCessRate to set
     */
    public void sethEdCessRate(Double hEdCessRate) {
        this.hEdCessRate = hEdCessRate;
    }

    /**
     * @return the hedCessAmount
     */
    public Double getHedCessAmount() {
        return hedCessAmount;
    }

    /**
     * @param hedCessAmount the hedCessAmount to set
     */
    public void setHedCessAmount(Double hedCessAmount) {
        this.hedCessAmount = hedCessAmount;
    }

    /**
     * @return the total_Without_Vat
     */
    public Double getTotal_Without_Vat() {
        return total_Without_Vat;
    }

    /**
     * @param total_Without_Vat the total_Without_Vat to set
     */
    public void setTotal_Without_Vat(Double total_Without_Vat) {
        this.total_Without_Vat = total_Without_Vat;
    }

    /**
     * @return the ledgerDTO
     */
    public gen.account.ledger.LedgerDTO getLedgerDTO() {
        return ledgerDTO;
    }

    /**
     * @param ledgerDTO the ledgerDTO to set
     */
    public void setLedgerDTO(gen.account.ledger.LedgerDTO ledgerDTO) {
        this.ledgerDTO = ledgerDTO;
    }

    /**
     * @return the cstRate
     */
    public Double getCstRate() {
        return cstRate;
    }

    /**
     * @param cstRate the cstRate to set
     */
    public void setCstRate(Double cstRate) {
        this.cstRate = cstRate;
    }

    /**
     * @return the cstAmount
     */
    public Double getCstAmount() {
        return cstAmount;
    }

    /**
     * @param cstAmount the cstAmount to set
     */
    public void setCstAmount(Double cstAmount) {
        this.cstAmount = cstAmount;
    }

    /**
     * @return the poNo
     */
    public String getPoNo() {
        return poNo;
    }

    /**
     * @param poNo the poNo to set
     */
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    /**
     * @return the poDate
     */
    public String getPoDate() {
        return poDate;
    }

    /**
     * @param poDate the poDate to set
     */
    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    /**
     * @return the ocNo
     */
    public String getOcNo() {
        return ocNo;
    }

    /**
     * @param ocNo the ocNo to set
     */
    public void setOcNo(String ocNo) {
        this.ocNo = ocNo;
    }

    /**
     * @return the ocDate
     */
    public String getOcDate() {
        return ocDate;
    }

    /**
     * @param ocDate the ocDate to set
     */
    public void setOcDate(String ocDate) {
        this.ocDate = ocDate;
    }
}

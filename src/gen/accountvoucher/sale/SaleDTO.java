/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.sale;

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
public class SaleDTO {

    private String trans_ID = "";
    private Integer receiptNo = 0;
    private String cashLedger = "";
    private String saleLedger = "";
    private String saleLedgerContactDetails = "";
    private String saleLedgerEMailDetails = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private String note = gen.dto.Constants.DELIVERY_TYPE;
    private String dispatchDocNo = "";
    private String dispatchDocThrough = gen.dto.Constants.TRANSPORT_TYPE;
    private String paymentMode = "";
    private Double Amount = 0d;
    private Double vatRate = 0d;
    private Double vatAmount = 0d;
    private Double lessBillAmount = 0d;
    private Double transport = 0d;
    private Double finalAmount = 0d;
    private Double discount = 0d;
    private Double discountAmount = 0d;
    private Double shipping = 0d;
    private String challan_trans_ID = "";
    private String challan_receipt_No = "";
    private String poNo = gen.dto.Constants.PO_NUMBER;
    private String poDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String ocNo = "";
    private String ocDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String delivery_Type = "";
    private String payment_Type = "";
    private String timeIss = "";
    private String dateIssDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String timeRem = "";
    private String dateRemDate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private Double exciseDutyRate = 0d;
    private Double exciseDutyAmount = 0d;
    private Double edCessRate = 0d;
    private Double edCessAmount = 0d;
    private Double hEdCessRate = 0d;
    private Double hedCessAmount = 0d;
    private Double total_Without_Vat = 0d;
    private Double roundOffAmount = 0d;
    private gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
    private Double cstRate = 0d;
    private Double cstAmount = 0d;
   
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
     * @return the saleLedger
     */
    public String getSaleLedger() {
        return saleLedger;
    }

    /**
     * @param saleLedger the saleLedger to set
     */
    public void setSaleLedger(String saleLedger) {
        this.saleLedger = saleLedger;
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
     * @return the dispatchDocNo
     */
    public String getDispatchDocNo() {
        return dispatchDocNo;
    }

    /**
     * @param dispatchDocNo the dispatchDocNo to set
     */
    public void setDispatchDocNo(String dispatchDocNo) {
        this.dispatchDocNo = dispatchDocNo;
    }

    /**
     * @return the dispatchDocThrough
     */
    public String getDispatchDocThrough() {
        return dispatchDocThrough;
    }

    /**
     * @param dispatchDocThrough the dispatchDocThrough to set
     */
    public void setDispatchDocThrough(String dispatchDocThrough) {
        this.dispatchDocThrough = dispatchDocThrough;
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
     * @return the lessBillAmount
     */
    public Double getLessBillAmount() {
        return lessBillAmount;
    }

    /**
     * @param lessBillAmount the lessBillAmount to set
     */
    public void setLessBillAmount(Double lessBillAmount) {
        this.lessBillAmount = lessBillAmount;
    }

    /**
     * @return the transport
     */
    public Double getTransport() {
        return transport;
    }

    /**
     * @param transport the transport to set
     */
    public void setTransport(Double transport) {
        this.transport = transport;
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
     * @return the discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return the shipping
     */
    public Double getShipping() {
        return shipping;
    }

    /**
     * @param shipping the shipping to set
     */
    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    /**
     * @return the discountAmount
     */
    public Double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the discountAmount to set
     */
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return the challan_trans_ID
     */
    public String getChallan_trans_ID() {
        return challan_trans_ID;
    }

    /**
     * @param challan_trans_ID the challan_trans_ID to set
     */
    public void setChallan_trans_ID(String challan_trans_ID) {
        this.challan_trans_ID = challan_trans_ID;
    }

    /**
     * @return the challan_receipt_No
     */
    public String getChallan_receipt_No() {
        return challan_receipt_No;
    }

    /**
     * @param challan_receipt_No the challan_receipt_No to set
     */
    public void setChallan_receipt_No(String challan_receipt_No) {
        this.challan_receipt_No = challan_receipt_No;
    }

    /**
     * @return the saleLedgerContactDetails
     */
    public String getSaleLedgerContactDetails() {
        return saleLedgerContactDetails;
    }

    /**
     * @param saleLedgerContactDetails the saleLedgerContactDetails to set
     */
    public void setSaleLedgerContactDetails(String saleLedgerContactDetails) {
        this.saleLedgerContactDetails = saleLedgerContactDetails;
    }

    /**
     * @return the saleLedgerEMailDetails
     */
    public String getSaleLedgerEMailDetails() {
        return saleLedgerEMailDetails;
    }

    /**
     * @param saleLedgerEMailDetails the saleLedgerEMailDetails to set
     */
    public void setSaleLedgerEMailDetails(String saleLedgerEMailDetails) {
        this.saleLedgerEMailDetails = saleLedgerEMailDetails;
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

    /**
     * @return the delivery_Type
     */
    public String getDelivery_Type() {
        return delivery_Type;
    }

    /**
     * @param delivery_Type the delivery_Type to set
     */
    public void setDelivery_Type(String delivery_Type) {
        this.delivery_Type = delivery_Type;
    }

    /**
     * @return the payment_Type
     */
    public String getPayment_Type() {
        return payment_Type;
    }

    /**
     * @param payment_Type the payment_Type to set
     */
    public void setPayment_Type(String payment_Type) {
        this.payment_Type = payment_Type;
    }

    /**
     * @return the timeIss
     */
    public String getTimeIss() {
        return timeIss;
    }

    /**
     * @param timeIss the timeIss to set
     */
    public void setTimeIss(String timeIss) {
        this.timeIss = timeIss;
    }

    /**
     * @return the dateIssDate
     */
    public String getDateIssDate() {
        return dateIssDate;
    }

    /**
     * @param dateIssDate the dateIssDate to set
     */
    public void setDateIssDate(String dateIssDate) {
        this.dateIssDate = dateIssDate;
    }

    /**
     * @return the timeRem
     */
    public String getTimeRem() {
        return timeRem;
    }

    /**
     * @param timeRem the timeRem to set
     */
    public void setTimeRem(String timeRem) {
        this.timeRem = timeRem;
    }

    /**
     * @return the dateRemDate
     */
    public String getDateRemDate() {
        return dateRemDate;
    }

    /**
     * @param dateRemDate the dateRemDate to set
     */
    public void setDateRemDate(String dateRemDate) {
        this.dateRemDate = dateRemDate;
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
     * @return the roundOffAmount
     */
    public Double getRoundOffAmount() {
        return roundOffAmount;
    }

    /**
     * @param roundOffAmount the roundOffAmount to set
     */
    public void setRoundOffAmount(Double roundOffAmount) {
        this.roundOffAmount = roundOffAmount;
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

   
}

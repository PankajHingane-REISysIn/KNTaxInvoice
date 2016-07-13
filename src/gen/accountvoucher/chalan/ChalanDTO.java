/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.chalan;

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
public class ChalanDTO {

    private String trans_ID = "";
    private Integer receiptNo = 0;
    private String cashLedger = "";
    private String saleLedger = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private String note = "";
    private String dispatchDocNo = "";
    private String dispatchDocThrough = "";
    private Double vatRate = 0d;
    private Double vatAmount = 0d;
    private Double lessBillAmount = 0d;
    private Double transport = 0d;

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
}

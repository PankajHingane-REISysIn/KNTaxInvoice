/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.purchaserOrderReport;

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
    //private String trans_ID = "";
    private String quotationNo = "";
    private String Ledger = "";
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private List<StockItemTransactionDTO> stockItemTreansactioDTOList = new ArrayList<StockItemTransactionDTO>();
    private String termsAndCondition = "";
    private Double Amount = 0d;
    private Double vatRate = 0d;
    private Double vatAmount = 0d;
    private Double finalAmount = 0d;

    
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
    public String getLedger() {
        return Ledger;
    }

    /**
     * @param Ledger the Ledger to set
     */
    public void setLedger(String Ledger) {
        this.Ledger = Ledger;
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
    
    
}

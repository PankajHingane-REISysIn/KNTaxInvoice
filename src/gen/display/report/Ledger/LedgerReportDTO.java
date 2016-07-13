/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.Ledger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class LedgerReportDTO {
    
    private Double total = 0D; 
    private List<TransactionsDTO>  transactionsDTOList = new ArrayList<TransactionsDTO>();  
    private Double opening_balance = 0D;
    private Double closing_balance = 0D;
    private String opening_balance_type = "";
    private String closing_balance_type = "";
    private Double total_credit = 0D;
    private Double total_debit = 0D;

    public LedgerReportDTO() {
        
    }

    /**
     * @return the total
     */
    public Double getTotal() {
	return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
	this.total = total;
    }


    /**
     * @return the opening_balance
     */
    public Double getOpening_balance() {
	return opening_balance;
    }

    /**
     * @param opening_balance the opening_balance to set
     */
    public void setOpening_balance(Double opening_balance) {
	this.opening_balance = opening_balance;
    }

    /**
     * @return the closing_balance
     */
    public Double getClosing_balance() {
	return closing_balance;
    }

    /**
     * @param closing_balance the closing_balance to set
     */
    public void setClosing_balance(Double closing_balance) {
	this.closing_balance = closing_balance;
    }

    /**
     * @return the opening_balance_type
     */
    public String getOpening_balance_type() {
	return opening_balance_type;
    }

    /**
     * @param opening_balance_type the opening_balance_type to set
     */
    public void setOpening_balance_type(String opening_balance_type) {
	this.opening_balance_type = opening_balance_type;
    }

    /**
     * @return the closing_balance_type
     */
    public String getClosing_balance_type() {
	return closing_balance_type;
    }

    /**
     * @param closing_balance_type the closing_balance_type to set
     */
    public void setClosing_balance_type(String closing_balance_type) {
	this.closing_balance_type = closing_balance_type;
    }

    /**
     * @return the total_credit
     */
    public Double getTotal_credit() {
	return total_credit;
    }

    /**
     * @param total_credit the total_credit to set
     */
    public void setTotal_credit(Double total_credit) {
	this.total_credit = total_credit;
    }

    /**
     * @return the total_debit
     */
    public Double getTotal_debit() {
	return total_debit;
    }

    /**
     * @param total_debit the total_debit to set
     */
    public void setTotal_debit(Double total_debit) {
	this.total_debit = total_debit;
    }

    /**
     * @return the transactionsDTO
     */
    public List<TransactionsDTO> getTransactionsDTO() {
	return transactionsDTOList;
    }

    /**
     * @param transactionsDTO the transactionsDTO to set
     */
    public void setTransactionsDTO(List<TransactionsDTO> transactionsDTO) {
	this.transactionsDTOList = transactionsDTO;
    }

    

    
}

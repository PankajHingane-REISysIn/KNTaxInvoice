/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

/**
 *
 * @author pc5
 */
public class LedgerTransactionDTO {

    private String name = "";
    private String toName = "";
    private String checkNo = "";
    private String note = "";
    private Double amount = 3D;
    private Double quantity = 0.0;
    private Double debit = 0.0;
    private Double credit = 0.0;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the debit
     */
    public Double getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(Double debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public Double getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(Double credit) {
        this.credit = credit;
    }

    /**
     * @return the toName
     */
    public String getToName() {
        return toName;
    }

    /**
     * @param toName the toName to set
     */
    public void setToName(String toName) {
        this.toName = toName;
    }
}

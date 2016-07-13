/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.ledger;

/**
 *
 * @author Kasturi NovaSoft
 */
public class LedgerData
{
    private int id;
    private String name;
    private String alias;
    private int under;
    private int balance;
    private String address;
    private int contactNo;
    private String emailid;
    private int incomtax;
    private int saletax;

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getUnder() {
        return under;
    }

    public int getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getEmailid() {
        return emailid;
    }

    public int getIncomtax() {
        return incomtax;
    }

    public int getSaletax() {
        return saletax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUnder(int under) {
        this.under = under;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setIncomtax(int incomtax) {
        this.incomtax = incomtax;
    }

    public void setSaletax(int saletax) {
        this.saletax = saletax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}

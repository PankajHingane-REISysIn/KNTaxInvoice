/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

/**
 *
 * @author pc5
 */
public class StockItemDTO {

    private String name = "";
    private String alias = "";
    private String under = "";
    private Double length = 0.0;
    private Double width = 0.0;
    private Double thkness = 0.0;
    private String unit = "";
    private Double rate = 0.0;
    private Double openingBal = 0.0;
    private String create_opening_balance_date = null;
    private String update_opening_balance_date = null;
    private Long ID = 0L;

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
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the under
     */
    public String getUnder() {
        return under;
    }

    /**
     * @param under the under to set
     */
    public void setUnder(String under) {
        this.under = under;
    }

    /**
     * @return the length
     */
    public Double getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Double length) {
        this.length = length;
    }

    /**
     * @return the width
     */
    public Double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * @return the thkness
     */
    public Double getThkness() {
        return thkness;
    }

    /**
     * @param thkness the thkness to set
     */
    public void setThkness(Double thkness) {
        this.thkness = thkness;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * @return the openingBal
     */
    public Double getOpeningBal() {
        return openingBal;
    }

    /**
     * @param openingBal the openingBal to set
     */
    public void setOpeningBal(Double openingBal) {
        this.openingBal = openingBal;
    }

    /**
     * @return the create_opening_balance_date
     */
    public String getCreate_opening_balance_date() {
        return create_opening_balance_date;
    }

    /**
     * @param create_opening_balance_date the create_opening_balance_date to set
     */
    public void setCreate_opening_balance_date(String create_opening_balance_date) {
        this.create_opening_balance_date = create_opening_balance_date;
    }

    /**
     * @return the update_opening_balance_date
     */
    public String getUpdate_opening_balance_date() {
        return update_opening_balance_date;
    }

    /**
     * @param update_opening_balance_date the update_opening_balance_date to set
     */
    public void setUpdate_opening_balance_date(String update_opening_balance_date) {
        this.update_opening_balance_date = update_opening_balance_date;
    }

    /**
     * @return the ID
     */
    public Long getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Long ID) {
        this.ID = ID;
    }
}

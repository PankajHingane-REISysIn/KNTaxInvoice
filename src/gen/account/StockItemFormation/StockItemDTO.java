/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.StockItemFormation;

import gen.account.stockitem.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc5
 */
public class StockItemDTO {

    private String name = "";
    private Double length = 0d;
    private Double width = 0d;
    private Double thkness = 0d;
    private Double quantity = 0d;
    private Double sqFeet = 0d;
    private Double rate = 0D;
    private Double Amount = 0d;
    private String unit_of_symbol = "";
    private String isdeemedpositive = "";
    private String alias = "";
    private String under = "";
    private String unit = "";
    private Double sizeA = 0D;
    private Double sizeB = 0D;
    private String packagee = "";
    private Integer numbers = 0;
    private Double openingBal = 0D;
    private String stockitem_Date = "";
    private String create_opening_balance_date = null;
    private String update_opening_balance_date = null;
    private Long ID = 0L;
    private Integer OpeningBal_type = 0;
    private List<ColorAndOBDTO> colorAndOBDTOList = new ArrayList<ColorAndOBDTO>();
    private StockItemCategoryDTO stockItemCategoryDTO;
    private StockItemColorDTO stockItemColorDTO;
    private StockItemFinishTypeDTO stockItemFinishTypeDTO;
    private StockItemTypeDTO stockItemTypeDTO;
    private  Long production_def_id = 0l;

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
     * @return the sqFeet
     */
    public Double getSqFeet() {
        return sqFeet;
    }

    /**
     * @param sqFeet the sqFeet to set
     */
    public void setSqFeet(Double sqFeet) {
        this.sqFeet = sqFeet;
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
     * @return the unit_of_symbol
     */
    public String getUnit_of_symbol() {
        return unit_of_symbol;
    }

    /**
     * @param unit_of_symbol the unit_of_symbol to set
     */
    public void setUnit_of_symbol(String unit_of_symbol) {
        this.unit_of_symbol = unit_of_symbol;
    }

    /**
     * @return the isdeemedpositive
     */
    public String getIsdeemedpositive() {
        return isdeemedpositive;
    }

    /**
     * @param isdeemedpositive the isdeemedpositive to set
     */
    public void setIsdeemedpositive(String isdeemedpositive) {
        this.isdeemedpositive = isdeemedpositive;
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

    /**
     * @return the OpeningBal_type
     */
    public Integer getOpeningBal_type() {
        return OpeningBal_type;
    }

    /**
     * @param OpeningBal_type the OpeningBal_type to set
     */
    public void setOpeningBal_type(Integer OpeningBal_type) {
        this.OpeningBal_type = OpeningBal_type;
    }

    /**
     * @return the stockitem_Date
     */
    public String getStockitem_Date() {
        return stockitem_Date;
    }

    /**
     * @param stockitem_Date the stockitem_Date to set
     */
    public void setStockitem_Date(String stockitem_Date) {
        this.stockitem_Date = stockitem_Date;
    }

    /**
     * @return the colorAndOBDTOList
     */
    public List<ColorAndOBDTO> getColorAndOBDTOList() {
        return colorAndOBDTOList;
    }

    /**
     * @param colorAndOBDTOList the colorAndOBDTOList to set
     */
    public void setColorAndOBDTOList(List<ColorAndOBDTO> colorAndOBDTOList) {
        this.colorAndOBDTOList = colorAndOBDTOList;
    }

    /**
     * @return the sizeA
     */
    public Double getSizeA() {
        return sizeA;
    }

    /**
     * @param sizeA the sizeA to set
     */
    public void setSizeA(Double sizeA) {
        this.sizeA = sizeA;
    }

    /**
     * @return the sizeB
     */
    public Double getSizeB() {
        return sizeB;
    }

    /**
     * @param sizeB the sizeB to set
     */
    public void setSizeB(Double sizeB) {
        this.sizeB = sizeB;
    }

    /**
     * @return the packagee
     */
    public String getPackagee() {
        return packagee;
    }

    /**
     * @param packagee the packagee to set
     */
    public void setPackagee(String packagee) {
        this.packagee = packagee;
    }

    /**
     * @return the numbers
     */
    public Integer getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    /**
     * @return the stockItemCategoryDTO
     */
    public StockItemCategoryDTO getStockItemCategoryDTO() {
        return stockItemCategoryDTO;
    }

    /**
     * @param stockItemCategoryDTO the stockItemCategoryDTO to set
     */
    public void setStockItemCategoryDTO(StockItemCategoryDTO stockItemCategoryDTO) {
        this.stockItemCategoryDTO = stockItemCategoryDTO;
    }

    /**
     * @return the stockItemColorDTO
     */
    public StockItemColorDTO getStockItemColorDTO() {
        return stockItemColorDTO;
    }

    /**
     * @param stockItemColorDTO the stockItemColorDTO to set
     */
    public void setStockItemColorDTO(StockItemColorDTO stockItemColorDTO) {
        this.stockItemColorDTO = stockItemColorDTO;
    }

    /**
     * @return the stockItemFinishTypeDTO
     */
    public StockItemFinishTypeDTO getStockItemFinishTypeDTO() {
        return stockItemFinishTypeDTO;
    }

    /**
     * @param stockItemFinishTypeDTO the stockItemFinishTypeDTO to set
     */
    public void setStockItemFinishTypeDTO(StockItemFinishTypeDTO stockItemFinishTypeDTO) {
        this.stockItemFinishTypeDTO = stockItemFinishTypeDTO;
    }

    /**
     * @return the stockItemTypeDTO
     */
    public StockItemTypeDTO getStockItemTypeDTO() {
        return stockItemTypeDTO;
    }

    /**
     * @param stockItemTypeDTO the stockItemTypeDTO to set
     */
    public void setStockItemTypeDTO(StockItemTypeDTO stockItemTypeDTO) {
        this.stockItemTypeDTO = stockItemTypeDTO;
    }

    /**
     * @return the production_def_id
     */
    public Long getProduction_def_id() {
        return production_def_id;
    }

    /**
     * @param production_def_id the production_def_id to set
     */
    public void setProduction_def_id(Long production_def_id) {
        this.production_def_id = production_def_id;
    }
}

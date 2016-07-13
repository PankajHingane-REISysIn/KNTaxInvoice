/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.StockItemFormation;

/**
 *
 * @author pc5
 */
public class StockItemCategoryDTO {

    private int categoryID;
    private String categoryName = "";

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

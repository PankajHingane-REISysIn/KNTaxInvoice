/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.StockItemFormation;

/**
 *
 * @author pc5
 */
public class StockItemFinishTypeDTO {

    private int finishTypeID;
    private String finishTypeName = "";

    /**
     * @return the finishTypeID
     */
    public int getFinishTypeID() {
        return finishTypeID;
    }

    /**
     * @param finishTypeID the finishTypeID to set
     */
    public void setFinishTypeID(int finishTypeID) {
        this.finishTypeID = finishTypeID;
    }

    /**
     * @return the finishTypeName
     */
    public String getFinishTypeName() {
        return finishTypeName;
    }

    /**
     * @param finishTypeName the finishTypeName to set
     */
    public void setFinishTypeName(String finishTypeName) {
        this.finishTypeName = finishTypeName;
    }
}

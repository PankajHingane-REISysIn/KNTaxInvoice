/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.finisheditemdefination;

import gen.dto.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Kasturi
 */
public class AddRawMaterialDTO {
    private String finishStockItem = "";
//    private Double quantity = 0d;
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());;
    private List<gen.account.StockItemFormation.StockItemDTO> listStockItemDTO = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();

    /**
     * @return the finishStockItem
     */
    public String getFinishStockItem() {
        return finishStockItem;
    }

    /**
     * @param finishStockItem the finishStockItem to set
     */
    public void setFinishStockItem(String finishStockItem) {
        this.finishStockItem = finishStockItem;
    }

    /**
     * @return the listStockItemDTO
     */
    public List<gen.account.StockItemFormation.StockItemDTO> getListStockItemDTO() {
        return listStockItemDTO;
    }

    /**
     * @param listStockItemDTO the listStockItemDTO to set
     */
    public void setListStockItemDTO(List<gen.account.StockItemFormation.StockItemDTO> listStockItemDTO) {
        this.listStockItemDTO = listStockItemDTO;
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

//    /**
//     * @return the quantity
//     */
//    public Double getQuantity() {
//        return quantity;
//    }
//
//    /**
//     * @param quantity the quantity to set
//     */
//    public void setQuantity(Double quantity) {
//        this.quantity = quantity;
//    }
}

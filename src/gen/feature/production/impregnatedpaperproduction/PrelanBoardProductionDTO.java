/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.impregnatedpaperproduction;

import gen.dto.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Kasturi
 */
public class PrelanBoardProductionDTO {

    private String trans_id = "";
    private String impreganated_group_id = "2";
    private String finishStockItemName = "";
    private Double finishItemQty = 0d;
    private Long finishItemId = 0l;
    private List<gen.account.StockItemFormation.StockItemDTO> listStockItemDTO = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
    private String date = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private Integer receiptNo = 0;
    
    /**
     * @return the finishStockItem
     */
    public String getFinishStockItemName() {
        return finishStockItemName;
    }

    /**
     * @param finishStockItem the finishStockItem to set
     */
    public void setFinishStockItemName(String finishStockItem) {
        this.finishStockItemName = finishStockItem;
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
     * @return the finishItemQty
     */
    public Double getFinishItemQty() {
        return finishItemQty;
    }

    /**
     * @param finishItemQty the finishItemQty to set
     */
    public void setFinishItemQty(Double finishItemQty) {
        this.finishItemQty = finishItemQty;
    }

    /**
     * @return the finishItemId
     */
    public Long getFinishItemId() {
        return finishItemId;
    }

    /**
     * @param finishItemId the finishItemId to set
     */
    public void setFinishItemId(Long finishItemId) {
        this.finishItemId = finishItemId;
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
     * @return the trans_id
     */
    public String getTrans_id() {
        return trans_id;
    }

    /**
     * @param trans_id the trans_id to set
     */
    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }
    
    /**
     * @return the impreganated_group_id
     */
    public String getImpreganated_group_id() {
        return impreganated_group_id;
    }

    /**
     * @param impreganated_group_id the impreganated_group_id to set
     */
    public void setImpreganated_group_id(String impreganated_group_id) {
        this.impreganated_group_id = impreganated_group_id;
    }
}

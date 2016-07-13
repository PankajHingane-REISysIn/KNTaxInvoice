/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.purchaserOrderReport;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import gen.dto.Constants;

/**
 *
 * @author admin
 */
public class POReportDTO {
    
    private String fromdate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    private String todate = new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(Calendar.getInstance().getTime());
    List<PurchaseOrderDTO>  purchaseOrderDTOList  = new ArrayList<PurchaseOrderDTO>();

}

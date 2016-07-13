/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.chalan.ChalanDTO;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author pc5
 */
public class ChalanTagHelper {

    public static String generateChalanVoucherNodeList(List<ChalanDTO> chalanDTOList) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String returnString = "";
        System.out.println("in the--------->>generateSaleNodes(List<SaleDTO> saleDTOList) : " + chalanDTOList.size());
	if (chalanDTOList != null && chalanDTOList.size() > 0) {
	    returnString = returnString + "\n<ChalanVoucherList>";
        for (ChalanDTO chalanDTO : chalanDTOList) {
            System.out.println("--------->>saleDTO : " + chalanDTO.getCashLedger());
            returnString = returnString + generateChalanVoucherNodes(chalanDTO);
        }
	    returnString = returnString + "\n</ChalanVoucherList>";
	}
        return returnString;
    }
    
    public static String generateChalanVoucherNodes(ChalanDTO chalanDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String saleXML = generateChalanVoucherMainNodes(chalanDTO);
	String StringArray[] = saleXML.split("</ChalanVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (!Util.isEmpty(chalanDTO.getStockItemTreansactioDTOList())) {
	    outputString = outputString + "\n<StockItemTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size Chalan:" + chalanDTO.getStockItemTreansactioDTOList().size());
            for (StockItemTransactionDTO stkitemDTO : chalanDTO.getStockItemTreansactioDTOList()) {
                outputString = outputString + SaleTagHelper.generateStockItemTransactionNode(stkitemDTO);
            }
	    outputString = outputString + "</StockItemTransactionList>\n";
        }
	outputString = outputString + "</ChalanVoucher>";

        //  return convertDoctoDOMSource(doc);
        return outputString;
    }
    
    public static String generateChalanVoucherMainNodes(ChalanDTO chalanDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();
        System.out.println("--------->>generateSaleNodes : 1");

        Document doc = impl.createDocument(null, null, null);
	Element chalan = doc.createElement("ChalanVoucher");
        doc.appendChild(chalan);
        System.out.println("--------->>generateSaleNodes : 2");

	Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode(chalanDTO.getReceiptNo().toString()));
        chalan.appendChild(receiptNo);

        Element cashLedger = doc.createElement("CashLedger");
        cashLedger.appendChild(doc.createTextNode(chalanDTO.getCashLedger()));
        chalan.appendChild(cashLedger);
        System.out.println("--------->>generateSaleNodes : 3");

        Element saleLedger = doc.createElement("SaleLedger");
        saleLedger.appendChild(doc.createTextNode(chalanDTO.getSaleLedger()));
        chalan.appendChild(saleLedger);

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(chalanDTO.getDate()));
        chalan.appendChild(date);

        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode(chalanDTO.getNote()));
        chalan.appendChild(note);
        System.out.println("--------->>generateSaleNodes : 4");

        Element dispatchDocNo = doc.createElement("DispatchDocNo");
        dispatchDocNo.appendChild(doc.createTextNode(chalanDTO.getDispatchDocNo()));
        chalan.appendChild(dispatchDocNo);

        Element dispatchDocThrough = doc.createElement("DispatchDocThrough");
        dispatchDocThrough.appendChild(doc.createTextNode(chalanDTO.getDispatchDocThrough()));
        chalan.appendChild(dispatchDocThrough);


	Element vatRate = doc.createElement("VatRate");
	vatRate.appendChild(doc.createTextNode(chalanDTO.getVatRate().toString()));
	chalan.appendChild(vatRate);

	Element vatAmount = doc.createElement("VatAmount");
	vatAmount.appendChild(doc.createTextNode(chalanDTO.getVatAmount().toString()));
	chalan.appendChild(vatAmount);

	Element lessBillAmount = doc.createElement("LessBillAmount");
	lessBillAmount.appendChild(doc.createTextNode(chalanDTO.getLessBillAmount().toString()));
	chalan.appendChild(lessBillAmount);

	Element transport = doc.createElement("Transport");
	transport.appendChild(doc.createTextNode(chalanDTO.getTransport().toString()));
	chalan.appendChild(transport);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

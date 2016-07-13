/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.purchase.PurchaseDTO;
import gen.dto.Constants;
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
public class PurchaseTagHelper {

    public static String generatePurchaseVouchersNodeList(List<PurchaseDTO> purchaseDTOList) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String returnString = "";
        Double amountToBeSent = 0D;
        if (purchaseDTOList != null && purchaseDTOList.size() > 0) {
            returnString = returnString + "\n<PurchaseVoucherList>";
            for (PurchaseDTO purchaseDTO : purchaseDTOList) {
                returnString = returnString + generatePurchaseVoucherNode(purchaseDTO);
                amountToBeSent = amountToBeSent + purchaseDTO.getFinalAmount();
            }
            //returnString = returnString + "\n</PurchaseVoucherList>";
        }

        returnString = returnString + "\n</PurchaseVoucherList>" + "\n<TotalPurchaseAmount>" + Constants.DECIMAL_FORMAT.format(amountToBeSent) + "\n</TotalPurchaseAmount>";
        return returnString;
    }

    public static String generatePurchaseVoucherNode(PurchaseDTO purchaseDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String saleXML = generatePurchaseVoucherMainNode(purchaseDTO);
        String StringArray[] = saleXML.split("</PurchaseVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (!Util.isEmpty(purchaseDTO.getStockItemTreansactioDTOList())) {
            outputString = outputString + "\n<StockItemTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size Purchase:" + purchaseDTO.getStockItemTreansactioDTOList().size());
            for (StockItemTransactionDTO stkitemDTO : purchaseDTO.getStockItemTreansactioDTOList()) {
                outputString = outputString + SaleTagHelper.generateStockItemTransactionNode(stkitemDTO);
            }
            outputString = outputString + "</StockItemTransactionList>\n";
        }
        outputString = outputString + "</PurchaseVoucher>";

        //  return convertDoctoDOMSource(doc);
        return outputString;
    }

    public static String generatePurchaseVoucherMainNode(PurchaseDTO purchaseDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element sale = doc.createElement("PurchaseVoucher");
        doc.appendChild(sale);

        Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode(purchaseDTO.getReceiptNo().toString()));
        sale.appendChild(receiptNo);

        Element cashLedger = doc.createElement("CashLedger");
        cashLedger.appendChild(doc.createTextNode(purchaseDTO.getCashLedger()));
        sale.appendChild(cashLedger);

        Element purchaseLedger = doc.createElement("PurchaseLedger");
        purchaseLedger.appendChild(doc.createTextNode(purchaseDTO.getPurchaseLedger()));
        sale.appendChild(purchaseLedger);

        Element PurchaseReference = doc.createElement("PurchaseReference");
        System.out.println("Purchase Rewference ------------- " + purchaseDTO.getPurchaseReference());
        PurchaseReference.appendChild(doc.createTextNode(purchaseDTO.getPurchaseReference()));
        sale.appendChild(PurchaseReference);

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(purchaseDTO.getDate()));
        sale.appendChild(date);

        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode(purchaseDTO.getNote()));
        sale.appendChild(note);

        Element paymentMode = doc.createElement("PaymentMode");
        paymentMode.appendChild(doc.createTextNode(purchaseDTO.getPaymentMode()));
        sale.appendChild(paymentMode);

        Element Amount = doc.createElement("Amount");
        Amount.appendChild(doc.createTextNode(purchaseDTO.getAmount().toString()));
        sale.appendChild(Amount);

        Element vatRate = doc.createElement("VatRate");
        vatRate.appendChild(doc.createTextNode(purchaseDTO.getVatRate().toString()));
        sale.appendChild(vatRate);

        Element vatAmount = doc.createElement("VatAmount");
        vatAmount.appendChild(doc.createTextNode(purchaseDTO.getVatAmount().toString()));
        sale.appendChild(vatAmount);

        Element finalAmount = doc.createElement("FinalAmount");
        finalAmount.appendChild(doc.createTextNode(purchaseDTO.getFinalAmount().toString()));
        sale.appendChild(finalAmount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.payment.PaymentDTO;
import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
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
public class PaymentTagHelper {
    public static String generatePaymentVoucherNodeList(List<PaymentDTO> paymentDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        Double amountToBeSent = 0D;
        if(paymentDTOList != null && paymentDTOList.size() > 0){
            returnString = returnString + "\n<PaymentVoucherList>";
            for (PaymentDTO payment : paymentDTOList) {
                returnString = returnString + generatePaymentVoucherNodes(payment);
                amountToBeSent = amountToBeSent + payment.getFinalAmount();
            }
            
            returnString = returnString + "\n<TotalPaymentAmount>" + Constants.DECIMAL_FORMAT.format(amountToBeSent) + "\n</TotalPaymentAmount>";
            returnString = returnString + "\n</PaymentVoucherList>";
        }
        return returnString;
    }
    
    public static String generatePaymentVoucherNodes(PaymentDTO paymentDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String paymentXML = generatePaymentVoucherMainNodes(paymentDTO);
        String StringArray[] = paymentXML.split("</PaymentVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (paymentDTO.getLedgerTransactionDTOList() != null && paymentDTO.getLedgerTransactionDTOList().size() > 0) {
            outputString = outputString + "\n<LedgerTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size Payment:" + paymentDTO.getLedgerTransactionDTOList().size());
            for (LedgerTransactionDTO ledgerTransactionDTO : paymentDTO.getLedgerTransactionDTOList()) {
                outputString = outputString + ReceiptTagHelper.generateLedgerTransactionNode(ledgerTransactionDTO);
            }
            outputString = outputString + "</LedgerTransactionList>\n";
        }
        outputString = outputString + "</PaymentVoucher>";

        return outputString;
    }
    
    public static String generatePaymentVoucherMainNodes(PaymentDTO paymentDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element payment = doc.createElement("PaymentVoucher");
        doc.appendChild(payment);

        Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode(paymentDTO.getReceiptNo().toString()));
        payment.appendChild(receiptNo);

        Element account = doc.createElement("Account");
        account.appendChild(doc.createTextNode(paymentDTO.getAccountName()));
        payment.appendChild(account);

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(paymentDTO.getDate()));
        payment.appendChild(date);

        Element Amount = doc.createElement("FinalAmount");
        Amount.appendChild(doc.createTextNode(paymentDTO.getFinalAmount().toString()));
        payment.appendChild(Amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    
}

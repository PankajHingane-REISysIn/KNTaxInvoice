/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.receipt.ReceiptDTO;
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
public class ReceiptTagHelper {
    
    public static String generateReceiptVoucherNodeList(List<ReceiptDTO> receiptDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        Double amountToBeSent = 0D;
        if(receiptDTOList != null && receiptDTOList.size() > 0){
            returnString = returnString + "\n<ReceiptVoucherList>";
            for (ReceiptDTO receiptDTO : receiptDTOList) {
                returnString = returnString + ReceiptTagHelper.generateReceiptVoucherNode(receiptDTO);
                amountToBeSent = amountToBeSent + receiptDTO.getFinalAmount();
            }

            returnString = returnString + "\n<TotalReceiptAmount>" + Constants.DECIMAL_FORMAT.format(amountToBeSent) + "\n</TotalReceiptAmount>";
            returnString = returnString + "\n</ReceiptVoucherList>";
        }
        return returnString;
    }
    
    public static String generateReceiptVoucherNode(ReceiptDTO reciptDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String receiptXML = generateReceiptVoucherNodes(reciptDTO);
        String StringArray[] = receiptXML.split("</ReceiptVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (reciptDTO.getLedgerTransactionDTOList() != null && reciptDTO.getLedgerTransactionDTOList().size() > 0) {
            outputString = outputString + "\n<LedgerTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size Receipt:" + reciptDTO.getLedgerTransactionDTOList().size());
            for (LedgerTransactionDTO ledgerTransactionDTO : reciptDTO.getLedgerTransactionDTOList()) {
                outputString = outputString + generateLedgerTransactionNode(ledgerTransactionDTO);
            }
            outputString = outputString + "</LedgerTransactionList>\n";
        }
        outputString = outputString + "</ReceiptVoucher>";

        return outputString;
    }

    public static String generateReceiptVoucherNodes(ReceiptDTO receiptDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element receipt = doc.createElement("ReceiptVoucher");
        doc.appendChild(receipt);

        Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode(receiptDTO.getReceiptNo().toString()));
        receipt.appendChild(receiptNo);

        Element account = doc.createElement("Account");
        account.appendChild(doc.createTextNode(receiptDTO.getAccountName()));
        receipt.appendChild(account);

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(receiptDTO.getDate()));
        receipt.appendChild(date);

        Element Amount = doc.createElement("FinalAmount");
        Amount.appendChild(doc.createTextNode(receiptDTO.getFinalAmount().toString()));
        receipt.appendChild(Amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateLedgerTransactionNode(LedgerTransactionDTO ledgerTransactionDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element ledgerTransactions = doc.createElement("LedgerDetails");
        doc.appendChild(ledgerTransactions);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(ledgerTransactionDTO.getName()));
        ledgerTransactions.appendChild(name);

        Element checkNo = doc.createElement("CheckNo");
        checkNo.appendChild(doc.createTextNode(ledgerTransactionDTO.getCheckNo()));
        ledgerTransactions.appendChild(checkNo);

        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode(ledgerTransactionDTO.getNote()));
        ledgerTransactions.appendChild(note);

        Element amount = doc.createElement("Amount");
        amount.appendChild(doc.createTextNode(ledgerTransactionDTO.getAmount().toString()));
        ledgerTransactions.appendChild(amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

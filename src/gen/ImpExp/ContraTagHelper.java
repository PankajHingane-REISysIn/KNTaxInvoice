/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.contra.ContraDTO;
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
public class ContraTagHelper {
    
    public static String generateContraVoucherNodeList(List<ContraDTO> contraDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        if(contraDTOList != null && contraDTOList.size() > 0){
            returnString = returnString + "\n<ContraVoucherList>";
            for (ContraDTO contra : contraDTOList) {
                returnString = returnString + generateContraVoucherNodes(contra);
            }
            returnString = returnString + "\n</ContraVoucherList>";
        }
        return returnString;
    }
    
    public static String generateContraVoucherNodes(ContraDTO contraDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String contraXML = generateContraVoucherMainNode(contraDTO);
        String StringArray[] = contraXML.split("</ContraVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (contraDTO.getLedgerTransactionDTOList() != null && contraDTO.getLedgerTransactionDTOList().size() > 0) {
            outputString = outputString + "\n<LedgerTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size Receipt:" + contraDTO.getLedgerTransactionDTOList().size());
            for (LedgerTransactionDTO ledgerTransactionDTO : contraDTO.getLedgerTransactionDTOList()) {
                System.out.println("In for loop:::--->>>");
                outputString = outputString + ReceiptTagHelper.generateLedgerTransactionNode(ledgerTransactionDTO);
            }
            outputString = outputString + "</LedgerTransactionList>\n";
        }
        outputString = outputString + "</ContraVoucher>";

        return outputString;
    }
    
    public static String generateContraVoucherMainNode(ContraDTO contraDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element contra = doc.createElement("ContraVoucher");
        doc.appendChild(contra);

        Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode("" + contraDTO.getReceiptNo()));
        contra.appendChild(receiptNo);

        Element account = doc.createElement("Account");
        account.appendChild(doc.createTextNode(contraDTO.getAccountName()));
        contra.appendChild(account);

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(contraDTO.getDate()));
        contra.appendChild(date);

        Element Amount = doc.createElement("FinalAmount");
        Amount.appendChild(doc.createTextNode(contraDTO.getFinalAmount().toString()));
        contra.appendChild(Amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

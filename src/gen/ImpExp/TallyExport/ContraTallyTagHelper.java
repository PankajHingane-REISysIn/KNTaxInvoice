/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp.TallyExport;

import gen.ImpExp.*;
import gen.accountvoucher.contra.ContraDTO;
import gen.dto.LedgerTransactionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author pc5
 */
public class ContraTallyTagHelper {

    public static String generateContraVoucherNodeList(List<ContraDTO> contraDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        if (contraDTOList != null && contraDTOList.size() > 0) {
            for (ContraDTO contraDTO : contraDTOList) {
                returnString = returnString + generatePaymentVoucherNodes(contraDTO);
            }
        }
        return returnString;
    }

    public static String generatePaymentVoucherNodes(ContraDTO contraDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String last_ledger = generatePaymentVoucherMainNodes(contraDTO);
        System.out.println("Return ------- " + last_ledger);
        return last_ledger;
    }

    public static String generatePaymentVoucherMainNodes(ContraDTO contraDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element TALLYMESSAGE = doc.createElement("TALLYMESSAGE");
        doc.appendChild(TALLYMESSAGE);

        Attr attr = doc.createAttribute("xmlns:UDF");
        attr.setValue("TallyUDF");
        TALLYMESSAGE.setAttributeNode(attr);

        Element VOUCHER = doc.createElement("VOUCHER");
        TALLYMESSAGE.appendChild(VOUCHER);

        gen.dto.Constants.REMOTE_ID_For_Tally = gen.dto.Constants.REMOTE_ID_For_Tally + 1;
        VOUCHER.setAttribute("REMOTEID", "aaaa-bbbb-cccc-" + gen.dto.Constants.REMOTE_ID_For_Tally);
        VOUCHER.setAttribute("VCHTYPE", "Contra");
        VOUCHER.setAttribute("ACTION", "Create");

        Element isoptional = doc.createElement("ISOPTIONAL");
        isoptional.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(isoptional);

        Element gainloss = doc.createElement("USEFORGAINLOSS");
        gainloss.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(gainloss);

        Element useforcompound = doc.createElement("USEFORCOMPOUND");
        useforcompound.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(useforcompound);

        Element voucherName = doc.createElement("VOUCHERTYPENAME");
        voucherName.appendChild(doc.createTextNode("Contra"));
        VOUCHER.appendChild(voucherName);

        String split_date[] = contraDTO.getDate().split("-");
        String assign_date = "";
        for (String assign_date1 : split_date) {
            assign_date = assign_date + assign_date1;
        }
        Element date = doc.createElement("DATE");
        date.appendChild(doc.createTextNode(assign_date));
        VOUCHER.appendChild(date);

        Element iseffectivedate = doc.createElement("EFFECTIVEDATE");
        iseffectivedate.appendChild(doc.createTextNode(assign_date));
        VOUCHER.appendChild(iseffectivedate);


        Element iscancel = doc.createElement("ISCANCELLED");
        iscancel.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(iscancel);

        Element USETRACKINGNUMBER = doc.createElement("USETRACKINGNUMBER");
        USETRACKINGNUMBER.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(USETRACKINGNUMBER);

        Element ISPOSTDATED = doc.createElement("ISPOSTDATED");
        ISPOSTDATED.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(ISPOSTDATED);

        Element ISINVOICE = doc.createElement("ISINVOICE");
        ISINVOICE.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(ISINVOICE);

        Element DIFFACTUALQTY = doc.createElement("DIFFACTUALQTY");
        DIFFACTUALQTY.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(DIFFACTUALQTY);

        Element VOUCHERNUMBER = doc.createElement("VOUCHERNUMBER");
        VOUCHERNUMBER.appendChild(doc.createTextNode(contraDTO.getReceiptNo().toString()));
        VOUCHER.appendChild(VOUCHERNUMBER);

        Element PARTYLEDGERNAME = doc.createElement("PARTYLEDGERNAME");
        if (contraDTO.getLedgerTransactionDTOList() != null && contraDTO.getLedgerTransactionDTOList().size() > 0) {
            ContraDTO contradto1 = new ContraDTO();
            contradto1 = contraDTO;
            for (LedgerTransactionDTO ledgerTransactionDTO : contradto1.getLedgerTransactionDTOList()) {
//                PARTYLEDGERNAME.appendChild(doc.createTextNode(contraDTO.getAccountName()));
                int i = 0;
                if (i == 0) {
                    PARTYLEDGERNAME.appendChild(doc.createTextNode(ledgerTransactionDTO.getName()));
                    i++;
                    break;
                }
            }
        }
        VOUCHER.appendChild(PARTYLEDGERNAME);

        Element NARRATION = doc.createElement("NARRATION");
        NARRATION.appendChild(doc.createTextNode(""));
        VOUCHER.appendChild(NARRATION);

        Element ASPAYSLIP = doc.createElement("ASPAYSLIP");
        ASPAYSLIP.appendChild(doc.createTextNode("No"));
        VOUCHER.appendChild(ASPAYSLIP);

        Element GUID = doc.createElement("GUID");
        GUID.appendChild(doc.createTextNode("aaaa-bbbb-cccc-" + gen.dto.Constants.REMOTE_ID_For_Tally));
        VOUCHER.appendChild(GUID);

        Element ALTERID = doc.createElement("ALTERID");
        ALTERID.appendChild(doc.createTextNode("65"));
        VOUCHER.appendChild(ALTERID);

        Element uid = doc.createElement("UDF:HARYANAVAT.LIST");
        //ALTERID.appendChild(doc.createTextNode("65"));
        VOUCHER.appendChild(uid);
        uid.setAttribute("DESC", "`HARYANAVAT`");

        if (contraDTO.getLedgerTransactionDTOList() != null && contraDTO.getLedgerTransactionDTOList().size() > 0) {
            for (LedgerTransactionDTO ledgerTransactionDTO : contraDTO.getLedgerTransactionDTOList()) {

                Element ledgerTransactions = doc.createElement("ALLLEDGERENTRIES.LIST");
                VOUCHER.appendChild(ledgerTransactions);

                Element REMOVEZEROENTRIES = doc.createElement("REMOVEZEROENTRIES");
                REMOVEZEROENTRIES.appendChild(doc.createTextNode("No"));
                ledgerTransactions.appendChild(REMOVEZEROENTRIES);

                Element ISDEEMEDPOSITIVE = doc.createElement("ISDEEMEDPOSITIVE");
                ISDEEMEDPOSITIVE.appendChild(doc.createTextNode("No"));
                ledgerTransactions.appendChild(ISDEEMEDPOSITIVE);

                Element LEDGERFROMITEM = doc.createElement("LEDGERFROMITEM");
                LEDGERFROMITEM.appendChild(doc.createTextNode("No"));
                ledgerTransactions.appendChild(LEDGERFROMITEM);

                Element name = doc.createElement("LEDGERNAME");
                name.appendChild(doc.createTextNode(ledgerTransactionDTO.getName()));
                ledgerTransactions.appendChild(name);

                Element amount = doc.createElement("AMOUNT");
                amount.appendChild(doc.createTextNode(ledgerTransactionDTO.getAmount().toString() + "0"));
                ledgerTransactions.appendChild(amount);
            }
        }

        Element ledgerTransactions = doc.createElement("ALLLEDGERENTRIES.LIST");
        VOUCHER.appendChild(ledgerTransactions);

        Element REMOVEZEROENTRIES = doc.createElement("REMOVEZEROENTRIES");
        REMOVEZEROENTRIES.appendChild(doc.createTextNode("No"));
        ledgerTransactions.appendChild(REMOVEZEROENTRIES);

        Element ISDEEMEDPOSITIVE = doc.createElement("ISDEEMEDPOSITIVE");
        ISDEEMEDPOSITIVE.appendChild(doc.createTextNode("Yes"));
        ledgerTransactions.appendChild(ISDEEMEDPOSITIVE);

        Element LEDGERFROMITEM = doc.createElement("LEDGERFROMITEM");
        LEDGERFROMITEM.appendChild(doc.createTextNode("No"));
        ledgerTransactions.appendChild(LEDGERFROMITEM);


        Element name = doc.createElement("LEDGERNAME");
        name.appendChild(doc.createTextNode(contraDTO.getAccountName()));
        ledgerTransactions.appendChild(name);


        Element amount = doc.createElement("AMOUNT");
        amount.appendChild(doc.createTextNode("-" + contraDTO.getFinalAmount().toString() + "0"));
        ledgerTransactions.appendChild(amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
//        return doc;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp.TallyExport;

import gen.ImpExp.ImpExpUtil;
import gen.accountvoucher.payment.PaymentDTO;
import gen.dto.LedgerTransactionDTO;
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
public class PaymentTallyTagHelper {

    public static String generatePaymentVoucherNodeList(List<PaymentDTO> paymentDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        if (paymentDTOList != null && paymentDTOList.size() > 0) {
            for (PaymentDTO payment : paymentDTOList) {
                returnString = returnString + generatePaymentVoucherNodes(payment);
            }
        }
        return returnString;
    }

    public static String generatePaymentVoucherNodes(PaymentDTO paymentDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String last_ledger = generatePaymentVoucherMainNodes(paymentDTO);
        return last_ledger;
    }

    public static String generatePaymentVoucherMainNodes(PaymentDTO paymentDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
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
        VOUCHER.setAttribute("VCHTYPE", "Payment");
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
        voucherName.appendChild(doc.createTextNode("Payment"));
        VOUCHER.appendChild(voucherName);

        String split_date[] = paymentDTO.getDate().split("-");
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
        VOUCHERNUMBER.appendChild(doc.createTextNode(paymentDTO.getReceiptNo().toString()));
        VOUCHER.appendChild(VOUCHERNUMBER);

        Element PARTYLEDGERNAME = doc.createElement("PARTYLEDGERNAME");
        PARTYLEDGERNAME.appendChild(doc.createTextNode(paymentDTO.getAccountName()));
        VOUCHER.appendChild(PARTYLEDGERNAME);

        Element NARRATION = doc.createElement("NARRATION");
        NARRATION.appendChild(doc.createTextNode(paymentDTO.getNarration()));
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

        if (paymentDTO.getLedgerTransactionDTOList() != null && paymentDTO.getLedgerTransactionDTOList().size() > 0) {
            for (LedgerTransactionDTO ledgerTransactionDTO : paymentDTO.getLedgerTransactionDTOList()) {

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
                name.appendChild(doc.createTextNode(ledgerTransactionDTO.getName()));
                ledgerTransactions.appendChild(name);

                Element amount = doc.createElement("AMOUNT");
                amount.appendChild(doc.createTextNode("-" + ledgerTransactionDTO.getAmount().toString()));
                ledgerTransactions.appendChild(amount);
            }
        }

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
        name.appendChild(doc.createTextNode(paymentDTO.getAccountName()));
        ledgerTransactions.appendChild(name);

        Element amount = doc.createElement("AMOUNT");
        amount.appendChild(doc.createTextNode(paymentDTO.getFinalAmount().toString()));
        ledgerTransactions.appendChild(amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
//        return doc;
    }
}

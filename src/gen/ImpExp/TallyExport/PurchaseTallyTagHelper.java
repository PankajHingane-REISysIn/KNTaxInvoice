/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp.TallyExport;

import gen.ImpExp.*;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.dto.StockItemTransactionDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PurchaseTallyTagHelper {

    public static String generatePurchaseVoucherNodeList(List<PurchaseDTO> PurchaseDTOList) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String returnString = "";
        if (PurchaseDTOList != null && PurchaseDTOList.size() > 0) {
            for (PurchaseDTO purchaseDTO : PurchaseDTOList) {
                returnString = returnString + generatePaymentVoucherNodes(purchaseDTO);
            }
        }
        return returnString;
    }

    public static String generatePaymentVoucherNodes(PurchaseDTO purchaseDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String last_ledger = generatePaymentVoucherMainNodes(purchaseDTO);
        System.out.println("Return ------- " + last_ledger);
        return last_ledger;
    }

    public static String generatePaymentVoucherMainNodes(PurchaseDTO purchaseDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        try {

            Element TALLYMESSAGE = doc.createElement("TALLYMESSAGE");
            doc.appendChild(TALLYMESSAGE);

            Attr attr = doc.createAttribute("xmlns:UDF");
            attr.setValue("TallyUDF");
            TALLYMESSAGE.setAttributeNode(attr);

            Element VOUCHER = doc.createElement("VOUCHER");
            TALLYMESSAGE.appendChild(VOUCHER);

            gen.dto.Constants.REMOTE_ID_For_Tally = gen.dto.Constants.REMOTE_ID_For_Tally + 1;
            VOUCHER.setAttribute("REMOTEID", "aaaa-bbbb-cccc-" + gen.dto.Constants.REMOTE_ID_For_Tally);
            VOUCHER.setAttribute("VCHTYPE", "Purchase");
            VOUCHER.setAttribute("ACTION", "Create");


//        Element ADDRESS_List = doc.createElement("ADDRESS.LIST");
//        VOUCHER.appendChild(ADDRESS_List);
//
//        Element ADDRESS = doc.createElement("ADDRESS");
//        ADDRESS.appendChild(doc.createTextNode("Cash Address"));
//        ADDRESS_List.appendChild(ADDRESS);


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
            voucherName.appendChild(doc.createTextNode("Purchase"));
            VOUCHER.appendChild(voucherName);


            String split_date[] = purchaseDTO.getDate().split("-");
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
            VOUCHERNUMBER.appendChild(doc.createTextNode(purchaseDTO.getReceiptNo().toString()));
            VOUCHER.appendChild(VOUCHERNUMBER);

            Element REFERENCE = doc.createElement("REFERENCE");
            REFERENCE.appendChild(doc.createTextNode(purchaseDTO.getPurchaseReference().toString()));
            VOUCHER.appendChild(REFERENCE);

            // change depends upon ledger name
            Element VOUCHERNAME = doc.createElement("PARTYNAME");
//        VOUCHERNAME.appendChild(doc.createTextNode("Cash"));
            VOUCHERNAME.appendChild(doc.createTextNode(purchaseDTO.getCashLedger()));
            VOUCHER.appendChild(VOUCHERNAME);

            Element PARTYLEDGERNAME = doc.createElement("PARTYLEDGERNAME");
            PARTYLEDGERNAME.appendChild(doc.createTextNode(purchaseDTO.getCashLedger()));
            VOUCHER.appendChild(PARTYLEDGERNAME);

            Element NARRATION = doc.createElement("NARRATION");
            NARRATION.appendChild(doc.createTextNode(purchaseDTO.getNote()));
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

            Element ledgerTransactions_Cash = doc.createElement("ALLLEDGERENTRIES.LIST");
            VOUCHER.appendChild(ledgerTransactions_Cash);

            Element REMOVEZEROENTRIES_Cash = doc.createElement("REMOVEZEROENTRIES");
            REMOVEZEROENTRIES_Cash.appendChild(doc.createTextNode("No"));
            ledgerTransactions_Cash.appendChild(REMOVEZEROENTRIES_Cash);

            Element ISDEEMEDPOSITIVE_Cash = doc.createElement("ISDEEMEDPOSITIVE");
            ISDEEMEDPOSITIVE_Cash.appendChild(doc.createTextNode("No"));
            ledgerTransactions_Cash.appendChild(ISDEEMEDPOSITIVE_Cash);

            Element LEDGERFROMITEM_Cash = doc.createElement("LEDGERFROMITEM");
            LEDGERFROMITEM_Cash.appendChild(doc.createTextNode("No"));
            ledgerTransactions_Cash.appendChild(LEDGERFROMITEM_Cash);

            Element name_Cash = doc.createElement("LEDGERNAME");
            name_Cash.appendChild(doc.createTextNode(purchaseDTO.getCashLedger()));
            ledgerTransactions_Cash.appendChild(name_Cash);

            Element amount_Cash = doc.createElement("AMOUNT");
            amount_Cash.appendChild(doc.createTextNode(purchaseDTO.getFinalAmount().toString() + "0"));
            ledgerTransactions_Cash.appendChild(amount_Cash);

            Element ledgerTransactions = doc.createElement("ALLLEDGERENTRIES.LIST");
            VOUCHER.appendChild(ledgerTransactions);

            Element REMOVEZEROENTRIES = doc.createElement("REMOVEZEROENTRIES");
            REMOVEZEROENTRIES.appendChild(doc.createTextNode("No"));
            ledgerTransactions.appendChild(REMOVEZEROENTRIES);

            Element ISDEEMEDPOSITIVE = doc.createElement("ISDEEMEDPOSITIVE");
            ISDEEMEDPOSITIVE.appendChild(doc.createTextNode("Yes"));
            ledgerTransactions.appendChild(ISDEEMEDPOSITIVE);

            Element LEDGERFROMITEM_Cash1 = doc.createElement("LEDGERFROMITEM");
            LEDGERFROMITEM_Cash1.appendChild(doc.createTextNode("No"));
            ledgerTransactions.appendChild(LEDGERFROMITEM_Cash1);

            Element name_Cash1 = doc.createElement("LEDGERNAME");
            name_Cash1.appendChild(doc.createTextNode(purchaseDTO.getPurchaseLedger()));
            ledgerTransactions.appendChild(name_Cash1);

            Element amount_Cash1 = doc.createElement("AMOUNT");
            amount_Cash1.appendChild(doc.createTextNode("-" + purchaseDTO.getAmount().toString() + "0"));
            ledgerTransactions.appendChild(amount_Cash1);


            /// writing code for Vat value ------  
            if (purchaseDTO.getVatAmount() >= 0D) {

                Element ledgerTransactions_Cash_Round = doc.createElement("ALLLEDGERENTRIES.LIST");
                VOUCHER.appendChild(ledgerTransactions_Cash_Round);

                Element REMOVEZEROENTRIES_Cash_Round = doc.createElement("REMOVEZEROENTRIES");
                REMOVEZEROENTRIES_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(REMOVEZEROENTRIES_Cash_Round);

                Element ISDEEMEDPOSITIVE_Cash_Round = doc.createElement("ISDEEMEDPOSITIVE");
                ISDEEMEDPOSITIVE_Cash_Round.appendChild(doc.createTextNode("Yes"));
                ledgerTransactions_Cash_Round.appendChild(ISDEEMEDPOSITIVE_Cash_Round);

                Element LEDGERFROMITEM_Cash_Round = doc.createElement("LEDGERFROMITEM");
                LEDGERFROMITEM_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(LEDGERFROMITEM_Cash_Round);

                Element name_Cash_Round = doc.createElement("LEDGERNAME");
                name_Cash_Round.appendChild(doc.createTextNode("Input Vat @" + purchaseDTO.getVatRate() + "%"));
                ledgerTransactions_Cash_Round.appendChild(name_Cash_Round);

                Element amount_Cash_Round = doc.createElement("AMOUNT");
                amount_Cash_Round.appendChild(doc.createTextNode("-"+purchaseDTO.getVatAmount().toString()));
                ledgerTransactions_Cash_Round.appendChild(amount_Cash_Round);

                Element ledgerTransactions_RATEOFINVOICETAX = doc.createElement("UDF:RATEOFINVOICETAX.LIST");
                ledgerTransactions_Cash_Round.appendChild(ledgerTransactions_RATEOFINVOICETAX);
                ledgerTransactions_RATEOFINVOICETAX.setAttribute("DESC", "`RATEOFINVOICETAX`");
                ledgerTransactions_RATEOFINVOICETAX.setAttribute("ISLIST", "YES");

                Element ledgerTransactions_RATEOFINVOICETAX_value = doc.createElement("UDF:RATEOFINVOICETAX.LIST");
                ledgerTransactions_RATEOFINVOICETAX_value.appendChild(doc.createTextNode(purchaseDTO.getVatRate().toString()));
                ledgerTransactions_RATEOFINVOICETAX.appendChild(ledgerTransactions_RATEOFINVOICETAX_value);
                ledgerTransactions_RATEOFINVOICETAX_value.setAttribute("DESC", "`RATEOFINVOICETAX`");


            }
            ////////////////////////////////////////////

            /// writing code for round off value ------  
            if (purchaseDTO.getRoundOfAmount() >= 0D) {

                Element ledgerTransactions_Cash_Round = doc.createElement("ALLLEDGERENTRIES.LIST");
                VOUCHER.appendChild(ledgerTransactions_Cash_Round);

                Element REMOVEZEROENTRIES_Cash_Round = doc.createElement("REMOVEZEROENTRIES");
                REMOVEZEROENTRIES_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(REMOVEZEROENTRIES_Cash_Round);

                Element ISDEEMEDPOSITIVE_Cash_Round = doc.createElement("ISDEEMEDPOSITIVE");
                ISDEEMEDPOSITIVE_Cash_Round.appendChild(doc.createTextNode("Yes"));
                ledgerTransactions_Cash_Round.appendChild(ISDEEMEDPOSITIVE_Cash_Round);

                Element LEDGERFROMITEM_Cash_Round = doc.createElement("LEDGERFROMITEM");
                LEDGERFROMITEM_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(LEDGERFROMITEM_Cash_Round);

                Element name_Cash_Round = doc.createElement("LEDGERNAME");
                name_Cash_Round.appendChild(doc.createTextNode("Round Off"));
                ledgerTransactions_Cash_Round.appendChild(name_Cash_Round);

                Element amount_Cash_Round = doc.createElement("AMOUNT");
                amount_Cash_Round.appendChild(doc.createTextNode("-"+purchaseDTO.getRoundOfAmount().toString()));
                ledgerTransactions_Cash_Round.appendChild(amount_Cash_Round);
            }

            if (purchaseDTO.getStockItemTreansactioDTOList() != null && purchaseDTO.getStockItemTreansactioDTOList().size() > 0) {
                for (StockItemTransactionDTO stockTransactionDTO : purchaseDTO.getStockItemTreansactioDTOList()) {

                    Element ACCOUNTINGALLOCATIONS = doc.createElement("INVENTORYALLOCATIONS.LIST");
                    ledgerTransactions.appendChild(ACCOUNTINGALLOCATIONS);

                    Element ISDEEMEDPOSITIVE_Cash1 = doc.createElement("ISDEEMEDPOSITIVE");
                    ISDEEMEDPOSITIVE_Cash1.appendChild(doc.createTextNode("Yes"));
                    ACCOUNTINGALLOCATIONS.appendChild(ISDEEMEDPOSITIVE_Cash1);

                    Element STOCKITEMNAME = doc.createElement("STOCKITEMNAME");
                    STOCKITEMNAME.appendChild(doc.createTextNode(stockTransactionDTO.getName()));
                    ACCOUNTINGALLOCATIONS.appendChild(STOCKITEMNAME);

                    Element AMOUNT = doc.createElement("AMOUNT");
                    AMOUNT.appendChild(doc.createTextNode("-" + stockTransactionDTO.getAmount().toString() + "0"));
                    ACCOUNTINGALLOCATIONS.appendChild(AMOUNT);

                    Element ACTUALQTY = doc.createElement("ACTUALQTY");
                    ACTUALQTY.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ACCOUNTINGALLOCATIONS.appendChild(ACTUALQTY);

                    Element BILLEDQTY = doc.createElement("BILLEDQTY");
                    BILLEDQTY.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ACCOUNTINGALLOCATIONS.appendChild(BILLEDQTY);

                    Element RATE = doc.createElement("RATE");
                    RATE.appendChild(doc.createTextNode(" " + stockTransactionDTO.getRate().toString() + "0" + "/" + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ACCOUNTINGALLOCATIONS.appendChild(RATE);

//                Element LEDGERFROMITEM_Cash1 = doc.createElement("LEDGERFROMITEM");
//                LEDGERFROMITEM_Cash1.appendChild(doc.createTextNode("No"));
//                ACCOUNTINGALLOCATIONS.appendChild(LEDGERFROMITEM_Cash1);

//                Element name_Cash1 = doc.createElement("LEDGERNAME");
//                name_Cash1.appendChild(doc.createTextNode(purchaseDTO.getPurchaseLedger()));
//                ACCOUNTINGALLOCATIONS.appendChild(name_Cash1);

//                Element amount_Cash1 = doc.createElement("AMOUNT");
//                amount_Cash1.appendChild(doc.createTextNode("-" + stockTransactionDTO.getAmount().toString()));
//                ACCOUNTINGALLOCATIONS.appendChild(amount_Cash1);

                    Element BATCHALLOCATIONS = doc.createElement("BATCHALLOCATIONS.LIST");
                    ACCOUNTINGALLOCATIONS.appendChild(BATCHALLOCATIONS);

                    Element TRACKINGNUMBER = doc.createElement("TRACKINGNUMBER");
                    TRACKINGNUMBER.appendChild(doc.createTextNode(""));
                    BATCHALLOCATIONS.appendChild(TRACKINGNUMBER);

                    Element BATCHNAME = doc.createElement("BATCHNAME");
                    BATCHNAME.appendChild(doc.createTextNode("Primary Batch"));
                    BATCHALLOCATIONS.appendChild(BATCHNAME);

                    Element GODOWNNAME = doc.createElement("GODOWNNAME");
                    GODOWNNAME.appendChild(doc.createTextNode("Main Location"));
                    BATCHALLOCATIONS.appendChild(GODOWNNAME);

                    Element MFDON = doc.createElement("MFDON");
                    MFDON.appendChild(doc.createTextNode(assign_date));
                    BATCHALLOCATIONS.appendChild(MFDON);

                    Element EXPIRYPERIOD = doc.createElement("EXPIRYPERIOD");
                    EXPIRYPERIOD.appendChild(doc.createTextNode(""));
                    BATCHALLOCATIONS.appendChild(EXPIRYPERIOD);

                    Element AMOUNT1 = doc.createElement("AMOUNT");
                    AMOUNT1.appendChild(doc.createTextNode("-" + stockTransactionDTO.getAmount().toString() + "0"));
                    BATCHALLOCATIONS.appendChild(AMOUNT1);

                    Element ACTUALQTY1 = doc.createElement("ACTUALQTY");
                    ACTUALQTY1.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    BATCHALLOCATIONS.appendChild(ACTUALQTY1);

                    Element BILLEDQTY1 = doc.createElement("BILLEDQTY");
                    BILLEDQTY1.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    BATCHALLOCATIONS.appendChild(BILLEDQTY1);

                    Element ORDERNO = doc.createElement("ORDERNO");
                    ORDERNO.appendChild(doc.createTextNode(""));
                    BATCHALLOCATIONS.appendChild(ORDERNO);

                }
            }

            Element BUYERNAME_LIST = doc.createElement("UDF:BUYERNAME.LIST");
            VOUCHER.appendChild(BUYERNAME_LIST);
            BUYERNAME_LIST.setAttribute("DESC", "`BUYERNAME`");
            BUYERNAME_LIST.setAttribute("ISLIST", "YES");

            // get company Name
            Element BUYERNAME = doc.createElement("UDF:BUYERNAME");
            BUYERNAME.appendChild(doc.createTextNode(gen.dto.Constants.CURRENT_COMPANY_NAME.toUpperCase()));
            BUYERNAME_LIST.appendChild(BUYERNAME);
            BUYERNAME.setAttribute("DESC", "`BUYERNAME`");


            Element BUYERADDRESS_LIST = doc.createElement("UDF:BUYERADDRESS.LIST");
            VOUCHER.appendChild(BUYERADDRESS_LIST);
            BUYERADDRESS_LIST.setAttribute("DESC", "`BUYERADDRESS`");
            BUYERADDRESS_LIST.setAttribute("ISLIST", "YES");

            // get company Address
            Element BUYERADDRESS = doc.createElement("UDF:BUYERADDRESS");
            BUYERADDRESS.appendChild(doc.createTextNode("Sadsd"));
            BUYERADDRESS_LIST.appendChild(BUYERADDRESS);
            BUYERADDRESS.setAttribute("DESC", "`BUYERADDRESS`");


            Element BUYERSSALESTAXNO_LIST = doc.createElement("UDF:BUYERSSALESTAXNO.LIST");
            VOUCHER.appendChild(BUYERSSALESTAXNO_LIST);
            BUYERSSALESTAXNO_LIST.setAttribute("DESC", "`BUYERSSALESTAXNO`");
            BUYERSSALESTAXNO_LIST.setAttribute("ISLIST", "YES");

            // get company Sale Tax no
            Element BUYERSSALESTAXNO = doc.createElement("UDF:BUYERSSALESTAXNO");
            BUYERSSALESTAXNO.appendChild(doc.createTextNode("cash sale tax no"));
            BUYERSSALESTAXNO_LIST.appendChild(BUYERSSALESTAXNO);
            BUYERSSALESTAXNO.setAttribute("DESC", "`BUYERSSALESTAXNO`");

            Element DATETIMEOFINVOICE_LIST = doc.createElement("UDF:DATETIMEOFINVOICE.LIST");
            VOUCHER.appendChild(DATETIMEOFINVOICE_LIST);
            DATETIMEOFINVOICE_LIST.setAttribute("DESC", "`DATETIMEOFINVOICE`");
            DATETIMEOFINVOICE_LIST.setAttribute("ISLIST", "YES");

            Element DATETIMEOFINVOICE = doc.createElement("UDF:DATETIMEOFINVOICE");
            DATETIMEOFINVOICE.appendChild(doc.createTextNode(gen.dto.Util.getDate(purchaseDTO.getDate().toString()) + " at " + gen.dto.Util.getCurrentTime()));
            DATETIMEOFINVOICE_LIST.appendChild(DATETIMEOFINVOICE);
            DATETIMEOFINVOICE.setAttribute("DESC", "`DATETIMEOFINVOICE`");

            Element DATETIMEOFREMOVAL_LIST = doc.createElement("UDF:DATETIMEOFREMOVAL.LIST");
            VOUCHER.appendChild(DATETIMEOFREMOVAL_LIST);
            DATETIMEOFREMOVAL_LIST.setAttribute("DESC", "`DATETIMEOFREMOVAL`");
            DATETIMEOFREMOVAL_LIST.setAttribute("ISLIST", "YES");

            Element DATETIMEOFREMOVAL = doc.createElement("UDF:DATETIMEOFREMOVAL");
            DATETIMEOFREMOVAL.appendChild(doc.createTextNode(gen.dto.Util.getDate(purchaseDTO.getDate().toString()) + " at " + gen.dto.Util.getCurrentTime()));
            DATETIMEOFREMOVAL_LIST.appendChild(DATETIMEOFREMOVAL);
            DATETIMEOFREMOVAL.setAttribute("DESC", "`DATETIMEOFREMOVAL`");

            Element BASEPARTYNAME_LIST = doc.createElement("UDF:BASEPARTYNAME.LIST");
            VOUCHER.appendChild(BASEPARTYNAME_LIST);
            BASEPARTYNAME_LIST.setAttribute("DESC", "`BASEPARTYNAME`");
            BASEPARTYNAME_LIST.setAttribute("ISLIST", "YES");

            // if Cash Ledger then Cash Unlesss Bank
            Element BASEPARTYNAME = doc.createElement("UDF:BASEPARTYNAME");
            BASEPARTYNAME.appendChild(doc.createTextNode(purchaseDTO.getCashLedger()));
            BASEPARTYNAME_LIST.appendChild(BASEPARTYNAME);
            BASEPARTYNAME.setAttribute("DESC", "`BASEPARTYNAME`");


//        return doc;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PurchaseTallyTagHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

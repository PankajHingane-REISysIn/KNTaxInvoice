/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp.TallyExport;

import gen.ImpExp.*;
import gen.accountvoucher.sale.SaleDTO;
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
public class SaleTallyTagHelper {

    public static String generateSaleVoucherNodeList(List<SaleDTO> SaleDTOList) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String returnString = "";
        if (SaleDTOList != null && SaleDTOList.size() > 0) {
            for (SaleDTO saleDTO : SaleDTOList) {
                returnString = returnString + generatePaymentVoucherNodes(saleDTO);
            }
        }
        return returnString;
    }

    public static String generatePaymentVoucherNodes(SaleDTO saleDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String last_ledger = generatePaymentVoucherMainNodes(saleDTO);
        System.out.println("Return ------- " + last_ledger);
        return last_ledger;
    }

    public static String generatePaymentVoucherMainNodes(SaleDTO saleDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        try {
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
            VOUCHER.setAttribute("VCHTYPE", "Sales");
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
            voucherName.appendChild(doc.createTextNode("Sales"));
            VOUCHER.appendChild(voucherName);

            String split_date[] = saleDTO.getDate().split("-");
            String assign_date = "";
            for (String assign_date1 : split_date) {
                assign_date = assign_date + assign_date1;
            }

            Element date = doc.createElement("DATE");
            // System.out.println("assign date ------ "+assign_date);
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
            ISINVOICE.appendChild(doc.createTextNode("Yes"));
            VOUCHER.appendChild(ISINVOICE);

            Element DIFFACTUALQTY = doc.createElement("DIFFACTUALQTY");
            DIFFACTUALQTY.appendChild(doc.createTextNode("No"));
            VOUCHER.appendChild(DIFFACTUALQTY);

            Element VOUCHERNUMBER = doc.createElement("VOUCHERNUMBER");
            VOUCHERNUMBER.appendChild(doc.createTextNode(saleDTO.getReceiptNo().toString()));
            VOUCHER.appendChild(VOUCHERNUMBER);

            Element VOUCHERNAME = doc.createElement("PARTYNAME");
            VOUCHERNAME.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
            VOUCHER.appendChild(VOUCHERNAME);

            Element PARTYLEDGERNAME = doc.createElement("PARTYLEDGERNAME");
            PARTYLEDGERNAME.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
            VOUCHER.appendChild(PARTYLEDGERNAME);

            Element NARRATION = doc.createElement("NARRATION");
            NARRATION.appendChild(doc.createTextNode(saleDTO.getNote()));
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

            Element INVOICEDELNOTES = doc.createElement("INVOICEDELNOTES.LIST");
            VOUCHER.appendChild(INVOICEDELNOTES);

            Element SHIPDELIVERYNOTE = doc.createElement("UDF:SHIPDELIVERYNOTE");
            SHIPDELIVERYNOTE.appendChild(doc.createTextNode(saleDTO.getNote()));
            INVOICEDELNOTES.appendChild(SHIPDELIVERYNOTE);
            SHIPDELIVERYNOTE.setAttribute("DESC", "`SHIPDELIVERYNOTE`");

            Element SHIPPINGDATE = doc.createElement("UDF:SHIPPINGDATE");
            SHIPPINGDATE.appendChild(doc.createTextNode(assign_date));
            INVOICEDELNOTES.appendChild(SHIPPINGDATE);
            SHIPPINGDATE.setAttribute("DESC", "`SHIPPINGDATE`");


            Element INVOICEORDERLIST = doc.createElement("INVOICEORDERLIST.LIST");
            VOUCHER.appendChild(INVOICEORDERLIST);

            Element PURCHASEORDERNO = doc.createElement("UDF:PURCHASEORDERNO");
            PURCHASEORDERNO.appendChild(doc.createTextNode(""));
            INVOICEORDERLIST.appendChild(PURCHASEORDERNO);
            PURCHASEORDERNO.setAttribute("DESC", "`PURCHASEORDERNO`");

            Element ORDERDATE = doc.createElement("UDF:ORDERDATE");
            ORDERDATE.appendChild(doc.createTextNode(assign_date));
            INVOICEORDERLIST.appendChild(ORDERDATE);
            ORDERDATE.setAttribute("DESC", "`ORDERDATE`");

            Element ledgerTransactions_Cash = doc.createElement("LEDGERENTRIES.LIST");
            VOUCHER.appendChild(ledgerTransactions_Cash);

            Element REMOVEZEROENTRIES_Cash = doc.createElement("REMOVEZEROENTRIES");
            REMOVEZEROENTRIES_Cash.appendChild(doc.createTextNode("No"));
            ledgerTransactions_Cash.appendChild(REMOVEZEROENTRIES_Cash);

            Element ISDEEMEDPOSITIVE_Cash = doc.createElement("ISDEEMEDPOSITIVE");
            ISDEEMEDPOSITIVE_Cash.appendChild(doc.createTextNode("Yes"));
            ledgerTransactions_Cash.appendChild(ISDEEMEDPOSITIVE_Cash);

            Element LEDGERFROMITEM_Cash = doc.createElement("LEDGERFROMITEM");
            LEDGERFROMITEM_Cash.appendChild(doc.createTextNode("No"));
            ledgerTransactions_Cash.appendChild(LEDGERFROMITEM_Cash);

            Element name_Cash = doc.createElement("LEDGERNAME");
            name_Cash.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
            ledgerTransactions_Cash.appendChild(name_Cash);

            Element amount_Cash = doc.createElement("AMOUNT");
            amount_Cash.appendChild(doc.createTextNode("-" + saleDTO.getFinalAmount().toString() + "0"));
            ledgerTransactions_Cash.appendChild(amount_Cash);

            Element BILLALLOCATIONS_List = doc.createElement("BILLALLOCATIONS.LIST");
            ledgerTransactions_Cash.appendChild(BILLALLOCATIONS_List);

            Element NAME = doc.createElement("NAME");
            NAME.appendChild(doc.createTextNode(saleDTO.getReceiptNo().toString()));
            BILLALLOCATIONS_List.appendChild(NAME);

            Element BILLTYPE = doc.createElement("BILLTYPE");
            BILLTYPE.appendChild(doc.createTextNode("New Ref"));
            BILLALLOCATIONS_List.appendChild(BILLTYPE);

            Element BILLCREDITPERIOD = doc.createElement("BILLCREDITPERIOD");
//        BILLCREDITPERIOD.appendChild(doc.createTextNode("-" + saleDTO.getFinalAmount().toString()));
            BILLALLOCATIONS_List.appendChild(BILLCREDITPERIOD);

            Element amount_Bill = doc.createElement("AMOUNT");
            amount_Bill.appendChild(doc.createTextNode("-" + saleDTO.getFinalAmount().toString() + "0"));
            BILLALLOCATIONS_List.appendChild(amount_Bill);

            /// writing code for Vat value ------  
            if (saleDTO.getVatAmount() >= 0D) {

                Element ledgerTransactions_Cash_Round = doc.createElement("LEDGERENTRIES.LIST");
                VOUCHER.appendChild(ledgerTransactions_Cash_Round);

                Element REMOVEZEROENTRIES_Cash_Round = doc.createElement("REMOVEZEROENTRIES");
                REMOVEZEROENTRIES_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(REMOVEZEROENTRIES_Cash_Round);

                Element ISDEEMEDPOSITIVE_Cash_Round = doc.createElement("ISDEEMEDPOSITIVE");
                ISDEEMEDPOSITIVE_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(ISDEEMEDPOSITIVE_Cash_Round);

                Element LEDGERFROMITEM_Cash_Round = doc.createElement("LEDGERFROMITEM");
                LEDGERFROMITEM_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(LEDGERFROMITEM_Cash_Round);

                Element name_Cash_Round = doc.createElement("LEDGERNAME");
                name_Cash_Round.appendChild(doc.createTextNode("Output Vat @" + saleDTO.getVatRate() + "%"));
                ledgerTransactions_Cash_Round.appendChild(name_Cash_Round);

                Element amount_Cash_Round = doc.createElement("AMOUNT");
                amount_Cash_Round.appendChild(doc.createTextNode(saleDTO.getVatAmount().toString()));
                ledgerTransactions_Cash_Round.appendChild(amount_Cash_Round);

                Element ledgerTransactions_RATEOFINVOICETAX = doc.createElement("UDF:RATEOFINVOICETAX.LIST");
                ledgerTransactions_Cash_Round.appendChild(ledgerTransactions_RATEOFINVOICETAX);
                ledgerTransactions_RATEOFINVOICETAX.setAttribute("DESC", "`RATEOFINVOICETAX`");
                ledgerTransactions_RATEOFINVOICETAX.setAttribute("ISLIST", "YES");

                Element ledgerTransactions_RATEOFINVOICETAX_value = doc.createElement("UDF:RATEOFINVOICETAX.LIST");
                ledgerTransactions_RATEOFINVOICETAX_value.appendChild(doc.createTextNode(saleDTO.getVatRate().toString()));
                ledgerTransactions_RATEOFINVOICETAX.appendChild(ledgerTransactions_RATEOFINVOICETAX_value);
                ledgerTransactions_RATEOFINVOICETAX_value.setAttribute("DESC", "`RATEOFINVOICETAX`");


            }
            //////////////////////////////////////////// 

            /// writing code for round off value ------  
            if (saleDTO.getRoundOffAmount() >= 0D) {

                Element ledgerTransactions_Cash_Round = doc.createElement("LEDGERENTRIES.LIST");
                VOUCHER.appendChild(ledgerTransactions_Cash_Round);

                Element REMOVEZEROENTRIES_Cash_Round = doc.createElement("REMOVEZEROENTRIES");
                REMOVEZEROENTRIES_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(REMOVEZEROENTRIES_Cash_Round);

                Element ISDEEMEDPOSITIVE_Cash_Round = doc.createElement("ISDEEMEDPOSITIVE");
                ISDEEMEDPOSITIVE_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(ISDEEMEDPOSITIVE_Cash_Round);

                Element LEDGERFROMITEM_Cash_Round = doc.createElement("LEDGERFROMITEM");
                LEDGERFROMITEM_Cash_Round.appendChild(doc.createTextNode("No"));
                ledgerTransactions_Cash_Round.appendChild(LEDGERFROMITEM_Cash_Round);

                Element name_Cash_Round = doc.createElement("LEDGERNAME");
                name_Cash_Round.appendChild(doc.createTextNode("Round Off"));
                ledgerTransactions_Cash_Round.appendChild(name_Cash_Round);

                Element amount_Cash_Round = doc.createElement("AMOUNT");
                amount_Cash_Round.appendChild(doc.createTextNode(saleDTO.getRoundOffAmount().toString()));
                ledgerTransactions_Cash_Round.appendChild(amount_Cash_Round);
            }
            //////////////////////////////////////////// 

            if (saleDTO.getStockItemTreansactioDTOList() != null && saleDTO.getStockItemTreansactioDTOList().size() > 0) {
                for (StockItemTransactionDTO stockTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {

                    Element ledgerTransactions = doc.createElement("ALLINVENTORYENTRIES.LIST");
                    VOUCHER.appendChild(ledgerTransactions);

//                Element REMOVEZEROENTRIES = doc.createElement("REMOVEZEROENTRIES");
//                REMOVEZEROENTRIES.appendChild(doc.createTextNode("No"));
//                ledgerTransactions.appendChild(REMOVEZEROENTRIES);

                    Element ISDEEMEDPOSITIVE = doc.createElement("ISDEEMEDPOSITIVE");
                    ISDEEMEDPOSITIVE.appendChild(doc.createTextNode("No"));
                    ledgerTransactions.appendChild(ISDEEMEDPOSITIVE);

                    Element STOCKITEMNAME = doc.createElement("STOCKITEMNAME");
                    STOCKITEMNAME.appendChild(doc.createTextNode(stockTransactionDTO.getName()));
                    ledgerTransactions.appendChild(STOCKITEMNAME);

                    Element AMOUNT = doc.createElement("AMOUNT");
                    AMOUNT.appendChild(doc.createTextNode(stockTransactionDTO.getAmount().toString() + "0"));
                    ledgerTransactions.appendChild(AMOUNT);

                    Element ACTUALQTY = doc.createElement("ACTUALQTY");
//                ACTUALQTY.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " a"));
                    System.out.println("Stock item Nmae ----------- " + stockTransactionDTO.getName());
                    System.out.println("Stock item Nmae ----------- " + TagsHelper1.stockItemNameToUOMMapping.keySet());
                    System.out.println("UOM---------- " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName()));
                    ACTUALQTY.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ledgerTransactions.appendChild(ACTUALQTY);

                    Element BILLEDQTY = doc.createElement("BILLEDQTY");
                    BILLEDQTY.appendChild(doc.createTextNode(" " + stockTransactionDTO.getQuantity().toString() + "0" + " " + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ledgerTransactions.appendChild(BILLEDQTY);

                    Element RATE = doc.createElement("RATE");
                    RATE.appendChild(doc.createTextNode(" " + stockTransactionDTO.getRate().toString() + "0" + "/" + TagsHelper1.stockItemNameToUOMMapping.get(stockTransactionDTO.getName())));
                    ledgerTransactions.appendChild(RATE);

                    Element ACCOUNTINGALLOCATIONS = doc.createElement("ACCOUNTINGALLOCATIONS.LIST");
                    ledgerTransactions.appendChild(ACCOUNTINGALLOCATIONS);

                    Element REMOVEZEROENTRIES_Cash1 = doc.createElement("REMOVEZEROENTRIES");
                    REMOVEZEROENTRIES_Cash1.appendChild(doc.createTextNode("No"));
                    ACCOUNTINGALLOCATIONS.appendChild(REMOVEZEROENTRIES_Cash1);

                    Element ISDEEMEDPOSITIVE_Cash1 = doc.createElement("ISDEEMEDPOSITIVE");
                    ISDEEMEDPOSITIVE_Cash1.appendChild(doc.createTextNode("No"));
                    ACCOUNTINGALLOCATIONS.appendChild(ISDEEMEDPOSITIVE_Cash1);

                    Element LEDGERFROMITEM_Cash1 = doc.createElement("LEDGERFROMITEM");
                    LEDGERFROMITEM_Cash1.appendChild(doc.createTextNode("No"));
                    ACCOUNTINGALLOCATIONS.appendChild(LEDGERFROMITEM_Cash1);

                    Element name_Cash1 = doc.createElement("LEDGERNAME");
                    name_Cash1.appendChild(doc.createTextNode(saleDTO.getSaleLedger()));
                    ACCOUNTINGALLOCATIONS.appendChild(name_Cash1);

                    Element amount_Cash1 = doc.createElement("AMOUNT");
                    amount_Cash1.appendChild(doc.createTextNode(stockTransactionDTO.getAmount().toString() + "0"));
                    ACCOUNTINGALLOCATIONS.appendChild(amount_Cash1);

                    Element BATCHALLOCATIONS = doc.createElement("BATCHALLOCATIONS.LIST");
                    ledgerTransactions.appendChild(BATCHALLOCATIONS);

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
                    AMOUNT1.appendChild(doc.createTextNode(stockTransactionDTO.getAmount().toString() + "0"));
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


            Element SHIPPEDBY_LIST = doc.createElement("UDF:SHIPPEDBY.LIST");
            VOUCHER.appendChild(SHIPPEDBY_LIST);
            SHIPPEDBY_LIST.setAttribute("DESC", "`SHIPPEDBY`");
            SHIPPEDBY_LIST.setAttribute("ISLIST", "YES");

            Element SHIPPEDBY = doc.createElement("UDF:SHIPPEDBY");
            SHIPPEDBY.appendChild(doc.createTextNode(saleDTO.getDispatchDocThrough()));
            SHIPPEDBY_LIST.appendChild(SHIPPEDBY);
            SHIPPEDBY.setAttribute("DESC", "`SHIPPEDBY`");


            Element BUYERNAME_LIST = doc.createElement("UDF:BUYERNAME.LIST");
            VOUCHER.appendChild(BUYERNAME_LIST);
            BUYERNAME_LIST.setAttribute("DESC", "`BUYERNAME`");
            BUYERNAME_LIST.setAttribute("ISLIST", "YES");

            Element BUYERNAME = doc.createElement("UDF:BUYERNAME");
            BUYERNAME.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
            BUYERNAME_LIST.appendChild(BUYERNAME);
            BUYERNAME.setAttribute("DESC", "`BUYERNAME`");


            Element BUYERADDRESS_LIST = doc.createElement("UDF:BUYERADDRESS.LIST");
            VOUCHER.appendChild(BUYERADDRESS_LIST);
            BUYERADDRESS_LIST.setAttribute("DESC", "`BUYERADDRESS`");
            BUYERADDRESS_LIST.setAttribute("ISLIST", "YES");

            Element BUYERADDRESS = doc.createElement("UDF:BUYERADDRESS");
            BUYERADDRESS.appendChild(doc.createTextNode("adres s bank"));
            BUYERADDRESS_LIST.appendChild(BUYERADDRESS);
            BUYERADDRESS.setAttribute("DESC", "`BUYERADDRESS`");


            Element SHIPDOCUMENTNO_LIST = doc.createElement("UDF:SHIPDOCUMENTNO.LIST");
            VOUCHER.appendChild(SHIPDOCUMENTNO_LIST);
            SHIPDOCUMENTNO_LIST.setAttribute("DESC", "`SHIPDOCUMENTNO`");
            SHIPDOCUMENTNO_LIST.setAttribute("ISLIST", "YES");

            Element SHIPDOCUMENTNO = doc.createElement("UDF:SHIPDOCUMENTNO");
            SHIPDOCUMENTNO.appendChild(doc.createTextNode(saleDTO.getDispatchDocNo()));
            SHIPDOCUMENTNO_LIST.appendChild(SHIPDOCUMENTNO);
            SHIPDOCUMENTNO.setAttribute("DESC", "`SHIPDOCUMENTNO`");


            Element FINALDESTINATION_LIST = doc.createElement("UDF:FINALDESTINATION.LIST");
            VOUCHER.appendChild(FINALDESTINATION_LIST);
            FINALDESTINATION_LIST.setAttribute("DESC", "`FINALDESTINATION`");
            FINALDESTINATION_LIST.setAttribute("ISLIST", "YES");

            Element FINALDESTINATION = doc.createElement("UDF:FINALDESTINATION");
            FINALDESTINATION.appendChild(doc.createTextNode("Destiantion"));
            FINALDESTINATION_LIST.appendChild(FINALDESTINATION);
            FINALDESTINATION.setAttribute("DESC", "`FINALDESTINATION`");


            Element ORDERREF_LIST = doc.createElement("UDF:ORDERREF.LIST");
            VOUCHER.appendChild(ORDERREF_LIST);
            ORDERREF_LIST.setAttribute("DESC", "`ORDERREF`");
            ORDERREF_LIST.setAttribute("ISLIST", "YES");

            Element ORDERREF = doc.createElement("UDF:ORDERREF");
            ORDERREF.appendChild(doc.createTextNode("rfeference Number"));
            ORDERREF_LIST.appendChild(ORDERREF);
            ORDERREF.setAttribute("DESC", "`ORDERREF`");


            Element ORDERTERMS_LIST = doc.createElement("UDF:ORDERTERMS.LIST");
            VOUCHER.appendChild(ORDERTERMS_LIST);
            ORDERTERMS_LIST.setAttribute("DESC", "`ORDERTERMS`");
            ORDERTERMS_LIST.setAttribute("ISLIST", "YES");

            Element ORDERTERMS = doc.createElement("UDF:ORDERTERMS");
            ORDERTERMS.appendChild(doc.createTextNode("OrderTerms"));
            ORDERTERMS_LIST.appendChild(ORDERTERMS);
            ORDERTERMS.setAttribute("DESC", "`ORDERTERMS`");


            Element BUYERSSALESTAXNO_LIST = doc.createElement("UDF:BUYERSSALESTAXNO.LIST");
            VOUCHER.appendChild(BUYERSSALESTAXNO_LIST);
            BUYERSSALESTAXNO_LIST.setAttribute("DESC", "`BUYERSSALESTAXNO`");
            BUYERSSALESTAXNO_LIST.setAttribute("ISLIST", "YES");

            Element BUYERSSALESTAXNO = doc.createElement("UDF:BUYERSSALESTAXNO");
            BUYERSSALESTAXNO.appendChild(doc.createTextNode("BUYERSSALESTAXNO"));
            BUYERSSALESTAXNO_LIST.appendChild(BUYERSSALESTAXNO);
            BUYERSSALESTAXNO.setAttribute("DESC", "`BUYERSSALESTAXNO`");

            Element DUEDATEOFPYMT_LIST = doc.createElement("UDF:DUEDATEOFPYMT.LIST");
            VOUCHER.appendChild(DUEDATEOFPYMT_LIST);
            DUEDATEOFPYMT_LIST.setAttribute("DESC", "`DUEDATEOFPYMT`");
            DUEDATEOFPYMT_LIST.setAttribute("ISLIST", "YES");

            Element DUEDATEOFPYMT = doc.createElement("UDF:DUEDATEOFPYMT");
            DUEDATEOFPYMT.appendChild(doc.createTextNode(saleDTO.getPaymentMode()));
            DUEDATEOFPYMT_LIST.appendChild(DUEDATEOFPYMT);
            DUEDATEOFPYMT.setAttribute("DESC", "`DUEDATEOFPYMT`");

            Element DATETIMEOFINVOICE_LIST = doc.createElement("UDF:DATETIMEOFINVOICE.LIST");
            VOUCHER.appendChild(DATETIMEOFINVOICE_LIST);
            DATETIMEOFINVOICE_LIST.setAttribute("DESC", "`DATETIMEOFINVOICE`");
            DATETIMEOFINVOICE_LIST.setAttribute("ISLIST", "YES");

            Element DATETIMEOFINVOICE = doc.createElement("UDF:DATETIMEOFINVOICE");
            DATETIMEOFINVOICE.appendChild(doc.createTextNode(gen.dto.Util.getDate(saleDTO.getDate().toString()) + " at " + gen.dto.Util.getCurrentTime()));
            DATETIMEOFINVOICE_LIST.appendChild(DATETIMEOFINVOICE);
            DATETIMEOFINVOICE.setAttribute("DESC", "`DATETIMEOFINVOICE`");

            Element DATETIMEOFREMOVAL_LIST = doc.createElement("UDF:DATETIMEOFREMOVAL.LIST");
            VOUCHER.appendChild(DATETIMEOFREMOVAL_LIST);
            DATETIMEOFREMOVAL_LIST.setAttribute("DESC", "`DATETIMEOFREMOVAL`");
            DATETIMEOFREMOVAL_LIST.setAttribute("ISLIST", "YES");

            Element DATETIMEOFREMOVAL = doc.createElement("UDF:DATETIMEOFREMOVAL");
            System.out.println("Date -=          " + saleDTO.getDate());
            DATETIMEOFREMOVAL.appendChild(doc.createTextNode(gen.dto.Util.getDate(saleDTO.getDate().toString()) + " at " + gen.dto.Util.getCurrentTime()));
            System.out.println("EE             " + gen.dto.Util.getDate(saleDTO.getDate().toString()) + " at " + gen.dto.Util.getCurrentTime());
            DATETIMEOFREMOVAL_LIST.appendChild(DATETIMEOFREMOVAL);
            DATETIMEOFREMOVAL.setAttribute("DESC", "`DATETIMEOFREMOVAL`");

            Element BASEPARTYNAME_LIST = doc.createElement("UDF:BASEPARTYNAME.LIST");
            VOUCHER.appendChild(BASEPARTYNAME_LIST);
            BASEPARTYNAME_LIST.setAttribute("DESC", "`BASEPARTYNAME`");
            BASEPARTYNAME_LIST.setAttribute("ISLIST", "YES");

            Element BASEPARTYNAME = doc.createElement("UDF:BASEPARTYNAME");
            BASEPARTYNAME.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
            BASEPARTYNAME_LIST.appendChild(BASEPARTYNAME);
            BASEPARTYNAME.setAttribute("DESC", "`BASEPARTYNAME`");

            return ImpExpUtil.convertDoctoDOMSource(doc);
//        return "sdfd";
//        return doc;
        } catch (Exception ex) {
            Logger.getLogger(SaleTallyTagHelper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }
    }
}

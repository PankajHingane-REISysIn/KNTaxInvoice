/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.account.ledger.LedgerDTO;
import gen.account.stockitem.StockItemDTO;
import gen.account.unitofmeasure.UnitOfMeasureDTO;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.contra.ContraDTO;
import gen.accountvoucher.journal.JournalDTO;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.sale.SaleDTO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 *
 * @author pc5
 */
public class ImpExpUtil {

    public static String convertDoctoDOMSource(Document doc) throws TransformerConfigurationException, TransformerException {
        DOMSource domSource = new DOMSource(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        java.io.StringWriter stringWriter = new java.io.StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        transformer.transform(domSource, streamResult);
        String outputString = stringWriter.toString();
        return outputString;
    }

    public static SaleDTO valiDateAndRepairSaleDTO(SaleDTO saleDTO) {
        System.out.println("valiDateAndRepairSaleDTO()");
        if (saleDTO.getAmount() == null) {
            saleDTO.setAmount(0D);
        }

        if (saleDTO.getCashLedger() == null) {
//	    saleDTO.setCashLedger(" ");
            saleDTO.setCashLedger(null);
        }

        if (saleDTO.getDate() == null) {
            saleDTO.setDate(null);
        }

        if (saleDTO.getDispatchDocNo() == null) {
            saleDTO.setDispatchDocNo(" ");
        }

        if (saleDTO.getDispatchDocThrough() == null) {
            saleDTO.setDispatchDocThrough(" ");
        }

        if (saleDTO.getFinalAmount() == null) {
            saleDTO.setFinalAmount(0D);
        }

        if (saleDTO.getLessBillAmount() == null) {
            saleDTO.setLessBillAmount(0D);
        }

        if (saleDTO.getNote() == null) {
            saleDTO.setNote(" ");
        }

        if (saleDTO.getReceiptNo() == null) {
            saleDTO.setReceiptNo(0);
        }

        if (saleDTO.getSaleLedger() == null) {
            saleDTO.setSaleLedger(" ");
        }

        if (saleDTO.getTransport() == null) {
            saleDTO.setTransport(0D);
        }

        if (saleDTO.getVatAmount() == null) {
            saleDTO.setVatAmount(0D);
        }

        if (saleDTO.getVatRate() == null) {
            saleDTO.setVatRate(0D);
        }

        if (saleDTO.getPaymentMode() == null) {
            saleDTO.setPaymentMode(" ");
        }
        return saleDTO;
    }

    // Code remove by Atul As Method is for Group but pass LedgerDTO which is already present and GroupDTO is not present
//    public static LedgerDTO valiDateAndRepairGroupDTO(LedgerDTO ledgerDTO) {
//        if (ledgerDTO.getDebitOrCredit() == null) {
//            ledgerDTO.setDebitOrCredit(0);
//        }
//
//        if (ledgerDTO.getLedger_Address() == null) {
//            ledgerDTO.setLedger_Address(" ");
//        }
//
//        if (ledgerDTO.getLedger_Alias() == null) {
//            ledgerDTO.setLedger_Alias(" ");
//        }
//
//        if (ledgerDTO.getLedger_ContactNo() == null) {
//            ledgerDTO.setLedger_ContactNo(" ");
//        }
//
//        if (ledgerDTO.getLedger_CreditLimit() == null) {
//            ledgerDTO.setLedger_CreditLimit(0.0);
//        }
//
//        if (ledgerDTO.getLedger_EmailID() == null) {
//            ledgerDTO.setLedger_EmailID(" ");
//        }
//
//        if (ledgerDTO.getLedger_IncomeTaxNo() == null) {
//            ledgerDTO.setLedger_IncomeTaxNo(" ");
//        }
//
//        if (ledgerDTO.getLedger_Name() == null) {
//            ledgerDTO.setLedger_Name(" ");
//        }
//
//        if (ledgerDTO.getLedger_OpeningBalence() == null) {
//            ledgerDTO.setLedger_OpeningBalence(0.0);
//        }
//
//        if (ledgerDTO.getLedger_SaleTaxNo() == null) {
//            ledgerDTO.setLedger_SaleTaxNo(" ");
//        }
//
//        if (ledgerDTO.getLedger_Under() == null) {
//            ledgerDTO.setLedger_Under(" ");
//        }
//
//        return ledgerDTO;
//    }
    public static gen.account.groupDTODAO.GroupDTO valiDateAndRepairgroupDTO(gen.account.groupDTODAO.GroupDTO groupDTO) {

        if (groupDTO.getGroup_Name() == null) {
            groupDTO.setGroup_Name(" ");
        }

        if (groupDTO.getGroup_Alias() == null) {
            groupDTO.setGroup_Alias(" ");
        }

        if (groupDTO.getGroup_Under() == null) {
//	    groupDTO.setGroup_Under(" ");
            groupDTO.setGroup_Under(null);
        }

        return groupDTO;
    }

    public static LedgerDTO valiDateAndRepairLedgerDTO(LedgerDTO ledgerDTO) {
        if (ledgerDTO.getDebitOrCredit() == null) {
            ledgerDTO.setDebitOrCredit(0);
        }

        if (ledgerDTO.getLedger_Address() == null) {
            ledgerDTO.setLedger_Address(" ");
        }

        if (ledgerDTO.getLedger_Alias() == null) {
            ledgerDTO.setLedger_Alias(" ");
        }

        if (ledgerDTO.getLedger_ContactNo() == null) {
            ledgerDTO.setLedger_ContactNo(" ");
        }

        if (ledgerDTO.getLedger_CreditLimit() == null) {
            ledgerDTO.setLedger_CreditLimit(0D);
        }

        if (ledgerDTO.getLedger_EmailID() == null) {
            ledgerDTO.setLedger_EmailID(" ");
        }

        if (ledgerDTO.getLedger_IncomeTaxNo() == null) {
            ledgerDTO.setLedger_IncomeTaxNo(" ");
        }

        if (ledgerDTO.getLedger_Name() == null) {
//	    ledgerDTO.setLedger_Name(" ");
            ledgerDTO.setLedger_Name(null);
        }

        if (ledgerDTO.getLedger_OpeningBalence() == null) {
            ledgerDTO.setLedger_OpeningBalence(0D);
        }

        if (ledgerDTO.getLedger_SaleTaxNo() == null) {
            ledgerDTO.setLedger_SaleTaxNo(" ");
        }

        if (ledgerDTO.getLedger_Under() == null) {
//	    ledgerDTO.setLedger_Under(" ");
            ledgerDTO.setLedger_Under(null);
        }

        if (ledgerDTO.getLedger_CSTNumber() == null) {
            ledgerDTO.setLedger_CSTNumber(" ");
        }
        if (ledgerDTO.getLedger_ECSNumber() == null) {
            ledgerDTO.setLedger_ECSNumber(" ");
        }
        if (ledgerDTO.getLedger_CVATNumber() == null) {
            ledgerDTO.setLedger_CVATNumber(" ");
        }
        if (ledgerDTO.getLedger_SaleTaxNo() == null) {
            ledgerDTO.setLedger_SaleTaxNo(" ");
        }

        return ledgerDTO;
    }

    public static StockItemDTO valiDateAndRepairStockItemDTO(StockItemDTO stockItemDTO) {
        if (stockItemDTO.getName() == null) {
            stockItemDTO.setName(" ");
        }

        if (stockItemDTO.getAlias() == null) {
            stockItemDTO.setAlias(" ");
        }

        if (stockItemDTO.getLength() == null) {
            stockItemDTO.setLength(0.0);
        }

        if (stockItemDTO.getWidth() == null) {
            stockItemDTO.setWidth(0.0);
        }

        if (stockItemDTO.getThkness() == null) {
            stockItemDTO.setThkness(0.0);
        }

        if (stockItemDTO.getOpeningBal() == null) {
            stockItemDTO.setOpeningBal(0.0);
        }

        if (stockItemDTO.getRate() == null) {
            stockItemDTO.setRate(0.0);
        }

        if (stockItemDTO.getUnder() == null) {
//	    stockItemDTO.setUnder(" ");
            stockItemDTO.setUnder(null);
        }

        if (stockItemDTO.getUnit() == null) {
//	    stockItemDTO.setUnit(" ");
            stockItemDTO.setUnit(null);
        }


        return stockItemDTO;
    }

    public static UnitOfMeasureDTO valiDateAndRepairUnitOfMeasureDTO(UnitOfMeasureDTO unitOfMeasureDTO) {

        if (unitOfMeasureDTO.getUnitOfMeasure_FormalName() == null) {
//	    unitOfMeasureDTO.setUnitOfMeasure_FormalName(" ");
            unitOfMeasureDTO.setUnitOfMeasure_FormalName(null);
        }

        if (unitOfMeasureDTO.getUnitOfMeasure_NoDecimalPlaces() == null) {
            unitOfMeasureDTO.setUnitOfMeasure_NoDecimalPlaces(" ");
        }

        if (unitOfMeasureDTO.getUnitOfMeasure_Symbol() == null) {
            unitOfMeasureDTO.setUnitOfMeasure_Symbol(" ");
        }

        if (unitOfMeasureDTO.getUnitOfMeasure_Type() == null) {
            unitOfMeasureDTO.setUnitOfMeasure_Type(" ");
        }


        return unitOfMeasureDTO;
    }

    public static PaymentDTO valiDateAndRepairPaymemntDTO(PaymentDTO paymentDTO) {


        if (paymentDTO.getAccountName() == null) {
//	    paymentDTO.setAccountName(" ");
            paymentDTO.setAccountName(null);
        }

        if (paymentDTO.getDate() == null) {
            paymentDTO.setDate(" ");
        }


        if (paymentDTO.getFinalAmount() == null) {
            paymentDTO.setFinalAmount(0D);
        }

        if (paymentDTO.getReceiptNo() == null) {
            paymentDTO.setReceiptNo(0);
        }


        return paymentDTO;
    }

    public static ReceiptDTO valiDateAndRepairReceiptDTO(ReceiptDTO receiptDTO) {


        if (receiptDTO.getAccountName() == null) {
//	    receiptDTO.setAccountName(" ");
            receiptDTO.setAccountName(null);
        }

        if (receiptDTO.getDate() == null) {
            receiptDTO.setDate(" ");
        }


        if (receiptDTO.getFinalAmount() == null) {
            receiptDTO.setFinalAmount(0D);
        }

        if (receiptDTO.getReceiptNo() == null) {
            receiptDTO.setReceiptNo(0);
        }


        return receiptDTO;
    }

    public static ContraDTO valiDateAndRepairContraDTO(ContraDTO contraDTO) {


        if (contraDTO.getAccountName() == null) {
//	    contraDTO.setAccountName(" ");
            contraDTO.setAccountName(null);
        }

        if (contraDTO.getDate() == null) {
            contraDTO.setDate(" ");
        }


        if (contraDTO.getFinalAmount() == null) {
            contraDTO.setFinalAmount(0D);
        }

        if (contraDTO.getReceiptNo() == null) {
            contraDTO.setReceiptNo(0);
        }


        return contraDTO;
    }

    public static ChalanDTO valiDateAndRepairChalanDTO(ChalanDTO chalanDTO) {

        if (chalanDTO.getCashLedger() == null) {
//	    chalanDTO.setCashLedger(" ");
            chalanDTO.setCashLedger(null);
        }

        if (chalanDTO.getDate() == null) {
            chalanDTO.setDate(" ");
        }

        if (chalanDTO.getDispatchDocNo() == null) {
            chalanDTO.setDispatchDocNo(" ");
        }

        if (chalanDTO.getDispatchDocThrough() == null) {
            chalanDTO.setDispatchDocThrough(" ");
        }

        if (chalanDTO.getNote() == null) {
            chalanDTO.setNote(" ");
        }

        if (chalanDTO.getReceiptNo() == null) {
            chalanDTO.setReceiptNo(0);
        }

        if (chalanDTO.getSaleLedger() == null) {
//	    chalanDTO.setSaleLedger(" ");
            chalanDTO.setSaleLedger(null);
        }

        return chalanDTO;
    }

    public static PurchaseDTO valiDateAndRepairPurchaseDTO(PurchaseDTO purchaseDTO) {
        if (purchaseDTO.getAmount() == null) {
            purchaseDTO.setAmount(0D);
        }

        if (purchaseDTO.getCashLedger() == null) {
//            purchaseDTO.setCashLedger(" ");
            purchaseDTO.setCashLedger(null);
        }

        if (purchaseDTO.getDate() == null) {
            purchaseDTO.setDate(" ");
        }



        if (purchaseDTO.getNote() == null) {
            purchaseDTO.setNote(" ");
        }

        if (purchaseDTO.getReceiptNo() == null) {
            purchaseDTO.setReceiptNo(0);
        }

        if (purchaseDTO.getPurchaseLedger() == null) {
//	    purchaseDTO.setPurchaseLedger(" ");
            purchaseDTO.setPurchaseLedger(null);
        }


        if (purchaseDTO.getVatAmount() == null) {
            purchaseDTO.setVatAmount(0D);
        }

        if (purchaseDTO.getVatRate() == null) {
            purchaseDTO.setVatRate(0D);
        }
        return purchaseDTO;
    }

    public static JournalDTO valiDateAndRepairJournalDTO(JournalDTO journalDTO) {


        if (journalDTO.getAccountName() == null) {
//	    journalDTO.setAccountName(" ");
            journalDTO.setAccountName(null);
        }

        if (journalDTO.getDate() == null) {
            journalDTO.setDate(" ");
        }


        if (journalDTO.getFinalAmount() == null) {
            journalDTO.setFinalAmount(0D);
        }

        if (journalDTO.getReceiptNo() == null) {
            journalDTO.setReceiptNo(0);
        }


        return journalDTO;
    }

    public static DOMImplementation getDomImplemetation() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        return impl;
    }
}

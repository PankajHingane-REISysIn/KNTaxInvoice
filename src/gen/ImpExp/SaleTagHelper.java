/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import static gen.ImpExp.TagsHelper1.ledgerNameSet;
import static gen.ImpExp.TagsHelper1.stockItemIDSet;
import gen.account.ledger.LedgerDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.dto.Constants;
import gen.dto.StockItemTransactionDTO;
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
public class SaleTagHelper {

    public static String generateSaleVoucherNodeList(List<SaleDTO> saleDTOList) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String returnString = "";
        Double amountToBeSent = 0D;
        System.out.println("in the--------->>generateSaleNodes(List<SaleDTO> saleDTOList) : " + saleDTOList.size());
        if (saleDTOList != null && saleDTOList.size() > 0) {
            returnString = returnString + "\n<SaleVoucherList>";
            for (SaleDTO saleDTO : saleDTOList) {
                System.out.println("--------->>saleDTO : " + saleDTO.getCashLedger());
                returnString = returnString + generateSaleVoucherNodes(saleDTO);
                amountToBeSent = amountToBeSent + saleDTO.getFinalAmount();
                System.out.println("amountToBeSent--->>>>" + amountToBeSent);
            }
        }
        returnString = returnString + "\n</SaleVoucherList>" + "\n<TotalSalesAmount>" + Constants.DECIMAL_FORMAT.format(amountToBeSent) + "\n</TotalSalesAmount>";
        return returnString;
    }

    public static String generateSaleVoucherNodes(SaleDTO saleDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        String saleXML = generateSaleVoucherMainNodes(saleDTO);
        String StringArray[] = saleXML.split("</SaleVoucher>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (!gen.dto.Util.isEmpty(saleDTO.getStockItemTreansactioDTOList())) {
            outputString = outputString + "\n<StockItemTransactionList>";
            System.out.println("TagsHelper-------->>generateNodes--------->>List not empty-->size:New :" + saleDTO.getStockItemTreansactioDTOList().size());

            for (StockItemTransactionDTO stkitemDTO : saleDTO.getStockItemTreansactioDTOList()) {

                System.out.println("INTO THe FOR LOOP");
                outputString = outputString + generateStockItemTransactionNode(stkitemDTO);
            }
            outputString = outputString + "</StockItemTransactionList>\n";
        }
        outputString = outputString + "</SaleVoucher>";

        //  return convertDoctoDOMSource(doc);
        return outputString;
    }

    public static String generateSaleVoucherMainNodes(SaleDTO saleDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {
        gen.ImpExp.TagsHelper1.loadCategaryIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadColorIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadTypeIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadBoardtypeIDToNameMapping();

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();
        System.out.println("--------->>generateSaleNodes : 1");

        Document doc = impl.createDocument(null, null, null);
        Element sale = doc.createElement("SaleVoucher");
        doc.appendChild(sale);
        System.out.println("--------->>generateSaleNodes : 2");

        Element receiptNo = doc.createElement("ReceiptNo");
        receiptNo.appendChild(doc.createTextNode(saleDTO.getReceiptNo().toString()));
        sale.appendChild(receiptNo);

        Element cashLedger = doc.createElement("CashLedger");
        cashLedger.appendChild(doc.createTextNode(saleDTO.getCashLedger()));
        sale.appendChild(cashLedger);
        
        TagsHelper1.ledgerNameSet.add(saleDTO.getCashLedger());

        /// Creating Tag for Cash Ledger Information
        LedgerDTO ledgerdto = saleDTO.getLedgerDTO();

        Element cashLedgerAddress = doc.createElement("CashLedgerAddress");
        cashLedgerAddress.appendChild(doc.createTextNode(ledgerdto.getLedger_Address()));
        sale.appendChild(cashLedgerAddress);

        Element cashLedgerContactNo = doc.createElement("CashLedgerContactNo");
        cashLedgerContactNo.appendChild(doc.createTextNode(ledgerdto.getLedger_ContactNo()));
        sale.appendChild(cashLedgerContactNo);

        Element cashLedgerECSNo = doc.createElement("CashLedgerECSNo");
        cashLedgerECSNo.appendChild(doc.createTextNode(ledgerdto.getLedger_ECSNumber()));
        sale.appendChild(cashLedgerECSNo);

        Element cashLedgerCVATNo = doc.createElement("CashLedgerCVATNo");
        cashLedgerCVATNo.appendChild(doc.createTextNode(ledgerdto.getLedger_CVATNumber()));
        sale.appendChild(cashLedgerCVATNo);

        Element cashLedgerCSTNo = doc.createElement("CashLedgerCSTNo");
        cashLedgerCSTNo.appendChild(doc.createTextNode(ledgerdto.getLedger_SaleTaxNo()));
        sale.appendChild(cashLedgerCSTNo);
        ///////////////////////

        System.out.println("--------->>generateSaleNodes : 3");

        Element saleLedger = doc.createElement("SaleLedger");
        saleLedger.appendChild(doc.createTextNode(saleDTO.getSaleLedger()));
        sale.appendChild(saleLedger);
        TagsHelper1.ledgerNameSet.add(saleDTO.getSaleLedger());

        Element date = doc.createElement("Date");
        date.appendChild(doc.createTextNode(saleDTO.getDate()));
        sale.appendChild(date);

        Element poNo = doc.createElement("PONo");
        poNo.appendChild(doc.createTextNode(saleDTO.getPoNo()));
        sale.appendChild(poNo);
        
        Element poDate = doc.createElement("PODate");
        poDate.appendChild(doc.createTextNode(saleDTO.getPoDate()== null ? "" : saleDTO.getPoDate()));
        sale.appendChild(poDate);
        
        Element OcNo = doc.createElement("OcNo");
        OcNo.appendChild(doc.createTextNode(saleDTO.getOcNo()));
        sale.appendChild(OcNo);
        
        Element oCDate = doc.createElement("OcDate");
        oCDate.appendChild(doc.createTextNode(saleDTO.getOcDate()==null ? "" : saleDTO.getOcDate()));
        sale.appendChild(oCDate);
        
        Element dateIss = doc.createElement("dateIss");
        dateIss.appendChild(doc.createTextNode(saleDTO.getDateIssDate()==null ? "" : saleDTO.getDateIssDate()));
        sale.appendChild(dateIss);
        
        Element dateRem = doc.createElement("dateRem");
        dateRem.appendChild(doc.createTextNode(saleDTO.getDateRemDate()==null ? "" : saleDTO.getDateRemDate()));
        sale.appendChild(dateRem);
        
        Element IssTime = doc.createElement("IssTime");
        IssTime.appendChild(doc.createTextNode(saleDTO.getTimeIss()));
        sale.appendChild(IssTime);
        
        Element IssRem = doc.createElement("RemTime");
        IssRem.appendChild(doc.createTextNode(saleDTO.getTimeRem()));
        sale.appendChild(IssRem);
        
        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode(saleDTO.getNote()));
        sale.appendChild(note);
        System.out.println("--------->>generateSaleNodes : 4");

        Element dispatchDocNo = doc.createElement("DispatchDocNo");
        dispatchDocNo.appendChild(doc.createTextNode(saleDTO.getDispatchDocNo()));
        sale.appendChild(dispatchDocNo);

        Element dispatchDocThrough = doc.createElement("DispatchDocThrough");
        dispatchDocThrough.appendChild(doc.createTextNode(saleDTO.getDispatchDocThrough()));
        sale.appendChild(dispatchDocThrough);

        Element Amount = doc.createElement("Amount");
        Amount.appendChild(doc.createTextNode(saleDTO.getAmount().toString()));
        sale.appendChild(Amount);
        System.out.println("--------->>generateSaleNodes : 5");

        Element vatRate = doc.createElement("VatRate");
        vatRate.appendChild(doc.createTextNode(saleDTO.getVatRate().toString()));
        sale.appendChild(vatRate);

        Element vatAmount = doc.createElement("VatAmount");
        vatAmount.appendChild(doc.createTextNode(saleDTO.getVatAmount().toString()));
        sale.appendChild(vatAmount);
        
        Element CSTRate = doc.createElement("CSTRate");
        CSTRate.appendChild(doc.createTextNode(saleDTO.getCstRate().toString()));
        sale.appendChild(CSTRate);

        Element CSTAmount = doc.createElement("CSTAmount");
        CSTAmount.appendChild(doc.createTextNode(saleDTO.getCstAmount().toString()));
        sale.appendChild(CSTAmount);
        
        Element hEDCessRate = doc.createElement("hEDCessRate");
        hEDCessRate.appendChild(doc.createTextNode(saleDTO.gethEdCessRate().toString()));
        sale.appendChild(hEDCessRate);

        Element hEDCessAmount = doc.createElement("hEDCessAmount");
        hEDCessAmount.appendChild(doc.createTextNode(saleDTO.getHedCessAmount().toString()));
        sale.appendChild(hEDCessAmount);
        
        Element EDCessRate = doc.createElement("EDCessRate");
        EDCessRate.appendChild(doc.createTextNode(saleDTO.getEdCessRate().toString()));
        sale.appendChild(EDCessRate);

        Element EDCessAmount = doc.createElement("EDCessAmount");
        EDCessAmount.appendChild(doc.createTextNode(saleDTO.getEdCessAmount().toString()));
        sale.appendChild(EDCessAmount);

        Element ExciseDutyRate = doc.createElement("ExciseDutyRate");
        ExciseDutyRate.appendChild(doc.createTextNode(saleDTO.getExciseDutyRate().toString()));
        sale.appendChild(ExciseDutyRate);

        Element ExciseDutyAmount = doc.createElement("ExciseDutyAmount");
        ExciseDutyAmount.appendChild(doc.createTextNode(saleDTO.getExciseDutyAmount().toString()));
        sale.appendChild(ExciseDutyAmount);
        
        
        Element lessBillAmount = doc.createElement("LessBillAmount");
        lessBillAmount.appendChild(doc.createTextNode(saleDTO.getLessBillAmount().toString()));
        sale.appendChild(lessBillAmount);
        System.out.println("--------->>generateSaleNodes : 6");

        Element transport = doc.createElement("Transport");
        transport.appendChild(doc.createTextNode(saleDTO.getTransport().toString()));
        sale.appendChild(transport);

        Element paymentMode = doc.createElement("PaymentMode");
        paymentMode.appendChild(doc.createTextNode(saleDTO.getPaymentMode().toString()));
        sale.appendChild(paymentMode);

        Element finalAmount = doc.createElement("FinalAmount");
        finalAmount.appendChild(doc.createTextNode(saleDTO.getFinalAmount().toString()));
        sale.appendChild(finalAmount);
        System.out.println("--------->>generateSaleNodes : 7");


        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateStockItemTransactionNode(StockItemTransactionDTO stockItemTransactionDTO) throws ParserConfigurationException, TransformerConfigurationException, Exception {

        //gen.ImpExp.TagsHelper1.initilise();

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element stockItemTransactions = doc.createElement("StockItemTransaction");
        doc.appendChild(stockItemTransactions);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(stockItemTransactionDTO.getName()));
        stockItemTransactions.appendChild(name);
        
        TagsHelper1.stockItemIDSet.add(stockItemTransactionDTO.getName());

        Element categary = doc.createElement("Category");
        if (gen.ImpExp.TagsHelper1.categaryIDToNameMapping.get(stockItemTransactionDTO.getCatogary()) != null) {
        categary.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.categaryIDToNameMapping.get(stockItemTransactionDTO.getCatogary())));
        } else {
            categary.appendChild(doc.createTextNode(""));
        }
        stockItemTransactions.appendChild(categary);

        Element length = doc.createElement("Length");
        length.appendChild(doc.createTextNode(stockItemTransactionDTO.getSizeA().toString()));
        stockItemTransactions.appendChild(length);

        Element width = doc.createElement("Width");
        width.appendChild(doc.createTextNode(stockItemTransactionDTO.getSizeB().toString()));
        stockItemTransactions.appendChild(width);

        Element thkness = doc.createElement("Thickness");
        thkness.appendChild(doc.createTextNode(stockItemTransactionDTO.getThkness().toString()));
        stockItemTransactions.appendChild(thkness);

        Element color = doc.createElement("Color");
        if (gen.ImpExp.TagsHelper1.colorIDToNameMapping.get(stockItemTransactionDTO.getColor()) != null) {
            color.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.colorIDToNameMapping.get(stockItemTransactionDTO.getColor())));
        } else {
            color.appendChild(doc.createTextNode(""));
        }
        stockItemTransactions.appendChild(color);

        Element finishType = doc.createElement("FinishType");
        if (gen.ImpExp.TagsHelper1.typeIDToNameMapping.get(stockItemTransactionDTO.getType()) != null) {
        finishType.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.typeIDToNameMapping.get(stockItemTransactionDTO.getType())));
        } else {
            finishType.appendChild(doc.createTextNode(""));
        }
        stockItemTransactions.appendChild(finishType);

        Element boardType = doc.createElement("BoardType");
        if (gen.ImpExp.TagsHelper1.boardtypeIDToNameMapping.get(stockItemTransactionDTO.getBoard_type()) != null) {
        boardType.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.boardtypeIDToNameMapping.get(stockItemTransactionDTO.getBoard_type())));
        } else {
            boardType.appendChild(doc.createTextNode(""));
        }
        stockItemTransactions.appendChild(boardType);

        Element nos = doc.createElement("Nos");
        if (stockItemTransactionDTO.getNo().toString().equalsIgnoreCase(null) || stockItemTransactionDTO.getNo().toString().equalsIgnoreCase("")) {
        nos.appendChild(doc.createTextNode(stockItemTransactionDTO.getNo().toString()));
        } else {
            nos.appendChild(doc.createTextNode(""));
        }
        stockItemTransactions.appendChild(nos);

        Element stkPackage = doc.createElement("Package");
        stkPackage.appendChild(doc.createTextNode(stockItemTransactionDTO.getStkpackage().toString()));
        stockItemTransactions.appendChild(stkPackage);

        Element userUnitSymbol = doc.createElement("UserUnitSymbol");
        userUnitSymbol.appendChild(doc.createTextNode(stockItemTransactionDTO.getUser_unit_of_symbol().toString()));
        stockItemTransactions.appendChild(userUnitSymbol);
        
        Element quantity = doc.createElement("Quantity");
        quantity.appendChild(doc.createTextNode(stockItemTransactionDTO.getQuantity().toString()));
        stockItemTransactions.appendChild(quantity);

        Element sqFeet = doc.createElement("SqFeet");
        sqFeet.appendChild(doc.createTextNode(stockItemTransactionDTO.getSqFeet().toString()));
        stockItemTransactions.appendChild(sqFeet);

        Element rate = doc.createElement("Rate");
        rate.appendChild(doc.createTextNode(stockItemTransactionDTO.getRate().toString()));
        stockItemTransactions.appendChild(rate);

        Element amount = doc.createElement("Amount");
        amount.appendChild(doc.createTextNode(stockItemTransactionDTO.getAmount().toString()));
        stockItemTransactions.appendChild(amount);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

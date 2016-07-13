/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.ImpExp.TallyExport.ContraTallyTagHelper;
import gen.ImpExp.TallyExport.PaymentTallyTagHelper;
import gen.ImpExp.TallyExport.PurchaseTallyTagHelper;
import gen.ImpExp.TallyExport.ReceiptTallyTagHelper;
import gen.ImpExp.TallyExport.SaleTallyTagHelper;
import gen.accountvoucher.chalan.ChalanDAO;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.contra.ContraDAO;
import gen.accountvoucher.contra.ContraDTO;
import gen.accountvoucher.payment.PaymentDAO;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.purchase.PurchaseDAO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.receipt.ReceiptDAO;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.sale.SaleDAO;
import gen.accountvoucher.sale.SaleDTO;
import gen.display.report.GroupSummaryDTO;
import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.StockItem.StockItemReportDTO;
import gen.display.report.TrialBalanceDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author pc5
 */
public class AdSuMuDiXMLGenerator {

    static Boolean flag = true;

    public static String generateSaleVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        return generateSaleVoucherXML(receiptIDSet, "VoucherId");
    }
    public static String generateSaleVoucherXML(Set<String> receiptIDSet, String type) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<SaleDTO> saleDTOList = SaleDAO.getSales(receiptIDSet, calling_class, type);
        System.out.println("SALE             " + saleDTOList.size());
        if (flag) {
            return SaleTagHelper.generateSaleVoucherNodeList(saleDTOList);
        } else {
            return SaleTallyTagHelper.generateSaleVoucherNodeList(saleDTOList);
        }
    }

    public static String generatePurchaseVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<PurchaseDTO> purchaseDTOList = PurchaseDAO.getPurchase(receiptIDSet, calling_class);
        if (flag) {
            return PurchaseTagHelper.generatePurchaseVouchersNodeList(purchaseDTOList);
        } else {
            return PurchaseTallyTagHelper.generatePurchaseVoucherNodeList(purchaseDTOList);
        }
    }

    public static String generateReceiptVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<ReceiptDTO> receiptDTOList = ReceiptDAO.getReceipts(receiptIDSet, calling_class);
        if (flag) {
            return ReceiptTagHelper.generateReceiptVoucherNodeList(receiptDTOList);
        } else {
            System.out.println("iN TAlly -------------");
            return ReceiptTallyTagHelper.generateReceiptVoucherNodeList(receiptDTOList);
        }
    }

    public static String generateChallanVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<ChalanDTO> saleDTOList = ChalanDAO.getChalan(receiptIDSet, calling_class);
        return ChalanTagHelper.generateChalanVoucherNodeList(saleDTOList);
    }

    public static String generatePaymentVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<PaymentDTO> paymentDTOList = PaymentDAO.getPayment(receiptIDSet, calling_class);

        if (flag) {
            return PaymentTagHelper.generatePaymentVoucherNodeList(paymentDTOList);
        } else {
            return PaymentTallyTagHelper.generatePaymentVoucherNodeList(paymentDTOList);
        }

    }

    public static String generateContraVoucherXML(Set<String> receiptIDSet) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        String calling_class = "AdSuMuDiXMLGenerator";
        List<ContraDTO> contraDTOList = ContraDAO.getContra(receiptIDSet, calling_class);
        if (flag) {
            return ContraTagHelper.generateContraVoucherNodeList(contraDTOList);
        } else {
            return ContraTallyTagHelper.generateContraVoucherNodeList(contraDTOList);
        }
    }

    public static String generateOpClosBalanceSupportXMLForLedgerReport(List<LedgerReportDTO> listOfLedgerReport) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        return OpeningClosingBalanceSupportTagHelper.generateOpeningClosingSupportNodesForLedgerReport(listOfLedgerReport);
    }

    public static String generateOpClosBalanceSupportXMLForStockItemReport(List<StockItemReportDTO> listOfStockItemReport) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        return OpeningClosingBalanceSupportTagHelper.generateOpeningClosingSupportNodesForStockItemReport(listOfStockItemReport);
    }

    public static String generateTrialBalanceReport(List<TrialBalanceDTO> listOfTrialBalanceDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        return TrialBalanceReportTagHelper.generateNodesForTrialBlanceReport(listOfTrialBalanceDTO);
    }

    public static String generateGroupSummaryReport(Map<String, List<GroupSummaryDTO>> mapOfLedgerClosingBalance) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        return GroupSummaryReportTagHelper.generateNodesForGroupSummaryReport(mapOfLedgerClosingBalance);
    }
}

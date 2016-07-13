/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.accountvoucher.chalan.ChalanDTO;
import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.Ledger.TransactionsDTO;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class LedgerReportMobileTagHelper {

    private static Map<String, String> mapLedger = new HashMap<String, String>();
    static List<String> groups = new ArrayList<String>();

    public static String generateLedgerReportMObileTags(List<LedgerReportDTO> ledgerReportFormDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException, Exception {
        String returnString = "";
        System.out.println("in the--------->>generateSaleNodes(List<SaleDTO> saleDTOList) : " + ledgerReportFormDTOList.size());
        for (LedgerReportDTO ledgerDTO : ledgerReportFormDTOList) {
            returnString = returnString + generateLedgerMainNodes(ledgerDTO);
            System.out.println("ReturnString--->>>" + returnString);
        }
        return returnString;
    }

    public static String generateLedgerMainNodes(LedgerReportDTO ledgerDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException, Exception {

        String ledgerXML = generateLedgerReportNodes(ledgerDTO);
        System.out.println("generateLedgerReportNodes---->>>" + ledgerXML);
        String StringArray[] = ledgerXML.split("</LedgerReport>");
        String outputString = StringArray[0];
        System.out.println("TagsHelper-------->>generateNodes--------->>StringArray" + StringArray[0]);
        if (ledgerDTO.getTransactionsDTO().size() != 0) {
            //outputString = outputString + "\n<SubTransactions>";
            System.out.println("TagsHelper-------->>generateNodes--------->>Ledgers-->>" + ledgerDTO.getTransactionsDTO().size());
            for (TransactionsDTO transactionDTO : ledgerDTO.getTransactionsDTO()) {
                outputString = outputString + generateLedgerReportTransactionNodes(transactionDTO);
            }
            //outputString = outputString + "</SubTransactions>\n";
        }
        outputString = outputString + "</LedgerReport>";

        //  return convertDoctoDOMSource(doc);
        return outputString;

    }

    public static String generateLedgerReportNodes(LedgerReportDTO ledgerDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element ledgerReport = doc.createElement("LedgerReport");
        doc.appendChild(ledgerReport);

        Element total = doc.createElement("Total");
        total.appendChild(doc.createTextNode(ledgerDTO.getTotal().toString()));
        ledgerReport.appendChild(total);

        Element openingBalance = doc.createElement("OpeningBalance");
        openingBalance.appendChild(doc.createTextNode(ledgerDTO.getOpening_balance().toString()));
        ledgerReport.appendChild(openingBalance);

        Element closingBalance = doc.createElement("ClosingBalance");
        closingBalance.appendChild(doc.createTextNode(ledgerDTO.getClosing_balance().toString()));
        ledgerReport.appendChild(closingBalance);

        Element openingBalanceType = doc.createElement("OpeningBalanceType");
        openingBalanceType.appendChild(doc.createTextNode(ledgerDTO.getOpening_balance_type().toString()));
        ledgerReport.appendChild(openingBalanceType);

        Element closingBalancetype = doc.createElement("ClosingBalancetype");
        closingBalancetype.appendChild(doc.createTextNode(ledgerDTO.getClosing_balance_type().toString()));
        ledgerReport.appendChild(closingBalancetype);

        Element totalCredit = doc.createElement("TotalCredit");
        totalCredit.appendChild(doc.createTextNode(ledgerDTO.getTotal_credit().toString()));
        ledgerReport.appendChild(totalCredit);

        Element totalDebit = doc.createElement("TotalDebit");
        totalDebit.appendChild(doc.createTextNode(ledgerDTO.getTotal_debit().toString()));
        ledgerReport.appendChild(totalDebit);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateLedgerReportTransactionNodes(TransactionsDTO transactionsDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException, Exception {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();
        mapLedger  = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups, false);
        Document doc = impl.createDocument(null, null, null);
        Element ledgerTransaction = doc.createElement("SubTransactions");
        doc.appendChild(ledgerTransaction);

        for (Map.Entry<String, String> e : mapLedger.entrySet()) {
            if (transactionsDTO.getParticulars().toLowerCase().equals(e.getValue())) {
                Element particulars = doc.createElement("Particulars");
                //particulars.appendChild(doc.createTextNode(transactionsDTO.getParticulars()));
                particulars.appendChild(doc.createTextNode(e.getKey()));
                ledgerTransaction.appendChild(particulars);
            }
        }

        Element dateOfTransaction = doc.createElement("Date");
        dateOfTransaction.appendChild(doc.createTextNode(transactionsDTO.getDate()));
        ledgerTransaction.appendChild(dateOfTransaction);

        Element voucherType = doc.createElement("VoucherType");
        voucherType.appendChild(doc.createTextNode(transactionsDTO.getVchtype()));
        ledgerTransaction.appendChild(voucherType);

        Element voucherNo = doc.createElement("VoucherNo");
        voucherNo.appendChild(doc.createTextNode(transactionsDTO.getVchno()));
        ledgerTransaction.appendChild(voucherNo);

        Element debit = doc.createElement("Debit");
        debit.appendChild(doc.createTextNode(transactionsDTO.getDebit()));
        ledgerTransaction.appendChild(debit);

        Element credit = doc.createElement("Credit");
        credit.appendChild(doc.createTextNode(transactionsDTO.getCredit()));
        ledgerTransaction.appendChild(credit);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

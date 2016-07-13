/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.StockItem.StockItemReportDTO;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OpeningClosingBalanceSupportTagHelper {

    public static String generateOpeningClosingSupportNodesForLedgerReport(List<LedgerReportDTO> listOfLedgerReport) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DecimalFormat decformat = new DecimalFormat("#.##");
        
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element openingClosingBalances = doc.createElement("FinalLedgerReportBalances");
        doc.appendChild(openingClosingBalances);

        for (LedgerReportDTO ledgerReportDTO : listOfLedgerReport) {

            Element totalDebit = doc.createElement("TotalDebit");
            totalDebit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getTotal_debit())));
            openingClosingBalances.appendChild(totalDebit);

            Element totalCredit = doc.createElement("TotalCredit");
            totalCredit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getTotal_credit())));
            openingClosingBalances.appendChild(totalCredit);

            if (ledgerReportDTO.getOpening_balance_type().equalsIgnoreCase("Debit")) {
                Element openingDebit = doc.createElement("OpeningDebit");
                openingDebit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getOpening_balance())));
                openingClosingBalances.appendChild(openingDebit);
            } else {
                Element openingDebit = doc.createElement("OpeningDebit");
                openingDebit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(openingDebit);
            }

            if (ledgerReportDTO.getOpening_balance_type().equalsIgnoreCase("Credit")) {
                Element openingCredit = doc.createElement("OpeningCredit");
                openingCredit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getOpening_balance())));
                openingClosingBalances.appendChild(openingCredit);
            } else {
                Element openingCredit = doc.createElement("OpeningCredit");
                openingCredit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(openingCredit);
            }

            if (ledgerReportDTO.getClosing_balance_type().equalsIgnoreCase("Debit")) {
                Element closingDebit = doc.createElement("ClosingDebit");
                closingDebit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getClosing_balance())));
                openingClosingBalances.appendChild(closingDebit);
            } else {
                Element closingDebit = doc.createElement("ClosingDebit");
                closingDebit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(closingDebit);
            }

            if (ledgerReportDTO.getClosing_balance_type().equalsIgnoreCase("Credit")) {
                Element closingCredit = doc.createElement("ClosingCredit");
                closingCredit.appendChild(doc.createTextNode("" + decformat.format(ledgerReportDTO.getClosing_balance())));
                openingClosingBalances.appendChild(closingCredit);
            } else {
                Element closingCredit = doc.createElement("ClosingCredit");
                closingCredit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(closingCredit);
            }
        }
        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateOpeningClosingSupportNodesForStockItemReport(List<StockItemReportDTO> listOfStockItemReport) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        
        DecimalFormat decformat = new DecimalFormat("#.##");
        
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element openingClosingBalances = doc.createElement("FinalStockItemReportBalances");
        doc.appendChild(openingClosingBalances);

        for (StockItemReportDTO stockItemReportDTO : listOfStockItemReport) {

            Element totalDebit = doc.createElement("TotalInward");
            totalDebit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getTotal_credit())));
            openingClosingBalances.appendChild(totalDebit);

            Element totalCredit = doc.createElement("TotalOutward");
            totalCredit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getTotal_debit())));
            openingClosingBalances.appendChild(totalCredit);

            if (stockItemReportDTO.getOpening_balance_type().equalsIgnoreCase("Credit")) {
                Element openingDebit = doc.createElement("OpeningInward");
                openingDebit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getOpening_balance())));
                openingClosingBalances.appendChild(openingDebit);
            } else {
                Element openingDebit = doc.createElement("OpeningInward");
                openingDebit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(openingDebit);
            }

            if (stockItemReportDTO.getOpening_balance_type().equalsIgnoreCase("Debit")) {
                Element openingCredit = doc.createElement("OpeningOutward");
                openingCredit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getOpening_balance())));
                openingClosingBalances.appendChild(openingCredit);
            } else {
                Element openingCredit = doc.createElement("OpeningOutward");
                openingCredit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(openingCredit);
            }

            if (stockItemReportDTO.getClosing_balance_type().equalsIgnoreCase("Credit")) {
                Element closingDebit = doc.createElement("ClosingInward");
                closingDebit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getClosing_balance())));
                openingClosingBalances.appendChild(closingDebit);
            } else {
                Element closingDebit = doc.createElement("ClosingInward");
                closingDebit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(closingDebit);
            }

            if (stockItemReportDTO.getClosing_balance_type().equalsIgnoreCase("Debit")) {
                Element closingCredit = doc.createElement("ClosingOutward");
                closingCredit.appendChild(doc.createTextNode("" + decformat.format(stockItemReportDTO.getClosing_balance())));
                openingClosingBalances.appendChild(closingCredit);
            } else {
                Element closingCredit = doc.createElement("ClosingOutward");
                closingCredit.appendChild(doc.createTextNode("" + 0.0));
                openingClosingBalances.appendChild(closingCredit);
            }
        }
        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

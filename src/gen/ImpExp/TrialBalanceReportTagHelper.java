/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.TrialBalanceDTO;
import java.text.DecimalFormat;
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
public class TrialBalanceReportTagHelper {

    public static String generateNodesForTrialBlanceReport(List<TrialBalanceDTO> listOfTrialBalanceDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DecimalFormat decformat = new DecimalFormat("#.##");
        Double totalDebitAmount = 0D;
        Double totalCreditAmount = 0D;
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element openingClosingBalances = doc.createElement("FinalTrialBalanceReport");
        doc.appendChild(openingClosingBalances);

        for (TrialBalanceDTO trialBalanceDTO : listOfTrialBalanceDTO) {

            if (trialBalanceDTO.getDebitAmount() != 0 || trialBalanceDTO.getCreditAmount() != 0) {
                Element groupName = doc.createElement("GroupName");
                groupName.appendChild(doc.createTextNode("" + trialBalanceDTO.getGroup_name()));
                openingClosingBalances.appendChild(groupName);

                Element groupDebit = doc.createElement("GroupDebitAmount");
                groupDebit.appendChild(doc.createTextNode("" + decformat.format(trialBalanceDTO.getCreditAmount())));
                openingClosingBalances.appendChild(groupDebit);

                Element groupCredit = doc.createElement("GroupCreditAmount");
                groupCredit.appendChild(doc.createTextNode("" + decformat.format(trialBalanceDTO.getDebitAmount())));
                openingClosingBalances.appendChild(groupCredit);


                totalDebitAmount = totalDebitAmount + trialBalanceDTO.getCreditAmount();

                totalCreditAmount = totalCreditAmount + trialBalanceDTO.getDebitAmount();

            }
        }
        Element totalGroupDebit = doc.createElement("TotalDebitAmount");
        totalGroupDebit.appendChild(doc.createTextNode("" + decformat.format(totalDebitAmount)));
        openingClosingBalances.appendChild(totalGroupDebit);

        Element totalGroupCredit = doc.createElement("TotalCreditAmount");
        totalGroupCredit.appendChild(doc.createTextNode("" + decformat.format(totalCreditAmount)));
        openingClosingBalances.appendChild(totalGroupCredit);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

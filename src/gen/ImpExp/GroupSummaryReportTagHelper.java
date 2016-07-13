/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.display.report.GroupSummaryDTO;
import java.text.DecimalFormat;
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
public class GroupSummaryReportTagHelper {

    public static String generateNodesForGroupSummaryReport(Map<String, List<GroupSummaryDTO>> mapOfLedgerClosingBalance) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DecimalFormat decformat = new DecimalFormat("#.##");
        Double totalDebitAmount = 0D;
        Double totalCreditAmount = 0D;
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
//        Element openingClosingBalances = doc.createElement("FinalTrialBalanceReport");
        Element openingClosingBalances = doc.createElement("FinalGroupSummaryReport");
        doc.appendChild(openingClosingBalances);

        Element groupList = doc.createElement("GroupList");
        openingClosingBalances.appendChild(groupList);

        int rowCount = 1;
        for (Map.Entry<String, List<GroupSummaryDTO>> e : mapOfLedgerClosingBalance.entrySet()) {

            System.out.println("GroupNames----------------------->>>" + e.getKey());

            for (GroupSummaryDTO g : e.getValue()) {

                Element group = doc.createElement("Group");
                groupList.appendChild(group);
                
            Element groupName = doc.createElement("GroupName");
            groupName.appendChild(doc.createTextNode("" + e.getKey()));
                group.appendChild(groupName);

                Element ledgerDetails = doc.createElement("LedgerDetails");
                group.appendChild(ledgerDetails);

                Element ledgerName = doc.createElement("LedgerName");
                ledgerName.appendChild(doc.createTextNode("" + g.getLedger_name()));
                ledgerDetails.appendChild(ledgerName);

                if (g.getClosing_trans_Type() == 1) {
                    Element groupDebit = doc.createElement("DebitAmount");
                    groupDebit.appendChild(doc.createTextNode("" + decformat.format(g.getClosingBalance())));
                    ledgerDetails.appendChild(groupDebit);
                    totalDebitAmount = totalDebitAmount + g.getClosingBalance();
                } else {
                    Element groupDebit = doc.createElement("CreditAmount");
                    groupDebit.appendChild(doc.createTextNode("" + decformat.format(g.getClosingBalance())));
                    ledgerDetails.appendChild(groupDebit);
                    totalCreditAmount = totalCreditAmount + g.getClosingBalance();
                }
            }
            rowCount++;
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

package Schedular;

import MobileXMLOperations.ConvertStringToXMLDocFormat;
import MobileXMLOperations.MobileDataInOutHelper;
import MobileXMLOperations.ReadingMobileDataRequestFromXML;
import MobileXMLOperations.RequestForMobileDataDTO;
import Schedular.RequestResponseBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import gen.ImpExp.LedgerReportMobileTagHelper;
import gen.ImpExp.TagsHelper1;
import gen.account.ledger.LedgerDAO;
import gen.account.ledger.LedgerDTO;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemDTO;
import gen.display.report.GroupSummaryDAO;
import gen.display.report.GroupSummaryDTO;
import gen.display.report.Ledger.LedgerReportDAO;
import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.Ledger.TransactionsDTO;
import gen.display.report.StockItem.StockItemReportDAO;
import gen.display.report.StockItem.StockItemReportDTO;
import gen.display.report.TrialBalanceDAO;
import gen.display.report.TrialBalanceDTO;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Document;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kasturi Novasoft
 */
public class RestJob implements Job {

    Set<String> saleIDSet = new TreeSet<String>();
    Set<String> purchaseIDSet = new TreeSet<String>();
    Set<String> receiptIDSet = new TreeSet<String>();
    Set<String> paymentIDSet = new TreeSet<String>();
    Set<String> chalanIDSet = new TreeSet<String>();
    public static Boolean isRequestInProgress = false;
    static RequestForMobileDataDTO mobileDataRequestDTO = new RequestForMobileDataDTO();
    String mobileID = "";
    String requestedDate = "";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        if (!isRequestInProgress) {
            System.out.println("Mobile --------  key    = " + Schedular.Constants.mobile_key);
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource service = client.resource(getBaseURI());
            //Mobile App
            RequestResponseBean rrb = new RequestResponseBean(Schedular.Constants.mobile_key, "", "", "", "");
            ClientResponse response = service.path("products").path("App").path("21").accept(MediaType.APPLICATION_XML).put(ClientResponse.class, rrb);

            System.out.println(response.getStatus());
            if (response.getStatus() == Schedular.Constants.PENDING_DATA) {
                try {
                    isRequestInProgress = true;
                    getRequestData(service);
                } catch (Exception ex) {
                    Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
                }
//                catch (ParserConfigurationException ex) {
//                    Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (TransformerConfigurationException ex) {
//                    Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (TransformerException ex) {
//                    Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        }
    }

    private static URI getBaseURI() {
//        return UriBuilder.fromUri("http://192.168.1.105:8084/KasturiNovasoft").build();
        return UriBuilder.fromUri("http://www.kasturinovasoft.com").build();
    }

    private void getRequestData(WebResource service) throws TransformerConfigurationException, TransformerException, ParserConfigurationException, Exception {
        System.out.println("Print ------------------------");
        System.out.println("Print ------------------------     " + service.path("products").path("App").path("requestfor").path(Schedular.Constants.mobile_key).accept(MediaType.APPLICATION_XML).get(RequestResponseBean.class));
        RequestResponseBean response = service.path("products").path("App").path("requestfor").path(Schedular.Constants.mobile_key).accept(MediaType.APPLICATION_XML).get(RequestResponseBean.class);
        System.out.println("response=========>>>" + response.getRequest());
        String receivedXMLAsResponse = response.getRequest();
        mobileID = response.getMobile_id();
        requestedDate = response.getRequested_date();
        Document doc = ConvertStringToXMLDocFormat.convertStringToDocument(receivedXMLAsResponse);
        mobileDataRequestDTO = ReadingMobileDataRequestFromXML.readingMobileDataRequestFromXML(doc);

        sendDataResponse(service, response);
    }

    private void sendDataResponse(WebResource service, RequestResponseBean request) throws TransformerConfigurationException, TransformerException, ParserConfigurationException, Exception {
        String responseString = null;
        String responseString1 = null;
        String combinedResponseStringForLR = null;

        Map<String, Set<String>> dataMap = new HashMap<String, Set<String>>();

        Set<String> IdSet = new HashSet<String>();
        Set<String> IdSet_2 = new HashSet<String>();
        System.out.println("Before Into The Sale Fire--->>>" + mobileDataRequestDTO.getVoucherType());
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRrrr   ");
        //comment on date 18-1-2014
//        if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("SaleReport")) {
        if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("LedgerNameList")) {
            try {
                System.out.println("Into The LedgerNameList Fire--->>>");
                List<LedgerDTO> allLedgers = LedgerDAO.exportAllLedgers();

                for (LedgerDTO ledgerDTO : allLedgers) {
                    IdSet.add(ledgerDTO.getLedger_Name());
                }

                if (!IdSet.isEmpty()) {
                    dataMap.put("LedgerList", IdSet);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("StockNameList")) {
            try {
                System.out.println("Into The StockNameList Fire--->>>");
//                List<StockItemDTO> allStockItemList = StockItemDAO.exportAllStockItems();
                List<gen.account.StockItemFormation.StockItemDTO> allStockItemList = gen.account.StockItemFormation.StockItemFormationDAO.exportAllStockItems();

                for (gen.account.StockItemFormation.StockItemDTO stockItemDTO : allStockItemList) {
                    System.out.println("stockItemDTO.getName()-->>>" + stockItemDTO.getName());
                    IdSet.add(stockItemDTO.getName());
                }

                if (!IdSet.isEmpty()) {
                    dataMap.put("StockNameList", IdSet);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("Sale")) {
            try {
                System.out.println("Into The Sale Fire--->>>");
                IdSet = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());
                if (!IdSet.isEmpty()) {
                    dataMap.put("Sale", IdSet);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
//	} else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("PurchaseReport")) {
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("Purchase")) {
            try {
                IdSet = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());
                System.out.println("rrrrrrrrrrrr   " + IdSet.size());
                if (!IdSet.isEmpty()) {
                    dataMap.put("Purchase", IdSet);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("LedgerReport")) {
            try {
                List<String> ledgerList = new ArrayList<String>();
                Map<String, String> mapLedger = new HashMap<String, String>();
                List<String> groups = new ArrayList<String>();
                mapLedger = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups, false);
                String ledgerName = mapLedger.get(mobileDataRequestDTO.getName());
                System.out.println("ledger_Name In RestJob--->>>" + ledgerName);
                ledgerList.add(ledgerName);

                List<LedgerReportDTO> listOfLedgerReport = LedgerReportDAO.getLedgerReport(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(), ledgerList);

                for (LedgerReportDTO ledgerReportDTO : listOfLedgerReport) {
                    for (TransactionsDTO transactionDTO : ledgerReportDTO.getTransactionsDTO()) {
                        System.out.println("transactionDTO.getVchtype().equalsIgnoreCase()---->>" + transactionDTO.getVchtype());
                        if (transactionDTO.getVchtype().equalsIgnoreCase("Sale")) {
                            saleIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Purchase")) {
                            purchaseIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Receipt")) {
                            receiptIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Payment")) {
                            paymentIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Chalan")) {
                            chalanIDSet.add(transactionDTO.getVchno());
                        }
                    }
                }

                Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                IDMapSet.put("Sale", saleIDSet);
                IDMapSet.put("Purchase", purchaseIDSet);
                IDMapSet.put("Receipt", receiptIDSet);
                IDMapSet.put("Payment", paymentIDSet);
                IDMapSet.put("Chalan", chalanIDSet);

                TagsHelper1.combinedLedgerReport(mobileDataRequestDTO);

                responseString = TagsHelper1.exportLedgerReport(IDMapSet, listOfLedgerReport);

                System.out.println("Final Response String Printing---->>>>>");
                System.out.println("" + responseString);

//                Map<String, String> mapLedger1 = new HashMap<String, String>();
//                List<String> ledger_List = new ArrayList<String>();
//                List<String> groups1 = new ArrayList<String>();
//                mapLedger1 = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups1, false);
//                System.out.println("mapLedger--->>>" + mapLedger1.size());
//                System.out.println("name--->>>" + mobileDataRequestDTO.getName());
//                String ledger_Name = mapLedger1.get(mobileDataRequestDTO.getName());
//                System.out.println("ledger_Name--->>" + ledger_Name);
//                ledger_List.add(ledger_Name);
//                List<LedgerReportDTO> ledgerReportFormDTOList = LedgerReportDAO.getLedgerReport(mobileDataRequestDTO.getFromDate(), mobileDataRequestDTO.getToDate(), ledger_List);
//                //String xmlreport = LedgerReportMobileTagHelper.generateLedgerReportMObileTags(ledgerReportFormDTOList);
//                responseString1 = LedgerReportMobileTagHelper.generateLedgerReportMObileTags(ledgerReportFormDTOList);

//                combinedResponseStringForLR = responseString + responseString1;

//                System.out.println("hurreeeeeeeeeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" + combinedResponseStringForLR);


            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("StockItemReport")) {
            try {
                List<String> ledger_List = new ArrayList<String>();
                Map<String, String> mapLedger = new HashMap<String, String>();
                mapLedger = StockItemDAO.getStockItemWithIDList();
                String stockItemName = mapLedger.get(mobileDataRequestDTO.getName());
                System.out.println("stockItemName In RestJob--->>>" + stockItemName);
                //ledger_List.add(ledger_Name);

                List<StockItemReportDTO> listStockItemReport = StockItemReportDAO.getStockItemReport(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(), stockItemName);

                for (StockItemReportDTO stockItemReportDTO : listStockItemReport) {
                    System.out.println("stockItemReportDTO.getTransactionsDTOList()--->>" + stockItemReportDTO.getTransactionsDTOList().size());
                    for (TransactionsDTO transactionDTO : stockItemReportDTO.getTransactionsDTOList()) {
                        System.out.println("transactionDTO.getVchtype().equalsIgnoreCase()---->>" + transactionDTO.getVchtype());
                        if (transactionDTO.getVchtype().equalsIgnoreCase("Sales")) {
                            saleIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Purchase")) {
                            purchaseIDSet.add(transactionDTO.getVchno());
                        } else if (transactionDTO.getVchtype().equalsIgnoreCase("Chalan")) {
                            receiptIDSet.add(transactionDTO.getVchno());
                        }
                    }
                }

                Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                IDMapSet.put("Sale", saleIDSet);
                IDMapSet.put("Purchase", purchaseIDSet);
                IDMapSet.put("Chalan", receiptIDSet);

                responseString = TagsHelper1.exportStockItemReport(IDMapSet, listStockItemReport);

                System.out.println("Final Response String Printing---->>>>>");
                System.out.println("" + responseString);

            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("DaybookReceiptPayment")) {
            try {
                mobileDataRequestDTO.setVoucherType("Receipt");
                IdSet = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());

                mobileDataRequestDTO.setVoucherType("Payment");
                IdSet_2 = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());

                if (!IdSet.isEmpty() || !IdSet_2.isEmpty()) {
                    dataMap.put("Receipt", IdSet);
                    dataMap.put("Payment", IdSet_2);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("DaybookSalePurchase")) {
            try {
                mobileDataRequestDTO.setVoucherType("Sale");
                IdSet = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());

                mobileDataRequestDTO.setVoucherType("Purchase");
                IdSet_2 = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());

                if (!IdSet.isEmpty() || !IdSet_2.isEmpty()) {
                    dataMap.put("Sale", IdSet);
                    dataMap.put("Purchase", IdSet_2);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("DaybookChalan")) {
            try {
                IdSet = MobileDataInOutHelper.retriveTransactionIDsFromDatabase(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate(),
                        mobileDataRequestDTO.getVoucherType(),
                        mobileDataRequestDTO.getName());
                if (IdSet.size() != 0) {
                    dataMap.put("Chalan", IdSet);
                    responseString = TagsHelper1.exportDayBook(dataMap);
                } else {
                    responseString = "No Data Available For Requested Date";
                }
            } catch (Exception ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("TrialBalance")) {
            try {
                List<TrialBalanceDTO> listOfTrialBalanceDTO = TrialBalanceDAO.getTrialBalance(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate());
                responseString = TagsHelper1.exportTrialBalanceReport(listOfTrialBalanceDTO);
            } catch (SQLException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (mobileDataRequestDTO.getVoucherType().equalsIgnoreCase("GroupSummary")) {
            try {
                Map<String, List<GroupSummaryDTO>> mapOfLedgerClosingBalance = GroupSummaryDAO.getLedgerClosingBalance(mobileDataRequestDTO.getFromDate(),
                        mobileDataRequestDTO.getToDate());
                responseString = TagsHelper1.exportGroupSummary(mapOfLedgerClosingBalance);
            } catch (SQLException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestResponseBean rrb = new RequestResponseBean(Schedular.Constants.mobile_key, mobileID, "", "", "", requestedDate, responseString);
        ClientResponse response = service.path("products").path("App").path("data").accept(MediaType.APPLICATION_XML).put(ClientResponse.class, rrb);
        isRequestInProgress = false;
    }

    public static void main(String args[]) {
        try {
            Map<String, String> mapLedger = new HashMap<String, String>();
            mobileDataRequestDTO.setVoucherType("StockItemReport");
            mobileDataRequestDTO.setName("Bagasse 1 x 1 x 1mm Ajanta Black HardWood BSL");
            mobileDataRequestDTO.setToDate("2014-02-02");
            mobileDataRequestDTO.setFromDate("2014-02-02");
            List<String> ledger_List = new ArrayList<String>();
            List<String> groups = new ArrayList<String>();
            mapLedger = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups, false);
            System.out.println("mapLedger--->>>" + mapLedger.size());
            System.out.println("name--->>>" + mobileDataRequestDTO.getName());
            String ledger_Name = mapLedger.get(mobileDataRequestDTO.getName());
            System.out.println("ledger_Name--->>" + ledger_Name);
            // String ledger_Name = mobileDataRequestDTO.getName();
            ledger_List.add(ledger_Name);
            List<LedgerReportDTO> ledgerReportFormDTOList = LedgerReportDAO.getLedgerReport(mobileDataRequestDTO.getFromDate(), mobileDataRequestDTO.getToDate(), ledger_List);
            String xmlreport = LedgerReportMobileTagHelper.generateLedgerReportMObileTags(ledgerReportFormDTOList);

            System.out.println("hurreeeeeeeeeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy\n" + xmlreport);

            RestJob r = new RestJob();
            WebResource service = null;
            RequestResponseBean request = null;
            r.sendDataResponse(service, request);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RestJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

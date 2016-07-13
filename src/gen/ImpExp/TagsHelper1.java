/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import MobileXMLOperations.RequestForMobileDataDTO;
import gen.account.groupDTODAO.GroupDAO;
import gen.account.groupDTODAO.GroupDTO;
import gen.account.ledger.LedgerDAO;
import gen.account.ledger.LedgerDTO;
import gen.account.stockgroup.StockGroupDAO;
import gen.account.stockgroup.StockGroupDTO;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemDTO;
import gen.account.unitofmeasure.UnitOfMeasureDAO;
import gen.account.unitofmeasure.UnitOfMeasureDTO;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.contra.ContraDTO;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.GroupSummaryDTO;
import gen.display.report.Ledger.LedgerReportDAO;
import gen.display.report.Ledger.LedgerReportDTO;
import gen.display.report.SaleReport1.AdvanceSaleReportDTO;
import gen.display.report.SaleReport1.AdvanceSaleReportDTOWithFinalValue;
import gen.display.report.StockItem.StockItemReportDTO;
import gen.display.report.TrialBalanceDTO;
import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import groovy.lang.Writable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import jxl.Cell;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hsqldb.Row;
import org.w3c.dom.DOMImplementation;
import sun.awt.geom.AreaOp;

/**
 *
 * @author pc5
 */
public class TagsHelper1 {

    static String mobileResponseString = "";
    public static Set<String> ledgerNameSet;
    public static Set<String> ledgerIDSet;
    public static Set<String> groupNameSet;
    public static Set<String> groupIDSet;
    public static Set<String> stockGroupIDSet;
    public static Set<String> stockGroupNameSet;
    public static Set<String> stockItemIDSet;
    public static Set<String> stockItemNameSet;
    public static Set<String> unitOfMeasureNameSet;
    public static Set<String> unitOfMeasureIDSet;
    public static Map<String, String> groupIDToNameMapping, groupNameToIDMapping;
    public static Map<String, String> ledgerNameToIDMapping;
    public static Map<String, String> stockItemNameToIDMapping;
    public static Map<String, String> stockGroupIDToNameMapping, stockGroupNameToIDMapping;
    public static Map<String, String> unitOfMeasureIDToNameMapping, unitOfMeasureNameToIDMapping, unitOfMeasureTypeIDToNameMapping;
    public static Map<String, String> maxReceiptNoWithVoucherTypeMapping;
    public static Map<String, String> stockItemNameToUOMMapping;
    public static Map<String, String> categaryIDToNameMapping, categaryNameToIDMapping;
    public static Map<String, String> colorIDToNameMapping, colorNameToIDMapping;
    public static Map<String, String> typeIDToNameMapping, typeNameToIDMapping;
    public static Map<String, String> boardtypeIDToNameMapping, boardtypeNameToIDMapping;
    public static String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n<AdSuMuDi>";
    public static String xmlEnd = "\n</AdSuMuDi>";
//    public static String xmlTallyHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n<AdSuMuDi>";
    public static String xmlTallyHeader = "<ENVELOPE>";
    public static String xmlTallyEnd = "\n</ENVELOPE>";
    // getting receipt number of Account voucher
    public static Map<String, Set<String>> vouchertypeToReceiptNoSetMapping = new HashMap<String, Set<String>>();
    public static Set<String> saleReceiptNoIDSet;
    public static Set<String> purchaseReceiptNoIDSet;
    public static Set<String> receiptReceiptNoIDSet;
    public static Set<String> paymentReceiptNoIDSet;
    public static Set<String> chalanReceiptNoIDSet;
    public static Set<String> contraReceiptNoIDSet;

    public static void initilise() throws Exception {
        System.out.println("Initialised");
        ledgerNameSet = new HashSet<String>();
        ledgerIDSet = new HashSet<String>();
        groupNameSet = new HashSet<String>();
        groupIDSet = new HashSet<String>();

        stockGroupIDSet = new HashSet<String>();
        stockGroupNameSet = new HashSet<String>();
        stockItemIDSet = new HashSet<String>();
        stockItemNameSet = new HashSet<String>();

        unitOfMeasureNameSet = new HashSet<String>();
        unitOfMeasureIDSet = new HashSet<String>();

        vouchertypeToReceiptNoSetMapping = new HashMap<String, Set<String>>();
        saleReceiptNoIDSet = new HashSet<String>();
        purchaseReceiptNoIDSet = new HashSet<String>();
        receiptReceiptNoIDSet = new HashSet<String>();
        paymentReceiptNoIDSet = new HashSet<String>();
        chalanReceiptNoIDSet = new HashSet<String>();
        contraReceiptNoIDSet = new HashSet<String>();


        groupIDToNameMapping = new HashMap<String, String>();
        stockGroupIDToNameMapping = new HashMap<String, String>();
        groupNameToIDMapping = new HashMap<String, String>();
        stockGroupNameToIDMapping = new HashMap<String, String>();
        unitOfMeasureIDToNameMapping = new HashMap<String, String>();
        unitOfMeasureTypeIDToNameMapping = new HashMap<String, String>();
        unitOfMeasureNameToIDMapping = new HashMap<String, String>();
        ledgerNameToIDMapping = new HashMap<String, String>();
        stockItemNameToIDMapping = new HashMap<String, String>();
        stockItemNameToUOMMapping = new HashMap<String, String>();



        maxReceiptNoWithVoucherTypeMapping = new HashMap<String, String>();


        categaryIDToNameMapping = new HashMap<String, String>();;
        colorIDToNameMapping = new HashMap<String, String>();
        typeIDToNameMapping = new HashMap<String, String>();
        boardtypeIDToNameMapping = new HashMap<String, String>();

        categaryNameToIDMapping = new HashMap<String, String>();;
        colorNameToIDMapping = new HashMap<String, String>();
        typeNameToIDMapping = new HashMap<String, String>();
        boardtypeNameToIDMapping = new HashMap<String, String>();

        loadGroupIDToNameMap();

        loadStockGroupIDToNameMap();

        loadGroupNameToIDMap();

        loadStockGroupNameToIDMap();

        loadLedgerNameToIDMap();

        loadUnitOfMeasureIDToNameMap();

        loadUnitOfMeasureTypeIDToNameMap();

        loadUnitOfMeasureNameToIDMap();

        loadvouchertypeToMaxReceiptNoSetMapping();

        loadSIToUOMMapping();

        loadCategaryIDToNameMapping();

        loadColorIDToNameMapping();

        loadTypeIDToNameMapping();

        loadBoardtypeIDToNameMapping();

        loadCategaryNameToIDMapping();

        loadColorNameToIDMapping();

        loadTypeNameToIDMapping();

        loadBoardtypeNameToIDMapping();

    }
    
    public static String exportDayBook(Map<String, Set<String>> IDListMap) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        return exportDayBook(IDListMap, "VoucherId");
    }

    public static String exportDayBook(Map<String, Set<String>> IDListMap, String type) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        initilise();
        String saleVoucherXML = "";
        String purchaseVoucherXML = "";
        String receiptVoucherXML = "";
        String paymentVoucherXML = "";
        System.out.println("Into exportDaybook");
        Set<String> saleSet = IDListMap.get("Sale");// = HashSet<String>();
        if (saleSet != null && saleSet.size() > 0) {
            System.out.println("Into Sale");
            saleVoucherXML = AdSuMuDiXMLGenerator.generateSaleVoucherXML(saleSet);
        }
        System.out.println("------------->>>>sale---->> str : " + saleVoucherXML);

        Set<String> purchaseSet = IDListMap.get("Purchase");
        if (purchaseSet != null && purchaseSet.size() > 0) {
            purchaseVoucherXML = AdSuMuDiXMLGenerator.generatePurchaseVoucherXML(purchaseSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + purchaseVoucherXML);

        Set<String> receiptSet = IDListMap.get("Receipt");
        if (receiptSet != null && receiptSet.size() > 0) {
            receiptVoucherXML = AdSuMuDiXMLGenerator.generateReceiptVoucherXML(receiptSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + receiptVoucherXML);

        Set<String> paymentSet = IDListMap.get("Payment");
        if (paymentSet != null && paymentSet.size() > 0) {
            paymentVoucherXML = AdSuMuDiXMLGenerator.generatePaymentVoucherXML(paymentSet);
        }
        String chalnVoucherXML = "";
        Set<String> chalanSet = IDListMap.get("Chalan");
        if (chalanSet != null && chalanSet.size() > 0) {
            chalnVoucherXML = AdSuMuDiXMLGenerator.generateChallanVoucherXML(chalanSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + paymentVoucherXML);
        String contraVoucherXML = "";
        Set<String> contraSet = IDListMap.get("Contra");
        if (contraSet != null && contraSet.size() > 0) {
            contraVoucherXML = AdSuMuDiXMLGenerator.generateContraVoucherXML(contraSet);
        }

        String ledgerListXML = "";
        String groupListXML = "";
        String stockGroupXML = "";
        String stockItemXML = "";

        String returnString = "";


        //List<LedgerDTO> ledgerDTOList = getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        System.out.println("ledgerNameSet========" + ledgerNameSet.size() + ledgerNameSet.toString());
        List<LedgerDTO> ledgerDTOList = LedgerDAO.getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        List<GroupDTO> groupDTOList = GroupDAO.getGroupListForExportImport(groupIDSet, Constants.GROUP_NAME);

        System.out.println("exportSaleVoucher----------------------------------->>>1" + unitOfMeasureIDSet.isEmpty());

//        List<StockItemDTO> stockItemDTOList = StockItemDAO.getStockItemList(stockItemNameSet, Constants.STOCK_ITEM_NAME);

        List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList(stockItemIDSet, "Name");

        for (String str : unitOfMeasureIDSet) {
            System.out.println("exportSaleVoucher----------------------------------->>>11" + str);
        }

        System.out.println("-------->>unitOfMeasureIDToNameMapping1 : " + unitOfMeasureIDToNameMapping.values().toString());
        System.out.println("stockGroupIDSet =======  " + stockGroupIDSet);
        List<StockGroupDTO> stockGroupDTOList = StockGroupDAO.getStockGroupList(stockGroupIDSet, Constants.STOCK_GROUP_NAME);
        List<UnitOfMeasureDTO> unitOfMesureDTOList = UnitOfMeasureDAO.getUnitOfMeasureList(unitOfMeasureIDSet, Constants.UNIT_MEASURE_NAME);

        System.out.println("unitOfMesureDTOList----------------------------------->>>" + unitOfMesureDTOList.size());

        System.out.println("exportSaleVoucher----------------------------------->>>2");
        //groupListXML = InventoryTagHelper.generateGroupListNodes(groupDTOList);
        ledgerListXML = InventoryTagHelper.generateLedgerListNodes(ledgerDTOList);
        //stockGroupXML = InventoryTagHelper.generateStockGroupListNodes(stockGroupDTOList);
        stockItemXML = InventoryTagHelper.generateStockItemListNodes(stockItemDTOList);
        String uOMXML = InventoryTagHelper.generateUnitOfMeasureListNodes(unitOfMesureDTOList);


        System.out.println("exportSaleVoucher----------------------------------->>>3");
        returnString = xmlHeader;
        returnString = returnString + "\n" + groupListXML;
        returnString = returnString + "\n" + ledgerListXML;
        returnString = returnString + "\n" + stockGroupXML;
        returnString = returnString + "\n" + stockItemXML;
        returnString = returnString + "\n" + uOMXML;
        returnString = returnString + "\n" + saleVoucherXML;
        returnString = returnString + "\n" + purchaseVoucherXML;
        returnString = returnString + "\n" + receiptVoucherXML;
        returnString = returnString + "\n" + paymentVoucherXML;
        returnString = returnString + "\n" + chalnVoucherXML;
        returnString = returnString + "\n" + contraVoucherXML;
        returnString = returnString + "\n" + xmlEnd;
        System.out.println("exportSaleVoucher----------------------------------->>>returnString :" + returnString);
        return returnString;
    }

    ///  fgf
    public static String exportDayBookTally(Map<String, Set<String>> IDListMap) throws SQLException, TransformerException, ParserConfigurationException, Exception {
        initilise();
        String saleVoucherXML = "";
        String purchaseVoucherXML = "";
        String receiptVoucherXML = "";
        String paymentVoucherXML = "";
        System.out.println("Into exportDaybook");
        Set<String> saleSet = IDListMap.get("Sale");// = HashSet<String>();
        if (saleSet != null && saleSet.size() > 0) {
            System.out.println("Into Sale");
            saleVoucherXML = AdSuMuDiXMLGenerator.generateSaleVoucherXML(saleSet);
        }
        System.out.println("------------->>>>sale---->> str : " + saleVoucherXML);

        Set<String> purchaseSet = IDListMap.get("Purchase");
        if (purchaseSet != null && purchaseSet.size() > 0) {
            purchaseVoucherXML = AdSuMuDiXMLGenerator.generatePurchaseVoucherXML(purchaseSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + purchaseVoucherXML);

        Set<String> receiptSet = IDListMap.get("Receipt");
        if (receiptSet != null && receiptSet.size() > 0) {
            System.out.println("In Receipt No ------------");
            receiptVoucherXML = AdSuMuDiXMLGenerator.generateReceiptVoucherXML(receiptSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + receiptVoucherXML);

        Set<String> paymentSet = IDListMap.get("Payment");
        if (paymentSet != null && paymentSet.size() > 0) {
            paymentVoucherXML = AdSuMuDiXMLGenerator.generatePaymentVoucherXML(paymentSet);
        }
        String chalnVoucherXML = "";
        Set<String> chalanSet = IDListMap.get("Chalan");
        if (chalanSet != null && chalanSet.size() > 0) {
            chalnVoucherXML = AdSuMuDiXMLGenerator.generateChallanVoucherXML(chalanSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + paymentVoucherXML);
        String contraVoucherXML = "";
        Set<String> contraSet = IDListMap.get("Contra");
        if (contraSet != null && contraSet.size() > 0) {
            contraVoucherXML = AdSuMuDiXMLGenerator.generateContraVoucherXML(contraSet);
        }


        // For AdSuMuDi
        /*
         String returnString = "";
         String ledgerListXML = "";
         String groupListXML = "";
         String stockGroupXML = "";
         String stockItemXML = "";

         //List<LedgerDTO> ledgerDTOList = getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
         List<LedgerDTO> ledgerDTOList = LedgerDAO.getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
         List<GroupDTO> groupDTOList = GroupDAO.getGroupListForExportImport(groupIDSet, Constants.GROUP_NAME);

         System.out.println("exportSaleVoucher----------------------------------->>>1" + unitOfMeasureIDSet.isEmpty());

         List<StockItemDTO> stockItemDTOList = StockItemDAO.getStockItemList(stockItemNameSet, Constants.STOCK_ITEM_NAME);
         for (String str : unitOfMeasureIDSet) {
         System.out.println("exportSaleVoucher----------------------------------->>>11" + str);
         }

         System.out.println("-------->>unitOfMeasureIDToNameMapping1 : " + unitOfMeasureIDToNameMapping.values().toString());
         System.out.println("stockGroupIDSet =======  " + stockGroupIDSet);
         List<StockGroupDTO> stockGroupDTOList = StockGroupDAO.getStockGroupList(stockGroupIDSet, Constants.STOCK_GROUP_NAME);
         List<UnitOfMeasureDTO> unitOfMesureDTOList = UnitOfMeasureDAO.getUnitOfMeasureList(unitOfMeasureIDSet, Constants.UNIT_MEASURE_NAME);

         System.out.println("unitOfMesureDTOList----------------------------------->>>" + unitOfMesureDTOList.size());

         System.out.println("exportSaleVoucher----------------------------------->>>2");
         //groupListXML = InventoryTagHelper.generateGroupListNodes(groupDTOList);
         ledgerListXML = InventoryTagHelper.generateLedgerListNodes(ledgerDTOList);
         //stockGroupXML = InventoryTagHelper.generateStockGroupListNodes(stockGroupDTOList);
         stockItemXML = InventoryTagHelper.generateStockItemListNodes(stockItemDTOList);
         String uOMXML = InventoryTagHelper.generateUnitOfMeasureListNodes(unitOfMesureDTOList);
        
         System.out.println("exportSaleVoucher----------------------------------->>>3");
         // for AdSuMuDi
         returnString = xmlHeader;
         returnString = returnString + "\n" + groupListXML;
         returnString = returnString + "\n" + ledgerListXML;
         returnString = returnString + "\n" + stockGroupXML;
         returnString = returnString + "\n" + stockItemXML;
         returnString = returnString + "\n" + uOMXML;
         returnString = returnString + "\n" + saleVoucherXML;
         returnString = returnString + "\n" + purchaseVoucherXML;
         returnString = returnString + "\n" + receiptVoucherXML;
         returnString = returnString + "\n" + paymentVoucherXML;
         returnString = returnString + "\n" + chalnVoucherXML;
         returnString = returnString + "\n" + contraVoucherXML;
         returnString = returnString + "\n" + xmlEnd; 
         */


        // for Tally
        String returnString = "";
        returnString = xmlTallyHeader;
        returnString = returnString + "\n" + "<HEADER>";
        returnString = returnString + "\n" + "<TALLYREQUEST>";
        returnString = returnString + "Import Data";
        returnString = returnString + "</TALLYREQUEST>";
        returnString = returnString + "\n" + "</HEADER>";
        returnString = returnString + "\n" + "<BODY>";
        returnString = returnString + "\n" + "<IMPORTDATA>";
        returnString = returnString + "\n" + "<REQUESTDESC>";
        returnString = returnString + "\n" + "<REPORTNAME>";
        returnString = returnString + "Vouchers";
        returnString = returnString + "</REPORTNAME>";
        returnString = returnString + "\n" + "<STATICVARIABLES>";
        returnString = returnString + "\n" + "<SVCURRENTCOMPANY>";
        returnString = returnString + gen.dto.Constants.CURRENT_COMPANY_NAME;
        returnString = returnString + "</SVCURRENTCOMPANY>";
        returnString = returnString + "\n" + "</STATICVARIABLES> ";
        returnString = returnString + "\n" + "</REQUESTDESC>";
        returnString = returnString + "\n" + "<REQUESTDATA>";
        returnString = returnString + "\n" + saleVoucherXML;
        returnString = returnString.trim() + "\n" + purchaseVoucherXML;
        returnString = returnString.trim() + "\n" + receiptVoucherXML;
        returnString = returnString.trim() + "\n" + paymentVoucherXML;
        //        returnString = returnString + "\n" + chalnVoucherXML;
        returnString = returnString.trim() + "\n" + contraVoucherXML;
        returnString = returnString + "</REQUESTDATA>";
        returnString = returnString + "\n" + "</IMPORTDATA>";
        returnString = returnString + "\n" + "</BODY>";
        returnString = returnString + xmlTallyEnd;



        System.out.println("exportSaleVoucher----------------------------------->>>returnString :" + returnString);
        return returnString;
    }

    public static String exportLedgerReport(Map<String, Set<String>> IDListMap, List<LedgerReportDTO> listOfLedgerReportDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException, SQLException, Exception {
        initilise();
        String saleVoucherXML = "";
        String purchaseVoucherXML = "";
        String receiptVoucherXML = "";
        String paymentVoucherXML = "";
        System.out.println("Into exportLedgerReport");
        Set<String> saleSet = IDListMap.get("Sale");// = HashSet<String>();
        if (saleSet != null && saleSet.size() > 0) {
            System.out.println("Into Sale");
            saleVoucherXML = AdSuMuDiXMLGenerator.generateSaleVoucherXML(saleSet);
        }
        System.out.println("------------->>>>sale---->> str : " + saleVoucherXML);

        Set<String> purchaseSet = IDListMap.get("Purchase");
        if (purchaseSet != null && purchaseSet.size() > 0) {
            purchaseVoucherXML = AdSuMuDiXMLGenerator.generatePurchaseVoucherXML(purchaseSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + purchaseVoucherXML);

        Set<String> receiptSet = IDListMap.get("Receipt");
        if (receiptSet != null && receiptSet.size() > 0) {
            receiptVoucherXML = AdSuMuDiXMLGenerator.generateReceiptVoucherXML(receiptSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + receiptVoucherXML);

        Set<String> paymentSet = IDListMap.get("Payment");
        if (paymentSet != null && paymentSet.size() > 0) {
            paymentVoucherXML = AdSuMuDiXMLGenerator.generatePaymentVoucherXML(paymentSet);
        }
        String chalnVoucherXML = "";
        Set<String> chalanSet = IDListMap.get("Chalan");
        if (chalanSet != null && chalanSet.size() > 0) {
            chalnVoucherXML = AdSuMuDiXMLGenerator.generateChallanVoucherXML(chalanSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + paymentVoucherXML);
        String contraVoucherXML = "";
        Set<String> contraSet = IDListMap.get("Contra");
        if (contraSet != null && contraSet.size() > 0) {
            contraVoucherXML = AdSuMuDiXMLGenerator.generateContraVoucherXML(contraSet);
        }

        String openingClosingBalanceSupportXML = AdSuMuDiXMLGenerator.generateOpClosBalanceSupportXMLForLedgerReport(listOfLedgerReportDTO);

        String ledgerListXML = "";
        String groupListXML = "";
        String stockGroupXML = "";
        String stockItemXML = "";
        String returnString = "";


        //List<LedgerDTO> ledgerDTOList = getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        List<LedgerDTO> ledgerDTOList = LedgerDAO.getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        List<GroupDTO> groupDTOList = GroupDAO.getGroupListForExportImport(groupIDSet, Constants.GROUP_NAME);

        System.out.println("exportSaleVoucher----------------------------------->>>1" + unitOfMeasureIDSet.isEmpty());

        //commented by sudeep on 2-5-2014
//        List<StockItemDTO> stockItemDTOList = StockItemDAO.getStockItemList(stockItemNameSet, Constants.STOCK_ITEM_NAME);

        List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList(stockItemIDSet);

        for (String str : unitOfMeasureIDSet) {
            System.out.println("exportSaleVoucher----------------------------------->>>11" + str);
        }

        System.out.println("-------->>unitOfMeasureIDToNameMapping1 : " + unitOfMeasureIDToNameMapping.values().toString());
        List<StockGroupDTO> stockGroupDTOList = StockGroupDAO.getStockGroupList(stockGroupIDSet, Constants.STOCK_GROUP_NAME);
        List<UnitOfMeasureDTO> unitOfMesureDTOList = UnitOfMeasureDAO.getUnitOfMeasureList(unitOfMeasureIDSet, Constants.UNIT_MEASURE_NAME);

        System.out.println("unitOfMesureDTOList----------------------------------->>>" + unitOfMesureDTOList.size());

        System.out.println("exportSaleVoucher----------------------------------->>>2");
        groupListXML = InventoryTagHelper.generateGroupListNodes(groupDTOList);
        ledgerListXML = InventoryTagHelper.generateLedgerListNodes(ledgerDTOList);
        stockGroupXML = InventoryTagHelper.generateStockGroupListNodes(stockGroupDTOList);
        stockItemXML = InventoryTagHelper.generateStockItemListNodes(stockItemDTOList);
        String uOMXML = InventoryTagHelper.generateUnitOfMeasureListNodes(unitOfMesureDTOList);


        System.out.println("exportSaleVoucher----------------------------------->>>3");
        returnString = xmlHeader;
        //Static string added here mobileResponseString for pramods combined ledger xml
        //should be checked for inverse effects
        System.out.println("MobileResponseStringIn TagsHelper1---->>>>" + mobileResponseString);
        returnString = returnString + mobileResponseString;
        returnString = returnString + "\n" + groupListXML;
        returnString = returnString + "\n" + ledgerListXML;
        returnString = returnString + "\n" + stockGroupXML;
        returnString = returnString + "\n" + stockItemXML;
        returnString = returnString + "\n" + uOMXML;
        returnString = returnString + "\n" + saleVoucherXML;
        returnString = returnString + "\n" + purchaseVoucherXML;
        returnString = returnString + "\n" + receiptVoucherXML;
        returnString = returnString + "\n" + paymentVoucherXML;
        returnString = returnString + "\n" + chalnVoucherXML;
        returnString = returnString + "\n" + contraVoucherXML;
        returnString = returnString + "\n" + openingClosingBalanceSupportXML;
        returnString = returnString + "\n" + xmlEnd;
        System.out.println("exportSaleVoucher----------------------------------->>>returnString :" + returnString);


        return returnString;
    }

    public static String exportStockItemReport(Map<String, Set<String>> IDListMap, List<StockItemReportDTO> listOfStockItemReportDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException, SQLException, Exception {

        initilise();
        String saleVoucherXML = "";
        String purchaseVoucherXML = "";
        String receiptVoucherXML = "";
        String paymentVoucherXML = "";
        System.out.println("Into exportLedgerReport");
        Set<String> saleSet = IDListMap.get("Sale");// = HashSet<String>();
        if (saleSet != null && saleSet.size() > 0) {
            System.out.println("Into Sale");
            saleVoucherXML = AdSuMuDiXMLGenerator.generateSaleVoucherXML(saleSet);
        }
        System.out.println("------------->>>>sale---->> str : " + saleVoucherXML);

        Set<String> purchaseSet = IDListMap.get("Purchase");
        if (purchaseSet != null && purchaseSet.size() > 0) {
            purchaseVoucherXML = AdSuMuDiXMLGenerator.generatePurchaseVoucherXML(purchaseSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + purchaseVoucherXML);

        Set<String> receiptSet = IDListMap.get("Receipt");
        if (receiptSet != null && receiptSet.size() > 0) {
            receiptVoucherXML = AdSuMuDiXMLGenerator.generateReceiptVoucherXML(receiptSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + receiptVoucherXML);

        Set<String> paymentSet = IDListMap.get("Payment");
        if (paymentSet != null && paymentSet.size() > 0) {
            paymentVoucherXML = AdSuMuDiXMLGenerator.generatePaymentVoucherXML(paymentSet);
        }
        String chalnVoucherXML = "";
        Set<String> chalanSet = IDListMap.get("Chalan");
        if (chalanSet != null && chalanSet.size() > 0) {
            chalnVoucherXML = AdSuMuDiXMLGenerator.generateChallanVoucherXML(chalanSet);
        }
        System.out.println("\n\n\n\n\n\n\\n\n------------->>>>Purchase---->> str : " + paymentVoucherXML);
        String contraVoucherXML = "";
        Set<String> contraSet = IDListMap.get("Contra");
        if (contraSet != null && contraSet.size() > 0) {
            contraVoucherXML = AdSuMuDiXMLGenerator.generateContraVoucherXML(contraSet);
        }

        String openingClosingBalanceSupportXML = AdSuMuDiXMLGenerator.generateOpClosBalanceSupportXMLForStockItemReport(listOfStockItemReportDTO);

        String ledgerListXML = "";
        String groupListXML = "";
        String stockGroupXML = "";
        String stockItemXML = "";

        String returnString = "";


        //List<LedgerDTO> ledgerDTOList = getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        List<LedgerDTO> ledgerDTOList = LedgerDAO.getLedgerList(ledgerNameSet, Constants.LEDGER_NAME);
        List<GroupDTO> groupDTOList = GroupDAO.getGroupListForExportImport(groupIDSet, Constants.GROUP_NAME);

        System.out.println("exportSaleVoucher----------------------------------->>>1" + unitOfMeasureIDSet.isEmpty());

        //commented by sudeep 2-5-2014
//        List<StockItemDTO> stockItemDTOList = StockItemDAO.getStockItemList(stockItemNameSet, Constants.STOCK_ITEM_NAME);

        List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList(stockItemIDSet);

        for (String str : unitOfMeasureIDSet) {
            System.out.println("exportSaleVoucher----------------------------------->>>11" + str);
        }

        System.out.println("-------->>unitOfMeasureIDToNameMapping1 : " + unitOfMeasureIDToNameMapping.values().toString());
        List<StockGroupDTO> stockGroupDTOList = StockGroupDAO.getStockGroupList(stockGroupIDSet, Constants.STOCK_GROUP_NAME);
        List<UnitOfMeasureDTO> unitOfMesureDTOList = UnitOfMeasureDAO.getUnitOfMeasureList(unitOfMeasureIDSet, Constants.UNIT_MEASURE_NAME);

        System.out.println("unitOfMesureDTOList----------------------------------->>>" + unitOfMesureDTOList.size());

        System.out.println("exportSaleVoucher----------------------------------->>>2");
        groupListXML = InventoryTagHelper.generateGroupListNodes(groupDTOList);
        ledgerListXML = InventoryTagHelper.generateLedgerListNodes(ledgerDTOList);
        stockGroupXML = InventoryTagHelper.generateStockGroupListNodes(stockGroupDTOList);
        stockItemXML = InventoryTagHelper.generateStockItemListNodes(stockItemDTOList);
        String uOMXML = InventoryTagHelper.generateUnitOfMeasureListNodes(unitOfMesureDTOList);


        System.out.println("exportSaleVoucher----------------------------------->>>3");
        returnString = xmlHeader;
        returnString = returnString + "\n" + groupListXML;
        returnString = returnString + "\n" + ledgerListXML;
        returnString = returnString + "\n" + stockGroupXML;
        returnString = returnString + "\n" + stockItemXML;
        returnString = returnString + "\n" + uOMXML;
        returnString = returnString + "\n" + saleVoucherXML;
        returnString = returnString + "\n" + purchaseVoucherXML;
        returnString = returnString + "\n" + receiptVoucherXML;
        returnString = returnString + "\n" + paymentVoucherXML;
        returnString = returnString + "\n" + chalnVoucherXML;
        returnString = returnString + "\n" + contraVoucherXML;
        returnString = returnString + "\n" + openingClosingBalanceSupportXML;
        returnString = returnString + "\n" + xmlEnd;
        System.out.println("exportSaleVoucher----------------------------------->>>returnString :" + returnString);


        return returnString;
    }

    public static String exportTrialBalanceReport(List<TrialBalanceDTO> listOfTrialBalanceDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, Exception {

        initilise();

        String groupListXML = "";
        String returnString = "";

        groupListXML = AdSuMuDiXMLGenerator.generateTrialBalanceReport(listOfTrialBalanceDTO);

        returnString = xmlHeader;
        returnString = returnString + "\n" + groupListXML;
        returnString = returnString + "\n" + xmlEnd;
        System.out.println("exportTrialBalance----------------------------------->>>" + returnString);


        return returnString;

    }

    public static String exportGroupSummary(Map<String, List<GroupSummaryDTO>> mapOfLedgerClosingBalance) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, Exception {

        initilise();

        String groupSummaryXML = "";
        String returnString = "";

        groupSummaryXML = AdSuMuDiXMLGenerator.generateGroupSummaryReport(mapOfLedgerClosingBalance);

        returnString = xmlHeader;
        returnString = returnString + "\n" + groupSummaryXML;
        returnString = returnString + "\n" + xmlEnd;
        System.out.println("exportTrialBalance----------------------------------->>>" + returnString);


        return returnString;

    }

    public static void loadGroupIDToNameMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblgroup";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                groupIDToNameMapping.put(String.valueOf(resultSet.getInt("group_id")), resultSet.getString("group_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadGroupNameToIDMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblgroup";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                groupNameToIDMapping.put(String.valueOf(resultSet.getString("group_Name")), String.valueOf(resultSet.getInt("group_ID")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadUnitOfMeasureNameToIDMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblunitofmeasure";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                unitOfMeasureNameToIDMapping.put(String.valueOf(resultSet.getString("uom_formalName")), String.valueOf(resultSet.getInt("uom_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadUnitOfMeasureIDToNameMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblunitofmeasure";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                unitOfMeasureIDToNameMapping.put(String.valueOf(resultSet.getInt("uom_id")), resultSet.getString("uom_formalName"));
            }
            System.out.println("-------->>unitOfMeasureIDToNameMapping : " + unitOfMeasureIDToNameMapping.values().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadUnitOfMeasureTypeIDToNameMap() {

        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblunitofmeasure";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                unitOfMeasureIDToNameMapping.put(String.valueOf(resultSet.getInt("uomType_id")), resultSet.getString("uom_type"));
            }
            System.out.println("-------->>unitOfMeasureIDToNameMapping : " + unitOfMeasureIDToNameMapping.values().toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void loadStockGroupIDToNameMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblstockgroup";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println("SG _ id =============  " + resultSet.getInt("sg_id"));
                System.out.println("SG _ Nmae =============  " + resultSet.getString("sg_name"));
                stockGroupIDToNameMapping.put(String.valueOf(resultSet.getInt("sg_id")), resultSet.getString("sg_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadStockGroupNameToIDMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblstockgroup";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                stockGroupNameToIDMapping.put(resultSet.getString("sg_name"), String.valueOf(resultSet.getInt("sg_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadLedgerNameToIDMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblledger";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                ledgerNameToIDMapping.put(resultSet.getString("ledger_name"), String.valueOf(resultSet.getInt("ledger_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadStockItemNameToIDMap() {
        Connection con = DatabaseConnection1.GetConnection();
        String query = "select * from tblstockitem";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                stockItemNameToIDMapping.put(resultSet.getString("si_name"), String.valueOf(resultSet.getInt("si_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static Map<String, Set<String>> getDatabaseReceiptNoWithDate(Map<String, Map<String, String>> paramMap) {
        Map<String, Set<String>> returnMap = new HashMap<String, Set<String>>();
        Connection con = null;
//	String query = "select  trans_receiptNo,trans_typeIndex from tblTransactionMain trans_typeIndex = " + Constants.SALE_TYPE_INDEX;
        System.out.println("TTTTTTTT        " + paramMap.keySet());
        for (String str : paramMap.keySet()) {

            String voucherIdStr = "";
            for (String group : paramMap.get(str).keySet()) {
                voucherIdStr = voucherIdStr + "OR ( trans_receiptNo = " + group + " AND trans_date='" + paramMap.get(str).get(group) + "') ";
            }

            voucherIdStr = voucherIdStr.trim().substring(3, voucherIdStr.length() - 1);

            String query = "select  trans_receiptNo,trans_typeIndex from tblTransactionMain where trans_typeIndex in (" + str + ") and " + voucherIdStr;
            System.out.println("Query getting ----++++++++++-----  " + query);
            try {
                con = DatabaseConnection1.GetConnection();
                PreparedStatement preparedStmt = con.prepareStatement(query);
                ResultSet resultSet = preparedStmt.executeQuery();
                while (resultSet.next()) {
                    if (returnMap.get(str) == null) {
                        returnMap.put(str, new HashSet<String>());
                    }
                    System.out.println("Always -------------  " + resultSet.getString("trans_receiptNo"));
                    returnMap.get(str).add(resultSet.getString("trans_receiptNo"));
                }
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger
                        .getLogger(TagsHelper1.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnMap;

    }

    public static Map<String, Set<String>> getDatabaseReceiptNo(Map<String, Set<String>> paramMap) {
        Map<String, Set<String>> returnMap = new HashMap<String, Set<String>>();
        Connection con = null;
//	String query = "select  trans_receiptNo,trans_typeIndex from tblTransactionMain trans_typeIndex = " + Constants.SALE_TYPE_INDEX;
        System.out.println("TTTTTTTT        " + paramMap.keySet());
        for (String str : paramMap.keySet()) {

            String groupNameList = "";
            for (String group : paramMap.get(str)) {
                groupNameList = groupNameList + "" + group + ",";
            }

            groupNameList = groupNameList.trim().substring(0, groupNameList.length() - 1);

            String query = "select  trans_receiptNo,trans_typeIndex from tblTransactionMain where trans_typeIndex in (" + str + ") and trans_receiptNo in (" + groupNameList + ")";
            System.out.println("Query getting ---------  " + query);
            try {
                con = DatabaseConnection1.GetConnection();
                PreparedStatement preparedStmt = con.prepareStatement(query);
                ResultSet resultSet = preparedStmt.executeQuery();
                while (resultSet.next()) {
                    if (returnMap.get(str) == null) {
                        returnMap.put(str, new HashSet<String>());
                    }
                    System.out.println("Always -------------  " + resultSet.getString("trans_receiptNo"));
                    returnMap.get(str).add(resultSet.getString("trans_receiptNo"));
                }
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger
                        .getLogger(TagsHelper1.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return returnMap;

    }

    public static void loadvouchertypeToMaxReceiptNoSetMapping() throws Exception {
        Connection con = DatabaseConnection1.GetConnection();
        con.setAutoCommit(false);
        String query = "select  trans_receiptNo,trans_typeIndex from tblTransactionMain order by trans_receiptNo Asc";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                maxReceiptNoWithVoucherTypeMapping.put(resultSet.getString("trans_typeIndex"), resultSet.getString("trans_receiptNo"));
                System.out.println("Vouchert type -----------------" + resultSet.getString("trans_typeIndex"));
                System.out.println("Receipt no -----------------" + resultSet.getString("trans_receiptNo"));
//		purchaseReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
            }
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public static void loadSIToUOMMapping() throws Exception {
        Connection con = DatabaseConnection1.GetConnection();
        con.setAutoCommit(false);
        String query = "select si.si_name,uom.uom_symbol from tblstockitem as si inner join tblunitofmeasure as uom ON si.si_unitOfMeasure = uom.uom_id";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                stockItemNameToUOMMapping.put(resultSet.getString("si_name").trim(), resultSet.getString("uom_symbol").trim());
                System.out.println("Vouchert type -----------------" + resultSet.getString("si_name"));
                System.out.println("Receipt no -----------------" + resultSet.getString("uom_symbol"));
//		purchaseReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
            }
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public static void loadCategaryIDToNameMapping() throws Exception {
        categaryIDToNameMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadCategoryIDToNameMap();
    }

    public static void loadCategaryNameToIDMapping() throws Exception {
        categaryNameToIDMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadCategoryNameToIDMap();
    }

    public static void loadColorIDToNameMapping() throws Exception {
        colorIDToNameMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadColorIDToNameMap();
    }

    public static void loadColorNameToIDMapping() throws Exception {
        colorNameToIDMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadColorNameToIDMap();
    }

    public static void loadTypeIDToNameMapping() throws Exception {
        typeIDToNameMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadFinishTypeIDToNameMap();

    }

    public static void loadTypeNameToIDMapping() throws Exception {
        typeNameToIDMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadFinishTypeNameToIDMap();

    }

    public static void loadBoardtypeIDToNameMapping() throws Exception {
        boardtypeIDToNameMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadTypeIDToNameMap();
    }

    public static void loadBoardtypeNameToIDMapping() throws Exception {
        boardtypeNameToIDMapping = gen.account.StockItemFormation.StockItemFormationDAO.loadTypeNameToIDMap();
    }

//
//    public static void loadreceiptReceiptNoIDSet() {
//	Connection con = DatabaseConnection1.GetConnection();
//	String query = "select  trans_receiptNo from tblTransactionMain trans_typeIndex = " + Constants.RECEIPT_TYPE_INDEX;
//	try {
//	    PreparedStatement preparedStmt = con.prepareStatement(query);
//	    ResultSet resultSet = preparedStmt.executeQuery();
//	    while (resultSet.next()) {
//		receiptReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
//	    }
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void loadpaymentReceiptNoIDSet() {
//	Connection con = DatabaseConnection1.GetConnection();
//	String query = "select  trans_receiptNo from tblTransactionMain trans_typeIndex = " + Constants.PAYMENT_TYPE_INDEX;
//	try {
//	    PreparedStatement preparedStmt = con.prepareStatement(query);
//	    ResultSet resultSet = preparedStmt.executeQuery();
//	    while (resultSet.next()) {
//		paymentReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
//	    }
//	    con.close();
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void loadchalanReceiptNoIDSet() {
//	Connection con = DatabaseConnection1.GetConnection();
//	String query = "select  trans_receiptNo from tblTransactionMain trans_typeIndex = " + Constants.CHALAN_TYPE_INDEX;
//	try {
//	    PreparedStatement preparedStmt = con.prepareStatement(query);
//	    ResultSet resultSet = preparedStmt.executeQuery();
//	    while (resultSet.next()) {
//		chalanReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
//	    }
//	    con.close();
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
//
//    public static void loadcontraReceiptNoIDSet() {
//	Connection con = DatabaseConnection1.GetConnection();
//	String query = "select  trans_receiptNo from tblTransactionMain trans_typeIndex = " + Constants.CONTRA_TYPE_INDEX;
//	try {
//	    PreparedStatement preparedStmt = con.prepareStatement(query);
//	    ResultSet resultSet = preparedStmt.executeQuery();
//	    while (resultSet.next()) {
//		contraReceiptNoIDSet.add(resultSet.getString("trans_receiptNo"));
//	    }
//	} catch (SQLException ex) {
//	    ex.printStackTrace();
//	    Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//	}
//    }
    public static void main(String[] args) {
        //////////////////////////////Sale
        /*    List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
         SaleDTO saleDTO = new SaleDTO();
         List<StockItemTransactionDTO> stkList = new ArrayList<StockItemTransactionDTO>();
         StockItemTransactionDTO a = new StockItemTransactionDTO();
         a.setName("Balls");
         StockItemTransactionDTO b = new StockItemTransactionDTO();
         stkList.add(b);
         stkList.add(a);
         saleDTO.setStockItemTreansactioDTOList(stkList);
         saleDTOList.add(saleDTO);
         saleDTOList.add(saleDTO);
         TagsHelper1.initilise();
         try {
         try {
         System.out.println("groupXML------>>" + TagsHelper1.exportSaleVoucher(saleDTOList));
         } catch (TransformerConfigurationException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         } catch (TransformerException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         }
         } catch (ParserConfigurationException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         }

         /////////////////////////////////////////////////payment
         /*List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
         PaymentDTO paymentDTO = new PaymentDTO();
         List<LedgerTransactionDTO> ledList = new ArrayList<LedgerTransactionDTO>();
         LedgerTransactionDTO a = new LedgerTransactionDTO();
         a.setName("Sudeep");
         LedgerTransactionDTO b = new LedgerTransactionDTO();
         ledList.add(b);
         ledList.add(a);
         paymentDTO.setLedgerTransactionDTOList(ledList);
         paymentDTOList.add(paymentDTO);
         paymentDTOList.add(paymentDTO);
         TagsHelper1.initilise();
         try {
         try {
         System.out.println("groupXML------>>" + TagsHelper1.exportPaymentVoucher(paymentDTOList));
         } catch (TransformerConfigurationException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         } catch (TransformerException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         }
         } catch (ParserConfigurationException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error------>>" + ex);
         }*/
        /*try {
         exportDayBook();
         } catch (SQLException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         } catch (TransformerException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ParserConfigurationException ex) {
         Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    public static String combinedLedgerReport(RequestForMobileDataDTO mobileDataRequestDTO) {
        String response = "";
        try {
            Map<String, String> mapLedger1 = new HashMap<String, String>();
            List<String> ledger_List = new ArrayList<String>();
            List<String> groups1 = new ArrayList<String>();
            mapLedger1 = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups1, false);
            System.out.println("mapLedger--->>>" + mapLedger1.size());
            System.out.println("name--->>>" + mobileDataRequestDTO.getName());
            String ledger_Name = mapLedger1.get(mobileDataRequestDTO.getName());
            System.out.println("ledger_Name--->>" + ledger_Name);
            ledger_List.add(ledger_Name);
            List<LedgerReportDTO> ledgerReportFormDTOList = LedgerReportDAO.getLedgerReport(mobileDataRequestDTO.getFromDate(), mobileDataRequestDTO.getToDate(), ledger_List);
            //String xmlreport = LedgerReportMobileTagHelper.generateLedgerReportMObileTags(ledgerReportFormDTOList);
            response = LedgerReportMobileTagHelper.generateLedgerReportMObileTags(ledgerReportFormDTOList);
            mobileResponseString = response;
            System.out.println("mobileResponseString---in taghelper--->>>" + mobileResponseString);


        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TagsHelper1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    public static String generateAdvanceSaleOrPurchaseReportExcel(String transactionType, AdvanceSaleReportDTOWithFinalValue advanceSaleReportDTOWithFinalValue, String path, String fromDate, String toDate) throws Exception {
        try {
            int row = 2;
            int column = 1;
            System.out.println("PAth ============ " + path);
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(path + ".xls"));
            WritableSheet wsheet = wworkbook.createSheet("First", 0);



            // set width to column
            wsheet.setColumnView(row, 40);
            wsheet.setColumnView(row + 1, 12);
            wsheet.setColumnView(row + 2, 18);
            wsheet.setColumnView(row + 3, 18);
            wsheet.setColumnView(row + 4, 18);
            wsheet.setColumnView(row + 5, 18);
            wsheet.setColumnView(row + 6, 18);

            WritableFont cellFont_16 = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
//            cellFont.setColour(Colour.BLUE);
            WritableCellFormat cellFormat_16 = new WritableCellFormat(cellFont_16);

            // Date 
            Label fromDateL = new Label(column + 3, row, fromDate, cellFormat_16);
            wsheet.addCell(fromDateL);

            Label to = new Label(column + 4, row, "TO", cellFormat_16);
            wsheet.addCell(to);

            Label toDateL = new Label(column + 5, row, toDate, cellFormat_16);
            wsheet.addCell(toDateL);

            row++;

            // Heading Sale/Purchase
            Label Title = new Label(column + 4, row, transactionType, cellFormat_16);
            wsheet.addCell(Title);

            row = row + 2;

            WritableFont cellFont_Part = new WritableFont(WritableFont.TIMES, 12);
            WritableCellFormat cellFormat_Part = new WritableCellFormat(cellFont_Part);
            cellFormat_Part.setBorder(Border.ALL, BorderLineStyle.THIN);

            Label SrNo_label = new Label(column, row, "Sr.No", cellFormat_Part);
            wsheet.addCell(SrNo_label);
            Label parti = new Label(column + 1, row, "Party", cellFormat_Part);
            wsheet.addCell(parti);
            Label No_Invoice = new Label(column + 2, row, "No of Inovice", cellFormat_Part);
            wsheet.addCell(No_Invoice);
            Label particular_VATNo = new Label(column + 3, row, "Vat Tin No", cellFormat_Part);
            wsheet.addCell(particular_VATNo);
            Label Basic_Amount = new Label(column + 4, row, "Basic", cellFormat_Part);
            wsheet.addCell(Basic_Amount);
            Label VAT5Per = new Label(column + 5, row, "Vat @ 5%", cellFormat_Part);
            wsheet.addCell(VAT5Per);
            Label VAT12Per = new Label(column + 6, row, "Vat @ 12%", cellFormat_Part);
            wsheet.addCell(VAT12Per);
            Label TotalAmount = new Label(column + 7, row, "TotalAmount", cellFormat_Part);
            wsheet.addCell(TotalAmount);

            row++;
            int srnoCount = 0;
            srnoCount++;

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

            for (AdvanceSaleReportDTO SaleReportDTO : advanceSaleReportDTOWithFinalValue.getAdvanceSaleReportDTOList()) {

                jxl.write.Number sr_no = new jxl.write.Number(column, row, srnoCount, cellFormat_Part);
                wsheet.addCell(sr_no);
                jxl.write.Label partyname = new jxl.write.Label(column + 1, row, SaleReportDTO.getParticular(), cellFormat_Part);
                wsheet.addCell(partyname);
                jxl.write.Number no_of_Invoice = new jxl.write.Number(column + 2, row, Double.parseDouble(SaleReportDTO.getNo_Of_Invoices()), cellFormat_Part);
                wsheet.addCell(no_of_Invoice);
                jxl.write.Label parti_VATNo = new jxl.write.Label(column + 3, row, SaleReportDTO.getParticular_VAT_No(), cellFormat_Part);
                wsheet.addCell(parti_VATNo);
                jxl.write.Number total_Basic = new jxl.write.Number(column + 4, row, Double.parseDouble(SaleReportDTO.getTotal_Basic_Amount()), cellFormat_Part);
                wsheet.addCell(total_Basic);
                jxl.write.Number totalt_5Per = new jxl.write.Number(column + 5, row, Double.parseDouble(SaleReportDTO.getTotal_5Per_Amount()), cellFormat_Part);
                wsheet.addCell(totalt_5Per);
                jxl.write.Number totalt_12Per = new jxl.write.Number(column + 6, row, Double.parseDouble(SaleReportDTO.getTotal_12Per_Amount()), cellFormat_Part);
                wsheet.addCell(totalt_12Per);
                jxl.write.Number total_final_Amount = new jxl.write.Number(column + 7, row, Double.parseDouble(SaleReportDTO.getTotal_Final_Amount()), cellFormat_Part);
                wsheet.addCell(total_final_Amount);
                row++;
                srnoCount++;
            }


            jxl.write.Number final_basic_total = new jxl.write.Number(column + 4, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Basic_Amount()), cellFormat);
            jxl.write.Number final_5Per_total = new jxl.write.Number(column + 5, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_5Per_Amount()), cellFormat);
            jxl.write.Number final_12Per_total = new jxl.write.Number(column + 6, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_12Per_Amount()), cellFormat);
            jxl.write.Number final_total_Amount = new jxl.write.Number(column + 7, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Amount()), cellFormat);
            wsheet.addCell(final_basic_total);
            wsheet.addCell(final_5Per_total);
            wsheet.addCell(final_12Per_total);
            wsheet.addCell(final_total_Amount);



            wworkbook.write();
            wworkbook.close();


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return "dsf0";
    }

    public static void generateSaleReportExcel(String transactionType, AdvanceSaleReportDTOWithFinalValue advanceSaleReportDTOWithFinalValue, String path, String fromDate, String toDate) throws Exception {
        try {
            int row = 2;
            int column = 1;
            System.out.println("PAth ============ " + path);
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(path + ".xls"));
            WritableSheet wsheet = wworkbook.createSheet("First", 0);

            // set width to column
            wsheet.setColumnView(row, 40);
            wsheet.setColumnView(row + 1, 12);
            wsheet.setColumnView(row + 2, 12);
            wsheet.setColumnView(row + 3, 18);
            wsheet.setColumnView(row + 4, 18);
            wsheet.setColumnView(row + 5, 18);
            wsheet.setColumnView(row + 6, 18);
            wsheet.setColumnView(row + 7, 18);
            wsheet.setColumnView(row + 8, 18);
            wsheet.setColumnView(row + 9, 18);
            wsheet.setColumnView(row + 10, 18);

            WritableFont cellFont_16 = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
//            cellFont.setColour(Colour.BLUE);
            WritableCellFormat cellFormat_16 = new WritableCellFormat(cellFont_16);

            // Heading Date 
            Label fromDateL = new Label(column + 3, row, fromDate, cellFormat_16);
            wsheet.addCell(fromDateL);

            Label to = new Label(column + 4, row, "TO", cellFormat_16);
            wsheet.addCell(to);

            Label toDateL = new Label(column + 5, row, toDate, cellFormat_16);
            wsheet.addCell(toDateL);

            row++;

            // Heading Sale/Purchase
            Label Title = new Label(column + 4, row, transactionType, cellFormat_16);
            wsheet.addCell(Title);

            row = row + 2;

            WritableFont cellFont_Part = new WritableFont(WritableFont.TIMES, 12);
            WritableCellFormat cellFormat_Part = new WritableCellFormat(cellFont_Part);
            cellFormat_Part.setBorder(Border.ALL, BorderLineStyle.THIN);

            Label SrNo_label = new Label(column, row, "Sr.No", cellFormat_Part);
            wsheet.addCell(SrNo_label);
            Label parti = new Label(column + 1, row, "Particulars", cellFormat_Part);
            wsheet.addCell(parti);
            Label invoice_No = new Label(column + 2, row, "VCH No", cellFormat_Part);
            wsheet.addCell(invoice_No);
            Label quantity = new Label(column + 3, row, "Quantity", cellFormat_Part);
            wsheet.addCell(quantity);
            Label Basic_Amount = new Label(column + 4, row, "Amount", cellFormat_Part);
            wsheet.addCell(Basic_Amount);
            Label ex_Amt = new Label(column + 5, row, "Ex Amt", cellFormat_Part);
            wsheet.addCell(ex_Amt);
            Label ed_Amt = new Label(column + 6, row, "Ed Amt", cellFormat_Part);
            wsheet.addCell(ed_Amt);
            Label h_Ed_Amt = new Label(column + 7, row, "H_Ed Amt", cellFormat_Part);
            wsheet.addCell(h_Ed_Amt);
            Label vat_Amt = new Label(column + 8, row, "VAT Amount", cellFormat_Part);
            wsheet.addCell(vat_Amt);
            Label cst_Amt = new Label(column + 9, row, "CST Amount", cellFormat_Part);
            wsheet.addCell(cst_Amt);
            Label total_Amount = new Label(column + 10, row, "Final Amount", cellFormat_Part);
            wsheet.addCell(total_Amount);

            row++;
            int srnoCount = 0;
            srnoCount++;

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

            for (AdvanceSaleReportDTO SaleReportDTO : advanceSaleReportDTOWithFinalValue.getAdvanceSaleReportDTOList()) {

                jxl.write.Number sr_no = new jxl.write.Number(column, row, srnoCount, cellFormat_Part);
                wsheet.addCell(sr_no);
                jxl.write.Label partyname = new jxl.write.Label(column + 1, row, SaleReportDTO.getParticular(), cellFormat_Part);
                wsheet.addCell(partyname);

                jxl.write.Label invoice_No_data = new jxl.write.Label(column + 2, row, SaleReportDTO.getVch_No(), cellFormat_Part);
                wsheet.addCell(invoice_No_data);

                jxl.write.Label quantity_data = new jxl.write.Label(column + 3, row, SaleReportDTO.getQuantity(), cellFormat_Part);
                wsheet.addCell(quantity_data);

//                jxl.write.Number total_Basic = new jxl.write.Number(column + 4, row, Double.parseDouble(SaleReportDTO.getTotal_Basic_Amount()), cellFormat_Part);
                jxl.write.Label total_Basic = new jxl.write.Label(column + 4, row, SaleReportDTO.getTotal_Basic_Amount(), cellFormat_Part);
                wsheet.addCell(total_Basic);

//                jxl.write.Number ex_Amt_data = new jxl.write.Number(column + 5, row, Double.parseDouble(SaleReportDTO.getEx_Amt()), cellFormat_Part);
                jxl.write.Label ex_Amt_data = new jxl.write.Label(column + 5, row, SaleReportDTO.getEx_Amt(), cellFormat_Part);
                wsheet.addCell(ex_Amt_data);

//                jxl.write.Number ed_Amt_Data = new jxl.write.Number(column + 6, row, Double.parseDouble(SaleReportDTO.getEd_Amt()), cellFormat_Part);
                jxl.write.Label ed_Amt_Data = new jxl.write.Label(column + 6, row, SaleReportDTO.getEd_Amt(), cellFormat_Part);
                wsheet.addCell(ed_Amt_Data);

//                jxl.write.Number h_ed_Amt_Data = new jxl.write.Number(column + 7, row, Double.parseDouble(SaleReportDTO.getH_Ed_Amt()), cellFormat_Part);
                jxl.write.Label h_ed_Amt_Data = new jxl.write.Label(column + 7, row, SaleReportDTO.getH_Ed_Amt(), cellFormat_Part);
                wsheet.addCell(h_ed_Amt_Data);

//                jxl.write.Number vat_Amt_Data = new jxl.write.Number(column + 8, row, Double.parseDouble(SaleReportDTO.getVat_Amt()), cellFormat_Part);
                jxl.write.Label vat_Amt_Data = new jxl.write.Label(column + 8, row, SaleReportDTO.getVat_Amt(), cellFormat_Part);
                wsheet.addCell(vat_Amt_Data);

//                jxl.write.Number cst_Data = new jxl.write.Number(column + 9, row, Double.parseDouble(SaleReportDTO.getCst_Amt()), cellFormat_Part);
                jxl.write.Label cst_Data = new jxl.write.Label(column + 9, row, SaleReportDTO.getCst_Amt(), cellFormat_Part);
                wsheet.addCell(cst_Data);

                jxl.write.Label total_Amount_Data = new jxl.write.Label(column + 10, row, SaleReportDTO.getTotal_Final_Amount(), cellFormat_Part);
                wsheet.addCell(total_Amount_Data);


                row++;
                srnoCount++;
            }


            jxl.write.Label total_Label = new jxl.write.Label(column + 3, row, "Total", cellFormat);
            jxl.write.Number final_basic_total = new jxl.write.Number(column + 4, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Basic_Amount()), cellFormat);
            jxl.write.Number final_Quantity_total = new jxl.write.Number(column + 3, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Quantity()), cellFormat);
            jxl.write.Number final_Ex_total = new jxl.write.Number(column + 5, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Ex_Amount()), cellFormat);
            jxl.write.Number final_Ed_Amount = new jxl.write.Number(column + 6, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Ed_Amount()), cellFormat);
            jxl.write.Number final_H_Ed_Amount = new jxl.write.Number(column + 7, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_H_Ed_Amount()), cellFormat);
            jxl.write.Number final_Vat_Amount = new jxl.write.Number(column + 8, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Vat_Amount()), cellFormat);
            jxl.write.Number final_Cst_Amount = new jxl.write.Number(column + 9, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Cst_Amount()), cellFormat);
            jxl.write.Number final_Amount_Total = new jxl.write.Number(column + 10, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Amount()), cellFormat);

            wsheet.addCell(total_Label);
            wsheet.addCell(final_basic_total);
            wsheet.addCell(final_Quantity_total);
            wsheet.addCell(final_Ex_total);
            wsheet.addCell(final_Ed_Amount);
            wsheet.addCell(final_H_Ed_Amount);
            wsheet.addCell(final_Vat_Amount);
            wsheet.addCell(final_Cst_Amount);
            wsheet.addCell(final_Amount_Total);

            wworkbook.write();
            wworkbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void generatePurchaseReportExcel(String transactionType, AdvanceSaleReportDTOWithFinalValue advanceSaleReportDTOWithFinalValue, String path, String fromDate, String toDate) throws Exception {
        try {
            int row = 2;
            int column = 1;
            System.out.println("PAth ============ " + path);
            WritableWorkbook wworkbook;
            wworkbook = Workbook.createWorkbook(new File(path + ".xls"));
            WritableSheet wsheet = wworkbook.createSheet("First", 0);

            // set width to column
            wsheet.setColumnView(row, 40);
            wsheet.setColumnView(row + 1, 12);
            wsheet.setColumnView(row + 2, 12);
            wsheet.setColumnView(row + 3, 18);
            wsheet.setColumnView(row + 4, 18);
            wsheet.setColumnView(row + 5, 18);
            wsheet.setColumnView(row + 6, 18);
            wsheet.setColumnView(row + 7, 18);
            wsheet.setColumnView(row + 8, 18);
            wsheet.setColumnView(row + 9, 18);
            wsheet.setColumnView(row + 10, 18);
            wsheet.setColumnView(row + 11, 18);

            WritableFont cellFont_16 = new WritableFont(WritableFont.TIMES, 16, WritableFont.BOLD);
//            cellFont.setColour(Colour.BLUE);
            WritableCellFormat cellFormat_16 = new WritableCellFormat(cellFont_16);

            // Heading Date 
            Label fromDateL = new Label(column + 3, row, fromDate, cellFormat_16);
            wsheet.addCell(fromDateL);

            Label to = new Label(column + 4, row, "TO", cellFormat_16);
            wsheet.addCell(to);

            Label toDateL = new Label(column + 5, row, toDate, cellFormat_16);
            wsheet.addCell(toDateL);

            row++;

            // Heading Sale/Purchase
            Label Title = new Label(column + 4, row, transactionType, cellFormat_16);
            wsheet.addCell(Title);

            row = row + 2;

            WritableFont cellFont_Part = new WritableFont(WritableFont.TIMES, 12);
            WritableCellFormat cellFormat_Part = new WritableCellFormat(cellFont_Part);
            cellFormat_Part.setBorder(Border.ALL, BorderLineStyle.THIN);

            Label SrNo_label = new Label(column, row, "Sr.No", cellFormat_Part);
            wsheet.addCell(SrNo_label);
            Label parti = new Label(column + 1, row, "Particulars", cellFormat_Part);
            wsheet.addCell(parti);

            Label reference = new Label(column + 2, row, "Reference", cellFormat_Part);
            wsheet.addCell(reference);

            Label invoice_No = new Label(column + 3, row, "VCH No", cellFormat_Part);
            wsheet.addCell(invoice_No);
            Label quantity = new Label(column + 4, row, "Quantity", cellFormat_Part);
            wsheet.addCell(quantity);
            Label Basic_Amount = new Label(column + 5, row, "Amount", cellFormat_Part);
            wsheet.addCell(Basic_Amount);
            Label ex_Amt = new Label(column + 6, row, "Ex Amt", cellFormat_Part);
            wsheet.addCell(ex_Amt);
            Label ed_Amt = new Label(column + 7, row, "Ed Amt", cellFormat_Part);
            wsheet.addCell(ed_Amt);
            Label h_Ed_Amt = new Label(column + 8, row, "H_Ed Amt", cellFormat_Part);
            wsheet.addCell(h_Ed_Amt);
            Label vat_Amt = new Label(column + 9, row, "VAT Amount", cellFormat_Part);
            wsheet.addCell(vat_Amt);
            Label cst_Amt = new Label(column + 10, row, "CST Amount", cellFormat_Part);
            wsheet.addCell(cst_Amt);
            Label total_Amount = new Label(column + 11, row, "Final Amount", cellFormat_Part);
            wsheet.addCell(total_Amount);

            row++;
            int srnoCount = 0;
            srnoCount++;

            WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

            for (AdvanceSaleReportDTO SaleReportDTO : advanceSaleReportDTOWithFinalValue.getAdvanceSaleReportDTOList()) {

                jxl.write.Number sr_no = new jxl.write.Number(column, row, srnoCount, cellFormat_Part);
                wsheet.addCell(sr_no);
                
                jxl.write.Label partyname = new jxl.write.Label(column + 1, row, SaleReportDTO.getParticular(), cellFormat_Part);
                wsheet.addCell(partyname);

                jxl.write.Label reference_data = new jxl.write.Label(column + 2, row, SaleReportDTO.getPurchase_Reference(), cellFormat_Part);
                wsheet.addCell(reference_data);
                
                jxl.write.Label invoice_No_data = new jxl.write.Label(column + 3, row, SaleReportDTO.getVch_No(), cellFormat_Part);
                wsheet.addCell(invoice_No_data);

                jxl.write.Label quantity_data = new jxl.write.Label(column + 4, row, SaleReportDTO.getQuantity(), cellFormat_Part);
                wsheet.addCell(quantity_data);

//                jxl.write.Number total_Basic = new jxl.write.Number(column + 4, row, Double.parseDouble(SaleReportDTO.getTotal_Basic_Amount()), cellFormat_Part);
                jxl.write.Label total_Basic = new jxl.write.Label(column + 5, row, SaleReportDTO.getTotal_Basic_Amount(), cellFormat_Part);
                wsheet.addCell(total_Basic);

//                jxl.write.Number ex_Amt_data = new jxl.write.Number(column + 5, row, Double.parseDouble(SaleReportDTO.getEx_Amt()), cellFormat_Part);
                jxl.write.Label ex_Amt_data = new jxl.write.Label(column + 6, row, SaleReportDTO.getEx_Amt(), cellFormat_Part);
                wsheet.addCell(ex_Amt_data);

//                jxl.write.Number ed_Amt_Data = new jxl.write.Number(column + 6, row, Double.parseDouble(SaleReportDTO.getEd_Amt()), cellFormat_Part);
                jxl.write.Label ed_Amt_Data = new jxl.write.Label(column + 7, row, SaleReportDTO.getEd_Amt(), cellFormat_Part);
                wsheet.addCell(ed_Amt_Data);

//                jxl.write.Number h_ed_Amt_Data = new jxl.write.Number(column + 7, row, Double.parseDouble(SaleReportDTO.getH_Ed_Amt()), cellFormat_Part);
                jxl.write.Label h_ed_Amt_Data = new jxl.write.Label(column + 8, row, SaleReportDTO.getH_Ed_Amt(), cellFormat_Part);
                wsheet.addCell(h_ed_Amt_Data);

//                jxl.write.Number vat_Amt_Data = new jxl.write.Number(column + 8, row, Double.parseDouble(SaleReportDTO.getVat_Amt()), cellFormat_Part);
                jxl.write.Label vat_Amt_Data = new jxl.write.Label(column + 9, row, SaleReportDTO.getVat_Amt(), cellFormat_Part);
                wsheet.addCell(vat_Amt_Data);

//                jxl.write.Number cst_Data = new jxl.write.Number(column + 9, row, Double.parseDouble(SaleReportDTO.getCst_Amt()), cellFormat_Part);
                jxl.write.Label cst_Data = new jxl.write.Label(column + 10, row, SaleReportDTO.getCst_Amt(), cellFormat_Part);
                wsheet.addCell(cst_Data);

                jxl.write.Label total_Amount_Data = new jxl.write.Label(column + 11, row, SaleReportDTO.getTotal_Final_Amount(), cellFormat_Part);
                wsheet.addCell(total_Amount_Data);


                row++;
                srnoCount++;
            }


            jxl.write.Label total_Label = new jxl.write.Label(column + 3, row, "Total", cellFormat);
            jxl.write.Number final_basic_total = new jxl.write.Number(column + 5, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Basic_Amount()), cellFormat);
            jxl.write.Number final_Quantity_total = new jxl.write.Number(column + 4, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Quantity()), cellFormat);
            jxl.write.Number final_Ex_total = new jxl.write.Number(column + 6, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Ex_Amount()), cellFormat);
            jxl.write.Number final_Ed_Amount = new jxl.write.Number(column + 7, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Ed_Amount()), cellFormat);
            jxl.write.Number final_H_Ed_Amount = new jxl.write.Number(column + 8, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_H_Ed_Amount()), cellFormat);
            jxl.write.Number final_Vat_Amount = new jxl.write.Number(column + 9, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Vat_Amount()), cellFormat);
            jxl.write.Number final_Cst_Amount = new jxl.write.Number(column + 10, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Cst_Amount()), cellFormat);
            jxl.write.Number final_Amount_Total = new jxl.write.Number(column + 11, row, Double.parseDouble(advanceSaleReportDTOWithFinalValue.getFinal_Total_Amount()), cellFormat);

            wsheet.addCell(total_Label);
            wsheet.addCell(final_basic_total);
            wsheet.addCell(final_Quantity_total);
            wsheet.addCell(final_Ex_total);
            wsheet.addCell(final_Ed_Amount);
            wsheet.addCell(final_H_Ed_Amount);
            wsheet.addCell(final_Vat_Amount);
            wsheet.addCell(final_Cst_Amount);
            wsheet.addCell(final_Amount_Total);

            wworkbook.write();
            wworkbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

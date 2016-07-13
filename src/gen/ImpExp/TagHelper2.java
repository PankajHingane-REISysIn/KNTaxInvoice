package gen.ImpExp;

import exception.InvalidFileException;
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
import gen.accountvoucher.sale.SaleDAO;
import gen.accountvoucher.sale.SaleDTO;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.LedgerTransactionDTO;
import gen.dto.StockItemTransactionDTO;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TagHelper2 {

    //static String name, alias, under;
    static String name, alias, under, unit, address, contact, email, incometax, saletax, type, symbol, formalName, NoOfDecimalPlaces;
    static Double length, width, thickness, rate, openingBalence;
    List<GroupDTO> GroupDTOList = new ArrayList<GroupDTO>();
    List<LedgerDTO> LedgerDTOList = new ArrayList<LedgerDTO>();
    static List<UnitOfMeasureDTO> UnitOfMeasureDTOList = new ArrayList<UnitOfMeasureDTO>();
    List<StockGroupDTO> StockGroupDTOList = new ArrayList<StockGroupDTO>();
    List<StockItemDTO> StockItemDTOList = new ArrayList<StockItemDTO>();
    List<SaleDTO> SaleFormDTOList = new ArrayList<SaleDTO>();
    static int controlConstantImpExp = 0;
    static int dialogButton = JOptionPane.YES_NO_OPTION;
    static int dialogResult = 1;

    /**
     * ****************************************************
     */
    public static Document getDocument(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        return doc;
    }
    /*
     * @Following getGroupDTOList() will IMPORT all the xml file records of Groups
     *  @Authors: Sudeep
     *  @Date   : 1-04-2013
     */

    public static Map<String, Object> importXML(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, Exception {
        Map<String, Map<String, Object>> returnMapFromXml = new HashMap<String, Map<String, Object>>();
        List<Object> object_List = new ArrayList<Object>();
        Map<String, Object> return_map = new HashMap<String, Object>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("We Got The First Tag Name---->>" + doc.getDocumentElement().getNodeName());

        String importValue = doc.getDocumentElement().getNodeName();//checkImportValueStatus();
        System.out.println("value returned by checkImportValueStatus()---->>>" + importValue);

        if (importValue.equalsIgnoreCase("AdSuMuDi")) {
            System.out.println("Ledger ---------- " + doc.getElementsByTagName("LedgerList"));
            NodeList nodeList1 = doc.getElementsByTagName("Group");
            NodeList nodeList2 = doc.getElementsByTagName("LedgerList");

            System.out.println("Node List 0 length  ------------ " + nodeList2.getLength());
            System.out.println("Node List 0 ------------ " + nodeList2.item(0));
            System.out.println("Node List 0 ------------ " + nodeList2.toString());

            NodeList nodeList3 = doc.getElementsByTagName("UnitOfMeasureList");
            NodeList nodeList4 = doc.getElementsByTagName("StockGroupList");
            NodeList nodeList5 = doc.getElementsByTagName("StockItemList");
            NodeList nodeList6 = doc.getElementsByTagName("SaleVoucherList");
            NodeList nodeList7 = doc.getElementsByTagName("PurchaseVoucherList");
            NodeList nodeList8 = doc.getElementsByTagName("ReceiptVoucherList");
            NodeList nodeList9 = doc.getElementsByTagName("PaymentVoucherList");
            NodeList nodeList10 = doc.getElementsByTagName("ChalanVoucherList");
            NodeList nodeList11 = doc.getElementsByTagName("ContraVoucherList");



//	    NodeList nodeList6 = doc.getElementsByTagName("SaleVoucher");
//	    NodeList nodeList7 = doc.getElementsByTagName("PurchaseVoucher");
//	    NodeList nodeList8 = doc.getElementsByTagName("ReceiptVoucher");
//	    NodeList nodeList9 = doc.getElementsByTagName("PaymentVoucher");
//	    NodeList nodeList10 = doc.getElementsByTagName("Chalan");
//	    NodeList nodeList11 = doc.getElementsByTagName("ContraVoucherList");

//	    System.err.println("nodeList6=======>> " + nodeList6 + "=======>> ");

            if (nodeList1 != null && nodeList1.getLength() != 0) {
//            groupDTOList = getGroupDTOList(fXmlFile);
//            System.out.println("getGroupDTOList()called From importXML()---->" + groupDTOList.size());
//            insertGroup(groupDTOList);
            }
            if (nodeList2 != null && nodeList2.getLength() != 0) {
                System.out.println("Ledger -------------- ");
                insertLedgerXMLToDatabase(fXmlFile);
            }
            if (nodeList3 != null && nodeList3.getLength() != 0) {
                //insertUnitOfMeasureXMLToDatabase(fXmlFile);
            }
            if (nodeList4 != null && nodeList4.getLength() != 0) {
//            stockGroupDTOList = getStockGroupDTOList(fXmlFile);
//            System.out.println("getStockGroupDTOList()called From importXML()");
//            insertStockGroup(stockGroupDTOList);
            }
            if (nodeList5 != null && nodeList5.getLength() != 0) {
                insertStockItemXMLToDatabase(fXmlFile);
            }
            if (nodeList6 != null && nodeList6.getLength() != 0) {
                returnMapFromXml.put(gen.dto.Constants.SALE_TYPE_INDEX.toString(), insertSaleXMLToDatabase(fXmlFile));
            }
            if (nodeList7 != null && nodeList7.getLength() != 0) {
//		insertPurchaseXMLToDatabase(fXmlFile);
                returnMapFromXml.put(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString(), insertPurchaseXMLToDatabase(fXmlFile));
            }
            if (nodeList8 != null && nodeList8.getLength() != 0) {
//		insertReceiptXMLToDatabase(fXmlFile);
                returnMapFromXml.put(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString(), insertReceiptXMLToDatabase(fXmlFile));
            }
            if (nodeList9 != null && nodeList9.getLength() != 0) {
                returnMapFromXml.put(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString(), insertPaymentXMLToDatabase(fXmlFile));
//		insertPaymentXMLToDatabase(fXmlFile);
            }
            System.out.println("nodeList10.getLength() --------------- " + nodeList10.getLength());
            if (nodeList10 != null && nodeList10.getLength() != 0) {
                System.out.println("Chalan-----------");
                returnMapFromXml.put(gen.dto.Constants.CHALAN_TYPE_INDEX.toString(), insertChalanXMLToDatabase(fXmlFile));
//		insertChalanXMLToDatabase(fXmlFile);
            }
            if (nodeList11 != null && nodeList11.getLength() != 0) {
//		insertContraXMLToDatabase(fXmlFile);
                returnMapFromXml.put(gen.dto.Constants.CONTRA_TYPE_INDEX.toString(), insertContraXMLToDatabase(fXmlFile));
            }


            Map<String, Map<String, String>> inputParam = new HashMap<String, Map<String, String>>();
            for (String voucherType : returnMapFromXml.keySet()) {
                System.out.println("Voucher Type ---------  " + voucherType);
                inputParam.put(voucherType, new HashMap<String, String>());
                for (String receiptNo : returnMapFromXml.get(voucherType).keySet()) {
                    System.out.println("Receipt  no = " + receiptNo + " With Voucher Type-------------  " + receiptNo);
                    if(voucherType.equalsIgnoreCase(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
                        inputParam.get(voucherType).put(receiptNo, ( (SaleDTO)returnMapFromXml.get(voucherType).get(receiptNo)).getDate() );
                    } else if(voucherType.equalsIgnoreCase(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString())) {
                        inputParam.get(voucherType).put(receiptNo, ( (PurchaseDTO)returnMapFromXml.get(voucherType).get(receiptNo)).getDate() );
                    } else if(voucherType.equalsIgnoreCase(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString())) {
                        inputParam.get(voucherType).put(receiptNo, ( (ReceiptDTO)returnMapFromXml.get(voucherType).get(receiptNo)).getDate() );
                    } else if(voucherType.equalsIgnoreCase(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString())) {
                        inputParam.get(voucherType).put(receiptNo, ( (PaymentDTO)returnMapFromXml.get(voucherType).get(receiptNo)).getDate() );
                    }
                    
                }
            }

            Map<String, Set<String>> mapValuePresent = gen.ImpExp.TagsHelper1.getDatabaseReceiptNoWithDate(inputParam);

            object_List.add(returnMapFromXml);
            object_List.add(mapValuePresent);
            object_List.add(gen.ImpExp.EnumAction.OVERRIDE);

            if (mapValuePresent.isEmpty()) {
                Map<String, Set<String>> user_Selected_ReceiptNoMap = new HashMap<String, Set<String>>();
                Map<String, Set<String>> mapValueDeletion = getTrans_id(mapValuePresent);
                if (mapValueDeletion.size() > 0 && mapValueDeletion != null) {
                    deleteTransaction(mapValueDeletion);
                }
                insert_All_VoucherTransaction(returnMapFromXml, mapValuePresent, gen.ImpExp.EnumAction.OVERRIDE, user_Selected_ReceiptNoMap);
            } else {
                return_map.put("returnMapFromXml", returnMapFromXml);
                return_map.put("mapValuePresent", mapValuePresent);
                return_map.put("gen.ImpExp.EnumAction.OVERRIDE", gen.ImpExp.EnumAction.OVERRIDE);
            }

        } else if (importValue.equalsIgnoreCase("GroupList")) {
            insertGroupXMLToDatabase(fXmlFile);
        } else if (importValue.equalsIgnoreCase("LedgerList")) {
            insertLedgerXMLToDatabase(fXmlFile);
        } else if (importValue.equalsIgnoreCase("StockGroupList")) {
            insertStockGroupXMLToDatabase(fXmlFile);
        } else if (importValue.equalsIgnoreCase("StockItemList")) {
            insertStockItemXMLToDatabase(fXmlFile);
        } else if (importValue.equalsIgnoreCase("UnitOfMeasureList")) {
            insertUnitOfMeasureXMLToDatabase(fXmlFile);
        } else if (importValue.equalsIgnoreCase("ENVELOPE")) {

            System.out.println("In ENVELOPE ---------------");
            String voucherType = "";
            NodeList sale = null;
            NodeList purchase = null;
            NodeList receipt = null;
            NodeList payment = null;
            NodeList contra = null;
            NodeList nodeList = doc.getElementsByTagName("VOUCHER");
            System.out.println("TTTTTT              " + doc.getElementsByTagName("VOUCHER").getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
                System.out.println("Element 5/12/2013--->>>" + e.getTagName());
                if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("payment")) {
                    payment = e.getChildNodes();
                }
                if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("receipt")) {
                    receipt = e.getChildNodes();
                }
                if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("sales")) {
                    sale = e.getChildNodes();
                }
                if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("purchase")) {
                    purchase = e.getChildNodes();
                }
                System.out.println("TTTTTT              " + e.getAttribute("VCHTYPE").equalsIgnoreCase("contra"));
                if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("contra")) {
                    contra = e.getChildNodes();
                }
            }


            if (sale != null && sale.getLength() != 0) {
                // insertTallySaleXMLToDatabase(fXmlFile);
                System.out.println("In Sale ------------------------------- ");
                returnMapFromXml.put(gen.dto.Constants.SALE_TYPE_INDEX.toString(), insertTallySaleXMLToDatabase(fXmlFile));

            }
            if (purchase != null && purchase.getLength() != 0) {
                //insertTallyPurchaseXMLToDatabase(fXmlFile);
                System.out.println("In Purchase ------------------------------- ");
                returnMapFromXml.put(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString(), insertTallyPurchaseXMLToDatabase(fXmlFile));

            }
            if (receipt != null && receipt.getLength() != 0) {
                // insertTallyReceiptXMLToDatabase(fXmlFile);
                System.out.println("In Receipt ------------------------------- ");
                returnMapFromXml.put(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString(), insertTallyReceiptXMLToDatabase(fXmlFile));

            }
            if (payment != null && payment.getLength() != 0) {
//                insertTallyPaymentXMLToDatabase(fXmlFile);
                System.out.println("In Payment -------------------------------");
                returnMapFromXml.put(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString(), insertTallyPaymentXMLToDatabase(fXmlFile));
            }

            if (contra != null && contra.getLength() != 0) {
                System.out.println("In Contra -------------------------------");
                returnMapFromXml.put(gen.dto.Constants.CONTRA_TYPE_INDEX.toString(), insertTallyContraXMLToDatabase(fXmlFile));
            }


            Map<String, Set<String>> inputParam = new HashMap<String, Set<String>>();
            for (String voucherType1 : returnMapFromXml.keySet()) {
                System.out.println("Voucher Type ---------  " + voucherType1);
                System.out.println("Key set --------------  " + returnMapFromXml.get(voucherType1).keySet());
                inputParam.put(voucherType1, new HashSet<String>());
                for (String receiptNo : returnMapFromXml.get(voucherType1).keySet()) {
                    System.out.println("Receipt  no = " + receiptNo + " With Voucher Type-------------  " + receiptNo);
                    inputParam.get(voucherType1).add(receiptNo);
                }
            }

            Map<String, Set<String>> mapValuePresent = gen.ImpExp.TagsHelper1.getDatabaseReceiptNo(inputParam);

            object_List.add(returnMapFromXml);
            object_List.add(mapValuePresent);
            object_List.add(gen.ImpExp.EnumAction.OVERRIDE);

            if (mapValuePresent.isEmpty()) {
                Map<String, Set<String>> user_Selected_ReceiptNoMap = new HashMap<String, Set<String>>();
                Map<String, Set<String>> mapValueDeletion = getTrans_id(mapValuePresent);
                if (mapValueDeletion.size() > 0 && mapValueDeletion != null) {
                    deleteTransaction(mapValueDeletion);
                }
                System.out.println("mapValuePresent       is Empty ------------");
                insert_All_VoucherTransaction(returnMapFromXml, mapValuePresent, gen.ImpExp.EnumAction.OVERRIDE, user_Selected_ReceiptNoMap);
            } else {
                System.out.println("mapValuePresent       is NOT Empty ------------");
                return_map.put("returnMapFromXml", returnMapFromXml);
                return_map.put("mapValuePresent", mapValuePresent);
                return_map.put("gen.ImpExp.EnumAction.OVERRIDE", gen.ImpExp.EnumAction.OVERRIDE);
            }

        } else {
            throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
        }
        return return_map;
    }

    public static List<GroupDTO> getGroupDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<GroupDTO> groupDTOList = new ArrayList<GroupDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();


//	NodeList nodeList = doc.getElementsByTagName("Group");

        Node groupNode = null;
        NodeList groupNodeList = null;
        if (doc.getElementsByTagName("GroupList") != null) {
            groupNode = doc.getElementsByTagName("GroupList").item(0);
            groupNodeList = groupNode.getChildNodes();
        }

        if (groupNode != null && groupNodeList != null) {
            for (int j = 0; j < groupNodeList.getLength(); j++) {
                Node node = groupNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());
                System.out.println("\nNode Length:" + groupNodeList.getLength());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    GroupDTO groupDTO = new GroupDTO();
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("ID").item(0) != null) {
                        groupDTO.setGroup_id(eElement.getElementsByTagName("ID").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Name").item(0) != null) {
                        groupDTO.setGroup_Name(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Alias").item(0) != null) {
                        groupDTO.setGroup_Alias(eElement.getElementsByTagName("Alias").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("UnderGroupID").item(0) != null) {
                        groupDTO.setGroup_Under(eElement.getElementsByTagName("UnderGroupID").item(0).getTextContent());
                    }

                    name = groupDTO.getGroup_Name().toString();
                    alias = groupDTO.getGroup_Alias().toString();
                    under = groupDTO.getGroup_Under().toString();
                    String id = groupDTO.getGroup_id().toString();

                    System.out.println("getGroupDTOList--" + name);
                    System.out.println("getGroupDTOList--" + alias);
                    System.out.println("getGroupDTOList--" + under);
                    System.out.println("getGroupDTOList--" + id);

                    groupDTOList.add(groupDTO);

                }
            }
        }
        System.out.println("ReadXMLFile-->>getGroupDTOList()-->>groupDTOList.size()-->>" + groupDTOList.size());

        return groupDTOList;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public static Map<String, GroupDTO> convertGroupTOMap(List<GroupDTO> groupDTOList) {
        Map<String, GroupDTO> groupDTOMap = new HashMap<String, GroupDTO>();
        for (GroupDTO groupDTO : groupDTOList) {
            groupDTOMap.put(groupDTO.getGroup_Name(), groupDTO);
        }
        return groupDTOMap;
    }

    public static Set<String> convertGroupTOSet(List<GroupDTO> groupDTOList) {
        Set<String> groupDTOSet = new TreeSet<String>();
        for (GroupDTO groupDTO : groupDTOList) {
            groupDTOSet.add(groupDTO.getGroup_Name());
        }
        return groupDTOSet;
    }

    public static Boolean insertGroupXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        List<GroupDTO> groupDTOEntity1 = new ArrayList<GroupDTO>();
        List<GroupDTO> groupDTOInsert = new ArrayList<GroupDTO>();

        List<GroupDTO> groupDTOList = getGroupDTOList(xmlFile);
        Map<String, GroupDTO> groupDTOMap = convertGroupTOMap(groupDTOList);
        Set<String> gruopNameSet = convertGroupTOSet(groupDTOList);

        List<GroupDTO> groupDTOEntityList = GroupDAO.getGroupListForExportImport(gruopNameSet, Constants.GROUP_NAME);
        Map<String, GroupDTO> groupDTOEntityMap = convertGroupTOMap(groupDTOEntityList);

        Set<String> entityKeySet = groupDTOEntityMap.keySet();
        Set<String> KeySet = groupDTOEntityMap.keySet();

        for (String key : KeySet) {
            if (!entityKeySet.contains(key)) {
                groupDTOEntity1.add(groupDTOMap.get(key));
            }
        }

        for (GroupDTO groupDTO : groupDTOEntity1) {
            groupDTO.setGroup_Under(TagsHelper1.groupNameToIDMapping.get(groupDTO.getGroup_Under()));
            groupDTOInsert.add(groupDTO);
        }

        insertGroup(groupDTOInsert);
        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Map<String, LedgerDTO> convertLedgerTOMap(List<LedgerDTO> ledgerDTOList) {
        Map<String, LedgerDTO> ledgerDTOMap = new HashMap<String, LedgerDTO>();
        for (LedgerDTO LedgerDTO : ledgerDTOList) {
            ledgerDTOMap.put(LedgerDTO.getLedger_Name(), LedgerDTO);
        }
        return ledgerDTOMap;
    }

    public static Set<String> convertLedgerTOSet(List<LedgerDTO> LedgerDTOList) {
        Set<String> LedgerDTOSet = new TreeSet<String>();
        for (LedgerDTO LedgerDTO : LedgerDTOList) {
            LedgerDTOSet.add(LedgerDTO.getLedger_Name());
        }
        return LedgerDTOSet;
    }

    public static Boolean insertLedgerXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        TagsHelper1.loadGroupNameToIDMap();

        List<LedgerDTO> LedgerDTOEntity1 = new ArrayList<LedgerDTO>();
        List<LedgerDTO> LedgerDTOInsert = new ArrayList<LedgerDTO>();

        List<LedgerDTO> LedgerDTOList = getLedgerDTOList(xmlFile);
        Map<String, LedgerDTO> LedgerDTOMap = convertLedgerTOMap(LedgerDTOList);
        Set<String> gruopNameSet = convertLedgerTOSet(LedgerDTOList);

        List<LedgerDTO> LedgerDTOEntityList = LedgerDAO.getLedgerList(gruopNameSet, Constants.LEDGER_NAME);
        Map<String, LedgerDTO> LedgerDTOEntityMap = convertLedgerTOMap(LedgerDTOEntityList);

        Set<String> entityKeySet = LedgerDTOEntityMap.keySet();
        Set<String> KeySet = LedgerDTOMap.keySet();

        for (String key : KeySet) {
            if (!entityKeySet.contains(key)) {
                System.out.println("If Key Set ---------------------  " + LedgerDTOMap.get(key));
                System.out.println("If Key ---------------------  " + key);
                LedgerDTOEntity1.add(LedgerDTOMap.get(key));
            }
        }

        for (LedgerDTO LedgerDTO : LedgerDTOEntity1) {
            System.out.println("UUUUUUUU           " + LedgerDTO.getLedger_Under());
            LedgerDTO.setLedger_Under(TagsHelper1.groupNameToIDMapping.get(LedgerDTO.getLedger_Under()));
            System.out.println("Key VAlue w s ------------" + TagsHelper1.groupNameToIDMapping.keySet());
            System.out.println("Under Filed ------------- " + TagsHelper1.groupNameToIDMapping.get(LedgerDTO.getLedger_Under()));
            LedgerDTO = ImpExpUtil.valiDateAndRepairLedgerDTO(LedgerDTO);
            LedgerDTOInsert.add(LedgerDTO);
        }
        System.out.println("LedgerDTOInsert-->>" + LedgerDTOInsert.size());
        System.out.println("LedgerDTOEntity1-->>" + LedgerDTOEntity1.size());
        System.out.println("LedgerDTOEntityList-->>" + LedgerDTOEntityList.size());
        System.out.println("LedgerDTOEntityMap-->>" + LedgerDTOEntityMap.size());
        System.out.println("LedgerDTOInsert-->>" + LedgerDTOInsert.size());
        System.out.println("entityKeySet-->>" + entityKeySet.size());
        System.out.println("KeySet-->>" + KeySet.size());
        System.out.println("LedgerDTOList-->>" + LedgerDTOList.size());
        System.out.println("LedgerDTOMap-->>" + LedgerDTOMap.size());
        System.out.println("gruopNameSet-->>" + gruopNameSet.size());

        if (LedgerDTOInsert.size() > 0) {
            insertLedger(LedgerDTOInsert);
        } else {
            System.out.println("UUUUUUUUUUUUUUUUUUU");
        }
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

    public static Map<String, StockGroupDTO> convertStockGroupTOMap(List<StockGroupDTO> StockGroupDTOList) {
        Map<String, StockGroupDTO> StockGroupDTOMap = new HashMap<String, StockGroupDTO>();
        for (StockGroupDTO StockGroupDTO : StockGroupDTOList) {
            StockGroupDTOMap.put(StockGroupDTO.getStockGroupName(), StockGroupDTO);
        }
        return StockGroupDTOMap;
    }

    public static Set<String> convertStockGroupTOSet(List<StockGroupDTO> StockGroupDTOList) {
        Set<String> StockGroupDTOSet = new TreeSet<String>();
        for (StockGroupDTO StockGroupDTO : StockGroupDTOList) {
            StockGroupDTOSet.add(StockGroupDTO.getStockGroupName());
        }
        return StockGroupDTOSet;
    }

    public static Boolean insertStockGroupXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        List<StockGroupDTO> StockGroupDTOEntity1 = new ArrayList<StockGroupDTO>();
        List<StockGroupDTO> StockGroupDTOInsert = new ArrayList<StockGroupDTO>();

        List<StockGroupDTO> StockGroupDTOList = getStockGroupDTOList(xmlFile);
        Map<String, StockGroupDTO> StockGroupDTOMap = convertStockGroupTOMap(StockGroupDTOList);
        Set<String> gruopNameSet = convertStockGroupTOSet(StockGroupDTOList);

        List<StockGroupDTO> StockGroupDTOEntityList = StockGroupDAO.getStockGroupList(gruopNameSet, Constants.GROUP_NAME);
        Map<String, StockGroupDTO> StockGroupDTOEntityMap = convertStockGroupTOMap(StockGroupDTOEntityList);

        Set<String> entityKeySet = StockGroupDTOEntityMap.keySet();
        Set<String> KeySet = StockGroupDTOEntityMap.keySet();

        for (String key : KeySet) {
            if (!entityKeySet.contains(key)) {
                StockGroupDTOEntity1.add(StockGroupDTOMap.get(key));
            }
        }

        for (StockGroupDTO StockGroupDTO : StockGroupDTOEntity1) {
            StockGroupDTO.setStockGroupUnder(TagsHelper1.groupNameToIDMapping.get(StockGroupDTO.getStockGroupUnder()));
            StockGroupDTOInsert.add(StockGroupDTO);
        }

        insertStockGroup(StockGroupDTOInsert);
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    public static Map<String, StockItemDTO> convertStockItemTOMap(List<StockItemDTO> StockItemDTOList) {
        Map<String, StockItemDTO> StockItemDTOMap = new HashMap<String, StockItemDTO>();
        for (StockItemDTO StockItemDTO : StockItemDTOList) {
            StockItemDTOMap.put(StockItemDTO.getName(), StockItemDTO);
        }
        return StockItemDTOMap;
    }

    public static Set<String> convertStockItemTOSet(List<StockItemDTO> StockItemDTOList) {
        Set<String> StockItemDTOSet = new TreeSet<String>();
        for (StockItemDTO StockItemDTO : StockItemDTOList) {
            StockItemDTOSet.add(StockItemDTO.getName());
        }
        return StockItemDTOSet;
    }

    public static Boolean insertStockItemXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        TagsHelper1.loadStockGroupNameToIDMap();
        TagsHelper1.loadUnitOfMeasureNameToIDMap();

        List<StockItemDTO> StockItemDTOEntity1 = new ArrayList<StockItemDTO>();
        List<StockItemDTO> StockItemDTOInsert = new ArrayList<StockItemDTO>();

        List<StockItemDTO> StockItemDTOList = getStockItemDTOList(xmlFile);
        Map<String, StockItemDTO> StockItemDTOMap = convertStockItemTOMap(StockItemDTOList);
        Set<String> gruopNameSet = convertStockItemTOSet(StockItemDTOList);

        List<StockItemDTO> StockItemDTOEntityList = StockItemDAO.getStockItemList(gruopNameSet, Constants.STOCK_ITEM_NAME);
        Map<String, StockItemDTO> StockItemDTOEntityMap = convertStockItemTOMap(StockItemDTOEntityList);

        Set<String> entityKeySet = StockItemDTOEntityMap.keySet();
        Set<String> KeySet = StockItemDTOMap.keySet();

        for (String key : KeySet) {
            if (!entityKeySet.contains(key)) {
                StockItemDTOEntity1.add(StockItemDTOMap.get(key));
            }
        }

        for (StockItemDTO StockItemDTO : StockItemDTOEntity1) {
            StockItemDTO.setUnder(TagsHelper1.stockGroupNameToIDMapping.get(StockItemDTO.getUnder()));
            System.out.println("Unit ID ------------------" + StockItemDTO.getUnit());
            System.out.println("Unit ID ------------------" + TagsHelper1.unitOfMeasureNameToIDMapping.get(StockItemDTO.getUnit()));
            StockItemDTO.setUnit(TagsHelper1.unitOfMeasureNameToIDMapping.get(StockItemDTO.getUnit()));
            StockItemDTO = ImpExpUtil.valiDateAndRepairStockItemDTO(StockItemDTO);
            StockItemDTOInsert.add(StockItemDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());
        System.out.println("StockItemDTOInsert-->>" + StockItemDTOInsert.size());
        System.out.println("StockItemDTOEntity1-->>" + StockItemDTOEntity1.size());
        System.out.println("StockItemDTOEntityList-->>" + StockItemDTOEntityList.size());
        System.out.println("StockItemDTOEntityMap-->>" + StockItemDTOEntityMap.size());
        System.out.println("StockItemDTOInsert-->>" + StockItemDTOInsert.size());
        System.out.println("entityKeySet-->>" + entityKeySet.size());
        System.out.println("KeySet-->>" + KeySet.size());
        System.out.println("StockItemDTOList-->>" + StockItemDTOList.size());
        System.out.println("StockItemDTOMap-->>" + StockItemDTOMap.size());
        System.out.println("gruopNameSet-->>" + gruopNameSet.size());

        if (StockItemDTOInsert.size() > 0) {
            insertStockItem(StockItemDTOInsert);
        }
        return true;
    }

    public static Map<String, Object> insertSaleXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();
        TagsHelper1.loadStockItemNameToIDMap();
        gen.ImpExp.TagsHelper1.loadCategaryIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadColorIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadTypeIDToNameMapping();
        gen.ImpExp.TagsHelper1.loadBoardtypeIDToNameMapping();

        Map<String, Object> saleReceiptNoDTOMap = new HashMap<String, Object>();

        List<SaleDTO> saleDTOList = getSaleDTOList(xmlFile);

        for (SaleDTO saleDTO : saleDTOList) {

            saleDTO.setCashLedger(TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getCashLedger()));//casj ledger
            saleDTO.setSaleLedger(TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getSaleLedger()));//sa;e lefdgetr

            List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));

                stockItemTransactionDTO.setCatogary(TagsHelper1.categaryNameToIDMapping.get(stockItemTransactionDTO.getCatogary()));
                stockItemTransactionDTO.setColor(TagsHelper1.colorNameToIDMapping.get(stockItemTransactionDTO.getColor()));
                stockItemTransactionDTO.setType(TagsHelper1.typeNameToIDMapping.get(stockItemTransactionDTO.getType()));
                stockItemTransactionDTO.setBoard_type(TagsHelper1.boardtypeNameToIDMapping.get(stockItemTransactionDTO.getBoard_type()));

                System.out.println(" Taghelper Catogary --------------------" + stockItemTransactionDTO.getCatogary());
                System.out.println("Taghelper Color --------------------" + stockItemTransactionDTO.getColor());
                System.out.println("Taghelper Finish --------------------" + stockItemTransactionDTO.getType());
                System.out.println("Taghelper B Type --------------------" + stockItemTransactionDTO.getBoard_type());



                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            saleDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

            saleDTO = ImpExpUtil.valiDateAndRepairSaleDTO(saleDTO);
            saleReceiptNoDTOMap.put(saleDTO.getReceiptNo().toString(), saleDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());
        return saleReceiptNoDTOMap;


    }

    public static Map<String, Object> insertTallySaleXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();
        TagsHelper1.loadStockItemNameToIDMap();

        System.out.println("");
        Map<String, Object> saleReceiptNoDTOMap = new HashMap<String, Object>();
        List<SaleDTO> StockItemDTOEntity1 = new ArrayList<SaleDTO>();

        List<SaleDTO> saleDTOList = getTallySaleDTOList(xmlFile);

        for (SaleDTO saleDTO : saleDTOList) {
            System.out.println("SAle Ledger In Sale---------" + saleDTO.getSaleLedger());
            System.out.println("Cash Ledger In Sale---------" + saleDTO.getCashLedger());
            System.out.println("Sale     ledger ----------- " + TagsHelper1.ledgerNameToIDMapping.keySet());
            System.out.println("Cash ledger Form MAP----------- " + TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getCashLedger()));
            System.out.println("Sale ledger Form MAP----------- " + TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getSaleLedger()));
            System.out.println("Contains Cash ------------- " + TagsHelper1.ledgerNameToIDMapping.containsKey(saleDTO.getCashLedger()));
            System.out.println("Contains Sale ------------- " + TagsHelper1.ledgerNameToIDMapping.containsKey(saleDTO.getSaleLedger()));
            System.out.println("Receipt No ------------- " + saleDTO.getReceiptNo());

            saleDTO.setCashLedger(TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getCashLedger()));//casj ledger
            saleDTO.setSaleLedger(TagsHelper1.ledgerNameToIDMapping.get(saleDTO.getSaleLedger()));//sa;e lefdgetr

            List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {
                System.out.println("Stock Item Name           ----------  " + stockItemTransactionDTO.getName());
                System.out.println("Stock Item Present ----------  " + TagsHelper1.stockItemNameToIDMapping.containsKey(stockItemTransactionDTO.getName()));
                System.out.println("All Stock Item ----------  " + TagsHelper1.stockItemNameToIDMapping.keySet());
                System.out.println("Stock Item ----------  " + TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTO.setName(TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            saleDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);
            System.out.println("DDT___DDN---------------------------------->>>>>>>>>>>>>" + saleDTO.getDispatchDocNo());
            System.out.println("DDT___DDN---------------------------------->>>>>>>>>>>>>" + saleDTO.getDispatchDocThrough());

            saleDTO = ImpExpUtil.valiDateAndRepairSaleDTO(saleDTO);
//            StockItemDTOEntity1.add(saleDTO);
            saleReceiptNoDTOMap.put(saleDTO.getReceiptNo().toString(), saleDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());

//	insertSaleVoucher(StockItemDTOEntity1);
//        return true;
        return saleReceiptNoDTOMap;
    }

    public static Map<String, Object> insertChalanXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();
        TagsHelper1.loadStockItemNameToIDMap();


//	List<ChalanDTO> StockItemDTOEntity1 = new ArrayList<ChalanDTO>();
        Map<String, Object> chalanReceiptNoDTOMap = new HashMap<String, Object>();

        List<ChalanDTO> chalanDTOList = getChalanDTOList(xmlFile);

        for (ChalanDTO chalanDTO : chalanDTOList) {
            chalanDTO.setCashLedger(TagsHelper1.ledgerNameToIDMapping.get(chalanDTO.getCashLedger()));//casj ledger
            chalanDTO.setSaleLedger(TagsHelper1.ledgerNameToIDMapping.get(chalanDTO.getSaleLedger()));//sa;e lefdgetr

            List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            chalanDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

            chalanDTO = ImpExpUtil.valiDateAndRepairChalanDTO(chalanDTO);
            chalanReceiptNoDTOMap.put(chalanDTO.getReceiptNo().toString(), chalanDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());

//	if (!StockItemDTOEntity1.isEmpty()) {
//	    insertChalanVoucher(StockItemDTOEntity1);
//	} else {
//	    throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
//	}

        return chalanReceiptNoDTOMap;
    }

    public static Map<String, Object> insertPurchaseXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, Exception, SQLException, ParseException {
        TagsHelper1.loadLedgerNameToIDMap();
        TagsHelper1.loadStockItemNameToIDMap();


//	List<PurchaseDTO> StockItemDTOEntity1 = new ArrayList<PurchaseDTO>();
        Map<String, Object> purchaseReceiptNoDTOMap = new HashMap<String, Object>();

        List<PurchaseDTO> purchaseDTOList = getPurchaseDTOList(xmlFile);

        for (PurchaseDTO purchseDTO : purchaseDTOList) {
            purchseDTO.setCashLedger(TagsHelper1.ledgerNameToIDMapping.get(purchseDTO.getCashLedger()));//casj ledger
            purchseDTO.setPurchaseLedger(TagsHelper1.ledgerNameToIDMapping.get(purchseDTO.getPurchaseLedger()));//sa;e lefdgetr

            List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : purchseDTO.getStockItemTreansactioDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            purchseDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

            purchseDTO = ImpExpUtil.valiDateAndRepairPurchaseDTO(purchseDTO);
            purchaseReceiptNoDTOMap.put(purchseDTO.getReceiptNo().toString(), purchseDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());

//	if (!StockItemDTOEntity1.isEmpty()) {
//	    insertPurchaseVoucher(StockItemDTOEntity1);
//	} else {
//	    throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
//	}

        return purchaseReceiptNoDTOMap;
    }

//    public static Boolean insertTallyPurchaseXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
    public static Map<String, Object> insertTallyPurchaseXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
        TagsHelper1.loadLedgerNameToIDMap();
        TagsHelper1.loadStockItemNameToIDMap();

        Map<String, Object> purchaseReceiptNoDTOMap = new HashMap<String, Object>();
        List<PurchaseDTO> StockItemDTOEntity1 = new ArrayList<PurchaseDTO>();

        List<PurchaseDTO> purchaseDTOList = getTallyPurchaseDTOList(xmlFile);

        for (PurchaseDTO purchseDTO : purchaseDTOList) {
            System.out.println("SAle Ledger In Purchase---------" + purchseDTO.getPurchaseLedger());
            System.out.println("Cash Ledger In Purchase ---------" + purchseDTO.getCashLedger());
            purchseDTO.setCashLedger(TagsHelper1.ledgerNameToIDMapping.get(purchseDTO.getCashLedger()));//casj ledger
            purchseDTO.setPurchaseLedger(TagsHelper1.ledgerNameToIDMapping.get(purchseDTO.getPurchaseLedger()));//sa;e lefdgetr
            List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
            for (StockItemTransactionDTO stockItemTransactionDTO : purchseDTO.getStockItemTreansactioDTOList()) {
                System.out.println("Stock item purchase ---  " + stockItemTransactionDTO.getName());
                stockItemTransactionDTO.setName(TagsHelper1.stockItemNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            purchseDTO.setStockItemTreansactioDTOList(stockItemTransactionDTOList);

            purchseDTO = ImpExpUtil.valiDateAndRepairPurchaseDTO(purchseDTO);
//            StockItemDTOEntity1.add(purchseDTO);
            purchaseReceiptNoDTOMap.put(purchseDTO.getReceiptNo().toString(), purchseDTO);
        }
        System.out.println("stockGroupNameToIDMapping-->>" + TagsHelper1.stockGroupNameToIDMapping.size());

//	insertPurchaseVoucher(StockItemDTOEntity1);
//        return true;
        return purchaseReceiptNoDTOMap;
    }

    public static Map<String, Object> insertPaymentXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();

//	List<PaymentDTO> paymentDTOEntityList = new ArrayList<PaymentDTO>();
        Map<String, Object> paymentReceiptNoDTOMap = new HashMap<String, Object>();

        List<PaymentDTO> paymentDTOList = getPaymentDTOList(xmlFile);

        for (PaymentDTO paymentDTO : paymentDTOList) {
            paymentDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(paymentDTO.getAccountName()));//casj ledger
            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            for (LedgerTransactionDTO stockItemTransactionDTO : paymentDTO.getLedgerTransactionDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            paymentDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            paymentDTO = ImpExpUtil.valiDateAndRepairPaymemntDTO(paymentDTO);
            System.out.println("Payment receipt number -------------- " + paymentDTO.getReceiptNo().toString());
            paymentReceiptNoDTOMap.put(paymentDTO.getReceiptNo().toString(), paymentDTO);
        }
        System.out.println("paymentDTOEntityList-->>" + paymentReceiptNoDTOMap.size());

        return paymentReceiptNoDTOMap;
    }

//    public static Boolean insertTallyPaymentXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
    public static Map<String, Object> insertTallyPaymentXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
        TagsHelper1.loadLedgerNameToIDMap();

        Map<String, Object> paymentReceiptNoDTOMap = new HashMap<String, Object>();
        List<PaymentDTO> paymentDTOEntityList = new ArrayList<PaymentDTO>();

        List<PaymentDTO> paymentDTOList = getTallyPaymentDTOList(xmlFile);

        for (PaymentDTO paymentDTO : paymentDTOList) {
            System.out.println("Account Ledger In Payment ---------" + paymentDTO.getAccountName());
            paymentDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(paymentDTO.getAccountName()));//casj ledger

            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            for (LedgerTransactionDTO stockItemTransactionDTO : paymentDTO.getLedgerTransactionDTOList()) {
                System.out.println("Ledger Name in ---------" + stockItemTransactionDTO.getName());
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            paymentDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            paymentDTO = ImpExpUtil.valiDateAndRepairPaymemntDTO(paymentDTO);
//            paymentDTOEntityList.add(paymentDTO);
            paymentReceiptNoDTOMap.put(paymentDTO.getReceiptNo().toString(), paymentDTO);
        }
        System.out.println("paymentDTOEntityList-->>" + paymentDTOEntityList.size());
        System.out.println("paymentDTOEntityList-->>" + paymentReceiptNoDTOMap.size());
//	insertPaymentVoucher(paymentDTOEntityList);
        return paymentReceiptNoDTOMap;
    }

    public static Map<String, Object> insertTallyContraXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
        TagsHelper1.loadLedgerNameToIDMap();

        Map<String, Object> contraReceiptNoDTOMap = new HashMap<String, Object>();
        List<ContraDTO> contraDTOEntityList = new ArrayList<ContraDTO>();

        List<ContraDTO> contraDTOList = getTallyContraDTOList(xmlFile);

        for (ContraDTO contraDTO : contraDTOList) {
            System.out.println("Account Ledger In Payment ---------" + contraDTO.getAccountName());
            contraDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(contraDTO.getAccountName()));//casj ledger

            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            for (LedgerTransactionDTO stockItemTransactionDTO : contraDTO.getLedgerTransactionDTOList()) {
                System.out.println("Ledger Name in ---------" + stockItemTransactionDTO.getName());
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            contraDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            contraDTO = ImpExpUtil.valiDateAndRepairContraDTO(contraDTO);
            contraReceiptNoDTOMap.put(contraDTO.getReceiptNo().toString(), contraDTO);
        }
        System.out.println("paymentDTOEntityList-->>" + contraDTOEntityList.size());
        System.out.println("paymentDTOEntityList-->>" + contraReceiptNoDTOMap.size());
        return contraReceiptNoDTOMap;
    }

    public static Map<String, Object> insertReceiptXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();


//	List<ReceiptDTO> receiptDTOEntity = new ArrayList<ReceiptDTO>();
        Map<String, Object> receiptReceiptNoDTOMap = new HashMap<String, Object>();

        List<ReceiptDTO> receiptDTOList = getReceiptDTOList(xmlFile);

        for (ReceiptDTO receiptDTO : receiptDTOList) {
            receiptDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(receiptDTO.getAccountName()));//casj ledger
            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            System.out.println("paymentDTO.getLedgerTransactionDTOList()------>>" + receiptDTO.getLedgerTransactionDTOList().size());
            for (LedgerTransactionDTO stockItemTransactionDTO : receiptDTO.getLedgerTransactionDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            receiptDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            receiptDTO = ImpExpUtil.valiDateAndRepairReceiptDTO(receiptDTO);
            receiptReceiptNoDTOMap.put(receiptDTO.getReceiptNo().toString(), receiptDTO);
        }
        System.out.println("receiptDTOEntity-->>" + receiptReceiptNoDTOMap.size());

//	if (!receiptDTOEntity.isEmpty()) {
//	    insertReceiptVoucher(receiptDTOEntity);
//	} else {
//	    throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
//	}

        return receiptReceiptNoDTOMap;
    }

//    public static Boolean insertTallyReceiptXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
    public static Map<String, Object> insertTallyReceiptXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException {
        TagsHelper1.loadLedgerNameToIDMap();
        Map<String, Object> receiptReceiptNoDTOMap = new HashMap<String, Object>();
        List<ReceiptDTO> receiptDTOEntity = new ArrayList<ReceiptDTO>();

        List<ReceiptDTO> receiptDTOList = getTallyReceiptDTOList(xmlFile);

        for (ReceiptDTO receiptDTO : receiptDTOList) {
            System.out.println("Account Ledger In Receipt ---------" + receiptDTO.getAccountName());
            receiptDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(receiptDTO.getAccountName()));//casj ledger

            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            System.out.println("paymentDTO.getLedgerTransactionDTOList()------>>" + receiptDTO.getLedgerTransactionDTOList().size());
            for (LedgerTransactionDTO stockItemTransactionDTO : receiptDTO.getLedgerTransactionDTOList()) {
                System.out.println("Ledger Name ---------" + stockItemTransactionDTO.getName());
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            receiptDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            receiptDTO = ImpExpUtil.valiDateAndRepairReceiptDTO(receiptDTO);
//            receiptDTOEntity.add(receiptDTO);
            receiptReceiptNoDTOMap.put(receiptDTO.getReceiptNo().toString(), receiptDTO);
        }
        System.out.println("receiptDTOEntity-->>" + receiptDTOEntity.size());

//	insertReceiptVoucher(receiptDTOEntity);
//        return true;
        return receiptReceiptNoDTOMap;
    }

    public static Map<String, Object> insertContraXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, ParseException, Exception {
        TagsHelper1.loadLedgerNameToIDMap();

//	List<ContraDTO> contraDTOEntity = new ArrayList<ContraDTO>();
        Map<String, Object> contraReceiptNoDTOMap = new HashMap<String, Object>();

        List<ContraDTO> contraDTOList = getContraDTOList(xmlFile);

        for (ContraDTO contraDTO : contraDTOList) {
            contraDTO.setAccountName(TagsHelper1.ledgerNameToIDMapping.get(contraDTO.getAccountName()));//casj ledger
            List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
            for (LedgerTransactionDTO stockItemTransactionDTO : contraDTO.getLedgerTransactionDTOList()) {
                stockItemTransactionDTO.setName(TagsHelper1.ledgerNameToIDMapping.get(stockItemTransactionDTO.getName()));
                stockItemTransactionDTOList.add(stockItemTransactionDTO);
            }
            contraDTO.setLedgerTransactionDTOList(stockItemTransactionDTOList);

            contraDTO = ImpExpUtil.valiDateAndRepairContraDTO(contraDTO);
            contraReceiptNoDTOMap.put(contraDTO.getReceiptNo().toString(), contraDTO);
        }

//	if (!contraDTOEntity.isEmpty()) {
//	    insertContraVoucher(contraDTOEntity);
//	} else {
//	    throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
//	}
        return contraReceiptNoDTOMap;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public static Map<String, UnitOfMeasureDTO> convertUnitOfMeasureTOMap(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) {
        Map<String, UnitOfMeasureDTO> UnitOfMeasureDTOMap = new HashMap<String, UnitOfMeasureDTO>();
        for (UnitOfMeasureDTO UnitOfMeasureDTO : UnitOfMeasureDTOList) {
            UnitOfMeasureDTOMap.put(UnitOfMeasureDTO.getUnitOfMeasure_FormalName(), UnitOfMeasureDTO);
        }
        return UnitOfMeasureDTOMap;
    }

    public static Set<String> convertUnitOfMeasureTOSet(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) {
        Set<String> UnitOfMeasureDTOSet = new TreeSet<String>();
        for (UnitOfMeasureDTO UnitOfMeasureDTO : UnitOfMeasureDTOList) {
            UnitOfMeasureDTOSet.add(UnitOfMeasureDTO.getUnitOfMeasure_FormalName());
        }
        return UnitOfMeasureDTOSet;
    }

    public static Boolean insertUnitOfMeasureXMLToDatabase(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException, Exception {
        List<UnitOfMeasureDTO> UnitOfMeasureDTOEntity1 = new ArrayList<UnitOfMeasureDTO>();
        List<UnitOfMeasureDTO> UnitOfMeasureDTOInsert = new ArrayList<UnitOfMeasureDTO>();

        List<UnitOfMeasureDTO> UnitOfMeasureDTOList1 = getUnitOfMeasureDTOList(xmlFile);
        Map<String, UnitOfMeasureDTO> UnitOfMeasureDTOMap = convertUnitOfMeasureTOMap(UnitOfMeasureDTOList1);
        Set<String> gruopNameSet = convertUnitOfMeasureTOSet(UnitOfMeasureDTOList1);

        System.out.println("UnitOfMeasureDTOList1------------------->>: " + UnitOfMeasureDTOList1.size());

        List<UnitOfMeasureDTO> UnitOfMeasureDTOEntityList = UnitOfMeasureDAO.getUnitOfMeasureList(gruopNameSet, Constants.UNIT_MEASURE_NAME);
        Map<String, UnitOfMeasureDTO> UnitOfMeasureDTOEntityMap = convertUnitOfMeasureTOMap(UnitOfMeasureDTOEntityList);

        Set<String> entityKeySet = UnitOfMeasureDTOEntityMap.keySet();
        Set<String> KeySet = UnitOfMeasureDTOMap.keySet();
        for (String kaySet : KeySet) {
            System.out.println("UnitOfMeasureDTOList1-----------------KeySet-->>: " + kaySet);
        }

        for (String key : KeySet) {
            if (!entityKeySet.contains(key)) {
                System.out.println("UnitOfMeasureDTOList1-----------------key-->>: UnitOfMeasureDTOEntity1: " + UnitOfMeasureDTOEntity1.size());
                UnitOfMeasureDTOEntity1.add(UnitOfMeasureDTOMap.get(key));
                System.out.println("UnitOfMeasureDTOList1-----------------key-->>: UnitOfMeasureDTOEntity1: " + UnitOfMeasureDTOEntity1.size());
            }
        }

        for (UnitOfMeasureDTO UnitOfMeasureDTO : UnitOfMeasureDTOEntity1) {
            // UnitOfMeasureDTO.setUnitOfMeasure_Type(TagsHelper1.unitOfMeasureNameToIDMapping.get(UnitOfMeasureDTO.getUnitOfMeasure_Type()));
            UnitOfMeasureDTOInsert.add(UnitOfMeasureDTO);
        }

//        System.out.println("UnitOfMeasureDTOInsert------------------->>: " + UnitOfMeasureDTOInsert.get(0).getUnitOfMeasure_Type());

        if (UnitOfMeasureDTOInsert.size() > 0) {
            insertUnitOfMeasure(UnitOfMeasureDTOInsert);
        }
//	else {
//	    throw new InvalidFileException(gen.dto.Label.INVALID_FILE_EXCEPTION);
//	}
        return true;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    /*
     * *Following getLedgerDTOList() will IMPORT all the xml file records of Ledger
     *  Authors: Pankaj & Sudeep
     *  Date   : 1-04-2013
     */
    public static List<LedgerDTO> getLedgerDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<LedgerDTO> LedgerDTOList = new ArrayList<LedgerDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();
        //NodeList nodeList = doc.getElementsByTagName("Ledger");
        Node ledgerNode = null;
        NodeList ledgerNodeList = null;
        if (doc.getElementsByTagName("LedgerList").item(0) != null) {
            ledgerNode = doc.getElementsByTagName("LedgerList").item(0);
            ledgerNodeList = ledgerNode.getChildNodes();
        }

        if (ledgerNode != null && ledgerNodeList != null) {
            for (int j = 0; j < ledgerNodeList.getLength(); j++) {
                System.out.println("nodeList.getLength() ------------------ " + ledgerNodeList.getLength());
                Node node = ledgerNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    LedgerDTO ledgerDTO = new LedgerDTO();
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("Name").item(0) != null) {
                        ledgerDTO.setLedger_Name(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Alias").item(0) != null) {
                        ledgerDTO.setLedger_Alias(eElement.getElementsByTagName("Alias").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Under").item(0) != null) {
                        ledgerDTO.setLedger_Under(eElement.getElementsByTagName("Under").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("DebitOrCredit").item(0) != null) {
                        ledgerDTO.setDebitOrCredit(Integer.parseInt(eElement.getElementsByTagName("DebitOrCredit").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Address").item(0) != null) {
                        ledgerDTO.setLedger_Address(eElement.getElementsByTagName("Address").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("ContactNo").item(0) != null) {
                        ledgerDTO.setLedger_ContactNo(eElement.getElementsByTagName("ContactNo").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("EmailID").item(0) != null) {
                        ledgerDTO.setLedger_EmailID(eElement.getElementsByTagName("EmailID").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("IncomeTaxNo").item(0) != null) {
                        ledgerDTO.setLedger_IncomeTaxNo(eElement.getElementsByTagName("IncomeTaxNo").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("SaleTaxNo").item(0) != null) {
                        ledgerDTO.setLedger_SaleTaxNo(eElement.getElementsByTagName("SaleTaxNo").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("OpeningBalance").item(0) != null) {
                        ledgerDTO.setLedger_OpeningBalence(Double.parseDouble(eElement.getElementsByTagName("OpeningBalance").item(0).getTextContent()));
                    }
                    //     ledgerDTO.setLedger_CreditLimit(Double.parseDouble(eElement.getElementsByTagName("CreditLimit").item(0).getTextContent()));

                    name = ledgerDTO.getLedger_Name().toString();
                    alias = ledgerDTO.getLedger_Alias().toString();
                    under = ledgerDTO.getLedger_Under().toString();
                    address = ledgerDTO.getLedger_Address().toString();
                    contact = ledgerDTO.getLedger_ContactNo().toString();
                    email = ledgerDTO.getLedger_EmailID().toString();
                    incometax = ledgerDTO.getLedger_IncomeTaxNo().toString();
                    saletax = ledgerDTO.getLedger_SaleTaxNo().toString();

                    System.out.println("getLedgerDTOList--" + name);
                    System.out.println("getLedgerDTOList--" + alias);
                    System.out.println("getLedgerDTOList--" + under);
                    System.out.println("getLedgerDTOList--" + address);
                    System.out.println("getLedgerDTOList--" + contact);
                    System.out.println("getLedgerDTOList--" + email);
                    System.out.println("getLedgerDTOList--" + incometax);
                    System.out.println("getLedgerDTOList--" + saletax);
                    //System.out.println("getGroupDTOList--"+under);

                    LedgerDTOList.add(ledgerDTO);

                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + LedgerDTOList.size());

        return LedgerDTOList;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   

    /*
     * *Following getUnitOfMeasureDTOList() will IMPORT all the xml file records of Unit Of Measure
     *  Authors: Pankaj & Sudeep
     *  Date   : 1-04-2013
     */
    public static List<UnitOfMeasureDTO> getUnitOfMeasureDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<UnitOfMeasureDTO> UnitOfMeasureDTOList = new ArrayList<UnitOfMeasureDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("UnitOfMeasure");

        Node UMNode = null;
        NodeList UMNodeList = null;
        if (doc.getElementsByTagName("UnitOfMeasureList").item(0) != null) {
            UMNode = doc.getElementsByTagName("UnitOfMeasureList").item(0);
            UMNodeList = UMNode.getChildNodes();
        }

        if (UMNode != null && UMNodeList != null) {
            for (int j = 0; j < UMNodeList.getLength(); j++) {
                Node node = UMNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    UnitOfMeasureDTO unitDTO = new UnitOfMeasureDTO();
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("Type").item(0) != null) {
                        unitDTO.setUnitOfMeasure_Type(eElement.getElementsByTagName("Type").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("FormalName").item(0) != null) {
                        unitDTO.setUnitOfMeasure_FormalName(eElement.getElementsByTagName("FormalName").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Symbol").item(0) != null) {
                        unitDTO.setUnitOfMeasure_Symbol(eElement.getElementsByTagName("Symbol").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("NoDecimalPlaces").item(0) != null) {
                        unitDTO.setUnitOfMeasure_NoDecimalPlaces(eElement.getElementsByTagName("NoDecimalPlaces").item(0).getTextContent());
                    }

                    type = unitDTO.getUnitOfMeasure_Type().toString();
                    symbol = unitDTO.getUnitOfMeasure_Symbol().toString();
                    formalName = unitDTO.getUnitOfMeasure_FormalName().toString();
                    NoOfDecimalPlaces = unitDTO.getUnitOfMeasure_NoDecimalPlaces().toString();

                    System.out.println("getUnitDTOList--" + type);
                    System.out.println("getUnitDTOList--" + symbol);
                    System.out.println("getUnitDTOList--" + formalName);
                    System.out.println("getUnitDTOList--" + NoOfDecimalPlaces);

                    UnitOfMeasureDTOList.add(unitDTO);

                }
            }
        }
        System.out.println("ReadXMLFile-->>getGroupDTOList()-->>groupDTOList.size()-->>" + UnitOfMeasureDTOList.size());

        return UnitOfMeasureDTOList;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////         

    /*
     * *Following getStockGroupDTOList() will IMPORT all the xml file records of Stock Group
     *  Authors: Pankaj & Sudeep
     *  Date   : 1-04-2013
     */
    public static List<StockGroupDTO> getStockGroupDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<StockGroupDTO> StockGroupDTOList = new ArrayList<StockGroupDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("StockGroup");

        Node stockGroupNode = null;
        NodeList stockGroupNodeList = null;
        if (doc.getElementsByTagName("StockGroupList").item(0) != null) {
            stockGroupNode = doc.getElementsByTagName("StockGroupList").item(0);
            stockGroupNodeList = stockGroupNode.getChildNodes();
        }

        if (stockGroupNode != null && stockGroupNodeList != null) {
            for (int j = 0; j < stockGroupNodeList.getLength(); j++) {
                Node node = stockGroupNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    StockGroupDTO stockgroupDTO = new StockGroupDTO();
                    Element eElement = (Element) node;
                    if (eElement.getElementsByTagName("Name").item(0) != null) {
                        stockgroupDTO.setStockGroupName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Alias").item(0) != null) {
                        stockgroupDTO.setStockGroupAlias(eElement.getElementsByTagName("Alias").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Under").item(0) != null) {
                        stockgroupDTO.setStockGroupUnder(eElement.getElementsByTagName("Under").item(0).getTextContent());
                    }

                    name = stockgroupDTO.getStockGroupName().toString();
                    alias = stockgroupDTO.getStockGroupAlias().toString();
                    under = stockgroupDTO.getStockGroupUnder().toString();

                    System.out.println("getStockGroupDTOList--" + name);
                    System.out.println("getStockGroupDTOList--" + alias);
                    System.out.println("getStockGroupDTOList--" + under);

                    StockGroupDTOList.add(stockgroupDTO);

                }
            }
        }
        System.out.println("ReadXMLFile-->>getGroupDTOList()-->>groupDTOList.size()-->>" + StockGroupDTOList.size());

        return StockGroupDTOList;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        

    /**
     * *****************************************************
     */
    public static List<StockItemDTO> getStockItemDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<StockItemDTO> StockItemDTOList = new ArrayList<StockItemDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        Element svgElement = doc.getDocumentElement();

        doc.getDocumentElement().normalize();


//	NodeList nodeList = doc.getElementsByTagName("StockItem");
        Node stockItemNode = null;
        NodeList stockItemNodeList = null;
        if (doc.getElementsByTagName("StockItemList").item(0) != null) {
            stockItemNode = doc.getElementsByTagName("StockItemList").item(0);
            stockItemNodeList = stockItemNode.getChildNodes();
        }

        if (stockItemNode != null && stockItemNodeList != null) {
            for (int j = 0; j < stockItemNodeList.getLength(); j++) {
                Node node = stockItemNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    StockItemDTO stockItemDTO = new StockItemDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("Name").item(0) != null) {
                        stockItemDTO.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    }

                    if (eElement.getElementsByTagName("Alias").item(0) != null) {
                        stockItemDTO.setAlias(eElement.getElementsByTagName("Alias").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Under").item(0) != null) {
                        System.out.println("Under ------------- ");
                        stockItemDTO.setUnder(eElement.getElementsByTagName("Under").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Length").item(0) != null) {
                        stockItemDTO.setLength(Double.parseDouble(eElement.getElementsByTagName("Length").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Width").item(0) != null) {
                        stockItemDTO.setWidth(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Thickness").item(0) != null) {
                        stockItemDTO.setThkness(Double.parseDouble(eElement.getElementsByTagName("Thickness").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Unit").item(0) != null) {
                        stockItemDTO.setUnit(eElement.getElementsByTagName("Unit").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Rate").item(0) != null) {
                        stockItemDTO.setRate(Double.parseDouble(eElement.getElementsByTagName("Rate").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("OpeningBalance").item(0) != null) {
                        stockItemDTO.setOpeningBal(Double.parseDouble(eElement.getElementsByTagName("OpeningBalance").item(0).getTextContent()));
                    }

                    name = stockItemDTO.getName().toString();
                    alias = stockItemDTO.getAlias().toString();
                    under = stockItemDTO.getUnder().toString();
                    length = Double.parseDouble(stockItemDTO.getLength().toString());
                    width = Double.parseDouble(stockItemDTO.getWidth().toString());
                    thickness = Double.parseDouble(stockItemDTO.getThkness().toString());
                    unit = stockItemDTO.getUnit().toString();
                    rate = Double.parseDouble(stockItemDTO.getRate().toString());
                    openingBalence = Double.parseDouble(stockItemDTO.getOpeningBal().toString());

                    System.out.println("getLedgerDTOList--" + name);
                    System.out.println("getLedgerDTOList--" + alias);
                    System.out.println("getLedgerDTOList--" + under);
                    System.out.println("getLedgerDTOList--" + length);
                    System.out.println("getLedgerDTOList--" + width);
                    System.out.println("getLedgerDTOList--" + unit);
                    System.out.println("getLedgerDTOList--" + thickness);
                    System.out.println("getLedgerDTOList--" + rate);
                    System.out.println("getGroupDTOList--" + openingBalence);

                    StockItemDTOList.add(stockItemDTO);

                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + StockItemDTOList.size());

        return StockItemDTOList;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        

    public static List<SaleDTO> getSaleDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        controlConstantImpExp = 1;
        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        List<StockItemTransactionDTO> stockItemDTOList = new ArrayList<StockItemTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("SaleVoucher");

        Node saleNode = null;
        NodeList saleNodeList = null;
        if (doc.getElementsByTagName("SaleVoucherList").item(0) != null) {
            saleNode = doc.getElementsByTagName("SaleVoucherList").item(0);
            saleNodeList = saleNode.getChildNodes();
        }

        if (saleNode != null && saleNodeList != null) {
            for (int j = 0; j < saleNodeList.getLength(); j++) {
                Node node = saleNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    SaleDTO saleFormDTO = new SaleDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        saleFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                        System.out.println("Receipt VAlue of Sale -----------" + Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        saleFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                        System.out.println("daTE VAlue of Sale -----------" + eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("CashLedger").item(0) != null) {
                        saleFormDTO.setCashLedger(eElement.getElementsByTagName("CashLedger").item(0).getTextContent());
                        System.out.println("CashLedger VAlue of Sale -----------" + eElement.getElementsByTagName("CashLedger").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("SaleLedger").item(0) != null) {
                        System.out.println("SaleLedger VAlue of Sale -----------" + eElement.getElementsByTagName("SaleLedger").item(0).getTextContent());
                        saleFormDTO.setSaleLedger(eElement.getElementsByTagName("SaleLedger").item(0).getTextContent());
                    }

                    System.out.println("^^^^^^^^^         " + eElement.getElementsByTagName("StockItemTransactionList"));
                    System.out.println("^^^^^^^^^         " + eElement.getElementsByTagName("StockItemTransactionList").item(0));

                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (eElement.getElementsByTagName("StockItemTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("StockItemTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }

                    List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                    if (node1 != null && nodeList1 != null) {
                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;
                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
                                if (eElementEntity.getElementsByTagName("Categary").item(0) != null) {
                                    stockItemTransactionDTO.setCatogary(eElementEntity.getElementsByTagName("Categary").item(0).getTextContent());
                                }

                                if (eElementEntity.getElementsByTagName("Length").item(0) != null) {
                                    stockItemTransactionDTO.setLength(Double.parseDouble(eElementEntity.getElementsByTagName("Length").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Width").item(0) != null) {
                                    stockItemTransactionDTO.setWidth(Double.parseDouble(eElementEntity.getElementsByTagName("Width").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Thickness").item(0) != null) {
                                    stockItemTransactionDTO.setThkness(Double.parseDouble(eElementEntity.getElementsByTagName("Thickness").item(0).getTextContent()));
                                }

                                if (eElementEntity.getElementsByTagName("Color").item(0) != null) {
                                    stockItemTransactionDTO.setColor(eElementEntity.getElementsByTagName("Color").item(0).getTextContent());
                                }
                                if (eElementEntity.getElementsByTagName("FinishType").item(0) != null) {
                                    stockItemTransactionDTO.setType(eElementEntity.getElementsByTagName("FinishType").item(0).getTextContent());
                                }

                                if (eElementEntity.getElementsByTagName("BoardType").item(0) != null) {
                                    stockItemTransactionDTO.setBoard_type(eElementEntity.getElementsByTagName("BoardType").item(0).getTextContent());
                                }

                                if (eElementEntity.getElementsByTagName("Nos").item(0) != null) {
                                    stockItemTransactionDTO.setNo(Double.parseDouble(eElementEntity.getElementsByTagName("Nos").item(0).getTextContent().isEmpty() ? "0" : eElementEntity.getElementsByTagName("Nos").item(0).getTextContent()));
                                }

                                if (eElementEntity.getElementsByTagName("Quantity").item(0) != null) {
                                    stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent().isEmpty() ? "0" : eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("SqFeet").item(0) != null) {
                                    stockItemTransactionDTO.setSqFeet(Double.parseDouble(eElementEntity.getElementsByTagName("SqFeet").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Rate").item(0) != null) {
                                    stockItemTransactionDTO.setRate(Double.parseDouble(eElementEntity.getElementsByTagName("Rate").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }

                                name = stockItemTransactionDTO.getName().toString();
                                length = Double.parseDouble(stockItemTransactionDTO.getLength().toString());
                                width = Double.parseDouble(stockItemTransactionDTO.getWidth().toString());
                                thickness = Double.parseDouble(stockItemTransactionDTO.getThkness().toString());
                                unit = stockItemTransactionDTO.getQuantity().toString();
                                rate = Double.parseDouble(stockItemTransactionDTO.getRate().toString());
                                openingBalence = Double.parseDouble(stockItemTransactionDTO.getAmount().toString());

                                System.out.println("getLedgerDTOList Name--" + name);
                                System.out.println("getLedgerDTOList--length" + length);
                                System.out.println("getLedgerDTOList--width" + width);
                                System.out.println("getLedgerDTOList--unit" + unit);
                                System.out.println("getLedgerDTOList--thickness" + thickness);
                                System.out.println("getLedgerDTOList--rate" + rate);
                                System.out.println("getGroupDTOList--openingBalence" + openingBalence);
                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
                            }
                        }
                    }

                    saleFormDTO.setStockItemTreansactioDTOList(StockItemTransactionDTOList);
                    if (eElement.getElementsByTagName("Amount").item(0) != null) {
                        saleFormDTO.setAmount(Double.parseDouble(eElement.getElementsByTagName("Amount").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("PONo").item(0) != null) {
                        saleFormDTO.setPoNo(eElement.getElementsByTagName("PONo").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("PODate").item(0) != null) {
                        saleFormDTO.setPoDate(eElement.getElementsByTagName("PODate").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("OcNo").item(0) != null) {
                        saleFormDTO.setOcNo(eElement.getElementsByTagName("OcNo").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("OcDate").item(0) != null) {
                        saleFormDTO.setOcDate(eElement.getElementsByTagName("OcDate").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("dateIss").item(0) != null) {
                        saleFormDTO.setDateIssDate(eElement.getElementsByTagName("dateIss").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("dateRem").item(0) != null) {
                        saleFormDTO.setDateRemDate(eElement.getElementsByTagName("dateRem").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("IssTime").item(0) != null) {
                        saleFormDTO.setTimeIss(eElement.getElementsByTagName("IssTime").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("RemTime").item(0) != null) {
                        saleFormDTO.setTimeRem(eElement.getElementsByTagName("RemTime").item(0).getTextContent());
                    }
                    
                    if (eElement.getElementsByTagName("CSTRate").item(0) != null) {
                        saleFormDTO.setCstRate(Double.parseDouble(eElement.getElementsByTagName("CSTRate").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("CSTAmount").item(0) != null) {
                        saleFormDTO.setCstAmount(Double.parseDouble(eElement.getElementsByTagName("CSTAmount").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("hEDCessRate").item(0) != null) {
                        saleFormDTO.sethEdCessRate(Double.parseDouble(eElement.getElementsByTagName("hEDCessRate").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("hEDCessAmount").item(0) != null) {
                        saleFormDTO.setHedCessAmount(Double.parseDouble(eElement.getElementsByTagName("hEDCessAmount").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("EDCessRate").item(0) != null) {
                        saleFormDTO.setEdCessRate(Double.parseDouble(eElement.getElementsByTagName("EDCessRate").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("EDCessAmount").item(0) != null) {
                        saleFormDTO.setEdCessAmount(Double.parseDouble(eElement.getElementsByTagName("EDCessAmount").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("ExciseDutyRate").item(0) != null) {
                        saleFormDTO.setExciseDutyRate(Double.parseDouble(eElement.getElementsByTagName("ExciseDutyRate").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("ExciseDutyAmount").item(0) != null) {
                        saleFormDTO.setExciseDutyAmount(Double.parseDouble(eElement.getElementsByTagName("ExciseDutyAmount").item(0).getTextContent()));
                    }
                    
                    if (eElement.getElementsByTagName("Note").item(0) != null) {
                        saleFormDTO.setNote(eElement.getElementsByTagName("Note").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("DispatchDocNo").item(0) != null) {
                        saleFormDTO.setDispatchDocNo(eElement.getElementsByTagName("DispatchDocNo").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("DispatchDocThrough").item(0) != null) {
                        saleFormDTO.setDispatchDocThrough(eElement.getElementsByTagName("DispatchDocThrough").item(0).getTextContent());
                    }

                    if (eElement.getElementsByTagName("PaymentMode").item(0) != null) {
                        saleFormDTO.setPaymentMode(eElement.getElementsByTagName("PaymentMode").item(0).getTextContent());
                    }

                    if (eElement.getElementsByTagName("VatRate").item(0) != null) {
                        saleFormDTO.setVatRate(Double.parseDouble(eElement.getElementsByTagName("VatRate").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("VatAmount").item(0) != null) {
                        saleFormDTO.setVatAmount(Double.parseDouble(eElement.getElementsByTagName("VatAmount").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("LessBillAmount").item(0) != null) {
                        saleFormDTO.setLessBillAmount(Double.parseDouble(eElement.getElementsByTagName("LessBillAmount").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Transport").item(0) != null) {
                        saleFormDTO.setTransport(Double.parseDouble(eElement.getElementsByTagName("Transport").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("FinalAmount").item(0) != null) {
                        saleFormDTO.setFinalAmount(Double.parseDouble(eElement.getElementsByTagName("FinalAmount").item(0).getTextContent()));
                    }


                    System.out.println("TNO" + saleFormDTO.getReceiptNo());
                    System.out.println("TDATE" + saleFormDTO.getDate());
                    System.out.println("Saleaccount" + saleFormDTO.getCashLedger());
                    System.out.println("saleLedger" + saleFormDTO.getSaleLedger());
                    System.out.println("Amount" + saleFormDTO.getAmount());
                    System.out.println("Note" + saleFormDTO.getNote());
                    System.out.println("VatRate" + saleFormDTO.getVatRate());
                    System.out.println("FinalAmount" + saleFormDTO.getFinalAmount());

                    saleDTOList.add(saleFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + saleDTOList.size());

        return saleDTOList;

    }

    public static List<SaleDTO> getTallySaleDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {

        controlConstantImpExp = 2;
        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        List<StockItemTransactionDTO> stockItemDTOList = new ArrayList<StockItemTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VOUCHER");
        System.out.println("nodeList.getLength()-->>>" + nodeList.getLength());
        for (int j = 0; j < nodeList.getLength(); j++) {

            Element e = (Element) nodeList.item(j);
            if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("sales")) {
                //sale = e.getChildNodes();

                Node node = nodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    SaleDTO saleFormDTO = new SaleDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("VOUCHERNUMBER").item(0) != null) {
                        saleFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("VOUCHERNUMBER").item(0).getTextContent()));
                    }

                    String date = "";
                    String string1 = "";

                    if (eElement.getElementsByTagName("DATE").item(0) != null) {
                        date = eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        string1 = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        saleFormDTO.setDate(string1);
                    }
                    System.out.println("Date--->>>" + string1);



                    NodeList tagExistsCheck = eElement.getElementsByTagName("NARRATION");
                    System.out.println("Narration Tag Check---->>>>" + tagExistsCheck.getLength());
                    if (tagExistsCheck.getLength() != 0) {
                        if (eElement.getElementsByTagName("NARRATION").item(0) != null) {
                            saleFormDTO.setNote(eElement.getElementsByTagName("NARRATION").item(0).getTextContent());
                        }
                    } else {
                        saleFormDTO.setNote("");
                    }

                    if (eElement.getElementsByTagName("PARTYLEDGERNAME").item(0) != null) {
                        saleFormDTO.setCashLedger(eElement.getElementsByTagName("PARTYLEDGERNAME").item(0).getTextContent());
                    }
                    System.out.println("Cash Ledger--->>>" + saleFormDTO.getCashLedger());
                    NodeList nodeListNew = doc.getElementsByTagName("ACCOUNTINGALLOCATIONS.LIST");
                    for (int i = 0; i < nodeListNew.getLength(); i++) {
                        Node nodeNew = nodeListNew.item(i);
                        if (nodeNew.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementNew = (Element) nodeNew;
                            if (eElementNew.getElementsByTagName("LEDGERNAME").item(0) != null) {
                                saleFormDTO.setSaleLedger(eElementNew.getElementsByTagName("LEDGERNAME").item(0).getTextContent());
                            }
                            System.out.println("Sale Ledger--->>>" + saleFormDTO.getSaleLedger());
                        }
                    }

                    //Node node1 = eElement.getElementsByTagName("ALLINVENTORYENTRIES.LIST").item(0);
                    NodeList nodeList1 = eElement.getElementsByTagName("ALLINVENTORYENTRIES.LIST");//node1.getChildNodes();
                    List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                    for (int k = 0; k < nodeList1.getLength(); k++) {
                        Node nodeEntity = nodeList1.item(k);
                        System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                        if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("Came INside the if block...");
                            StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                            Element eElementEntity = (Element) nodeEntity;
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                stockItemTransactionDTO.setIsdeemedpositive(eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent());
                            }
                            System.out.println("ISDEEMEDPOSITIVE--->>>" + stockItemTransactionDTO.getIsdeemedpositive());
                            if (eElementEntity.getElementsByTagName("STOCKITEMNAME").item(0) != null) {
                                stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("STOCKITEMNAME").item(0).getTextContent());
                            }
                            System.out.println("NameOfStockItem--->>>" + stockItemTransactionDTO.getName());
                            //stockItemTransactionDTO.setLength(Double.parseDouble(eElementEntity.getElementsByTagName("length").item(0).getTextContent()));
                            //stockItemTransactionDTO.setWidth(Double.parseDouble(eElementEntity.getElementsByTagName("Width").item(0).getTextContent()));
                            //stockItemTransactionDTO.setThkness(Double.parseDouble(eElementEntity.getElementsByTagName("Thkness").item(0).getTextContent()));
                            NodeList actualQuantityTagExistsCheck = eElementEntity.getElementsByTagName("ACTUALQTY");
                            if (actualQuantityTagExistsCheck.getLength() != 0) {
                                String quantity = "";
                                if (eElementEntity.getElementsByTagName("ACTUALQTY").item(0) != null) {
                                    quantity = extractDigitsFromString(eElementEntity.getElementsByTagName("ACTUALQTY").item(0).getTextContent());
                                }
                                System.out.println("quantity--->>>" + quantity);
                                if (!quantity.equalsIgnoreCase("")) {
                                    stockItemTransactionDTO.setQuantity(Double.parseDouble(quantity));
                                } else {
                                    stockItemTransactionDTO.setQuantity(0D);
                                }
                                System.out.println("QuantityOfStockItem--->>>" + stockItemTransactionDTO.getQuantity());
                            }
                            NodeList rateTagExistsCheck = eElementEntity.getElementsByTagName("RATE");
                            if (rateTagExistsCheck.getLength() != 0) {
                                String rateAmount = "";
                                if (eElementEntity.getElementsByTagName("RATE").item(0) != null) {
                                    rateAmount = extractDigitsFromString(eElementEntity.getElementsByTagName("RATE").item(0).getTextContent());
                                }
                                if (!rateAmount.equalsIgnoreCase("")) {
                                    stockItemTransactionDTO.setRate(Double.parseDouble(rateAmount));
                                } else {
                                    stockItemTransactionDTO.setRate(0D);
                                }
                                System.out.println("RateOfStockItem--->>>" + stockItemTransactionDTO.getRate());
                            }
                            if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent()));
                            }
                            System.out.println("AmountOfStockItem--->>>" + stockItemTransactionDTO.getAmount());

                            StockItemTransactionDTOList.add(stockItemTransactionDTO);
                            System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
                        }
                    }

                    NodeList nodeListOld = eElement.getElementsByTagName("LEDGERENTRIES.LIST");
                    for (int i = 0; i < nodeListOld.getLength(); i++) {
                        Node nodeOld = nodeListOld.item(i);
                        if (nodeOld.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementOld = (Element) nodeOld;
                            if (eElementOld.getElementsByTagName("AMOUNT").item(0) != null) {
                                saleFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementOld.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                            }
                            System.out.println("FinalTotalAmount--->>>" + saleFormDTO.getFinalAmount());
                        }
                    }

                    NodeList listofdispatchThrough = doc.getElementsByTagName("UDF:SHIPPEDBY.LIST");
                    for (int i = 0; i < listofdispatchThrough.getLength(); i++) {
                        Node nodeDispatchThrough = listofdispatchThrough.item(i);
                        if (nodeDispatchThrough.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementDT = (Element) nodeDispatchThrough;
                            if (eElementDT.getElementsByTagName("UDF:SHIPPEDBY").item(0) != null) {
                                saleFormDTO.setDispatchDocThrough(eElementDT.getElementsByTagName("UDF:SHIPPEDBY").item(0).getTextContent());
                            }
                            System.out.println("listofShippingTransport--->>>" + saleFormDTO.getDispatchDocThrough());
                        }
                    }

                    NodeList listofDispatchDoc = doc.getElementsByTagName("UDF:SHIPDOCUMENTNO.LIST");
                    for (int i = 0; i < listofDispatchDoc.getLength(); i++) {
                        Node nodeDispatchDoc = listofDispatchDoc.item(i);
                        if (nodeDispatchDoc.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementDD = (Element) nodeDispatchDoc;
                            if (eElementDD.getElementsByTagName("UDF:SHIPDOCUMENTNO").item(0) != null) {
                                saleFormDTO.setDispatchDocNo(eElementDD.getElementsByTagName("UDF:SHIPDOCUMENTNO").item(0).getTextContent());
                            }
                            System.out.println("listofShippingTransport--->>>" + saleFormDTO.getDispatchDocNo());
                        }
                    }


                    saleFormDTO.setStockItemTreansactioDTOList(StockItemTransactionDTOList);

                    saleDTOList.add(saleFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + saleDTOList.size());

        return saleDTOList;

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

    public static List<PurchaseDTO> getPurchaseDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        controlConstantImpExp = 1;
        List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
        List<StockItemTransactionDTO> stockItemDTOList = new ArrayList<StockItemTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("PurchaseVoucher");
        Node purchaseNode = null;
        NodeList purchaseNodeList = null;
        if (doc.getElementsByTagName("PurchaseVoucherList").item(0) != null) {
            purchaseNode = doc.getElementsByTagName("PurchaseVoucherList").item(0);
            purchaseNodeList = purchaseNode.getChildNodes();
        }

        if (purchaseNode != null && purchaseNodeList != null) {
            for (int j = 0; j < purchaseNodeList.getLength(); j++) {
                Node node = purchaseNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    PurchaseDTO purchaseFormDTO = new PurchaseDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        purchaseFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        purchaseFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("CashLedger").item(0) != null) {
                        purchaseFormDTO.setCashLedger(eElement.getElementsByTagName("CashLedger").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("PurchaseLedger").item(0) != null) {
                        purchaseFormDTO.setPurchaseLedger(eElement.getElementsByTagName("PurchaseLedger").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("PurchaseReference").item(0) != null) {
                        purchaseFormDTO.setPurchaseReference(eElement.getElementsByTagName("PurchaseReference").item(0).getTextContent());
                    }

                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (eElement.getElementsByTagName("StockItemTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("StockItemTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }
                    List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();

                    if (node1 != null && nodeList1 != null) {
                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;

                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
                                if (eElementEntity.getElementsByTagName("Length").item(0) != null) {
                                    stockItemTransactionDTO.setLength(Double.parseDouble(eElementEntity.getElementsByTagName("Length").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Width").item(0) != null) {
                                    stockItemTransactionDTO.setWidth(Double.parseDouble(eElementEntity.getElementsByTagName("Width").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Thickness").item(0) != null) {
                                    stockItemTransactionDTO.setThkness(Double.parseDouble(eElementEntity.getElementsByTagName("Thickness").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Quantity").item(0) != null) {
                                    stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("SqFeet").item(0) != null) {
                                    stockItemTransactionDTO.setSqFeet(Double.parseDouble(eElementEntity.getElementsByTagName("SqFeet").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Rate").item(0) != null) {
                                    stockItemTransactionDTO.setRate(Double.parseDouble(eElementEntity.getElementsByTagName("Rate").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }

                                name = stockItemTransactionDTO.getName().toString();
                                length = Double.parseDouble(stockItemTransactionDTO.getLength().toString());
                                width = Double.parseDouble(stockItemTransactionDTO.getWidth().toString());
                                thickness = Double.parseDouble(stockItemTransactionDTO.getThkness().toString());
                                unit = stockItemTransactionDTO.getQuantity().toString();
                                rate = Double.parseDouble(stockItemTransactionDTO.getRate().toString());
                                openingBalence = Double.parseDouble(stockItemTransactionDTO.getAmount().toString());

                                System.out.println("getLedgerDTOList Name--" + name);
                                System.out.println("getLedgerDTOList--length" + length);
                                System.out.println("getLedgerDTOList--width" + width);
                                System.out.println("getLedgerDTOList--unit" + unit);
                                System.out.println("getLedgerDTOList--thickness" + thickness);
                                System.out.println("getLedgerDTOList--rate" + rate);
                                System.out.println("getGroupDTOList--openingBalence" + openingBalence);
                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
                            }
                        }
                    }

                    purchaseFormDTO.setStockItemTreansactioDTOList(StockItemTransactionDTOList);
                    if (eElement.getElementsByTagName("Amount").item(0) != null) {
                        purchaseFormDTO.setAmount(Double.parseDouble(eElement.getElementsByTagName("Amount").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Note").item(0) != null) {
                        purchaseFormDTO.setNote(eElement.getElementsByTagName("Note").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("PaymentMode").item(0) != null) {
                        purchaseFormDTO.setPaymentMode(eElement.getElementsByTagName("PaymentMode").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("VatRate").item(0) != null) {
                        purchaseFormDTO.setVatRate(Double.parseDouble(eElement.getElementsByTagName("VatRate").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("VatAmount").item(0) != null) {
                        purchaseFormDTO.setVatAmount(Double.parseDouble(eElement.getElementsByTagName("VatAmount").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("FinalAmount").item(0) != null) {
                        purchaseFormDTO.setFinalAmount(Double.parseDouble(eElement.getElementsByTagName("FinalAmount").item(0).getTextContent()));
                    }

                    System.out.println("TNO" + purchaseFormDTO.getReceiptNo());
                    System.out.println("TDATE" + purchaseFormDTO.getDate());
                    System.out.println("Saleaccount" + purchaseFormDTO.getCashLedger());
                    System.out.println("PurchaseLedger" + purchaseFormDTO.getPurchaseLedger());
                    System.out.println("Amount" + purchaseFormDTO.getAmount());
                    System.out.println("Note" + purchaseFormDTO.getNote());
                    System.out.println("VatRate" + purchaseFormDTO.getVatRate());
                    System.out.println("FinalAmount" + purchaseFormDTO.getFinalAmount());

                    purchaseDTOList.add(purchaseFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + purchaseDTOList.size());

        return purchaseDTOList;

    }

    public static List<PurchaseDTO> getTallyPurchaseDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        controlConstantImpExp = 2;
        List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
        List<StockItemTransactionDTO> stockItemDTOList = new ArrayList<StockItemTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VOUCHER");
        for (int j = 0; j < nodeList.getLength(); j++) {

            Element e = (Element) nodeList.item(j);
            if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("Purchase")) {
                //sale = e.getChildNodes();

                Node node = nodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    PurchaseDTO purchaseFormDTO = new PurchaseDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("VOUCHERNUMBER").item(0) != null) {
                        purchaseFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("VOUCHERNUMBER").item(0).getTextContent()));
                    }

                    String date = "";
                    String string1 = "";
                    if (eElement.getElementsByTagName("DATE").item(0) != null) {
                        date = eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        string1 = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        purchaseFormDTO.setDate(string1);
                    }
                    System.out.println("Date--->>>" + string1);


                    NodeList tagExistsCheck = eElement.getElementsByTagName("NARRATION");
                    if (tagExistsCheck.getLength() != 0) {
                        if (eElement.getElementsByTagName("NARRATION").item(0) != null) {
                            purchaseFormDTO.setNote(eElement.getElementsByTagName("NARRATION").item(0).getTextContent());
                        }
                    } else {
                        purchaseFormDTO.setNote("");
                    }
                    //purchaseFormDTO.setCashLedger(eElement.getElementsByTagName("CashLedger").item(0).getTextContent());
                    if (eElement.getElementsByTagName("PARTYLEDGERNAME").item(0) != null) {
                        purchaseFormDTO.setPurchaseLedger(eElement.getElementsByTagName("PARTYLEDGERNAME").item(0).getTextContent());
                    }
                    System.out.println("Purchase Ledger" + purchaseFormDTO.getCashLedger());
                    NodeList nodeListNew = eElement.getElementsByTagName("ALLLEDGERENTRIES.LIST");
                    for (int i = 0; i < nodeListNew.getLength(); i++) {
                        Node nodeNew = nodeListNew.item(i);
                        if (nodeNew.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementNew = (Element) nodeNew;
                            if (eElementNew.getElementsByTagName("LEDGERNAME").item(0) != null) {
                                purchaseFormDTO.setCashLedger(eElementNew.getElementsByTagName("LEDGERNAME").item(0).getTextContent());
                            }
                            System.out.println("Cash Ledger--->>>" + purchaseFormDTO.getPurchaseLedger());
                        }
                    }

                    //purchaseFormDTO.setPurchaseLedger(eElement.getElementsByTagName("PurchaseLedger").item(0).getTextContent());
                    //Node node1 = eElement.getElementsByTagName("StockItemTransaction").item(0);
                    NodeList nodeList1 = eElement.getElementsByTagName("INVENTORYALLOCATIONS.LIST");//node1.getChildNodes();
                    List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
                    for (int k = 0; k < nodeList1.getLength(); k++) {
                        Node nodeEntity = nodeList1.item(k);
                        System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                        if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {


                            System.out.println("Came INside the if block...");
                            StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                            Element eElementEntity = (Element) nodeEntity;
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                stockItemTransactionDTO.setIsdeemedpositive(eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent());
                            }
                            System.out.println("ISDEEMEDPOSITIVE--->>>" + stockItemTransactionDTO.getIsdeemedpositive());
                            if (eElementEntity.getElementsByTagName("STOCKITEMNAME").item(0) != null) {
                                stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("STOCKITEMNAME").item(0).getTextContent());
                            }
                            System.out.println("NameOfStockItem--->>>" + stockItemTransactionDTO.getName());
                            //stockItemTransactionDTO.setLength(Double.parseDouble(eElementEntity.getElementsByTagName("length").item(0).getTextContent()));
                            //stockItemTransactionDTO.setWidth(Double.parseDouble(eElementEntity.getElementsByTagName("Width").item(0).getTextContent()));
                            //stockItemTransactionDTO.setThkness(Double.parseDouble(eElementEntity.getElementsByTagName("Thkness").item(0).getTextContent()));
                            NodeList actualQuantityTagExistsCheck = eElementEntity.getElementsByTagName("ACTUALQTY");
                            if (actualQuantityTagExistsCheck.getLength() != 0) {
                                String quantity = "";
                                if (eElementEntity.getElementsByTagName("ACTUALQTY").item(0) != null) {
                                    quantity = extractDigitsFromString(eElementEntity.getElementsByTagName("ACTUALQTY").item(0).getTextContent());
                                }
                                System.out.println("quantity--->>>" + quantity);
                                if (!quantity.equalsIgnoreCase("")) {
                                    stockItemTransactionDTO.setQuantity(Double.parseDouble(quantity));
                                } else {
                                    stockItemTransactionDTO.setQuantity(0D);
                                }
                                System.out.println("QuantityOfStockItem--->>>" + stockItemTransactionDTO.getQuantity());
                            }
                            NodeList rateTagExistsCheck = eElementEntity.getElementsByTagName("RATE");
                            if (rateTagExistsCheck.getLength() != 0) {
                                String rateAmount = "";
                                if (eElementEntity.getElementsByTagName("RATE").item(0) != null) {
                                    rateAmount = extractDigitsFromString(eElementEntity.getElementsByTagName("RATE").item(0).getTextContent());
                                }
                                if (!rateAmount.equalsIgnoreCase("")) {
                                    stockItemTransactionDTO.setRate(Double.parseDouble(rateAmount));
                                } else {
                                    stockItemTransactionDTO.setRate(0D);
                                }
                                System.out.println("RateOfStockItem--->>>" + stockItemTransactionDTO.getRate());
                            }

                            if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent()));
                            }
                            System.out.println("AmountOfStockItem--->>>" + stockItemTransactionDTO.getAmount());

                            StockItemTransactionDTOList.add(stockItemTransactionDTO);
                            System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());

                        }
                    }

                    NodeList nodeListOld = eElement.getElementsByTagName("ALLLEDGERENTRIES.LIST");
                    for (int i = 0; i < nodeListOld.getLength(); i++) {
                        Node nodeOld = nodeListOld.item(i);
                        if (nodeOld.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementOld = (Element) nodeOld;
                            if (eElementOld.getElementsByTagName("AMOUNT").item(0) != null) {
                                purchaseFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementOld.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                            }
                            System.out.println("FinalTotalAmount--->>>" + purchaseFormDTO.getFinalAmount());
                        }
                    }
                    purchaseFormDTO.setStockItemTreansactioDTOList(StockItemTransactionDTOList);

                    purchaseDTOList.add(purchaseFormDTO);
                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + purchaseDTOList.size());

        return purchaseDTOList;

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<ReceiptDTO> getReceiptDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<ReceiptDTO> receiptDTOList = new ArrayList<ReceiptDTO>();
        List<LedgerTransactionDTO> ledgerDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("ReceiptVoucher");

        Node receiptNode = null;
        NodeList receiptNodeList = null;
        if (doc.getElementsByTagName("ReceiptVoucherList").item(0) != null) {
            receiptNode = doc.getElementsByTagName("ReceiptVoucherList").item(0);
            receiptNodeList = receiptNode.getChildNodes();
        }

        if (receiptNode != null && receiptNodeList != null) {
            for (int j = 0; j < receiptNodeList.getLength() - 2; j++) {
                Node node = receiptNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ReceiptDTO receiptFormDTO = new ReceiptDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        receiptFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        receiptFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Account").item(0) != null) {
                        receiptFormDTO.setAccountName(eElement.getElementsByTagName("Account").item(0).getTextContent());
                    }

                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (eElement.getElementsByTagName("LedgerTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("LedgerTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }


                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    if (node1 != null && nodeList1 != null) {
                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;
                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
//                        stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("CheckNo").item(0) != null) {
                                    stockItemTransactionDTO.setCheckNo((eElementEntity.getElementsByTagName("CheckNo").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Note").item(0) != null) {
                                    stockItemTransactionDTO.setNote(eElementEntity.getElementsByTagName("Note").item(0).getTextContent());
                                }

                                name = stockItemTransactionDTO.getName().toString();

                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
                            }
                        }
                    }
                    receiptFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);

                    if (eElement.getElementsByTagName("FinalAmount").item(0) != null) {
                        receiptFormDTO.setFinalAmount(Double.parseDouble(eElement.getElementsByTagName("FinalAmount").item(0).getTextContent()));
                    }

                    System.out.println("TNO" + receiptFormDTO.getReceiptNo());
                    receiptDTOList.add(receiptFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + receiptDTOList.size());

        return receiptDTOList;

    }

    public static List<ReceiptDTO> getTallyReceiptDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<ReceiptDTO> receiptDTOList = new ArrayList<ReceiptDTO>();
        List<LedgerTransactionDTO> ledgerDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VOUCHER");
        for (int j = 0; j < nodeList.getLength(); j++) {
            Element e = (Element) nodeList.item(j);
            if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("Receipt")) {

                Node node = nodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ReceiptDTO receiptFormDTO = new ReceiptDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("VOUCHERNUMBER").item(0) != null) {
                        receiptFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("VOUCHERNUMBER").item(0).getTextContent()));
                    }

                    String date = "";
                    String string1 = "";
                    if (eElement.getElementsByTagName("DATE").item(0) != null) {
                        date = eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        string1 = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        receiptFormDTO.setDate(string1);
                    }
                    System.out.println("Date--->>>" + string1);



                    if (eElement.getElementsByTagName("PARTYLEDGERNAME").item(0) != null) {
                        receiptFormDTO.setAccountName(eElement.getElementsByTagName("PARTYLEDGERNAME").item(0).getTextContent());
                    }

                    NodeList nodeListOld = e.getElementsByTagName("ALLLEDGERENTRIES.LIST");
                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    for (int i = 0; i < nodeListOld.getLength(); i++) {
                        Node nodeLedgerEntries = nodeListOld.item(i);
                        if (nodeLedgerEntries.getNodeType() == Node.ELEMENT_NODE) {
                            LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                            Element eElementEntity = (Element) nodeLedgerEntries;
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("no")) {
                                    if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                        receiptFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                    }
                                    System.out.println("FinalTotalAmount--->>>" + receiptFormDTO.getFinalAmount());
                                    if (eElementEntity.getElementsByTagName("LEDGERNAME").item(0) != null) {
                                        stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("LEDGERNAME").item(0).getTextContent());
                                    }
                                    if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                        stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent()));
                                    }
                                    NodeList tagExistsCheck = eElement.getElementsByTagName("NARRATION");
                                    if (tagExistsCheck.getLength() != 0) {
                                        if (eElement.getElementsByTagName("NARRATION").item(0) != null) {
                                            stockItemTransactionDTO.setNote(eElement.getElementsByTagName("NARRATION").item(0).getTextContent());
                                        }
                                    } else {
                                        stockItemTransactionDTO.setNote("");
                                    }

                                    StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                }
                            }
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("yes")) { //Gives total amount
                                    receiptFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                }
                            }
                        }
                    }
                    receiptFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);
                    receiptDTOList.add(receiptFormDTO);
                }

            }
        }

        return receiptDTOList;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<ContraDTO> getContraDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        List<LedgerTransactionDTO> ledgerDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("ContraVoucherList");

        Node contraNode = null;
        NodeList contraNodeList = null;
        if (doc.getElementsByTagName("ContraVoucherList").item(0) != null) {
            contraNode = doc.getElementsByTagName("ContraVoucherList").item(0);
            contraNodeList = contraNode.getChildNodes();
        }

        if (contraNode != null && contraNodeList != null) {

            for (int j = 0; j < contraNodeList.getLength(); j++) {
                Node node = contraNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ContraDTO contraFormDTO = new ContraDTO();
                    Element eElement = (Element) node;

//		    System.out.println("eElement.getElementsByTagName(\"ReceiptNo\")       " + eElement.getElementsByTagName("ReceiptNo111"));
//		    System.out.println("eElement.getElementsByTagName(\"ReceiptNo\")       " + eElement.getElementsByTagName("ReceiptNo111").item(0));
//		    System.out.println("eElement.getElementsByTagName(\"ReceiptNo\")       " + eElement.getElementsByTagName("ReceiptNo111").item(0).getTextContent());

                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        contraFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        contraFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("Account").item(0) != null) {
                        contraFormDTO.setAccountName(eElement.getElementsByTagName("Account").item(0).getTextContent());
                    }

                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (doc.getElementsByTagName("LedgerTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("LedgerTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }

//		    Node node1 = eElement.getElementsByTagName("LedgerTransactionList").item(0);
//		    NodeList nodeList1 = node1.getChildNodes();

                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    if (node1 != null && nodeList1 != null) {

                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;
                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
//                        stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("CheckNo").item(0) != null) {
                                    stockItemTransactionDTO.setCheckNo((eElementEntity.getElementsByTagName("CheckNo").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Note").item(0) != null) {
                                    stockItemTransactionDTO.setNote(eElementEntity.getElementsByTagName("Note").item(0).getTextContent());
                                }

                                name = stockItemTransactionDTO.getName().toString();

                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                            }
                        }
                    }
                    contraFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);

                    if (eElement.getElementsByTagName("FinalAmount").item(0) != null) {
                        contraFormDTO.setFinalAmount(Double.parseDouble(eElement.getElementsByTagName("FinalAmount").item(0).getTextContent()));
                    }

                    contraDTOList.add(contraFormDTO);


                }
            }
        }

        return contraDTOList;

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public static List<PaymentDTO> getPaymentDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        List<LedgerTransactionDTO> stockItemDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("PaymentVoucher");

        Node paymentNode = null;
        NodeList paymentNodeList = null;

        if (doc.getElementsByTagName("PaymentVoucherList").item(0) != null) {
            System.out.println("111111111111111111111111111111");
            paymentNode = doc.getElementsByTagName("PaymentVoucherList").item(0);
            paymentNodeList = paymentNode.getChildNodes();
        }

        System.out.println("222222222222222222222222222222222222222");
        System.out.println("222222222222222222222222222222222222222" + paymentNode);
        System.out.println("222222222222222222222222222222222222222" + paymentDTOList);
        if (paymentNode != null && paymentNodeList != null) {
            System.out.println("RRRRRRRRRRr    apyemry  ");
            for (int j = 0; j < paymentNodeList.getLength() - 2; j++) {
                System.out.println("RRRRRRRRRRr    apyemry  11111111111");
                Node node = paymentNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    PaymentDTO paymentFormDTO = new PaymentDTO();
                    Element eElement = (Element) node;

                    System.out.println("rteceip NO of Payment ------- " + eElement.getElementsByTagName("ReceiptNo"));
                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        paymentFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    System.out.println("rteceip NO of Payment ------- " + eElement.getElementsByTagName("Date"));
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        paymentFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    System.out.println("rteceip NO of Payment ------- " + eElement.getElementsByTagName("Account"));
                    if (eElement.getElementsByTagName("Account").item(0) != null) {
                        paymentFormDTO.setAccountName(eElement.getElementsByTagName("Account").item(0).getTextContent());
                    }

                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (eElement.getElementsByTagName("LedgerTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("LedgerTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }

                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    if (node1 != null && nodeList1 != null) {
                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            System.out.println("RRRRRRRRR     in Payment --------- ");
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;
                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    System.out.println("1111111111          " + eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
//                        stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    System.out.println("1111111111          " + eElementEntity.getElementsByTagName("Amount").item(0).getTextContent());
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("CheckNo").item(0) != null) {
                                    stockItemTransactionDTO.setCheckNo((eElementEntity.getElementsByTagName("CheckNo").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Note").item(0) != null) {
                                    stockItemTransactionDTO.setNote(eElementEntity.getElementsByTagName("Note").item(0).getTextContent());
                                }

                                name = stockItemTransactionDTO.getName().toString();

                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                System.out.println("Size of stockitem_id_arraylist-- in Payment " + StockItemTransactionDTOList.size());
                            }
                        }
                    }

                    if (eElement.getElementsByTagName("FinalAmount").item(0) != null) {
                        paymentFormDTO.setFinalAmount(Double.parseDouble(eElement.getElementsByTagName("FinalAmount").item(0).getTextContent()));
                    }
                    paymentFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);

                    System.out.println("TNO" + paymentFormDTO.getReceiptNo());
                    paymentDTOList.add(paymentFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + paymentDTOList.size());

        return paymentDTOList;

    }

    public static List<PaymentDTO> getTallyPaymentDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        List<LedgerTransactionDTO> stockItemDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VOUCHER");
        for (int j = 0; j < nodeList.getLength(); j++) {
            Element e = (Element) nodeList.item(j);
            if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("Payment")) {

                Node node = nodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    PaymentDTO paymentFormDTO = new PaymentDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("VOUCHERNUMBER").item(0) != null) {
                        paymentFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("VOUCHERNUMBER").item(0).getTextContent()));
                    }

                    String date = "";
                    String string1 = "";
                    if (eElement.getElementsByTagName("DATE").item(0) != null) {
                        date = eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        string1 = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        paymentFormDTO.setDate(string1);
                    }
                    System.out.println("Date--->>>" + string1);



                    if (eElement.getElementsByTagName("PARTYLEDGERNAME").item(0) != null) {
                        paymentFormDTO.setAccountName(eElement.getElementsByTagName("PARTYLEDGERNAME").item(0).getTextContent());
                    }

                    NodeList nodeListOld = e.getElementsByTagName("ALLLEDGERENTRIES.LIST");
                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    for (int i = 0; i < nodeListOld.getLength(); i++) {
                        Node nodeLedgerEntries = nodeListOld.item(i);
                        if (nodeLedgerEntries.getNodeType() == Node.ELEMENT_NODE) {
                            LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                            Element eElementEntity = (Element) nodeLedgerEntries;
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("yes")) {
                                    if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                        paymentFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                    }
                                    System.out.println("FinalTotalAmount--->>>" + paymentFormDTO.getFinalAmount());
                                    if (eElementEntity.getElementsByTagName("LEDGERNAME").item(0) != null) {
                                        stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("LEDGERNAME").item(0).getTextContent());
                                    }

                                    if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                        stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent()));
                                    }

                                    NodeList tagExistsCheck = eElement.getElementsByTagName("NARRATION");
                                    if (tagExistsCheck.getLength() != 0) {
                                        if (eElement.getElementsByTagName("NARRATION").item(0) != null) {
                                            stockItemTransactionDTO.setNote(eElement.getElementsByTagName("NARRATION").item(0).getTextContent());
                                        }
                                    } else {
                                        stockItemTransactionDTO.setNote("");
                                    }

                                    StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                }
                            }
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("no")) { //Gives total amount
                                    if (eElementEntity.getElementsByTagName("AMOUNT").item(0) != null) {
                                        paymentFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                    }
                                }
                            }
                        }
                    }
                    paymentFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);
                    paymentDTOList.add(paymentFormDTO);
                }

            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + paymentDTOList.size());

        return paymentDTOList;

    }

    public static List<ContraDTO> getTallyContraDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        List<LedgerTransactionDTO> stockItemDTOList = new ArrayList<LedgerTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("VOUCHER");
        System.out.println("EEEEEEEE      -------- " + doc.getElementsByTagName("VOUCHER"));
        for (int j = 0; j < nodeList.getLength(); j++) {

            Element e = (Element) nodeList.item(j);
            if (e.hasAttribute("VCHTYPE") && e.getAttribute("VCHTYPE").equalsIgnoreCase("Contra")) {


                Node node = nodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ContraDTO contraFormDTO = new ContraDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("VOUCHERNUMBER").item(0) != null) {
                        contraFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("VOUCHERNUMBER").item(0).getTextContent()));
                    }
                    String date = "";
                    String string1 = "";
                    if (eElement.getElementsByTagName("DATE").item(0) != null) {
                        date = eElement.getElementsByTagName("DATE").item(0).getTextContent();
                        string1 = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        contraFormDTO.setDate(string1);
                    }

                    if (eElement.getElementsByTagName("PARTYLEDGERNAME").item(0) != null) {
                        contraFormDTO.setAccountName(eElement.getElementsByTagName("PARTYLEDGERNAME").item(0).getTextContent());
                    }

                    NodeList nodeListOld = e.getElementsByTagName("ALLLEDGERENTRIES.LIST");
                    List<LedgerTransactionDTO> StockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
                    for (int i = 0; i < nodeListOld.getLength(); i++) {
                        Node nodeLedgerEntries = nodeListOld.item(i);
                        if (nodeLedgerEntries.getNodeType() == Node.ELEMENT_NODE) {
                            LedgerTransactionDTO stockItemTransactionDTO = new LedgerTransactionDTO();
                            Element eElementEntity = (Element) nodeLedgerEntries;
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("yes")) {

                                    if (eElement.getElementsByTagName("AMOUNT").item(0) != null) {
                                        contraFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                    }
                                    System.out.println("FinalTotalAmount--->>>" + contraFormDTO.getFinalAmount());

                                    if (eElement.getElementsByTagName("LEDGERNAME").item(0) != null) {
                                        stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("LEDGERNAME").item(0).getTextContent());
                                    }

                                    if (eElement.getElementsByTagName("AMOUNT").item(0) != null) {
                                        stockItemTransactionDTO.setAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                    }
                                    NodeList tagExistsCheck = eElement.getElementsByTagName("NARRATION");
                                    if (tagExistsCheck.getLength() != 0) {
                                        if (eElement.getElementsByTagName("NARRATION").item(0) != null) {
                                            stockItemTransactionDTO.setNote(eElement.getElementsByTagName("NARRATION").item(0).getTextContent());
                                        }
                                    } else {
                                        stockItemTransactionDTO.setNote("");
                                    }

                                    StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                }
                            }
                            if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0) != null) {
                                if (eElementEntity.getElementsByTagName("ISDEEMEDPOSITIVE").item(0).getTextContent().equalsIgnoreCase("no")) { //Gives total amount
                                    contraFormDTO.setFinalAmount(Math.abs(Double.parseDouble(eElementEntity.getElementsByTagName("AMOUNT").item(0).getTextContent())));
                                }
                            }
                        }
                    }
                    contraFormDTO.setLedgerTransactionDTOList(StockItemTransactionDTOList);
                    contraDTOList.add(contraFormDTO);
                }

            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + contraDTOList.size());

        return contraDTOList;

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<ChalanDTO> getChalanDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException, ParseException {
        List<ChalanDTO> chalanDTOList = new ArrayList<ChalanDTO>();
        List<StockItemTransactionDTO> stockItemDTOList = new ArrayList<StockItemTransactionDTO>();

        Document doc = TagHelper2.getDocument(fXmlFile);
        doc.getDocumentElement().normalize();

//	NodeList nodeList = doc.getElementsByTagName("Chalan");
        Node chalanNode = null;
        NodeList chalanNodeList = null;
        if (doc.getElementsByTagName("ChalanVoucherList").item(0) != null) {
            chalanNode = doc.getElementsByTagName("ChalanVoucherList").item(0);
            chalanNodeList = chalanNode.getChildNodes();
        }

        System.out.println("cahlan ---------------");
        if (chalanNode != null && chalanNodeList != null) {
            for (int j = 0; j < chalanNodeList.getLength(); j++) {
                Node node = chalanNodeList.item(j);
                System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("Chalan------------------------");
                    ChalanDTO chalanFormDTO = new ChalanDTO();
                    Element eElement = (Element) node;

                    if (eElement.getElementsByTagName("ReceiptNo").item(0) != null) {
                        System.out.println("Chalan Receipt No ------------------------------" + eElement.getElementsByTagName("ReceiptNo"));
                        chalanFormDTO.setReceiptNo(Integer.parseInt(eElement.getElementsByTagName("ReceiptNo").item(0).getTextContent()));
                    }
                    if (eElement.getElementsByTagName("Date").item(0) != null) {
                        System.out.println("Chalan Receipt No ------------------------------" + eElement.getElementsByTagName("Date"));
                        chalanFormDTO.setDate(eElement.getElementsByTagName("Date").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("CashLedger").item(0) != null) {
                        System.out.println("Chalan Receipt No ------------------------------" + eElement.getElementsByTagName("CashLedger"));
                        chalanFormDTO.setCashLedger(eElement.getElementsByTagName("CashLedger").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("SaleLedger").item(0) != null) {
                        System.out.println("Chalan Receipt No ------------------------------" + eElement.getElementsByTagName("SaleLedger"));
                        chalanFormDTO.setSaleLedger(eElement.getElementsByTagName("SaleLedger").item(0).getTextContent());
                    }


                    Node node1 = null;
                    NodeList nodeList1 = null;
                    if (doc.getElementsByTagName("StockItemTransactionList").item(0) != null) {
                        node1 = eElement.getElementsByTagName("StockItemTransactionList").item(0);
                        nodeList1 = node1.getChildNodes();
                    }

                    List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();

                    if (node1 != null && nodeList1 != null) {
                        for (int k = 0; k < nodeList1.getLength(); k++) {
                            Node nodeEntity = nodeList1.item(k);
                            System.out.println("\nCURRENT ELEMENT :" + nodeEntity.getNodeName().toUpperCase());

                            if (nodeEntity.getNodeType() == Node.ELEMENT_NODE) {
                                StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                                Element eElementEntity = (Element) nodeEntity;
                                if (eElementEntity.getElementsByTagName("Name").item(0) != null) {
                                    stockItemTransactionDTO.setName(eElementEntity.getElementsByTagName("Name").item(0).getTextContent());
                                }
                                if (eElementEntity.getElementsByTagName("Length").item(0) != null) {
                                    stockItemTransactionDTO.setLength(Double.parseDouble(eElementEntity.getElementsByTagName("Length").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Width").item(0) != null) {
                                    stockItemTransactionDTO.setWidth(Double.parseDouble(eElementEntity.getElementsByTagName("Width").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Thickness").item(0) != null) {
                                    stockItemTransactionDTO.setThkness(Double.parseDouble(eElementEntity.getElementsByTagName("Thickness").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Quantity").item(0) != null) {
                                    stockItemTransactionDTO.setQuantity(Double.parseDouble(eElementEntity.getElementsByTagName("Quantity").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("SqFeet").item(0) != null) {
                                    stockItemTransactionDTO.setSqFeet(Double.parseDouble(eElementEntity.getElementsByTagName("SqFeet").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Rate").item(0) != null) {
                                    stockItemTransactionDTO.setRate(Double.parseDouble(eElementEntity.getElementsByTagName("Rate").item(0).getTextContent()));
                                }
                                if (eElementEntity.getElementsByTagName("Amount").item(0) != null) {
                                    stockItemTransactionDTO.setAmount(Double.parseDouble(eElementEntity.getElementsByTagName("Amount").item(0).getTextContent()));
                                }

                                name = stockItemTransactionDTO.getName().toString();
                                length = Double.parseDouble(stockItemTransactionDTO.getLength().toString());
                                width = Double.parseDouble(stockItemTransactionDTO.getWidth().toString());
                                thickness = Double.parseDouble(stockItemTransactionDTO.getThkness().toString());
                                unit = stockItemTransactionDTO.getQuantity().toString();
                                rate = Double.parseDouble(stockItemTransactionDTO.getRate().toString());
                                openingBalence = Double.parseDouble(stockItemTransactionDTO.getAmount().toString());

                                System.out.println("getLedgerDTOList Name--" + name);
                                System.out.println("getLedgerDTOList--length" + length);
                                System.out.println("getLedgerDTOList--width" + width);
                                System.out.println("getLedgerDTOList--unit" + unit);
                                System.out.println("getLedgerDTOList--thickness" + thickness);
                                System.out.println("getLedgerDTOList--rate" + rate);
                                System.out.println("getGroupDTOList--openingBalence" + openingBalence);
                                StockItemTransactionDTOList.add(stockItemTransactionDTO);
                                System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
                            }
                        }
                    }


                    chalanFormDTO.setStockItemTreansactioDTOList(StockItemTransactionDTOList);

                    if (eElement.getElementsByTagName("Note").item(0) != null) {
                        chalanFormDTO.setNote(eElement.getElementsByTagName("Note").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("DispatchDocNo").item(0) != null) {
                        chalanFormDTO.setDispatchDocNo(eElement.getElementsByTagName("DispatchDocNo").item(0).getTextContent());
                    }
                    if (eElement.getElementsByTagName("DispatchDocThrough").item(0) != null) {
                        chalanFormDTO.setDispatchDocThrough(eElement.getElementsByTagName("DispatchDocThrough").item(0).getTextContent());
                    }

                    if (eElement.getElementsByTagName("VatRate").item(0) != null) {
                        chalanFormDTO.setVatRate(Double.parseDouble(eElement.getElementsByTagName("VatRate").item(0).getTextContent()));
                    }

                    if (eElement.getElementsByTagName("VatAmount").item(0) != null) {
                        chalanFormDTO.setVatAmount(Double.parseDouble(eElement.getElementsByTagName("VatAmount").item(0).getTextContent()));
                    }

                    if (eElement.getElementsByTagName("LessBillAmount").item(0) != null) {
                        chalanFormDTO.setLessBillAmount(Double.parseDouble(eElement.getElementsByTagName("LessBillAmount").item(0).getTextContent()));
                    }

                    if (eElement.getElementsByTagName("Transport").item(0) != null) {
                        chalanFormDTO.setTransport(Double.parseDouble(eElement.getElementsByTagName("Transport").item(0).getTextContent()));
                    }

                    System.out.println("TNO" + chalanFormDTO.getReceiptNo());
                    System.out.println("TDATE" + chalanFormDTO.getDate());
                    System.out.println("Saleaccount" + chalanFormDTO.getCashLedger());
                    System.out.println("saleLedger" + chalanFormDTO.getSaleLedger());

                    chalanDTOList.add(chalanFormDTO);


                }
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + chalanDTOList.size());
        return chalanDTOList;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public static List<StockItemTransactionDTO> getStockItemTransactionDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<StockItemTransactionDTO> StockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("StockItemList");

        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                StockItemTransactionDTO stockItemTransactionDTO = new StockItemTransactionDTO();
                Element eElement = (Element) node;
                stockItemTransactionDTO.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                // stockItemTransactionDTO.setStockItemAlias(eElement.getElementsByTagName("Alias").item(0).getTextContent());
                // stockItemTransactionDTO.setStockItemUnder(eElement.getElementsByTagName("Under").item(0).getTextContent());
                stockItemTransactionDTO.setLength(Double.parseDouble(eElement.getElementsByTagName("Length").item(0).getTextContent()));
                stockItemTransactionDTO.setWidth(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent()));
                stockItemTransactionDTO.setThkness(Double.parseDouble(eElement.getElementsByTagName("Thickness").item(0).getTextContent()));
                //stockItemTransactionDTO.setStockItemUnit(eElement.getElementsByTagName("StockItemUnit").item(0).getTextContent());
                stockItemTransactionDTO.setQuantity(Double.parseDouble(eElement.getElementsByTagName("Quantity").item(0).getTextContent()));
                stockItemTransactionDTO.setSqFeet(Double.parseDouble(eElement.getElementsByTagName("SqFeet").item(0).getTextContent()));
                stockItemTransactionDTO.setRate(Double.parseDouble(eElement.getElementsByTagName("Rate").item(0).getTextContent()));
                stockItemTransactionDTO.setAmount(Double.parseDouble(eElement.getElementsByTagName("Amount").item(0).getTextContent()));



                name = stockItemTransactionDTO.getName().toString();


                // alias = stockItemTransactionDTO.getStockItemAlias().toString();
                //  under = stockItemTransactionDTO.getStockItemUnder().toString();
                length = Double.parseDouble(stockItemTransactionDTO.getLength().toString());
                width = Double.parseDouble(stockItemTransactionDTO.getWidth().toString());
                thickness = Double.parseDouble(stockItemTransactionDTO.getThkness().toString());
                unit = stockItemTransactionDTO.getQuantity().toString();
                rate = Double.parseDouble(stockItemTransactionDTO.getRate().toString());
                openingBalence = Double.parseDouble(stockItemTransactionDTO.getAmount().toString());

                System.out.println("Name--" + name);
                //  System.out.println("getLedgerDTOList--"+alias);
                //  System.out.println("getLedgerDTOList--"+under);
                System.out.println("getLedgerDTOList--" + length);
                System.out.println("getLedgerDTOList--" + width);
                System.out.println("getLedgerDTOList--" + unit);
                System.out.println("getLedgerDTOList--" + thickness);
                System.out.println("getLedgerDTOList--" + rate);
                System.out.println("getGroupDTOList--" + openingBalence);



                StockItemTransactionDTOList.add(stockItemTransactionDTO);


                System.out.println("Size of stockitem_id_arraylist--" + StockItemTransactionDTOList.size());
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + StockItemTransactionDTOList.size());

        return StockItemTransactionDTOList;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<LedgerTransactionDTO> getLedgerTransactionDTOList(File fXmlFile) throws ParserConfigurationException, SAXException, IOException {
        List<LedgerTransactionDTO> LedgerTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
        Document doc = TagHelper2.getDocument(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("ReceiptLedger");

        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            System.out.println("\nCURRENT ELEMENT :" + node.getNodeName().toUpperCase());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                LedgerTransactionDTO ledgerTransactionDTO = new LedgerTransactionDTO();
                Element eElement = (Element) node;
                ledgerTransactionDTO.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                ledgerTransactionDTO.setCheckNo(eElement.getElementsByTagName("CheckNo").item(0).getTextContent());
                ledgerTransactionDTO.setNote(eElement.getElementsByTagName("Note").item(0).getTextContent());
//                ledgerTransactionDTO.setQuantity(Double.parseDouble(eElement.getElementsByTagName("Quantity").item(0).getTextContent()));
                ledgerTransactionDTO.setAmount(Double.parseDouble(eElement.getElementsByTagName("LedgerAmount").item(0).getTextContent()));

                name = ledgerTransactionDTO.getName().toString();
                thickness = Double.parseDouble(ledgerTransactionDTO.getCheckNo().toString());
                unit = ledgerTransactionDTO.getQuantity().toString();
                String note = ledgerTransactionDTO.getNote().toString();
                openingBalence = Double.parseDouble(ledgerTransactionDTO.getAmount().toString());

                System.out.println("Name--" + name);
                System.out.println("getLedgerDTOList--Quantity---->" + unit);
                System.out.println("getLedgerDTOList--CheckNO--" + thickness);
                System.out.println("getLedgerDTOList--Note---->" + note);
                System.out.println("getGroupDTOList--Amount---->" + openingBalence);



                LedgerTransactionDTOList.add(ledgerTransactionDTO);


                System.out.println("Size of stockitem_id_arraylist--" + LedgerTransactionDTOList.size());
            }
        }
        System.out.println("ReadXMLFile-->>getLedgerDTOList()-->>ledgerDTOList.size()-->>" + LedgerTransactionDTOList.size());

        return LedgerTransactionDTOList;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void insertGroup(List<GroupDTO> GroupDTOList) throws SQLException {

        System.out.println("Insert function called");
        Connection conn = DatabaseConnection1.GetConnection();
        String q = "insert into tblgroup(group_id,group_name,group_alias,group_under)values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q);
        for (GroupDTO groupDTO : GroupDTOList) {

            ps.setInt(1, Integer.parseInt(groupDTO.getGroup_id().toString()));
            ps.setString(2, groupDTO.getGroup_Name().toString());
            ps.setString(3, groupDTO.getGroup_Alias().toString());
            ps.setInt(4, Integer.parseInt(groupDTO.getGroup_Under()));
            System.out.println("UNDER" + groupDTO.getGroup_Under());
            ps.addBatch();
            //ps.executeUpdate();
        }
        ps.executeBatch();
        System.out.println("GroupDTO Size" + GroupDTOList.size());
        System.out.println("Insert function END");
    }

    private static void insertLedger(List<LedgerDTO> LedgerDTOList) throws SQLException {

        System.out.println("Insert function for ledger called");
        Connection conn = DatabaseConnection1.GetConnection();
        Statement st = conn.createStatement();
        Statement st1 = conn.createStatement();
        String q = "insert into tblledger(ledger_name, ledger_under, ledger_address, ledger_contactNo, ledger_emailId, ledger_openingBalance, ledger_alias, ledger_inTaxo, ledger_saleTaxNo)values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
        for (LedgerDTO ledgerDTO : LedgerDTOList) {
            ps.setString(1, ledgerDTO.getLedger_Name().toString());
            System.out.println("IIIIIIIII       " + ledgerDTO.getLedger_Under().toString());
            ps.setInt(2, Integer.parseInt(ledgerDTO.getLedger_Under().toString()));
            ps.setString(3, ledgerDTO.getLedger_Address().toString());
            ps.setString(4, ledgerDTO.getLedger_ContactNo().toString());
            ps.setString(5, ledgerDTO.getLedger_EmailID().toString());
            ps.setDouble(6, Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()));
            ps.setString(7, ledgerDTO.getLedger_Alias().toString());
            ps.setString(8, ledgerDTO.getLedger_IncomeTaxNo().toString());
            ps.setString(9, ledgerDTO.getLedger_SaleTaxNo().toString());


            System.out.println("UNDER" + ledgerDTO.getLedger_Under());
            ps.addBatch();

            //ps.executeUpdate();
        }
        ps.executeBatch();
        ResultSet rs = ps.getGeneratedKeys();
        List<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }

        //insert other table data
        int i = 0;
        for (LedgerDTO ledgerDTO : LedgerDTOList) {
            Integer fk = idList.get(i);
            System.out.println("Ledger FK++++" + fk);
            String query = "insert into tblledgercreditlimit(ledger_id,ledger_limit)values(" + fk + "," + Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString()) + ")";
            st.executeUpdate(query);
            System.out.println("Ledger Data added into tblledgercreditlimit--->" + Double.parseDouble(ledgerDTO.getLedger_CreditLimit().toString()));
            String query1 = "";
            if (ledgerDTO.getDebitOrCredit() == 2) {
                query1 = "insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + fk + "," + Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()) + ",2)";  //2 for credit and 1 for debit
                st1.executeUpdate(query1);
            } else {
                query1 = "insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + fk + "," + Double.parseDouble(ledgerDTO.getLedger_OpeningBalence().toString()) + ",1)";
                st1.executeUpdate(query1);
            }
            i++;
        }



        System.out.println("Insert function END");

    }

    private static void insertStockGroup(List<StockGroupDTO> StockGroupDTOList) throws SQLException {

        Connection conn = DatabaseConnection1.GetConnection();
        String q = "insert into tblstockgroup(sg_name,sg_alias,sg_under)values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q);
        for (StockGroupDTO stockgroupDTO : StockGroupDTOList) {

            ps.setString(1, stockgroupDTO.getStockGroupName().toString());
            ps.setString(2, stockgroupDTO.getStockGroupAlias().toString());
            ps.setInt(3, Integer.parseInt(stockgroupDTO.getStockGroupUnder()));
            System.out.println("UNDER" + stockgroupDTO.getStockGroupUnder());
            ps.addBatch();
        }
        ps.executeBatch();
        System.out.println("Insert function END");
    }

    private static void insertUnitOfMeasure(List<UnitOfMeasureDTO> UnitOfMeasureDTOList) throws SQLException {

        Connection conn = DatabaseConnection1.GetConnection();
        String q = "insert into tblunitofmeasure(uomType_id,uom_type,uom_symbol,uom_formalName,uom_noOfDecimalPts)values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q);
        for (UnitOfMeasureDTO unitofmeasureDTO : UnitOfMeasureDTOList) {
            ps.setString(1, "" + 2);
            ps.setString(2, unitofmeasureDTO.getUnitOfMeasure_Type().toString());
            ps.setString(3, unitofmeasureDTO.getUnitOfMeasure_Symbol().toString());
            ps.setString(4, unitofmeasureDTO.getUnitOfMeasure_FormalName().toString());
            ps.setInt(5, Integer.parseInt(unitofmeasureDTO.getUnitOfMeasure_NoDecimalPlaces()));
            ps.addBatch();
        }
        ps.executeBatch();
        System.out.println("Insert function END");

    }

    private static void insertStockItem(List<StockItemDTO> StockItemDTOList) throws SQLException {

        System.out.println("Insert function for Stock Item called");
        Connection conn = DatabaseConnection1.GetConnection();
        Statement st1 = conn.createStatement();

        String q = "insert into tblstockitem(si_name,si_alias,si_under,si_rate,si_unitOfMeasure,si_openingBalance,si_length,si_width,si_thickness) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
        for (StockItemDTO stockItemDTO : StockItemDTOList) {

            ps.setString(1, stockItemDTO.getName().toString());
            ps.setString(2, stockItemDTO.getAlias().toString());
            System.out.println("5555555555" + stockItemDTO.getUnder().toString());
            ps.setInt(3, Integer.parseInt(stockItemDTO.getUnder().toString()));
            ps.setDouble(7, Double.parseDouble(stockItemDTO.getLength().toString()));
            ps.setDouble(8, Double.parseDouble(stockItemDTO.getWidth().toString()));
            ps.setDouble(9, Double.parseDouble(stockItemDTO.getThkness().toString()));
            System.out.println("unit value in staock item ---- " + stockItemDTO.getUnit().toString());
            ps.setInt(5, Integer.parseInt(stockItemDTO.getUnit().toString()));
            ps.setDouble(4, Double.parseDouble(stockItemDTO.getRate().toString()));
            ps.setDouble(6, Double.parseDouble(stockItemDTO.getOpeningBal().toString()));

            System.out.println("UNDER" + stockItemDTO.getUnder());
            ps.addBatch();

            //ps.executeUpdate();
        }
        ps.executeBatch();
        ResultSet rs = ps.getGeneratedKeys();
        List<Integer> idList = new ArrayList<Integer>();
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
        int i = 0;
        for (StockItemDTO stockItemDTO : StockItemDTOList) {
            Integer fk = idList.get(i);

            String query1 = "";
            query1 = "insert into tblStockItemCurrentBalance(si_id,si_currentBalance,si_DebitOrCredit) values(" + fk + "," + stockItemDTO.getOpeningBal() + ",2)";  //2 for credit and 1 for debit
            st1.executeUpdate(query1);

            i++;
        }

    }

    public static void insert_All_VoucherTransaction(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, gen.ImpExp.EnumAction actionEnum, Map<String, Set<String>> user_Selected_ReceiptNoMap) {
        try {
//	    System.out.println("Enum Value in insertion ----------------- " + actionEnum);
//	    Map<String, Set<String>> mapValueDeletion = getTrans_id(mapValueexitsInDatabase);
//	    if (mapValueDeletion.size() > 0 && mapValueDeletion.item(0)   != null) {
//		deleteTransaction(mapValueDeletion);
//	    }

//	    if (actionEnum.name().equalsIgnoreCase(actionEnum.OVERRIDE.toString())) {
            System.out.println("Insert key SALE -------------" + returnMapFromXml.containsKey(gen.dto.Constants.SALE_TYPE_INDEX.toString()));
            System.out.println("Insert key PURCHASE_TYPE_INDEX -------------" + returnMapFromXml.containsKey(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()));
            System.out.println("Insert key RECEIPT -------------" + returnMapFromXml.containsKey(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()));
            System.out.println("Insert key PAYMENT_TYPE_INDEX -------------" + returnMapFromXml.containsKey(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()));
            System.out.println("Insert key Chalan -------------" + returnMapFromXml.containsKey(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()));

            System.out.println("ttttttttttttt         " + returnMapFromXml.containsKey(gen.dto.Constants.SALE_TYPE_INDEX.toString()));
            System.out.println("ttttttttttttt         " + returnMapFromXml.containsKey(gen.dto.Constants.SALE_TYPE_INDEX));
            if (returnMapFromXml.containsKey(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
                insertSaleVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
            if (returnMapFromXml.containsKey(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString())) {
                insertPurchaseVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
            if (returnMapFromXml.containsKey(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString())) {
                insertReceiptVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
            if (returnMapFromXml.containsKey(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString())) {
                insertPaymentVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
            if (returnMapFromXml.containsKey(gen.dto.Constants.CHALAN_TYPE_INDEX.toString())) {
                System.out.println("Insert into chalan ---------------");
                insertChalanVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
            if (returnMapFromXml.containsKey(gen.dto.Constants.CONTRA_TYPE_INDEX.toString())) {
                insertContraVoucher(returnMapFromXml, mapValueexitsInDatabase, user_Selected_ReceiptNoMap);
            }
//	    }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagHelper2.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(TagHelper2.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void insertSaleVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {
        System.out.println("Insert function for Sale Vouchers called");

        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
            saleDTOList.add((SaleDTO) object);
        }

        SaleDAO.insertSaleVoucher(saleDTOList);


//        Connection conn = DatabaseConnection1.GetConnection();
//
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_transport,trans_lessBillAmt) values(?,?,?,?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
//	for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//            ps.setString(1, saleDTO.getReceiptNo().toString());
//	    System.out.println("Receipt no --------- "+saleDTO.getReceiptNo().toString());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(saleDTO.getDate().trim());
//
//	    DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            ps.setInt(3, Constants.SALE_TYPE_INDEX);
//            ps.setString(4, saleDTO.getNote().toString());
//            ps.setDouble(5, Math.abs(Double.parseDouble(saleDTO.getFinalAmount().toString())));
//            ps.setDouble(6, Double.parseDouble(saleDTO.getTransport().toString()));
//            ps.setDouble(7, Double.parseDouble(saleDTO.getLessBillAmount().toString()));
//
//            ps.addBatch();
//
//        }
//
//	if (!returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values().isEmpty()) {
//        ps.executeBatch();
//	}
//
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//
//        int i = 0;
//        String query1 = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
//        PreparedStatement ps1 = conn.prepareStatement(query1);
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment) values(?,?,?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
////	for (SaleDTO saleDTO : saleFormDTOList) {
//	for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//            Integer fk = idList.get(i);
//
//            ps1.setInt(1, fk);
//            ps1.setDouble(2, saleDTO.getVatRate());
//            ps1.setDouble(3, saleDTO.getVatAmount());
//
//            ps1.addBatch();
//
//            ps2.setInt(1, fk);
//            ps2.setString(2, saleDTO.getDispatchDocThrough());
//            ps2.setString(3, saleDTO.getDispatchDocNo());
//            if (controlConstantImpExp == 1) {
//                ps2.setDouble(4, Math.abs(saleDTO.getAmount()));
//            } else {
//                ps2.setDouble(4, Math.abs(saleDTO.getFinalAmount()));
//            }
//            ps2.setString(5, "" + 0);
//            ps2.setString(6, saleDTO.getPaymentMode());
//
//            ps2.addBatch();
//            i++;
//        }
//	if (!returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values().isEmpty()) {
//        ps1.executeBatch();
//        ps2.executeBatch();
//	}
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt) values(?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (SaleDTO saleDTO : saleFormDTOList) {
//	for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//            Integer fk = idList.get(i);
//            ps6.setInt(1, fk);
//            ps6.setInt(2, Integer.parseInt(saleDTO.getCashLedger()));
//            ps6.setInt(3, Constants.CREDIT);      //1 for credit
//            ps6.setInt(4, 0);      //index of ledger on form
//            ps6.setDouble(5, Math.abs(saleDTO.getFinalAmount()));
//            ps6.addBatch();
//
//            ps6.setInt(1, fk);
//            ps6.setInt(2, Integer.parseInt(saleDTO.getSaleLedger().toString()));
//            ps6.setInt(3, Constants.DEBIT);      //1 for credit
//            ps6.setInt(4, 1);      //index of ledger on form
//            ps6.setDouble(5, Math.abs(saleDTO.getFinalAmount()));
//            ps6.addBatch();
//            i++;
//
//        }
//	if (!returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values().isEmpty()) {
//        ps6.executeBatch();
//	}
//        ps6.close();
//
//        i = 0;
//        String query3 = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
//        PreparedStatement ps3 = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
////	for (SaleDTO saleDTO : saleFormDTOList) {
//	for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//            Integer fk = idList.get(i);
//            ps3.setInt(1, fk);
//            ps3.setString(2, "" + 1);
//            ps3.addBatch();
//            i++;
//        }
//	if (!returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values().isEmpty()) {
//        ps3.executeBatch();
//	}
//
//        ResultSet rs1 = ps3.getGeneratedKeys();
//        List<Integer> idList1 = new ArrayList<Integer>();
//        while (rs1.next()) {
//            idList1.add(rs1.getInt(1));
//        }
//        ps3.close();
//        i = 0;
//        String query4 = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement ps4 = conn.prepareStatement(query4);
////	for (SaleDTO saleDTO : saleFormDTOList) {
//	for (Object object : returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values()) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//            Integer fk = idList1.get(i);
//            int k = 0;
//            for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {
//
//                ps4.setInt(1, fk);
//                ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
//                ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
//                ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
//                ps4.setDouble(5, Math.abs(Double.parseDouble(stockItemTransactionDTO.getAmount().toString())));
//                ps4.setDouble(6, Double.parseDouble(stockItemTransactionDTO.getSqFeet().toString()));
//                ps4.setInt(7, k);
//                ps4.setDouble(8, Double.parseDouble(stockItemTransactionDTO.getMaking().toString()));
//                ps4.setDouble(9, Double.parseDouble(stockItemTransactionDTO.getLength().toString()));
//                ps4.setDouble(10, Double.parseDouble(stockItemTransactionDTO.getWidth().toString()));
//                ps4.setDouble(11, Double.parseDouble(stockItemTransactionDTO.getThkness().toString()));
//
//                ps4.addBatch();
//                k++;
//            }
//            i++;
//        }
//	if (!returnMapFromXml.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()).values().isEmpty()) {
//        ps4.executeBatch();
//	}
//        ps4.close();
//	}
    }

    private static void insertPurchaseVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {

        System.out.println("Insert function for Purcahse Vouchers called");

        List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
            purchaseDTOList.add((PurchaseDTO) object);
        }

        gen.accountvoucher.purchase.PurchaseDAO.insertPurchaseVoucher(purchaseDTOList);

//        Connection conn = DatabaseConnection1.GetConnection();
//        Statement st = conn.createStatement();
//        Statement st1 = conn.createStatement();
//
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration,trans_grandtotal,trans_payment) values(?,?,?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
////	for (PurchaseDTO purchaseDTO : purchaseFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
//            PurchaseDTO purchaseDTO = (PurchaseDTO) object;
//            System.out.println("Receipt no --------- " + purchaseDTO.getReceiptNo().toString());
//            System.out.println("Receipt Number----->" + purchaseDTO.getReceiptNo().toString());
//            ps.setString(1, purchaseDTO.getReceiptNo().toString());
//            System.out.println("Transaction Date----->" + purchaseDTO.getDate());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(purchaseDTO.getDate().trim());
//
//            //dateNow = f.format(dat.getDate());
//            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            //ps.setString(2, saleDTO.getDate());
//            ps.setInt(3, Constants.PURCHASE_TYPE_INDEX);
//            System.out.println("Note----->" + purchaseDTO.getNote().toString());
//            ps.setString(4, purchaseDTO.getNote().toString());
//            System.out.println("FinalAmount----->" + Double.parseDouble(purchaseDTO.getFinalAmount().toString()));
//            ps.setDouble(5, Math.abs(Double.parseDouble(purchaseDTO.getFinalAmount().toString())));
//            ps.setString(6, purchaseDTO.getPaymentMode().toString());
//            ps.addBatch();
//
//            //ps.executeUpdate();
//        }
//        System.out.println("psssssssssss        " + ps);
//        if (!returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps.executeBatch();
//        }
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//
//        int i = 0;
//        String query1 = "insert into tblTransactionVat(trans_id,vat_rate,vat_amt) values(?,?,?)";
//        PreparedStatement ps1 = conn.prepareStatement(query1);
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
////	for (PurchaseDTO purchaseDTO : purchaseFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
//            PurchaseDTO purchaseDTO = (PurchaseDTO) object;
//            //Integer fk = idList.get(i);
//            Integer fk = idList.get(i);
//
//            System.out.println("tblTransactionOtherDetails------------------>>>>" + fk);
//
//            ps1.setInt(1, fk);
//            ps1.setDouble(2, purchaseDTO.getVatRate());
//            ps1.setDouble(3, purchaseDTO.getVatAmount());
//
//            ps1.addBatch();
//
//
//            System.out.println("Done PS1");
//
//            ps2.setInt(1, fk);
//
//            if (controlConstantImpExp == 1) {
        //ps2.setDouble(2, Math.abs(purchaseDTO.getAmount()));
//            } else {
//                ps2.setDouble(2, Math.abs(purchaseDTO.getFinalAmount()));
//            }
//            //ps2.setDouble(2, Math.abs(purchaseDTO.getAmount()));
//            ps2.setString(3, purchaseDTO.getPurchaseReference());
//            ps2.setString(4, "" + 0);
//
//            ps2.addBatch();
//
//
//            System.out.println("Done PS2");
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps1.executeBatch();
//            ps2.executeBatch();
//        }
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt) values(?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (PurchaseDTO purchaseDTO : purchaseFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
//            PurchaseDTO purchaseDTO = (PurchaseDTO) object;
//            Integer fk = idList.get(i);
//            ps6.setInt(1, fk);
////            ps6.setInt(2, Integer.parseInt(purchaseDTO.getCashLedger().toString()));
//            ps6.setInt(2, Integer.parseInt(purchaseDTO.getPurchaseLedger().toString()));
//            ps6.setInt(3, Constants.CREDIT);      //1 for credit
//            ps6.setInt(4, 0);      //index of ledger on form
//            ps6.setDouble(5, Math.abs(purchaseDTO.getFinalAmount()));
//            ps6.addBatch();
//            ps6.setInt(1, fk);
////            ps6.setInt(2, Integer.parseInt(purchaseDTO.getPurchaseLedger().toString()));
//            ps6.setInt(2, Integer.parseInt(purchaseDTO.getCashLedger().toString()));
//            ps6.setInt(3, Constants.DEBIT);      //1 for credit
//            ps6.setInt(4, 1);      //index of ledger on form
//            ps6.setDouble(5, Math.abs(purchaseDTO.getFinalAmount()));
//            ps6.addBatch();
//
//            i++;
//
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps6.executeBatch();
//        }
//        ps6.close();
//        i = 0;
//        String query3 = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
//        PreparedStatement ps3 = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
////	for (PurchaseDTO purchaseDTO : purchaseFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
//            PurchaseDTO purchaseDTO = (PurchaseDTO) object;
//            Integer fk = idList.get(i);
//            ps3.setInt(1, fk);
//            ps3.setString(2, "" + 2);
//            ps3.addBatch();
//            i++;
//            System.out.println("Done PS3");
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps3.executeBatch();
//        }
//        ResultSet rs1 = ps3.getGeneratedKeys();
//        List<Integer> idList1 = new ArrayList<Integer>();
//        while (rs1.next()) {
//            idList1.add(rs1.getInt(1));
//        }
//        ps3.close();
//        i = 0;
//        String query4 = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement ps4 = conn.prepareStatement(query4);
////	for (PurchaseDTO purchaseDTO : purchaseFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values()) {
//            PurchaseDTO purchaseDTO = (PurchaseDTO) object;
//            Integer fk = idList1.get(i);
//            System.out.println("rerertrrretretrere" + Integer.parseInt(purchaseDTO.getReceiptNo().toString()));
//            int k = 0;
//            for (StockItemTransactionDTO stockItemTransactionDTO : purchaseDTO.getStockItemTreansactioDTOList()) {
//
//                ps4.setInt(1, fk);
//                ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
//                ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
//                ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
//                ps4.setDouble(5, Math.abs(Double.parseDouble(stockItemTransactionDTO.getAmount().toString())));
//                ps4.setDouble(6, Double.parseDouble(stockItemTransactionDTO.getSqFeet().toString()));
//                ps4.setInt(7, k);
//                ps4.setDouble(8, Double.parseDouble(stockItemTransactionDTO.getMaking().toString()));
//                ps4.setDouble(9, Double.parseDouble(stockItemTransactionDTO.getLength().toString()));
//                ps4.setDouble(10, Double.parseDouble(stockItemTransactionDTO.getWidth().toString()));
//                ps4.setDouble(11, Double.parseDouble(stockItemTransactionDTO.getThkness().toString()));
//
//                ps4.addBatch();
//                k++;
//            }
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps4.executeBatch();
//        }
//        ps4.close();
    }

    private static void insertReceiptVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {

        System.out.println("Insert function for Receipt Vouchers called");

        List<ReceiptDTO> receiptDTOList = new ArrayList<ReceiptDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values()) {
            receiptDTOList.add((ReceiptDTO) object);
        }

        gen.accountvoucher.receipt.ReceiptDAO.insertReceiptVoucher(receiptDTOList);

//        System.out.println("Insert function for Receipt Vouchers called");
//        Connection conn = DatabaseConnection1.GetConnection();
//        Statement st = conn.createStatement();
//        Statement st1 = conn.createStatement();
//
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
////	for (ReceiptDTO receiptDTO : receiptFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values()) {
//            ReceiptDTO receiptDTO = (ReceiptDTO) object;
//            System.out.println("Receipt Number----->" + receiptDTO.getReceiptNo().toString());
//            ps.setString(1, receiptDTO.getReceiptNo().toString());
//            System.out.println("Transaction Date----->" + receiptDTO.getDate());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(receiptDTO.getDate().trim());
//            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            ps.setInt(3, Constants.RECEIPT_TYPE_INDEX);
//            ps.setDouble(4, Math.abs(Double.parseDouble(receiptDTO.getFinalAmount().toString())));
//            ps.addBatch();
//
//            //ps.executeUpdate();
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps.executeBatch();
//        }
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//
//        int i = 0;
//
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values()) {
//            ReceiptDTO receiptDTO = (ReceiptDTO) object;
//            Integer fk = idList.get(i);
//            ps2.setInt(1, fk);
//            ps2.setDouble(2, Math.abs(receiptDTO.getFinalAmount()));
//            ps2.setString(3, "" + 0);
//            ps2.setString(4, "" + 0);
//
//            ps2.addBatch();
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps2.executeBatch();
//        }
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (ReceiptDTO receiptDTO : receiptFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values()) {
//            ReceiptDTO receiptDTO = (ReceiptDTO) object;
//            Integer fk = idList.get(i);
//            int k = 1;
//            for (LedgerTransactionDTO ledgerTransactioDTO : receiptDTO.getLedgerTransactionDTOList()) {
//
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(receiptDTO.getAccountName()));
//                ps6.setInt(3, Constants.CREDIT);      //1 for credit
//                ps6.setInt(4, k);      //index of ledger on form
//                ps6.setDouble(5, Math.abs(ledgerTransactioDTO.getAmount()));
//                ps6.setString(6, ledgerTransactioDTO.getNote());
//                ps6.setString(7, ledgerTransactioDTO.getCheckNo());
//                ps6.addBatch();
//
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(ledgerTransactioDTO.getName().toString()));
//                ps6.setInt(3, Constants.DEBIT);      //1 for credit
//                ps6.setInt(4, k);      //index of ledger on form
//                ps6.setDouble(5, Math.abs(ledgerTransactioDTO.getAmount()));
//                ps6.setString(6, ledgerTransactioDTO.getNote());
//                ps6.setString(7, ledgerTransactioDTO.getCheckNo());
//                ps6.addBatch();
//                k++;
//            }
//
//            i++;
//
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps6.executeBatch();
//        }
//        ps6.close();
    }

    private static void insertContraVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {

        System.out.println("Insert function for Contra Vouchers called");

        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values()) {
            contraDTOList.add((ContraDTO) object);
        }

        gen.accountvoucher.contra.ContraDAO.insertContraVoucher(contraDTOList);

//        System.out.println("Insert function for Contra Vouchers called");
//        Connection conn = DatabaseConnection1.GetConnection();
//        Statement st = conn.createStatement();
//        Statement st1 = conn.createStatement();
//
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
////	for (ContraDTO contraDTO : contraFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values()) {
//            ContraDTO contraDTO = (ContraDTO) object;
//            ps.setString(1, contraDTO.getReceiptNo().toString());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(contraDTO.getDate().trim());
//            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            ps.setInt(3, Constants.CONTRA_TYPE_INDEX);
//            ps.setDouble(4, Double.parseDouble(contraDTO.getFinalAmount().toString()));
//            ps.addBatch();
//
//            //ps.executeUpdate();
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps.executeBatch();
//        }
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//
//        int i = 0;
//
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
////	for (ContraDTO contraDTO : contraFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values()) {
//            ContraDTO contraDTO = (ContraDTO) object;
//            Integer fk = idList.get(i);
//            ps2.setInt(1, fk);
//            ps2.setDouble(2, contraDTO.getFinalAmount());
//            ps2.setString(3, "" + 0);
//            ps2.setString(4, "" + 0);
//
//            ps2.addBatch();
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps2.executeBatch();
//        }
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_checkNo,trans_narration) values(?,?,?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (ContraDTO contraDTO : contraFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values()) {
//            ContraDTO contraDTO = (ContraDTO) object;
//            Integer fk = idList.get(i);
//            int k = 1;
//            for (LedgerTransactionDTO ledgerTransactioDTO : contraDTO.getLedgerTransactionDTOList()) {
//
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(contraDTO.getAccountName()));
//                ps6.setInt(3, Constants.CREDIT);      //1 for credit
//                ps6.setInt(4, k);      //index of ledger on form
//                ps6.setDouble(5, ledgerTransactioDTO.getAmount());
//                ps6.setString(6, ledgerTransactioDTO.getCheckNo());
//                ps6.setString(7, ledgerTransactioDTO.getNote());
//                ps6.addBatch();
//
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(ledgerTransactioDTO.getName().toString()));
//                ps6.setInt(3, Constants.DEBIT);      //1 for credit
//                ps6.setInt(4, k);      //index of ledger on form
//                ps6.setDouble(5, ledgerTransactioDTO.getAmount());
//                ps6.setString(6, ledgerTransactioDTO.getCheckNo());
//                ps6.setString(7, ledgerTransactioDTO.getNote());
//                ps6.addBatch();
//                k++;
//            }
//
//            i++;
//
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps6.executeBatch();
//        }
//        ps6.close();
    }

    private static void insertPaymentVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {

        System.out.println("Insert function for Payment Vouchers called");

        List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values()) {
            paymentDTOList.add((PaymentDTO) object);
        }
        gen.accountvoucher.payment.PaymentDAO.insertPaymentVoucher(paymentDTOList);

//        Connection conn = DatabaseConnection1.GetConnection();
//
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_grandtotal) values(?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
////	for (PaymentDTO paymentDTO : paymentFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values()) {
//            PaymentDTO paymentDTO = (PaymentDTO) object;
//            ps.setString(1, paymentDTO.getReceiptNo().toString());
//            System.out.println("Receipt no ;;;;;; " + paymentDTO.getReceiptNo().toString());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(paymentDTO.getDate().trim());
//            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            ps.setInt(3, Constants.PAYMENT_TYPE_INDEX);
//            ps.setDouble(4, Math.abs(Double.parseDouble(paymentDTO.getFinalAmount().toString())));
//            ps.addBatch();
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps.executeBatch();
//        }
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//
//        int i = 0;
//
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_amt,trans_reference,trans_payment) values(?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
////	for (PaymentDTO paymentDTO : paymentFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values()) {
//            PaymentDTO paymentDTO = (PaymentDTO) object;
//            Integer fk = idList.get(i);
//            ps2.setInt(1, fk);
//            ps2.setDouble(2, Math.abs(paymentDTO.getFinalAmount()));
//            ps2.setString(3, "" + 0);
//            ps2.setString(4, "" + 0);
//
//            ps2.addBatch();
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps2.executeBatch();
//        }
//
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt,trans_narration,trans_checkNo) values(?,?,?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (PaymentDTO paymentDTO : paymentFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values()) {
//            PaymentDTO paymentDTO = (PaymentDTO) object;
//            Integer fk = idList.get(i);
//            int k = 1;
//            System.out.println("Find ----------------------   " + paymentDTO.getLedgerTransactionDTOList().size());
//            for (LedgerTransactionDTO ledgerTransactioDTO : paymentDTO.getLedgerTransactionDTOList()) {
//                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYY" + fk);
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(paymentDTO.getAccountName()));
//                ps6.setInt(3, Constants.DEBIT);      //1 for credit
//                ps6.setInt(4, 0);      //index of ledger on form
//                ps6.setDouble(5, Math.abs(ledgerTransactioDTO.getAmount()));
//                ps6.setString(6, ledgerTransactioDTO.getNote());
//                ps6.setString(7, ledgerTransactioDTO.getCheckNo());
//                ps6.addBatch();
//
//
//                ps6.setInt(1, fk);
//                ps6.setInt(2, Integer.parseInt(ledgerTransactioDTO.getName().toString()));
//                ps6.setInt(3, Constants.CREDIT);      //1 for credit
//                ps6.setInt(4, k);      //index of ledger on form
//                ps6.setDouble(5, Math.abs(ledgerTransactioDTO.getAmount()));
//                ps6.setString(6, ledgerTransactioDTO.getNote());
//                ps6.setString(7, ledgerTransactioDTO.getCheckNo());
//                ps6.addBatch();
//                k++;
//            }
//
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps6.executeBatch();
//        }
//        ps6.close();
    }

    private static void insertChalanVoucher(Map<String, Map<String, Object>> returnMapFromXml, Map<String, Set<String>> mapValueexitsInDatabase, Map<String, Set<String>> user_Selected_ReceiptNoMap) throws SQLException, Exception {

        System.out.println("Insert function for Receipt Vouchers called");

        List<ChalanDTO> chalanDTOList = new ArrayList<ChalanDTO>();
        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
            chalanDTOList.add((ChalanDTO) object);
        }
        gen.accountvoucher.chalan.ChalanDAO.insertChalanVoucher(chalanDTOList);


//        Connection conn = DatabaseConnection1.GetConnection();
//        String q = "insert into tblTransactionMain(trans_receiptNo,trans_date,trans_typeIndex,trans_narration) values(?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
////	for (ChalanDTO chalanDTO : chalanFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
//            ChalanDTO chalanDTO = (ChalanDTO) object;
//            ps.setString(1, chalanDTO.getReceiptNo().toString());
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = (java.util.Date) formatter.parse(chalanDTO.getDate().trim());
//
//            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            ps.setString(2, "" + formatter1.format(date));
//            ps.setInt(3, Constants.CHALAN_TYPE_INDEX);
//            ps.setString(4, chalanDTO.getNote().toString());
//            ps.addBatch();
//
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps.executeBatch();
//        }
//        ResultSet rs = ps.getGeneratedKeys();
//        List<Integer> idList = new ArrayList<Integer>();
//        while (rs.next()) {
//            idList.add(rs.getInt(1));
//        }
//        ps.close();
//        int i = 0;
//
//        String query2 = "insert into tblTransactionOtherDetails(trans_id,trans_dispatchDocThrough,trans_dispatchDocNo,trans_amt,trans_reference,trans_payment) values(?,?,?,?,?,?)";
//        PreparedStatement ps2 = conn.prepareStatement(query2);
////	for (ChalanDTO chalanDTO : chalanFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
//            ChalanDTO chalanDTO = (ChalanDTO) object;
//            Integer fk = idList.get(i);
//
//            ps2.setInt(1, fk);
//            System.out.println("DispatchThrough" + chalanDTO.getDispatchDocThrough());
//            ps2.setString(2, chalanDTO.getDispatchDocThrough());
//            System.out.println("DispatchNo" + chalanDTO.getDispatchDocNo());
//            ps2.setString(3, chalanDTO.getDispatchDocNo());
//            ps2.setDouble(4, 0D);
//            ps2.setString(5, "" + 0);
//            ps2.setString(6, "" + 0);
//
//            ps2.addBatch();
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps2.executeBatch();
//        }
//        String query33 = "insert into tblTransactionLedger(trans_id,trans_ledgerId,trans_type,trans_index,trans_amt) values(?,?,?,?,?)";
//        PreparedStatement ps6 = conn.prepareStatement(query33);
//        i = 0;
////	for (ChalanDTO chalanDTO : chalanFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
//            ChalanDTO chalanDTO = (ChalanDTO) object;
//            Integer fk = idList.get(i);
//            ps6.setInt(1, fk);
//            ps6.setInt(2, Integer.parseInt(chalanDTO.getCashLedger()));
//            ps6.setInt(3, Constants.CREDIT);      //1 for credit
//            ps6.setInt(4, 0);      //index of ledger on form
//            ps6.setDouble(5, 0D);
//            ps6.addBatch();
//
//            ps6.setInt(1, fk);
//            ps6.setInt(2, Integer.parseInt(chalanDTO.getSaleLedger().toString()));
//            ps6.setInt(3, Constants.DEBIT);      //1 for credit
//            ps6.setInt(4, 1);      //index of ledger on form
//            ps6.setDouble(5, 0D);
//            ps6.addBatch();
//
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps6.executeBatch();
//        }
//        ps6.close();
//
//        i = 0;
//        String query3 = "insert into tblInventoryTransaction(trans_id,invtrans_type) values(?,?)";
//        PreparedStatement ps3 = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
////	for (ChalanDTO chalanDTO : chalanFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
//            ChalanDTO chalanDTO = (ChalanDTO) object;
//            Integer fk = idList.get(i);
//            ps3.setInt(1, fk);
//            ps3.setString(2, "" + 1);
//            ps3.addBatch();
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps3.executeBatch();
//        }
//
//
//        ResultSet rs1 = ps3.getGeneratedKeys();
//        List<Integer> idList1 = new ArrayList<Integer>();
//        while (rs1.next()) {
//            idList1.add(rs1.getInt(1));
//        }
//        ps3.close();
//        i = 0;
//        String query4 = "insert into tblInventoryTransactionItems(invtrans_id,invtrans_itemId,invtrans_qty,invtrans_rate,invtrans_amount,invtrans_totalSize,invtrans_index,invtrans_making,invtrans_length,invtrans_width,invtrans_thickness) values(?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement ps4 = conn.prepareStatement(query4);
////	for (ChalanDTO chalanDTO : chalanFormDTOList) {
//        for (Object object : returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values()) {
//            ChalanDTO chalanDTO = (ChalanDTO) object;
//            Integer fk = idList1.get(i);
//            System.out.println("rerertrrretretrere" + Integer.parseInt(chalanDTO.getReceiptNo().toString()));
//            int k = 0;
//            for (StockItemTransactionDTO stockItemTransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {
//                ps4.setInt(1, fk);
//                ps4.setInt(2, Integer.parseInt(stockItemTransactionDTO.getName().toString()));
//                ps4.setDouble(3, Double.parseDouble(stockItemTransactionDTO.getQuantity().toString()));
//                ps4.setDouble(4, Double.parseDouble(stockItemTransactionDTO.getRate().toString()));
//                ps4.setDouble(5, Double.parseDouble(stockItemTransactionDTO.getAmount().toString()));
//                ps4.setDouble(6, Double.parseDouble(stockItemTransactionDTO.getSqFeet().toString()));
//                ps4.setInt(7, k);
//                ps4.setDouble(8, 0.0);
//                ps4.setDouble(9, Double.parseDouble(stockItemTransactionDTO.getLength().toString()));
//                ps4.setDouble(10, Double.parseDouble(stockItemTransactionDTO.getWidth().toString()));
//                ps4.setDouble(11, Double.parseDouble(stockItemTransactionDTO.getThkness().toString()));
//
//                ps4.addBatch();
//                k++;
//            }
//            i++;
//        }
//        if (!returnMapFromXml.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()).values().isEmpty()) {
//            ps4.executeBatch();
//        }
//        ps4.close();
    }

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("C:\\Daybook.xml");
            TagsHelper1.initilise();

            importXML(fXmlFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//     enum insertAction {
//
//	SKIP("skip"), OVERRIDE("override"), SELECTED("selected");
//	private String action;
//
//	public String getAction() {
//	    return this.action;
//	}
//
//	private insertAction(String action) {
//	    this.action = action;
//	}
//    }
    public static String extractDigitsFromString(String text) {
        if (text.contains("/")) {

            String line = text;
            String[] array = line.split("/");
            double amount = Double.parseDouble(array[0]);
            System.out.println(amount);
            return "" + amount;

        } else {
            String extractDigitsFromString = "";

            System.out.println("extractDigitsFromString Original---- >>>" + text);
            char[] c = text.toCharArray();
            for (int i = 0; i < c.length; i++) {
                System.out.println("Char Array--->>>" + c[i]);
                if (Character.isDigit(c[i]) || Character.toString(c[i]).equalsIgnoreCase(".")) {
                    extractDigitsFromString = extractDigitsFromString + c[i];
                }
            }
            System.out.println("extractDigitsFromString Afterwards--->>>" + extractDigitsFromString);
            double amount = Double.parseDouble(extractDigitsFromString);
            System.out.println("Amount--->>>" + amount);

            return "" + amount;
        }
//        if (text.contains("/")) {
//
//            String line = text;
//            String[] array = line.split("/");
//            double amount = Double.parseDouble(array[0]);
//            System.out.println(amount);
//            return "" + amount;
//
//        } else if (text.contains("Kg")) {
//
//            String line = text;
//            String[] array = line.split("Kg");
//            double amount = Double.parseDouble(array[0]);
//            System.out.println(amount);
//            return "" + amount;
//
//        } else if (text.contains("Gm")) {
//
//            String line = text;
//            String[] array = line.split("Gm");
//            double amount = Double.parseDouble(array[0]);
//            System.out.println(amount);
//            return "" + amount;
//
//        } else {
//
//            String line = text;
//            String[] array = line.split("sy");
//            double amount = Double.parseDouble(array[0]);
//            System.out.println(amount);
//            return "" + amount;
//
//        }
    }
//    public static String assignReceiptNoForTallyTransaction(String voucherName) {
//        String receiptNumber = "";
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            String query = "select * from tblaccountvouchersmaxid";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                if (voucherName.equalsIgnoreCase("Sales")) {
//                    receiptNumber = "" + rs.getInt("salemaxid");
//                } else if (voucherName.equalsIgnoreCase("Purchase")) {
//                    receiptNumber = "" + rs.getInt("purchasemaxid");
//                } else if (voucherName.equalsIgnoreCase("Receipt")) {
//                    receiptNumber = "" + rs.getInt("receiptmaxid");
//                } else if (voucherName.equalsIgnoreCase("Payment")) {
//                    receiptNumber = "" + rs.getInt("paymentmaxid");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TagHelper2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return receiptNumber;
//    }

    public static Map<String, Set<String>> getTrans_id(Map<String, Set<String>> map) throws SQLException, Exception {
        Connection conn = null;
        Map<String, Set<String>> returnMap = new HashMap<String, Set<String>>();

        System.out.println("TTTTTTTT        " + map.keySet());
        for (String str : map.keySet()) {

            String groupNameList = "";
            for (String group : map.get(str)) {
                groupNameList = groupNameList + "" + group + ",";
            }
            groupNameList = groupNameList.trim().substring(0, groupNameList.length() - 1);
            String query = "select  trans_id from  tblTransactionMain where trans_typeIndex in (" + str + ") and trans_receiptNo in (" + groupNameList + ")";
            System.out.println("Query getting ---------  " + query);
            try {
                conn = DatabaseConnection1.GetConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                ResultSet resultSet = preparedStmt.executeQuery();
                while (resultSet.next()) {
                    if (returnMap.get(str) == null) {
                        returnMap.put(str, new HashSet<String>());
                    }
                    System.out.println("Always -------------  " + resultSet.getString("trans_id"));
                    returnMap.get(str).add(resultSet.getString("trans_id"));
                }

                conn.commit();
                conn.close();
            } catch (Exception e) {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                e.printStackTrace();
                throw e;
            }

        }
        return returnMap;
    }

    public static void deleteTransaction(Map<String, Set<String>> mapValueDeletion) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            String query = "";

            if (mapValueDeletion.containsKey(gen.dto.Constants.CHALAN_TYPE_INDEX)) {
                String trans_IDList = "";
                for (String group : mapValueDeletion.get(gen.dto.Constants.CHALAN_TYPE_INDEX)) {
                    trans_IDList = trans_IDList + "" + group + ",";
                }
                trans_IDList = trans_IDList.trim().substring(0, trans_IDList.length() - 1);
//                query = "delete from tbltranscactionchange where chalan_trans_id in( ? )";
//                PreparedStatement prmt = conn.prepareStatement(query);
//                prmt.setString(1, "" + trans_IDList);
                query = "delete from tbltranscactionchange where chalan_trans_id in( ? ) and and voucher_type = ?";
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + trans_IDList);
                prmt.setString(2, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            } else if (mapValueDeletion.containsKey(gen.dto.Constants.SALE_TYPE_INDEX)) {
                String trans_IDList = "";
               query = "delete from tbltranscactionchange where sale_trans_id = ? and voucher_type = ?";
                for (String group : mapValueDeletion.get(gen.dto.Constants.CHALAN_TYPE_INDEX)) {
                    trans_IDList = trans_IDList + "" + group + ",";
                }
                trans_IDList = trans_IDList.trim().substring(0, trans_IDList.length() - 1);
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + trans_IDList);
                prmt.setString(2, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            }

            Set<String> trans_Total_ID = new HashSet<String>();
            for (Map.Entry<String, Set<String>> e : mapValueDeletion.entrySet()) {
                trans_Total_ID.addAll(e.getValue());
            }

            String trans_Total_IDList = "";
            for (String trans_id : trans_Total_ID) {
                trans_Total_IDList = trans_Total_IDList + trans_id + ",";
            }
            trans_Total_IDList = trans_Total_IDList.trim().substring(0, trans_Total_IDList.length() - 1);

            System.out.println("Delete tyrans_id--------- " + trans_Total_IDList);

            query = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (" + trans_Total_IDList + "))";
            System.out.println("Delete query from tblinventorytransactionitems " + query);
            st1.executeUpdate(query);

            query = "delete from tblinventorytransaction where trans_id in (" + trans_Total_IDList + ")";
            System.out.println("Delete query from tblinventorytransaction " + query);
            st1.executeUpdate(query);

            query = "delete from tbltransactionledger where trans_id  in (" + trans_Total_IDList + ")";
            System.out.println("Delete query from tbltransactionledger " + query);
            st2.executeUpdate(query);

            query = "delete from tbltransactionotherdetails where trans_id  in (" + trans_Total_IDList + ")";
            System.out.println("Delete query from tbltransactionotherdetails " + query);
            st2.executeUpdate(query);

            query = "delete from tbltransactionvat where trans_id  in (" + trans_Total_IDList + ")";
            System.out.println("Delete query from tbltransactionvat " + query);
            st3.executeUpdate(query);


            query = "delete from tbltransactionmain where trans_id  in (" + trans_Total_IDList + ") ";
            System.out.println("Delete query from tbltransactionmain " + query);
            st3.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }
}

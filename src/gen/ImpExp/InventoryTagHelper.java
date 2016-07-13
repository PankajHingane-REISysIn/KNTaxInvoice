/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

import gen.account.groupDTODAO.GroupDTO;
import gen.account.ledger.LedgerDTO;
import gen.account.stockgroup.StockGroupDTO;
import gen.account.stockitem.StockItemDTO;
import gen.account.unitofmeasure.UnitOfMeasureDTO;
import java.util.List;
import java.util.Map;
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
public class InventoryTagHelper {

    public static String generateGroupListNodes(List<GroupDTO> groupDTOList) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String returnString = "";
        if (groupDTOList != null && groupDTOList.size() > 0) {
            returnString = returnString + "\n<GroupList>";
            for (GroupDTO groupDTO : groupDTOList) {
                returnString = returnString + generateNodes(groupDTO);
            }
            returnString = returnString + "\n</GroupList>";
        }
        return returnString;
    }

    public static String generateNodes(GroupDTO groupDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();
        System.out.println();

        Document doc = impl.createDocument(null, null, null);
        Element group = doc.createElement("Group");
        doc.appendChild(group);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(groupDTO.getGroup_Name()));
        group.appendChild(name);

        Element under = doc.createElement("Under");
        under.appendChild(doc.createTextNode(groupDTO.getGroup_Under()));
//        under.appendChild(doc.createTextNode(TagsHelper1.groupIDToNameMapping.get(groupDTO.getGroup_Under())));
        group.appendChild(under);

        Element alias = doc.createElement("Alias");
        alias.appendChild(doc.createTextNode(groupDTO.getGroup_Alias()));
        group.appendChild(alias);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateLedgerListNodes(List<LedgerDTO> ledgerDTOList) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        String returnString = "";
        if (ledgerDTOList != null && ledgerDTOList.size() > 0) {
            returnString = returnString + "\n<LedgerList>";
            for (LedgerDTO ledgerDTO : ledgerDTOList) {
                returnString = returnString + generateNodes(ledgerDTO);
            }  
            returnString = returnString + "\n</LedgerList>";
        }
        
        return returnString;
    }

    public static String generateNodes(LedgerDTO ledgerDTO) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {

        try {
            //        under.appendChild(doc.createTextNode(ledgerDTO.getLedger_Under()));
            //TagsHelper1.initilise();
        } catch (Exception ex) {
            Logger.getLogger(InventoryTagHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element ledger = doc.createElement("Ledger");
        doc.appendChild(ledger);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(ledgerDTO.getLedger_Name()));
        ledger.appendChild(name);

        Element under = doc.createElement("Under");
        under.appendChild(doc.createTextNode(TagsHelper1.groupIDToNameMapping.get(ledgerDTO.getLedger_Under())));
        ledger.appendChild(under);

        Element alias = doc.createElement("Alias");
        alias.appendChild(doc.createTextNode(ledgerDTO.getLedger_Alias()));
        ledger.appendChild(alias);

        Element openingBal = doc.createElement("OpeningBalance");
        openingBal.appendChild(doc.createTextNode(ledgerDTO.getLedger_OpeningBalence().toString()));
        ledger.appendChild(openingBal);

        Element debitOrCredit = doc.createElement("DebitOrCredit");
        debitOrCredit.appendChild(doc.createTextNode(ledgerDTO.getDebitOrCredit().toString()));
        ledger.appendChild(debitOrCredit);

        Element address = doc.createElement("Address");
        address.appendChild(doc.createTextNode(ledgerDTO.getLedger_Address()));
        ledger.appendChild(address);

        Element contactNo = doc.createElement("ContactNo");
        contactNo.appendChild(doc.createTextNode(ledgerDTO.getLedger_ContactNo()));
        ledger.appendChild(contactNo);

        Element creditLimit = doc.createElement("CreditLimit");
        creditLimit.appendChild(doc.createTextNode("" + ledgerDTO.getLedger_CreditLimit()));
        ledger.appendChild(creditLimit);

        Element emailID = doc.createElement("EmailID");
        emailID.appendChild(doc.createTextNode(ledgerDTO.getLedger_EmailID()));
        ledger.appendChild(emailID);

        Element incomeTaxNo = doc.createElement("IncomeTaxNo");
        incomeTaxNo.appendChild(doc.createTextNode(ledgerDTO.getLedger_IncomeTaxNo()));
        ledger.appendChild(incomeTaxNo);

        Element saleTaxNo = doc.createElement("SaleTaxNo");
        saleTaxNo.appendChild(doc.createTextNode(ledgerDTO.getLedger_SaleTaxNo()));
        ledger.appendChild(saleTaxNo);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateStockGroupListNodes(List<StockGroupDTO> stockGroupDTOList) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        String returnString = "";
        if (stockGroupDTOList != null && stockGroupDTOList.size() > 0) {
            returnString = returnString + "\n<StockGroupList>";
            for (StockGroupDTO stockGroupDTO : stockGroupDTOList) {
                returnString = returnString + generateNodes(stockGroupDTO);
            }
            returnString = returnString + "\n</StockGroupList>";
        }
        
        return returnString;
    }

    public static String generateNodes(StockGroupDTO stockGroup) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element group = doc.createElement("StockGroup");
        doc.appendChild(group);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(stockGroup.getStockGroupName()));
        group.appendChild(name);

        Element under = doc.createElement("Under");
        under.appendChild(doc.createTextNode(stockGroup.getStockGroupUnder()));
//	System.out.println("TagsHelper1.stockGroupIDToNameMapping.get(stockGroup.getStockGroupUnder())"+TagsHelper1.stockGroupIDToNameMapping.get(stockGroup.getStockGroupUnder()));
//	System.out.println("TagsHelper1.stockGroupIDToNameMapping.get(stockGroup.getStockGroupUnder()) === "+stockGroup.getStockGroupUnder());
//        under.appendChild(doc.createTextNode(TagsHelper1.stockGroupIDToNameMapping.get(stockGroup.getStockGroupUnder())));
        group.appendChild(under);

        Element alias = doc.createElement("Alias");
        alias.appendChild(doc.createTextNode(stockGroup.getStockGroupAlias()));
        group.appendChild(alias);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateStockItemListNodes(List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        String returnString = "";
        if (stockItemDTOList != null && stockItemDTOList.size() > 0) {
            returnString = returnString + "\n<StockItemList>";
            for (gen.account.StockItemFormation.StockItemDTO stockItemDTO : stockItemDTOList) {
                returnString = returnString + generateNodes(stockItemDTO);
            }
            returnString = returnString + "\n</StockItemList>";
        }
        return returnString;
    }

    public static String generateUnitOfMeasureListNodes(List<UnitOfMeasureDTO> unitOfMeasureDTO) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        String returnString = "";
        if (unitOfMeasureDTO != null && unitOfMeasureDTO.size() > 0) {
           returnString = returnString + "\n<UnitOfMeasureList>";
           for (UnitOfMeasureDTO unitOfMeasure : unitOfMeasureDTO) {
                returnString = returnString + generateNodes(unitOfMeasure);
           }
           returnString = returnString + "\n</UnitOfMeasureList>";
        }
        
        return returnString;
    }

    public static String generateNodes(gen.account.StockItemFormation.StockItemDTO stockitem) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        try {
           // TagsHelper1.initilise();
        } catch (Exception ex) {
            Logger.getLogger(InventoryTagHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        Document doc = impl.createDocument(null, null, null);
        Element stockItem = doc.createElement("StockItem");
        doc.appendChild(stockItem);

        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(stockitem.getName()));
        stockItem.appendChild(name);

        Element under = doc.createElement("Under");
//        under.appendChild(doc.createTextNode(stockitem.getUnder()));
        if (TagsHelper1.stockGroupIDToNameMapping.get(stockitem.getUnder()) != null) {
        under.appendChild(doc.createTextNode(TagsHelper1.stockGroupIDToNameMapping.get(stockitem.getUnder())));
        } else {
            under.appendChild(doc.createTextNode(""));
        }
//        under.appendChild(doc.createTextNode(stockitem.getUnder()));
        stockItem.appendChild(under);

        Element alias = doc.createElement("Alias");
        alias.appendChild(doc.createTextNode(stockitem.getAlias()));
        stockItem.appendChild(alias);

//        Element categary = doc.createElement("Categary");
//        categary.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.categaryIDToNameMapping.get(stockitem.getStockItemCategoryDTO().getCategoryName())));
//        stockItem.appendChild(categary);
        
        Element length = doc.createElement("Length");
        length.appendChild(doc.createTextNode(String.valueOf(stockitem.getLength())));
        stockItem.appendChild(length);

        Element width = doc.createElement("Width");
        width.appendChild(doc.createTextNode(String.valueOf(stockitem.getWidth())));
        stockItem.appendChild(width);

        Element thkness = doc.createElement("Thickness");
        thkness.appendChild(doc.createTextNode(String.valueOf(stockitem.getThkness())));
        stockItem.appendChild(thkness);

//        Element color = doc.createElement("Color");
//        color.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.colorIDToNameMapping.get(stockitem.getStockItemColorDTO().getColorName())));
//        stockItem.appendChild(color);
//        
//        Element finishType = doc.createElement("FinishType");
//        finishType.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.typeIDToNameMapping.get(stockitem.getStockItemFinishTypeDTO().getFinishTypeName())));
//        stockItem.appendChild(finishType);
//        
//        Element boardType = doc.createElement("BoardType");
//        boardType.appendChild(doc.createTextNode(gen.ImpExp.TagsHelper1.boardtypeIDToNameMapping.get(stockitem.getStockItemTypeDTO().getTypeName())));
//        stockItem.appendChild(boardType);
//        
        Element rate = doc.createElement("Rate");
        rate.appendChild(doc.createTextNode(String.valueOf(stockitem.getRate())));
        stockItem.appendChild(rate);

        Element unit = doc.createElement("Unit");
//        unit.appendChild(doc.createTextNode(String.valueOf(stockitem.getUnit())));
        if (TagsHelper1.unitOfMeasureIDToNameMapping.get(stockitem.getUnit()) != null) {
	System.out.println("UUUUUUUUUU        " + TagsHelper1.unitOfMeasureIDToNameMapping.get(stockitem.getUnit()));
	unit.appendChild(doc.createTextNode(TagsHelper1.unitOfMeasureIDToNameMapping.get(stockitem.getUnit())));
        } else {
            unit.appendChild(doc.createTextNode(""));
        }
//	unit.appendChild(doc.createTextNode(stockitem.getUnit()));
        stockItem.appendChild(unit);

        Element openBal = doc.createElement("OpeningBalance");
        openBal.appendChild(doc.createTextNode(String.valueOf(stockitem.getOpeningBal())));
        stockItem.appendChild(openBal);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }

    public static String generateNodes(UnitOfMeasureDTO unitofMeasureDTO) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

        try {
            //TagsHelper1.initilise();
        } catch (Exception ex) {
            Logger.getLogger(InventoryTagHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        DOMImplementation impl = ImpExpUtil.getDomImplemetation();

        System.out.println("UnitOfMeasureDTO---------------->>");

        Document doc = impl.createDocument(null, null, null);
        Element unitOfMeasure = doc.createElement("UnitOfMeasure");
        doc.appendChild(unitOfMeasure);

        Element type = doc.createElement("Type");
        System.out.println("===================" + unitofMeasureDTO.getUnitOfMeasure_Type());
        System.out.println("===================" + doc.createTextNode(TagsHelper1.unitOfMeasureTypeIDToNameMapping.get(unitofMeasureDTO.getUnitOfMeasure_Type())));
        type.appendChild(doc.createTextNode(TagsHelper1.unitOfMeasureIDToNameMapping.get(unitofMeasureDTO.getUnitOfMeasure_Type())));
        unitOfMeasure.appendChild(type);

        Element symbol = doc.createElement("Symbol");
        System.out.println("===================" + unitofMeasureDTO.getUnitOfMeasure_Symbol());
        symbol.appendChild(doc.createTextNode(unitofMeasureDTO.getUnitOfMeasure_Symbol()));
        unitOfMeasure.appendChild(symbol);

        Element formalName = doc.createElement("FormalName");
        System.out.println("===================" + unitofMeasureDTO.getUnitOfMeasure_FormalName());
        formalName.appendChild(doc.createTextNode(unitofMeasureDTO.getUnitOfMeasure_FormalName()));
        unitOfMeasure.appendChild(formalName);

        Element noOfDecimalPt = doc.createElement("NoDecimalPlaces");
        System.out.println("===================" + doc.createTextNode(String.valueOf(unitofMeasureDTO.getUnitOfMeasure_NoDecimalPlaces())));
        noOfDecimalPt.appendChild(doc.createTextNode(String.valueOf(unitofMeasureDTO.getUnitOfMeasure_NoDecimalPlaces())));
        unitOfMeasure.appendChild(noOfDecimalPt);

        return ImpExpUtil.convertDoctoDOMSource(doc);
    }
}

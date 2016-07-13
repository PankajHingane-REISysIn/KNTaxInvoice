package MobileXMLOperations;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class ReadingMobileDataRequestFromXML {

    //public static void main(String args[]) {
    static RequestForMobileDataDTO mobileDataRequestDTO = new RequestForMobileDataDTO();

    public static RequestForMobileDataDTO readingMobileDataRequestFromXML(Document doc) {
        //DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //DocumentBuilder db = dbf.newDocumentBuilder();
        //Document doc = db.parse(new File("D:\\AdSuMuDiMobileDataRequest.xml"));

        NodeList nList = doc.getElementsByTagName("AdSuMuDiMobileTransaction");
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element e = (Element) node;
            mobileDataRequestDTO.setVoucherType(e.getElementsByTagName("VoucherType").item(0).getTextContent());
            mobileDataRequestDTO.setFromDate(e.getElementsByTagName("FromDate").item(0).getTextContent());
            mobileDataRequestDTO.setToDate(e.getElementsByTagName("ToDate").item(0).getTextContent());
            mobileDataRequestDTO.setName(e.getElementsByTagName("Name").item(0).getTextContent());

            System.out.println("Data1--mobileDataRequestDTO-->>>" + mobileDataRequestDTO.getVoucherType());
            System.out.println("Data2-mobileDataRequestDTO--->>>" + mobileDataRequestDTO.getFromDate());
            System.out.println("Data3-mobileDataRequestDTO--->>>" + mobileDataRequestDTO.getToDate());
            System.out.println("Data4-mobileDataRequestDTO--->>>" + mobileDataRequestDTO.getName());
        }


        return mobileDataRequestDTO;
    }
    //}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileXMLOperations;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author pc5
 */
public class ConvertStringToXMLDocFormat {

    public static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            NodeList nList = doc.getElementsByTagName("AdSuMuDiMobileTransaction");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Element e = (Element) node;
                System.out.println("Data1 In Pramod--->>>" + e.getElementsByTagName("VoucherType").item(0).getTextContent());
                System.out.println("Data2 In Pramod--->>>" + e.getElementsByTagName("FromDate").item(0).getTextContent());
                System.out.println("Data3 In Pramod--->>>" + e.getElementsByTagName("ToDate").item(0).getTextContent());
                System.out.println("Data4 In Pramod--->>>" + e.getElementsByTagName("Name").item(0).getTextContent());
            }

            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}

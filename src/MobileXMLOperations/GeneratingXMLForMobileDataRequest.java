package MobileXMLOperations;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class GeneratingXMLForMobileDataRequest {

    public static void main(String args[]) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element rootElement = doc.createElement("AdSuMuDi");
            doc.appendChild(rootElement);

            Element subElement1 = doc.createElement("VoucherType");
            subElement1.setTextContent("Sale");
            rootElement.appendChild(subElement1);

            Element subElement2 = doc.createElement("FromDate");
            subElement2.setTextContent("10-10-2013");
            rootElement.appendChild(subElement2);

            Element subElement3 = doc.createElement("ToDate");
            subElement3.setTextContent("12-12-2013");
            rootElement.appendChild(subElement3);

            Element subElement4 = doc.createElement("Name");
            subElement4.setTextContent("Sudeep Masare");
            rootElement.appendChild(subElement4);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            DOMSource dom = new DOMSource(doc);
            StreamResult str = new StreamResult(new File("D:\\AdSuMuDiMobileDataRequest.xml"));

            t.transform(dom, str);

        } catch (TransformerException ex) {
            Logger.getLogger(GeneratingXMLForMobileDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GeneratingXMLForMobileDataRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 How to call
 Map<String,String> parameters=new HashMap<String, String>();
 parameters.put("transId", "12");
 parameters.put("tranType", "1");
 Printer.directPrint("D://A5_ table_estimate.jrxml", parameters);


 */
package gen.display.report;

import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;


import org.codehaus.groovy.ast.Parameter;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import gen.database.connection.DatabaseConnection1;

/**
 *
 * @author Kasturi NovaSoft
 */
public class Printer extends Thread {

    private String fileName;
    private Map<String, String> map;

    public String getFileName() {
        return fileName;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        System.out.println("method will call.....................");

        try {
            System.out.println("in jasper report class................");

            Connection connection = null;
            Statement statement = null;

            connection = new DatabaseConnection1().GetConnection();
            statement = connection.createStatement();

            System.out.println("Connecton is created..................");
            //JasperReport jasperReport;
            JasperPrint jasperPrint = new JasperPrint();
            jasperPrint.setName("NoReport");
            jasperPrint.setPageWidth(595);
            jasperPrint.setPageHeight(842);
            System.out.println("Connection is created....................");

            //	Map<String, String> jasperParameter = new HashMap<String, String>();
            JasperDesign jd = JRXmlLoader.load(this.fileName);
            System.out.println("File is loaded...............");
            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            System.out.println("File is compile................");

            jasperPrint = JasperFillManager.fillReport(jasperReport, this.map, connection);
            PrinterJob job = PrinterJob.getPrinterJob();

            /* Create an array of PrintServices */
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            int selectedService = 0;
            /* Scan found services to see if anyone suits our needs */
            for (int i = 0; i < services.length; i++) {
                if (services[i].getName().toUpperCase().contains("Your printer's name")) {
                    /*If the service is named as what we are querying we select it */
                    selectedService = i;
                }
            }

            System.out.println("your peinter name is:" + services[selectedService]);

            job.setPrintService(services[selectedService]);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // MediaSizeName mediaSizeName = MediaSize.findMedia(10,4,MediaPrintableArea.INCH);
            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
            printRequestAttributeSet.add(new Copies(1));

            // Boolean b =new Boolean(false);

            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);


            Boolean b = false;

            exporter.exportReport();

        } catch (Exception e12) {
            // TODO: handle exception

            e12.printStackTrace();

            System.out.println("please add lib of jasper report" + e12);
        }

    }

    public static boolean directPrint(String filname, Map<String, String> map) {

        Printer p = new Printer();
        p.setFileName(filname);
        p.setMap(map);
        p.start();
        return true;
    }
}

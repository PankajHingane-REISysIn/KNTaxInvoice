package gen.other.startup;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

public class PrinterFile {

    Connection conn = null;

    public void initConnection() {
        String HOST = "jdbc:mysql://localhost:3306/aj";
        String USERNAME = "root";
        String PASSWORD = "admin";
        System.out.println("Connection exit");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("Driver Loaded");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void pdfExport() {

        System.out.println("Done!");
        try {
            String reportName = "reports/DayBook/Day_report.jasper";
//			 //Get a stream to read the file
            System.out.println("ReportDone!");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName);
            System.out.println("INputDone!");
            JasperPrint jp = JasperFillManager.fillReport(is, null, conn);



////////////////////////Coding for Xls///////////////////////////////////////////////					
//					JExcelApiExporter xlsExporter = new JExcelApiExporter();
//					xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//					xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "ExcelJasperReport"+ ".xls");
//					System.out.println("Exporting report...");
//					xlsExporter.exportReport();
////////////////////////////////////////////////////////////////////////					



//////////////////////////  Coding for html///////////////////////////////////////////////				 
            JRHtmlExporter htmlExporter = new JRHtmlExporter();
            htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            htmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "HTMLjasperreport1" + ".html");
            System.out.println("Exporting report...");
            htmlExporter.exportReport();
            java.awt.Desktop.getDesktop().open(new File("HTMLjasperreport1.html"));

//////////////////////////////////////////////////////////////////////////




            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report2" + ".pdf");
            System.out.println("Exporting report...");
            pdfExporter.exportReport();
            java.awt.Desktop.getDesktop().open(new File("report2.pdf"));
            System.out.println("Done!");

//////////////////////////Coding for RTF///////////////////////////////////////////////							
            JRRtfExporter rtfExporter = new JRRtfExporter();
            rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "RTFclassic" + ".rtf");
            rtfExporter.exportReport();
            java.awt.Desktop.getDesktop().open(new File("RTFclassic.rtf"));
/////////////////////////////////////////////////////////////////////////							


            PrinterJob job = PrinterJob.getPrinterJob();
            String defaultPrinter;
            PrintService[] ser = PrintServiceLookup.lookupPrintServices(null, null);

            System.out.println("**************** All Printers ******************");
            for (int i = 0; i < ser.length; ++i) {
                String p_name = ser[i].getName();
                System.out.println(p_name);
            }
            System.out.println("***********************************************\n");
            defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
            System.out.println("Default Printer  : " + defaultPrinter);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

            printRequestAttributeSet.add(MediaSizeName.ISO_A4);
            printRequestAttributeSet.add(new Copies(1));

            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.exportReport();

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        PrinterFile ma = new PrinterFile();
        ma.initConnection();
        ma.pdfExport();
    }
}

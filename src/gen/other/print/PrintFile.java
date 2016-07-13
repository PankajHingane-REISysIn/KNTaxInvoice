package gen.other.print;

import gen.database.connection.DatabaseConnection1;
import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

public class PrintFile extends Thread {

    private String reportName;
    private Map<String, String> parameters;

    public void run() {
        closed();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(this.reportName);
            System.out.println("pankaj" + this.reportName);
            JasperPrint jp = JasperFillManager.fillReport(is, this.parameters, DatabaseConnection1.GetConnection());

            System.out.println("In the report");

            Double w = Double.valueOf(jp.getPageWidth() / 72.0D);
            Double h = Double.valueOf(jp.getPageHeight() / 72.0D);

            System.out.println("Height and width : " + w + " " + h);

            Float w1 = Float.valueOf(Float.parseFloat(w.toString()));
            Float h1 = Float.valueOf(Float.parseFloat(h.toString()));

            MediaSizeName mediaSizeName = MediaSize.findMedia(w1.floatValue(), h1.floatValue(), 25400);

            System.out.println("***********************************************\n");

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(mediaSizeName);

            int unidad = 25400;

            printRequestAttributeSet.add(MediaSize.findMedia(w1.floatValue(), h1.floatValue(), unidad));

            printRequestAttributeSet.add(new MediaPrintableArea(0.0F, 0.0F, w1.floatValue(), h1.floatValue(), unidad));

            OrientationRequested orientation = OrientationRequested.PORTRAIT;
            if (jp.getOrientation() == 2) {
                orientation = OrientationRequested.LANDSCAPE;
            }

            printRequestAttributeSet.add(orientation);

            printRequestAttributeSet.add(new Copies(1));

            if (PrinterSettings.flagDirectPrint == 0) {
                PrintService[] ser = PrintServiceLookup.lookupPrintServices(null, null);

                System.out.println("**************** All Printers ******************");

                String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();

                PrintService ser1 = null;
                String p_name = null;
                for (int i = 0; i < ser.length; i++) {
                    if (defaultPrinter.equalsIgnoreCase(ser[i].getName())) {
                        ser1 = ser[i];
                        System.out.println("Default Printer  : " + defaultPrinter);
                    }
                    p_name = ser[i].getName();
                    System.out.println(p_name);
                }

                if (ser1 != null) {
                    JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, ser1);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, ser1.getAttributes());

                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

                    exporter.exportReport();
                    System.out.println("Data sent to default printer");
                }
            } else {
                String dir = System.getProperty("user.dir");
                String fileName = dir + "/server/report/report" + Math.random();

                if (PrinterSettings.flagPrintPageFormat == 4) {
                    JExcelApiExporter xlsExporter = new JExcelApiExporter();
                    xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

                    xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName + ".xls");

                    System.out.println("Exporting report...");

                    xlsExporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

                    xlsExporter.exportReport();
                    Desktop.getDesktop().open(new File(fileName + ".xls"));
                }

                if (PrinterSettings.flagPrintPageFormat == 2) {
                    JRHtmlExporter htmlExporter = new JRHtmlExporter();
                    htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

                    htmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName + ".html");

                    System.out.println("Exporting report...");
                    htmlExporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

                    htmlExporter.exportReport();
                    Desktop.getDesktop().open(new File(fileName + ".html"));
                }

                if (PrinterSettings.flagPrintPageFormat == 1) {
                    JRPdfExporter pdfExporter = new JRPdfExporter();
                    pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

                    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName + ".pdf");

                    System.out.println("Exporting report...");
                    pdfExporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

                    pdfExporter.exportReport();
                    Desktop.getDesktop().open(new File(fileName + ".pdf"));
                    System.out.println("Done!");
                }

                if (PrinterSettings.flagPrintPageFormat == 3) {
                    JRRtfExporter rtfExporter = new JRRtfExporter();
                    rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

                    rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName + ".rtf");

                    rtfExporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);

                    rtfExporter.exportReport();
                    Desktop.getDesktop().open(new File(fileName + ".rtf"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printReport(String fileName, Map<String, String> map) {
        PrintFile printFile = new PrintFile();
        printFile.reportName = fileName;
        printFile.parameters = map;

        printFile.start();
    }

    public static void closed() {
        File pdf = new File("report1.pdf");
        File pdf1 = new File("RTFclassic.rtf");
        File pdf2 = new File("ExcelReport.xls");
        File pdf3 = new File("HTMLreport.html");
        if (pdf.exists()) {
            pdf.deleteOnExit();
        }
        if (pdf1.exists()) {
            pdf1.deleteOnExit();
        }
        if (pdf2.exists()) {
            pdf2.deleteOnExit();
        }
        if (pdf3.exists()) {
            pdf3.deleteOnExit();
        }
    }
}
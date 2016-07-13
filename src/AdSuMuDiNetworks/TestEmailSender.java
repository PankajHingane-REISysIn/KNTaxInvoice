package AdSuMuDiNetworks;

import gen.account.ledger.LedgerDAO;
import gen.accountvoucher.sale.SaleDTO;
import gen.dto.StockItemTransactionDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestEmailSender {
//    public static void main(String[] args) throws AddressException, MessagingException {
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String mailFrom = "sudeepmasare.india@gmail.com";
//        String mailTo = "kasturinovasoft@gmail.com";
//        String password = "bshudushlib1781989";
//        String subject = "AdSuMuDi Transactions";
//        String bodyMessage = "We Fly High To Touch The Sky";
//        
//        EmailSender sender = new EmailSender();
//        //String[] fileAttachment = {"C:\\Users\\pc5\\Desktop\\IMP.txt"};
//        sender.sendEmail(host, port, mailFrom, password, mailTo, subject, bodyMessage);// fileAttachment);       
//    }

    public static void setupPropertiesAndSendMail(List<SaleDTO> saleFormDTOList) throws AddressException, MessagingException, FileNotFoundException, IOException {
        System.out.println("Step--->>>19");
        String chEMail = loadNetworkEMailSettings();
        System.out.println("Step--->>>21");
        String dir = System.getProperty("user.dir");
        String readLine = "";
        FileReader fileRead = new FileReader(dir + "\\others\\NetworkEMailSettings.properties");
        BufferedReader bufferedRead = new BufferedReader(fileRead);

        String readEMail = "";
        int lineCounting = 1;
        while ((readLine = bufferedRead.readLine()) != null) {
            System.out.println("Reading NetworkEMailSettings readLine-->>>" + readLine);
            if (lineCounting == 3) {
                readEMail = readLine;
                System.out.println("readEMail---->>" + readEMail);
            }
            lineCounting++;
        }
        bufferedRead.close();

        FileReader fileReadEMail = new FileReader(dir + "\\others\\NetworkEMailSender.properties");
        BufferedReader bufferedReadEmail = new BufferedReader(fileReadEMail);

        String readEMailID = "";
        int lineCountingCheck = 1;
        while ((readLine = bufferedReadEmail.readLine()) != null) {
            System.out.println("Reading NetworkEMailSender readLine-->>>" + readLine);
            if (lineCountingCheck == 1) {
                readEMailID = readLine;
                System.out.println("readEMail---->>" + readEMailID);
            }
            lineCountingCheck++;
        }
        bufferedReadEmail.close();

        FileReader fileReadPWD = new FileReader(dir + "\\others\\NetworkEMailPWD.properties");
        BufferedReader bufferedReadPWD = new BufferedReader(fileReadPWD);

        String readEMailPWD = "";
        int lineCountingCheckNew = 1;
        while ((readLine = bufferedReadPWD.readLine()) != null) {
            System.out.println("Reading NetworkEMailPWD readLine-->>>" + readLine);
            if (lineCountingCheckNew == 1) {
                readEMailPWD = readLine;
                System.out.println("readEMail---->>" + readEMailPWD);
            }
            lineCountingCheckNew++;
        }
        bufferedReadPWD.close();

        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = readEMailID;//"";//"kasturinovasoft@gmail.com";
        System.out.println("mailFrom--->>" + mailFrom);
        String mailTo = "";
        String password = readEMailPWD;//"sachinpankaj2012";
        System.out.println("password--->>>" + password);
        String subject = "AdSuMuDi Transactions";
        String data = "";

        if (chEMail.equalsIgnoreCase("1")) {

            for (SaleDTO saleDTO : saleFormDTOList) {
                try {
                    Map<String, String> map = new HashMap<String, String>();
                    System.out.println("Step--->>>22");
                    map = LedgerDAO.getLedgerIDToNameMap();
                    System.out.println("Step--->>>24");
                    saleDTO.setCashLedger(map.get(saleDTO.getCashLedger()));
                mailTo = saleDTO.getSaleLedgerEMailDetails();
                System.out.println("mailTo-->>>" + mailTo);
                try {

                        data = "<HTML>" + "<font face=\"Times New Roman\" size=\"4\">"
                                + "<I>Dear Mr.</I>" + "<I>" + saleDTO.getCashLedger() + "</I><BR>"
                                + "<I>Your order has been billed</I><BR>"
                                + "<I>Invoice No. :</I>" + "<I>" + saleDTO.getReceiptNo() + "</I><BR>"
                                + "<I>Dated : </I>" + "<I>" + saleDTO.getDate() + "</I><BR>"
                                + "<I>Amount : </I>" + "<I>" + saleDTO.getFinalAmount() + "</I><BR>"
                                + "<I>We value your business</I><BR>"
                                + "<I>Thanks and Regards</I>"
                                + "</font>"
                                + "</HTML>";

                } catch (Exception ex) {
                    Logger.getLogger(TestSMS.class.getName()).log(Level.SEVERE, null, ex);
                }

                } catch (SQLException ex) {
                    Logger.getLogger(TestEmailSender.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(TestEmailSender.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            EmailSender sender = new EmailSender();
//            String[] fileAttachment = {"C:\\Users\\pc5\\Desktop\\Attachement.pdf"};
            String[] fileAttachment = {dir + "/server/report/Sale.pdf"};

//            File folder = new File(dir + "/server/report/");
//            File[] listOfFiles = folder.listFiles();
//            String file = "";
//            for (int i = 0; i < listOfFiles.length; i++) {
//                if (listOfFiles[i].isFile()) {
//                    System.out.println("File " + listOfFiles[i].getName());
//                    file = listOfFiles[i].getName();
//                }
//            }
//            String[] fileAttachment = {dir + "/server/report/" + file};
            sender.sendEmail(host, port, mailFrom, password, mailTo, subject, data, fileAttachment);
            System.out.println("Step--->>>26");
        }
    }

    public static String loadNetworkEMailSettings() throws FileNotFoundException, IOException {

        System.out.println("Step--->>>20");
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\NetworkEMailSettings.properties";
        String line = null;
        String oldText = "";
        String stringValueOfEmail = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int lineCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (lineCounter == 0) {
                    System.out.println(line);
                    stringValueOfEmail = line.substring(16, 17);
                    System.out.println("SubString Network stringValueOfEmail-->>" + stringValueOfEmail);
                    oldText += line + "\r\n";
                    lineCounter++;
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return stringValueOfEmail;

    }
}

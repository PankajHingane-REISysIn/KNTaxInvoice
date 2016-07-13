package AdSuMuDiNetworks;

import gen.accountvoucher.sale.SaleDTO;
import gen.dto.StockItemTransactionDTO;
import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TestSMS {

    static int i = 0;
    static GsmModem gsmModem = null;
    private static String port = "COM4"; //Modem Port.
    private static int bitRate = 115200; //this is also optional. leave as it is.
    private static String modemName = "VisionTech"; //this is optional.
    private static String modemPin = "0000"; //Pin code if any have assigned to the modem.
    private static String SMSC = "+919890051914"; //Message Center Number ex. Mobitel
    static String stringValueOfStkItem = "";
    static String stringValueOfQuantity = "";
    static String stringValueOfAmount = "";

    public static void sendSMSToClient(List<SaleDTO> saleFormDTOList) throws FileNotFoundException, IOException {

        loadDataSMSSettings();
        String ch = loadNetworkSMSSettings();

        System.out.println("stringValueOfStkItem--->>TestSMS---" + stringValueOfStkItem);
        System.out.println("stringValueOfQuantity-->>TestSMS---" + stringValueOfQuantity);
        System.out.println("stringValueOfAmount-->>TestSMS---" + stringValueOfAmount);

        String stockItemName = "";
        String quantity = "";
        String amount = "";
        String TotalData = "";
        String part1ofsms = "";
        String part2ofsms = "";
        String part3ofsms = "";
        String part4ofsms = "";
        String part5ofsms = "";
        String part6ofsms = "";
        String part7ofsms = "";
        if (ch.equalsIgnoreCase("1")) {
            if (i == 0) {
                gsmModem = new GsmModem();
                GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);
                i++;
            }

            for (SaleDTO saleDTO : saleFormDTOList) {

                try {
                    //String sms = "Hello Mr." + saleDTO.getSaleLedger() + ",\r\nyour sale order is generated at AdSuMuDi\r\n Quantity : " + saleDTO.getStockItemTreansactioDTOList() + "\r\nAmount :" + saleDTO.getFinalAmount();
                    String name = "Hello Mr." + saleDTO.getSaleLedger() + "\r\n";
                    for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {

                        stockItemName = "";
                        quantity = "";
                        amount = "";

                        stockItemName = stockItemName + stockItemTransactionDTO.getName() + "/";
                        quantity = quantity + stockItemTransactionDTO.getQuantity() + "/";
                        amount = amount + stockItemTransactionDTO.getAmount() + "\r\n";

                        if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfQuantity.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                            TotalData = stockItemName + quantity + amount + TotalData;
                        } else if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfQuantity.equalsIgnoreCase("1")) {
                            TotalData = stockItemName + quantity + TotalData;
                        } else if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                            TotalData = stockItemName + amount + TotalData;
                        } else if (stringValueOfQuantity.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                            TotalData = quantity + amount + TotalData;
                        }
                    }
                    String totalAmount = "" + saleDTO.getFinalAmount();
                    String sms = name + TotalData + "Total:" + totalAmount;
                    int length = sms.length();
                    System.out.println("Length-->>" + length);
                    if (length < 120) {
                        //gsmModem.Sender("+919503312446", sms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), sms);
                        System.out.println("From else block");
                    } else if (length >= 120 && length < 240) {

                        System.out.println("From If block");
                        part1ofsms = sms.substring(0, 119);
                        part2ofsms = sms.substring(119, length);
                        System.out.println("part1ofsms--->>" + part1ofsms);
                        System.out.println("part2ofsms--->>" + part2ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);

                    } else if (length >= 240 && length <= 360) {

                        System.out.println("From else If block");
                        part1ofsms = sms.substring(0, 119);
                        part2ofsms = sms.substring(119, 239);
                        part3ofsms = sms.substring(239, length);
                        System.out.println("part1ofsms--->>" + part1ofsms);
                        System.out.println("part2ofsms--->>" + part2ofsms);
                        System.out.println("part3ofsms--->>" + part3ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);

                    } else if (length >= 360 && length <= 480) {

                        System.out.println("From else If block");
                        part1ofsms = sms.substring(0, 119);
                        part2ofsms = sms.substring(119, 239);
                        part3ofsms = sms.substring(239, 359);
                        part4ofsms = sms.substring(359, length);
                        System.out.println("part1ofsms--->>" + part1ofsms);
                        System.out.println("part2ofsms--->>" + part2ofsms);
                        System.out.println("part3ofsms--->>" + part3ofsms);
                        System.out.println("part4ofsms--->>" + part4ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
//                    gsmModem.Sender("+919503312446", part4ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part4ofsms);

                    } else if (length >= 480 && length <= 600) {

                        System.out.println("From else If block");
                        part1ofsms = sms.substring(0, 119);
                        part2ofsms = sms.substring(119, 239);
                        part3ofsms = sms.substring(239, 359);
                        part4ofsms = sms.substring(359, 479);
                        part5ofsms = sms.substring(479, length);
                        System.out.println("part1ofsms--->>" + part1ofsms);
                        System.out.println("part2ofsms--->>" + part2ofsms);
                        System.out.println("part3ofsms--->>" + part3ofsms);
                        System.out.println("part4ofsms--->>" + part4ofsms);
                        System.out.println("part5ofsms--->>" + part5ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
//                    gsmModem.Sender("+919503312446", part4ofsms);
//                    gsmModem.Sender("+919503312446", part5ofsms);

                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part4ofsms);
                        gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part5ofsms);

                    }

                } catch (Exception ex) {
                    Logger.getLogger(TestSMS.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (ch.equalsIgnoreCase("3")) {

            int optionResult = JOptionPane.showConfirmDialog(null, "Do You Want To Send SMS To This Client ?");

            if (optionResult == JOptionPane.YES_OPTION) {

                if (i == 0) {
                    gsmModem = new GsmModem();
                    GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);
                    i++;
                }

                for (SaleDTO saleDTO : saleFormDTOList) {

                    try {
                        //String sms = "Hello Mr." + saleDTO.getSaleLedger() + ",\r\nyour sale order is generated at AdSuMuDi\r\n Quantity : " + saleDTO.getStockItemTreansactioDTOList() + "\r\nAmount :" + saleDTO.getFinalAmount();
                        String name = "Hello Mr." + saleDTO.getSaleLedger() + "\r\n";
                        for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {

                            stockItemName = "";
                            quantity = "";
                            amount = "";

                            stockItemName = stockItemName + stockItemTransactionDTO.getName() + "/";
                            quantity = quantity + stockItemTransactionDTO.getQuantity() + "/";
                            amount = amount + stockItemTransactionDTO.getAmount() + "\r\n";

                            if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfQuantity.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                                TotalData = stockItemName + quantity + amount + TotalData;
                            } else if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfQuantity.equalsIgnoreCase("1")) {
                                TotalData = stockItemName + quantity + TotalData;
                            } else if (stringValueOfStkItem.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                                TotalData = stockItemName + amount + TotalData;
                            } else if (stringValueOfQuantity.equalsIgnoreCase("1") && stringValueOfAmount.equalsIgnoreCase("1")) {
                                TotalData = quantity + amount + TotalData;
                            }
                        }
                        String totalAmount = "" + saleDTO.getFinalAmount();
                        String sms = name + TotalData + "Total:" + totalAmount;
                        int length = sms.length();
                        System.out.println("Length-->>" + length);
                        if (length < 120) {
                            //gsmModem.Sender("+919503312446", sms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), sms);
                            System.out.println("From else block");
                        } else if (length >= 120 && length < 240) {

                            System.out.println("From If block");
                            part1ofsms = sms.substring(0, 119);
                            part2ofsms = sms.substring(119, length);
                            System.out.println("part1ofsms--->>" + part1ofsms);
                            System.out.println("part2ofsms--->>" + part2ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);

                        } else if (length >= 240 && length <= 360) {

                            System.out.println("From else If block");
                            part1ofsms = sms.substring(0, 119);
                            part2ofsms = sms.substring(119, 239);
                            part3ofsms = sms.substring(239, length);
                            System.out.println("part1ofsms--->>" + part1ofsms);
                            System.out.println("part2ofsms--->>" + part2ofsms);
                            System.out.println("part3ofsms--->>" + part3ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);

                        } else if (length >= 360 && length <= 480) {

                            System.out.println("From else If block");
                            part1ofsms = sms.substring(0, 119);
                            part2ofsms = sms.substring(119, 239);
                            part3ofsms = sms.substring(239, 359);
                            part4ofsms = sms.substring(359, length);
                            System.out.println("part1ofsms--->>" + part1ofsms);
                            System.out.println("part2ofsms--->>" + part2ofsms);
                            System.out.println("part3ofsms--->>" + part3ofsms);
                            System.out.println("part4ofsms--->>" + part4ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
//                    gsmModem.Sender("+919503312446", part4ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part4ofsms);

                        } else if (length >= 480 && length <= 600) {

                            System.out.println("From else If block");
                            part1ofsms = sms.substring(0, 119);
                            part2ofsms = sms.substring(119, 239);
                            part3ofsms = sms.substring(239, 359);
                            part4ofsms = sms.substring(359, 479);
                            part5ofsms = sms.substring(479, length);
                            System.out.println("part1ofsms--->>" + part1ofsms);
                            System.out.println("part2ofsms--->>" + part2ofsms);
                            System.out.println("part3ofsms--->>" + part3ofsms);
                            System.out.println("part4ofsms--->>" + part4ofsms);
                            System.out.println("part5ofsms--->>" + part5ofsms);
//                    gsmModem.Sender("+919503312446", part1ofsms);
//                    gsmModem.Sender("+919503312446", part2ofsms);
//                    gsmModem.Sender("+919503312446", part3ofsms);
//                    gsmModem.Sender("+919503312446", part4ofsms);
//                    gsmModem.Sender("+919503312446", part5ofsms);

                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part1ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part2ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part3ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part4ofsms);
                            gsmModem.Sender("+91" + saleDTO.getSaleLedgerContactDetails(), part5ofsms);

                        }

                    } catch (Exception ex) {
                        Logger.getLogger(TestSMS.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
    }

    public static void loadDataSMSSettings() throws FileNotFoundException, IOException {


        FileWriter writer = null;
        //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
        final String dir = System.getProperty("user.dir");
        System.out.println("DIR--->>>" + dir);
        String fileName = dir + "\\others\\DataSMSSettings.properties";
        System.out.println("Fila=ename-->>>" + fileName);
        String line = null;
        String oldText = "";
        String oldText1 = "";
        String oldText2 = "";


        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("S")) {
                stringValueOfStkItem = line.substring(16, 17);
                System.out.println("stringValueOfStkItem-->>" + stringValueOfStkItem);
                oldText += line + "\r\n";
            }
            if (line.startsWith("Q")) {
                stringValueOfQuantity = line.substring(11, 12);
                System.out.println("stringValueOfQuantity-->>" + stringValueOfQuantity);
                oldText1 += line + "\r\n";
            }
            if (line.startsWith("A")) {
                stringValueOfAmount = line.substring(9, 10);
                System.out.println("stringValueOfAmount-->>" + stringValueOfAmount);
                oldText2 += line + "\r\n";
            }
        }



        bufferedReader.close();

    }

    public static String loadNetworkSMSSettings() throws FileNotFoundException, IOException {


        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\NetworkSMSSettings.properties";
        String line = null;
        String oldText = "";
        String stringValueOfSMS = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stringValueOfSMS = line.substring(14, 15);
                System.out.println("SubString Network stringValueOfSMS-->>" + stringValueOfSMS);
                oldText += line + "\r\n";
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return stringValueOfSMS;

    }

    public static TestSMSDTO getSavedSettings() throws IOException {

        TestSMSDTO testSMSDTO = new TestSMSDTO();

        FileWriter writer = null;
        //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
        final String dir = System.getProperty("user.dir");
        System.out.println("DIR--->>>" + dir);
        String fileName = dir + "\\others\\DataSMSSettings.properties";
        System.out.println("Fila=ename-->>>" + fileName);
        String line = null;
        String oldText = "";
        String oldText1 = "";
        String oldText2 = "";

        String stringValueOfStkItem = "";
        String stringValueOfQuantity = "";
        String stringValueOfAmount = "";

        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            if (line.startsWith("S")) {
                stringValueOfStkItem = line.substring(16, 17);
                System.out.println("stringValueOfStkItem-->>" + stringValueOfStkItem);
                oldText += line + "\r\n";
            }
            if (line.startsWith("Q")) {
                stringValueOfQuantity = line.substring(11, 12);
                System.out.println("stringValueOfQuantity-->>" + stringValueOfQuantity);
                oldText1 += line + "\r\n";
            }
            if (line.startsWith("A")) {
                stringValueOfAmount = line.substring(9, 10);
                System.out.println("stringValueOfAmount-->>" + stringValueOfAmount);
                oldText2 += line + "\r\n";
            }
        }



        bufferedReader.close();

        testSMSDTO.setName(stringValueOfStkItem);
        testSMSDTO.setQuantity(stringValueOfQuantity);
        testSMSDTO.setAmount(stringValueOfAmount);

        return testSMSDTO;
    }
}

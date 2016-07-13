package gen.progressebar;

import AdSuMuDiSecurity.AdSuMuDiEncryptDecrypt;
import AdSuMuDiSecurity.AdSuMuDiRegistryEntry;
import AdSuMuDiSecurity.CreateNewFile;
import AdSuMuDiProductRegistration.RegisterProductDTO;
import AdSuMuDiProductRegistration.RegisterProductHelper;
import AdSuMuDiProductRegistration.VersionExpired;
import AdSuMuDiSecurity.WinUACOff;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import gen.database.connection.StartMysqlServer;
import gen.other.login.CheckInternetConnection;
import gen.other.login.Login1;
import gen.other.login.SampleWebServiceGet;
import gen.other.startup.USBRunnable;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgressBarWithNewGraphicsWithREST { //Main class

    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    static String hitURLViaREST = "";
    static String Key = "";
    static String encryptedSecurityKey = "";
    static int checkOnlineRegisteredOrNot;
    static String getDate = "";
    static JFrame frmMain;
    static Container pane;
    static JProgressBar progressBar;
    int num = 0;
    static String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ProgressBarWithNewGraphicsWithREST() throws FileNotFoundException, IOException {


        System.out.println("dir path-->>>" + dir);
        getDate = AdSuMuDiEncryptDecrypt.generateDecryptionForEDate();
        System.out.println("Date::::" + getDate);

        String readLine = "";
        FileReader fileRead = new FileReader(dir + "\\others\\Licence.dat");
        BufferedReader bufferedRead = new BufferedReader(fileRead);

        String readLicence = "";
        int lineCounting = 0;
        while ((readLine = bufferedRead.readLine()) != null) {
            System.out.println("Reading Licence readLine-->>>" + readLine);
            if (lineCounting == 5) {
                readLicence = readLine;
            }
            lineCounting++;
        }
        bufferedRead.close();

        try {

//            if (readLicence.equalsIgnoreCase("")) {
//
//                String CName = "";
//                String CContact = "";
//                String CEmail = "";
//                String CUniqueKey = "";
//
//                String line = "";
//                FileReader fileReader = new FileReader(dir + "\\others\\Licence.dat");
//
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//                int lineCounter = 0;
//                while ((line = bufferedReader.readLine()) != null) {
//                    System.out.println("Reading Licence-->>>" + line);
//                    if (lineCounter == 1) {
//                        CName = line;
//                    }
//                    if (lineCounter == 2) {
//                        CContact = line;
//                    }
//                    if (lineCounter == 3) {
//                        CEmail = line;
//                    }
//                    if (lineCounter == 4) {
//                        CUniqueKey = line;
//                    }
//                    lineCounter++;
//                }
//                bufferedReader.close();
//
//                final String companyName = CName.trim();
//                final String EmailID = CEmail.trim();
//                final String contactDetails = CContact.trim();
//                final String uniqueKey = CUniqueKey.trim();
//                Date date = new Date();
//                date = dateFormat.parse(getDate);
//                final String dateToBeSent = dateToBeSentFormat.format(date);
//                System.out.println("dateToBeSent--PP-->>" + dateToBeSent);
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            CheckInternetConnection.testInternetConnection();
//                            final RegisterProductDTO registrationDTO = new RegisterProductDTO();
//
//                            String companyNameSend = "";
//                            String EmailIDSend = "";
//                            String contactDetailsSend = "";
//
//                            companyNameSend = companyName.replace(" ", "-");
//                            EmailIDSend = EmailID.replace(" ", "-");
//                            contactDetailsSend = contactDetails.replace(" ", "-");
//
//                            registrationDTO.setCompanyName(companyNameSend.trim());
//                            registrationDTO.setCompanyEmailID(EmailIDSend.trim());
//                            registrationDTO.setCompanyContactDetails(contactDetailsSend.trim());
//                            registrationDTO.setUniqueKey(uniqueKey);
//
//                            String readLine = "";
//                            FileReader fileRead = new FileReader(dir + "\\others\\Licence.dat");
//                            BufferedReader bufferedRead = new BufferedReader(fileRead);
//
//                            String readLicence = "";
//                            int lineCounting = 0;
//                            while ((readLine = bufferedRead.readLine()) != null) {
//                                System.out.println("Reading Licence readLine-->>>" + readLine);
//                                if (lineCounting == 5) {
//                                    readLicence = readLine;
//                                    System.out.println("Reading Licence readLine-->>>PBAR--->>>readLicence--->>" + readLicence);
//                                }
//                                lineCounting++;
//                            }
//                            bufferedRead.close();
//
//                            if (readLicence.equalsIgnoreCase("")) {
//                                SampleWebServiceGet s = new SampleWebServiceGet();
//                                String line = s.callRestService(readingURLDynamically());
//                                System.out.println("Response Line---In ProgressBarWithREST-->>" + line);
//                                String splitting[] = line.split("/");
//
//                                RegisterProductHelper.RESTServiceRelatedOperations(dateToBeSent, splitting, registrationDTO);
//
//                                FileWriter fileWritter = new FileWriter(dir + "\\others\\Licence.dat", true);
//                                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//                                bufferWritter.write("\n" + "Registered User For Trial Version");
//                                bufferWritter.close();
//
//                                fileWritter = new FileWriter("C:\\Program Files\\Common Files\\System\\Licence.dat", true);
//                                bufferWritter = new BufferedWriter(fileWritter);
//                                bufferWritter.write("\n" + "Registered User For Trial Version");
//                                bufferWritter.close();
//                            }
//                        } catch (Exception ex) {
//                            Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                            JOptionPane.showMessageDialog(null, ex.getMessage());
//                        }
//                    }
//                }).start();
//            }

            fileRead = new FileReader(dir + "\\others\\WinUACSettings.properties");
            BufferedReader br = new BufferedReader(fileRead);
            readLine = "";
            String UACValueLine = "";
            lineCounting = 0;
            while ((readLine = br.readLine()) != null) {

                System.out.println("readLine-->>" + readLine);
                if (lineCounting == 1) {
                    UACValueLine = readLine;
                }
                lineCounting++;

            }
            br.close();
            System.out.println("UACValueLine Checking-->>" + UACValueLine);

            if (UACValueLine.equalsIgnoreCase("0x1")) {
                WinUACOff.WinUACOn();
            }

            Calendar cal = Calendar.getInstance();
            //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String currentDate = dateToBeSentFormat.format(cal.getTime());
            java.util.Date currentTime = dateToBeSentFormat.parse(currentDate);
            java.util.Date getExpireLimitDate = dateToBeSentFormat.parse(getDate);
            if (currentTime.compareTo(getExpireLimitDate) < 0) {

                String ch = loadingThemeSettings();
                if (ch.equalsIgnoreCase("01") || ch.equalsIgnoreCase("02") || ch.equalsIgnoreCase("03") || ch.equalsIgnoreCase("04") || ch.equalsIgnoreCase("05")
                        || ch.equalsIgnoreCase("06") || ch.equalsIgnoreCase("07") || ch.equalsIgnoreCase("08") || ch.equalsIgnoreCase("09") || ch.equalsIgnoreCase("10")) {
                    changeThemeOfApplication(ch);

                }

                //Create all components
                 
                /* Commented by sudeep for hiding progressbar on 14-02-2014.
                
                frmMain = new JFrame("KASTURI NOVASOFT presents...");
                 frmMain.setIconImage(img);
                 Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
                 int screenWidth = (int) screenSize1.getWidth();
                 int screenHeight = (int) screenSize1.getHeight();

                 int x = screenWidth / 4;
                 int y = screenHeight / 4;

                 frmMain.setLocation((screenWidth - x) / 2, (screenHeight - y) / 2);
                 frmMain.setSize(400, 55);

                 pane = frmMain.getContentPane();
                 pane.setLayout(null);

                 frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                 progressBar = new JProgressBar(0, 100);
                 pane.add(progressBar);
                 progressBar.setBounds(0, 5, 390, 20);

                 frmMain.setResizable(false); //No resize
                 frmMain.setVisible(true);

                 //ProgressBarWithNewGraphics frame = new ProgressBarWithNewGraphicsWithREST();

                 new Thread(new ProgressBarWithNewGraphicsWithREST.thread1()).start();

                 USBRunnable usbRunnable = new USBRunnable();
                 usbRunnable.runApps();

                 //                StartMysqlServer mysqlConnection = new StartMysqlServer();
                 //                mysqlConnection.start();//start mysql Server



                 this.iterate();
                frmMain.setVisible(false);

                 */

                USBRunnable usbRunnable = new USBRunnable();
                usbRunnable.runApps();

                gen.dto.Constants.DATABASE_SERVER = "org.hsqldb.jdbcDriver";

                Login1 m = new Login1();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension jInternalFrameSize = m.getSize();
                m.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                        ((screenSize.height) - (jInternalFrameSize.height)) / 2);
                m.setVisible(true);
            } else {

                //JOptionPane.showMessageDialog(null, "Trial Version Time Period Expired" + "\n" + "Visit Us At www.kasturinovasoft.com For Further Assistance");
                //VersionExpired v = new VersionExpired();
                //v.setVisible(true);
                //AdSuMuDiRegistryEntry are = new AdSuMuDiRegistryEntry();
                //are.lastEntry();
                //CreateNewFile.createSecurityEmptyFile();
                //CreateNewFile.createDSecurityEmptyFile();
                
                USBRunnable usbRunnable = new USBRunnable();
                usbRunnable.runApps();

                gen.dto.Constants.DATABASE_SERVER = "org.hsqldb.jdbcDriver";

                Login1 m = new Login1();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension jInternalFrameSize = m.getSize();
                m.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,//Changes made by sudeep just put brackets:Date:7-01-2013
                        ((screenSize.height) - (jInternalFrameSize.height)) / 2);
                m.setVisible(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void iterate() {
        while (num < 125) {
            progressBar.setValue(num);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            num += 2;
        }
    }

    public static class thread1 implements Runnable {

        public void run() {

            for (int i = 0; i <= 100; i++) {
                //progressBar.setValue(i);
                progressBar.repaint();
                try {
                    Thread.sleep(150);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        }
    }

    public static void changeThemeOfApplication(String choiceNumber) throws Exception {

        System.out.println("choiceNumber-->>" + choiceNumber);
        try {
            if (choiceNumber.equalsIgnoreCase("01")) {
                Properties props = new Properties();
                props.put("logoString", "");
                TextureLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("02")) {
                Properties props = new Properties();
                props.put("logoString", "");
                AeroLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("03")) {
                Properties props = new Properties();
                props.put("logoString", "");
                AluminiumLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("04")) {
                Properties props = new Properties();
                props.put("logoString", "");
                McWinLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("05")) {
                Properties props = new Properties();
                props.put("logoString", "");
                LunaLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("06")) {
                Properties props = new Properties();
                props.put("logoString", "");
                AcrylLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("07")) {
                Properties props = new Properties();
                props.put("logoString", "");
                MintLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("08")) {
                Properties props = new Properties();
                props.put("logoString", "");
                FastLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("09")) {
                Properties props = new Properties();
                props.put("logoString", "");
                GraphiteLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            } else if (choiceNumber.equalsIgnoreCase("10")) {
                Properties props = new Properties();
                props.put("logoString", "");
                SmartLookAndFeel.setCurrentTheme(props);
                javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String loadingThemeSettings() throws Exception {

        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\System.properties";
        System.out.println("Path-->>>Today-->>>" + fileName);
        //String fileName = "C:\\Users\\pc5\\Desktop\\Sudeep IMP FILES\\SystemProperties\\System.properties";
        String line = null;
        int lineNo;
        String oldText = "";
        String stringValueOfTheme = "";

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            LineNumberReader totalNumberOfLines = new LineNumberReader(new FileReader(new File(fileName)));
            totalNumberOfLines.skip(Long.MAX_VALUE);
            System.out.println("Total-->>" + totalNumberOfLines.getLineNumber());
            for (lineNo = 1; lineNo <= totalNumberOfLines.getLineNumber(); lineNo++) {
                if (lineNo == 1) {
                    line = bufferedReader.readLine();
                    System.out.println(line);
                    stringValueOfTheme = line.substring(8, 10);
                    System.out.println("SubString-->>" + stringValueOfTheme);
                    oldText += line + "\r\n";
                }
            }
            bufferedReader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return stringValueOfTheme;
    }

    public static String readingURLDynamically() throws Exception {

        String dynamicURL = "";
        FileReader read = null;
        try {
            read = new FileReader(dir + "\\others\\DynamicURL.properties");
            System.out.println("Path-->>>" + read);
            BufferedReader br = new BufferedReader(read);
            String readLine = "";
            String readContent = "";
            int lineCountingCheck = 1;
            while ((readLine = br.readLine()) != null) {
                System.out.println("Reading contents-->>>" + readLine);
                if (lineCountingCheck == 3) {
                    dynamicURL = readLine;
                    System.out.println("dynamicURL---PBARWREST--->>>" + dynamicURL);
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProgressBarWithNewGraphicsWithREST.class.getName()).log(Level.SEVERE, null, ex);
            read.close();
            throw ex;
        }
        return dynamicURL;
    }
}

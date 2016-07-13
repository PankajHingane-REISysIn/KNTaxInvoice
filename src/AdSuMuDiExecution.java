
import AdSuMuDiProductRegistration.RegisterProduct;
import AdSuMuDiProductRegistration.VersionExpired;
import AdSuMuDiSecurity.AdSuMuDiRegistryEntry;
import AdSuMuDiSecurity.CheckIfDirectoryExists;
import AdSuMuDiSecurity.ReadRegistryEntry;
import gen.database.connection.StartHSQLServer;
import gen.progressebar.ProgressBarWithNewGraphicsWithREST;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/*
 
 
 */
public class AdSuMuDiExecution {

    //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    DateFormat dateToBeSentFormat = new SimpleDateFormat("yyyy-MM-dd");
    final String dir = System.getProperty("user.dir");
    static String operatingSystemDrive = System.getenv("SystemDrive");

    public static void main(String args[]) {
        try {
//            StartHSQLServer.startHSQLServer();//Added by sudeep 8-7-2014
            String ch = ProgressBarWithNewGraphicsWithREST.loadingThemeSettings();
            if (ch.equalsIgnoreCase("01") || ch.equalsIgnoreCase("02") || ch.equalsIgnoreCase("03") || ch.equalsIgnoreCase("04") || ch.equalsIgnoreCase("05")
                    || ch.equalsIgnoreCase("06") || ch.equalsIgnoreCase("07") || ch.equalsIgnoreCase("08") || ch.equalsIgnoreCase("09") || ch.equalsIgnoreCase("10")) {

                ProgressBarWithNewGraphicsWithREST.changeThemeOfApplication(ch);
            }

            AdSuMuDiExecution aexc = new AdSuMuDiExecution();
            aexc.beginExecutionChecking();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void beginExecutionChecking() throws Exception {
        FileReader fileRead = null;
        try {
            String readRegistryEntry = ReadRegistryEntry.read();
            String fileExistenceCheck = CheckIfDirectoryExists.isCDirectoryExists();
            File getFirstFile = new File(fileExistenceCheck + "\\WinSysAi.dat");
            String supportFileExistenceCheck = CheckIfDirectoryExists.isCDirectoryExists();
            File getSupportFile = new File(supportFileExistenceCheck + "\\Licence.dat");
            System.out.println("getSupportFile.exists()-->>" + getSupportFile.exists());
            fileRead = new FileReader(dir + "\\others\\UserLicence.properties");
            BufferedReader br = new BufferedReader(fileRead);
            String readLine = "";
            String licenceLine = "";
            int lineCounting = 0;
            while ((readLine = br.readLine()) != null) {

                System.out.println("readLine-->>" + readLine);
                if (lineCounting == 1) {
                    licenceLine = readLine;
                }
                lineCounting++;

            }
            br.close();
            System.out.println("licenceLine Checking-->>" + licenceLine);
            if (!licenceLine.equalsIgnoreCase("Registered User For Licenced Version")) {

                if (readRegistryEntry == null) {

                    if (!getFirstFile.exists()) {

                        if (!getSupportFile.exists()) {
                            // validation when all entry deleted but already copy Purchased
                            // getting Mobile_key first time when application start
                            RegisterProduct rp = new RegisterProduct();
                            rp.setVisible(true);
                        } else {
                            AdSuMuDiRegistryEntry are = new AdSuMuDiRegistryEntry();
                            are.firstEntry();
                            fileCopyFromToOperation();
                            new ProgressBarWithNewGraphicsWithREST();
                        }
                    } else {

                        VersionExpired v = new VersionExpired();
                        v.setVisible(true);

                    }
                } else {

                    System.out.println("Step-1");
                    String registryEntry = ReadRegistryEntry.read();

                    System.out.println("dir path---->>>" + dir);
                    getFirstFile = new File(dir + "\\others\\DKFSupport.dat");

                    if (registryEntry.equalsIgnoreCase("1")) {
                        fileCopyFromToOperation();
                        new ProgressBarWithNewGraphicsWithREST();
                    } else if (registryEntry.equalsIgnoreCase("2")) {
                        VersionExpired v = new VersionExpired();
                        v.setVisible(true);
                    } else {

                        if (getFirstFile.exists()) {
                            VersionExpired v = new VersionExpired();
                            v.setVisible(true);
                        }
                    }

                }
            } else {

                new ProgressBarWithNewGraphicsWithREST();

            }
        } catch (Exception ex) {
            fileRead.close();
            Logger.getLogger(AdSuMuDiExecution.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    public void fileCopyFromToOperation() throws Exception {

        File checkEDFSupportExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//EDFSupport.dat");

        if (checkEDFSupportExists.exists()) {
            try {
                String sourcebin4 = operatingSystemDrive + "\\Program Files\\Common Files\\System\\EDFSupport.dat";
                //directory where file will be copied
                String targetbin4 = dir + "\\others\\";

                //name of source file
                File sourceFilebin4 = new File(sourcebin4);
                String namebin4 = sourceFilebin4.getName();

                File targetFilebin4 = new File(targetbin4 + namebin4);
                System.out.println("Copying file : " + sourceFilebin4.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin4, targetFilebin4);

                System.out.println("copying of file from Java program is completed");
            } catch (Exception ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }

        File checkDKFSupportExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//DKFSupport.dat");

        if (checkDKFSupportExists.exists()) {
            try {
                String sourcebin4 = operatingSystemDrive + "\\Program Files\\Common Files\\System\\DKFSupport.dat";
                //directory where file will be copied
                String targetbin4 = dir + "\\others\\";

                //name of source file
                File sourceFilebin4 = new File(sourcebin4);
                String namebin4 = sourceFilebin4.getName();

                File targetFilebin4 = new File(targetbin4 + namebin4);
                System.out.println("Copying file : " + sourceFilebin4.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin4, targetFilebin4);

                System.out.println("copying of file from Java program is completed");
            } catch (Exception ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }

        File checkLicenceExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//Licence.dat");

        if (checkLicenceExists.exists()) {
            try {
                String sourcebin4 = operatingSystemDrive + "\\Program Files\\Common Files\\System\\Licence.dat";
                //directory where file will be copied
                String targetbin4 = dir + "\\others\\";

                //name of source file
                File sourceFilebin4 = new File(sourcebin4);
                String namebin4 = sourceFilebin4.getName();

                File targetFilebin4 = new File(targetbin4 + namebin4);
                System.out.println("Copying file : " + sourceFilebin4.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin4, targetFilebin4);

                System.out.println("copying of file from Java program is completed");
            } catch (Exception ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }

        File checkUserLicenceExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//UserLicence.properties");

        if (checkUserLicenceExists.exists()) {
            try {
                String sourcebin4 = operatingSystemDrive + "\\Program Files\\Common Files\\System\\UserLicence.properties";
                //directory where file will be copied
                String targetbin4 = dir + "\\others\\";

                //name of source file
                File sourceFilebin4 = new File(sourcebin4);
                String namebin4 = sourceFilebin4.getName();

                File targetFilebin4 = new File(targetbin4 + namebin4);
                System.out.println("Copying file : " + sourceFilebin4.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin4, targetFilebin4);

                System.out.println("copying of file from Java program is completed");
            } catch (Exception ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiProductRegistration;

import exception.NetWorkValidationException;
import gen.other.login.SampleWebServiceGet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author pc5
 */
public class RegisterProductHelper {

    static String dir = System.getProperty("user.dir");
    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    static String operatingSystemDrive = System.getenv("SystemDrive");

    /*
     The following getHash() method is used for encryption purpose
     specially used for encryption of security key to be sent via REST service.
     * Implementation of MD5 algorithm.
     */
    public static String getHash(String pass) {
        byte buf[] = pass.getBytes();
        String hexStr = "";
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);
            byte[] digest = algorithm.digest();
            // get the hex string 
            for (int i = 0; i < digest.length; i++) {
                hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        return hexStr.toString();
    }

    public static void copyEDFSupportFileFromEnvironmentToCDrive() {

        String sourcebin = "";
        String targetbin = "";

        System.out.println("OS Drive Letter Found Location: RegisterProductHelper.java-->>" + operatingSystemDrive);

        File checkEDFSupportExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//EDFSupport.dat");

        if (!checkEDFSupportExists.exists()) {
            try {
                sourcebin = dir + "\\others\\EDFSupport.dat";
                //directory where file will be copied
                targetbin = operatingSystemDrive + "\\Program Files\\Common Files\\System\\";

                //name of source file
                File sourceFilebin = new File(sourcebin);
                String namebin = sourceFilebin.getName();

                File targetFilebin = new File(targetbin + namebin);
                System.out.println("Copying file : " + sourceFilebin.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin, targetFilebin);

                System.out.println("copying of file from Java program is completed");
            } catch (IOException ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void copyDKFSupportFileFromEnvironmentToCDrive() {

        String sourcebin = "";
        String targetbin = "";

        File checkDKFSupportExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//DKFSupport.dat");

        if (!checkDKFSupportExists.exists()) {
            try {
                sourcebin = dir + "\\others\\DKFSupport.dat";
                //directory where file will be copied
                targetbin = operatingSystemDrive + "\\Program Files\\Common Files\\System\\";

                //name of source file
                File sourceFilebin = new File(sourcebin);
                String namebin = sourceFilebin.getName();

                File targetFilebin = new File(targetbin + namebin);
                System.out.println("Copying file : " + sourceFilebin.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin, targetFilebin);

                System.out.println("copying of file from Java program is completed");
            } catch (IOException ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void copyLicenceFileFromEnvironmentToCDrive() {

        String sourcebin = "";
        String targetbin = "";

        File checkLicenceExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//Licence.dat");

        if (!checkLicenceExists.exists()) {
            try {
                sourcebin = dir + "\\others\\Licence.dat";
                //directory where file will be copied
                targetbin = operatingSystemDrive + "\\Program Files\\Common Files\\System\\";

                //name of source file
                File sourceFilebin = new File(sourcebin);
                String namebin = sourceFilebin.getName();

                File targetFilebin = new File(targetbin + namebin);
                System.out.println("Copying file : " + sourceFilebin.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin, targetFilebin);

                System.out.println("copying of file from Java program is completed");
            } catch (IOException ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void copyUserLicenceFileFromEnvironmentToCDrive() {

        String sourcebin = "";
        String targetbin = "";

        File checkUserLicenceExists = new File(operatingSystemDrive + "//Program Files//Common Files//System//UserLicence.properties");

        if (!checkUserLicenceExists.exists()) {
            try {
                sourcebin = dir + "\\others\\UserLicence.properties";
                //directory where file will be copied
                targetbin = operatingSystemDrive + "\\Program Files\\Common Files\\System\\";

                //name of source file
                File sourceFilebin = new File(sourcebin);
                String namebin = sourceFilebin.getName();

                File targetFilebin = new File(targetbin + namebin);
                System.out.println("Copying file : " + sourceFilebin.getName() + " from Java Program");

                //copy file from one location to other
                FileUtils.copyFile(sourceFilebin, targetFilebin);

                System.out.println("copying of file from Java program is completed");
            } catch (IOException ex) {
                Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static String RESTServiceRelatedOperations(String dateToBeSent, String splitting[], RegisterProductDTO registrationDTO) throws Exception {
        //RegisterProductHelper registration = new RegisterProductHelper();
        String finalLine = "";
        RegisterProductDTO registration = new RegisterProductDTO();
        try {

            String removeParameterHitURLViaREST = "";
            String encryptedSecurityKey = "";
            String hitURLViaREST = "";

            RegisterProductDTO returnedRegistrationDTO = replaceDetailsWithSpecialCharacters(registrationDTO);

            for (int i = 0; i < splitting.length - 6; i++) {
                removeParameterHitURLViaREST = removeParameterHitURLViaREST + splitting[i] + "/";
            }

            System.out.println("removeParamHitURLViaREST-->>>" + removeParameterHitURLViaREST);

            for (int i = 0; i < splitting.length; i++) {

                if (i != splitting.length - 1) {
                    hitURLViaREST = hitURLViaREST + splitting[i] + "/";
                }
                System.out.println("hitURLViaREST String-->>" + hitURLViaREST);
                if (i == splitting.length - 1) {

                    System.out.println("Splitting String-->>" + splitting[i].toString());
                    encryptedSecurityKey = (splitting[i].toString());
                }

                if (i == splitting.length - 2) {

                    System.out.println("Splitting param5 -->>" + splitting[i].toString());
                    registration.setParam5(splitting[i].toString());

                    if (registration.getParam5().equalsIgnoreCase("cdate")) {

                        registration.setParam5(dateToBeSent);

                    } else if (registration.getParam5().equalsIgnoreCase("email")) {

                        registration.setParam5(returnedRegistrationDTO.getCompanyEmailID());

                    } else if (registration.getParam5().equalsIgnoreCase("cmnumber")) {

                        registration.setParam5(returnedRegistrationDTO.getCompanyContactDetails());

                    } else if (registration.getParam5().equalsIgnoreCase("cname")) {

                        registration.setParam5(returnedRegistrationDTO.getCompanyName());

                    } else if (registration.getParam5().equalsIgnoreCase("uniquekey")) {

                        registration.setParam5(returnedRegistrationDTO.getUniqueKey());

                    }

                }

                if (i == splitting.length - 3) {

                    System.out.println("Splitting param4 -->>" + splitting[i].toString());
                    registration.setParam4(splitting[i].toString());

                    if (registration.getParam4().equalsIgnoreCase("cdate")) {

                        registration.setParam4(dateToBeSent);

                    } else if (registration.getParam4().equalsIgnoreCase("email")) {

                        registration.setParam4(returnedRegistrationDTO.getCompanyEmailID());

                    } else if (registration.getParam4().equalsIgnoreCase("cmnumber")) {

                        registration.setParam4(returnedRegistrationDTO.getCompanyContactDetails());

                    } else if (registration.getParam4().equalsIgnoreCase("cname")) {

                        registration.setParam4(returnedRegistrationDTO.getCompanyName());

                    } else if (registration.getParam5().equalsIgnoreCase("uniquekey")) {

                        registration.setParam5(returnedRegistrationDTO.getUniqueKey());

                    }

                }
                if (i == splitting.length - 4) {

                    System.out.println("Splitting param3 -->>" + splitting[i].toString());
                    registration.setParam3(splitting[i].toString());

                    if (registration.getParam3().equalsIgnoreCase("cdate")) {

                        registration.setParam3(dateToBeSent);

                    } else if (registration.getParam3().equalsIgnoreCase("email")) {

                        registration.setParam3(returnedRegistrationDTO.getCompanyEmailID());

                    } else if (registration.getParam3().equalsIgnoreCase("cmnumber")) {

                        registration.setParam3(returnedRegistrationDTO.getCompanyContactDetails());

                    } else if (registration.getParam3().equalsIgnoreCase("cname")) {

                        registration.setParam3(returnedRegistrationDTO.getCompanyName());

                    } else if (registration.getParam5().equalsIgnoreCase("uniquekey")) {

                        registration.setParam5(returnedRegistrationDTO.getUniqueKey());

                    }

                }
                if (i == splitting.length - 5) {

                    System.out.println("Splitting param2 -->>" + splitting[i].toString());
                    registration.setParam2(splitting[i].toString());

                    if (registration.getParam2().equalsIgnoreCase("cdate")) {

                        registration.setParam2(dateToBeSent);

                    } else if (registration.getParam2().equalsIgnoreCase("email")) {

                        registration.setParam2(returnedRegistrationDTO.getCompanyEmailID());

                    } else if (registration.getParam2().equalsIgnoreCase("cmnumber")) {

                        registration.setParam2(returnedRegistrationDTO.getCompanyContactDetails());

                    } else if (registration.getParam2().equalsIgnoreCase("cname")) {

                        registration.setParam2(returnedRegistrationDTO.getCompanyName());

                    } else if (registration.getParam5().equalsIgnoreCase("uniquekey")) {

                        registration.setParam5(returnedRegistrationDTO.getUniqueKey());

                    }

                }
                if (i == splitting.length - 6) {

                    System.out.println("Splitting param1 -->>" + splitting[i].toString());
                    registration.setParam1(splitting[i].toString());

                    if (registration.getParam1().equalsIgnoreCase("cdate")) {

                        registration.setParam1(dateToBeSent);

                    } else if (registration.getParam1().equalsIgnoreCase("email")) {

                        registration.setParam1(returnedRegistrationDTO.getCompanyEmailID());

                    } else if (registration.getParam1().equalsIgnoreCase("cmnumber")) {

                        registration.setParam1(returnedRegistrationDTO.getCompanyContactDetails());

                    } else if (registration.getParam1().equalsIgnoreCase("cname")) {

                        registration.setParam1(returnedRegistrationDTO.getCompanyName());

                    } else if (registration.getParam5().equalsIgnoreCase("uniquekey")) {

                        registration.setParam5(returnedRegistrationDTO.getUniqueKey());

                    }

                }

            }
            encryptedSecurityKey = encryptedSecurityKey.concat(staticSecurityKey);
            System.out.println("Original Key to be encrypted and To Be Sent Back With Parameters--->>" + encryptedSecurityKey);
            encryptedSecurityKey = RegisterProductHelper.getHash(encryptedSecurityKey);
            System.out.println("Enctypted Key To Be Sent Back With Parameters--->>" + encryptedSecurityKey);

            removeParameterHitURLViaREST = removeParameterHitURLViaREST + registration.getParam1() + "/" + registration.getParam2() + "/" + registration.getParam3() + "/" + registration.getParam4() + "/" + registration.getParam5() + "/" + encryptedSecurityKey;

            System.out.println("Once Again Final hitURLViaREST String-->>" + removeParameterHitURLViaREST);

            SampleWebServiceGet s = new SampleWebServiceGet();
            finalLine = s.callRestService(removeParameterHitURLViaREST);

            System.out.println("Final Response--->>>" + finalLine);

        } catch (Exception ex) {
            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return finalLine;
    }

    public static void writingRegistrationTextToFile() throws Exception {
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(dir + "\\others\\Licence.dat", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n" + "Registered User For Trial Version");
            bufferWritter.close();
            fileWritter = new FileWriter(operatingSystemDrive + "\\Program Files\\Common Files\\System\\Licence.dat", true);
            bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n" + "Registered User For Trial Version");
            bufferWritter.close();
        } catch (IOException ex) {
            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    public static RegisterProductDTO replaceDetailsWithSpecialCharacters(RegisterProductDTO registrationDTO) {

        registrationDTO.setCompanyName(registrationDTO.getCompanyName().replace(" ", "-"));
        registrationDTO.setCompanyContactDetails(registrationDTO.getCompanyContactDetails().replace(" ", "-"));
        registrationDTO.setCompanyEmailID(registrationDTO.getCompanyEmailID().replace(" ", "-"));

        return registrationDTO;
    }

    public static boolean validateData(RegisterProductDTO registrationDTO) {

        if (registrationDTO.getCompanyName().equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Enter valid company name");
            return false;

        }
        if (registrationDTO.getCompanyContactDetails().equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Enter valid contact details");
            return false;

        }
        if (registrationDTO.getCompanyEmailID().equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Enter valid E-Mail ID");
            return false;

        }
        return true;
    }

    public static String generateKey(RegisterProductDTO registerProductDTO) throws Exception{

        String unique_key = "";
        String key_generated_date = "";
        String string_to_generate = "KasturiNovasoftAccessBeyondTheSpace";

        System.out.println("-------------In generateKey----------");
        char[] chars = registerProductDTO.getCompanyName().toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String t = sb.toString();

        //Generate random string from client_mobile_no
        String client_mobile_number1 = String.valueOf(registerProductDTO.getCompanyContactDetails());

        char[] chars1 = client_mobile_number1.toCharArray();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char c = chars1[random.nextInt(chars1.length)];
            sb1.append(c);
        }
        String t1 = sb1.toString();

        //Generate random string from string

        char[] chars2 = string_to_generate.toCharArray();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char c = chars2[random.nextInt(chars2.length)];
            sb2.append(c);
        }
        String t2 = sb2.toString();

        //concate all 3 strings
        String after_concate = t + t1 + t2;

        StringBuilder sb3 = new StringBuilder(after_concate);
        String char_for_insert = Long.toString(Math.abs(random.nextLong()), 5);

        System.out.println("T = " + t);
        System.out.println("T1 = " + t1);
        System.out.println("T2 = " + t2);
        System.out.println("After Concate = " + after_concate);

        String ch = char_for_insert.substring(0, 1);
        System.out.println("Random Key =" + ch);
        sb3.insert(4, ch);
        sb3.insert(9, ch);
        sb3.insert(14, ch);
        sb3.insert(19, ch);
        sb3.insert(24, ch);

        String reverse = new StringBuilder(sb3).reverse().toString();
        System.out.println("Reverse String =" + reverse);

        String str1 = reverse.substring(0, 12);
        String str2 = reverse.substring(12, 25);


        String reverse_str1 = new StringBuilder(str1).reverse().toString();
        String reverse_str2 = new StringBuilder(str2).reverse().toString();
        System.out.println("Reverse String1 =" + reverse_str1);
        System.out.println("Reverse String2 =" + reverse_str2);

        unique_key = reverse_str1 + reverse_str2;
        System.out.println("Product Key =" + unique_key);

        StringBuilder sb4 = new StringBuilder(unique_key);
        sb4.insert(5, '-');
        sb4.insert(11, '-');
        sb4.insert(17, '-');
        sb4.insert(23, '-');
        unique_key = new StringBuilder(sb4).toString();
        System.out.println("After Adding (-) =" + unique_key);


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        key_generated_date = dateFormat.format(date);
        System.out.println("Current date = " + key_generated_date);
        return unique_key;

    }
}

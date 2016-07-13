/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import exception.FieldValidationException;
import exception.NetWorkValidationException;
import gen.other.login.SampleWebServiceGet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author pc5
 */
public class PurchaseLicenceHelper {

    static String staticSecurityKey = "SecurityKeyForAdSuMuDi";
    static String encryptedSecurityKey = "";
    static final String dir = System.getProperty("user.dir");
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

    public static String RESTServiceRelatedOperations(String dateToBeSent, String splitting[], PurchaseLicenceDTO registrationDTO) throws Exception {
        //RegisterProductHelper registration = new RegisterProductHelper();
        System.out.println("RESTServiceRelatedOperations cname-->>>" + registrationDTO.getCompanyName());
        String finalLine = "";
        //PurchaseLicenceDTO registrationDTO = new PurchaseLicenceDTO();
        String removeParameterHitURLViaREST = "";
        String encryptedSecurityKeyLocal = "";
        String hitURLViaREST = "";

        //PurchaseLicenceDTO returnedRegistrationDTO = replaceDetailsWithSpecialCharacters(registrationDTO);

        for (int i = 0; i < splitting.length - 7; i++) {
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
                encryptedSecurityKeyLocal = (splitting[i].toString());
            }

            if (i == splitting.length - 2) {

                System.out.println("Splitting param5 -->>" + splitting[i].toString());
                registrationDTO.setParam5(splitting[i].toString());

                if (registrationDTO.getParam5().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam5(dateToBeSent);

                } else if (registrationDTO.getParam5().equalsIgnoreCase("email")) {

                    registrationDTO.setParam5(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam5().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam5(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam5().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam5(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam5().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam5(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam5().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam5(registrationDTO.getProductKey());

                }

            }

            if (i == splitting.length - 3) {

                System.out.println("Splitting param4 -->>" + splitting[i].toString());
                registrationDTO.setParam4(splitting[i].toString());

                if (registrationDTO.getParam4().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam4(dateToBeSent);

                } else if (registrationDTO.getParam4().equalsIgnoreCase("email")) {

                    registrationDTO.setParam4(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam4().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam4(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam4().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam4(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam4().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam4(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam4().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam4(registrationDTO.getProductKey());

                }

            }
            if (i == splitting.length - 4) {

                System.out.println("Splitting param3 -->>" + splitting[i].toString());
                registrationDTO.setParam3(splitting[i].toString());

                if (registrationDTO.getParam3().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam3(dateToBeSent);

                } else if (registrationDTO.getParam3().equalsIgnoreCase("email")) {

                    registrationDTO.setParam3(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam3().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam3(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam3().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam3(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam3().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam3(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam3().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam3(registrationDTO.getProductKey());

                }

            }
            if (i == splitting.length - 5) {

                System.out.println("Splitting param2 -->>" + splitting[i].toString());
                registrationDTO.setParam2(splitting[i].toString());

                if (registrationDTO.getParam2().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam2(dateToBeSent);

                } else if (registrationDTO.getParam2().equalsIgnoreCase("email")) {

                    registrationDTO.setParam2(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam2().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam2(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam2().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam2(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam2().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam2(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam2().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam2(registrationDTO.getProductKey());

                }

            }
            if (i == splitting.length - 6) {

                System.out.println("Splitting param1 -->>" + splitting[i].toString());
                registrationDTO.setParam1(splitting[i].toString());

                if (registrationDTO.getParam1().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam1(dateToBeSent);

                } else if (registrationDTO.getParam1().equalsIgnoreCase("email")) {

                    registrationDTO.setParam1(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam1().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam1(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam1().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam1(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam1().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam1(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam1().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam1(registrationDTO.getProductKey());

                }

            }
            if (i == splitting.length - 7) {

                System.out.println("Splitting param0 -->>" + splitting[i].toString());
                registrationDTO.setParam0(splitting[i].toString());

                if (registrationDTO.getParam0().equalsIgnoreCase("cdate")) {

                    registrationDTO.setParam0(dateToBeSent);

                } else if (registrationDTO.getParam0().equalsIgnoreCase("email")) {

                    registrationDTO.setParam0(registrationDTO.getCompanyEmailID());

                } else if (registrationDTO.getParam0().equalsIgnoreCase("cmnumber")) {

                    registrationDTO.setParam0(registrationDTO.getCompanyContactDetails());

                } else if (registrationDTO.getParam0().equalsIgnoreCase("cname")) {

                    registrationDTO.setParam0(registrationDTO.getCompanyName());

                } else if (registrationDTO.getParam0().equalsIgnoreCase("uniquekey")) {

                    registrationDTO.setParam0(registrationDTO.getUniqueKey());

                } else if (registrationDTO.getParam0().equalsIgnoreCase("pkey")) {

                    registrationDTO.setParam0(registrationDTO.getProductKey());

                }

            }

        }
        encryptedSecurityKeyLocal = encryptedSecurityKeyLocal.concat(staticSecurityKey);
        System.out.println("Original Key to be encrypted and To Be Sent Back With Parameters--->>" + encryptedSecurityKeyLocal);
        encryptedSecurityKeyLocal = RegisterProductHelper.getHash(encryptedSecurityKeyLocal);
        System.out.println("Enctypted Key To Be Sent Back With Parameters--->>" + encryptedSecurityKeyLocal);

        System.out.println("registrationDTO.getParam0()-->>>" + registrationDTO.getParam0());

        removeParameterHitURLViaREST = removeParameterHitURLViaREST + registrationDTO.getParam0() + "/" + registrationDTO.getParam1() + "/" + registrationDTO.getParam2() + "/" + registrationDTO.getParam3() + "/" + registrationDTO.getParam4() + "/" + registrationDTO.getParam5() + "/" + encryptedSecurityKeyLocal;

        System.out.println("Once Again Final hitURLViaREST String-->>" + removeParameterHitURLViaREST);

        SampleWebServiceGet s = new SampleWebServiceGet();
        finalLine = s.callRestService(removeParameterHitURLViaREST);

        System.out.println("Final Response--->>>" + finalLine);

        return finalLine;
    }
//    public static String RESTServiceRelatedOperations(String splitting[], PurchaseLicenceDTO purchaseLicenceDTO) {
//
//        String hitURLViaREST = "";
//        String finalHitURLViaREST = "";
//
//        for (int i = 0; i < splitting.length; i++) {
//
//            if (i != splitting.length - 1) {
//                hitURLViaREST = hitURLViaREST + splitting[i] + "/";
//            }
//
//            System.out.println("hitURLViaREST String-->>" + hitURLViaREST);
//
//            if (i == splitting.length - 1) {
//
//                System.out.println("Splitting String-->>" + splitting[i].toString());
//                encryptedSecurityKey = (splitting[i].toString());
//            }
//
//        }
//        for (int i = 0; i < splitting.length - 2; i++) {
//            if (i != splitting.length) {
//                finalHitURLViaREST = finalHitURLViaREST + splitting[i] + "/";
//            }
//            System.out.println("finalHitURLViaREST String-->>" + finalHitURLViaREST);
//        }
//
//
//        encryptedSecurityKey = encryptedSecurityKey.concat(staticSecurityKey);
//        System.out.println("Original Key to be encrypted and To Be Sent Back With Parameters--->>" + encryptedSecurityKey);
//        encryptedSecurityKey = getHash(encryptedSecurityKey);
//        System.out.println("Enctypted Key To Be Sent Back With Parameters--->>" + encryptedSecurityKey);
//
//        finalHitURLViaREST = finalHitURLViaREST + purchaseLicenceDTO.getCompanyName() + "/" + purchaseLicenceDTO.getCompanyContactDetails() + "/" + purchaseLicenceDTO.getCompanyEmailID() + "/" + encryptedSecurityKey + "/" + purchaseLicenceDTO.getProductKey();
//
//        System.out.println("Once Again Final hitURLViaREST String-->>" + hitURLViaREST);
//        System.out.println("Once Again Final finalHitURLViaREST String-->>" + finalHitURLViaREST);
//
//        return finalHitURLViaREST;
//    }

    public static void writingRegistrationTextToFile() throws IOException {
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(dir + "\\others\\UserLicence.properties", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n" + "Registered User For Licenced Version");
            bufferWritter.close();
        } catch (IOException ex) {
            throw ex;
        
        }

    }

    public static void regeneratingUserLicenceFileCDriveBackup() throws IOException {

        String sourcebin = "";
        String targetbin = "";

        try {
            sourcebin = dir + "\\others\\UserLicence.properties";
            //directory where file will be copied
            targetbin = operatingSystemDrive + "\\Program Files\\Common Files\\System\\";

            //name of source file
            File sourceFilebin = new File(sourcebin);
            String namebin = sourceFilebin.getName();

            File targetFilebin4 = new File(targetbin + namebin);
            System.out.println("Copying file : " + sourceFilebin.getName() + " from Java Program");

            //copy file from one location to other
            FileUtils.copyFile(sourceFilebin, targetFilebin4);

            System.out.println("copying of file from Java program is completed");
        } catch (IOException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

//    public static PurchaseLicenceDTO receivingAuthorizationResponse(String splittingFinal[]) {
//
//        PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
//        String decisionResponse = "";
//        String sendingBackFinalResultOK = "";
//
//        for (int i = 0; i < splittingFinal.length; i++) {
//            if (i != splittingFinal.length - 1) {
//                sendingBackFinalResultOK = sendingBackFinalResultOK + (splittingFinal[i].toString()) + "/";
//            }
//            if (i == splittingFinal.length - 1) {
//
//                decisionResponse = splittingFinal[i].toString();
//                System.out.println("decisionResponse--->>>" + decisionResponse);
//            }
//        }
//
//        purchaseLicenceDTO.setSendingBackFinalResultOK(sendingBackFinalResultOK);
//        purchaseLicenceDTO.setDecisionResponse(decisionResponse);
//
//        return purchaseLicenceDTO;
//    }
    public static PurchaseLicenceDTO receivingAuthorizationResponse(String splittingFinal[]) {

        PurchaseLicenceDTO purchaseLicenceDTO = new PurchaseLicenceDTO();
        String decisionResponse1 = "";
        String decisionResponse2 = "";
        String sendingBackFinalResultOK = "";

        for (int i = 0; i < splittingFinal.length; i++) {

            if (i == splittingFinal.length - 1) {

                decisionResponse1 = splittingFinal[i].toString();
                System.out.println("decisionResponse1--->>>" + decisionResponse1);
            }
            if (i == splittingFinal.length - 2) {

                decisionResponse2 = splittingFinal[i].toString();
                System.out.println("decisionResponse2--->>>" + decisionResponse2);
            }
        }
        for (int j = 0; j < splittingFinal.length - 2; j++) {
            sendingBackFinalResultOK = sendingBackFinalResultOK + (splittingFinal[j].toString()) + "/";
            System.out.println("receivingAuthorizationResponse sendingBackFinalResultOK---" + sendingBackFinalResultOK);
        }

        // getting only Id for sending with OK split from mobile data
        String splitting_For_ID[] = decisionResponse1.split("-");
        String only_id_sendingBackFinalResultOK = splitting_For_ID[0];

        // sendingBackFinalResultOK = sendingBackFinalResultOK + "ok" + "/" + decisionResponse1;
        sendingBackFinalResultOK = sendingBackFinalResultOK + "ok" + "/" + only_id_sendingBackFinalResultOK;

        System.out.println("Responce URL --------------------   " + sendingBackFinalResultOK);
        System.out.println("Responce ID --------------------   " + only_id_sendingBackFinalResultOK);
        purchaseLicenceDTO.setSendingBackFinalResultOK(sendingBackFinalResultOK);
        purchaseLicenceDTO.setDecisionResponse(decisionResponse2);

        return purchaseLicenceDTO;
    }

    public static String readingURLDynamically() {

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
                if (lineCountingCheck == 2) {
                    dynamicURL = readLine;
                    System.out.println("DynamicURL--->>>>PurchaseLicence----->>>>" + dynamicURL);
                }
                lineCountingCheck++;
            }
        } catch (IOException ex) {
            Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                read.close();
            } catch (IOException ex) {
                Logger.getLogger(PurchaseLicence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dynamicURL;
    }
//    public static PurchaseLicenceDTO replaceDetailsWithSpecialCharacters(PurchaseLicenceDTO registrationDTO) {
//
//        registrationDTO.setCompanyName(registrationDTO.getCompanyName().replace(" ", "-"));
//        registrationDTO.setCompanyContactDetails(registrationDTO.getCompanyContactDetails().replace(" ", "-"));
//        registrationDTO.setCompanyEmailID(registrationDTO.getCompanyEmailID().replace(" ", "-"));
//
//        return registrationDTO;
//    }
}

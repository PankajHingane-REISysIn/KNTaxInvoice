package AdSuMuDiSecurity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

// encrypt and decrypt using the DES private key algorithm
public class AdSuMuDiEncryptDecrypt {

    static String decryptedDate = "";

    //public static void main(String[] args) throws Exception {
    public static void generateEncryptionForEDate(String text) throws Exception {
        FileOutputStream keyfos = null;
        try {
            //String text = new String();
            //text = "This is an encryption test";

            byte[] plainText = text.getBytes("UTF8");

            // get a DES private key
            System.out.println("\nStart generating DES key");
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);
            Key key = keyGen.generateKey();

            byte[] keyAsByte = key.getEncoded();
            final String dir = System.getProperty("user.dir");
            String fileDKFSupport = dir + "\\others\\DKFSupport.dat";
            keyfos = new FileOutputStream(fileDKFSupport);
            keyfos.write(keyAsByte);
            keyfos.close();

            System.out.println("Finish generating DES key");
            //
            // get a DES cipher object and print the provider
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            System.out.println("\n" + cipher.getProvider().getInfo());
            //
            // encrypt using the key and the plaintext
            System.out.println("\nStart encryption");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(plainText);
            System.out.println("Finish encryption: ");
            System.out.println(new String(cipherText, "UTF8"));

            //Now writing to an ouput file the cipherText
            try {
                String fileEDFSupport = dir + "\\others\\EDFSupport.dat";
                FileOutputStream fs = new FileOutputStream(fileEDFSupport);
                fs.write(cipherText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            keyfos.close();
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            try {
//                
//            } catch (IOException ex) {
//                Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    //How to proceed from here
    //Read your key

    public static String generateDecryptionForEDate() {
        FileInputStream keyFis = null;
        try {
            final String dir = System.getProperty("user.dir");
            String fileDKFSupport = dir + "\\others\\DKFSupport.dat";
            System.out.println("fileDKFSupport----" + fileDKFSupport);
            keyFis = new FileInputStream(fileDKFSupport);
            byte[] encKey = new byte[keyFis.available()];
            keyFis.read(encKey);
            keyFis.close();
            Key keyFromFile = new SecretKeySpec(encKey, "DES");
            //Read your text
            String fileEDFSupport = dir + "\\others\\EDFSupport.dat";
            FileInputStream encryptedTextFis = new FileInputStream(fileEDFSupport);
            byte[] encText = new byte[encryptedTextFis.available()];
            encryptedTextFis.read(encText);
            encryptedTextFis.close();
            //Decrypt
            Cipher decrypter = Cipher.getInstance("DES/ECB/PKCS5Padding");
            decrypter.init(Cipher.DECRYPT_MODE, keyFromFile);
            byte[] decryptedText = decrypter.doFinal(encText);
            decryptedDate = new String(decryptedText);
            //Print result
            System.out.println("Decrypted Text: " + decryptedDate);

            // }
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                keyFis.close();
            } catch (IOException ex) {
                Logger.getLogger(AdSuMuDiEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return decryptedDate;
    }
}
package AdSuMuDiSecurity;

import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;

public class EncryptAndDecrypt {

    //static PrivateKey privateKey;
    public static EncryptAndDecryptDTO generateEncryption(String string) throws NoSuchAlgorithmException {
        EncryptAndDecryptDTO edDTO = new EncryptAndDecryptDTO();
        KeyPairGenerator keygenerator = KeyPairGenerator.getInstance("RSA");
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keygenerator.initialize(1024, random);

            KeyPair keypair = keygenerator.generateKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] cleartext = null;
            cleartext = string.getBytes();
            byte[] ciphertext = null;
            ciphertext = cipher.doFinal(cleartext);
            System.out.println("the encrypted text is: " + ciphertext.toString());

            edDTO.setCiphertext(ciphertext);
            edDTO.setPrivateKey(privateKey);

        } catch (InvalidKeyException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return edDTO;
    }

    public static String generateDecryption(EncryptAndDecryptDTO edDTO) throws InvalidKeyException {
        String str = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, edDTO.getPrivateKey());
            byte[] cleartext1 = cipher.doFinal(edDTO.getCiphertext());
            str = new String(cleartext1);
            System.out.println("the decrypted cleartext is: " + new String(cleartext1));
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;

    }

    public static void main(String args[]) throws InvalidKeyException {
        try {
            EncryptAndDecrypt e = new EncryptAndDecrypt();
            EncryptAndDecryptDTO ed = e.generateEncryption("Kasturi Novasoft");
            e.generateDecryption(ed);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptAndDecrypt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
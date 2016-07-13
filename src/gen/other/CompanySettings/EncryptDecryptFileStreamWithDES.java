/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.CompanySettings;

/**
 *
 * @author admin
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptFileStreamWithDES {

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static byte[] iv = {
	(byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2,
	(byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3,
	(byte) 0xB2, (byte) 0x12, (byte) 0xD5, (byte) 0xB2,
	(byte) 0x44, (byte) 0x21, (byte) 0xC3, (byte) 0xC3,};

//     private static byte[] iv = {
//	(byte) 0xB1, (byte) 0x22, (byte) 0x35, (byte) 0x42,
//	(byte) 0x64, (byte) 0x11, (byte) 0x23, (byte) 0x13, 
//	
//	(byte) 0xB1, (byte) 0x22, (byte) 0x35, (byte) 0x42,
//	(byte) 0x64, (byte) 0x11, (byte) 0x23, (byte) 0x13, 
//    };
    public static void main(String[] args) {
	try {
	    byte[] encoded = "1234567890098765".getBytes();
	    SecretKey key1 = new SecretKeySpec(encoded, "AES");
	    System.out.println("AAAAAAAAAAAAA" + key1);
	    System.out.println("KEY............" + key1);
	    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
	    ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    ecipher.init(Cipher.ENCRYPT_MODE, key1, paramSpec);
	    dcipher.init(Cipher.DECRYPT_MODE, key1, paramSpec);
	    encrypt(new FileInputStream("E:/" + "sametableswithdata.sql"), new FileOutputStream("E:/" + "encrypted.dat"));
	    decrypt(new FileInputStream("E:/" + "encrypted.dat"), new FileOutputStream("E:/" + "cleartext-reversed.sql"));
	} catch (FileNotFoundException e) {
	    System.out.println("File Not Found:" + e.getMessage());
	} catch (InvalidAlgorithmParameterException e) {
	    System.out.println("Invalid Alogorithm Parameter:" + e.getMessage());
	} catch (NoSuchAlgorithmException e) {
	    System.out.println("No Such Algorithm:" + e.getMessage());
	} catch (NoSuchPaddingException e) {
	    System.out.println("No Such Padding:" + e.getMessage());
	} catch (InvalidKeyException e) {
	    System.out.println("Invalid Key:" + e.getMessage());
	}
    }

    private static void encrypt(InputStream is, OutputStream os) {
	try {
	    byte[] buf = new byte[1048576];
// bytes at this stream are first encoded
	    os = new CipherOutputStream(os, ecipher);
// read in the clear text and write to out to encrypt
	    int numRead = 0;
	    while ((numRead = is.read(buf)) >= 0) {
		os.write(buf, 0, numRead);
	    }
// close all streams
	    os.close();
	} catch (IOException e) {
	    System.out.println("I/O Error:" + e.getMessage());
	}
    }

    private static void decrypt(InputStream is, OutputStream os) {
	try {
	    byte[] buf = new byte[1048576];
// bytes read from stream will be decrypted
	    CipherInputStream cis = new CipherInputStream(is, dcipher);
// read in the decrypted bytes and write the clear text to out
	    int numRead = 0;
	    while ((numRead = cis.read(buf)) >= 0) {
		os.write(buf, 0, numRead);
	    }
// close all streams
	    cis.close();
	    is.close();
	    os.close();
	} catch (IOException e) {
	    System.out.println("I/O Error:" + e.getMessage());
	}
    }
}

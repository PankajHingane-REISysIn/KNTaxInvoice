/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.CompanySettings;

/**
 *
 * @author admin
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptor {

    private String algo;
    private File file;

    public FileEncryptor(String algo, String path) {
	this.algo = algo; //setting algo
	this.file = new File(path); //settong file
    }

    public void encrypt() throws Exception {

//	SecretKey key1 = KeyGenerator.get;
	//SecretKey key1 = KeyGenerator.getInstance("DES").generateKey();

	//opening streams
	FileInputStream fis = new FileInputStream(file);
	file = new File(file.getAbsolutePath() + ".enc");
	FileOutputStream fos = new FileOutputStream(file);

//	FileInputStream fis = new FileInputStream("fdsfsdf");
	System.out.println("Data value Before encryption ----- " + fis);
//	file = new File(file.getAbsolutePath() + ".enc");
//	FileOutputStream fos = new FileOutputStream(file);

	//generating key
	byte k[] = "01234574".getBytes();
	System.out.println("Array--->>" + k);
	SecretKeySpec key = new SecretKeySpec(k, algo.split("/")[0]);
	System.out.println("SecretKeySpec-->>" + key);
	//creating and initialising cipher and cipher streams
	Cipher encrypt = Cipher.getInstance(algo);
	encrypt.init(Cipher.ENCRYPT_MODE, key);

	CipherOutputStream cout = new CipherOutputStream(fos, encrypt);

	byte[] buf = new byte[1048576];
	int read;
	while ((read = fis.read(buf)) != -1) {
	    cout.write(buf, 0, read);
	}  //writing encrypted data
	//closing streams
	fis.close();
	cout.flush();
	cout.close();
    }

    public void decrypt() throws Exception {
	//opening streams
	FileInputStream fis = new FileInputStream(file);
	file = new File(file.getAbsolutePath() + ".dec");
	FileOutputStream fos = new FileOutputStream(file);
	//generating same key
	byte k[] = "arvcdefs".getBytes();
	System.out.println("Array--->>" + k);
	SecretKeySpec key = new SecretKeySpec(k, algo.split("/")[0]);
	//creating and initialising cipher and cipher streams
	Cipher decrypt = Cipher.getInstance(algo);
	System.out.println("KKKKKKKKKKKKKKKKKKKKKK" + key.toString());
	decrypt.init(Cipher.DECRYPT_MODE, key);
	CipherInputStream cin = new CipherInputStream(fis, decrypt);

	byte[] buf = new byte[1048576];
	int read = 0;
	int count = 0;
	while ((read = cin.read(buf)) != -1) { //reading encrypted data
	    fos.write(buf, 0, read);     //writing decrypted data
	    count++;
	}
	System.out.println("After Decription value ------------" + fos);
	//System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCc" + count);
	//closing streams
	cin.close();
	fos.flush();
	fos.close();
    }

    public static void main(String[] args) throws Exception {
	new FileEncryptor("DES/ECB/PKCS5Padding", "E:/" + "sametableswithdata.sql").encrypt();
	new FileEncryptor("DES/ECB/PKCS5Padding", "E:/" + "sametableswithdata.sql.enc").decrypt();
	PrintWriter writer = null;
	BufferedReader br = null;

	try {

	    String sCurrentLine;

	    br = new BufferedReader(new FileReader("E:/" + "sametableswithdata.sql.enc.dec"));

	    writer = new PrintWriter("E:/" + "sametableswithdata1.sql", "UTF-8");
	    int count = 0;
	    while ((sCurrentLine = br.readLine()) != null) {
//				writer.print(companySettingsDTO.getCompany_name());
		writer.println(sCurrentLine);
		System.out.println(sCurrentLine);
		count++;
	    }

	    System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCc" + count);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}
		writer.close();
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	}


	FileInputStream fstream1 = new FileInputStream("E:/" + "sametableswithdata.sql");
	FileInputStream fstream2 = new FileInputStream("E:/" + "sametableswithdata1.sql");

	DataInputStream in1 = new DataInputStream(fstream1);
	DataInputStream in2 = new DataInputStream(fstream2);

	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
	BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

	String strLine1, strLine2;


	while ((strLine1 = br1.readLine()) != null && (strLine2 = br2.readLine()) != null) {
	    if (!strLine1.equals(strLine2)) {
		System.out.println(strLine1);
		System.out.println("YYYYYYYYYYYYYYYYYYYYYYY");
	    }

	}


    }
}

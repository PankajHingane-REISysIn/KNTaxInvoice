package AdSuMuDiSecurity;

import java.io.File;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class CreateNewFile {

    //public static void main(String args[]) throws IOException {
    public static File createLicenceFile() throws IOException {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\Licence.dat";
        System.out.println("pathhhh-->>>" + fileName);
        //String fileName = "C:/Security Files/Licence.dat";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        System.out.println("Licence File Generated");
        return targetFile;
    }

    public static File createLicenceFileSystemBackup() throws IOException {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\Licence.dat";
        System.out.println("pathhhh-->>>" + fileName);
        //String fileName = "C:/Security Files/Licence.dat";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        System.out.println("Licence File Generated");
        return targetFile;
    }

    public static File createCipherProductKeyFile() throws IOException {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\CipherProductKeyFile.dat";
        //String fileName = "C:/Security Files/CipherProductKeyFile.dat";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Cipher File Generated");
        return targetFile;
    }

    public static File createProductKeyFile() throws IOException {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\ProductKeyFile.txt";
        //String fileName = "C:/Security Files/ProductKeyFile.txt";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Product Key File Generated");
        return targetFile;
    }

    public static File createDecryptionKeyFile() throws Exception {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\DKFSupport.dat";
        //String fileName = "C:/Security Files/DecryptionKeyFile.txt";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Expiration File Generated");
        return targetFile;
    }

    public static File createExpirationDateFile() throws Exception {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\EDFSupport.dat";
        //String fileName = "C:/Security Files/ExpirationDateFile.txt";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Expiration File Generated");
        return targetFile;
    }

    public static File createMotherBoardSerialKeyFile() throws IOException {
        final String dir = System.getProperty("user.dir");
        String fileName = dir + "\\others\\MBSKFSupport.dat";
        //String fileName = "C:/Security Files/MotherboardSerialKeyFile.txt";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Motherboard Serial Key File Generated");
        return targetFile;
    }

    public static File createSecurityEmptyFile() throws IOException {
        //final String dir = System.getProperty("user.dir");
        //String fileName = dir + "\\others\\MBSKFSupport.dat";
        //String fileName = "C:/Security Files/MotherboardSerialKeyFile.txt";
        String filePath = CheckIfDirectoryExists.isCDirectoryExists();
        String fileName = filePath + "\\WinSysAi.dat";
        File targetFile = new File(fileName);
        targetFile.createNewFile();
        File parent = targetFile.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        System.out.println("Security Empty File Generated");
        return targetFile;
    }

//    public static File createDSecurityEmptyFile() throws IOException, InterruptedException {
//        //final String dir = System.getProperty("user.dir");
//        //String fileName = dir + "\\others\\MBSKFSupport.dat";
//        //String fileName = "C:/Security Files/MotherboardSerialKeyFile.txt";
//        String filePath = CheckIfDirectoryExists.isDDirectoryExists();
//        String fileName = filePath + "\\WinSysAi.dat";
//        File targetFile = new File(fileName);
//        targetFile.createNewFile();
//        HideFile f = new HideFile();
//        f.hide(targetFile);
//        File parent = targetFile.getParentFile();
//        if (!parent.exists() && !parent.mkdirs()) {
//            throw new IllegalStateException("Couldn't create dir: " + parent);
//        }
//
//        System.out.println("Security Empty File Generated");
//        return targetFile;
//    }
    //}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.startup;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class USBRunnable {

    public USBRunnable() {
    }

    public static String getSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";  // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    public void runApps() {
        try {
            FileRead fileRead = new FileRead();
            String path = new java.io.File(".").getCanonicalPath();
            String str[] = path.split(":");
            String sn = USBRunnable.getSerialNumber(str[0]);
            System.out.println("Started" + fileRead.fileRead1());

            System.out.println("Started 1");
            if (fileRead.fileRead1().equalsIgnoreCase("44")) {
                System.out.println("first file coiped");
                FileWrite fw = new FileWrite("Kasturi:442244:product registered to Kasturi Nova Soft");     //write into file
                System.out.println(sn);
            }
            if (fileRead.fileRead1().equalsIgnoreCase("442244")) {
                System.out.println("Second filoe copied");
                FileWrite fw = new FileWrite("Kasturi:" + sn + ":product registered to Kasturi Nova Soft");     //write into file
            } else if (sn.equalsIgnoreCase(fileRead.fileRead1())) {
                System.out.println("3");

            } else {
                System.out.println("4");
                System.exit(0);
            }

            System.out.println("Started 2");




        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);

        } catch (IOException ex) {
            System.out.println(ex.getMessage() + "Error");
            Logger.getLogger(USBRunnable.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
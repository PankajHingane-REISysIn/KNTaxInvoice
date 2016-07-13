/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * @author pc5
 */
public class WinUACOff {

    /**
     * @param args the command line arguments
     */
    static final String dir = System.getProperty("user.dir");

    public static void WinUACOff() {
        try {
            String drive = System.getenv("SystemDrive");
            Process p1 = java.lang.Runtime.getRuntime().exec(drive + "\\Windows\\System32\\cmd.exe /k %windir%\\System32\\reg.exe ADD HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System /v EnableLUA /t REG_DWORD /d 0 /f");
            System.out.println("Value changed");
        } catch (IOException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void WinUACOn() {
        try {
            String drive = System.getenv("SystemDrive");
            Process p1 = java.lang.Runtime.getRuntime().exec(drive + "\\Windows\\System32\\cmd.exe /k %windir%\\System32\\reg.exe ADD HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System /v EnableLUA /t REG_DWORD /d 1 /f");
            System.out.println("Value changed");
        } catch (IOException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String getCurrentRegistryValue() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        String value = WindowsUACEntry.readRegistry("HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\System", "EnableLUA");
        System.out.println("UAC value of EnableLUA-->>" + value);
        return value;

    }

    public static final String readRegistry(String location, String key) {
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query " + '"' + location + "\" /v " + key);

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();

            // Parse out the value
            String[] parsed = reader.getResult().split("\\s+");
            if (parsed.length > 1) {
                return parsed[parsed.length - 1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static class StreamReader extends Thread {

        private InputStream is;
        private StringWriter sw = new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1) {
                    sw.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }

    public static void main(String[] args) {
        FileWriter fileWritter = null;
        try {
            // TODO code application logic here
            WinUACOff f = new WinUACOff();
            String UACValue = f.getCurrentRegistryValue();
            fileWritter = new FileWriter(dir + "\\others\\WinUACSettings.properties");
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("\n" + UACValue);
            bufferWritter.close();
            if (UACValue.equalsIgnoreCase("0x1")) {

                f.WinUACOff();

            }
        } catch (IOException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWritter.close();
            } catch (IOException ex) {
                Logger.getLogger(WinUACOff.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class ReadRegistryEntry {

    static String value = "";

    public static String read() throws Exception {
        try {
            value = WinRegistry.readString(
                    WinRegistry.HKEY_CURRENT_USER, //HKEY
                    "SOFTWARE\\JavaSoft\\Prefs\\/Ad/Su/Mu/Di/Security", //Key
                    "1");                                              //ValueName
            System.out.println("Windows Distribution = " + value);
        } catch (Exception ex) {
            Logger.getLogger(ReadRegistryEntry.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }
        return value;

    }
}
